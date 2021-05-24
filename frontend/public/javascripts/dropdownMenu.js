$(document).ready(function () {
    $(".btn_user").click(function (e) {
        e.preventDefault();
        $(".dropdownMenu").toggleClass("noShowdropdown");
        $(".dropdownMenuResp").toggleClass("noShowdropdown");
    });
});