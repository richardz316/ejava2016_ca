$(function() {
    
    var buildHtmlTable = function(notes){            
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
    
    var addAllColumnHeaders = function(notes) {
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
    
    $("#searchBtn").on("click", function() {
        var socket = new WebSocket("ws://localhost:8080/ca2/note/" + $("#category").val());
        socket.onopen = function() {
        }
        
        socket.onmessage = function(evt) {
            var data = JSON.parse(evt.data);
            buildHtmlTable(JSON.parse(data.message));
        }
    })
})
