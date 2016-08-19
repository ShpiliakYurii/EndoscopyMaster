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
  <title>${revisionType.revisionName}</title>
</head>
<body>
<div class="container">
  <t:head/>
  <t:menu/>
  <div class="row content">
    <div class="col-sm-12 top-border">
      Редагування типу обстеження "${revisionType.revisionName}".
      <br>
      <a href="/addNewRevisionType" class="button col-sm-3">Назад</a>
    </div>
    <form:form method="post" commandName="revisionType" action="${url}execEditRevisionType">
      <div class="col-sm-3">
        <label>Назва типу обстеження</label>
      </div>
      <div class="col-sm-9">
        <form:input path="revisionName" value="${revisionType.revisionName}" class='input-text'/>
        <label><form:errors path="revisionName"></form:errors></label>
      </div>
      <div class="col-sm-3">
        <label>Абревіатура</label>
      </div>
      <div class="col-sm-9">
        <form:input path="abr" value="${revisionType.abr}" class='input-text'/>
        <label><form:errors path="abr"></form:errors></label>
      </div>
      <div class="col-sm-12">
        <input type="submit" value="Зберегти" class="button col-sm-3"/>
        <a href="javascript:deleteRevisionType('${revisionType.id}', '${revisionType.revisionName}')" class="button col-sm-3">Видалити</a>
      </div>
    </form:form>
  </div>
</div>
</body>
</html>
