<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ tag import="org.apache.shiro.util.StringUtils" %>
<%@ tag import="org.apache.shiro.SecurityUtils" %>
<%@ attribute name="name" type="java.lang.String"  required="true" description=""%>
<%@ attribute name="delimiter" type="java.lang.String"  required="false" description=""%>


<%
	if(!StringUtils.hasText(delimiter)){
		
		delimiter = ",";//default delimiter!
				
	}

	if(!StringUtils.hasText(name)){
%>

	<jsp:doBody/>	
<%

	return;
	
	}
	
	String[] roles = name.split(delimiter);
	
	if(!SecurityUtils.getSubject().isPermittedAll(roles)){
		return; 
		
	}else{
%>

<jsp:doBody/>
<% 
}
%>

