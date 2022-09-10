import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'

import {ButtonModule} from 'primeng/button';
import {CardModule} from 'primeng/card';
import {DividerModule} from 'primeng/divider';
import {ImageModule} from 'primeng/image';
import {ListboxModule} from 'primeng/listbox';
import { MenubarModule } from 'primeng/menubar';
import { InputTextModule } from 'primeng/inputtext';







import { MenuComponent } from './menu/menu.component';
import { PagesRoutingModule } from './pages-routing.module';
import { PagesComponent } from './pages.component';
import { EnterpriseComponent } from './enterprise/enterprise.component';
import { HomeComponent } from './home/home.component';
import { DepartmentsComponent } from './departments/departments.component';
import { EmployeesComponent } from './employees/employees.component';



@NgModule({
  declarations: [
    MenuComponent,
    PagesComponent,
    EnterpriseComponent,
    HomeComponent,
    DepartmentsComponent,
    EmployeesComponent

  ],
  imports: [
    CommonModule,
    HttpClientModule,

    //Modules
    ButtonModule,
    CardModule,
    DividerModule,
    ImageModule,
    ListboxModule,
    MenubarModule,
    InputTextModule,
    // Routing
    PagesRoutingModule,
    BrowserModule,
    BrowserAnimationsModule

  ],
  exports: [
    PagesComponent
  ],
})
export class PagesModule { }
