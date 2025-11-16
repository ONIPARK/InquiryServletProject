package main.java.control.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.UserDAO;

@WebServlet("/checkId")
public class CheckIdServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException{
		
		String userId = req.getParameter("username");
		boolean exists = UserDAO.isUserIdExists(userId); // データベースで、ID重複を確認する。
		
		// JavaScript Fetch()와 서버(Servlet)가 소통하기 위해 작성된 구문
		res.setContentType("text/plain; charset=UTF-8");
		if (exists) {
			res.getWriter().write("DUPLICATE");
		} else {
			res.getWriter().write("OK");
		}
	}
}
