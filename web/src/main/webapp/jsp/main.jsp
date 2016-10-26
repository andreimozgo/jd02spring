<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Welcome</title>
</head>
<body>
	<h3>Система бронирования билетов LOWCOST</h3>
	<hr />
	${user}, hello!
	<hr />
<h3>Расписание рейсов</h3>
</head>


 <table border="1">
 <thead align = "center">
   <tr>
    <th>Номер рейса</th>
    <th>Дата</th>
    <th>Количество мест</th>
    <th colspan="2">Action</th>
   </tr>
  </thead>
  <tbody align = "center">
   <c:forEach items="${flights}" var="flight">
    <tr>
     <td><c:out value="${flight.id}" /></td>
     <td><c:out value="${flight.date}" /></td>
     <td><c:out value="${flight.seats}" /></td>
     <td><form method="post" action="controller">
       <input type="hidden" name="command" value="deleteflight" /> <input
        type="hidden" name="flight_id" value="${flight.id}">
       <input type="submit" value="Удалить" />
      </form></td>
    </tr>
   </c:forEach>
   <td><body>
Role: ${role}</td>
  </tbody>
  <form action="controller" method="post">

   <div>
    <label for="flightDate">Дата:</label><br> <input
     type="date" name="flightDate" value="" required />
   </div>

   <div>
    <label for="seats">Число мест:</label><br> <input
     type="text" name="seats" value="" required />
   </div>
   <div>
    <label for="cost">Цена:</label><br> <input
     type="text" name="cost" value="" required />
   </div>
    <div>
    <label for="upCost">Признак повышения цены</label> </br><select name="upCost">
     <option value="0">0 - не повышается</option>
     <option value="1">1 - повышается</option>
    </select>
   </div>
   <div>
    <input type="hidden" name="command" value="addflight" /> <input
     type="submit" value="add flight" />
   </div>

 </form>
  
 </table>
    <br>
	<a href="controller?command=logout">Logout</a>

</body>
</html>