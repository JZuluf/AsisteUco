// Call the dataTables jQuery plugin
$(document).ready(function() {
	
	cargarUsuarios();
  $('#tipo-identificacion').DataTable();
});


async function cargarTiposIdentificacion() {
	
	const Request = await fetch('/usuarios', {
		method: 'GET',
		headers: {
			'acept': 'application/json',
			'Content-Type': 'application/json'
		},
	});
	const usuarios = await Request.json();
	
	constole.log(usuarios);
	
}
