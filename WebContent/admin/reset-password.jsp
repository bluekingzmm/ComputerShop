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
<script src="<c:url value="/lib/jquery-1.7.2.min.js"/>" type="text/javascript"></script>

<!-- Demo page code -->


</head>
<%@include file="/admin/Basion.jsp"%>
<body>
	<div class="content">

		<div class="header">

			<h1 class="page-title">Change Password</h1>
	</div>

		<ul class="breadcrumb">
			<li><a href="#">Home</a> <span class="divider">/</span></li>
			<li><a href="#">admin</a> <span class="divider">/</span></li>
	</ul>

		<div class="container-fluid">
			<div class="row-fluid">

				<div class="btn-toolbar"></div>
				<div class="well">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#home" data-toggle="tab">重置密码</a></li>
				</ul>
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							<form id="tab" method="post"
							action='<c:url value="/AdminAction?operate=modifyPsw"></c:url>'>
								<label>管理员名称</label> <input type="text" value="${sessionScope.adminName}"
								class="input-xlarge" name="adminName" readonly="readonly">
								<label>原密码</label> <input type="text" placeholder="请输入原密码"
								required class="input-xlarge" name="oldPsw"> <label>新密码</label>
								<input type="text" name="newPsw" placeholder="请输入新密码" required
								class="input-xlarge"> <label>请在一次输入新密码</label> <input
								type="text" name="againPsw" placeholder="请再次输入新密码" required
								class="input-xlarge">
								<button class="btn btn-primary" type="submit"><i
									class="icon-save"></i> Update</button> <span style="color: red;">${msg}</span>
						</form>
					</div>

				</div>
			</div>

		</div> <footer>
			<hr>
			<p>&copy; 2016 <a href="#" target="_blank">zmm</a></p>
		</footer>

	</div>
</div>
	<script src="../lib/bootstrap/js/bootstrap.js"></script>
</body>
</html>