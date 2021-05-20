$(document).ready(function () {
    const $urlRecipe = 'http://localhost:8080/api/recipe/getRecipesById/',
        $urlIngredients = 'http://localhost:8080/api/ingredient/getIngredients';

    let $idRecipe = (window.location.search).substr(4, ),
        $arrayIngredients = [],
        $ingredientUnity,
        $countModifyRecipeIngredients = 0,
        $countAddRecipeIngredients = 6,
        $countModiyRecipeSteps = 0,
        $countAddRecipeSteps = 5;

    let $token, $IDuser;

    if (token() == true) {
        $token = localStorage.getItem('token');
        $IDuser = localStorage.getItem('id');
    }

    $.ajax({
        url: $urlIngredients,
        type: 'GET',
        async: false,
        success: function ($ingredientsRecipe) {
            $.each($ingredientsRecipe, function ($index, $ingredients) {
                $arrayIngredients.push($ingredients);
            });
        }
    })

    if ($idRecipe != '') {
        $.ajax({
            url: $urlRecipe + $idRecipe,
            type: 'GET',
            headers: {
                'Authorization': $token
            },
            success: function ($recipe) {
                console.log($recipe);
                $('#inp_name').val($recipe.name)
                $('#imageVideoRecipe').text(trimImage($recipe.image))
                $('#inp_description').text($recipe.recipeInitDescription);
                console.log($recipe.recipeIngredients.length);
                if ($recipe.recipeIngredients.length > 0) {
                    $('.ingr_cont').remove();
                    $.each($recipe.recipeIngredients, function ($indexI, $recipeIngredients) {
                        $.each($arrayIngredients, function ($indexX, $nameIngredients) {
                            if ($nameIngredients.id == $recipeIngredients.ingredientId) {
                                if ($recipeIngredients.ingredientUnity == 'G') {
                                    $ingredientUnity = '<option>u.</option>\
                                                        <option selected>gr</option>\
                                                        <option>chr.</option>';
                                } else if ($recipeIngredients.ingredientUnity == 'U') {
                                    $ingredientUnity = '<option selected>u.</option>\
                                                        <option>gr</option>\
                                                        <option>chr.</option>';
                                } else if ($recipeIngredients.ingredientUnity == 'S') {
                                    $ingredientUnity = '<option>u.</option>\
                                                        <option>gr</option>\
                                                        <option selected>chr.</option>';
                                }
                                $('#ingredients').append('<div class="ingr_cont">\
                                                            <div class="ingredients">\
                                                                <div class="cantidad">\
                                                                    <select>\
                                                                        ' + $ingredientUnity + '\
                                                                    </select>\
                                                                    <input type="text" placeholder="Cantidad" value="' + $recipeIngredients.ingredientQTY + '" style="width: 150px;">\
                                                                </div>\
                                                                <input list="list_ingredient" name="ingredient" id="inp_ingredient_' + $indexI + '"\
                                                                placeholder="Introduce un ingrediente esté o no en la lista" value="' + $nameIngredients.name + '" style="width: 100%;">\
                                                                <datalist id="list_ingredient"></datalist>\
                                                            </div>\
                                                        </div>');
                                $countModifyRecipeIngredients++;
                            }
                        });
                    });
                }
                addIngredients($countModifyRecipeIngredients);
                $.each($arrayIngredients, function ($index, $ingredients) {
                    $('#list_ingredient').append("<option>" + $ingredients.name + "</option>");
                });
                if ($recipe.recipeSteps.length > 0) {
                    $('.pasos_cont').remove();
                    $.each($recipe.recipeSteps, function ($index, $recipeSteps) {
                        $('#steps').append('<div class="pasos_cont">\
                                                <div class="pasos">\
                                                    <div class="inputs">\
                                                        <label>Descripción paso ' + ($index + 1) + '</label>\
                                                        <textarea class="desc_pas" id="desc_pas_' + $index + '" type="text"\
                                                         placeholder="Introduce la descripción del paso ' + ($index + 1) + '">' + $recipeSteps.stepDescription + '</textarea>\
                                                    </div>\
                                                    <div class="inputs dropzone">\
                                                        <label>Imagen paso 1</label>\
                                                        <div class="dropzone dropzone-single" data-toggle="dropzone" data-dropzone-url="http://">\
                                                            <div class="fallback">\
                                                                <div class="custom-file">\
                                                                    <input type="file" class="custom-file-input" id="dropzoneBasicUpload">\
                                                                    <label class="custom-file-label" for="dropzoneBasicUpload">' + trimImage($recipeSteps.image) + '</label>\
                                                                </div>\
                                                            </div>\
                                                        </div>\
                                                    </div>\
                                                </div>\
                                            </div>');
                        $countModiyRecipeSteps++;
                    });
                }
                addSteps($countModiyRecipeSteps)
                $('#inp_descriptionEnding').text($recipe.recipeEndingDescription)
            }
        });


    } else {
        addIngredients($countAddRecipeIngredients);
        addSteps($countAddRecipeSteps)
        console.log('no entra porque no tiene id')
    }

    function trimImage($image) {
        return ($image).substr(7, );
    }

    function addIngredients($addIngredients) {
        $('.btn_addIngr').on('click', () => {
            $('#ingredients').append('<div class="ingr_cont">\
                                        <div class="ingredients">\
                                            <div class="cantidad">\
                                                <select>\
                                                    <option>u.</option>\
                                                    <option>gr</option>\
                                                    <option>chr.</option>\
                                                </select>\
                                                <input type="text" placeholder="Cantidad" style="width: 150px;">\
                                            </div>\
                                            <input list="list_ingredient" name="ingredient" id="inp_ingredient_' + $addIngredients + '"\
                                            placeholder="Introduce un ingrediente esté o no en la lista" style="width: 100%;">\
                                            <datalist id="list_ingredient"></datalist>\
                                        </div>\
                                    </div>');
            $addIngredients++;
        });
    }

    function addSteps($addSteps) {
        $('.btn_addPasos').on('click', () => {
            $('#steps').append('<div class="pasos_cont">\
                                                <div class="pasos">\
                                                    <div class="inputs">\
                                                        <label>Descripción paso ' + ($addSteps + 1) + '</label>\
                                                        <textarea class="desc_pas" id="desc_pas_' + $addSteps + '" type="text"\
                                                         placeholder="Introduce la descripción del paso ' + ($addSteps + 1) + '"></textarea>\
                                                    </div>\
                                                    <div class="inputs dropzone">\
                                                        <label>Imagen paso 1</label>\
                                                        <div class="dropzone dropzone-single" data-toggle="dropzone" data-dropzone-url="http://">\
                                                            <div class="fallback">\
                                                                <div class="custom-file">\
                                                                    <input type="file" class="custom-file-input" id="dropzoneBasicUpload">\
                                                                    <label class="custom-file-label" for="dropzoneBasicUpload"></label>\
                                                                </div>\
                                                            </div>\
                                                        </div>\
                                                    </div>\
                                                </div>\
                                            </div>');
            $addSteps++;
        });
    }
});