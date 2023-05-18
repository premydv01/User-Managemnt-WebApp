<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body >

<jsp:include page="header.jsp"/>

<font color="red">${errMsg}</font>
<h2>Login Here</h2>
<form:form action="signIn"  method="POST" >
   <table border="1" >
		<tr>
			<td>Email :</td>
			<td><input type="text" name="email" ></td>
		</tr>
		<tr>
			<td>Password :</td>
			<td><input type="password" name="pwd" ></td>
		</tr>
		<tr>
			<td><input type="submit" value="Sign In"></td>
		</tr>
		<tr>
			<td><a href="register">Sign Up</a></td>
			<td><a href="forgetPwd">Forgot Pwd?</a></td>
		</tr>

   </table>

</form:form>
 <jsp:include page="footer.jsp"/>
</body>
</html>