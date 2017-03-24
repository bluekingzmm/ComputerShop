<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>前台主要部分</title>
  

<script type="text/javascript" src="js/Calculation.js"></script> 

<link href="<c:url value="/css/style.css"/>" rel="stylesheet"
	type="text/css" media="all" />
<link href="<c:url value="/css/form.css"/>" rel="stylesheet"
	type="text/css" media="all" />
<link href="<c:url value="/css/megamenu.css"/>" rel="stylesheet"
	type="text/css" media="all" />
<script src="<c:url value="/js/jquery-ui.min.js"/>"></script>
<link href='http://fonts.googleapis.com/css?family=Exo+2'
	rel='stylesheet' type='text/css'>
<!-- start menu -->
<script type="text/javascript" src="<c:url value="/js/megamenu.js"/>"></script>
<script>
	$(document).ready(function() {
		$(".megamenu").megamenu();
	});
</script>
<!--start slider -->
<link rel="stylesheet" href="css/fwslider.css" media="all">
<script src="<c:url value="/js/jquery-ui.min.js"/>"></script>
<script src="<c:url value="/js/css3-mediaqueries.js"/>"></script>
<script src="<c:url value="/js/fwslider.js"/>"></script>
<!--end slider -->
<script src="<c:url value="/js/jquery.easydropdown.js"/>"></script>
<link href="<c:url value="/css/default.css"/>" rel="stylesheet"
	type="text/css" media="all" />
<link href="<c:url value="/css/nivo-slider.css"/>" rel="stylesheet"
	type="text/css" media="all" />
	<%--  <script type="text/javascript" src="<c:url value="/js/jquery-1.4.4.min.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/jquery1.min.js"/>"></script> 

<script type="text/javascript" src="js/jquery.1.4.2-min.js"></script> --%>
</head>
<body>

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
						<li><a href="<c:url value="/user/login.jsp"></c:url>"><span></span>登录</a>
						
						
						</li>
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
				<div class="logo"><a href="index.jsp"><img
						src="<c:url value="/images/logo.png"/>"></a></div>
				<div class="menu">
					<ul class="megamenu skyblue">
						<li class="active grid"><a
							href='<c:url value="/index.jsp"></c:url>'>首页</a></li>

						<li><c:if test="${!empty sessionScope.userInfo }">
								<a class="color5"
									href='<c:url value="/ShopCartAction?operate=list"></c:url>'>我的购物车</a>
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
				<div class="tag-list">
					<ul class="icon1 sub-icon1 profile_img">
						<li><a class="active-icon c2"
							href='<c:url value="/ShopCartAction?operate=list"></c:url>'>
						</a></li>
				</ul>

			</div>
		</div>
			<div class="clear"></div>
	</div>
</div>

</body>
</html>