<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>member UploadSpreadsheet</title>
</head>
<body>
<form action="uploadSpreadsheet" method="post" enctype="multipart/form-data">
Upload your spreadsheet:
<input type="file" name="formFile" />
	<select name="event">
		<c:forEach var="e" items="${eventList}">
			<option value="${f:h(e.key)}">${f:h(e.name)}</option>
		</c:forEach>
	</select>
<input type="submit" value="Upload"/>
</form>
</body>
</html>
