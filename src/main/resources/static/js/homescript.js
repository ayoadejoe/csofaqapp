function showSelectedText() {
    const selectedItem = document.getElementById("menu").value;
    const selectedItemText = document.getElementById("menu").options[
        document.getElementById("menu").selectedIndex
        ].text;
    const selectedTextElement = document.getElementById("selectedItem");

    if (selectedItem) {

    var regulator = $('#menu').val();
    $.get("/regulators/" + encodeURIComponent(regulator), function(data) {
        $(selectedTextElement).html(data);
    }).fail(function(xhr, ajaxOptions, thrownError) {
        alert(xhr.status);
        alert(thrownError);
    });
        document.querySelector(".selected-text").style.display = "block";
    } else {
        selectedTextElement.innerText = "";
        document.querySelector(".selected-text").style.display = "none";
    }
}


