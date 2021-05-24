/**
 * @fileoverview Panell per poder veure els usuaris que tens si ets nutricionista o el nutricionista contractat si ets usuari.
 * <p>Poder trobar nutricionistes a prop segons el codi postal.</p>
 * 
 * <p> History</p>
 * 0.1 - Implementació de la visualització del panell si ets nutricionista o usuari.
 *  
 * @version     0.1
 * @author      Sergio Asensio Ruiz 
 * @copyright   bienmesabe.com
 * 
 */
$(document).ready(function () {
    /**
     * @const $urlUser
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor per recuperar les dades de l'usuari/nutricionista
     */
    const $urlUser = 'http://localhost:8080/api/user/getUserById/';
    /**
     * @const $urlSerachNutricionist
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor per recuperar les dades del nutricionista segons la cerca del codi postal.
     */
    const $urlSerachNutricionist = 'http://localhost:8080/api/nutricionist/findNutricionistByCP/';
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
        url: $urlUser + $IDuser,
        type: 'GET',
        async: false,
        headers: {
            'Authorization': $token
        },
        success: function ($type) {
            $typeUser = $type.type;
        }
    });


    if ($typeUser != 2) {
        $('.nutrist_container').css('display', 'none');

        //TODO: RAUL | Pendiente de que pueda hacer la conexión entre usuarios y nutricionistas.

        $('.btn_add').on('click', function () {
            $pcNutricionist = $(this).prev().val();

            $.ajax({
                url: $urlSerachNutricionist + $pcNutricionist,
                type: 'GET',
                headers: {
                    'Authorization': $token
                },
                success: function ($nutricionists) {
                    $('.nutritionists').empty();
                    $('.messageError').css('display', 'none');
                    $('.nutritionists').append('<div class="infoNutri"><a href="http://localhost:3000/fichaNutri?id=' + $nutricionists.id + '"\
                                                <p id="name">' + $nutricionists.name + '</p>\
                                                <p id="dire">' + $nutricionists.companyDirection + '</p>\
                                                <p id="titul">Titulación</p>\
                                                <div class="score_user">\
                                                    <img class="forkValue" id="fork1" value="5" src="/images/tenedor-gold.svg"\
                                                        alt="tenerdor" style="width: 15px; height: 30px;">\
                                                    <img class="forkValue" id="fork2" value="4" src="/images/tenedor-gold.svg"\
                                                        alt="tenerdor" style="width: 15px; height: 30px;">\
                                                    <img class="forkValue" id="fork3" value="3" src="/images/tenedor-gold.svg"\
                                                        alt="tenerdor" style="width: 15px; height: 30px;">\
                                                    <img class="forkValue" id="fork4" value="2" src="/images/tenedor-black.svg"\
                                                        alt="tenerdor" style="width: 15px; height: 30px;">\
                                                    <img class="forkValue" id="fork5" value="1" src="/images/tenedor-black.svg"\
                                                        alt="tenerdor" style="width: 15px; height: 30px;"></a>\
                                                </div>')
                },
                error: function () {
                    $('.nutritionists').empty();
                    $('.messageError').css('display', 'block');
                }
            });
        });

    } else {
        //Parte nutricionista
        $('.user_container').css('display', 'none');

        $('.mt-4').html('Mis usuarios');
    }
});