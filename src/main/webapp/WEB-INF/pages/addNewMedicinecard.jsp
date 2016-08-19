<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="url" value="http://localhost:8080/"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <script src="<c:url value="/resources/s/description.js"/>"></script>
    <link href="<c:url value="/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
    <link href="<c:url value="/resources/c/css.css"/>" rel="stylesheet" media="screen">
    <link href="<c:url value="/resources/c/select.css"/>" rel="stylesheet" media="screen">
    <script src="<c:url value="/resources/s/select.js"/>"></script>
    <title>Новий пацієнт</title>
</head>
<body>
<div class="container">
  <t:head/>
  <t:menu/>
  <div class="row content">
      <div class="col-sm-12 top-border">
          <div class="col-sm-3">
              ПІБ
          </div>
          <div class="col-sm-3">
              Ідентифікаційний код
          </div>
          <div class="col-sm-3">
              Дата народження
          </div>
          <div class="col-sm-3">
              Адрес
          </div>
      </div>
      <c:forEach items="${medicinecards}" var="m">
      <div class="col-sm-12 top-border">
          <div class="col-sm-3">
              <a href="${url}editMedicinecard/${m.id}">${m.pib}</a>
          </div>
          <div class="col-sm-3">
                ${m.identifyCode}
          </div>
          <div class="col-sm-3">
              ${m.burnDate}
          </div>
          <div class="col-sm-3">
              ${m.adress}
          </div>
      </div>
      </c:forEach>
      <div class="col-sm-12 top-border">
        <form:form method="post" commandName="medicinecard" action="${url}addNewMedicinecard">
              <div class="col-sm-5">
                <label>Прізвище ім'я по батькові</label>
              </div>
              <div class="col-sm-7">
                <form:input path="pib" class='input-text'/>
                <label><form:errors path="pib"></form:errors></label>
              </div>
              <div class="col-sm-5">
                <label>Ідентифікаційний код</label>
              </div>
              <div class="col-sm-7">
                <form:input path="identifyCode" size="10" class='input-text'/>
                <label><form:errors path="identifyCode"></form:errors></label>
              </div>
              <div class="col-sm-5">
                <label>Адрес постійного місця проживання</label>
              </div>
              <div class="col-sm-7">
                <form:input path="adress" class='input-text'/>
                <label><form:errors path="adress"></form:errors></label>
              </div>
              <div class="col-sm-5">
                <label>Дата народження</label>
              </div>
              <div class="col-sm-7">
                <form:input type="date" path="burnDate" class="input-text"/>
                <label><form:errors path="burnDate"></form:errors></label>
              </div>
                <input type="submit" value="Добавити" class="button col-sm-6"/>
                <input type="reset" value="Очистити" class="button col-sm-6"/>
        </form:form>
      </div>
  </div>
</div>
</body>
</html>
