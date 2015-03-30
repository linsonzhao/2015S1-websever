<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>playTracking</title>
</head>
<body>
	<center>

				<video id="myvideo" autoplay=true controls> <source
		 			src="<%=request.getContextPath()%><%=request.getAttribute("outputFile")%>" 
		 			type="video/mp4"> Your browser doesn't support HTML5 video. 
				Try a different browser instead. </video>

		<!-- 		<object id="MediaPlayer1" -->
		<!-- 			classid="CLSID:22D6F312-B0F6-11D0-94AB-0080C74C7E95" hspace="0" -->
		<!-- 			vspace="0" -->
		<!-- 			standby="Loading Microsoft Windows Media Player components..."> -->
		<!-- 			<param name="Filename" -->
		<%-- 				value="<%=request.getContextPath()%><%=request.getAttribute("outputFile")%>"> --%>

		<!-- 			<embed type="application/x-mplayer2" -->
		<!-- 				pluginspage="http://www.microsoft.com/Windows/MediaPlayer/" -->
		<!-- 				name="mediaplayer1" autoplay="true" autostart="true" height="1700" -->
		<!-- 				width="1700" loop="false" controls="true" allowFullscreen="True" -->
		<%-- 				src="<%=request.getContextPath()%><%=request.getAttribute("outputFile")%>" /> --%>
		<!-- 		</object> -->

<!-- 		<object id="MediaPlayer1" -->
<!-- 			classid="CLSID:22D6F312-B0F6-11D0-94AB-0080C74C7E95" hspace="0" -->
<!-- 			vspace="0" -->
<!-- 			standby="Loading Microsoft Windows Media Player components..."> -->
<!-- 			<param name="Filename" -->
<%-- 				value="<%=request.getContextPath()%><%=request.getAttribute("outputFile")%>"> --%>

<!-- 			<embed type="application/x-mplayer2" -->
<!-- 				pluginspage="http://www.microsoft.com/Windows/MediaPlayer/" -->
<!-- 				name="mediaplayer1" autoplay="true" autostart="true"  -->
<!-- 				loop="false" controls="true" allowFullscreen="True" -->
<%-- 				src="<%=request.getContextPath()%><%=request.getAttribute("outputFile")%>" /> --%>
<!-- 		</object> -->

	</center>

</body>
</html>

