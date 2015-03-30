<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("button").click(function() {
			$.post("struts/sendMessage", {
				name : "Donald Duck",
				city : "Duckburg"
			}, function(data, status) {
				alert("Data: " + data + "\nStatus: " + status);
			});
			loadXMLDoc();
		});
	});
	
	function loadXMLDoc() {
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				document.getElementById("stepTwo").innerHTML = xmlhttp.responseText;
			}
		}

		xmlhttp.open("GET", "struts/playTracking", true);
		xmlhttp.send();
	}
</script>
</head>
<body>
		
			<button>Send an HTTP POST request to a page and get the result
		back</button>
<p>	<div id="stepTwo">
		<h2>Let AJAX change this text</h2>
	</div></p>
</body>
</html>