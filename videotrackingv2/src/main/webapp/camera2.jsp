<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/myNav.css">

<title>camera</title>
</head>
<body background="<%=request.getContextPath()%>/images/images.jpg">
	<%@ include file="navigation.jsp"%>
	<script type="text/javascript">
		var ws = new WebSocket("ws://" + location.host
				+ "/videotracking/camera2");
		ws.onopen = function() {
			console.log("Openened connection to websocket");
		}
		ws.onmessage = function(msg) {
			var target = document.getElementById("target");
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

	<center>
		<h1>&nbsp</h1>
		<h1>&nbsp</h1>
		
		<h1>&nbsp</h1>

		<div>
			<img id="target" style="display: inline;" />
		</div>
	</center>
</body>
</html>