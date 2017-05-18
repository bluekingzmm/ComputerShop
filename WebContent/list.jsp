<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>前台商品显示</title>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/form.css" rel="stylesheet" type="text/css" media="all" />
<link href="" rel="stylesheet" type="text/css" />
<link href="<c:url value="/css/button.css"/>" rel="stylesheet"
	type="text/css" />

<link href='http://fonts.googleapis.com/css?family=Exo+2'
	rel='stylesheet' type='text/css'>
<script src="js/jquery1.min.js"></script>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<!-- start menu -->
<link href="css/megamenu.css" rel="stylesheet" type="text/css"
	media="all" />
<script type="text/javascript" src="js/megamenu.js"></script>
<script>
	$(document).ready(function() {
		$(".megamenu").megamenu();
	});
</script>
<script type="text/javascript" src="js/jquery.jscrollpane.min.js"></script>
<script type="text/javascript" id="sourcecode">
	$(function() {
		$('.scroll-pane').jScrollPane();
	});
</script>
<style type="text/css">

/***************分页******************/
.fenye {
	float: left;
	margin-top: 10px;
}

.fenye ul {
	float: left;
	margin-left: 32px;
}

.fenye ul li {
	float: left;
	margin-left: 5px;
	padding: 4px 6px;
	border: 1px solid #ccc;
	font-weight: bold;
	cursor: pointer;
	color: #999;
}

.fenye ul li a {
	color: #999;
}

.fenye ul li.xifenye {
	width: 38px;
	text-align: center;
	float: left;
	position: relative;
	cursor: pointer;
}

.fenye ul li .xab {
	float: left;
	position: absolute;
	width: 39px;
	border: 1px solid #ccc;
	height: 123px;
	overflow-y: auto;
	overflow-x: hidden;
	top: -125px;
	background-color: #fff;
	display: inline;
	left: -1px;
	width: 50px;
}

.fenye ul li .xab ul {
	margin-left: 0;
	padding-bottom: 0;
}

.fenye ul li .xab ul li {
	border: 0;
	padding: 4px 0px;
	color: #999;
	width: 34px;
	margin-left: 0px;
	text-align: center;
}
</style>

<!-- <script type="text/javascript">
function sort(url) {
	if(url!=""){
		window.location.href='<c:url value="/ComputerAction?operate=listCom&${url}"/>';
	}
}

</script> -->
</head>
<%@include file="/nav/basion.jsp"%>
<body>
	<div class="mens">
		<div class="main">
			<div class="wrap">
				<div class="cont span_2_of_3">
					<h2 class="head">Computer</h2>
					<div class="mens-toolbar">
				<span style="color: blue;font-size:24px; ">人气特惠推荐</span>
				</div>


					<div class="section group">
						<h2 class="head" style="color: red;">商品列表</h2>

						<div class="section group"><c:forEach items="${comList }"
								var="com">
								<div class="col_1_of_3 span_1_of_3"
									style="border-left-style: solid; margin-left: 2%;"><br>
									<a
									href='<c:url value="/ComputerAction?operate=getProduct&id=${com.com_id }"></c:url>'>
										<div class="inner_content clearfix">
											<div class="product_image">&nbsp; <img
												src="<c:if test="${!empty com.imgPath }">
												<c:url value="/upload/${com.imgPath }"></c:url>
												</c:if>   
											<c:if test="${empty com.imgPath}">
												upload/default.jpg
												</c:if>  " />

										</div>
											<div class="sale-box1"><span class="on_sale title_shop">Sale</span></div>
											<div class="price">
												<div class="cart-left">
													<p class="title" style="text-align: center;">${com.brand.brand_name }</p>
													<ul style="text-align: center; color: #000;">
														<li>${com.cpu.cpu_name }</li>
														<li>屏幕尺寸：${com.screen.screen_name}</li>
														<li>电脑型号：${com.model}</li>
														<li>颜色：${com.color}</li>
														<li>像素：${com.pixels}</li>
														<li>内存：${com.ram}</li>
														<li>原价：<s>${com.price }</s></li>
														<li>售价：${com.rel_price }</li>

												</ul>

													<div class="price1"><span class="actual"></span></div>
											</div>
												<div class="cart-right"></div>
												<div class="clear"></div>
										</div>
									</div>
								</a></div>
							</c:forEach></div> <script src="js/jquery-1.7.2.js" type="text/javascript"></script>
						<script src="js/public.js" type="text/javascript"></script>
				</div>
			</div> <br> <br> <br> <br> <br> <br> <br>
				<br> <br> <br>

				<div class="rsidebar span_1_of_left" >
					<form
					action='<c:url value="/ComputerAction?operate=listCom"></c:url>'
					method="post">


						<h5 class="m_1">品牌</h5> <select class="dropdown" tabindex="8"
						name="brandName" data-settings='{"wrapperClass":"metro"}'>
							<option value="" selected="selected">==品牌==</option>
							<c:forEach items="${bList }" var="b">
								<option value="${b.brand_name}">${b.brand_name}</option>
							</c:forEach>
					</select> <section class="sky-form">
						<h4>处理器</h4>
						<select class="dropdown" tabindex="8" name="cpuName"
							data-settings='{"wrapperClass":"metro"}'>
							<option value="">==处理器==</option>
							<c:forEach items="${cList }" var="c">
								<option value="${c.cpu_name}">${c.cpu_name}</option>
							</c:forEach>
						</select> </section>
						<h5 class="m_1">颜色</h5> <input type="text" name="color" />
						<h5 class="m_1">屏幕尺寸</h5> <input type="text" name="screen" />
						<h5 class="m_1">价格</h5><input type="text" name="fPrice" style="width: 40%;" />- <input type="text" name="sPrice"style="width: 40%;"  /><br>
						 <br>
						 <span class="button-wrap"> <input
							class="button button-pill button-raised button-primary"
							type="submit" value="查询" />
					</span>
					
					
				</form>

			</div>
				<div class="wrap" style="margin-top: 300px; text-align: center;">

					<div class="fenye"><br>

						<ul>
							<li><a>共有${count }条记录，当前第${pageNo}/${totalPage}页</a></li>
							<li><c:if test="${pageNo!=1 }">
									<a
										href='<c:url value="/ComputerAction?operate=listCom&pageNo=1"></c:url>'>首页</a>
								</c:if> <c:if test="${pageNo==1 }">
									<a href="javascript:void(0);" style="color: #cfcfcf">首页</a>
								</c:if></li>
							<li><c:if test="${pageNo!=1 }">

									<a
										href="<c:url value="/ComputerAction?operate=listCom&pageNo=${pageNo<1?pageNo:pageNo-1}"></c:url>">上一页</a>

								</c:if> <c:if test="${pageNo==1 }">

									<a href="javascript:void(0);" style="color: #cfcfcf;">上一页</a>

								</c:if></li>
							<li><c:if test="${pageNo!=totalPage }">
									<a
										href="<c:url value="/ComputerAction?operate=listCom&pageNo=${pageNo>totalPage?pageNo:pageNo+1}"></c:url>">下一页</a>
								</c:if> <c:if test="${pageNo==totalPage }">
									<a href="javascript:void(0);" style="color: #cfcfcf;">下一页</a>
								</c:if></li>
							<li><c:if test="${pageNo!=totalPage }">
									<a
										href="<c:url value="/ComputerAction?operate=listCom&pageNo=${totalPage}"></c:url>">尾页</a>
								</c:if> <c:if test="${pageNo==totalPage }">

									<a href="javascript:void(0)" style="color: #cfcfcf;">尾页</a>

								</c:if></li>
					</ul></div>
			</div>
				<div class="clear"></div>

		</div>
	</div>
</div>
	<script src="js/jquery.easydropdown.js"></script>

	<div class="footer-top">
		<div class="wrap">
			<div class="section group example">
				<div class="col_1_of_2 span_1_of_2">
					<ul class="f-list">
						<li><img src="images/2.png"><span class="f-text">圆通包邮</span>
							<div class="clear"></div></li>
				</ul>
			</div>
				<div class="col_1_of_2 span_1_of_2">
					<ul class="f-list">
						<li><img src="images/3.png"><span class="f-text">联系我们
								222-555-6666 </span>
							<div class="clear"></div></li>
				</ul>
			</div>
				<div class="clear"></div>
		</div>
	</div>
</div>


	<div class="footer-bottom">
		<div class="wrap">
			<center><a href="sign-in.jsp" style="color: white;">web后台管理登录</a></center>
			<div class="clear"></div>
	</div>
</div>

	<div style="display: none"></div>
</body>
</html>