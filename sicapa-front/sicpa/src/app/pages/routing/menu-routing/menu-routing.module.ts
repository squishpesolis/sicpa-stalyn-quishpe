
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouterModule, Routes } from '@angular/router';


import { MenuComponent } from '../../menu/menu.component';


const routes: Routes = [
    {
      path: '**', component: MenuComponent
    }
  ];
  
  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })

export class MenuRoutingModule {}
