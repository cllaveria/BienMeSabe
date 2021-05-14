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
     * @var $name 
     * @description Variable per emmagatzemar el nom de l'usuari/nutricionista. 
     */
    /** 
     * @var $phone  
     * @description Variable per emmagatzemar el número de telèfon de l'usuari/nutricionista. 
     */
    /** 
     * @var $alias 
     * @description Variable per emmagatzemar l'Àlies de l'usuari/nutricionista. 
     */
    /** 
     * @var $email 
     * @description Variable per emmagatzemar l'email de l'usuari/nutricionista.
     */
    /** 
     * @var $pass  
     * @description Variable per emmagatzemar la contrasenya de l'usuari/nutricionista. 
     */
    /** 
     * @var $nif 
     * @description Variable per emmagatzemar el NIF/DNI, NIE o CIF del nutricionista. 
     */
    /** 
     * @var $pc 
     * @description Variable per emmagatzemar el codi postal de la ciutat del nutricionista. 
     */
    /** 
     * @var $city 
     * @description Variable per emmagatzemar la ciutat del nutricionista. 
     */
    /** 
     * @var $company 
     * @description Variable per emmagatzemar el nom d'empresa del nutricionista. 
     */
    /** 
     * @var $direction 
     * @description Variable per emmagatzemar la direcció d'empresa del nutricionista. 
     */
    /** 
     * @var $businessPhone 
     * @description Variable per emmagatzemar el número de telèfon d'empresa del nutricionista. 
     */
    /** 
     * @var $booleanNif 
     * @description Variable de tipus boolean per saber si el NIF/DNI, NIE o CIF estan introduïts correctament.
     */
    /** 
     * @var $booleanName 
     * @description Variable de tipus boolean per saber si el nom està introduït correctament.
     */
    /** 
     * @var $booleanSurname
     * @description Variable de tipus boolean per saber si el cognom està introduït correctament.
     */
    /** 
     * @var $booleanPc
     * @description Variable de tipus boolean per saber si el codi postal està introduït correctament.
     */
    /** 
     * @var $booleanEmail 
     * @description Variable de tipus boolean per saber si l'email està introduït correctament.
     */
    /** 
     * @var $booleanAlias 
     * @description Variable de tipus boolean per saber si l'Àlies està introduït correctament.
     */
    /** 
     * @var $booleanPassword 
     * @description Variable de tipus boolean per saber si el password està introduït correctament.
     */
    /** 
     * @var $booleanCity
     * @description Variable de tipus boolean per saber si la població està introduït correctament.
     */
    /** 
     * @var $booleaDirection
     * @description Variable de tipus boolean per saber si la direció està introduït correctament.
     */
    /** 
     * @var $booleanCompany
     * @description Variable de tipus boolean per saber si el nom de la companyia està introduït correctament.
     */
    /** 
     * @var $booleanBusinessPhone
     * @description Variable de tipus boolean per saber si el telèfon d'empresa està introduït correctament.
     */
    /** 
     * @var $document 
     * @description Variable per emmagatzemar el tipus de documentació que ha seleccionat l'usuari, si NIF/DNI, NIE o CIF.
     */
    /**
     * @constant $url
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor.
     */
    let $name,
        $phone,
        $alias,
        $email,
        $pass,
        $nif,
        $pc,
        $city,
        $company,
        $direction,
        $businessPhone,
        $booleanName,
        $booleanSurname,
        $booleanPhone,
        $booleanAlias,
        $booleanEmail,
        $booleanPassword,
        $booleanNif,
        $booleanPc,
        $booleanCity,
        $booleanDirection,
        $booleanCompany,
        $booleanBusinessPhone,
        $document = 'nif';

    const $url = 'http://localhost:8080/api/';

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
                console.log($booleanSurname)
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
                        $('#iconExcAlias').children().html('El Alias introducido ya está registrado.')
                    } else {
                        changeIconsCheck($('#alias'), $('#iconInfoAlias'), $('#iconExcAlias'), $('#iconCheckAlias'));
                        $booleanAlias = true;
                    }
                }
                /* ,
                                error: function () {
                                    changeIconsCheck($('#alias'), $('#iconInfoAlias'), $('#iconExcAlias'), $('#iconCheckAlias'));
                                    $booleanAlias = true;
                                } */
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
                /* ,
                                error: function () {
                                    changeIconsCheck($('#email'), $('#iconInfoEmail'), $('#iconExcEmail'), $('#iconCheckEmail'));
                                    $booleanEmail = true;
                                } */
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
        checkDocument();
    })

    /**
     * @type {jQuery}
     * @method blur
     * @listens nif - ID de l'input nif.
     * @description Quan l'usuari surt de l'input NIF es comprova si aquest és correcte i es canvien les classes i les icones corresponents.
     */
    $('#nif').blur(() => {
        checkDocument();
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
        $nif = $('#nif').val().toUpperCase();;
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
            $nifEncrypted = hex_md5($nif);
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
                        NIF: $nifEncrypted,
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
     * @description Modifiquem el required de les ID nif, pc i city.
     */
    function addRequired($boolean) {
        $('#nif').attr('required', $boolean);
        $('#pc').attr('required', $boolean);
        $('#city').attr('required', $boolean);
    }

    /**
     * @function checkDocument
     * @description Comprova si el NIF/DNI, NIE o CIF introduït és correcte
     */
    function checkDocument() {
        if ($document == 'nif') {
            if ((checkNif($('#nif').val().toUpperCase())) != true) {
                changeIconsError($('#nif'), $('#iconInfoNif'), $('#iconExcNif'), $('#iconCheckNif'));
                $('#iconExcNif').children().html('El NIF/DNI introducido no es correcto.');
                $booleanNif = false;
            } else {
                changeIconsCheck($('#nif'), $('#iconInfoNif'), $('#iconExcNif'), $('#iconCheckNif'));
                $booleanNif = true;
            }
        } else if ($document == 'nie') {
            if ((checkNie($('#nif').val().toUpperCase())) != true) {
                changeIconsError($('#nif'), $('#iconInfoNif'), $('#iconExcNif'), $('#iconCheckNif'));
                $('#iconExcNif').children().html('El NIE introducido no es correcto.');
                $booleanNif = false;
            } else {
                changeIconsCheck($('#nif'), $('#iconInfoNif'), $('#iconExcNif'), $('#iconCheckNif'));
                $booleanNif = true;
            }
        } else if ($document == 'cif') {
            if ((checkCif($('#nif').val().toUpperCase())) != true) {
                changeIconsError($('#nif'), $('#iconInfoNif'), $('#iconExcNif'), $('#iconCheckNif'));
                $('#iconExcNif').children().html('El CIF introducido no es correcto.');
                $booleanNif = false;
            } else {
                changeIconsCheck($('#nif'), $('#iconInfoNif'), $('#iconExcNif'), $('#iconCheckNif'));
                $booleanNif = true;
            }
        }
    }

    /**
     * @function checkNif
     * @description Comprova si el NIF/DNI introduït és correcte
     * @param {string} $nif NIF/DNI introduït
     * @returns {boolean}
     */
    function checkNif($nif) {
        let $letter = ($nif.charAt($nif.length - 1));
        let $calcNif = $nif.substr(0, 8);
        let $compareLetter = checkLetter($calcNif);
        if ($compareLetter != $letter) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @function checkNie
     * @description Comprova si el NIE introduït és correcte
     * @param {string} $nie NIE introduït
     * @returns {boolean}
     */
    function checkNie($nie) {
        let $insertNumber;
        let $letter = ($nie.charAt($nie.length - 1));
        let $initialLetter = ($nie.charAt(0));
        // Es compara la primera lletra del NIE per a donar-li un valor numèric.
        switch ($initialLetter) {
            case 'X':
                $insertNumber = 0;
                break;
            case 'Y':
                $insertNumber = 1;
                break;
            case 'Z':
                $insertNumber = 2;
                break;
        }
        // Una vegada obtingut el resultat, s'insereix el número resultat a la resta del NIE, però traient el número final.
        let $calcNie = $insertNumber + $nie.substr(1, 7);
        let $compareLetter = checkLetter($calcNie);
        if ($compareLetter != $letter) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @function checkLetter
     * @description Realitza el calcul de la numeració per saber si el NIF/NIE o DNI són correctes
     * @param {number} $numeration numeració del NIF/DNI o NIE
     * @returns {string}
     */
    function checkLetter($numeration) {
        let $calcNumeration = ($numeration / 23);
        let $calcNumeration2 = $calcNumeration - Math.trunc($calcNumeration);
        let $calcNumeration3 = $calcNumeration2 * 23;
        let $result = Math.round($calcNumeration3);
        switch ($result) {
            case 0:
                return 'T';
            case 1:
                return 'R';
            case 2:
                return 'W';
            case 3:
                return 'A';
            case 4:
                return 'G';
            case 5:
                return 'M';
            case 6:
                return 'Y';
            case 7:
                return 'F';
            case 8:
                return 'P';
            case 9:
                return 'D';
            case 10:
                return 'X';
            case 11:
                return 'B';
            case 12:
                return 'N';
            case 13:
                return 'J';
            case 14:
                return 'Z';
            case 15:
                return 'S';
            case 16:
                return 'Q';
            case 17:
                return 'V';
            case 18:
                return 'H';
            case 19:
                return 'L';
            case 20:
                return 'C';
            case 21:
                return 'K';
            case 22:
                return 'E';
        }
    }

    /**
     * @function checkCif
     * @description Comprova si el CIF introduït és correcte.
     * @param {string} $cif CIF introduït
     * @returns {boolean}
     */
    function checkCif($cif) {
        let $pairsSum = 0;
        let $oddSum = 0;
        let $firstOddResult = '';
        let $arrayOrganization = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'U', 'V', 'W'];
        let $letterOrNumber = $cif.charAt($cif.length - 1);
        for (let i = 0; i < 10; i++) {
            // Si el resultat coincideix amb un número, aquest s'avaluarà com a número.
            if ($letterOrNumber == i) {
                $letterOrNumber = eval($letterOrNumber);
            }
        }
        let $letterOrganization = $cif.charAt(0);
        let $coincidence = $.inArray($letterOrganization, $arrayOrganization);
        // Si no hi ha cap coincidència, el resultat serà -1 i retornarà false.
        if ($coincidence == -1) {
            return false;
        }
        let $numeration = $cif.substr(1, 7);
        let $arrayNumeration = $numeration.split('');
        for (let i = 0; i < $arrayNumeration.length; i++) {
            if (i % 2 == 0) {
                $firstOddResult = parseInt($arrayNumeration[i]) * 2;
                let $x = $firstOddResult.toString().length;
                if ($x == 2) {
                    let $innerOddSum = 0;
                    let $arrayResult = $firstOddResult.toString().split('');
                    $firstOddResult = 0;
                    for (let i = 0; i < $arrayResult.length; i++) {
                        $innerOddSum += parseInt($arrayResult[i]);
                    }
                    $firstOddResult += $innerOddSum;
                }
                $oddSum += $firstOddResult;
            } else {
                $pairsSum += parseInt($arrayNumeration[i]);
            }
        }
        let $semifinalResult = $pairsSum + $oddSum;
        let $finalNumber = $semifinalResult.toString().charAt(1);
        let $result = 10 - $finalNumber;
        if ($result == 10) {
            $result = $result.toString().charAt(1);
        }
        if (typeof ($letterOrNumber) == 'number') {
            if ($result != parseInt($letterOrNumber)) {
                return false;
            } else {
                return true;
            }
        } else if (typeof ($letterOrNumber) == 'string') {
            switch ($result) {
                case 0:
                    $result = 'J';
                    break;
                case 1:
                    $result = 'A';
                    break;
                case 2:
                    $result = 'B'
                    break;
                case 3:
                    $result = 'C';
                    break;
                case 4:
                    $result = 'D';
                    break;
                case 5:
                    $result = 'E';
                    break;
                case 6:
                    $result = 'F';
                    break;
                case 7:
                    $result = 'G';
                    break;
                case 8:
                    $result = 'H';
                    break;
                case 9:
                    $result = 'I';
                    break;
                case 10:
                    $result = 'J';
                    break;
            }
            if ($result != $letterOrNumber) {
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * @param {*} input Fa referèncía a l'input que s'està validant.
     * @param {*} iconInfo Icona d'informació del input.
     * @param {*} iconExcl Icona d'exclamació del input.
     * @param {*} iconCheck Icono de check del input.
     * @description Si la dada de l'input és buida modifica els displays de les icones per passar 
     * a mostrar la informació i treu la classe error i/o success.
     */
    function changeIconsInfo(input, iconInfo, iconExcl, iconCheck) {
        iconInfo.css("display", "block");
        iconCheck.css("display", "none");
        iconExcl.css("display", "none");
        input.removeClass("error");
        input.removeClass("success");
    }

    /**
     * @param {*} input Fa referèncía a l'input que s'està validant.
     * @param {*} iconInfo Icona d'informació del input.
     * @param {*} iconExcl Icona d'exclamació del input.
     * @param {*} iconCheck Icono de check del input.
     * @description Si la dada de l'input no és correcta modifica els displays de les icones per passar 
     * a mostrar l'exclamació i afegeix la classe error a l'input per  mostrar el focus i borders en vermell.
     */
    function changeIconsError(input, iconInfo, iconExcl, iconCheck) {
        iconExcl.css("display", "block");
        iconInfo.css("display", "none");
        iconCheck.css("display", "none");
        input.addClass("error");
    }

    /**
     * @param {*} input Fa referèncía a l'input que s'està validant.
     * @param {*} iconInfo Icona d'informació del input.
     * @param {*} iconExcl Icona d'exclamació del input.
     * @param {*} iconCheck Icono de check del input.
     * @description Si la dada de l'input és correcta modifica els displays de les icones per passar 
     * a mostrar el check. Treu la classe error de l'input i li afegeix la classe success per mostrar el focus en verd.
     */
    function changeIconsCheck(input, iconInfo, iconExcl, iconCheck) {
        iconCheck.css("display", "block");
        iconInfo.css("display", "none");
        iconExcl.css("display", "none");
        input.removeClass("error");
        input.addClass("success");
    }
});