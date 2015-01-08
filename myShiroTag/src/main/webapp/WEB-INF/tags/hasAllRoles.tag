<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ tag import="org.apache.shiro.util.StringUtils" %>
<%@ tag import="org.apache.shiro.SecurityUtils" %>
<%@ tag import="java.util.Arrays" %>

<%@ attribute name="name" type="java.lang.String" required="true" %>
<%@ attribute name="delimiter" type="java.lang.String" required="false" %>

<% 

if(!StringUtils.hasText(delimiter)) {
    delimiter = ",";//默认逗号分隔
}

if(!StringUtils.hasText(name)) {

%>
<jsp:doBody></jsp:doBody>		
<%	

		return;
	}

	String[] roles = name.split(delimiter);
	
	if(!SecurityUtils.getSubject().hasAllRoles(Arrays.asList(roles))){
		return;
	}else{
		

%>
<jsp:doBody></jsp:doBody>	
<%
	}
%>

