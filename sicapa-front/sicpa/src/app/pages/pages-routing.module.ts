import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';


import { PagesComponent } from './pages.component';


const routes: Routes = [
  {
    path: 'dashboard',
    component: PagesComponent,
    loadChildren: () => import('./routing/dashboard-routing/dashboard-routing.module')
    .then(m => m.DashboardRoutingModule)
  },
  {
    path: 'system',
    component: PagesComponent,
    loadChildren:() => import('./routing/system-routing/system-routing.module').then(m => m.SystemRoutingModule)
    //canActivate: [RouteAccessGuard],
  },   
];

@NgModule({
    declarations: [],
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class PagesRoutingModule { }