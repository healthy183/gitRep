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
</head>
<body>


<hr>
 <b>test servlet!</b>
		<form action="HelloServlet" method="post">
			用户名:<input name="usrName" value="kkk"></input>
			<input type="submit" value="提交"></input>
</form>

<hr>
 <b>test login in!</b>
  <b>the bug   which is the new user can not instead of  older user through login!</b>
		<form action="login.jsp" method="post">
			usrName:<input name="usrName" value="zhang"></input>
			usrPwd:<input name="usrPwd" value="123"></input>
		<input type="submit" value="提交"></input>
</form>	
	

<hr>

<!-- <script type="text/javascript">

	$(function(){
		
		$("#button").click(function(){
			
			var url = "helloAsyncServletJquery";
			
			var test = $("#test").val();
			var  value = $("#value").val();
			
			$.post(url,{test:test,value:value},function(data){
				
				alert(data);
			});
		});
	});
</script>


<input id="test" value="test"><br>
<input id="value" value="name"><br>
<input type="button"  id="button" value="提交"></input>
 -->
</body>
</html>