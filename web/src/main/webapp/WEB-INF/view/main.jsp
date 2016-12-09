<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h3>Система бронирования билетов LOWCOST</h3>
<hr/>
${user}, hello!
<hr/>
<h3>Расписание рейсов</h3>
</head>
<table>
    <form action="controller" method="post">

        <div>
            <label for="flightDate">Дата:</label><br>
            <input type="date" name="flightDate" value="" required/>
        </div>

        <div>
            <label for="seats">Число мест:</label><br> <input
                type="text" name="seats" value="" required/>
        </div>
        <div>
            <label for="cost">Цена:</label><br> <input
                type="text" name="cost" value="" required/>
        </div>
        <div>
            <label for="upCost">Признак повышения цены</label> </br><select name="upCost">
            <option value="0">0 - не повышается</option>
            <option value="1">1 - повышается</option>
        </select>
        </div>
        <div>
            <input type="hidden" name="command" value="addflight"/>
            <input type="submit" value="Добавить"/>
        </div>
    </form>
</table>
<br>
<form method="post" action="controller">
    <td>Количество строк на страницу:</td>
    <td>
        <div>
            <select name="recordsPerPage">
                <option value="${recordsPerPage}"></option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="5">5</option>
            </select>
        </div>
    </td>
    <input type="hidden" name="command" value="adminpage"/>
    <input type="submit" value="Поиск"/>
</form>
<table border="1">
 <thead align = "center">
 <tr>
     <th>Номер рейса</th>
     <th>Дата</th>
     <th>Количество мест</th>
     <th colspan="2">Action</th>
 </tr>
 </thead>
    <tbody align="center">
    <c:forEach items="${flights}" var="flight">
        <tr>
            <td><c:out value="${flight.id}"/></td>
            <td><c:out value="${flight.date}"/></td>
            <td><c:out value="${flight.seats}"/></td>
            <td>
                <form method="post" action="controller">
                    <input type="hidden" name="command" value="deleteflight"/> <input
                        type="hidden" name="flight_id" value="${flight.id}">
                    <input type="submit" value="Удалить"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    <td>
        Role:
        ${role}</td>
    </tbody>
</table>
<br>
<c:choose>
    <c:when test="${currentPage != 1}">
        <td><a href="controller?command=adminpage&currentPage=${currentPage - 1}&recordsPerPage=${recordsPerPage}">Предыдущая</a>
        </td>
        <td><a href="controller?command=adminpage&currentPage=1&recordsPerPage=${recordsPerPage}">1</a></td>
    </c:when>
    <c:when test="${currentPage == 1}">
        <td>Предыдущая</td>
        <td>1</td>
    </c:when>
    <c:otherwise>
        <td></td>
    </c:otherwise>
</c:choose>
<td>
    <select onchange="location.href=this.value">
        <option>${currentPage}</option>
        <c:forEach begin="2" end="${numberOfPages-0}" var="i">
        <c:choose>
        <c:when test="${currentPage eq i}">
<td>${i}</td>
</c:when>
<c:otherwise>
    <option value="controller?command=adminpage&currentPage=${i}&recordsPerPage=${recordsPerPage}">${i}</option>
</c:otherwise>
</c:choose>
</c:forEach>
</select>
</td>
<c:choose>
    <c:when test="${currentPage lt numberOfPages}">
        <td>
            <a href="controller?command=adminpage&currentPage=${numberOfPages}&recordsPerPage=${recordsPerPage}">${numberOfPages}</a>
        </td>
        <td><a href="controller?command=adminpage&currentPage=${currentPage + 1}&recordsPerPage=${recordsPerPage}">Следующая</a>
        </td>
    </c:when>
    <c:when test="${currentPage == numberOfPages}">
        <td>${numberOfPages}</td>
        <td>Следующая</td>
    </c:when>
    <c:otherwise>
        <td></td>
    </c:otherwise>
</c:choose>
<br>
<a href="controller?command=logout">Logout</a>
</body>
</html>