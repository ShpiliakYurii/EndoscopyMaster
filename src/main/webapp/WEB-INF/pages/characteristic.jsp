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
  <title>${characteristic.name}</title>
</head>
<body>
<div class="container">
  <t:head/>
  <t:menu/>
  <div class="row content">
    <div class="col-sm-12 top-border">
      Редагування характеристики "${characteristic.name}".
      <br>
      <a href="${url}addNewCharacteristic" class="button col-sm-3">Назад</a>
    </div>
    <form:form method="post" commandName="characteristic" action="${url}execEditCharacteristic">
      <input type="hidden" value="${characteristic.idCharacteristic}" name="idCharacteristic">
      <div class="col-sm-12">
        <label>Назва характеристики</label>
        <form:input path="name" class='input-text'/>
        <label><form:errors path="name"></form:errors></label>
      </div>
      <div class="col-sm-12">
        <label>Ознака</label>
        <form:select path="idFeatures" class="input-text">
          <c:forEach items="${features}" var="f">
            <c:forEach items="${terms}" var="t">
              <c:if test="${f.idTerm == t.idTerm}">
                <c:forEach items="${regions}" var="r">
                  <c:if test="${t.idRegion == r.idRegion}">
                    <c:forEach items="${organs}" var="o">
                      <c:if test="${r.idOrgan == o.idOrgan}">
                        <form:option value="${f.idFeatures}">${o.name} / ${r.name} / ${t.name} / ${f.name}</form:option>
                      </c:if>
                    </c:forEach>
                  </c:if>
                </c:forEach>
              </c:if>
            </c:forEach>
          </c:forEach>
        </form:select>
      </div>
      <div class="col-sm-12">
        <label>Заключення</label>
        <form:select path="conclusionDictionaryId" class="input-text" name="conclusionDictionaryId">
          <c:forEach items="${conclusions}" var="c">
            <form:option value="${c.id}">${c.name}</form:option>
          </c:forEach>
        </form:select>
      </div>
      <div class="col-sm-12">
        <input type="submit" value="Зберегти" class="button col-sm-3"/>
        <a href="javascript:deleteCharacteristic('${characteristic.idCharacteristic}', '${characteristic.name}')" class="button col-sm-3">Видалити</a>
      </div>
    </form:form>
  </div>
</div>
</body>
</html>

