package main.java.control.inquiryMain;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dto.InquiryDTO;
import model.InquiryDAO;

@WebServlet("/InquiryEditServlet")
public class InquiryEditServlet extends HttpServlet {
	 @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse res)
	            throws ServletException, IOException {
		 
		 // 0.ローグインセッション　確認
		 HttpSession session = req.getSession(false);
		 
		 Integer sessionUserId = (Integer) session.getAttribute("userId");
		 System.out.println("sessionUserId:" + sessionUserId);
		 Integer sessionAdminId = (Integer) session.getAttribute("adminId");
		 
		 
		 // 1. パラメーター有効性検査　（例：　/InquiryEditServlet?id=3)
		 // idはリンクURLから送信される物
		 String param = req.getParameter("inquiryId");
		 int inquiryId = 0;
		 
		 try {
			inquiryId = Integer.parseInt(param);
			System.out.println("inquiryId:" + inquiryId);
			
		} catch (NumberFormatException | NullPointerException e) {
			System.out.println("不明な inquiryId要請: " + param);
			res.sendRedirect(req.getContextPath() + "/InquiryListServlet");
			return;
		}
		 
		 // 2. データベースで該当の問い合わせ照会 データ取得
		 InquiryDTO dto = InquiryDAO.getInquiryById(inquiryId);
		 if (dto == null) {
			 System.out.println("存在しないinquiryId: " + inquiryId);
			 res.sendRedirect(req.getContextPath() + "/InquiryListServlet");
			 return;
		 }
		 
		 if (sessionAdminId != null) {
			    System.out.println("관리자 접근: adminId = " + sessionAdminId);
			}
		 
		 // 3. 権限確認
		if ((sessionUserId == null || !sessionUserId.equals(dto.getUserId())) && sessionAdminId == null) {
			req.setAttribute("inquiry", dto);  // 상세 내용 그대로 전달
			req.setAttribute("errorMsg", "この投稿を操作する権限がありません。");
			req.getRequestDispatcher("/inquiryMain/inquiryDetail.jsp").forward(req, res);
			return;
		}
		 
		 // 4. JSPへ送信
		 req.setAttribute("inquiry", dto);
		 req.getRequestDispatcher("/inquiryMain/inquiryEdit.jsp").forward(req, res);
	 }

}
