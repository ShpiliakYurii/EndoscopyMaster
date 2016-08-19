<%--
  Created by IntelliJ IDEA.
  User: Yurii
  Date: 13.02.2016
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="url" value="http://localhost:8080/"/>

<html>
<head>
    <link href="<c:url value="/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
    <link href="<c:url value="/resources/c/css.css"/>" rel="stylesheet" media="screen">
    <link href="<c:url value="/resources/c/select.css"/>" rel="stylesheet" media="screen">
    <script src="<c:url value="/resources/s/select.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/s/jquery-1.11.3.min.js"/>"></script>
    <title>Детальний опис обстеження</title>
</head>
<body>
<div class="container">
    <t:head/>
    <t:menu/>
    <div class="row content">
        <%
            List list = (List) request.getAttribute("revisions");
            List m = (List) request.getAttribute("manipulations");
            List c = (List) request.getAttribute("conclusions");
            List o = (List) request.getAttribute("operations");
            Object[] objectarray = (Object[]) list.get(0);
            int id = (Integer) objectarray[0];
            java.sql.Date date = (java.sql.Date) objectarray[1];
            String description = (String) objectarray[2];
            String revisionName = (String) objectarray[3];
            String pacientPib = (String) objectarray[4];
            String manipulations = (String) objectarray[5];
            String address = (String) objectarray[6];
            Date dateBurn = (Date) objectarray[7];
            int age = new Date().getYear() - dateBurn.getYear();
            String recomendations = request.getAttribute("recomendations").toString();
        %>
        <h3>Обстеження №<%=id%>
        </h3>
        <form:form method="post" action="${url}updateRevision">
            <input type="hidden" name="id" value="<%=id%>">

            <div class="col-sm-12">
                <label>Дата: </label> <%=date%>
            </div>
            <div class="col-sm-12">
                <label>Пацієнт: </label> <%=pacientPib%>
            </div>
            <div class="col-sm-12">
                <label>Вік: </label> <%=age%>
            </div>
            <div class="col-sm-12">
                <label>Тип обстеження: </label> <%=revisionName%>
            </div>
            <div class="col-sm-12">
                <label>Апарат: </label> ${apparatus}
            </div>
            <div class="col-sm-12">
                <label>Дизінфекційний розчин: </label> ${solution}
            </div>
            <div class="col-sm-12">
                <label>Опис обстеження</label>
            </div>
            <div class="col-sm-12">
            <textarea name="description" class="input-text" style="width: 100%" rows="10"><%=description%>
            </textarea>
                <label>Вперше виявлена паталогія:</label> ${firstPatalogy}<br>
                <label>Рекомендації: </label><%=recomendations%>
            </div>
            <input type="submit" value="Зберегти зміни" class="button">
        </form:form>
        <c:if test="${!empty(conclusions)}">
            <div class="col-sm-12"><label>Заключення</label></div>
            <div class="col-sm-12 top-border">
                <div class="col-sm-9">Назва</div>
                <div class="col-sm-3">
                </div>
            </div>
            <%
                for (int i = 0; i < c.size(); i++) {
                    Object[] objarr = (Object[]) c.get(i);
                    Integer cId = (Integer) objarr[1];
                    String name = objarr[0].toString();
            %>
            <div class="col-sm-12 top-border">
                <div class="col-sm-9"><%=name%>
                </div>
                <div class="col-sm-3">
                    <a href="${url}deleteConclusions/<%=cId%>/<%=id%>" class="button">Видалити</a>
                </div>
            </div>
            <%
                }
            %>

        </c:if>
        <c:if test="${!empty(manipulations)}">
            <div class="col-sm-12"><label>Маніпуляції</label></div>
            <div class="col-sm-12 top-border">
                <div class="col-sm-3">Назва</div>
                <div class="col-sm-3">Опис</div>
                <div class="col-sm-3">Значення</div>
            </div>
            <%
                for (int i = 0; i < m.size(); i++) {
                    Object[] objarr = (Object[]) m.get(i);
                    String name = objarr[0].toString();
                    String mDescription = objarr[1].toString();
                    String value = "";
                    if (objarr[2] != null)
                        value = objarr[2].toString();
                    Integer idM = (Integer) objarr[3];
            %>
            <form:form method="post" action="${url}execEditManipulations">
                <div class="col-sm-12 top-border">
                    <input type="hidden" name="id" value="<%=idM%>">
                    <input type="hidden" name="idRevision" value="<%=id%>">

                    <div class="col-sm-3">
                        <%=name%>
                    </div>
                    <div class="col-sm-3">
                        <textarea class="input-text" name="mDescription" style="width: 100%"><%=mDescription%>
                        </textarea>
                    </div>
                    <div class="col-sm-3">
                        <input type="text" value="<%=value%>" class="input-text" name="value">
                    </div>
                    <div class="col-sm-3">
                        <input type="submit" value="Зберегти" class="button">
                        <a href="${url}deleteManipulations/<%=idM%>/<%=id%>" class="button">Видалити</a>
                    </div>
                </div>
            </form:form>

            <%
                }
            %>
        </c:if>
        <c:if test="${!empty(operations)}">
            <div class="col-sm-12"><label>Операції</label></div>
            <div class="col-sm-12 top-border">
                <div class="col-sm-4">Назва</div>
                <div class="col-sm-4">Опис</div>
            </div>
            <%
                for (int i = 0; i < o.size(); i++) {
                    Object[] objarr = (Object[]) o.get(i);
                    Integer idO = (Integer) objarr[1];
                    String name = objarr[0].toString();
                    String oDescription = "";
                    if (objarr[2] != null)
                        oDescription = objarr[2].toString();
            %>
            <form:form method="post" action="${url}execEditOperations">
                <div class="col-sm-12 top-border">
                    <input type="hidden" name="id" value="<%=idO%>">
                    <input type="hidden" name="idRevision" value="<%=id%>">

                    <div class="col-sm-4">
                        <%=name%>
                    </div>
                    <div class="col-sm-4">
                        <textarea class="input-text" name="oDescription" style="width: 100%" rows="3"><%=oDescription%>
                        </textarea>
                    </div>
                    <div class="col-sm-4">
                        <input type="submit" value="Зберегти" class="button">
                        <a href="${url}deleteOperations/<%=idO%>/<%=id%>" class="button">Видалити</a>
                    </div>
                </div>
            </form:form>

            <%
                }
            %>
        </c:if>
        <div class="col-sm-3">
            <a href="${url}printRevision/<%=id%>" class="button">Версія для друку</a>
        </div>
    </div>
</div>
</body>
</html>