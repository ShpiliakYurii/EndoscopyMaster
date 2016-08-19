<%@ tag description="Template Tag" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row top-menu ">
    <ul class="Navigation">
        <li class="col-md-2 col-sm-2"><a href="/newRevision">Нове обстеження</a></li>
        <li class="col-md-2 col-sm-2"><a href="/takeManipulations/0">Маніпуляції</a></li>
        <li class="col-md-2 col-sm-2"><a href="/addNewMedicinecard">Пацієнти</a></li>
        <li class="col-md-1 col-sm-1"><a href="/revisionList">Журнал</a></li>
        <li class="col-md-2 col-sm-2">
            <a href="#">Словники</a>
            <ul>
                <li><a href="/addNewRevisionType">Словник типів обстежень</a></li>
                <li><a href="/addNewOrgan">Словник органів</a></li>
                <li><a href="/addNewRegion">Словник ділянок</a></li>
                <li><a href="/addNewTerm">Словник термінів</a></li>
                <li><a href="/addNewFeature">Словник ознак</a></li>
                <li><a href="/addNewCharacteristic">Словник характеристик</a></li>
                <li><a href="/addNewApparatus">Словник апаратів</a></li>
                <li><a href="/addNewDisinfectantSolution">Словник дизінфекційних розчинів</a></li>
                <li><a href="/addNewRefferal">Словник направлень</a></li>
                <li><a href="/addNewConlusion">Словник заключень</a></li>
                <li><a href="/addNewRecomendation">Словник рекомендацій</a></li>
                <li><a href="/addNewManipulation">Словник маніпуляцій</a></li>
                <li><a href="/addNewOperation">Словник операцій</a></li>
            </ul>
        </li>
        <li class="col-md-1 col-sm-1">
            <a href="#">Звіти</a>
            <ul>
                <li><a href="/monthReport">За місяць</a></li>
                <li><a href="/yearReport">За рік</a></li>
                <li><a href="/quarter/1">За перший квартал</a></li>
                <li><a href="/quarter/2">За другий квартал</a></li>
                <li><a href="/quarter/3">За третій квартал</a></li>
            </ul>
        </li>
        <li class="col-md-2 col-sm-2"><a href="/settings">Налаштування</a></li>
    </ul>
</div>