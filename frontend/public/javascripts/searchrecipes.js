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
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor per ver les cerces de les receptes.
     */
    const $urlSearch = 'http://localhost:8080/api/recipe/getRecipesByFilters/';
    /**
     * @constant $urlSearchPlate
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor per veure les receptes per tipus de plat.
     */
    const $urlSearchPlate = 'http://localhost:8080/api/recipe/getRecipesByType/';
    /**
     * @constant $urlAllUsers
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor i recuperar tots els usuaris.
     */
    const $urlAllUsers = 'http://localhost:8080/api/user/getUsers';
    /**
     * @constant $urlTypePlate
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor i recuperar tots els tipus de plats.
     */
    const $urlTypePlate = 'http://localhost:8080/api/recipeTypes/getRecipeTypes';
    /**
     * @constant $urlLatestRecipes
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor i recuperar totes les receptes en ordre de les últimes afegides.
     */
    const $urlLatestRecipes = 'http://localhost:8080/api/recipe/getRecipes';
    /**
     * @constant $urlRecipe
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor veure la fitxa de les receptes.
     */
    const $urlRecipe = 'http://localhost:3000/recetas/ficha?id=';
    /** 
     * @var $searchFilters 
     * @type {String}
     * @description Variable per emmagatzemar la ruta rebuda per url.
     */
    let $searchFilters = (window.location.search).substr(1, );
    /** 
     * @var $searchId 
     * @type {String}
     * @description Variable per emmagatzemar la ruta rebuda per url que correspon a "id=".
     */
    let $searchId = (window.location.search).substr(1, 3);
    /** 
     * @var $searchLatestRecipes 
     * @type {String}
     * @description Variable per emmagatzemar la ruta rebuda per url que correspon a "latestRecipes".
     */
    let $searchLatestRecipes = (window.location.search).substr(1, 13);
    /** 
     * @var $allUsers 
     * @type {Array}
     * @description Array per emmagatzemar tots els usuaris de la BBDD.
     */
    let $allUsers = [];
    /** 
     * @var $allTypePlate 
     * @type {Array}
     * @description Array per emmagatzemar tots els tipus de plats.
     */
    let $allTypePlate = [];
    /** 
     * @var $allRecipes 
     * @type {Array}
     * @description Array per emmagatzemar totes les receptes.
     */
    let $allRecipes = [];
    /** 
     * @var $dinners 
     * @type {Array}
     * @description Array per emmagatzemar els comensals.
     */
    let $dinners = [];
    /** 
     * @var $typePlate 
     * @type {String}
     * @description Variable per emmagatzemar el tipus de plat que vol cercar l'usuari.
     */
    let $typePlate;
    /** 
     * @var $userAlias 
     * @type {String}
     * @description Variable per emmagatzemar l'alies de l'usuari que ha creat la recepta i mostrar-lo per pantalla.
     */
    let $userAlias;
    /** 
     * @var $dinner 
     * @type {number}
     * @description Variable per emmagatzemar els comensals de l'array.
     */
    let $dinner;
    /** 
     * @var $forks 
     * @type {String}
     * @description Variable per emmagatzemar la cadena per inserir en el DOM per veure la puntuació mitjana de les receptes.
     */
    let $forks;
    /** 
     * @var $difficult
     * @type {String}
     * @description Variable per emmagatzemar la dificultat de la recepta i mostar-lo per pantalla.
     */
    let $difficult;
    /** 
     * @var $totalRecipes 
     * @type {Array}
     * @description Array per emmagatzemar totes les receptes.
     */
    
    let $totalRecipes = [];
    /** 
     * @var $countRecipe 
     * @type {number}
     * @description Variable inicialitzada a 0 per mostrar per pantalla el total de receptes.
     */
    let $countRecipe = 0;

    /** 
     * @var $token
     * @type {String}
     * @description Variable de tipus String per emmagatzemar el token desat en localStorage.
     */
    let $token;
    /** 
     * @var $IDuser
     * @type {number}
     * @description Variable de tipus number per emmagatzemar l'ID de l'usuari desat en localStorage.
     */
    let $IDuser;

    if (token() == true) {
        $token = localStorage.getItem('token');
        $IDuser = localStorage.getItem('id');
    }

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
        for (let i = $min; i < $max; i++) {
            if ($recipes[i] == null) {
                break;
            } else {
                for (let j = 0; j < $allUsers.length; j++) {

                    if ($allUsers[j][0] == $recipes[i].userId) {
                        $userAlias = $allUsers[j][4];
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
            }
        }
        $countRecipe = $max;
    }
});