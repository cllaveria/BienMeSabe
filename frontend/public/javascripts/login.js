$(document).ready(function () {
    const $urlLogin = 'http://localhost:8080/api/user/loginUser?data=password---'
    let $pswrd,
        $login;
        
    $('#loginForm').on('submit', (e) => {
        e.preventDefault();

        $email = $('#email').val();
        $pswrd = $('#pswrd').val();
        //TODO: Encriptar la pass una vez esté todo verificado.
        //$pswrd = hex_md5($('#pswrd').val());

        if (/^([\w.%+-]+)@([\w-]+\.)+([\w]{2,})$/.test($email)) {
            $login = '___email---' + $('#email').val();
        } else {
            $login = '___alias---' + $('#email').val();
        }
        $.ajax({
            url: $urlLogin + $pswrd + $login,
            type: 'POST',
            success: function ($token) {
                console.log($token);
                if ($token != '') {
                    localStorage.setItem('token', $token.token);
                    localStorage.setItem('id', $token.id);
                    window.location = '/';
                } else {
                    $('.messageError').css('display', 'block')
                }
            }
        });
    });
});