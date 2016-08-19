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
  <title>Новий тип обстеження</title>
</head>
<body>
<div class="container">
  <t:head/>
  <t:menu/>
  <div class="row content">
    <div class="col-sm-12">
      <div class="col-sm-6">Тип обстеження</div>
      <div class="col-sm-6">Органи</div>
    </div>
    <c:forEach items="${revisionTypes}" var="rTypes">
      <div class="col-sm-12 top-border">
        <div class="col-sm-6">
          <a href="/editRevisionType/${rTypes.id}">${rTypes.revisionName}</a><br>
        </div>
        <div class="col-sm-6">
          <c:forEach items="${organs}" var="o">
            <c:if test="${rTypes.id == o.idRevisionType}">
              <a href="/editOrgan/${o.id}">${o.name}</a><br>
            </c:if>
          </c:forEach>
        </div>
      </div>
    </c:forEach>
    <div class="col-sm-12 top-border">
      Щоб добавити новий тип обстеження, введіть його назву і натисніть кнопку добавити.
    </div>
    <form:form method="post" commandName="revisionType" action="${url}addNewRevisionType">
      <div class="col-sm-3">
        <label>Назва типу обстеження</label>
      </div>
      <div class="col-sm-9">
        <form:input path="revisionName" class='input-text'/>
        <label><form:errors path="revisionName"></form:errors></label>
      </div>
      <div class="col-sm-3">
        <label>Скорочення(<10 символів)</label>
      </div>
      <div class="col-sm-9">
        <form:input path="abr" class='input-text'/>
        <label><form:errors path="abr"></form:errors></label>
      </div>
      <input type="submit" value="Добавити" class="button col-sm-4"/>
    </form:form>
    <div class="col-sm-12 top-border">
      Виберіть тип обстеження та орган, щоб добавити його до цього типу обстеження.
    </div>
    <div class="col-sm-4">
        <select id="select-types" onchange="changeOrgan()" class="input-text">
          <c:forEach items="${revisionTypes}" var="rTypes">
            <option value="${rTypes.id}">${rTypes.revisionName}</option>
          </c:forEach>
        </select>
    </div>
    <div class="col-sm-3">
        <select id="select-organs" class="input-text">
        </select>
    </div>
    <span class="col-sm-4 button" onclick="bind()">Добавити</span>
  </div>
</div>
</body>
</html>
<script>
  function unique(arr) {
    var result = [];
    nextInput:
            for (var i = 0; i < arr.length; i++) {
              var str = arr[i]; // для каждого элемента
              for (var j = 0; j < result.length; j++) { // ищем, был ли он уже?
                if (result[j] == str) continue nextInput; // если да, то следующий
              }
              result.push(str);
            }

    return result;
  }

  function deleteItem(arr,id){
    var result = [];
    for(var i = 0; i < arr.length; i++){
      if(i != id){
       result.push(arr[i]);
      }else{
        i++;
      }
    }
    return result;
  }

  function unique1(arr, rTypeId){
    for(var i = 0; i < arr.length; i++) {
      <c:forEach items="${organs}" var="o">
      if(rTypeId == ${o.idRevisionType}){
        if(arr[i] == ${o.idOrgan}){
          arr = deleteItem(arr,i);
        }
      }
      </c:forEach>
    }
    return arr;
  }

  function changeOrgan(){
    var rID = $("#select-types").val();
    var organsList = new Array();
    <c:forEach items="${organs}" var="o">
        if(rID != ${o.idRevisionType}){
          organsList.push(${o.idOrgan});
        }
    </c:forEach>
    var uniqueList = unique1(unique(organsList),rID);
    var str = "";
    var organsNames = [];
    for(var i = 0; i < uniqueList.length; i++){
      <c:forEach items="${organs}" var="o">
        if(uniqueList[i] == ${o.idOrgan}) {
          organsNames[i] = "${o.name}";
        }
      </c:forEach>
    }
    for(var i = 0; i < uniqueList.length; i++){
      str += "<option value='"+uniqueList[i]+"'>"+organsNames[i]+"</option>";
    }
    document.getElementById("select-organs").innerHTML = str;
  }

  function bind(){
    window.location.href = "/bindOrganToRType/rType/"+$("#select-types").val()+"/organ/"+$("#select-organs").val();;
  }

  changeOrgan();
  </script>
