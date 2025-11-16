package main.java.control.inquiryMain;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dto.InquiryDTO;
import model.InquiryDAO;
import utill.Pagination;

@WebServlet("/InquiryListServlet")
public class InquiryListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        // 1. 현재 페이지 번호 가져오기
        int curPage = 1;
        String pageParam = req.getParameter("page");
        
        if (pageParam != null) {
        	try {
				curPage = Integer.parseInt(pageParam);
				if (curPage < 1) curPage = 1; // 페이지가 0이나 음수일 때도 안전하게 1로
			} catch (Exception e) {
				curPage = 1; // 잘못된 입력은 그냥 1페이지로 처리
			}
        }
        // 2. 전체 게시글 수 가져오기
        int listCnt = InquiryDAO.getTotalCount();
        
        // 3. Pagination 객체 생성
        Pagination pagination = new Pagination(listCnt, curPage);
        
        // 4. 페이지 범위에 해당하는 게시글만 가져오기
        List<InquiryDTO> inquiries = InquiryDAO.getInquriesByPage(pagination.getStartIndex(), pagination.getPageSize());
        
        // 5. JSP에 전달
        req.setAttribute("inquiries", inquiries);
        req.setAttribute("pagination", pagination);
        req.getRequestDispatcher("/inquiryMain/inquiryList.jsp").forward(req, resp);
        
    }
}