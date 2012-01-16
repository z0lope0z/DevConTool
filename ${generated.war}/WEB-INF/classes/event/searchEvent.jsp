<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>event SearchEvent</title>
</head>
<body>
<form method="post" action="searchEvent">
	Name:
	<input type="text" name="name">
	<input type="submit" value="search"/>
</form>
<form method="post" action="searchEvent">
	<table border="0" cellspacing="2" cellpadding="2" id="teams">
		<tr>
		<th><font face="Arial, Helvetica, sans-serif">Edit</font></th>
		<th><font face="Arial, Helvetica, sans-serif">Delete</font></th>
<!--		<th><font face="Arial, Helvetica, sans-serif">Key</font></th>-->
		<th><font face="Arial, Helvetica, sans-serif">Name</font></th>
		</tr>
		<c:forEach var="e" items="${eventList}">
			<tr>
			<td><input type="radio" value="${f:h(e.key)}" name="edit[]"/></td>
			<td><input type="checkbox" value="${f:h(e.key)}" name="delete[]"/></td>
<!--			<td>${f:h(e.key)}</td>-->
			<td>${f:h(e.name)}</td>
			</tr>
		</c:forEach>
	</table>
	<input type="submit" name="modify" value="edit"/>
	<input type="submit" name="modify" value="delete"/>
</form>
</body>
</html>
