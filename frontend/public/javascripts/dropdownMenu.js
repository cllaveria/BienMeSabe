$(document).ready(function () {
    $(".btn_user").click(function (e) {
        e.preventDefault();
        console.log("hola");
        $(".dropdownMenu").toggleClass("noShowdropdown");
        $(".dropdownMenuResp").toggleClass("noShowdropdown");
    });
});