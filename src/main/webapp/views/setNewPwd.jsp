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
<h1>Reset New Password</h1>
<form:form action="setNewPwd" modelAttribute="forgetPwd" method="POST">
	<table>
	
		<tr>
		<form:hidden path="userId"/>
			<td>Email :</td>
			<td><form:input path="userEmail" readonly="true"/></td>
		</tr>
		<tr>
			<td>New Password :</td>
			<td><form:input path="newPwd"/></td>
		</tr>
		<tr>
			<td>Confirm Password :</td>
			<td><form:input path="confirmPwd"/></td>
		</tr>
	<tr>
			
			<td><input type="submit" name="Save"></td>
		</tr>
	</table>
</form:form>

</body>
</html>