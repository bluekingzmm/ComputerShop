<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理首页</title>

<link rel="stylesheet" type="text/css"
	href="lib/bootstrap/css/bootstrap.css">
<script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>


</head>
<%@include file="/admin/Basion.jsp"%>


<body>
	<div class="content">

		<div class="header">

			<h1 class="page-title">电脑列表</h1>
	</div>

		<ul class="breadcrumb">
			<li><a href="#">Home</a> <span class="divider">/</span></li>
			<li class="active">Info</li>
	</ul>

		<div class="container-fluid">
			<div class="row-fluid">


				<div class="row-fluid">
					<div class="block"><a href="#page-stats"
						class="block-heading" data-toggle="collapse">Admin Info</a>
						<table class="table">
							<thead>
								<tr>
									<th>序号</th>
									<th>名称</th>
									<th>密码</th>
									<th style="width: 26px;"></th>
							</tr>
						</thead>

							<c:forEach items="${aList}" var="ad">
								<tbody>

									<tr>
										<td>${ad.id}</td>
										<td>${ad.admin_name}</td>
										<td>${ad.admin_password}</td>
										<td><a
											href='<c:url value="/AdminAction?operate=modify&id=${ad.id }"></c:url>'><i
												class="icon-pencil"></i></a> <a role="button"
											data-toggle="modal"
											href="<c:url value="/AdminAction?operate=recycleBin&id=${ad.id }"></c:url>"><i
												class="icon-remove"></i></a></td>

								</tr>

								</tbody>


							</c:forEach>

					</table>

						<p><a href='<c:url value="/AdminAction?operate=list"></c:url>'>More...</a></p>
				</div>

			</div>
		</div>

	</div> <!--用户信息  -->
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="block"><a href="#page-stats" class="block-heading"
					data-toggle="collapse">Users Info<span
						class="label label-warning">+3</span></a>

					<table class="table">
						<thead>
							<tr>
								<th>编号</th>
								<th>名称</th>
								<th>性别</th>
								<th style="width: 26px;"></th>

						</tr>
					</thead>
						<c:forEach items="${uList}" var="user">
							<tbody>
								<tr>
									<td>${user.user_id }</td>
									<td>${user.user_name }</td>
									<td>${user.sex }</td>
									<td><a role="button" data-toggle="modal"
										href="<c:url value="/UserAction?operate=recycleBin&id=${user.user_id }"></c:url>"><i
											class="icon-remove"></i></a></td>
							</tr>
							</tbody>
						</c:forEach>
				</table>
					<p><a href='<c:url value="/UserAction?operate=list"></c:url>'>More...</a></p>
			</div>
		</div>
	</div>

</div>


</body>
</html>