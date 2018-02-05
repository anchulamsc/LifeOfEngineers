<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>

<div>

	<table width="100%" height="60" border="0" cellpadding="0"
		cellspacing="0">
		<tbody>
			<tr>
				<td bgcolor="#FFFFFF">
					<table width="1000" border="0" align="center" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td width="200"><a href="<c:url value='/'/>"><img  title="Young Engineers" src="${pageContext.request.contextPath}/resources/images/YoungEngineers.png" width="154" height="30" /></a></td>
								<td width="560">
									<table border="0" align="center" cellpadding="0" cellspacing="0" class="font_style3">
										<tbody>
											<tr>
												<td><a href="<c:url value='/'/>" title="Home" class="font_style3 gray">Home</a></td>
												<td width="30">&nbsp;</td>
												<td><a href="<c:url value='/aboutus'/>" title="AboutUs" class="font_style3 gray">AboutUS</a></td>
												<td width="30">&nbsp;</td>
												<td><a href="<c:url value='/activities/activity'/>" onclick="return activeUser('${userSession.activeUser}');" title="Activities" class="font_style3 gray">Activities</a></td>
												<td width="30">&nbsp;</td>
												<td><a href="<c:url value='/events/event'/>" onclick="return activeUser('${userSession.activeUser}');" title="Events" class="font_style3 gray">Events</a></td>
												<td width="30">&nbsp;</td>
												<td><a href="<c:url value='/galleries/gallery'/>" onclick="return activeUser('${userSession.activeUser}');" title="Galleries" class="font_style3 gray">Galleries</a></td>
												<td width="30">&nbsp;</td>
												<td><a href="<c:url value='/contactus/locadcontactus'/>" title="ContactUs" class="font_style3 gray">ContactUS</a></td>
												<td width="30">&nbsp;</td>
											</tr>
										</tbody>
									</table>
								</td>
								<td width="220" align="right">
									<table border="0" cellspacing="0" cellpadding="0">
										<tbody>
											<tr>
												<td><span class="font_style1"><a href="<c:url value='/registration/login'/>" title="Login"  class="font_style3 gray">Login</a></span></td>
												<td width="20">&nbsp;</td>
												<td>
													<table border="0" cellspacing="0" cellpadding="0">
														<tbody>
															<tr>
																<td bgcolor="#333333">&nbsp;</td>
																<td bgcolor="#333333"><a  title="Sign Up"  href="<c:url value='/registration/register'/>"><span class="font_style2">Sign Up</span></a></td>
																<td bgcolor="#333333">&nbsp;</td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</div>
