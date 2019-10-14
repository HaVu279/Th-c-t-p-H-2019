package com.tieucotdau.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tieucotdau.entities.Author;
import com.tieucotdau.logics.AuthorLogic;
import com.tieucotdau.logics.impl.AuthorLogicImpl;
import com.tieucotdau.utils.Constant;

/**
 * Servlet implementation class AuthorServletController
 */
@WebServlet("/AuthorServletController")
public class AuthorServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static AuthorLogic authorLogic = new AuthorLogicImpl();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");

			// if the command is missing, then default to listing Authors
			if (theCommand == null) {
				theCommand = "LIST";
			}
			String name = request.getParameter("name");

			// route to the appropriate method
			switch (theCommand) {

			case "LIST":
				listAuthor(request, response, name);
				break;

			case "NEW":
				newAuthor(request, response);
				break;
			case "ADD":
				addAuthor(request, response);
				break;

			case "LOAD":
				loadAuthor(request, response);
				break;

			case "UPDATE":
				updateAuthor(request, response);
				break;

			case "DELETE":
				deleteAuthor(request, response);
				break;
			default:
				listAuthor(request, response);
			}

		} catch (Exception exc) {
			throw new ServletException(exc);
		}

	}

	private void newAuthor(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, SQLException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.FOLDER_ADD_AUTHOR);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void addAuthor(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, SQLException, ServletException, IOException {
		Long id = authorLogic.generateId();
		String name = request.getParameter("nameAuthor");
		String dobString = request.getParameter("dobString");
		Date dob = sdf.parse(dobString);

		// create a new Author object
		Author theAuthor = new Author(id, name, dob);

		// perform update on database
		authorLogic.addNewAuthor(theAuthor);

		// send them back to the "list Author" page
		listAuthor(request, response);

	}

	private void listAuthor(HttpServletRequest request, HttpServletResponse response, String name)
			throws ParseException, SQLException, ServletException, IOException {
		// get Authors from db util
		List<Author> authorList = authorLogic.findAllAuthor(name);
		if (name != null) {
			request.setAttribute("search_name", name);
		}
		// added
		int count = authorList.size();
		int recordPerPage = Constant.RECORD_AUTHOR_PER_PAGE;
		int pageCount = count / recordPerPage;
		if (count % recordPerPage != 0) {
			pageCount += 1;
		}
		String pageid = request.getParameter("page");
		if (pageid == null) {
			pageid = "1";
		}
		Integer pageNum = Integer.parseInt(pageid);
		authorList = authorLogic.findAuthor(name, (pageNum - 1) * recordPerPage, recordPerPage);

		request.setAttribute("order_page_count", pageCount);
		
		request.setAttribute("AUTHOR_LIST", authorList);

		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.FOLDER_LIST_AUTHOR);
		dispatcher.forward(request, response);
	}

	private void listAuthor(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, SQLException, ServletException, IOException {
		listAuthor(request, response, null);
	}

	private void deleteAuthor(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read Author id from form data
		String authorId = request.getParameter("authorId");
		try {
			authorLogic.deleteAuthorById(Long.parseLong(authorId));
			listAuthor(request, response);
		} catch (Exception e) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/404error.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void updateAuthor(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read Author info from form data
		int id = Integer.parseInt(request.getParameter("authorId"));
		String name = request.getParameter("name");
		String dobString = request.getParameter("dob");
		Date dob = sdf.parse(dobString);
		// create a new Author object
		Author theAuthor = new Author(id, name, dob);

		// perform update on database
		authorLogic.modifyAuthor(theAuthor);

		// send them back to the "list Authors" page
		listAuthor(request, response);

	}

	private void loadAuthor(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read Author id from form data
		String authorId = request.getParameter("authorId");

		Author theAuthor = authorLogic.findAuthorById(Long.parseLong(authorId));

		request.setAttribute("the_Author", theAuthor);

		RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.FOLDER_UPDATE_AUTHOR);
		dispatcher.forward(request, response);
	}

}
