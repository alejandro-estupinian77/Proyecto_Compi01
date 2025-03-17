package edu.proyecto_compiladores.analisis_backend.Model;

import lombok.Data;

@Data
public class Token {
    String tipo;
    String valor;
    int linea;
    int columna;

    public Token(String tipo, String valor, int linea, int columna) {
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
