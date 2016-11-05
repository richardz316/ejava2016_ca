$(function() {
	var socket = new WebSocket("ws://localhost:8080/ca2/notedisplay");
	socket.onopen = function() {
		$("#notes").val("Connected\n" + $("#notes").val());
	}
	socket.onmessage = function(evt) {
		var data = JSON.parse(evt.data);
		$("#notes").val(data.time + ": " + data.text + "\n" + $("#notes").val());
	}
	$("#sendBtn").on("click", function() {
		socket.send($("#message").val())
		$("#message").val("")
	})
})
