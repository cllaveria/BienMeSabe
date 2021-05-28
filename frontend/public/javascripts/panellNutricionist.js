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
     * @const $urlUser
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor per recuperar al nutricionista contractat.
     */
    const $urlNutricionistHired = 'http://localhost:8080/api/user/getUserNutricionist/'
    /**
     * @const $urlSerachNutricionistCP
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor per recuperar les dades del nutricionista segons la cerca del codi postal.
     */
    const $urlSearchNutricionistCP = 'http://localhost:8080/api/nutricionist/findNutricionistByCP/';
    /**
     * @const $urlSerachNutricionist
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor per recuperar les dades del nutricionista segons la cerca del codi postal.
     */
    const $urlSearchNutricionist = 'http://localhost:8080/api/nutricionist/getNutricionistById/';
    /**
     * @const $urlSearchUsersNutricionist
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor per recuperar les dades dels usuaris que tenen el servei del nutricionista.
     */
    const $urlSearchUsersNutricionist = 'http://localhost:8080/api/user/getNutricionistUsers/';
    /**
     * @constant $urlModifyDataNutricionist 
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor per emmagatzemar les modificaciones de les dades del nutricionista.
     */
    const $urlModifyDataNutricionist = 'http://localhost:8080/api/nutricionist/';
    /**
     * @var $JSNONnutricionistDegree 
     * @type {JSON}
     * @description Variable per emmagatzemar en format JSON la titulació del nutricionista.
     */
    let $JSNONnutricionistDegree;
    /** 
     * @var $titleNutricionist
     * @type {String}
     * @description Variable String per emmagatzemar el titul del nutricionista.
     */
    let $titleNutricionist;
    /**
     * @var $descNuutricionist
     * @type {String}
     * @description Variable String per emmagatzemar la descipció del nutricionista.
     */
    let $descNutricionist;
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
    let $IDnutricionist;

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

        $.ajax({
            url: $urlNutricionistHired + $IDuser,
            type: 'GET',
            headers: {
                'Authorization': $token
            },
            success: function ($nutricionist) {
                
                $('.messageErrorNutrist').css('display', 'none');
                $.ajax({
                    url: $urlSearchNutricionist + $nutricionist,
                    type: 'GET',
                    headers: {
                        'Authorization': $token
                    },
                    success: function ($nutricionistData) {
                        $IDnutricionist = $nutricionistData.id;
                        $('.nutrist_cont').append('<div class="nutrist">\
                                        <h5 id="nameNutri">' + $nutricionistData.name + '</h5>\
                                        <i class="far fa-envelope"></i>\
                                        <i class="fas fa-trash-alt"></i>\
                                    </div>\
                                    <div class="diet">\
                                        <p id="fichero">Archivo dieta personal</p>\
                                    </div>')
                    }
                });
            },
            error: function () {
                $('.messageErrorNutrist').css('display', 'block');
            }
        });

        $('.btn_add').on('click', function () {
            $pcNutricionist = $(this).prev().val();

            $.ajax({
                url: $urlSearchNutricionistCP + $pcNutricionist,
                type: 'GET',
                headers: {
                    'Authorization': $token
                },
                success: function ($nutricionists) {
                    $('.nutritionists').empty();
                    $('.messageError').css('display', 'none');
                    if ($nutricionists != '') {
                        $.each($nutricionists, function ($i, $nutricionist) {
                            $('.nutritionists').append('<div class="infoNutri"><a href="http://localhost:3000/fichaNutri?id=' + $nutricionist.id + '"\
                                                <p id="name">' + $nutricionist.name + '</p>\
                                                <p id="dire">' + $nutricionist.companyDirection + '</p>\
                                                <p id="titul"></p>\
                                                <div class="score_user">\
                                                    <img class="forkValue" id="fork1" value="5" src="/images/tenedor-gold.svg"\
                                                        alt="tenedor" style="width: 15px; height: 30px;">\
                                                    <img class="forkValue" id="fork2" value="4" src="/images/tenedor-gold.svg"\
                                                        alt="tenedor" style="width: 15px; height: 30px;">\
                                                    <img class="forkValue" id="fork3" value="3" src="/images/tenedor-gold.svg"\
                                                        alt="tenedor" style="width: 15px; height: 30px;">\
                                                    <img class="forkValue" id="fork4" value="2" src="/images/tenedor-black.svg"\
                                                        alt="tenedor" style="width: 15px; height: 30px;">\
                                                    <img class="forkValue" id="fork5" value="1" src="/images/tenedor-black.svg"\
                                                        alt="tenedor" style="width: 15px; height: 30px;"></a>\
                                                </div>')
                        });
                    } else {
                        $('.nutritionists').empty();
                        $('.messageError').css('display', 'block');
                    }
                }
            });
        });

    } else {
        //Parte nutricionista
        $('.user_container').css('display', 'none');
        $('.mt-4').html('Mis usuarios');

        $.ajax({
            url: $urlSearchUsersNutricionist + $IDuser,
            type: 'GET',
            headers: {
                'Authorization': $token
            },
            success: function ($users) {
                $.each($users, function ($i, $user) {
                    $.ajax({
                        url: $urlUser + $user,
                        type: 'GET',
                        headers: {
                            'Authorization': $token
                        },
                        success: function ($user) {
                            $('.mt-4').after('<div class="user_cont">\
                                                    <div class="nutrist">\
                                                        <h5 id="nameNutri">' + $user.name + '</h5>\
                                                        <i class="far fa-envelope"></i>\
                                                        <i class="fas fa-trash-alt"></i>\
                                                    </div>\
                                                    <div class="diet">\
                                                        <p id="fichero">Archivo dieta personal</p>\
                                                    </div></div>')
                        }
                    });
                });
            }
        });

        $('.btn_see').on('click', () => {
            window.location.href = '/fichaNutri?id=' + $IDuser;
        });
    }

    $('.btn_saveDes').on('click', () => {
        $titleNutricionist = $('#input_titul').val();
        $descNutricionist = $('.desc_pas').val();

        if ($descNutricionist != '') {
            $.ajax({
                url: $urlModifyDataNutricionist + 'modifyNutricionist/id---' + $IDuser + '___description---' + $descNutricionist,
                type: 'PUT',
                headers: {
                    'Authorization': $token
                },
                success: function () {}
            });
        }

        if ($titleNutricionist != '') {
            $JSNONnutricionistDegree = {
                'nutricionistId': eval($IDuser),
                'name': $titleNutricionist,
                'year': null,
                'school': null,
                'description': null
            }

            $JSNONnutricionistDegree = JSON.stringify($JSNONnutricionistDegree);
            
            $.ajax({
                url: 'http://localhost:8080/api/nutricionist/addNutricionistDegree',
                data: $JSNONnutricionistDegree,
                type: 'POST',
                headers: {
                    'Authorization': $token
                },
                contentType: "application/json",
                dataType: "json",
                success: function(){
                }
            });
        }
    });
});