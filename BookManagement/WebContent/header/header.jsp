
<jsp:useBean id="user" scope="session"
	class="com.tieucotdau.entities.User" />

<%if(user.getName() == null) {
	  String msg="You must login first";
	  request.setAttribute("msg",msg);
	  String redirectURL = "index.jsp";
	  response.sendRedirect(redirectURL);
	  
  }%>
<div id="wrapper">
	<div id="header" class="header overflow">
		<div class="container">
			<div class="pull-left">
				<h1>Book Mangement</h1>
			</div>
			<div class="pull-right">
				<ul class="menu-1">
					<li><a href="#">Management</a>
						<ul class="menu-2">
							<li><a href="CategoryServletController">Category
									Management</a></li>
							<li><a href="AuthorServletController">Author Management</a></li>
							<li><a href="BookServletController">Book Management</a></li>
						</ul></li>
					<li><a href="#">Report</a>
						<ul class="menu-2">
							<li><a
								href="BookStoreServletController?command=TOP_AUTHOR_REVENUE">Top
									Author</a></li>
							<li><a
								href="BookStoreServletController?command=TOP_CATEGORY_REVENUE">Top
									Category</a></li>
							<li><a href="BookStoreServletController">All Report</a></li>

						</ul>
					<li class="login"><span class="pull-left"><%=user.getName() %></span>
						<a href="UserServletController?command=LOGOUT">Log out</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>