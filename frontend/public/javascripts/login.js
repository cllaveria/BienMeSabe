/**
 * @fileoverview Formulari per l'inici de sessió dels usuaris: 
 * <p> 1) Poder iniciar sessió amb l'alies o amb l'email. </p>
 * <p> 2) Comprovació amb expressió regular de l'email. </p>
 * <p> 3) Desar el token rebut en localStorage + ID de l'usuari. </p>
 * 
 * <p> History </p>
 * 0.1 - Implementació del login.
 *  
 * @version     0.1
 * @author      Sergio Asensio Ruiz 
 * @copyright   bienmesabe.com
 * 
 */

$(document).ready(function () {
    /**
     * @constant $urlLogin
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor per validar el registre.
     */    
    const $urlLogin = 'http://localhost:8080/api/user/loginUser?data=password---';
    /** 
     * @var $pswrd
     * @type {String}
     * @description Variable de tipus String per emmagatzemar la contrasenya de l'usuari.
     */
    let $pswrd;
    /** 
     * @var $login
     * @type {String}
     * @description Variable de tipus String per emmagatzemar l'alies o l'email de l'usuari, segons el que introdueixi en el camp requerit.
     */
    let $login;

    /**
     * @type {jQuery}
     * @type mouseenter
     * @method on
     * @listens viewPass - ID de la casella per veure el password.
     * @description Quan es passa per sobre de la icona de la contrasenya, la contrasenya escrita per l'usuari passa a ser 
     * visible canviant l'atribut type de l'id pswrd per un string vuit.
     */
    $('#viewPass').on('mouseenter', () => {
        $('#pswrd').attr('type', '');
    });

    /**
     * @type {jQuery}
     * @type mouseleave
     * @method on
     * @listens viewPass - ID de la casella per veure el password.
     * @description Quan es passa per sobre de la icona de la contrasenya, la contrasenya escrita per l'usuari deixa de ser 
     * visible canviant l'atribut type de l'id pswrd per password.
     */
    $('#viewPass').on('mouseleave', () => {
        $('#pswrd').attr('type', 'password');
    });

    /**
     * @type {jQuery}
     * @type submit
     * @method on
     * @listens loginForm - ID del botó del formulari del login.
     * @description Quan l'usuari polsa sobre el botó Registrar del formulari del login, es fa el procés de verificació de dades i si tot és correcte, rebre el token i l'id de l'usuari per emmagatzemar-lo en localStorage.
     */
    $('#loginForm').on('submit', (e) => {
        e.preventDefault();

        $email = $('#email').val();
        $pswrd = hex_md5($('#pswrd').val());

        if (/^([\w.%+-]+)@([\w-]+\.)+([\w]{2,})$/.test($email)) {
            $login = '___email---' + $('#email').val();
        } else {
            $login = '___alias---' + $('#email').val();
        }
        $.ajax({
            url: $urlLogin + $pswrd + $login,
            type: 'POST',
            success: function ($token) {
                if ($token != '') {
                    localStorage.setItem('id', $token.id);
                    localStorage.setItem('token', $token.token);

                    window.location = '/';
                } else {
                    $('.messageError').css('display', 'block')
                }
            }
        });
    });
});