<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>前台单个商品显示</title>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/form.css" rel="stylesheet" type="text/css" media="all" />
<link href='http://fonts.googleapis.com/css?family=Exo+2'
	rel='stylesheet' type='text/css'>
<script src="js/jquery1.min.js"></script>
<!-- start menu -->
<link href="css/megamenu.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="<c:url value="/css/button.css"/>" rel="stylesheet"
	type="text/css" />

<script type="text/javascript" src="js/megamenu.js"></script>
<script>
	$(document).ready(function() {
		$(".megamenu").megamenu();
	});
</script>
<link href="css/base.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery.jqzoom.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script type="text/javascript" src="js/jquery.jscrollpane.min.js"></script>
<script type="text/javascript" src="js/jquery.flexisel.js"></script>



<script type="text/javascript">
	function gwc() {
		alert("请登录！！");
	}
</script>


</head>
<%@include file="/nav/basion.jsp"%>

<body>

	<div class="mens">
		<div class="main">
			<div class="wrap">

				<div class="cont span_2_of_3">
					<div class="grid images_3_of_2">
						<div id="container">
							<div id="products_example">
								<div id="products">

									<div class="slides_container"
									style="overflow: hidden; position: relative; display: block; width: 430px; height: 430px;">

										<img alt="图片"
										src='<c:if test="${!empty com.imgPath }">
										<c:url value="/upload/${com.imgPath }"></c:url>
										
										
										</c:if>  <c:if test="${empty com.imgPath }">
										/upload/default.jpg
										</c:if>'>
								</div>

							</div>
						</div>
					</div>
				</div>
					<div class="desc1 span_3_of_2">
						<h3 class="m_3" style="text-align: center; font-size: 28px;">${com.brand.brand_name }</h3>
						<p class="m_5" style="color: red; text-align: center;">现价：${com.rel_price }&nbsp;&nbsp;&nbsp;<span
							class="reducedfrom">原价：${com.price }</span>
					</p>
						<div class="btn_form">

							<p class="m_text2" style="text-align: center; font-size: 18px;">型号：${com.model}&nbsp;<br>处理器：${com.cpu.cpu_name }&nbsp;<br>像素：${com.pixels }&nbsp;<br>内存：${com.ram }&nbsp;<br>屏幕尺寸：${com.screen.screen_name }&nbsp;<br>颜色：${com.color }&nbsp;<br>库存：${com.num }件
						</p> &nbsp;<br> 
						
						<c:if test="${!empty sessionScope.userInfo }">

								<c:if test="${com.num>0 }">
									<a
										class="button button-glow button-rounded button-raised button-primary"
										href="<c:url value="/ShopCartAction?operate=add&id=${com.com_id}"></c:url>">加入购物车</a>
								</c:if>
								
								<c:if test="${com.num<=0 }">
								<a href="#"
									class="button button-glow button-rounded button-raised button-primary">库存不够了，不能购买</a>
							</c:if>
								</c:if>
								
								<c:if test="${empty sessionScope.userInfo }">
<c:if test="${com.num>0}">
									<a href="#" onclick="gwc();"
										class="button button-glow button-rounded button-raised button-primary">加入购物车</a>
						</c:if>		
							 <c:if test="${com.num<=0 }">
								<a href="#"
									class="button button-glow button-rounded button-raised button-primary">库存不够了，不能购买</a>
							</c:if></c:if>
					</div>
				</div>
					<div class="right-extra" style="margin: 100px;">
						<!--产品参数开始-->
						<div></div> <!--产品参数结束-->
						<div style="clear: both; height: 10px;"></div>
						<div class="m" id="comment">
							<ul class="tab clearfix">
								<li onclick="tabs('#comment',0)" class="curr">商品评价<strong></strong><span></span></li>
						</ul>
							<div class="mc tabcon hide" style="display: none;">

								<div class="item odd">${com.content }</div>
						</div>
					</div>
				</div>

			</div> <script src="js/jquery.easydropdown.js"></script>
		</div>
			<div class="clear"></div>
	</div>
		<div class="clear"></div>
</div>

	<div class="footer-top">
		<div class="wrap">
			<div class="section group example">
				<div class="col_1_of_2 span_1_of_2">
					<ul class="f-list">
						<li><img src="<c:url value="/images/2.png"></c:url>"><span
							class="f-text">圆通包邮</span>
							<div class="clear"></div></li>
				</ul>
			</div>
				<div class="col_1_of_2 span_1_of_2">
					<ul class="f-list">
						<li><img src="<c:url value="/images/3.png"></c:url>"><span
							class="f-text">联系我们 222-555-6666 </span>
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