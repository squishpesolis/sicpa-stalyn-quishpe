export interface IHttpErrorResponseSC  {
  error: Error;
  message: string;
  name: string;
  ok: boolean;
  status: string;
  url:string;

}

interface Error {
  error: Error2;
}


interface Error2 {
  data: Data;
  message: string;
  statusCode: number;
}

interface Data {
  related: string;
}
