$(function() {
    $("#searchBtn").on("click", function() {
        var socket = new WebSocket("ws://localhost:8080/ca2/note/" + $("#category").val());
        socket.onopen = function() {
            $("#notes").val("Connected\n" + $("#notes").val());
        }
        socket.onmessage = function(evt) {
            var data = JSON.parse(evt.data);
            $("#notes").val(data.message + "\n" + $("#notes").val());
        }
    })
})
