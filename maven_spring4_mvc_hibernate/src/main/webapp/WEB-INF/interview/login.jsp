<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


<table class="tableA" id="tableA" border="1">
	
		<tr>
			<td>编号</td><td>用户名称</td><td>性别</td><td>年龄</td>
			<td>联系电话</td><td>加入时间</td><td>成功登陆次数</td><td>锁定状态</td>
			<td>
				
			</td>
		</tr>
		<c:forEach items="${userList}" var="vo" varStatus="v">
			<tr id="${v.count}">
				<td>${v.count}<input id="userId${v.count}" type="hidden" value="${vo.id}"></input></td>
				<td id="usrname${v.count}">${vo.userName}</td>
				<td id="usrpwd${v.count}">${vo.password}</td>
				<td id="usrpwd${v.count}">${vo.tbUserDetail.age}</td>
				<td id="usrpwd${v.count}">${vo.tbUserDetail.sex}</td>
				<td id="usrpwd${v.count}">${vo.tbUserDetail.phone}</td>
				<td id="usrpwd${v.count}">${vo.tbUserDetail.addDate}</td>
				<td id="usrpwd${v.count}">${vo.tbUserDetail.success}</td>
				<td>
						
				</td>
			</tr>		
		</c:forEach>
		<tr>
			<td></td>
				
		</tr>
		
	</table>



</body>
</html>