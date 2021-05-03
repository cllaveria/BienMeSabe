$(document).ready(function () {
    const $urlLogin = 'http://localhost:8080/api/user/loginUser?data=password---'
    let $pswrd;
    let $alias = '';
    let $email = '';
    $('#loginForm').on('submit', (e) => {
        e.preventDefault();

        $email = $('#email').val();
        $pswrd = $('#pswrd').val();
        //TODO: Encriptar la pass una vez esté todo verificado.
        //$pswrd = hex_md5($('#pswrd').val());

        if (/^([\w.%+-]+)@([\w-]+\.)+([\w]{2,})$/.test($email)) {
            $email = $('#email').val();
        } else {
            $alias = $('#email').val();
        }
        $.ajax({
            url: $urlLogin + $pswrd + '___email---' + $email + '___alias---' + $alias,
            type: 'POST',
            success: function ($token){
                console.log($token);
                if($token != ''){
                    localStorage.setItem('token', $token);
                    window.location = '/';
                }else{
                    $('.messageError').css('display', 'block')
                }       
            }
        });
    });
});