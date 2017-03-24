<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href='http://fonts.googleapis.com/css?family=Exo+2'
	rel='stylesheet' type='text/css'>
<script type="text/javascript" src='<c:url value="/js/jquery1.min.js"></c:url>'></script>
<!--  -->
<link href="css/megamenu.css" rel="stylesheet" type="text/css"
	media="all" />
<script type="text/javascript" src="js/megamenu.js"></script>
<script>
	$(document).ready(function() {
		$(".megamenu").megamenu();
	});
</script>
<!-- dropdown -->
<script src="<c:url value="/js/jquery.easydropdown.js"/>"></script>

<script language=javascript>
	//CharMode函数
	//测试某个字符是属于哪一类.
	function CharMode(iN) {
		if (iN >= 48 && iN <= 57) //数字
			return 1;
		if (iN >= 65 && iN <= 90) //大写字母
			return 2;
		if (iN >= 97 && iN <= 122) //小写
			return 4;
		else
			return 8; //特殊字符
	}
	//bitTotal函数
	//计算出当前密码当中一共有多少种模式
	function bitTotal(num) {
		modes = 0;
		for (i = 0; i < 4; i++) {
			if (num & 1)
				modes++;
			num >>>= 1;
		}
		return modes;
	}
	//checkStrong函数
	//返回密码的强度级别
	function checkStrong(sPW) {
		if (sPW.length <= 4)
			return 0; //密码太短
		Modes = 0;
		for (i = 0; i < sPW.length; i++) {
			//测试每一个字符的类别并统计一共有多少种模式.
			Modes |= CharMode(sPW.charCodeAt(i));
		}
		return bitTotal(Modes);
	}
	//pwStrength函数
	//当用户放开键盘或密码输入框失去焦点时,根据不同的级别显示不同的颜色
	function pwStrength(pwd) {
		O_color = "#eeeeee";
		L_color = "#FF0000";
		M_color = "#FF9900";
		H_color = "#33CC00";
		if (pwd == null || pwd == '') {
			Lcolor = Mcolor = Hcolor = O_color;
		} else {
			S_level = checkStrong(pwd);
			switch (S_level) {
			case 0:
				Lcolor = Mcolor = Hcolor = O_color;
			case 1:
				Lcolor = L_color;
				Mcolor = Hcolor = O_color;
				break;
			case 2:
				Lcolor = Mcolor = M_color;
				Hcolor = O_color;
				break;
			default:
				Lcolor = Mcolor = Hcolor = H_color;
			}
		}
		document.getElementById("strength_L").style.background = Lcolor;
		document.getElementById("strength_M").style.background = Mcolor;
		document.getElementById("strength_H").style.background = Hcolor;
		return;
	}
</script>
<script src="<c:url value="/lib/bootstrap/js/bootstrap.js"/>"></script>

<%@include file="/nav/basion.jsp"%>
<body>
	<div class="cssmenu">
		<ul>
			<c:if test="${!empty sessionScope.userInfo }">
				<li><a
					href="<c:url value="/UserAction?operate=modify&id=${sessionScope.userInfo.user_id}"></c:url>"><span></span>${sessionScope.userInfo.user_name}</a></li>
				<li><a
					href="<c:url value="/UserInfoController?operate=listOrder"></c:url>"><span></span>个人订单</a></li>
				<li><a href="<c:url value="/user/modifyPsw.jsp"></c:url>"><span></span>修改密码</a></li>
				<li><a href="<c:url value="/UserAction?operate=quit"></c:url>"><span></span>退出</a></li>
			</c:if>
			<c:if test="${empty sessionScope.userInfo }">
				<li><a href="<c:url value="/user/login.jsp"></c:url>"><span></span>登陆</a></li>
				<li><a href="<c:url value="/user/register.jsp"></c:url>"><span></span>注册</a></li>
			</c:if>

	</ul>
</div>
	<div class="login">
		<div class="wrap">

			<div class="col_1_of_login span_1_of_login">
				<div class="login-title">
					<h4 class="title">修改密码</h4>
					<div id="loginbox" class="loginbox">
						<form onsubmit="return confim();"
						action='<c:url value="/UserAction?operate=modifyPsw"></c:url>'
						method="post" name="login" id="login-form">
							<fieldset class="input"><input type="hidden"
								name="userName" value="${sessionScope.userInfo.user_name }"
								readonly="readonly">
								<p id="login-form-password"><label for="modlgn_passwd">
										输入原来密码:</label> <input id="modlgn_passwd" type="password"
									name="oldPsw" class="inputbox" size="10" required
									autocomplete="off"></p> <label for="modlgn_passwd">新密码</label>
								<input type="text" name="newPsw" placeholder="请输入新密码" required id="p1"
								onBlur=pwStrength(this.value) class="inputbox" size="10"
								onKeyUp=pwStrength(this.value) class="input-xlarge"><label
								for="modlgn_passwd">请在一次输入新密码</label> 
							
								
								<input type="text" id="p2"
								name="againPsw" placeholder="请再次输入新密码" required class="inputbox" onblur="confim();"
								size="10" class="input-xlarge">
									<script type="text/javascript">
								function confim() {
									var p1 = document.getElementById("p1").value;
									var p2 = document.getElementById("p2").value;
									if(p1!=p2){
										alert("两次密码不一样，请重新输入！");
										return false;
									}

								}
								
								</script>
								<div class="remember"><input type="submit" name="Submit"
									class="button" value="update"> <br>密码强度:
									<table width="217" border="1" cellspacing="3" cellpadding="3"
									bordercolor="#cccccc" height="23" style='display: inline'>
										<tr align="center" bgcolor="#eeeeee">
											<td width="33%" id="strength_L">弱</td>
											<td width="40%" id="strength_M">中</td>
											<td width="40%" id="strength_H">强</td>
									</tr>
								</table></div> <span style="color: red;">${msg}</span></fieldset>
					</form>
				</div>
			</div>
		</div>
			<div class="clear"></div>
	</div>
</div>
	
	<div class="footer-top">
		<div class="wrap">
			<div class="section group example">
				<div class="col_1_of_2 span_1_of_2">
					<ul class="f-list">
						<li><img src='<c:url value="/images/2.png"></c:url>'><span
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
			<center><a href="<c:url value="/sign-in.jsp"/>" style="color: white;">web后台管理登录</a></center>
			<div class="clear"></div>
	</div>
</div>

	<div style="display: none"></div>

</body>
</html>