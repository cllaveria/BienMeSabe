$(document).ready(function () {

    // Definim les variables globals.
    let info, $name, $phone, $alias, $email, $pass, $nif, $pc, $city, $company, $direction, $businessPhone;
    let $booleanNif, $booleanEmail, $booleanAlias;
    let $document = 'nif';
    // Variable per emmagatzemar la ruta de connexió amb el servidor.
    let $url = 'http://localhost:8080/api/';




    /* define(['require', 'bcrypt'], function (require) {
        const bcrypt = require('bcrypt');

    bcrypt.hash('123456', 10, function(err, hash) {
        console.log(hash)
      });
    }); */

    /* require(['bcrypt'], function (require) {
        const bcrypt = require('bcrypt');

    bcrypt.hash('123456', 10, function(err, hash) {
        console.log(hash)
      });
    });
     */



    // Si l'usuari que es registra cambia l'opció de si es nutricionista, aquest mostra un formulari o un altre.
    $(document).on('change', 'input[name="nutritionist"]', () => {
        // Si l'usuari selecciona que si, entrem al if.
        if ($('[name="nutritionist"]:checked').val() == 'yes') {
            // Insertem el formulari per registrar el nutricionista.
            // TODO: una vez esté comprobado lo del DNI/NIF insertar aquí.
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

    // Quan el mouse passa per sobre del botó, mostrem la contrasenya.
    $('#viewPass').on('mouseenter', () => {
        // Deixem l'atribut type buit perquè la contrasenya sigui visible.
        $('#pswrd').attr('type', '');
    });

    // Quan el mouse surt del botó, tornem a ocultar la contrasenya.
    $('#viewPass').on('mouseleave', () => {
        // Tornem a deixa l'atribut type amb el password perquè torni a ser oculta.
        $('#pswrd').attr('type', 'password');
    });

    $(document).on('change', '#document', () => {
        $document = $('#document option:selected').val();
    })

    // Quan sortim de l'input del NIF/DNI, comprovem si aquest està ven escrit.
    $('#nif').blur(() => {
        if ($document == 'nif') {
            // Desem en una variable el resultat true o false segons si la lletra del NIF/DNI coincideix amb el resultat.
            let $documentComprovat = comprovarNif($('#nif').val().toUpperCase());
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
            let $documentComprovat = comprovarNie($('#nif').val().toUpperCase());

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
            let $documentComprovat = comprovarCif($('#nif').val().toUpperCase());

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

    // Quan sortim de l'input del email, comprovem si aquest està ven escrit i si es correcte, si existeix en la BBDD.
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
    // Quan sortim de l'input de l'alias, comprovem si aquest existeix en la BBDD.
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

    // Quan prenem el botó de registrar comencem el procés d'inserció a la BBDD.
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

        // TODO: Buscar otro encriptador.
        // TODO: Conectar API para recibir las poblaciones por CP.
        // TODO: Hacer expresión regular para las PASS.
        // Encriptem la password.
        let $passEncrypted = hex_md5($pass);

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

        if ($booleanEmail == false || $booleanNif == false || $booleanAlias == false) {
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
                    NIF: $nif,
                    password: $pass,
                    name: $name,
                    email: $email,
                    surname: $surnames,
                    alias: $alias,
                    phone: $phone,
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
                    
                }
            });
        // Si el ckeck no està seleccionat, entrem al else per inserir l'usuari a la BBDD.
        } else {
            $.ajax($url + 'user/addUser/?', {
                data: {
                    NIF: $nif,
                    password: $pass,
                    name: $name,
                    email: $email,
                    surname: $surnames,
                    alias: $alias,
                    phone: $phone,
                    type: '1'
                },
                type: 'POST',
                dataType: 'json',
                success: function () {
                    
                }
            });
        }
    });

    // Fem la funció per comprovar que el NIF/DNI sigui correcte.
    function comprovarNif($nif) {
        // Guardem la lletra del NIF/DNI.
        let $lletra = ($nif.charAt($nif.length - 1));
        // Separem els vuit dígits del NIF/DNI per començar a fer l'operació.
        let $calcNif = $nif.substr(0, 8);
        // Anem al mètode comprovarLletra per obtenir la lletra corresponent al resultat de l'operació.
        let $lletraComparar = comprovarLletra($calcNif);
        // Comprovem si el resultat del mètode coincideix amb la lletra del NIF.
        if ($lletraComparar != $lletra) {
            // Si no coincideix, el boolean es false.
            return false;
        } else {
            // Si coincideix, el boolean es true.
            return true;
        }
    }

    function comprovarNie($nie) {
        // Inicialitzem la variable per emmagatzemar el número a inserir.
        let $numeroInsertar;
        // Guardem la lletra del NIE.
        let $lletra = ($nie.charAt($nie.length - 1));
        // Guardem la primera lletra del NIE
        let $lletraInicial = ($nie.charAt(0));
        // Comparem la primera lletra del NIE per a donar-li un valor numèric.
        switch ($lletraInicial) {
            case 'X':
                $numeroInsertar = 0;
                break;
            case 'Y':
                $numeroInsertar = 1;
                break;
            case 'Z':
                $numeroInsertar = 2;
                break;
        }
        // Una vegada obtingut el resultat, inserim el número resultat a la resta del NIE, però traient el número final.
        let $calcNie = $numeroInsertar + $nie.substr(1, 7);
        // Anem al mètode comprovarLletra per obtenir la lletra corresponent al resultat de l'operació.
        let $lletraComparar = comprovarLletra($calcNie);
        // Comprovem si el resultat del mètode coincideix amb la lletra del NIE.
        if ($lletraComparar != $lletra) {
            // Si no coincideix, el boolean es false.
            return false;
        } else {
            // Si coincideix, el boolean es true.
            return true;
        }
    }

    function comprovarLletra($numeration) {

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
        let $resultat = Math.round($calcNumeration3);
        // Assignem la lletra corresponent al resultat obtingut.
        switch ($resultat) {
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

    function comprovarCif($cif) {
        // Iniciem les variables numèriques amb valor inicial a 0.
        let $sumapars = 0;
        let $sumaimpars = 0;
        // Iniciem les variables buides.
        let $primerResultatImpars = '';
        // Array per saber si la lletra del tipus d'organització introduïda és correcte.
        let $arrayOrganitzacio = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'U', 'V', 'W'];
        // Guardem l'últim dígit del CIF.
        let $lletraONumero = $cif.charAt($cif.length - 1);
        // Comprovem si el resultat és numèric.
        for (let i = 0; i < 10; i++) {
            // Si el resultat coincideix amb un número, aquest s'avaluara com a número.
            if ($lletraONumero == i) {
                $lletraONumero = eval($lletraONumero);
            }
        }
        // Guardem el primer dígit del CIF
        let $lletraOrganitzacio = $cif.charAt(0);
        // Comprovem en el array si aquest dígit està registrat.
        let $coincidencia = $.inArray($lletraOrganitzacio, $arrayOrganitzacio);
        // Si no hi ha cap coincidència, el resultat serà -1 i retornarà false.
        if ($coincidencia == -1) {
            return false;
        }
        // Guardem la numeració del CIF.
        let $numeracio = $cif.substr(1, 7);
        // Creem un array per separar la numeració del CIF i poder fer les operacions corresponents.
        let $arrayNumeracio = $numeracio.split('');
        // Fem el for per recórrer l'array creat a vans.
        for (let i = 0; i < $arrayNumeracio.length; i++) {
            // Entrem a fer el if només amb les posicions pars del array.
            if (i % 2 == 0) {
                // Parsejem el número de l'array i fem l'operació.
                $primerResultatImpars = parseInt($arrayNumeracio[i]) * 2;
                // Desem en una variable la mida del resultat.
                let $x = $primerResultatImpars.toString().length;
                // Si la mida de X és igual a 2, entrem a fer el if.
                if ($x == 2) {
                    // Inicialitzem la variable a 0.
                    let $sumaImparsInterna = 0;
                    // Creem un array per al resultat i que ens divideixi aquest resultat en 2 files.
                    let $arrayResultat = $primerResultatImpars.toString().split('');
                    // Passem la variable a 0.
                    $primerResultatImpars = 0;
                    // Fem el for per recórrer l'array creat a vans.
                    for (let i = 0; i < $arrayResultat.length; i++) {
                        // Parsejem el número de l'array i el sumem amb la variable.
                        $sumaImparsInterna += parseInt($arrayResultat[i]);
                    }
                    // Sumem el resultat obtingut abans amb la variable.
                    $primerResultatImpars += $sumaImparsInterna;
                }
                // Sumem el resultat amb la variable.
                $sumaimpars += $primerResultatImpars;
                // Si la posició del array no és par, entrem al else.
            } else {
                // Parsejem el número i el sumem amb la variable.
                $sumapars += parseInt($arrayNumeracio[i]);
            }
        }
        // Sumem els resultats.
        let $resultatsemifinal = $sumapars + $sumaimpars;
        // Passem el resultat a String per obtenir l'últim dígit del resultat.
        let $numeroFinal = $resultatsemifinal.toString().charAt(1);
        // Restem el dígit obtingut.
        let $resultat = 10 - $numeroFinal;
        // Si el resultat obtingut és igual a 10, entrem a fer el if per agafar només l'últim dígit.
        if ($resultat == 10) {
            $resultat = $resultat.toString().charAt(1);
        }
        // Si el type de la variable és igual a number, entrem a fer el if.
        if (typeof ($lletraONumero) == 'number') {
            // Parsejem el número i si no coincideix amb el resultat, retornem false, si es que si, true.
            if ($resultat != parseInt($lletraONumero)) {
                return false;
            } else {
                return true;
            }
            // Si el type de la variable és igual a String, entrem a fer el if.
        } else if (typeof ($lletraONumero) == 'string') {
            // Assignem una lletra segons el resultat obtingut.
            switch ($resultat) {
                case 0:
                    $resultat = 'J';
                    break;
                case 1:
                    $resultat = 'A';
                    break;
                case 2:
                    $resultat = 'B'
                    break;
                case 3:
                    $resultat = 'C';
                    break;
                case 4:
                    $resultat = 'D';
                    break;
                case 5:
                    $resultat = 'E';
                    break;
                case 6:
                    $resultat = 'F';
                    break;
                case 7:
                    $resultat = 'G';
                    break;
                case 8:
                    $resultat = 'H';
                    break;
                case 9:
                    $resultat = 'I';
                    break;
                case 10:
                    $resultat = 'J';
                    break;
            }
            // Si el resultat no coincideix amb la lletra del CIF es retorna false, si és que si, true.
            if ($resultat != $lletraONumero) {
                return false;
            } else {
                return true;
            }
        }
    }
});