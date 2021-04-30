$(document).ready(function () {

    const $receivedData = (window.location.search).substr(1, );
    const $urlRecipe = 'http://localhost:8080/api/recipe/getRecipesById/';
    const $urlIngredients = 'http://localhost:8080/api/ingredient/getIngredients';
    const $arrayRecipe = $receivedData.split('_');
    const $idRecipe = $arrayRecipe[0].substr(3, );
    let $totalDinner, $collapse, $heading;

    if ($arrayRecipe.length > 1) {
        $totalDinner = $arrayRecipe[1].substr(7, );
    }

    $.ajax({
        url: $urlRecipe + $idRecipe,
        type: 'GET',
        success: function ($recipe) {
            console.log($recipe)
            $('.title').html($recipe.name);
            $('.title').append('<img src="' + $recipe.image + '" alt="Imagen receta" style="width:75%">');
            let $forks = getForks($recipe.recipeAssessment);
            let $difficult = getDifficult($recipe.recipeDifficult);
            $('.title').after($forks);
            if ($arrayRecipe.length > 1) {
                $('#dinner').html('Receta para ' + $totalDinner + ' personas');
            } else {
                $('#dinner').html('Receta para 1 persona');
            }
            $('#level').html('dificultad ' + $difficult);
            $('#time').html($recipe.recipeTime + ' min')

            insertIngredients($recipe.recipeIngredients);
            insertSteps($recipe.recipeSteps);
        }
    });

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

    function insertIngredients($ingredientsRecipe) {
        $.ajax({
            url: $urlIngredients,
            type: 'GET',
            success: function ($ingredients) {
                for (let i = 0; i < $ingredients.length; i++) {
                    for (let x = 0; x < $ingredientsRecipe.length; x++) {
                        if ($ingredients[i].id == $ingredientsRecipe[x].ingredientId) {
                            if ($ingredientsRecipe[x].ingredientUnity == 'unit') {
                                $('.ingredient').append('<li>' + $ingredientsRecipe[x].ingredientQTY + ' unidades de ' + $ingredients[i].name + '</li>');
                            } else {
                                $('.ingredient').append('<li>' + $ingredientsRecipe[x].ingredientQTY + 'gr de ' + $ingredients[i].name + '</li>');
                            }
                        }
                    }
                }
            }
        });
    }

    function insertSteps($stepsRecipe) {
        for (let i = 0; i < $stepsRecipe.length; i++) {
            assingCollapseHeading(i);
            if (i == 0) {
                $('#accordion').append('<div class="card">\
                                            <div class="card-header" id="' + $heading + '">\
                                                <h5 class="mb-0">\
                                                    <button class="btn" data-toggle="collapse" data-target="#' + $collapse + '"\
                                                        aria-expanded="true" aria-controls="' + $collapse + '">\
                                                        Paso ' + [i + 1] + '\
                                                    </button>\
                                                </h5>\
                                            </div>\
                                            <div id="' + $collapse + '" class="collapse show" aria-labelledby="' + $heading + '"\
                                                data-parent="#accordion">\
                                                <div class="card-body">\
                                                    <p> ' + $stepsRecipe[i].stepDescription + ' </p>\
                                                    <img src = "/images/maxresdefault.jpg" alt = "paso 1" >\
                                                </div>\
                                            </div>\
                                        </div>');
            } else {
                $('#accordion').append('<div class="card">\
                                            <div class="card-header" id="' + $heading + '">\
                                                <h5 class="mb-0">\
                                                    <button class="btn collapsed" data-toggle="collapse" data-target="#' + $collapse + '"\
                                                        aria-expanded="false" aria-controls="' + $collapse + '">\
                                                        Paso ' + [i + 1] + '\
                                                    </button>\
                                                </h5>\
                                            </div>\
                                            <div id="' + $collapse + '" class="collapse" aria-labelledby="' + $heading + '"\
                                                data-parent="#accordion">\
                                                <div class="card-body">\
                                                    <p> ' + $stepsRecipe[i].stepDescription + ' </p>\
                                                    <img src = "/images/maxresdefault.jpg" alt = "paso 1" >\
                                                </div>\
                                            </div>\
                                        </div>');
            }
        }

    }

    function assingCollapseHeading($number) {
        switch ($number) {
            case 0:
                $collapse = 'collapseOne';
                $heading = 'headingOne';
                break;
            case 1:
                $collapse = 'collapseTwo';
                $heading = 'headingTwo';
                break;
            case 2:
                $collapse = 'collapseThree';
                $heading = 'headingThree';
                break;
            case 3:
                $collapse = 'collapseFour';
                $heading = 'headingFour';
                break;
            case 4:
                $collapse = 'collapseFive';
                $heading = 'headingFive';
                break;
            case 5:
                $collapse = 'collapseSix';
                $heading = 'headingSix';
                break;
            case 6:
                $collapse = 'collapseSeven';
                $heading = 'headingSeven';
                break;
            case 7:
                $collapse = 'collapseEight';
                $heading = 'headingEight';
                break;
            case 8:
                $collapse = 'collapseNine';
                $heading = 'headingNine';
                break;
            case 9:
                $collapse = 'collapseTen';
                $heading = 'headingTen';
                break;
        }
    }
});