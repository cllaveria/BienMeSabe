/**
 * @fileoverview Implementació de la validació del token, per verificar si l'usuari està registrat o s'ha caducat la sessió. 
 * 
 * <p> History </p>
 * 0.1 - Implementació de la validació del token.
 *  
 * @version     0.1
 * @author      Sergio Asensio Ruiz 
 * @copyright   bienmesabe.com
 * 
 */

/** 
 * @var $localToken 
 * @type {String}
 * @description Variable per emmagatzemar el token emmagatzemat en localStorage amb el nom 'token'.
 */
let $localToken = localStorage.getItem('token');
/** 
 * @var $date 
 * @type {Date}
 * @description Variable per emmagatzemar la data actual del nostre sistema.
 */
let $date = new Date();
/** 
 * @var $dateModify 
 * @type {Date}
 * @description Variable per emmagatzemar la data actual del nostre sistema per a després modificar-la amb la data de caducitat del token.
 */
let $dateModify = new Date();
/** 
 * @var $arrayValidityToken 
 * @type {Array}
 * @description Variable array per emmagatzemar la data rebuda de la crida AJAX per separat, en dies i hores.
 */
let $arrayValidityToken;
/** 
 * @var $arrayDate 
 * @type {Array}
 * @description Variable array per emmagatzemar la data rebuda de la crida AJAX per separat, en format día, mes i any.
 */

let $arrayDate;
/** 
 * @var $arrayHours 
 * @type {Array}
 * @description Variable array per emmagatzemar la hora rebuda de la crida AJAX per separat, en format hora, minuts i segons.
 */
let $arrayHours;
/** 
 * @var $boolean 
 * @type {boolean}
 * @description Variable booleana per verificar si la sessió de l'usuari a caducat o no.
 */
let $boolean;

token();

/**
 * @function token
 * @description Validarem el token rebrem del servidor la data i hora de caducitat.
 */
function token() {
    if ($localToken != null) {
        let $token = $localToken.substr(7, );
        $.ajax({
            url: 'http://localhost:8080/api/tokenValidity/validateDate/',
            type: 'GET',
            async: false,
            data: {
                token: $token
            },
            success: function ($dateToken) {
                $arrayValidityToken = $dateToken.split(' ');
                $arrayDate = $arrayValidityToken[0].split('-');
                $arrayHours = $arrayValidityToken[1].split(':');

                $dateModify.setHours($arrayHours[0]);
                $dateModify.setMinutes($arrayHours[1]);
                $dateModify.setDate($arrayDate[0]);
                if ($date < $dateModify) {
                    $boolean = true;
                } else {
                    $boolean = false;
                }
            }
        });
        if ($boolean == true) {
            $('.loginNoShow').css('display', 'none');
            $('#register').css('display', 'none');
            $('.btn_user').css('display', 'inline-block');

        } else {
            $('.loginNoShow').css('display', 'inline-block');
            $('#register').css('display', 'inline-block');
            $('.btn_user').css('display', 'none');

            localStorage.removeItem('token');
            localStorage.removeItem('id');
        }
    }

    return $boolean;
}

$(document).ready(function () {

    /** 
     * @var $localToken 
     * @type {String}
     * @description Variable per emmagatzemar el token emmagatzemat en localStorage amb el nom 'token'.
     */
    let $localToken = localStorage.getItem('token');
    /** 
     * @var $date 
     * @type {Date}
     * @description Variable per emmagatzemar la data actual del nostre sistema.
     */
    let $date = new Date();
    /** 
     * @var $dateModify 
     * @type {Date}
     * @description Variable per emmagatzemar la data actual del nostre sistema per a després modificar-la amb la data de caducitat del token.
     */
    let $dateModify = new Date();
    /** 
     * @var $arrayValidityToken 
     * @type {Array}
     * @description Variable array per emmagatzemar la data rebuda de la crida AJAX per separat, en dies i hores.
     */
    let $arrayValidityToken;
    /** 
     * @var $arrayDate 
     * @type {Array}
     * @description Variable array per emmagatzemar la data rebuda de la crida AJAX per separat, en format día, mes i any.
     */

    let $arrayDate;
    /** 
     * @var $arrayHours 
     * @type {Array}
     * @description Variable array per emmagatzemar la hora rebuda de la crida AJAX per separat, en format hora, minuts i segons.
     */
    let $arrayHours;
    /** 
     * @var $boolean 
     * @type {boolean}
     * @description Variable booleana per verificar si la sessió de l'usuari a caducat o no.
     */
    let $boolean;

    token();

    /**
     * @function token
     * @description Validarem el token rebrem del servidor la data i hora de caducitat.
     */
    function token() {
        if ($localToken != null) {
            let $token = $localToken.substr(7, );
            $.ajax({
                url: 'http://localhost:8080/api/tokenValidity/validateDate/',
                type: 'GET',
                async: false,
                data: {
                    token: $token
                },
                success: function ($dateToken) {
                    $arrayValidityToken = $dateToken.split(' ');

                    $arrayDate = $arrayValidityToken[0].split('-');
                    $arrayHours = $arrayValidityToken[1].split(':');

                    $dateModify.setHours($arrayHours[0]);
                    $dateModify.setMinutes($arrayHours[1]);
                    $dateModify.setDate($arrayDate[0]);
                    if ($date < $dateModify) {
                        $boolean = true;
                    } else {
                        $boolean = false;
                    }
                }
            });
            if ($boolean == true) {
                $('.loginNoShow').css('display', 'none');
                $('#register').css('display', 'none');
                $('.btn_user').css('display', 'inline-block');
            } else {
                $('.loginNoShow').css('display', 'inline-block');
                $('#register').css('display', 'inline-block');
                $('.btn_user').css('display', 'none');

                localStorage.removeItem('token');
                localStorage.removeItem('id');
            }
        }
        return $boolean;
    }

    $('.btn_checkOut').on('click', () => {
        localStorage.removeItem('token');
        localStorage.removeItem('id');
        window.location = '/';
    });

});