<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="url" value="http://localhost:8080/"/>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <link href="<c:url value="/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
  <link href="<c:url value="/resources/c/css.css"/>" rel="stylesheet" media="screen">
  <link href="<c:url value="/resources/c/select.css"/>" rel="stylesheet" media="screen">
  <script src="<c:url value="/resources/s/select.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/s/jquery-1.11.3.min.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/s/request.js"/>"></script>
  <title>Словник ознак</title>
</head>
<body>
<div class="container">
  <t:head/>
  <t:menu/>
  <div class="row content">
    <div class="col-sm-12">
      <div class="col-sm-4">Термін</div>
      <div class="col-sm-4">Ознака</div>
      <div class="col-sm-4">Характеристика</div>
    </div>
    <c:forEach items="${features}" var="f">
      <div class="col-sm-12 top-border">
        <div class="col-sm-4">
          <c:forEach items="${terms}" var="t">
            <c:if test="${t.idTerm == f.idTerm}">
              <a href="/editTerm/${t.idTerm}">${t.name}</a><br>
            </c:if>
          </c:forEach>
        </div>
        <div class="col-sm-4">
          <a href="/editFeature/${f.idFeatures}">${f.name}</a>
        </div>
        <div class="col-sm-4">
          <c:forEach items="${characteristics}" var="c">
            <c:if test="${c.idFeatures == f.idFeatures}">
              <a href="/editCharacteristic/${c.idCharacteristic}">${c.name}</a>
              <br>
            </c:if>
          </c:forEach>
        </div>
      </div>
    </c:forEach>
    <div class="col-sm-12 top-border">
      Щоб добавити нову ознаку, введіть її назву, виберіть термін до якого вона відноситься і натисніть кнопку добавити.
    </div>
    <form:form method="post" commandName="newFeature" action="${url}addNewFeature">
      <div class="col-sm-12">
        <label>Назва ознаки</label>
        <form:input path="name" class='input-text'/>
        <label><form:errors path="name"></form:errors></label>
      </div>
      <div class="col-sm-12">
        <label>Термін</label>
        <form:select path="idTerm" class="input-text">
          <c:forEach items="${terms}" var="t">
            <c:forEach items="${regions}" var="r">
              <c:if test="${t.idRegion == r.idRegion}">
                <c:forEach items="${organs}" var="o">
                  <c:if test="${r.idOrgan == o.idOrgan}">
                    <form:option value="${t.idTerm}">${o.name} / ${r.name} / ${t.name}</form:option>
                  </c:if>
                </c:forEach>
              </c:if>
            </c:forEach>
          </c:forEach>
        </form:select>
      </div>
      <div class="col-sm-12">
        <label>Останній</label><br>
        <input type="radio" name="last" value="0" checked id="t0"><label for="t0"><span></span>Ні</label>
        <br>
        <input type="radio" name="last" value="1" id="t1"><label for="t1"><span></span>Так, поле для введення інформації з розмірністю.</label>
        <br>
        <input type="radio" name="last" value="2" id="t2"><label for="t2"><span></span>Ні, випадаючий список + поле для введення інформації з розмірністю.</label>
        <br>
        <input type="radio" name="last" value="3" id="t3"><label for="t3"><span></span>Ні, випадаючий список + поле для введення інформації без розмірності.</label>
        <br>
        <input type="radio" name="last" value="4" id="t4"><label for="t4"><span></span>Так, поле для введення інформації без розмірності.</label>
      </div>
      <div class="col-sm-12">
        <label>Підказка</label>
        <form:input type="text" class="input-text" path="placeholder"/>
      </div>
      <div class="col-sm-12">
        <label>Заключення</label>
        <form:select path="conclusionDictionaryId" class="input-text">
          <c:forEach items="${conclusions}" var="c">
            <form:option value="${c.id}">${c.name}</form:option>
          </c:forEach>
        </form:select>
      </div>
      <input type="submit" value="Добавити" class="button col-sm-4"/>
    </form:form>
  </div>
</div>
</body>
</html>


