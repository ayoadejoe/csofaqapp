$(document).ready(function() {
    $('#myForm').submit(function(e) {
        e.preventDefault(); // prevent the form from submitting normally

        // get the form data
        var formData = {
            'email': $('input[name=email]').val(),
            'question': $('textarea[name=question]').val()
        };

        // submit the form via AJAX
        $.ajax({
            type: 'POST',
            url: '/submitform',
            data: formData,
            dataType: 'json',
            encode: true
        })
            .done(function(data) {
                // show a success message
                alert(data.message);
            })
            .fail(function(xhr, status, error) {
                // show an error message
                alert('Form submission failed: ' + error);
            });
    });
});
