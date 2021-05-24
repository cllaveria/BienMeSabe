$(document).ready(function () {

    //Cambiar clase segun la pagina del panel de usuario
    var url = window.location.href;
    var lastSlash = url.lastIndexOf("/");
    var endpoint = url.substr(lastSlash, url.length);

    switch (endpoint) {
        case "/panelAdministrador":
            $("#administrator").addClass("checked");
            break;
        case "/panelUsuario":
            $("#conf").addClass("checked");
            break;
        case "/misRecetas":
            $("#myRec").addClass("checked");
            break;
        case "/panelNutri":
            $("#nutri").addClass("checked");
            break;
    }


    // Toggles the sidebar
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
        $(".list-group-item").toggleClass("toggled");
        $(".noToggle").toggleClass("noShow");
        $(".siToggle").toggleClass("noShow");

    });

});