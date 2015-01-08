<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Tran sitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"  href="/maven_spring4_mvc_hibernate/css/dialog.css">
<style type="text/css">

	*{ font-size:12px;
	   font-family:"宋体";
	 }
	
	.tableA{
	text-align:center;
	width:99%;
	font:Georgia 11px;
	font-size:12px;
	color:#333333;
	border-collapse:collapse;/*细线表格*/
}

.tableA th{
	border:1px solid #CBD8AC;/*细线条颜色*/
	height:22px;
}

.tableA td{
	border:1px solid #CBD8AC;/*细线条颜色*/
	height:22px;
}

body{
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
	
	.my-link { 
		/* org0:padding-top  org1:padding-right  org2:padding-bottom  org3:padding-left 
		padding: 5px 5px 8px 8px;*/
		padding-bottom:2px;
		padding-right :5px;
		padding-top:2px;
		padding-left:5px;
		text-decoration: none;
		position: relative;
		font-size: 12px;
	}
	.my-link:HOVER{
	color:red;
	}
	
	.my-link span.ui-icon {
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
	}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.9.0.custom.js"></script>
<script  type="text/javascript">
	$(function(){
		
		function checkIsNull(o,msg){
			$(".addLable").html("");
			if(o.val() == ""){
				$("#"+o.attr("id")+"Label").html(msg);
				return false;
			}else{
				return true;
			}
		};
		
		
		$("#addDialog").dialog({
			autoOpen : false,
			width : 500,
			modal: true,
			buttons : [{
				text:"提交",
				click:function(){
					var isTrue = true;					
					var usrName = $("#usrName");
					var usrPwd = $("#usrPwd");
					var usrNameVar = usrName.val();
					var usrPwdVar = usrPwd.val();
					isTrue = isTrue && checkIsNull(usrName,"员工名称不能为空!");
					isTrue = isTrue && checkIsNull(usrPwd,"员工密码不能为空!");
					if(isTrue){
						$.post("${pageContext.request.contextPath}/user/addUser.html",
								{usrName:usrNameVar,usrPwd:usrPwdVar},function(data){
									var lastIndex =	$("#tableA>tbody>tr:last").attr("id");
									lastIndex = (lastIndex*1)+1;
									var addTr ="<tr id='"+lastIndex+"'>"+
									"<td >"+lastIndex+"<input id='userId"+lastIndex+"' type='hidden' value='"+data+"'></input></td>"+
									"<td id='usrname"+lastIndex+"'>"+usrNameVar+"</td><td id='usrpwd"+lastIndex+"'>"+usrPwdVar+"</td><td>"+
									"<a href='javascirpt:void();' id=''"+
										"class='my-link ui-state-default ui-corner-all updtUsr'>"+
												"修&nbsp;改</a>&nbsp;"+
									"<a href='javascirpt:void();' id=''"+
									"class='my-link ui-state-default ui-corner-all delUsr'>"+
												"删&nbsp;除</a>"+	
									"</td></tr>";
									$("#tableA tbody").append(addTr);
								});
						$(this).dialog("close");
						$("#successAdd").dialog("open");
					}
				}
			},{
				text:"取消",
				click :function(){
					$(this).dialog("close");
				}
			}]
		});
		
		$("#successAdd").dialog({
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
		
		
		$("#addUser").click(function(event){
			$("#usrName").val("");
			$("#usrPwd").val("");
			$("#addDialog").dialog("open");
			event.preventDefault();
		});
		
		var trId = null;
		$(".updtUsr").live("click",function(event){
			trId = $(this).parent().parent().attr("id");
			$("#updtusrName").val($("#usrname"+trId).html());
			$("#updtusrPwd").val($("#usrpwd"+trId).html());
			$("#updtDialog").dialog("open");
			event.preventDefault();
		});
		
		$("#updtDialog").dialog({
			autoOpen : false,
			width : 500,
			modal: true,
			buttons : [{
				text: "确定",
				click :function(){
					
					var userId = $("#userId"+trId).val();
					var updtusrName = $("#updtusrName").val();
					var updtusrPwd = $("#updtusrPwd").val();
					$.post("${pageContext.request.contextPath}/user/updtusr/"+userId+"/"+updtusrName+"/"+updtusrPwd+".html",
						null,
							function(){
								$("#usrname"+trId).html(updtusrName);
								$("#usrpwd"+trId).html(updtusrPwd);
								$("#updtDialog").dialog("close");
					});
				},
			},{
				text:"取消",
				click:function(){
					$(this).dialog("close");
				}
			}]
		});
		
		$(".delUsr").live("click",function(event){
			trId = $(this).parent().parent().attr("id");
			$("#delDialog").dialog("open");
			event.preventDefault();
		});
		
		$("#delDialog").dialog({
			autoOpen : false,
			width : 500,
			modal: true,
			buttons : [{
				text:"确定",
				click:function(){
					//alert(trId);
					var userId = $("#userId"+trId).val();
					//alert(userId);
					$.post("${pageContext.request.contextPath}/user/delusr/"+userId+".html",null,function(){
						$("#"+trId).remove();
						$("#delDialog").dialog("close");
					});				
				}
			} , {
			    text:"取消",
			    click : function(){
			    	$(this).dialog("close");
			    }
			}]
		});
		
		
	});
</script>
</head>
<body>

	<table class="tableA" id="tableA">
		<tr><th colspan="4">员工信息</th></tr>
		<tr>
			<td>序号</td><td>姓名</td><td>密码</td>
			<td>
				<a href="javascirpt:void();" id="addUser" 
						class="my-link ui-state-default ui-corner-all">
								新增</a>
			</td>
		</tr>
		<c:forEach items="${requestScope.userList}" var="vo" varStatus="v">
			<tr id="${v.count}">
				<td>${v.count}<input id="userId${v.count}" type="hidden" value="${vo.usrid}"></input></td>
				<td id="usrname${v.count}">${vo.usrname}</td>
				<td id="usrpwd${v.count}">${vo.usrpwd}</td>
				<td><!-- ${pageContext.request.contextPath}/user/findAllUser.html -->
				<a href="javascirpt:void();" id="" 
						class="my-link ui-state-default ui-corner-all updtUsr">
								修&nbsp;改</a>
					<a href="javascirpt:void();" id="" 
			class="my-link ui-state-default ui-corner-all delUsr">
								删&nbsp;除</a>			
				</td>
			</tr>		
		</c:forEach>
	</table>
	
	<div id="addDialog" title="添加员工" style="display: none;">
		员工名称:<input name="usrName" id="usrName" ></input>
		   <font style="color:red;">*</font>
		   <label id="usrNameLabel" style="color:red;" class="addLable"></label>
	<br>
	员工密码:<input name="usrPwd" id="usrPwd"></input>
		   <font style="color:red;">*</font>
		   <label id="usrPwdLabel" style="color:red;" class="addLable"></label>
	</div>
	<div id="successAdd" title="提示" style="display:none;">
	添加成功!
	</div>
	<div id="updtDialog" title="修改员工" style="display: none;">
		员工名称:<input name="usrName" id="updtusrName" ></input>
		   <font style="color:red;">*</font>
		   <label id="updtusrNameLabel" style="color:red;" class="addLable"></label>
	<br>
	员工密码:<input name="usrPwd" id="updtusrPwd"></input>
		   <font style="color:red;">*</font>
		   <label id="updtusrPwdLabel" style="color:red;" class="addLable"></label>
	</div>
	<div id="delDialog" title="提示" style="display:none;">
			你确定要删除这条记录吗?
	</div>
</body>
</html>