package main.java.control.inquiryMain;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dto.AdminReplyDTO;
import dto.InquiryDTO;
import model.AdminReplyDAO;
import model.InquiryDAO;

@WebServlet("/InquiryDetailServlet")
public class InquiryDetailServlet extends HttpServlet {
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
		
		System.out.println("📌 AdminReplyServlet doPost 실행됨");
		try {
			// 요청 파라미터 "id"를 정수로 변환 (예: ?id=3)
			// getParameter()는 String을 반환하기 때문에! 
			int inquiryId = Integer.parseInt(req.getParameter("id"));
			
			// DB에서 해당 ID의 문의 정보를 조회
			InquiryDTO inquiry = InquiryDAO.getInquiryById(inquiryId);
			AdminReplyDTO reply = AdminReplyDAO.getReplyById(inquiryId);
			
			if (inquiry == null) {
				//　問い合わせが存在しない場合、リストへ遷移する。
				resp.sendRedirect("InquiryListServlet");
				return;
			}
			
			// requestの属性に含め、JSPに送信する。
			// 조회된 문의 데이터를 request에 저장 (JSP에서 사용 가능)
			req.setAttribute("inquiry", inquiry);
			req.setAttribute("adminReply", reply);
			// JSP 페이지로 포워딩하여 상세 내용 표시
			req.getRequestDispatcher("/inquiryMain/inquiryDetail.jsp").forward(req, resp);
			
		} catch (Exception e) {
			// idパラメーターが数字ではない場合は、例外処理
			// "id"가 정수가 아니거나 null일 경우 예외 발생 → 목록으로 이동
			resp.sendRedirect("InquiryListServlet");
		}
    }
}
