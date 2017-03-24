<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>前台显示订单</title>


<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/bootstrap.css"/>">
<link rel="stylesheet" type="text/css"
	href='<c:url value="/css/button.css"></c:url>'>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href='http://fonts.googleapis.com/css?family=Exo+2'
	rel='stylesheet' type='text/css'>

<link href="css/megamenu.css" rel="stylesheet" type="text/css"
	media="all" />
<script type="text/javascript"
	src="<c:url value="/js/jquery-2.1.1.js"></c:url>"></script>

<script type="text/javascript" src="<c:url value="/js/jquery.min.js"/>"></script>

<script type="text/javascript"
	src="<c:url value="/js/bootstrap.min.js"></c:url>"></script>
</head>
<%@include file="/nav/basion.jsp"%>
<body>

	<div class="content">
		<div class="container-fluid">
			<div class="well">

				<div id="myTabContent" class="tab-content">
					<div class="tab-pane active in" id="home"><c:forEach
							items="${ol }" var="o">
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

										<td><c:if test="${o.isPay==0 }">

												<a class="btn btn-2"
													href='<c:url value="/UserAction?operate=deleteOrder&id=${o.orderId }"/>'>取消订单</a>
											</c:if> <c:if test="${o.isPay==1}">

												<a class="btn btn-2" href='javascript:void(0)'>等待发货</a>
											</c:if></td>


										<td><c:if test="${o.isPay==0 }">
												<a class="btn btn-2"
													href='<c:url value="/OrderAction?operate=updatePay&id=${o.orderId }&isPay=${o.isPay }"/>'>确定付款</a>

											</c:if> <c:if test="${o.isPay==1 }">
												<a href='javascript:void(0)' style="color: #cfcfcf">已经付款</a>
											</c:if></td>
										<td><a class="btn btn-primary" data-toggle="modal"
											data-target="#myModal"
											href="<c:url value="/OrderAction?operate=show&id=${o.orderId}"></c:url>">查看明细</a></td>
<td>
										<c:if test="${o.isPay==1 }">
											<c:if test="${o.isDeliver==0 }">
												<c:if test="${o.tx==0 }">
														<form
															action="<c:url value="/OrderAction?operate=updateService&id=${o.orderId }&tx=${o.tx }"/>"
															method="post"><input type="text" name="label"
															placeholder="备注" value="空"> <input
															class="btn btn-primary" type="submit" value="提醒发货">&nbsp;&nbsp;&nbsp;
														</form>
													</c:if> <c:if test="${o.tx==1 }">
														<a href='javascript:void(0)' style="color: #cfcfcf">请等待</a>
													</c:if>
											</c:if>

										</c:if>
										</td>
								</tr>

							</tbody>

							</table>
						</c:forEach></div>



			</div>

		</div>
	</div>

</div>
	<br>
	<br>
	<br>
	<br>
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
			<center><a href="sign-in.jsp" style="color: white;">web后台管理登录</a></center>

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
		$(document).on("hidden.bs.modal", function(e) {
			$(e.target).removeData("bs.modal").find(".modal-content").empty();
		});
	</script>
</body>
</html>