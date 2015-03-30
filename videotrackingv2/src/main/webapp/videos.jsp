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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/myTable.css">
<script language="JavaScript" type="text/JavaScript"
	src="<%=request.getContextPath()%>/javascript/processing.min.js"></script>

<title>videos</title>
</head>
<body background="<%=request.getContextPath()%>/images/images.jpg">

	<%@ include file="navigation.jsp"%>
	<h1>&nbsp</h1>
	<h1>&nbsp</h1>

	<div id="videoTable">
		<table class="noborder">
			<tbody>

				<s:iterator value="videoList">
					<tr>
						<s:iterator status="rowStatus">
							<td style="width: 45%;"></td>
							<td><video width="400" controls> <source
									src="<%=request.getContextPath()%><s:property value="videoFile"/>"
									type="video/mp4"> Your browser does not support HTML5
								video. </video></td>
							<td>&nbsp&nbsp<strong><s:property value="target" /></strong><br />videoId=<s:property value="videoId" /><br/>
							&nbsp&nbsp<a href="struts/trackingVideo?videoId=<s:property value="videoId" />">Tracking Video</a>
							</td>
						</s:iterator>
					</tr>
					<tr style="height: 20px;"></tr>
				</s:iterator>
			</tbody>
		</table>

	</div>

</body>
</html>

