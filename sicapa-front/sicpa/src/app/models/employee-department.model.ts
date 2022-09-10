import { Base } from "./base.model";
import { Department } from "./department.model";
import { Employee } from "./employee.model";

export class EmployeeDeparment extends Base {
  constructor(
    public employee?: Employee,
    public department?: Department,
    public id?: string
  ) {
    super();
  }



}
