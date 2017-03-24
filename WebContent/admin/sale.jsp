<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>后台显示订单</title>
<script src="<c:url value="/js/bootstrap.js"></c:url>"
	type="text/javascript"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.min.js"/>"></script>
<script src="<c:url value="/js/jquery-2.1.1.js"></c:url>"></script>
<link rel="stylesheet" type="text/css"
	href="lib/bootstrap/css/bootstrap.css">
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

			<h1 class="page-title">SaleList</h1>
	</div>

		<ul class="breadcrumb">
			<li><a href="#">Home</a> <span class="divider">/</span></li>
			<li><a href="#">Sale</a> <span class="divider">/</span></li>
	</ul>

		<div class="container-fluid">
			<div class="row-fluid">

				<div class="btn-toolbar"></div>
				<div class="well">

					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home"
						style="text-align: center;">
							<div></div>

							<div class="well">
								<table class="table">
									<thead>
										<tr>
											<th>排名</th>
											<th>电脑编号</th>
											<th>品牌名</th>
											<th>电脑型号</th>
											<th>售价</th>
											<th>销售数量</th>
									</tr>
								</thead>
									<c:forEach items="${oList}" var="ol" varStatus="status">
										<tbody>

											<tr>
												<td>${(pageNo-1)*pageSize+status.count }</td>
												<td>${ol.com.com_id }</td>
												<td>${ol.com.brand.brand_name}</td>
												<td>${ol.com.model}</td>
												<td>${ol.buy_price }</td>
												<td>${ol.account}</td>
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
<script src="lib/bootstrap/js/bootstrap.js"></script>
	
</body>
</html>