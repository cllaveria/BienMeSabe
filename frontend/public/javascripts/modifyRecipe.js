$(document).ready(function () {
    const $urlRecipe = 'http://localhost:8080/api/recipe/getRecipesById/',
        $idRecipe = (window.location.search).substr(4, );

    console.log($idRecipe);

    // TODO: implementar la validación del token en otro página.

    let $token = localStorage.getItem('token'),
        $IDuser = localStorage.getItem('id');

    if ($token != '') {
        $.ajax({
            url: 'http://localhost:8080/api/recipe/getRecipesOfOtherUsers/' + $IDuser,
            type: 'GET',
            async: false,
            headers: {
                'Authorization': $token
            },
            dataType: 'json',
            contentType: 'aplication/json',
            success: function ($requestToken) {

                $('#login').css('display', 'none');
                $('#register').css('display', 'none');
                $('.btn_user').css('display', 'inline-block');
            },
            error: function ($error) {
                if ($error.responseText == '') {
                    $('#login').css('display', 'inline-block');
                    $('#register').css('display', 'inline-block');
                    $('.btn_user').css('display', 'none');
                }
            }
        });
    }
    // TODO: hasta aquí la implementación del token.

    $.ajax({
        url: $urlRecipe + $idRecipe,
        type: 'GET',
        async: false,
        headers: {
            'Authorization': $token
        },
        success: function ($recipe) {
            console.log($recipe)
            $('#nameRecipe').html($recipe.name);
            $('#recipeInitDescription').html($recipe.recipeInitDescription);
            $('#recipeEndingDescription').html($recipe.recipeEndingDescription);

        }
    });
});