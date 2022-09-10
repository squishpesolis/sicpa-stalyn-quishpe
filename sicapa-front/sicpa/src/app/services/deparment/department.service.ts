import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IrisService } from '../iris/iris.service';

import { Enterprise } from 'src/app/models/enterprise.model';


@Injectable({
  providedIn: 'root'
})
export class DepartmentService extends IrisService<Enterprise> {

  constructor(http: HttpClient) {
    super('/deparment', http);
  }



}
