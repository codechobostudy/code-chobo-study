var notiInit = function(){
    var urlNotiInitData = "/static/js/notifications/notiInitData.json";
    $.getJSON(urlNotiInitData)
        .done(function(json){
            showData(json);
        })
        .fail(function(textStatus) {
            var aElement = document.createElement("a");
            aElement.className="list-group-item";
            aElement.href="#";

            var textNode= document.createTextNode("Request Failed: "+textStatus.status);
            aElement.appendChild(textNode);

            $("#notiData").append(aElement);
        });

    connect();
};

var showData = function(notiJsonData){
    var notiList = notiJsonData.notiList;
    for (var i in notiList){
        var aElement = document.createElement("a");
        aElement.className="list-group-item";
        aElement.href="#";

        var textNode= document.createTextNode(notiList[i].contents);
        aElement.appendChild(textNode);

        $("#notiData").append(aElement);
    }
    $("#notiCnt").html(notiJsonData.notiCnt.totalCnt);
};


//TODO: Refactor
var stompClient = null;

function setConnected(connected) {
    var status = (connected) ? "connected" : "disconnected";
    $("#webSocketStatus").append(status);
}

function connect() {
    var socket = new SockJS('/notify');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/subscribe/notiData', function(notiJsonData){
            showData(JSON.parse(notiJsonData.body));
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}