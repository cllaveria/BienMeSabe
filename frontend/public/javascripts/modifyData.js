$(document).ready(function () {

    $.ajax({
        url: 'http://localhost:8080/api/user/findUserByAlias/SergioAsensio',
        type: 'GET',
        success: function($user){
            console.log($user);

            $('#name').html($user.name);
            $('#surname').html($user.surname)
            
        }
    })
});