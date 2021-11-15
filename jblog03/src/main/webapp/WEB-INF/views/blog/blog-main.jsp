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
</head>
<body>
	<div id="container">
		<div id="header">
			<a href="${pageContext.request.contextPath}/blog/${authUser.id}">
				<h1>${blogVo.title }</h1>
			</a>
			<ul>
				<c:choose>
					<c:when test="${authUser == null }">
						<li><a href="">로그인</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="">로그아웃</a></li>
						<li><a href="${pageContext.request.contextPath}/blog/${authUser.id}/admin">블로그 관리</a></li>
						<li><a>${blogVo.id }님 환영합니다!</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${postVo.title }</h4>
					<p>
						${postVo.content }
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items="${postList }" var="list">
						<li><a href="${pageContext.request.contextPath}/blog/${authUser.id}/post/${list.no}">${list.title }</a> <span>${list.regDate }</span>	</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath }/${blogVo.logo }">
				<!-- ${blogVo.logo} -->
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${categoryList }" var="list">
					<li><a href="${pageContext.request.contextPath}/blog/${authUser.id}/category/${list.no}">${list.name }</a></li>
				</c:forEach>

			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>${blogVo.title }</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>