<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/myNav.css">
<title>download</title>
</head>
<body background="<%=request.getContextPath()%>/images/images.jpg">

	<%@ include file="navigation.jsp"%>
	<h1>&nbsp</h1>
	<center>

		<h3>&nbsp</h3>
		<p>
		<div style="width: 80%; margin: 0 auto;">
			<img border="0"
				src="<%=request.getContextPath()%>/animal_images/kangaroo.jpg"
				alt="mydownload" width="152" height="114"> <img border="0"
				src="<%=request.getContextPath()%>/animal_images/koala.jpg"
				alt="mydownload" width="152" height="114"> <img border="0"
				src="<%=request.getContextPath()%>/animal_images/emu.jpg"
				alt="mydownload" width="152" height="114">

		</div>
		<h3>&nbsp</h3>
		<h3>&nbsp</h3>
		<h3>Click on below logo to download Android App:</h3>
		<h3>&nbsp</h3>
		<div>
			<a href="<%=request.getContextPath()%>/download/VideoApp.apk"><img border="0"
				src="<%=request.getContextPath()%>/images/app_logo.png"
				alt="mydownload" width="152" height="114" /></a>
		</div>
	</center>
</body>
</html>

