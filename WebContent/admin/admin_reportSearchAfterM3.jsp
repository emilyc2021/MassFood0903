<%@page import="com.Admin.model.AdminVO"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.Emily.Model.ReportVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%List<ReportVO> orderOK = (List<ReportVO>)request.getAttribute("orderOK");%>
<%List<ReportVO> orderList = (List<ReportVO>)request.getAttribute("orderList");%>
<%-- <%List<ReportVO> orderCancel = (List<ReportVO>)request.getAttribute("orderCancel");%> --%>
<% String orderMonth=request.getParameter("reportdate1"); %>
<%
// 登入session
if(session.getAttribute("login")==null){
	RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/admin_login.html");
	dispatcher.forward(request, response);	
}
AdminVO adminVO=(AdminVO) request.getSession().getAttribute("login");
pageContext.setAttribute("adminVO", adminVO);
%>
<jsp:useBean id="merchantVO" scope="page"
	class="com.Merchant.model.MerchantJDBCDAO" />
<jsp:useBean id="couriorVO" scope="page"
	class="com.Courior.model.CouriorJDBCDAO" />
<jsp:useBean id="orderDetailVO" scope="page"
	class="com.OrderDetail.model.OrderDetailJDBCDAO" />
<jsp:useBean id="systemParameterVO" scope="page"
	class="com.SystemParameter.model.SystemParameterJDBCDAO" />
	<jsp:useBean id="orderMasterVO" scope="page"
	class="com.OrderMaster.model.OrderMasterJDBCDAO" />

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
<link rel="stylesheet" href="./css/admin_reportSearch.css">
<!-- JS檔 -->
<script src="./js/admin_reportSearch.js"></script>
<title>後台報表查詢</title>
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
			<div class="row align-items-start" height="500">
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
						<h1>報表查詢</h1>
						<form METHOD="post" ACTION="report.do">
							<div class="form-inline">
								<div class="form-group col-md-6">
									<label for="memberType">會員類型：</label> <select name="memberType"
										class="form-control">
										<option selected value="M2">商家</option>
										<option value="M3">外送員</option>
									</select>
								</div>
								<div class="form-group col-md-6">
									<label for="memberNum">會員編號：</label> <input type="text"
										class="form-control" id="memberNum" name="AccountNumber"
										value="" required>
								</div>

							</div>
							<div class="form-inline mt-3">
								<div class="form-group col-md-10">
									<label for="reportDate">報表月份：</label> <input type="month"
										class="form-control" name="reportdate1" value="" required> 
<!-- 										<span 	class="pl-3 pr-3"> ~ </span> -->
<!-- 										 <input type="month" 	class="form-control" name="reportdate2" value="" required> -->
								</div>
								<button type="submit" class="col-md-1 btn btn-light btnsearch">查詢</button>
								<input type="hidden" name="action" value="reportSerach">
							</div>
						</form>
						<hr style="border: 1px solid #DB7243; background-color: #DB7243;">
					</div>
<!-- 					查詢結果 -->
					         <div class="table-responsive-md mt-3">
                        <table class="table table-hover table-bordered">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">會員編號</th>
                                    <th scope="col">外送員姓名</th>
                                    <th scope="col">訂單月份</th>
                                    <th scope="col">訂單筆數</th>
                                    <th scope="col">應付帳款</th>
                                    <th scope="col">明細</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<%
									int i = 0;
								%>
								<c:forEach var="report" items="${orderList}"> 
								<c:if test="${report.couriorAccountNumber==0}">   <th scope="row"  colspan="7">本月無外送紀錄</th></c:if>
								    <c:if test="${report.couriorAccountNumber!=0}">
                                <tr>
                                    <th scope="row"><%=++i%></th>
                                    <td>${report.couriorAccountNumber}</td>
                                    <c:forEach var="m3" items="${couriorVO.all}">
                  <c:if test="${report.couriorAccountNumber==m3.accountNumber}"><td>${m3.name}</td></c:if>
                </c:forEach>
                                    <td><%=orderMonth %></td>
                                    <td>${report.count }筆</td>
                                    
                                    <td><fmt:formatNumber value="${report.payableaccount}" type="currency" maxFractionDigits="0"/></td>
                                    <td><button type="submit" data-toggle="modal" data-target="#detailModal" class="btn btn-outline-primary btn-sm">對帳單</button></td>
                                </tr>
                                </c:if>
                               </c:forEach>
                            </tbody>
                        </table>
                    </div>					
				</main>
			</div>
		</section>
	<!-- Modal -->
    <div class="modal fade" id="detailModal" tabindex="-1" role="dialog" aria-labelledby="modelTitleId"
        aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title title d-flex" ><img style="margin-right:  1rem"src="./img/logo.svg" width="30" height="30" alt="Logo">
                    Mass Food! 外送員對帳單</img></h5>
                    <button type="button" class="close col-1" style="margin-left: 0" data-dismiss="modal" aria-label="Close">
                        <span  aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <dl class="row">
                    <c:forEach var="list" items="${orderList}"> 
                        <dt class="col-sm-6 mb-2"><h2 class="titleh2">&#9826會員帳號：<span class="titlespan1 ml-1"><c:forEach var="m3" items="${couriorVO.all}">
                  <c:if test="${list.couriorAccountNumber==m3.accountNumber}">${m3.email}</c:if>
                </c:forEach></span></h2></dt>
                        <dd class="col-sm-6"><h2><span></span></h2></dd>
                        <dt class="col-sm-6 mb-2"><h2 class="titleh2">&#9826會員名稱：<span class="titlespan1 ml-1"><c:forEach var="m3" items="${couriorVO.all}">
                  <c:if test="${list.couriorAccountNumber==m3.accountNumber}">${m3.name}</c:if>
                </c:forEach></span></h2></dt>
                        <dd class="col-sm-6"><h2 style="color: #5B352C;">會員帳戶資訊<span></span></h2></dd>
                        <dt class="col-sm-6 mb-2"><h2 class="titleh2">&#9826訂單月份：<span class="titlespan1 ml-2"><%=orderMonth %></span></h2></dt>
                        <dd class="col-sm-6"><h2 style="color: #5B352C;">&#9830銀行代碼：<span><c:forEach var="m3" items="${couriorVO.all}">
                  <c:if test="${list.couriorAccountNumber==m3.accountNumber}">${m3.bankCode}</c:if>
                </c:forEach></span></h2></dd>
                        <dt class="col-sm-6 mb-2"><h2 class="titleh2">&#9826訂單筆數：<span class="titlespan1 ml-2">${list.count }筆</span></h2></dt>
                        <dd class="col-sm-6"><h2 style="color: #5B352C;">&#9830銀行帳號：<span><c:forEach var="m3" items="${couriorVO.all}">
                  <c:if test="${list.couriorAccountNumber==m3.accountNumber}">${m3.bankAccount}</c:if>
                </c:forEach></span></h2></dd>
                        <dt class="col-sm-6 "><h2 class="titleh2">&#9826應付金額：<span class="titlespan1 ml-2"><fmt:formatNumber value="${list.payableaccount}" type="currency" maxFractionDigits="0"/></span></h2></dt>
                        <dd class="col-sm-6"><h2 style="color: #5B352C;">&#9830銀行戶名：<span><c:forEach var="m3" items="${couriorVO.all}">
                  <c:if test="${list.couriorAccountNumber==m3.accountNumber}">${m3.bankAccountName}</c:if>
                </c:forEach></span></h2></dd>
                    </dl>
                    </c:forEach>
                   <hr style="border: 1px solid #DFDFDF; background-color: #DFDFDF;">                  
                  
                    <h5 class="titleh5">訂單完成列表</h5>
                    <table class="table table-sm table-bordered table-hover">
                        <thead>
                            <tr class="table-info ">
                                <th scope="col" width="37px">#</th>
                                <th scope="col" width="98px">訂單編號</th>
                                <th scope="col" width="108px">訂單日期</th>
                                <th scope="col" width="98px">商家名稱</th>
                                <th scope="col" width="98px">商家區域</th>
                                <th scope="col" width="98px">外送費</th>
                                <th scope="col" width="227px">備註</th>
                            </tr>
                        </thead>
                        <tbody>
                        <% int j=0; %>
                          <c:forEach var="OK" items="${orderOK}"> 
                            <tr>
                           
                                <th scope="row"><%=++j %></th>
                                <td class="edit">${OK.orderNumber }</td>
                                <td class="edit"><fmt:formatDate value="${OK.orderDate}" pattern="yyyy-MM-dd" /></td>
                                <td class="edit"><c:forEach var="m2" items="${merchantVO.all}">
                  <c:if test="${OK.merchantAccountNumber==m2.accountNumber}">${m2.name}</c:if>
                </c:forEach></td>
                                <td class="edit"><c:forEach var="m2" items="${merchantVO.all}">
                  <c:if test="${OK.merchantAccountNumber==m2.accountNumber}"><c:forEach var="sys" items="${systemParameterVO.all}">
																<c:if test="${m2.zipCode==sys.code}">
																 ${sys.description}
																</c:if>						
																</c:forEach></c:if>
                </c:forEach></td>
                                <td class="edit2"><fmt:formatNumber value="${OK.deliverFee }" type="currency" pattern="$###,###" maxFractionDigits="0"/></td>
                                <td class="edit1">外送地址：
                                <c:forEach var="om" items="${orderMasterVO.all}">
																<c:if test="${OK.orderNumber==om.orderNumber}">
																<c:set var="string" value="${om.deliveryAddress}" />
																<c:set var="string1" value="${fn:substring(string, 0, 10)}" />
																 ${string1}
																</c:if>						
																</c:forEach>
                                </td>
                            </tr>
                            </c:forEach>
                              <tr class="table-info ">
                                <th scope="col"></th>
                                <td scope="col"></th>
                                <td scope="col"></th>
                                <td scope="col"></th>
                                <td scope="col"></th>
                                <td scope="col"></th>
                                <td scope="col"></th>
                            </tr>
                        </tbody>
                    </table>
                    <hr style="border: 1px solid #DFDFDF; background-color: #DFDFDF;">
               
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <!-- <button type="button" class="btn btn-primary">Save</button> -->
                </div>
            </div>
        </div>
    </div>	
				
		<!-- footer -->
		<footer id=" footer" class="text-white">
			<div class="footer-end bg-dark mt-4 py-2">
				<p class="text-center">&copy; 2021 All Rights Reserved. 版權所有
					不得轉載</p>
			</div>
		</footer>
</body>
</html>