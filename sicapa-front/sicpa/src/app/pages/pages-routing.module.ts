import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';


import { PagesComponent } from './pages.component';


const routes: Routes = [
  {
    path: 'menu',
    component: PagesComponent,
    loadChildren: () => import('./routing/menu-routing/menu-routing.module')
                        .then(m => m.MenuRoutingModule)
  },   
];

@NgModule({
    declarations: [],
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class PagesRoutingModule { }