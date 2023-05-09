	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js">
</script>
<script>
     $(document).ready(function(e){
    	 $("#contactEmail").blur(function(event){
    		$("#dupEmail").html("");
    		var emailId =$("#contactEmail").val();
    		$.ajax({
    			url : 'validateEmail?email=' + emailId,
    			success : function(response){
    				if(response=='Duplicate'){
    					$("#dupEmail").html("Email alredy registered");
    					$("#contactEmail").focus();
    				}
    			}		
    		});
    	 });
     });
</script>
</head>
<body>
<p>
<font color="green">${succMsg}</font>
</p>
<p>
  <font color="red">${errMsg}</font>
</p>
      <h2>Save Contact</h2>
 <form:form action="saveContact" modelAttribute="contact" method="POST">

 <table border="5" style="color:maroon;" >
 	 <tr>
 		 <form:hidden path="contactId"/>
 		<td>Contact Name :</td>
 		<td><form:input path="contactName"/> </td> 	<!-- path should be match with property of model class Contact -->
 	</tr>
 	
 		<tr>
 		<td>Contact Email : </td>
 		<td><form:input path="contactEmail"/>
 		    <font color="red">
 		        <div id="dupEmail"></div>
 		    </font>
 		 </td> 	
 	</tr>
 	
 		<tr>
 		<td>Contact Number : </td>
 		<td><form:input path="contactNumber"/> </td> 	
 	</tr>
 	
 		<tr>
 		<td><input type="reset" value="Reset"> </td>
 		<td><input type="submit" value="Save"> </td> 	
 	</tr>
 </table>
 </form:form>
<a href="viewContacts" >View All Contacts</a>
</body>
</html>