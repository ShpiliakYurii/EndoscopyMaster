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
  <title>${region.name}</title>
</head>
<body>
<div class="container">
  <t:head/>
  <t:menu/>
  <div class="row content">
    <div class="col-sm-12 top-border">
      Редагування ділянки "${region.name}".
      <br>
      <a href="${url}addNewRegion" class="button col-sm-3">Назад</a>
    </div>
    <form:form method="post" commandName="region" action="${url}execEditRegion">
      <div class="col-sm-12">
        <label>Назва ділянки</label>
        <form:input path="name" class='input-text'/>
        <label><form:errors path="name"></form:errors></label>
      </div>
      <div class="col-sm-12">
        <label>Орган</label>
        <form:select path="idOrgan" class="input-text">
          <c:forEach items="${organs}" var="o">
            <c:if test="${region.idOrgan == o.idOrgan}">
              <option selected value="${o.idOrgan}" >${o.name}</option>
            </c:if>
            <c:if test="${region.idOrgan != o.idOrgan}">
              <form:option value="${o.idOrgan}">${o.name}</form:option>
            </c:if>
          </c:forEach>
        </form:select>
        <input type="hidden" name="idRegion" value="${region.idRegion}">
      </div>
      <div class="col-sm-12">
        <input type="submit" value="Зберегти" class="button col-sm-3"/>
        <a href="javascript:deleteRegion('${region.idRegion}', '${region.name}')" class="button col-sm-3">Видалити</a>
      </div>
    </form:form>
  </div>
</div>
</body>
</html>