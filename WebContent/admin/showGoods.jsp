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
<script src="../js/DatePicker.js"></script>
<script type="text/javascript" src="../js/handlebars.min.js"></script>
<title>商品列表</title>

</head>
<body>
<div class="row" style="width:98%;margin-left: 1%;">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				会员列表
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<div class="form-group form-inline">
							<span>商品名称</span>
							<input type="text" name="name" class="form-control">
						</div>
					</div>
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5" style="width:75%;">
						<div class="form-group form-inline">
							<span>上架时间</span>
							<input type="text" readonly="readonly"  name="pubdate" class="form-control" onclick="setday(this)">
							<span>--</span>
							<input type="text" readonly="readonly"  name="pubdateTwo" class="form-control" onclick="setday(this)">
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
<script id="show-good" type="text/x-handlebars-template">
						<tr>
							<td>序号</td><td>商品名称</td><td>价格</td><td>图片</td><td>上架时间</td><td>类型</td><td>操作</td>
						</tr>
					 {{#each list}}
				
					
						<tr>
								<td>{{id}}</td>
								<td>{{goodsName}}</td>
								<td>{{price}}</td>
								<td><img src="/upload/{{imgPath}}" width="40px" height="40px"/></td>
								<td>{{getInfo createDate.time}}</td>
								<td>{{gradeName}}</td>
								<td><span class="btn btn-primary btn-xs" onclick="deletess({{id}})">删除</span> &nbsp;
									<span class="btn btn-primary btn-xs" onclick="update({{id}})">修改</span> &nbsp;
									<span class="btn btn-primary btn-xs" onclick="decr('{{comment}}')">描述</a>								
								</td>
							</tr>
					{{/each}}
			</script>
<script>
function deletess(id){
	$.ajax({
		type:"get",
		url:"${pageContext.request.contextPath}/QueryGoods?method=delete&id="+id,
		dataType:"json",
		success:function(data){
			if(data.code == 1){
				console.log(data.msg)
				findAll();
			}else{
				
			}
		}
		})
}
function update(id){
	location.href= "./addGoods.jsp?id="+id
}
function decr(msg){
	alert("商品描述为:"+msg)
}
function formatDateTime(timeStamp) { 
    var date = new Date(timeStamp);
    var y = date.getFullYear();    
    var m = date.getMonth() + 1;    
    m = m < 10 ? ('0' + m) : m;    
    var d = date.getDate();    
    d = d < 10 ? ('0' + d) : d;    
    var h = date.getHours();  
    h = h < 10 ? ('0' + h) : h;  
    var minute = date.getMinutes();  
    var second = date.getSeconds();  
    minute = minute < 10 ? ('0' + minute) : minute;    
    second = second < 10 ? ('0' + second) : second;   
    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;    
};
function findAll(){
	var userhtml = $("#show-good").html();
	var template = Handlebars.compile(userhtml);
	$.ajax({
		type:"get",
		url:"${pageContext.request.contextPath}/QueryGoods?method=findAll",
		dataType:"json",
		success:function(data){
			if(data.code == 1){
				console.log(data.msg)
				$("#tb_list").html(template({list:data.msg}));
			}else{
				$("#tip").html(data.msg);
			}
		}
		})
}
$(function(){
	
	Handlebars.registerHelper("getInfo", function (reateDate) {
		return formatDateTime(reateDate);
 	})
	findAll();
	
	$("#search").click(function(){
		var userhtml = $("#show-good").html();
		var template = Handlebars.compile(userhtml);
		var object = {};
		var sname = $("input[name='name']").val();
		var time = $("input[name='pubdate']").val()+" 00:00:00";
		var times = $("input[name='pubdateTwo']").val()+" 00:00:00";
		console.log(time)
		object.sname = sname;
		object.time = time;
		object.times = times;
		object.method = "search";
		$.ajax({
			type:'post',
			url:"${pageContext.request.contextPath}/QueryGoods",
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