/**
 * @fileoverview Visualització de totes les receptes afegides per l'usuari, amb possibilitat de les següents opcions.
 * <p> 1) Afegir una recepta nova.</p>
 * <p> 2) Visualització de la recepta escollida.</p>
 * <p> 3) Modificació de la recepta escollida.</p>
 * <p> 4) Visualització de la puntuació de les receptes.</p>
 * <p> 5) Visualització global de la puntuació de l'usuari.</p>
 * 
 * <p> History</p>
 * 0.1 - Implementació de la visualització de totes les receptes afegides per l'usuari.  
 *  
 * @version     0.1
 * @author      Sergio Asensio Ruiz 
 * @copyright   bienmesabe.com
 * 
 */

$(document).ready(function () {
    /**
     * @constant $urlLatestRecipes
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor i recuperar totes les receptes en ordre de les últimes afegides.
     */
    const $urlLatestRecipes = 'http://localhost:8080/api/recipe/getRecipes';
    /**
     * @constant $urlUserValoration
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor i recuperar la puntuació de les receptes.
     */
    const $urlUserValoration = 'http://localhost:8080/api/user/getUserValoration/';
    /** 
     * @var $forks 
     * @type {String}
     * @description Variable per emmagatzemar la cadena per inserir en el DOM per veure la puntuació mitjana de les receptes.
     */
    let $forks;
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
                    $forks = getForks($recipe.recipeAssessment);
                    $('#recipes').append('<tr title="' + $recipe.id + '">\
                                            <td class="nameRec" ><a href="http://localhost:3000/recetas/ficha?id=' + $recipe.id + '">' + $recipe.name + '</a></td>\
                                            <td><i class="fas fa-pen icon btn_modifyRecipe"></i></td>\
                                            <td><i class="fas fa-trash-alt icon btn_deleteRecipe"></i></td>\
                                            <td>' + $forks + '</td>\
                                        </tr>');
                }
            });
        }
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
                                     ' + $forks);
        }
    })
    /**
     * @type {jQuery}
     * @type click
     * @method on
     * @listens btn_modifyRecipe - Classe del botó per afegir receptes.
     * @descriptionQuan Quan l'usuari polsa sobre el botó "Añadir receta" és redirigeix a la pàgina per crear la nova recepta.
     */
    $('.btn_modifyRecipe').on('click', function () {
        window.location = '/crearReceta?id=' + $(this).parent().parent().attr('title');
    });

    /**
     * @type {jQuery}
     * @type click
     * @method on
     * @listens modalDeleteRecipe - Classe del botó per esborrar receptes.
     * @descriptionQuan Quan l'usuari polsa sobre el botó "Aceptar" del modal de les escombraries aquesta recepta s'esborra.
     */
    $('.btn_deleteRecipe').on('click', function () {
        $('#modalDeleteRecipe').modal('show');
    });
    /**
     * @type {jQuery}
     * @type click
     * @method on
     * @listens modalDeleteRecipe - Classe del botó per esborrar receptes.
     * @descriptionQuan Quan l'usuari polsa sobre el botó "Aceptar" del modal de les escombraries aquesta recepta s'esborra.
     */
    $('.modalDeleteRecipe').on('click', function () {
        $.ajax({
            url: 'http://localhost:8080/api/recipe/deleteRecipeSteps/' + $('.nameRec').parent().attr('title'),
            type: 'DELETE',
            async: false,
            headers: {
                'Authorization': $token
            }
        });
        $.ajax({
            url: 'http://localhost:8080/api/recipe/deleteRecipeIngredients/' + $('.nameRec').parent().attr('title'),
            type: 'DELETE',
            async: false,
            headers: {
                'Authorization': $token
            }
        });
        $.ajax({
            url: 'http://localhost:8080/api/comment/deleteComments/' + $('.nameRec').parent().attr('title'),
            type: 'DELETE',
            async: false,
            headers: {
                'Authorization': $token
            }
        });
        $.ajax({
            url: 'http://localhost:8080/api/assessment/deleteAssessments/' + $('.nameRec').parent().attr('title'),
            type: 'DELETE',
            async: false,
            headers: {
                'Authorization': $token
            }
        });

        $.ajax({
            url: 'http://localhost:8080/api/recipe/deleteRecipeById/' + $('.nameRec').parent().attr('title'),
            type: 'DELETE',
            async: false,
            headers: {
                'Authorization': $token
            }
        });
        
        location.reload()
    });
});