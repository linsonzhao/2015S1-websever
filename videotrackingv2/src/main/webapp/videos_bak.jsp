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
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath()%>/javascript/processing.min.js"></script>

<title>videos</title>
</head>
<body background="<%=request.getContextPath()%>/images/images.jpg">

	<%@ include file="navigation.jsp"%>
	<h1>&nbsp</h1>
	<h1>&nbsp</h1>
	<center>
		<!-- 		<div style="width: 100%; margin: 0 auto;"> -->
		<div class="fullblock">
			<div class="bigblock">
				<video width="400" controls> <source
					src="<%=request.getContextPath()%>/raw_video/kangaroo.mp4"
					type="video/mp4"> Your browser does not support HTML5
				video. </video>
			</div>
			<div class="smallblock">
			<center>Kangaroo</center>
			</div>

			<div class="bigblock">
				<video width="400" controls> <source
					src="<%=request.getContextPath()%>/raw_video/koala.mp4"
					type="video/mp4"> Your browser does not support HTML5
				video. </video>
			</div>
			<div class="smallblock">Koala</div>

			<div class="bigblock">
				<video width="400" controls> <source
					src="<%=request.getContextPath()%>/raw_video/cup_tracking.mpg"
					type="video/mpg"> Your browser does not support HTML5
				video. </video>
			</div>
			<div class="smallblock">Emu</div>

		</div>
	</center>
</body>
</html>

