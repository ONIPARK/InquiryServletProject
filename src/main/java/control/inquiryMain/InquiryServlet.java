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

@WebServlet("/InquiryServlet")
public class InquiryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        InquiryDTO dto = InquiryMapper.mapToDTO(req);
        boolean result = InquiryDAO.insertInquiry(dto);

        if (result) {
            resp.sendRedirect(req.getContextPath() + "/inquiryMain/inquirySuccess.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/inquiryMain/inquiryError.jsp");
        }
    }
}
