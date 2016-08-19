<%@ page import="java.util.List" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="url" value="http://localhost:8080/"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="<c:url value="/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
    <link href="<c:url value="/resources/c/css.css"/>" rel="stylesheet" media="screen">
    <link href="<c:url value="/resources/c/select.css"/>" rel="stylesheet" media="screen">
    <script src="<c:url value="/resources/s/select.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/s/request.js"/>"></script>
    <title>Робота з виконаними маніпуляціями</title>
</head>
<body>
<div class="container">
    <t:head/>
    <t:menu/>
    <div class="row content">
        <H3>Робота з маніпуляціями</H3>
        <c:if test="${modeText == 1}">
            <a href="${url}takeManipulations/${mode}" class="button">Маніпуляції без результатів</a>
        </c:if>
        <c:if test="${modeText == 0}">
            <a href="${url}takeManipulations/${mode}" class="button">Всі маніпуляції</a>
        </c:if>
        <c:if test="${empty(list)}">
            Немає виконаних маніпуляцій.
        </c:if>
        <%
            List list = (List) request.getAttribute("list");
            for (Object listelement : list) {
                Object[] objarr = (Object[]) listelement;
        %>
        <div class="col-sm-12 top-border">
            <form:form method="post" action="${url}updateManipulations">
                <div class="col-sm-2">
                    Номер маніпуляції:
                </div>
                <div class="col-sm-10">
                    <%=objarr[0]%>
                </div>
                <div class="col-sm-2">
                    Номер обстеження:
                </div>
                <div class="col-sm-10">
                    <a href="${url}detail/<%=objarr[3]%>"><%=objarr[3]%>
                    </a>
                </div>
                <div class="col-sm-2">
                    Назва маніпуляції:
                </div>
                <div class="col-sm-10">
                    <%=objarr[2]%>
                </div>
                <div class="col-sm-2">
                    Опис:
                </div>
                <div class="col-sm-10">
                    <textarea name="place" class="input-text" rows="3" style="width: 100%"><%=objarr[4]%>
                    </textarea>
                </div>
                <div class="col-sm-2">
                    Результат:
                </div>
                <div class="col-sm-10">
                    <input type="hidden" name="id" value="<%=objarr[0]%>">
                    <textarea name="value" class="input-text" rows="3" style="width: 100%"><%if(objarr[1] != null){%><%=objarr[1]%>
                        <%
                            }
                        %>
                        </textarea>
                    <input type="submit" value="Зберегти" class="button col-sm-3"/>
                    <a href="${url}deleteManipulations/<%=objarr[0]%>" class="button col-sm-3">Видалити</a>
                </div>
            </form:form>

        </div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
