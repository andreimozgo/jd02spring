<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Регистрация</title>
</head>
<body>
	<h4>Введите желаемый логин и пароль</h4>
		<form method="POST" action="addRegistration">
	<div>
		<label for="login">Логин:</label><br> <input type="text"
			name="login" value="" required />
	</div>

	<div>
		<label for="password">Пароль:</label><br> <input type="text"
			name="password" value="" required />
	</div>
	<div>
		 <input type="submit" value="Зарегистрировать" />
	</div>
	</form>
</body>
</html>