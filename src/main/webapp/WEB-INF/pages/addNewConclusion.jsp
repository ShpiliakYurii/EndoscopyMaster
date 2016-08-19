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
  <title>Словник заключень</title>
</head>
<body>
<div class="container">
  <t:head/>
  <t:menu/>
  <div class="row content">
    <div class="col-sm-12 top-border">
      <div class="col-sm-6">Заключення</div>
      <div class="col-sm-6">Рекомендація</div>
    </div>
    <c:forEach items="${conclusions}" var="c">
      <div class="col-sm-12 top-border">
        <div class="col-sm-6"><a href="${url}editConclusion/${c.id}">${c.name}</a></div>
        <div class="col-sm-6">
          <c:forEach items="${recomendations}" var="r">
            <c:if test="${c.id == r.conclusionDictionaryId}">
              <c:forEach items="${recomendationsDictionary}" var="rd">
                <c:if test="${rd.id == r.recomendationDictionaryId}">
                  <a href="${url}editRecomendation/${rd.id}">${rd.name}</a><br>
                </c:if>
              </c:forEach>
            </c:if>
          </c:forEach>
        </div>
      </div>
    </c:forEach>
    <div class="col-sm-12 top-border">
      <form:form method="post" commandName="newConclusion" action="${url}addNewConclusion">
        <div class="col-sm-12">
          <label>Заключення</label>
          <form:input path="name" class='input-text'/>
          <label><form:errors path="name"></form:errors></label>
        </div>
        <input type="submit" value="Добавити" class="button col-sm-6"/>
      </form:form>
    </div>
    <div class="col-sm-12 top-border">
      <label>З'єднання рекомендації і заключення.</label>
      <form:form method="post" commandName="newRecomendation" action="${url}bindRecomendationToConclusion">
        <div class="col-sm-12">
          <label>Заключення</label>
          <select name="conclusionDictionaryId" class="input-text">
            <c:forEach items="${conclusions}" var="c">
              <option value="${c.id}">${c.name}</option>
            </c:forEach>
          </select>
        </div>
        <div class="col-sm-12">
          <label>Рекомендація</label>
          <select name="recomendationDictionaryId" class="input-text">
            <c:forEach items="${recomendationsDictionary}" var="r">
              <option value="${r.id}">${r.name}</option>
            </c:forEach>
          </select>
        </div>
        <input type="submit" value="Добавити" class="button col-sm-6"/>
      </form:form>
    </div>
  </div>
</div>
</body>
</html>
