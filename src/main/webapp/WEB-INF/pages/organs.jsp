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
  <title>Словник органів</title>
</head>
<body>
<div class="container">
  <t:head/>
  <t:menu/>
  <div class="row content">
    <div class="col-sm-12">
      <div class="col-sm-4">Тип обстеження</div>
      <div class="col-sm-4">Органи</div>
      <div class="col-sm-4">Ділянки</div>
    </div>
    <c:forEach items="${uniqueOrgans}" var="o">
      <div class="col-sm-12 top-border">
        <div class="col-sm-4">
          <c:forEach items="${organs}" var="o1">
            <c:if test="${o1.name.equals(o.name)}">
              <c:forEach items="${revisionTypes}" var="rTypes">
              <c:if test="${o1.idRevisionType == rTypes.id}">
                <a href="/editRevisionType/${rTypes.id}">${rTypes.revisionName}</a><br>
              </c:if>
            </c:forEach>
            </c:if>
          </c:forEach>

        </div>
        <div class="col-sm-4">
          <a href="/editOrgan/${o.id}">${o.name}</a><br>
        </div>
        <div class="col-sm-4">
          <c:forEach items="${regions}" var="r">
            <c:if test="${o.idOrgan == r.idOrgan}">
              <a href="/editRegion/${r.idRegion}">${r.name}</a>
              <br>
            </c:if>
          </c:forEach>
        </div>
      </div>
    </c:forEach>
    <div class="col-sm-12 top-border">
      Щоб добавити новий орган, введіть його назву, вкажіть до якого типу обстеження він повинен відноситись і натисніть кнопку добавити.
    </div>
    <form:form method="post" commandName="newOrgan" action="${url}addNewOrgan">
      <div class="col-sm-12">
        <label>Назва органу</label>
        <form:input path="name" class='input-text'/>
        <label><form:errors path="name"></form:errors></label>
      </div>
      <div class="col-sm-12">
        <label>Тип обстеження</label>
        <form:select path="idRevisionType" class="input-text">
          <c:forEach items="${revisionTypes}" var="rTypes">
            <form:option value="${rTypes.id}">${rTypes.revisionName}</form:option>
          </c:forEach>
        </form:select>
      </div>
      <input type="submit" value="Добавити" class="button col-sm-4"/>
    </form:form>
  </div>
</div>
</body>
</html>
