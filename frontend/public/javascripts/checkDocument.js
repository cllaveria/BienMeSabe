/**
 * @fileoverview Verificació del NIF/DNI/NIE/CIF dels formularis de registre i modificació de dades: 
 * 
 * <p> History</p>
 * 0.1 - Implementació de la verificació NIF/DNI/NIE/CIF dels formularis.  
 *  
 * @version     0.1
 * @author      Sergio Asensio Ruiz 
 * @copyright   bienmesabe.com
 * 
 */

/**
 * @function checkDocument
 * @param {string} $document document introduït.
 * @description Comprova si el NIF/DNI, NIE o CIF introduït és correcte.
 */
function checkDocument($document, $nif) {
    if ($document == 'nif') {
        if ((checkNif($nif)) != true) {
            changeIconsError($('#nif'), $('#iconInfoNif'), $('#iconExcNif'), $('#iconCheckNif'));
            $('#iconExcNif').children().html('El NIF/DNI introducido no es correcto.');
            return false;
        } else {
            changeIconsCheck($('#nif'), $('#iconInfoNif'), $('#iconExcNif'), $('#iconCheckNif'));
            return true;
        }
    } else if ($document == 'nie') {
        if ((checkNie($nif)) != true) {
            changeIconsError($('#nif'), $('#iconInfoNif'), $('#iconExcNif'), $('#iconCheckNif'));
            $('#iconExcNif').children().html('El NIE introducido no es correcto.');
            return false;
        } else {
            changeIconsCheck($('#nif'), $('#iconInfoNif'), $('#iconExcNif'), $('#iconCheckNif'));
            return true;
        }
    } else if ($document == 'cif') {
        if ((checkCif($nif)) != true) {
            changeIconsError($('#nif'), $('#iconInfoNif'), $('#iconExcNif'), $('#iconCheckNif'));
            $('#iconExcNif').children().html('El CIF introducido no es correcto.');
            return false;
        } else {
            changeIconsCheck($('#nif'), $('#iconInfoNif'), $('#iconExcNif'), $('#iconCheckNif'));
            return true;
        }
    }
}

/**
 * @function checkNif
 * @description Comprova si el NIF/DNI introduït és correcte.
 * @param {string} $nif NIF/DNI introduït.
 * @returns {boolean}
 */
function checkNif($nif) {
    let $letter = ($nif.charAt($nif.length - 1));
    let $calcNif = $nif.substr(0, 8);
    let $compareLetter = checkLetter($calcNif);
    if ($compareLetter != $letter) {
        return false;
    } else {
        return true;
    }
}

/**
 * @function checkNie
 * @description Comprova si el NIE introduït és correcte.
 * @param {string} $nie NIE introduït.
 * @returns {boolean}
 */
function checkNie($nie) {
    let $insertNumber;
    let $letter = ($nie.charAt($nie.length - 1));
    let $initialLetter = ($nie.charAt(0));
    // Es compara la primera lletra del NIE per a donar-li un valor numèric.
    switch ($initialLetter) {
        case 'X':
            $insertNumber = 0;
            break;
        case 'Y':
            $insertNumber = 1;
            break;
        case 'Z':
            $insertNumber = 2;
            break;
    }
    // Una vegada obtingut el resultat, s'insereix el número resultat a la resta del NIE, però traient el número final.
    let $calcNie = $insertNumber + $nie.substr(1, 7);
    let $compareLetter = checkLetter($calcNie);
    if ($compareLetter != $letter) {
        return false;
    } else {
        return true;
    }
}

/**
 * @function checkLetter
 * @description Realitza el calcul de la numeració per saber si el NIF/NIE o DNI són correctes.
 * @param {number} $numeration numeració del NIF/DNI o NIE
 * @returns {string}
 */
function checkLetter($numeration) {
    let $calcNumeration = ($numeration / 23);
    let $calcNumeration2 = $calcNumeration - Math.trunc($calcNumeration);
    let $calcNumeration3 = $calcNumeration2 * 23;
    let $result = Math.round($calcNumeration3);
    switch ($result) {
        case 0:
            return 'T';
        case 1:
            return 'R';
        case 2:
            return 'W';
        case 3:
            return 'A';
        case 4:
            return 'G';
        case 5:
            return 'M';
        case 6:
            return 'Y';
        case 7:
            return 'F';
        case 8:
            return 'P';
        case 9:
            return 'D';
        case 10:
            return 'X';
        case 11:
            return 'B';
        case 12:
            return 'N';
        case 13:
            return 'J';
        case 14:
            return 'Z';
        case 15:
            return 'S';
        case 16:
            return 'Q';
        case 17:
            return 'V';
        case 18:
            return 'H';
        case 19:
            return 'L';
        case 20:
            return 'C';
        case 21:
            return 'K';
        case 22:
            return 'E';
    }
}

/**
 * @function checkCif
 * @description Comprova si el CIF introduït és correcte.
 * @param {string} $cif CIF introduït.
 * @returns {boolean}
 */
function checkCif($cif) {
    let $pairsSum = 0;
    let $oddSum = 0;
    let $firstOddResult = '';
    let $arrayOrganization = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'U', 'V', 'W'];
    let $letterOrNumber = $cif.charAt($cif.length - 1);
    for (let i = 0; i < 10; i++) {
        // Si el resultat coincideix amb un número, aquest s'avaluarà com a número.
        if ($letterOrNumber == i) {
            $letterOrNumber = eval($letterOrNumber);
        }
    }
    let $letterOrganization = $cif.charAt(0);
    let $coincidence = $.inArray($letterOrganization, $arrayOrganization);
    // Si no hi ha cap coincidència, el resultat serà -1 i retornarà false.
    if ($coincidence == -1) {
        return false;
    }
    let $numeration = $cif.substr(1, 7);
    let $arrayNumeration = $numeration.split('');
    for (let i = 0; i < $arrayNumeration.length; i++) {
        if (i % 2 == 0) {
            $firstOddResult = parseInt($arrayNumeration[i]) * 2;
            let $x = $firstOddResult.toString().length;
            if ($x == 2) {
                let $innerOddSum = 0;
                let $arrayResult = $firstOddResult.toString().split('');
                $firstOddResult = 0;
                for (let i = 0; i < $arrayResult.length; i++) {
                    $innerOddSum += parseInt($arrayResult[i]);
                }
                $firstOddResult += $innerOddSum;
            }
            $oddSum += $firstOddResult;
        } else {
            $pairsSum += parseInt($arrayNumeration[i]);
        }
    }
    let $semifinalResult = $pairsSum + $oddSum;
    let $finalNumber = $semifinalResult.toString().charAt(1);
    let $result = 10 - $finalNumber;
    if ($result == 10) {
        $result = $result.toString().charAt(1);
    }
    if (typeof ($letterOrNumber) == 'number') {
        if ($result != parseInt($letterOrNumber)) {
            return false;
        } else {
            return true;
        }
    } else if (typeof ($letterOrNumber) == 'string') {
        switch ($result) {
            case 0:
                $result = 'J';
                break;
            case 1:
                $result = 'A';
                break;
            case 2:
                $result = 'B'
                break;
            case 3:
                $result = 'C';
                break;
            case 4:
                $result = 'D';
                break;
            case 5:
                $result = 'E';
                break;
            case 6:
                $result = 'F';
                break;
            case 7:
                $result = 'G';
                break;
            case 8:
                $result = 'H';
                break;
            case 9:
                $result = 'I';
                break;
            case 10:
                $result = 'J';
                break;
        }
        if ($result != $letterOrNumber) {
            return false;
        } else {
            return true;
        }
    }
}

/**
 * @param {*} input Fa referèncía a l'input que s'està validant.
 * @param {*} iconInfo Icona d'informació del input.
 * @param {*} iconExcl Icona d'exclamació del input.
 * @param {*} iconCheck Icono de check del input.
 * @description Si la dada de l'input és buida modifica els displays de les icones per passar 
 * a mostrar la informació i treu la classe error i/o success.
 */
function changeIconsInfo(input, iconInfo, iconExcl, iconCheck) {
    iconInfo.css("display", "block");
    iconCheck.css("display", "none");
    iconExcl.css("display", "none");
    input.removeClass("error");
    input.removeClass("success");
}

/**
 * @param {*} input Fa referèncía a l'input que s'està validant.
 * @param {*} iconInfo Icona d'informació del input.
 * @param {*} iconExcl Icona d'exclamació del input.
 * @param {*} iconCheck Icono de check del input.
 * @description Si la dada de l'input no és correcta modifica els displays de les icones per passar 
 * a mostrar l'exclamació i afegeix la classe error a l'input per  mostrar el focus i borders en vermell.
 */
function changeIconsError(input, iconInfo, iconExcl, iconCheck) {
    iconExcl.css("display", "block");
    iconInfo.css("display", "none");
    iconCheck.css("display", "none");
    input.addClass("error");
}

/**
 * @param {*} input Fa referèncía a l'input que s'està validant.
 * @param {*} iconInfo Icona d'informació del input.
 * @param {*} iconExcl Icona d'exclamació del input.
 * @param {*} iconCheck Icono de check del input.
 * @description Si la dada de l'input és correcta modifica els displays de les icones per passar 
 * a mostrar el check. Treu la classe error de l'input i li afegeix la classe success per mostrar el focus en verd.
 */
function changeIconsCheck(input, iconInfo, iconExcl, iconCheck) {
    iconCheck.css("display", "block");
    iconInfo.css("display", "none");
    iconExcl.css("display", "none");
    input.removeClass("error");
    input.addClass("success");
}