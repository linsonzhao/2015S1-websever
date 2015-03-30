<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<style>
.generic_txt_heading2 a {
	font-family: Droid Sans, sans-serif;
	font-size: 100%;
	color: #0000ff;
	text-decoration: none;
}
</style>
</head>

<body>

	<p>Play the video and click below link:
	<div class="generic_txt_heading2">
		<a id="pause_lnk" href="#">Pause and Capture image frame from
			video</a>
	</div>
	<div>
		<video id="myvideo" controls height="400" width="500"> <%-- 			<source src='http://upload.wikimedia.org/wikipedia/commons/7/75/Big_Buck_Bunny_Trailer_400p.ogg'> --%>
		<source src="<%=request.getContextPath()%>/raw_video/baby_kangaroo.mp4"
			type="video/mp4"> Your browser doesn't support HTML5 video.
		Try a different browser instead. </video>
		<canvas width="700" height="450" id="mycanvas"
			style="border: 2px solid #000099;">Sorry, no canvas
		available</canvas>
		<p>
	</div>

	<div>
		<object id="MediaPlayer1"
			classid="CLSID:22D6F312-B0F6-11D0-94AB-0080C74C7E95" hspace="0"
			vspace="0"
			standby="Loading Microsoft Windows Media Player components...">
			<param name="Filename"
				value="<%=request.getContextPath()%>/raw_video/cup.avi">
<!-- 			<param name="autoplay" value="false" /> -->
			<param name="controller" value="true" />

			<embed type="application/x-mplayer2"
				pluginspage="http://www.microsoft.com/Windows/MediaPlayer/"
				name="mediaplayer1" height="330" autostart="false" width="360"
				loop="false" controls="false" allowFullscreen="true"
				src="<%=request.getContextPath()%>/raw_video/cup.avi">
		</object>
	</div>
</body>

<script>
	var v = document.getElementById("myvideo");
// 	var v = document.getElementById("MediaPlayer1");
	function pause_and_capture() {
		v.pause();
		var c = document.getElementById("mycanvas");
		var ctx = c.getContext("2d");
		ctx.drawImage(v, 1, 1);
		ctx.font = "30px Arial";
		//     ctx.fillText("This is a sample text",170,170);
	}

	pause_lnk.addEventListener('click', pause_and_capture, false);
</script>

<script>
	var canvas = document.getElementById('mycanvas'), ctx = canvas
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
		alert(rect.startX + "," + rect.startY + "," + rect.w + "," + rect.h);
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

</html>