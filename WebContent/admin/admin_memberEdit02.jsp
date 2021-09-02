<%@page import="com.Admin.model.AdminVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="com.Merchant.model.MerchantVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	MerchantVO merchantVO = (MerchantVO) request.getAttribute("merchantVO");
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
<jsp:useBean id="systemParameterVO" scope="page"
	class="com.SystemParameter.model.SystemParameterJDBCDAO" />

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
<title>後台商家資訊</title>
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
						<h1>商家資料查看</h1>

						<form METHOD="post"
							ACTION="<%=request.getContextPath()%>/admin/member.do">

							<div class="form-group row">
								<label for="phone" class="col-sm-4 col-form-label title">商家照片：</label>
								<div class="col-sm-6 align-self-center">
									<c:choose>
										<c:when test="<%=merchantVO.getPicture() == null%>">
											<span>會員未上傳照片</span>
										</c:when>
										<c:otherwise>
											<img src="<%=merchantVO.getPicture()%>" width="200"
												alt="未上傳照片">
										</c:otherwise>
									</c:choose>
								</div>
							</div>

							<div class="form-group row mt-3">
								<label for="name" class="col-sm-4 col-form-label title">系統自編會員編號：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="account"
										value="<%=merchantVO.getAccountNumber()%>" readonly>
								</div>
							</div>

							<div class="form-group row">
								<label for="Email" class="col-sm-4 col-form-label title">帳號：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="Email"
										value="<%=merchantVO.getEmail()%>" readonly>
								</div>
							</div>
							<div class="form-group row">
								<label for="password" class="col-sm-4 col-form-label title">密碼：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="password"
										value="<%=merchantVO.getPassword()%>" readonly>
								</div>
							</div>

							<div class="form-group row">
								<label for="name" class="col-sm-4 col-form-label title">商家名稱：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="name"
										value="<%=merchantVO.getName()%>" readonly>
								</div>
							</div>
							<div class="form-group row">
								<label for="id" class="col-sm-4 col-form-label title">統一編號：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="id"
										value="<%=merchantVO.getIdentityNumber()%>" readonly>
								</div>
							</div>

							<div class="form-group row">
								<label for="id" class="col-sm-4 col-form-label title">聯絡人姓名：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control"
										name="ContactPersonName"
										value="<%=merchantVO.getContactPersonName()%>" readonly>
								</div>
							</div>
							<!-- 							<div class="form-group row"> -->
							<!-- 								<label for="id" class="col-sm-4 col-form-label title">聯絡人電子信箱：</label> -->
							<!-- 								<div class="col-sm-6"> -->
							<!-- 									<input type="text" class="form-control" -->
							<!-- 										name="contactPersonEmail" -->
							<%-- 										value="<%=merchantVO.getContactPersonEmail()%>"> --%>
							<!-- 								</div> -->
							<!-- 							</div> -->
							<div class="form-group row">
								<label for="id" class="col-sm-4 col-form-label title">聯絡人身分證字號：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="pid"
										value="<%=merchantVO.getContactPersonIdentityNumber()%>"
										readonly>
								</div>
							</div>

							<div class="form-group row">
								<label for="phone" class="col-sm-4 col-form-label title">商家區域：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="ZipCode"
										value=<c:forEach var="sys" items="${systemParameterVO.all}">
																<c:if test="${merchantVO.zipCode==sys.code}">
																 "${sys.description}"
																</c:if>						
																</c:forEach>
										readonly>
								</div>
							</div>

							<div class="form-group row">
								<label for="phone" class="col-sm-4 col-form-label title">商家地址：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="address"
										value="<%=merchantVO.getAddress()%>" readonly>
								</div>
							</div>

							<div class="form-group row">
								<label for="phone" class="col-sm-4 col-form-label title">商家電話：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="LandlinePhone"
										value="<%=merchantVO.getLandlinePhone()%>" readonly>
								</div>
							</div>
							<%
								String foodType = "";
								switch (merchantVO.getFoodType()) {
								case 1:
									foodType = "早午餐";
									break;
								case 2:
									foodType = "火鍋";
									break;
								case 3:
									foodType = "韓式料理";
									break;
								case 4:
									foodType = "日式料理";
									break;
								case 5:
									foodType = "美式料理";
									break;
								case 6:
									foodType = "義式料理";
									break;
								case 7:
									foodType = "台式料理";
									break;
								case 8:
									foodType = "甜點飲料";
									break;
								}
							%>
							<div class="form-group row">
								<label for="phone" class="col-sm-4 col-form-label title">餐點類型：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="foodType"
										value="<%=foodType%>" readonly>
								</div>
							</div>



							<div class="form-group row">
								<label for="phone" class="col-sm-4 col-form-label title">商家描述：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="description"
										value="<%=merchantVO.getDescription()%>" readonly>
								</div>
							</div>

							<!-- 							<div class="form-group row"> -->
							<!-- 								<label for="phone" class="col-sm-4 col-form-label title">營業(星期一 -->
							<!-- 									~ 星期日)：</label> -->
							<!-- 								<div class="col-sm-6"> -->
							<!-- 									<input type="text" class="form-control" name="weekday" -->
							<%-- 										value="<%=merchantVO.getWeekday()%>" readonly> --%>
							<!-- 								</div> -->
							<!-- 							</div> -->

							<!-- 							<div class="form-group row"> -->
							<!-- 								<label for="phone" class="col-sm-4 col-form-label title">營業時段：</label> -->
							<!-- 								<div class="col-sm-6"> -->
							<!-- 									<input type="text" class="form-control" name="businessHour" -->
							<%-- 										value="<%=merchantVO.getBusinessHour()%>" readonly> --%>
							<!-- 								</div> -->
							<!-- 							</div> -->

							<!-- 							<div class="form-group row"> -->
							<!-- 								<label for="phone" class="col-sm-4 col-form-label title">公休日：</label> -->
							<!-- 								<div class="col-sm-6"> -->
							<!-- 									<input type="text" class="form-control" name="closedDate" -->
							<%-- 										value="<%=merchantVO.getClosedDate()%>" readonly> --%>
							<!-- 								</div> -->
							<!-- 							</div> -->

							<div class="form-group row">
								<label for="address" class="col-sm-4 col-form-label title">銀行代號：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="bankCode"
										value="<%=merchantVO.getBankCode()%>" readonly>
								</div>
							</div>
							<div class="form-group row">
								<label for="address" class="col-sm-4 col-form-label title">銀行帳號：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="bankAccount"
										value="<%=merchantVO.getBankAccount()%>" readonly>
								</div>
							</div>

							<div class="form-group row">
								<label for="address" class="col-sm-4 col-form-label title">銀行戶名：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="bankAccountName"
										value="<%=merchantVO.getBankAccountName()%>" readonly>
								</div>
							</div>




							<div class="form-group row">
								<label for="admin" class="col-sm-4 col-form-label title">註冊日期：</label>
								<div class="col-sm-6">
									<input type="hidden" class="form-control" name="enrollmentDate"
										value="<%=merchantVO.getEnrollmentDate()%>"> <input
										type="text" class="form-control" name="modify"
										value="<%=merchantVO.getEnrollmentDate()%>" readonly>
								</div>
							</div>

							<div class="form-group row">
								<label for="modify" class="col-sm-4 col-form-label title">前次修改時間：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="modify"
										value="<%=merchantVO.getLastUpdateDatetime()%>" readonly>
								</div>
							</div>
							<div class="form-group row">
								<label for="admin" class="col-sm-4 col-form-label title">前次修改人員：</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="admin"
										value="<%=merchantVO.getLastUpdateAccountNumber()%>" readonly>
								</div>
							</div>


							<div class="form-group row">
								<label for="address" class="col-sm-4 col-form-label title">營業資訊是否上架：</label>
								<div class="col-sm-6">
									<%
										String ReleaseToMarket = "";
										if (merchantVO.getIsReleaseToMarket() == true) {
											ReleaseToMarket = "是";
										} else if (merchantVO.getIsReleaseToMarket() != true) {
											ReleaseToMarket = "否";
										}
									%>
									<input type="text" class="form-control"
										name="isReleaseToMarket" value="<%=ReleaseToMarket%>" readonly>
								</div>
							</div>


							<div class="form-group row">
								<label for="admin" class="col-sm-4 col-form-label title">是否啟用：</label>

								<div class="col-sm-6 align-self-center">
									<input type="radio" class="form-check-inline" name="enable"
										value="false" id="no"
										<c:if test="<%=merchantVO.getIsEnable() == false%>">checked</c:if>>
									<label class="form-check-label mr-2" for="no">否</label> <input
										type="radio" class="form-check-inline" name="enable"
										value="true" id="yes"
										<c:if test="<%=merchantVO.getIsEnable() == true%>">checked</c:if>>
									<label class="form-check-label mr-2" for="yes">是</label>
								</div>
							</div>
							<div class="form-group row">
								<label for="admin" class="col-sm-4 col-form-label title">帳號狀態：</label>
								<div class="col-sm-6 align-self-center">
									<input type="radio" class="form-check-inline" name="status"
										value="0" id="fail"
										<c:if test="<%=merchantVO.getStatus() == 0%>">checked</c:if>>
									<label class="form-check-label mr-2" for="fail">不通過</label> <input
										type="radio" class="form-check-inline" name="status" value="1"
										id="wait"
										<c:if test="<%=merchantVO.getStatus() == 1%>">checked</c:if>>
									<label class="form-check-label mr-2" for="wait">待審核</label> <input
										type="radio" class="form-check-inline" name="status" value="2"
										id="pass"
										<c:if test="<%=merchantVO.getStatus() == 2%>">checked</c:if>>
									<label class="form-check-label" for="pass">通過</label>
								</div>
							</div>




							<div class="row justify-content-center">
								<button type="reset" class="btn btn-dark mt-3 mb-3 ml-3">重整</button>


								<button type="submit" class="btn btn-warning mt-3 mb-3 ml-3">會員資料儲存</button>
								<input type="hidden" name="action" value="MerchantUpdate"> 
								<input type="hidden" name="updateAdmin"value="${adminVO.accountNumber}">
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