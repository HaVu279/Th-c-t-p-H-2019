package com.tieucotdau.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tieucotdau.entities.Author;
import com.tieucotdau.entities.Category;
import com.tieucotdau.logics.AuthorLogic;
import com.tieucotdau.logics.BookLogic;
import com.tieucotdau.logics.CategoryLogic;
import com.tieucotdau.logics.impl.AuthorLogicImpl;
import com.tieucotdau.logics.impl.BookLogicImpl;
import com.tieucotdau.logics.impl.CategoryLogicImpl;
import com.tieucotdau.utils.Constant;

/**
 * Servlet implementation class BookStoreServletController
 */
@WebServlet("/BookStoreServletController")
public class BookStoreServletController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static CategoryLogic categoryLogic = new CategoryLogicImpl();
	public static AuthorLogic authorLogic = new AuthorLogicImpl();
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

			// route to the appropriate method
			switch (theCommand) {

			case "TOP_AUTHOR_REVENUE":
				topAuthorByRevenue(request, response);
				break;

			case "TOP_CATEGORY_REVENUE":
				topCategoryByRevenue(request, response);
				break;

			default:
				showAll(request, response);
			}

		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	public static void topCategoryByRevenue(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ArrayList<Category> categoryList = categoryLogic.findAllCategory(null);
		Collections.sort(categoryList, Category.compare);
		System.out.println("Top Category by revenue");
		request.setAttribute("category_List", categoryList);
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.FOLDER_CATEGORY_REVENUE);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void topAuthorByRevenue(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ArrayList<Author> authorList = authorLogic.findAllAuthor(null);
		Collections.sort(authorList, Author.compare);
		System.out.println("Top Category by revenue");
		request.setAttribute("author_List", authorList);
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.FOLDER_AUTHOR_REVENUE);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public static void showAll(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ArrayList<Category> categoryList = categoryLogic.findAllCategory(null);
		Collections.sort(categoryList, Category.compare);
		System.out.println("Top Category by revenue");
		request.setAttribute("category_List", categoryList);
		ArrayList<Author> authorList = authorLogic.findAllAuthor(null);
		Collections.sort(authorList, Author.compare);
		System.out.println("Top Author by revenue");
		request.setAttribute("author_List", authorList);
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.FOLDER_HOME);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
