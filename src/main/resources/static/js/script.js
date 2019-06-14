//array with all movies on the page
let movies = [];
//when movie is clicked, its assign to this variable
let selectedMovie;
//current page
let page = 0;
//max pages for this search
let maxPage = Infinity;
//default url path
const url = `http://localhost:8080`;
//switch for editing
let editFlag = 1;
//search term
let searchTerm;


//call functions when page is open for the first time
window.onload = () => {
    ChangeDropdown();
    eventListeners();
    getMovies();
}

//GET requests
function getMovies() {

    var fullUrl;

    var sort = $('#checkboxRating:checked').val();
    var category = $('#btn-options').attr('search-category');
    if ($('#movie-search').val() != searchTerm) {
        searchTerm = $('#movie-search').val();
        page = 0;
    }

    if (category != 'movies' && searchTerm.length != 0) {
        if (sort == 'on') {
            fullUrl = `${url}/${category}=${searchTerm}&sorted=1&page=${page}`;
        } else {
            fullUrl = `${url}/${category}=${searchTerm}&page=${page}`;
        }
    } else {
        if (sort == 'on') {
            fullUrl = `${url}/movies?sorted=1&page=${page}`;
        } else {
            fullUrl = `${url}/movies?page=${page}`;
        }
    }

    //console.log(fullUrl);

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

//filling movie table
function fillMovieTable(r) {
    movies = [];

    var movieList = r.movieList;
    maxPage = r.pages;

    $('#tb').empty();
    $('#tb').append(`
        <table class="table table-striped" id="movieData">
            <tbody>
                <tr>
                    <th><div class="th">Poster</div></th>
                    <th><div class="th">Title</div></th>
                    <th><div class="th">Year</div></th>
                    <th onclick="sortByRating()"><div class="th">IMDB Rating</div></th>
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

    var img = loadImages(movieList);

    for (var i = 0; i < movieList.length; i++) {
        movies.push(movieList[i]);

        var id = movieList[i]['id'];

        var poster = img[i];
        if (poster == null) {
            poster = '../static/default.jpg';
            movies[i]['poster'] = poster;
        }

        let title = movieList[i]['title'];
        let year = movieList[i]['year'];
        let rated = null;
        if (movieList[i]['imdb'] != null) {

        if(movieList[i]['imdb'].rating===null){

        rated="N.A.";

        }else{

            rated = movieList[i]['imdb'].rating;}
        }
        let contries = movieList[i]['countries'];
        let director = movieList[i]['director'];
        let writers = movieList[i]['writers'];
        let type = movieList[i]['type'];

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

    var movieName = row.closest('tr').attr('data-id');
    for (var i = 0; i < movies.length; i++) {
        if (movies[i].id == movieName) {
            selectedMovie = movies[i];
        }
    }

    var infoTemplate= `<div class="info-container">`;
    infoTemplate += `
        <div class="info-title"><p class=" info-p"> ${selectedMovie.title} </p></div>
        <textarea class="info-input" cols="50" row="1"> ${selectedMovie.title} </textarea>
        <div class="info-poster info-p"><img src=" ${selectedMovie.poster} " height="200px" width="200px"> </div>
        <textarea class="info-input" cols="50" row="1"> ${selectedMovie.poster} </textarea>
    `;

    if (selectedMovie.imdb != null) {
        let imdbResult = selectedMovie['imdb'];
        let imdb = getObjProps(imdbResult);
        if (imdb != '<div class="input-txt"></div>') {
            infoTemplate += `
                <div class="info-txt">
                    <div class="info-txt-title">IMDB</div>
                    ${imdb}
                    <textarea cols="15" rows="2" class="info-input">${imdb}</textarea>
                </div>
            `;
        }
    }

    if (selectedMovie.tomato != null) {
        let tomatoResult = selectedMovie['tomato'];
        let tomato = getObjProps(tomatoResult);
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
        let awards = getObjProps(awardsResult);
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

    $('.wrapper-info').append(`
        <div class="info-txt-buttons">
            <button class="btn btn-primary btn-flat" onclick="editMovie()">Edit</button>
            <button class="btn btn-danger btn-flat" onclick="deleteMovie()">Delete</button>
        </div>
    `);

}

function loadImages(movieList) {
    var img = [];
    for (var i = 0; i < movieList.length; i++) {
        img.push(movieList[i]['poster']);
    }

    var imagesLoaded = 0;
    var totalImages = movieList.length;

    $('img').each(function(idx, img) {
        $('<img>').on('load', imageLoaded).attr('src', $(img).attr('src'));
    });

    function imageLoaded() {
        imagesLoaded++;
        if (imagesLoaded == totalImages) {
            allImagesLoaded();
        }
    }
    return img;
}

function deleteMovie() {

    var movieId = selectedMovie.id;

    var fullUrl = url + `/movies?id=${movieId}`;

    //console.log(fullUrl);

    $.ajax({
        url: fullUrl,
        type: 'DELETE',
        success: (result) => {
            getMovies();
            hideInfoMovie()
        }
    });
}

function getObjProps(obj) {
    var result = '<div class="input-txt">';
    if (obj != null) {
        for (var key in obj) {
            if (obj.hasOwnProperty(key) && obj[key] != null) {
                result += `<div>${key}:${obj[key]}</div>`;
            }
        }
    }
    result += '</div>';
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
        page = 0;
    });

}

function eventListeners() {
    $('#shadow').click( function() {
        hideInfoMovie();
    });

    $(document).on('click', '#row', function(e) {
        loadInfo($(e.target).parent());
    });

    $(document).on('click', '#page-back', function() {
        if (page - 1 >= 0) {
            page--;
            getMovies();
        }
    });

    $(document).on('click', '#page-forward', function() {
        if (page + 1 < maxPage) {
            page++;
            getMovies();
        }
    });
}

function hideInfoMovie() {
    $('#shadow').css('visibility', 'hidden');
    $('#wrapper-info').css('visibility', 'hidden');
    //editMovie();
    selectedMovie = null;
}

function editMovie() {
    // console.log(editFlag);
    // if (editFlag == -1) {
    //     $('.info-p').css('visibility', 'visible');
    //     $('.info-p').css('display', 'inline');
    //     $('.info-input').css('visibility', 'hidden');
    //     $('.info-input').css('display', 'none');
    // } else {
    //     $('.info-p').css('visibility', 'hidden');
    //     $('.info-p').css('display', 'none');
    //     $('.info-input').css('visibility', 'visible');
    //     $('.info-input').css('display', 'inline');
    // }
    // editFlag *= -1;
}
