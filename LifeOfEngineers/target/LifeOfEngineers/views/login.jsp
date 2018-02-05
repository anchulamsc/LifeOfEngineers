<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
</head>
<body>
<c:if test="${registrationInfo.loginview}">
	<c:url var="performLoginAction" value="/registration/performlogin" ></c:url>
	<form:form action="${performLoginAction}" commandName="registrationInfo">
		<table>
			<tr>
				<td>
				</td>
				<td>
					<h1> Login </h1> 
				</td> 
			</tr>
			<tr>
				<td>
					<c:out value="${registrationInfo.infoMessage}"></c:out>
				</td>
				<td>
					<form:errors path="serviceError" cssClass="error"/>
				</td>		
			</tr>
			<tr>
				<td>
					<form:label path="user_name">
						<spring:message text="Please Enter Your Email *"/>
					</form:label>
				</td>
				<td>
					<form:input path="user_name" maxlength="50"/>
				</td> 
				<td>
					<form:errors path="user_name" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td>
					<form:label path="password">
						<spring:message text="Please Enter Your Password *"/>
					</form:label>
				</td>
				<td>
					<form:password path="password" maxlength="20"/>
				</td> 
				<td>
					<form:errors path="password" cssClass="error"/>
				</td> 
			</tr>
			
			<tr>
				<td align="center">
					<a href="<c:url value='/registration/register'/>" >Not Registered Yet?</a>
				</td>
				<td align="center">
					<input type="submit" value="<spring:message text="LogIn"/>" />
				</td>
				<td align="center">
					<a href="<c:url value='/registration/loadforgot'/>" >Forgot Password?</a>
				</td>
			</tr>
		</table>
	</form:form>
</c:if>
<c:if test="${registrationInfo.forgotview}">
	<c:url var="performForgotAction" value="/registration/forgot" ></c:url>
	<form:form action="${performForgotAction}" commandName="registrationInfo">
		<table>
			<tr>
				<td>
				</td>
				<td>
					<h1> Login </h1> 
				</td> 
			</tr>
			<tr>
				<td>
				</td>
				<td>
					<form:errors path="serviceError" cssClass="error"/>
				</td>		
			</tr>
			<tr>
				<td>
					<form:label path="user_name">
						<spring:message text="Please Enter Your Email *"/>
					</form:label>
				</td>
				<td>
					<form:input path="user_name" maxlength="50"/>
				</td> 
				<td>
					<form:errors path="user_name" cssClass="error"/>
				</td>
			</tr>
		
			<tr>
				<td>
				</td>
				<td align="center">
					<input type="submit" value="<spring:message text="Retrieve Password"/>" />
				</td>
				<td>
				</td>
			</tr>
		</table>
	</form:form>
</c:if>

</body>
</html>
