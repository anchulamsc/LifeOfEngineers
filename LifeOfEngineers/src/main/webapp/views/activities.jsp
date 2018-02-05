<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
</head>
<body>
<table>	
		<tr>
			<td>
			</td>
			<td>
				<h1>Activities Page</h1> 
			</td> 
		</tr>
		<tr>
			<td>
				Email
			</td>
			<td>
				<c:out value="${users.user_name}"/>
			</td> 			
		</tr>
		<tr>
			<td>
				First Name
			</td>
			<td>
				<c:out value="${users.registrationDetails.first_name}"/>
			</td> 			
		</tr>	
		<tr>
			<td>
				Last Name
			</td>
			<td>
				<c:out value="${users.registrationDetails.last_name}"/>
			</td> 			
		</tr>	
		<tr>
			<td>
				Middle Name
			</td>
			<td>
				<c:out value="${users.registrationDetails.middle_name}"/>
			</td> 			
		</tr>	
		<tr>
			<td>
				Date Of Birth
			</td>
			<td>
				<c:out value="${users.registrationDetails.birth_date}"/>
			</td> 			
		</tr>
		<tr>
			<td>
				Gender
			</td>
			<td>
				<c:out value="${users.registrationDetails.gender}"/>
			</td> 			
		</tr>
		<tr>
			<td>
				Marital Status
			</td>
			<td>
				<c:out value="${users.registrationDetails.marital_status}"/>
			</td> 			
		</tr>
		<tr>
			<td>
				Profile Photo
			</td>
			<td>
				<a href="<c:url value='/galleries/gallery'/> "><img src="${pageContext.request.contextPath}/galleries/imageDisplay/${users.user_id}" width="200px" height="200px" /></a>
			</td> 			
		</tr>				
	</table>	
</body>
</html>
