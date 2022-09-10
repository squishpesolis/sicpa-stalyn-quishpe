import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DepartmentsComponent } from '../../departments/departments.component';
import { EmployeesComponent } from '../../employees/employees.component';
import { EnterpriseComponent } from '../../enterprise/enterprise.component';


const routes: Routes = [
  {
    path: 'enterprise',
    component: EnterpriseComponent,
  },
  {
    path: 'departments',
    component: DepartmentsComponent,
  },
  {
    path: 'employes',
    component: EmployeesComponent,
  },
  {
    path: '**', component: EnterpriseComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SystemRoutingModule { }
