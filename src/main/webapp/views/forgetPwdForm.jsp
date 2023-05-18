<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Forget Password</h1>
<form:form action="forgetPwd" method="POST">
	<table border="1">
	<tr>   
			<td>Enter Email :</td>
			<td><input type="text" name="email" ></td>
		</tr>
		<tr>
			<td><input type="submit" value="Reset My Password"></td>
		</tr>
	</table>
</form:form>

</body>
</html>