$(document).ready(function () {
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
            if ($userAjax.type == 3) {
                $('.list-group-flush').before('<a class="list-group-item list-group-item-action bg-light" id="conf" href="/panelAdministrador">\
                                                    <p class="noToggle">Administración</p>\
                                                    <i class="fas fa-user siToggle noShow"></i>\
                                                </a>');
                $('.dropdownMenu').prepend('<a class="list-group-item list-group-item-action menudp" href="/panelAdministrador">\
                                            <p>Administración</p>\
                                        </a>')
            }
        }
    });
});