<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script src="<c:url value='/resource/js/jquery-3.2.1.min.js'/>"></script>
</head>
<body>
	<h1>请在后面输入loginForm登录</h1>
	<div>
		${sbHtml}
		${user}
	</div>

	<script>
		$(document).ready(function(){
			sendJson();
		
		
		});
		function sendJson(){
			$.ajax({
				url:"sendJson",
				type:"post",
				contentType:"application/json",
				dataType:"json",
				data:'{"username":"林俊浩","password":"123456"}',
				success:function(data){
					if(data.length>0){
						alert(data);
					}
				}
			});
		}
		
	</script>
</body>
</html>