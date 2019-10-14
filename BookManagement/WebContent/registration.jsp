<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<title>Registation</title>
	<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="css/register.css">
</head>

<body>
	
	<h1>Book Store</h1>
	<span>
	<%
				String msg = (String) request.getAttribute("error");
				if(msg != null) {
					out.print(msg);
				}
	%>
	</span>
	<form action="UserServletController" method="GET">
		<input type="hidden" name="command" value="ADD" /> 
		<input type="text" name="name" placeholder="Full Name"><span>*</span><br /> 
		<input type="text" name="email" placeholder="Email Address"><span>*</span><br />
		<input type="text" name="dob" placeholder="Dob"><span>*</span><br />
		<input type="password" name="password" placeholder="Password"><span>*</span><br />
		<input type="password" name="cfpassword" placeholder="Confirm password"><span>*</span><br /> 
		<input type="checkbox" name="tb"><span class="allow">I accept the Team of Use and Privacy Policy</span> 
		<input type="submit" value="Register Now">
	</form>
	<p>
		Have an Account?<a href="index.jsp">Login Now!</a>
	</p>
</body>
</html>