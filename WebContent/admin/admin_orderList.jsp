<%@page import="com.Admin.model.AdminVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.OrderMaster.model.OrderMasterVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	OrderMasterVO orderVO = (OrderMasterVO) request.getAttribute("orderList");
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
<jsp:useBean id="orderDetailVO" scope="page"
	class="com.OrderDetail.model.OrderDetailJDBCDAO" />
<jsp:useBean id="commentVO" scope="page"
	class="com.Comment.model.CommentJDBCDAO" />


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
<link rel="stylesheet" href="./css/admin_orderList.css">
<!-- JS檔 -->
<script src="./js/admin_orderList.js"></script>
<title>後台訂單明細</title>
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
			<div class="row align-items-start">
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
					<!--訂單 -->
					<main class="col-lg-10 main">
						<%--                 <%=orderVO.getOrderNumber()%> --%>
						<%--                 ${orderList.orderNumber} --%>
						<h1 class="title">
							訂單編號：<span> ${orderList.orderNumber}</span>
						</h1>
						<!-- 消費者資訊 -->
						<div class="row pl100 pr-5 pt-3">

							<p class="col-sm-6">
								訂單日期：<span>${orderList.orderDate}</span>
							</p>
							<p class="col-sm-6">
								訂單狀態：<span>${orderList.status}</span>
							</p>

							<p class="col-sm-6 pt-3">
								送餐日期時段：<span>${orderList.deliverDateTime}</span>
							</p>
<!-- 							<p class="col-sm-6 pt-3"> -->
<%-- 								取餐方式：<span>${orderList.isNeedDelivery}</span> --%>
<!-- 							</p> -->


							<p class="col-sm-12 pt-3">
								訂購人編號：<span>${orderList.consumerAccountNumber}</span>
							</p>
							<p class="col-sm-6 pt-3">
								訂購人姓名：<span><c:forEach var="m1" items="${consumerVO.all}">
										<c:if
											test="${orderList.consumerAccountNumber==m1.accountNumber}">${m1.name}</c:if>
									</c:forEach></span>
							</p>
							<p class="col-sm-6 pt-3">
								訂購人電話：<span><c:forEach var="m1" items="${consumerVO.all}">
										<c:if
											test="${orderList.consumerAccountNumber==m1.accountNumber}">${m1.mobilePhone}</c:if>
									</c:forEach></span>
							</p>

							<p class="col-sm-6 pt-3">
								用餐人編號：<span>${orderList.diningAccountNumber}</span>
							</p>
							<p class="col-sm-6 pt-3">
								是否為好友送餐：<span>${orderList.isSocial}</span>
							</p>
							<p class="col-sm-6 pt-3">
								餐點送達聯絡人：<span>${orderList.recipientName}</span>
							</p>
							<p class="col-sm-6 pt-3">
								餐點送達聯絡人電話：<span>${orderList.recipientPhoneNumber}</span>
							</p>
							<p class="col-sm-12 pt-3">
								外送地點：<span>${orderList.deliveryAddress}</span>
							</p>
						</div>
						<hr class="w-75"
							style="border: 1px solid #F3AD34; background-color: #F3AD34;">
						<!-- 外送員資訊 -->
						<div class="row pl100 pr-5">
							<p class="col-sm-12">
								外送員編號：<span>${orderList.couriorAccountNumber}</span>
							</p>
							<p class="col-sm-6 pt-3">
								外送員姓名：<span><c:forEach var="m3" items="${couriorVO.all}">
										<c:if
											test="${orderList.couriorAccountNumber==m3.accountNumber}">${m3.name}</c:if>
									</c:forEach></span>
							</p>
							<p class="col-sm-6 pt-3">
								外送員電話：<span><c:forEach var="m3" items="${couriorVO.all}">
										<c:if
											test="${orderList.couriorAccountNumber==m3.accountNumber}">${m3.mobilePhone}</c:if>
									</c:forEach></span>
							</p>
						</div>
						<hr class="w-75"
							style="border: 1px solid #F3AD34; background-color: #F3AD34;">
						<!-- 商家資訊 -->
						<div class="row pl100 pr-5">
							<p class="col-sm-12">
								商家編號：<span>${orderList.merchantAccountNumber}</span>
							</p>
							<p class="col-sm-12 pt-3">
								商家名稱：<span><c:forEach var="m2" items="${merchantVO.all}">
										<c:if
											test="${orderList.merchantAccountNumber==m2.accountNumber}">${m2.name}</c:if>
									</c:forEach></span>
							</p>
							<p class="col-sm-6 pt-3">
								商家聯絡人：<span><c:forEach var="m2" items="${merchantVO.all}">
										<c:if
											test="${orderList.merchantAccountNumber==m2.accountNumber}">${m2.contactPersonName}</c:if>
									</c:forEach></span>
							</p>
							<p class="col-sm-6 pt-3">
								商家電話：<span><c:forEach var="m2" items="${merchantVO.all}">
										<c:if
											test="${orderList.merchantAccountNumber==m2.accountNumber}">${m2.landlinePhone}</c:if>
									</c:forEach></span>
							</p>
							<p class="col-sm-12 pt-3">
								商家地址：<span><c:forEach var="m2" items="${merchantVO.all}">
										<c:if
											test="${orderList.merchantAccountNumber==m2.accountNumber}">${m2.address}</c:if>
									</c:forEach></span>
							</p>
						</div>
						<!-- 訂單明細 -->
						<%
							int i = 0, j = 0;
						%>

						<div class="row pl100 pr-5">
							<p class="col-sm-12 pt-3 ptitle">訂單品項</p>

							<c:forEach var="od" items="${orderDetailVO.all}">
								<c:if test="${orderList.orderNumber==od.orderNumber}">
									<div class="row col-sm-11 listborder">
										<p class="col-sm-1 ptitle"><%=++i%></p>
										<p class="col-sm-8 pt-2">
											品項名稱：<span>${od.itemName}</span>
										</p>
										<p class="col-sm-3 pt-2">
											品項金額：<span>${od.itemPrice}</span>元
										</p>
										<p class="col-sm-1 ptitle"></p>
										<p class="col-sm-8">
											備註：<span>${od.memo}</span>
										</p>
										<p class="col-sm-3">
											數量：<span>${od.itemAmount}</span>
										</p>
									</div>
								</c:if>
							</c:forEach>
							<!-- 小計 -->
							<div class="row col-sm-11 count">
								<p class="col-sm-12 pt-3">
									小計：<span>${orderList.mealFee}</span>元
								</p>
								<p class="col-sm-12 pt-2">
									外送服務費：<span>${orderList.deliverFee}</span>元
								</p>
								<p class="col-sm-12 pt-2">
									總金額：<span>${orderList.mealFee+orderList.deliverFee}</span>元
								</p>
							</div>
						</div>
						<hr class="w-75"
							style="border: 1px solid #F3AD34; background-color: #F3AD34;">
						<!-- 訂單備註 -->
						<div class="row pl100 pr-5">
							<p class="col-sm-12 ptitle">訂單備註</p>
							<div class="row col-sm-11 listborder pb-2">
								<p class="col-sm-12 mt10" style="line-height: 22px">${orderList.memo}</p>
							</div>
						</div>
						<!-- 訂單評分 -->
						<div class="row pl100 pr-5">
							<p class="col-sm-12 ptitle">訂單評分</p>
							<div class="row col-sm-11 listborder">
								<c:forEach var="od" items="${orderDetailVO.all}">
									<c:if test="${orderList.orderNumber==od.orderNumber}">
										<p class="col-sm-1 ptitle"><%=++j%></p>
										<!--                             EL如果是null 會顯示0 -->
										<p class="col-sm-11 mt10">${od.itemName}：<span style="color: #FFCB15;"> 
										<c:choose>
													<c:when test="${od.grade== 0}">
														<i class="far fa-star"></i>
													</c:when>
													<c:when test="${od.grade==1}">
														<i class="fas fa-star"></i>
													</c:when>
													<c:when test="${od.grade==2}">
														<i class="fas fa-star"></i>
														<i class="fas fa-star"></i>
													</c:when>
													<c:when test="${od.grade==3}">
														<i class="fas fa-star"></i>
														<i class="fas fa-star"></i>
														<i class="fas fa-star"></i>
													</c:when>
													<c:when test="${od.grade==4}">
														<i class="fas fa-star"></i>
														<i class="fas fa-star"></i>
														<i class="fas fa-star"></i>
														<i class="fas fa-star"></i>
													</c:when>
													<c:when test="${od.grade==5}">
														<i class="fas fa-star"></i>
														<i class="fas fa-star"></i>
														<i class="fas fa-star"></i>
														<i class="fas fa-star"></i>
														<i class="fas fa-star"></i>
													</c:when>
												</c:choose>
											</span>
										</p>
									</c:if>
								</c:forEach>
							</div>
						</div>
						<!-- 訂單評論 -->
						<div class="row pl100 pr-5">
							<p class="col-sm-12 ptitle">訂單評論</p>
							<div class="row col-sm-11 listborder pb-2 mb-5">
								<p class="col-sm-12 mt10" style="line-height: 22px">
									<c:forEach var="comment" items="${commentVO.all}">
										<c:if test="${orderList.orderNumber==comment.orderNumber}">${comment.content }</c:if>
									</c:forEach>
								</p>
							</div>
						</div>
						<!--                     <div class="row justify-content-center"> -->
						<!--                         <button type="button" class="btn btn-warning mt-3 mb-3">修改</button> -->
						<!--                     </div> -->

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