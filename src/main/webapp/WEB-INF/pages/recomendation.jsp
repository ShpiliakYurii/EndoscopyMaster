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
  <title>${recomendation.name}</title>
</head>
<body>
<div class="container">
  <t:head/>
  <t:menu/>
  <div class="row content">
    <div class="col-sm-12 top-border">
      Редагування "${recomendation.name}".
      <a href="${url}addNewRecomendation" class="button col-sm-3">Назад</a>
      <form:form method="post" commandName="recomendation" action="${url}execEditRecomendation">
        <input type="hidden" value="${recomendation.id}" name="id">
        <div class="col-sm-12">
          <label>Рекомендація</label>
          <form:input path="name" class='input-text'/>
          <label><form:errors path="name"></form:errors></label>
        </div>
        <div class="col-sm-12">
          <input type="submit" value="Зберегти" class="button col-sm-3"/>
          <a href="javascript:deleteRecomendation('${recomendation.id}', '${recomendation.name}')" class="button col-sm-3">Видалити</a>
        </div>
      </form:form>
    </div>
    <div class="col-sm-12 top-border">
      <label>Для видалення зв'язку клікніть по заключенню:</label>
    </div>
    <c:forEach items="${recomendations}" var="r">
        <c:forEach items="${conclusions}" var="c">
          <c:if test="${r.conclusionDictionaryId == c.id}">
            <div class="col-sm-12 top-border">
              <a href="javascript:deleteRecomendations('${r.id}', '${c.name}')">${c.name}</a>
            </div>
          </c:if>
        </c:forEach>
      </c:forEach>
  </div>
</div>
</body>
</html>
