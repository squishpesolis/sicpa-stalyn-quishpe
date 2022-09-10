
import { Component} from '@angular/core';
import { FormGroup,FormControl } from '@angular/forms';
@Component({
  selector: 'app-iris',
  template: '',
  styles: [
  ]
})
export class IrisComponent<T>  {

  public readonly rowsPerPageOptions = [10, 15, 20, 50];
  public readonly rows = 10;
  public readonly paginator = true;
  public readonly lazy = true;
  public imagenDataExport: string = '';


  constructor() {
  }



  formarErroresForm(form: FormGroup, controlName: string): string {
    const formAxu = form;
    let mensajeError = '';
    const control = formAxu.get(controlName);
    if (control!.touched && control!.errors !== null) {
      const controlError = control!.errors;
      Object.keys(controlError).map((key) => {
        const valorTipoError: ValorTipoError = {
          tipoError: key,
          valor: controlError[key]
        };

        switch (valorTipoError.tipoError) {
          case 'required':
            mensajeError = 'Campo Obligatorio';
            break;
          case 'email':
              mensajeError = 'Campo debe ser un email';
              break;
          case 'minlength':
            mensajeError = 'Número de caracteris minino '.concat(valorTipoError.valor.requiredLength);
            break;
          case 'maxlength':
            mensajeError = 'Número de caracteris máximo '.concat(valorTipoError.valor.requiredLength);
            break;
          default:
            console.log("ERROR NO CONTROLADO")
            console.log(valorTipoError.tipoError)
            break;
        }
      });
    }
    return mensajeError;
  }


  controlDate(control: FormControl): { [key: string]: any } {
    try {
      //let ptDatePattern = /^[0-9]{4}\/(0[1-9]|1[012])\/(0[1-9]|[12][0-9]|3[01])/;
      let ptDatePattern = /^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\d\d$/;
      //^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\d\d$
    if (!control.value.match(ptDatePattern))
        return { "controlDate": true };
    } catch (error) {

    }

    return { "controlDate": false };
  }

  public findInvalidControls(form: FormGroup) {
    const invalid = [];
    const controls = form.controls;
    for (const name in controls) {
        if ((controls[name].hasError)) {
            controls[name].errors
            invalid.push(name);
        }
    }
    return invalid;
}

}

interface ValorTipoError {
  tipoError: string;
  valor: any;
}



