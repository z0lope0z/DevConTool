<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Member Search</title>
</head>
<body>
<form method="post" action="searchMember">
	NameTest:
	<input type="text" name="name">
	Email:
	<input type="text" name="email">
	<select name="event">
		<c:forEach var="e" items="${eventList}">
			<option value="${f:h(e.key)}">${f:h(e.name)}</option>
		</c:forEach>
	</select> 
	<input type="submit" value="search"/>
</form>
<display:table name="memberList" pagesize="5" cellspacing="0"
	cellpadding="0" requestURI="" defaultsort="1" id="events" class="table"
	export="true">
	<display:column title="ID"
		sortable="true" titleKey="gCashUser.id" style="width: 25%">
		<c:url var="addEventUrl" value="addEvent"> <c:param name="key" value="${f:h(events.key)}" /> </c:url>
		<a href="${addEventUrl}">${f:h(events.key)}</a>
		</display:column>
	<display:column property="name" value="name" sortable="true" />
	<display:setProperty name="export.excel.filename"
		value="Event List.xls" />
</display:table>

<form method="post" action="searchMember">
	<table border="0" cellspacing="2" cellpadding="2" id="teams">
		<tr>
		<th><font face="Arial, Helvetica, sans-serif">Edit</font></th>
		<th><font face="Arial, Helvetica, sans-serif">Delete</font></th>
<!--		<th><font face="Arial, Helvetica, sans-serif">Key</font></th>-->
		<th><font face="Arial, Helvetica, sans-serif">Name</font></th>
		<th><font face="Arial, Helvetica, sans-serif">Email</font></th>
		<th><font face="Arial, Helvetica, sans-serif">Events</font></th>
		</tr>
		<c:forEach var="e" items="${memberList}">
			<tr>
			<td><input type="radio" value="${f:h(e.key)}" name="edit[]"/></td>
			<td><input type="checkbox" value="${f:h(e.key)}" name="delete[]"/></td>
<!--			<td>${f:h(e.key)}</td>-->
			<td>${f:h(e.name)}</td>
			<td>${f:h(e.email)}</td>
			<td><c:forEach var="e" items="${e.events}">
				${f:h(e)}<br />
			</c:forEach></td>
			</tr>
		</c:forEach>
	</table>
	
	<input type="submit" name="modify" value="download"/>
	<input type="submit" name="modify" value="add"/>
	<input type="submit" name="modify" value="edit"/>
	<input type="submit" name="modify" value="delete"/>
</form>
</body>
</html>
