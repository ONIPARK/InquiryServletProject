package main.java.control.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
	// session 無効化
	HttpSession session = req.getSession(false); // 既存のSessionをもらう。ない場合、NULL
	if (session != null) {
		session.invalidate(); // Session 終了
	}
	
	// 問い合わせのリストページ(index.jsp)に遷移（Redirection）
	res.sendRedirect(req.getContextPath() + "/index.jsp");
	}
}
