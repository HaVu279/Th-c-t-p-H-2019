<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<link rel="stylesheet" href="css/login.css" type="text/css" />
</head>
<body>

	<br />
	<h1>Book Store</h1>
	<span style="color: red; text-align: center;">
	<%
		String msg = (String) request.getAttribute("msg");
		if(msg != null) {
			out.print(msg);
		}
	%>
	</span>
	<form action="UserServletController" method="post">
		<input type="text" name="email" placeholder="Email" value=""><span>*</span><br />
		<input type="password" name="password" placeholder="Password" value=""><span>*</span><br />
		<input type="submit" value="Login">
	</form>
	<p>
		Don't have an account?<br />
		<a href="UserServletController?command=NEW">Register Now!</a>
	</p>
	
</body>
</html>