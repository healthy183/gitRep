<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>



<html>
<body>

<%-- <shiro:guest>
	welcome guest, pls click here to <a href="${pageContext.request.contextPath}/login.jsp">login</a>!
</shiro:guest> 
<shiro:user>
	welcome [<shiro:principal></shiro:principal>]!
</shiro:user>
--%>

<div class="error">${error}</div>
	<form action="loginServlet" method="post">
			usrName:<input name="usrName" value="zhang"></input>
			usrPwd:<input name="usrPwd" value="123"></input>
		<input type="submit" value="提交"></input>
</form>






</body>
</html>
