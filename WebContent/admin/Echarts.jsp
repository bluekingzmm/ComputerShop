<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>销售图标</title>

<link rel="stylesheet" type="text/css"
	href='<c:url value="/css/button.css"></c:url>'>
    <script src='<c:url value="/js/echarts.min.js"></c:url>' type="text/javascript"></script>
   <script src="<c:url value="/lib/jquery-1.7.2.min.js"/>" type="text/javascript"></script>
    
</head>
<%@include file="/admin/Basion.jsp"%>
<body>
<div class="content">

		<div class="header">

			<h1 class="page-title">销售列表</h1>
	</div>

		<ul class="breadcrumb">
			<li><a href="#">Home</a> <span class="divider">/</span></li>
			<li class="active">Sale</li>
	</ul>

		<div class="container-fluid">
			<div class="row-fluid">


				

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 900px;height:500px;"></div>
    <script type="text/javascript">
     //基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

       // 指定图表的配置项和数据
        var option = {
            title: {
                text: '电脑销售排名'
            },
            tooltip: {},
            legend: {
                data:['销量前4排名']
            },
            xAxis: {
                data: ["戴尔灵越15(7559)","戴尔灵越Ins15","惠普光影精灵","华硕 FX50JX"]
            },
            yAxis: {},
            series: [{
                name: '销量前4排名',
                type: 'bar',
                data: [4, 4, 2, 1]
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
		</div>

	</div> 
		
<input onclick="javascript:history.back(-1);"
									class="button button-primary button-rounded button-small"
									value="返回" type="button" />
</div>

</body>
</html>