function send() {
//    var xhttp = new XMLHttpRequest();
    var url = window.location.href;
//
//    xhttp.open("GET", url + 'movies', true);
//    xhttp.send();
    $.ajax({
        url: url + 'movies',
        type: 'GET',
        success: (result) => {
            console.log(result);
        }
    });
}