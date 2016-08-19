<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<script>
    var j = 0;
    var previous = null;
    var concl = new Array(${conclusionDictionaries.size()});
    function putConcludion(item) {
        concl[j] = item;
        j++;
    }
    function getCharact(idFeatures) {
        var txt = "";
        <c:forEach items = '${characteristics}' var='characteristic'>
        if (idFeatures == ${characteristic.idFeatures}) {
            txt += "<option class='options' conclusionId='${characteristic.conclusionDictionaryId}'>${characteristic.name}</option>";
        }

        </c:forEach>
        return txt;
    }

    function makeBorder(m){
        document.getElementById("region" + m).style.border = "1px solid #337ab7";
        if(previous != null && previous != m)
            document.getElementById("region" + previous).style.border = null;
        previous = m;
    }

    function innerHtml(m, region) {
        makeBorder(m);
        var isFeature = false;
        var txt = "<span id = 'regionName' value='" + region + "'> Ділянка, що описується: " + region + "</span><ul>";
        <c:forEach items = '${terms}' var='term'>
        if (m == ${term.idRegion}) {
            txt += "<li t = '${term.name}' conclusionId = '${term.conclusionId}'>${term.name}";
            <c:forEach items = '${features}' var='feature'>
            if (${term.idTerm} == ${feature.idTerm}) {
                if (isFeature == false) {
                    txt += "<ul>";
                    isFeature = true;
                }
                txt += "<li class='term-item'><input type='checkbox' name='feature' value='${feature.name}' id='feature${feature.idFeatures}' conclusionId='${feature.conclusionDictionaryId}'>";
                txt += "<label for='feature${feature.idFeatures}'><span></span>${feature.name}</label>";
                var fl = ${feature.last};
                if (fl == 0) {
                    txt += "<select class='options' onclick='makeCheck(${feature.idFeatures})' name = 'char'>";
                    txt += getCharact(${feature.idFeatures});
                    txt += "</select>";
                }
                if (fl == 1) {
                    txt += "<input class='input-text' type ='text' size='20' onclick='makeCheck(${feature.idFeatures})' placeholder='${feature.placeholder}' p='${feature.placeholder}' >";
                }
                if (fl == 2) {
                    txt += "<select class='options' onclick='makeCheck(${feature.idFeatures})' name = 'char'>";
                    txt += getCharact(${feature.idFeatures});
                    txt += "</select>";
                    txt += "<input class='input-text' type ='text' size='20' onclick='makeCheck(${feature.idFeatures})' placeholder='${feature.placeholder}' p='${feature.placeholder}' >";
                }
                if (fl == 3){
                    txt += "<select class='options' onclick='makeCheck(${feature.idFeatures})' name = 'char'>";
                    txt += getCharact(${feature.idFeatures});
                    txt += "</select>";
                    txt += "<input class='input-text' type ='text' size='20' onclick='makeCheck(${feature.idFeatures})' placeholder='${feature.placeholder}' >";
                }
                if (fl == 4){
                    txt += "<input class='input-text' type ='text' size='20' onclick='makeCheck(${feature.idFeatures})' placeholder='${feature.placeholder}' >";
                }
                txt += "</li>";
            }
            </c:forEach>
            if (isFeature == true) {
                txt += "</ul>";
                isFeature = false;
            }
            var tl = ${term.last};
            if (tl == 1)
                txt += "<input class='input-text' id='termInput' type ='text' size='20' placeholder='${term.placeholder}'>";
            txt += "</li>";
        }
        </c:forEach>
        document.getElementById('description').innerHTML = txt + "</ul><span class='button' onclick='addDescriptionElement1(" + m + ")'>Добавити</span>";

    }

    function addDescriptionElement1(m) {
        var first = true;
        var firstTerm;
        var region = document.getElementById('description').children[0].getAttribute("value");
        var description = document.getElementById('description').children[1];
        var descriptionCount = description.childNodes.length;
        for (var i = 0; i < descriptionCount; i++) {
            var term = description.childNodes[i];
            if (term.hasAttribute('t')) {
                firstTerm = true;
                var featuresList = term.childNodes[1];
                //Якщо термін з полем для введення
                if (featuresList.id == 'termInput') {
                    if (featuresList.value.length != 0) {
                        if (first) {
                            if (!document.getElementById('rd' + m)) {
                                document.getElementById('region' + m).innerHTML += "<br><textarea rows='3' class='regionText' id='rd" + m + "'></textarea>";
                                //document.getElementById('rd' + m).value = region + ". ";
                            }
                            first = false;
                            document.getElementById('region' + m).childNodes[1].checked = true;
                        }
                        document.getElementById('rd' + m).value += term.getAttribute('t') + ": " + featuresList.value + "; ";
                        document.getElementById('description').innerHTML = "";
                        if (checkConclusion(term.getAttribute('conclusionId'))) {
                            //добавити заключення в масив
                            addConclusion(term.getAttribute('conclusionId'));
                        }
                    }
                } else {
                    //якщо для терміну є список ознак
                    for (var j = 0; j < featuresList.childNodes.length; j++) {
                        //якщо ознака відмічена галочкою
                        if (featuresList.childNodes[j].childNodes[0].checked) {
                            //ознака терміну
                            var feature = featuresList.childNodes[j].childNodes[0].value;
                            //значення характеристики
                            var characteristic = featuresList.childNodes[j].childNodes[2].value;
                            var characteristics = featuresList.childNodes[j].childNodes[2].childNodes;
                            for(var o = 0; o < characteristics.length; o++){
                                if(characteristics[o].childNodes[0].nodeValue == characteristic)
                                    addConclusion(characteristics[o].getAttribute('conclusionId'));
                            }
                            //поле для введенняд додаткової інформації
                            var addInf = featuresList.childNodes[j].childNodes[3];
                            if(addInf != undefined){
                                if(addInf.getAttribute("p") != null) {
                                    characteristic += " " + addInf.value + " " + addInf.getAttribute("p");
                                }else{
                                    characteristic += " " + addInf.value + " ";
                                }
                            }else{
                                if(featuresList.childNodes[j].childNodes[2].hasAttribute("placeholder") && featuresList.childNodes[j].childNodes[2].hasAttribute("p")){
                                    characteristic += " " +featuresList.childNodes[j].childNodes[2].getAttribute("p");
                                }
                            }
                            //якщо поле характеристики пусте видати помилку.
                            if (characteristic.length == 0) {
                                featuresList.childNodes[j].childNodes[2].placeholder = "Введіть дані.";
                                return;
                            }
                            //якщо це перша інформація, добавити текстареа і чек
                            if (first) {
                                if (!document.getElementById('rd' + m)) {
                                    document.getElementById('region' + m).innerHTML += "<br><textarea rows='3' class='regionText' id='rd" + m + "'></textarea>";
                                    //document.getElementById('rd' + m).value = region + ": ";
                                }
                                first = false;
                                document.getElementById('region' + m).childNodes[1].checked = true;
                            }
                            //добавляєм в текстареа інформацію
                            if(firstTerm){
                                document.getElementById('rd' + m).value += term.getAttribute('t') + ": ";
                                firstTerm = false;
                            }
                            document.getElementById('rd' + m).value += feature + " " + characteristic + ", ";
                            addConclusion(featuresList.childNodes[j].childNodes[0].getAttribute("conclusionId"));
                            document.getElementById('description').innerHTML = "";
                            if (checkConclusion(term.getAttribute('conclusionId'))) {
                                //добавити заключення в масив
                                addConclusion(term.getAttribute('conclusionId'));
                            }
                        }
                    }
                    if(!firstTerm){
                        document.getElementById('rd' + m).value = document.getElementById('rd' + m).value.substr(0, document.getElementById('rd' + m).value.length-2) + "; ";
                    }
                }
            }
        }
        if(document.getElementById("rd"+m)) {
            showWindow("Добавлено опис для " + region + ".");
        }
    }
</script>

<html>
<head>
    <title>Опис обстеження</title>
    <script type="text/javascript" src="<c:url value="/resources/s/jquery-1.11.3.min.js"/>"></script>
    <script src="<c:url value="/resources/s/description.js"/>"></script>
    <link href="<c:url value="/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
    <link href="<c:url value="/resources/c/css.css"/>" rel="stylesheet" media="screen">
    <link href="<c:url value="/resources/c/select.css"/>" rel="stylesheet" media="screen">
    <script src="<c:url value="/resources/s/select.js"/>"></script>
</head>
<body>
<div class="container">
    <t:head/>
    <t:menu/>
    <div class="row content">
        <div id="pacient-label" class="col-sm-12">
            <label>Пацієнт ${pacient}</label>
        </div>
        <div id="content" class="col-sm-12 col-md-12 col-lg-12">
            <div class="col-sm-5 col-md-5 col-lg-5" id="organs">
                <c:if test="${!empty organs}">
                    <c:forEach items="${organs}" var="organ">
                        <span class="organName">${organ.name}</span>
                        <ul class="regions">
                            <c:forEach items="${regions}" var="region">
                                <c:if test="${organ.idOrgan.equals(region.idOrgan)}">
                                    <li class="regionName" id="region${region.idRegion}"
                                        onclick="innerHtml('${region.idRegion}','${region.name}')">
                                        <input type="checkbox" name="region" value="${region.name}"
                                               id="reg${region.idRegion}">
                                        <label for="reg${region.idRegion}"><span></span>${region.name}</label>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </c:forEach>
                </c:if>

            </div>
            <div class="col-sm-7 col-md-7 col-lg-7" id="description">
            </div>
        </div>
        <div class="col-sm-12">
            <input type="checkbox" name="firstPatalogy" id="firstPatalogy">
            <label for="firstPatalogy"><span></span>Вперше виявлена паталогія</label>
        </div>
        <div class="col-sm-6">
            <c:if test="${!empty manipulationDictionaries}">
                <select id="manipulation" class="input-text">
                    <c:forEach items="${manipulationDictionaries}" var="manipulation">
                        <option value="${manipulation.id}">${manipulation.name}</option>
                    </c:forEach>
                </select>
                <br>Опис маніпуляції:<br>
                <textarea id="manipulation-textarea" rows="2"></textarea>
                <span class='button' onclick="addManipulation()">Добавити маніпуляцію</span>
            </c:if>
        </div>
        <div class="col-sm-6">
            <c:if test="${!empty operations}">
                <select id="operation" class="input-text">
                    <c:forEach items="${operations}" var="o">
                        <option value="${o.id}">${o.name}</option>
                    </c:forEach>
                </select>
                <br>Опис операції:<br>
                <textarea id="operation-textarea" rows="2"></textarea>
                <span class='button' onclick="addOperation()">Добавити операцію</span>
            </c:if>
        </div>
        <div class="col-sm-12">
            <span class='button' onclick="saveAll()">Завершити</span>
        </div>
        <c:forEach items="${conclusionDictionaries}" var="cond">
            <script>
                putConcludion(${cond.id});
            </script>
        </c:forEach>
    </div>
</div>
</body>
</html>
<script>

    function makeCheck(id) {
        document.getElementById('feature' + id).checked = true;
    }

    initMas(concl);

    function sendManipulation(index, place) {
        $.ajax({
            url: 'addManipulation',
            type: 'POST',
            async: false,
            data: ({index: index, place: place}),
            success: function () {
            }
        });
    }

    function sendOperation(index, description) {
        $.ajax({
            url: 'addOperation',
            type: 'POST',
            async: false,
            data: ({index: index, description: description}),
            success: function () {
            }
        });
    }

    function sendConclusion(cId) {
        $.ajax({
            url: 'addConclusion',
            type: 'POST',
            async: false,
            data: ({cId: cId}),
            success: function () {
            }
        });
    }

    function saveManipulations() {
        var m = getManipulation();
        var p = getManipulationPlace();
        for (var i = 0; i < m.length; i++) {
            sendManipulation(m[i], p[i]);
        }
    }

    function saveConclusions() {
        var c = getConclusions();
        for (var i = 0; i < c.length; i++) {
            sendConclusion(c[i]);
        }
    }

    function saveOperations() {
        var o = getOperation();
        var oD = getOperationDescription();
        for (var i = 0; i < o.length; i++) {
            sendOperation(o[i], oD[i]);
        }
    }

    function saveAll() {
        doFinallyDescription();
        saveConclusions();
        saveManipulations();
        saveOperations();
        console.log("Revision was added.");
        window.location = "/detail/"+getRevisionNumber();
    }
</script>
