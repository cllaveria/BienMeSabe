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
 * <p> 0.1 - Implementació del filtre de cerca de receptes.</p>
 * <p> 0.2 - Implementació de la visualització de més receptes per tipus de plat, les últimes receptes afegides, i les receptes millor valorades en el slider.</p>
 * <p> 0.3 - Implementació de la verificació del token per saber si l'usuari ha iniciat, s'ha expirat o no ha iniciat sessió.</p>
 *  
 * @version     0.3
 * @author      Sergio Asensio Ruiz 
 * @copyright   bienmesabe.com
 * 
 */

$(document).ready(function () {
    /**
     * @constant $urlIngredients
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor i recuperar tots els ingredients.
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
     * @constant $urlAllUsers
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor i recuperar tots els usuaris.
     */
    /**
     * @constant $urlOrderByAssessment
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor i recuperar totes les receptes en ordre de millor valorades.
     */
    /**
     * @constant $urlRecipe
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor veure la fitxa de les receptes.
     */
    /**
     * @constant $screenSize
     * @description Constant per emmagatzemar les dimensions de la finestra del navegador.
     */
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
    /** 
     * @var $ingredientId 
     * @description Variable per emmagatzemar l'ID dels ingredients en el desplegable.
     */
    /** 
     * @var $userAlias 
     * @description Variable per emmagatzemar l'alies de l'usuari que ha creat la recepta i mostrar-lo per pantalla.
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
     * @var $recipePlates 
     * @description Array per emmagatzemar les receptes de la BBDD.
     */
    /** 
     * @var $latestRecipes 
     * @description Array per emmagatzemar les receptes de la BBDD ordenades per les últimes inserides.
     */
    /** 
     * @var $allUsers 
     * @description Array per emmagatzemar tots els usuaris de la BBDD.
     */
    /** 
     * @var $count 
     * @description Variable inicialitzada a 0 per contar les vegades que prenem el botó "VER MÁS" de les últimes receptes.
     */

    const $urlIngredients = 'http://localhost:8080/api/ingredient/getIngredients',
        $urlTypePlate = 'http://localhost:8080/api/recipeTypes/getRecipeTypes',
        $urlLatestRecipes = 'http://localhost:8080/api/recipe/getRecipes',
        $urlAllUsers = 'http://localhost:8080/api/user/getUsers',
        $urlOrderByAssessment = 'http://localhost:8080/api/recipe/getRecipesByAssessment',
        $urlRecipe = 'http://localhost:3000/recetas/ficha?id=',
        $screenSize = window.screen.width;

    let $ingredient,
        $numberPersons,
        $valueEnergMin,
        $valueEnergMax,
        $typePlate,
        $ingredientId,
        $userAlias,
        $forks,
        $difficult,
        $ingredients = [],
        $recipePlates = [],
        $latestRecipes = [],
        $allUsers = [],
        $count = 0;

    /** 
     * @var $insert 
     * @description Variable inicialitzada en blanc per emmagatzemar la cadena de les receptes a inserir.
     */

    let $token, $IDuser;

    if (token() == true) {
        $token = localStorage.getItem('token');
        $IDuser = localStorage.getItem('id');
    }

    $.ajax({
        url: $urlIngredients,
        type: 'GET',
        success: function (data) {
            for (let i = 0; i < data.length; i++) {
                $ingredients.push(data[i]);
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
                $recipePlates.push(data[i]);
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
                $allUsers.push($users[i]);
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
                    if ($allUsers[j][0] == $plateOrderByAssessment[i].userId) {
                        $userAlias = $allUsers[j][4];
                    }
                }
                $forks = getForks($plateOrderByAssessment[i].recipeAssessment);
                // Inserim en el carrousel DOM les receptes millor valroades.
                $('.carousel-inner').append('<div class="carousel-item">\
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
                if (i == 0) {
                    $('.carousel-item').addClass('active');
                }
            }
        }
    });

    $.ajax({
        url: $urlLatestRecipes,
        type: 'GET',
        async: false,
        success: function ($latestRecipesAjax) {

            for (let i = 0; i < 12; i++) {
                $latestRecipes[i] = $latestRecipesAjax[i];
            }
            if ($screenSize < 1240 && $screenSize > 700) {
                insertRecipe(0, 4, $allUsers, $latestRecipes, $urlRecipe);
            } else {
                insertRecipe(0, 3, $allUsers, $latestRecipes, $urlRecipe);
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

        let $booleanIngredientList = false;
        let $booleanIngredientSelect = true;

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

    /**
     * @type {jQuery}
     * @type each
     * @listens .recipeType - Classe de les imatges de tipus de plat del DOM.
     * @description Assignem una funció a cada tipus de plat enviant per paràmetre el tipus de plat, per quan polsem sobre qualsevol d'ells, faci la funció desitjada.
     */
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

    /**
     * @function checkTypePlate
     * @description Quan polsem sobre qualsevol plat, ens envia a la pàgina de 'filtros' amb l'ID del tipus de plat corresponent.
     * @param {string} $namePlate nom del tipus de plat seleccionat
     */
    function checkTypePlate($namePlate) {
        for (let i = 0; i < $recipePlates.length; i++) {
            if ($recipePlates[i].name == $namePlate) {
                window.location.href = '../recetas/filtros?id=' + $recipePlates[i].id
            }
        }
    }

    /**
     * @type {jQuery}
     * @type click
     * @method on
     * @listens .btn_lastRec - Botó per veure més receptes en la secció d'últimes afegides.
     * @description Funció per veure més receptes al pressionar sobre el botó "VER MÁS", segons les dimensions de la pantalla, mostrarem 3 (per a versions Mobile i Desktop) o 4 (per a versions de Tablet).
     */
    $('.btn_lastRec').on('click', () => {
        if ($count == 2) {
            window.location.href = '../recetas/filtros?latestRecipes'
        } else if ($count == 0) {
            if ($screenSize < 1240 && $screenSize > 700) {
                insertRecipe(4, 8, $allUsers, $latestRecipes, $urlRecipe);
            } else {
                insertRecipe(3, 6, $allUsers, $latestRecipes, $urlRecipe);
            }
        } else if ($count == 1) {
            if ($screenSize < 1240 && $screenSize > 700) {
                insertRecipe(8, 12, $allUsers, $latestRecipes, $urlRecipe);
            } else {
                insertRecipe(6, 9, $allUsers, $latestRecipes, $urlRecipe);
            }
        }
        $count++;
    });
});