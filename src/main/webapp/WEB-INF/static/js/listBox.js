$(function () {
    $(".select").select2({

        }
    );
});
function showDrivers(){
    var selectBox = document.getElementById('status');
    var userInput = selectBox.options[selectBox.selectedIndex].value;
    if(userInput == 'BROKEN'){
        document.getElementById('drivers').style.visibility = 'hidden';
    }
    else{
        document.getElementById('drivers').style.visibility = 'visible';
    }
    return false;
};



