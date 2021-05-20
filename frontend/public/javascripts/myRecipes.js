$(document).ready(function () {
    const $urlLatestRecipes = 'http://localhost:8080/api/recipe/getRecipes',
        $urlUserValoration = 'http://localhost:8080/api/user/getUserValoration/';

    let $forks;

    let $token, $IDuser;

    if (token() == true) {
        $token = localStorage.getItem('token');
        $IDuser = localStorage.getItem('id');
    }

    $.ajax({
        url: $urlLatestRecipes,
        type: 'GET',
        async: false,
        headers: {
            'Authorization': $token
        },
        success: function ($latestRecipes) {
            $.each($latestRecipes, function ($index, $recipe) {
                if ($recipe.userId == $IDuser) {
                    console.log($recipe)
                    $forks = getForks($recipe.recipeAssessment);
                    $('#recipes').append('<tr value="' + $recipe.id + '">\
                                            <td class="nameRec" ><a href="http://localhost:3000/recetas/ficha?id=' + $recipe.id + '">' + $recipe.name + '</a></td>\
                                            <td><i class="fas fa-pen icon btn_modifyRecipe"></i></td>\
                                            <td><i class="fas fa-trash-alt icon btn_deleteRecipe"></i></td>\
                                            <td>' + $forks + '</td>\
                                        </tr>');
                }
            });
        }
    });

    $('.btn_deleteRecipe').on('click', function () {

        $.ajax({
            url: 'http://localhost:8080/api/recipe/deleteRecipeById/' + $(this).parent().parent().attr('value'),
            type: 'DELETE',
            headers: {
                'Authorization': $token
            },
            success: function () {
                location.reload();
            }
        });
    });

    $.ajax({
        url: $urlUserValoration + $IDuser,
        type: 'GET',
        headers: {
            'Authorization': $token
        },
        success: function ($valoration) {
            $forks = getForks($valoration)
                $('.val').append('<p>Valoración media:</p>\
                                     '+$forks);
        }
    })

    $('.btn_modifyRecipe').on('click', function () {
        window.location = '/crearReceta?id=' + $(this).parent().parent().attr('value');
    });
});