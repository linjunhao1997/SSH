<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<c:url value='/resource/js/jquery-3.2.1.min.js'/>"></script> 
<title>修改密码</title>
  <style>
        #modifypw {
            margin-top:15%;
            text-align: center;
        }
   </style>
</head>

	<body>
	    <div id="modifypw">
	        <span>
	            <input type="password" id="oldpassword" placeholder="原密码" />
	        </span>
	        <br/>
	        <span>
	            <input type="password" id="newpassword" placeholder="新密码" />
	        </span> 
	        <br />
	        <span>
	            <input type="password" id="confirmpassword" placeholder="确认密码" />
	        </span>
	         <br/>
	        <input id="yes" type="button" value="确定" />
	        <input type="button" value="取消" />
   		 </div>
	</body>
	<script>
		$(document).ready(function(){
			
			$("#yes").click(function(){
				modifyPW();
			});
		});
		
		function modifyPW(){
			var oldpw = $("#oldpassword").val();
			var newpw = $("#newpassword").val();
			var confirm = $("#confirmpassword").val();
			
			$.ajax({
				url : 'modifyPassword',
				type : 'post',
				data : JSON.stringify({
					"oldpassword":oldpw,
					"newpassword":newpw,
					"confirmpassword":confirm
				}),

				contentType : 'application/json',
				dataType : "json",
				success : function(result) {
					if(result=="修改成功"){
						window.location.href="http://localhost:8080/SH/";
					}
					
					alert(result);
				},
				error : function(err) {
					alert(err);
				}
			
			})
		};
		
	</script>
</html>