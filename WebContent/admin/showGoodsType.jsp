<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/handlebars.min.js"></script>
<script src="../js/common.js"></script>
<title>商品分类</title>
</head>
<body>
<div class="row" style="width:98%;margin-left: 1%;">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				商品类型
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<div class="form-group form-inline">
							<span>商品等级</span>
							<input type="text" name="username" class="form-control">
						</div>
					</div>
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<div class="form-group form-inline">
							<span>商品名称</span>
							<input type="text" name="pubdate" class="form-control">
						</div>
					</div>
					<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
						<button type="button" class="btn btn-primary" id="search"><span class="glyphicon glyphicon-search">搜索</span></button>
					</div>
				</div>
				<div style="height: 400px;overflow: scroll;">
					<table id="tb_list" class="table table-striped table-hover table-bordered">
						
						
						
					</table>
				</div>
			</div>
			
		</div>
	</div>
</div>
</body>
	<script id="board-detail" type="text/x-handlebars-template">
	<tbody>
		<tr>
						<td>序号</td><td>类型</td><td>等级</td><td>所属类型</td><td>操作</td>
					</tr>
					
		 {{#each list}}
				
					<tr>
						<td>{{id}}</td>
						<td>{{gradeName}}</td>
						<td>{{grade}}</td>
						<td>{{getInfo parentId}}</td>
						<td>
							<button onClick="updateUse({{id}})">修改</button>&nbsp;&nbsp;
							<button onClick="deleteUse({{id}})">删除</button>
						</td>
					</tr>
			
	{{/each}}
</tbody>
	</script>
	<script>
	var str = {};
	function updateUse(id){
	
		location.href= "./addGoodsType.jsp?id="+id
	}
	function deleteUse(id){
		var that = this
		$.ajax({
			type:'get',
			url:"${pageContext.request.contextPath}/addGrade?method=delete&id="+id,
			dataType:"json",
			success:function(data){
				if(data.code==1){
					findAll();
				}else{
					alert(data.msg);
				}
				
			}
		})
	}
	
	function findAll(){
		var userhtml = $("#board-detail").html();
		var template = Handlebars.compile(userhtml);
		 Handlebars.registerHelper("getInfo", function (state) {
			 	$.each(str,function(index,item){
			 		console.log(item.id==state)
			 		if(item.id == state){
			 			state =  item.gradeName;
			 			return;
			 		}	
			 	})
			 	if(state == 0){
			 		state =  "无所属类型";
			 	}
			 	return state;
       			
		 })
		$.ajax({
			type:'get',
			url:"${pageContext.request.contextPath}/addGrade?method=list",
			dataType:"json",
			success:function(data){
				console.log(data)
				if(data.code==1){					
					$("#tb_list").html(template({list:data.msg}));
				}
				
			}
		})
	}
	function selectId(){
		$.ajax({
			type:'get',
			url:"${pageContext.request.contextPath}/addGrade?method=searchType&grade=1",
			dataType:"json",
			success:function(data){
				console.log(data)		
				if(data.code==1){	
					findAll();
					str = data.msg
				}
				
			}
		})
	}
	$(function(){	
		selectId();
		
		$("#search").click(function(){
			var userhtml = $("#board-detail").html();
			var template = Handlebars.compile(userhtml);
			var object = {};
			var grade = $("input[name='username']").val();
			var gradeName = $("input[name='pubdate']").val();
			object.grade = grade;
			object.gradeName = gradeName;
			object.method = "search";
			$.ajax({
				type:'post',
				url:"${pageContext.request.contextPath}/addGrade",
				data:object,
				dataType:"json",
				success:function(data){
					$("#tb_list").html(template({list:data.msg}));
					
					
				}
			})
			
		})
	
	})
	
	</script>
</html>