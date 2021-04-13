/**
 * @fileoverview Cerca de receptes personalitzat: 
 * <p> Mostra per pantalla el resultat de la cerca personalitzada.</p>
 * 
 * <p> History</p>
 * 0.1 - Implementació de la cerca de receptes.  
 *  
 * @version     0.1
 * @author      Sergio Asensio Ruiz 
 * @copyright   bienmesabe.com
 * 
 */

$(document).ready(function () {

    let $urlSearch = 'http://localhost:8080/api/recipe/getRecipesByFilters/';
    let $search = (window.location.search).substr(1, );

    $.ajax({
        url: $urlSearch + $search,
        type: 'GET',
        success: function (data) {
            for (let i = 0; i < data.length; i++) {
                $('.recetas').append('<div style="border: 1px solid #CED4DA;display: flex;align-items: center;border-radius: .25rem;max-width: 60%;margin-left: 10px;padding: 0px 10px;">\
                                        <span id="recipes" style="min-width: 275px;"><text style="font-weight:700;">Receta:</text> ' + data[i].name + '</span>\
                                        <img style="width: 50%;padding:10px;border-radius: 1.25rem;" src="' + data[i].image + '"></img>\
                                        </div>\
                                    </div>');
            }
        }
    });
});