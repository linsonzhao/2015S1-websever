<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<ul id="nav">
		<li><a href="<%=request.getContextPath()%>/index.jsp">Home</a></li>

		<li><a href="#">File</a>
			<ul>
				<li><a href="struts/uploadFile">Upload</a></li>
				<li><a href="<%=request.getContextPath()%>/download.jsp">Download</a></li>
			</ul></li>

		<!-- 		<li><a href="#">Search</a> -->
		<!-- 			<ul> -->
		<!-- 				<li><a href="#">Animal Search</a></li> -->
		<!-- 				<li><a href="#">Facilities Search</a></li> -->
		<!-- 			</ul></li> -->

		<li><a href="struts/getVideos">Watch</a>
<!-- 			<ul> -->
<!-- 				<li><a href="struts/getVideos">Raw Videos</a></li> -->
<!-- 				<li><a href="struts/getTrackingVideos">Tracking Videos</a></li> -->
<!-- 			</ul> -->
			</li>

<!-- 		<li><a href="#">Subscribe</a></li> -->

		<li><a href="#">Comments</a></li>

		<%-- 		<li><a href="<%=request.getContextPath()%>/demo.jsp">Demo</a></li> --%>

		<li><a href="<%=request.getContextPath()%>/about.jsp">About</a></li>
	</ul>
</body>
</html>