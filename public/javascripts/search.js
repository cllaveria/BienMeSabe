$(document).ready(function () {

    let $ingredient, $numberPersons, $valueEnergMin, $valueEnergMax, $typePlate;

    $(document).on('change', '#inp_ingredient', () => {
        $ingredient= $('input[name=ingredient]').val();

        console.log($ingredient)
    })
});