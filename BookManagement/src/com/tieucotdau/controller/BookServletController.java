package com.tieucotdau.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tieucotdau.entities.Book;
import com.tieucotdau.entities.Category;
import com.tieucotdau.logics.BookLogic;
import com.tieucotdau.logics.CategoryLogic;
import com.tieucotdau.logics.impl.BookLogicImpl;
import com.tieucotdau.logics.impl.CategoryLogicImpl;
import com.tieucotdau.utils.Constant;

/**
 * Servlet implementation class BookServletController
 */
@WebServlet("/BookServletController")
public class BookServletController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static CategoryLogic categoryLogic = new CategoryLogicImpl();
	public static BookLogic bookLogic = new BookLogicImpl();

	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");

			// if the command is missing, then default to listing Books
			if (theCommand == null) {
				theCommand = "LIST";
			}
			String name = request.getParameter("name");
			// route to the appropriate method
			switch (theCommand) {

			case "NEW":
				newBook(request, response);
				break;
			case "LIST":
				listBooks(request, response, name);
				break;

			case "ADD":
				addBook(request, response);
				break;

			case "LOAD":
				loadBook(request, response);
				break;

			case "UPDATE":
				updateBook(request, response);
				break;

			case "DELETE":
				deleteBook(request, response);
				break;
			default:
				listBooks(request, response);
			}

		} catch (Exception exc) {
			throw new ServletException(exc);
		}

	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read Book id from form data
		String theBookId = request.getParameter("bookId");

		// delete Book from database
		bookLogic.deleteBookById(Long.parseLong(theBookId));

		// send them back to "list Books" page
		listBooks(request, response);
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read Book info from form data
		int id = Integer.parseInt(request.getParameter("bookId"));
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String number = request.getParameter("number");
		String categoryId = request.getParameter("categoryName");
		Category category = categoryLogic.findCategoryById(Long.parseLong(categoryId));
		// create a new Book object
		Book theBook = new Book(id, name, Double.parseDouble(price), Long.parseLong(number), category);

		// perform update on database
		bookLogic.modifyBook(theBook);

		// send them back to the "list Books" page
		listBooks(request, response);

	}

	private void newBook(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ArrayList<Category> category_List = categoryLogic.findAllCategory(null);

		request.setAttribute("category_List", category_List);
		// send to jsp page: update-Book-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.FOLDER_ADD_BOOK);
		dispatcher.forward(request, response);
	}

	private void loadBook(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read Book id from form data
		String theBookId = request.getParameter("bookId");

		// get Book from database (db util)
		Book theBook = bookLogic.findBookById(Long.parseLong(theBookId));

		// place Book in the request attribute
		request.setAttribute("the_Book", theBook);
		ArrayList<Category> category_List = categoryLogic.findAllCategory(null);

		request.setAttribute("category_List", category_List);
		// send to jsp page: update-Book-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.FOLDER_UPDATE_BOOK);
		dispatcher.forward(request, response);
	}

	private void addBook(HttpServletRequest request, HttpServletResponse response) throws Exception {

		long id = bookLogic.generateId();
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String sold_number = request.getParameter("number");
		String categoryId = request.getParameter("categoryName");

		Category category = categoryLogic.findCategoryById(Long.parseLong(categoryId));
		// create a new Book object
		Book theBook = new Book(id, name, Double.parseDouble(price), Long.parseLong(sold_number), category);
		// perform addBook on database
		bookLogic.addNewBook(theBook);

		// send them back to the "list Books" page
		listBooks(request, response);
	}

	private void listBooks(HttpServletRequest request, HttpServletResponse response, String name) throws Exception {

		// get Books from db util
		List<Book> books = bookLogic.findAllBook(name);
		if (name != null) {
			request.setAttribute("search_name", name);
		}

		int count = books.size();
		int recordPerPage = Constant.RECORD_BOOK_PER_PAGE;
		int pageCount = count / recordPerPage;
		if (count % recordPerPage != 0) {
			pageCount += 1;
		}
		String spageid = request.getParameter("page");
		if (spageid == null) {
			spageid = "1";
		}
		Integer pageNum = Integer.parseInt(spageid);
		books = bookLogic.findBook(name, (pageNum - 1) * recordPerPage, recordPerPage);
		request.setAttribute("order_page_count", pageCount);
		// send to JSP page (view)
		// add Books to the request
		request.setAttribute("BOOK_LIST", books);

		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.FOLDER_LIST_BOOK);
		dispatcher.forward(request, response);
	}

	private void listBooks(HttpServletRequest request, HttpServletResponse response) throws Exception {
		listBooks(request, response, null);
	}
}
