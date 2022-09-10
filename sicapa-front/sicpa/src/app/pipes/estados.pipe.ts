import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'estados'
})
export class EstadosPipe implements PipeTransform {

  transform(estado: boolean): unknown {
    let estadoAux = '';

    switch (estado) {
      case true:
        estadoAux = 'Activo';
        break;
      case false:
        estadoAux = 'Inactivo';
        break;
      default:
        estadoAux = 'Inactivo';
        break;
    }
    return estadoAux;
  }

}
