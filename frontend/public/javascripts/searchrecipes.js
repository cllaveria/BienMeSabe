/**
 * @fileoverview Cerca de receptes personalitzat: 
 * <p> Mostra per pantalla el resultat de la cerca personalitzada.</p>
 * <p> Mostra per pantalla el resultat de la cerca per tipus de plat.</p>
 * 
 * <p> History</p>
 * 0.1 - Implementació de la cerca de receptes.
 * 0.2 - Implementació de la visualització de mes receptes per tipus de plat.  
 *  
 * @version     0.2
 * @author      Sergio Asensio Ruiz 
 * @copyright   bienmesabe.com
 * 
 */

$(document).ready(function () {

    const $urlSearch = 'http://localhost:8080/api/recipe/getRecipesByFilters/';
    const $urlSearchPlate = 'http://localhost:8080/api/recipe/getRecipesByType/';
    const $urlAllUsers = 'http://localhost:8080/api/user/getUsers';
    const $urlTypePlate = 'http://localhost:8080/api/recipeTypes/getRecipeTypes';
    const $urlLatestRecipes = 'http://localhost:8080/api/recipe/getRecipes';
    const $urlRecipe = 'http://localhost:3000/recetas/ficha?id=';

    let $searchFilters = (window.location.search).substr(1, );
    let $searchId = (window.location.search).substr(1, 3);
    let $searchLatestRecipes = (window.location.search).substr(1, 13);
    let $allUsers = [];
    let $allTypePlate = [];
    let $typePlate;
    let $userAlias;

    $.ajax({
        url: $urlAllUsers,
        type: 'GET',
        async: false,
        success: function ($users) {
            for (let i = 0; i < $users.length; i++) {
                $allUsers[i] = $users[i];
            }
        }
    });

    $.ajax({
        url: $urlTypePlate,
        type: 'GET',
        async: false,
        success: function ($plate) {
            for (let d = 0; d < $plate.length; d++) {
                $allTypePlate[d] = $plate[d];
            }
        }
    })

    
    if ($searchId == 'id=') {
        $.ajax({
            url: $urlSearchPlate + (window.location.search).substr(4, 5),
            type: 'GET',
            async: false,
            success: function ($recipes) {
                insertRecipes($recipes);
            }
        })
    } else if($searchLatestRecipes == 'latestRecipes'){
        $.ajax({
            url: $urlLatestRecipes,
            type: 'GET',
            async: false,
            success: function($recipes){
                insertRecipes($recipes);
            }
        })
    }else {
        $.ajax({
            url: $urlSearch + $searchFilters,
            type: 'GET',
            async: false,
            success: function ($recipes) {
                insertRecipes($recipes)
            }
        });
    }

    function insertRecipes($recipes) {
        for (let i = 0; i < $recipes.length; i++) {
            for (let j = 0; j < $allUsers.length; j++) {
/*                 if ($allUsers[j].id == $recipes[i].userId) {
                    $userAlias = $allUsers[j].alias;
                } */
                //TODO: BORRAR LO COMENTADO
                if ( /* $allUsers[j].id */ $allUsers[j][0] == $recipes[i].userId) {
                    $userAlias = /* $allUsers[j].alias */ $allUsers[j][4];
                }
            }
            for (let x = 0; x < $allTypePlate.length; x++) {
                if ($allTypePlate[x].id == $recipes[i].type) {
                    $typePlate = $allTypePlate[x].name;
                    if(i == 0){
                        if ((window.location.search).substr(1, 3) == 'id=') {
                            $('.recipes_container').prepend('<div class="tl_typePlate tl_' + $typePlate + '" >' + $typePlate + '</div>')
                        }
                    }
                }
            }
            let $forks = getForks($recipes[i].recipeAssessment);
            let $difficult = getDifficult($recipes[i].recipeDifficult);
            receivePlate($recipes[i], $userAlias, $forks, $difficult, $typePlate);
        }
    }

    function receivePlate($recipe, $userAlias, $forks, $difficult, $classPlate) {
        $('.recipes_cont').append('<div class="rcp_cnt">\
                                    <a href="' + $urlRecipe + $recipe.id + '">\
                                        <div class="recipe ' + $classPlate + '">\
                                            <img src="' + $recipe.image + '" alt="' + $classPlate + '" style="width: 100%;">\
                                            <div class="desc_rec">\
                                                <h3 id="title">' + $recipe.name + '</h3>\
                                                <p id="author">' + $userAlias + '</p>\
                                            </div>\
                                        </div>\
                                        <div class="info_rec">\
                                            <p id="level">Dificultad: ' + $difficult + '</p>\
                                            <div class="time_rec">\
                                                <i class="fas fa-clock clock"></i>\
                                                <p id="time">' + $recipe.recipeTime + ' min</p>\
                                            </div>\
                                        </div>\
                                        ' + $forks + '\
                                        </a>\
                                    </div>');
    }

    function getForks($forks) {
        let $insertForks = '<div class="score_rec">';
        for (let i = 0; i < $forks; i++) {
            $insertForks = $insertForks.concat('<img src="/images/tenedor-gold.svg" alt="tenerdor" style="width: 20px; height: 40px;">');
        }
        if ($forks != 5) {
            let $numberForks = 5 - $forks;
            for (let i = 0; i < $numberForks; i++) {
                $insertForks = $insertForks.concat('<img src="/images/tenedor-black.svg" alt="tenerdor" style="width: 20px; height: 40px;">');
            }
        }
        $insertForks = $insertForks.concat('</div>');
        return $insertForks;
    }

    function getDifficult($dificult) {
        let $insertDificult = '';
        switch ($dificult) {
            case 0:
                return $insertDificult = 'muy baja';
            case 1:
                return $insertDificult = 'baja';
            case 2:
                return $insertDificult = 'media';
            case 3:
                return $insertDificult = 'difícil';
            case 4:
                return $insertDificult = 'muy difícil';
        }
    }
});