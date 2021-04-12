$(document).ready(function () {
    

    let $id = (window.location.search).substr(8,)

    console.log($id)

    $.ajax({
        url: 'http://localhost:8080/api/recipe/getRecipesById/'+$id,
        type: 'GET',
        success: function (data){
            console.log(data)
            $('.recetas').append('<span> Receta: '+data.name)
        }
    })
});