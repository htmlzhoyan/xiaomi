<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>小米商城-个人中心</title>
		<link rel="stylesheet" type="text/css" href="./css/style.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/handlebars.min.js"></script>
	</head>
	<body>
<%@include file="header.jsp"%>

<!-- self_info -->
	<div class="grzxbj">
		<div class="selfinfo center">
		<div class="lfnav fl">
			<div class="ddzx">订单中心</div>
			<div class="subddzx">
				<ul>
					<li><a href="./dingdanzhongxin.html" >我的订单</a></li>
					<li><a href="">意外保</a></li>
					<li><a href="">团购订单</a></li>
					<li><a href="">评价晒单</a></li>
				</ul>
			</div>
			<div class="ddzx">个人中心</div>
			<div class="subddzx">
				<ul>
					<li><a href="./self_info.html" style="color:#ff6700;font-weight:bold;">我的个人中心</a></li>
					<li><a href="">消息通知</a></li>
					<li><a href="">优惠券</a></li>
					<li><a href="">收货地址</a></li>
				</ul>
			</div>
		</div>
		<div class="rtcont fr" id="rightName">
			
			
		</div>
		<div class="clear" id=></div>
		</div>
	</div>
<!-- self_info -->
		
		<footer class="mt20 center">			
			<div class="mt20">小米商城|MIUI|米聊|多看书城|小米路由器|视频电话|小米天猫店|小米淘宝直营店|小米网盟|小米移动|隐私政策|Select Region</div>
			<div>©mi.com 京ICP证110507号 京ICP备10046444号 京公网安备11010802020134号 京网文[2014]0059-0009号</div> 
			<div>违法和不良信息举报电话：185-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</div>
		</footer>
	</body>
	<script id="show-qing" type="text/x-handlebars-template">

			<div class="grzlbt ml40">我的资料</div>
			<div class="subgrzl ml40"><span>昵称</span><span>{{username}}</span></div>
			<div class="subgrzl ml40"><span>手机号</span><span>{{phone}}</span></div>
			<div class="subgrzl ml40"><span>密码</span><span>{{password}}</span></div>
			<div class="subgrzl ml40"><span>性别</span><span>男</span></div>
			<div class="subgrzl ml40"><span>等级</span><span>{{info role}}</span></div>
	</script>
	<script>
		$(function(){
			var userhtml = $("#show-qing").html();
			var template = Handlebars.compile(userhtml);
			 Handlebars.registerHelper("info", function (state) {
				 	if(state==1){
				 		return "管理员"
				 	}else{
				 		return "会员"
				 	}
	       			
			 })
			$.ajax({
				type:"get",
				url:"${pageContext.request.contextPath}/ShowUser",
				dataType:"json",
				success:function(data){
					if(data.code == 1){	
						console.log(data.msg)
						$("#rightName").html(template(data.msg));
					}else{
						
						alert(data.msg)
					}
					
				}	
			})
		})
	</script>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</html>