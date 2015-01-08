<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="js/jquery.1.10.2.js"></script>

<script type="text/javascript">

	$(function(){

		
		$("#testButton").click(function(){
				
			var usrNameVar = $("#usrName").val();
			if(!usrNameVar == ""){
				$("#thisForm").submit();
			}else{
				alert("usrName not be null!");
			}
		});
	});

</script>

</head>
<body>
<form action="char/loginIn.html" id="thisForm">
	usrName<input id="usrName" name="usrName"/>
	<input type="button" id="testButton" value="login!"></input>
</form>


<hr>

<form action="login.html" method="post">
name: <input type="text" name="name"/>
<input value="登录" type="submit"/>
</form>
</body>
</html>