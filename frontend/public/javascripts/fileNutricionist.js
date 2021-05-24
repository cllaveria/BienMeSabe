/**
 * @fileoverview Visualització de les dades del nutricionsita.
 * <p>Visualització dels comentaris fets al nutricionista.</p>
 * <p>Visualització de la puntuació del nutricionista.</p>
 * <p>Opció de poder comentar + valorar al nutricionista.</p>
 * 
 * <p> History</p>
 * 0.1 - Implementació de la visualització del nutricionista
 *  
 * @version     0.1
 * @author      Sergio Asensio Ruiz 
 * @copyright   bienmesabe.com
 * 
 */
$(document).ready(function () {
    /**
     * @const $idNutricionist
     * @type {number}
     * @description Constat per emmagatzemar l'ID del nutricionista
     */
    const $idNutricionist = (window.location.search).substr(4, );
    /**
     * @const $idNutricionist
     * @type {String}
     * @description Constat per emmagatzemar la ruta de connexió amb el servidor i recuperar les dades del nutricionista.
     */
    const $urlDateNutricionist = 'http://localhost:8080/api/nutricionist/getNutricionistById/';
    /** 
     * @var $booleanComment 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si s'ha realitzat comentari.
     */
    let $booleanComment = true;
    /** 
     * @var $booleanComment 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si s'ha realitzat valoració.
     */
    let $booleanAssessment = true;

    /** 
     * @var $token
     * @description Variable de tipus String per emmagatzemar el token desat en localStorage.
     */
    let $token;
    /** 
     * @var $IDuser
     * @description Variable de tipus String per emmagatzemar l'ID de l'usuari desat en localStorage.
     */
    let $IDuser;

    if (token() == true) {
        $token = localStorage.getItem('token');
        $IDuser = localStorage.getItem('id');
    }

    $.ajax({
        url: $urlDateNutricionist + $idNutricionist,
        type: 'GET',
        headers: {
            'Authorization': $token
        },
        success: function ($nutricionist) {

            console.log($nutricionist);

            $('#nameNutri').html($nutricionist.name + ' ' + $nutricionist.surname);
            $('#direccion').html($nutricionist.companyDirection);
        }
    });

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
     * @description Modifiquem les imatges de les forquilles segons la puntuació que dona l'usuari el nutricionista.
     * @param {number} $score Número que representa la puntuació que ha fet l'usuari sobre el nutricionista.
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
     * @listens .button - Botó per inserir comentari i valoració.
     * @description Funció per inserir a la BBDD el comentari i la valoració que ha realtizat l'usuari sobre el nutricionista, verificant previament si hi ha comentari o puntuació o ambes coses.
     */
    $('.button').on('click', () => {
        $comment = $('#textComment').val();
        if ($comment != '' || $score != '') {
            if ($comment != '') {
                $.ajax({
                    //TODO: RAUL | Pendiente de cambiar a la nueva url.
                    url: $urlAddComment + $idRecipe + '___' + $comment + '___' + $IDuser,
                    type: 'POST',
                    async: false,
                    headers: {
                        'Authorization': $token
                    },
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
                    //TODO: RAUL | Pendiente de cambiar a la nueva url.
                    url: $urlAddAssessment + $idRecipe + '___' + $score + '___' + $IDuser,
                    type: 'POST',
                    async: false,
                    headers: {
                        'Authorization': $token
                    },
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