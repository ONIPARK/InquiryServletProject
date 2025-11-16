<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>お問い合わせ入力</title>
</head>
<body>
	
	<h2>お問い合わせ入力</h2>
	<form method="post" action="${pageContext.request.contextPath}/InquiryServlet" id="inquiryForm" onsubmit="return submitCheck(event)">
	<!-- ID: <input type="number" name="userId"><br/>  -->
		
	    件名： <input type="text" name="subject" id="subject"><br/>
	    <small id="subjectError" style="color: red; display: none;"></small>
	    
	    内容： <textarea name="content" id="content" rows="5" cols="40"></textarea><br/>
	    <small id="contentError" style="color: red; display: none;"></small>
	    
	    <c:choose>    
	    	<c:when test="${not empty sessionScope.loggedInUser}">
	    	<p>セッション確認： ${sessionScope.username}</p>
	    	<!-- ローグイン　ユーザー -->
		    	氏名： <input type="text" name="name" id="name" value="${sessionScope.loggedInUser.name}" readonly><br/>
			    <small id="nameError" style="color: red; display: none;"></small>
			    
			    氏名（フリガナ）： <input type="text" name="nameFurigana" id="nameFurigana" value="${sessionScope.loggedInUser.nameFurigana}" readonly><br/>
			    <small id="furiError" style="color: red; display: none;"></small>
			    
			    メールアドレス： <input type="email" name="email" id="email" value="${sessionScope.loggedInUser.email}" readonly><br/>	
			    <small id="emailError" style="color: red; display: none;"></small>
	    	</c:when>
	    	
	    	
	    	<c:otherwise>
	    	<!-- 非ローグイン -->
				氏名： <input type="text" name="name" id="name"><br/>
		    	<small id="nameError" style="color: red; display: none;"></small>
		    
			    氏名（フリガナ）： <input type="text" name="nameFurigana" id="nameFurigana"><br/>
			    <small id="furiError" style="color: red; display: none;"></small>
			    
			    メールアドレス： <input type="email" name="email" id="email"><br/>	
			    <small id="emailError" style="color: red; display: none;"></small>
			    
			    メールアドレス(確認用)：<input type="email" name="confirmEmail" id="confirmEmail"><br/>	
			    <small id="confirmEmailError" style="color: red; display: none;"></small>	    	
	    	</c:otherwise>
	    </c:choose>
	    
	    <input type="submit" value="登録">
	</form>
	
	<script src="${pageContext.request.contextPath}/js/MainJs.js" defer></script>
</body>
</html>