import { Component, Inject, OnInit } from '@angular/core';
import {MenuItem} from 'primeng/api';




@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})

export class MenuComponent implements OnInit {
  
  items!: MenuItem[];


   ngOnInit() {
    this.items = [
        {
            label:'Companies',
            icon:'pi pi-fw pi-file',
            routerLink: ['/system/enterprise']
        },
        {
            label:'Departments',
            icon:'pi pi-fw pi-pencil',
            routerLink: ['/system/departments']
        },
        {
            label:'Employees',
            icon:'pi pi-fw pi-user',
            routerLink: ['/system/employes']
        }
    ];
}    

}
