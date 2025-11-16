package main.java.control.inquiryMain;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dto.InquiryDTO;
import mapper.InquiryMapper;
import model.InquiryDAO;

@WebServlet("/InquiryUpdateServlet")
public class InquiryUpdateServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		// リクエストデータからDTOを生成
		InquiryDTO dto = InquiryMapper.mapToUpdateDTO(req);
		
		// DAOを通じてDBのUPDATE実行
		boolean success = InquiryDAO.updateInquiry(dto);
		
		if (success) {
			//修正成功の場合、詳細ページにRedirect
			res.sendRedirect(req.getContextPath() + "/InquiryDetailServlet?inquiryId=" + dto.getInquiryId());
		} else {
			//失敗する場合、エラーメッセージと共に再度修正ページに遷移する。
			req.setAttribute("error", "更新に失敗しました。");
			req.setAttribute("inquiry", dto);
			req.getRequestDispatcher("/inquiryMain/inquiryEdit.jsp").forward(req, res);;
		}
		
	}
	

}
