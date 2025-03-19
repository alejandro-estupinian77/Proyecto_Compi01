document.addEventListener('DOMContentLoaded', ()=>{

    const btnCompilar = document.querySelector(".btn-compilar");
    const btnMostrarTokens = document.querySelector(".btn-tokens");
    const btnMostrarTabla = document.querySelector(".btn-tabla");
    const btnMostrarErrores = document.querySelector(".btn-errores");
    const output = document.getElementById("output");

    let resultadoAnalisis = null;

    btnCompilar.addEventListener('click', async()=>{
        const codigo = document.getElementById("editor-texto").value;

        try {
            const respuesta = await fetch("http://localhost:8080/api/analizar", {
                method: "POST",
                headers: {
                    "Content-Type": "text/plain",
                },
                body: codigo,
            });

            if (!respuesta.ok) {
                throw new Error(`Error ${respuesta.status}: ${respuesta.statusText}`);
            }

            resultadoAnalisis = await respuesta.json();
            
            if(resultadoAnalisis){
                alert("Se ha compilado el código exitosamente");
            }else{
                alert("No se ha logrado compilar el código");
            }
        } catch (error) {
            console.error("Error al analizar el código:", error);
            alert("Error al analizar el código. Revisa la consola para más detalles.");
        }
        
    });

    btnMostrarTokens.addEventListener('click', ()=>{
        if(resultadoAnalisis){
            mostrarTokens(resultadoAnalisis.tokens);
        }
    });

    function mostrarTokens(tokens){
        const tokensTexto = tokens.map(token => `<${token.tipo}> ${token.valor}`).join("\n");
        output.value = `==Tokens==\n${tokensTexto}`;
    }

    btnMostrarTabla.addEventListener('click', ()=>{
        if(resultadoAnalisis){
            mostrarSimbolos(resultadoAnalisis.simbolos);
        }
    })

    function mostrarSimbolos(simbolos){
        const simbolosTexto = simbolos.map(simbolo => `${simbolo.nombre}-${simbolo.tipo}-${simbolo.linea}-${simbolo.columna}`).join("\n");
        output.value = `==Tabla de Simbolos==\n${simbolosTexto}`;
    }

    btnMostrarErrores.addEventListener('click', () =>{
        console.log(resultadoAnalisis);
        if(resultadoAnalisis){
            mostrarErrores(resultadoAnalisis.errores);
        }
    })

    function mostrarErrores(errores){
        const erroresTexto = errores.map(error=>`${error.mensaje}`).join("\n");
        output.value = `==Errores==\n${erroresTexto}`;

    }
});
