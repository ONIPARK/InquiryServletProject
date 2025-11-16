package main.java.control.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dto.UserDTO;
import mapper.UserMapper;
import model.UserDAO;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		
		UserDTO user = UserMapper.mapToDTO(req);
		boolean result = UserDAO.insertUser(user);
		
		if (result) {
			resp.sendRedirect(req.getContextPath() + "/user/login.jsp");
		} else {
			resp.sendRedirect(req.getContextPath() + "/user/registerError.jsp");
		}
	}
}
