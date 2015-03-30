<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/myNav.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/myDiv.css">
<title>File Upload</title>
<script>
function validate(){
	var animalName = document.getElementById("animal-name");
	if(animalName.value == -1){
		alert("Please select an animal name");
		return false;
	}
	
	var file = document.getElementById("file");
	if(file.value == null || file.value == ""){
		alert("Please select a video file");
		return false;
	}
	
	
	return true;
	
}
</script>

</head>
<body background="<%=request.getContextPath()%>/images/images.jpg">
	<%@ include file="navigation.jsp"%>
	<h1>&nbsp</h1>
	<h1>&nbsp</h1>

	<div class="smallblock"></div>
	<div class="halfleft">

		<h1>File Upload</h1>
		<h2>&nbsp</h2>
		<%-- 		<form method="post" action="<%=request.getContextPath()%>/uploadfile" enctype="multipart/form-data"> --%>
		<!-- 			Select MP4 file to upload: <input type="file" name="file" size="60" accept='video/mp4'/> -->
		<!-- 			<p>&nbsp</p> -->
		<!-- 			Animal's name: <input type="text" name="target" size="40" /><br /> -->
		<!-- 			Description: &nbsp&nbsp&nbsp&nbsp&nbsp<input type="text" name="description" size="40" /> -->
		<!-- 			<br /> <br /> <input -->
		<!-- 				type="submit" value="Upload" /> -->
		<!-- 		</form> -->

		<form method="post" action="<%=request.getContextPath()%>/uploadfile"
			enctype="multipart/form-data">
			Select MP4 file to upload: <input id="file" type="file" name="file" size="60"
				accept='video/mp4' />
			<p>&nbsp</p>
			<s:select id="animal-name" label="Animal's name" headerKey="-1"
				headerValue="Select animal" list="animals.{name}"
				name="target" />
			<br /> Description: &nbsp&nbsp&nbsp&nbsp&nbsp<input type="text"
				name="description" size="40" /> <br /> <br /> <input
				type="submit" value="Upload" onclick="javascript:return validate();"/>
		</form>
		
	</div>
</body>
</html>