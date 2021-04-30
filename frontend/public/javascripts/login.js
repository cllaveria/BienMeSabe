$(document).ready(function () {
    
    let $user, $pswrd, $pswrdEncrypted;

    $('#loginForm').on('submit', (e) => {
        e.preventDefault();

        $user = $('#email').val();
        $pswrd = hex_md5($('#pswrd').val());

        if (!/^([\w.%+-]+)@([\w-]+\.)+([\w]{2,})$/.test($email)) {
            
        }

    })
})