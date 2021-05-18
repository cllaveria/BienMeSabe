$(document).ready(function () {
    const $urlLatestRecipes = 'http://localhost:8080/api/recipe/getRecipes';

    let $forks;
    
    let $token, $IDuser;
    let $result = token()
    console.log($result)
    if(token() == true){
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
                    $('#recipes').append('<tr>\
                                            <td class="nameRec" value="' + $recipe.id + '"><a href="http://localhost:3000/recetas/ficha?id=' + $recipe.id + '">' + $recipe.name + '</a></td>\
                                            <td><i class="fas fa-pen icon"></i></td>\
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

    $('.btn_modifyRecipe').on('click', function () {
        // TODO: Cuenado se creen las páginas correspondientes, modificar el enlace.
        window.location = '/panelUsuario/modificarReceta?id=' + $(this).parent().parent().attr('value');
    });
});