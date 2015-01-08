<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<html>
<head>
<title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css.css">
</head>
<body>

	<form:form  id="form" method="post" commandName="organization">
			<form:hidden path="id"/>
			<form:hidden path="available"/>
			<form:hidden path="parentId"/>
			<form:hidden path="parentIds"/>
			
			<div class="form-group">
				<form:label path="name">名称</form:label>
				<form:input path="name"/>
			</div>
			
			<shiro:hasPermission name="organization:update">
				<form:button id="updateBtn">修改</form:button>
			</shiro:hasPermission>
			
			<shiro:hasPermission name="organization:delete">
					<c:if test="${not organization.rootNode}">
						<form:button id="deleteBtn">删除</form:button>
					</c:if>
			</shiro:hasPermission>
			
			<shiro:hasPermission name="organization:create">
					<form:button id="appendChildBtn">添加子节点</form:button>
			</shiro:hasPermission>
			
			<shiro:hasPermission name="organization:update">
				<c:if test="${not organization.rootNode}">
					<form:button id="moveBtn">移动节点</form:button>
				</c:if>
			</shiro:hasPermission>
			
	</form:form>
	
	  <script src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.min.js"></script>
   	  <script type="text/javascript">
   	  
  		 var pagePath = "${pageContext.request.contextPath}/organization/${organization.id}/";
   	  	
   	  	function summitFtn(url){
   	  		
   	  		$("#form").attr("action",url).submit();
		 	   return false;
   	  		}
   	  
   	  		$(function(){
   	  		
   	  			$("#updateBtn").click(function(){
   	  				
   	  				var url = pagePath+"update";
   	  				summitFtn(url);
   	  				
   	  			});
   	  			
   	  			$("#deleteBtn").click(function(){
   	  				
   	  				if(confirm("Are you sure to delete?")){

   	   	  				var url = pagePath+"delete";
   		  				summitFtn(url);
   	  					
   	  				}
   	  				
   	  				return false;
   	  				
   	  			});
   	  			
   	  			$("#appendChildBtn").click(function(){
   	  				
   	  			 location.href=pagePath+"appendChild";
                 return false;
   	  				
   	  			});
   	  			
   	  			
   	  			$("#moveBtn").click(function(){
   	  				
   	  			location.href=pagePath+"move";
                return false;
  	  				
   	  				
   	  			});
   	  			
   	  		})
   	  
   	  
   	  </script>


</body>
</html>