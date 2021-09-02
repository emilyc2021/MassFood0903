<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.Friend.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	FriendJDBCDAO dao = new FriendJDBCDAO();
	List<FriendVO> list = dao.getAll();
	pageContext.setAttribute("list", list);
	FriendVO friendVO = (FriendVO) request.getAttribute("friendVO");//ConsumerServlet.java(Concroller), 存入req的ConsumerVO物件
%>


<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-s
    cale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>消費者專區</title>
</head>

<link href="<%=request.getContextPath()%>/tfa010WebPageRelativeData/css/Customize.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/tfa010WebPageRelativeData/css/Styles.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/tfa010WebPageRelativeData/css/FriendList.css"
	rel="stylesheet" />
<link rel="icon" type="image/x-icon" href="#" />
<script data-search-pseudo-elements defer
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.24.1/feather.min.js"
	crossorigin="anonymous">
</script>


<body>
	<div id="layoutDefault">
		<div id="layoutDefault_content">
			<main>
				<nav
					class="navbar navbar-marketing navbar-expand-lg bg-white navbar-light">
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
									style="font-size: 1.15em;">消費者專區</a> <i class="dropdown-arrow"></i>
									</a>
									<div
										class="dropdown-menu dropdown-menu-right animated--fade-in-up"
										aria-labelledby="navbarDropdownPages">
										<div class=" row no-gutters">
											<div class="col-lg-6 p-lg-5">
												<h6 class="dropdown-header text-primary">消費者資料</h6>
												<a class="dropdown-item" href="#">基本資料</a>
												<div class="dropdown-divider border-0"></div>
												<h6 class="dropdown-header text-primary">訂單管理</h6>
												<a class="dropdown-item" href="#">今日訂單</a> <a
													class="dropdown-item" href="#">訂單查詢</a>
												<div class="dropdown-divider border-0 d-lg-none"></div>
											</div>
											<div class="col-lg-6 p-lg-5">
												<h6 class="dropdown-header text-primary">好友系統</h6>
												<a class="dropdown-item" href="#">好友列表</a>
												<div class="dropdown-divider border-0"></div>
												<h6 class="dropdown-header text-primary">申訴聊天室</h6>
												<a class="dropdown-item" href="#">聯絡客服</a>
											</div>
										</div>
									</div></li>
								<li class="nav-item"><a class="nav-link" id="nav-link"
									href="#">購物車</a></li>
							</ul>
							<a class="btn-primary btn rounded-pill px-4 ml-lg-4" href="#">登出<i
								class="fas fa-arrow-right ml-1"></i></a>
						</div>
					</div>
				</nav>
				<header
					class="page-header page-header-dark bg-img-cover overlay overlay-60"
					style="background-image: url(https://source.unsplash.com/xZgvBXDB9wE/1600x900);">
					<div class="page-header-content">
						<div class="container text-center">
							<div class="row justify-content-center">
								<div class="col-lg-8">
									<h1 class="page-header-title mb-3 text-light">消費者專區</h1>
									<p class="page-header-text mb-0">Browse posts in the
										category 'UI'</p>
								</div>
							</div>
						</div>
					</div>
				</header>

				<div class="container" id="AsideAndMain">
					<div class="row align-items-start">
						<div class="col-lg-3" id="aside" style="height: 900px;">
							<ul class="cart">
								<li><a href="#">消費者資料</a>
									<ul>
										<li><a href="#">基本資料</a></li>
									</ul></li>
								<li><a href="#">訂單管理</a>
									<ul>
										<li><a href="#">今日訂單</a></li>
										<li><a href="#">歷史訂單</a></li>
									</ul></li>
								<li><a href="#">好友系統</a>
									<ul>
										<li><a href="#">好友列表</a></li>
									</ul></li>
								<li><a href="#">申訴聊天室</a>
									<ul>
										<li><a
											href='<%=request.getContextPath()%>/Consumerchat.jsp'>聯絡客服</a></li>
									</ul></li>
							</ul>
						</div>
						<div class="col-lg-9" id="main" style="height: 900px;">
							<div class="container py-5 text-center">
								<div id="friend-title">
									我的好友清單
									<button type="button" class="friend-Add" data-toggle="modal"
										data-target="#modalAdd">+加入好友</button>
								</div>
								<!-- Modal-->
								<div class="modal fade" id="modalAdd" data-backdrop="static"
									data-keyboard="false">
									<div class="modal-dialog modal-bulletin" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h4>請輸入E-mail進行搜尋</h4>
											</div>

											<div class="modal-body">

												<b>E-mail:</b> <input type="text" id="ThisEmail"
													name="email"> <input type="hidden" name="action"
													value="insert"> <input type="button"
													id="btnQueryEmail" value="送出">

											</div>

											<div class="modal-footer">
												<button type="button" class="btn btn-secondary" id="close"
													data-dismiss="modal">Close</button>
											</div>

										</div>
									</div>
								</div>
							</div>
							<div id="friend-list">
								<table class="tg">
									<thead>
										<tr>
											<td>姓名</td>
											<td>E-mail</td>
											<td>狀態</td>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="FriendVO" items="${list}">
											<tr>
												<td>${FriendVO.name}</td>
												<td>${FriendVO.email}</td>

												<td><c:choose>
														<c:when test="${FriendVO.status == 1}">
															<FORM METHOD="post"
																ACTION="<%=request.getContextPath()%>/FriendServlet"
																style="margin-bottom: 0px;">
																<input type="submit" value="解除好友關係"> <input
																	type="hidden" name="deleteAccountNumber"
																	value="${FriendVO.accountNumber}"> <input
																	type="hidden" name="deletefriendAccountNumber"
																	value="${FriendVO.friendAccountNumber}"> <input
																	type="hidden" name="action" value="delete">
															</FORM>
														</c:when>

														<c:when test="${FriendVO.status == null}">
															<FORM METHOD="post"
																ACTION="<%=request.getContextPath()%>/FriendServlet"
																style="margin-bottom: 0px;">
																<input type="submit" value="同意好友邀請"> <input
																	type="hidden" name="agreeAccountNumber"
																	value="${FriendVO.accountNumber}"> <input
																	type="hidden" name="agreefriendAccountNumber"
																	value="${FriendVO.friendAccountNumber}"><input
																	type="hidden" name="action" value="update">
															</FORM>
														</c:when>

														<c:otherwise>
															<c:forEach var="omEntry" items="${omMap}">
																<c:if test="${FriendVO.status == omEntry.key}">${omEntry.value}</c:if>
															</c:forEach>
														</c:otherwise>

													</c:choose></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
		</div>
	</div>
	</main>
	</div>
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
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/tfa010WebPageRelativeData/js/scripts.js"></script>
	<script src="<%=request.getContextPath()%>/tfa010WebPageRelativeData/js/Customize.js"></script>
	<script>
		$("#basicModal").modal({
			show : true
		});

		$().ready(function() {
			$('body').on('click', "#btnQueryEmail", function() {
				getExplain();
			})
		});

		$().ready(function() {
			$('body').on('click', "#close", function() {
				window.location.reload();
			})
		});
		
		
		//讀取Explain
		function getExplain() {
			var result = jQuery.ajax({
				url : "<%=request.getContextPath()%>/FriendServlet",//傳送數值之頁面
				type : "POST",
				async : false,
				data : {
					email : $('#ThisEmail').val(),
					action : "insert"
				},
				dataType : 'text',//接收的資料型態
				success : function(returnMsg) {
					alert(returnMsg)
				}
			});
		}
	</script>

</body>

</html>
