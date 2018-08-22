<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>后台 订单列表</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/handlebars.min.js"></script>

</head>
<body>
<div class="row" style="width:98%;margin-left: 1%;margin-top: 5px;">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				订单列表
			</div>
			<div class="panel-body">
			
				
				
				<table id="tb_list" class="table table-striped table-hover table-bordered table-condensed" id="orderInfo">
					
					
				</table>
			</div>
		</div>
	</div>
</div>
</body>
<script id="showBox" type="text/x-handlebars-template">
<tr>
						<td>序号</td>
						<td>订单编号</td>
						<td>总金额</td>
						<td>订单状态</td>
						<td>订单时间</td>
						<td>用户姓名</td>
						<td>操作</td>
					</tr>
					 {{#each list}}
					<tr>
						<td>{{id}}</td>
						<td>{{orderCode}}</td>
						<td>{{money}}</td>
						<td>
							{{{getInfo status id}}}	
						</td>
						<td>{{createDate}}</td>
						<td>{{username}}</td>
						<td>
							
								<button onclick='isClick(4,{{id}})' type="button" class="btn btn-danger btn-sm">
							发货
						</button>
							
						</td>
					</tr>
				{{/each}}
		</script>

<script type="text/javascript">
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

$(function(){
	findAll()
})

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
				$("#tb_list").html(template({list:data.msg}));
					
			}else{
				alert("请重新登录")
			}
		}
	})
}

</script>

















</html>