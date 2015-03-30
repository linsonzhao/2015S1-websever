<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/myNav.css">

<script type="text/javascript">
	var canvas = document.getElementById('canvas');
	var ctx = canvas.getContext('2d');
	var video = document.getElementById('video');

	video.addEventListener('play', function() {
		var $this = this; //cache
		(function loop() {
			if (!$this.paused && !$this.ended) {
				ctx.drawImage($this, 0, 0);
				setTimeout(loop, 1000 / 30); // drawing at 30fps
			}
		})();
	}, 0);
</script>
<title>Draw a rectangle #1</title>
<style>
body {
	padding: 0;
	margin: 0;
	/*   background:#000; */
}

canvas {
	 	display: block; 
	 	margin: 30px auto 0; 
	   background:#fff; 
	
}

h1 {
	position: absolute;
	top: 0;
	left: 0;
	/*   color:#fff; */
	margin: 0;
	font-size: 80px;
	font-family: Arial, sans-serif;
	width: 400px;
	line-height: 80px;
}
</style>
</head>

<body background="<%=request.getContextPath()%>/images/images.jpg">

	<h1>Draw a rectangle!</h1>
	<canvas id="canvas" width="500" height="500"> </canvas>

	<script>
		var canvas = document.getElementById('canvas'), ctx = canvas
				.getContext('2d'), rect = {}, drag = false;

// 		canvas2d.globalAlpha = 0.5;
		
		function draw() {
			ctx.fillRect(rect.startX, rect.startY, rect.w, rect.h);
		}

		function mouseDown(e) {
			rect.startX = e.pageX - this.offsetLeft;
			rect.startY = e.pageY - this.offsetTop;
			drag = true;
		}

		function mouseUp() {
			drag = false;
		}

		function mouseMove(e) {
			if (drag) {
				rect.w = (e.pageX - this.offsetLeft) - rect.startX;
				rect.h = (e.pageY - this.offsetTop) - rect.startY;
				ctx.clearRect(0, 0, canvas.width, canvas.height);
				draw();
			}
		}

		function init() {
			canvas.addEventListener('mousedown', mouseDown, false);
			canvas.addEventListener('mouseup', mouseUp, false);
			canvas.addEventListener('mousemove', mouseMove, false);
		}

		init();
	</script>
</body>
</html>