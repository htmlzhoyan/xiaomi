<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>我的购物车-小米商城</title>
		<link rel="stylesheet" type="text/css" href="./css/style.css">
		<script src="./js/jquery.min.js"></script>
		<script src="./js/bootstrap.min.js"></script>
		<script type="text/javascript" src="./js/handlebars.min.js"></script>
	</head>
	<body>
	<!-- start header -->
	<!--end header -->

<!-- start banner_x -->
		<div class="banner_x center">
			<a href="./index.html" target="_blank"><div class="logo fl"></div></a>
			
			<div class="wdgwc fl ml40">我的购物车</div>
			<div class="wxts fl ml20">温馨提示：产品是否购买成功，以最终下单为准哦，请尽快结算</div>
			<div class="dlzc fr">
				<ul>
					<li><a href="./login.jsp" target="_blank">登录</a></li>
					<li>|</li>
					<li><a href="./register.jsp" target="_blank">注册</a></li>	
				</ul>
				
			</div>
			<div class="clear"></div>
		</div>
		<div class="xiantiao"></div>
		<div class="gwcxqbj" style="height:1000px;margin-bottom: 200px;
    padding-bottom: 200px;">
			<div class="gwcxd center" id="gouwu">
				
				
			</div>
			<div class="jiesuandan mt20 center">
				<div class="tishi fl ml20">
					<ul>
						<li><a href="./liebiao.html">继续购物</a></li>
						<li>|</li>
						<li>共<span>2</span>件商品，已选择<span>1</span>件</li>
						<div class="clear"></div>
					</ul>
				</div>
				<div class="jiesuan fr">
					<div class="jiesuanjiage fl">合计（不含运费）：<span>2499.00元</span></div>
					<div class="jsanniu fr"><input class="jsan" type="submit" name="jiesuan"  value="去结算"/></div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
			</div>
			
		</div>

  

	
	<!-- footer -->
	<footer class="center">
			
			<div class="mt20">小米商城|MIUI|米聊|多看书城|小米路由器|视频电话|小米天猫店|小米淘宝直营店|小米网盟|小米移动|隐私政策|Select Region</div>
			<div>©mi.com 京ICP证110507号 京ICP备10046444号 京公网安备11010802020134号 京网文[2014]0059-0009号</div> 
			<div>违法和不良信息举报电话：185-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</div>
		</footer>

		<script id="show-gou" type="text/x-handlebars-template">
			<div class="top2 center">
					<div class="sub_top fl">
						<input type="checkbox" value="quanxuan" class="quanxuan" />全选
					</div>
					<div class="sub_top fl">商品名称</div>
					<div class="sub_top fl">单价</div>
					<div class="sub_top fl">数量</div>
					<div class="sub_top fl">小计</div>
					<div class="sub_top fr">操作</div>
					<div class="clear"></div>
				</div>
					 {{#each list}}
				<div class="content2 center">
					<div class="sub_content fl ">
						<input type="checkbox" value="quanxuan" class="quanxuan" />
					</div>
					<div class="sub_content fl"><img style="width: 50px;height: 120px;" src="/upload/{{imgPath}}"></div>
					<div class="sub_content fl ft20">{{comment}}</div>
					<div class="sub_content fl ">{{price}}元</div>
					<div class="sub_content fl">
						<input class="shuliang" type="number" value="{{goodNum}}" step="1" min="1" >
					</div>
					<div class="sub_content fl">{{getinfo goodNum price}}元</div>
					<div class="sub_content fl"><a href="#" onclick="deletess({{id}})">×</a></div>
					<div class="clear"></div>
				</div>
			{{/each}}
		</script>
			<script>
			function findAll(){
				var userhtml = $("#show-gou").html();
				var template = Handlebars.compile(userhtml);
				Handlebars.registerHelper({
					"getinfo":function(a,b){
						return a*b;
					}
				
				})
				$.ajax({
					type:"post",
					url:"${pageContext.request.contextPath}/QueryCart",
					dataType:"json",
					success:function(data){
						if(data.code==1){
							$("#gouwu").html(template({list:data.msg}));
						}else{
							
						}
					}
				})
			}
			$(function(){
				findAll()
			})
			function deletess(id){
				$.ajax({
					type:"get",
					url:"${pageContext.request.contextPath}/deleteCar?id="+id,
					dataType:"json",
					success:function(data){
						if(data.code == 1){
							
							findAll();
						}else{
							
						}
					}
					})
			}
			</script>
		
	</body>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</html>
