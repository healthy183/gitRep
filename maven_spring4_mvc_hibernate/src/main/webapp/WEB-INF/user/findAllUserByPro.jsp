<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
}</style>
</head>
<body>

<table class="tableA" id="tableA">
		<tr><th colspan="4">员工信息</th></tr>
		<tr>
			<td>序号</td><td>姓名</td><td>密码</td><td>类型</td>
		</tr>
		<c:forEach items="${requestScope.userList}" var="vo" varStatus="v">
			<tr id="${v.count}">
				<td>${v.count}</td>
				<td id="usrname${v.count}">${vo.usrname}</td>
				<td id="usrpwd${v.count}">${vo.usrpwd}</td>
				<td>
					${vo.usrtype}	
				</td>
			</tr>		
		</c:forEach>
	</table>


</body>
</html>