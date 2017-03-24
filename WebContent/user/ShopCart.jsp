<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>
<link href='<c:url value="/css/button.css"></c:url>' rel="stylesheet"
	type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href='http://fonts.googleapis.com/css?family=Exo+2'
	rel='stylesheet' type='text/css'>

<script type="text/javascript" src="js/jquery.1.4.2-min.js"></script>
<script type="text/javascript" src="js/Calculation.js"></script>
<script type="text/javascript" src="js/js.js"></script>

<!-- start menu -->
<link href="css/megamenu.css" rel="stylesheet" type="text/css"
	media="all" />

<link rel="stylesheet" type="text/css"
	href='<c:url value="/css/index.css"></c:url>'>


</head>
<%@include file="/nav/basion.jsp"%>
<body>
	<c:if test="${empty sessionScope.userInfo }">
		<script type="text/javascript">
			alert("请你先登录！才能查看购物车信息");
			window.location.href = '<c:url value="FirstAction"/>';
		</script>

	</c:if>
	<div class="gwc" style="margin: auto;">
		<table cellpadding="0" cellspacing="0" class="gwc_tb1">
			<tr>
				<!-- <td class="tb1_td1"><input id="Checkbox1" type="checkbox"
					class="allselect" /></td> -->
				<!-- 	<td class="tb1_td1">全选</td> -->
				<td class="tb1_td5">电脑型号</td>
				<td class="tb1_td3">商品</td>
				<td class="tb1_td3">商品信息</td>
				<td class="tb1_td6">单价</td>
				<td class="tb1_td6">购买数量</td>
				<td class="tb1_td6">总价</td>
				<td class="tb1_td6">库存</td>
				<td class="tb1_td6">操作</td>
				<td class="tb1_td6">操作</td>
		</tr>
	</table> <c:forEach items="${ls }" var="ls">
			<form action="<c:url value="/OrderAction?operate=addOne" ></c:url>"
				method="post"><input type="hidden" name="userId"
				value="${sessionScope.userInfo.user_id }" />
				<input type="hidden" name="id" value="${ls.com.com_id }"/>
				<table cellpadding="0" cellspacing="0" class="gwc_tb2">
					<tr>
						<td class="tb2_td2">${ls.com.model}</td>
						<td class="tb2_td2"><a href="#"> <img
								src="
			<c:if test="${!empty ls.com.imgPath }">
			<c:url value="/upload/${ls.com.imgPath }"/>
			
			</c:if>
			
			<c:if test="${empty ls.com.imgPath }">
			/upload/default.jpg
			
			</c:if>" />
			

						</a></td>
						<td class="tb1_td4">一件</td>
						<td class="pices" style="padding-left: 20px;">${ls.com.rel_price }</td>

						<td class="num" style="padding-left: 60px; width: 100px;">
							<%-- <form method="post"
						action="<c:url value="/ShopCartAction?operate=updateCount"></c:url>">
							<input type="hidden"  name="comId" value="${ls.com.com_id }" /> 
							<input
							type="text" name="count" value="${ls.count }"
							onkeyup="value=this.value.replace(/\D+/g,'')" id="count" />
							 <span>&nbsp;<input
								type="submit" value="更改" /></span>
					</form> --%> <c:if test="${ls.count!=1 }">

								<a style="font-size: 24px;"
									href="<c:url value="/ShopCartAction?operate=updateCount&id=${ls.com.com_id}&count=${ls.count<1?ls.count:ls.count-1}"></c:url>">-</a>

							</c:if> <c:if test="${ls.count==1 }">

								<a href="javascript:void(0);"
									style="color: #cfcfcf; font-size: 18px;">-</a>

							</c:if> &nbsp;&nbsp;&nbsp; <c:if test="${ls.count!=0 }">
								<a style="font-size: 18px;" href='#'>${ls.count}</a>
							</c:if> &nbsp;&nbsp;&nbsp; <c:if test="${ls.count!=totalCount}">
								<a style="font-size: 24px;"
									href="<c:url value="/ShopCartAction?operate=updateCount&id=${ls.com.com_id}&count=${ls.count>totalCount?ls.count:ls.count+1}"></c:url>">+</a>
							</c:if> <c:if test="${ls.count==totalCount}">
								<a href="javascript:void(0);"
									style="color: #cfcfcf; font-size: 18px;">+</a>
							</c:if>


					</td>

						<!--onkeyup 键盘按钮下松开触发的事件  -->
						<td class="totle" style="padding-left: 50px;"><input
							name="tPrice" value="${ls.count*ls.com.rel_price }"
							style="width: 90%;" /></td>
						<td class="totle" style="padding-left: 60px;"><input
							readonly="readonly" type="text" name="num" value="${ls.com.num}"
							style="width: 80%;" /></td>
						<td class="tb1_td5" style="padding-left: 50px;">
							<div class="del" style="padding-left: 20px;"><a
								href="<c:url value="/ShopCartAction?operate=delete&id=${ls.com.com_id}"/>">删除</a>

						</div>
					</td>
						<td><input type="submit" value="结算"/></td>

				</tr>

			</table></form>
		</c:forEach>

		<table cellpadding="0" cellspacing="0" class="gwc_tb3">
			<tr>

				<td><a
					style="color: #ff5500; font-size: 18px; font-weight: bold;"
					href='<c:url value="/ShopCartAction?operate=clearShopCart"></c:url>'>清空购物车</a></td>
				<td class="tb3_td4"><c:if test="${empty ls }">
						<p>当前购物车没有商品</p>
						<p><a href="<c:url value="ComputerAction?operate=listCom"/>">返回重新购物</a></p>
					</c:if></td>

				<td class="tb3_td3">合计<span>￥</span><span>${totalPrice}</span></td>

				<td class="tb3_td4"><c:if test="${!empty ShopCart }">
						<a class="button button-glow button-rounded button-caution"
							href="<c:url value="/OrderAction?operate=add" ></c:url>">全部结算</a>

					</c:if></td>
		</tr>
	</table> <span>${msg}</span> <br>


</div>


	<br></br>

	<br></br>
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

</body>
</html>