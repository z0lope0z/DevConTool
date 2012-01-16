<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>twitter AddTweet</title>
</head>
<body>
<form method="post" action="tweet">
	<input type="hidden" value="${f:h(tweet.key)}" name="key"/>
	<textarea name="content"/>${f:h(tweet.content)}</textarea><br />
	<input type="text" name="version" value="${f:h(tweet.version)}"/><br />
	<input type="submit" name="saveOrUpdate" value="Save"/>	
</form>
</body>
</html>