<%@page import="com.Admin.model.AdminVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ChatRoom.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	ChatRoomJDBCDAO dao = new ChatRoomJDBCDAO();
	List<ChatRoomVO> list = dao.getAll();
	List<ChatRoomVO> conversationList = dao.conversationList();
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("conversationList", conversationList);
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
<html>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 頁籤小icon -->
<link rel="icon shortcut"
	href="<%=request.getContextPath()%>/tfa010WebPageRelativeData/img/favicon.ico">
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
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
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
<link href="<%=request.getContextPath()%>/tfa010WebPageRelativeData/css/admin_chatRoom.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/tfa010WebPageRelativeData/css/ManagerChatRoom.css"
	rel="stylesheet" />

<script data-search-pseudo-elements defer
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.24.1/feather.min.js"
	crossorigin="anonymous"></script>

<title>後台聊天室</title>
</head>

<body>
<!-- 管理員帳號 -->
<input type="hidden" name="admin"value="${adminVO.accountNumber}">
	<div>
		<!-- navbar -->
		<!-- 使用Boostrap Navbar -->
		<!-- navbar-expand-md在768px以下就出現漢堡式選單 -->
		<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
			<!-- 導覽列左上角的文字 -->
			<a class="navbar-brand" href="<%=request.getContextPath()%>/admin/admin_index.jsp"> <img
				src="<%=request.getContextPath()%>/tfa010WebPageRelativeData/img/m.svg" width="30" height="30"
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
								<form method="POST" action="<%=request.getContextPath()%>/admin/adm.do2">
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
								<li><a href="<%=request.getContextPath()%>/admin/admin_list.jsp">管理員列表</a></li>
								<li><a href="<%=request.getContextPath()%>/admin/admin_insert.jsp">新增管理員</a></li>
							</ul></li>
						<li><a href="<%=request.getContextPath()%>/admin/admin_registerSearch.jsp">查詢註冊表單</a></li>
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
								<li><a href="<%=request.getContextPath()%>/admin/admin_memberSearch.jsp">會員查詢</a></li>
								<li><a href="<%=request.getContextPath()%>/admin/admin_orderSearch.jsp">訂單查詢</a></li>
								<li><a href="<%=request.getContextPath()%>/admin/admin_reportSearch.jsp">報表查詢</a></li>
							</ul></li>
					</ul>
				</div>
				<!--聊天室-->
				<div class="col-lg-10" style="height: 900px;">
					<div id="chat-container" class="chat-container">
						<div id="search-container">
							<!--<input type="text" placeholder="Search" /> -->
						</div>
						<!-- 消費者清單 Start-->
						<div id="conversation-list">
							<c:forEach var="ChatRoomVO" items="${conversationList}">
								<c:if test="${ChatRoomVO.messageType == 41}">
									<div class="conversation"
										id="${ChatRoomVO.senderAccountNumber}">
										<img src="<%=request.getContextPath()%>/tfa010WebPageRelativeData/img/Male.png"
											alt="Test Men" />
										<div id="sender" class="title-text">${ChatRoomVO.name}</div>
										<div class="created-date">
											<fmt:formatDate value="${ChatRoomVO.dateTime}"
												pattern="yyyy-MM-dd" />
										</div>
										<div class="conversation-message">
											${ChatRoomVO.messageContent}</div>
									</div>
								</c:if>
							</c:forEach>
						</div>

						<!-- 消費者清單 End-->
						<div id="new-message-container"></div>
						<div id="chat-title">
							<span id="clickSender"></span>
						</div>

						<div class="chat-history" id="chat-message-list"></div>

						<!-- end chat-history -->

						<div class="chat-message clearfix" id="chat-form">
							<input type="text" id="message-to-send"
								placeholder="Type your message"> <input type="hidden"
								name="action" value="consumer-response">
							<button class="chat-submit" id="sendmessage">送出訊息</button>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- section end-->
		<!-- footer -->
		<footer id=" footer" class="text-white">
			<div class="footer-end bg-dark mt-4 py-2">
				<p class="text-center">&copy; 2021 All Rights Reserved. 版權所有不得轉載</p>
			</div>
		</footer>
		<!-- footer end-->

		<script src="https://code.jquery.com/jquery-3.4.1.min.js"
			crossorigin="anonymous"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"
			crossorigin="anonymous"></script>
		<script src="<%=request.getContextPath()%>/tfa010WebPageRelativeData/js/scripts.js"></script>
		<script src="<%=request.getContextPath()%>/tfa010WebPageRelativeData/js/Customize.js"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>

		<script>
			$('.conversation').on('click', function() {
				let strSender = this.id;				
				$('#clickSender').html(strSender);
				
				var result = jQuery.ajax({
					url : "./getConsumerHistoryChatMessage.jsp",//傳送數值之頁面
					type : "POST",
					async : false,
					data : {
						sender : strSender,
						actionForAjax : "findMessageContent",
					},
					dataType : 'text',//接收的資料型態
					success : function(strHTML) {
						$('.chat-history').html(strHTML);
					}
				}).responseText;
			})
		</script>

		<script>
		
		$().ready(function () {
			$('body').on('click', "#sendmessage", function () {
				//alert("test");
				getmessage();
// 				window.location.reload();
			})
				
		});

		function getmessage() {
			var result = jQuery.ajax({
				url: "<%=request.getContextPath()%>/ChatRoomServlet",//傳送數值之頁面
					type : "POST",
					async : false,
					data : {
						receiverAccountNumber : $('#receiverAccountNumber')
								.val(),
						messageContent : $('#message-to-send').val(),
						action : "consumer-response"
					},
					dataType : 'text',//接收的資料型態
					success : function() {
						//
						let elementTargetDivs = document
								.querySelectorAll('.conversation');

						for (let i = 0; i < elementTargetDivs.length; i++) {
							let elemtntTargetDiv = elementTargetDivs[i];
							if (elemtntTargetDiv.getAttribute('id') == $(
									'#receiverAccountNumber').val()) {
								elemtntTargetDiv.click();
								return;
							}
						}
					}
				})
			}
		</script>
</body>
</html>