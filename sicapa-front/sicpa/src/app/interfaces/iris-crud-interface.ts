import { LazyLoadEvent } from 'primeng/api';

export interface IrisCrudInterface<T> {

  buildForm(disabled: boolean): void;   
  setFormData(entidad: T): void;  
  getFormData(): void; 


  hideDialog(): void;   
  create(): void;  
  edit(entidad: T): void; 
  show(entidad: T): void; 
  buscarPorId(entidad: T, viewMode: boolean) : void;
  save(): void; 

  obtenerListaPorPaginacion(): void;
  loadLazyRecords(event: LazyLoadEvent): any; 


  sendService(): any;


  resetSelectedItems(): void

  buildColumnsTable(): any[];

  getErrorForm(controlName: string): string;

}
