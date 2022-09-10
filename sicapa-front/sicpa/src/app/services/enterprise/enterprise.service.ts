import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IrisService } from '../iris/iris.service';
import { Observable} from 'rxjs';

import { Enterprise } from 'src/app/models/enterprise.model';


@Injectable({
  providedIn: 'root'
})
export class EnterpriseService extends IrisService<Enterprise> {

  constructor(http: HttpClient) {
    super('/enterprise', http);
  }

  getActiveCompanies() : Observable<any>{
    let urlCompleta = this.url.concat('/get-active-companies')
    return this.http.get(urlCompleta) as Observable<any>;
  }

}
