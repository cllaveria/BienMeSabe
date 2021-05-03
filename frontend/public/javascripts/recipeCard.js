$(document).ready(function () {

    const $receivedData = (window.location.search).substr(1, );
    const $urlRecipe = 'http://localhost:8080/api/recipe/getRecipesById/';
    const $urlIngredients = 'http://localhost:8080/api/ingredient/getIngredients';
    const $urlComments = 'http://localhost:8080/api/comment/getCommentsByRecipeId/';
    const $urlAllUsers = 'http://localhost:8080/api/user/getUsers';
    const $urlAddComment = 'http://localhost:8080/api/comment/addComment/';
    const $urlAddAssessment = 'http://localhost:8080/api/assessment/addAssessment/';
    const $arrayRecipe = $receivedData.split('_');
    const $idRecipe = $arrayRecipe[0].substr(3, );
    let $totalDinner,
        $collapse,
        $heading,
        $carbohidrates = 0,
        $proteins = 0,
        $fat = 0,
        $satured = 0,
        $monoinsaturated = 0,
        $polyinsaturated = 0,
        $sugars = 0,
        $fiber = 0,
        $sodium = 0,
        $booleanComment = true,
        $booleanAssessment = true,
        $allUsers = [],
        $score = '',
        $comment = '';

    if ($arrayRecipe.length > 1) {
        $totalDinner = $arrayRecipe[1].substr(7, );
        if ($totalDinner == '') {
            $totalDinner = 1;
        }
    } else {
        $totalDinner = 1;
    }

    $.ajax({
        url: $urlAllUsers,
        type: 'GET',
        async: false,
        success: function ($users) {
            for (let i = 0; i < $users.length; i++) {
                $allUsers.push($users[i]);
            }
        }
    });

    $.ajax({
        url: $urlComments + $idRecipe,
        type: 'GET',
        success: function ($comments) {
            for (let i = 0; i < $comments.length; i++) {
                for (let x = 0; x < $allUsers.length; x++) {
                    if ($comments[i].userId == $allUsers[x][0]) {
                        $('.comments_users').append('<div class="comment_user">\
                                                        <p id="alias">' + $allUsers[x][4] + '</p>\
                                                        <p id="comment">' + $comments[i].commentValue + '</p>')
                    }
                }
            }
        }
    });

    $.ajax({
        url: $urlRecipe + $idRecipe,
        type: 'GET',
        success: function ($recipe) {
            console.log($recipe)
            $('.title').html($recipe.name);
            $('.title').after('<img class="imgRec" src="' + $recipe.image + '" alt="Imagen receta">');
            let $forks = getForks($recipe.recipeAssessment);
            let $difficult = getDifficult($recipe.recipeDifficult);
            $('.alias_cont').append('<h4 class="alias">Alias usuario</h4>');
            $('.alias_cont').append($forks);
            $('.init').find('p').append($recipe.recipeInitDescription);
            $('.ending').find('p').append($recipe.recipeEndingDescription);
            if ($arrayRecipe.length > 1) {
                $('#dinner').html('Receta para ' + $totalDinner + ' personas');
            } else {
                $('#dinner').html('Receta para ' + $totalDinner + ' persona');
            }
            $('#level').html('Dificultad: ' + $difficult);
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
                return $insertDificult = 'Muy baja';
            case 1:
                return $insertDificult = 'Baja';
            case 2:
                return $insertDificult = 'Media';
            case 3:
                return $insertDificult = 'Difícil';
            case 4:
                return $insertDificult = 'Muy difícil';
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
                            console.log($ingredients[i])
                            $carbohidrates += $ingredients[i].carbohidrates;
                            $fat += $ingredients[i].fat;
                            $fiber += $ingredients[i].fiber;
                            $monoinsaturated += $ingredients[i].monoinsaturatedFats;
                            $polyinsaturated += $ingredients[i].polyinsaturatedFats;
                            $proteins += $ingredients[i].proteins;
                            $satured += $ingredients[i].saturedFats;
                            $sodium += $ingredients[i].sodium;
                            $sugars += $ingredients[i].sugars;

                            if ($ingredientsRecipe[x].ingredientUnity == 'U') {
                                $('.ingredient').append('<li>' + ($ingredientsRecipe[x].ingredientQTY * $totalDinner) + ' unidades de ' + $ingredients[i].name + '</li>');
                            } else {
                                $('.ingredient').append('<li>' + ($ingredientsRecipe[x].ingredientQTY * $totalDinner) + 'gr de ' + $ingredients[i].name + '</li>');
                            }
                        }
                    }
                }
                $('.caloricTable').append('<tr>\
                                                <td>Carbohidratos:</td>\
                                                <td>' + $carbohidrates.toFixed(2) + '</td>\
                                            </tr>\
                                            <tr>\
                                                <td>Proteínas:</td>\
                                                <td>' + $proteins.toFixed(2) + '</td>\
                                            </tr>\
                                            <tr>\
                                                <td>Grasas:</td>\
                                                <td>' + $fat.toFixed(2) + '</td>\
                                            </tr>\
                                            <tr>\
                                                <td>De las cuales saturadas:</td>\
                                                <td>' + $satured.toFixed(2) + '</td>\
                                            </tr>\
                                            <tr>\
                                                <td>De las cuales monosaturadas:</td>\
                                                <td>' + $monoinsaturated.toFixed(2) + '</td>\
                                            </tr>\
                                            <tr>\
                                                <td>De las cuales Poliinsaturadas:</td>\
                                                <td>' + $polyinsaturated.toFixed(2) + '</td>\
                                            </tr>\
                                            <tr>\
                                                <td>Azucares:</td>\
                                                <td>' + $sugars.toFixed(2) + '</td>\
                                            </tr>\
                                            <tr>\
                                                <td>Fibra:</td>\
                                                <td>' + $fiber.toFixed(2) + '</td>\
                                            </tr>\
                                            <tr>\
                                                <td>Sodio:</td>\
                                                <td>' + $sodium.toFixed(2) + '</td>\
                                            </tr>'
                                            );
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
                                                    <img src = "' + $stepsRecipe[i].image + '" alt = "paso ' + [i + 1] + '" >\
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
                                                    <img src = "' + $stepsRecipe[i].image + '" alt = "paso ' + [i + 1] + '" >\
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


    $('.forkValue').each(function () {
        $(this).on('click', () => {
            $score = $(this).attr('value');
            switch ($score) {
                case '1':
                    insertForks($score);
                    break;
                case '2':
                    insertForks($score);
                    break;
                case '3':
                    insertForks($score);
                    break;
                case '4':
                    insertForks($score);
                    break;
                case '5':
                    insertForks($score);
                    break;
            }
        });
    });

    function insertForks($score) {
        let $arrayForks = []
        $('.forkValue').each(function () {
            $(this).attr('src', '/images/tenedor-black.svg')
            $arrayForks.push($(this));
        })
        $arrayForks.reverse()
        for (let i = 0; i < $score; i++) {
            $arrayForks[i].attr('src', '/images/tenedor-gold.svg')
        }
    }

    $('.button').on('click', () => {
        $comment = $('#textComment').val();

        if ($comment != '' || $score != '') {

            if ($comment != '') {
                $.ajax({
                    //TODO: Falta añadir el ID del usuario registrado que comenta.
                    url: $urlAddComment + $idRecipe + '___' + $comment + '___3',
                    type: 'POST',
                    async: false,
                    success: function ($insertComment) {
                        if ($insertComment == '') {
                            $booleanComment = false;
                            console.log('ya comentaste esta receta');
                        } else {
                            $booleanComment = true;
                        }
                    }
                });
            }

            if ($score != '') {
                $.ajax({
                    //TODO: Falta añadir el ID del usuario que puntua.
                    url: $urlAddAssessment + $idRecipe + '___' + $score + '___3',
                    type: 'POST',
                    async: false,
                    success: function ($score) {
                        if ($score == '') {
                            $booleanAssessment = false;
                            console.log('ya puntuaste esta receta');
                        } else {
                            $booleanAssessment = true;
                        }
                    }
                });
            }
            if ($booleanComment == true && $booleanAssessment == true) {
                location.reload();
            }
        } else {
            console.log('no se comenta ni se hace ninguna valoración.');
        }
    });
});