<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>后台显示信息</title>
<script src="<c:url value="/js/bootstrap.js"></c:url>"
	type="text/javascript"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery-2.1.1.js"></c:url>"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/lib/bootstrap/css/bootstrap.css"/>">
<link rel="stylesheet" type="text/css"
	href='<c:url value="/css/button.css"></c:url>'>

<style type="text/css">
ul, ol {
	color: #000000;
}
</style>

</head>
<%@include file="/admin/Basion.jsp"%>

<body>
	<div class="content">

		<div class="header">

			<h1 class="page-title">MyMessge</h1>
	</div>

		<ul class="breadcrumb">
			<li><a href="#">Home</a> <span class="divider">/</span></li>
			<li><a href="#">Messge</a> <span class="divider">/</span></li>
	</ul>

		<div class="container-fluid">
			<div class="row-fluid">

				<div class="btn-toolbar"></div>
				<div class="well">

					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home"
						style="text-align: center;">



							<div class="well">
								<table class="table">
									<thead>
										<tr>
											<th>序号</th>
											<th>订单编号</th>
											<th>用户名</th>
											<th>订单总价格</th>
											<th>备注</th>
											<th>是否已发货</th>
											<th>订单日期</th>
											<th>操作</th>
											<th>操作</th>
									</tr>
								</thead>
									<c:forEach items="${oList}" var="o" varStatus="status">
										<tbody>

											<tr>
												<td>${status.count}</td>
												<td>${o.orderId }</td>
												<td>${o.user.user_name }</td>
												<td>${o.totalPrice }</td>
												<td>${o.label}</td>
												<td><c:if test="${o.isDeliver==0}">未发货</c:if> <c:if
														test="${o.isDeliver==1}">已发货</c:if></td>
												<td>${o.submitTime }</td>
												<td><c:if test="${o.isDeliver==0 }">
														<a class="btn btn-primary btn-lg"
															href='<c:url value="/OrderAction?operate=updateDeliver&id=${o.orderId }&isDeliver=${o.isDeliver }"/>'>发货</a>
													</c:if> <c:if test="${o.isDeliver==1 }">
														<a href='javascript:void(0)' style="color: #cfcfcf;">发货</a>
													</c:if></td>
												<td><a
													href='<c:url value="OrderAction?operate=deleteMessge&id=${o.orderId }"/>'>删除</a></td>
										</tr>
										</tbody>
									</c:forEach>
							</table>
							
						</div>



					</div>

				</div>
			</div>

		</div> <footer>
			<hr>
			<p>&copy; 2016 <a href="#" target="_blank">zmm</a></p>
		</footer>
			<div></div>
	</div>
</div>
</body>
</html>