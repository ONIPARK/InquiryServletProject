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

@WebServlet("/InquiryDeleteServlet")
public class InquiryDeleteServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// 1. ローグインセッション　維持情報を取得する
		HttpSession session = req.getSession(false);
		
		Integer sessionUserId = (Integer) session.getAttribute("userId");
		
		try {
			
			// 2. パラメーター有効性検査　（例：　/InquiryEditServlet?id=3)
			int inquiryId = Integer.parseInt(req.getParameter("inquiryId"));
			System.out.println("削除リクエスト: inquiryId=" + inquiryId + ", sessionUserId=" + sessionUserId);
			
			// 問い合わせ情報照会（作成者確認用）
			InquiryDTO dto = InquiryDAO.getInquiryById(inquiryId);
			if (dto == null) {
				res.sendRedirect(req.getContextPath() + "/InquiryListServlet");
				return;
			}
			
			System.out.println("DBで取得した userId: " + dto.getUserId());
			System.out.println("セッションuserId: " + sessionUserId);
			
			
			// 作成者と現在のユーザーが一致するかどうかを確認
			if (dto.getUserId() != (sessionUserId)) {
				req.setAttribute("inquiry", dto);
				req.setAttribute("errorMsg", "この投稿を削除する権限がありません。");
				req.getRequestDispatcher("/inquiryMain/inquiryDetail.jsp").forward(req, res);
				return;
			}
			
			
			// 削除実行
			boolean deleted = InquiryDAO.deleteInquiryById(inquiryId);
			if (deleted) {
				res.sendRedirect(req.getContextPath() + "/InquiryListServlet");
			} else {
				req.setAttribute("error", "削除に失敗しました。");
				req.setAttribute("inquiry", dto);
				req.getRequestDispatcher("/inquiryMain/inquiryDetail.jsp").forward(req, res);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			res.sendRedirect(req.getContextPath() + "/InquiryListServlet");
		}
	}
}
