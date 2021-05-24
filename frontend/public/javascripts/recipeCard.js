/**
 * @fileoverview · Visualització de les dades de la recepta: 
 * <p> 1) Visualització de la imatge de la recepta.</p>
 * <p> 2) Visualització de l'usuari que ha creat la recepta.</p>
 * <p> 3) Visualització de la puntuació de la recepta.</p>
 * <p> 4) Visualització de la descripció de la recepta.</p>
 * <p> 5) Visualització dels ingredients de la recepta.</p>
 * <p> 6) Visualització de la taula kalorica de la recepta.</p>
 * <p> 7) Visualització del temps de la recepta.</p>
 * <p> 8) Visualització per a cuantes persones está feta la recepta.</p>
 * <p> 9) Visualització dels passos a realitzar per ver la recepta.</p>
 * <p> 10) Visualització dels comentaris dels usuaris que conté la recepta.</p>
 * <p> · Si l'usuari está registrat, aquest podrá valorar la recepta amb una puntuació de 1 a 5.</p>
 * <p> · Si l'usuari está registrat, aquest podrá comentar la recepta.</p>
 * 
 * <p> History</p>
 * <p> 0.1 - Implementació de la visualitzacióde la recepta + comentar i valorar.</p>
 *  
 * @version     0.1
 * @author      Sergio Asensio Ruiz 
 * @copyright   bienmesabe.com
 * 
 */

$(document).ready(function () {
    /**
     * @constant $receivedData
     * @type {String}
     * @description Constant per emmagatzemar la ruta rebuda per URL.
     */
    const $receivedData = (window.location.search).substr(1, );
    /**
     * @constant $urlRecipe 
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor veure la fitxa de les receptes.
     */
    const $urlRecipe = 'http://localhost:8080/api/recipe/getRecipesById/';
    /**
     * @constant $urlIngredients 
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor i recuperar tots els ingredients.
     */
    const $urlIngredients = 'http://localhost:8080/api/ingredient/getIngredients';
    /**
     * @constant $urlAllUsers 
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor i recuperar tots els usuaris.
     */
    const $urlAllUsers = 'http://localhost:8080/api/user/getUsers';
    /**
     * @constant $urlAddComment 
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor i poder comentar les receptes.
     */
    const $urlAddComment = 'http://localhost:8080/api/comment/addComment/';
    /**
     * @constant $urlAddAssessment 
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor i poder valorar les receptes.
     */
    const $urlAddAssessment = 'http://localhost:8080/api/assessment/addAssessment/';
    /**
     * @constant $urlModifyAssessment 
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor i poder modificar la valoració de les receptes.
     */
    const $urlModifyAssessment = 'http://localhost:8080/api/assessment/modifyAssessment/';
    /**
     * @constant $arrayRecipe
     * @type {Array}
     * @description Constant per emmagatzemar en forma d'array l'ID de la recepta i els comensals rebuts per URL.
     */
    const $arrayRecipe = $receivedData.split('_');
    /**
     * @constant $idRecipe
     * @type {number}
     * @description Constant per emmagatzemar l'ID de la recepta.
     */
    const $idRecipe = $arrayRecipe[0].substr(3, );
    /** 
     * @var $totalDinner 
     * @type {number}
     * @description Variable per emmagatzemar els comensals de l'array.
     */
    let $totalDinner;
    /** 
     * @var $collapse 
     * @type {String}
     * @description Variable per emmagatzemar el tipus de collapse per mostrar en passos de la recepta.
     */

    let $collapse;
    /** 
     * @var $heading 
     * @type {String}
     * @description Variable per emmagatzemar el tipus de heading per mostrar en passos de la recepta.
     */
    let $heading;
    /** 
     * @var $user 
     * @type {String}
     * @description Variable per emmagatzemar l'alies de l'usuari que ha creat la recepta.
     */
    let $user;
    /** 
     * @var $carbohidrates 
     * @type {number}
     * @description Variable inicialitzada a 0 per emmagatzemar els carbohidrats de la recepta.
     */
    let $carbohidrates = 0;
    /** 
     * @var $proteins 
     * @type {number}
     * @description Variable inicialitzada a 0 per emmagatzemar les proteïnes de la recepta.
     */
    let $proteins = 0;
    /** 
     * @var $fat 
     * @type {number}
     * @description Variable inicialitzada a 0 per emmagatzemar les grases de la recepta.
     */
    let $fat = 0;
    /** 
     * @var $satured 
     * @type {number}
     * @description Variable inicialitzada a 0 per emmagatzemar les grases saturades de la recepta.
     */

    let $satured = 0;
    /** 
     * @var $monoinsaturated 
     * @type {number}
     * @description Variable inicialitzada a 0 per emmagatzemar les grases monosaturades de la recepta.
     */
    let $monoinsaturated = 0;
    /** 
     * @var $polyinsaturated 
     * @type {number}
     * @description Variable inicialitzada a 0 per emmagatzemar les grases polyinsaturades de la recepta.
     */
    let $polyinsaturated = 0;
    /** 
     * @var $sugars 
     * @type {number}
     * @description Variable inicialitzada a 0 per emmagatzemar els sucres de la recepta.
     */
    let $sugars = 0;
    /** 
     * @var $fiber 
     * @type {number}
     * @description Variable inicialitzada a 0 per emmagatzemar les fibres de la recepta.
     */
    let $fiber = 0;
    /** 
     * @var $sodium 
     * @type {number}
     * @description Variable inicialitzada a 0 per emmagatzemar el sodi de la recepta.
     */
    let $sodium = 0;
    /** 
     * @var $booleanComment 
     * @type {boolean}
     * @description Boolean inicialitzat en true per verificar si l'usuari ha comentat la recepta o no.
     */
    let $booleanComment = true;
    /** 
     * @var $booleanAssessment 
     * @type {boolean}
     * @description Boolean inicialitzat en true per verificar si l'usuari ha puntuat la recepta o no.
     */
    let $booleanAssessment = true;
    /** 
     * @var $allUsers 
     * @type {Array}
     * @description Array per emmagatzemar tots els usuaris de la BBDD.
     */
    let $allUsers = [];
    /** 
     * @var $score 
     * @type {number}
     * @description Variable inicialitzada sense cap dada per emmagatzemar la puntuació de l'usuari que ha fet de la recepta.
     */
    let $score = '';
    /** 
     * @var $comment 
     * @type {String}
     * @description Variable inicialitzada sense cap dada per emmagatzemar el comentari de l'usuari que ha fet de la recepta.
     */
    let $comment = '';
    /** 
     * @var $token
     * @type {String}
     * @description Variable de tipus String per emmagatzemar el token desat en localStorage.
     */
    let $token;
    /** 
     * @var $IDuser
     * @type {number}
     * @description Variable de tipus String per emmagatzemar l'ID de l'usuari desat en localStorage.
     */
    let $IDuser;
    let $saveRecipe;

    if (token() == true) {
        $token = localStorage.getItem('token');
        $IDuser = localStorage.getItem('id');
    }

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
        url: $urlRecipe + $idRecipe,
        type: 'GET',
        async: false,
        success: function ($recipe) {
            for (let i = 0; i < $allUsers.length; i++) {
                if ($allUsers[i][0] == $recipe.userId) {
                    $user = $allUsers[i][4]
                }
            }
            $saveRecipe = $recipe;
            console.log($recipe)
            $('.title').html($recipe.name);
            $('.title').after('<img class="imgRec" src="' + $recipe.image + '" alt="Imagen receta">');
            let $forks = getForks($recipe.recipeAssessment);
            let $difficult = getDifficult($recipe.recipeDifficult);
            $('.alias_cont').append('<h4 class="alias">' + $user + '</h4>');
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

            $.each($recipe.comments, function ($i, $commentsRecipe) {
                console.log($commentsRecipe)
                for (let x = 0; x < $allUsers.length; x++) {
                    if ($commentsRecipe.userId == $allUsers[x][0]) {
                        $('.comments_users').append('<div class="comment_user">\
                                                        <p id="alias">' + $allUsers[x][4] + '</p>\
                                                        <p id="comment">' + $commentsRecipe.commentValue + '</p>');
                    }
                }
            });
        }
    });

    /**
     * @function getForks
     * @description Concatenem una cadena per inserir en el DOM i mostrar la puntuació de la recepta.
     * @param {string} $forks Número de forquilles (puntuació) que te la recepta.
     * @return {string}
     */
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

    /**
     * @function getDificult
     * @description Rebem un número que representa la dificultat de la recepta i l'hi assignem una cadena String amb el text de dificultat.
     * @param {string} $dificult Número que representa la dificultat de la recepta.
     * @return {string}
     */
    function getDifficult($dificult) {
        switch ($dificult) {
            case 0:
                return 'Muy baja';
            case 1:
                return 'Baja';
            case 2:
                return 'Media';
            case 3:
                return 'Difícil';
            case 4:
                return 'Muy difícil';
        }
    }

    /**
     * @function insertIngredients
     * @description Rebem un número que representa la dificultat de la recepta i l'hi assignem una cadena String amb el text de dificultat.
     * @param {object} $ingredientsRecipe objecte JSON amb totes les dades de l'ingredient.
     */
    function insertIngredients($ingredientsRecipe) {
        $.ajax({
            url: $urlIngredients,
            type: 'GET',
            success: function ($ingredients) {
                for (let i = 0; i < $ingredients.length; i++) {
                    for (let x = 0; x < $ingredientsRecipe.length; x++) {
                        if ($ingredients[i].id == $ingredientsRecipe[x].ingredientId) {
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
                                            </tr>');
            }
        });
    }

    /**
     * @function insertSteps
     * @description Inserim en el DOM els passos a realitzar per fer la recepta.
     * @param {object} $stepsRecipe objecte JSON amb totes els passos a realitzar per fer la recepta.
     */
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

    /**
     * @function assingCollapseHeading
     * @description Inserim en el DOM les propietats collapse i heading.
     * @param {number} $number Número que utilitzarem per assignar el collapse i el heading.
     */
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

    /**
     * @type {jQuery}
     * @method each
     * @listens forkValue - ID de les forquilles.
     * @description Selecciona la forquilla segons la puntuació que prem l'usuari.
     */
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

    /**
     * @function insertForks
     * @description Modifiquem les imatges de les forquilles segons la puntuació que dona l'usuari a la recepta.
     * @param {number} $score Número que representa la puntuació que ha fet l'usuari sobre la recepta.
     */
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

    /**
     * @type {jQuery}
     * @type click
     * @method on
     * @listens .btn_send - Botó per inserir comentari i valoració.
     * @description Funció per inserir a la BBDD el comentari i la valoració que ha realtizat l'usuari sobre la recepta, verificant previament si hi ha comentari o valoració o ambes coses.
     */
    $('.btn_send').on('click', () => {
        $comment = $('#textComment').val();

        if ($comment != '' || $score != '') {

            if ($comment != '') {
                $.ajax({
                    url: $urlAddComment + $idRecipe + '___' + $comment + '___' + $IDuser,
                    type: 'POST',
                    async: false,
                    headers: {
                        'Authorization': $token
                    },
                    success: function ($insertComment) {
                        if ($insertComment == '') {
                            $booleanComment = false;
                            $('#sameComment').modal('show');
                        } else {
                            $booleanComment = true;
                        }
                    }
                });
            }

            if ($score != '') {
                $.ajax({
                    url: $urlAddAssessment + $idRecipe + '___' + $score + '___' + $IDuser,
                    type: 'POST',
                    async: false,
                    headers: {
                        'Authorization': $token
                    },
                    success: function ($insertAssessment) {
                        if($insertAssessment == ''){
                            $.each($saveRecipe.assessments, function ($i, $assessmentsRecipe) {
                                if ($assessmentsRecipe.assessmentValue == $score) {
                                    $booleanAssessment = false;
                                    $('#sameAssessment').modal('show');
                                } else {
                                    $('#updateAssessment').modal('show');
                                }
                            });
                        }else{
                            $booleanAssessment = true;
                        }
                    }
                });
            }
            if ($booleanComment == true && $booleanAssessment == true) {
                location.reload();
            }
        }
    });

    /**
     * @type {jQuery}
     * @type click
     * @method on
     * @listens .btn_accept - Botó per aceptar la modificació de la valoració de l'usuari.
     * @description Funció per inserir a la BBDD la modificació de la valoració que va realitzar en un altre moment l'usuari.
     */
    $('.btn_accept').on('click', function () {
        $.ajax({
            url: $urlModifyAssessment + 'recipe---' + $saveRecipe.id + '___value---' + $score + '___user---' + $IDuser,
            type: 'PUT',
            async: false,
            headers: {
                'Authorization': $token
            },
            async: false,
            success: function () {
                location.reload();
            }
        });        
    });
});