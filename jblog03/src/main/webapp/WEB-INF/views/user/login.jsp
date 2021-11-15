<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div class="center-content">
		<a href="${pageContext.request.contextPath}/main">
			<h1 class="logo" style="background:url(${pageContext.request.contextPath}/assets/images/logo.jpg)">JBlog</h1>
		</a>
		<ul class="menu">
			<li><a href="${pageContext.request.contextPath}/user/joinForm">회원가입</a></li>
			<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
			<li><a href="">내블로그</a></li>
		</ul>
		<form class="login-form" action="${pageContext.request.contextPath}/user/auth">
      		<label>아이디</label> <input type="text" name="id">
      		<label>패스워드</label> <input type="text" name="password">
      		<c:set var='result' value='${result }'/>
					<c:choose>
						<c:when test='${result == "fail" }'>
						<p>
							로그인이 실패 했습니다.
						</p>
						</c:when>
					</c:choose>
      		<input type="submit" value="로그인">
		</form>
	</div>
</body>
</html>
