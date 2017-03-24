<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台显示单个商品</title>
<link rel="stylesheet" type="text/css"
	href='<c:url value="/css/button.css"></c:url>'>
</head>

<%@include file="/admin/Basion.jsp"%>
<body>


	<div class="content">

		<div class="header">

			<h1 class="page-title">Computer Show</h1>
	</div>

		<ul class="breadcrumb">
			<li><a href="index.html">Home</a> <span class="divider">/</span></li>
			<li class="active">Computer</li>
	</ul>

		<div class="container-fluid">
			<div class="row-fluid">


				<div class="block" style="text-align: center; width: 70%;">
					<p class="block-heading">商品信息</p>
					<div class="block-body gallery" style="text-align: center;">

						<table class="table">

							<tr>
								<th>序号</th>
								<th>${com.com_id}</th>
						<tr />
							<tr>
								<th>品牌</th>
								<th>${com.brand.brand_name}</th>
						</tr>
							<tr>
								<th>型号</th>
								<th>${com.model}</th>
						</tr>
							<tr>
								<th>颜色</th>
								<th>${com.color}</th>
						</tr>
							<tr>
								<th>价格</th>
								<th>￥${com.price}</th>
						</tr>
							<tr>
								<th>售价</th>
								<th>￥${com.rel_price}</th>
						</tr>
							<tr>
								<th>上架时间</th>
								<th>${com.reg_date}</th>
						</tr>
							<tr><th>像素</th>
								<th>${com.pixels}</th>
						</tr>

							<tr>
								<th>处理器</th>
								<th>${com.cpu.cpu_name}</th>
						</tr>
							<tr>
								<th>内存</th>
								<th>${com.ram}</th>
						</tr>
							<tr>
								<th>屏幕尺寸</th>
								<th>${com.screen.screen_name}</th>
						</tr>
							<tr>
								<th>库存</th>
								<th>${com.num}</th>
						</tr>
						<tr>
								<th>评价</th>
								
								<th>
								${com.content}
								</th>
						</tr>
							<tr>
								<th><input onclick="javascript:history.back(-1);"
									class="button button-primary button-rounded button-small"
									value="返回" type="button" /></th>
								<th><img width="340" height="340"
									src="<c:if test="${!empty com.imgPath }">
													<c:url value="/upload/${com.imgPath}"></c:url>
												</c:if>
												<c:if test="${empty com.imgPath }">
												upload/default.jpg
												</c:if>"></th>
						<tr />
					</table>
						<div class="clearfix"></div>
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