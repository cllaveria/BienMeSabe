/**
 * @fileoverview Visualització segons l'opció escollida en la pàgina "misRecetas", si se selecciona "Añadir receta" es visualitzarà el formulari per afegir una nova recepta, si es polsa el botó de llapis, es modificarà la recepta escollida mostrant en els inputs els valors d'aquesta.
 * <p> 1) Afegir títol.</p>
 * <p> 2) Afegir imatge.</p>
 * <p> 3) Afegir descripció inicial.</p>
 * <p> 4) Afegir ingredients.</p>
 * <p> 5) Afegir els passos a realitzar + imatge.</p>
 * <p> 6) Afegir una descripció final.</p>
 * 
 * <p> History</p>
 * 0.1 - Implementació de la visualització segons l'opció escollida per l'usuari.  
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
     * @constant $urlRecipe
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor per veure la fitxa de les receptes.
     */
    const $urlRecipe = 'http://localhost:8080/api/recipe/getRecipesById/';
    /**
     * @constant $urlIngredients
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor i recuperar tots els ingredients.
     */
    const $urlIngredients = 'http://localhost:8080/api/ingredient/getIngredients';
    /**
     * @constant $urlAddIngredients
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor per desar a la BBDD els ingredients de la nova recepta.
     */
    const $urlAddIngredients = 'http://localhost:8080/api/recipe/addIngredient/';
    /**
     * @constant $urlAddSteps
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor per desar a la BBDD els passos de la nova recepta.
     * @contents http://localhost:8080/api/recipe/addRecipeStep/
     */
    const $urlAddSteps = 'http://localhost:8080/api/recipe/addRecipeStep/';
    /**
     * @constant $urlModifyRecipe
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor per modificar les receptes.
     */
    const $urlModifyRecipe = 'http://localhost:8080/api/recipe/modifyRecipe/';
    /**
     * @constant $urlAddRecipe
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor per afegir les receptes.
     */
    const $urlAddRecipe = 'http://localhost:8080/api/recipe/addRecipe/';
    /**
     * @var $idRecipe
     * @type {number}
     * @description Variable per emmagatzemar l'ID de la recepta.
     */
    let $idRecipe = (window.location.search).substr(4, );
    /** 
     * @var $arrayIngredients
     * @type {Array} 
     * @description Array per emmagatzemar els ingredients que es troben a la BBDD registrats de la recepta.
     */
    let $arrayIngredients = [];
    /** 
     * @var $arrayIngredientsAdd
     * @type {Array} 
     * @description Array per emmagatzemar els ingredients de la recepta.
     */
    let $arrayIngredientsAdd = [];
    /** 
     * @var $arrayStepsAdd
     * @type {Array} 
     * @description Array per emmagatzemar els passos de la recepta.
     */
    let $arrayStepsAdd = [];
    /** 
     * @var $ingredientUnity 
     * @type {String}
     * @description Variable per emmagatzemar el tipus d'unitat de l'ingredient. 
     */
    let $ingredientUnity;
    /** 
     * @var $countModifyRecipeIngredients 
     * @type {number}
     * @description Variable per emmagatzemar el compte de les vegades que s'afegeix un nou ingredient en la modificació de la recepta. 
     */
    let $countModifyRecipeIngredients = 0;
    /** 
     * @var $countAddRecipeIngredients 
     * @type {number}
     * @description Variable per emmagatzemar el compte de les vegades que s'afegeix un nou ingredient al afegir una recepta nova. 
     */
    let $countAddRecipeIngredients = 6;
    /** 
     * @var $countModiyRecipeSteps 
     * @type {number}
     * @description Variable per emmagatzemar el compte de les vegades que s'afegeix un nou pas al modificar una recepta.
     */
    let $countModiyRecipeSteps = 0;
    /** 
     * @var $countAddRecipeSteps 
     * @type {number}
     * @description Variable per emmagatzemar el compte de les vegades que s'afegeix un nou pas al afegir una recepta nova.
     */
    let $countAddRecipeSteps = 5;
    /** 
     * @var $nameRecipe 
     * @type {String}
     * @description Variable per emmagatzemar el nom de la recepta. 
     */
    let $nameRecipe;
    /** 
     * @var $time 
     * @type {number}
     * @description Variable per emmagatzemar el temps total per realitzar la recepta. 
     */
    let $time;
    /** 
     * @var $type 
     * @type {number}
     * @description Variable per emmagatzemar el tipus de plat.
     */
    let $type;
    /** 
     * @var $difficult 
     * @type {number}
     * @description Variable per emmagatzemar la dificultat de la recepta.
     */
    let $difficult;
    /** 
     * @var $JSONrecipe 
     * @type {JSON}
     * @description Variable JSON per emmagatzemar les ades de la recepta.
     */
    let $JSONrecipe;
    /** 
     * @var $descriptionInitRecipe 
     * @type {String}
     * @description Variable per emmagatzemar la descripció inicial de la recepta.
     */
    let $descriptionInitRecipe;
    /** 
     * @var $endscriptionFinalRecipe 
     * @type {String}
     * @description Variable per emmagatzemar la descripció final de la recepta.
     */
    let $endescriptionFinalRecipe;
    /** 
     * @var $imageVideoRecipe 
     * @type {String}
     * @description Variable per emmagatzemar el video de la recepta.
     */
    let $imageVideoRecipe;
    /** 
     * @var $JSONmodifyRecipe 
     * @type {JSON}
     * @description Variable per emmagatzemar la recepta modificada en format JSON.
     */
    let $JSONmodifyRecipe;
    /** 
     * @var $JSONmodifySteps 
     * @type {JSON}
     * @description Variable per emmagatzemar la recepta modificada en format JSON.
     */
    let $JSONmodifySteps;
    /** 
     * @var $imageVideoRecipe 
     * @type {FormData}
     * @description Variable per emmagatzemar la imatge en format formData.
     */
    let $image = new FormData();
    /** 
     * @var $token
     * @type {String}
     * @description Variable de tipus String per emmagatzemar el token desat en localStorage.
     */
    let $token;
    /** 
     * @var $IDuser 
     * @type {String}
     * @description Variable de tipus String per emmagatzemar l'ID de l'usuari desat en localStorage.
     */
    let $IDuser;

    if (token() == true) {
        $token = localStorage.getItem('token');
        $IDuser = localStorage.getItem('id');
    }

    $.ajax({
        url: $urlIngredients,
        type: 'GET',
        async: false,
        success: function ($ingredientsRecipe) {
            $.each($ingredientsRecipe, function ($index, $ingredients) {
                $arrayIngredients.push($ingredients);
            });
        }
    })

    if ($idRecipe != '') {
        $.ajax({
            url: $urlRecipe + $idRecipe,
            type: 'GET',
            headers: {
                'Authorization': $token
            },
            success: function ($recipe) {
                console.log($recipe)
                $JSONrecipe = $recipe
                $('#inp_name').val($recipe.name)
                $('#imageVideoRecipe').text(trimImage($recipe.image))
                $('#inp_description').text($recipe.recipeInitDescription);
                $('.difficult option[value="' + $recipe.recipeDifficult + '"]').attr("selected", "selected");
                $('.type option[value="' + $recipe.type + '"]').attr("selected", "selected");
                $('.time option[value="' + $recipe.recipeTime + '"]').attr("selected", "selected");
                if ($recipe.recipeIngredients.length > 0) {
                    $('.ingr_cont').remove();
                    $.each($recipe.recipeIngredients, function ($indexI, $recipeIngredients) {
                        $.each($arrayIngredients, function ($indexX, $nameIngredients) {
                            if ($nameIngredients.id == $recipeIngredients.ingredientId) {
                                if ($recipeIngredients.ingredientUnity == 'G') {
                                    $ingredientUnity = '<option value="U">u.</option>\
                                                        <option value="G" selected>gr</option>\
                                                        <option value="S">chr.</option>';
                                } else if ($recipeIngredients.ingredientUnity == 'U') {
                                    $ingredientUnity = '<option value="U" selected>u.</option>\
                                                        <option value="G">gr</option>\
                                                        <option value="S">chr.</option>';
                                } else if ($recipeIngredients.ingredientUnity == 'S') {
                                    $ingredientUnity = '<option value="U">u.</option>\
                                                        <option value="G">gr</option>\
                                                        <option value="S" selected>chr.</option>';
                                }
                                $('#ingredients').append('<div class="ingr_cont">\
                                                            <div class="ingredients">\
                                                                <div class="cantidad">\
                                                                    <select class="ingredient">\
                                                                        ' + $ingredientUnity + '\
                                                                    </select>\
                                                                    <input class="quan" type="text" placeholder="Cantidad" value="' + $recipeIngredients.ingredientQTY + '">\
                                                                </div>\
                                                                <input list="list_ingredient" name="ingredient" id="inp_ingredient_' + $indexI + '"\
                                                                placeholder="Introduce un ingrediente esté o no en la lista" value="' + $nameIngredients.name + '" style="width: 100%;">\
                                                                <datalist id="list_ingredient"></datalist>\
                                                            </div>\
                                                        </div>');
                                $countModifyRecipeIngredients++;
                            }
                        });
                    });
                }
                addIngredients($countModifyRecipeIngredients);
                $.each($arrayIngredients, function ($index, $ingredients) {
                    $('#list_ingredient').append("<option>" + $ingredients.name + "</option>");
                });
                if ($recipe.recipeSteps.length > 0) {
                    $('.pasos_cont').remove();
                    $.each($recipe.recipeSteps, function ($index, $recipeSteps) {
                        $('#steps').append('<div class="pasos_cont">\
                                                <div class="pasos">\
                                                    <div class="inputs">\
                                                        <label>Descripción paso ' + ($index + 1) + '</label>\
                                                        <textarea class="desc_pas" id="desc_pas_' + $index + '" type="text"\
                                                         placeholder="Introduce la descripción del paso ' + ($index + 1) + '">' + $recipeSteps.stepDescription + '</textarea>\
                                                    </div>\
                                                    <div class="inputs dropzone">\
                                                        <label>Imagen paso 1</label>\
                                                        <div class="dropzone dropzone-single" data-toggle="dropzone" data-dropzone-url="http://">\
                                                            <div class="fallback">\
                                                                <div class="custom-file">\
                                                                    <input type="file" class="custom-file-input" id="dropzoneBasicUpload">\
                                                                    <label class="custom-file-label" id="imageStep" for="dropzoneBasicUpload">' + trimImage($recipeSteps.image) + '</label>\
                                                                </div>\
                                                            </div>\
                                                        </div>\
                                                    </div>\
                                                </div>\
                                            </div>');
                        $countModiyRecipeSteps++;
                    });
                }
                addSteps($countModiyRecipeSteps)
                $('#inp_descriptionEnding').text($recipe.recipeEndingDescription)
            }
        });


    } else {
        addIngredients($countAddRecipeIngredients);
        addSteps($countAddRecipeSteps)
    }

    $.each($arrayIngredients, function ($index, $ingredients) {
        $('#list_ingredient').append("<option>" + $ingredients.name + "</option>");
    });

    /**
     * @function trimImage
     * @param {number} $image 
     * @description Retallem la ruta de la imatge per quedar-nos només amb el nom de l'arxiu a pujar.
     */
    function trimImage($image) {
        if ($image != null) {
            return ($image).substr(7, );
        } else {
            return;
        }

    }

    /**
     * @function addIngredients
     * @param {number} $addIngredients 
     * @description Afegim al DOM les caselles per poder afegir els ingredients per realitzar la recepta.
     */
    function addIngredients($addIngredients) {
        $('.btn_addIngr').on('click', () => {
            $('#ingredients').append('<div class="ingr_cont">\
                                        <div class="ingredients">\
                                            <div class="cantidad">\
                                            <select class="ingredient">\
                                                <option value="U">u.</option>\
                                                <option value="G">gr</option>\
                                                <option value="S">chr.</option>\
                                            </select>\
                                                <input class="quan" type="text" placeholder="Cantidad">\
                                            </div>\
                                            <input list="list_ingredient" name="ingredient" id="inp_ingredient_' + $addIngredients + '"\
                                            placeholder="Introduce un ingrediente esté o no en la lista" style="width: 100%;">\
                                            <datalist id="list_ingredient"></datalist>\
                                        </div>\
                                    </div>');
            $addIngredients++;
        });
    }
    /**
     * @function addSteps
     * @param {number} $addSteps 
     * @description Afegim al DOM les caselles per poder afegir els passos per realitzar la recepta.
     */
    function addSteps($addSteps) {
        $('.btn_addPasos').on('click', () => {
            $('#steps').append('<div class="pasos_cont">\
                                                <div class="pasos">\
                                                    <div class="inputs">\
                                                        <label>Descripción paso ' + ($addSteps + 1) + '</label>\
                                                        <textarea class="desc_pas" id="desc_pas_' + $addSteps + '" type="text"\
                                                         placeholder="Introduce la descripción del paso ' + ($addSteps + 1) + '"></textarea>\
                                                    </div>\
                                                    <div class="inputs dropzone">\
                                                        <label>Imagen paso 1</label>\
                                                        <div class="dropzone dropzone-single" data-toggle="dropzone" data-dropzone-url="http://">\
                                                            <div class="fallback">\
                                                                <div class="custom-file">\
                                                                    <input type="file" class="custom-file-input" id="dropzoneBasicUpload">\
                                                                    <label class="custom-file-label" id="imageStep" for="dropzoneBasicUpload"></label>\
                                                                </div>\
                                                            </div>\
                                                        </div>\
                                                    </div>\
                                                </div>\
                                            </div>');
            $addSteps++;
        });
    }

    /**
     * @type {jQuery}
     * @type click
     * @method on
     * @listens btn_save - Classe del botó per afegir o modificar la recepta.
     * @description Quan l'usuair polsa sobre el botó "Guardar" es modifica o s'afegeix la recepta, segons l'opció escollida en la página "misRecetas".
     */

    $('.btn_save').on('click', () => {
        $nameRecipe = $('#inp_name').val();
        console.log($('input[name="imageVideoRecipe"')[0].files[0])

        $image.append('image', $('input[name="imageVideoRecipe"')[0].files[0]);
        $descriptionInitRecipe = $('#inp_description').val();
        $time = $('.time option:selected').val();
        $difficult = $('.difficult option:selected').val();
        $type = $('type option:selected').val();

        $('.ingredients').each(function () {
            $arrayIngredientsAdd.push([$(this).find('.ingredient option:selected').val(), $(this).find('.quan').val(), $(this).find('input[name=ingredient]').val()]);
        });

        $('.pasos_cont').each(function () {
            $arrayStepsAdd.push([$(this).find('.desc_pas').val(), $(this).find('#imageStep').prev().val()])
        })

        $endescriptionFinalRecipe = $('#inp_descriptionEnding').val();

        if ($idRecipe != '') {
            $JSONmodifyRecipe = {
                'id': $JSONrecipe.id,
                'name': $nameRecipe,
                'recipeEndingDescription': $endescriptionFinalRecipe,
                'recipeInitDescription': $descriptionInitRecipe,
                'userId': eval($IDuser),
                'active': $JSONrecipe.active,
                'recipeAssessment': $JSONrecipe.recipeAssessment,
                'recipeDifficult': eval($difficult),
                'recipeDinners': $JSONrecipe.recipeDinners,
                'recipeTime': eval($time),
                'type': eval($type),
            };

            $JSONmodifyRecipe = JSON.stringify($JSONmodifyRecipe)
            $.ajax({
                url: $urlModifyRecipe,
                type: 'PUT',
                data: $JSONmodifyRecipe,
                headers: {
                    'Authorization': $token
                },
                contentType: "application/json",
                dataType: "json",
                success: function () {}
            });
        } else {
            $JSONmodifyRecipe = {
                'name': $nameRecipe,
                'recipeEndingDescription': $endescriptionFinalRecipe,
                'recipeInitDescription': $descriptionInitRecipe,
                'userId': eval($IDuser),
                //TODO: Modificar si da tiempo.
                'active': eval(1),
                'recipeAssessment': eval(0),
                'recipeDifficult': eval($difficult),
                'recipeDinners': eval(1),
                'recipeTime': eval($time),
                'type': eval($type),
            };

            $JSONmodifyRecipe = JSON.stringify($JSONmodifyRecipe)
            $.ajax({
                url: $urlAddRecipe,
                type: 'POST',
                data: $JSONmodifyRecipe,
                async: false,
                headers: {
                    'Authorization': $token
                },
                contentType: "application/json",
                dataType: "json",
                success: function () {}
            });
            $.ajax({
                url: $urlLatestRecipes,
                async: false,
                type: 'GET',
                headers: {
                    'Authorization': $token
                },
                success: function ($latestRecipe) {
                    $.each($arrayIngredientsAdd, function ($i, $ingredientsAdd) {
                        console.log($ingredientsAdd)
                        $.each($arrayIngredients, function ($x, $IngredientsName) {
                            if ($ingredientsAdd[2] == $IngredientsName.name) {
                                $.ajax({
                                    url: $urlAddIngredients + 'recipe---' + $latestRecipe[0].id + '___id---' + $IngredientsName.id + '___qty---' + $ingredientsAdd[1] + '___unity---' + $ingredientsAdd[0],
                                    type: 'POST',
                                    async: false,
                                    headers: {
                                        'Authorization': $token
                                    }
                                });
                            }
                        });
                    });

                    $.each($arrayStepsAdd, function ($i, $stepsAdd) {
                        $JSONmodifySteps = {
                            'stepDescription': $stepsAdd[0],
                            'recipeId': eval($latestRecipe[0].id),
                            'image': null
                        }

                        $JSONmodifySteps = JSON.stringify($JSONmodifySteps)
                        $.ajax({
                            url: $urlAddSteps,
                            type: 'POST',
                            async: false,
                            body: $JSONmodifySteps,
                            headers: {
                                'Authorization': $token
                            },
                            contentType: "application/json",
                            dataType: "json",
                            success: function () {}
                        });
                    });
                }
            });

        }
    });

    $('.btn_checkIn').on('click', () => {
        //window.location.href='/misRecetas';
    })
});