<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/simple_layout.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/myNav.css">
<title>File Upload</title>
</head>
<body background="<%=request.getContextPath()%>/images/images.jpg">
	<%@ include file="navigation.jsp"%>
	<!-- 	<h1>&nbsp</h1> -->

	<div id="mainBody">
		<div id="header"></div>
		<div id="navColumn"></div>
		<div id="mainContent">

			<span id="block"></span>
			<div id="dashboard3">
				<h5>&nbsp</h5>
				<center>
					<form name="input" action="struts/sendMessage" method="post">
						<label>Input notification message:</label>
						<h5>&nbsp</h5>
						<input type="text" name="message" /> <input type="submit"
							value="send" />
					</form>
				</center>
			</div>
			<div id="dashboard3">
				<h5>&nbsp</h5>
				<center>
					<form name="input" action="struts/koalaAwake" method="post">
						<input type="submit" id="clickbutton" value="Koala is awake" />
					</form>
					<form name="input" action="struts/kangarooAwake" method="post">
						<input type="submit" id="clickbutton" value="Kangaroo is awake" />
					</form>
					<form name="input" action="struts/koalaVideo" method="post">
						<input type="submit" id="clickbutton" value="New Koala video" />
					</form>
					<form name="input" action="struts/kangarooVideo" method="post">
						<input type="submit" id="clickbutton" value="New Kangaroo video" />
					</form>
				</center>
			</div>

			<%-- 			<span id="block"></span> --%>
			<div id="dashboard3">
				<h5>&nbsp</h5>
				<center>
					<form method="post" action="upload" enctype="multipart/form-data">
						Select video file to upload:
						<h6>&nbsp</h6>
						<input type="file" name="file" /> <input type="submit"
							value="Upload" />
					</form>
				</center>
			</div>
			<div id="dashboard3">
				<h5>&nbsp</h5>
				<center>
					<form name="input" action="struts/trackingJNI" method="post">
						<input type="submit" id="clickbutton" value="JNI test" />
					</form>
				</center>
			</div>

		</div>
		<div id="adColumn"></div>
		<!-- 		<div id="footer">Programmed by Linson</div> -->

	</div>

</body>
</html>