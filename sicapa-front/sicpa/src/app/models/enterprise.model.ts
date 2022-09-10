import { Base } from "./base.model";

export class Enterprise extends Base {
  constructor(
    public phone: string,
    public name: string,
    public adress: string,
    public status: boolean,
    public id?: string,
    public idEnterprise?: string,
    public eliminate?: boolean,
  ) {
    super();
  }



}
