$(document).ready(function () {

    const $urlModifyDataUser = 'http://localhost:8080/api/user/';
    let $user, $alias, $email, $pswrd_1, $pswrd_2, $pswrd_3, $name, $surname, $phone;
    let $responseAlias, $responseEmail;
    let $concat = '';

    let $token = localStorage.getItem('token');
    let $IDuser = localStorage.getItem('id');

    if ($token != '') {
        $.ajax({
            url: 'http://localhost:8080/api/recipe/getRecipesOfOtherUsers/' + $IDuser,
            type: 'GET',
            async: false,
            headers: {
                'Authorization': $token
            },
            dataType: 'json',
            contentType: 'aplication/json',
            success: function ($requestToken) {

                $('#login').css('display', 'none');
                $('#register').css('display', 'none');
                $('.btn_user').css('display', 'inline-block');
            },
            error: function ($error) {
                if ($error.responseText == '') {
                    $('#login').css('display', 'inline-block');
                    $('#register').css('display', 'inline-block');
                    $('.btn_user').css('display', 'none');
                }
            }
        });
    }

    $.ajax({
        url: 'http://localhost:8080/api/user/getUserById/' + $IDuser,
        type: 'GET',
        async: false,
        success: function ($userAjax) {
            $user = $userAjax;
        }
    });

    $('#name').html($user.name);
    $('#surname').html($user.surname);
    $('#phone').html($user.phone)
    $('#alias').html($user.alias);
    $('#email').html($user.email);
    console.log($user.nif)
    if ($user.nif == null) {
        $('.nif').css('display', 'block')
    }
    
    $('.btn_checkIn').on('click', () => {

        $name = $('#input_name').val();
        $surname = $('#input_surname').val();
        $phone = $('#input_phone').val();
        $alias = $('#input_alias').val();
        $email = $('#input_email').val();
        $pswrd_1 = $('#input_psdw_1').val();
        $pswrd_2 = $('#input_psdw_2').val();
        $pswrd_3 = $('#input_psdw_3').val();

        if ($name != '' || $surname != '' || $phone != '') {
            if ($name != '') {
                $concat = $concat.concat('___name---' + $name);
            }
            if ($surname != '') {
                $concat = $concat.concat('___surname---' + $surname);
            }
            if ($phone != '') {
                $concat = $concat.concat('___phone---' + $phone);
            }
            console.log($concat)
            $.ajax({
                url: $urlModifyDataUser + '/modifyUser/id---' + $user.id + $concat,
                type: 'PUT',
                headers: {
                    'Authorization': $token
                },
                success: function ($response) {
                    if ($name != '') {
                        console.log('El nombre se modificó correctamente.')
                    }
                    if ($surname != '') {
                        console.log('Los apellidos se modificaron correctamente.')
                    }
                    if ($phone != '') {
                        console.log('El teléfono se modificó correctamente.')
                    }
                }
            })
        }
        if ($alias != '') {
            $.ajax({
                url: $urlModifyDataUser + 'updateUserAlias/userId---' + $user.id + '___alias---' + $alias,
                type: 'PUT',
                headers: {
                    'Authorization': $token
                },
                success: function ($response) {
                    $responseAlias = $response;
                    console.log('El alias se ha modificado correctamente.')
                }
            });
        } else {
            $responseAlias = true;
        }

        if ($email != '') {
            if (!/^([\w.%+-]+)@([\w-]+\.)+([\w]{2,})$/.test($email)) {
                console.log('El email introducido no es valido.')
            } else {
                $.ajax({
                    url: $urlModifyDataUser + 'updateUserEmail/userId---' + $user.id + '___email---' + $user.email + ',,,' + $email,
                    type: 'PUT',
                    headers: {
                        'Authorization': $token
                    },
                    success: function ($response) {
                        console.log('El email se ha modificado correctamente.')
                        $responseEmail = $response;
                    }
                })
            }
        } else {
            $responseEmail = true;
        }
        if ($pswrd_1 != '' || $pswrd_2 != '' || $pswrd_3 != '') {
            if ($pswrd_1 != '' && $pswrd_2 != '' && $pswrd_3 != '') {
                if ($pswrd_2 == $pswrd_3) {
                    if (!/^\b[A-Z]{1}\w{5,}\d{2,}\W{1,}$/.test($pswrd_3)) {
                        console.log('La contraseña no cumple con los requisitos.')
                    } else {
                        $.ajax({
                            url: $urlModifyDataUser + 'updateUserPassword/userId---' + $user.id + '___password---' + $pswrd_1 + ',,,' + $pswrd_3,
                            type: 'PUT',
                            headers: {
                                'Authorization': $token
                            },
                            success: function ($response) {
                                if ($response == true) {
                                    console.log('La contraseña se ha modificado correctamente.')
                                } else {
                                    console.log('La contraseña no coincide con la registrado.')
                                }
                            }
                        });
                    }
                }
            } else {
                console.log('Falta alguna casilla por rellenar.')
            }
        } else {
            console.log('no se quiere rellenar ninguna')
        }
    });

    $('.btn_checkOut').on('click', () => {
        localStorage.removeItem('token');
        window.location = '/';
    })
});