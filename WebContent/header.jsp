<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
    <!-- start header -->
		<header>
			<div class="top center">
				<div class="left fl">
					<ul>
						<li><a href="http://www.mi.com/" target="_blank">小米商城</a></li>
						<li>|</li>
						<li><a href="">MIUI</a></li>
						<li>|</li>
						<li><a href="">米聊</a></li>
						<li>|</li>
						<li><a href="">游戏</a></li>
						<li>|</li>
						<li><a href="">多看阅读</a></li>
						<li>|</li>
						<li><a href="">云服务</a></li>
						<li>|</li>
						<li><a href="">金融</a></li>
						<li>|</li>
						<li><a href="">小米商城移动版</a></li>
						<li>|</li>
						<li><a href="">问题反馈</a></li>
						<li>|</li>
						<li><a href="">Select Region</a></li>
						<div class="clear"></div>
					</ul>
				</div>
				<div class="right fr">
					<div class="gouwuche fr"><a style="width:100%;height:100%;display:block" href="./gouwuche.jsp">购物车</a></div>
					<div class="fr">
						<ul>
							<c:if test="${not empty user}">
								<li><a href="#" target="_blank">${user.username }</a></li>
								<li>|</li>
								<li><a style="cursor: pointer;" onclick="loginOut()" target="_blank" >退出</a></li>
								<li>|</li>
								<li><a href="./dingdanzhongxin.jsp">消息通知</a></li>
							</c:if>
							<c:if test="${empty user }">
								<li><a href="./login.jsp" target="_blank">登录</a></li>
								<li>|</li>
								<li><a href="./register.jsp" target="_blank" >注册</a></li>
								<li>|</li>
								<li><a href="">消息通知</a></li>
							</c:if>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
			</div>
		</header>
	<!--end header -->

<!-- start banner_x -->
		<div class="banner_x center">
			<a href="./index.html" target="_blank"><div class="logo fl"></div></a>
			<a href=""><div class="ad_top fl"></div></a>
			<div class="nav fl">
				<ul id="secondMenu">
					
				</ul>
			</div>
			<script>
				
				
				</script>
			<script type="text/javascript">
				function loginOut(){
					$.ajax({
						type:'post',
						url:"${pageContext.request.contextPath}/loginout",
						dataType:"json",
						success:function(data){
							if(data.code == 1){
								location.href = "login.jsp"
							}
						}
					})
				}
				function count(){
					$.ajax({
						type:"get",
						url:"${pageContext.request.contextPath}/CarCount",
						dataType:"json",
						success:function(data){
							if(data.code==1){
								$(".gouwuche a").html("购物车("+data.msg+")")
							}
						}
					})
				}
				$(function(){
					// 页面加载完成，加载二级分类
					count();
					$.ajax({
						type:"get",
						url:"${pageContext.request.contextPath}/addGrade?method=searchType&grade=2",
						dataType:"json",
						success:function(data){
							if(data.code == 1){
								var infos = data.msg;
								$(infos).each(function(){
									var html = "<li><a href='./liebiao.jsp?id="+this.id+"' target='_blank'>" + this.gradeName + "</a></li>";
									$("#secondMenu").append($(html));
								
								})
								
							}
						}
					})
				})
			</script>
			
			<div class="search fr">
				<form action="" method="post">
					<div class="text fl">
						<input type="text" class="shuru"  placeholder="小米6&nbsp;小米MIX现货">
					</div>
					<div class="submit fl">
						<input type="submit" class="sousuo" value="搜索"/>
					</div>
					<div class="clear"></div>
				</form>
				<div class="clear"></div>
			</div>
		</div>
<!-- end banner_x -->

