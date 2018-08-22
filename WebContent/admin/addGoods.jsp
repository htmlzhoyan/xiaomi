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
<script src="../js/DatePicker.js"></script>
<script src="../js/jquery.validate.min.js"></script>
<script src="../js/jquery.form.min.js"></script>

<title>商品添加页面</title>
</head>
<body>
	<div class="row" style="margin-left: 20px;">
		<form action="#" method="post" enctype="multipart/form-data">
			<div>
				<h3>新增商品</h3>
			</div>
			<hr />
			<div class="row">
				<div class="col-sm-6">
				<p style="color:red"></p>
					<div class="form-group form-inline">
						<label>名称:</label>
						<input type="text" name="goodsName" class="form-control" />
					</div>
					
					<div class="form-group form-inline">
						<label>分类:</label>
						<select name="gradeId" class="form-control" id="gradeSelect">
						
							<option value="0">---请选择商品类型---</option>
							
						</select>
					</div>
					<div class="form-group form-inline">
						<label>时间:</label>
						<input type="text" name="createDate" readonly="readonly" class="form-control" onclick="setday(this)" />
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group form-inline">
						<label>价格:</label>
						<input type="text" name="price" class="form-control" /> 元
					</div>
					<div class="form-group form-inline">
						<label>评分:</label>
						<input type="text" name="score" class="form-control" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-10">
					<div class="form-group form-inline">
						<label>商品图片</label>
						
						<input type="file" name="imgPath" onchange="upperCase()"/>
						<img id="showImg" style='max-width:68px;width:68px;width:expression(width>68?"68px":width "px");max-width: 68px;' 
								src="">
					</div>
					<div class="form-group ">
						<label>商品简介</label>
						<textarea  name="comment" class="form-control" rows="5"></textarea>
					</div>
					<div class="form-group form-inline">
						<input id="ddsss" type="submit" value="添加" class="btn btn-primary" />
						<input type="reset" value="重置" class="btn btn-default" />
					</div>
				</div>
			</div>
		</form>
	</div>
	
	<script type="text/javascript">
		function getQueryString(key) {
			var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
			var result = window.location.search.substr(1).match(reg);
			return result ? decodeURIComponent(result[2]) : null;
		}
		function upperCase(){
		    //获取文件
		    var file = $("input[name='imgPath']")[0].files[0];
		 	
		    //创建读取文件的对象
		    var reader = new FileReader();
		 
		    //创建文件读取相关的变量
		    var imgFile;
		 
		    //为文件读取成功设置事件
		    reader.onload=function(e) {
		    	$("#showImg").show();
		        imgFile = e.target.result;
		        $("#showImg").attr('src', imgFile);
		    };
		 
		    //正式读取文件
		    reader.readAsDataURL(file);
		}
		$(function(){
			// 页面加载完成，获取商品类别的数据
			
			
			if(getQueryString("id")){
				$("#ddsss").val("修改");
				$.ajax({
					type:"get",
					url:"${pageContext.request.contextPath}/QueryGoods?method=findId&id="+getQueryString("id"),
					dataType:"json",
					success:function(data){
						if(data.code == 1){
							var infos = data.msg;
							$("input[name='goodsName']").val(infos.goodsName);
							$("input[name='createDate']").val(infos.createDate);
							$("input[name='price']").val(infos.price);
							$("input[name='score']").val(infos.score);
							$("input[name='imgPath']")[0].files[0] = infos.imgPath;
							$("#showImg").attr("src",'/upload/'+infos.imgPath);
							$("textarea[name='comment']").val(infos.comment);
							$.ajax({
								type:"get",
								url:"${pageContext.request.contextPath}/addGrade?method=searchType&grade=2",
								dataType:"json",
								success:function(datas){
									if(datas.code == 1){
										var infom = datas.msg;
										$(infom).each(function(){
											if(infos.gradeId== this.id){
												var html = "<option selected value='" + this.id + "'>" + this.gradeName + "</option>";
											}else{
												var html = "<option value='" + this.id + "'>" + this.gradeName + "</option>";
											}
											
											$("#gradeSelect").append($(html));
										})
									}
								}
							})
						}
					}
				})
				
				
			}else{
				$("#ddsss").val("添加");
				$("#showImg").hide();
				
				$.ajax({
					type:"get",
					url:"${pageContext.request.contextPath}/addGrade?method=searchType&grade=2",
					dataType:"json",
					success:function(data){
						if(data.code == 1){
							var infos = data.msg;
							$(infos).each(function(){
								var html = "<option value='" + this.id + "'>" + this.gradeName + "</option>";
								$("#gradeSelect").append($(html));
							})
						}
					}
				})
			}
			
			
			if(getQueryString("id")){
				$("form").validate({
					
					submitHandler:function(){
						// jquery.form.js中提供的方法，异步提交
						$("form").ajaxSubmit({
							type:"post",
							url:"${pageContext.request.contextPath}/QueryGoods?method=update&id="+getQueryString("id"),
							data:$("form").serialize(),
							dataType:"json",
							success:function(data){
								location.href = "showGoods.jsp";
							}
						})
					},
					
					rules:{
						gradeName:"required"
					},
					messages:{
						gradeName:"商品名不能为空"
					}
				})
			}else{
				$("form").validate({
					
					submitHandler:function(){
						// jquery.form.js中提供的方法，异步提交
						$("form").ajaxSubmit({
							type:"post",
							url:"${pageContext.request.contextPath}/addGoods",
							data:$("form").serialize(),
							dataType:"json",
							success:function(data){
								location.href = "showGoods.jsp";
							}
						})
					},
					
					rules:{
						gradeName:"required"
					},
					messages:{
						gradeName:"商品名不能为空"
					}
				})
			}
			
			
			
		})
	
	
		
	
	</script>
	
</body>
</html>