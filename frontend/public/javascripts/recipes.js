/**
 * @fileoverview · Visualització de les receptes insertades en la BBDD. 
 * 
 * <p> History</p>
 * <p> 0.1 - Implementació de la visualització de les receptes</p>
 *  
 * @version     0.1
 * @author      Sergio Asensio Ruiz 
 * @copyright   bienmesabe.com
 * 
 */

$(document).ready(function () {
    /**
     * @constant $urlAllUsers
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor i recuperar tots els usuaris.
     */       
    const $urlAllUsers = 'http://localhost:8080/api/user/getUsers';
    /**
     * @constant $urlRecipes
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor i recuperar totes les receptes.
     */
    const $urlRecipes = 'http://localhost:8080/api/recipe/getRecipesByType/';
    /**
     * @constant $urlType
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor i recuperar tots els tipus de plats.
     */
    const $urlType = 'http://localhost:8080/api/recipeTypes/getRecipeTypes';
    /**
     * @constant $urlRecipe
     * @type {String}
     * @description Constant per emmagatzemar la ruta de connexió amb el servidor veure la fitxa de les receptes.
     */
    const $urlRecipe = 'http://localhost:3000/recetas/ficha?id=';
    /**
     * @constant $screenSize
     * @type {String}
     * @description Constant per emmagatzemar les dimensions de la finestra del navegador.
     */
    const $screenSize = window.screen.width;
    /** 
     * @var $countStarters 
     * @type {number}
     * @description Variable inicialitzada a 0 per contar les vegades que prenem el botó "VER MÁS" de les receptes de Entrantes.
     */
    
    let $countStarters = 0;
    /** 
     * @var $countFirsts 
     * @type {number}
     * @description Variable inicialitzada a 0 per contar les vegades que prenem el botó "VER MÁS" de les receptes de Primeros.
     */
    let $countFirsts = 0;
    /** 
     * @var $countSeconds 
     * @type {number}
     * @description Variable inicialitzada a 0 per contar les vegades que prenem el botó "VER MÁS" de les receptes de Segundos.
     */
    let $countSeconds = 0;
    /** 
     * @var $countDesserts 
     * @type {number}
     * @description Variable inicialitzada a 0 per contar les vegades que prenem el botó "VER MÁS" de les receptes de Postres.
     */
    let $countDesserts = 0;
    /** 
     * @var $countCakes 
     * @type {number}
     * @description Variable inicialitzada a 0 per contar les vegades que prenem el botó "VER MÁS" de les receptes de Repostería.
     */
    let $countCakes = 0;
    /** 
     * @var $forks 
     * @type {String}
     * @description Variable per emmagatzemar la cadena per inserir en el DOM per veure la puntuació mitjana de les receptes.
     */
    let $forks;
    /** 
     * @var $difficult 
     * @type {String}
     * @description Variable per emmagatzemar la dificultat de la recepta i mostar-lo per pantalla.
     */
    let $difficult;
    /** 
     * @var $userAlias 
     * @type {String}
     * @description Variable per emmagatzemar l'alies de l'usuari que ha creat la recepta i mostrar-lo per pantalla.
     */
    let $userAlias;
    /** 
     * @var $allUsers 
     * @type {Array}
     * @description Array per emmagatzemar tots els usuaris de la BBDD.
     */
    let $allUsers = [];
    /** 
     * @var $allPlatesStarters
     * @type {Array} 
     * @description Array per emmagatzemar totes les receptes del tipus de plat Entrantes.
     */
    let $allPlatesStarters = [];
    /** 
     * @var $allPlatesFirsts 
     * @type {Array}
     * @description Array per emmagatzemar totes les receptes del tipus de plat Primeros.
     */
    let $allPlatesFirsts = [];
    /** 
     * @var $allPlatesSeconds 
     * @type {Array}
     * @description Array per emmagatzemar totes les receptes del tipus de plat Segundos
     */
    let $allPlatesSeconds = [];
    /** 
     * @var $allPlatesDesserts 
     * @type {Array}
     * @description Array per emmagatzemar totes les receptes del tipus de plat Postres.
     */
    let $allPlatesDesserts = [];
    /** 
     * @var $allPlatesCakes 
     * @type {Array}
     * @description Array per emmagatzemar totes les receptes del tipus de plat Repostería.
     */
    let $allPlatesCakes = [];
    /** 
     * @var $allTypePlate 
     * @type {Array}
     * @description Array per emmagatzemar tots els tipus de plats.
     */
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
                                if ($allUsers[j][0] == $recipe[x].userId) {
                                    $userAlias = $allUsers[j][4];
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

    /**
     * @type {jQuery}
     * @type each
     * @listens .btn_more - Classe dels botons "VER MÁS" de les receptes.
     * @description Assignem a cada botó una funció ver veure mes receptes, segons la mida de la pantalla mostrarem de 4 en 4 (per la versió Mobile) o de 6 en 6 (per la versió Desktop i Tablet). 
     */
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

    /**
     * @function insertPlates
     * @description Funció per inserir en el DOM les receptes afegides a la BBDD.
     * @param {number} $count Número mínim per començar el compte en el for.
     * @param {number} $maxCount Numero màxim per finalitzar el compte en el for.
     * @param {object} $insertPlates objecte JSON amb les dades de la recepta.
     * @param {String} $insertTypePlate String amb el tipus de recepta.
     * @param {String} $insertTypePlateSpanish String amb el tipus de recepta en espanyol.
     */

    function insertPlates($count, $maxCount, $insertPlates, $insertTypePlate, $insertTypePlateSpanish) {
        for (let i = $count; i < $maxCount; i++) {
            for (let j = 0; j < $allUsers.length; j++) {
                if ($allUsers[j][0] == $insertPlates[i].userId) {
                    $userAlias = $allUsers[j][4];
                }
            }
            $forks = getForks($insertPlates[i].recipeAssessment);
            $difficult = getDifficult($insertPlates[i].recipeDifficult);

            $($insertTypePlate).append('<div class="rcp_cnt">\
                                        <a href="' + $urlRecipe + $insertPlates[i].id + '">\
                                            <div class="recipe ' + $insertTypePlateSpanish + '">\
                                                <img src="' + $insertPlates[i].image + '" alt="Entrantes" style="width: 100%;">\
                                                <div class="desc_rec">\
                                                    <h3 id="title">' + $insertPlates[i].name + '</h3>\
                                                    <p id="author">' + $userAlias + '</p>\
                                                </div>\
                                            </div>\
                                            <div class="info_rec">\
                                                <p id="level">Dificultad: ' + $difficult + '</p>\
                                                <div class="time_rec">\
                                                    <i class="fas fa-clock clock"></i>\
                                                    <p id="time">' + $insertPlates[i].recipeTime + ' min</p>\
                                                </div>\
                                            </div>\
                                            ' + $forks + '\
                                        </a>\
                                    </div>');
        }
    }

    /**
     * @function urlTypePlate
     * @description Quan polsem sobre el botó "VER MÁS" i s'ha polsat 3 vegades, es redirigeix a la pàgina "filtros".
     * @param {string} $namePlate nom del plat.
     */
    function urlTypePlate($namePlate) {
        for (let i = 0; i < $allTypePlate.length; i++) {
            if ($allTypePlate[i].name == $namePlate) {
                window.location.href = '../recetas/filtros?id=' + $allTypePlate[i].id
            }

        }
    }
});