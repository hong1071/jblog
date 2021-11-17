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
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>	

	<script>
	
		//jquery 이벤트 처리
		$(function(){
			$("#btnChkEmail").click(function(){
				var email = $("#blog-id").val();
				if(email == ''){
					return;
				}
				console.log(email);
				$.ajax({
					url:"${pageContext.request.contextPath }/user/api/checkemail?email=" + email,
					type: "get",
					dataType: "json",
					error: function(xhr, statues, e){				//xhr: ajax에서 사용하는 XmlHttpRequest객체
						console.log(status, e);
					},					
					success: function(response){
						console.log(response);
						
						if(response.result != "success"){
							console.error(response.message);
							return;
						}
						
						if(response.data){
							alert("존재하는 이메일입니다. 다른 이메일을 사용하세요");
							$("#blog-id").val("").focus();
							return;
						}
						
						$("#btnChkEmail").hide();
						$("#imgCheckEmail").show();
					}
				});
			});
		});
	
	</script>

</head>
<body>
	<div class="center-content">
		<a href="${pageContext.request.contextPath}/main">
			<h1 class="logo" style="background:url(${pageContext.request.contextPath}/assets/images/logo.jpg)">JBlog</h1>
		</a>
		<ul class="menu">
			<li><a href="${pageContext.request.contextPath}/user/loginForm">로그인</a></li>
		</ul>
		<form class="join-form" id="join-form" method="post" action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
			<input id="name"name="name" type="text" value="">
			
			<label class="block-label" for="blog-id">아이디</label>
			<input id="blog-id" name="id" type="text"> 
			<input id="btnChkEmail" type="button" value="id 중복체크">
			<img id="imgCheckEmail" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" />

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form>
	</div>
</body>
</html>
