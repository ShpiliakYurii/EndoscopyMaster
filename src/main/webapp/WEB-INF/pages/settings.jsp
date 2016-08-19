<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="url" value="http://localhost:8080/"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script src="<c:url value="/resources/s/description.js"/>"></script>
    <link href="<c:url value="/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
    <link href="<c:url value="/resources/c/css.css"/>" rel="stylesheet" media="screen">
    <link href="<c:url value="/resources/c/select.css"/>" rel="stylesheet" media="screen">
    <script src="<c:url value="/resources/s/select.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/s/request.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/s/jquery-1.11.3.min.js"/>"></script>
    <title>Налаштування профілю</title>
</head>
<body>
<div class="container">
    <t:head/>
    <t:menu/>
    <div class="row content">
        <div class="col-sm-12">
            <H1>Налаштування</H1>
            <form:form method="post" action="${url}settings">
                <div class="col-sm-12">
                    <div class="col-sm-5"><label>Пароль</label></div>
                    <div class="col-sm-7">
                        Старий пароль<input type="password" name="oldPass" class="input-text"><br>
                        Новий пароль<input type="password" name="newPass" class="input-text">
                    </div>
                </div>
                <div class="col-sm-12">
                    <div class="col-sm-5"><label>ПІБ</label></div>
                    <div class="col-sm-7">
                        <input type="text" name="pib" class="input-text" value="${pib}">
                    </div>
                </div>
                <div class="col-sm-12">
                    <div class="col-sm-5"><label>Місце роботи</label></div>
                    <div class="col-sm-7">
                        <input type="text" name="workPlace" maxlength="250" class="input-text" value="${workPlace}">
                    </div>
                </div>
                <div class="col-sm-12">
                    <div class="col-sm-5"><label>Апарат за замовчуванням</label></div>
                    <div class="col-sm-7">
                        <select name="apparatusId" class="input-text">
                            <c:forEach items="${apparatuses}" var="a">
                                <c:if test="${a.id == apparatusId}">
                                    <option value="${a.id}" selected>${a.name}</option>
                                </c:if>
                                <c:if test="${a.id != apparatusId}">
                                    <option value="${a.id}">${a.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div class="col-sm-5"><label>Дизінфекційний розчин за мовчуванням</label></div>
                    <div class="col-sm-7">
                        <select name="disinfectantSolutionId" class="input-text">
                            <c:forEach items="${solutions}" var="s">
                                <c:if test="${s.id == disinfectantSolutionId}">
                                    <option value="${s.id}" selected>${s.name}</option>
                                </c:if>
                                <c:if test="${s.id != disinfectantSolutionId}">
                                    <option value="${s.id}">${s.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="col-sm-12">
                    <input type="hidden" name="uId" value="${uId}">
                    <input type="submit" value="Зберегти зміни" class="button">
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
