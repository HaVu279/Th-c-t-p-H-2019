<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>Update Book</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-style.css">
</head>

<body>
	<%@ include file="/header/header.jsp"%>
	<div id="container">
		<h3>Update Book</h3>

		<form action="BookServletController" method="GET">

			<input type="hidden" name="command" value="UPDATE" /> <input
				type="hidden" name="bookId" value="${the_Book.id}" />

			<table>
				<tbody>
					<tr>
						<td><label>Name:</label></td>
						<td><input type="text" name="name" value="${the_Book.name}" /></td>
					</tr>

					<tr>
						<td><label>Price:</label></td>
						<td><input type="text" name="price" value="${the_Book.price}" /></td>
					</tr>
					
					<tr>
						<td><label>Number:</label></td>
						<td><input type="text" name="number" value="${the_Book.soldNumber}" /></td>
					</tr>

					<tr>
						<td><label>Category:</label></td>
						<td><select name="categoryName"
						style="width: 260px; border: 1px solid #666; border-radius: 5px; padding: 4px; font-size: 16px;">
								<option value="${the_Book.category.id}" selected>${the_Book.category.name}</option>
								<c:forEach var="row" items="${category_List}">
									<c:if test="${row.id != the_Book.category.id}">
										<option value='<c:out value="${row.id}" />'>${row.name}</option>
									</c:if>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>

				</tbody>
			</table>
		</form>

		<div style="clear: both;"></div>

		<p>
			<a href="BookServletController">Back to List</a>
		</p>
	</div>
</body>

</html>











