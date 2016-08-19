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
  <script type="text/javascript" src="<c:url value="/resources/s/request.js"/>"></script>
  <title>Словник термінів</title>
</head>
<body>
<div class="container">
  <t:head/>
  <t:menu/>
  <div class="row content">
    <div class="col-sm-12">
      <div class="col-sm-4">Ділянки</div>
      <div class="col-sm-4">Терміни</div>
      <div class="col-sm-4">Ознаки</div>
    </div>
    <c:forEach items="${terms}" var="t">
      <div class="col-sm-12 top-border">
        <div class="col-sm-4">
          <c:forEach items="${regions}" var="r">
            <c:if test="${r.idRegion == t.idRegion}">
              <a href="/editRegion/${r.idRegion}">${r.name}</a>
            </c:if>
          </c:forEach>
        </div>
        <div class="col-sm-4">
          <a href="/editTerm/${t.idTerm}">${t.name}</a>
        </div>
        <div class="col-sm-4">
          <c:forEach items="${features}" var="f">
            <c:if test="${f.idTerm == t.idTerm}">
              <a href="/editFeature/${f.idFeatures}">${f.name}</a>
              <br>
            </c:if>
          </c:forEach>
        </div>
      </div>
    </c:forEach>
    <div class="col-sm-12 top-border">
      Щоб добавити новий термін, введіть його назву, вкажіть до якої ділянки він повинен відноситись і натисніть кнопку добавити.
    </div>
    <form:form method="post" commandName="newTerm" action="${url}addNewTerm">
      <div class="col-sm-12">
        <label>Назва терміну</label>
        <form:input path="name" class='input-text'/>
        <label><form:errors path="name"></form:errors></label>
      </div>
      <div class="col-sm-12">
        <label>Ділянка</label>
        <form:select path="idRegion" class="input-text">
          <c:forEach items="${regions}" var="r">
            <c:forEach items="${organs}" var="o">
              <c:if test="${o.idOrgan == r.idOrgan}">
                <form:option value="${r.idRegion}">${o.name} / ${r.name}</form:option>
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
      </div>
      <div class="col-sm-12">
        <label>Підказка</label>
        <form:input type="text" class="input-text" path="placeholder"/>
      </div>
      <div class="col-sm-12">
        <label>Заключення</label>
        <form:select path="conclusionId" class="input-text">
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