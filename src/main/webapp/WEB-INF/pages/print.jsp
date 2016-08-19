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
        String l0, l2;
        if(mode == 0){
            l0 = "Звіт за " + month + " " + year + "p.";
            l2 = "Число місяця";
        }else{
            l0 = "Звіт за " + year + "p.";
            l2 = "Місяць року";
        }
    %>
    <link href="<c:url value="/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
    <title><%=l0%></title>
</head>
<body>
<div class="container">
    <table style="width: 100%">
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
</div>
</body>
</html>
<style>
    .basic-table, tr, td, th{
        border: 1px solid black;
        border-collapse: collapse;
    }

    .basic-table{
        text-align: center;
    }
    p.vertical{
        transform: rotate(-180deg);
        writing-mode: vertical-rl;
        height: 300px;
        width:40px;
    }
    td{
        width: 20px;
    }

    .container {
        width: 1170px;
        padding-right: 15px;
        padding-left: 15px;
        margin-right: auto;
        margin-left: auto;
    }

    table {
        border-collapse: collapse;
        white-space: normal;
        line-height: normal;
        font-weight: normal;
        font-size: medium;
        font-variant: normal;
        font-style: normal;
        color: -webkit-text;
        text-align: start;
        box-sizing: border-box
    }
    </style>