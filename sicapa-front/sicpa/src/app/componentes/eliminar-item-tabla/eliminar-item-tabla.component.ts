import { Component, OnInit, Input } from '@angular/core';
import { Output, EventEmitter } from '@angular/core';

import { ConfirmationService, MessageService } from 'primeng/api';
import { DELETE_ERROR_MESSAGE } from '../../config/mensajes';
import { SweetAlertGenericoService } from '../../services/alertas/sweet-alert-generico.service';

@Component({
  selector: 'app-eliminar-item-tabla',
  templateUrl: './eliminar-item-tabla.component.html',
  styleUrls: ['./eliminar-item-tabla.component.css']
})
export class EliminarItemTablaComponent implements OnInit {

  @Input() modelInstance: any = {};
  @Input() serviceInstance:any = null;
  @Input() title = "";
  @Input() disabled : boolean = true;
  @Output() sendResponseEvent = new EventEmitter<boolean>();

  constructor(
    public confirmationService: ConfirmationService,
    public messageService: MessageService,
    public sweetAlertGenericoService: SweetAlertGenericoService
  ) { }

  ngOnInit(): void {
  }
  deleteItem(): void {
    const alert = this.sweetAlertGenericoService.sweeAletEliminarGenerico(''.concat(this.title));
    alert.then((result) => {
      if (result.value) {
        this.modelInstance.eliminate = true;
        this.serviceInstance!.guardar(this.modelInstance)
          .subscribe(
            {
              next: () => {
        
                this.sweetAlertGenericoService.sweetAlertConfirmacionEliminado()
                this.sendResponseEvent.emit();
              },
              error: (error: any) => {
                this.sweetAlertGenericoService.sweetAletGenericoHttp(error, DELETE_ERROR_MESSAGE)
              }
            }
          );
      }
    });
  }
}
