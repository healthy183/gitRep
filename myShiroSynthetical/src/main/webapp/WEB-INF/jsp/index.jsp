<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<html>
<head>
	<title>my shiro index page</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/layout-default-latest.css"></link>
</head>

<body>
	
	<iframe name="context" class="ui-layout-center"
		src="${pageContext.request.contextPath}/welcome" frameborder="0" scrolling="auto">
	</iframe>
	<div class="ui-layout-north">welcome[<shiro:principal/>]
		|
		<a href="${pageContext.request.contextPath}/runas">切换身份</a>	
	
	</div>
	
	<div class="ui-layout-south">
    	获取源码：<a href="https://github.com/zhangkaitao/shiro-example" target="_blank">https://github.com/zhangkaitao/shiro-example</a>
	</div>	
	
	
	<div class="ui-layout-west">
	功能菜单<br/>
	<c:forEach items="${menus}" var="m">
		<a href="${pageContext.request.contextPath}${m.url}">
			${m.name}
		</a><br/>
		
	</c:forEach>
	</div>
	
	<script src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.layout-latest.min.js"></script>
	<script>
	    $(function () {
	        $(document).ready(function () {
	            $('body').layout({ applyDemoStyles: true });
	        });
	    });
	</script>
	
	

</body>
</html>
