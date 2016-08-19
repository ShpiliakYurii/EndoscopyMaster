/**
 * Created by Yurii on 17.12.2015.
 */

var manipulations;
var manipulationPlace;
var operation;
var operationDescription;
var conclusions;
var conclusionsList;
var revisionNumber;

function getRevisionNumber() {
    return revisionNumber;
}

function initMas(concl) {
    manipulations = new Array();
    manipulationPlace = new Array();
    conclusions = concl;
    conclusionsList = new Array();
    operation = new Array();
    operationDescription = new Array();
}

function getManipulation() {
    return manipulations;
}

function getManipulationPlace() {
    return manipulationPlace;
}
function getOperation() {
    return operation;
}

function getOperationDescription() {
    return operationDescription;
}

function getConclusions() {
    return conclusionsList;
}

function addManipulation() {
    manipulations[manipulations.length] = $('#manipulation').val();
    manipulationPlace[manipulationPlace.length] = $('#manipulation-textarea').val();
    $('#manipulation-textarea').val("");
    showWindow("Маніпуляцію добавлено");

}

function addOperation() {
    console.log("operation " + $('#operation').val() + " description" + $('#operation-textarea').val());
    operation[operation.length] = $('#operation').val();
    operationDescription[operationDescription.length] = $('#operation-textarea').val();
    $('#operation-textarea').val("");
    showWindow("Операцію добавлено");
}

function addConclusion(value) {
    if(value == -1)
        return;
    for(var i = 0; i <conclusionsList.length; i++){
        if(conclusionsList[i] == value)
            return;
    }
    console.log(value);
    conclusionsList[conclusionsList.length] = value;
}


function checkConclusion(id) {
    for (var i = 0; i < conclusions.length; i++) {
        if (conclusions[i] == id)
            return true;
    }
    return false;
}

function doFinallyDescription() {
    var finallyDescription = "";
    var first = true;
    var organs = document.getElementById('organs');
    var organ = "";
    for (var i = 0; i < organs.childNodes.length; i++) {
        if (organs.childNodes[i].className == "organName") {
            organ = organs.childNodes[i].childNodes[0].textContent;
        }
        if (organs.childNodes[i].className == "regions") {
            console.log(organs.childNodes[i]);
            var regions = organs.childNodes[i];
            for (var j = 1; j < regions.childNodes.length; j++) {
                //if(regions.childNodes[j].childNodes[0].checked){
                //console.log(j+" "+regions.childNodes[j]);
                //}
                if (regions.childNodes[j].childNodes[1].checked) {
                    if (regions.childNodes[j].childNodes[6] != undefined) {
                        //console.log(regions.childNodes[j].childNodes[3].value);
                        if (first) {
                            finallyDescription += organ + " ";
                            first = false;
                        }
                        finallyDescription += regions.childNodes[j].childNodes[6].value;
                    }
                }
                j++;
            }
        }
        first = true;
    }
    var firstPatalogy = document.getElementById("firstPatalogy");
    var firstPatalogyRes;
    if (firstPatalogy.checked) {
        firstPatalogyRes = 1;
    } else {
        firstPatalogyRes = 0;
    }
    $.ajax({
        url: 'completeRevision',
        type: 'POST',
        async: false,
        data: ({description: finallyDescription, firstPatalogy: firstPatalogyRes}),
        success: function (data) {
            revisionNumber = data;
        },
        error: function (data) {
            alert("Виникла помилка");
        }
    });
}


function clientWidth() { // Ширина окна просмотра
    return document.compatMode == 'CSS1Compat' && !window.opera ? document.documentElement.clientWidth : document.body.clientWidth;
}
function clientHeight() { // Высота окна просмотра
    return document.compatMode == 'CSS1Compat' && !window.opera ? document.documentElement.clientHeight : document.body.clientHeight;
}

function showWindow(msg) {
    if (!document.getElementById('window1')) {
        var okno = document.createElement('div'),
            oknoWidth = 200,
            oknoHeight = 100;
        okno.id = 'window1';
        okno.style.position = 'fixed';
        okno.style.left = (clientWidth() - oknoWidth) + 'px';
        okno.style.top = (clientHeight() - oknoHeight) + 'px';
        okno.style.width = oknoWidth + 'px';
        okno.style.height = oknoHeight + 'px';
        okno.style.zIndex = '999';
        okno.style.boxSizing = 'border-box';
        okno.style.padding = '10px';
        okno.style.borderRadius = '7px';
        okno.style.background = 'rgba(237, 255, 238, 0.9)';
        okno.style.boxShadow = '0 0 10px rgba(0, 0, 0, 0.5)';
        okno.innerHTML ='<div onclick="this.parentNode.parentNode.removeChild(this.parentNode)" title="Закрыть" '
        +'style="position: absolute; top: 0; right: 8px; cursor: pointer; '
        +'font: 28px/1 Arial; color: red;">×</div>'
        +'<div style="text-align: center; padding: 5px;">'+msg+'</div>';

        document.body.appendChild(okno);
        setTimeout(function(){
            var okno = document.getElementById("window1");
            okno.parentNode.removeChild(okno);
        },4000);
    }
    return false;
};
