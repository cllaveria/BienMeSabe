/**
 * @fileoverview Implementació de la visualització de la recepta, la seva puntuació i la dificultat, perquè sigui accessible des dels altres fitxers.
 * 
 * <p> History </p>
 * 0.1 - Implementació de la visualització de la recepta, puntuació i dificultat.
 *  
 * @version     0.1
 * @author      Sergio Asensio Ruiz 
 * @copyright   bienmesabe.com
 * 
 */

/**
 * @function getDificult
 * @description Rebem un número que representa la dificultat de la recepta i l'hi assignem una cadena String amb el text de dificultat.
 * @param {string} $dificult Número que representa la dificultat de la recepta.
 * @return {string}
 */
function getDifficult($dificult) {

    switch ($dificult) {
        case 0:
            return 'Muy baja';
        case 1:
            return 'Baja';
        case 2:
            return 'Media';
        case 3:
            return 'Difícil';
        case 4:
            return 'Muy difícil';
    }
}

/**
 * @function getForks
 * @description Concatenem una cadena per inserir en el DOM i mostrar la puntuació de la recepta.
 * @param {string} $forks Número de forquilles (puntuació) que te la recepta.
 * @return {string}
 */
function getForks($forks) {
    let $insertForks = '<div class="score_rec">';

    for (let i = 0; i < $forks; i++) {
        $insertForks = $insertForks.concat('<img src="/images/tenedor-gold.svg" alt="tenedor" style="width: 20px; height: 40px;">');
    }
    if ($forks != 5) {
        let $numberForks = 5 - $forks;
        for (let i = 0; i < $numberForks; i++) {
            $insertForks = $insertForks.concat('<img src="/images/tenedor-black.svg" alt="tenedor" style="width: 20px; height: 40px;">');
        }
    }
    $insertForks = $insertForks.concat('</div>');
    return $insertForks;
}

/**
 * @function insertRecipe
 * @description Funció per inserir en el DOM les últimes receptes afegides a la BBDD.
 * @param {number} $min Número mínim per començar el compte en el for.
 * @param {number} $max Numero màxim per finalitzar el compte en el for.
 */
function insertRecipe($min, $max, $allUsers, $latestRecipes, $urlRecipe) {
    for (let i = $min; i < $max; i++) {
        for (let j = 0; j < $allUsers.length; j++) {
            if ($allUsers[j][0] == $latestRecipes[i].userId) {
                $userAlias = $allUsers[j][4];
            }
        }

        $forks = getForks($latestRecipes[i].recipeAssessment);
        $difficult = getDifficult($latestRecipes[i].recipeDifficult);
        $('.latest_rec').append('<div class="rcp_cnt">\
                                <a href="' + $urlRecipe + $latestRecipes[i].id + '">\
                                    <div class="recipe">\
                                        <img src="' + $latestRecipes[i].image + '" alt="image_' + $latestRecipes[i].name + '">\
                                        <div class="desc_rec">\
                                            <h3 id="title">' + $latestRecipes[i].name + '</h3>\
                                            <p id="author">' + $userAlias + '</p>\
                                        </div>\
                                    </div>\
                                    <div class="info_rec">\
                                        <p id="level">Dificultad: ' + $difficult + '</p>\
                                        <div class="time_rec">\
                                            <i class="fas fa-clock clock"></i>\
                                            <p id="time">' + $latestRecipes[i].recipeTime + ' min</p>\
                                        </div>\
                                    </div>\
                                    ' + $forks + '\
                                </a>\
                            </div>');
    }
}