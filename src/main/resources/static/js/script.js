var movies = [];
var page = 0;
var maxPage = Infinity;

window.onload = () => {
    ChangeDropdown();
    eventListeners();
    send();
}

function send() {

    var url = `http://localhost:8080`;

    var fullUrl;

    var category = $('#btn-options').attr('search-category');
    if (category != 'movies') {
        var searchTerm = $('#movie-search').val();
        fullUrl = `${url}/${category}=${searchTerm}&page=${page}`;
    } else {
        fullUrl = `${url}/${category}?page=${page}`;
    }

    console.log(fullUrl);

    $.ajax({
        url: fullUrl,
        type: 'GET',
        success: (result) => {
            fillMovieTable(result);
        },
        error: (thrownError) => {
            alert(thrownError.responseText);
        }
    });
}

function fillMovieTable(r) {
    movies = [];

    var result = r.movieList;
    maxPage = r.pages;


    $('#tb').empty();
    $('#tb').append(`
        <table class="table table-striped" id="movieData">
            <tbody>
                <tr>
                    <th><div class="th">Poster</div></th>
                    <th><div class="th">Title</div></th>
                    <th><div class="th">Year</div></th>
                    <th><div class="th">IMDB Rating</div></th>
                    <th><div class="th">Countries</div></th>
                    <th><div class="th">Director</div></th>
                    <th><div class="th">writers</div></th>
                </tr>
            </tbody>
            <ul class="pagination pagination-sm pull-right" style="margin-right: 6% !important">
                <li><a id="page-back"> << </a></li>
                <li><a>Page: ${page + 1} </a></li>
                <li><a id="page-forward"> >> </a></li>
            </ul>
        </table>
    `);

    var img = [];

    for (var i = 0; i < result.length; i++) {
        img.push(result[i]['poster']);
    }

    var imagesLoaded = 0;
    var totalImages = result.length;

    $('img').each(function(idx, img) {
        $('<img>').on('load', imageLoaded).attr('src', $(img).attr('src'));
    });

    function imageLoaded() {
        imagesLoaded++;
        if (imagesLoaded == totalImages) {
            allImagesLoaded();
        }
    }

    for (var i = 0; i < result.length; i++) {

        movies.push(result[i]);

        var id = result[i]['id'];

        var poster = img[i];
        if (poster == null) {
            poster = '../static/default.jpg';
            movies[i]['poster'] = poster;
        }

        let title = result[i]['title'];
        let year = result[i]['year'];
        let rated = result[i]['imdb'].rating;
        let contries = result[i]['countries'];
        let director = result[i]['director'];
        let writers = result[i]['writers'];
        let type = result[i]['type'];

        $('#movieData tbody').append(`
            <tr id="row" data-id='${id}'>
                <td id="poster" align="center"><img src=" ${poster} " height="100px" width="100px"></td>
                <td id="Title"><div class="tdData"> ${title} </div></td>
                <td id="year"><div class="tdData"> ${year} </div></td>
                <td id="rated"><div class="tdData"> ${rated} </div></td>
                <td id="contries"><div class="tdData"> ${contries} </div></td>
                <td id="director"><div class="tdData"> ${director} </div></td>
                <td id="writers"><div class="tdData"> ${writers} </div></td>
            </tr>
        `);
    }
}

function loadInfo(row) {
    $('#wrapper-info').empty();
    $('#shadow').css('visibility', 'visible');
    $('#wrapper-info').css('visibility', 'visible');

    var selectedMovie;
    var movieName = row.closest('tr').attr('data-id');
    for (var i = 0; i < movies.length; i++) {
        if (movies[i].id == movieName) {
            selectedMovie = movies[i];
        }
    }

    var infoTemplate= `<div class="info-container">`;
    infoTemplate += `
        <div class="info-title"> ${selectedMovie.title} </div>
        <div class="info-poster"><img src=" ${selectedMovie.poster} " height="200px" width="200px"> </div>
    `;

    if (selectedMovie.imdb != null) {
        let imdbResult = selectedMovie['imdb'];
        let imdb = getObjProp(imdbResult);
        if (imdb != '') {
            infoTemplate += `
                <div class="info-txt">
                    <div class="info-txt-title">IMDB</div>
                    <p>${imdb}</p>
                </div>
            `;
        }
    }

    if (selectedMovie.tomato != null) {
        let tomatoResult = selectedMovie['tomato'];
        let tomato = getObjProp(tomatoResult);
        if (tomato != '') {
            infoTemplate += `
                <div class="info-txt">
                    <div class="info-txt-title">Tomato</div>
                    <p>${tomato} </p>
                </div>
            `;
        }
    }

    if (selectedMovie.awards != null) {
        let awardsResult = selectedMovie['awards'];
        let awards = getObjProp(awardsResult);
        if (awards != '') {
            infoTemplate += `
                <div class="info-txt">
                    <div class="info-txt-title">Awards</div>
                    <p>${awards}</p>
                </div>
            `;
        }
    }

    if (selectedMovie.rated != null && selectedMovie.rated != ' ') {
        infoTemplate += `
            <div class="info-txt">
                <div class="info-txt-title">Rated</div>
                <p>${selectedMovie.rated} </p>
            </div>
        `;
    }

    if (selectedMovie.metacritics != null && selectedMovie.metacritics != ' ') {
        infoTemplate += `
            <div class="info-txt">
                <div class="info-txt-title">Metacritics</div>
                <p>${selectedMovie.metacritics}</p>
            </div>
        `;
    }

    if (selectedMovie.year != null && selectedMovie.year != ' ') {
        infoTemplate += `
            <div class="info-txt">
                <div class="info-txt-title">Year</div>
                <p>${selectedMovie.year} </p>
            </div>
        `;
    }

    if (selectedMovie.countries != null && selectedMovie.countries != ' ') {
        infoTemplate += `
            <div class="info-txt">
                <div class="info-txt-title">Countries</div>
                <p>${selectedMovie.countries}</p>
            </div>
        `;
    }

    if (selectedMovie.genres != null && selectedMovie.genres != ' ') {
        infoTemplate += `
            <div class="info-txt">
                <div class="info-txt-title">Genres</div>
                <p>${selectedMovie.genres} </p>
            </div>
        `;
    }

    if (selectedMovie.director != null && selectedMovie.director != ' ') {
        infoTemplate += `
            <div class="info-txt">
                <div class="info-txt-title">Director</div>
                <p>${selectedMovie.director} </p>
            </div>
        `;
    }

    if (selectedMovie.writers != null && selectedMovie.writers.length > 0) {
        infoTemplate += `
            <div class="info-txt">
                <div class="info-txt-title">Writers</div>
                <p>${selectedMovie.writers} </p>
            </div>
        `;
    }

    if (selectedMovie.actors != null && selectedMovie.actors.length > 0) {
        infoTemplate += `
        <div class="info-txt">
            <div class="info-txt-title">Actors</div>
            <p>${selectedMovie.actors} </p>
        </div>
        `;
    }

    if (selectedMovie.plot != null && selectedMovie.plot != ' ') {
        infoTemplate += `
            <div class="info-txt">
                <div class="info-txt-title">Plot</div>
                <p>${selectedMovie.plot} </p>
            </div>
        `;
    }

    if (selectedMovie.type != null && selectedMovie.type != ' ') {
        infoTemplate += `
            <div class="info-txt">
                <div class="info-txt-title">Type</div>
                <p>${selectedMovie.type} </p>
            </div>
        `;
    }

    infoTemplate += '</div>';

    $('.wrapper-info').append(infoTemplate);

    // $('.wrapper-info').append(`
    //     <div class="info-txt-buttons">
    //         <button class="btn btn-primary btn-flat" name="AddUser">Add</button>
    //         <button class="btn btn-danger btn-flat" name="Cancel">Delete</button>
    //     </div>
    // `);

    //console.log(selectedMovie);

}

function getObjProp(obj) {
    var result = '';
    if (obj != null) {
        for (var key in obj) {
            if (obj.hasOwnProperty(key) && obj[key] != null) {
                result += key + ' : ' + obj[key] + '<br>';
            }
        }
    }
    return result;
}

function ChangeDropdown() {
    $('#dropdown-content a').click(function() {
        const txt = $(this).text();
        const atr = $(this).attr('search-category');
        const lastSelectedText = $('#btn-options').text();
        const lastSelectedAtr = $('#btn-options').attr('search-category')

        $('#btn-options').text(txt);
        $('#btn-options').attr('search-category', atr);

        $(this).text(lastSelectedText);
        $(this).attr('search-category', lastSelectedAtr);
    });
}


function eventListeners() {
    $('#shadow').click( function() {
        $(this).css('visibility', 'hidden');
        $('#wrapper-info').css('visibility', 'hidden');
    });

    $(document).on('click', '#row', function(e) {
        loadInfo($(e.target).parent());
    });

    $(document).on('click', '#page-back', function() {
        if (page - 1 >= 0) {
            page--;
            send();
        }
    });

    $(document).on('click', '#page-forward', function() {
        if (page + 1 <= maxPage) {
            page++;
            send();
        }
    });
}

    //<th><div class="th">Runtime</div></th>
    // <th><div class="th">Actors</div></th>
    // <th><div class="th">Plot</div></th>
    // <th><div class="th">Poster</div></th>
    // <th><div class="th">IMDB</div></th>
    // <th><div class="th">Tomato</div></th>
    // <th><div class="th">Metacritic</div></th>
    // <th><div class="th">Awards</div></th>
    // <th><div class="th">Type</div></th>


//'<td id="Runtime"><div class="tdData">' + runtime + '</div></td>' +
//'<td id="actors"><div class="tdData">' + actors + '</div></td>' +
//'<td id="plot"><div class="tdData">' + plot + '</div></td>' +
//'<td id="poster"><div class="tdData">' + poster + '</div></td>' +
//'<td id="imdb"><div class="tdData">' + imdb + '</div></td>' +
//'<td id="tomato"><div class="tdData">' + tomato + '</div></td>' +
//'<td id="metacritic"><div class="tdData">' + metacritic + '</div></td>' +
//'<td id="awards"><div class="tdData">' + awards + '</div></td>' +
//'<td id="type"><div class="tdData">' + type + '</div></td>' +

        //let runtime = result[i]['runtime'];
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
