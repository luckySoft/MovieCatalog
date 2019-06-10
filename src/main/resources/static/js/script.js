window.onload = () => {
    fillDropdownList();
}

function fillDropdownList() {
    $('#dropdown-content a').click(function() {
        const txt = $(this).attr('data-city');
        $('#btn-options').text(txt);
        $('#btn-options').attr('data-city', txt);
    });
}

function send() {

    var port = location.port;

    var url = `http://localhost:8080/movies`;

    var searchTerm = $('#movie-search').val();
    var category = $('#btn-options').text().toLowerCase();

    $.ajax({
        url: url + `/${category}=${searchTerm}`,
        type: 'GET',
        success: (result) => {
            fillMovieTable(result);
        },
        error: (thrownError) => {
            alert(thrownError.responseText);
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
                    <th><div class="th">Countries</div></th>
                    <th><div class="th">Director</div></th>
                    <th><div class="th">writers</div></th>
                    <th></th>
                </tr>
            </tbody>
        </table>
    `);

    //<th><div class="th">Runtime</div></th>
    // <th><div class="th">Actors</div></th>
    // <th><div class="th">Plot</div></th>
    // <th><div class="th">Poster</div></th>
    // <th><div class="th">IMDB</div></th>
    // <th><div class="th">Tomato</div></th>
    // <th><div class="th">Metacritic</div></th>
    // <th><div class="th">Awards</div></th>
    // <th><div class="th">Type</div></th>

    for (var i = 0; i < result.length; i++) {
        let title = result[i]['title'];
        let year = result[i]['year'];
        let rated = result[i]['rated'];
        //let runtime = result[i]['runtime'];
        let contries = result[i]['countries'];
        let director = result[i]['director'];
        let writers = result[i]['writers'];
        //let actors = result[i]['actors'];
        //let plot = result[i]['plot'];
        //let poster = result[i]['poster'];

        //let imdbResult = result[i]['imdb'];
        // let imdb = '';
        // if (imdbResult != null && imdbResult != undefined) {
        //     for (var key in imdbResult) {
        //         if (imdbResult.hasOwnProperty(key)) {
        //             imdb += key + ' : ' + imdbResult[key] + '<br>';
        //         }
        //     }
        // }

        // let tomatoResult = result[i]['tomato'];
        // let tomato = '';
        // if (tomatoResult != null && tomatoResult != undefined) {
        //     for (var key in tomatoResult) {
        //         if (tomatoResult.hasOwnProperty(key)) {
        //             tomato += key + ' : ' + tomatoResult[key] + '<br>';
        //         }
        //     }
        // }

        //let metacritic = result[i]['metacritic'];

        // let awardsResult = result[i]['awards'];
        // let awards = '';
        // if (awardsResult != null && awardsResult != undefined) {
        //     for (var key in awardsResult) {
        //         if (awardsResult.hasOwnProperty(key)) {
        //             awards += key + ' : ' + awardsResult[key] + '<br>';
        //         }
        //     }
        // }

        let type = result[i]['type'];

        $('#movieData tbody').append(
            '<tr><td id="Title"><div class="tdData">' + title + '</div></td>' +
            '<td id="year"><div class="tdData">' + year + '</div></td>' +
            '<td id="rated"><div class="tdData">' + rated + '</div></td>' +
            //'<td id="Runtime"><div class="tdData">' + runtime + '</div></td>' +
            '<td id="contries"><div class="tdData">' + contries + '</div></td>' +
            '<td id="director"><div class="tdData">' + director + '</div></td>' +
            '<td id="writers"><div class="tdData">' + writers + '</div></td>'
            //'<td id="actors"><div class="tdData">' + actors + '</div></td>' +
            //'<td id="plot"><div class="tdData">' + plot + '</div></td>' +
            //'<td id="poster"><div class="tdData">' + poster + '</div></td>' +
            //'<td id="imdb"><div class="tdData">' + imdb + '</div></td>' +
            //'<td id="tomato"><div class="tdData">' + tomato + '</div></td>' +
            //'<td id="metacritic"><div class="tdData">' + metacritic + '</div></td>' +
            //'<td id="awards"><div class="tdData">' + awards + '</div></td>' +
            //'<td id="type"><div class="tdData">' + type + '</div></td>' +
        );
    }

}
