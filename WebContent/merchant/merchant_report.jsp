<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>商家專區</title>
    <link href="css/styles.css" rel="stylesheet" />
    <link href="css/Customize.css" rel="stylesheet" />
    <link rel="icon" type="image/x-icon" href="#" />
    <script data-search-pseudo-elements defer
        src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.24.1/feather.min.js"
        crossorigin="anonymous"></script>

    <!-- google Noto Sans TC 字型 -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@600&display=swap" rel="stylesheet">

    <!-- 載入Bootstrap相關JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    

</head>

<body>
    <div id="layoutDefault">
        <div id="layoutDefault_content ">
            <main>
                      <nav class="navbar navbar-marketing navbar-expand-lg bg-white navbar-light">
                    <!-- 若要讓navBar定在最上方要加navbar sticky-top -->
                    <div class="container" id="navbarcontainer">
                        <a class="navbar-brand text-primary" href="#">Mass Food!</a><button class="navbar-toggler"
                            type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                            aria-controls="navbarSupportedContent" aria-expanded="false"
                            aria-label="Toggle navigation"><i data-feather="menu"></i></button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav ml-auto mr-lg-5">
                                <li class="nav-item"><a class="nav-link" id="nav-link" href="#">美食專區</a></li>
                                <li class="nav-item dropdown dropdown-lg no-caret">
                                    <a class="nav-link dropdown-toggle" id="navbarDropdownPages" href="#" role="button"
                                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                                        style="font-size:1.15em; ;">廠商專區</a>
                                    <i class="dropdown-arrow"></i>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right animated--fade-in-up"
                                        aria-labelledby="navbarDropdownPages">
                                        <div class=" row no-gutters">
                                            <div class="col-lg-6 p-lg-5">
                                                <h6 class="dropdown-header text-primary">商家資訊</h6>
                                                <a class="dropdown-item" href="#">會員資料修改</a>
                                                <a class="dropdown-item" href="#">營業資訊</a>
                                                <div class="dropdown-divider border-0"></div>
                                                <h6 class="dropdown-header text-primary">刊登商品</h6>
                                                <a class="dropdown-item" href="#">商品增修</a>
                                                <div class="dropdown-divider border-0"></div>
                                                <h6 class="dropdown-header text-primary">訂單管理</h6>
                                                <a class="dropdown-item" href="#">今日訂單</a>
                                                <a class="dropdown-item" href="#">歷史訂單</a>
                                                <div class="dropdown-divider border-0"></div>
                                            </div>
                                            <div class="col-lg-6 p-lg-5">
                                                <h6 class="dropdown-header text-primary">報表查詢</h6>
                                                <a class="dropdown-item" href="#">營收報表</a>
                                                <div class="dropdown-divider border-0"></div>
                                                <h6 class="dropdown-header text-primary">評論回覆</h6>
                                                <a class="dropdown-item" href="#">評論回覆</a>
                                                <div class="dropdown-divider border-0"></div>
                                                <h6 class="dropdown-header text-primary">申訴聊天室</h6>
                                                <a class="dropdown-item" href="#">聯絡客服</a>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                            <a class="btn-primary btn rounded-pill px-4 ml-lg-4" href="#">登出<i
                                    class="fas fa-arrow-right ml-1"></i></a>
                        </div>
                    </div>
                </nav>

                <!-- header中店家圖片 -->
                <header class="page-header page-header-dark bg-img-cover overlay overlay-60"
                    style="background-image: url(https://source.unsplash.com/xZgvBXDB9wE/1600x900);">
                    <div class="page-header-content">
                        <div class="container text-center">
                            <div class="row justify-content-center">
                                <div class="col-lg-8">
                                    <h1 class="page-header-title mb-3 text-light">商家專區</h1>
<!--                                     <p class="page-header-text mb-0">Browse posts in the category 'UI'</p> -->
                                </div>
                            </div>
                        </div>
                    </div>
                </header>

                <!-- AsideAndMain區塊 -->
     <div class="container" id="AsideAndMain">
                    <div class="row align-items-start">

                        <!-- Aside區塊 -->
                        <div class="col-lg-2" id="aside">
                            <ul class="cart">
                                <li>
                                    <a href="#">商家資訊</a>
                                    <ul>
                                        <li><a href="#">會員資料修改</a></li>
                                        <li><a href="#">營業資訊</a></li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#">刊登商品</a>
                                    <ul>
                                        <li><a href="#">商品增修</a></li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#">訂單管理</a>
                                    <ul>
                                        <li><a href="#">今日訂單</a></li>
                                        <li><a href="#">歷史訂單</a></li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#">報表查詢</a>
                                    <ul>
                                        <li><a href="#">營收報表</a></li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#">評論回覆</a>
                                    <ul>
                                        <li><a href="#">評論回覆</a></li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#">申訴聊天室</a>
                                    <ul>
                                        <li><a href="#">聯絡客服</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>

                        <!-- Main區塊 -->
                        <div class="col-lg-10" id="main">
                            <div class=MainTitle>
                                <p>商家報表資訊</p>
                            </div>
                            <div class="infoSection">
                                <form METHOD="post" ACTION="merchant.do">
							<div class="form-inline mt-3">
								<div class="form-group col-md-8">
									<label for="reportDate">報表月份：</label> <input type="month"
										class="form-control" name="reportdate1" value="" required> 
								</div>
								
								<button type="submit" class="col-md-2 btn btn-light btnsearch">查詢</button>
								<input type="hidden" name="action" value="reportMerchant">
								<input type="hidden" name="account" value="1">
							</div>
                                   

                                </form>
                                <hr style="border: 1px solid #EDE1D1; background-color: #EDE1D1;">
                                
                               <div style="height:100px;">
                               </div> 
                                         <!-- 頁籤 -->
<!--                     <nav> -->
<!--                         <div class="nav nav-tabs" id="nav-tab" role="tablist"> -->
<!--                             <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-chart1" -->
<!--                                 role="tab" aria-controls="nav-home" aria-selected="true">月營收報表</a> -->
<!--                             <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-chart2" -->
<!--                                 role="tab" aria-controls="nav-profile" aria-selected="false">熱門餐點TOP5</a> -->
<!--                         </div> -->
<!--                     </nav> -->
<!--                     <div class="tab-content mb-2" id="nav-tabContent"> -->
                        
<!--                         <div class="tab-pane fade show active m-auto" id="nav-chart1" role="tabpanel" -->
<!--                             aria-labelledby="nav-home-tab"><canvas id="myChart1"></canvas></div> -->
                        
<!--                         <div class="tab-pane fade m-auto" id="nav-chart2" role="tabpanel" -->
<!--                             aria-labelledby="nav-profile-tab" style="position: relative;width:400px"> -->
<!--                             <canvas id="myChart2"></canvas> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
                                
                                
                            </div>
                        </div>
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
                    <span id="contactService">
                        <a class="col-md-12">聯絡客服</a>
                    </span>
                </div>
                <div class="row">
                    <div class="col-md-12" style="color: #DFDFDF;">&copy; 2021 All Rights Reserved. 版權所有 不得轉載</div>
                </div>
            </div>
        </footer>
    </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>


    <script
        src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.min.js"></script>


    <!-- 載入套版JS -->
    <script src="js/scripts.js"></script>

    <!-- 載入自訂js -->
    <script src="js/Customize.js"></script>
    
    <!-- 銀行信用卡到期 Monthpicker -->
    <script src='http://code.jquery.com/ui/1.10.3/jquery-ui.js'></script>
    <script src='./vendor/js/jquery.ui.monthpicker.js'></script>

<!-- 報表chart.js+js檔 -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="js/report.js"></script>



</body>

</html>