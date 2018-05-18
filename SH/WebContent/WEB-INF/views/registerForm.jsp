<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value='/resource/css/bootstrap.min.css'/>">
	<script src="<c:url value='/resource/js/jquery-3.2.1.min.js'/>"></script>  <!--jquery引用与bootstrap前-->
	<script src="<c:url value='/resource/js/bootstrap.min.js'/>"></script>
  <style>
  #row1{
  	margin-top:60px;
  	
  }
  	
  </style>
  </head>
	<body>
		<div id="nav" class="container" >
			<div class="row">
				<div class="col-md-1">
					<a href="guide">主页</a>
				</div>
				<div class="col-md-1">
					<a href="loginForm">登录</a>
				</div>
				<div class="col-md-1 col-md-offset-8">
					<a href="#">联系客服</a>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="col-sm-offset-3 col-sm-6" style="margin-top:30px;">
				<form class="form-horizontal" action="register" method="post">
					<div class="form-group">
						<label for="username" class="col-sm-3 control-label">登录名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="username"
								name="username" placeholder="登录名"><font id="useableName"
								color="red">${lnEmsg}</font>
						</div>
						<span id="inputSuccess2Status" class="sr-only">(success)</span>
					</div>
					<div class="form-group">
						<label for="password" class="col-md-3 control-label">密码</label>
						<div class="col-md-6">
							<input type="password" class="form-control" id="password"
								name="password" placeholder="密码"><font color="red">${pwEmsg}</font>
						</div>
					</div>
					
					<div class="form-group">
						<label for="phone" class="col-md-3 control-label">手机</label>
						<div class="col-md-6">
							<input type="text" class="form-control" id="phone" name="phone"
								placeholder="手机号码"><font color="red">${pEmsg}</font>
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-md-3 control-label">email</label>
						<div class="col-md-6">
							<input type="text" class="form-control" id=""
								name="email" placeholder="邮箱"><font color="red">${eEmsg}</font>
						</div>
					</div>
					<!-- 
					<div class="form-group">
						<label for="address" class="col-md-3 control-label">地址</label>
						<div class="col-md-6">
							<textarea class="form-control" rows="3" id="address"
								name="address" placeholder="地址"></textarea>
						</div>
					</div>
					 -->
					<div class="form-group">
						<div class="col-sm-offset-5 col-sm-10">
							<button type="submit" class="btn btn-info">注册</button>
						</div>
					</div>
					<div class="col-sm-offset-5 col-sm-10">
							<a class="btn btn-primary" href="loginForm">取消</a>
					</div>
				</form>
			</div>
		</div>
	<script>
		$("#username").blur(function() {
			if($(this).val().length>0){
				validate();
			}
		});

		function validate() {
			var username = $("#username").val();
			$.ajax({
				url : 'action/validate',
				type : 'post',
				data : JSON.stringify({
					"username" : username
				}),

				contentType : 'application/json',
				dataType : "json",
				success : function(result) {
					if(result.message=="该用户名可用."){
						$("#useableName").attr("color","green");
						$("#useableName").text(result.message);
					
					
					}else{
						$("#useableName").text(result.message);
					}
				

				},
				error : function(err) {
					alert("连接错误");
				}
			});
		}
	</script>
</body>
</html>