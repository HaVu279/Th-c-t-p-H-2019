package com.tieucotdau.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tieucotdau.entities.Category;
import com.tieucotdau.logics.CategoryLogic;
import com.tieucotdau.logics.impl.CategoryLogicImpl;
import com.tieucotdau.utils.Constant;

/**
 * Servlet implementation class CategoryServletController
 */
@WebServlet("/CategoryServletController")
public class CategoryServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static CategoryLogic categoryLogic = new CategoryLogicImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");

			// if the command is missing, then default to listing Categorys
			if (theCommand == null) {
				theCommand = "LIST";
			}
			String name = request.getParameter("name");
			// route to the appropriate method
			switch (theCommand) {

			case "LIST":
				listCategorys(request, response, name);
				break;

			case "ADD":
				addCategory(request, response);
				break;

			case "NEW":
				newCategory(request, response);
				break;

			case "LOAD":
				loadCategory(request, response);
				break;

			case "UPDATE":
				updateCategory(request, response);
				break;

			case "DELETE":
				deleteCategory(request, response);
				break;

			default:
				listCategorys(request, response);
			}

		} catch (Exception exc) {
			throw new ServletException(exc);
		}

	}

	private void newCategory(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, SQLException {

		RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.FOLDER_ADD_CATEGORY);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read Category id from form data
		String theCategoryId = request.getParameter("categoryId");

		// delete Category from database
		categoryLogic.deleteCategoryById(Long.parseLong(theCategoryId));

		// send them back to "list Categorys" page
		listCategorys(request, response);
	}

	private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read Category info from form data
		int id = Integer.parseInt(request.getParameter("categoryId"));
		String name = request.getParameter("name");
		// create a new Category object
		Category theCategory = new Category(id, name);

		// perform update on database
		categoryLogic.modifyCategory(theCategory);

		// send them back to the "list Categorys" page
		listCategorys(request, response);

	}

	private void loadCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read Category id from form data
		String theCategoryId = request.getParameter("categoryId");

		// get Category from database (db util)
		Category theCategory = categoryLogic.findCategoryById(Long.parseLong(theCategoryId));

		// place Category in the request attribute
		request.setAttribute("the_Category", theCategory);

		// send to jsp page: update-category-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.FOLDER_UPDATE_CATEGORY);
		dispatcher.forward(request, response);
	}

	private void addCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read Category info from form data
		String name = request.getParameter("name");

		// create a new Category object
		Category theCategory = new Category(categoryLogic.generateId(), name);

		// add the Category to the database
		categoryLogic.addNewCategory(theCategory);

		// send back to main page (the Category list)
		listCategorys(request, response);
	}

	private void listCategorys(HttpServletRequest request, HttpServletResponse response, String name) throws Exception {

		List<Category> categorys = categoryLogic.findAllCategory(name);
		if (name != null) {
			request.setAttribute("search_name", name);
		}

		int count = categorys.size();
		int recordPerPage = Constant.RECORD_CATEGORY_PER_PAGE;
		int pageCount = count / recordPerPage;
		if (count % recordPerPage != 0) {
			pageCount += 1;
		}
		String spageid = request.getParameter("page");
		if (spageid == null) {
			spageid = "1";
		}
		Integer pageNum = Integer.parseInt(spageid);
		categorys = categoryLogic.findCategory(name, (pageNum - 1) * recordPerPage, recordPerPage);
		request.setAttribute("order_page_count", pageCount);

		// add Categorys to the request
		request.setAttribute("CATEGORY_LIST", categorys);

		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.FOLDER_LIST_CATEGORY);
		dispatcher.forward(request, response);
	}

	private void listCategorys(HttpServletRequest request, HttpServletResponse response) throws Exception {
		listCategorys(request, response, null);
	}

}
