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
  <title>${conclusion.name}</title>
</head>
<body>
<div class="container">
  <t:head/>
  <t:menu/>
  <div class="row content">
    <div class="col-sm-12 top-border">
      Редагування "${conclusion.name}".
      <a href="${url}addNewConlusion" class="button col-sm-3">Назад</a>
      <form:form method="post" commandName="conclusion" action="${url}execEditConclusion">
        <input type="hidden" value="${conclusion.id}" name="id">
        <div class="col-sm-12">
          <label>Заключення</label>
          <form:input path="name" class='input-text'/>
          <label><form:errors path="name"></form:errors></label>
        </div>
        <div class="col-sm-12">
          <input type="submit" value="Зберегти" class="button col-sm-3"/>
          <a href="javascript:deleteConclusion('${conclusion.id}', '${conclusion.name}')" class="button col-sm-3">Видалити</a>
        </div>
      </form:form>
    </div>
  </div>
</div>
</body>
</html>
