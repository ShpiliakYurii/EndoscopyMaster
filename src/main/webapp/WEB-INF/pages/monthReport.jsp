<%@ page import="java.util.List" %>
<%@ page import="com.springapp.mvc.domain.Revisiontype" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="url" value="http://localhost:8080/"/>
<html>
<head>
    <%
        Integer mode = (Integer)request.getAttribute("mode");
        Integer month = (Integer)request.getAttribute("month");
        Integer year = (Integer)request.getAttribute("year");
        String l0, l1, l2;
        if(mode == 0){
            l0 = "Звіт за " + month + " місяць " + year + "p.";
            l1 = "Місяць";
            l2 = "Число місяця";
        }else{
            l0 = "Звіт за " + year + "p.";
            l1 = "Рік";
            l2 = "Місяць року";
        }
    %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script src="<c:url value="/resources/s/description.js"/>"></script>
    <link href="<c:url value="/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
    <link href="<c:url value="/resources/c/css.css"/>" rel="stylesheet" media="screen">
    <link href="<c:url value="/resources/c/select.css"/>" rel="stylesheet" media="screen">
    <script src="<c:url value="/resources/s/select.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/s/request.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/s/jquery-1.11.3.min.js"/>"></script>
    <title><%=l0%></title>
</head>
<body>
<div class="container">
    <t:head/>
    <t:menu/>
    <div class="row content">
        <div class="col-sm-12">
            <form:form method="post" action="${url}monthReport">
                <label><%=l1%></label>
                <input type="month" name="month" id="date" onclick="logout()">
                <input type="hidden" name="mode" value="${mode}">
                <input type="submit" value="Сформувати" class="button">
            </form:form>
            <c:if test="${!empty(data)}">
                <table>
                    <tr>
                        <td></td>
                        <td colspan="6">Усього</td>
                        <%
                            List<Revisiontype> revisiontypes = (List) request.getAttribute("revisiontypes");
                            for (int i = 0; i < revisiontypes.size(); i++) {
                        %>
                        <td colspan="6"><%=revisiontypes.get(i).getRevisionName()%>
                        </td>
                        <%
                            }
                        %>
                    </tr>
                    <tr>
                        <th><p class="vertical"><%=l2%></p></th>
                        <%
                            for (int i = 0; i < revisiontypes.size() + 1; i++) {
                        %>
                        <th><p class="vertical">Ендоскопічних досліджень</p></th>
                        <th><p class="vertical">Діагностичних досліджень</p></th>
                        <th><p class="vertical">Ендоскопічних маніпуляцій</p></th>
                        <th><p class="vertical">Взято матеріал на цитоморфологічне дослідження</p></th>
                        <th><p class="vertical">Ендоскопічних операцій</th>
                        <th><p class="vertical">Кількість хворих з вперше виявленою онкопаталогією</p></th>
                        <%
                            }
                        %>
                    </tr>
                    <tr>
                        <td><b>1</b></td>
                        <%
                            int k = 1;
                            for (int i = 0; i < revisiontypes.size() + 1; i++) {
                        %>
                        <td><b><%=k + 1%></b>
                        </td>
                        <td><b><%=k + 2%></b>
                        </td>
                        <td><b><%=k + 3%></b>
                        </td>
                        <td><b><%=k + 4%></b>
                        </td>
                        <td><b><%=k + 5%></b>
                        </td>
                        <td><b><%=k + 6%></b>
                        </td>
                        <%
                                k += 6;
                            }
                        %>
                    </tr>
                    <%
                        Long[][] data = (Long[][]) request.getAttribute("data");
                        for (int i = 0; i < data.length; i++) {
                    %>
                    <tr>
                        <td>
                            <%=data[i][0]%>
                        </td>
                        <%
                            for (int j = 1; j < data[i].length; j++) {
                        %>
                        <td>
                            <%=data[i][j]%>
                        </td>
                        <%
                            }
                        %>
                    </tr>
                    <%
                        }
                    %>
                    <tr>
                        <td><b>Всього</b></td>
                        <%
                            int sum;
                            for (int j = 1; j < data[0].length; j++){
                                sum = 0;
                                for (int i = 0; i < data.length; i++) {
                                    sum += data[i][j];
                                }
                        %>
                        <td><b><%=sum%></b></td>
                        <%
                            }
                        %>
                    </tr>
                </table>
                <a href="${url}printMonthReport/${year}/${month}/${mode}" class="button col-sm-3">Друк</a>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
<script>
    var d = new Date();
    $("#date").val(d.toISOString().substring(0, 7));
</script>