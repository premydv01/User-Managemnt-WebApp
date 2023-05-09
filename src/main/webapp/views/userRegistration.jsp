<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  

<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js">
</script>
<script type="text/javascript"  src="./js/app.js">
<script>
     $(document).ready(function(e){
    	 $("#userEmail").blur(function(event){
    		$("#dupEmail").html("");
    		var emailId =$("#userEmail").val();
    		$.ajax({
    			url : 'validateEmail?email=' + emailId,
    			success : function(response){
    				if(response=='Duplicate'){
    					$("#dupEmail").html("Email alredy registered");
    					$("#userEmail").focus();
    				}
    			}		
    		});
    	 });
     });
</script>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <h1>Registration Form</h1>
 <form:form action="register" modelAttribute="user" method="POST">
  <table>
       <tr>
       	   <td>First Name :</td>
       	   <td><form:input path="firstName"/>
       </tr>
        <tr>
       	   <td>Last Name :</td>
       	   <td><form:input path="lastName"/>
       </tr>
       
        <tr>
       	   <td>Email :</td>
       	   <td><form:input path="userEmail"/>
       	    <font color="red">
 		        <div id="dupEmail"></div>
 		    </font>
 		 </td> 	
       </tr>
       
        <tr>
       	   <td>Mobile :</td>
       	   <td><form:input path="moblieNo"/>
       </tr>
       <tr>
          <td>Date-Of-Birth :</td>
          <td><form:input path="dob"/></td>
       </tr>
         
         <tr>
          <td>Gender :</td>
          <td><form:radiobutton path="gender" value="M"/>Male
              <form:radiobutton path="gender" value="F"/>Female
          </td>
         </tr>
           <tr>
        <td>Country :</td>
          <td><form:select path="countryId">
              <form:option value="">-select-</form:option>
              <form:options items="${countryMap}" />
              </form:select>
          </td>
         </tr>
         <tr>
          <td>State :</td>
          <td><form:select path="stateId">
              <form:option value="">-select-</form:option>
          
              </form:select>
          </td>
         </tr>
          <tr>
          <td>City :</td>
          <td><form:select path="cityId">
              <form:option value="">-select-</form:option>
            
              </form:select>
          </td>
         </tr>
           
         <tr>
         <td><input type="reset" value="Reset"></td>
         <td><input type="submit" value="Register">
         </tr>
  
     </table>
  
  </form:form>
 
</body>
</html>