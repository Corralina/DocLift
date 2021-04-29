var pt = document.getElementsByClassName("pathC");
var pi = document.getElementsByClassName("inpV");
for(var j = 0; j < 10; j++){
    for (var i = 0; i < pt.length; i++){
        pt[i].href = pt[i].href.replace('%C2%A0', '');
    }
}

for (var i = 0; i < pi.length; i++){
    pi[i].value = pi[i].value.replace(/\s/g, '');
}
