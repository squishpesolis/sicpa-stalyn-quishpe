import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PagesModule } from './pages/pages.module';

const routes: Routes = [

  { path: '**', pathMatch: 'full', redirectTo: '/dashboard' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true, onSameUrlNavigation: 'reload' }), PagesModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
