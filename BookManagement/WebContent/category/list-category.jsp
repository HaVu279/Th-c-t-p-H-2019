<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>

<head>
	<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>Category Management</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/list-style.css">
</head>
<body>

	<%@ include file="/header/header.jsp"%>

	<div id="container">

		<div id="content">
			<form action="CategoryServletController" method="GET">
				<!-- put new button: Add BookStore -->

				<a href="CategoryServletController?command=NEW">Add new Category</a>
				</br>
				</br>
				<table>

					<tr>
						<th>Category Name</th>
						<th>Action</th>

					</tr>

					<c:forEach var="tempCategory" items="${CATEGORY_LIST}">

						<!-- set up a link for each student -->
						<c:url var="tempLink" value="CategoryServletController">
							<c:param name="command" value="LOAD" />
							<c:param name="categoryId" value="${tempCategory.id}" />
						</c:url>

						<c:url var="deleteLink" value="CategoryServletController">
							<c:param name="command" value="DELETE" />
							<c:param name="categoryId" value="${tempCategory.id}" />
						</c:url>

						<tr>
							<td>${tempCategory.name}</td>
							<td><a href="${tempLink}">Update</a> | <a
								href="${deleteLink}"
								onclick="if (!(confirm('Are you sure you want to delete this category?'))) return false">
									Delete</a></td>
						</tr>

					</c:forEach>

				</table>
				<%
					int order_page_count = (Integer) request.getAttribute("order_page_count");
					for (int i = 1; i <= order_page_count; i++) {
						%>
				<c:url var="page" value="CategoryServletController">
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
				<div id="search">
					<p style="color: red">Search Category By Name</p>
					<input type="text" name="name" value="${search_name}" placeholder="Category name">
					<input type="submit" value="Search" />
				</div>
			</form>
		</div>

	</div>
</body>


</html>








