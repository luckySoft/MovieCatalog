function send() {

    var port = location.port;

    //var url = `http://localhost:${port}/`;
    var url = `http://localhost:8080/`;

    $.ajax({
        url: url + 'movies',
        type: 'GET',
        success: (result) => {
            fillMovieTable(result);
        }
    });
}

function fillMovieTable(result) {
    console.log(result);
    $('#tb').empty();
    $('#tb').append(`
        <section class="content-header">Users</section>
        <table class="table table-striped" id="movieData">
            <tbody>
                <tr>
                    <th><div class="th">Title</div></th>
                    <th><div class="th">Year</div></th>
                    <th><div class="th">Rated</div></th>
                    <th><div class="th">Runtime</div></th>
                    <th><div class="th">Countries</div></th>
                    <th><div class="th">Director</div></th>
                    <th><div class="th">writers</div></th>
                    <th><div class="th">Actors</div></th>
                    <th><div class="th">Plot</div></th>
                    <th><div class="th">Poster</div></th>
                    <th><div class="th">IMDB</div></th>
                    <th><div class="th">Tomato</div></th>
                    <th><div class="th">Metacritic</div></th>
                    <th><div class="th">Awards</div></th>
                    <th><div class="th">Type</div></th>
                    <th></th>
                </tr>
            </tbody>
        </table>
    `);
    for (var i = 0; i < result.length; i++) {
        let title = result[i]['title'];
        let year = result[i]['year'];
        let rated = result[i]['rated'];
        let runtime = result[i]['runtime'];
        let contries = result[i]['countries'];
        let director = result[i]['director'];
        let writers = result[i]['writers'];
        let actors = result[i]['actors'];
        let plot = result[i]['plot'];
        let poster = result[i]['poster'];

        let imdbResult = result[i]['imdb'];
        let imdb = '';
        if (imdbResult != null && imdbResult != undefined) {
            for (var key in imdbResult) {
                if (imdbResult.hasOwnProperty(key)) {
                    imdb += key + ' : ' + imdbResult[key] + '<br>';
                }
            }
        }

        let tomatoResult = result[i]['tomato'];
        let tomato = '';
        if (tomatoResult != null && tomatoResult != undefined) {
            for (var key in tomatoResult) {
                if (tomatoResult.hasOwnProperty(key)) {
                    tomato += key + ' : ' + tomatoResult[key] + '<br>';
                }
            }
        }

        let metacritic = result[i]['metacritic'];

        let awardsResult = result[i]['awards'];
        let awards = '';
        if (awardsResult != null && awardsResult != undefined) {
            for (var key in awardsResult) {
                if (awardsResult.hasOwnProperty(key)) {
                    awards += key + ' : ' + awardsResult[key] + '<br>';
                }
            }
        }

        let type = result[i]['type'];

        $('#movieData tbody').append(
            '<tr><td id="Title"><div class="tdData">' + title + '</div></td>' +
            '<td id="Year"><div class="tdData">' + contries + '</div></td>' +
            '<td id="Rated"><div class="tdData">' + actors + '</div></td>' +
            '<td id="Runtime"><div class="tdData">' + runtime + '</div></td>' +
            '<td id="contries"><div class="tdData">' + contries + '</div></td>' +
            '<td id="director"><div class="tdData">' + director + '</div></td>' +
            '<td id="writers"><div class="tdData">' + writers + '</div></td>' +
            '<td id="actors"><div class="tdData">' + actors + '</div></td>' +
            '<td id="plot"><div class="tdData">' + plot + '</div></td>' +
            '<td id="poster"><div class="tdData">' + poster + '</div></td>' +
            '<td id="imdb"><div class="tdData">' + imdb + '</div></td>' +
            '<td id="tomato"><div class="tdData">' + tomato + '</div></td>' +
            '<td id="metacritic"><div class="tdData">' + metacritic + '</div></td>' +
            '<td id="awards"><div class="tdData">' + awards + '</div></td>' +
            '<td id="type"><div class="tdData">' + type + '</div></td>' +
            '<td><button class="btn btn-primary btn-flat" name="btnEdit" style="margin: 10px"> ' + ' Edit ' + '</button>' +
            '<button class="btn btn-danger btn-flat" name="btnDelete"> ' + 'Delete' + '</button></td></tr>'
        );
    }

}
