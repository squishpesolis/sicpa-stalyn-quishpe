import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';


import { LazyLoadEvent, MessageService } from 'primeng/api';
import { ConfirmationService } from 'primeng/api';

import { DepartmentService } from 'src/app/services/deparment/department.service';
import { EnterpriseService } from 'src/app/services/enterprise/enterprise.service';
import { SweetAlertGenericoService } from '../../services/alertas/sweet-alert-generico.service';

import { Department } from 'src/app/models/department.model';
import { Enterprise } from 'src/app/models/enterprise.model';

import { IrisComponent } from '../iris-generico/iris.component';

import { IrisCrudInterface } from 'src/app/interfaces/iris-crud-interface';
import { IColumnaTabla } from '../../interfaces/columna-tabla-interface';
import { IHttpErrorResponse } from '../../interfaces/htttp-error-response';

import { ERROR_ALERT_TITLE, SUCCESS_ALERT_TITLE, SAVE_SUCCESS_MESSAGE } from '../../config/mensajes';


@Component({
  selector: 'app-departments',
  templateUrl: './departments.component.html',
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
export class DepartmentsComponent extends IrisComponent<Department> implements IrisCrudInterface<Department>, OnInit {

  loading!: boolean;
  columns!: IColumnaTabla[];
  filters!: IColumnaTabla[];
  lazyEvent!: LazyLoadEvent;
  totalRecords!: number;
  deparments!: Department[];
  deparmentsSeleccionadas!: Department[];

  empresasActivas!: Enterprise[];

  dataForm!: FormGroup;
  deparmentDialog!: boolean;
  viewMode!: boolean;

  constructor(private empresaService: EnterpriseService,
    private departmentService: DepartmentService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private builder: FormBuilder,
    public sweetAlertGenericoService: SweetAlertGenericoService) {
      super();
  }

 
  ngOnInit(): void {

    this.getActiveCompanies();
    this.loading = true;
    this.columns = this.buildColumnsTable();
    this.filters = this.buildFiltersTable();
    
  }

  buildForm(disabled = false): void {
    this.dataForm = this.builder.group({
      id: new FormControl(null, []),
      idDepartment: new FormControl(null, []),
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
        description: new FormControl({ value: '', disabled: disabled },
        [
          Validators.required,
          Validators.minLength(1),
          Validators.maxLength(150)
        ]),
        status: new FormControl({ value: true, disabled: disabled },
        [
          Validators.required
        ]),
        enterprise: new FormControl({ value: this.empresasActivas[0], disabled: disabled },
          [Validators.required]),
      eliminate: false,
      createDate: '',
      modifiedDate: '',
      createdBy: '',
      modifiedBy: '',
    });
  }

  setFormData(entidad: Department): void {
    if (this.dataForm != null && entidad != null)
    this.dataForm.patchValue(entidad);
  }

  getFormData(): void {
    return this.dataForm.value;
  }

  hideDialog(): void {
    this.deparmentDialog = false;
  }

  create() {
    this.deparmentDialog = true;
    this.buildForm();
    this.viewMode = false;
  }

  edit(entidad: Department) {
    this.deparmentDialog = true;
    this.buildForm();
    this.buscarPorId(entidad, false)
  }

  show(entidad: Department) {
    this.deparmentDialog = true;
    this.buildForm(true);
    this.buscarPorId(entidad, true)
  }


  buscarPorId(entidad: Department, viewMode: boolean) {
    this.departmentService.buscarPorId(entidad.id!)
      .subscribe({
        next: (company) => {
  
          this.setFormData(<Department> company);
          this.viewMode = viewMode;
        },
        error: (error) => {
          this.sweetAlertGenericoService.sweetAletGenericoHttp(error, ERROR_ALERT_TITLE)
        }
      });
  }


  save() {
    let company = this.departmentService.addAuditoria(this.getFormData());
    if (!this.dataForm.invalid) {
      this.departmentService.guardar(company)
        .subscribe({
          next: (company) => {
    
            this.obtenerListaPorPaginacion();
            this.deparmentDialog = false;
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
    this.departmentService.cargarTablaLazy(event)
      .subscribe({
        next: (company) => {
          this.totalRecords = company.contarRegistros;
          this.deparments = company.obtenerListaPagina;
          console.log("deparments") 
          console.log(this.totalRecords)
          console.log("deparments") 
          console.log(this.deparments)
          this.loading = false;
        },
        error: (error) => {
          this.sweetAlertGenericoService.sweetAletGenericoHttp(error, ERROR_ALERT_TITLE)
        }
      });
  }

  public get sendService(): any {
    return this.departmentService
  }

  resetSelectedItems(): void {
    this.deparmentsSeleccionadas = [];
    this.obtenerListaPorPaginacion();
  }

  buildColumnsTable(): any[] {
    return [
      { field: 'name', header: 'Name'},
      { field: 'phone', header: 'Phone'},
      { field: 'adress', header: 'Adress'},
      { field: 'description', header: 'Descrip.'},
      { field: 'status', header: 'status' },
      { field: 'enterprise.name', header: 'Company' },
    ];
  }

  buildFiltersTable(): any[] {
    return [
      { field: 'name', header: 'Name'},
      { field: 'phone', header: 'Phone'},
      { field: 'adress', header: 'Adress'},
      { field: 'description', header: 'Descrip.'},
      { field: 'status', header: 'status' },
      { field: 'enterprise.name', header: 'Company' },
    ];
  }

  getErrorForm(controlName: string): string {
    return this.formarErroresForm(this.dataForm, controlName);
  }

  getActiveCompanies() {
    this.empresaService.getActiveCompanies().subscribe({
      next: (company) => {
        this.empresasActivas = company;
        console.log(this.empresasActivas)

      },
      error: (error) => {
        this.sweetAlertGenericoService.sweetAletGenericoHttp(error, ERROR_ALERT_TITLE)
      }
    });

  }



}
