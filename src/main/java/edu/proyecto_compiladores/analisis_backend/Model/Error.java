package edu.proyecto_compiladores.analisis_backend.Model;

import lombok.Data;

@Data
public class Error {
        int linea;
        int columna;
        String mensaje;

        public Error(int linea, int columna, String mensaje) {
            this.linea = linea;
            this.columna = columna;
            this.mensaje = mensaje;
        }

        @Override
        public String toString() {
            return String.format("Error en L%d:%d - %s", linea, columna, mensaje);
        }
}
