$(document).ready(function () {

    const $urlModifyDataUser = ' http://localhost:8080/api/user/';
    let $user;
    let $responseAlias, $responseEmail;

    $.ajax({
        url: 'http://localhost:8080/api/user/getUserById/3',
        type: 'GET',
        async: false,
        success: function ($userAjax) {
            $user = $userAjax;
        }
    });

    $('#name').html($user.name);
    $('#surname').html($user.surname);
    $('#alias').html($user.alias);
    $('#email').html($user.email);


    $('#submit').on('click', () => {

        if ($('#input_alias').val() != '') {
            $.ajax({
                url: $urlModifyDataUser + 'updateUserAlias/userId---' + $user.id + '___alias---' + $('#input_alias').val(),
                type: 'PUT',
                success: function ($response) {
                    $responseAlias = $response;
                }
            });
        }else{
            $responseAlias = true;
        }

        if ($('#input_email').val() != '') {
            $.ajax({
                url: $urlModifyDataUser + 'updateUserEmail/iserId---' + $user.id + '___email---' + $user.email + ',,,' + $('#input_email').val(),
                type: 'PUT',
                success: function ($response) {
                    $responseEmail = $response;
                }
            })
        }else{
            $responseEmail = true;
        }

        if($responseEmail == true && $responseAlias == true){
            alert('los datos se actualizaron correctamente');
        }else{
            if($responseAlias != true){
                alert('')
            }
        }
        /* updateUserEmail/userId---1___email---nsfratu@hotmail.com,,,starlord.rrc@gmail.com
updateUserPassword/userId---1___password---4321,,,1234 */
    });
});