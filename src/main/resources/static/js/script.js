function send() {

    var url = window.location.href;

    $.ajax({
        url: url + 'movies',
        type: 'GET',
        success: (result) => {
            fillMovieTable(result);
        }
    });
}

function fillMovieTable(result) {
    $('#tb').empty();
    $('#tb').append(`
        <section class="content-header">Users</section>
        <table class="table table-striped" id="usersData">
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
        <button class="btn btn-block btn-success btn-flat" name="AddRow" style="margin: 10px; width: 100px; display: inline-block">Add User</button>
        <ul class="pagination pagination-sm no-margin pull-right" style="margin-right: 6% !important">
            <li><a href="${urlBackLink}" id="page-back"> << </a></li>
            <li><a>Page: ${page}</a></li>
            <li><a href="${urlForwardLink}" id="page-forward"> >> </a></li>
        </ul>
    `);
}
