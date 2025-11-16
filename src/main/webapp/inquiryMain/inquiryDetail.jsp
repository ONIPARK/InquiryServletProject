<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/inquiryDetail.css">
    <title>お問い合わせ詳細</title>
</head>
<body>
    <h2>お問い合わせ詳細</h2>

    <p><b>件名:</b> ${inquiry.subject}</p>
    <p><b>氏名:</b> ${inquiry.name} (${inquiry.email})</p>
    <p><b>作成日:</b> <fmt:formatDate value="${inquiry.createDate}" pattern="yyyy-MM-dd HH:mm" /></p>
    <p><b>内容:</b><br/>${inquiry.content}</p>
	
	<!-- ログインしている場合のみ表示 -->
	<c:if test="${not empty sessionScope.loggedInUser}">
		
		<!-- 権限なし、メッセージ出力 -->
		<c:if test="${not empty errorMsg}">
			<p style="color:red;">${errorMsg}</p>
		</c:if>
		
		<!-- 作成者本人のみ修正・削除を表示 -->
		<c:if test="${sessionScope.loggedInUser.userId == inquiry.userId || sessionScope.adminId != null}">
		<p>관리자 ID: ${sessionScope.adminId}</p>
			<!-- 修正ボタン -->
		<form action="${pageContext.request.contextPath}/InquiryEditServlet?id=${inquiry.inquiryId}" method="get">
		    <input type="hidden" name="inquiryId" value="${inquiry.inquiryId}" />
		    <input type="submit" value="修正" />
		</form>
		
		<!-- 削除ボタン： -->
		<form action="${pageContext.request.contextPath}/InquiryDeleteServlet" method="post" onsubmit="return confirm('本当に削除しますか？');">
			<input type="hidden" name="inquiryId" value="${inquiry.inquiryId}">
			<input type="submit" value="削除">
		</form>
		
		</c:if>
		
	</c:if>
	
    <hr/>

    <h3>返答</h3>

    <c:choose>
        <c:when test="${not empty adminReply}">
            <p>${adminReply.content}</p>
            <p><small>返答日: <fmt:formatDate value="${adminReply.replyDate}" pattern="yyyy-MM-dd HH:mm" /></small></p>
        </c:when>
        <c:otherwise>
            <p>まだ回答が登録されていません。</p>
        </c:otherwise>
    </c:choose>

    <hr/>

    <c:if test="${not empty sessionScope.loggedAdmin}">
	    <h3>返答登録</h3>
	    <form action="${pageContext.request.contextPath}/AdminReplyServlet" method="post">
	        <input type="hidden" name="inquiryId" value="${inquiry.inquiryId}" />
	        <textarea name="content" rows="6" cols="60" placeholder="返答内容を入力してください。" required></textarea><br/><br/>
	        <input type="submit" value="返答登録" />
	    </form>
    </c:if>

    <br/>
    <a href="InquiryListServlet">リストに戻る</a>
</body>
</html>
