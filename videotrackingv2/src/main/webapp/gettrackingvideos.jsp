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

				<s:iterator value="trackingVideoList">
					<tr>
						<s:iterator status="rowStatus">
							<td style="width: 15%;"></td>
							<td><object id="MediaPlayer1"
									classid="CLSID:22D6F312-B0F6-11D0-94AB-0080C74C7E95" hspace="0"
									vspace="0"
									standby="Loading Microsoft Windows Media Player components...">
									<param name="Filename"
										value="<%=request.getContextPath()%><s:property value="videoFile"/>">

									<embed type="application/x-mplayer2"
										pluginspage="http://www.microsoft.com/Windows/MediaPlayer/"
										name="mediaplayer1" autoplay="false" autostart="false"
										height="300" width="400" loop="false" controls="true"
										allowFullscreen="True"
										src="<%=request.getContextPath()%><s:property value="videoFile"/>" />
								</object></td>
							<td>&nbsp&nbsp<strong><s:property value="target" /></strong>
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

