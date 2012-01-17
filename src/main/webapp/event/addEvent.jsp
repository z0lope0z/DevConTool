<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Add Event</title>
</head>
<body>
<form method="post" action="addEvent">
	<input type="hidden" value="${f:h(event.key)}" name="key"/>
	Event Name:
	<input type="text" name="name" value="${f:h(event.name)}"/><br />
	<input type="submit" name="saveOrUpdate" value="Save"/>	
</form>
</body>
</html>
