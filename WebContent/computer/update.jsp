<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改商品</title>
<link rel="stylesheet" type="text/css"
	href="lib/bootstrap/css/bootstrap.css">
<script src="<c:url value="/lib/jquery-1.7.2.min.js"/>" type="text/javascript"></script>
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

			<h1 class="page-title">Computer</h1>
	</div>

		<ul class="breadcrumb">
			<li><a href="index.html">Home</a> <span class="divider">/</span></li>
			<li class="active">Computer</li>
	</ul>

		<div class="container-fluid">

			<div class="">
				<form method="post"
				action='<c:url value="/ComputerAction?operate=update"></c:url>'>
				<input type="hidden" name="id" value="${com.com_id }"/>
					<table>
						<tr><td>品牌名称</td>
							<td><select name="brandName">
									<option value="">==请选择==</option>
									<c:forEach items="${bList }" var="b">
										<option value="${b.brand_id }" ${b.brand_id==com.brand.brand_id? "selected":"" }>${b.brand_name}</option>
									</c:forEach>
							</select></td></tr>
						<tr><td>电脑型号：</td>
							<td><input type="text" name="model" required value="${com.model }"></td></tr>
						<tr><td>颜色：</td>
							<td><input type="text" name="color" required value="${com.color }"></td></tr>
						<tr><td>价格：</td>
							<td><input type="text" name="price" required value="${com.price }"></td></tr>
						<tr><td>售价：</td>
							<td><input type="text" name="relPrice" required value="${com.rel_price }"></td></tr>
						<tr><td>像素：</td>
							<td><input type="text" name="pixels" required value="${com.pixels }"></td></tr>
						<tr><td>处理器：</td>
							<td><select name="cpuName">
									<option value="">==请选择==</option>
									<c:forEach items="${cList }" var="c">
										<option value="${c.cpu_id }"${c.cpu_id ==com.cpu.cpu_id?"selected" :""}>${c.cpu_name}</option>
									</c:forEach>
							</select></td></tr>
						<tr><td>屏幕尺寸：</td>
							<td><select name="screenName">
									<option value="">==请选择==</option>
									<c:forEach items="${sList }" var="s">
										<option value="${s.screen_id }"${s.screen_id==com.screen.screen_id?"selected":"" }>${s.screen_name}</option>
									</c:forEach>
							</select></td></tr>
						<tr><td>内存：</td>
							<td><input type="text" name="ram" required value="${com.ram }"></td></tr>
							<tr><td>库存：</td>
							<td><input type="text" name="num" required value="${com.num}"></td></tr>
							<tr><td>评价：</td>
							<td>
							<input name="content" value="${com.content}"/>
						<tr><td>上传图片： <input type="hidden" name="imgPath"
								id="imgPath" />
						</td>
							<td><img id="photo" width="300" height="300"
								src="<c:if test="${!empty com.imgPath }">
							<c:url value="/upload/${com.imgPath}"></c:url>
							</c:if> 				
								<c:if test="${empty com.imgPath }">
							upload/default.jpg							
							</c:if>				
							" />
								<a href="#" onclick="upload();">上传</a></td></tr>



				</table>
					<fieldset><span>

							<button class="btn btn-primary" type="submit"  onclick="javascript:history.back(-1)" ><i
								class="icon-plus"></i>返回 </button>
							<button class="btn btn-primary"type="submit"><i
								class="icon-plus"></i> 修改</button>

					</span> <span>${msg }</span>

				</fieldset>

			</form>

		</div>
	</div>
</div>


	<script type="text/javascript">
		function upload() {
			window.open('<c:url value='computer/upload.jsp' />', "",
					"width=450,height=350")
		}
	</script>
</body>
</html>