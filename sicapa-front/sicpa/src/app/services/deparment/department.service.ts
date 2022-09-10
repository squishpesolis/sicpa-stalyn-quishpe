import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable} from 'rxjs';

import { IrisService } from '../iris/iris.service';

import { Enterprise } from 'src/app/models/enterprise.model';
import { Department } from 'src/app/models/department.model';


@Injectable({
  providedIn: 'root'
})
export class DepartmentService extends IrisService<Department> {

  constructor(http: HttpClient) {
    super('/deparment', http);
  }


  getActiveDepartments() : Observable<any>{
    let urlCompleta = this.url.concat('/get-active-departments')
    return this.http.get(urlCompleta) as Observable<any>;
  }

}
