$(document).ready(function() {

    $('form').on('submit', function(event) {
        console.log($('input[name=email]').val());
        event.preventDefault();
        var formData = {
            email: $('input[name=email]').val(),
            question: $('input[name=question]').val()
        };
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/submitform',
            data: formData,
            success: function(data) {
                alert('Form submitted successfully!');
            },
            error: function(xhr, status, error) {
                alert('Form submission failed: ' + error);
            }
        });
    });
});
