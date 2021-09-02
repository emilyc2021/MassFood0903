<%@page import="com.Admin.model.AdminVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.OrderMaster.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%  
List<OrderMasterVO> list = (List<OrderMasterVO>)session.getAttribute("list");
request.setAttribute("list", list);
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

<jsp:useBean id="consumerVO" scope="page"
	class="com.Consumer.model.ConsumerJDBCDAO" />
<jsp:useBean id="merchantVO" scope="page"
	class="com.Merchant.model.MerchantJDBCDAO" />
<jsp:useBean id="couriorVO" scope="page"
	class="com.Courior.model.CouriorJDBCDAO" />

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
<link rel="stylesheet" href="./css/admin_orderSearch.css">
<!-- JS檔 -->
<script src="./js/admin_orderSearch.js"></script>
<title>後台訂單查詢</title>
</head>

<body>
	<div>
		<!-- navbar -->
		<!-- 使用Boostrap Navbar -->
		<!-- navbar-expand-md在768px以下就出現漢堡式選單 -->
		<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
			<!-- 導覽列左上角的文字 -->
			<a class="navbar-brand" href="./admin_index.jsp"> <img src="./img/m.svg"
				width="30" height="30" class="d-inline-block align-top" alt="">
				<p style="margin-bottom: 0px;">MassFood! 後台管理</p>
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
							<ul class="collapse" id="collapseSet" style="padding-left: 0px">
								<li><a href="./admin_list.jsp">管理員列表</a></li>
								<li><a href="./admin_insert.jsp">新增管理員</a></li>
							</ul></li>
						<li><a href="./admin_registerSearch.jsp">查詢註冊表單</a></li>
						<li><a href="#" data-toggle="collapse"
							data-target="#collapseChat">會員申訴聊天室</a>
							<ul class="collapse" id="collapseChat" style="padding-left: 0px">
								<li><a href="<%=request.getContextPath()%>/chatRoom/MangerChatWithConsumer.jsp">消費者聊天室</a></li>
								<li><a href="<%=request.getContextPath()%>/chatRoom/MangerChatWithMerchant.jsp">商家聊天室</a></li>
								<li><a href="<%=request.getContextPath()%>/chatRoom/MangerChatWithCourior.jsp">外送員聊天室</a></li>
							</ul></li>
						<li><a href="#" data-toggle="collapse"
							data-target="#collapseSearch">資料調閱</a>
							<ul class="collapse" id="collapseSearch" style="padding-left: 0px">
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
						<h1>訂單查詢</h1>
						<form METHOD="post" ACTION="order.do">
							<div class="form-inline">
								<div class="form-group col-md-6">
									<label for="orderNum">訂單編號：</label> <input type="text"
										class="form-control" id="orderNum" name="OrderNumber" value="">
								</div>
								<div class="form-group col-md-6">
									<label for="orderState">訂單狀態：</label> <select id="orderState" name="Status"
										class="form-control">
										<option selected value="">----請選擇----</option>
										<option value="0">消費者送出訂單</option>
										<option value="1">商家收單</option>
										<option value="2">餐點已完成</option>
										<option value="3">外送員已接單</option>
										<option value="4">餐點到達通知取餐</option>
										<option value="5">待填寫評分及評論</option>
										<option value="6">訂單完成</option>
										<option value="98">消費者未取餐</option>
										<option value="99">取消訂單</option>
									</select>
								</div>
							</div>
							<div class="form-inline mt-3">
								<div class="form-group col-md-6">
									<label for="memberType">會員類型：</label> <select name="memberType"
										class="form-control">
								<option selected value="M1">消費者</option>
										<option value="M2">商家</option>
										<option value="M3">外送員</option>
									</select>
								</div>
								<div class="form-group col-md-6">
									<label for="memberNum">會員編號：</label> <input type="text"
										class="form-control" id="memberNum" name="AccountNumber" value="">
								</div>
							</div>
<!-- 							<div class="form-inline mt-3"> -->
<!-- 								<div class="form-group col-md-6">															 -->
<!-- 									<label for="pickType">取單方式：</label>  -->
<!-- 											<input class="form-check-input" type="radio" -->
<!-- 											name="IsNeedDelivery" id="pick" value="0"> <label -->
<!-- 											class="form-check-label mr-2" for="pick">自取</label>  -->
<!-- 											<input class="form-check-input" type="radio" name="IsNeedDelivery" -->
<!-- 											id="take" value="1"> <label -->
<!-- 											class="form-check-label mr-2" for="take">外帶</label> 				 -->
<!-- 								</div> -->
<!-- 							</div> -->
							<div class="form-inline mt-3">
								<div class="form-group col-md-10">
									<label for="orderDate">訂單日期：</label> <input type="date"
										class="form-control" name="Orderdate1" value=""> <span
										class="pl-3 pr-3"> ~ </span> <input type="date"
										class="form-control" name="Orderdate2" value="">
								</div>
								<button type="submit" class="col-md-1 btn btn-light btnsearch" >查詢</button>
							<input type="hidden" name="action" value="orderSearch">
							</div>
						</form>
						<hr style="border: 1px solid #DB7243; background-color: #DB7243;">
					</div>
				<%@ include file="pages/page3.file"%>
					<!-- TODO:DB查詢顯示表格 -->
					<div class="table-responsive-md mt-3">
						<table class="table table-hover table-bordered">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">訂單編號</th>
									<th scope="col">訂單日期</th>
									<th scope="col">訂單狀態</th>
									<th scope="col">消費者</th>
									<th scope="col">商家名稱</th>
									<th scope="col">外送員</th>
									<th scope="col">金額</th>
									<th scope="col">明細</th>
								</tr>

							</thead>
							<tbody>
								<%
									int i = 0;
								%>
											<c:forEach var="Order" items="${list}"  begin="<%=pageIndex%>"
													end="<%=pageIndex+rowsPerPage-1%>"> 
<%-- 								<c:forEach var="Order" items="${list}"> --%>
									<tr>
										<th scope="row"><%=++i%></th>
										<td>${Order.orderNumber }</td>										
													<td><fmt:formatDate value="${Order.orderDate}" pattern="yyyy-MM-dd" /></td>
										<td>
										<c:if test="${Order.status==0}"> 消費者送出訂單 </c:if>
										<c:if test="${Order.status==1}"> 商家收單 </c:if>
										<c:if test="${Order.status==2}"> 餐點已完成 </c:if>
										<c:if test="${Order.status==3}"> 外送員已接單 </c:if>
										<c:if test="${Order.status==4}"> 餐點到達通知取餐 </c:if>
										<c:if test="${Order.status==5}"> 待填寫評分及評論 </c:if>
										<c:if test="${Order.status==6}"> 訂單完成 </c:if>
										<c:if test="${Order.status==98}"> 消費者未取餐 </c:if>
										<c:if test="${Order.status==99}"> 取消訂單 </c:if>																	
										</td>
										<c:forEach var="m1" items="${consumerVO.all}">
                  <c:if test="${Order.consumerAccountNumber==m1.accountNumber}"><td>${m1.name}</td></c:if>
                </c:forEach>
										<c:forEach var="m2" items="${merchantVO.all}">
                  <c:if test="${Order.merchantAccountNumber==m2.accountNumber}"><td>${m2.name}</td></c:if>
                </c:forEach>
															<c:forEach var="m3" items="${couriorVO.all}">
                  <c:if test="${Order.couriorAccountNumber==m3.accountNumber}"><td>${m3.name}</td></c:if>
                </c:forEach>
										<td><fmt:formatNumber value="${Order.mealFee}" type="currency" pattern="$###,###" maxFractionDigits="0"/></td>
										<td><a href="order.do?action=orderList&orderNumber=${Order.orderNumber}"><i class="far fa-list-alt"></i></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
								<div class="form-inline mt-3 ">
							<div class="col-md-3 page1"></div>
							<div class="col-md-6 text-center">

								<%@ include file="pages/page2.file"%>
							</div>
							<div class="col-md-3 page1"></div>
						</div>
						
					</div>
				</main>	
				
			</div>
		</section>
		<!-- footer -->
		<footer id=" footer" class="text-white">
			<div class="footer-end bg-dark mt-4 py-2">
				<p class="text-center">&copy; 2021 All Rights Reserved. 版權所有
					不得轉載</p>
			</div>
		</footer>
</body>

</html>