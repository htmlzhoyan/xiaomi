<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>小米6立即购买-小米商城</title>
		<link rel="stylesheet" type="text/css" href="./css/style.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/handlebars.min.js"></script>
	</head>
	<body>
	<%@include file="header.jsp"%>
	<!-- xiangqing -->
	<form action="post" method="">
	<div class="xiangqing">
		<div class="neirong w">
			<div class="xiaomi6 fl">${param.name}</div>
			<nav class="fr">
				<li><a href="">概述</a></li>
				<li>|</li>
				<li><a href="">变焦双摄</a></li>
				<li>|</li>
				<li><a href="">设计</a></li>
				<li>|</li>
				<li><a href="">参数</a></li>
				<li>|</li>
				<li><a href="">F码通道</a></li>
				<li>|</li>
				<li><a href="">用户评价</a></li>
				<div class="clear"></div>
			</nav>
			<div class="clear"></div>
		</div>	
	</div>
	
	<div class="jieshao mt20 w">
		<div class="left fl"><img id="imgName" style="width:560px;height:100%" src="./image/liebiao_xiaomi6.jpg"></div>
		<div class="right fr" id="rightName">
			
		</div>
		<div class="clear"></div>
	</div>
	</form>
	<!-- footer -->
	<footer class="mt20 center">
			
			<div class="mt20">小米商城|MIUI|米聊|多看书城|小米路由器|视频电话|小米天猫店|小米淘宝直营店|小米网盟|小米移动|隐私政策|Select Region</div>
			<div>©mi.com 京ICP证110507号 京ICP备10046444号 京公网安备11010802020134号 京网文[2014]0059-0009号</div> 
			<div>违法和不良信息举报电话：185-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</div>

		</footer>

	</body>
	<script id="show-qing" type="text/x-handlebars-template">
	
		<div class="h3 ml20 mt20">{{goodsName}}</div>
			<div class="jianjie mr40 ml20 mt10">{{comment}}</div>
			<div class="jiage ml20 mt10">2499.00元</div>
			<div class="ft20 ml20 mt20">选择版本</div>
			<div class="ft20 ml20 mt20">
			<span>购买数量</span>
			<input id="nums" style="width:30px;height:30px;text-align:center;" type="text" value="1"/>
			<div>
			<div class="xqxq mt20 ml20">
				<div class="top1 mt10">
					<div class="left1 fl">{{goodsName}}</div>
					<div class="right1 fr">{{price}}元</div>
					<div class="clear"></div>
				</div>
				<div class="bot mt20 ft20 ftbc">总计：2499元</div>
			</div>
			<div class="xiadan ml20 mt20">
					<input onclick="btnClick({{id}},'{{imgPath}}')" class="jrgwc"  type="button" name="buy" value="立即选购" />
					<input onclick="btnGou({{id}},{{gradeId}})" class="jrgwc" type="button" name="cart" value="加入购物车" />
				
			</div>
	
	</script>
	<script>
	
		$(function(){
			
			
			var userhtml = $("#show-qing").html();
			var template = Handlebars.compile(userhtml);
			$.ajax({
				type:"get",
				url:"${pageContext.request.contextPath}/QueryGoods?name=${param.name}&method=findName",
				dataType:'json',
				success:function(data){
					if(data.code == 1){
						$("#rightName").html(template(data.msg));
						console.log(data.msg)				
						$("#imgName").attr("src","/upload/"+data.msg.imgPath);
					}
				}
				
			})
			
			
		})
		function btnGou(id,grade){
			var nums = $("#nums").val();
			$.ajax({
				type:"get",
				url:"${pageContext.request.contextPath}/AddCartServlet",
				data:{goodsId:id, num:nums,gradeId:grade},
				dataType:"json",
				success:function(data){
					if(data.code == 1){
						alert("加入购车车成功");
						location.reload();
					}else{
						alert(data.msg);
					}
				}
			})
		}
		function btnClick(id,imgPaths){
			var nums = $("#nums").val();
			$.ajax({
				type:"get",
				url:"${pageContext.request.contextPath}/AddOrderServlet",
				data:{goodsId:id, num:nums,imgPath:imgPaths},
				dataType:"json",
				success:function(data){
					if(data.code == 1){
						window.location.href = "dingdanzhongxin.jsp";
					}else{
						alert(data.msg);
					}
				}
			})
		}
	</script>
	
	
	
	
	
	
	
	
	
	
	
</html>