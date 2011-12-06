/**
 *  DEMO LEGISLATOR PEOPLE GENDER
 */
function getBills(ip) {
	var bills = [];
	$.ajax({url : "http://"+ip+":8080/legislativo/api/bill/all.json",
			type : "GET",
			async : false,
			dataType : "JSON"})
			.success(function(resp) {
				bills = resp;
			})
			.error(
					function() {
						alert("Error: Error en la conexion (LegislatorPeople)");
					}).complete(function() {
			});
	return bills;
}
function countByYear(bills,from,to) {
	var count = new Array();
	for ( var i = 0; i < to-from; i++){
		count[i] = new Array();
		count[i][0] = from+i;
		count[i][1] = 0;
	}
	for ( var i = 0; i < bills.length; i++){
		var fullYear = (new Date(bills[i].entryDate)).getFullYear();
		if(fullYear >= from && fullYear <= to)
			count[fullYear-from][1]++;
	}
	return count;
}
