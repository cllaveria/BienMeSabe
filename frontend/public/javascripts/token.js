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
 * @description Variable per emmagatzemar el token emmagatzemat en localStorage amb el nom 'token'.
 */
/** 
 * @var $date 
 * @description Variable per emmagatzemar la data actual del nostre sistema.
 */
/** 
 * @var $arrayValidityToken 
 * @description Variable array per emmagatzemar la data rebuda de la crida AJAX per separat, en dies i hores.
 */
/** 
 * @var $arrayDate 
 * @description Variable array per emmagatzemar la data rebuda de la crida AJAX per separat, en format día, mes i any.
 */
/** 
 * @var $arrayHours 
 * @description Variable array per emmagatzemar la hora rebuda de la crida AJAX per separat, en format hora, minuts i segons.
 */
/** 
 * @var $boolean 
 * @description Variable booleana per verificar si la sessió de l'usuari a caducat o no.
 */


let $localToken = localStorage.getItem('token'),
    $date = new Date(),
    $arrayValidityToken,
    $arrayDate,
    $arrayHours,
    $boolean;

token();

function token() {

    if ($localToken != '') {
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

                if ($date.getHours() > $arrayHours[0]) {
                    if ($date.getMinutes() > $arrayHours[1]) {
                        $boolean = false;
                    }
                } else if ($date.getDate() > $arrayDate[0]) {
                    $boolean = false;
                } else {
                    $boolean = true;
                }
            }
        });

        if ($boolean == true) {
            $('#login').css('display', 'none');
            $('#register').css('display', 'none');
            $('.btn_user').css('display', 'inline-block');
        } else {
            $('#login').css('display', 'inline-block');
            $('#register').css('display', 'inline-block');
            $('.btn_user').css('display', 'none');

            localStorage.removeItem('token');
            localStorage.removeItem('id');
        }
    }

    return $boolean;
}
$(document).ready(function () {
    
    let $localToken = localStorage.getItem('token'),
        $date = new Date(),
        $arrayValidityToken,
        $arrayDate,
        $arrayHours,
        $boolean;

    token();

    function token() {

        if ($localToken != '') {
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

                    if ($date.getHours() > $arrayHours[0]) {
                        if ($date.getMinutes() > $arrayHours[1]) {
                            $boolean = false;
                        }
                    } else if ($date.getDate() > $arrayDate[0]) {
                        $boolean = false;
                    } else {
                        $boolean = true;
                    }
                }
            });

            if ($boolean == true) {
                $('#login').css('display', 'none');
                $('#register').css('display', 'none');
                $('.btn_user').css('display', 'inline-block');
            } else {
                $('#login').css('display', 'inline-block');
                $('#register').css('display', 'inline-block');
                $('.btn_user').css('display', 'none');

                localStorage.removeItem('token');
                localStorage.removeItem('id');
            }
        }

        return $boolean;
    }
});