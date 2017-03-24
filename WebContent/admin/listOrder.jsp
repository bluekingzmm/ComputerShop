<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>后台显示订单</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/lib/bootstrap/css/bootstrap.css"/>">
<link rel="stylesheet" type="text/css"
	href='<c:url value="/css/button.css"></c:url>'>
	<script type="text/javascript" src="<c:url value="/js/jquery-2.1.1.js"></c:url>"></script>
	
<script type="text/javascript" src="<c:url value="/js/jquery.min.js"/>"></script>

<script src="<c:url value="/js/bootstrap.js"></c:url>"
	type="text/javascript"></script>
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

			<h1 class="page-title">OrderList</h1>
	</div>

		<ul class="breadcrumb">
			<li><a href="#">Home</a> <span class="divider">/</span></li>
			<li><a href="#">Order</a> <span class="divider">/</span></li>
	</ul>

		<div class="container-fluid">
			<div class="row-fluid">

				<div class="btn-toolbar"></div>
				<div class="well">

					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home"
						style="text-align: center;">
							<form method="post"
							action='<c:url value="/OrderAction?operate=list"></c:url>'>
								用户名·:<input type="text" name="userName" style="width: 10%" />
								订单编号:<input type="text" name="orderId" style="width: 10%" /> <select
								style="width: 10%" name="isPay" title="是否付款">
									<option value="">是否付款</option>
									<option value="0">未付款</option>
									<option value="1">已付款</option>
							</select> <select style="width: 10%" name="isDeliver" title="是否发货">
									<option value="">是否发货</option>
									<option value="0">未发货</option>
									<option value="1">已发货</option>
							</select> &nbsp; &nbsp;&nbsp; &nbsp; <input type="submit" value="查询"
								class="button button-primary button-rounded button-small" />

						</form>
							<div></div>

							<div class="well">
								<table class="table">
									<thead>
										<tr>
											<th>订单编号</th>
											<th>用户名</th>
											<th>订单总价格</th>
											<th>是否已发货</th>
											<th>是否已付款</th>
											<th>订单日期</th>
											<th>操作</th>
											<th>操作</th>
											<th>操作</th>
									</tr>
								</thead>
									<c:forEach items="${oList}" var="o" varStatus="status">
										<tbody>

											<tr>
												<td>${o.orderId }</td>
												<td>${o.user.user_name }</td>
												<td>${o.totalPrice }</td>
												<td><c:if test="${o.isDeliver==0}">未发货</c:if> <c:if
														test="${o.isDeliver==1}">已发货</c:if></td>
												<td><c:if test="${o.isPay==0}">未付款</c:if> <c:if
														test="${o.isPay==1}">已付款</c:if></td>
												<td>${o.submitTime }</td>
												<td><c:if test="${o.isDeliver==0 }">
														<a class="btn btn-primary btn-lg"
															href='<c:url value="/OrderAction?operate=updateDeliver&id=${o.orderId }&isDeliver=${o.isDeliver }"/>'>发货</a>
													</c:if> <c:if test="${o.isDeliver==1 }">
														<a href='javascript:void(0)' style="color: #cfcfcf;">发货</a>
													</c:if></td>
												<td><a class="btn btn-primary btn-lg"
													data-toggle="modal" data-target="#myModal"
													href="<c:url value="/OrderAction?operate=show&id=${o.orderId}"></c:url>">查看明细</a>
											</td>
												<td><a
													href='<c:url value="OrderAction?operate=deleteOrder&id=${o.orderId }"/>'>删除</a></td>
										</tr>
										</tbody>
									</c:forEach>
							</table>
						</div>

							<div class="pagination">
								<ul>
									<li><a>共有${count }条记录，当前第${pageNo}/${totalPage}页</a></li>
									<li><c:if test="${pageNo!=1 }">
											<a
												href='<c:url value="/OrderAction?operate=list&pageNo=1"></c:url>'>首页</a>
										</c:if> <c:if test="${pageNo==1 }">
											<a href="javascript:void(0);" style="color: #cfcfcf">首页</a>
										</c:if></li>
									<li><c:if test="${pageNo!=1 }">

											<a
												href="<c:url value="/OrderAction?operate=list&pageNo=${pageNo<1?pageNo:pageNo-1}"></c:url>">上一页</a>

										</c:if> <c:if test="${pageNo==1 }">

											<a href="javascript:void(0);" style="color: #cfcfcf;">上一页</a>

										</c:if></li>
									<li><c:if test="${pageNo!=totalPage }">
											<a
												href="<c:url value="/OrderAction?operate=list&pageNo=${pageNo>totalPage?pageNo:pageNo+1}"></c:url>">下一页</a>
										</c:if> <c:if test="${pageNo==totalPage }">
											<a href="javascript:void(0);" style="color: #cfcfcf;">下一页</a>
										</c:if></li>
									<li><c:if test="${pageNo!=totalPage }">
											<a
												href="<c:url value="/OrderAction?operate=list&pageNo=${totalPage}"></c:url>">尾页</a>
										</c:if> <c:if test="${pageNo==totalPage }">

											<a href="javascript:void(0)" style="color: #cfcfcf;">尾页</a>

										</c:if></li>
							</ul>
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

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content"></div>

	</div>
</div>
<script type="text/javascript">
$(document).on("hidden.bs.modal", function (e) {
	$(e.target).removeData("bs.modal").find(".modal-content").empty();
	});
</script>
</body>
</html>