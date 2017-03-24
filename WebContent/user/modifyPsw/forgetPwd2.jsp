<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="images/favicon.ico" />
<link type="text/css"
	href="<c:url value="/user/modifyPsw/css/css.css"></c:url>"
	rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.8.3-min.js"></script>
<script type="text/javascript">
	//导航定位
	$(function() {
		// $(".nav li:eq(2) a:first").addClass("navCur")
		//验证手机 邮箱 var selval = $(this).find("option:selected").val();
			if (selval == "0") {
				$(".sel-yzsj").show()
				$(".sel-yzyx").hide()
			} else if (selval == "1") {
				$(".sel-yzsj").hide()
				$(".sel-yzyx").show()
			}
		})
	})
</script>
</head>

<body>

	<div class="content">
		<div class="web-width">
			<div class="for-liucheng">
				<div class="liulist for-cur"></div>
				<div class="liulist for-cur"></div>
				<div class="liulist"></div>
				<div class="liulist"></div>
				<div class="liutextbox">
					<div class="liutext for-cur"><em>1</em><br /> <strong>填写账户名</strong></div>
					<div class="liutext for-cur"><em>2</em><br /> <strong>验证身份</strong></div>
					<div class="liutext"><em>3</em><br /> <strong>设置新密码</strong></div>
					<div class="liutext"><em>4</em><br /> <strong>完成</strong></div>
			</div>
		</div> 
			<form action='<c:url value="/UserAction?operate=modifyPsw2"></c:url>' method="post" class="forget-pwd">
				<dl>
					<dt>验证方式：</dt>
					<dd><select class="selyz">
							<option value="0">已验证邮箱</option>
					</select></dd>
					<div class="clears"></div>
			</dl>
				<dl>
					<dt>用户名：</dt>
					<dd><input type="text" value="${sessionScope.userName }" 
						readonly="readonly" name="userName" /></dd>
					<div class="clears"></div>
			</dl>

				<dl class="sel-yzyx">
					<dt>请输入邮箱：</dt>
					<dd><input type="text" name="mail" /></dd>
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