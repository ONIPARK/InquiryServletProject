package main.java.control.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dto.UserDTO;
import mapper.UserMapper;
import model.UserDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		
		// サーバーから値を受け取り、
		UserDTO dto = UserMapper.mapToLoginDTO(req);
		// 値が一致するのか確認した上、一致したらセッションを保つ役割
		UserDAO dao = new UserDAO(); 
		UserDTO user = dao.findUserByLogin(dto);
		
		if (user != null) {
			// ① ログイン成功の場合
			// 1. 既存のSession無力化
			HttpSession oldSession = req.getSession(false);
			if (oldSession != null) {
				oldSession.invalidate();
			}
			
			// 2. 新しいSession生成（強制生成）
			HttpSession newSession = req.getSession(true);
			newSession.setAttribute("loggedInUser", user); // セッションにローグインユーザー情報保存
			newSession.setAttribute("userId", user.getUserId()); 
			System.out.println("user.getUserId(): " + user.getUserId());
			
			res.sendRedirect("index.jsp"); // メインページへRedirect
		} else {
			// ② ログイン失敗の場合
			req.setAttribute("error", "ログイン　失敗：　ユーザー名又は、パスワードが一致しません。");
			req.getRequestDispatcher("/user/login.jsp").forward(req, res);
		}
		
	}
	
	
}
