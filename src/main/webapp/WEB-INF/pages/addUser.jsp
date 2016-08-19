<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Yurii
  Date: 18.10.2015
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add user</title>
</head>
<body>
Enter data
<form:form method="post" commandName="user" action="addUser">
  <table>
    <tr>
      <td>Login</td>
      <td><form:input path="login"/></td>
      <td><form:errors path="login"></form:errors> </td>
    </tr>
    <tr>
      <td>Password</td>
      <td><form:password path="pass"/></td>
      <td><form:errors path="pass"></form:errors> </td>
    </tr>
    <tr>
      <td>Role</td>
      <td>
        <form:select path="role">
          <form:option value="admin"/>
          <form:option value="doctor"/>
          <form:option value="registry"/>
        </form:select>
      </td>
      <td><form:errors path="role"></form:errors> </td>
    </tr>
    <tr>
      <td>FIO</td>
      <td><form:input path="pib"/></td>
      <td><form:errors path="pib"></form:errors> </td>
    </tr>
    <tr>
      <td><input type="submit" value="Add"/></td>
      <td><input type="reset" value="Reset"/></td>
    </tr>
  </table>
</form:form>
</body>
</html>
