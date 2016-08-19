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
  <title>${organ.name}</title>
</head>
<body>
<div class="container">
  <t:head/>
  <t:menu/>
  <div class="row content">
    <div class="col-sm-12 top-border">
      Редагування органу "${organ.name}".
      <br>
      <a href="${url}addNewOrgan" class="button col-sm-3">Назад</a>
    </div>
    <form:form method="post" commandName="organ" action="${url}execEditOrgan">
      <div class="col-sm-12">
        <label>Назва органу</label>
        <form:input path="name" class='input-text'/>
        <label><form:errors path="name"></form:errors></label>
      </div>
      <div class="col-sm-12">
        <label>Тип обстеження</label>
        <form:select path="idRevisionType" class="input-text">
          <c:forEach items="${revisionTypes}" var="rTypes">
            <c:if test="${organ.idRevisionType == rTypes.id}">
              <option selected value="${rTypes.id}" >${rTypes.revisionName}</option>
            </c:if>
            <c:if test="${organ.idRevisionType != rTypes.id}">
              <form:option value="${rTypes.id}">${rTypes.revisionName}</form:option>
            </c:if>
          </c:forEach>
        </form:select>
        <input type="hidden" name="id" value="${organ.id}">
      </div>
      <div class="col-sm-12">
        <input type="submit" value="Зберегти" class="button col-sm-3"/>
        <a href="javascript:deleteOrgan('${organ.id}', '${organ.name}')" class="button col-sm-3">Видалити</a>
      </div>
    </form:form>
  </div>
</div>
</body>
</html>