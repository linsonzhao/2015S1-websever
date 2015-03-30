<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/myTable.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/myNav.css">
<title>videotracking</title>
</head>
<body background="<%=request.getContextPath()%>/images/images.jpg">
	<%@ include file="navigation.jsp"%>
	<h1>&nbsp</h1>
	<h1>&nbsp</h1>
	<center>
		<h1>Welcome to digital zoo!</h1>
		<h3>&nbsp</h3>
		<h3>
			<a href="videos.jsp">Watch animal videos online</a>
		</h3>
		<h3>
			<a href="#">Subscribe animal videos</a>
		</h3>
		<h3>
			<a href="#">Find animal location</a>
		</h3>
		<h3>
			<a href="#">Write or view comments</a>
		</h3>
	</center>
	<h1>&nbsp</h1>
	<div id="page-wrap">
		<table class="myTable">
			<tbody>
				<s:iterator value="rows">
					<tr>
						<s:iterator status="rowStatus">
							<td><s:property /></td>
						</s:iterator>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
</body>
</html>