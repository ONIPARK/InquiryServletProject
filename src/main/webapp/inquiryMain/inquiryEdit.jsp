<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>お問い合わせ内容 修正</title>
</head>
<body>
    <h2>お問い合わせ内容 修正</h2>

    <form action="${pageContext.request.contextPath}/InquiryUpdateServlet" method="post">
        <!-- 고유 ID는 hidden으로 전달 -->
        <input type="hidden" name="inquiryId" value="${inquiry.inquiryId}" />
        
        <label>件名：</label>
        <input type="text" name="subject" value="${inquiry.subject}" required /><br>

        <label>内容：</label><br>
        <textarea name="content" rows="5" cols="50" required>${inquiry.content}</textarea><br><br>

        <label>氏名：</label>
        <input type="text" name="name" value="${inquiry.name}" readonly /><br>

        <label>氏名（フリガナ）：</label>
        <input type="text" name="nameFurigana" value="${inquiry.nameFurigana}" readonly /><br>

        <label>メールアドレス：</label>
        <input type="email" name="email" value="${inquiry.email}" readonly /><br>
        
        <input type="submit" value="保存する" />
        <a href="${pageContext.request.contextPath}/InquiryListServlet">キャンセル</a>
        
    </form>
    
    <!-- 削除ボタン： -->
	<form action="${pageContext.request.contextPath}/InquiryDeleteServlet" method="post" onsubmit="return confirm('本当に削除しますか？');">
		<input type="hidden" name="inquiryId" value="${inquiry.inquiryId}">
		<input type="submit" value="削除">
	</form>
	
</body>
</html>
