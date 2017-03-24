<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="lib/bootstrap/css/bootstrap.css">

<link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
<link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">

<script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>

<!-- 前台显示用户信息 -->

<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

.brand {
	font-family: georgia, serif;
}

.brand .first {
	color: #ccc;
	font-style: italic;
}

.brand .second {
	color: #fff;
	font-weight: bold;
}
</style>
</head>
<%@include file="/admin/Basion.jsp"%>
<body class="">
	<div class="content">

		<div class="header">

			<h1 class="page-title">Show User</h1>
	</div>

		<ul class="breadcrumb">
			<li><a href="index.html">Home</a> <span class="divider">/</span></li>
			<li><a href="users.html">Users</a> <span class="divider">/</span></li>
			<li class="active">User</li>
	</ul>

		<div class="container-fluid">
			<div class="row-fluid">

				<div class="well">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#home" data-toggle="tab">用户详细信息</a></li>

				</ul>
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home"><label>用户名</label>
							<input type="text" readonly="readonly" value="${user.user_name }"
							class="input-xlarge"> <label> 真实姓名</label> <input
							type="text" value="${user.rel_name }" readonly="readonly"
							class="input-xlarge"> <label>手机号码</label> <input
							type="text" value="${user.phone }" readonly="readonly"
							class="input-xlarge"> <label>Email</label> <input
							type="text" value="${user.mail}" readonly="readonly"
							class="input-xlarge"> <label>地址</label> <input
							type="text" value="${user.address}" readonly="readonly"
							class="input-xlarge"> <label>注册时间</label> <input
							type="text" value="${user.reg_time}" readonly="readonly"
							class="input-xlarge"> <label>性别</label> <input
							type="text" value="${user.sex}" readonly="readonly"
							class="input-xlarge"> <label>年龄</label> <input
							type="text" value="${user.age}" readonly="readonly"
							class="input-xlarge"> <label>注册码</label> <input
							type="text" value="${user.code}" readonly="readonly"
							class="input-xlarge"> <label>返回上一个页面</label> <input
							onclick="javascript:history.back(-1);" value="返回" type="button"
							class="input-xlarge" />
							<div class="tab-pane fade" id="profile"></div></div>

				</div> <footer>
					<hr>


					<p>&copy; 2016 <a href="#" target="_blank">zmm</a></p>
				</footer>

			</div>
		</div>
	</div>
</div>
	<script src="../lib/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript">
		$("[rel=tooltip]").tooltip();
		$(function() {
			$('.demo-cancel-click').click(function() {
				return false;
			});
		});
	</script>
</body>
</html>