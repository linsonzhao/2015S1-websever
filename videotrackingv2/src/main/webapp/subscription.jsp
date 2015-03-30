<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/myNav.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/myDiv.css">
<title>Subscription</title>
</head>
<body background="<%=request.getContextPath()%>/images/images.jpg">
	<%@ include file="navigation.jsp"%>
	<h1>&nbsp</h1>
	<h1>&nbsp</h1>

	<div class="smallblock"></div>
	<div class="halfleft">

		<h1>Subscribe</h1>
		<h2>&nbsp</h2>
		<form method="post" action="subscription" >
			Animal's name: <input type="text" name="target" size="40" /><br />
			Description: &nbsp&nbsp&nbsp&nbsp&nbsp<input type="text"
				name="description" size="40" /> <br /> <br /> <input
				type="submit" value="Upload" />
		</form>
	</div>
</body>
</html>