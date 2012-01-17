<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="/css/theme.css" />
<title>Member Search</title>
</head>
<body>
<form method="post" action="searchMember">Name: <input
	type="text" name="name"> Email: <input type="text" name="email">
<select name="event">
	<c:forEach var="e" items="${eventList}">
		<option value="${f:h(e.key)}">${f:h(e.name)}</option>
	</c:forEach>
</select> <input type="submit" value="search" /></form>

<display:table name="memberList" pagesize="5" cellspacing="0"
	cellpadding="0" requestURI="" defaultsort="1" id="members"
	class="table" export="true">
	<display:column title="ID" sortable="true" titleKey="gCashUser.id"
		style="width: 25%">
		<c:url var="addMemberUrl" value="addMember">
			<c:param name="key" value="${f:h(members.key)}" />
		</c:url>
		<a href="${addMemberUrl}">${f:h(members.key)}</a>
	</display:column>
	<display:column property="name" value="name" sortable="true" />
	<display:column property="email" value="email" sortable="true" />
	<display:column title="Events" titleKey="gCashUser.id"
		style="width: 25%" sortable="true">
		<c:forEach var="e" items="${members.events}">
				${f:h(e)}<br />
		</c:forEach>
	</display:column>
	<display:setProperty name="export.excel.filename"
		value="Event List.xls" />
	<display:setProperty name="export.csv.filename" value="Event List.csv" />
	<display:setProperty name="export.pdf.filename" value="Event List.pdf" />
</display:table>

<form method="post" action="searchMember">
    <input type="submit" name="modify" value="download" /> 
	<input type="submit" name="modify"	value="add" /> 
	<input type="submit" name="modify" value="delete" />
</form>
</body>
</html>
