<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Нове обстеження</title>
    <script src="<c:url value="/resources/s/description.js"/>"></script>
    <link href="<c:url value="/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
    <link href="<c:url value="/resources/c/css.css"/>" rel="stylesheet" media="screen">
    <script type="text/javascript" src="<c:url value="/resources/s/jquery-1.11.3.min.js"/>"></script>
    <link href="<c:url value="/resources/c/select.css"/>" rel="stylesheet" media="screen">
    <script src="<c:url value="/resources/s/select.js"/>"></script>
</head>
<body>
<div class="container">
    <t:head/>
    <t:menu/>
    <div class="row content">
        <form:form method="post" action="revisionDescription">
            <div class="col-sm-12">
                <h3>Крок 1. Вибір пацієнта.</h3>
                <c:if test="${!empty medicinecards}">
                    <select name="pacient" class="input-text">
                        <c:forEach items="${medicinecards}" var="medicinecard">
                            <option value="${medicinecard.id}">${medicinecard.pib}</option>
                        </c:forEach>
                    </select>
                </c:if>
                <a href="addNewMedicinecard">Додати нового пацієнта.</a>
            </div>
            <div class="col-sm-12">
                <h3>Крок 2. Вибір апарату для обстеження.</h3>
                <c:if test="${!empty apparatuses}">
                    <select name="apparat" class="input-text">
                        <c:forEach items="${apparatuses}" var="apparat">
                            <c:if test="${apparat.id == defaultApparatus}">
                                <option value="${apparat.id}" selected>${apparat.name}</option>
                            </c:if>
                            <c:if test="${apparat.id != defaultApparatus}">
                                <option value="${apparat.id}">${apparat.name}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </c:if>
                <a href="addNewApparatus">Додати новий апарат.</a>
            </div>
            <div class="col-sm-12">
                <h3>Крок 3. Вибір направлення пацієнта.</h3>
                <c:if test="${!empty refferalDictionaries}">
                    <select name="refferal" class="input-text">
                        <c:forEach items="${refferalDictionaries}" var="reff">
                            <option value="${reff.id}">${reff.name}</option>
                        </c:forEach>
                    </select>
                </c:if>
                <a href="addNewRefferal">Додати нове направлення.</a>
            </div>
            <div class="col-sm-12">
                <h3>Крок 4. Вибір дизінфекційного розчину для обробки апарату.</h3>
                <c:if test="${!empty disinfectantSolutions}">
                    <select name="solution" class="input-text">
                        <c:forEach items="${disinfectantSolutions}" var="disSol">
                            <c:if test="${disSol.id == defaultSolution}">
                                <option value="${disSol.id}" selected>${disSol.name}</option>
                            </c:if>
                            <c:if test="${disSol.id != defaultSolution}">
                                <option value="${disSol.id}">${disSol.name}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </c:if>
                <a href="addNewDisinfectantSolution">Додати новий дизінфекційний розчин.</a>
            </div>
            <div class="col-sm-12" id="rTypes">
                <h3>Крок 5. Вибір типу обстеження.</h3>
                <c:if test = "${!empty revisionTypeList}">
                    <c:forEach items = "${revisionTypeList}" var = "r">
                        <input type="radio" name="revisionType" value="${r.id}" id="r${r.id}">
                        <label for="r${r.id}"><span></span>${r.revisionName}</label>
                        <br>
                    </c:forEach>
                    <script>
                        document.getElementById('rTypes').childNodes[3].checked = true;
                    </script>
                </c:if>
                <a href="addNewRevisionType">Додати новий вид обстеження.</a>
                <input type="submit" value="Далі" class="button"/>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>


