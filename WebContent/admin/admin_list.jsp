<!-- 記得登入的人 不能修改密碼 -->

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.Admin.model.*"%>

<%
	AdminService admSvc = new AdminService();
	List<AdminVO> list = admSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<%
// 登入session
if(session.getAttribute("login")==null){
	RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/admin_login.html");
	dispatcher.forward(request, response);	
}
AdminVO adminVO=(AdminVO) request.getSession().getAttribute("login");
pageContext.setAttribute("adminVO", adminVO);
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
<!-- Font Awesome -->
<script data-search-pseudo-elements defer
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js"
	crossorigin="anonymous"></script>
<!-- 載入jQuery相關JS -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- 載入Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
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
<link rel="stylesheet" href="./css/admin_index.css">
<!-- JS檔 -->
<script src="./js/admin_index.js"></script>
<title>後台管理員列表</title>
</head>
<body>
<!-- 	<div style="height: 400px;display: flex;justify-content: center;align-items: center;"> -->
<div>
		<!-- navbar -->
		<!-- 使用Boostrap Navbar -->
		<!-- navbar-expand-md在768px以下就出現漢堡式選單 -->
		<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
			<!-- 導覽列左上角的文字 -->
			<a class="navbar-brand" href="./admin_index.jsp"> <img
				src="./img/m.svg" width="30" height="30"
				class="d-inline-block align-top" alt="">
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
					<li class="nav-item">
								<form method="POST" action="adm.do2">
						<button type="submit" class="btn btn-outline-warning btnsignup">登出</button>
						<input type="hidden" name="adminXX" value="logout">
						</form> 
					</li>
				</ul>
			</div>
		</nav>
		<!-- navbar end -->
		<!-- section -->
		<section class="container">
			<div class="row align-items-start">
				<!--左選單 -->
				<div class="col-lg-2 aside">
					<ul class="cart">
						<li><a href="#" data-toggle="collapse"
							data-target="#collapseSet">管理員設定</a>
							<ul class="collapse" id="collapseSet">
								<li><a href="./admin_list.jsp">管理員列表</a></li>
								<li><a href="./admin_insert.jsp">新增管理員</a></li>
							</ul></li>
						<li><a href="./admin_registerSearch.jsp">查詢註冊表單</a></li>
						<li><a href="#" data-toggle="collapse"
							data-target="#collapseChat">會員申訴聊天室</a>
							<ul class="collapse" id="collapseChat">
								<li><a href="<%=request.getContextPath()%>/chatRoom/MangerChatWithConsumer.jsp">消費者聊天室</a></li>
								<li><a href="<%=request.getContextPath()%>/chatRoom/MangerChatWithMerchant.jsp">商家聊天室</a></li>
								<li><a href="<%=request.getContextPath()%>/chatRoom/MangerChatWithCourior.jsp">外送員聊天室</a></li>
							</ul></li>
						<li><a href="#" data-toggle="collapse"
							data-target="#collapseSearch">資料調閱</a>
							<ul class="collapse" id="collapseSearch">
								<li><a href="./admin_memberSearch.jsp">會員查詢</a></li>
								<li><a href="./admin_orderSearch.jsp">訂單查詢</a></li>
								<li><a href="./admin_reportSearch.jsp">報表查詢</a></li>
							</ul></li>
					</ul>
				</div>

				<!-- 內容 -->
				<div class="row col-lg-10">
					<!--管理員列表 -->
					<div class="col-lg-12">
						<hr style="border: 1px solid #DB7243; background-color: #DB7243;">
						<h1 class="title">管理員列表</h1>
					</div>
					<div class="ml-5">
						<table class="table">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">管理員編號</th>
									<th scope="col">帳號</th>
									<th scope="col">修改</th>
									<th scope="col">刪除</th>
								</tr>
							</thead>
							<tbody>

								<%
									int i = 0;
									String test="collapsetest";
								%>
								<c:forEach var="A0" items="${list}">
									<tr>

										<td><%=++i%></td>
										<td>${A0.accountNumber}</td>
										<td>${A0.email}</td>
										<td><input type="button" data-toggle="collapse" data-target="<%='#'+test+i%>"
											class="btn btn-sm btn-outline-secondary" value="修改密碼">
										</td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/admin/adm.do1">
											<td><c:if test="${A0.accountNumber!=1}">  <input type="submit"
												class="btn btn-sm btn-outline-danger" value="刪除"> <input
												type="hidden" name="action" value="delete"></td> <input
												type="hidden" name="admno" value="${A0.accountNumber}"></c:if> 
											
<!-- 											<input type="submit" -->
<!-- 												class="btn btn-sm btn-outline-danger" value="刪除"> <input -->
<!-- 												type="hidden" name="action" value="delete"></td> <input -->
<%-- 												type="hidden" name="admno" value="${A0.accountNumber}"> --%>
									</tr>
									</form>
									<tr  id="<%=test+i%>" class="collapse">
										<td></td>
										<td>修改密碼:</td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/admin/adm.do1">
											<td><input type="text" name="changePassword"
												class="form-control" value="${A0.password}"></td>


											<td><input type="submit"
												class="btn btn-sm btn-outline-warning" value="儲存"> <input
												type="hidden" name="action" value="update"></td> <input
												type="hidden" name="admno" value="${A0.accountNumber}">
											<input type="hidden" name="changePassword"
												value="${A0.password}"> <input type="hidden"
												name="admemail" value="${A0.email}">
										</form>
									</tr>
								</c:forEach>
							</tbody>
						</table>


					</div>
					<div class="col-lg-12 mt-2">
						<hr style="border: 1px solid #DB7243; background-color: #DB7243;">

					</div>
				</div>
		</section>
	</div>
	<!-- section end-->
	<!-- footer -->
	<footer id=" footer" class="text-white">
		<div class="footer-end bg-dark mt-4 py-2">
			<p class="text-center">&copy; 2021 All Rights Reserved. 版權所有 不得轉載</p>
		</div>
	</footer>
	<!-- footer end-->
</body>

</html>