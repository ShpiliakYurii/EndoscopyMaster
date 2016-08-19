<%@ page import="java.util.List" %>
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
  <title>Словник маніпуляцій</title>
</head>
<body>
<div class="container">
  <t:head/>
  <t:menu/>
  <div class="row content">
    <div class="col-sm-12 top-border">
      Маніпуляція
    </div>
    <c:forEach items="${manipulations}" var="m">
      <div class="col-sm-12 top-border">
        <a href="${url}editManipulation/${m.id}">${m.name}</a>
      </div>
    </c:forEach>
    <div class="col-sm-12 top-border">
      <form:form method="post" commandName="newManipulation" action="${url}addNewManipulation">
        <div class="col-sm-12">
          <label>Маніпуляція</label>
          <form:input path="name" class='input-text'/>
          <label><form:errors path="name"></form:errors></label>
        </div>
        <input type="submit" value="Добавити" class="button col-sm-6"/>
      </form:form>
    </div>
  </div>
</div>
</body>
</html>
