import { CancionDTO } from "./cancionDTO";

export interface ListDTO {
   id?: number;
  nombre: string;
  descripcion: string;
  canciones: CancionDTO[];
}
