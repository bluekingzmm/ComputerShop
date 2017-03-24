<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加管理员</title>
<link rel="stylesheet" type="text/css"
	href="lib/bootstrap/css/bootstrap.css">
<script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>
<style type="text/css">
fieldset {
	border: none;
	text-align: center;
}

.table {
	border: 2px solid #808080;
	width: 800px;
	height: auto;
}

.table tr {
	width: 200px;
	text-align: center;
	background: #dba8bd;
	height: 50px;
}

.table tr:HOVER {
	background: #f0f0f0;
}
</style>

</head>
<%@include file="/admin/Basion.jsp"%>
<body class="">
	<div class="content">

		<div class="header">

			<h1 class="page-title">Admin</h1>
	</div>

		<ul class="breadcrumb">
			<li><a href="#">Home</a> <span class="divider">/</span></li>
			<li class="active">admin</li>
	</ul>

		<div class="container-fluid">

			<div class="">
				<form method="post"
				action='<c:url value="/AdminAction?operate=add"></c:url>'>
					<table>
						<tr><td>请输入用户名：</td>
							<td><input type="text" name="adminName" required></td></tr>
						<tr><td>请输入密码：</td>
							<td><input type="password" name="adminPassword"
								maxlength="12" required placeholder="请输入密码"></td></tr>
				</table>
					<fieldset><span>

							<button class="btn btn-primary" type="submit"><i
								class="icon-plus"></i> 添加</button>

					</span> <span><%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg").toString()%></span>

				</fieldset>

			</form>

		</div>
	</div>
</div>

</body>
</html>