/*
Deze function laadt de kaart met bepaalde coordinaten in die hij leest uit de input-box. Hierna zet het een tag op de plek van de coordinaten
 */
function initMap() {
    var coordinates = document.getElementById("coordinatenInput").value + '';
    var coordinatesArray = coordinates.split(",", 2);
    var lat = parseFloat(coordinatesArray[0]);
    var lng = parseFloat(coordinatesArray[1]);

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
