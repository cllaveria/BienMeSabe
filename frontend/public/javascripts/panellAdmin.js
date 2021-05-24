$(document).ready(function () {
    const $urlMessagesContact = 'http://localhost:8080/api/user/getAdminContacts'
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
        url: $urlMessagesContact,
        type: 'GET',
        async: false,
        headers: {
            'Authorization': $token
        },
        success: function ($messages) {
            $.each($messages, function ($i, $message) {
                $('.message_container').append('<div class="message">\
                                            <div class="email">\
                                            <h5 class="emailUser" value="' + $message.message + '">' + $message.messageSubject + '</h5>\
                                                <i class="far fa-envelope"></i>\
                                                <i class="fas fa-trash-alt"></i>\
                                        </div></div>');
            });
        }
    });

    $('.emailUser').on('click', function () {
        $('.modal-header').html($(this).text())
        $('.modal-body').html($(this).attr('value'))
        $('#viewMessage').modal('show');
    });




});