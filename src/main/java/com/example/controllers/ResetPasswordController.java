package com.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.example.services.IUserService;
import com.example.services.impl.UserServiceImpl;

/**
 * Servlet implementation class ResetPasswordController
 */
@WebServlet("/resetpassword")
public class ResetPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUserService service = new UserServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPasswordController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String password = req.getParameter("new-password");
		String cofmpassword = req.getParameter("confirm-password");
		String username = req.getParameter("username");
		String alertMsg = "";
		if(password != cofmpassword) {
			alertMsg = "Mật khẩu mới không khớp !";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/TakeNewPassword.jsp").forward(req, resp);
		}
		
		service.resetPassword(username, password);
		
		
		
		
		
		
	}

}
