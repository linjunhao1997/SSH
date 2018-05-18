<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import ="com.junhao.domain.User" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>主页</title>
	<link rel="stylesheet" href="<c:url value='/resource/css/bootstrap.min.css'/>">
	<script src="<c:url value='/resource/js/jquery-3.2.1.min.js'/>"></script>  <!--jquery引用与bootstrap前-->
	<script src="<c:url value='/resource/js/bootstrap.min.js'/>"></script>

</head>
	<body>
	
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"></a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="mainPage">首页<span class="sr-only">(current)</span></a></li>
					<li><a href="#"></a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">更多 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value="goToCart"/>">我的购物车</a></li>
							<li><a href="<c:url value="goToModifyPassword"/>">修改密码</a></li>
							
							<li role="separator" class="divider"></li>
							<li><a href="<c:url value='quit'/>">注销</a></li>
						</ul></li>
				</ul>
				<form action="search"class="navbar-form navbar-left">
					<div class="form-group">
						<input id="search-key" name="searchkey" type="text" class="form-control" value="${searchkey}" placeholder="Search">
					</div>
					<button id="search" type="submit" class="btn btn-default">搜索</button>
				</form>
				
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	


	<div class="container">
		<div class="row">
			<span class="col-md-offset-9 col-md-3">WelCome,<h3 style="display:inline">${user.username}</h3></span>
		</div>
		<div class="row maincontent">
			<c:forEach items="${goodsList}" var="goods">
				<div class="col-md-offset-2 col-md-8">
					<div id="${goods.goodsid}"class="thumbnail">
						<a href="#"> 
						<img src="<c:url value='/resource/images/'/><c:forEach items="${goods.goodsimages}" begin="0" end="0" var="goodsimage">${goodsimage.imgpath}</c:forEach>"
							alt="${goods.introduce}">
						</a>
					
						<div class="caption">
							<h3>${goods.goodsname}</h3>
							<p>${goods.introduce}</p>
							<p style="color:red;">${goods.price}</p>
							<p class="text-center">
								<a
									href="<c:url value="addToCart/goodsid/${goods.goodsid}"/>"
									class="btn btn-primary">购买</a>
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		
		
	</div>

	<script>
	
		$(document).ready(function(){
			
			pageNum();
			
			clickAdd();
			
			<!--search();-->
		});
		
		function pageNum(){
			/*分页开始**/
			var pageNum = ${pageNum};
			
			for (var i =pageNum; 0 < i;i--)
			{
				$("#li-previous").after("<li><a "+"id='"+i+"'"+"href='<c:url value='mainPage'/>?pageNo="+i+"'>"+i+"</a></li>")
			};
			/*分页结束**/
		}
		
		/**图片选择器，展示图片
		function imgSeletor(){
			var img = $("#getimg");
			img.hide();
			var img_content = $("#getimg span");
			
			var imgArray=new Array()
			img_content.each(function(){
				imgArray.push($(this).text())
			});
			
			var a=parseInt(array.length*Math.random())
			$(".thumbnail img").attr("src","/resource/images/"+imgArray[a]);
		}
		
		function showimg(){
			$(".container .row ").each(function(){
				imgSeletor();
			});
		}
		
		*/
		function clickAdd(){
			$(".caption p a").click(function(){
				alert("加入购物车成功");
			});
		};
		
		function goToCart(){
			$("#goToCart").bind("click",function(){
				
			});
		};
		
		function search(){
			$("#search").click(function(){
			
				var searchkey = $("#search-key").val();
				alert(searchkey);
				$.ajax({
					url:'search',
					type:'post',
					data:JSON.stringify({
						"searchkey": searchkey
					}),
					contentType : 'application/json',
					dataType:"json",
					success:function(result){
							alert(result);
						<!--	$(".container").children().remove();
							$(".container").html("<div>没办法</div>",function(){ $(".maincontent").fadeIn(100);});
					-->
					},
					error:function(){
						alert("出错");
					}
					
				});
				
			});
		};
	
		
	</script>


</body>
</html>
