$(document).ready(function() {
    $(".button-collapse").sideNav();
    var tr = $("#rates tr").get(0);

    var size = 5;

    for(i = 0; i <= size; i ++)
        $tr = $(tr).append( "<th>it "+ (i+1) +"</th>" );

    connect();
});

var stompClient = null;

function setConnected(connected) {
    if (connected) {
        $("#connection_status").text("sync");
    } else {
        $("#connection_status").text("sync_problems");
    }
}

function connect() {
    var socket = new SockJS('/server-websocket');
    stompClient = Stomp.over(socket);
    stompClient.debug = null;
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body));
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

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'message': "my name"}));
}

function showGreeting(message) {
    // console.dir(message);
    show(message);
    // console.log(message);
    // $("#greetings").append("<tr><td>" + message + "</td></tr>");
}
function show(payload) {
    var row = $("#c_" + payload.currencyCode);
    var imageURL = "http://coincap.io/images/coins/" + payload.currencyCode.toLowerCase() + ".png";

    if (row.length == 0) {
        $("<tr id='c_" + payload.currencyCode + "'><td><p><img align='middle' class='currency_image' src='" + imageURL + "' />" + payload.currencyName + "(" + payload.currencyCode + ")</p></td><td>" + payload.price + "</td><td>" + payload.percentage + "</td></tr>")
        .appendTo('#rates');
    } else {
        var tds = ($(row).find("td"));
        $(tds[0]).html("<p><img align='middle' class='currency_image' src='" + imageURL + "' />" + payload.currencyName + "(" + payload.currencyCode + ")</p>");
        $(tds[1]).html(payload.price);
        $(tds[2]).html(payload.percentage);

        for (var i = 3; i < tds.length; i++) {
            $(tds[i]).remove();
        }

        for (var i =0; i < payload.percentages.length; i++) {
            var p = payload.percentages[i];
            $("<td>" + p.toPrecision(2)  + "</td>").appendTo($(row));
        }
    }

    return row;
}
