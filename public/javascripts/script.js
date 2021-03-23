$(document).ready(function () {
    
    // Definim les variables globals.
    let info, $name, $phone, $alias, $email, $pass, $nif, $pc, $city, $company, $direction, $businessPhone;
    let $booleanNif, $booleanEmail;
    // Connexió amb el servidor.
    let $url = 'http://localhost:8080/api/user/';

    // Si l'usuari que es registra cambia l'opció de si es nutricionista, aquest mostra un formulari o un altre.
    $(document).on('change', 'input[name="nutritionist"]', () => {
        // Si l'usuari selecciona que si, entrem al if.
        if ($('[name="nutritionist"]:checked').val() == 'yes') {
            // Insertem el formulari per registrar el nutricionista.
            $('#registerNutritionist').prepend("<p><span style='color:red;font-size:15px'>* </span>NIF/DNI: <input type='text' id='nif' minlength='9' maxlength='9' required></p>\
                <p><span style='color:red;font-size:15px'>* </span>Código postal: <input type='text' id='pc' minlength='5' maxlength='5' required></p>\
                <p><span style='color:red;font-size:15px'>* </span>Población: <input type='text' id='city' minlength='3' maxlength='50'required></p>\
                <p>Dirección: <input type='text' id='direction' minlength='5' maxlength='100'></p>\
                <p>Compañia: <input type='text' id='company' minlength='2' maxlength='50'></p>\
                <p>Teléfono de empresa: <input type='text' id='business_phone' minlength='9' maxlength='9'></p>");
        } else {
            $('#registerNutritionist').empty();
        }
    });

    // Quan el mouse passa per sobre del botó, mostrem la contrasenya.
    $('#viewPass').on('mouseenter', () =>{
        // Deixem l'atribut type buit perquè la contrasenya sigui visible.
        $('#pswrd').attr('type', '');
    });

    // Quan el mouse surt del botó, tornem a ocultar la contrasenya.
    $('#viewPass').on('mouseleave', () =>{
        // Tornem a deixa l'atribut type amb el password perquè torni a ser oculta.
        $('#pswrd').attr('type', 'password');
    }); 

    // Quan sortim de l'input del NIF/DNI, comprovem si aquest està ven escrit.
    $('#nif').blur(() => {
        // Desem en una variable el resultat true o false segons si la lletra del NIF/DNI coincideix amb el resultat.
        let $nifComprovat = comprovarNif($('#nif').val().toUpperCase());
        // Si el resultat és true, entrem a fer el if.
        if ($nifComprovat != true) {
            // Esborrem el span amb el text.
            $('#inputErrorNif').remove()
            // Inserim el span amb el text
            $('#nif').after("<span style='display: block; color:red;'id='inputErrorNif'>El NIF introducido no es correcto.</span>")
            $booleanNif = false;
        } else {
            $('#inputErrorNif').remove()
            $('#nif').after("<span style='display: block; color:green;'id='inputErrorNif'>El NIF introducido es correcto.</span>")
            $booleanNif = true; 
        }
    });

    // Quan sortim de l'input del email, comprovem si aquest està ven escrit.
    $('#email').blur(() => {
        // Desem en una variable l'email introduït en l'input.
        $email = $('#email').val();
        // Verifiquem amb una expressió regular si compleix les condicions. Si és que no, entrem a fer el if.
        if(!/^([\w.%+-]+)@([\w-]+\.)+([\w]{2,})$/.test($email)){
            // Esborrem el span amb el text.
            $('#inputErrorEmail').remove()
            // Inserim el span amb el text
            $('#email').after("<span style='display: block; color:red;'id='inputErrorEmail'>El email introducido no es correcto.</span>")
            $booleanEmail = false; 
        }else {
            $('#inputErrorEmail').remove()
            $booleanEmail = true;
        }
    });

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
        if($surnames == ''){
            $surnames = null;
        }
        // Si l'input del telèfon és vuit, aquest passa a ser valor Null.
        if($phone == ''){
            $phone = null;
        }

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
            if($company == ''){
                $company= null;
            }
            // Si l'input de la direcció és vuit, aquest passa a ser valor Null.
            if($direction == ''){
                $direction = null;
            }
            // Si l'input del telèfon d'empresa és vuit, aquest passa a ser valor Null.
            if($businessPhone == ''){
                $businessPhone = null;
            }            
        } 
    
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

        if($booleanEmail == false || $booleanNif == false){
            //<div id="errors" style="text-align: right;display: inline-table;width: 200px;font-size: 16px; background-color: rgba(255, 115, 115, 0.789); border: 1px solid #000000; display:none;"></div>
            e.preventDefault()
            $('#errors').remove();
            let $insertText = "<div id='errors' style='text-align: left;margin:15px 0px 15px 0px; padding-left:5px; display: inline-table;width: 200px;font-size: 16px; background-color: rgba(255, 115, 115, 0.789); border: 1px solid #000000;'>";
            if($booleanEmail == false){
                $insertText = $insertText.concat('','<p class="errors">- Arregla el email</p>')
                console.log($insertText)
            }

            if($booleanNif == false){
                $insertText = $insertText.concat('', '<p class="errors">- Arregla el NIF/DNI</p>')
                console.log($insertText)
            }

            $insertText = $insertText.concat('', '</div>')
            $('#registrationForm').prepend($insertText);
            console.log($insertText)
        }else{
            $('#errors').hide();
        }

        // Mostrem per consola les dades introduides.
        console.table(info)

        $.ajax({
            url: $url+'getUsers',
            /* data: {
                'nombre':$name,
                'apellidos': $surnames,
                'telefono': $phone},
            type: 'POST', */
            success: function (data){
                console.log(data[0])
                console.log(data[0].name);              
            },
            error: function(){
                alert('No conecta');
            }
        });

        /* $.ajax({
            url: 'http://localhost:8080/api/user/addUser',
            data: {
                'nombre':$name,
                'apellidos': $surnames,
                'telefono': $phone},
            type: 'POST',
            success: function (data){
                console.log(data[0])
                console.log(data[0].name);              
            },
            error: function(){
                alert('No conecta');
            }
        }); */

        
        e.preventDefault()
    });

    // Fem la funció per comprovar que el NIF/DNI sigui correcte.
    function comprovarNif($nif) {
        /* Operació a realitzar: Separem els 8 dígits del NIF/DNI, després aquests 8 dígits el dividim entre
            23. Aquest resultat el restem amb si mateix però sense decimals. Després aquest resultat el
            multipliquem entre 23, aquest resultat final arrodonit és el que tenim que utilitzar
            per comparar amb la taula de lletres si coincideix.
        */

        // Inicialitzem la variable per emmagatzemar la lletra que sortirà a partir del resultat.
        let $lletraComparar;
        // Guardem la lletra del NIF/DNI i la passem a majúscula.
        let $lletra = ($nif.charAt($nif.length - 1))
        // Separem els vuit dígits del NIF/DNI per començar a fer l'operació.
        let $calcNif = $nif.substr(0, 8) / 23;
        // Traiem la coma del resultat de la primera operació.
        let $calcNif1 = Math.trunc($calcNif)
        // Restem per fer la tercera operació.
        let $calcNif2 = $calcNif - $calcNif1;
        // Multipliquem el resultat obtingut per 23
        let $calcNif3 = $calcNif2 * 23;
        // Arrodonim el resultat.
        let $resultat = Math.round($calcNif3)
        // Assignem la lletra corresponent al resultat obtingut.
        switch ($resultat) {
            case 0:
                $lletraComparar = 'T';
                break;
            case 1:
                $lletraComparar = 'R';
                break;
            case 2:
                $lletraComparar = 'W';
                break;
            case 3:
                $lletraComparar = 'A';
                break;
            case 4:
                $lletraComparar = 'G';
                break;
            case 5:
                $lletraComparar = 'M';
                break;
            case 6:
                $lletraComparar = 'Y';
                break;
            case 7:
                $lletraComparar = 'F';
                break;
            case 8:
                $lletraComparar = 'P';
                break;
            case 9:
                $lletraComparar = 'D';
                break;
            case 10:
                $lletraComparar = 'X';
                break;
            case 11:
                $lletraComparar = 'B';
                break;
            case 12:
                $lletraComparar = 'N';
                break;
            case 13:
                $lletraComparar = 'J';
                break;
            case 14:
                $lletraComparar = 'Z';
                break;
            case 15:
                $lletraComparar = 'S';
                break;
            case 16:
                $lletraComparar = 'Q';
                break;
            case 17:
                $lletraComparar = 'V';
                break;
            case 18:
                $lletraComparar = 'H';
                break;
            case 19:
                $lletraComparar = 'L';
                break;
            case 20:
                $lletraComparar = 'C';
                break;
            case 21:
                $lletraComparar = 'K';
                break;
            case 22:
                $lletraComparar = 'E';
                break;
        }
        console.table({
            NIF: $nif,
            Letra: $lletra,
            NúmerosNIF: $nif.substr(0, 8),
            PrimerCalculo: $calcNif,
            SegundoCalculo: $calcNif2,
            TercerCalculo: $resultat,
            LletraResultant: $lletraComparar
        })

        // Comprovem si el resultat coincideix amb el resutlat.
        if ($lletraComparar != $lletra) {
            // Si no coincideix, el boolean es false.
            return false;
        } else {
            // Si coincideix, el boolean es true.
            return true;
        }
    }
});