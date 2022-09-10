import { Base } from "./base.model";
import { EmployeeDeparment } from "./employee-department.model";

export class Employee extends Base {
  constructor(
    public status: boolean,
    public age: number,
    public email: string,
    public name: string,
    public position: string,
    public surname: string,
    public id?: string,
    public idEmployee?: string,
    public eliminate?: boolean,
    public departments?:EmployeeDeparment[]
    
  ) {
    super();
  }



}
