<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
</head>
<body>
<div>																	
	<h1  style="font-weight: 100; text-align: center; font-size: 40px; ">Events Details</h1>
	<h2 style="font-weight: 300; text-align: center; margin-bottom: 20px; ">The following are the Events..</h2>			
</div>
<c:if test="${eventsInfo.displayView}">
	<form:form>
	    <table>
	        <c:forEach items="${eventsList}" var="events">
	            <tr>
	                <td>Event Date: <c:out value="${events.event_date}" /></td>
	                <td>Event Name: <c:out value="${events.event_name}" /></td>
	            </tr>
	            <tr >
	                <td colspan="2"><p><c:out value="${events.event_details}" /> </p></td>
	            </tr>
	            <tr >
	                <td colspan="2"></td>
	            </tr>
	        </c:forEach>
			<tr >
                <td colspan="2" align="center"><a href="<c:url value='/events/addeventview'/>" >Add Event</a></td>
            </tr>
	    </table>
    </form:form>
</c:if>

<c:if test="${eventsInfo.addView}">
	<c:url var="sendMessageAction" value="/events/addEvents" ></c:url>

	<form:form action="${sendMessageAction}" commandName="eventsInfo">
			<table>
				<tr>
					<td>
						<form:label path="event_date">
							<spring:message text="Please Enter Event Date *"/>
						</form:label>
					</td>
					<td>
						<form:input path="event_date" maxlength="20"/>
					</td> 
					<td>
						<form:errors path="event_date" cssClass="error"/>
					</td>
				</tr>
				<tr>
					<td>
						<form:label path="event_name">
							<spring:message text="Please Enter Event Name *"/>
						</form:label>
					</td>
					<td>
						<form:input path="event_name" maxlength="50"/>
					</td>
					<td>
						<form:errors path="event_name" cssClass="error"/>
					</td> 
				</tr>
				<tr>
					<td>
						<form:label path="event_details">
							<spring:message text="Please Write the Events to Save *"/>
						</form:label>
					</td>
					<td>
						<form:textarea path="event_details" rows="10" cols="50" maxlength="1000"/>
					</td>
					<td>
						<form:errors path="event_details" cssClass="error"/>
					</td> 
				</tr>
				<tr>
					<td>
						<form:input type="hidden" path="user_id"/>
					</td>
					<td align="center">
						<input type="submit" value="<spring:message text="Add Event"/>" />
					</td>
				</tr>
			</table>
	</form:form>
</c:if>
</body>
</html>
