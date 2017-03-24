<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>前台用户注册</title>

<link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css" media="all" />
<link href='http://fonts.googleapis.com/css?family=Exo+2'
	rel='stylesheet' type='text/css'>
<!-- start menu -->
<link href="<c:url value="/css/megamenu.css"/>" rel="stylesheet" type="text/css"
	media="all" />
	<link href="<c:url value="/css/button.css"/>" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<c:url value="/js/megamenu.js"/>"></script>
<script>
	$(document).ready(function() {
		$(".megamenu").megamenu();
	});
</script>
<link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet">
<link href="<c:url value="/css/city-picker.css"/>" rel="stylesheet">
<link href="<c:url value="/css/main.css"/>" rel="stylesheet">
<script src="<c:url value="/js/jquery.easydropdown.js"/>"></script>
<style type="text/css">
.login .on_changes {
	width: 232px;
	position: absolute;
	top: 40px;
	list-style: none;
	background: #FFF;
	border: 1px solid #000;
	display: none;
	padding: 10px;
}

.login .on_changes li {
	margin: 8px;
	padding: 4px;
}

.login .on_changes li.active {
	background: #CEE7FF;
}

.login {
	margin: 0 auto;
	position: relative;
}
</style>
</head>
<%@include file="/nav/basion.jsp"%>
<body>


	<div class="register_account">
		<div class="wrap">
			<h4 class="title">创建用户</h4>
			<form action='<c:url value="/UserAction?operate=add"></c:url>'
			method="post" class="form-inline" onsubmit="return must();">
				<div class="col_1_of_2 span_1_of_2">

					<div>userName <input type="text" name="userName"
						placeholder="用户名" onfocus="this.value = '';" requried
						onblur="if (this.value == '') {this.value = '用户名';}"></div>
					<div>Password&nbsp;<input type="text" name="userPassword"
						onfocus="this.value = '';" requried placeholder="密码"
						onblur="if (this.value == '') {this.value = '密码';}"></div>
					
					<div>IDCard<br /> <input type="text" name="Id"
						onfocus="this.value = '';" requried placeholder="身份证号"
						onblur="if (this.value == '') {this.value = '身份证号';}"></div>
	
	<div id="distpicker">发货地址：&nbsp;
			<div class="form-group">
				<div style="position: relative;width: 250px;">
					<input id="city-picker3" class="form-control" readonly type="text" name="address" value="江苏省/常州市/溧阳市" data-toggle="city-picker">
				</div>
			</div>
			<div class="form-group">
				<button class="btn btn-warning" id="reset" type="button">Reset</button>
				<button class="btn btn-danger" id="destroy" type="button">Destroy</button>
			</div>
		</div>
    <br>
			</div>
			
				<div class="col_1_of_2 span_1_of_2">
					<div>RelName<input type="text" name="relName"
						placeholder="真实姓名" onfocus="this.value = '';" requried
						onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')"
						onblur="if (this.value == '') {this.value = '真实姓名';}"></div>
					<div><input type="radio" name="sex" value="男"
						checked="checked">男<input type="radio" name="sex"
						value="女">女</div>
					
				<br>
				<input type="text" value="" class="number"
					onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = '手机号码';}" requried
					placeholder="手机号" name="phone"
					onkeyup="value=value.replace(/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{9}$/g,'')">

					<br>

					<input type="text" maxlength="128"
						required name="mail" placeholder="邮箱" />
			</div>
			<script type="text/javascript">
			function must() {
				alert("必须同意才能注册！请点击同意");
				return false;
			}
			</script>
				<p class="terms">是否同意本网站条令 <input type="radio" name="agree"
					value="yes" checked="checked" />同意<input type="radio" name="agree"
					value="no" onclick="must();"/>不同意
			</p> &nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="注册"
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
			<center><a href="<c:url value="/sign-in.jsp"/>" style="color: white;">web后台管理登录</a></center>
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