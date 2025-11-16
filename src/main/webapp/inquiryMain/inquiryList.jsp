<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="dto.UserDTO" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/inquiryList.css">
	<title>お問い合わせLIST</title>
	<style>
	  table { border-collapse: collapse; width: 80%; }
	  th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
	  th { background-color: #f0f0f0; }
	  a { text-decoration: none; color: blue; }
	</style>
</head>
<body>
	
	<!-- 로그인 사용자 정보 표시(있을 때만) -->
	<div style="text-align: right; margin-right: 50px;">
		<c:choose>
			<%-- 管理者の場合 --%>
			<c:when test="${not empty sessionScope.loggedAdmin}">
				ようこそ、<strong>${sessionScope.loggedInAdmin.username}管理者様</strong>
				<a href="${pageContext.request.contextPath}/AdminLogout">ログアウト</a>
			</c:when>
			
			<%-- 会員の場合 --%>
			<c:when test="${not empty sessionScope.loggedInUser}">
				ようこそ、<strong>${sessionScope.loggedInUser.name}様</strong>
				<a href="${pageContext.request.contextPath}/logout">ログアウト</a>	
			</c:when>
			<c:otherwise>
				<a href="${pageContext.request.contextPath}/user/login.jsp">ログイン</a>	
			</c:otherwise>
		</c:choose>
	</div>
	
    </div>
    <h2>お問い合わせの目録</h2>

    <p><a href="${pageContext.request.contextPath}/inquiryMain">お問い合わせを作成する</a></p>

    <table>
        <tr>
            <th>NO</th>
            <th>件名</th>
            <th>氏名</th>
            <th>作成日</th>
            <th>回答可否</th>
        </tr>
        <c:forEach var="inquiry" items="${inquiries}">
            <tr>
                <td>${inquiry.inquiryId}</td>
                <td>
                    <a href="InquiryDetailServlet?id=${inquiry.inquiryId}">
                        ${inquiry.subject}
                    </a>
                </td>
                <td>${inquiry.name}</td>
                <td><fmt:formatDate value="${inquiry.createDate}" pattern="yyyy-MM-dd"/></td>
                <td>
                    <c:choose>
                        <c:when test="${inquiry.hasReply == false}">未返答</c:when>
                        <c:otherwise>回答完了</c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
    
    <div class="pagination">
    	<c:if test="${pagination.curPage > 1}">
    		<a href="InquiryListServlet?page=${pagination.prevPage}">以前</a>
    	</c:if>
    	
    	<c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}">
    		<c:choose>
    			<c:when test="${i == pagination.curPage}">
    				<span><strong>[${i}]</strong></span>
    			</c:when>
    			<c:otherwise>
    				<a href="InquiryListServlet?page=${i}">${i}</a>
    			</c:otherwise>
    		</c:choose>
    	</c:forEach>
    	
    	<c:if test="${pagination.curPage < pagination.pageCnt}">
    		<a href="InquiryListServlet?page=${pagination.nextPage}">次に</a>
    	</c:if>
    </div>
</body>
</html>
