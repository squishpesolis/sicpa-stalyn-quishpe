import { BuscarPorCriterio } from './buscar-por-criterio.interface';
export interface LazyParametros {
  listaBuscarCriterio: BuscarPorCriterio[];
  pagina: number;
  size: number;
  order: number;
  campoOrdenar: string;
}
