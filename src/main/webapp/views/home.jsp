<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>API Legislativo</title>
</head>
<body>
	<h1>Objects</h1>
	<div id="navcontainer">
		<ul>
			<li><a>Bills</a>
				<ul>
					<li><a href="bill/all.json">List Bills</a> Despliega todos los
						proyectos</li>
					<li><a href="bill/any.json?id=1">Find by Id</a> Busca un
						proyecto por Id. Requerido: id</li>
				</ul></li>
			<li><a>Circunscription</a>
				<ul>
					<li><a href="geo/circunscription/all.json">List
							Circunscription</a> Despliega todas las circunscripciones</li>
					<li><a href="geo/circunscription/any.json?region_id=">List
							by Region</a>Busca circunscripciones por Región. Requerido: region_id</li>
					<li><a href="geo/circunscription/any.json?id=1">Find by Id</a>
						Busca una circunscripción por Id. Requerido: id</li>
					<li><a href="geo/circunscription/any.json?name=">Find by
							Name</a>Busca una circunscripción por Nombre. Requerido: name</li>
				</ul>
			</li>
			<li><a>Commune</a>
				<ul>
					<li><a href="geo/commune/all.json">List Communes</a>Despliega
						todas las comunas</li>
					<li><a href="geo/commune/any.json?id=1">Find by Id</a>Busca
						una comuna por Id. Requerido: id</li>
					<li><a href="geo/commune/any.json?name=">Find by Name</a>
						Busca una comuna por Nombre. Requerido: name</li>
				</ul>
			</li>
			<li><a>District</a>
				<ul>
					<li><a href="geo/district/all.json">List District</a>
						Despliega todos los distritos</li>
					<li><a href="geo/district/any.json?id=1">Find by Id</a>Busca
						un distrito por Id. Requerido: id</li>
					<li><a href="geo/district/any.json?name=">Find by Name</a>
						Busca un distrito por Nombre. Requerido: name</li>
				</ul>
			</li>
			<li><a>Legislator</a>
				<ul>
					<li><a href="legislator/people.json?id=1">List Legislators</a>
						Despliega todas las legislaturas históricas.</li>
					<li><a href="legislator/period.json?id=1">Find by Id</a>Busca
						un legislador del periodo actual por Id. Requerido: id</li>
					<li><a href="legislator/person.json?id=1">Find by Person</a>
						Busca las legislaturas históricas de una persona por Id.
						Requerido: id</li>
				</ul>
			</li>
			<li><a>Person</a>
				<ul>
					<li><a href="person/all.json">List People</a>Despliega todas
						las personas</li>
					<li><a href="person/any.json?id=1">Find by Id</a> Busca una
						persona por Id. Requerido: id</li>
					<li><a href="person/any.json?firstName=">Find by First
							Name</a> Busca una persona por Nombre. Requerido: firstName</li>
					<li><a href="person/any.json?lastName=">Find by Last Name</a>
						Busca una persona por Apellido. Requerido: lastName</li>
				</ul>
			</li>
			<li><a>Region</a>
				<ul>
					<li><a href="geo/region/all.json">List Regions</a>Despliega
						todas las regiones</li>
					<li><a href="geo/region/any.json?id=1">Find by Id</a>Busca una
						región por Id. Requerido: id</li>
					<li><a href="geo/region/any.json?name=">Find by Name</a>Busca
						una región por Nombre. Requerido: name</li>
				</ul>
			</li>
		</ul>
	</div>
</body>
</html>
