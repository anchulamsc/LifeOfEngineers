<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
</head>
<body>

<c:url var="submitAction" value="/registration/enroll" ></c:url>

<form:form action="${submitAction}" commandName="registrationInfo">
	
	<table>	
		<tr>
			<td>
			</td>
			<td>
				<h1> Sign up </h1> 
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
				<form:input path="user_name" maxlength="50" />
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
			<td>
				<form:label path="confirm_password">
					<spring:message text="Please Confirm Your Password *"/>
				</form:label>
			</td>
			<td>
				<form:password path="confirm_password" maxlength="20"/>
			</td>
			<td>
				<form:errors path="confirm_password" cssClass="error"/>
			</td> 
		</tr>
		
		<tr>
			<td>
				<form:label path="mobile_num">
					<spring:message text="Please Enter Your Mobile No: *"/>
				</form:label>
			</td>
			<td>
				<form:input path="mobile_num" maxlength="20"/>
			</td>
			<td>
				<form:errors path="mobile_num" cssClass="error"/>
			</td> 
		</tr>
		
		<tr>
			<td>
				<form:label path="first_name">
					<spring:message text="First Name *"/>
				</form:label>
			</td>
			<td>
				<form:input path="first_name" maxlength="50"/>
			</td> 
			<td>
				<form:errors path="first_name" cssClass="error"/>
			</td>
		</tr>
		
		<tr>
			<td>
				<form:label path="last_name">
					<spring:message text="Last Name *"/>
				</form:label>
			</td>
			<td>
				<form:input path="last_name" maxlength="50"/>
			</td> 
			<td>
				<form:errors path="last_name" cssClass="error"/>
			</td>
		</tr>
		
		<tr>
			<td>
				<form:label path="middle_name">
					<spring:message text="Middle Name"/>
				</form:label>
			</td>
			<td>
				<form:input path="middle_name" maxlength="50"/>
			</td> 
		</tr>

		<tr>
			<td>
				<form:label path="birth_date">
					<spring:message text="Date Of Birth(MM/DD/YYYY) *"/>
				</form:label>
			</td>
			<td>
				<form:input path="birth_date" maxlength="15"/>
			</td>
			<td>
				<form:errors path="birth_date" cssClass="error"/>
			</td> 
		</tr>
		
		<tr>
			<td>
				<form:label path="gender">
					<spring:message text="Gender"/>
				</form:label>
			</td>
			<td>
				<form:radiobutton path="gender" value="Male"/>Male
				<form:radiobutton path="gender" value="Female"/>Female
			</td> 
		</tr>
		
		<tr>
			<td>
				<form:label path="marital_status">
					<spring:message text="Marital Status"/>
				</form:label>
			</td>
			<td>
				<form:radiobutton path="marital_status" value="Single"/>Single
				<form:radiobutton path="marital_status" value="Married"/>Married
			</td> 
		</tr>
		
		<tr>
			<td>
				<form:label path="technologies">
					<spring:message text="Technologies you work"/>
				</form:label>
			</td>
			<td>
				<form:textarea path="technologies" rows="5" maxlength="100"/>
			</td> 
		</tr>

		<tr>
			<td>
				<a href="<c:url value='/registration/login'/>" >Already Registered?</a>
			</td>
			<td>
				<input type="submit" value="<spring:message text="SignUp"/>" />
			</td>
		</tr>
	</table>	
</form:form>

</body>
</html>
