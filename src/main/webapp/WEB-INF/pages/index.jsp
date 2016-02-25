<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form  method="POST" action="login">
	email
	<input name="email" type="text" />
	password
	<input name="password" type="text" />
	
	<input type="submit" value="enviar"/>
</form>

</body>
</html>