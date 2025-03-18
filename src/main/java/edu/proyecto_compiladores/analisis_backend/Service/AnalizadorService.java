package edu.proyecto_compiladores.analisis_backend.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.proyecto_compiladores.analisis_backend.Model.*;
import edu.proyecto_compiladores.analisis_backend.Model.Error;

import org.springframework.stereotype.Service;

@Service
public class AnalizadorService {

    private List<Token> tokens = new ArrayList<>();
    private List<Simbolo> tablaSimbolos = new ArrayList<>();
    private List<Error> errores = new ArrayList<>();
    private String codigoFuente;
    private int lineaActual = 1;
    private int columnaActual = 1;

    // Expresiones regulares para los tokens
    private final Pattern patronComentarioLinea = Pattern.compile("//.*");
    private final Pattern patronInicioComentarioBloque = Pattern.compile("/\\*");
    private final Pattern patronFinalComentarioBloque = Pattern.compile("\\*\\/");
    private final Pattern patronCadena = Pattern.compile("\"[^\"]*\"");
    private final Pattern patronCaracter = Pattern.compile("'[^']'");
    private final Pattern patronNumeroReal = Pattern.compile("-?\\d+\\.\\d+");
    private final Pattern patronNumeroEntero = Pattern.compile("-?\\d+");
    private final Pattern patronIdentificador = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*");
    private final Pattern patronOperadores = Pattern.compile("[+\\-*/^#=<>!&|]|>=|<=|==|!=|&&|\\|\\|");
    private final Pattern patronSignos = Pattern.compile("[();{},]");

    // Variable para rastrear si estamos dentro de un comentario de bloque
    private boolean dentroDeComentarioBloque = false;

    public void analizar(String codigo) {
        String[] lineas = codigo.trim().split("\\n");
        for (String linea : lineas) {
            procesarLinea(linea);
            lineaActual++;
            columnaActual = 1; // Reiniciar la columna al inicio de cada línea
        }
    }

    private void procesarLinea(String linea) {
        String texto = linea.trim();
        while (!texto.isEmpty()) {
    
            // Si estamos dentro de un comentario de bloque, buscar el cierre */
            if (dentroDeComentarioBloque) {
                Matcher matcherComentarioFinalBloque = patronFinalComentarioBloque.matcher(texto);
                if (matcherComentarioFinalBloque.find()) {
                    // Capturar todo el contenido del comentario de bloque, incluyendo */
                    String comentario = texto.substring(0, matcherComentarioFinalBloque.end());
                    agregarToken("COMENTARIO", comentario, lineaActual, columnaActual);
                    texto = texto.substring(matcherComentarioFinalBloque.end()).trim();
                    dentroDeComentarioBloque = false;
                    columnaActual += matcherComentarioFinalBloque.end();
                    continue;
                } else {
                    // Si no encontramos el cierre, capturar toda la línea como parte del comentario
                    agregarToken("COMENTARIO", texto, lineaActual, columnaActual);
                    break;
                }
            }
    
            // Capturar comentarios de una sola línea
            Matcher matcherComentarioLinea = patronComentarioLinea.matcher(texto);
            if (matcherComentarioLinea.lookingAt()) {
                String comentario = matcherComentarioLinea.group();
                agregarToken("COMENTARIO", comentario, lineaActual, columnaActual);
                break; // Ignorar el resto de la línea
            }
    
            // Detectar inicio de comentario de bloque
            Matcher matcherComentarioBloque = patronInicioComentarioBloque.matcher(texto);
            if (matcherComentarioBloque.lookingAt()) {
                dentroDeComentarioBloque = true;
                // Capturar el inicio del comentario de bloque (/*)
                String comentario = matcherComentarioBloque.group();
                agregarToken("COMENTARIO", comentario, lineaActual, columnaActual);
                texto = texto.substring(matcherComentarioBloque.end()).trim();
                columnaActual += matcherComentarioBloque.end();
                continue;
            }
    
            // Procesar tokens (resto del código sigue igual)
            boolean encontrado = false;
    
            // Cadenas
            Matcher matcherCadena = patronCadena.matcher(texto);
            if (matcherCadena.lookingAt()) {
                agregarToken("CADENA", matcherCadena.group(), lineaActual, columnaActual);
                texto = texto.substring(matcherCadena.end()).trim();
                columnaActual += matcherCadena.end();
                encontrado = true;
                continue;
            }
    
            // Caracteres
            Matcher matcherCaracter = patronCaracter.matcher(texto);
            if (matcherCaracter.lookingAt()) {
                agregarToken("CARACTER", matcherCaracter.group(), lineaActual, columnaActual);
                texto = texto.substring(matcherCaracter.end()).trim();
                columnaActual += matcherCaracter.end();
                encontrado = true;
                continue;
            }
    
            // Números reales
            Matcher matcherReal = patronNumeroReal.matcher(texto);
            if (matcherReal.lookingAt()) {
                agregarToken("REAL", matcherReal.group(), lineaActual, columnaActual);
                texto = texto.substring(matcherReal.end()).trim();
                columnaActual += matcherReal.end();
                encontrado = true;
                continue;
            }
    
            // Números enteros
            Matcher matcherEntero = patronNumeroEntero.matcher(texto);
            if (matcherEntero.lookingAt()) {
                agregarToken("ENTERO", matcherEntero.group(), lineaActual, columnaActual);
                texto = texto.substring(matcherEntero.end()).trim();
                columnaActual += matcherEntero.end();
                encontrado = true;
                continue;
            }
    
            // Operadores y signos
            Matcher matcherOperadores = patronOperadores.matcher(texto);
            if (matcherOperadores.lookingAt()) {
                String operador = matcherOperadores.group();
                agregarToken("OPERADOR", operador, lineaActual, columnaActual);
                texto = texto.substring(operador.length()).trim();
                columnaActual += operador.length();
                encontrado = true;
                continue;
            }
    
            Matcher matcherSignos = patronSignos.matcher(texto);
            if (matcherSignos.lookingAt()) {
                String signo = matcherSignos.group();
                agregarToken("SIGNO", signo, lineaActual, columnaActual);
                texto = texto.substring(signo.length()).trim();
                columnaActual += signo.length();
                encontrado = true;
                continue;
            }
    
            // Identificadores y palabras reservadas
            Matcher matcherIdentificador = patronIdentificador.matcher(texto);
            if (matcherIdentificador.lookingAt()) {
                String identificador = matcherIdentificador.group().toLowerCase();
                if (esPalabraReservada(identificador)) {
                    agregarToken("PALABRA_RESERVADA", identificador, lineaActual, columnaActual);
                } else {
                    agregarToken("IDENTIFICADOR", identificador, lineaActual, columnaActual);
                    agregarSimbolo(identificador, "VARIABLE", lineaActual, columnaActual);
                }
                texto = texto.substring(identificador.length()).trim();
                columnaActual += identificador.length();
                encontrado = true;
                continue;
            }
    
            // Si no se encontró ningún token válido, registrar error
            if (!encontrado) {
                String caracterInvalido = texto.substring(0, 1);
                errores.add(new Error(lineaActual, columnaActual, "Carácter inválido: " + caracterInvalido));
                texto = texto.substring(1).trim();
                columnaActual++;
            }
        }
    }

    private boolean esPalabraReservada(String token) {
        String[] palabrasReservadas = {
            "entero", "real", "booleano", "caracter", "cadena",
            "si", "sino", "para", "mientras", "hacer", "escribirlinea",
            "escribir", "longitud", "acadena"
        };
        for (String palabra : palabrasReservadas) {
            if (palabra.equalsIgnoreCase(token)) {
                return true;
            }
        }
        return false;
    }

    private void agregarToken(String tipo, String valor, int linea, int columna) {
        tokens.add(new Token(tipo, valor, linea, columna));
    }

    private void agregarSimbolo(String nombre, String tipo, int linea, int columna) {
        tablaSimbolos.add(new Simbolo(nombre, tipo, linea, columna));
    }

    public ResultadoAnalisis getAnalisis(String codigo){
        analizar(codigo);
        return new ResultadoAnalisis(tokens, errores, tablaSimbolos);
    }
}