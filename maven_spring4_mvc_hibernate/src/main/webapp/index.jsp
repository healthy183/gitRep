<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--  
<link href="css/jquery-ui-1.9.0.custom.css" rel="stylesheet">
<link href="css/secondCss.css" rel="stylesheet">-->
<link href="css/dialog.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.9.0.custom.js"></script>
<script type="text/javascript">
$(function() {

	$("#success").dialog({
		autoOpen : false,
		width : 500,
		modal: true,
		buttons : [{
			text: "确定",
			click :function(){
				$(this).dialog("close");
			}
		}]
	});
	
	$("#dialog").dialog({
			autoOpen : false,
			width : 500,
			modal: false,
			buttons : [ {
				text : "提交",
				click : function() {
					var usrNameVar = $("#usrName").val();
					if(usrNameVar == ""){
						$("#usrNameLabel").html("用户名不能为空!");
					}else{
						$(this).dialog("close");
						$("#success").dialog("open");
					}
				}
			}, {
				text : "取消",
				click : function() {
					$(this).dialog("close");
				}
			} ]
		});

		$("#dialog-link").click(function(event) {
			$("#usrName").val("");
			$("#usrPwd").val("");
			$("#usrNameLabel").html("");
			$("#dialog").dialog("open");
			event.preventDefault();
		});
	});
</script>
</head>
<body>




<%-- <form:form action="interview/login.html" method="post" modelAttribute="user"> --%>
<form id="" action="interview/login.html" method="post"><%--  --%>

<table border="1">
		<tr>
			<td>用户名:</td>
			<td><input name="userName" id="usrNameTest"></input></td>
		</tr>
		<tr>
			<td>密码:</td>
			<td><input name="password" id="usrPwdTest"></input></td>
		</tr>
		<tr >
			<td colspan="2" align="right">
				<input type="submit" value="提交"/>
				<input type="reset" value="重置"/>		
				</td>
		</tr>

</table>
${msg}
		</form> 
	<%-- </form> --%>
	 <%-- </form:form> --%>



















<p><!-- ui-state-default背景 字体  ui-corner-all css3加圆滑角度-->
	<!-- <a href="javascript:void();" id="dialog-link" class="ui-state-default ui-corner-all">
		<span class="ui-icon ui-icon-newwin"></span>ui-icon ui-icon-newwin加载图片
			Open Dialog
	</a> -->
</p>
<br>
<p>
<%-- 	<a href="user/findAllUser.html" id="my-link" 
			class="ui-state-default ui-corner-all">
		测试dialog
	</a><br><br><br>
	
	<c:url value="user/findAllUserByPro.html" var="findAllUserByPro"></c:url>
	<a href="${findAllUserByPro}"
			class="ui-state-default ui-corner-all mya" >
		测试基本存储过程
	</a> --%>
	
</p>
<%-- <div id="dialog" title="请填写员工信息">
<form id="myForm">
	员工名称:<input name="usrName" id="usrName"></input>
		   <font style="color:red;">*</font>
		   <label id="usrNameLabel" style="color:red;"></label>
	<br>
	员工密码:<input name="usrPwd" id="usrPwd"></input>
		   <font style="color:red;">*</font>
		   <label id="usrPwdLabel" style="color:red;"></label>
	</form> 
</div>
<div id="success" title="提示" style="display:none;">
	添加成功!
</div> --%>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
<style>
/* 	body{
		font: 62.5% "Trebuchet MS", sans-serif;
		margin: 50px;
	} 
	
	#dialog-link {
		padding: .4em 1em .4em 20px;
		text-decoration: none;
		position: relative;
	}
	#dialog-link span.ui-icon {
		margin: 0 5px 0 0;
		position: absolute;
		left: .2em;
		top: 50%;
		margin-top: -8px;
	}
	*/
	
	/* org0:padding-top  org1:padding-right  org2:padding-bottom  org3:padding-left 
		padding: 5px 5px 8px 8px;*/
	/*.mya { 
		
		padding-bottom:5px;
		padding-right :5px;
		padding-top:5px;
		padding-left:5px;
		text-decoration: none;
		position: relative;
		font-size: 12px;
	}
	.mya:HOVER{
	color:red;
	}
	
	
	.mya span.ui-icon {
		margin: 0 5px 0 0;
		position: absolute;
		left: .2em;
		top: 50%;
		margin-top: -8px;
	}
	*/
	
	/* org0:padding-top  org1:padding-right  org2:padding-bottom  org3:padding-left 
		padding: 5px 5px 8px 8px;
	/*#my-link { 
		
		padding-bottom:5px;
		padding-right :5px;
		padding-top:5px;
		padding-left:5px;
		text-decoration: none;
		position: relative;
		font-size: 12px;
	}
	#my-link:HOVER{
	color:red;
	}
	
	
	#my-link span.ui-icon {
		margin: 0 5px 0 0;
		position: absolute;
		left: .2em;
		top: 50%;
		margin-top: -8px;
	}
	
	#dialog{
		font-size: 14px;
		margin-top: 10px;
	}
	#success{
		font-size: 14px;
		margin-top: 10px;
	}*/
	</style>
</html>