<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
			
	<title>购物车</title>
    <link rel="stylesheet" href="<c:url value='/resource/css/bootstrap.min.css'/>">
	<script src="<c:url value='/resource/js/jquery-3.2.1.min.js'/>"></script>  <!--jquery引用与bootstrap前-->
	<script src="<c:url value='/resource/js/bootstrap.min.js'/>"></script>
	<title></title>
	</head>
	<body>
		<table class="table table-hover">
			<caption>${user.username}的购物清单</caption>
			<thead>
				<tr>
					<th>名称</th>
					<th></th>
					<th>价格</th>
					<th>数量</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orders}" var="orders">
				<tr id="${orders.id}">
					<td>${orders.goods.goodsname}</td>
					<td>
					<img  src="<c:url value='/resource/images/'/><c:forEach items="${orders.goods.goodsimages}" begin="0" end="0" var="goodsimage">${goodsimage.imgpath}</c:forEach>"
							alt="${orders.goods.introduce}"  style="width=50px;height=50px;">
					</td>
					<td class="price">${orders.price}</td>
					<td>

						<div class="col-md-2 col-xm-2">
							<div class="input-group">
								<span class="input-group-btn">
									<button class="btn btn-default minus" type="button">
										<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
									</button>
								</span> 
								<input type="text" class="form-control" value="${orders.quantity}">
								<span class="input-group-btn">
									<button class="btn btn-default plus" type="button">
										<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
									</button>
								</span> 
							</div>
							<!-- /input-group -->
						</div>
					</td>
					<td><button class="glyphicon glyphicon-remove"></button></td>
				
				</tr>
				</c:forEach>
				
				
			</tbody>
		</table>
		<span id="total"></span>
		<div style="margin-left:30px;">
			<a id="goToCart" href="<c:url value="mainPage"/>"
			class="btn btn-primary">返回</a>
		</div>
		<script>
			$(document).ready(function() {
				total();
				delectOrder();
			
			

				$(".plus").click(function() {

					var number = parseInt($(this).parent().prev().val());

					number = number + 1;

					$(this).parent().prev().val(number);
					total();

				});

				$(".minus").click(function() {

					var number = parseInt($(this).parent().next().val());

					number = number - 1;
					if (number >= 1) {

						$(this).parent().next().val(number);
						
					};
					var i=0;
					total();

				});

			});
			function total(){
				var i = 0;
				$(".price").each(function() {
					i = i + parseInt($(this).text())*parseInt($(this).next().find("input").val());
				});
				$("#total").text(i);
			};
			
			function delectOrder(){
				$(".glyphicon.glyphicon-remove").click(function(){
					var orderid = $(this).parent().parent();
					orderid.remove();
					$.ajax({
							url : 'deleteOrder',
							type : 'post',
							data : JSON.stringify({
								"id":$(orderid).attr("id"),
								
							}),

							contentType : 'application/json',
							dataType : "json",
							success : function(result) {
								if(result=="删除订单成功"){
								total();
								}
							},
							error : function(err) {
								alert(err);
							}
					});
					
					
				})
			};
		</script>
	</body>
</html>
