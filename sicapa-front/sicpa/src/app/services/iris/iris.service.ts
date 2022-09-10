import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { LazyLoadEvent, FilterMetadata, SelectItem } from 'primeng/api';



// Intrefaces
import { LazyParametros } from '../../interfaces/lazy-parametros.interface';
import { BuscarPorCriterio } from '../../interfaces/buscar-por-criterio.interface';
import { LazyTabla } from '../../interfaces/lazy-tabla.interface';
import { Base } from '../../models/base.model';



import { URL_SERVICES,
  ZONA_HORARIO_TIEMPO,
  IDIOMA_PRINCIPAL_SISTEMA} from '../../config/config';

import { MayHaveId } from 'src/app/interfaces/generic-id.interface';




export class IrisService<T extends MayHaveId> {
  protected url: string;
  protected http: HttpClient;

  constructor(url: string, http: HttpClient) {
    this.url = URL_SERVICES + url;
    this.http = http;
  }

  crear(modelo: T): Observable<object> {
    return this.http.post(this.url, modelo) as Observable<object>;
  }

  guardar(modelo: T): Observable<object> {
    if(modelo.id === null) {
      return this.http.post(this.url, modelo) as Observable<object>;
    }
    return this.editar(modelo)
    
  }

  editar(modelo: T): Observable<object> {
    return this.http.put(this.url, modelo) as Observable<object>;
  }

  

  obtenerListaPorPaginacion(lazyParametros: LazyParametros) {
    let urlCompleta = this.url.concat('/obtenerListaPorPaginacion');
    return this.http.put(urlCompleta, JSON.stringify(lazyParametros)) as Observable<object>;
  }

  obtenerTodo(): Observable<object> {
    let urlCompleta = this.url.concat('/obtenerTodo');
    return this.http.get(urlCompleta) as Observable<object>;
  }

  buscarPorId(id: string): Observable<object> {
    let urlCompleta = this.url.concat('/').concat(id);
    return this.http.get(urlCompleta) as Observable<object>;
  }

  autocompletarEntidad(key: string, cadena: string): Observable<object> {
    let urlCompleta = this.url.concat('/autocompletarEntidad');

      urlCompleta += '?key=' + key + '&cadena=' + cadena;

    return this.http.get(urlCompleta) as Observable<object>;
  }

  autocompletarEntidadPorUrls(urlAux: string, key: string, cadena: string): Observable<object> {
    urlAux = URL_SERVICES + urlAux;
    let urlCompleta = urlAux.concat('/autocompletarEntidad');
    urlCompleta += '?key=' + key + '&cadena=' + cadena;
    return this.http.get(urlCompleta) as Observable<object>;
  }

  contarRegistro(lazyParametros: LazyParametros) {
    let urlCompleta = this.url.concat('/contarRegistros');
    return this.http.put(urlCompleta, JSON.stringify(lazyParametros)) as Observable<object>;
  }

  cargarTablaLazy(event: LazyLoadEvent) {
    let lazyParametros:LazyParametros = this.formarLazyParametros(event);
    const obtenerListaPagina = this.obtenerListaPorPaginacion(lazyParametros);
    const contarRegistros = this.contarRegistro(lazyParametros);
    return forkJoin({ obtenerListaPagina, contarRegistros }) as Observable<LazyTabla>;
  }

  formarLazyParametros(event: LazyLoadEvent): LazyParametros {
    let lazyParametros: LazyParametros = {
       listaBuscarCriterio: [],
       pagina: 1,
       size: 10,
       order: 1,
       campoOrdenar: ''
    };
    const filtrosObject = event.filters!;
    const size: number = event.rows!;
    const pagina: number = event.first!;
    const paginaAux = pagina / size;
    const order = event.sortOrder;
    const sortField = event.sortField;
    let campoOrdenar: string = '';
    campoOrdenar = sortField === undefined ? 'createDate' : sortField;
    if (Object.keys(filtrosObject).length === 0) {
      // No hay filtros;
      return lazyParametros = this.formarLazyLoadParametros(
        paginaAux,
        size,
        null,
        order,
        campoOrdenar
      );
    } else {
      const listaBuscarCriterio: BuscarPorCriterio[] = [];
      Object.keys(filtrosObject).map((key) => {
        let filterMetadata: FilterMetadata = {};
        let buscarCriterio: BuscarPorCriterio = {};
        let operacion: string = '';
        filterMetadata = filtrosObject[key];
        operacion = filterMetadata.matchMode!;

        let filterMetadataValue = this.toLowerConverter(filterMetadata.value);

        // formar filtros solo si hay datos
        if (filterMetadataValue != null || filterMetadataValue != undefined) {
          if (filterMetadataValue != "") {
            if(key.includes("_")) {
              const listaCompuesta = key.split("_")
              let keyAux = listaCompuesta[0].toString()
              let valueAux = listaCompuesta[1]
              let objectAux = {[valueAux]: filterMetadataValue}
              buscarCriterio = {
                key: keyAux,
                value: [(objectAux)],
                operacion,
              };
            }else {
              buscarCriterio = {
                key,
                value: [(filterMetadataValue)],
                operacion,
              };
            }
            listaBuscarCriterio.push(buscarCriterio);
          }
        }

      });

      return lazyParametros = this.formarLazyLoadParametros(
        paginaAux,
        size,
        listaBuscarCriterio,
        order,
        campoOrdenar
      );
    }
  }

  formarLazyLoadParametros(
    pagina: number,
    size: number,
    listaBuscarCriterio: BuscarPorCriterio[] | null,
    order: number = 0,
    campoOrdenar: string = 'createDate'): LazyParametros {

    // Filtar por sucursal
    if (listaBuscarCriterio === null) {
      listaBuscarCriterio = [];
    }

    const lazyParametros: LazyParametros = {
      pagina,
      size,
      order,
      campoOrdenar,
      listaBuscarCriterio
    };
    return lazyParametros;
  }

  eliminarVarios(ids: any[]) {
    let urlCompleta = this.url.concat('/eliminarPorListaIds');
    return this.http.put(urlCompleta, ids) as Observable<number>;
  }


  toLowerConverter(value: any): any {
    if (value != null && value != undefined && value != "" && (typeof value) === 'string')
      return (value).toLowerCase();
    return value
  }


  formarAuditoria(entidad: any): Base {

    let createDate = entidad.createDate;
    let createdBy = entidad.createdBy;

    const modifiedBy = 'sicpa-front';

    if (!createDate) {
      createDate = new Date().toLocaleString(IDIOMA_PRINCIPAL_SISTEMA, { timeZone: ZONA_HORARIO_TIEMPO });
      createDate = this.formarFecha(createDate);
    }


    if (!createdBy) {
      createdBy = 'sicpa-front';
    }
    const auditoria: Base = new Base(createDate, this.obtenerFechaActual(), createdBy, modifiedBy);
    return auditoria;
  }

  formarFecha(fechaYHora: string) {
    const arrayFecha: string[] = fechaYHora.split('/');

    let month = '' + arrayFecha[1];
    let day = '' + arrayFecha[0];
    if (month.length < 2) {
      month = '0' + month;
    }
    if (day.length < 2) {
      day = '0' + day;
    }
    return [day, month, arrayFecha[2]].join('-');
  }

  obtenerFechaActual() {
    const ecuadorTime = new Date().toLocaleString(IDIOMA_PRINCIPAL_SISTEMA, { timeZone: ZONA_HORARIO_TIEMPO });
    const d = this.formarFecha(ecuadorTime);
    return d;
  }



  borrarLocalStorage(){
    localStorage.clear();
  }


  addAuditoria(modelo: any) {
    let auditoria = this.formarAuditoria(modelo);
    modelo.createDate = auditoria.createDate;
    modelo.modifiedDate = auditoria.modifiedDate;
    modelo.createdBy = auditoria.createdBy;
    modelo.modifiedBy = auditoria.modifiedBy;
    return modelo;
  }



}
