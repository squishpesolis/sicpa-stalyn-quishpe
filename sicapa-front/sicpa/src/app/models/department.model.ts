import { Base } from "./base.model";
import { Enterprise } from "./enterprise.model";

export class Department extends Base {
  constructor(
    public phone: string,
    public name: string,
    public adress: string,
    public status: boolean,
    public description: string,
    public id?: string,
    public idDepartment?: string,
    public eliminate?: boolean,
    public enterprise?: Enterprise
  ) {
    super();
  }



}
