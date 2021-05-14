/**
 * @fileoverview Cerca de receptes personalitzat: 
 * <p> Mostra per pantalla el resultat de la cerca personalitzada.</p>
 * <p> Mostra per pantalla el resultat de la cerca per tipus de plat.</p>
 * 
 * <p> History</p>
 * <p> 0.1 - Implementació de la cerca de receptes.</p>
 * <p> 0.2 - Implementació de la visualització de mes receptes per tipus de plat.</p>
 *  
 * @version     0.2
 * @author      Sergio Asensio Ruiz 
 * @copyright   bienmesabe.com
 * 
 */

$(document).ready(function () {

    /**
     * @constant $urlSearch
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor per ver les cerces de les receptes.
     */
    /**
     * @constant $urlSearchPlate
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor per veure les receptes per tipus de plat.
     */
    /**
     * @constant $urlAllUsers
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor i recuperar tots els usuaris.
     */
    /**
     * @constant $urlTypePlate
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor i recuperar tots els tipus de plats.
     */
    /**
     * @constant $urlLatestRecipes
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor i recuperar totes les receptes en ordre de les últimes afegides.
     */
    /**
     * @constant $urlRecipe
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor veure la fitxa de les receptes.
     */
    /** 
     * @var $searchFilters 
     * @description Variable per emmagatzemar la ruta rebuda per url.
     */
    /** 
     * @var $searchId 
     * @description Variable per emmagatzemar la ruta rebuda per url que correspon a "id=".
     */
    /** 
     * @var $searchLatestRecipes 
     * @description Variable per emmagatzemar la ruta rebuda per url que correspon a "latestRecipes".
     */
    /** 
     * @var $allUsers 
     * @description Array per emmagatzemar tots els usuaris de la BBDD.
     */
    /** 
     * @var $allTypePlate 
     * @description Array per emmagatzemar tots els tipus de plats.
     */
    /** 
     * @var $allRecipes 
     * @description Array per emmagatzemar totes les receptes.
     */
    /** 
     * @var $dinners 
     * @description Array per emmagatzemar els comensals.
     */
    /** 
     * @var $userAlias 
     * @description Variable per emmagatzemar l'alies de l'usuari que ha creat la recepta i mostrar-lo per pantalla.
     */
    /** 
     * @var $typePlate 
     * @description Variable per emmagatzemar el tipus de plat que vol cercar l'usuari.
     */
    /** 
     * @var $dinner 
     * @description Variable per emmagatzemar els comensals de l'array.
     */
    /** 
     * @var $forks 
     * @description Variable per emmagatzemar la cadena per inserir en el DOM per veure la puntuació mitjana de les receptes.
     */
    /** 
     * @var $difficult 
     * @description Variable per emmagatzemar la dificultat de la recepta i mostar-lo per pantalla.
     */
    /** 
     * @var $totalRecipes 
     * @description Array per emmagatzemar totes les receptes.
     */
    /** 
     * @var $countRecipe 
     * @description Variable inicialitzada a 0 per mostrar per pantalla el total de receptes.
     */

    const $urlSearch = 'http://localhost:8080/api/recipe/getRecipesByFilters/',
        $urlSearchPlate = 'http://localhost:8080/api/recipe/getRecipesByType/',
        $urlAllUsers = 'http://localhost:8080/api/user/getUsers',
        $urlTypePlate = 'http://localhost:8080/api/recipeTypes/getRecipeTypes',
        $urlLatestRecipes = 'http://localhost:8080/api/recipe/getRecipes',
        $urlRecipe = 'http://localhost:3000/recetas/ficha?id=';

    let $searchFilters = (window.location.search).substr(1, ),
        $searchId = (window.location.search).substr(1, 3),
        $searchLatestRecipes = (window.location.search).substr(1, 13),
        $allUsers = [],
        $allTypePlate = [],
        $allRecipes = [],
        $dinners = [],
        $typePlate,
        $userAlias,
        $dinner,
        $forks,
        $difficult,
        $totalRecipes = [],
        $countRecipe = 0;

    if ($searchFilters.indexOf('dinner') > 1) {
        $allRecipes = $searchFilters.split('_');
        $dinners = $allRecipes[1].split('-');
    }

    $(window).scroll(function () {
        if ($(window).scrollTop().toFixed(0) > ($(document).height() - 1100)) {
            insertRecipes($countRecipe, ($countRecipe + 18), $totalRecipes);
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
        url: $urlTypePlate,
        type: 'GET',
        async: false,
        success: function ($plate) {
            for (let d = 0; d < $plate.length; d++) {
                $allTypePlate[d] = $plate[d];
            }
        }
    });

    if ($searchId == 'id=') {
        $.ajax({
            url: $urlSearchPlate + (window.location.search).substr(4, 5),
            type: 'GET',
            async: false,
            success: function ($recipes) {
                $totalRecipes = $recipes;
                insertRecipes($countRecipe, ($countRecipe + 18), $recipes);
            }
        });
    } else if ($searchLatestRecipes == 'latestRecipes') {
        $.ajax({
            url: $urlLatestRecipes,
            type: 'GET',
            async: false,
            success: function ($recipes) {
                $totalRecipes = $recipes;
                insertRecipes($countRecipe, ($countRecipe + 18), $recipes);
            }
        });
    } else {
        $.ajax({
            url: $urlSearch + $searchFilters,
            type: 'GET',
            async: false,
            success: function ($recipes) {
                if ($recipes.length == 0) {
                    $('.messageError').css('display', 'inline-block')
                } else {
                    $totalRecipes = $recipes;
                    insertRecipes($countRecipe, ($countRecipe + 18), $recipes);
                }
            }
        });
    }

    /**
     * @function insertRecipe
     * @description Funció per inserir en el DOM les receptes afegides a la BBDD.
     * @param {number} $min Número mínim per començar el compte en el for.
     * @param {number} $max Numero màxim per finalitzar el compte en el for.
     * @param {object} $recipes objecte JSON amb les dades de la recepta.
     */
    function insertRecipes($min, $max, $recipes) {
        for (let i = $min; i < $max /* $recipes.length */ ; i++) {
            if ($recipes[i] == null) {
                break;
            } else {
                for (let j = 0; j < $allUsers.length; j++) {
                    //TODO: BORRAR LO COMENTADO
                    if ( /* $allUsers[j].id */ $allUsers[j][0] == $recipes[i].userId) {
                        $userAlias = /* $allUsers[j].alias */ $allUsers[j][4];
                    }
                }
                for (let x = 0; x < $allTypePlate.length; x++) {
                    if ($allTypePlate[x].id == $recipes[i].type) {
                        $typePlate = $allTypePlate[x].name;
                        if (i == 0) {
                            if ((window.location.search).substr(1, 3) == 'id=') {
                                $('.recipes_container').prepend('<div class="tl_typePlate tl_' + $typePlate + '" >' + $typePlate + '</div>')
                            }
                        }
                    }
                }

                $forks = getForks($recipes[i].recipeAssessment);
                $difficult = getDifficult($recipes[i].recipeDifficult);

                if ($dinners[1] != null) {
                    $dinner = $dinners[1];
                } else {
                    $dinner = '';
                }

                $('.recipes_cont').append('<div class="rcp_cnt">\
                                            <a href="' + $urlRecipe + $recipes[i].id + '_dinner=' + $dinner + '">\
                                                <div class="recipe ' + $typePlate + '">\
                                                    <img src="' + $recipes[i].image + '" alt="' + $typePlate + '" style="width: 100%;">\
                                                    <div class="desc_rec">\
                                                        <h3 id="title">' + $recipes[i].name + '</h3>\
                                                        <p id="author">' + $userAlias + '</p>\
                                                    </div>\
                                                </div>\
                                                <div class="info_rec">\
                                                    <p id="level">Dificultad: ' + $difficult + '</p>\
                                                    <div class="time_rec">\
                                                        <i class="fas fa-clock clock"></i>\
                                                        <p id="time">' + $recipes[i].recipeTime + ' min</p>\
                                                    </div>\
                                                </div>\
                                                ' + $forks + '\
                                                </a>\
                                            </div>');
                //receivePlate($recipes[i], $userAlias, $forks, $difficult, $typePlate);
            }
        }
        $countRecipe = $max;
    }

    /* function receivePlate($recipe, $userAlias, $forks, $difficult, $classPlate) {
        if ($dinners[1] != null) {
            $dinner = $dinners[1];
        } else {
            $dinner = '';
        }
        $('.recipes_cont').append('<div class="rcp_cnt">\
                                    <a href="' + $urlRecipe + $recipe.id + '_dinner=' + $dinner + '">\
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
    } */

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
        //let $insertDificult = '';
        switch ($dificult) {
            case 0:
                return /* $insertDificult =  */ 'Muy baja';
            case 1:
                return /* $insertDificult =  */ 'Baja';
            case 2:
                return /* $insertDificult =  */ 'Media';
            case 3:
                return /* $insertDificult =  */ 'Difícil';
            case 4:
                return /* $insertDificult =  */ 'Muy difícil';
        }
    }
});