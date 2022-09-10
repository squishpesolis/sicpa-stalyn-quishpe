export interface IHttpErrorResponse  {
  error: Error;
}

interface Error {
  httpStatus: number;
  mensajes: string;
  urlRequest: string;
}

