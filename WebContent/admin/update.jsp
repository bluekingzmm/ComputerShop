<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改管理员信息</title>
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
<body>
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
				action='<c:url value="/AdminAction?operate=update"></c:url>'>
					<table>
						<tr><td><input type="hidden" name="id"
								value="${admin.id}" /></td></tr>
						<tr><td>请输入用户名：</td>
							<td><input type="text" name="adminName" required
								value="${admin.admin_name}"></td></tr>
						
				</table>
					<fieldset><span>

							<button class="btn btn-primary" type="submit"><i
								class="icon-plus"></i> 更新</button>

					</span> <span><c:if test="${not empty msg }">
							<script type="text/javascript">
								alert("${msg}");
							</script>
						</c:if></span></fieldset>

			</form>

		</div>
	</div>
</div>
</body>
</html>