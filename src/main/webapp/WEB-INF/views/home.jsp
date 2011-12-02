<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
</head>
<body>
	<!-- <h1>${mensaje}</h1> -->
	<h1>Objects</h1>
	<ul class="listObjects">
		<li>Bills:
			<ul>
				<li><a href="bill/all.json">List Bills</a>: Despliega todos los
					proyectos</li>
				<li><a href="bill/any.json?id=1">Find by Id</a>: Busca un
					proyecto por Id y despliega los detalles. Requerido: id</li>
			</ul></li>
		<li>Circunscription:
			<ul>
				<li><a href="geo/circunscription/all.json">List
						Circunscription</a>: Despliega todas las circunscripciones</li>
				<li><a href="geo/circunscription/any.json?region_id=">List
						by Region</a>: Busca circunscripciones por Región. Requerido:
					region_id</li>
				<li><a href="geo/circunscription/any.json?id=1">Find by Id</a>:
					Busca una circunscripción por Id y despliega los detalles.
					Requerido: id</li>
				<li><a href="geo/circunscription/any.json?name=">Find by
						Name</a>: Busca una circunscripción por Nombre y despliega los
					detalles. Requerido: name</li>
			</ul>
		</li>
		<li>Commune:
			<ul>
				<li><a href="geo/commune/all.json">List Communes</a>: Despliega
					todas las comunas</li>
				<li><a href="geo/commune/any.json?id=1">Find by Id</a>: Busca
					una comuna por Id y despliega los detalles. Requerido: id</li>
				<li><a href="geo/commune/any.json?name=">Find by Name</a>:
					Busca una comuna por Nombre y despliega los detalles. Requerido:
					name</li>
			</ul>
		</li>
		<li>District:
			<ul>
				<li><a href="geo/district/all.json">List District</a>:
					Despliega todos los distritos</li>
				<li><a href="geo/district/any.json?id=1">Find by Id</a>: Busca
					un distrito por Id y despliega los detalles. Requerido: id</li>
				<li><a href="geo/district/any.json?name=">Find by Name</a>:
					Busca un distrito por Nombre y despliega los detalles. Requerido:
					name</li>
			</ul>
		</li>
		<li>Legislator:
			<ul>
				<li><a href="legislator/period.json?id=1">Find by Id</a>: Busca
					un legislador por Id y despliega los detalles del rol. Requerido:
					id</li>
			</ul></li>
		<li>Region:
			<ul>
				<li><a href="geo/region/all.json">List Regions</a>: Despliega
					todos las regiones</li>
				<li><a href="geo/region/any.json?id=1">Find by Id</a>: Busca
					una región por Id y despliega los detalles. Requerido: id</li>
				<li><a href="geo/region/any.json?name=">Find by Name</a>: Busca
					una región por Nombre y despliega los detalles. Requerido: name</li>
			</ul>
		</li>

	</ul>
</body>
</html>
