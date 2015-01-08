<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<html>
<head>
<title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css.css">
</head>
<body>

<script src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.layout-latest.min.js"></script>


	<form:form id="form" method="post" commandName="childOrganization">
		<form:hidden path="id"/>
		<form:hidden path="available"/>
		<form:hidden path="parentId"/>
		<form:hidden path="parentIds"/>
	
		<div class="form-group">
				<label>父节点名称：</label>
				${parentOrganization.name}
		</div>
		
		<div class="form-group">
			<form:label path="name">子节点名称：</form:label>
			<form:input path="name"/>
		</div>
		
		<form:button id="newChild">新增子节点</form:button>
	
	</form:form>

	<script type="text/javascript">
		
		$(function(){
			
			
			
		});
	
	</script>

</body>
</html>