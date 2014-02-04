<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<html>
<head>
	<title>Ajax Tests </title>
	<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.js"></script>
	
</head>
<body>

<a href="" id="getPublicUserDetails"> Get Public User Details </a>
<div id="getPublicUserDetailsResults">
</div>

<a href="" id="getPrivateUserDetails"> Get Private User Details </a>
<div id="getPrivateUserDetailsResults">
</div>

<form id="loginForm">
	<table>
		<tr>
			<td>User:</td>
			<td>
					<input type='text' id='j_username' value=''>
			</td>
		</tr>
		<tr>
			<td>Password:</td>
			<td>
					<input type='password' id='j_password' />
			</td>
					
		</tr>
		<tr>
			<td colspan='2'>
				<button id="loginBtn">Log In</button>
			</td>
		</tr>
	</table>
</form>
<div id="loginResults">
</div>
<br />
<a href="<c:url value="/j_spring_security_logout" />" > Logout</a>


<script type="text/javascript">
	$(document).ready(function(){
		
		$("#loginBtn").on("click", function(e){
			e.preventDefault();
			var formData = {};
			formData['j_username'] = $("#j_username").val();
			formData['j_password'] = $("#j_password").val();
		
			$.ajax({        url: 'json/login'
				   ,       data: formData
				   ,   dataType:"json"
				   ,     method: 'POST'
				})
				.success(function(data){
					if(data.loggedIn && data.loggedIn === true){
						console.log("LOGIN SUCCESSFUL for user " + data.username);
						$("#loginResults").empty().append("<span> LOGIN SUCCESSFUL for user " + data.username + "</span>");
					}else{
						console.log("LOGIN FAILED - invalid usr / psw" );
						$("#loginResults").empty().append("<span> LOGIN FAILED - invalid usr / psw</span>");
					}
				})
				.fail(function(){
					console.log('LOGIN FAILED');
					$("#loginResults").empty().append("<span> LOGIN FAILED </span>");
				});
			
		});
		
		$("#getPublicUserDetails").on("click", function(e){
			e.preventDefault();
			
			$.ajax({        url: 'json/publicUserDetails'
				 //  ,contentType: 'application/json; charset=UTF-8'
				})
				.success(function(data){
					console.log("success");
					$("#getPublicUserDetailsResults").append("<span> UserName: </span>")
					                                .append("<span> "+ data.userName +" </span>").append("<br />")
					                                .append("<span> UserEmail: </span>")
					                                .append("<span> "+ data.userEmail +" </span>").append("<br />")
					                                .append("<span> UserAge: </span>")
					                                .append("<span> "+ data.userAge +" </span>").append("<br />")
					                                .append("<hr />");
				}).fail(function(){
					console.log("Fail");
				});
		});
		
		$("#getPrivateUserDetails").on("click", function(e){
			e.preventDefault();
			
			$.ajax({        url: 'json/privateUserDetails'
				   ,contentType: 'application/json; charset=UTF-8'
				})
				.success(function(data){
					if(typeof data === "string"){
						data = JSON.parse(data);
					}
					if(data.status === 'NOT AUTHORIZED'){
						console.log("Invalid access");
						$("#getPrivateUserDetailsResults").append("<span> INVALID ACCESS MUST BE LOGGED IN TO VIEW THIS CONTENT </span>")
						                                 .append("<hr />");
					}else{
						console.log("success");
						$("#getPrivateUserDetailsResults").append("<span> UserName: </span>")
						                                .append("<span> "+ data.userName +" </span>").append("<br />")
						                                .append("<span> UserEmail: </span>")
						                                .append("<span> "+ data.userEmail +" </span>").append("<br />")
						                                .append("<span> UserAge: </span>")
						                                .append("<span> "+ data.userAge +" </span>").append("<br />")
						                                .append("<hr />");
					}
				}).fail(function(){
					console.log("Fail");
				});
		});
	});
</script>
</body>
</html>