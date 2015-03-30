<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/test.css">

<title>Playing YouTube video on HTML5 canvas</title>
<meta name="viewport"
	content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, width=device-width" />
<style type="text/css">
html, body {
	width: 100%;
	height: 100%;
	padding: 0px;
	margin: 0px;
}

#canvas {
	padding: 0px;
	margin: 0px;
	top: 0;
	left: 0;
	z-index: 30;
	position: absolute;
	width: 50%;
	height: 50%;
}
</style>
</head>
<body>
	<div style="display: none;">
	<video id="video" autoplay="true" loop="true">
<!-- 		<video id="video" >     <source src="./video/BigBuckBunny_640x360.ogv" type="video/ogg" />   -->
		<source src="<%=request.getContextPath()%>/raw_video/kangaroo.mp4"
			type="video/mp4" /> </video>
	</div>
	<canvas id="canvas"></canvas>
	<script>
		document.addEventListener('DOMContentLoaded', function() {
			var v = document.getElementById('video');
			var canvas = document.getElementById('canvas');
			var context = canvas.getContext('2d');
			//var cw = Math.floor(canvas.clientWidth / 100);  
			//var ch = Math.floor(canvas.clientHeight / 100);  
			var cw = Math.floor(canvas.clientWidth);
			var ch = Math.floor(canvas.clientHeight);
			canvas.width = cw;
			canvas.height = ch;
			v.addEventListener('play', function() {
				draw(this, context, cw, ch);
			}, false);
		}, false);
		function draw(v, c, w, h) {
			if (v.paused || v.ended)
				return false;
			c.drawImage(v, 0, 0, w, h);
			setTimeout(draw, 20, v, c, w, h);
		}
	</script>
</body>
</html>
