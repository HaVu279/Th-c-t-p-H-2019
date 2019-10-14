<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>Book Management</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/list-style.css">
</head>

<body>

	<%@ include file="/header/header.jsp"%>

	<div id="container">

		<div id="content">
			<form action="BookServletController" method="GET">
				<!-- put new button: Add BookStore -->

				<a href="BookServletController?command=NEW">Add new Book</a> </br>
				</br>
				<table>

					<tr>
						<th>Book Name</th>
						<th>Book Price</th>
						<th>Book Category</th>
						<th>Action</th>
					</tr>

					<c:forEach var="tempBook" items="${BOOK_LIST}">

						<!-- set up a link for each student -->
						<c:url var="tempLink" value="BookServletController">
							<c:param name="command" value="LOAD" />
							<c:param name="bookId" value="${tempBook.id}" />
						</c:url>

						<c:url var="deleteLink" value="BookServletController">
							<c:param name="command" value="DELETE" />
							<c:param name="bookId" value="${tempBook.id}" />
						</c:url>

						<tr>
							<td>${tempBook.name}</td>
							<td>${tempBook.price}</td>
							<td>${tempBook.category.name}</td>
							<td><a href="${tempLink}">Update</a> | <a
								href="${deleteLink}"
								onclick="if (!(confirm('Are you sure you want to delete this book?'))) return false">
									Delete</a></td>
						</tr>

					</c:forEach>

				</table>
				<%
					int order_page_count = (Integer) request.getAttribute("order_page_count");
					for (int i = 1; i <= order_page_count; i++) {
						%>
				<c:url var="page" value="BookServletController">
					<c:param name="name" value="${search_name}" />
				</c:url>
				<%
						if (i < order_page_count) {
							%>
				<a href="${page}&page=<%=i%>"> <%=i%>,
				</a>
				<%
						} else {
							%>
				<a href="${page}&page=<%=i%>"> <%=i%></a>
				<%
						}
					}
				%>
				</br>
				</br>
				</br>
				<p style="color: red">Search Book By Name</p>
				<input type="text" name="name" value="${search_name}" placeholder="Book name">
				<input type="submit" value="Search" />
			</form>
		</div>

	</div>
</body>


</html>


