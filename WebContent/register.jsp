<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>用户注册</title>
		<link rel="stylesheet" type="text/css" href="./css/login.css">
		<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
		<script src="./js/jquery.min.js"></script>
		<script src="./js/bootstrap.min.js"></script>
		<script type="text/javascript" src="./js/handlebars.min.js"></script>
		<script src="./js/jquery.validate.min.js"></script>
	</head>
	<style>
		.username .error{
			color:red;
			margin-left:10px;
		}
	</style>
	
	<body>
		<form  method="post" onsubmit="return false">
		<div class="regist">
			<div class="regist_center">
				<div class="regist_top">
					<div class="left fl">会员注册</div>
					<div class="right fr"><a href="./index.jsp" target="_self">小米商城</a></div>
					<div class="clear"></div>
					<div class="xian center"></div>
				</div>
				<div class="regist_main center">
					<div class="username">用&nbsp;&nbsp;户&nbsp;&nbsp;名:&nbsp;&nbsp;<input class="shurukuang" type="text" name="username" placeholder="请输入你的用户名"/><span>请不要输入汉字</span></div>
					<div class="username">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;&nbsp;<input id="password" class="shurukuang" id="" type="password" name="password" placeholder="请输入你的密码"/><span>请输入6位以上字符</span></div>
					
					<div class="username">确认密码:&nbsp;&nbsp;<input class="shurukuang"  type="password" name="repassword" placeholder="请确认你的密码"/><span>两次密码要输入一致哦</span></div>
					
					<div class="radio">
						  <label>
						    <input type="radio" name="sex" id="optionsRadios1" value="男" checked>
						    男
						  </label>
						</div>
						<div class="radio">
						  <label>
						    <input type="radio" name="sex" id="optionsRadios2" value="女">
						   女
						  </label>
						</div>
					
					<div class="username">手&nbsp;&nbsp;机&nbsp;&nbsp;号:&nbsp;&nbsp;<input class="shurukuang" id="phone" type="text" name="phone" placeholder="请填写正确的手机号"/><span>填写下手机号吧，方便我们联系您！</span></div>
					<div class="username">
						<div class="left fl">验&nbsp;&nbsp;证&nbsp;&nbsp;码:&nbsp;&nbsp;<input class="yanzhengma" type="text" name="vlidateCode" placeholder="请输入验证码"/></div>
						<div class="right fl"><img src="createCode" style="cursor: pointer;" id="codeImg"></div>
						<div class="clear"></div>
					</div>
				</div>
				<div class="regist_submit">
					<input class="submit" type="submit" name="submit" value="立即注册" >
				</div>
				
			</div>
		</div>
		</form>
		<script>
			$(function(){
			$("#codeImg").click(function(){
				$("#codeImg").attr("src","createCode?r="+Math.random())
			})
			 jQuery.validator.addMethod("isMobile", function(value, element) {
			        var length = value.length;
			        var mobile = /^1[34578]\d{9}$/;/*/^1(3|4|5|7|8)\d{9}$/*/
			        return this.optional(element) || (length == 11 && mobile.test(value));
			    }, "请正确填写您的手机号码");
				$("form").validate({	
					submitHandler:function(){
						$.ajax({
							type:'post',
							url:"${pageContext.request.contextPath}/userRegister",
							data:$("form").serialize(),
							dataType:"json",
							success:function(data){
								if(data.code==1){
									window.location.href="login.jsp"
				
								}
								else{
									alert(data.msg)
								}
								
							}
						})			
					},
					rules:{
						username:'required',
						password:'required',
						repassword:{
							required:true,
							equalTo:"#password",
						},
						phone:{
							required:true,
							isMobile:true,
						},
						
						
				
					},
					messages:{
						username:"必须输入用户名",
						password:"必须输入密码",
						repassword:{
							required:"必须输入二次确认密码",
							equalTo:"两次必须输入相同密码"
						},
						phone:{
							required:"必须输入手机号",
							isMobile:"手机格式错误"
						}
					
					},
					
				})
				
				
				
			})
			
		
		</script>
		
		
		
		
		
		
		
		
		
		
		
		
		
	</body>
</html>