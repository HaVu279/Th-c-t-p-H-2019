package com.tieucotdau.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tieucotdau.entities.User;
import com.tieucotdau.logics.UserLogic;
import com.tieucotdau.logics.impl.UserLogicImpl;

/**
 * Servlet implementation class UserServletController
 */
@WebServlet("/UserServletController")
public class UserServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static UserLogic userLogic = new UserLogicImpl();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String theCommand = request.getParameter("command");

			switch (theCommand) {
			case "LOGOUT":
				invalidateUserSession(request, response);
				break;

			case "ADD":
				addNewUser(request, response);
				break;

			case "NEW":
				newUser(request, response);
				break;

			default:
				break;
			}

		} catch (Exception exc) {
			throw new ServletException(exc);
		}

	}

	private void newUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/registration.jsp");
		dispatcher.forward(request, response);

	}

	private void addNewUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, ParseException {
		long id = userLogic.generateId();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String dob = request.getParameter("dob");
		Date dobDate = sdf.parse(dob);
		String password = request.getParameter("password");
		String cfpassword = request.getParameter("cfpassword");
		String check = request.getParameter("tb");
		
		if (cfpassword.equals(password) && check != null) {	
			User user = new User(id, name, email, dobDate, password);
			userLogic.addNewUser(user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/registration.jsp");
			dispatcher.forward(request, response);
		}

	}

	private void invalidateUserSession(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, SQLException {
		HttpSession session = request.getSession();
		// session.setAttribute("user",null);
		session.invalidate();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		try {
			User user = userLogic.findUser(email, password);
			if (user == null) {
				request.setAttribute("msg", "Wrong password or email");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				response.sendRedirect("BookStoreServletController");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
