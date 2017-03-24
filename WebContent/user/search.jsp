<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<link rel="stylesheet" type="text/css"
	href='<c:url value="/css/button.css"></c:url>'>
<script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>


<style type="text/css">

#search {
	text-align: center;
	letter-spacing: 5px;
}

</style>

</head>

<%@include file="/admin/Basion.jsp"%>
<body>
	<div class="content">

		<div class="header">

			<h1 class="page-title">Users</h1>
	</div>

		<ul class="breadcrumb">
			<li><a href="index.html">Home</a> <span class="divider">/</span></li>
			<li class="active">Users</li>
	</ul>
		<form action='<c:url value="/UserAction?operate=searchUserName"></c:url>'
		method="post">
			<div id="search"><br /> <input type="text" name="userName"
				 placeholder="search...."  />
				 <input type="submit"
								value="查询"
								class="button button-primary button-rounded button-small" /></div>
	</form>

		<div class="container-fluid">
			<div class="row-fluid">

				<div class="btn-toolbar">

					<div class="btn-group"></div>
			</div>
				<div class="well">
					<table class="table">
						<thead>
							<tr>
								<th>序号</th>
								<th>用户名</th>
								<th>性别</th>
								<th>操作</th>
								<th>操作</th>

								<th style="width: 26px;"></th>
						</tr>
					</thead>
						<tbody>

							<c:forEach items="${uList}" var="user" varStatus="status">
								<tr>
									<td>${(pageNo-1)*pageSize+status.count}</td>
									<td>${user.user_name }</td>
									<td>${user.sex }</td>

									<td><a
										href='<c:url value="/UserAction?operate=recycleBin&id=${user.user_id }"></c:url>'
										role="button" data-toggle="modal"><i class="icon-remove"></i></a></td>
									<td><a
									
										href='<c:url value="/UserAction?operate=show&id=${user.user_id }"></c:url>'>查看</a></td>
										
								</tr>
							</c:forEach>
					</tbody>
				</table>
				<input onclick="javascript:history.back(-1);" value="返回" type="button" class="button button-primary button-rounded button-small" />
			</div>
			</div> <footer>
				<hr>
				<p>&copy; 2016 <a href="#" target="_blank">zmm</a></p>
			</footer>

		</div>
	</div>
</div>
</body>
</html>