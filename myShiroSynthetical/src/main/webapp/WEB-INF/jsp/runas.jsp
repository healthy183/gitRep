<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="zhang" uri="http://github.com/zhangkaitao/tags/zhang-functions" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css.css">

</head>
<body>

<c:if test="${needRefresh}">
        <script type="text/javascript">
            top.document.getElementById("username").innerText = "${user.username}";
        </script>
 </c:if>
    
    
   <div>${msg}</div>

    <h2>切换身份</h2>  
    
	 <c:if test="${isRunas}">
        上一个身份：${previousUsername}
        |
        <a href="${pageContext.request.contextPath}/runas/switchBack">切换回该身份</a>
    </c:if>	
		
	 <h3>切换到其他身份：</h3>
	 
	 <c:choose>
	 		<c:when test="${empty fromUserIds}">无</c:when>
	 		<c:otherwise>
	 			<table class="table">
	 					<thead>
                <tr>
                    <th>用户名</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${fromUserIds}" var="id">
                    <tr>
                        <td>${zhang:username(id)}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/runas/switchTo/${id}">切换到该身份</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
	 			
	 			</table>
	 		</c:otherwise>
	 
	 </c:choose>
	 	<table class="table">
        <thead>
        <tr>
            <th>用户名</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allUsers}" var="u">
            <tr>
                <td>${u.username}</td>
                <td>
                    <c:choose>
                        <c:when test="${zhang:in(toUserIds, u.id)}">
                            <a href="${pageContext.request.contextPath}/runas/revoke/${u.id}">回收身份</a>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/runas/grant/${u.id}">授予身份</a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
			
		



</body>
</html>