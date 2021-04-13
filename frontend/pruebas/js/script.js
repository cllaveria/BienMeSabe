$(document).ready(function () {
    let $nifCorrecte;
    // Definim la variable global per mostrar les dades per consola.
    let info;

    /* 
    * Definim les restriccions de mida per al formulari de registre tant de l'usuari 
    * com del nutricionista. 
    */

    $('#name').attr('maxlength', '100');
    $('#surnames').attr('maxlength', '200');
    $('#phone').attr('maxlength', '9');
    $('#alias').attr('maxlength', '50');
    $('#email').attr('maxlength', '100');
    $('#password').attr('maxlength', '9');
    $('#nif').attr('maxlength', '90');
    $('#pc').attr('maxlength', '5');
    $('#city').attr('maxlength', '45');
    $('#company').attr('maxlength', '50');
    $('#direction').attr('maxlength', '100');
    $('#business_phone').attr('maxlength', '9');

    // Botón para ver pass (BORRAR)
    $(document).on('change', '[name="viewPass"]:checked', () => {
        if ($('[name="viewPass"]:checked').val() == 'yes') {
            $('#password').attr('type', '');
        }else{
            $('#password').attr('type', 'password');
        }
    })
    // FIN botón ver pass

    // Si l'usuari que es registra cambia l'opció de si o no, aquest mostra un formulari o un altre.
    $('.nutritionist').on('click', () => {
        // Si l'usuari selecciona que si, entrem al if.
        console.log('si')
        if ($('[name="nutritionist"]:checked').val() == 'yes') {
            // Insertem el formulari per registrar el nutricionista.
            $('#registerNutritionist').prepend("<p>NIF: <input type='text' id='nif'></p>\
                <p>Código postal: <input type='text' id='pc'></p>\
                <p>Ciudad: <input type='text' id='city'></p>\
                <p>Compañia: <input type='text' id='company'></p>\
                <p>Dirección: <input type='text' id='direction'> (opcional)</p>\
                <p>Teléfono de empresa: <input type='text' id='business_phone' maxlength='9'> (opcional)</p>");
        } else {
            $('#registerNutritionist').empty();;
        }
    });

    $('#submit').click(() => {
        // Recuperem les dades del formulari de l'usuari.
        let $name = $('#name').val()
        let $surnames = $('#surnames').val();
        let $phone = $('#phone').val();
        let $alias = $('#alias').val();
        let $email = $('#email').val();
        let $pass = $('#password').val();
        // Encriptem la password.
        let $passEncrypted = hex_md5($pass);
        // Si el checkbox esta seleccionat com Si.
        if ($('[name="nutritionist"]:checked').val() == 'yes') {
            // Recuperem les dades del formulari del nutricionista.
            let $nif = $('#nif').val();
            let $pc = $('#pc').val();
            let $city = $('#city').val();
            let $company = $('#company').val();
            let $direction = $('#direction').val();
            let $businessPhone = $('#business_phone').val();
            // Anem a la funció comprovarNif per verificar que el NIF introduït sigui correcte.
            comprovarNif($nif);
            // Emmagatzemem en la variable info les dades a mostrar per consola del nutricionista.
            info = {
                Nombre: $name,
                Apellidos: $surnames,
                Teléfono: $phone,
                Alias: $alias,
                Correo: $email,
                ContraseñaSinEncriptar: $password,
                ContraseñaEncriptada: $passEncrypted,
                NIF: $nif,
                CódigoPostal: $pc,
                Ciudad: $city,
                Compañia: $company,
                Dirección: $direction,
                TeléfonoEmpresa: $businessPhone
            };
        } else {
            // Emmagatzemem en la variable info les dades a mostrar per consola de l'usuari.
            info = {
                Nombre: $name,
                Apellidos: $surnames,
                Teléfono: $phone,
                Alias: $alias,
                Correo: $email,
                ContraseñaSinEncriptar: $pass,
                ContraseñaEncriptada: $passEncrypted
            };
        }
        // Mostrem per consola les dades introduides.
        console.table(info)

        $.post('ActionServlet', {
            nombre: $name,
            apellidos: $surnames,
            telefono: $phone
        });

        $.ajax({
            url: 'http://localhost:8080/mavenproject2/ActionServlet',
            data: {
                'nombre':$name,
                'apellidos': $surnames,
                'telefono': $phone},
            type: 'POST',
            success: function (data){
                
            },
            error: function(){
                alert('No conecta')
            }
        });
    });
    // Fem la funció per comprovar que el NIF sigui correcte.
    function comprovarNif($nif) {
        /* Operació a realitzar: Separem els 8 dígits del NIF, després aquests 8 dígits el dividim entre
            23. Aquest resultat el restem amb si mateix però sense decimals. Després aquest resultat el
            multipliquem entre 23, aquest resultat final arrodonit és el que tenim que utilitzar
            per comparar amb la taula de lletres si coincideix.
        */

        // Inicialitzem la variable per emmagatzemar la lletra que sortirà a partir del resultat.
        let $lletraComparar;
        // Guardem la lletra del NIF.
        let $lletra = $nif.charAt($nif.length - 1)
        // Separem els vuit dígits del NIF per començar a fer l'operació.
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
        // Comprovem si el resultat coincideix amb el resutlat.
        if($lletraComparar != $lletra){
            // Si no coincideix, el boolean es false.
            $nifCorrecte = false;
        }else{
            // Si coincideix, el boolean es true.
            $nifCorrecte = true;
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
    }
});