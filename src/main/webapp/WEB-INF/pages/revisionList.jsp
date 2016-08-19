<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="url" value="http://localhost:8080/"/>

<%--
  Created by IntelliJ IDEA.
  User: Yurii
  Date: 10.01.2016
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
    <link href="<c:url value="/resources/c/css.css"/>" rel="stylesheet" media="screen">
    <link href="<c:url value="/resources/c/select.css"/>" rel="stylesheet" media="screen">
    <script src="<c:url value="/resources/s/select.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/s/jquery-1.11.3.min.js"/>"></script>
    <title>Журнал</title>
    <style>
        .r-list-strip:hover {
            background-color: #f7e1b5;
        }
    </style>
    <script>
        function detail(id) {
            window.location.href = "${url}detail/" + id;
        }
        function filter() {
            window.location.href = "/filteredRevisionList/from/" + $("#fromD").val() + "/to/" + $("#toD").val() + "/pacient/" + $("#pacientId").val();
        }

        function onDateChange() {
            document.getElementById("fDate").checked = true;
        }
        function onPacientChange() {
            document.getElementById("fPacient").checked = true;
        }
        function onRTypeChange() {
            document.getElementById("fRType").checked = true;
        }
    </script>
</head>
<body>
<div class="container">
    <t:head/>
    <t:menu/>
    <div class="row content">
        <form:form method="post" action="${url}filteredRevisionList">

            <div class="col-sm-4">
                <c:if test="${fDate == 1}">
                    <input type="checkbox" name="fDate" id="fDate" value="f1" checked>
                </c:if>
                <c:if test="${fDate != 1}">
                    <input type="checkbox" name="fDate" id="fDate" value="f1">
                </c:if>
                <label for="fDate"><span></span>Фільтр по даті</label>
                <br>
                Від <input type="date" name="from" id="fromD" class="input-text" onchange="onDateChange()">
                <br>
                До <input type="date" name="to" id="toD" class="input-text" onchange="onDateChange()">
            </div>
            <div class="col-sm-4">
                <c:if test="${fPacient == 1}">
                    <input type="checkbox" name="fPacient" id="fPacient" value="f2" checked>
                </c:if>
                <c:if test="${fPacient != 1}">
                    <input type="checkbox" name="fPacient" id="fPacient" value="f2">
                </c:if>
                <label for="fPacient"><span></span>Фільтр по пацієнту</label>
                <select class="input-text" name="pacient" id="pacientId" onchange="onPacientChange()">
                    <c:forEach items="${pacients}" var="p">
                        <c:if test="${pacient == p.id}">
                            <option value="${p.id}" selected>${p.pib}</option>
                        </c:if>
                        <c:if test="${pacient != p.id}">
                            <option value="${p.id}">${p.pib}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            <div class="col-sm-4">
                <c:if test="${fRType == 1}">
                    <input type="checkbox" name="fRType" id="fRType" value="f3" checked>
                </c:if>
                <c:if test="${fRType != 1}">
                    <input type="checkbox" name="fRType" id="fRType" value="f3">
                </c:if>
                <label for="fRType"><span></span>Фільтр по виду дослідження</label>
                <select class="input-text" name="revisionType" onchange="onRTypeChange()">
                    <c:forEach items="${rTypes}" var="r">
                        <c:if test="${revisionType == r.id}">
                            <option value="${r.id}" selected>${r.revisionName}</option>
                        </c:if>
                        <c:if test="${revisionType != r.id}">
                            <option value="${r.id}">${r.revisionName}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            <%--<span class="col-sm-12 button" onclick="filter()">Фільтрувати</span>--%>
            <div class="col-sm-12">
                <input type="submit" value="Фільтрувати" class="button">
            </div>
        </form:form>
        <c:if test="${!empty revisions}">
            <table class="basic-table col-sm-12">
                <tr>
                    <td>№</td>
                    <td>Дата</td>
                    <td>Прізвище, ім’я,<br>
                        по батькові пацієнта,<br>
                        його адреса
                        № мед. карточки
                    </td>
                    <td>Вік</td>
                    <td>Назва та адреса<br>
                        медичного закладу<br>
                        (його підрозділу),<br>
                        який направив<br>
                        хворого на<br>
                        дослідження
                    </td>
                    <td>Вид дослідження</td>
                    <td>Протокол дослідження</td>
                </tr>
                <%
                    List list = (List) request.getAttribute("revisions");
                    for (Object listelement : list) {
                        Object[] objectarray = (Object[]) listelement;
                        int id = (Integer) objectarray[0];
                        Timestamp date = (Timestamp) objectarray[1];
                        String description = (String) objectarray[2];
                        String revisionName = (String) objectarray[3];
                        String pacientPib = (String) objectarray[4] + "<br>";
                        String manipulations = (String) objectarray[5];
                        String address = (String) objectarray[6] + "<br>";
                        Date dateBurn = (Date) objectarray[7];
                        int age = new Date().getYear() - dateBurn.getYear();
                        String identifyCode = (String) objectarray[8];
                        String refferal = (String) objectarray[9];
                        Integer firstPatalogy = (Integer) objectarray[10];
                        String fPatalogy;
                        if (firstPatalogy == 1)
                            fPatalogy = "Так";
                        else
                            fPatalogy = "Ні";
                %>
                <tr onclick="detail(<%=id%>)" class="r-list-strip">
                    <td><%=id%>
                    </td>
                    <td><%=date%>
                    </td>
                    <td><%=pacientPib%><%=address%><%=identifyCode%>
                    </td>
                    <td><%=age%>
                    </td>
                    <td><%=refferal%>
                    </td>
                    <td><%=revisionName%>
                    </td>
                    <td style="text-align: left"><p><%=description%>
                    </p>

                        <p>Вперше виявлена паталогія: <%=fPatalogy%>
                        </p>

                        <p><%=manipulations%>
                        </p>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
        </c:if>
    </div>
    <c:if test="${empty revisions}">
        <div class="row avtorisation"><label>Журнал з вибраними фільтрами пустий.</label></div>
    </c:if>
</div>

</body>
</html>

<script>

    if (1 != ${d1}) {
        d = new Date(${d1});
        var day = parseInt(d.toISOString().substr(8, 10));
        day++;
        if (day < 10)
            day = "0" + day;
        $("#fromD").val(d.toISOString().substring(0, 8) + day);
        d = new Date(${d2});
        day = parseInt(d.toISOString().substr(8, 10));
        day++;
        if (day < 10)
            day = "0" + day;
        $("#toD").val(d.toISOString().substring(0, 8) + day);
    } else {
        d = new Date();
        $("#fromD").val(d.toISOString().substring(0, 10));
        $("#toD").val(d.toISOString().substring(0, 10));
    }


</script>
