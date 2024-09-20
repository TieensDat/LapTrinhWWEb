package com.example.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.example.models.UserModel;
import com.example.services.IUserService;
import com.example.services.impl.UserServiceImpl;
import com.example.utils.Constant;

/**
 * Servlet implementation class LoginController
 */
@WebServlet({"/login"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// lấy toàn bộ hàm trong service
	IUserService service = new UserServiceImpl();

	/**
	 * Default constructor.
	 */
	public LoginController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// Lấy form
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		request.getRequestDispatcher("/login.jsp").forward(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	// post dữ liệu lên.
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		// lay tham so tu view
		String username = req.getParameter("uname");
		String password = req.getParameter("psw");
		String remember = req.getParameter("remember");

		boolean isRememberMe = false;
		if ("on".equals(remember)) {
			isRememberMe = true;
		}
		String alertMsg = "";
		
		
		if (username.isEmpty() || password.isEmpty()) {
			alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
//			alertMsg = "1";

			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
			return;
		}
		// xử lý bài toán
		UserModel user = service.login(username, password);
		if (user != null) {
			HttpSession session = req.getSession(true);
			session.setAttribute("account", user);
			if (isRememberMe) {
				saveRemeberMe(resp, username);
			}
			resp.sendRedirect(req.getContextPath() + "/waiting");
		} else {
			alertMsg = "Tài khoản hoặc mật khẩu không đúng";
			alertMsg = "2";

			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}

	}

	private void saveRemeberMe(HttpServletResponse resp, String username) {
		// TODO Auto-generated method stub

		Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
		
//		30 * 60 giây = 1800 giây
		cookie.setMaxAge(30 * 60);
		resp.addCookie(cookie);

	}
	
	
	public static void main(String[] args) {
		try {
			IUserService service = new UserServiceImpl();
			System.out.print(service.login("tiendat", "123456"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
