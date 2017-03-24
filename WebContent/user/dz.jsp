<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.css" rel="stylesheet">
<link href="<c:url value="/css/city-picker.css"/>" rel="stylesheet">
<link href="<c:url value="/css/main.css"/>" rel="stylesheet">
</head>
<body>
<!-- Content -->
<div class="container">

<h2 class="page-header">演示</h2>

<div class="docs-methods">
	<form class="form-inline">
		<div id="distpicker">
			<div class="form-group">
				<div style="position: relative;" >
					<input  id="city-picker3" class="form-control" readonly type="text" value="江苏省/常州市/溧阳市" data-toggle="city-picker" >
				</div>
			</div>
			<div class="form-group">
				<button class="btn btn-warning" id="reset" type="button">Reset</button>
				<button class="btn btn-danger" id="destroy" type="button">Destroy</button>
			</div>
		</div>
	</form>
</div>
</div>

<script src="<c:url value="/js/jquery.js"/>"></script>
<script src="<c:url value="/js/bootstrap.js"/>"></script>
<script src="<c:url value="/js/city-picker.data.js"/>"></script>
<script src="<c:url value="/js/city-picker.js"/>"></script>
<script src="<c:url value="/js/main.js"/>"></script>
    
</body>
</html>