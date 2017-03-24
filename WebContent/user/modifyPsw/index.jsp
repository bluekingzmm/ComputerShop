<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css"
	href='<c:url value="/user/modifyPsw/css/css.css"></c:url>'
	rel="stylesheet" />
<script language="javascript">
	function myReload() {
		document.getElementById("CreateCheckCode").src = document
				.getElementById("CreateCheckCode").src
				+ "?nocache=" + new Date().getTime();
	}
</script>
</head>
<body>

	<div class="content">
		<div class="web-width">
			<div class="for-liucheng">
				<div class="liulist for-cur"></div>
				<div class="liulist"></div>
				<div class="liulist"></div>
				<div class="liulist"></div>
				<div class="liutextbox">
					<div class="liutext for-cur"><em>1</em><br /> <strong>填写账户名</strong></div>
					<div class="liutext"><em>2</em><br /> <strong>验证身份</strong></div>
					<div class="liutext"><em>3</em><br /> <strong>设置新密码</strong></div>
					<div class="liutext"><em>4</em><br /> <strong>完成</strong></div>
			</div>
		</div> <!--for-liucheng/-->
			<form action='<c:url value="/UserAction?operate=modifyPsw1"></c:url>'
			method="post" class="forget-pwd">
				<dl>
					<dt>用户名：</dt>
					<dd><input type="text" required name="userName" /></dd>
					<div class="clears"></div>
			</dl>
				<dl>
					<dt>验证码：</dt>
					<dd><input type="text" placeholder="输入验证码" maxlength="100"
						id="checkCode" name="checkCode" nullmsg="请输入验证码！" required />
						<div class="yanzma"><img id="CreateCheckCode"
							src='<c:url value="/UserAction?operate=yzm"></c:url>'
							class="yzm-img" /><a onclick="myReload()" href=""><br />看不清？换一张试试</a></div></dd>
					<div class="clears"></div>
			</dl>
				<div class="subtijiao"><input type="submit" value="提交" /></div> <br />
				<span style="color: red;">${msg}</span>
		</form> <!--forget-pwd/-->
	</div> <!--web-width/-->
</div>
	<!--content/-->


</body>
</html>