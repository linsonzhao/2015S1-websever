<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/myNav.css">

<title>Insert title here</title>
</head>
<body background="<%=request.getContextPath()%>/images/images.jpg">
	<%@ include file="navigation.jsp"%>

<div>
		<script type="text/javascript">
		var ws = new WebSocket("ws://" + location.host
				+ "/videotracking/camera1");
		ws.onopen = function() {
			console.log("Openened connection to websocket");
		}
		ws.onmessage = function(msg) {
			var target = document.getElementById("target1");
			url = window.URL.createObjectURL(msg.data);
			target.onload = function() {
				window.URL.revokeObjectURL(url);
			};
			target.src = url;
			console.log(msg.data);

		}
		timer = setInterval(function() {
			ws.send("waiting for image..");
		}, 100);
	</script>

		<script type="text/javascript">
		var ws2 = new WebSocket("ws://" + location.host
				+ "/videotracking/camera2");
		ws2.onopen = function() {
			console.log("Openened connection to websocket");
		}
		ws2.onmessage = function(msg) {
			var target = document.getElementById("target2");
			url = window.URL.createObjectURL(msg.data);
			target.onload = function() {
				window.URL.revokeObjectURL(url);
			};
			target.src = url;
			console.log(msg.data);

		}
		timer = setInterval(function() {
			ws2.send("waiting for image..");
		}, 100);
	</script>

	<center>
		<h1>&nbsp</h1>
		<h1>&nbsp</h1>
		
		<h1>&nbsp</h1>

		<div>
			<img id="target1" style="display: inline;width:480px;height:360px" />&nbsp&nbsp&nbsp&nbsp&nbsp
			<img id="target2" style="display: inline;width:480px;height:360px" />
		</div>
	</center>
</div>
</body>
</html>