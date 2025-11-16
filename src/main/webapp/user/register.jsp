<%@ page language="java" contentType="text/html; charset=UTF-8"
	 pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会員登録</title>
</head>
<body>
	<h2>会員登録</h2>
	<form method="post" action="${pageContext.request.contextPath}/RegisterServlet" onsubmit="return validateForm()">
	    ログイン名: <input type="text" id="username" name="username" required><br>
	    <button type = "button" onclick="checkDuplicate()">重複チェック</button>
	    <span id = "checkResult"></span>
	    <br>
	    パスワード: <input type="password" name="password" required><br>
	    パスワード（再度）: <input type="password" name="confirmPassword" required><br>
	    氏名: <input type="text" name="name" required><br>
	    氏名(フリガナ): <input type="text" name="nameFurigana" required><br>
	    メールアドレス: <input type="email" name="email" required><br>
	    <input type="submit" value="入力する">
	</form>
	<script>
    	const contextPath = "${pageContext.request.contextPath}";
	</script>
	<script src="${pageContext.request.contextPath}/js/registerJs.js" defer></script>
</body>
</html>