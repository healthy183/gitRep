<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ taglib prefix="kang" tagdir="/WEB-INF/tags" %>


<html>
<head>
    <title>登录</title>
    <style>.error{color:red;}</style>
</head>
<body>


<shiro:guest>
	welcome guest, pls click here to <a href="${pageContext.request.contextPath}/login.jsp">login</a>!
</shiro:guest> 
<shiro:user>
	welcome [<shiro:principal></shiro:principal>]!
	
	<a href="${pageContext.request.contextPath}/logout">logout</a>
</shiro:user>
<br>

<shiro:authenticated>
	<shiro:principal/> had  authenticated!
</shiro:authenticated>
<br>

<shiro:notAuthenticated>
	 shiro:notAuthenticated user(include remeber me!)
</shiro:notAuthenticated>
<br>
<shiro:hasRole name="admin">
 <shiro:principal/> had admin role!
</shiro:hasRole>
<br>
<shiro:hasAnyRoles name="admin,user">
  <shiro:principal/> had addmin or user role!
</shiro:hasAnyRoles>
<br>
<shiro:lacksRole name="abc">
  <shiro:principal/> did not had abc role!
</shiro:lacksRole> 
<br>
<shiro:hasPermission name="user:create">
<shiro:principal/> did  had user:create permission!
</shiro:hasPermission>
<br>
<shiro:lacksPermission name="org:abc">
<shiro:principal/> did not had org:abc permission!
</shiro:lacksPermission>

<br>
<kang:hasAllPermissions name="user:create,user:update">

<shiro:principal/> had many permissons include user:*,menu:*!
</kang:hasAllPermissions>
<br>
<kang:hasAllRoles name="admin,good">

<shiro:principal/> had many role include admin,good!

</kang:hasAllRoles>
<br>
<kang:hasAnyPermissions name="user:try,kang:create">

<shiro:principal/> had one of the roles include user:try,kang:create!

</kang:hasAnyPermissions>

</body>
</html>