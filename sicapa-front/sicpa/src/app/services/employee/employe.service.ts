import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IrisService } from '../iris/iris.service';

import { Enterprise } from 'src/app/models/enterprise.model';
import { Employee } from 'src/app/models/employee.model';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService extends IrisService<Employee> {

  constructor(http: HttpClient) {
    super('/employee', http);
  }



}
