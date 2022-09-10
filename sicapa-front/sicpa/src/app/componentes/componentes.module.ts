import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

// Componentes
import { EliminarItemTablaComponent } from './eliminar-item-tabla/eliminar-item-tabla.component';

// Modulos COmware
import { PipesModule } from '../pipes/pipes.module';

// Prime Ng
import {ButtonModule} from 'primeng/button';
import {RippleModule} from 'primeng/ripple';
import {ProgressSpinnerModule} from 'primeng/progressspinner';


import { SpinerComponent } from './spiner/spiner.component';


@NgModule({
  declarations: [EliminarItemTablaComponent, SpinerComponent],
  imports: [
    CommonModule,

    // Modulos Comware
    PipesModule,

    //Prime ng
    ButtonModule,
    RippleModule,
    ProgressSpinnerModule
  ],
  exports:[
    EliminarItemTablaComponent,
    SpinerComponent
  ]
})
export class ComponentesModule { }
