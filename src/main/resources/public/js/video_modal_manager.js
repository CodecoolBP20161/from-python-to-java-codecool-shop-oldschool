$(document).ready(function () {
    $('a[name="videoModal"]').click(function () {
        var id = $(this).attr("id")
        $.ajax({
            url: '/video/' + id,
            type: 'GET',
            success: insertEmbedCode,
            error: function (error) {
                console.log(error);
            }

        })

        /* Assign empty url value to the iframe src attribute when
         modal hide, which stop the video playing */
        $("#videoModal").on('hide.bs.modal', function () {
            $('.modal-body').html("Loading")
        });


    });
})

function insertEmbedCode(res) {
    console.log(res)
    $('.modal-body').html(res)
}
