<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/myNav.css">

<script src="<%=request.getContextPath()%>/jQuery/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/jQuery/purl.js"></script>
<style>
html, body {
	max-width: 100%;
	overflow-x: hidden;
	max-height: 100%;
	overflow-y: hidden;
}
</style>
</head>

<body>
	<%@ include file="navigation.jsp"%>
	<h1>&nbsp</h1>
	<h4>&nbsp&nbsp&nbsp Video Tracking Function &nbsp&nbsp&nbsp
		(Please use IE for this function)</h4>
	<p>
		&nbsp&nbsp Step one: <Strong><a id="pause_lnk" href="#">Please
				Click here to start</a></Strong> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Step two: Draw a
		rectangle around target &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Step three:
		<button id="play_tracking" disabled>Play Tracking Video</button>
	</p>
	<div id="coordinates">&nbsp</div>

	<div id="stepThree">

		<center>
			<canvas width="1200" height="900" id="mycanvas" style="border: 2px;">Sorry,
			no canvas available</canvas>
			<p>
		</center>
		<p>
			<video id="myvideo"> <source
				src="<%=request.getContextPath()%><s:property value="videoFile"/>"
				type="video/mp4"> Your browser doesn't support HTML5 video.
			Try a different browser instead. </video>
		</p>
	</div>
</body>

<script language="javascript">
	var canvas = document.getElementById('mycanvas'), ctx = canvas
			.getContext('2d'), rect = {}, drag = false;

	var v = document.getElementById("myvideo");
	var videoHeight;
	var videoWidth;
	function pause_and_capture() {
		// 		var v = document.getElementById("myvideo");
		videoHeight = v.getBoundingClientRect().height;
		videoWidth = v.getBoundingClientRect().width;
		v.pause();
		ctx.drawImage(v, 0, 0);
// 				alert(videoWidth + "," + videoHeight);
	}

	pause_lnk.addEventListener('click', pause_and_capture, false);

	function draw() {
		pause_and_capture();
		ctx.beginPath();
		ctx.strokeStyle = "#F00";
		ctx.lineWidth = "2";
		ctx.rect(rect.startX, rect.startY, rect.w, rect.h);
		ctx.stroke();
	}

	function mouseDown(e) {
		rect.startX = e.pageX - this.offsetLeft;
		rect.startY = e.pageY - this.offsetTop;
		drag = true;
	}

	function mouseUp() {
		drag = false;

		var text = "&nbsp&nbsp<strong>Coordinates: x=" + rect.startX + ", y="
				+ rect.startY + ", w=" + rect.w + ", h=" + rect.h + "</strong>";
		$('button[id="play_tracking"]').removeAttr('disabled');
		document.getElementById("coordinates").innerHTML = text;
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

	var videoId = $.url().param('videoId');

	$(document)
			.ready(
					function() {
						$('button[id="play_tracking"]')
								.click(
										function() {
											$.post("struts/playTracking", {
												x : rect.startX,
												y : rect.startY,
												w : rect.w,
												h : rect.h,
												videoId : videoId,
												videoWidth : videoWidth,
												videoHeight : videoHeight
											}, function(data, status) {
												$('div[id="stepThree"]').html(
														data);
												// 				alert(data);
											});
											$('button[id="play_tracking"]')
													.attr('disabled', true);
											$('div[id="stepThree"]')
													.html(
															'<center><h1>&nbsp</h1><h1>&nbsp&nbsp&nbsp&nbspPlease wait...</h1></center>');
										});
					});
</script>

</html>