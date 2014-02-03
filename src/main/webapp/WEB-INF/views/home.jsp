<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.js"></script>
	
</head>
<body>
<h1>
	Hello world!  
</h1>
<h2>Welcome to ${pageName} </h2>
<P>  The time on the server is ${serverTime}. bla bla </P>

<a href="/security/">Home</a>
<a href="/security/public">Public Site</a>
<a href="/security/user">User Site</a>
<a href="/security/admin">Admin Site</a>

<br />
<a href="<c:url value="/j_spring_security_logout" />" > Logout</a>

<br />
<a id="ajaxCall" href=""> Ajax Call</a>


<script type="text/javascript">
	function makeAjaxCall(){
		
		$.ajax({          url: '/security/admin'
		        ,contentType : 'application/json; charset=UTF-8'
			   })
		.success( function(data){
			console.log("success");
		})
		.fail( function(){
			console.log("fail");
		});
	}
	
	
	$(document).ready(function(){
		$("#ajaxCall").on("click", function(){
			console.log("about to invoke ajax");
			makeAjaxCall();
			console.log("ajax invoked");
		});
	});
</script>
</body>
</html>
