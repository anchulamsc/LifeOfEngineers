<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
</head>
<body>
	<c:url var="saveuploadedfile" value="/galleries/saveuploadedfile" ></c:url>
	<form:form method="POST" action="${saveuploadedfile}" commandName="galleriesInfo" enctype="multipart/form-data">
		<table>
			<tr>
				<td>
				</td>
				<td>
					<h1> Galleries </h1> 
				</td> 
			</tr>
		</table>
		<form:errors path="*" cssClass="errorblock" element="div" />
		Please select your profile pic to upload : <form:input type="file" name="file" path="file" />
		<input type="submit" value="<spring:message text="Upload"/>" onclick="return validateUpload();" />
		<span><form:errors path="file" cssClass="error" /></span><br> <br>
	</form:form>
</body>
</html>
