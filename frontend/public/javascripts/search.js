/**
 * @fileoverview · Filtre per cercar les receptes de forma personalitzada: 
 * <p> 1) L'usuari selecciona els ingredients necessaris per trobar la recepta.</p>
 * <p> 2) L'usuari introdueix per a quantes persones és la recepta a cercar.</p>
 * <p> 3) L'usuari selecciona el valor energètic mínim a cercar.</p>
 * <p> 4) L'usuari selecciona el valor energètic màxim a cercar.</p>
 * <p> 5) L'usuari selecciona el tipus de plat a cercar.</p>
 * <p> · L'usuari selecciona el tipus de plat a cercar.</p>
 * <p> · L'usuari pot visualitzar les últimes receptes afegides.</p>
 * 
 * <p> History</p>
 * 0.1 - Implementació del filtre de cerca de receptes.  
 * 0.2 - Implementació de la visualització de més receptes per tipus de plat, les últimes receptes afegides, i les receptes millor valorades en el slider.  
 *  
 * @version     0.2
 * @author      Sergio Asensio Ruiz 
 * @copyright   bienmesabe.com
 * 
 */

$(document).ready(function () {

    /** 
     * @var $ingredient 
     * @description Variable per emmagatzemar l'ingredient que introdueix l'usuari. 
     */
    /** 
     * @var $numberPersons 
     * @description Variable per emmagatzemar el nombre de persones per cercar la recepta. 
     */
    /** 
     * @var $valueEnergMin 
     * @description Variable per emmagatzemar el valor energètic mínim que selecciona l'usuari. 
     */
    /** 
     * @var $valueEnergMax 
     * @description Variable per emmagatzemar el valor energètic màxim que selecciona l'usuari. 
     */
    /** 
     * @var $typePlate 
     * @description Variable per emmagatzemar el tipus de plat que vol cercar l'usuari.
     */
    /** 
     * @var $ingredients 
     * @description Array per emmagatzemar els ingredients que es troben a la BBDD registrats i es mostren a l'usuari.
     */
    const $urlIngredients = 'http://localhost:8080/api/ingredient/getIngredients';
    const $urlTypePlate = 'http://localhost:8080/api/recipeTypes/getRecipeTypes';
    const $urlLatestRecipes = 'http://localhost:8080/api/recipe/getRecipes';
    const $urlAllUsers = 'http://localhost:8080/api/user/getUsers';
    const $urlOrderByAssessment = 'http://localhost:8080/api/recipe/getRecipesByAssessment';
    const $urlRecipe = 'http://localhost:3000/recetas/ficha?id=';
    let $ingredient, $numberPersons, $valueEnergMin, $valueEnergMax, $typePlate, $ingredientId, $userAlias;
    let $ingredients = [];
    let $recipePlates = [];
    let $latestRecipes = [];
    let $allUsers = [];
    let $count = 0;
    let $forks, $difficult;
    let $insert = '';

    $.ajax({
        url: $urlIngredients,
        type: 'GET',
        success: function (data) {
            for (let i = 0; i < data.length; i++) {
                $ingredients[i] = data[i];
                $('#list_ingredient').append("<option>" + $ingredients[i].name + "</option>")
            }
        }
    });

    $.ajax({
        url: $urlTypePlate,
        type: 'GET',
        success: function (data) {
            $('#typePlate').append('<option value="all" selected>Selecciona una opción</option>');
            for (let i = 0; i < data.length; i++) {
                $recipePlates[i] = data[i]
                $('#typePlate').append('<option value="' + data[i].id + '">' + data[i].name + '</option>')
            }
        }
    });

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
        url: $urlOrderByAssessment,
        type: 'GET',
        async: false,
        success: function ($plateOrderByAssessment) {

            for (let i = 0; i < 4; i++) {
                for (let j = 0; j < $allUsers.length; j++) {
                    //TODO: BORRAR LO COMENTADO
                    if ( /* $allUsers[j].id */ $allUsers[j][0] == $plateOrderByAssessment[i].userId) {
                        $userAlias = /* $allUsers[j].alias */ $allUsers[j][4];
                    }
                }
                $forks = $getForks($plateOrderByAssessment[i].recipeAssessment)
                if (i == 0) {
                    $insert = $insert.concat('<div class="carousel-item active">\
                                                <a href="' + $urlRecipe + $plateOrderByAssessment[i].id + '">\
                                                    <img class="imgCarousel" src="' + $plateOrderByAssessment[i].image + '" alt="' + $plateOrderByAssessment[i].name + '">\
                                                    <div class="carousel-desc">\
                                                        <div class="carousel-caption">\
                                                            <div class="title">' + $plateOrderByAssessment[i].name + '</div>\
                                                            <h3>' + $userAlias + '</h3>\
                                                            ' + $forks + '\
                                                        </div>\
                                                    </div>\
                                                </a>\
                                            </div>');
                } else {
                    $insert = $insert.concat('<div class="carousel-item">\
                                                <a href="' + $urlRecipe + $plateOrderByAssessment[i].id + '">\
                                                    <img class="imgCarousel" src="' + $plateOrderByAssessment[i].image + '" alt="' + $plateOrderByAssessment[i].name + '">\
                                                    <div class="carousel-desc">\
                                                        <div class="carousel-caption">\
                                                            <div class="title">' + $plateOrderByAssessment[i].name + '</div>\
                                                            <h3>' + $userAlias + '</h3>\
                                                            ' + $forks + '\
                                                        </div>\
                                                    </div>\
                                                </a>\
                                            </div>');
                }
            }
            $('.carousel-inner').append($insert)
        }
    });

    $.ajax({
        url: $urlLatestRecipes,
        type: 'GET',
        async: false,
        success: function ($latestRecipesAjax) {

            for (let i = 0; i < 9; i++) {
                $latestRecipes[i] = $latestRecipesAjax[i];
            }

            for (let i = 0; i < 3; i++) {
                for (let j = 0; j < $allUsers.length; j++) {
                    //TODO: BORRAR LO COMENTADO
                    if ( /* $allUsers[j].id */ $allUsers[j][0] == $latestRecipes[i].userId) {
                        $userAlias = /* $allUsers[j].alias */ $allUsers[j][4];
                    }
                }

                $forks = $getForks($latestRecipes[i].recipeAssessment)
                $difficult = $getDificult($latestRecipes[i].recipeDifficult)

                insertLatestRecipe($latestRecipes[i], $userAlias, $difficult, $forks)
            }
        }
    })

    $typePlate = $('[name=typePlate]').val();
    $numberPersons = $('[name=numPer]').val();
    $valueEnergMin = $('[name=valueMin]').val();
    $valueEnergMax = $('[name=valueMax]').val();

    /**
     * @type {jQuery}
     * @type change
     * @method on
     * @listens document - Detecta canvi en la pàgina.
     * @description Quan l'usuari selecciona l'ingredient, es comprova que no sigui buit i que aquest es trobi en el desplegable. Una vegada verificat, si és correcte, s'inserirà en el div $ingredients
     */
    $(document).on('change', '#inp_ingredient', () => {
        // Iniciem els booleans.
        let $booleanIngredientList = false;
        let $booleanIngredientSelect = true;

        // Guardem en una variable l'ingredient seleccionat.
        $ingredient = $('input[name=ingredient]').val();

        // Comprovem si l'ingredient introduït correspon a un ingredient que està a la llista.
        $ingredients.forEach($element => {
            if ($element.name == $ingredient) {
                $booleanIngredientList = true;
            }
        });
        // Comprovem si l'ingredient introduït es troba en el div.
        $('#ingredientes').children().each(function () {
            if ($(this).find('.ingredient').text() == $ingredient) {
                $booleanIngredientSelect = false;
            }
        })
        //Comprovem que el valor introduït no sigui vuit, ni que els booleans estiguin en false, en cas de ser així, mostraran un alert.
        if ($ingredient == '') {
            alert('inserta un ingrediente que esté en la lista.')
        } else if ($booleanIngredientSelect == false) {
            alert('El ingrediente introducido ya está seleccionado.')
        } else if ($booleanIngredientList == false) {
            alert('inserta un ingrediente que esté en la lista.')
        } else {
            for (let i = 0; i < $ingredients.length; i++) {
                if ($ingredients[i].name == $ingredient) {
                    $ingredientId = $ingredients[i].id;
                }
            }
            // Inserim l'ingredient en el div per mostrar els ingredients que va seleccionant l'usuari.
            $('#ingredientes').append("<li class='select_ingredient' style='background-color: #FFFFFF;border-radius: 3px;font-size: 12px;font-weight: lighter;padding: 4px;border: 1px solid;display: inline-block;margin: 2px;'>\
                                        <span class='ingredient' value=" + $ingredientId + ">" + $ingredient + "</span>\
                                        <button class='buttonIngredients' style='margin-left: 7px;font-size: 11px;border-radius: 25px;'>X</button>\
                                    </li>");
            // Assignem la funció del botó d'eliminar l'ingredient a l'últim ingredient seleccionat.
            $('.select_ingredient:last').each(function () {
                $(this).find('.buttonIngredients').on('click', () => {
                    $(this).remove();
                });
            });
        }
        // Una vegada que ja s'ha inserit l'ingredient en el div, deixem la caixa de l'input buida. 
        $('input[name=ingredient]').val('');
    });

    /**
     * @type {jQuery}
     * @type change
     * @method on
     * @listens #numPer - ID del select del nombre de persones.
     * @description Detecta quan l'usuari selecciona les persones per cercar la recepta i desem la selecció de l'usuari en la variable $numberPersons.
     */
    $('#numPer').on('change', () => {
        $numberPersons = $('#numPer option:selected').val();
    });

    /**
     * @type {jQuery}
     * @type change
     * @method on
     * @listens #valueMin - ID del select valor energètic mínim.
     * @description Detecta quan l'usuari selecciona el valor energètic mínim per cercar la recepta i desem la selecció de l'usuari en la variable $valueEnergMin.
     */
    $('#valueMin').on('change', () => {
        $valueEnergMin = $('#valueMin option:selected').val();
    });

    /**
     * @type {jQuery}
     * @type change
     * @method on
     * @listens #valueMax - ID del select valor energètic mínim.
     * @description Detecta quan l'usuari selecciona el valor energètic màxim per cercar la recepta i desem la selecció de l'usuari en la variable $valueEnergMax.
     */
    $('#valueMax').on('change', () => {
        $valueEnergMax = $('#valueMax option:selected').val();
    });

    /**
     * @type {jQuery}
     * @type change
     * @method on
     * @listens #typePlate - ID del select del tipus de plat.
     * @description Detecta quan l'usuari selecciona el tipus de plat per cercar la recepta i desem la selecció de l'usuari en la variable $typePlate.
     */
    $('#typePlate').on('change', () => {
        $typePlate = $('#typePlate option:selected').val();
    });

    /**
     * @type {jQuery}
     * @type click
     * @method on
     * @listens .submit - Classe del botó submit.
     * @description Detecta quan l'usuari polsa sobre el botó "BUSCAR" i envia els parametres corresponents de cerca a la pàgina "recetas".
     */
    $('.submit').on('click', function () {
        let $search = '';
        if ($('#ingredientes').children().length > 0) {
            $search = 'ingredients-';
            $('#ingredientes').children().each(function () {
                $search = $search.concat($(this).find('.ingredient').attr('value'));
                $search = $search.concat(',')
            })
            $search = $search.substr(0, $search.length - 1);
        }

        if ($search.length > 0) {
            if ($valueEnergMin != 'all' && $valueEnergMax != 'all') {
                $search = $search.concat('_kcal-' + $valueEnergMin + ',' + $valueEnergMax);
            }
        } else {
            if ($valueEnergMin != 'all' && $valueEnergMax != 'all') {
                $search = $search.concat('kcal-' + $valueEnergMin + ',' + $valueEnergMax);
            }
        }

        if ($search.length > 0) {
            if ($typePlate != 'all' && $typePlate != null) {
                $search = $search.concat('_type-' + $typePlate);
            }
        } else {
            if ($typePlate != 'all' && $typePlate != null) {
                $search = $search.concat('type-' + $typePlate);
            }
        }

        if ($search.length > 0) {
            if ($numberPersons != 'all') {
                $search = $search.concat('_dinner-' + $numberPersons);
            }
        } else {
            if ($numberPersons != 'all') {
                $search = $search.concat('dinner-' + $numberPersons);
            }
        }

        window.location.href = "../recetas/filtros?" + $search;

    });

    $('.recipeType').each(function () {
        $(this).on('click', () => {
            switch ($(this).children().attr('alt')) {
                case 'Entrantes':
                    checkTypePlate('Entrantes');
                    break;
                case 'Primeros':
                    checkTypePlate('Primeros');
                    break;
                case 'Segundos':
                    checkTypePlate('Segundos');
                    break;
                case 'Postres':
                    checkTypePlate('Postres');
                    break;
                case 'Reposteria':
                    checkTypePlate('Repostería');
                    break;

            }
        })

    });

    function checkTypePlate($namePlate) {
        for (let i = 0; i < $recipePlates.length; i++) {
            if ($recipePlates[i].name == $namePlate) {
                window.location.href = '../recetas/filtros?id=' + $recipePlates[i].id
            }
        }
    }

    function $getForks($forks) {
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

    function $getDificult($dificult) {
        let $insertDificult;
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

    function insertLatestRecipe($recipe, $userAlias, $difficult, $forks) {
        $('.latest_rec').append('<div class="rcp_cnt">\
                                    <a href="' + $urlRecipe + $recipe.id + '">\
                                        <div class="recipe">\
                                            <img src="' + $recipe.image + '">\
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

    $('.btn_lastRec').on('click', () => {

        if ($count == 2) {
            window.location.href = '../recetas/filtros?latestRecipes'
        } else if ($count == 0) {
            for (let i = 3; i < 6; i++) {
                for (let j = 0; j < $allUsers.length; j++) {
                    //TODO: BORRAR LO COMENTADO
                    if ( /* $allUsers[j].id */ $allUsers[j][0] == $latestRecipes[i].userId) {
                        $userAlias = /* $allUsers[j].alias */ $allUsers[j][4];
                    }
                }

                let $forks = $getForks($latestRecipes[i].recipeAssessment)
                let $difficult = $getDificult($latestRecipes[i].recipeDifficult)

                insertLatestRecipe($latestRecipes[i], $userAlias, $difficult, $forks)
            }
        } else if ($count == 1) {
            for (let i = 6; i < 9; i++) {
                for (let j = 0; j < $allUsers.length; j++) {
                    //TODO: BORRAR LO COMENTADO
                    if ( /* $allUsers[j].id */ $allUsers[j][0] == $latestRecipes[i].userId) {
                        $userAlias = /* $allUsers[j].alias */ $allUsers[j][4];
                    }
                }
                console.log($userAlias)
                let $forks = $getForks($latestRecipes[i].recipeAssessment)
                let $difficult = $getDificult($latestRecipes[i].recipeDifficult)

                insertLatestRecipe($latestRecipes[i], $userAlias, $difficult, $forks)
            }
        }
        $count++;
    });

});