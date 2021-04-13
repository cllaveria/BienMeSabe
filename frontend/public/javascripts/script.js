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
     * @var $document 
     * @description Variable per emmagatzemar el tipus de documentació que ha seleccionat l'usuari, si NIF/DNI, NIE o CIF.
     */
    /**
     * @constant $url
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor.
     */
    //TODO: Borrar el info
    let info, $name, $phone, $alias, $email, $pass, $nif, $pc, $city, $company, $direction, $businessPhone;
    let $booleanNif, $booleanEmail, $booleanAlias, $booleanPassword;
    let $document = 'nif';
    const $url = 'http://localhost:8080/api/';

    // TODO: Este apartado se borrará y cambiará, hablarlo con Cris.
    // Si l'usuari que es registra cambia l'opció de si es nutricionista, aquest mostra un formulari o un altre.
    $(document).on('change', 'input[name="nutritionist"]', () => {
        // Si l'usuari selecciona que si, entrem al if.
        if ($('[name="nutritionist"]:checked').val() == 'yes') {
            // Insertem el formulari per registrar el nutricionista.
            $('#registerNutritionist').prepend("<p><text style='color:red;font-size:15px'>* </text>NIF/DNI: <input type='text' id='nif' minlength='9' maxlength='9' required></p>\
                <p><text style='color:red;font-size:15px'>* </text>Código postal: <input type='text' id='pc' minlength='5' maxlength='5' required></p>\
                <p><text style='color:red;font-size:15px'>* </text>Población: <input type='text' id='city' minlength='3' maxlength='50'required></p>\
                <p>Dirección: <input type='text' id='direction' minlength='5' maxlength='100'></p>\
                <p>Compañia: <input type='text' id='company' minlength='2' maxlength='50'></p>\
                <p>Teléfono de empresa: <input type='text' id='business_phone' minlength='9' maxlength='9'></p>");
        } else {
            $('#registerNutritionist').empty();
        }
    });
    // TODO: BORRAR HASTA AQUÍ.

    /**
     * @type {jQuery}
     * @type mouseenter
     * @method on
     * @listens viewPass - ID de la casella per veure el password.
     * @description Quan el ratolí entra a la casella per veure la password, canviem l'atribut type de l'id pswrd i el deixem vuit perquè 
     * la password escrita en l'input sigui visible.
     */
    $('#viewPass').on('mouseenter', () => {
        $('#pswrd').attr('type', '');
    });

    /**
     * @type {jQuery}
     * @type mouseleave
     * @method on
     * @listens viewPass - ID de la casella per veure el password.
     * @description Quan el ratolí surt de la casella de veure la password, canviem l'atribut type de l'id pswrd i posem password perquè 
     * la password escrita en l'input torni a estar oculta.
     */
    $('#viewPass').on('mouseleave', () => {
        $('#pswrd').attr('type', 'password');
    });

    /**
     * @type {jQuery}
     * @type change
     * @method on
     * @listens document - Detecta canvi en la pàgina.
     * @description Quan l'usuari selecciona el tipus de document (NIF/DNI, NIE o CIF) aquest queda guardat en la variable $document.
     */
    $(document).on('change', '#document', () => {
        $document = $('#document option:selected').val();
    })

    /**
     * @type {jQuery}
     * @method blur
     * @listens nif - ID de l'input nif.
     * @description Quan l'usuari surt de l'input NIF comprovem si aquest està correctament escrit.
     */
    $('#nif').blur(() => {
        if ($document == 'nif') {
            // Desem en una variable el resultat true o false segons si la lletra del NIF/DNI coincideix amb el resultat.
            let $documentComprovat = checkNif($('#nif').val().toUpperCase());
            // Si el resultat és true, entrem a fer el if.
            if ($documentComprovat != true) {
                // Esborrem el span amb el text.
                $('#inputErrorNif').remove();
                // Inserim el span amb el text
                $('#nif').after("<span style='display: block; color:red;'id='inputErrorNif'>El NIF introducido no es correcto.</span>")
                $booleanNif = false;
            } else {
                $('#inputErrorNif').remove();
                $('#nif').after("<span style='display: block; color:green;'id='inputErrorNif'>El NIF introducido es correcto.</span>")
                $booleanNif = true;
            }
        } else if ($document == 'nie') {
            let $documentComprovat = checkNie($('#nif').val().toUpperCase());

            if ($documentComprovat != true) {
                // Esborrem el span amb el text.
                $('#inputErrorNif').remove();
                // Inserim el span amb el text
                $('#nif').after("<span style='display: block; color:red;'id='inputErrorNif'>El NIE introducido no es correcto.</span>")
                $booleanNif = false;
            } else {
                $('#inputErrorNif').remove();
                $('#nif').after("<span style='display: block; color:green;'id='inputErrorNif'>El NIE introducido es correcto.</span>")
                $booleanNif = true;
            }
        } else if ($document == 'cif') {
            let $documentComprovat = checkCif($('#nif').val().toUpperCase());

            if ($documentComprovat != true) {
                // Esborrem el span amb el text.
                $('#inputErrorNif').remove()
                // Inserim el span amb el text
                $('#nif').after("<span style='display: block; color:red;'id='inputErrorNif'>El CIF introducido no es correcto.</span>")
                $booleanNif = false;
            } else {
                $('#inputErrorNif').remove();
                $('#nif').after("<span style='display: block; color:green;'id='inputErrorNif'>El CIF introducido es correcto.</span>")
                $booleanNif = true;
            }
        }
    });

    /**
     * @type {jQuery}
     * @method blur
     * @listens paswrd - ID de l'input password.
     * @description Quan l'usuari surt de l'input password comprovem si aquest està correctament escrit i compleix les condicions establertes.
     */
    $('#pswrd').blur(() => {
        // Desem en una variable password introduït en l'input.
        $pass = $('#pswrd').val();
        //Verifiquem amb una expressió regular si compleix les condicions. Si es que no, entrem a fer el if.
        /*
         * Expressió regular per verificar el password: 
         * · ^ - Començament de la cadena.
         * · () - Obrim el grup per crear la restricció del fragment.
         * · \b - Inidiquem que coincideixi amb al començament de la cadena.
         * · [] - Creem el joc de caràcters.
         * · {1} - Indiquem el Mínim de paraules que ha de tenir la cadena.
         * · \w - Caràcters alfanumèrics (A-Z,a-z,0-9,_).
         * · \W - El contrari que \W. 
         */
        if (!/^(\b[A-Z]{1})\w{5,}\d{2,}\W{1,}$/.test($pass)) {
            // Esborrem el span amb el text.
            $('#inputErrorPass').remove();
            // Inserim el span amb el text.
            $('#pswrd').after("<span style='display: block; color:red;'id='inputErrorPass'>La contraseña introducida no es correcta.</span>");
            // El boolean de comprovació del password passa a false.
            $booleanPassword = false;
        } else {
            // Esborrem el span amb el text.
            $('#inputErrorPass').remove();
            // El boolean de comprovació del password passa a true.
            $booleanPassword = true;
        }
    });

    /**
     * @type {jQuery}
     * @method blur
     * @listens email - ID de l'input email.
     * @description Quan l'usuari surt de l'input email comprovem si aquest està correctament escrit, compleix les condicions establertes i si aquest ja està registrat a la BBDD.
     */
    $('#email').blur(() => {
        // Desem en una variable l'email introduït en l'input.
        $email = $('#email').val();
        // Verifiquem amb una expressió regular si compleix les condicions. Si és que no, entrem a fer el if.
        /* 
         * Expressió regular per verificar l'email:
         * · ^ - Començament de la cadena.
         * · () - Obrim el grup per crear la restricció del fragment.
         * · [] - Creem el joc de caràcters.
         * · \w - Caràcters alfanumèrics (A-Z,a-z,0-9,_).
         * · . - Tots els caràcters excepte salt de línia. 
         * · + - Cerca el grup 0 o més vegades.
         * · @ - Separem el primer grup amb el @.
         * · {} - Mínim de paraules que ha de tenir la cadena.
         * */

        if (!/^([\w.%+-]+)@([\w-]+\.)+([\w]{2,})$/.test($email)) {
            // Esborrem el span amb el text.
            $('#inputErrorEmail').remove();
            // Inserim el span amb el text.
            $('#email').after("<span style='display: block; color:red;'id='inputErrorEmail'>El email introducido no es correcto.</span>");
            // El boolean de comprovació de l'email passa a false.
            $booleanEmail = false;
        } else {
            // Esborrem el span amb el text.
            $('#inputErrorEmail').remove();
            // Fem la crida Ajax per comprovar si l'email està registrat en la BBDD.
            $.ajax({
                url: $url + 'user/findUserByEmail/' + $email,
                type: 'GET',
                success: function (data) {
                    console.log(data.email)
                    // Si coincideix l'email retornat amb l'email escrit, entrem a fer el if.
                    if (data.email == $email) {
                        // Inserim el span amb el text.
                        $('#email').after("<span style='display: block; color:red;'id='inputErrorEmail'>El email introducido ya está registrado.</span>");
                        // Si no hi ha coincidència, fem el else.
                    } else {
                        // El boolean de comprovació de l'email passa a true.
                        $booleanEmail = true;
                    }
                }
            });
        }
    });

    /**
     * @type {jQuery}
     * @method blur
     * @listens alias - ID de l'input alias.
     * @description Quan l'usuari surt de l'input alias comprovem si aquest està correctament escrit, compleix les condicions establertes i si aquest ja està registrat a la BBDD.
     */
    $('#alias').blur(() => {
        // Desem en una variable l'alias introduït en l'input.
        $alias = $('#alias').val();
        // Fem la crida Ajax per comprovar si l'alias està registrat en la BBDD.
        $.ajax({
            url: $url + 'user/findUserByAlias/' + $alias,
            type: 'GET',
            success: function (data) {
                // Si coincideix l'email retornat amb l'email escrit, entrem a fer el if.
                if (data.alias == $alias) {
                    // Esborrem el span amb el text.
                    $('#inputErrorAlias').remove();
                    // Inserim el span amb el text.
                    $('#alias').after("<span style='display: block; color:red;'id='inputErrorAlias'>El alias introducido ya está registrado.</span>");
                    // El boolean de comprovació de l'alias passa a false.
                    $booleanAlias = false;
                }
            },
            error: function () {
                // Esborrem el span amb el text.
                $('#inputErrorAlias').remove();
                // El boolean de comprovació de l'email passa a true.
                $booleanAlias = true;
            }
        });
    });

    /**
     * @type {jQuery}
     * @type submit
     * @method on
     * @listens registrationForm - ID del botó del formulari.
     * @description Quan l'usuari polsa sobre el botó Registrar del formulari, comencem el procés de verificació de dades i inserció a la BBDD.
     */
    $('#registrationForm').on('submit', (e) => {

        // Recuperem les dades del formulari de l'usuari.
        $name = $('#name').val();
        $surnames = $('#surnames').val();
        $phone = $('#phone').val();
        $alias = $('#alias').val();
        $email = $('#email').val();
        $pass = $('#pswrd').val();
        $nif = $('#nif').val().toUpperCase();

        // Si l'input dels cognoms és vuit, aquest passa a ser valor Null.
        if ($surnames == '') {
            $surnames = null;
        }
        // Si l'input del telèfon és vuit, aquest passa a ser valor Null.
        if ($phone == '') {
            $phone = null;
        }

        // Si el checkbox esta seleccionat com Si entrem a fer el if.
        if ($('[name="nutritionist"]:checked').val() == 'yes') {
            // Recuperem les dades del formulari del nutricionista.
            $nif = $('#nif').val();
            $pc = $('#pc').val();
            $city = $('#city').val();
            $company = $('#company').val();
            $direction = $('#direction').val();
            $businessPhone = $('#business_phone').val();

            // Si l'input de la companyia és vuit, aquest passa a ser valor Null.
            if ($company == '') {
                $company = null;
            }
            // Si l'input de la direcció és vuit, aquest passa a ser valor Null.
            if ($direction == '') {
                $direction = null;
            }
            // Si l'input del telèfon d'empresa és vuit, aquest passa a ser valor Null.
            if ($businessPhone == '') {
                $businessPhone = null;
            }
        }
        // Encriptem la password.
        let $passEncrypted = hex_md5($pass);
        // Encriptem el nif.
        let $nifEncrypted = hex_md5($nif);
        // Encriptem el teléfon.
        let $phoneEncrypted = hex_md5($phone);

        //TODO: BORRAR
        // Emmagatzemem en la variable info les dades a mostrar per consola.
        info = {
            Nombre: $name,
            Apellidos: $surnames,
            Teléfono: $phone,
            Alias: $alias,
            Correo: $email,
            ContraseñaSinEncriptar: $pass,
            ContraseñaEncriptada: $passEncrypted,
            NIF: $nif,
            CódigoPostal: $pc,
            Ciudad: $city,
            Compañia: $company,
            Dirección: $direction,
            TeléfonoEmpresa: $businessPhone
        };

        if ($booleanEmail == false || $booleanNif == false || $booleanAlias == false || $booleanPassword == false) {
            e.preventDefault();
            $('#errors').remove();
            let $insertText = "<div id='errors' style='text-align: left;margin:15px 0px 15px 0px; padding-left:5px; display: inline-table;width: 200px;font-size: 16px; background-color: rgba(255, 115, 115, 0.789); border: 1px solid #000000;'>";
            if ($booleanEmail == false) {
                $insertText = $insertText.concat('', '<p class="errors">- Modifica el email</p>');
            }
            if ($booleanNif == false) {
                $insertText = $insertText.concat('', '<p class="errors">- Modifica el NIF/DNI</p>');
            }
            if ($booleanAlias == false) {
                $insertText = $insertText.concat('', '<p class="errors">- Modifica el alias</p>');
            }
            if ($booleanPassword == false) {
                $insertText = $insertText.concat('', '<p class="errors">- Modifica la contraseña</p>');
            }
            $insertText = $insertText.concat('', '</div>');
            $('#registrationForm').prepend($insertText);
        } else {
            $('#errors').hide();
        }
        // TODO: BORRAR
        // Mostrem per consola les dades introduides.
        console.table(info)
        e.preventDefault();

        // Comprovem si el check del nutricionista està seleccionat. Si es que si, entrem a fer el if per realitzar la inserció a la BBDD.
        if ($('[name="nutritionist"]:checked').val() == 'yes') {
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
                success: function () {}
            });
            // Si el ckeck no està seleccionat, entrem al else per inserir l'usuari a la BBDD.
        } else {
            $.ajax($url + 'user/addUser/?', {
                data: {
                    //TODO: Comprobar si se tiene que borrar el NIF de aquí. 
                    NIF: $nifEncrypted,
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
                success: function () {}
            });
        }
    });

    /**
     * @function checkNif
     * @description Comprovem que el NIF/DNI introduït sigui correcte
     * @param {string} $nif NIF/DNI introduït
     * @returns {boolean}
     */
    function checkNif($nif) {
        // Guardem la lletra del NIF/DNI.
        let $letter = ($nif.charAt($nif.length - 1));
        // Separem els vuit dígits del NIF/DNI per començar a fer l'operació.
        let $calcNif = $nif.substr(0, 8);
        // Anem al mètode checkLetter per obtenir la lletra corresponent al resultat de l'operació.
        let $compareLetter = checkLetter($calcNif);
        // Comprovem si el resultat del mètode coincideix amb la lletra del NIF.
        if ($compareLetter != $letter) {
            // Si no coincideix, el boolean es false.
            return false;
        } else {
            // Si coincideix, el boolean es true.
            return true;
        }
    }

    /**
     * @function checkNie
     * @description Comprovem que el NIE introduït sigui correcte
     * @param {string} $nie NIE introduït
     * @returns {boolean}
     */
    function checkNie($nie) {
        // Inicialitzem la variable per emmagatzemar el número a inserir.
        let $insertNumber;
        // Guardem la lletra del NIE.
        let $letter = ($nie.charAt($nie.length - 1));
        // Guardem la primera lletra del NIE
        let $initialLetter = ($nie.charAt(0));
        // Comparem la primera lletra del NIE per a donar-li un valor numèric.
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
        // Una vegada obtingut el resultat, inserim el número resultat a la resta del NIE, però traient el número final.
        let $calcNie = $insertNumber + $nie.substr(1, 7);
        // Anem al mètode checkLetter per obtenir la lletra corresponent al resultat de l'operació.
        let $compareLetter = checkLetter($calcNie);
        // Comprovem si el resultat del mètode coincideix amb la lletra del NIE.
        if ($compareLetter != $letter) {
            // Si no coincideix, el boolean es false.
            return false;
        } else {
            // Si coincideix, el boolean es true.
            return true;
        }
    }

    /**
     * @function checkLetter
     * @description Realitzem el calcul de la numeració per saber si el NIF/NIE o DNI son correctes
     * @param {number} $numeration numeració del NIF/DNI o NIE
     * @returns {string}
     */
    function checkLetter($numeration) {

        /* Operació a realitzar: Separem els 8 dígits del NIF/DNI, després aquests 8 dígits el dividim entre
            23. Aquest resultat el restem amb si mateix però sense decimals. Després aquest resultat el
            multipliquem entre 23, aquest resultat final arrodonit és el que tenim que utilitzar
            per comparar amb la taula de lletres si coincideix.
        */
        // Comencem a fer l'operació dividint la numeració entre 23.
        let $calcNumeration = ($numeration / 23);
        // Traiem la coma del resultat de la primera operació i restem el resultat anterior amb aquest.
        let $calcNumeration2 = $calcNumeration - Math.trunc($calcNumeration);
        // Multipliquem el resultat obtingut per 23
        let $calcNumeration3 = $calcNumeration2 * 23;
        // Arrodonim el resultat.
        let $result = Math.round($calcNumeration3);
        // Assignem la lletra corresponent al resultat obtingut.
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
     * @description Comprovem que el CIF introduït sigui correcte.
     * @param {string} $cif CIF introduït
     * @returns {boolean}
     */
    function checkCif($cif) {
        // Iniciem les variables numèriques amb valor inicial a 0.
        let $pairsSum = 0;
        let $oddSum = 0;
        // Iniciem les variables buides.
        let $firstOddResult = '';
        // Array per saber si la lletra del tipus d'organització introduïda és correcte.
        let $arrayOrganization = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'U', 'V', 'W'];
        // Guardem l'últim dígit del CIF.
        let $letterOrNumber = $cif.charAt($cif.length - 1);
        // Comprovem si el resultat és numèric.
        for (let i = 0; i < 10; i++) {
            // Si el resultat coincideix amb un número, aquest s'avaluara com a número.
            if ($letterOrNumber == i) {
                $letterOrNumber = eval($letterOrNumber);
            }
        }
        // Guardem el primer dígit del CIF
        let $letterOrganization = $cif.charAt(0);
        // Comprovem en el array si aquest dígit està registrat.
        let $coincidence = $.inArray($letterOrganization, $arrayOrganization);
        // Si no hi ha cap coincidència, el resultat serà -1 i retornarà false.
        if ($coincidence == -1) {
            return false;
        }
        // Guardem la numeració del CIF.
        let $numeration = $cif.substr(1, 7);
        // Creem un array per separar la numeració del CIF i poder fer les operacions corresponents.
        let $arrayNumeration = $numeration.split('');
        // Fem el for per recórrer l'array creat a vans.
        for (let i = 0; i < $arrayNumeration.length; i++) {
            // Entrem a fer el if només amb les posicions pars del array.
            if (i % 2 == 0) {
                // Parsejem el número de l'array i fem l'operació.
                $firstOddResult = parseInt($arrayNumeration[i]) * 2;
                // Desem en una variable la mida del resultat.
                let $x = $firstOddResult.toString().length;
                // Si la mida de X és igual a 2, entrem a fer el if.
                if ($x == 2) {
                    // Inicialitzem la variable a 0.
                    let $innerOddSum = 0;
                    // Creem un array per al resultat i que ens divideixi aquest resultat en 2 files.
                    let $arrayResult = $firstOddResult.toString().split('');
                    // Passem la variable a 0.
                    $firstOddResult = 0;
                    // Fem el for per recórrer l'array creat a vans.
                    for (let i = 0; i < $arrayResult.length; i++) {
                        // Parsejem el número de l'array i el sumem amb la variable.
                        $innerOddSum += parseInt($arrayResult[i]);
                    }
                    // Sumem el resultat obtingut abans amb la variable.
                    $firstOddResult += $innerOddSum;
                }
                // Sumem el resultat amb la variable.
                $oddSum += $firstOddResult;
                // Si la posició del array no és par, entrem al else.
            } else {
                // Parsejem el número i el sumem amb la variable.
                $pairsSum += parseInt($arrayNumeration[i]);
            }
        }
        // Sumem els resultats.
        let $semifinalResult = $pairsSum + $oddSum;
        // Passem el resultat a String per obtenir l'últim dígit del resultat.
        let $finalNumber = $semifinalResult.toString().charAt(1);
        // Restem el dígit obtingut.
        let $result = 10 - $finalNumber;
        // Si el resultat obtingut és igual a 10, entrem a fer el if per agafar només l'últim dígit.
        if ($result == 10) {
            $result = $result.toString().charAt(1);
        }
        // Si el type de la variable és igual a number, entrem a fer el if.
        if (typeof ($letterOrNumber) == 'number') {
            // Parsejem el número i si no coincideix amb el resultat, retornem false, si es que si, true.
            if ($result != parseInt($letterOrNumber)) {
                return false;
            } else {
                return true;
            }
            // Si el type de la variable és igual a String, entrem a fer el if.
        } else if (typeof ($letterOrNumber) == 'string') {
            // Assignem una lletra segons el resultat obtingut.
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
            // Si el resultat no coincideix amb la lletra del CIF es retorna false, si és que si, true.
            if ($result != $letterOrNumber) {
                return false;
            } else {
                return true;
            }
        }
    }
});