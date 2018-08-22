<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<title>添加商品种类</title>
<script>

</script>
</head>
<body>
<div style="width:98%;margin-left: 1%;">
	<div class="panel panel-default">
		<div class="panel-heading">
			添加商品种类
		</div>
		<div class="panel-body">
			<form action="" method="post" onsubmit="return false">
				<div class="row">
					<div class="form-group form-inline">
						<span>所属类型</span>
						<select name="gradeName" id="selectName">
							<option value="0">无默认类型</option>
							
						</select>
					</div>
				</div>
				<div class="row">
					<div class="form-group form-inline">
						<span>种类名称</span>
						<input type="text" name="typename" class="form-control">
					</div>
				</div>
				<div class="row">
					<div class="btn-group">
						<button type="reset" class="btn btn-default">清空</button>
						<button type="submit" class="btn btn-default" id="isUpdate">添加</button>
					</div>
				</div>
			</form>
			<p style="color:red"></p>
		</div>
	</div>
</div>

	<script>
		function getQueryString(key) {
			var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
			var result = window.location.search.substr(1).match(reg);
			return result ? decodeURIComponent(result[2]) : null;
		}
		$(function(){
			if(getQueryString("id")){
				$("#isUpdate").html("修改")
				$.ajax({
					type:"get",
					url:"${pageContext.request.contextPath}/addGrade?method=searchId&id="+getQueryString("id"),
					dataType:"json",
					success:function(data){
						if(data.code==1){
							console.log(data.msg)
							$("input[name='typename']").val(data.msg.gradeName);
							if(data.msg.grade==2){
								//jia zai yi ji lei bie xin xi
								$.ajax({
								type:'get',
								url:"${pageContext.request.contextPath}/addGrade?method=searchType&grade=1",
								dataType:"json",
								success:function(datas){
									if(datas.code==1){
									
										$.each(datas.msg,function(i,item){
											if(data.msg.parentId==item.id){
												$("#selectName").append("<option selected value="+item.id+">"+item.gradeName+"</option>")
											}else{
												$("#selectName").append("<option value="+item.id+">"+item.gradeName+"</option>")
											}
											
										})
									}
									
									}
								})
							}else{
								
							}
							
						}
					}
				})
			}else{
				$("#isUpdate").html("添加")
				//jia zai yi ji lei bie xin xi
				$.ajax({
				type:'get',
				url:"${pageContext.request.contextPath}/addGrade?method=searchType&grade=1",
				dataType:"json",
				success:function(data){
					if(data.code==1){
					
						$.each(data.msg,function(i,item){
							
							$("#selectName").append("<option value="+item.id+">"+item.gradeName+"</option>")
						})
					}
					
					}
				})
			}
			
			$("form").validate({
				submitHandler:function(){
					var object = {};
					object.parentId = $("#selectName").val();
					object.typename = $("input[name='typename']").val();
					
					if(object.parentId==0){
						object.grade = 1
					}else{
						object.grade = 2
					}
					if(getQueryString("id")){
						$.ajax({
							type:"post",
							url:"${pageContext.request.contextPath}/addGrade?method=update&id="+getQueryString("id"),
							dataType:"json",
							data:object,
							success:function(data){
								if(data.code==1){
									location.href = "showGoodsType.jsp";
								}
							}
						})
					}else{
						$.ajax({
							type:"get",
							url:"${pageContext.request.contextPath}/addGrade?method=add",
							dataType:"json",
							data:object,
							success:function(data){
								if(data.code==1){
									location.href = "showGoodsType.jsp";
								}
							}
						})
					}
					
					
				},
				rules: {
					typename: "required",
			
				},
				messages: {
					typename: "必须输入种类名称",
				
				}
				
			}) 
		
			
		
			
			
			
		})
	
	</script>

















</body>
</html>