document.getElementById("search").addEventListener("click", function (ev) {
    var c = 0;
    var req = "http://localhost:8080/resolution";
    if (document.getElementById("dateStart").value != ""){
        req += "?ds=" + document.getElementById("dateStart").value;
        c = 1;
    }
    if (document.getElementById("dateFinish").value != ""){
        if (c == 1){
            req += "&";
        }else {
            req += "?";
            c = 1;
        }
        req += "df=" + document.getElementById("dateFinish").value;
    }
    if (document.getElementById("n").value != ""){
        if (c == 1){
            req += "&";
        }else {
            req += "?";
            c = 1;
        }
        req += "n=" + document.getElementById("n").value
    }
    if (document.getElementById("a").value != ""){
        if (c == 1){
            req += "&";
        }else {
            req += "?";
            c = 1;
        }
        req += "a=" + document.getElementById("a").value
    }
    if (document.getElementById("c").value != ""){
        if (c == 1){
            req += "&";
        }else {
            req += "?";
            c = 1;
        }
        req += "c=" + document.getElementById("c").value
    }
    document.location.href = req;
})