//EN ESTE FICHERO, TENEMOS INSTRUCCIONESJAVASCRTIPT, QUE HACEN PETICIONES
//HTTP AL SERVIDOR - lo mismo que con postman, de manera programática
//AJAX
//FETCH
//ANGULAR - OBSERVABLES
//AXIOS
console.log("pagina cargada ...");
fetch("http://localhost:33333/alumno")
.then(respuesta => respuesta.json())//DESERIALIZAR - JSON TEXTO A VARIABLE
.then (lista_alumnos => {
	console.log ('Aquí la vuelta con JS Normal');
	lista_alumnos.forEach (alumno => {
		console.log (alumno.id + " " +alumno.nombre);
	});
	//MOSTRAR LOS DATOS EN LA ESTRUCTURA HTML
})
.catch(function(error) {
  console.log ('Aquí la vuelta con JS Normal');
  console.log('Hubo un problema con la petición Fetch:' + error.message);
});