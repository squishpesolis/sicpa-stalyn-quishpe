import { Injectable } from '@angular/core';
import Swal, { SweetAlertIcon } from 'sweetalert2';
import { IHttpErrorResponse } from 'src/app/interfaces/htttp-error-response';
import { IHttpErrorResponseSC } from '../../interfaces/htttp-error-response-sc';

@Injectable({
  providedIn: 'root'
})
export class SweetAlertGenericoService {

  constructor() {
   }

   sweeAletEliminarGenerico(texto: string = 'Texto', icono: string= 'warning') {
        const a = Swal.fire({
        title: '¿Está seguro que desea eliminar?' ,
        text: texto,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Eliminar',
        cancelButtonText: 'Cancelar'
        });
        return a;
    }

    sweetAlertConfirmacionEliminado() {
      Swal.fire(
        {
          title: 'Eliminado!',
          text: 'Registro/s eliminado.',
          icon: 'success'
        }
      );
    }

    sweetAletGenericoSuccess(titulo: string = 'titulo', texto: string = 'texto') {
      Swal.fire({ title: titulo, text: texto,  icon: 'success' });
    }

    sweetAletConfirmacionGuardado(titulo: string = 'ENTIDAD', texto: string = 'texto') {
      Swal.fire({ title: titulo, text: texto,  icon: 'success' });
    }

    sweetAletGenericoWarning(titulo: string = 'titulo', texto: string = 'Config texto...') {
      Swal.fire({ title: titulo, text: texto,  icon: 'warning' });
    }

  sweetAletGenericoHttp(resp: any, titulo: string = 'titulo') {
      const respAux: IHttpErrorResponse = resp;
      let iconAux: SweetAlertIcon = 'warning';
      switch (respAux.error.httpStatus) {
        case 500:
          iconAux = 'error';
          break;
        case 400:
          iconAux = 'warning';
          break;
        case 401:
          iconAux = 'warning';
          break;
        default:
          iconAux = 'warning';
          break;
      }
      Swal.fire({ title: titulo, text: respAux.error.mensajes,  icon: iconAux });
    }


    sweetAletGenericoHttpSC(resp: any, titulo: string = 'titulo') {
      const respAux = resp;
      let iconAux: SweetAlertIcon = 'warning';
      let texto = '';
      if(respAux.error.error) {
        switch (respAux.error.error.statusCode) {
          case 500:
            iconAux = 'error';
            break;
          case 404:
            iconAux = 'error';
            break;
          case 400:
            iconAux = 'warning';
            break;
          case 401:
            iconAux = 'warning';
            break;
          default:
            iconAux = 'warning';
            break;
        }

        texto = respAux.error.error.message
      }else {
        texto = respAux.error
      }

      Swal.fire({ title: titulo, text: texto,  icon: iconAux });
    }

    sweetAletEliminarVariosSuccess(cantidadRegistrosEliminados: number = 0, entidad: string = '') {
      const mensaje = cantidadRegistrosEliminados + ' ' + entidad + ' exitoso!!';
      Swal.fire({ title: 'ELIMINAR',
                  text: 'Eliminación de '
                         .concat(mensaje),
                  icon: 'success' });
    }


    // ERRORE HTTP
}
