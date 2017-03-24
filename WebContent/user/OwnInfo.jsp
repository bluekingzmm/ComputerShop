<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>前台显示个人信息</title>
<link href='<c:url value="/css/style.css"></c:url>' rel="stylesheet" type="text/css" 	media="all"/>
<link href='<c:url value="/css/form.css"></c:url>' rel="stylesheet" type="text/css" 	media="all"/>
<link href='<c:url value="/css/megamenu.css"></c:url>' rel="stylesheet" type="text/css" 	media="all"/>
<link href='<c:url value="/css/fwslider.css"></c:url>' rel="stylesheet" type="text/css" media="all"/>
<link href='<c:url value="/css/default.css"></c:url>' rel="stylesheet" type="text/css" media="all"/>
<link href='<c:url value="/css/nivo-slider.css"></c:url>' rel="stylesheet" type="text/css" media="all"/>
<link href='http://fonts.googleapis.com/css?family=Exo+2'
	rel='stylesheet' type='text/css'>
<script type="text/javascript" src="<c:url value="/js/jquery1.min.js"/>"></script>
<!-- start menu -->
<link href="<c:url value="/css/button.css"></c:url>" rel="stylesheet"
	type="text/css">
<link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet">
<link href="<c:url value="/css/city-picker.css"/>" rel="stylesheet">
<link href="<c:url value="/css/main.css"/>" rel="stylesheet">
<script type="text/javascript" src="js/megamenu.js"></script>
<script>
	$(document).ready(function() {
		$(".megamenu").megamenu();
	});
</script>
<script src="<c:url value="/js/jquery.easydropdown.js"/>"></script>
</head>
<%@include file="/nav/basion.jsp"%>
<body>
	<!--前台用户显示个人信息  -->

	<div class="register_account">
		<div class="wrap">
			<h4 class="title"></h4>
			<form action='<c:url value="/UserAction?operate=update"></c:url>'
			method="post">
				<div class="col_1_of_2 span_1_of_2"><input type="hidden"
					name="id" value="${user.user_id}" />
					<div>UserName <input type="text" value="${user.user_name }"
						name="userName" readonly="readonly"></div>
					<div>You Sex&nbsp;<input type="text" requried name="sex"
						readonly="readonly" value="${user.sex }"></div>
					<div id="distpicker">发货地址：&nbsp;
			<div class="form-group">
				<div style="position: relative;width: 250px;">
					<input id="city-picker3" class="form-control" readonly type="text" name="address" value="${user.address }" data-toggle="city-picker">
				</div>
			</div>
			<div class="form-group">
				<button class="btn btn-warning" id="reset" type="button">Reset</button>
				<button class="btn btn-danger" id="destroy" type="button">Destroy</button>
			</div>
		</div>
					<div>IDCard<br /> <input type="text" requried name="Id"
						value="${user.id }"></div></div>
				<div class="col_1_of_2 span_1_of_2">
					<div>RelName<input type="text" requried name="relName"
						value="${user.rel_name }"></div>

					<div>You Age<input type="text" requried value="${user.age }"
						name="age" readonly="readonly"></div>
					<div>Reg_Time <input type="text" requried name="regTime"
						value="${user.reg_time }" readonly="readonly">

				</div> Phone: <input type="text" value="${user.phone }" requried
					name="phone"> <br>
					<div>You E-mail<input type="text" requried name="mail"
						value="${user.mail }"></div>
			</div> &nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="update"
				class="button button-glow button-border button-rounded button-primary">
				<input type="button" value="返回"
				onclick="javascript:history.back(-1);"
				class="button button-glow button-border button-rounded button-primary">
				<br> <span style="color: red;">${msg }</span>
				<div class="clear"></div>
		</form>
	</div>
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
<script src="<c:url value="/js/jquery.js"/>"></script>
<script src="<c:url value="/js/bootstrap.js"/>"></script>
<script src="<c:url value="/js/city-picker.data.js"/>"></script>
<script src="<c:url value="/js/city-picker.js"/>"></script>
<script src="<c:url value="/js/main.js"/>"></script>
</body>
</html>