<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<thead align="center">
		<tr>
			<th>Номер рейса</th>
			<th>Дата</th>
			<th>Количество мест</th>
			<th>Цена</th>
			<th>Действие</th>
		</tr>
	</thead>
	<tbody align="center">
		<c:forEach items="${flights}" var="flight">
			<tr>
				<td><c:out value="${flight.id}" /></td>
				<td><c:out value="${flight.date}" /></td>
				<td><c:out value="${flight.seats}" /></td>
				<td><c:out value="${flight.cost}" /></td>
				<td>
					<form method="post" action="controller">
						<input type="hidden" name="command" value="buyticket" /> <input
							type="hidden" name="flight_id" value="${flight.id}"> <input
							type="submit" value="Купить" />
					</form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<table border="1">
	<thead align="center">
		<tr>
			<th>Номер билета</th>
			<th>Номер рейса</th>
			<th>Багаж (+35)</th>
			<th>Приоритет регистрации (+10)</th>
			<th>Приоритет посадки (+12)</th>
			<th>Цена</th>
			<th>Статус</th>
			<th>Действие</th>
		</tr>
	</thead>
	<tbody align="center">
			<c:forEach items="${tickets}" var="ticket">
				<tr>		
				<form method="post" action="controller">
					<td><c:out value="${ticket.id}" /></td>
					<td><c:out value="${ticket.fligthId}" /></td>
					<td>
						<div>
							<select name="baggage">
								<option value="0">Без багажа</option>
								<option value="1">С багажом</option>
							</select>
						</div>
					</td>
					<td>
						<div>
							<select name="priorityregistration">
								<option value="0">Нет</option>
								<option value="1">Да</option>
							</select>
						</div>
					</td>
					<td>
						<div>
							<select name="priorityboarding">
								<option value="0">Нет</option>
								<option value="1">Да</option>
							</select>
						</div>
					</td>
					<td><c:out value="${ticket.cost}" /></td>
					<td><c:out value="${ticket.paid}" /></td>
					<td><input type="hidden" name="command" value="recalculate" />
						<input type="hidden" name="ticket_id" value="${ticket.id}">
						<input type="hidden" name="baggage" value="1"><input
						type="submit" value="Пересчитать" />
		</form>
		<form method="post" action="controller">
			<input type="hidden" name="command" value="payticket" /> <input
				type="hidden" name="ticket_id" value="${ticket.id}"> <input
				type="submit" value="Оплатить" />
		</form>
		</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<br>
<a href="controller?command=logout">Logout</a>

</body>
</html>