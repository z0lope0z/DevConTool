<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="/css/theme.css" />
<!--<link rel="stylesheet" type="text/css" href="/css/global.css" />-->
<title>Search an Event</title>
</head>
<body>
<form method="post" action="searchEvent">Name: <input type="text"
	name="name"> <input type="submit" value="search" /></form>
<form method="post" action="searchEvent">
<display:table name="eventList" pagesize="5" cellspacing="0"
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
	<display:setProperty name="export.csv.filename" value="Event List.csv" />
</display:table>

<input type="submit" name="modify" value="add" />
<input type="submit" name="modify" value="delete" />
</form>
</body>
</html>
