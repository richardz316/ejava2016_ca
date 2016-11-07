
    var socket;
    var serviceLocation = "ws://localhost:8080/ca2/note/";
    
    function buildHtmlTable(notes){            
        $("#notetable").children('tbody').remove();
        var columns = addAllColumnHeaders(notes);

        for (var i = 0 ; i < notes.length ; i++) {
            var row$ = $('<tr/>');
            for (var colIndex = 0 ; colIndex < columns.length ; colIndex++) {
                var cellValue = notes[i][columns[colIndex]];

                if (cellValue == null) { cellValue = ""; }

                row$.append($('<td/>').html(cellValue));
            }
            $("#notetable").append(row$);
        }
    }
    
    function addAllColumnHeaders(notes) {
        var columnSet = [];
        var headerTr$ = $('<tr/>');

        for (var i = 0 ; i < notes.length ; i++) {
            var rowHash = notes[i];
            for (var key in rowHash) {
                if ($.inArray(key, columnSet) == -1){
                    columnSet.push(key);
                    headerTr$.append($('<th/>').html(key));
                }
            }
        }
        $("#notetable").append(headerTr$);

        return columnSet;
    }

    function onMessageReceived(evt) {
        var data = JSON.parse(evt.data);
        if (typeof data.notes !== 'undefined') {
            buildHtmlTable(JSON.parse(data.notes));
        }
        
        else {
            var row$ = $('<tr/>');
            row$.append($('<td/>').html(data.Title));
            row$.append($('<td/>').html(data.Posted));
            row$.append($('<td/>').html(data.User));
            row$.append($('<td/>').html(data.Category));
            row$.append($('<td/>').html(data.Content));
            $('#notetable tr:first').after(row$);
        }
        
    }
    
    function connectToServer() {
        socket = new WebSocket("ws://localhost:8080/ca2/note/" + $("#category").val());
        socket.onmessage = onMessageReceived;
    }

    $(document).ready(function() {

        $('#searchBtn').click(function(evt) {
            connectToServer();
        });
    });