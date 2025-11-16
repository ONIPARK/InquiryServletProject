<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="loginForm" method="post" action="${pageContext.request.contextPath}/AdminLoginServlet">
		ID:<input type="text" name="username" placeholder="ID" required><br>
		パスワード:<input type="password" name="password" placeholder="パスワード" required><br>
		<button onclick="login()">管理者ログイン</button>
	</form>
	<script src="${pageContext.request.contextPath}/js/adminJs.js" defer></script>
</body>
</html>