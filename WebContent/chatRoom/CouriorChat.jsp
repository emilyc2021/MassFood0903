<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ChatRoom.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	ChatRoomJDBCDAO dao = new ChatRoomJDBCDAO();	
	List<ChatRoomVO> list = dao.getAll();
	List<ChatRoomVO> conversationList = dao.conversationList();
	List<ChatRoomVO> findMessageContent = dao.findMessageContent(1,8);
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("conversationList", conversationList);
	pageContext.setAttribute("findMessageContent", findMessageContent);
%>

<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>外送員專區</title>
<link href="<%=request.getContextPath()%>/tfa010WebPageRelativeData/css/Customize.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/tfa010WebPageRelativeData/css/Styles.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/tfa010WebPageRelativeData/css/ConsumerChatRoom.css"
	rel="stylesheet" />

<link rel="icon" type="image/x-icon" href="#" />
<script data-search-pseudo-elements defer
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.24.1/feather.min.js"
	crossorigin="anonymous"></script>


<!-- google Noto Sans TC 字型 -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@600&display=swap"
	rel="stylesheet">


<!-- 載入Bootstrap相關JS (若套用最新版boostrap css會跑版) -->
<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>

<!-- 載入多選清單 -->
<link href="<%=request.getContextPath()%>/vendors/css/bootstrap-multiselect.css" rel="stylesheet"
	type="text/css">
<link href="<%=request.getContextPath()%>/vendors/css/example-styles.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/vendors/js/jquery.multi-select.js"></script>
<script src="<%=request.getContextPath()%>/vendors/js/jquery.multi-select.min.js"></script>

</head>

<body>
	<div id="layoutDefault">
		<div id="layoutDefault_content ">
			<main>
				<nav
					class="navbar navbar-marketing navbar-expand-lg bg-white navbar-light">
					<!-- 若要讓navBar定在最上方要加navbar sticky-top -->
					<div class="container" id="navbarcontainer">
						<a class="navbar-brand text-primary" href="#">Mass Food!</a>
						<button class="navbar-toggler" type="button"
							data-toggle="collapse" data-target="#navbarSupportedContent"
							aria-controls="navbarSupportedContent" aria-expanded="false"
							aria-label="Toggle navigation">
							<i data-feather="menu"></i>
						</button>
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<ul class="navbar-nav ml-auto mr-lg-5">
								<li class="nav-item"><a class="nav-link" id="nav-link"
									href="#">美食專區</a></li>
								<li class="nav-item dropdown dropdown-lg no-caret"><a
									class="nav-link dropdown-toggle" id="navbarDropdownPages"
									href="#" role="button" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false"
									style="font-size: 1.15em;">外送員專區</a> <i class="dropdown-arrow"></i>
									</a>
									<div
										class="dropdown-menu dropdown-menu-right animated--fade-in-up"
										aria-labelledby="navbarDropdownPages">
										<div class=" row no-gutters">
											<div class="col-lg-6 p-lg-5">
												<h6 class="dropdown-header text-primary">基本資料</h6>
												<a class="dropdown-item" href="#">會員資料修改</a>
												<div class="dropdown-divider border-0"></div>
												<h6 class="dropdown-header text-primary">訂單管理</h6>
												<a class="dropdown-item" href="#">今日訂單</a> <a
													class="dropdown-item" href="#">訂單查詢</a>
												<div class="dropdown-divider border-0 d-lg-none"></div>
											</div>
											<div class="col-lg-6 p-lg-5">
												<h6 class="dropdown-header text-primary">收益查詢</h6>
												<a class="dropdown-item" href="#">收益表單</a>
												<div class="dropdown-divider border-0"></div>
												<h6 class="dropdown-header text-primary">申訴聊天室</h6>
												<a class="dropdown-item" href="<%=request.getContextPath()%>/CouriorChat.jsp">聯絡客服</a>
											</div>
										</div>
									</div></li>
							</ul>
							<a class="btn-primary btn rounded-pill px-4 ml-lg-4" href="#">登出<i
								class="fas fa-arrow-right ml-1"></i></a>
						</div>
					</div>
				</nav>

				<!-- header中店家圖片 -->
				<header
					class="page-header page-header-dark bg-img-cover overlay overlay-60"
					style="background-image: url(https://source.unsplash.com/xZgvBXDB9wE/1600x900);">
					<div class="page-header-content">
						<div class="container text-center">
							<div class="row justify-content-center">
								<div class="col-lg-8">
									<h1 class="page-header-title mb-3 text-light">外送員專區</h1>
									<p class="page-header-text mb-0">Browse posts in the
										category 'UI'</p>
								</div>
							</div>
						</div>
					</div>
				</header>

				<!-- AsideAndMain區塊 -->
				<div class="container" id="AsideAndMain">
					<div class="row align-items-start">

						<!-- Aside區塊 -->
						<div class="col-lg-3" id="aside">
							<ul class="cart">
								<li><a href="#">基本資料</a>
									<ul>
										<li><a href="#">會員資料修改</a></li>
									</ul></li>
								<li><a href="#">訂單管理</a>
									<ul>
										<li><a href="#">今日訂單</a></li>
										<li><a href="#">歷史訂單</a></li>
									</ul></li>
								<li><a href="#">收益查詢</a>
									<ul>
										<li><a href="#">收益表單</a></li>
									</ul></li>
								<li><a href="#">申訴聊天室</a>
									<ul>
										<li><a href="<%=request.getContextPath()%>/CouriorChat.jsp">聯絡客服</a></li>
									</ul></li>
							</ul>
						</div>

						<!-- Main區塊 -->
						<div class="col-lg-9" id="main" style="height: 900px;">
							<div class="chat" id="chat-container">
								<div class="chat-header clearfix" id="chat-title">
									<div class="chat-about">
										<div class="chat-with">
											<span>MassFood客服人員</span>
										</div>
									</div>
								</div>

								<!-- end chat-header -->

								<div class="chat-history" id="chat-message-list">
									<c:forEach var="ChatRoomVO" items="${findMessageContent}">
										<ul>
											<c:if test="${ChatRoomVO.messageType == 18}">
												<li class="clearfix other-message">
													<div class="message other-message float-right">${ChatRoomVO.messageContent}</div>
													<div class="message-data align-right">
														<span class="message-data-time"><fmt:formatDate
																value="${ChatRoomVO.dateTime}"
																pattern="yyyy-MM-dd HH:mm" /></span>
													</div>

												</li>
											</c:if>
										</ul>
										<ul>

											<c:if test="${ChatRoomVO.messageType == 81}">
												<li class="clearfix you-message">
													<div class="message other-message float-right">${ChatRoomVO.messageContent}</div>
													<div class="message-data align-right">
														<span class="message-data-time"><fmt:formatDate
																value="${ChatRoomVO.dateTime}"
																pattern="yyyy-MM-dd HH:mm" /></span>
													</div>
												</li>
											</c:if>
										</ul>
									</c:forEach>
								</div>
								<!-- end chat-history -->

								<div class="chat-message clearfix" id="chat-form">
									<input type="text" id="message-to-send"
										placeholder="Type your message"> <input type="hidden"
										name="action" value="courior-insert">
									<button class="chat-submit" id="sendmessage">送出訊息</button>
								</div>
								<!-- end chat-message -->
							</div>
						</div>
<!-- Main區塊 -->

					</div>
				</div>
		</div>



		</main>
	</div>

	<!-- Footer -->
	<div id="layoutDefault_footer">
		<footer class="footer pt-10 pb-5 mt-auto bg-dark footer-dark">
			<div class="container">
				<div class="row">
					<span id="contactService"> <a class="col-md-12">聯絡客服</a>
					</span>
				</div>
				<div class="row">
					<div class="col-md-12" style="color: #DFDFDF;">&copy; 2021
						All Rights Reserved. 版權所有 不得轉載</div>
				</div>
			</div>
		</footer>
	</div>
	</div>

	<!-- 載入套版JS navBar -->
	<!-- 4.3.1/js/bootstrap.bundle.min.js fisrt,, then script.js  -->

	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/tfa010WebPageRelativeData/js/scripts.js"></script>
	<script src="<%=request.getContextPath()%>/tfa010WebPageRelativeData/js/Customize.js"></script>
	<script>
	
	$().ready(function() {
		$('body').on('click', "#sendmessage", function() {
			getmessage();
			//alert(getmessage());				
			window.location.reload(); 
		})
	});
	function getmessage() {
		var result = jQuery.ajax({
			url : "<%=request.getContextPath()%>/ChatRoomServlet",//傳送數值之頁面
				type : "POST",
				async : false,
				data : {
					messageContent : $('#message-to-send').val(),
					action : "courior-insert"
				},
				dataType : 'text',//接收的資料型態
				success : function(returnMsg) {
					//alert("成功");
				}
			});
		}
	</script>


</body>

</html>