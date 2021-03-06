/**
 * @fileoverview Formulari de registre d'usuaris i nutricionistes amb les següents característiques: 
 * <p> 1) Comprovació amb expressió regular amb email i password.</p>
 * <p> 2) Comprovació de si el NIF/DNI, NIE o CIF estan correctament introduïts.</p>
 * <p> 3) Encriptació del NIF/DNI, NIE o CIF, del telèfon i del password.</p>
 * <p> 4) Comprovació amb la BBDD de si l'Àlies i l'email estan ja introduïts.</p>
 * <p> 5) Inserció a la BBDD de l'usuari o nutricionista que es registri.</p>
 * 
 * <p> History</p>
 * 0.1 - Implementació del formulari.  
 *  
 * @version     0.1
 * @author      Sergio Asensio Ruiz 
 * @copyright   bienmesabe.com
 * 
 */

$(document).ready(function () {

    /**
     * @constant $url {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor.
     */
    const $url = 'http://localhost:8080/api/';

    /** 
     * @var $name 
     * @type {String} 
     * @description Variable per emmagatzemar el nom de l'usuari/nutricionista. 
     */
    let $name;
    /** 
     * @var $phone 
     * @type {number} 
     * @description Variable per emmagatzemar el número de telèfon de l'usuari/nutricionista. 
     */
    let $phone;
    /** 
     * @var $alias 
     * @type {String}
     * @description Variable per emmagatzemar l'Àlies de l'usuari/nutricionista. 
     */
    let $alias;
    /** 
     * @var $email 
     * @type {String}
     * @description Variable per emmagatzemar l'email de l'usuari/nutricionista.
     */
    let $email;
    /** 
     * @var $pass 
     * @type {String}
     * @description Variable per emmagatzemar la contrasenya de l'usuari/nutricionista. 
     */
    let $pass;
    /** 
     * @var $nif 
     * @type {String}
     * @description Variable per emmagatzemar el NIF/DNI, NIE o CIF del nutricionista. 
     */
    let $nif;
    /** 
     * @var $surnames 
     * @type {String}
     * @description Variable per emmagatzemar el cognom de l'usuari/nutricionista. 
     */
    let $surnames;
    /** 
     * @var $pc 
     * @type {number}
     * @description Variable per emmagatzemar el codi postal de la ciutat del nutricionista. 
     */
    let $pc;
    /** 
     * @var $city 
     * @type {String}
     * @description Variable per emmagatzemar la ciutat del nutricionista. 
     */
    let $city;
    /** 
     * @var $company 
     * @type {String}
     * @description Variable per emmagatzemar el nom d'empresa del nutricionista. 
     */
    let $company;
    /** 
     * @var $direction 
     * @type {String}
     * @description Variable per emmagatzemar la direcció d'empresa del nutricionista. 
     */
    let $direction;
    /** 
     * @var $businessPhone 
     * @type {boolean}
     * @description Variable per emmagatzemar el número de telèfon d'empresa del nutricionista. 
     */
    let $businessPhone;
    /** 
     * @var $booleanName 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si el nom està introduït correctament.
     */
    let $booleanName;
    /** 
     * @var $booleanSurname 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si el cognom està introduït correctament.
     */
    let $booleanSurname;
    /** 
     * @var $booleanPhone 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si el teléfon està introduït correctament.
     */
    let $booleanPhone;
    /** 
     * @var $booleanAlias 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si l'Àlies està introduït correctament.
     */
    let $booleanAlias;
    /** 
     * @var $booleanEmail 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si l'email està introduït correctament.
     */
    let $booleanEmail;
    /** 
     * @var $booleanPassword 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si el password està introduït correctament.
     */
    let $booleanPassword;
    /** 
     * @var $booleanNif 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si el NIF/DNI, NIE o CIF estan introduïts correctament.
     */
    let $booleanNif;
    /** 
     * @var $booleanPc 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si el codi postal està introduït correctament.
     */
    let $booleanPc;
    /** 
     * @var $booleanCity 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si la població està introduït correctament.
     */
    let $booleanCity;
    /** 
     * @var $booleanDirection 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si la direció està introduït correctament.
     */
    let $booleanDirection;
    /** 
     * @var $booleanCompany 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si el nom de la companyia està introduït correctament.
     */
    let $booleanCompany;
    /** 
     * @var $booleanBusinessPhone 
     * @type {boolean}
     * @description Variable de tipus boolean per saber si el telèfon d'empresa està introduït correctament.
     */
    let $booleanBusinessPhone;
    /** 
     * @var $document 
     * @type {String}
     * @description Variable inicialitzada en una String 'nif' per emmagatzemar el tipus de documentació i es modifica segons el que ha seleccionat l'usuari, si NIF/DNI, NIE o CIF.
     */
    let $document = 'nif';

    /**
     * @type {jQuery}
     * @method blur
     * @listens name - ID de l'input name.
     * @description Quan l'usuari surt de l'input name es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     */
    $('#name').blur(() => {
        $name = $('#name').val();
        if ($name.length < 5 || $name.length > 50) {
            changeIconsError($('#name'), $('#iconInfoName'), $('#iconExcName'), $('#iconCheckName'));
            $booleanName = false;
        } else {
            changeIconsCheck($('#name'), $('#iconInfoName'), $('#iconExcName'), $('#iconCheckName'));
            $booleanName = true;
        }
    });

    /**
     * @type {jQuery}
     * @method blur
     * @listens surname - ID de l'input surname.
     * @description Quan l'usuari surt de l'input surname es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     */
    $('#surnames').blur(() => {
        $surnames = $('#surnames').val();
        if ($surnames != "") {
            if ($surnames.length < 5 || $surnames.length > 100) {
                changeIconsError($('#surnames'), $('#iconInfoSurnames'), $('#iconExcSurnames'), $('#iconCheckSurnames'));
                $booleanSurname = false;
            } else {
                changeIconsCheck($('#surnames'), $('#iconInfoSurnames'), $('#iconExcSurnames'), $('#iconCheckSurnames'));
                $booleanSurname = true;
            }
        } else {
            $booleanSurname = true;
            changeIconsInfo($('#surnames'), $('#iconInfoSurnames'), $('#iconExcSurnames'), $('#iconCheckSurnames'));
        }
    });

    /**
     * @type {jQuery}
     * @method blur
     * @listens phone - ID de l'input phone.
     * @description Quan l'usuari surt de l'input phone es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     */
    $('#phone').blur(() => {
        $phone = $('#phone').val();
        if ($phone != "") {
            if ($phone.length != 9) {
                changeIconsError($('#phone'), $('#iconInfoPhone'), $('#iconExcPhone'), $('#iconCheckPhone'));
                $booleanPhone = false;
            } else {
                changeIconsCheck($('#phone'), $('#iconInfoPhone'), $('#iconExcPhone'), $('#iconCheckPhone'));
                $booleanPhone = true;
            }
        } else {
            changeIconsInfo($('#phone'), $('#iconInfoPhone'), $('#iconExcPhone'), $('#iconCheckPhone'));
            $booleanPhone = true;
        }
    });

    /**
     * @type {jQuery}
     * @method blur
     * @listens alias - ID de l'input alias.
     * @description Quan l'usuari surt de l'input alias es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     * També es comprova si ja està registrat a la BBDD.
     */
    $('#alias').blur(() => {
        $alias = $('#alias').val();
        if ($alias.length < 3 || $alias.length > 50) {
            changeIconsError($('#alias'), $('#iconInfoAlias'), $('#iconExcAlias'), $('#iconCheckAlias'));
            $booleanAlias = false;
        } else {
            $.ajax({
                url: $url + 'user/findUserByAlias/' + $alias,
                type: 'GET',
                success: function (data) {
                    if (data.alias == $alias) {
                        changeIconsError($('#alias'), $('#iconInfoAlias'), $('#iconExcAlias'), $('#iconCheckAlias'));
                        $('#iconExcAlias').children().html('El Alias introducido ya está registrado.');
                    } else {
                        changeIconsCheck($('#alias'), $('#iconInfoAlias'), $('#iconExcAlias'), $('#iconCheckAlias'));
                        $booleanAlias = true;
                    }
                }
            });
        }
    });

    /**
     * @type {jQuery}
     * @method blur
     * @listens email - ID de l'input email.
     * @description Quan l'usuari surt de l'input email es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     * També es comprova si ja està registrat a la BBDD.
     */
    $('#email').blur(() => {
        $email = $('#email').val();
        if (!/^([\w.%+-]+)@([\w-]+\.)+([\w]{2,})$/.test($email)) {
            changeIconsError($('#email'), $('#iconInfoEmail'), $('#iconExcEmail'), $('#iconCheckEmail'));
            $('#iconExcEmail').children().html('El email introducido no cumple los requisitos.')
            $booleanEmail = false;
        } else {
            $.ajax({
                url: $url + 'user/findUserByEmail/' + $email,
                type: 'GET',
                success: function (data) {
                    console.log(data)
                    if (data.email == $email) {

                        changeIconsError($('#email'), $('#iconInfoEmail'), $('#iconExcEmail'), $('#iconCheckEmail'));
                        $('#iconExcEmail').children().html('El email introducido ya está registrado.')
                    } else {
                        changeIconsCheck($('#email'), $('#iconInfoEmail'), $('#iconExcEmail'), $('#iconCheckEmail'));
                        $booleanEmail = true;
                    }
                }
            });
        }
    });

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
     * @method blur
     * @listens paswrd - ID de l'input password.
     * @description Quan l'usuari surt de l'input password es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     */
    $('#pswrd').blur(() => {
        $pass = $('#pswrd').val();
        if (!/^\b[A-Z]{1}\w{5,}\d{2,}\W{1,}$/.test($pass)) {
            changeIconsError($('#pswrd'), $('#iconInfoPswrd'), $('#iconExcPswrd'), $('#iconCheckPswrd'));
            $booleanPassword = false;
        } else {
            changeIconsCheck($('#pswrd'), $('#iconInfoPswrd'), $('#iconExcPswrd'), $('#iconCheckPswrd'));
            $booleanPassword = true;
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
        $booleanNif = checkDocument($document, $('#nif').val().toUpperCase());
    })

    /**
     * @type {jQuery}
     * @method blur
     * @listens nif - ID de l'input nif.
     * @description Quan l'usuari surt de l'input NIF es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     */
    $('#nif').blur(() => {
        $booleanNif = checkDocument($document, $('#nif').val().toUpperCase());
    });


    /**
     * @type {jQuery}
     * @method blur
     * @listens pc - ID de l'input pc.
     * @description Quan l'usuari surt de l'input pc es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     */
    $('#pc').blur(() => {
        $pc = $('#pc').val();
        if ($pc.length != 5) {
            changeIconsError($('#pc'), $('#iconInfoPc'), $('#iconExcPc'), $('#iconCheckPc'));
            $booleanPc = false;
        } else {
            changeIconsCheck($('#pc'), $('#iconInfoPc'), $('#iconExcPc'), $('#iconCheckPc'));
            $booleanPc = true;
        }
    });

    /**
     * @type {jQuery}
     * @method blur
     * @listens city - ID de l'input city.
     * @description Quan l'usuari surt de l'input city es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     */
    $('#city').blur(() => {
        $city = $('#city').val();
        if ($city.length < 3 || $city.length > 50) {
            changeIconsError($('#city'), $('#iconInfoCity'), $('#iconExcCity'), $('#iconCheckCity'));
            $booleanCity = false;
        } else {
            changeIconsCheck($('#city'), $('#iconInfoCity'), $('#iconExcCity'), $('#iconCheckCity'));
            $booleanCity = true;
        }
    });

    /**
     * @type {jQuery}
     * @method blur
     * @listens direction - ID de l'input direction.
     * @description Quan l'usuari surt de l'input direction es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     */
    $('#direction').blur(() => {
        $direction = $('#direction').val();
        if ($direction != "") {
            if ($direction.length < 5 || $direction.length > 100) {
                changeIconsError($('#direction'), $('#iconInfoDire'), $('#iconExcDire'), $('#iconCheckDire'));
                $booleanDirection = false;
            } else {
                changeIconsCheck($('#direction'), $('#iconInfoDire'), $('#iconExcDire'), $('#iconCheckDire'));
                $booleanDirection = true;
            }
        } else {
            changeIconsInfo($('#direction'), $('#iconInfoDire'), $('#iconExcDire'), $('#iconCheckDire'));
            $booleanDirection = true;
        }
    });

    /**
     * @type {jQuery}
     * @method blur
     * @listens company - ID de l'input company.
     * @description Quan l'usuari surt de l'input company es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     */
    $('#company').blur(() => {
        $company = $('#company').val();
        if ($company != "") {
            if ($company.length < 2 || $company.length > 50) {
                changeIconsError($('#company'), $('#iconInfoComp'), $('#iconExcComp'), $('#iconCheckComp'));
                $booleanCompany = false;
            } else {
                changeIconsCheck($('#company'), $('#iconInfoComp'), $('#iconExcComp'), $('#iconCheckComp'));
                $booleanCompany = true;
            }
        } else {
            changeIconsInfo($('#company'), $('#iconInfoComp'), $('#iconExcComp'), $('#iconCheckComp'));
            $booleanCompany = true;
        }
    });

    /**
     * @type {jQuery}
     * @method blur
     * @listens business_phone - ID de l'input business_phone.
     * @description Quan l'usuari surt de l'input business_phone es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     */
    $('#business_phone').blur(() => {
        $business_phone = $('#business_phone').val();
        if ($business_phone != "") {
            if ($business_phone.length != 9) {
                changeIconsError($('#business_phone'), $('#iconInfoBusPhon'), $('#iconExcBusPhon'), $('#iconCheckBusPhon'));
                $booleanBusinessPhone = false;
            } else {
                changeIconsCheck($('#business_phone'), $('#iconInfoBusPhon'), $('#iconExcBusPhon'), $('#iconCheckBusPhon'));
                $booleanBusinessPhone = true;
            }
        } else {
            changeIconsInfo($('#business_phone'), $('#iconInfoBusPhon'), $('#iconExcBusPhon'), $('#iconCheckBusPhon'));
            $booleanBusinessPhone = true;
        }
    });

    /**
     * Si l'usuari marca que és nutricionista es mostrarà la resta de dades a emplanar i si el desmarca s'amagaràn.
     */
    $(document).on('change', 'input[name="nutritionist"]', () => {
        if ($('[name="nutritionist"]:checked').val() === 'yes') {
            $('#registerNutritionist').show();
            addRequired(true);
        } else {
            $('#registerNutritionist').hide();
            addRequired(false)
        }
    });

    /**
     * @type {jQuery}
     * @type submit
     * @method on
     * @listens registrationForm - ID del botó del formulari.
     * @description Quan l'usuari polsa sobre el botó Registrar del formulari, es fa el procés de verificació de dades i inserció a la BBDD.
     */
    $('#registrationForm').on('submit', (e) => {
        e.preventDefault();
        // Es recuperen les dades del formulari.
        $name = $('#name').val();
        $surnames = $('#surnames').val();
        $phone = $('#phone').val();
        $alias = $('#alias').val();
        $email = $('#email').val();
        $pass = $('#pswrd').val();
        $nif = $('#nif').val().toUpperCase();
        $pc = $('#pc').val();
        $city = $('#city').val();
        $company = $('#company').val();
        $direction = $('#direction').val();
        $businessPhone = $('#business_phone').val();

        let $nifEncrypted;
        let $phoneEncrypted;
        let $passEncrypted = hex_md5($pass);

        if ($surnames == '') {
            $surnames = null;
        }
        if ($phone == '') {
            $phone = null;
        }
        if ($company == '') {
            $company = null;
        }
        if ($direction == '') {
            $direction = null;
        }
        if ($businessPhone == '') {
            $businessPhone = null;
        }

        // Si el checkbox esta seleccionat es recuperen les dades del nutricionista
        if ($('[name="nutritionist"]:checked').val() == 'yes') {
            //$nifEncrypted = hex_md5($nif);
            $nif = $('#nif').val().toUpperCase();
        }

        if ($('[name="nutritionist"]:checked').val() == 'yes') {
            if ($booleanName == false || $booleanSurname == false || $booleanPhone == false ||
                $booleanAlias == false || $booleanEmail == false || $booleanPassword == false ||
                $booleanNif == false || $booleanPc == false || $booleanCity == false ||
                $booleanDirection == false || $booleanCompany == false || $booleanBusinessPhone == false) {
                loading();
                $('#loading').fadeOut(1000, function () {
                    $('#submit').html('REGISTRAR');
                });
                e.preventDefault();
            } else {
                $.ajax({
                    url: $url + 'nutricionist/addNutricionist/?',
                    data: {
                        NIF: $nif,
                        password: $passEncrypted,
                        name: $name,
                        email: $email,
                        surname: $surnames,
                        alias: $alias,
                        phone: $phoneEncrypted,
                        type: '2',
                        companyName: $company,
                        companyPostalCode: $pc,
                        companyDirection: $direction,
                        companyCity: $city,
                        companyPhone: $businessPhone
                    },
                    method: 'POST',
                    dataType: 'json',
                    success: function () {
                        loading();
                        $('#loading').fadeOut(1500, function () {
                            register();
                        });
                    }
                });
            }
        } else {
            if ($booleanName == false || $booleanSurname == false || $booleanPhone == false || $booleanAlias == false || $booleanEmail == false || $booleanPassword == false) {
                loading();
                $('#loading').fadeOut(1000, function () {
                    $('#submit').html('REGISTRAR');
                });
                e.preventDefault();
            } else {
                $.ajax({
                    url: $url + 'user/addUser/?',
                    data: {
                        NIF: null,
                        password: $passEncrypted,
                        name: $name,
                        email: $email,
                        surname: $surnames,
                        alias: $alias,
                        phone: $phoneEncrypted,
                        type: '1'
                    },
                    type: 'POST',
                    dataType: 'json',
                    success: function () {
                        loading();
                        $('#loading').fadeOut(1500, function () {
                            register();
                        });
                    }
                });
            }
        }
    });

    /**
     * @function loading
     * @description Mostrem gif de càrrega.
     */

    function loading() {
        $('#submit').html('<div id="loading"><img style="width:85%;margin-top:-20px;" src="../images/loading.gif" alt="loading" /></div>')
    }

    /**
     * @function register
     * @description Redirigim a la pàgina de login una vegada que s'ha completat el registre.
     */
    function register() {
        $('.checkIn_container').children().remove()
        $('.checkIn_container').html('<div style="height:300px;font-size:20px;display:flex;align-items: center;text-align: center;"><span style="color:green">Te registraste correctamente. <br>En breve serás redirigido a la página de login.</span></div>');
        $('.checkIn_container').fadeOut(5000, function () {
            window.location.href = '../login';
        });
    }

    /**
     * @function addRequired
     * @param {boolean} $boolean 
     * @description Modifiquem el required de les ID nif, pc i city.
     */
    function addRequired($boolean) {
        $('#nif').attr('required', $boolean);
        $('#pc').attr('required', $boolean);
        $('#city').attr('required', $boolean);
    }
});