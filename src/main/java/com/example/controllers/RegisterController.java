package com.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.example.models.UserModel;
import com.example.services.IUserService;
import com.example.services.impl.UserServiceImpl;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IUserService service = new UserServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		// lay tham so tu view
		String username = req.getParameter("uname");
		String password = req.getParameter("pwd");
		String email = req.getParameter("email");
		String fullname = req.getParameter("fname");
		String phone = req.getParameter("phone");
		int roleid = 2;
		
		String alertMsg = "";
		
		
//		if (username.isEmpty() || password.isEmpty()) {
//			alertMsg = "Nhập đầy đủ thông tin!";
//
//			req.setAttribute("alert", alertMsg);
//			req.getRequestDispatcher("/register.jsp").forward(req, resp);
//			return;
//		}
		// xử lý bài toán
		UserModel user = service.register(username,email, password,fullname,phone);
		if (user != null) {
			alertMsg = "Đăng ký thành công !";

			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/register.jsp").forward(req, resp);
			
//			resp.sendRedirect(req.getContextPath() + "/waiting");
		} else {
			alertMsg = "Username đã tồn tại hoặc nhập thông tin không đầy đủ !";

			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/register.jsp").forward(req, resp);
		}
		
		req.getRequestDispatcher("/register.jsp").forward(req, resp);

	}

}
