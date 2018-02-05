<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>AboutUs Page</title>
</head>
<body>
	<h1>AboutUs Page</h1>
	<table>
		<tr>
			<td><a href="<c:url value='/' />">Home</a></td>
			<td><a href="<c:url value='/aboutus' />">AboutUs</a></td>
			<td><a href="<c:url value='/activities' />">Activities</a></td>
			<td><a href="<c:url value='/events' />">Events</a></td>
			<td><a href="<c:url value='/galleries' />">Galleries</a></td>
			<td><a href="<c:url value='/contactus' />">ContactUs</a></td>
			<td><a href="<c:url value='/login' />">Login</a></td>
			<td><a href="<c:url value='/registration' />">Registration</a></td>
		</tr>
	</table>
</body>
</html>
