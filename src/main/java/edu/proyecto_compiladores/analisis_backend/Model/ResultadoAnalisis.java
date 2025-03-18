package edu.proyecto_compiladores.analisis_backend.Model;

import java.util.List;

public class ResultadoAnalisis {
    private List<Token> tokens;
    private List<Error> errores;
    private List<Simbolo> simbolos;

    public ResultadoAnalisis(List<Token> tokens, List<Error> errores, List<Simbolo> simbolos) {
        this.tokens = tokens;
        this.errores = errores;
        this.simbolos = simbolos;
    }

    // Getters
    public List<Token> getTokens() {
        return tokens;
    }

    public List<Error> getErrores() {
        return errores;
    }

    public List<Simbolo> getSimbolos() {
        return simbolos;
    }

    // Setters (corregidos)
    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void setErrores(List<Error> errores) {
        this.errores = errores; // ¡Corregido! Antes estaba asignando a tokens
    }

    public void setSimbolos(List<Simbolo> simbolos) { // Nombre más claro
        this.simbolos = simbolos;
    }
}