<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home Page</title>
	
</head>
<body>
<h1>
	Home Page
</h1>
<spring:message text="validUser"/>

${validUser}

</body>
</html>
