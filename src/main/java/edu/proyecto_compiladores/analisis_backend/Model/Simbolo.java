package edu.proyecto_compiladores.analisis_backend.Model;

import lombok.Data;

@Data
public class Simbolo {
    private String indice;
    private String nombre;
    private String tipo;
    private int linea;
    private int columna;

    public Simbolo(String indice, String nombre, String tipo, int linea, int columna) {
        this.indice = indice;
        this.nombre = nombre;
        this.tipo = tipo;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public String toString() {
        return String.format(
            "%s - %s - Identificador - %s - Linea: %d - Columna: %d",
            indice, nombre, tipo, linea, columna
        );
    }
}