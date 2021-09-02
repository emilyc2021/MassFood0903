<%@page import="com.Admin.model.AdminVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.Consumer.model.ConsumerVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	ConsumerVO consumerVO = (ConsumerVO) request.getAttribute("consumerVO");
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
<link rel="stylesheet" href="./css/admin_memberEdit.css">
<!-- JS檔 -->
<script src="./js/admin_memberEdit.js"></script>
<title>後台消費者註冊表單</title>
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
					<div>
						<h1>消費者註冊表單</h1>
						<form>					
							<div class="form-group row mt-3">
								<label for="name" class="col-sm-6 col-form-label title">系統自編會員編號：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control-plaintext" name="account"
										value="<%=consumerVO.getAccountNumber()%>" >
								</div>
							</div>

							<div class="form-group row">
								<label for="Email" class="col-sm-6 col-form-label title">帳號：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control-plaintext" name="Email"
										value="<%=consumerVO.getEmail()%>" readonly>
								</div>
							</div>
							<div class="form-group row">
								<label for="password" class="col-sm-6 col-form-label title">密碼：</label>
								<div class="col-sm-6">
									<input type="password" class="form-control-plaintext" name="password"
										value="<%=consumerVO.getPassword()%>">
								</div>
							</div>

							<div class="form-group row">
								<label for="name" class="col-sm-6 col-form-label title">姓名：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control-plaintext" name="name"
										value="<%=consumerVO.getName()%>">
								</div>
							</div>
							<div class="form-group row">
								<label for="id" class="col-sm-6 col-form-label title">身分證字號：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control-plaintext" name="id"
										value="<%=consumerVO.getIdentityNumber()%>">
								</div>
							</div>

							<div class="form-group row">
								<label for="phone" class="col-sm-6 col-form-label title">行動電話：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control-plaintext" name="phone"
										value="<%=consumerVO.getMobilePhone()%>">
								</div>
							</div>
							<div class="form-group row">
								<label for="address" class="col-sm-6 col-form-label title">居住地址：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control-plaintext" name="address"
										value="<%=consumerVO.getAddress()%>" readonly>
								</div>
							</div>
							<%
							String delivery=consumerVO.getDeliveryAddresses();
							String delivery1=delivery.replaceAll("\\p{Punct}", "");
							String delivery2=delivery1.replaceAll(" ", " ,");
							%>
							<div class="form-group row">
								<label for="address" class="col-sm-6 col-form-label title">常用配送地址：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control-plaintext" name="Deliveryaddress"
										value="<%=delivery2%>" readonly>
								</div>
							</div>

							<div class="form-group row">
								<label for="card" class="col-sm-6 col-form-label title">信用卡卡號：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control-plaintext" name="creditcard"
										value="<%=consumerVO.getCreditCardNumber()%>" readonly>
								</div>
							</div>
							<div class="form-group row">
								<label for="date" class="col-sm-6 col-form-label title">信用卡期限：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control-plaintext" name="creditdate"
										value="<%=consumerVO.getCreditCardExpirationDate()%>" readonly>
								</div>
							</div>
							<div class="form-group row">
								<label for="cvv" class="col-sm-6 col-form-label title">CVV：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control-plaintext" name="creditcvv"
										value="<%=consumerVO.getCreditSecurityCode()%>" readonly>
								</div>
							</div>

							<div class="form-group row">
								<label for="admin" class="col-sm-6 col-form-label title">註冊日期：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control-plaintext" name="modify"
										value="<%=consumerVO.getEnrollmentDate()%>" readonly>
									<input type="hidden" class="form-control" name="enrollmentDate"
										value="<%=consumerVO.getEnrollmentDate()%>">
								</div>
							</div>
							<div class="form-group row">
								<label for="modify" class="col-sm-6 col-form-label title">前次修改時間：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control-plaintext" name="modify"
										value="<%=consumerVO.getLastUpdateDatetime()%>" readonly>
								</div>
							</div>
							<div class="form-group row">
								<label for="admin" class="col-sm-6 col-form-label title">前次修改人員：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control-plaintext" name="admin"
										value="<%=consumerVO.getLastUpdateAccountNumber()%>" readonly>
								</div>
							</div>

							<%
								String meal = "";
								if (consumerVO.getIsExposeMealInformation() == true) {
									meal = "是";
								} else if (consumerVO.getIsExposeMealInformation() != true) {
									meal = "否";
								}
							%>
							<div class="form-group row">
								<label for="admin" class="col-sm-6 col-form-label title">是否顯示餐點：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control-plaintext" id="meal"
										value="<%=meal%>" > <input type="hidden"
										class="form-control" name="meal"
										value="<%=consumerVO.getIsExposeMealInformation()%>">
								</div>
							</div>


							<%
								String enable = "";
								if (consumerVO.getIsEnable() == true) {
									enable = "是";
								} else if (consumerVO.getIsEnable() != true) {
									enable = "否";
								}
							%>
							<div class="form-group row">
								<label for="admin" class="col-sm-6 col-form-label title">是否啟用：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control-plaintext" id="enable"
										value="<%=enable%>" > <input type="hidden"
										class="form-control" name="enable"
										value="<%=consumerVO.getIsEnable()%>">
								</div>
							</div>



							<div class="form-group row">
								<label for="admin" class="col-sm-6 col-form-label title">帳號狀態：</label>
								<div class="col-sm-6">
									<%
										String status = "";
										if (consumerVO.getStatus() == 0) {
											status = "不通過";
										} else if (consumerVO.getStatus() == 1) {
											status = "待審核";
										} else if (consumerVO.getStatus() == 2) {
											status = "通過";
										}
									%>
									<input type="text" class="form-control-plaintext" id="status"
										value="<%=status%>" > <input type="hidden"
										class="form-control" name="status"
										value="<%=consumerVO.getStatus()%>">
								</div>
							</div>

						</form>
					</div>
			</div>
			</main>
	</div>
	</section>
	<!-- footer -->
	<footer id=" footer" class="text-white">
		<div class="footer-end bg-dark mt-4 py-2">
			<p class="text-center">&copy; 2021 All Rights Reserved. 版權所有 不得轉載</p>
		</div>
	</footer>
</body>

</html>