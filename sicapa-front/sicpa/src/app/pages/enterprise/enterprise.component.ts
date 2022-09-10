import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';


import { LazyLoadEvent, MessageService } from 'primeng/api';
import { ConfirmationService } from 'primeng/api';

import { EnterpriseService } from 'src/app/services/enterprise/enterprise.service';
import { SweetAlertGenericoService } from '../../services/alertas/sweet-alert-generico.service';

import { Enterprise } from 'src/app/models/enterprise.model';

import { IrisComponent } from '../iris-generico/iris.component';

import { IrisCrudInterface } from 'src/app/interfaces/iris-crud-interface';
import { IColumnaTabla } from '../../interfaces/columna-tabla-interface';
import { IHttpErrorResponse } from '../../interfaces/htttp-error-response';

import { ERROR_ALERT_TITLE, SUCCESS_ALERT_TITLE, SAVE_SUCCESS_MESSAGE } from '../../config/mensajes';

@Component({
  selector: 'app-enterprise',
  templateUrl: './enterprise.component.html',
  styleUrls: ['../../demo/view/tabledemo.css'],
  styles: [`
  :host ::ng-deep .p-dialog .product-image {
      width: 150px;
      margin: 0 auto 2rem auto;
      display: block;
  }
`],
providers: [MessageService, ConfirmationService]
})
export class EnterpriseComponent extends IrisComponent<Enterprise> implements IrisCrudInterface<Enterprise>, OnInit {



  loading!: boolean;
  columns!: IColumnaTabla[];
  lazyEvent!: LazyLoadEvent;
  totalRecords!: number;
  empresas!: Enterprise[];
  empresasSeleccionadas!: Enterprise[];


  dataForm!: FormGroup;
  empresaDialog!: boolean;
  viewMode!: boolean;


  constructor(private empresaService: EnterpriseService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private builder: FormBuilder,
    public sweetAlertGenericoService: SweetAlertGenericoService) {
      super();
  }

  ngOnInit(): void {


    this.loading = true;
    this.columns = this.buildColumnsTable();
  }

  buildForm(disabled = false): void {
    this.dataForm = this.builder.group({
      id: new FormControl(null, []),
      idEnterprise: new FormControl(null, []),
      phone: new FormControl({ value: '', disabled: disabled }, [
        Validators.required,
        Validators.minLength(7),
        Validators.maxLength(10)
      ]),
      name: new FormControl({ value: '', disabled: disabled },
        [
          Validators.required,
          Validators.minLength(1),
          Validators.maxLength(50)
        ]),
        adress: new FormControl({ value: '', disabled: disabled },
        [
          Validators.required,
          Validators.minLength(1),
          Validators.maxLength(150)
        ]),
        status: new FormControl({ value: true, disabled: disabled },
        [
          Validators.required
        ]),
      eliminate: false,
      fechaCreacion: '',
      fechaModificacion: '',
      usuarioCreacion: '',
      usuarioModificacion: '',
    });
  }

  setFormData(entidad: Enterprise): void {
    if (this.dataForm != null && entidad != null)
    this.dataForm.patchValue(entidad);
  }

  getFormData(): void {
    return this.dataForm.value;
  }

  hideDialog(): void {
    this.empresaDialog = false;
  }

  create() {
    this.empresaDialog = true;
    this.buildForm();
    this.viewMode = false;
  }

  edit(entidad: Enterprise) {
    this.empresaDialog = true;
    this.buildForm();
    this.buscarPorId(entidad, false)
  }

  show(entidad: Enterprise) {
    this.empresaDialog = true;
    this.buildForm(true);
    this.buscarPorId(entidad, true)
  }


  buscarPorId(entidad: Enterprise, viewMode: boolean) {
    this.empresaService.buscarPorId(entidad.id!)
      .subscribe({
        next: (company) => {
  
          this.setFormData(<Enterprise> company);
          this.viewMode = viewMode;
        },
        error: (error) => {
          this.sweetAlertGenericoService.sweetAletGenericoHttp(error, ERROR_ALERT_TITLE)
        }
      });
  }


  save() {
    let company = this.empresaService.addAuditoria(this.getFormData());
    if (!this.dataForm.invalid) {
      this.empresaService.guardar(company)
        .subscribe({
          next: (company) => {
    
            this.obtenerListaPorPaginacion();
            this.empresaDialog = false;
            this.sweetAlertGenericoService.sweetAletGenericoSuccess(SUCCESS_ALERT_TITLE, SAVE_SUCCESS_MESSAGE);
          },
          error: (error) => {
            this.sweetAlertGenericoService.sweetAletGenericoHttp(error, ERROR_ALERT_TITLE)
          }
        });
    }
  }

  obtenerListaPorPaginacion(): void {
    this.loadLazyRecords(this.lazyEvent);
  }


  loadLazyRecords(event: LazyLoadEvent) {
    this.lazyEvent = event;
    this.loading = true;
    this.empresaService.cargarTablaLazy(event)
      .subscribe({
        next: (company) => {
          this.totalRecords = company.contarRegistros;
          this.empresas = company.obtenerListaPagina;
          console.log("totalRecords") 
          console.log(this.totalRecords)
          console.log("RRRRRRRRRRRRRRRRR") 
          console.log(this.empresas)
          this.loading = false;
        },
        error: (error) => {
          this.sweetAlertGenericoService.sweetAletGenericoHttp(error, ERROR_ALERT_TITLE)
        }
      });
  }

  public get sendService(): any {
    return this.empresaService
  }

  resetSelectedItems(): void {
    this.empresasSeleccionadas = [];
    this.obtenerListaPorPaginacion();
  }

  buildColumnsTable(): any[] {
    return [
      { field: 'name', header: 'Name'},
      { field: 'adress', header: 'Adress'},
      { field: 'phone', header: 'Phone'},
      { field: 'status', header: 'status' },
    ];
  }

  getErrorForm(controlName: string): string {
    return this.formarErroresForm(this.dataForm, controlName);
  }



}
