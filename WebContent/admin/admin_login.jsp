<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String message = request.getParameter("message");
pageContext.setAttribute("message", message);
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 頁籤小icon -->
<link rel="icon shortcut" href="./img/favicon.ico">
<!-- google Noto Sans TC 字型 -->

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100&display=swap"
	rel="stylesheet">
<script data-search-pseudo-elements defer
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js"
	crossorigin="anonymous"></script>
<!-- 載入Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<!-- 載入sweet alert -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<!-- 載入Bootstrap相關JS -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
	crossorigin="anonymous"></script>
<!-- CSS檔 -->
<link rel="stylesheet" href="./css/admin_login.css">

<title>後台登入</title>
</head>

<body>
	<div>
		<!-- navbar -->
		<!-- 使用Boostrap Navbar -->
		<!-- navbar-expand-md在768px以下就出現漢堡式選單 -->
		<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
			<!-- 導覽列左上角的文字 -->
			<a class="navbar-brand" href="./admin_login.jsp"> <img src="./img/m.svg"
				width="30" height="30" class="d-inline-block align-top" alt="">
				<p>MassFood! 後台管理</p>
			</a>
			<button class="navbar-toggler d-lg-none" type="button"
				data-toggle="collapse" data-target="#collapsibleNavId"
				aria-controls="collapsibleNavId" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<!-- 導覽列裡的連結 -->
			<div class="collapse navbar-collapse" id="collapsibleNavId">
				<ul class="navbar-nav ml-auto">
<!-- 					<li class="nav-item"><a class="nav-link" href="#">美食地圖</a></li> -->
					<li class="nav-item"><a class="nav-link" href="#">廠商專區</a></li>
					<li class="nav-item"><a class="nav-link" href="#">消費者專區</a></li>
					<li class="nav-item"><a class="nav-link" href="#">外送員專區</a></li>
				</ul>


			</div>

		</nav>
		<!-- navbar end -->
		<!-- 登入區 -->
		<section>
		<p class="mt-2 bg-warning  text-center" style="font-size:18px;">${message }</p>
			<form method="POST" action="<%=request.getContextPath()%>/admin/adm.do2">
				<div class="form-group pt-3">
					<p class="title pb-4">系統管理員</p>
					<label class="pb-3" for="exampleInputEmail1">帳號：</label> <input
						type="email" name="userEmail" class="form-control" id="email1"
						aria-describedby="emailHelp" placeholder="Email" required>
					<input type="hidden" name="adminXX" value="login">
				</div>
				<div class="form-group">
					<label class="pb-3" for="exampleInputPassword1">密碼：</label> <input
						type="password" name="userPassword" class="form-control"
						id="password1" placeholder="Password" required> <input
						type="hidden" name="adminXX" value="login">
				</div>
				<div class="pb-3" style="text-align: center">
					<button type="submit"
						class="btn btn-secondary btn-lg mt-5 signinbtn" id="submit">登入</button>
				</div>

			</form>
		</section>
		<!-- footer -->
		<footer id="footer" class="text-white">
			<div class="footer-end bg-dark mt-4 py-2">
				<p class="text-center">&copy; 2021 All Rights Reserved. 版權所有
					不得轉載</p>
			</div>
		</footer>

	</div>
	<!-- JS檔 -->
	<script src="./js/admin_login.js"></script>
</body>

</html>