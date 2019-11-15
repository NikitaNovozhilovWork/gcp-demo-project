var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#history").prop("disabled", !connected);
    $("#disconnect").prop("disabled", !connected);
    $("#chat").html("");
}

function connect() {
    var socket = new SockJS('/gitter-translator-streaming');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/gitter-messages', function (data) {
            showMessage(JSON.parse(data.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function history() {
    var historyBtn = $("#history");
    var historyHref = historyBtn.attr("href");
    historyBtn.prop("disabled", true);
    var fromDate;
    if ($("#chat > tr").length) {
        fromDate = new Date(Number($("#chat > tr").attr('date'))).toISOString();
    } else {
        fromDate = new Date().toISOString();
    }
    var request = {
        from: fromDate,
        size: 10
    };
    if (request.from && 0 !== request.from.length) {
        $.ajax({
          type: "POST",
          contentType: "application/json",
          url: historyHref,
          dataType: 'json',
          data: JSON.stringify(request),
          success: function (messages) {
            for (var i = 0 ; i < messages.length ; i++) {
                showHistory(messages[i]);
            }
          },
          error: function (e) {
            console.log('History loading error: ' + e);
          }
        });
    }
    historyBtn.prop("disabled", false);
}

function showMessage(message) {
    var date = new Date(message.sent);
    $("#chat").append("<tr id=" + message.id + " date=" + date.getTime() + "><td>" + message.userDisplayName + "</td><td>" + message.text + "</td><td>" + message.translation + "</td><td>" + date.toLocaleString() + "</td></tr>");
}

function showHistory(message) {
    var date = new Date(message.sent);
    $("#chat").prepend("<tr id=" + message.id + " date=" + date.getTime() + "><td>" + message.userDisplayName + "</td><td>" + message.text + "</td><td>" + message.translation + "</td><td>" + date.toLocaleString() + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#history" ).click(function() { history(); });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
});

$('#homeButton').on('click', function(event) {
  event.preventDefault();
  $('html, body').animate({ scrollTop: 0 }, 'slow');
});