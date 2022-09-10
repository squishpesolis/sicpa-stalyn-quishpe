import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators, FormArray } from '@angular/forms';

import { LazyLoadEvent, MessageService } from 'primeng/api';
import { ConfirmationService } from 'primeng/api';

import { DepartmentService } from 'src/app/services/deparment/department.service';
import { EmployeeService } from 'src/app/services/employee/employe.service';
import { SweetAlertGenericoService } from '../../services/alertas/sweet-alert-generico.service';

import { Department } from 'src/app/models/department.model';
import { Employee } from 'src/app/models/employee.model';

import { IrisComponent } from '../iris-generico/iris.component';

import { IrisCrudInterface } from 'src/app/interfaces/iris-crud-interface';
import { IColumnaTabla } from '../../interfaces/columna-tabla-interface';
import { IHttpErrorResponse } from '../../interfaces/htttp-error-response';

import { ERROR_ALERT_TITLE, SUCCESS_ALERT_TITLE, SAVE_SUCCESS_MESSAGE } from '../../config/mensajes';
import { EmployeeDeparment } from 'src/app/models/employee-department.model';



@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
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
export class EmployeesComponent extends IrisComponent<Employee> implements IrisCrudInterface<Employee>, OnInit {

  loading!: boolean;
  columns!: IColumnaTabla[];
  filters!: IColumnaTabla[];
  lazyEvent!: LazyLoadEvent;
  totalRecords!: number;
  employees!: Employee[];
  employeesSeleccionadas!: Employee[];

  departmesActives!: Department[];

  dataForm!: FormGroup;
  employeeDialog!: boolean;
  viewMode!: boolean;

  constructor(
    private departmentService: DepartmentService,
    private employeeService: EmployeeService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private builder: FormBuilder,
    public sweetAlertGenericoService: SweetAlertGenericoService) {
      super();
  }

 
  ngOnInit(): void {

    this.getActiveDepartments();
    this.loading = true;
    this.columns = this.buildColumnsTable();
    this.filters = this.buildFiltersTable();
    
  }

  buildForm(disabled = false): void {
    this.dataForm = this.builder.group({
      id: new FormControl(null, []),
      idEmployee: new FormControl(null, []),
      name: new FormControl({ value: '', disabled: disabled },
      [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(50)
      ]),
      surname: new FormControl({ value: '', disabled: disabled },
      [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(150)
      ]),
      email: new FormControl({ value: '', disabled: disabled },
      [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(50)
      ]),
      position: new FormControl({ value: '', disabled: disabled },
      [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(150)
      ]),
      status: new FormControl({ value: true, disabled: disabled },
        [
          Validators.required
        ]),
        age: new FormControl({ value: 0, disabled: disabled }, [
        Validators.required
      ]),
      departments: this.builder.array([]),
      eliminate: false,
      createDate: '',
      modifiedDate: '',
      createdBy: '',
      modifiedBy: '',
    });
  }

  get departments(): FormArray {
    return this.dataForm.get('departments') as FormArray
  }

  /*setFormData(entidad: Employee): void {
    if (this.dataForm != null && entidad != null)
    this.dataForm.patchValue(entidad);
  }*/

  setFormData(entidad: Employee,disabled = false): void {
    console.log("ENTIDAD")
    console.log(entidad)
    if (this.dataForm != null && entidad != null) {
      this.dataForm.patchValue(entidad);
      let departmentssAux: EmployeeDeparment[] = entidad.departments!;
      for(const se of departmentssAux) {
        let aux = this.addControlsDeparment(se.department!, disabled)
        this.departments.push(aux);
      }
    }
  }

  private addControlsDeparment(seve: Department,disabled = false) {
    let depart = seve!;

    console.log("departdepartdepartdepartdepartdepartdepart")
    console.log(depart)
/*     return new FormGroup({
      id: new FormControl({value: depart.id, disabled: disabled}),
      idDepartment: new FormControl({value: depart.idDepartment, disabled: disabled}),
      phone: new FormControl({value: depart.phone, disabled: disabled}),
      name: new FormControl({value: depart.name, disabled: disabled}),
      adress: new FormControl({value: depart.adress, disabled: disabled}),
      status: new FormControl({value: depart.status, disabled: disabled}),
      description: new FormControl({value: depart.description, disabled: disabled}),
      eliminate: new FormControl({value: depart.eliminate, disabled: disabled}),
    }); */

    return new FormGroup({
      department: new FormControl({ value: depart, disabled: disabled },
        [Validators.required])
    });



  }

  
  addDepartment(){
    let aux = this.addControlsDeparment(this.departmesActives[0])
    this.departments.push(aux);
  }

  public removeDepartment(i: number) {
    const depart = this.departments
    if (depart.length > 1) {
      depart.removeAt(i)
    } else {
      depart.reset()
    }
  }

  getFormData(): void {
    return this.dataForm.value;
  }

  hideDialog(): void {
    this.employeeDialog = false;
  }

  create() {
    this.employeeDialog = true;
    this.buildForm();
    this.addDepartment();
    this.viewMode = false;
  }

  edit(entidad: Employee) {
    this.employeeDialog = true;
    this.buildForm();
    this.buscarPorId(entidad, false)
  }

  show(entidad: Employee) {
    this.employeeDialog = true;
    this.buildForm(true);
    this.buscarPorId(entidad, true)
  }


  buscarPorId(entidad: Employee, viewMode: boolean) {
    this.employeeService.buscarPorId(entidad.id!)
      .subscribe({
        next: (employ) => {
  
          this.setFormData(<Employee> employ);
          this.viewMode = viewMode;
        },
        error: (error) => {
          this.sweetAlertGenericoService.sweetAletGenericoHttp(error, ERROR_ALERT_TITLE)
        }
      });
  }


  save() {
    let employ = this.employeeService.addAuditoria(this.getFormData());
    if (!this.dataForm.invalid) {

      console.log("EMPLOYED")
      console.log(employ)
      this.employeeService.guardar(employ)
        .subscribe({
          next: (employ) => {
    
            this.obtenerListaPorPaginacion();
            this.employeeDialog = false;
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
    this.employeeService.cargarTablaLazy(event)
      .subscribe({
        next: (employ) => {
          this.totalRecords = employ.contarRegistros;
          this.employees = employ.obtenerListaPagina;
          console.log("employees") 
          console.log(this.totalRecords)
          console.log("employees") 
          console.log(this.employees)
          this.loading = false;
        },
        error: (error) => {
          this.sweetAlertGenericoService.sweetAletGenericoHttp(error, ERROR_ALERT_TITLE)
        }
      });
  }

  public get sendService(): any {
    return this.employeeService
  }

  resetSelectedItems(): void {
    this.employeesSeleccionadas = [];
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

  getActiveDepartments() {
    this.departmentService.getActiveDepartments().subscribe({
      next: (employ) => {
        this.departmesActives = employ;
        console.log(this.departmesActives)

      },
      error: (error) => {
        this.sweetAlertGenericoService.sweetAletGenericoHttp(error, ERROR_ALERT_TITLE)
      }
    });

  }




}
