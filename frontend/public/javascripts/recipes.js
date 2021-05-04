$(document).ready(function () {

    const $urlAllUsers = 'http://localhost:8080/api/user/getUsers';
    const $urlRecipes = 'http://localhost:8080/api/recipe/getRecipesByType/';
    const $urlType = 'http://localhost:8080/api/recipeTypes/getRecipeTypes';
    const $urlRecipe = 'http://localhost:3000/recetas/ficha?id=';

    const $screenSize = window.screen.width;

    let $countStarters = 0;
    let $countFirsts = 0;
    let $countSeconds = 0;
    let $countDesserts = 0;
    let $countCakes = 0;
    let $forks;
    let $difficult;
    let $userAlias;
    let $allUsers = [];
    let $allPlatesStarters = [];
    let $allPlatesFirsts = [];
    let $allPlatesSeconds = [];
    let $allPlatesDesserts = [];
    let $allPlatesCakes = [];
    let $allTypePlate = [];

    $.ajax({
        url: $urlAllUsers,
        type: 'GET',
        async: false,
        success: function ($users) {
            for (let i = 0; i < $users.length; i++) {
                $allUsers[i] = $users[i];
            }
        }
    });

    $.ajax({
        url: $urlType,
        type: 'GET',
        success: function ($typePlate) {
            for (let i = 0; i < $typePlate.length; i++) {
                $allTypePlate.push($typePlate[i]);
                $.ajax({
                    url: $urlRecipes + $typePlate[i].id,
                    type: 'GET',
                    async: false,
                    success: function ($recipe) {
                        for (let x = 0; x < $recipe.length; x++) {
                            for (let j = 0; j < $allUsers.length; j++) {
                                //TODO: BORRAR LO COMENTADO
                                if ( /* $allUsers[j].id */ $allUsers[j][0] == $recipe[x].userId) {
                                    $userAlias = /* $allUsers[j].alias */ $allUsers[j][4];
                                }
                            }

                            if ($recipe.length != 0) {
                                if ($typePlate[i].name == 'Entrantes') {
                                    $allPlatesStarters.push($recipe[x]);
                                } else if ($typePlate[i].name == 'Primeros') {
                                    $allPlatesFirsts.push($recipe[x]);
                                } else if ($typePlate[i].name == 'Segundos') {
                                    $allPlatesSeconds.push($recipe[x]);
                                } else if ($typePlate[i].name == 'Postres') {
                                    $allPlatesDesserts.push($recipe[x]);
                                } else if ($typePlate[i].name == 'Repostería') {
                                    $allPlatesCakes.push($recipe[x]);
                                }
                            }

                        }
                    }
                });
            }
            if ($screenSize <= 700) {
                if ($allPlatesStarters.length != 0) {
                    insertPlates(0, 4, $allPlatesStarters, '#starters', 'entrantes');
                }
                if ($allPlatesFirsts != 0) {
                    insertPlates(0, 4, $allPlatesFirsts, '#first', 'primeros');
                }
                if ($allPlatesSeconds != 0) {
                    insertPlates(0, 4, $allPlatesSeconds, '#second', 'segundos');
                }
                if ($allPlatesDesserts != 0) {
                    insertPlates(0, 4, $allPlatesDesserts, '#dessert', 'postres');
                }
                if ($allPlatesCakes != 0) {
                    insertPlates(0, 4, $allPlatesCakes, '#cake', 'repos');
                }
            } else {
                if ($allPlatesStarters.length != 0) {
                    insertPlates(0, 6, $allPlatesStarters, '#starters', 'entrantes');
                }
                if ($allPlatesFirsts != 0) {
                    insertPlates(0, 6, $allPlatesFirsts, '#first', 'primeros');
                }
                if ($allPlatesSeconds != 0) {
                    insertPlates(0, 6, $allPlatesSeconds, '#second', 'segundos');
                }
                if ($allPlatesDesserts != 0) {
                    insertPlates(0, 6, $allPlatesDesserts, '#dessert', 'postres');
                }
                if ($allPlatesCakes != 0) {
                    insertPlates(0, 6, $allPlatesCakes, '#cake', 'repos');
                }
            }
        }
    });

    function receivePlate($recipe, $userAlias, $forks, $difficult, $typePlate, $classPlate) {
        $($typePlate).append('<div class="rcp_cnt">\
                                <a href="' + $urlRecipe + $recipe.id + '">\
                                    <div class="recipe ' + $classPlate + '">\
                                        <img src="' + $recipe.image + '" alt="Entrantes" style="width: 100%;">\
                                        <div class="desc_rec">\
                                            <h3 id="title">' + $recipe.name + '</h3>\
                                            <p id="author">' + $userAlias + '</p>\
                                        </div>\
                                    </div>\
                                    <div class="info_rec">\
                                        <p id="level">Dificultad: ' + $difficult + '</p>\
                                        <div class="time_rec">\
                                            <i class="fas fa-clock clock"></i>\
                                            <p id="time">' + $recipe.recipeTime + ' min</p>\
                                        </div>\
                                    </div>\
                                    ' + $forks + '\
                                </a>\
                            </div>');
    }

    function getForks($forks) {
        let $insertForks = '<div class="score_rec">';
        for (let i = 0; i < $forks; i++) {
            $insertForks = $insertForks.concat('<img src="/images/tenedor-gold.svg" alt="tenerdor" style="width: 20px; height: 40px;">');
        }
        if ($forks != 5) {
            let $numberForks = 5 - $forks;
            for (let i = 0; i < $numberForks; i++) {
                $insertForks = $insertForks.concat('<img src="/images/tenedor-black.svg" alt="tenerdor" style="width: 20px; height: 40px;">');
            }
        }
        $insertForks = $insertForks.concat('</div>');
        return $insertForks;
    }

    function getDifficult($dificult) {
        let $insertDificult = '';
        switch ($dificult) {
            case 0:
                return $insertDificult = 'Muy baja';
            case 1:
                return $insertDificult = 'Baja';
            case 2:
                return $insertDificult = 'Media';
            case 3:
                return $insertDificult = 'Difícil';
            case 4:
                return $insertDificult = 'Muy difícil';
        }
    }
    $('.btn_more').each(function () {
        $(this).on('click', () => {
            let $typePlateButton = $(this).siblings()[1].id;
            if ($screenSize <= 700) {
                if ($typePlateButton == 'starters') {
                    if ($countStarters == 0) {
                        insertPlates(4, 8, $allPlatesStarters, '#starters', 'entrantes');
                    } else if ($countStarters == 1) {
                        insertPlates(8, 12, $allPlatesStarters, '#starters', 'entrantes');
                    } else if ($countStarters == 2) {
                        urlTypePlate('Entrantes');
                    }
                    $countStarters++;
                } else if ($typePlateButton == 'first') {
                    if ($countStarters == 0) {
                        insertPlates(4, 8, $allPlatesFirsts, '#first', 'primeros');
                    } else if ($countStarters == 1) {
                        insertPlates(8, 12, $allPlatesFirsts, '#first', 'primeros');
                    } else if ($countStarters == 2) {
                        urlTypePlate('Primeros');
                    }
                    $countFirsts++;
                } else if ($typePlateButton == 'second') {
                    if ($countStarters == 0) {
                        insertPlates(4, 8, $allPlatesSeconds, '#second', 'segundos');
                    } else if ($countStarters == 1) {
                        insertPlates(8, 12, $allPlatesSeconds, '#second', 'segundos');
                    } else if ($countStarters == 2) {
                        urlTypePlate('Segundos');
                    }
                    $countSeconds++;
                } else if ($typePlateButton == 'dessert') {
                    if ($countStarters == 0) {
                        insertPlates(4, 8, $allPlatesDesserts, '#dessert', 'postres');
                    } else if ($countStarters == 1) {
                        insertPlates(8, 12, $allPlatesDesserts, '#dessert', 'postres');
                    } else if ($countStarters == 2) {
                        urlTypePlate('Postres');
                    }
                    $countDesserts++;
                } else if ($typePlateButton == 'cake') {
                    if ($countStarters == 0) {
                        insertPlates(6, 12, $allPlatesCakes, '#cake', 'repos');
                    } else if ($countStarters == 1) {
                        insertPlates(12, 18, $allPlatesCakes, '#cake', 'repos');
                    } else if ($countStarters == 2) {
                        urlTypePlate('Repostería');
                    }
                    $countCakes++;
                }
            } else {
                if ($typePlateButton == 'starters') {
                    if ($countStarters == 0) {
                        insertPlates(6, 12, $allPlatesStarters, '#starters', 'entrantes');
                    } else if ($countStarters == 1) {
                        insertPlates(12, 18, $allPlatesStarters, '#starters', 'entrantes');
                    } else if ($countStarters == 2) {
                        urlTypePlate('Entrantes');
                    }
                    $countStarters++;
                } else if ($typePlateButton == 'first') {
                    if ($countStarters == 0) {
                        insertPlates(6, 12, $allPlatesFirsts, '#first', 'primeros');
                    } else if ($countStarters == 1) {
                        insertPlates(12, 18, $allPlatesFirsts, '#first', 'primeros');
                    } else if ($countStarters == 2) {
                        urlTypePlate('Primeros');
                    }
                    $countFirsts++;
                } else if ($typePlateButton == 'second') {
                    if ($countStarters == 0) {
                        insertPlates(6, 12, $allPlatesSeconds, '#second', 'segundos');
                    } else if ($countStarters == 1) {
                        insertPlates(12, 18, $allPlatesSeconds, '#second', 'segundos');
                    } else if ($countStarters == 2) {
                        urlTypePlate('Segundos');
                    }
                    $countSeconds++;
                } else if ($typePlateButton == 'dessert') {
                    if ($countStarters == 0) {
                        insertPlates(6, 12, $allPlatesDesserts, '#dessert', 'postres');
                    } else if ($countStarters == 1) {
                        insertPlates(12, 18, $allPlatesDesserts, '#dessert', 'postres');
                    } else if ($countStarters == 2) {
                        urlTypePlate('Postres');
                    }
                    $countDesserts++;
                } else if ($typePlateButton == 'cake') {
                    if ($countStarters == 0) {
                        insertPlates(6, 12, $allPlatesCakes, '#cake', 'repos');
                    } else if ($countStarters == 1) {
                        insertPlates(12, 18, $allPlatesCakes, '#cake', 'repos');
                    } else if ($countStarters == 2) {
                        urlTypePlate('Repostería');
                    }
                    $countCakes++;
                }
            }
        });
    });

    function insertPlates($count, $maxCount, $insertPlates, $insertTypePlate, $insertTypePlateSpanish) {
        for (let i = $count; i < $maxCount; i++) {
            for (let j = 0; j < $allUsers.length; j++) {
                //TODO: BORRAR LO COMENTADO
                if (/* $allUsers[j].id */ $allUsers[j][0] == $insertPlates[i].userId) {
                    $userAlias =  /* $allUsers[j].alias */ $allUsers[j][4];
                }
            }
            $forks = getForks($insertPlates[i].recipeAssessment);
            $difficult = getDifficult($insertPlates[i].recipeDifficult)
            receivePlate($insertPlates[i], $userAlias, $forks, $difficult, $insertTypePlate, $insertTypePlateSpanish);
        }
    }

    function urlTypePlate($namePlate) {
        for (let i = 0; i < $allTypePlate.length; i++) {
            if ($allTypePlate[i].name == $namePlate) {
                window.location.href = '../recetas/filtros?id=' + $allTypePlate[i].id
            }

        }
    }
});