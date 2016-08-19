<%--
  Created by IntelliJ IDEA.
  User: Yurii
  Date: 07.03.2016
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Друк обстеження ${pacientPIB} ${date}</title>
</head>
<body>
<table>
  <tr>
    <td><b>МОЗ України</b></td>
    <td><b>Протокол ендоскопічного дослідження №${id}</b></td>
  </tr>
  <tr>
    <td></td>
    <td><b>Вид обстеження</b>: ${revisionType}</td>
  </tr>
  <tr>
    <td><b>Дата</b>: ${date}</td>
    <td><b>Апарат</b>: ${apparatus}</td>
  </tr>
  <tr>
    <td><b>ПІБ пацієнта</b>: ${pacientPIB}</td>
    <td><b>Дизінфекційний розчин</b>: ${solution}</td>
  </tr>
  <tr>
    <td colspan="2"><b>Дата народження</b>: ${burnDate}</td>
  </tr>
  <tr>
    <td><b>Адреса</b>: ${address}</td>
  </tr>
  <tr>
    <td><b>Опис обстеження</b></td>
  </tr>
  <tr>
    <td colspan="2">${description}<br>
      <b>Вперше виявлена паталогія</b>: ${firstPatalogy}
    </td>
  </tr>
  <tr>
    <td colspan="2"><b>Маніпуляції</b>:<br>${manipulations}</td>
  </tr>
  <tr>
    <td colspan="2"><b>Операції</b><br>${operations}</td>
  </tr>
  <tr>
    <td colspan="2"><b>Заключення</b><br>${conclusions}</td>
  </tr>
  <tr>
    <td colspan="2"><b>Рекомендації</b><br>${recomendations}</td>
  </tr>
  <tr>
    <td></td>
    <td><b>Лікар</b> ${doctor}</td>
  </tr>
</table>
</body>
</html>
