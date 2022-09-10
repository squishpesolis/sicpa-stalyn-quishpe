import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule, NoopAnimationsModule } from '@angular/platform-browser/animations';

import {ButtonModule} from 'primeng/button';
import {CardModule} from 'primeng/card';
import {DividerModule} from 'primeng/divider';
import {ImageModule} from 'primeng/image';
import {ListboxModule} from 'primeng/listbox';
import { MenubarModule } from 'primeng/menubar';
import { InputTextModule } from 'primeng/inputtext';
import { AvatarModule } from 'primeng/avatar';
import { AvatarGroupModule } from 'primeng/avatargroup';
import { MenuModule } from 'primeng/menu';
import {ToastModule} from 'primeng/toast';
import {TableModule} from 'primeng/table';
import {DialogModule} from 'primeng/dialog';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {RadioButtonModule} from 'primeng/radiobutton';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {InputNumberModule} from 'primeng/inputnumber';
import {FileUploadModule} from 'primeng/fileupload';
import {CheckboxModule} from 'primeng/checkbox';
import {DropdownModule} from 'primeng/dropdown';
import {PasswordModule} from 'primeng/password';
import {CalendarModule} from 'primeng/calendar';
import {ProgressSpinnerModule} from 'primeng/progressspinner';
import {AccordionModule} from 'primeng/accordion';
import {ToolbarModule} from 'primeng/toolbar';


import { PipesModule } from '../pipes/pipes.module';
import { ComponentesModule } from '../componentes/componentes.module';




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
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AvatarModule,
    AvatarGroupModule,
    DividerModule,
    MenuModule,
    MenubarModule,
    BrowserAnimationsModule,
    NoopAnimationsModule,
    ToastModule,
    ToolbarModule,
    TableModule,
    DialogModule,
    ConfirmDialogModule,
    RadioButtonModule,
    InputTextModule,
    InputTextareaModule,
    InputNumberModule,
    FileUploadModule,
    HttpClientModule,
    ButtonModule,
    CheckboxModule,
    DropdownModule,
    PasswordModule,
    CalendarModule,
    ProgressSpinnerModule,
    AccordionModule,
    ListboxModule,
    CardModule,
    ImageModule,
    // Routing
    PagesRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,

    //
    PipesModule,
    ComponentesModule

  ],
  exports: [
    PagesComponent
  ],
})
export class PagesModule { }
