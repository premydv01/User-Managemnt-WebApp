<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js">
</script>
<script>
     function validatePwds(){
    		 var newPwd = $('#newPwd').val();
    		 var confirmPwd= $('#confirmPwd').val();
    		 if(newPwd != confirmPwd){
        		 $('#errId').text('New Pasword & Confirm Password should be same');
                  return false;
    		 }
    		 
    		 return true;
    	 }
   
</script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   <font color="red">${errMsg}</font>
        <h1>Unlock Account Here</h1>
<form:form action="unlockAcc"  modelAttribute="unlockAccount" method="POST">

<font color="red"><span id="errId"></span></font>
<table>
	<tr>
		<td>Email :</td>
		<td><form:input path="email" readonly="true"/></td>
	</tr>
    <tr>
		<td>Temporary  Password :</td>
		<td><form:password path="tempPwd"/>
		
	</tr>
	  <tr>
		<td>New Password :</td>
		<td><form:password path="newPwd"/>
		
	</tr>
	 <tr>
		<td>Confirm New Password :</td>
		<td><form:password path="confirmPwd"/>
	</tr>
	<tr>
		<td>   </td>
		<td><input type="submit" value="Unlock" 
		onclick="javascript:return validatePwds()"/></td>
	</tr>


</table>




</form:form>                

</body>
</html>