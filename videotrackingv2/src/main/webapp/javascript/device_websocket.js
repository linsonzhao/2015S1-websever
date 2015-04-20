var btnUpdateList = document.getElementById("btnUpdateList");
var slWebcamName = document.getElementById("slWebcamName");
var opt = document.createElement("option");

var ws = new WebSocket("ws://" + location.host + "/videotracking/camdevices");
ws.onopen = function() {
	console.log("Openened connection to camdevices websocket");
}
ws.onmessage = function(event) {
	var device = JSON.parse(event.data);
	if (device.action === "add") {
		var slWebcamName = document.getElementById("slWebcamName");
		var option = document.createElement("option");
		var id = "optWebcam" + device.id;
		option.id = id;
		option.value = device.name;
		option.innerHTML = device.name;
		slWebcamName.appendChild(option);
		var opt = document.getElementById(id);
		console.log(opt.id + "," + opt.tagName + "," + opt.value);
		
		var target = document.getElementById("target");
		url = window.URL.createObjectURL(device.image);
		target.onload = function() {
			window.URL.revokeObjectURL(url);
		};
		target.src = url;
	}
	if (device.action === "clear") {
		var size = parseInt(device.size);
		for (i = 0; i < size; i++) {
			document.getElementById("optWebcam" + i).remove();
		}
	}
}

function updateList() {
	var DeviceAction = {
		action : "update",
		message : "update webcam list..."
	};
	ws.send(JSON.stringify(DeviceAction));
}

function changeWebcam() {
	var webcam = document.getElementById("slWebcamName").value;
	var DeviceAction = {
		action : "changewebcam",
		device : webcam,
		message : "changewebcam"
	};
	ws.send(JSON.stringify(DeviceAction));
}


//second web socket define here
//var ws = new WebSocket("ws://" + location.host
//		+ "/videotracking/livevideo");
//ws.onopen = function() {
//	console.log("Openened connection to websocket");
//}

