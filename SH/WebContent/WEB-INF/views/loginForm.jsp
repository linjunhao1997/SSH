<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>登录</title>

    <!-- Bootstrap -->
    <link href="<c:url value='resource/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='resource/css/loginform.css'/>" rel="stylesheet"> 
  </head>
  <body>
	<div class="container">
		<div class="row">
			<div class="col-md-offset-3 col-md-6">
				<form action="login" class="form-horizontal" enctype="mutipart/form-data" method="post">
					<span class="heading">用户登录</span>
					<div class="form-group">
						<input type="text" class="form-control" id="loginname"
							name="username" placeholder="用户名或电子邮件"> <i
							class="fa fa-user"></i>
					</div>
					<div class="form-group help">
						<input type="password" class="form-control" id="Password"
							name="password" placeholder="密　码"> <i class="fa fa-lock"></i>
						<a href="#" class="fa fa-question-circle"></a>
					</div>
					<div class="form-group">
						<div class="main-checkbox">
							<input type="checkbox" value="autoLogin" id="checkbox" name="autoLogin" />
							<label for="checkbox"></label>
						</div>
						<span class="text">Remember me</span>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-default">登录</button>
						<div>
						<a class="btn btn-default" href="registerForm" style="margin-right:20px;">注册</a>
						</div>
					</div>
					<font color="red">${requestScope.message}</font>
				</form>
			</div>
		</div>
		<div class="row">

		

		</div>
	</div>
	<div class="col-md-offset-2 col-md-10">
		<a href="<c:url value='adminloginForm'/>">管理员登录</a>
	</div>

</body>
</html>


