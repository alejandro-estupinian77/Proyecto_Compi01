package edu.proyecto_compiladores.analisis_backend.Controller;


import edu.proyecto_compiladores.analisis_backend.Model.*;
import edu.proyecto_compiladores.analisis_backend.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AnalizadorController {

    @Autowired
    private AnalizadorService analizadorService;

    @PostMapping("/analizar")
    public ResultadoAnalisis analizarCodigo(@RequestBody String codigo){
        return analizadorService.getAnalisis(codigo);
    }
}
