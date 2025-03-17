//Mostrar pantallas
const pantallaEditor = document.getElementsByClassName('editor-texto')[0];
const pantallaOutput = document.getElementsByClassName('output')[0];

const botonEditor = document.getElementById('btn-editor');
const botonOutput = document.getElementById('btn-token');

botonEditor.addEventListener('click', function(){
    pantallaEditor.style.display = "grid";
    pantallaOutput.style.display = "none";
});

botonOutput.addEventListener('click', function(){
    pantallaEditor.style.display = "none";
    pantallaOutput.style.display = "grid";
})