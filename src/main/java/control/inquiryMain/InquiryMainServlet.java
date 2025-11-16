package main.java.control.inquiryMain;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/inquiryMain")
public class InquiryMainServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession(false); // false: Sessionがない場合、NULL返還
		
		// Sessionにローグイン情報がない場合、ローグインページへ遷移
		if (session == null || session.getAttribute("loggedInUser") == null) {
			res.sendRedirect(req.getContextPath() + "/user/login.jsp");
			return;
		}
		
		// ローグインした場合、問い合わせページにforwarding
		req.getRequestDispatcher("/inquiryMain/inquiryMain.jsp").forward(req, res);
	}
}
