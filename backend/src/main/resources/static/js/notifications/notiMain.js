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
};

var showData = function(notiJsonData){
    var notiDataList = notiJsonData.notiDataList;
    for (var i in notiDataList){
        var aElement = document.createElement("a");
        aElement.className="list-group-item";
        aElement.href="#";

        var textNode= document.createTextNode(notiDataList[i].contents);
        aElement.appendChild(textNode);

        $("#notiData").append(aElement);
    }
    $("#notiCnt").append(notiJsonData.notiCnt.totalCnt);
};