<%@page import="com.Admin.model.AdminVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.Courior.model.*"%>
<%@ page import="com.Emily.Model.*"%>

<%-- <jsp:useBean id="list_m3" scope="request"type="java.util.List<CouriorVO>" /> --%>
<%   List<CouriorVO> list = (List<CouriorVO>)session.getAttribute("list_m3");%> 
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
<link rel="stylesheet" href="./css/admin_registerSearch.css">
<!-- JS檔 -->
<script src="./js/admin_registerSearch.js"></script>
<title>後台註冊表單查詢</title>
</head>

<body>
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
		<section class="container">
			<!--左選單 -->
			<div class="row align-items-start">
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
				<!--內容 -->
				<main class="col-lg-10 main">
					<!-- 查詢 -->
					<div>
						<h1>註冊表單查詢</h1>
						<form METHOD="post" ACTION="register.do">
							<div class="form-inline">
								<div class="form-group col-md-12">
									<label for="memberType">會員類型：</label> <select id="memberType"
										name="memberType" class="form-control">
										<option selected value="M1">消費者</option>
										<option value="M2">商家</option>
										<option value="M3">外送員</option>
									</select>
								</div>
							</div>
							<div class="form-inline mt-3">
								<div class="form-group col-md-12">
									<label for="accountStatus">帳號狀態：</label>
									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio"
											name="Status" id="no" value="0"> <label
											class="form-check-label mr-2" for="no">審核不通過</label> <input
											class="form-check-input" type="radio" name="Status"
											id="wait" value="1"> <label
											class="form-check-label mr-2" for="wait">待管理員審核</label> <input
											class="form-check-input" type="radio" name="Status"
											id="yes" value="2"> <label class="form-check-label"
											for="yes">審核通過</label>
									</div>
								</div>
							</div>
							<div class="form-inline mt-3">
								<div class="form-group col-md-10">
									<label for="orderDate">註冊日期：</label> <input type="date"
										class="form-control" id="orderDate" name="EnrollmentDate" value="">
								</div>
								<button type="submit" class="col-md-1 btn btn-light btnsearch">查詢</button>
								<input type="hidden" name="action" value="registerSearch">
							</div>
						</form>
						<hr style="border: 1px solid #DB7243; background-color: #DB7243;">
					</div>
					<!-- 					<div> -->
					<!-- 						<div class="form-inline mt-3"> -->
					<!-- 							<div class="col-md-8"> -->
					<!-- 								<select class="form-control"> -->
					<!-- 									<option disabled selected hidden>排序方式</option> -->
					<!-- 									<option>會員編號遞增</option> -->
					<!-- 									<option>會員編號遞減</option> -->
					<!-- 								</select> -->
					<!-- 							</div> -->
					<!-- 							<div class="col-md-4"> -->
					<!-- 								<span>一頁顯示</span> -->
					<!-- 								<div class="dropdown" id="viewNum"> -->
					<!-- 									<select class="form-control p-2"> -->
					<!-- 										<option selected>10筆</option> -->
					<!-- 										<option>50筆</option> -->
					<!-- 									</select> -->
					<!-- 								</div> -->
					<!-- 								<a href="#">下一頁</a> <a href="#">最後一頁</a> -->
					<!-- 							</div> -->
					<!-- 						</div> -->
					<!-- 					</div> -->
					
						<%@ include file="pages/page1.file"%>
					<div class="table-responsive-md mt-3">
						<table class="table table-hover table-bordered">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">會員編號</th>
									<th scope="col">會員類型</th>
									<th scope="col">姓名</th>
									<th scope="col">行動電話</th>
									<th scope="col">信箱</th>
									<th scope="col">帳號狀態</th>
									<th scope="col">審核</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<%
										int i = 0;
									%>

									<c:forEach var="M3" items="${list_m3}" begin="<%=pageIndex%>"
									end="<%=pageIndex+rowsPerPage-1%>"> 

<%-- 									<c:forEach var="M3" items="${list_m3}" > --%>
										<th scope="row"><%=++i%></th>
										<td>${M3.accountNumber}</td>
										<td>外送員</td>
										<td>${M3.name}</td>
										<td>${M3.mobilePhone}</td>
										<td>${M3.email}</td>
										<td><c:if test="${M3.status==0}"> 不通過 </c:if> <c:if
												test="${M3.status==1}"> 待審核 </c:if> <c:if
												test="${M3.status==2}"> 通過 </c:if></td>


<!-- 										<td><a -->
<%-- 											href="adm.do?name=consumer&account=${M1.accountNumber}"><i --%>
<!-- 												class="far fa-edit"></i></a></td> -->

										<td><c:if test="${M3.status==0}">
												<a href="register.do1?name=courior&account=${M3.accountNumber}&status=0" class="no"><i class="fas fa-times"></i></a>
											</c:if> <c:if test="${M3.status==1}">
												<a href="register.do1?name=courior&account=${M3.accountNumber}&status=1"><i class="far fa-edit"></i></i></a>
											</c:if> <c:if test="${M3.status==2}">
												<a href="register.do1?name=courior&account=${M3.accountNumber}&status=2" class="pass"><i
														class="fas fa-check"></i></a>
											</c:if></td>
											</tr>
								</c:forEach>
							</tbody>
						</table>
						
						<div class="form-inline mt-3 ">
							<div class="col-md-3 page1"></div>
							<div class="col-md-6 text-center">

<%-- 								<%@ include file="pages/page2.file"%> --%>
							</div>
							<div class="col-md-3 page1"></div>
						</div>
						
						</div>
				</main>
			</div>
		</section>
		<footer id=" footer" class="text-white">
			<div class="footer-end bg-dark mt-4 py-2">
				<p class="text-center">&copy; 2021 All Rights Reserved. 版權所有
					不得轉載</p>
			</div>
		</footer>
</body>

</html>