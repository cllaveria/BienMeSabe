$(document).ready(function () {

    // Toggles the sidebar

    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
        $(".list-group-item").toggleClass("toggled");
        $(".noToggle").toggleClass("noShow");
        $(".siToggle").toggleClass("noShow");

    });

});