<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ログイン</title>
</head>
<body>
    <h2>ログイン</h2>
    <form method="post" action="${pageContext.request.contextPath}/LoginServlet">
        ID: <input type="text" name="username" required><br>
        パスワード: <input type="password" name="password" required><br>
        <input type="submit" value="ログイン">
    </form>
    <a href="${pageContext.request.contextPath}/InquiryListServlet">リストに戻る</a>
    <a href="${pageContext.request.contextPath}/user/register.jsp">会員登録はこちら！</a>
</body>
</html>
