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
     * @const $urlUser
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor per recuperar al nutricionista contractat.
     */
    const $urlNutricionistHired = 'http://localhost:8080/api/user/getUserNutricionist/'
    /**
     * @const $urlRemoveNutricionist
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor per deixar de rebre els serveis del nutricionista.
     */
    const $urlRemoveNutricionist = 'http://localhost:8080/api/user/removeUserAssignmentToNutricionist/';
    /**
     * @const $urlAddNutricionist
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor per contractar els serveis del nutricionista.
     */
    const $urlAddNutricionist = 'http://localhost:8080/api/user/createUserAssignmentToNutricionist/';
    /**
     * @constant $urlAllUsers 
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor i recuperar tots els usuaris.
     */
    const $urlAllUsers = 'http://localhost:8080/api/user/getUsers';
    /**
     * @constant $urlAddComment 
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor i poder comentar al nutricionista.
     */
    const $urlAddComment = 'http://localhost:8080/api/user/createNutricionistComment/';
    /**
     * @constant $urlAddAssessment 
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor i poder valorar al nutricionista.
     */
     const $urlAddAssessment = 'http://localhost:8080/api/user/createNutricionistAssessment/';
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
     * @var $JSONComment 
     * @type {JSON}
     * @description Variable per emmagatzemar el comentari que ha fet l'usuari.
     */
    let $JSONComment;
    /** 
     * @var $JSONAssessment 
     * @type {JSON}
     * @description Variable per emmagatzemar la valoració que ha fet l'usuari.
     */
    let $JSONAssessment;
    /** 
     * @var $IDnutricionist
     * @description Variable de tipus String per emmagatzemar l'ID del nutricionista.
     */
    let $IDnutricionist;
    /** 
     * @var $allUsers 
     * @type {Array}
     * @description Array per emmagatzemar tots els usuaris de la BBDD.
     */
    let $allUsers = [];
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
        url: $urlDateNutricionist + $idNutricionist,
        type: 'GET',
        async: false,
        headers: {
            'Authorization': $token
        },
        success: function ($nutricionist) {
            $IDnutricionist = $nutricionist.id;
            $.ajax({
                url: $urlNutricionistHired + $IDuser,
                type: 'GET',
                headers: {
                    'Authorization': $token
                },
                success: function () {
                    $('.btn_fire').css('display', 'block');
                    $('.btn_hire').css('display', 'none');
                },
                error: function () {
                    $('.btn_fire').css('display', 'none');
                    $('.btn_hire').css('display', 'block');
                }

            })

            $('#nameNutri').html($nutricionist.name + ' ' + $nutricionist.surname);
            $('#direccion').html($nutricionist.companyDirection);
            $('.descNutri').append($nutricionist.nutricionistDescription);

            let $forks = getForks($nutricionist.nutricionistAssessment);
            $('.score_rec').append($forks);

            $.each($nutricionist.nutricionistDegree, function ($i, $nutricionistDegree) {
                $('#titulacion').html($nutricionistDegree.name)
            });

            $.each($nutricionist.comment, function ($i, $commentsNutricionist) {
                for (let x = 0; x < $allUsers.length; x++) {
                    if ($commentsNutricionist.userId == $allUsers[x][0]) {
                        $('.comments_users').append('<div class="comment_user ">\
                                                        <p id="alias">' + $allUsers[x][4] + '</p>\
                                                        <p id="comment">' + $commentsNutricionist.commentValue + '</p>');
                    }
                }
            });
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
     * @function getForks
     * @description Concatenem una cadena per inserir en el DOM i mostrar la puntuació del nutricionista.
     * @param {string} $forks Número de forquilles (puntuació) que te el nutricionista.
     * @return {string}
     */
    function getForks($forks) {
        let $insertForks = '<div class="score_rec">';
        for (let i = 0; i < $forks; i++) {
            $insertForks = $insertForks.concat('<img src="/images/tenedor-gold.svg" alt="tenedor" style="width: 20px; height: 40px;">');
        }
        if ($forks != 5) {
            let $numberForks = 5 - $forks;
            for (let i = 0; i < $numberForks; i++) {
                $insertForks = $insertForks.concat('<img src="/images/tenedor-black.svg" alt="tenedor" style="width: 20px; height: 40px;">');
            }
        }
        $insertForks = $insertForks.concat('</div>');
        return $insertForks;
    }

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
     * @listens .btn_fire - Botó per prescindir dels serveis del nutricionista.
     * @description Funció per deixar de rebre els serveis del nutricionista.
     */
    $('.btn_fire').on('click', () => {
        $('#fireNutricionist').modal('show');
    })

    /**
     * @type {jQuery}
     * @type click
     * @method on
     * @listens .btn_hire - Botó per contractar dels serveis del nutricionista.
     * @description Funció per contractar els serveis del nutricionista.
     */
    $('.btn_hire').on('click', () => {
        $('#hireNutricionist').modal('show');
    })

    /**
     * @type {jQuery}
     * @type click
     * @method on
     * @listens .btn_acceptHireNutricionist - Botó per confirmar que es vol contractar dels serveis del nutricionista.
     * @description Funció per confirmar que es vol contractar els serveis del nutricionista.
     */
    $('.btn_acceptFireNutricionist').on('click', () => {
        $.ajax({
            url: $urlRemoveNutricionist + $IDnutricionist + '___' + $IDuser,
            type: 'DELETE',
            headers: {
                'Authorization': $token
            },
            success: function () {
                location.reload();
            }
        })
    });

    /**
     * @type {jQuery}
     * @type click
     * @method on
     * @listens .btn_acceptFireNutricionist - Botó per confirmar que es vol prescindir dels serveis del nutricionista.
     * @description Funció per confirmar que es vol prescindir dels serveis del nutricionista.
     */
    $('.btn_acceptHireNutricionist').on('click', () => {
        $.ajax({
            url: $urlAddNutricionist + $IDnutricionist + '___' + $IDuser,
            type: 'POST',
            headers: {
                'Authorization': $token
            },
            success: function () {
                location.reload();
            }
        })
    });

    /**
     * @type {jQuery}
     * @type click
     * @method on
     * @listens .button - Botó per inserir comentari i valoració.
     * @description Funció per inserir a la BBDD el comentari i la valoració que ha realtizat l'usuari sobre el nutricionista, verificant previament si hi ha comentari o puntuació o ambes coses.
     */
    $('.btn_send').on('click', () => {
        $comment = $('#textComment').val();

        if ($comment != '' || $score != '') {
            if ($comment != '') {
                $JSONComment = {
                    'nutricionistId': eval($IDnutricionist),
                    'commentValue': $comment,
                    'userId': eval($IDuser)
                }

                $JSONComment = JSON.stringify($JSONComment);
                $.ajax({
                    url: $urlAddComment,
                    data: $JSONComment,
                    type: 'POST',
                    async: false,
                    headers: {
                        'Authorization': $token
                    },
                    contentType: "application/json",
                    dataType: "json",
                    success: function ($insertComment) {
                        if ($insertComment == '') {
                            $booleanComment = false;
                        } else {
                            $booleanComment = true;
                        }
                    }
                });
            }

            if ($score != '') {

                $JSONAssessment = {
                    'nutricionistId': eval($IDnutricionist),
                    'assessmentValue': $comment,
                    'userId': eval($IDuser)
                }

                $JSONAssessment = JSON.stringify($JSONAssessment);
                $.ajax({                    
                    url: $urlAddAssessment,
                    data: $JSONAssessment,
                    type: 'POST',
                    async: false,
                    headers: {
                        'Authorization': $token
                    },
                    contentType: "application/json",
                    dataType: "json",
                    success: function ($score) {
                        if ($score == '') {
                            $booleanAssessment = false;
                        } else {
                            $booleanAssessment = true;
                        }
                    }
                });
            }
            if ($booleanComment == true && $booleanAssessment == true) {
                location.reload();
            }
        }
    });
});