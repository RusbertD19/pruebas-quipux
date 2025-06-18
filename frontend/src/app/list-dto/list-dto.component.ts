import { CancionDTO } from "./cancionDTO";

export interface ListDTO {
  nombre: string;
  descripcion: string;
  canciones: CancionDTO[];
}
