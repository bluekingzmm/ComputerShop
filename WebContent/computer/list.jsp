<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>后台显示商品</title>

<link rel="stylesheet" type="text/css"
	href='<c:url value="/css/button.css"></c:url>'>
<script src="<c:url value="/lib/jquery-1.7.2.min.js"/>" type="text/javascript"></script>
</head>
<%@include file="/admin/Basion.jsp"%>

<body>
	<div class="content">

		<div class="header">

			<h1 class="page-title">ComputerList</h1>
	</div>

		<ul class="breadcrumb">
			<li><a href="index.html">Home</a> <span class="divider">/</span></li>
			<li><a href="users.html">Computer</a> <span class="divider">/</span></li>
	</ul>

		<div class="container-fluid">
			<div class="row-fluid">

				<div class="btn-toolbar"></div>
				<div class="well">

					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home"
						style="text-align: center;">
							<form method="post"
							action='<c:url value="/ComputerAction?operate=list"></c:url>'>
							<select style="width: 10%" 
									name="brand">
									<option value="">品牌</option>
									<c:forEach items="${bList }" var="brand">
										<option value="${brand.brand_name }">${brand.brand_name }</option>
									</c:forEach>
									</select>
							 颜色:<input type="text" name="color"
								value="${colorName}" style="width: 10%" /> 型号:<input
								type="text" name="model" value="${modelName}" style="width: 10%" />
								<select style="width: 10%" name="status" title="是否上架">
									<option value="">是否上架</option>
									<option value="0">未上架</option>
									<option value="1">已上架</option>
							</select>
							
							&nbsp; 起始<input style="width: 7%;" name="start" type="text" value="${start }"
								id="c10" /> 结束 <input type="text" id="c11" style="width: 7%" value="${over }"
								name="over" />&nbsp; &nbsp;&nbsp; &nbsp;
								 <input type="submit"
								value="查询"
								class="button button-primary button-rounded button-small" />
						</form>
							<div></div>

							<div class="well">
								<table class="table">
									<thead>
										<tr>
											<th>序号</th>
											<th>图片</th>
											<th>品牌</th>
											<th>型号</th>
											<th>颜色</th>
											<th>价格</th>
											<th>售价</th>
											<th>上架时间</th>
											<th>像素</th>
											<th>处理器</th>
											<th>内存</th>
											<th>屏幕尺寸</th>
											<th>库存</th>
											<th colspan="3"><div style="">操作</div></th>

									</tr>
								</thead>
									<c:forEach items="${comList}" var="com" varStatus="status">
										<tbody>

											<tr>
												<td>${(pageNo-1)*pageSize+status.count}</td>
												<td><img width="34" height="34"
													src="<c:if test="${!empty com.imgPath }">
													<c:url value="/upload/${com.imgPath}"></c:url>
												</c:if>
												<c:if test="${empty com.imgPath }">
												upload/default.jpg
												</c:if>"></td>
												<td>${com.brand.brand_name}</td>
												<td>${com.model}</td>
												<td>${com.color}</td>
												<td>￥${com.price}</td>
												<td>￥${com.rel_price}</td>
												<td>${com.reg_date}</td>
												<td>${com.pixels}</td>
												<td>${com.cpu.cpu_name}</td>
												<td>${com.ram}</td>
												<td>${com.screen.screen_name}</td>
												<td>${com.num}</td>
												<td><a
													href="<c:url value="/ComputerAction?operate=changeState&id=${com.com_id}&status=${com.status}"></c:url>">
														<c:if test="${com.status==0}">上架</c:if> <c:if
															test="${com.status==1}">下架</c:if>
												</a> <a
													href=" <c:url value="/ComputerAction?operate=show&id=${com.com_id}"></c:url>">查看</a>
													<a
													href="<c:url value="/ComputerAction?operate=edit&id=${com.com_id}"></c:url>">编辑
												</a></td>
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
												href='<c:url value="/ComputerAction?operate=list&pageNo=1"></c:url>'>首页</a>
										</c:if> <c:if test="${pageNo==1 }">
											<a href="javascript:void(0);" style="color: #cfcfcf">首页</a>
										</c:if></li>
									<li><c:if test="${pageNo!=1 }">

											<a
												href="<c:url value="/ComputerAction?operate=list&pageNo=${pageNo<1?pageNo:pageNo-1}"></c:url>">上一页</a>

										</c:if> <c:if test="${pageNo==1 }">

											<a href="javascript:void(0);" style="color: #cfcfcf;">上一页</a>

										</c:if></li>
									<li><c:if test="${pageNo!=totalPage }">
											<a
												href="<c:url value="/ComputerAction?operate=list&pageNo=${pageNo>totalPage?pageNo:pageNo+1}"></c:url>">下一页</a>
										</c:if> <c:if test="${pageNo==totalPage }">
											<a href="javascript:void(0);" style="color: #cfcfcf;">下一页</a>
										</c:if></li>
									<li><c:if test="${pageNo!=totalPage }">
											<a
												href="<c:url value="/ComputerAction?operate=list&pageNo=${totalPage}"></c:url>">尾页</a>
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

</body>
</html>