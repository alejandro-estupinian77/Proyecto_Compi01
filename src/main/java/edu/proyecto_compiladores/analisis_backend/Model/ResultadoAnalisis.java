package edu.proyecto_compiladores.analisis_backend.Model;

import java.util.List;
import java.util.ArrayList;

public class ResultadoAnalisis {

    private List<Token> tokens;
    private List<Error> errores;
    private List<Simbolo> simbolos;

    public ResultadoAnalisis(List<Token> tokens, List<Error> errores, List<Simbolo> simbolos){
        this.tokens = tokens;
        this.errores = errores;
        this.simbolos = simbolos;
    }

    //Gets y Sets
    public List<Token> getTokens(){
        return this.tokens;
    }

    public List<Error> getErrores(){
        return this.errores;
    }

    public List<Simbolo> getSimbolos(){
        return this.simbolos;
    }

    public void setTokens(List<Token> tokens){
        this.tokens = tokens;
    }

    public void setErrores(List<Error> errores){
        this.tokens = tokens;
    }

    public void setTabla(List<Simbolo> simbolos){
        this.simbolos = simbolos;
    }
    
}
