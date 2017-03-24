<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href='<c:url value="/lib/bootstrap/css/bootstrap.css"></c:url>'>
<link rel="stylesheet" type="text/css"
	href='<c:url value="/stylesheets/theme.css"></c:url>'>
<link rel="stylesheet" type="text/css"
	href='<c:url value="/lib/font-awesome/css/font-awesome.css"></c:url>'>
</head>
<body>
	<div class="navbar">
		<div class="navbar-inner">
			<ul class="nav pull-right">

		</ul> <a class="brand" href="admin/index.jsp"><span class="first">Your</span>
				<span class="second">Company</span></a>
	</div>
</div>

	<div class="row-fluid">
		<div class="dialog">
			<div class="block">
				<p class="block-heading">Sign Up</p>
				<div class="block-body">
					<form method="post"
				action='<c:url value="/AdminAction?operate=add"></c:url>'><label>Admin Name</label> <input
						name="adminName" required type="text" class="span12">  <label>Password</label> <input
						name="adminPassword" type="password" class="span12"> 
						<button class="btn btn-primary" class="remember-me" class="btn btn-primary pull-right" type="submit"><i
								class="icon-plus"></i> 注 册!</button>
						</form>
			</div>
		</div>
			
	</div>
</div>

</body>
</html>