<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>小米手机列表</title>
		<link rel="stylesheet" type="text/css" href="./css/style.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="./js/handlebars.min.js"></script>
	</head>
	<body>
	<!-- start header -->
	<%@include file="header.jsp"%>
	<!--end header -->

	<!-- start banner_y -->
	<!-- end banner -->

	<!-- start danpin -->
		<div class="danpin center" id="gouwuc">

		</div>
		

		<footer class="mt20 center">			
			<div class="mt20">小米商城|MIUI|米聊|多看书城|小米路由器|视频电话|小米天猫店|小米淘宝直营店|小米网盟|小米移动|隐私政策|Select Region</div>
			<div>©mi.com 京ICP证110507号 京ICP备10046444号 京公网安备11010802020134号 京网文[2014]0059-0009号</div> 
			<div>违法和不良信息举报电话：185-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</div>

		</footer>

	<!-- end danpin -->


	</body>
	<script id="show-gous" type="text/x-handlebars-template">
			<div class="biaoti center">{{gradeName}}</div>
			<div class="main center">
				
			{{#each goodsList}}
				<div class="mingxing fl mb20" style="border:2px solid #fff;width:230px;cursor:pointer;" onmouseout="this.style.border='2px solid #fff'" onmousemove="this.style.border='2px solid red'">
					<div class="sub_mingxing"><a href="xiangqing.jsp?name={{goodsName}}"><img src="/upload/{{imgPath}}" alt=""></a></div>
					<div class="pinpai"><a href="">{{goodsName}}</a></div>
					<div class="youhui">{{comment}}</div>
					<div class="jiage">{{price}}元</div>
			</div>
				{{/each}}
			<div class="clear"></div>
		</div>
	
	</script>
	
	<script>
		$(function(){
			var userhtml = $("#show-gous").html();
			var template = Handlebars.compile(userhtml);
			$.ajax({
				type:'get',
				url:"${pageContext.request.contextPath}/QueryGradeServlet?grade=2&gradeId=${param.id}",
				dataType:"json",
				success:function(data){
					list = data.msg[0];
					console.log(list)
					$("#gouwuc").html(template(list));
				}
			})
		})
	
	
	</script>
	
	
	
	
	
	
	
	
	
</html>