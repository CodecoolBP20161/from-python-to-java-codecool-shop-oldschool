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
        /* Get iframe src attribute value i.e. YouTube video url
         and store it in a variable */
        var url = $("#productVideo").attr('src');

        /* Assign empty url value to the iframe src attribute when
         modal hide, which stop the video playing */
        $("#videoModal").on('hide.bs.modal', function () {
            $("#productVideo").attr('src', '');
        });

        /* Assign the initially stored url back to the iframe src
         attribute when modal is displayed again */
        $("#productVideo").on('show.bs.modal', function () {
            $("#productVideo").attr('src', url);
        });
    });
})

function insertEmbedCode(res) {
    console.log(res)
    $('.modal-body').html(res)
}
