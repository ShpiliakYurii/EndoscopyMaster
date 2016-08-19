<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yurii
  Date: 18.10.2015
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User list</title>
</head>
<body>
<h1>User list</h1>
<c:if test = "${!empty users}">
  <table>
    <tr>
      <td>FIO</th>
      <td>Role</td>
      <td>Action</th>
    </tr>
    <c:forEach items = "${users}" var = "user">
      <tr>
        <td>${user.pib}</td>
        <td>${user.role}</td>
        <td><a href="/removeUser/${user.id}">Delete</a><td>
      </tr>
    </c:forEach>
  </table>
</c:if>
<a href="addUser">Add user</a>
</body>
</html>
