package edu.proyecto_compiladores.analisis_backend.Model;

import lombok.Data;

@Data
public class Token {
    private int indice; // Nuevo campo para el índice
    private String tipo;
    private String valor;
    private int linea;
    private int columna;

    // Constructor modificado para incluir el índice
    public Token(int indice, String tipo, String valor, int linea, int columna) {
        this.indice = indice;
        this.tipo = tipo;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (Línea %d, Col %d)", tipo, valor, linea, columna);
    }
}