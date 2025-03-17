package edu.proyecto_compiladores.analisis_backend.Model;

import lombok.Data;

@Data
public class Simbolo {
        String nombre;
        String tipo;
        int linea;
        int columna;

        public Simbolo(String nombre, String tipo, int linea, int columna) {
            this.nombre = nombre;
            this.tipo = tipo;
            this.linea = linea;
            this.columna = columna;
        }

        @Override
        public String toString() {
            return String.format("%s - %s (Línea %d)", nombre, tipo, linea);
        }
}
