<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="images/favicon.ico" />
<link type="text/css" href="<c:url value="/user/modifyPsw/css/css.css"></c:url>" rel="stylesheet" />
</head>

<body>

	<div class="content">
		<div class="for-liucheng">
			<div class="liulist for-cur"></div>
			<div class="liulist for-cur"></div>
			<div class="liulist for-cur"></div>
			<div class="liulist for-cur"></div>
			<div class="liutextbox">
				<div class="liutext for-cur"><em>1</em><br />
				<strong>填写账户名</strong></div>
				<div class="liutext for-cur"><em>2</em><br />
				<strong>验证身份</strong></div>
				<div class="liutext for-cur"><em>3</em><br />
				<strong>设置新密码</strong></div>
				<div class="liutext for-cur"><em>4</em><br />
				<strong>完成</strong></div>
		</div>
	</div>
	<!--for-liucheng/-->
		<div class="successs">
			<h3>恭喜您，修改成功！</h3>
			<h3><a href='<c:url value="/user/login.jsp"></c:url>'>放回登录</a></h3>
	</div>
</div>
<!--web-width/-->
	<!--content/-->

</body>
</html>