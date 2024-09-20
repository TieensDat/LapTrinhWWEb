package com.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.example.models.UserModel;
import com.example.services.IUserService;
import com.example.services.impl.UserServiceImpl;

/**
 * Servlet implementation class ForgotPasswordController
 */

@WebServlet("/forgotpassword")
public class ForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IUserService service = new UserServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPasswordController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/forgotPassword.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String username = req.getParameter("uname");
		String email = req.getParameter("email");
		
		String alertMsg = "";
		
		UserModel user = service.findByUserName(username);
		
		
		if (user == null || !email.equals(user.getEmail())) {
	        alertMsg = "Username và email không hợp lệ !";
	        req.setAttribute("alert", alertMsg);
	        req.getRequestDispatcher("/forgotPassword.jsp").forward(req, resp);
//	        return; // Chặn không cho tiếp tục nếu không hợp lệ
	    }
//		
		req.setAttribute("username", user.getUsername());
		req.getRequestDispatcher("/TakeNewPassword.jsp").forward(req, resp);
		
		
		
		
	}

}
