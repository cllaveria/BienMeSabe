/**
 * @fileoverview -Formulari per modificar les dades tant dels usuaris com dels nutricionistes: 
 * <p> 1) Comprovació amb expressió regular amb email i password.</p>
 * <p> 2) Comprovació de si el NIF/DNI, NIE o CIF estan correctament introduïts.</p>
 * <p> 3) Encriptació del la password.</p>
 * <p> 4) Comprovació amb la BBDD de si l'Àlies i l'email estan ja introduïts a la BBDD.</p>
 * - Inserció de les modificacions a la BBDD de l'usuari o nutricionista que vulgui modificar les seves dades.</p>
 * 
 * <p> History</p>
 * 0.1 - Implementació del formulari per modificar les dades.  
 *  
 * @version     0.1
 * @author      Sergio Asensio Ruiz 
 * @copyright   bienmesabe.com
 * 
 */

$(document).ready(function () {
    /**
     * @constant $urlModifyDataUser 
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor per emmagatzemar les modificaciones de les dades de l'usuari.
     */
    const $urlModifyDataUser = 'http://localhost:8080/api/user/';
    /**
     * @constant $urlModifyDataNutricionist 
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor per emmagatzemar les modificaciones de les dades del nutricionista.
     */
    const $urlModifyDataNutricionist = 'http://localhost:8080/api/nutricionist/';
    /**
     * @constant $url 
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor.
     */
    const $url = 'http://localhost:8080/api/';
    /** 
     * @var $user 
     * @type {String}
     * @description Variable per emmagatzemar les dades de l'usuari/nutricionista que rebem per AJAX.
     */
    let $user;
    /** 
     * @var $alias 
     * @type {String}
     * @description Variable per emmagatzemar l'Àlies de l'usuari/nutricionista. 
     */
    let $alias = '';
    /** 
     * @var $email 
     * @type {String}
     * @description Variable per emmagatzemar l'email de l'usuari/nutricionista.
     */
    let $email = '';
    /** 
     * @var $pass 
     * @type {String}
     * @description Variable per emmagatzemar la nova contrasenya de l'usuari/nutricionista. 
     */
    let $pass = '';
    /** 
     * @var $pswrd_1 
     * @type {String} 
     * @description Variable per emmagatzemar la antiga contrasenya de l'usuari/nutricionista. 
     */
    let $pswrd_1;
    /** 
     * @var $pswrd_2 
     * @type {String}
     * @description Variable per emmagatzemar la nova contrasenya de l'usuari/nutricionista. 
     */
    let $pswrd_2;
    /** 
     * @var $pswrd_3 
     * @type {String}
     * @description Variable per emmagatzemar la nova contrasenya de l'usuari/nutricionista. 
     */
    let $pswrd_3;
    /** 
     * @var $name 
     * @type {String}
     * @description Variable per emmagatzemar el nom de l'usuari/nutricionista. 
     */
    let $name = '';
    /** 
     * @var $surnames 
     * @type {String}
     * @description Variable per emmagatzemar el cognom de l'usuari/nutricionista. 
     */
    let $surnames = '';
    /** 
     * @var $phone 
     * @type {number}
     * @description Variable per emmagatzemar el número de telèfon de l'usuari/nutricionista. 
     */
    let $phone = '';
    /** 
     * @var $nif 
     * @type {String}
     * @description Variable per emmagatzemar el NIF/DNI, NIE o CIF del nutricionista. 
     */
    let $nif = '';
    /** 
     * @var $document 
     * @type {String}
     * @description Variable inicialitzada en una String 'nif' per emmagatzemar el tipus de documentació i es modifica segons el que ha seleccionat l'usuari, si NIF/DNI, NIE o CIF.
     */
    let $document = 'nif';
    /** 
     * @var $pc 
     * @type {number}
     * @description Variable per emmagatzemar el codi postal de la ciutat del nutricionista. 
     */
    let $pc = '';
    /** 
     * @var $city 
     * @type {String}
     * @description Variable per emmagatzemar la ciutat del nutricionista. 
     */
    let $city = '';
    /** 
     * @var $company 
     * @type {String}
     * @description Variable per emmagatzemar el nom d'empresa del nutricionista. 
     */
    let $company = '';
    /** 
     * @var $direction 
     * @type {String}
     * @description Variable per emmagatzemar la direcció d'empresa del nutricionista. 
     */

    let $direction = '';
    /** 
     * @var $businessPhone 
     * @type {number}
     * @description Variable per emmagatzemar el número de telèfon d'empresa del nutricionista. 
     */
    let $businessPhone = '';
    /** 
     * @var $concat 
     * @type {String}
     * @description Variable inicialitzada buida per concatenar la crida AJAX per la inserció de les modificacions de l'usuari.
     */
    let $concat = '';
    /** 
     * @var $concatNutricionst 
     * @type {String}
     * @description Variable inicialitzada buida per concatenar la crida AJAX per la inserció de les modificacions del nutricionista.
     */
    let $concatNutricionst = '';
    /** 
     * @var $booleanName 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si el nom està introduït correctament.
     */
    let $booleanName = true;
    /** 
     * @var $booleanSurname 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si el cognom està introduït correctament.
     */
    let $booleanSurname = true;
    /** 
     * @var $booleanPhone 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si el teléfon està introduït correctament.
     */
    let $booleanPhone = true;
    /** 
     * @var $booleanAlias 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si l'Àlies està introduït correctament.
     */
    let $booleanAlias = true;
    /** 
     * @var $booleanEmail 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si l'email està introduït correctament.
     */
    let $booleanEmail = true;
    /** 
     * @var $booleanPassword_1 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si el password antic està introduït correctament.
     */
    let $booleanPassword_1 = true;
    /** 
     * @var $booleanPassword_2 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si el nou password està introduït correctament.
     */
    let $booleanPassword_2 = true;
    /** 
     * @var $booleanPassword_3 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si la password es igual a na nova password.
     */
    let $booleanPassword_3 = true;
    /** 
     * @var $booleanPc 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si el codi postal està introduït correctament.
     */
    let $booleanPc = true;
    /** 
     * @var $booleanNif 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si el NIF/DNI, NIE o CIF estan introduïts correctament.
     */
    let $booleanNif = true;
    /** 
     * @var $booleanCompany 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si el nom de la companyia està introduït correctament.
     */
    let $booleanCompany = true;
    /** 
     * @var $booleanCity 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si la població està introduït correctament.
     */
    let $booleanCity = true;
    /** 
     * @var $booleanBusinessPhone 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si el telèfon d'empresa està introduït correctament.
     */
    let $booleanBusinessPhone = true;
    /** 
     * @var $booleanDirection 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si la direció està introduït correctament.
     */
    let $booleanDirection = true;
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
        url: 'http://localhost:8080/api/user/getUserByIdWithAllProperties/' + $IDuser,
        type: 'GET',
        async: false,
        headers: {
            'Authorization': $token
        },
        success: function ($userAjax) {
            $user = $userAjax;
        }
    });

    console.log($user)
    $('#name').html($user.name);
    $('#surname').html($user.surname);
    $('#phone').html($user.phone)
    $('#alias').html($user.alias);
    $('#email').html($user.email);
    $('#nif').html($user.nif);
    $('#pc').html($user.companyPostalCode);
    $('#city').html($user.companyCity);
    $('#direction').html($user.companyDirection);
    $('#company').html($user.companyName);
    $('#business_phone').html($user.companyPhone);

    if ($user.type != 2) {
        $('#registerNutritionist').css('display', 'none');
    }

    /**
     * @type {jQuery}
     * @method blur
     * @listens name - ID de l'input name.
     * @description Quan l'usuari surt de l'input name es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     */
    $('#input_name').blur(() => {
        $name = $('#input_name').val();

        if ($name != '') {
            if ($name.length < 5 || $name.length > 50) {
                changeIconsError($('#name'), $('#iconInfoName'), $('#iconExcName'), $('#iconCheckName'));
                $booleanName = false;
            } else {
                changeIconsCheck($('#name'), $('#iconInfoName'), $('#iconExcName'), $('#iconCheckName'));
                $booleanName = true;
            }
        } else {
            changeIconsInfo($('#name'), $('#iconInfoName'), $('#iconExcName'), $('#iconCheckName'));
            $booleanName = true;
        }
    });

    /**
     * @type {jQuery}
     * @method blur
     * @listens surname - ID de l'input surname.
     * @description Quan l'usuari surt de l'input surname es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     */
    $('#input_surnames').blur(() => {
        $surnames = $('#input_surnames').val();

        if ($surnames != "") {
            if ($surnames.length < 5 || $surnames.length > 100) {
                changeIconsError($('#input_surnames'), $('#iconInfoSurnames'), $('#iconExcSurnames'), $('#iconCheckSurnames'));
                $booleanSurname = false;
                console.log($booleanSurname)
            } else {
                changeIconsCheck($('#input_surnames'), $('#iconInfoSurnames'), $('#iconExcSurnames'), $('#iconCheckSurnames'));
                $booleanSurname = true;
            }
        } else {
            $booleanSurname = true;
            changeIconsInfo($('#input_surnames'), $('#iconInfoSurnames'), $('#iconExcSurnames'), $('#iconCheckSurnames'));
        }
    });

    /**
     * @type {jQuery}
     * @method blur
     * @listens phone - ID de l'input phone.
     * @description Quan l'usuari surt de l'input phone es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     */
    $('#input_phone').blur(() => {
        $phone = $('#input_phone').val();

        if ($phone != "") {
            if ($phone.length != 9) {
                changeIconsError($('#input_phone'), $('#iconInfoPhone'), $('#iconExcPhone'), $('#iconCheckPhone'));
                $booleanPhone = false;
            } else {
                changeIconsCheck($('#input_phone'), $('#iconInfoPhone'), $('#iconExcPhone'), $('#iconCheckPhone'));
                $booleanPhone = true;
            }
        } else {
            changeIconsInfo($('#input_phone'), $('#iconInfoPhone'), $('#iconExcPhone'), $('#iconCheckPhone'));
            $booleanPhone = true;
        }
    });

    /**
     * @type {jQuery}
     * @type mouseenter
     * @method on
     * @listens viewPass_1 - ID de la casella per veure el password actual.
     * @description Quan es passa per sobre de la icona de la contrasenya, la contrasenya escrita per l'usuari passa a ser 
     * visible canviant l'atribut type de l'id input_psdw_1 per un string vuit.
     */
    $('#viewPass_1').on('mouseenter', () => {
        $('#input_psdw_1').attr('type', '');
    });

    /**
     * @type {jQuery}
     * @type mouseleave
     * @method on
     * @listens viewPass_1 - ID de la casella per veure el password actual.
     * @description Quan es passa per sobre de la icona de la contrasenya, la contrasenya escrita per l'usuari deixa de ser 
     * visible canviant l'atribut type de l'id input_psdw_1 per password.
     */
    $('#viewPass_1').on('mouseleave', () => {
        $('#input_psdw_1').attr('type', 'password');
    });

    /**
     * @type {jQuery}
     * @method blur
     * @listens input_psdw_1 - ID de l'input de la antiga password.
     * @description Quan l'usuari surt de l'input password es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     */
    $('#input_psdw_1').blur(() => {
        $pswrd_1 = $('#input_psdw_1').val();

        if ($pswrd_1 != '') {
            $pass = hex_md5($pswrd_1);
            if ($user.password == $pass) {
                changeIconsCheck($('#input_psdw_1'), $('#iconInfoPswrd_1'), $('#iconExcPswrd_1'), $('#iconCheckPswrd_1'));
                $booleanPassword_1 = true;
            } else {
                changeIconsError($('#input_psdw_1'), $('#iconInfoPswrd_1'), $('#iconExcPswrd_1'), $('#iconCheckPswrd_1'));
                $booleanPassword_1 = false;
            }
        } else {
            changeIconsInfo($('#input_psdw_1'), $('#iconInfoPswrd_1'), $('#iconExcPswrd_1'), $('#iconCheckPswrd_1'));
            $booleanPassword_1 = true;
        }
    });

    /**
     * @type {jQuery}
     * @type mouseenter
     * @method on
     * @listens viewPass_2 - ID de la casella per veure la nova password.
     * @description Quan es passa per sobre de la icona de la contrasenya, la contrasenya escrita per l'usuari passa a ser 
     * visible canviant l'atribut type de l'id input_psdw_2 per un string vuit.
     */
    $('#viewPass_2').on('mouseenter', () => {
        $('#input_psdw_2').attr('type', '');
    });

    /**
     * @type {jQuery}
     * @type mouseleave
     * @method on
     * @listens viewPass_2 - ID de la casella per veure la nova password.
     * @description Quan es passa per sobre de la icona de la contrasenya, la contrasenya escrita per l'usuari deixa de ser 
     * visible canviant l'atribut type de l'id input_psdw_2 per password.
     */
    $('#viewPass_2').on('mouseleave', () => {
        $('#input_psdw_2').attr('type', 'password');
    });

    /**
     * @type {jQuery}
     * @method blur
     * @listens input_psdw_2 - ID de l'input de la nova password.
     * @description Quan l'usuari surt de l'input password es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     */
    $('#input_psdw_2').blur(() => {
        $pswrd_2 = $('#input_psdw_2').val();

        if ($pswrd_2 != '') {
            $pass = hex_md5($pswrd_2);
            if (!/^\b[A-Z]{1}\w{5,}\d{2,}\W{1,}$/.test($pswrd_2)) {
                changeIconsError($('#input_psdw_2'), $('#iconInfoPswrd_2'), $('#iconExcPswrd_2'), $('#iconCheckPswrd_2'));
                $booleanPassword_2 = false;
            } else {
                changeIconsCheck($('#input_psdw_2'), $('#iconInfoPswrd_2'), $('#iconExcPswrd_2'), $('#iconCheckPswrd_2'));
                $booleanPassword_2 = true;
            }
        } else {
            changeIconsInfo($('#input_psdw_2'), $('#iconInfoPswrd_2'), $('#iconExcPswrd_2'), $('#iconCheckPswrd_2'));
            $booleanPassword_2 = true;
        }

    });

    /**
     * @type {jQuery}
     * @type mouseenter
     * @method on
     * @listens viewPass_3 - ID de la casella per veure la repetició de la nova password.
     * @description Quan es passa per sobre de la icona de la contrasenya, la contrasenya escrita per l'usuari passa a ser 
     * visible canviant l'atribut type de l'id input_psdw_3 per un string vuit.
     */
    $('#viewPass_3').on('mouseenter', () => {
        $('#input_psdw_3').attr('type', '');
    });

    /**
     * @type {jQuery}
     * @type mouseleave
     * @method on
     * @listens viewPass_3 - ID de la casella per veure la repetició de la nova password.
     * @description Quan es passa per sobre de la icona de la contrasenya, la contrasenya escrita per l'usuari deixa de ser 
     * visible canviant l'atribut type de l'id input_psdw_3 per password.
     */
    $('#viewPass_3').on('mouseleave', () => {
        $('#input_psdw_3').attr('type', 'password');
    });

    /**
     * @type {jQuery}
     * @method blur
     * @listens input_psdw_3 - ID de l'input per repetir la nova password.
     * @description Quan l'usuari surt de l'input password es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     */
    $('#input_psdw_3').blur(() => {
        $pswrd_3 = $('#input_psdw_3').val();

        if ($pswrd_3 != '' || !$booleanPassword_2 || $pswrd_2 != '') {
            if ($pswrd_2 == $pswrd_3) {
                changeIconsCheck($('#input_psdw_3'), $('#iconInfoPswrd_3'), $('#iconExcPswrd_3'), $('#iconCheckPswrd_3'));
                $booleanPassword_3 = true;
            } else {
                changeIconsError($('#input_psdw_3'), $('#iconInfoPswrd_3'), $('#iconExcPswrd_3'), $('#iconCheckPswrd_3'));
                $booleanPassword_3 = false;
            }
        } else {
            changeIconsInfo($('#input_psdw_3'), $('#iconInfoPswrd_3'), $('#iconExcPswrd_3'), $('#iconCheckPswrd_3'));
            $booleanPassword_3 = true;
        }
    });

    /**
     * @type {jQuery}
     * @method blur
     * @listens input_alias - ID de l'input alias.
     * @description Quan l'usuari surt de l'input alias es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     * També es comprova si ja està registrat a la BBDD.
     */
    $('#input_alias').blur(() => {
        $alias = $('#input_alias').val();
        if ($alias != '') {
            if ($alias.length < 3 || $alias.length > 50) {
                changeIconsError($('#input_alias'), $('#iconInfoAlias'), $('#iconExcAlias'), $('#iconCheckAlias'));
                $booleanAlias = false;
            } else {
                $.ajax({
                    url: $url + 'user/findUserByAlias/' + $alias,
                    type: 'GET',
                    success: function ($data) {
                        if ($data.alias == $alias) {
                            changeIconsError($('input_#alias'), $('#iconInfoAlias'), $('#iconExcAlias'), $('#iconCheckAlias'));
                            $('#iconExcAlias').children().html('El Alias introducido ya está registrado.')
                            $booleanAlias = false;
                        } else {
                            changeIconsCheck($('#input_alias'), $('#iconInfoAlias'), $('#iconExcAlias'), $('#iconCheckAlias'));
                            $booleanAlias = true;
                        }
                    }
                });
            }
        } else {
            changeIconsInfo($('#input_alias'), $('#iconInfoAlias'), $('#iconExcAlias'), $('#iconCheckAlias'));
            $booleanAlias = true;
        }
    });

    /**
     * @type {jQuery}
     * @method blur
     * @listens input_email - ID de l'input email.
     * @description Quan l'usuari surt de l'input email es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     * També es comprova si ja està registrat a la BBDD.
     */
    $('#input_email').blur(() => {
        $email = $('#input_email').val();

        if ($email != '') {
            if (!/^([\w.%+-]+)@([\w-]+\.)+([\w]{2,})$/.test($email)) {
                changeIconsError($('#input_email'), $('#iconInfoEmail'), $('#iconExcEmail'), $('#iconCheckEmail'));
                $('#iconExcEmail').children().html('El email introducido no cumple los requisitos.')
                $booleanEmail = false;
            } else {
                $.ajax({
                    url: $url + 'user/findUserByEmail/' + $email,
                    type: 'GET',
                    success: function (data) {
                        console.log(data)
                        if (data.email == $email) {
                            changeIconsError($('#input_email'), $('#iconInfoEmail'), $('#iconExcEmail'), $('#iconCheckEmail'));
                            $('#iconExcEmail').children().html('El email introducido ya está registrado.');
                            $booleanEmail = false;
                        } else {
                            changeIconsCheck($('#input_email'), $('#iconInfoEmail'), $('#iconExcEmail'), $('#iconCheckEmail'));
                            $booleanEmail = true;
                        }
                    }
                });
            }
        } else {
            changeIconsInfo($('#input_email'), $('#iconInfoEmail'), $('#iconExcEmail'), $('#iconCheckEmail'));
            $booleanEmail = true;
        }
    });

    /**
     * @type {jQuery}
     * @type change
     * @method on
     * @listens document - Detecta canvi en la pàgina.
     * @description Quan l'usuari selecciona el tipus de document (NIF/DNI, NIE o CIF) aquest queda guardat en la variable $document i comprovem l'input.
     */
    $(document).on('change', '#document', () => {
        $document = $('#document option:selected').val();
        checkDocument($document, $('#input_nif').val().toUpperCase());
    });

    /**
     * @type {jQuery}
     * @method blur
     * @listens input_nif - ID de l'input nif.
     * @description Quan l'usuari surt de l'input NIF es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     */
    $('#input_nif').blur(() => {
        $nif = $('#input_nif').val().toUpperCase();

        if ($nif != '') {
            $booleanNif = checkDocument($document, $('#input_nif').val().toUpperCase());
        } else {
            changeIconsInfo($('#nif'), $('#iconInfoNif'), $('#iconExcNif'), $('#iconCheckNif'));
            $booleanNif = true;
        }
    });

    /**
     * @type {jQuery}
     * @method blur
     * @listens input_pc - ID de l'input pc.
     * @description Quan l'usuari surt de l'input pc es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     */
    $('#input_pc').blur(() => {
        $pc = $('#input_pc').val();

        if ($pc != '') {
            if ($pc.length != 5) {
                changeIconsError($('#input_pc'), $('#iconInfoPc'), $('#iconExcPc'), $('#iconCheckPc'));
                $booleanPc = false;
            } else {
                changeIconsCheck($('#input_pc'), $('#iconInfoPc'), $('#iconExcPc'), $('#iconCheckPc'));
                $booleanPc = true;
            }
        } else {
            changeIconsInfo($('#input_pc'), $('#iconInfoPc'), $('#iconExcPc'), $('#iconCheckPc'));
            $booleanPc = true;
        }

    });

    /**
     * @type {jQuery}
     * @method blur
     * @listens input_city - ID de l'input city.
     * @description Quan l'usuari surt de l'input city es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     */
    $('#input_city').blur(() => {
        $city = $('#input_city').val();

        if ($city != '') {
            if ($city.length < 3 || $city.length > 50) {
                changeIconsError($('#input_city'), $('#iconInfoCity'), $('#iconExcCity'), $('#iconCheckCity'));
                $booleanCity = false;
            } else {
                changeIconsCheck($('#input_city'), $('#iconInfoCity'), $('#iconExcCity'), $('#iconCheckCity'));
                $booleanCity = true;
            }
        } else {
            changeIconsInfo($('#input_city'), $('#iconInfoCity'), $('#iconExcCity'), $('#iconCheckCity'));
            $booleanCity = true;
        }

    });

    /**
     * @type {jQuery}
     * @method blur
     * @listens input_direction - ID de l'input direction.
     * @description Quan l'usuari surt de l'input direction es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     */
    $('#input_direction').blur(() => {
        $direction = $('#input_direction').val();

        if ($direction != "") {
            if ($direction.length < 5 || $direction.length > 100) {
                changeIconsError($('#input_direction'), $('#iconInfoDire'), $('#iconExcDire'), $('#iconCheckDire'));
                $booleanDirection = false;
            } else {
                changeIconsCheck($('#input_direction'), $('#iconInfoDire'), $('#iconExcDire'), $('#iconCheckDire'));
                $booleanDirection = true;
            }
        } else {
            changeIconsInfo($('#input_direction'), $('#iconInfoDire'), $('#iconExcDire'), $('#iconCheckDire'));
            $booleanDirection = true;
        }
    });

    /**
     * @type {jQuery}
     * @method blur
     * @listens input_company - ID de l'input company.
     * @description Quan l'usuari surt de l'input company es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     */
    $('#input_company').blur(() => {
        $company = $('#input_company').val();

        if ($company != "") {
            if ($company.length < 2 || $company.length > 50) {
                changeIconsError($('#input_company'), $('#iconInfoComp'), $('#iconExcComp'), $('#iconCheckComp'));
                $booleanCompany = false;
            } else {
                changeIconsCheck($('#input_company'), $('#iconInfoComp'), $('#iconExcComp'), $('#iconCheckComp'));
                $booleanCompany = true;
            }
        } else {
            changeIconsInfo($('#input_company'), $('#iconInfoComp'), $('#iconExcComp'), $('#iconCheckComp'));
            $booleanCompany = true;
        }
    });

    /**
     * @type {jQuery}
     * @method blur
     * @listens input_business_phone - ID de l'input business_phone.
     * @description Quan l'usuari surt de l'input business_phone es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     */
    $('#input_business_phone').blur(() => {
        $businessPhone = $('#input_business_phone').val();

        if ($businessPhone != "") {
            if ($businessPhone.length != 9) {
                changeIconsError($('#input_business_phone'), $('#iconInfoBusPhon'), $('#iconExcBusPhon'), $('#iconCheckBusPhon'));
                $booleanBusinessPhone = false;
            } else {
                changeIconsCheck($('#input_business_phone'), $('#iconInfoBusPhon'), $('#iconExcBusPhon'), $('#iconCheckBusPhon'));
                $booleanBusinessPhone = true;
            }
        } else {
            changeIconsInfo($('#input_business_phone'), $('#iconInfoBusPhon'), $('#iconExcBusPhon'), $('#iconCheckBusPhon'));
            $booleanBusinessPhone = true;
        }
    });

    /**
     * @type {jQuery}
     * @type click
     * @method on
     * @listens btn_save - ID del botó per actualitzar les dades.
     * @description Botó per fer les verificacions i funcions necessàries per emmagatzemar les dades de l'usuari i del nutricionista.
     */
    $('.btn_save').on('click', () => {
        let $1 = true, $2 = true, $3 = true, $4 = true, $5 = true, $6 = true, $7 = true, $8 = true, $9 = true, $10 = true;

        if ($name != '' || $surnames != '' || $phone != '') {

            if ($name != '') {
                $concat = $concat.concat('___name---' + $name);
            }

            if ($surnames != '') {
                $concat = $concat.concat('___surname---' + $surnames);
            }

            if ($phone != '') {
                $concat = $concat.concat('___phone---' + $phone);
            }

            if ($nif != '') {
                $concat = $concat.concat('___nif---' + $phone);
            }

            if ($booleanName && $booleanSurname && $booleanPhone) {
                $.ajax({
                    url: $urlModifyDataUser + 'modifyUser/id---' + $user.id + $concat,
                    type: 'PUT',
                    headers: {
                        'Authorization': $token
                    }
                });
                $1 = false;
            }
            $concat = '';
        }

        if ($alias != '') {
            if ($booleanAlias) {
                $.ajax({
                    url: $urlModifyDataUser + 'updateUserAlias/userId---' + $user.id + '___alias---' + $alias,
                    type: 'PUT',
                    headers: {
                        'Authorization': $token
                    }
                });
                $2 = false;
            }
        }

        if ($email != '') {
            if ($booleanEmail) {
                $.ajax({
                    url: $urlModifyDataUser + 'updateUserEmail/userId---' + $user.id + '___email---' + $user.email + ',,,' + $email,
                    type: 'PUT',
                    headers: {
                        'Authorization': $token
                    }
                });
                $3 = false;
            }
        }

        if ($pass != '') {
            if ($booleanPassword_1 || $booleanPassword_2 || $booleanPassword_3) {
                if ($booleanPassword_1 && $booleanPassword_2 && $booleanPassword_3) {
                    $.ajax({
                        url: $urlModifyDataUser + 'updateUserPassword/userId---' + $user.id + '___password---' + $user.password + ',,,' + $pass,
                        type: 'PUT',
                        headers: {
                            'Authorization': $token
                        }
                    });
                    $4 = false;
                }
            }
        }
        if ($user.type == 2) {
            if ($pc != '') {
                $concatNutricionst = $concatNutricionst.concat('___postalCode---' + $pc);
                $5 = false;
            }

            if ($city != '') {
                $concatNutricionst = $concatNutricionst.concat('___city---' + $city);
                $6 = false;
            }

            if ($direction != '') {
                $concatNutricionst = $concatNutricionst.concat('___direction---' + $direction);
                $7 = false;
            }

            if ($company != '') {
                $concatNutricionst = $concatNutricionst.concat('___name---' + $company);
                $8 = false;
            }

            if ($businessPhone != '') {
                $concatNutricionst = $concatNutricionst.concat('___phone---' + $businessPhone);
                $9 = false;
            }

            if ($5 == false || $6 == false || $7 == false || $8 == false || $9 == false) {
                $.ajax({
                    url: $urlModifyDataNutricionist + 'modifyNutricionist/id---' + $user.id + $concatNutricionst,
                    type: 'PUT',
                    headers: {
                        'Authorization': $token
                    },
                    success: function () {}
                });
                $10 = false;
            }


            if ($10 == false) {
                $('#updateDataTrue').modal('show');
            }

            $concatNutricionst = '';
        }

        if ($user.type == 1) {
            if ($1 == false || $2 == false || $3 == false || $4 == false) {
                $('#updateDataTrue').modal('show');
            }
        }

    });
    
    /**
     * @type {jQuery}
     * @type click
     * @method on
     * @listens btn_accept - ID del botó per acceptar les modificacions de les dades.
     * @description Botó què surt en un modal de confirmació avisant que les dades s'han modificat correctament.
     */
    $('.btn_accept').on('click', function () {
        location.reload();
    });

    /**
     * @type {jQuery}
     * @type click
     * @method on
     * @listens btn_deleteAccount - ID del botó per esborrar el compte.
     * @description Botó per esborrar el compte de l'usuari o nutricionista, esborrant totes les seves dades de la BBDD.
     */
    $('.btn_deleteAccount').on('click', () => {
        
        if ($user.type == 1 || $user.type == 3) {
            $.ajax({
                url: $urlModifyDataUser + 'deleteUserById/' + $IDuser,
                type: 'DELETE',
                headers: {
                    'Authorization': $token
                },
                success: function () {
                    localStorage.removeItem('id');
                    localStorage.removeItem('token');
                    window.location = '/';
                }
            });
        }else if($user.type == 2){

            $.ajax({
                url: 'http://localhost:8080/api/recipe/getRecipesOfUser/' + $IDuser,
                type: 'GET',
                async: false,
                headers: {
                    'Authorization': $token
                },
                success: function ($recipes){
                    $.each($recipes, function($i, $recipe){
                        $.ajax({
                            url: 'http://localhost:8080/api/recipe/deleteRecipeSteps/' + $recipe.id,
                            type: 'DELETE',
                            async: false,
                            headers: {
                                'Authorization': $token
                            }
                        });
                        $.ajax({
                            url: 'http://localhost:8080/api/recipe/deleteRecipeIngredients/' + $recipe.id,
                            type: 'DELETE',
                            async: false,
                            headers: {
                                'Authorization': $token
                            }
                        });
                        $.ajax({
                            url: 'http://localhost:8080/api/comment/deleteComments/' + $recipe.id,
                            type: 'DELETE',
                            async: false,
                            headers: {
                                'Authorization': $token
                            }
                        });
                        $.ajax({
                            url: 'http://localhost:8080/api/assessment/deleteAssessments/' + $recipe.id,
                            type: 'DELETE',
                            async: false,
                            headers: {
                                'Authorization': $token
                            }
                        });
                        $.ajax({
                            url: 'http://localhost:8080/api/recipe/deleteRecipeById/' + $recipe.id,
                            type: 'DELETE',
                            async: false,
                            headers: {
                                'Authorization': $token
                            }
                        });
                    });
                }

            });

            $.ajax({
                url: $urlModifyDataNutricionist + 'deleteNutricionistById/' + $IDuser,
                type: 'DELETE',
                headers: {
                    'Authorization': $token
                },
                success: function () {
                    localStorage.removeItem('id');
                    localStorage.removeItem('token');
                    window.location = '/';
                }
            });            
        }
    });
});