import { CancionDTO } from "./cancion-dto";

export interface ListDTO {
  id?: number;
  nombre: string;
  descripcion: string;
  canciones: CancionDTO[];
}
