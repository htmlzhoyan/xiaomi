<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>小米网后台主页-会员信息页面</title>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/handlebars.min.js"></script>
<script src="../js/common.js"></script>

</head>
<body>
	
	<div class="row" style="width: 100%;">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">会员列表</div>
					<div class="panel-body">
					<!-- 条件查询 -->
						<div class="row">
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
								<div class="form-group form-inline">
									<span>用户姓名</span>
									<input type="text" name="username" class="form-control">
								</div>
							</div>
							<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
								<div class="form-group form-inline">
									<span>性别</span>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<label class="radio-inline">
									  <input type="radio" name="gender" value="男"> 男&nbsp;&nbsp;&nbsp;&nbsp;
									</label>
									<label class="radio-inline">
									  <input type="radio"name="gender" value="女"> 女
									</label>
								</div>
							</div>
							<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
								<button onClick="search()" type="button" class="btn btn-primary" id="search"><span class="glyphicon glyphicon-search"></span></button>
							</div>
						</div>
				<!-- 列表显示 -->
						<table id="tb_list" class="table table-striped table-hover table-bordered">
							
						</table>

						
					</div>
				</div>
			</div>
		</div>
</body>
	<script id="board-detail" type="text/x-handlebars-template">
	<tbody>
				<td>序号</td>
				<td>手机号</td>
				<td>姓名</td>
				<td>性别</td>
				<td>vip</td>
				<td></td>
		</tr>
		 {{#each list}}
			<tr>
				<td>{{id}}</td>
				<td>{{phone}}</td>
				<td>{{username}}</td>
				<td>{{sex}}</td>
				<td>{{Infos role}}</td>
				<td><a onClick="deleteUse({{id}})" class='btn btn-primary btn-xs'>删除</a></td>
			</tr>
		{{/each}}
		</tbody>
	</script>
	<script>
	function search(){
		var Object = {};
		Object.name = $("input[name='username']").val();
		Object.radio =  $("input[name='gender']:checked").val();
		Object.method =  "search";
		console.log(Object);
		var userhtml = $("#board-detail").html();
		var template = Handlebars.compile(userhtml);
		$.ajax({
			type:'get',
			url:"${pageContext.request.contextPath}/findAll",
			data:Object,
			dataType:"json",
			success:function(data){
				if(data.code==1){
					$("#tb_list").html(template({list:data.msg}));
				}
				
			}
		})
	}
	function deleteUse(id){
		var that = this
		
		$.ajax({
			type:'get',
			url:"${pageContext.request.contextPath}/findAll?method=delete",
			dataType:"json",
			success:function(data){
				if(data.code==1){
					findAll();
				}
				
			}
		})
	}
	function findAll(){
		var userhtml = $("#board-detail").html();
		var template = Handlebars.compile(userhtml);
	 Handlebars.registerHelper("Infos", function (role) {
		 	if(role==1){
		 		return "普通会员";
		 	}else{
		 		return "管理员";
		 	}

		 })
		$.ajax({
			type:'get',
			url:"${pageContext.request.contextPath}/findAll?method=list",
			dataType:"json",
			success:function(data){
				if(data.code==1){					
					$("#tb_list").html(template({list:data.msg}));
				}
				
			}
		})
	}
	
	$(function(){	
		findAll();
	})
	
	</script>
</html>