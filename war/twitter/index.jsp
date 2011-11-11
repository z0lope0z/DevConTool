<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>twitter Index</title>
<link rel="stylesheet" type="text/css" href="/css/global.css" />
</head>
<body>
<p>What are you doing?</p>
<form method="post" action="tweet">
<textarea name="content"></textarea><br />
<input type="text" name="version"><br />
<input type="submit" value="tweet"/>
</form>

<form method="post" action="searchTweet">
	Content:
	<input type="text" name="content">
	Version:
	<input type="text" name="version">
	<input type="submit" value="search"/>
</form>

<table border="0" cellspacing="2" cellpadding="2" id="teams">
	<tr>
	<th><font face="Arial, Helvetica, sans-serif">Edit</font></th>
	<th><font face="Arial, Helvetica, sans-serif">Delete</font></th>
	<th><font face="Arial, Helvetica, sans-serif">Key</font></th>
	<th><font face="Arial, Helvetica, sans-serif">Content</font></th>
	<th><font face="Arial, Helvetica, sans-serif">Version</font></th>
	</tr>
	<c:forEach var="e" items="${tweetList}">
		<tr>
		<td><input type="radio" value="${f:h(e.key)}" name="edit[]"/></td>
		<td><input type="checkbox" value="${f:h(e.key)}" name="delete[]"/></td>
		<td>${f:h(e.key)}</td>
		<td>${f:h(e.content)}</td>
		<td>${f:h(e.version)}</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>