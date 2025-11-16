package main.java.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter({"/InquiryEditServlet", "/InquiryDeleteServlet"})
public class LoginCheckFilter implements jakarta.servlet.Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException{
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession(false); //セッションがない場合、Null
		
		if (session == null || session.getAttribute("userId") == null) {
			res.sendRedirect(req.getContextPath() + "/userLogin/login.jsp");
			return; //　未ログインの場合、login.jspへ遷移
		}
		
		chain.doFilter(req, res);
		
	}
}
