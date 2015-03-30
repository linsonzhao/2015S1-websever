<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/myDiv.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/myTable.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/myNav.css">
<title>about</title>
</head>
<body background="<%=request.getContextPath()%>/images/images.jpg">
	<%@ include file="navigation.jsp"%>
	<h1>&nbsp</h1>
	<h1>&nbsp</h1>
	<h1>&nbsp</h1>
	<div class="smallblock"></div>
	<div class="halfleft">
		<img alt="etri" src="<%=request.getContextPath()%>/images/etri.png"
			height="50" width="115"> <a href="struts/admin"><img alt="usyd"
			src="<%=request.getContextPath()%>/images/usyd.png" height="50"
			width="115"></a>
		<h3>&nbsp</h3>
		<h2>Project Title</h2>
		<p>Study on event detection and extraction algorithm in video
			streams</p>
		<h3>&nbsp</h3>
		<h2>Research Collaborators</h2>
		<p>
			<strong>Dr. Changseok Bae</strong><br /> Director and Principal
			Member of Research Scientist<br /> Human Computing Research Section,
			ETRI, Korea<br />
		</p>
		<h3>&nbsp</h3>
		<p>
			<strong>Dr. Yuk Ying Vera Chung</strong><br /> School of Information
			Technologies,<br /> University of Sydney
		</p>
		<h3></h3>

	</div>
	<h1>&nbsp</h1>

</body>
</html>