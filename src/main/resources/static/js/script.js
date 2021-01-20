function httpGet(theUrl)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
    xmlHttp.send( null );
    return xmlHttp.responseText;
}



/*
Deze function laadt de kaart met bepaalde coordinaten in die hij leest uit de input-box. Hierna zet het een tag op de plek van de coordinaten
 */
function initMap() {
    //Eerst pakken we het ID die door de user is opgegeven
    var id = document.getElementById("coordinatenInput").value + '';

    //Dan gebruiken we die om de bijbehorende locatie te krijgen
    var msg = httpGet("http://localhost:8080/armband/locationByID?id=" + id);
    console.log(msg);
    var msgSplit = msg.split(",");

    var lng = parseFloat(msgSplit[0].substring(1).replace('"', ''));
    var lat = parseFloat(msgSplit[1].substring(1).replace('"', ''));;

    const locatie = { lat, lng};


    // De map, gecentreerd op de locatie
    const map = new google.maps.Map(document.getElementById("map"), {
        zoom: 4,
        center: locatie,
    });

    // Een marker die de locatie aangeeft
    const marker = new google.maps.Marker({
        position: locatie,
        map: map,
    });
}

function disappear() {
    document.getElementById("container").style.visibility = "hidden";
    document.getElementById("newIDButtonContainer").style.visibility = "visible";
}

function newID() {
    document.getElementById("container").style.visibility = "visible";
    document.getElementById("newIDButtonContainer").style.visibility = "hidden";
}
