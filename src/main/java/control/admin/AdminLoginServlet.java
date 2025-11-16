package main.java.control.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dto.AdminDTO;
import model.AdminDAO;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		// サーバーから値を受け取り、
		AdminDTO dto = new AdminDTO();
		dto.setUsername(req.getParameter("username"));
		dto.setPassword(req.getParameter("password"));
		System.out.println("Admin 確認：" + req.getParameter("username"));
		System.out.println("Admin 確認：" + req.getParameter("password"));
		
		AdminDAO dao = new AdminDAO();
		AdminDTO admin = dao.findAdminByLogin(dto);
		
		if(admin != null) {
			// １．既存のSession無効
			HttpSession oldSession = req.getSession(false);
			if (oldSession != null) {
				oldSession.invalidate();
			}
			
			// 2.新しいSession生成
			HttpSession newSession = req.getSession(true);
			newSession.setAttribute("loggedAdmin", admin);
			newSession.setAttribute("adminId", admin.getAdminId());
			
			res.sendRedirect(req.getContextPath() + "/index.jsp");
			
		} else {
			req.setAttribute("error", "ログイン　失敗：　ユーザー名又は、パスワードが一致しません。");
			req.getRequestDispatcher("/admin/adminLogin.jsp").forward(req, res);
		}
		
	}
	
}
