<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/device_websocket.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/myNav.css">

</head>
<body>
	<%@ include file="navigation.jsp"%>

	<h1>&nbsp</h1>
	<h1>&nbsp</h1>

	<div id="wrapper">

		<div id="addDevice">

			<span><input id="btnUpdateList" type="button" class="button"
				value="Update Webcam List" onClick=updateList()></span> <span>Webcam
				name: <select id="slWebcamName" onChange=changeWebcam()></select>
			</span>

		</div>
		<br />
		<h3>Currently connected devices:</h3>
		<div id="content"></div>
	</div>

	<script
		src="<%=request.getContextPath()%>/javascript/device_websocket.js"></script>

	<center>
		<h1>&nbsp</h1>
		<h1>&nbsp</h1>

		<div>
			<img id="target" style="display: inline;" />
		</div>
	</center>

</body>
</html>