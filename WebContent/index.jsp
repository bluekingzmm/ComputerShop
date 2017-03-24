<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>电脑商城</title>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/form.css" rel="stylesheet" type="text/css" media="all" />
<link href='http://fonts.googleapis.com/css?family=Exo+2'
	rel='stylesheet' type='text/css'>
<script type="text/javascript" src="js/jquery1.min.js"></script>
<!-- start menu -->
<link href="css/megamenu.css" rel="stylesheet" type="text/css"
	media="all" />
<script type="text/javascript" src="js/megamenu.js"></script>
<script>
	$(document).ready(function() {
		$(".megamenu").megamenu();
	});
</script>
<link type="text/css" rel="stylesheet"
	href="/ComputerShop/css/alertstyle.css" />
<script type="text/javascript"
	src="<c:url value="/js/jquery-1.6.2.min.js"/>"></script>
<script type="text/javascript"
	src="/ComputerShop/js/jquery-1.8.3.min.js"></script>
<!--start slider -->
<link rel="stylesheet" href="css/fwslider.css" media="all">
<script src="js/jquery-ui.min.js"></script>
<script src="js/css3-mediaqueries.js"></script>
<script src="js/fwslider.js"></script>
<!--end slider -->

<script src="js/jquery.easydropdown.js"></script>
<link href="css/default.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/nivo-slider.css" rel="stylesheet" type="text/css"
	media="all" />
<link rel="stylesheet" href="<c:url value="/css/nav.css"/>"
	type="text/css">

</head>
<body>
	<c:if test="${comList==null}">
		<script type="text/javascript">
			window.location.href = "<c:url value="FirstAction"></c:url>"
		</script>
	</c:if>
	<div class="header-top">
		<div class="wrap">
			<div class="header-top-left">
				<div class="clear"></div>
		</div>
			<div class="cssmenu">
				<ul>
					<c:if test="${!empty sessionScope.userInfo }">
						<li><a
							href="<c:url value="/UserAction?operate=modify&id=${sessionScope.userInfo.user_id}"></c:url>"><span></span>${sessionScope.userInfo.user_name}</a></li>
						<li><a
							href="<c:url value="/OrderAction?operate=listOrder"></c:url>"><span></span>个人订单</a></li>
						<li><a href="<c:url value="/user/modifyPsw.jsp"></c:url>"><span></span>修改密码</a></li>
						<li><a
							href="<c:url value="/UserAction?operate=quit"></c:url>"><span></span>退出</a></li>
					</c:if>
					<c:if test="${empty sessionScope.userInfo }">
						<li><div style="display: none"></div>
							<div align="center">
								<p><a href="#" class="tc">快速登录</a></p>
						</div></li>
						<li><a href="<c:url value="/user/login.jsp"></c:url>"><span></span>登陆</a></li>
						<li><a href="<c:url value="/user/register.jsp"></c:url>"><span></span>注册</a></li>
					</c:if>

			</ul>
		</div>
			<div class="clear"></div>
	</div>
</div>
	<div class="header-bottom">
		<div class="wrap">
			<div class="header-bottom-left">
				<div class="logo"><a href="index.html"><img
						src="images/logo.png" alt="" /></a></div>
				<div class="menu">
					<ul class="megamenu skyblue">
						<li class="active grid"><a href="index.jsp">首页</a></li>

						<li><c:if test="${!empty sessionScope.userInfo }">
								<a class="color5"
									href="<c:url value="/ShopCartAction?operate=list"></c:url>">我的购物车</a>
							</c:if></li>
						<li><a class="color6"
							href='<c:url value="/ComputerAction?operate=listCom"></c:url>'>电脑列表</a></li>
				</ul>
			</div>
		</div>
			<div class="header-bottom-right">
				<div class="search">
					<form
					action='<c:url value="/ComputerAction?operate=listCom"></c:url>'
					method="post"><input type="text" class="textbox"
						value="${search }" name="search" onFocus="this.value = '';"
						onBlur="if (this.value == '') {this.value = 'Search';}"> <input
						type="submit" value="Subscribe" id="submit" name="submit"></form>
					<div id="response"></div>
			</div>
				
		</div>
			<div class="clear"></div>
	</div>
</div>

	<!-- start slider -->
	<div id="fwslider">
		<div class="slider_container">
			<div class="slide">
				<!-- Slide image --> <img src="images/1.jpg" alt="" /> <!-- /Slide image -->
				<!-- Texts container -->
				<div class="slide_content">
					<div class="slide_content_wrap">
						<!-- Text title -->
						<h4 class="title">Computer Shop</h4> <!-- /Text title --> <!-- Text description -->
						<p class="description">Welcome to you</p> <!-- /Text description -->
				</div>
			</div> <!-- /Texts container -->
		</div> <!-- /Duplicate to create more slides -->
			<div class="slide"><img src="images/2.jpg" alt="" />
				<div class="slide_content">
					<div class="slide_content_wrap">
						<h4 class="title">Computer Shop</h4>
						<p class="description">Welcome to you</p>
				</div>
			</div></div>
			<div class="slide"><img src="images/3.jpg" alt="" />
				<div class="slide_content">
					<div class="slide_content_wrap">
						<h4 class="title">Computer Shop</h4>
						<p class="description">Welcome to you</p>
				</div>
			</div></div> <!--/slide -->
	</div>

		<div class="timers"></div>
		<div class="slidePrev"><span></span></div>
		<div class="slideNext"><span></span></div>
</div>
	<!--/slider -->
	<div class="main">
		<div class="wrap">
			<div class="section group">
				<div class="cont span_2_of_3">
					<h2 class="head" style="color: red;">热销商品</h2>

					<div class="section group"><c:forEach items="${comList }"
							var="com">
							<div class="col_1_of_3 span_1_of_3" style="border-left-style: solid; margin-left: 2%;"><br> <a
								href='<c:url value="/ComputerAction?operate=getProduct&id=${com.com_id }"></c:url>'>
									<div class="inner_content clearfix">
										<div class="product_image">&nbsp; <img
											src="<c:if test="${!empty com.imgPath }">
						<c:url value="/upload/${com.imgPath }"></c:url>
						</c:if>   
					<c:if test="${empty com.imgPath}">
												upload/default.jpg
												</c:if>
						  " />
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
													<li>原价:<s>${com.price}</s></li>
													<li>售价：${com.rel_price }</li>

											</ul>

												<div class="price1"><span class="actual"></span></div>
										</div>
											<div class="cart-right"></div>
											<div class="clear"></div>
									</div>
								</div>
							</a></div>
						</c:forEach></div>

			</div>
		</div> <br> <br> <br>
			<div class="rsidebar span_1_of_left">
				<div class="top-border"></div>
				<div class="border"><script src="js/jquery.nivo.slider.js"></script>
					<script type="text/javascript">
						$(window).load(function() {
							$('#slider').nivoSlider();
						});
					</script>
					<div class="slider-wrapper theme-default">
						<div id="slider" class="nivoSlider"><img
							src="images/t-img1.jpg" alt="" /> <img src="images/t-img2.jpg"
							alt="" /> <img src="images/t-img3.jpg" alt="" /></div>
				</div></div>
	<img  src="<c:url value="/images/AL155TX_1.jpg"/>"/>

		</div>
			<div class="clear"></div>
	</div>
</div>
<div>
	<img  src="<c:url value="/images/house_con_img04.jpg"/>"/>


<img style="width: 100%;" src="<c:url value="/images/footer-banner.jpg"/>"/></div>

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


	<div id="gray"></div>

	<div class="popup" id="popup">

		<div class="top_nav" id='top_nav'>
			<div align="center"><span>登录账号</span> <a class="guanbi"></a></div>
	</div>

		<div class="min">

			<div class="tc_login">

				<div class="left">
					<h4 align="center">手机扫描，注册一下</h4>
					<div align="center"><img src="images/zfb_2yuan.jpg"
						width="150" height="150" /></div>

			</div>

				<div class="right">
					<form method="POST" name="form_login" target="_top"
					action='<c:url value="/UserAction?operate=login"></c:url>'>
				<br>
					<br><br>
				
						<div align="center"><i class="icon-mobile-phone"></i> <input
							type="text" name="userName" id="name" required="required"
							placeholder="用户名" autocomplete="off" class="input_yh">
								<br>
							 <input
							type="password" name="userPassword" id="pass" required="required"
							placeholder="密码" autocomplete="off" class="input_mm"></div>

						<div align="center"><input type="submit" class="button"
							title="Sign In" value="登录"></div>
				</form>
					<dd>
						<div align="center">
						<a
							href="<c:url value="/user/modifyPsw/index.jsp"></c:url>" target="_blank">找回密码</a><br>
						<a
							href="<c:url value="/user/register.jsp"></c:url>" target="_blank">立即注册</a></div>
				</dd>

			</div>

		</div>

	</div>

</div>
	<script type="text/javascript">
		//窗口效果
		//点击登录class为tc 显示
		$(".tc").click(function() {
			$("#gray").show();
			$("#popup").show();//查找ID为popup的DIV show()显示#gray
			tc_center();
		});
		//点击关闭按钮
		$("a.guanbi").click(function() {
			$("#gray").hide();
			$("#popup").hide();//查找ID为popup的DIV hide()隐藏
		})

		//窗口水平居中
		$(window).resize(function() {
			tc_center();
		});

		function tc_center() {
			var _top = ($(window).height() - $(".popup").height()) / 2;
			var _left = ($(window).width() - $(".popup").width()) / 2;

			$(".popup").css({
				top : _top,
				left : _left
			});
		}
	</script>

	<script type="text/javascript">
		$(document).ready(function() {

			$(".top_nav").mousedown(function(e) {
				$(this).css("cursor", "move");//改变鼠标指针的形状 
				var offset = $(this).offset();//DIV在页面的位置 
				var x = e.pageX - offset.left;//获得鼠标指针离DIV元素左边界的距离 
				var y = e.pageY - offset.top;//获得鼠标指针离DIV元素上边界的距离 
				$(document).bind("mousemove", function(ev) { //绑定鼠标的移动事件，因为光标在DIV元素外面也要有效果，所以要用doucment的事件，而不用DIV元素的事件 

					$(".popup").stop();//加上这个之后 

					var _x = ev.pageX - x;//获得X轴方向移动的值 
					var _y = ev.pageY - y;//获得Y轴方向移动的值 

					$(".popup").animate({
						left : _x + "px",
						top : _y + "px"
					}, 10);
				});

			});

			$(document).mouseup(function() {
				$(".popup").css("cursor", "default");
				$(this).unbind("mousemove");
			});
		})
	</script>

	<div class="J-global-toolbar">
		<div class="toolbar-wrap J-wrap">
			<div class="toolbar">
				<div class="toolbar-panels J-panel">
					<div style="visibility: hidden;"
					class="J-content toolbar-panel tbar-panel-cart toolbar-animate-out">
						<h3 class="tbar-panel-header J-panel-header"><a
							href="<c:url value="/ShopCartAction?operate=list"></c:url>"
							class="title"><i></i><em class="title"></em></a> <span
							class="close-panel J-close"></span></h3>
						<div class="tbar-panel-main">
							<div class="tbar-panel-content J-panel-content">

								<div id="J-cart-render">
									<div class="tbar-cart-list">
										<div class="tbar-cart-item"><c:forEach items="${ls }"
												var="ls">
												<div class="jtc-item-promo"><em
													class="promo-tag promo-mz">实惠<i class="arrow"></i></em>
													<div class="promo-text">已购满5000元，可优惠100元</div></div>
												<div class="jtc-item-goods"><span class="p-img"><a
														href="#"> <img height="50" width="50"
															src="
													<c:if test="${!empty ls.com.imgPath }">
													<c:url value="/upload/${ls.com.imgPath }"/>
													
													</c:if>
													
													<c:if test="${empty ls.com.imgPath }">
													/upload/default.jpg
													
													</c:if>
													" />
														</a> </span>
													<div class="p-name">${ls.com.model }</div>


													<div class="p-price"><strong>${ls.com.rel_price }</strong>×${ls.count }
												</div></div>

											</c:forEach></div> 合计<span>￥</span><span style="color: red; font-size: 24px;">${totalPrice}元</span>

								</div>
							</div>
						</div>
					</div>

				</div>

					<div style="visibility: hidden;" data-name="follow"
					class="J-content toolbar-panel tbar-panel-follow">
						<div class="tbar-panel-main">
							<div class="tbar-panel-content J-panel-content">
								<div class="tbar-tipbox2">
									<div class="tip-inner"><i class="i-loading"></i></div>
							</div>
						</div>
					</div>
						<div class="tbar-panel-footer J-panel-footer"></div>
				</div>


			</div> <c:if test="${!empty userInfo }">

					<div class="toolbar-tabs J-tab">
						<div class="toolbar-tab tbar-tab-cart"><i class="tab-ico"></i>
							<em class="tab-text ">购物车</em></div>


					</div>
				</c:if>

				<div class="toolbar-footer">
					<div class="toolbar-tab tbar-tab-top"><a href="#"> <i
							class="tab-ico  "></i> <em class="footer-tab-text">顶部</em>
					</a></div>
			</div>
				<div class="toolbar-mini"></div>
		</div>
			<div id="J-toolbar-load-hook"></div>
	</div>
</div>
	<script type="text/javascript" src='<c:url value="/js/nav.js"/>'></script>
</body>
</html>