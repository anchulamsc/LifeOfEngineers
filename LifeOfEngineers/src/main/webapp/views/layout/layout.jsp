<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
	<title><tiles:insertAttribute name="title" ignore="true" /></title>  
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
	<meta content="" name="keywords">
	<meta content="" name="description">
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/YoungEngineersIcon.png">
	<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css">
	<script  src="${pageContext.request.contextPath}/resources/js/constants.js" type="text/javascript"></script>
	<script  src="${pageContext.request.contextPath}/resources/js/application.js" type="text/javascript"></script> 
</head>
<body class="sample_bigimg01" bgcolor="#f1f1f1">
	<div>
		<tiles:insertAttribute name="header" />
	</div>
	<div style="float: left; padding: 10px; width: 15%;">
		<tiles:insertAttribute name="menu" />
	</div> 
	<div
		style="float: left; padding: 10px; width: 80%; border-left: 1px solid pink;">
		<tiles:insertAttribute name="body" />
	</div>
	<div style="float: center;clear: both; ">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>
