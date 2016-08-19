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
    <title>Список користувачів</title>
</head>
<body>
<div class="container">
    <t:head/>
    <div class="row content">
        <h1>Список користувачів</h1>
        <div class="col-sm-12 top-border">
            <div class="col-sm-4">
                ПІБ
            </div>
            <div class="col-sm-4">
                Логін
            </div>
            <div class="col-sm-4">
                Роль
            </div>
        </div>
        <c:forEach items="${users}" var="u">
            <div class="col-sm-12 top-border">
                <div class="col-sm-4">
                    <a href="${url}editUser/${u.id}">${u.pib}</a>
                </div>
                <div class="col-sm-4">
                        ${u.login}
                </div>
                <div class="col-sm-4">
                        ${u.role}
                </div>
            </div>
        </c:forEach>
        <div class="col-sm-12">
            <h1>Добавити нового користувача</h1>
            <form:form method="post" commandName="user" action="${url}addUser">
            <div class="col-sm-12"></div>
            <div class="col-sm-12">
                <div class="col-sm-2">Логін</div>
                <div class="col-sm-6"><form:input path="login" class="input-text"/></div>
                <div class="col-sm-4"><form:errors path="login"></form:errors></div>
            </div>
            <div class="col-sm-12">
                <div class="col-sm-2">Пароль</div>
                <div class="col-sm-6"><form:password path="pass" class="input-text"/></div>
                <div class="col-sm-4"><form:errors path="pass"></form:errors></div>
            </div>
            <div class="col-sm-12">
                <div class="col-sm-2">Роль</div>
                <div class="col-sm-6">
                    <form:select path="role" class="input-text">
                        <form:option value="admin"/>
                        <form:option value="doctor"/>
                    </form:select>
                </div>
                <div class="col-sm-4"><form:errors path="role"></form:errors></div>
            </div>
            <div class="col-sm-12">
                <div class="col-sm-2">ФІО</div>
                <div class="col-sm-6"><form:input path="pib" class="input-text"/></div>
                <div class="col-sm-4"><form:errors path="pib"></form:errors></div>
            </div>
            <div class="col-sm-12">
                <div class="col-sm-2">Місце роботи</div>
                <div class="col-sm-6"><form:input path="workPlace" class="input-text"/></div>
                <div class="col-sm-4"><form:errors path="workPlace"></form:errors></div>
            </div>
            <div class="col-sm-12">
                <div class="col-sm-2">Дефолтний апарат</div>
                <div class="col-sm-6">
                    <select name="apparatus" class="input-text">
                        <c:forEach items="${apparatuses}" var="a">
                            <option value="${a.id}">${a.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-sm-12">
                <div class="col-sm-2">Дефолтний диз. розчин</div>
                <div class="col-sm-6">
                    <select name="solution" class="input-text">
                        <c:forEach items="${solutions}" var="s">
                            <option value="${s.id}">${s.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-sm-12">
                <div class="col-sm-3"><input type="submit" value="Добавити" class="button"/></div>
                <div class="col-sm-3"><input type="reset" value="Очистити" class="button"/></div>
            </div>
        </div>
        </form:form>
    </div>
</div>
</div>
</body>
</html>

