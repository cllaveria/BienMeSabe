/**
 * @fileoverview Filtre per cercar les receptes de forma personalitzada: 
 * <p> 1) L'usuari selecciona els ingredients necessaris per trobar la recepta.</p>
 * <p> 2) L'usuari introdueix per a quantes persones és la recepta a cercar.</p>
 * <p> 3) L'usuari selecciona el valor energètic mínim a cercar.</p>
 * <p> 4) L'usuari selecciona el valor energètic màxim a cercar.</p>
 * <p> 5) L'usuari selecciona el tipus de plat a cercar.</p>
 * 
 * <p> History</p>
 * 0.1 - Implementació del filtre de cerca de receptes.  
 *  
 * @version     0.1
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
    let $ingredient, $numberPersons, $valueEnergMin, $valueEnergMax, $typePlate, $ingredientId;
    let $ingredients = [];
    let $searchIngredients = [];

    $.ajax({
        url: 'http://localhost:8080/api/ingredient/getIngredients',
        type: 'GET',
        success: function (data) {
            for (let i = 0; i < data.length; i++) {
                $ingredients[i] = data[i];
                $('#list_ingredient').append("<option>" + $ingredients[i].name + "</option>")
                // data-id='" + $ingredients[i].id + "'
            }
        }
    });

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
        console.log($numberPersons)
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
        console.log($valueEnergMin)
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
        console.log($valueEnergMax)
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
        console.log($typePlate)
    });

    $('.submit').on('click', function () {

        let $urlSearch = 'http://localhost:8080/api/recipe/getRecipesByFilters/ingredients-'
        let x = 0;
        $('#ingredientes').children().each(function () {
            if (x != 0) {
                $urlSearch = $urlSearch.concat(',')
            }
            x++;
            $urlSearch = $urlSearch.concat($(this).find('.ingredient').attr('value'));
        })
        $urlSearch = $urlSearch.concat('_kcal-' + $valueEnergMin + ',' + $valueEnergMax + '_type-21_dinner-' + $numberPersons);

        $.ajax({
            url: $urlSearch,
            method: 'GET',
            dataType: 'json',
            success: function (data) {
                let $searchId = [];
                let $seachText = '';
                let x = 0;
                for (let i = 0; i < data.length; i++) {
                    if (x != 0) {
                        $seachText = $seachText.concat(',')
                    }
                    x++;
                    $seachText = $seachText.concat(data[i].id);

                }
                window.location.href = "../recetas?search="+$seachText;
            }
        })
    })
});