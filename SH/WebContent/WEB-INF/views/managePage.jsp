<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resource/css/main.css"/>"  type="text/css" rel="stylesheet" />
<title>商品管理</title>
</head>
<body>
	<div>管理员，${admin.adminname}</div>
	<div class="main">
		<h2 class="title"><span>商品管理</span></h2>
		<form action="<c:url value="/goods/deletes?pageNO=${pageNO}"/>" method="post">
		<table border="1" width="100%" class="tab">
			<tr>
				<th><input type="checkbox" id="chbAll"></th>
				<th>编号</th>
				<th>产品名</th>
				<th>价格</th>
				<th>图片</th>
				<th>操作</th>
			</tr>
			<c:forEach var="entity" items="${goods}">
				<tr>
					<th><input type="checkbox" name="id" value="${entity.goodsid}"></th>
					<td>${entity.goodsid}</td>
					<td>${entity.goodsname}</td>
					<td>${entity.price}</td>
					<td><img src="<c:url value='/resource/images/'/><c:forEach items="${entity.goodsimages}" begin="0" end="0" var="goodsimage">${goodsimage.imgpath}</c:forEach>" height="40"/></td>
					
					<td>
					<a href="<c:url value="/goods/"/>delete/${entity.goodsid}?pageNO=${pageNO}" class="abtn">删除</a>
					<a href="<c:url value="/goods/"/>edit/${entity.goodsid}" class="abtn">编辑</a>
					<a href="<c:url value="/goods/"/>upPicture/${entity.goodsid}" class="abtn">上传</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div id="pager"></div>
		<p>
		    <a href="add" class="abtn out">添加</a>
		    <input type="submit"  value="批量删除" class="btn out"/>
		</p>
		<p style="color: red">${faildmessage}</p>
		<p style="color: green">${successmessage}</p>
		<!--分页 -->
		<script type="text/javascript" src="<c:url value="/resource/js/jquery-3.2.1.min.js"/>" ></script>
		<link href="<c:url value="/resource/css/pagination.css"/>"  type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="<c:url value="/resource/js/jquery.pagination2.2.js"/>" ></script>
		<script type="text/javascript">
		   //初始化分页组件
		   var count=${count};
		   var size=${size};
		   var pageNO=${pageNO};
		   $("#pager").pagination(count, {
			  items_per_page:size,
			   current_page:pageNO-1,
			   next_text:"下一页",
			   prev_text:"上一页",
			   num_edge_entries:2,
			   load_first_page:false,
			  callback:handlePaginationClick
			});
		   
		   //回调方法
		   function handlePaginationClick(new_page_index, pagination_container){
			   location.href="<c:url value="/goods/"/>list?pageNO="+(new_page_index+1);
		   }
		   

		</script>
	</form>
	</div>
</body>
</html>