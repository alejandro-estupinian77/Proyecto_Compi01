//Mostrar pantallas
const pantallaEditor = document.getElementsByClassName('editor-texto')[0];
const pantallaOutput = document.getElementsByClassName('output')[0];
const pantallaSintactico = document.getElementsByClassName('analisis-sintactico')[0];

//Variables del analizador sintáctico
const botonArbol = document.getElementsByClassName('btn-arbol')[0];
const botonTabla = document.getElementsByClassName('btn-textarea')[0];
const botonErrores = document.getElementsByClassName('btn-erroresS')[0];
const canvasArbol = document.getElementsByClassName('resultado-arbol')[0];
const outputSintactico = document.getElementsByClassName('resultado-sintactico')[0];

const botonEditor = document.getElementById('btn-editor');
const botonOutput = document.getElementById('btn-token');
const botonSintactico = document.getElementById('btn-sintactico');

botonEditor.addEventListener('click', function(){
    pantallaEditor.style.display = "grid";
    pantallaOutput.style.display = "none";
    pantallaSintactico.style.display = "none";
});

botonOutput.addEventListener('click', function(){
    pantallaEditor.style.display = "none";
    pantallaSintactico.style.display = "none";
    pantallaOutput.style.display = "grid";
});

botonSintactico.addEventListener('click', function(){
    pantallaEditor.style.display = "none";
    pantallaOutput.style.display = "none";
    pantallaSintactico.style.display = "grid";
});

//Cambio de pantalla para resultados del analizador sintactico
botonArbol.addEventListener('click', function(){
    canvasArbol.style.display = "block";
    outputSintactico.style.display = "none";
});

botonTabla.addEventListener('click', function(){
    canvasArbol.style.display = "none";
    outputSintactico.style.display = "block";
});

botonErrores.addEventListener('click', function(){
    canvasArbol.style.display = "none";
    outputSintactico.style.display = "block";
});
