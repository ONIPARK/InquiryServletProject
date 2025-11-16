package main.java.control.inquiryMain;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dto.AdminReplyDTO;
import model.AdminReplyDAO;

@WebServlet("/AdminReplyServlet")
public class AdminReplyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    	
        req.setCharacterEncoding("UTF-8");
        
        // 1. Sessionで、管理者ID確認
        HttpSession session = req.getSession(false);
        Integer adminId = (Integer) session.getAttribute("adminId");
        
        // 2. 要請パラメタ取得
        int inquiryId = Integer.parseInt(req.getParameter("inquiryId"));
        String content = req.getParameter("content");
        
        // 3. DTOに保存
        AdminReplyDTO dto = new AdminReplyDTO();
        dto.setAdminId(adminId);
        dto.setInquiryId(inquiryId);
        dto.setContent(content);
        
        // 4. Insert実行
        boolean success = AdminReplyDAO.insertReply(dto);
        
        if (success) {
            res.sendRedirect(req.getContextPath() + "/InquiryDetailServlet?id=" + inquiryId);
            
        } else {
        	req.setAttribute("error", "登録に失敗しました。");
            res.sendRedirect(req.getContextPath() + "/InquiryDetailServlet?id=" + inquiryId + "&error=1");
        }
             	
        
    }
}
