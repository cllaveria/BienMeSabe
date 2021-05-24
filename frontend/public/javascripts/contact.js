$(document).ready(function () {
    const $urlContact = 'http://localhost:8080/api/user/adminContact'
    let $name;
    let $email;
    let $message;
    let $JSONContact;
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

    $('.btn_contact').on('click', function (e) {
        e.preventDefault();
        $name = $('#name').val();
        $email = $('#email').val();
        $message = $('#textComment').val();

        $JSONContact = {
            'messageSubject': $email,
            'message': $name + ' comenta: ' + $message,
            'userId': eval($IDuser)
        }

        $JSONContact = JSON.stringify($JSONContact);

        $.ajax({
            url: $urlContact,
            type: 'POST',
            async: false,
            data: $JSONContact,
            headers: {
                'Authorization': $token
            },
            contentType: "application/json",
            dataType: "json",
            success: function () {}
        });
    });
});