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
  <title>${term.name}</title>
</head>
<body>
<div class="container">
  <t:head/>
  <t:menu/>
  <div class="row content">
    <div class="col-sm-12 top-border">
      Редагування терміну "${term.name}".
      <br>
      <a href="${url}addNewTerm" class="button col-sm-3">Назад</a>
    </div>
    <form:form method="post" commandName="term" action="${url}execEditTerm">
      <input type="hidden" name="idTerm" value="${term.idTerm}">
      <div class="col-sm-12">
        <label>Назва терміну</label>
        <form:input path="name" name="name" class='input-text' value="${term.name}"/>
        <label><form:errors path="name"></form:errors></label>
      </div>
      <div class="col-sm-12">
        <label>Ділянка</label>
        <form:select path="idRegion" class="input-text" name="idRegion">
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
        <%--<c:if test="${term.last == 0}">
          <input type="radio" name="last" value="0" checked id="t0"><label for="t0"><span></span>Ні</label><br>
          <input type="radio" name="last" value="1" id="t1"><label for="t1"><span></span>Так, поле для введення інформації.</label>
        </c:if>
        <c:if test="${term.last == 1}">
          <input type="radio" name="last" value="0" id="t0"><label for="t0"><span></span>Ні</label><br>
          <input type="radio" name="last" value="1" checked id="t1"><label for="t1"><span></span>Так, поле для введення інформації.</label>
        </c:if>--%>
        <label>Останній</label><br>
        <input type="radio" name="last" value="0" id="t0"/><label for="t0"><span></span>Ні</label>
        <br>
        <input type="radio" name="last" value="1" id="t1"/><label for="t1"><span></span>Так, поле для введення інформації з розмірністю.</label>
      </div>
      <div class="col-sm-12">
        <label>Підказка</label>
        <form:input type="text" class="input-text" path="placeholder" name="placeholder"/>
      </div>
      <div class="col-sm-12">
        <label>Заключення</label>
        <form:select path="conclusionId" class="input-text" name="conclusionId">
          <c:forEach items="${conclusions}" var="c">
            <form:option value="${c.id}">${c.name}</form:option>
          </c:forEach>
        </form:select>
      </div>
      <div class="col-sm-12">
        <input type="submit" value="Зберегти" class="button col-sm-3"/>
        <a href="javascript:deleteTerm('${term.idTerm}', '${term.name}')" class="button col-sm-3">Видалити</a>
      </div>
    </form:form>
  </div>
</div>
</body>
</html>

<script>
  document.getElementById("t" + ${term.last}).checked = true;
  </script>