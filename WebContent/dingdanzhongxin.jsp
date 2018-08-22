<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>小米商城-个人中心</title>
		<link rel="stylesheet" type="text/css" href="./css/style.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="./js/handlebars.min.js"></script>
		<style>
			.ddbh{
				 white-space: nowrap;
                 text-overflow: ellipsis;
                 overflow: hidden;
                 width:200px;
                 line-height:100px;
			}
			#orderInfo{
				overflow-y:scroll;
			}
			
		</style>
	</head>
	<body>
	<%@include file="header.jsp" %>
	
<!-- self_info -->
	<div class="grzxbj">
		<div class="selfinfo center">
		<div class="lfnav fl">
			<div class="ddzx">订单中心</div>
			<div class="subddzx">
				<ul>
					<li><a href="" style="color:#ff6700;font-weight:bold;">我的订单</a></li>
					<li><a href="">意外保</a></li>
					<li><a href="">团购订单</a></li>
					<li><a href="">评价晒单</a></li>
				</ul>
			</div>
			<div class="ddzx">个人中心</div>
			<div class="subddzx">
				<ul>
					<li><a href="./self_info.jsp">我的个人中心</a></li>
					<li><a href="">消息通知</a></li>
					<li><a href="">优惠券</a></li>
					<li><a href="">收货地址</a></li>
				</ul>
			</div>
		</div>
		<div class="rtcont fr" id="orderInfo">
		
			
		</div>
		<div class="clear"></div>
		</div>
	</div>
<!-- self_info -->
		
		<footer class="mt20 center">			
			<div class="mt20">小米商城|MIUI|米聊|多看书城|小米路由器|视频电话|小米天猫店|小米淘宝直营店|小米网盟|小米移动|隐私政策|Select Region</div>
			<div>©mi.com 京ICP证110507号 京ICP备10046444号 京公网安备11010802020134号 京网文[2014]0059-0009号</div> 
			<div>违法和不良信息举报电话：185-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</div>
		</footer>
		<script id="showBox" type="text/x-handlebars-template">

			<div class="ddzxbt">交易订单</div>
			 {{#each list}}
				
			<div class="ddxq">
				<div class="ddspt fl"><img style="width: 80px;height: 80px;" src="/upload/{{imgPath}}" alt=""></div>
				<div class="ddbh fl">订单号:{{orderCode}}</div>
				<div class="ztxx fl">
					<ul><li>{{{getInfo status id}}}</li>						
						<li>￥{{money}}</li>
						<li style="font-size:10px">{{createDate}}</li>
						<li style="cursor: pointer;" onclick="detailMethod()">个人详情</li>
						<div class="clear"></div>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			{{/each}}
		</script>
		
		<script type="text/javascript">
			function detailMethod(){
				location.href="self_info.jsp";
			}
			function isClick(state,id){
				
				if(state==1){
					state = 2
				}else if(state ==2){
					state = 3
				}else if(state ==3){
					state = 4
				}else if(state ==4){
					state = 5
				}
				
				$.ajax({
					type:"get",
					url:"${pageContext.request.contextPath}/UploadOrder?state="+state+"&id="+id,
					dataType:"json",
					success:function(data){
						if(data.code == 1){					
							findAll()
						}
						
					}
				})
			}
			function findAll(){
				var userhtml = $("#showBox").html();
				var template = Handlebars.compile(userhtml);
				Handlebars.registerHelper("getInfo", function (state,id) {
					var html = '';
					switch(state){
					case 1:
						html += "<b>未付款</b><button onclick='isClick("+state+","+id+")'>付款</button>";
						break;
					case 2:
						html += "<b>未发货</b><button onclick='isClick("+state+","+id+")'>取消订单</button>";
						break;
					case 3:
						html += "<b>未收货</b><button onclick='isClick("+state+","+id+")'>确认收货</button>";
						break;
					case 4:
						html += "<b>已收货</b><button onclick='isClick("+state+","+id+")'>评价</button>";
						break;
					case 5:
						html += "<b>订单完成</b>";
						break;
					}
					return html;
			 	})
				$.ajax({
					type:"get",
					url:"${pageContext.request.contextPath}/queryOrder",
					dataType:"json",
					success:function(data){
						if(data.code == 1){
							var infos = data.msg;				
							$("#orderInfo").html(template({list:data.msg}));
								
						}
					}
				})
			}
			$(function(){
				findAll()
			})
		</script>
		
	</body>
</html>