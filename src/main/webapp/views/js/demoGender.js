/**
 *  DEMO LEGISLATOR PEOPLE GENDER
 */
function getLegislatorPeople(ip) {
	var people = [];
	$.ajax({url : "http://"+ip+":8080/legislativo/api/legislator/people.json",
			type : "GET",
			async : false,
			dataType : "JSON"})
			.success(function(resp) {
				people = resp;
			})
			.error(
					function() {
						alert("Error: Error en la conexion (LegislatorPeople)");
					}).complete(function() {
			});
	return people;
}
function getMales(people) {
	return countGender(people, "M");
}
function getFemales(people) {
	return countGender(people, "F");
}
function countGender(people, gender) {
	var count = 0;
	for ( var i = 0; i < people.length; i++)
		if(people[i].gender == gender)
			count++;
	return count;
}
