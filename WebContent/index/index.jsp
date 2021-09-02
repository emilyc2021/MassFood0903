<%@page import="com.Emily.Model.PopresturantVO"%>
<%@page import="java.util.List"%>
<%@page import="com.Emily.Model.PopresturantDAO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions"%>

<%
	PopresturantDAO pop = new PopresturantDAO();
	List<PopresturantVO> list = pop.popular_restaurant();
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="merchantVO" scope="page"
	class="com.Merchant.model.MerchantJDBCDAO" />
<jsp:useBean id="systemParameterVO" scope="page"
	class="com.SystemParameter.model.SystemParameterJDBCDAO" />

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>Mass Food!</title>
<!-- 頁籤小icon -->
<link rel="icon shortcut" href="./img/favicon.ico">
<!-- Font Awesome -->
<script data-search-pseudo-elements defer
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js"
	crossorigin="anonymous"></script>
<!-- feather-icons -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.24.1/feather.min.js"
	crossorigin="anonymous"></script>
<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	crossorigin="anonymous"></script>
<!-- bootstrap -->
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"
	crossorigin="anonymous"></script>
<!-- CSS檔 -->
<link rel="stylesheet" href="./css/styles.css">
<link rel="stylesheet" href="./css/index.css">
<!-- js檔 -->
<!-- <script src="index.js"></script> -->
</head>
<body>
	<div id="layoutDefault">
		<div id="layoutDefault_content">
			<main>
				<!--               導覽列 -->
				<nav
					class="navbar navbar-marketing navbar-expand-lg bg-white navbar-light">
					<div class="container">

						<!-- 左邊LOGO -->
						<a class="navbar-brand text-logo" href="index.jsp"><img
							src="./img/logo.svg" width="30" height="30" alt="Logo">Mass
							Food!</img></a>
						<button class="navbar-toggler" type="button"
							data-toggle="collapse" data-target="#navbarSupportedContent"
							aria-controls="navbarSupportedContent" aria-expanded="false"
							aria-label="Toggle navigation">
							<i class="fas fa-bars"></i>
						</button>

						<!-- 各專區 -->
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<ul class="navbar-nav ml-auto mr-lg-5">
<!-- 								<li class="nav-item"><a class="nav-link" href="#">美食地圖 -->
<!-- 								</a></li> -->
								<li class="nav-item"><a class="nav-link" href="#">廠商專區
								</a></li>
								<li class="nav-item"><a class="nav-link" href="#">外送員專區
								</a></li>
								<!--                                 購物車icon -->
								<li class="nav-item"><a class="nav-link" href="#"><i
										class="fas fa-shopping-cart"></i></a>
								<li class="nav-item nav-item1"><a
									class="btn-teal btn rounded-pill px-4 ml-lg-4" href="#">Sign
										in</a></li>
							</ul>
						</div>
					</div>
				</nav>

				<!-- header -->
				<header class="page-header page-header-dark bg-img-cover overlay">
					<div class="page-header-content">
						<div class="container">
							<div class="row justify-content-center">
								<!--                                 搜尋列 -->
								<div class="col-xl-8 col-lg-10 ">
									<h1 class="page-header-title mb-1">今天想來點....</h1>
									<h3 class="page-header-text mb-3">Take a
										break，美食是生命裡最好的慰藉。</h3>
									<form class="page-header-signup">
										<div class="form-row ">
											<div class="col-lg-9 col-md-10">
												<div class="row">
<!-- 												縣市 -->
													<div class="col-md-6 mr-0 pr-0">
														<div class="form-group">
															<select class="form-control city" id="city"
																onchange="changeZip(this.selectedIndex)" >
															</select>
														</div>
													</div>
<!-- 													區 -->
													<div class="col-md-6 ml-0 pl-0">
														<div class="form-group">
															<select class="form-control zip" id="zip">
															</select>
														</div>
													</div>
												</div>



											</div>
											<div class="col-lg-3 col-md-2">
												<!--                                                 搜尋按鈕 -->
												<button
													class="btn btn-teal btn-block btn-marketing rounded-pill"
													type="submit">Find Food!</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
					<div class="svg-border-angled text-light">
						<!-- 火鍋背景斜角 -->
						<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 100"
							preserveAspectRatio="none" fill="currentColor">
                            <polygon points="0,100 100,0 100,100"></polygon>
                        </svg>
					</div>
				</header>
				<!-- 美食分類 -->
				<section class="bg-light py-8">
					<div class="container">
						<div class="row justify-content-center">
							<div class="col-lg-6">
								<div class="mb-5 text-center">
									<div class="text-l text-uppercase-expanded text-gray-dark mb-2">美食分類
									</div>
								</div>
							</div>
						</div>
						<!--          美食分類區 -->
						<div class="row justify-content-center">
<!-- TODO:連結 -->
							<div class="col-md-6 col-lg-4 col-xl-3 mb-5">
								<a class="card lift" href="#?name=foodType&value=1"><img class="card-img-top"
									src="./img/01brunch.jpg" alt="..." />
									<div class="card-body text-center py-3">
										<h6 class="card-title mb-0">早午餐</h6>
									</div> </a>
							</div>
							<div class="col-md-6 col-lg-4 col-xl-3 mb-5">
								<a class="card lift" href="#?name=foodType&value=2"><img class="card-img-top"
									src="./img/02hotpot.jpg" alt="..." />
									<div class="card-body text-center py-3">
										<h6 class="card-title mb-0">火鍋</h6>
									</div> </a>
							</div>
							<div class="col-md-6 col-lg-4 col-xl-3 mb-5">
								<a class="card lift" href="#?name=foodType&value=3"><img class="card-img-top"
									src="./img/03koreafood.jpg" alt="..." />
									<div class="card-body text-center py-3">
										<h6 class="card-title mb-0">韓式料理</h6>
									</div> </a>
							</div>
							<div class="col-md-6 col-lg-4 col-xl-3 mb-5">
								<a class="card lift" href="#?name=foodType&value=4"><img class="card-img-top"
									src="./img/04japanfood.jpg" alt="..." />
									<div class="card-body text-center py-3">
										<h6 class="card-title mb-0">日式料理</h6>
									</div> </a>
							</div>
							<div class="col-md-6 col-lg-4 col-xl-3 mb-5 mb-lg-0">
								<a class="card lift" href="#?name=foodType&value=5"><img class="card-img-top"
									src="./img/05hanburger.jpg" alt="..." />
									<div class="card-body text-center py-3">
										<h6 class="card-title mb-0">美式料理</h6>
									</div> </a>
							</div>
							<div class="col-md-6 col-lg-4 col-xl-3 mb-5 mb-lg-0">
								<a class="card lift" href="#?name=foodType&value=6"><img class="card-img-top"
									src="./img/06pizza.jpg" alt="..." />
									<div class="card-body text-center py-3">
										<h6 class="card-title mb-0">義式料理</h6>
									</div> </a>
							</div>
							<div class="col-md-6 col-lg-4 col-xl-3 mb-5 mb-md-0">
								<a class="card lift" href="#?name=foodType&value=7"><img class="card-img-top"
									src="./img/07taiwanfood.jpg" alt="..." />
									<div class="card-body text-center py-3">
										<h6 class="card-title mb-0">台式料理</h6>
									</div> </a>
							</div>
							<div class="col-md-6 col-lg-4 col-xl-3">
								<a class="card lift" href="#?name=foodType&value=8"><img class="card-img-top"
									src="./img/08dessert.jpg" alt="..." />
									<div class="card-body text-center py-3">
										<h6 class="card-title mb-0">甜點 & 飲料</h6>
									</div> </a>
							</div>
						</div>
					</div>




					<!-- 美食分類背景斜框 -->
					<div class="svg-border-angled text-white">
						<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 100"
							preserveAspectRatio="none" fill="currentColor">
                            <polygon points="0,100 100,0 100,100"></polygon>
                        </svg>
					</div>
				</section>
				<!-- 熱門餐廳 -->
				<section class="bg-white pt-5 pb-10">
					<div class="container">
						<div class="row justify-content-center">
							<div class="col-lg-6">
								<div class="mb-5 text-center">
									<div class="text-l text-uppercase-expanded text-gray-dark mb-2">熱門餐廳
									</div>
								</div>
							</div>
						</div>
						<!-- 輪播 -->
						<div id="carouselExampleControls" class="carousel slide"
							data-ride="carousel">
							<div class="carousel-inner">
								<!-- 輪播第一頁 -->
								<div class="carousel-item active">
									<div class="container">
										<div class="row">
											<c:forEach var="pop" items="${list}" begin="0" end="2">
												<div class="col-lg-4 mb-5 mb-lg-0">
													<a class="card lift" href="#?accountNumber=${pop.accountNumber}"><img
														class="card-img-top"
														src="<c:forEach var="m2" items="${merchantVO.all}">
                  <c:if test="${pop.accountNumber==m2.accountNumber}">${m2.picture}</c:if></c:forEach>"
														height="190px" alt="..." />
														<div class="card-body text-center py-3">
															<h6 class="card-title mb-2">
																<c:forEach var="m2" items="${merchantVO.all}">
																	<c:if test="${pop.accountNumber==m2.accountNumber}">${m2.name}</c:if>
																</c:forEach>
															</h6>
															<div class="text-yellow">商家評分：${pop.totalAverage}
<%-- 																                                        <c:choose> --%>
<%-- 																                                        <c:when test="${pop.totalAverage==5 }"><i class="fas fa-star"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i></c:when> --%>
<%-- 																                                        <c:when test="${pop.totalAverage>=4.5 }"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star-half-alt"></i></c:when> --%>
<%-- 																                                        <c:when test="${pop.totalAverage>=4 }"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i></c:when> --%>
<%-- 																                                         <c:when test="${pop.totalAverage>=3.5 }"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star-half-alt"></i></c:when> --%>
<%-- 																                                        <c:when test="${pop.totalAverage>=3 }"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i></c:when> --%>
<%-- 																                                        <c:when test="${pop.totalAverage>=2.5 }"><i class="fas fa-star"></i><i class="fas fa-star"><i class="fas fa-star-half-alt"></i></c:when> --%>
<%-- 																                                        <c:when test="${pop.totalAverage>=2 }"><i class="fas fa-star"></i><i class="fas fa-star"></c:when> --%>
<%-- 																                                        <c:when test="${pop.totalAverage>=1.5 }"><i class="fas fa-star"><i class="fas fa-star-half-alt"></i></c:when> --%>
<%-- 																                                        <c:when test="${pop.totalAverage>=1 }"><i class="fas fa-star"></c:when> --%>
<%-- 																                                        <c:when test="${pop.totalAverage>=0.5 }"><i class="fas fa-star-half-alt"></i></c:when> --%>
<%-- 																                                        <c:otherwise><i class="far fa-star"></i></c:otherwise>  --%>
<%-- 																                                        </c:choose> --%>
															</div>
														</div>
														<div class="card-footer text-center text-xs">
															<c:forEach var="m2" items="${merchantVO.all}">
																<c:if test="${pop.accountNumber==m2.accountNumber}">
																
																<c:forEach var="sys" items="${systemParameterVO.all}">
																<c:if test="${m2.zipCode==sys.code}">
																 ${sys.description}
																</c:if>						
																</c:forEach>
																
																</c:if>
															</c:forEach>
														</div> </a>
												</div>
											</c:forEach>
										</div>
									</div>
								</div>
								<!-- 輪播第二頁 -->
								<div class="carousel-item">
									<div class="container">
										<div class="row">
											<c:forEach var="pop" items="${list}" begin="3">
												<div class="col-lg-4 mb-5 mb-lg-0">
													<a class="card lift" href="#?accountNumber=${pop.accountNumber}"><img
														class="card-img-top"
														src="<c:forEach var="m2" items="${merchantVO.all}">
                  <c:if test="${pop.accountNumber==m2.accountNumber}">${m2.picture}</c:if></c:forEach>"
														height="190px" alt="..." />
														<div class="card-body text-center py-3">
															<h6 class="card-title mb-2">
																<c:forEach var="m2" items="${merchantVO.all}">
																	<c:if test="${pop.accountNumber==m2.accountNumber}">${m2.name}</c:if>
																</c:forEach>
															</h6>

															<div class="text-yellow">商家評分：${pop.totalAverage}
<%-- 															    <c:choose> --%>
<%-- 																                                        <c:when test="${pop.totalAverage==5 }"><i class="fas fa-star"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i></c:when> --%>
<%-- 																                                        <c:when test="${pop.totalAverage>=4.5 }"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star-half-alt"></i></c:when> --%>
<%-- 																                                        <c:when test="${pop.totalAverage>=4 }"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i></c:when> --%>
<%-- 																                                         <c:when test="${pop.totalAverage>=3.5 }"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star-half-alt"></i></c:when> --%>
<%-- 																                                        <c:when test="${pop.totalAverage>=3 }"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i></c:when> --%>
<%-- 																                                        <c:when test="${pop.totalAverage>=2.5 }"><i class="fas fa-star"></i><i class="fas fa-star"><i class="fas fa-star-half-alt"></i></c:when> --%>
<%-- 																                                        <c:when test="${pop.totalAverage>=2 }"><i class="fas fa-star"></i><i class="fas fa-star"></c:when> --%>
<%-- 																                                        <c:when test="${pop.totalAverage>=1.5 }"><i class="fas fa-star"><i class="fas fa-star-half-alt"></i></c:when> --%>
<%-- 																                                        <c:when test="${pop.totalAverage>=1 }"><i class="fas fa-star"></c:when> --%>
<%-- 																                                        <c:when test="${pop.totalAverage>=0.5 }"><i class="fas fa-star-half-alt"></i></c:when> --%>
<%-- 																                                        <c:otherwise><i class="far fa-star"></i></c:otherwise>  --%>
<%-- 																                                        </c:choose> --%>
															
															</div>
														</div>
														
														
														<div class="card-footer text-center text-xs">
															<c:forEach var="m2" items="${merchantVO.all}">
																<c:if test="${pop.accountNumber==m2.accountNumber}">
																<c:forEach var="sys" items="${systemParameterVO.all}">
																<c:if test="${m2.zipCode==sys.code}">
																 ${sys.description}
																</c:if>						
																</c:forEach>
																</c:if>
															</c:forEach>
														</div> </a>
												</div>
											</c:forEach>

										</div>
									</div>
								</div>
							</div>
							<a class="carousel-control-prev" href="#carouselExampleControls"
								role="button" data-slide="prev"> <span
								class="carousel-control-prev-icon" aria-hidden="true"></span> <span
								class="sr-only">Previous</span>
							</a> <a class="carousel-control-next" href="#carouselExampleControls"
								role="button" data-slide="next"> <span
								class="carousel-control-next-icon" aria-hidden="true"></span> <span
								class="sr-only">Next</span>
							</a>
						</div>









						<!-- 熱門餐廳背景斜框 -->
						<div class="svg-border-angled text-dark">
							<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 100"
								preserveAspectRatio="none" fill="currentColor">
                            <polygon points="0,100 100,0 100,100"></polygon>
                        </svg>
						</div>
				</section>
			</main>
		</div>
		<!-- footer -->
		<div id="layoutDefault_footer">
			<footer class="footer pt-8 pb-4 mt-auto bg-dark footer-dark">
				<div class="container">
					<div class="row">
						<div class="col-lg-3">
							<div class="footer-brand mb-3">Mass Food! 美食埠</div>
							<div class="mb-3">聯絡資訊</div>
							<div class="mb-3">電話：(02)2712-0589</div>
							<div class="icon-list-social mb-5">
								<a class="icon-list-social-link" href="javascript:void(0);"><i
									class="fab fa-instagram"></i></a><a class="icon-list-social-link"
									href="javascript:void(0);"><i class="fab fa-facebook"></i></a><a
									class="icon-list-social-link" href="javascript:void(0);"><i
									class="fab fa-twitter"></i></a>
							</div>
						</div>
						<div class="col-lg-9">
							<div class="row">
								<iframe class=""
									src="
                                        https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3614.467687045302!2d121.54106421405311!3d25.052132843720397!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3442abddfb12794f%3A0x71c40951a2fc40fe!2zMTA0OTHlj7DljJfluILkuK3lsbHljYDljZfkuqzmnbHot6_kuInmrrUyMTnomZ8!5e0!3m2!1szh-TW!2stw!4v1625838507243!5m2!1szh-TW!2stw"
									width="800" height="200" style="border: 0;" allowfullscreen=""
									loading="lazy"></iframe>


							</div>
						</div>
					</div>
					<hr class="my-1 mt-2" />
					<div class="row align-items-center">
						<div class="col-md-6 small pt-2 text-md-right">2021&copy;
							Mass Food! All Rights Reserved.</div>
					</div>
			</footer>
		</div>
	</div>
	    <script>
            //選擇縣市
            var city = ['台北市', '新北市'];
            var citycode = ['A', 'F'];
            var citySelect = document.getElementById("city");
            var inner = "";
            for (var i = 0; i < city.length; i++) {
                inner = inner + '<option value='+citycode[i]+'>' + city[i] + '</option>';
            }
            citySelect.innerHTML = inner;
            //放該縣市郵遞區號
            var sectors = new Array();
            sectors[0] = ['中正區', '大同區', '中山區', '松山區', '大安區', '萬華區', '信義區', '士林區', '北投區', '內湖區', '南港區', '文山區'];
            sectors[1] = ['板橋區', '中和區', '永和區', '土城區', '樹林區', '三峽區', '鶯歌區', '三重區', '蘆洲區', '新莊區', '五股區', '泰山區', '林口區', '八里區', '淡水區', '三芝區', '石門區', '金山區', '萬里區', '汐止區', '瑞芳區', '平溪區', '雙溪區', '貢寮區', '新店區', '深坑區', '石碇區', '坪林區', '烏來區'];
            var zipcode = new Array();
            zipcode[0]=['A.100', 'A.103', 'A.104', 'A.105', 'A.106', 'A.108', 'A.110', 'A.111', 'A.112', 'A.114', 'A.115', 'A.116'];
            zipcode[1] = ['F.220', 'F.235', 'F.234', 'F.236', 'F.238', 'F.237', 'F.239', 'F.241', 'F.247', 'F.242', 'F.248', 'F.243', 'F.244','F.249', 'F.251', 'F.252', 'F.253', 'F.208', 'F.207', 'F.221', 'F.224', 'F.226', 'F.227', 'F.228', 'F.231', 'F.222', 'F.223', 'F.232', 'F.233'];
            function changeZip(index) {
                var Sinner = "";
                for (var i = 0; i < sectors[index].length; i++) {
                    Sinner = Sinner + '<option value='+zipcode[index][i]+'>' + sectors[index][i] + '</option>';
                }
                var zipSelect = document.getElementById("zip");
                zipSelect.innerHTML = Sinner;
            }
            changeZip(document.getElementById("city").selectedIndex);
        </script>
</body>
</html>