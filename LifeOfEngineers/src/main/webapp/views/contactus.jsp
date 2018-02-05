<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
</head>
<body>

<c:url var="sendMessageAction" value="/contactus/sendmessage" ></c:url>

<form:form action="${sendMessageAction}" commandName="contactInfo">
		<div>																	
			<h1  style="font-weight: 100; text-align: center; font-size: 40px; ">CONTACT US</h1>
			<h2 style="font-weight: 300; text-align: center; margin-bottom: 20px; ">For further detail, please contact us.</h2>			
		</div>
		
		<table>
						<tr>
							<td>
								<form:label path="user_name">
									<spring:message text="Please Enter Your Name *"/>
								</form:label>
							</td>
							<td>
								<form:input path="user_name" maxlength="100"/>
							</td> 
							<td>
								<form:errors path="user_name" cssClass="error"/>
							</td>
						</tr>
						<tr>
							<td>
								<form:label path="user_email">
									<spring:message text="Please Enter Your Email *"/>
								</form:label>
							</td>
							<td>
								<form:input path="user_email" maxlength="50"/>
							</td>
							<td>
								<form:errors path="user_email" cssClass="error"/>
							</td> 
						</tr>
						<tr>
							<td>
								<form:label path="user_mobile">
									<spring:message text="Please Enter Your Mobile No *"/>
								</form:label>
							</td>
							<td>
								<form:input path="user_mobile" maxlength="15"/>
							</td>
							<td>
								<form:errors path="user_mobile" cssClass="error"/>
							</td> 
						</tr>
						<tr>
							<td>
								<form:label path="user_message">
									<spring:message text="Please Write Message to be Sent *"/>
								</form:label>
							</td>
							<td>
								<form:textarea path="user_message" rows="10" cols="50"/>
							</td>
							<td>
								<form:errors path="user_message" cssClass="error"/>
							</td> 
						</tr>
						<tr>
							<td>
								
							</td>
							<td align="center">
								<input type="submit" value="<spring:message text="Send Message"/>" />
							</td>
						</tr>
					</table>
</form:form>		
</body>
</html>
