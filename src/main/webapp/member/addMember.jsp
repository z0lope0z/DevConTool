<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Add Members</title>
<link rel="stylesheet" href="../js/chosen.css">
<script src="../js/jquery.js" type="text/javascript"></script>
<script src="../js/chosen.jquery.js" type="text/javascript"></script>
<script type="text/javascript"> 
$(document).ready(function() {
	var	memberEventList = ${memberEventList};
	convertToSelected(memberEventList, convertToChosen);
});

function convertToChosen() {
	$(".chzn-select-event").chosen({allow_multiple_deselect:true});
}

function convertToSelected(memberEventList){
	$.each(memberEventList, function (index, event) {
		$("select.chzn-select-event option[value="+event.key+"]").attr("selected", "selected");
	});
	convertToChosen();
}

</script>
</head>
<body>
<form method="post" action="addMember">
	<input type="hidden" value="${f:h(member.key)}" name="key"/>
	Name:
	<input type="text" name="name" value="${f:h(member.name)}"/><br />
	Email:
	<input type="text" name="email" value="${f:h(member.email.email)}"/><br />
	<select name="event" class="chzn-select-event" style="width:350px;" multiple="multiple" tabindex="3">
		<c:forEach var="e" items="${eventList}">
			<option value="${f:h(e.key)}">${f:h(e.name)}</option>
		</c:forEach>
	</select><br />
	<input type="submit" name="saveOrUpdate" value="Save"/>	
	<br /><a href="uploadSpreadsheet">Upload a Spreadsheet</a>
</form>
</body>
</html>