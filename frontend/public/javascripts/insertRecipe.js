/**
 * @function getForks
 * @description Concatenem una cadena per inserir en el DOM i mostrar la puntuació de la recepta.
 * @param {string} $forks Número de forquilles (puntuació) que te la recepta.
 * @return {string}
 */
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