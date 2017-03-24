<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href='<c:url value="/css/button.css"></c:url>'>
<link rel="stylesheet" type="text/css"
	href="lib/bootstrap/css/bootstrap.css">
<script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>

<!--后台显示单个用户 -->

</head>

<%@include file="/admin/Basion.jsp"%>
<span> <c:if test="${!empty msg }">
		<script type="text/javascript">
			alert("${msg}");
		</script>
	</c:if>
</span>
<body>
	<div class="content">

		<div class="header">

			<h1 class="page-title">Users</h1>
	</div>

		<ul class="breadcrumb">
			<li><a href="index.html">Home</a> <span class="divider">/</span></li>
			<li class="active">Users</li>
	</ul>

		<div class="container-fluid">
			<div class="row-fluid">

				<div class="btn-toolbar">
					<button class="btn btn-primary"><i class="icon-plus"></i>
						New User</button>
					<div class="btn-group"></div>
			</div>
				<div class="well">
					<table class="table">
						<thead>
							<tr>
								<th>序号</th>
								<th>用户名</th>
								<th>手机号码</th>
								<th>地址</th>
								<th>电子邮箱</th>
								<th>注册时间</th>
								<th>真实姓名</th>
								<th>性别</th>
								<th>注册码</th>
								<th>操作</th>
								<th style="width: 26px;"></th>
						</tr>
					</thead>
						<tbody>


							<tr>
								<td>${user.user_id}</td>
								<td>${user.user_name }</td>
								<td>${user.phone }</td>
								<td>${user.address }</td>
								<td>${user.mail }</td>
								<td>${user.reg_time }</td>
								<td>${user.rel_name }</td>
								<td>${user.sex }</td>
								<td>${user.code }</td>
						</tr>
							<tr>
								<td><input onclick="javascript:history.back(-1);"
									value="返回" type="button"
									class="button button-primary button-rounded button-small" /></td>
						</tr>
					</tbody>
				</table>
			</div> <span>${msg }</span> <footer>
				<hr>
				<p>&copy; 2016 <a href="#" target="_blank">zmm</a></p>
			</footer>

		</div>
	</div>
</div>
</body>
</html>