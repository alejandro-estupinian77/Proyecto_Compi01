//Código para cargar archivo TXT al textarea

const boton = document.getElementsByClassName('btn-txt')[0];
const textarea = document.getElementById('editor-texto');
const fileInput = document.getElementById('file-input');

boton.addEventListener('click', function() {
    fileInput.click(); // Simula un clic en el input de archivo
});

fileInput.addEventListener('change', function(event){
    const archivo = event.target.files[0];

    if(archivo && archivo.type === "text/plain"){
        const lector = new FileReader();

        lector.onload = function(e){
            textarea.value = e.target.result;
        }

        lector.readAsText(archivo);
    }else{
        alert("El formato elegido no es el indicado");
    }
});