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








import { MenuComponent } from './menu/menu.component';
import { PagesRoutingModule } from './pages-routing.module';
import { PagesComponent } from './pages.component';



@NgModule({
  declarations: [
    MenuComponent,
    PagesComponent

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
