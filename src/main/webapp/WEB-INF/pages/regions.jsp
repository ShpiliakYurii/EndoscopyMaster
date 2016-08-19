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
  <title>Словник ділянок</title>
</head>
<body>
<div class="container">
  <t:head/>
  <t:menu/>
  <div class="row content">
    <div class="col-sm-12">
      <div class="col-sm-4">Органи</div>
      <div class="col-sm-4">Ділянки</div>
      <div class="col-sm-4">Терміни</div>
    </div>
    <c:forEach items="${regions}" var="r">
      <div class="col-sm-12 top-border">
        <div class="col-sm-4">
          <c:forEach items="${organs}" var="o">
            <c:if test="${r.idOrgan == o.idOrgan}">
              <a href="/editOrgan/${o.id}">${o.name}</a>
            </c:if>
          </c:forEach>
        </div>
        <div class="col-sm-4">
          <a href="/editRegion/${r.idRegion}">${r.name}</a>
        </div>
        <div class="col-sm-4">
          <c:forEach items="${terms}" var="t">
            <c:if test="${r.idRegion == t.idRegion}">
              <a href="/editTerm/${t.idTerm}">${t.name}</a>
              <br>
            </c:if>
          </c:forEach>
        </div>
      </div>
    </c:forEach>
    <div class="col-sm-12 top-border">
      Щоб добавити нову ділянку, введіть її назву, вкажіть до якого органу вона повинна відноситись і натисніть кнопку добавити.
    </div>
    <form:form method="post" commandName="newRegion" action="${url}addNewRegion">
      <div class="col-sm-12">
        <label>Назва ділянки</label>
        <form:input path="name" class='input-text'/>
        <label><form:errors path="name"></form:errors></label>
      </div>
      <div class="col-sm-12">
        <label>Орган</label>
        <form:select path="idOrgan" class="input-text">
          <c:forEach items="${organs}" var="o">
            <form:option value="${o.idOrgan}">${o.name}</form:option>
          </c:forEach>
        </form:select>
      </div>
      <input type="submit" value="Добавити" class="button col-sm-4"/>
    </form:form>
  </div>
  </div>
</body>
</html>
