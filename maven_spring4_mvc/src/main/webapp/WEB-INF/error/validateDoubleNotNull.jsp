<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form:form commandName="user">
	<form:errors path="*" cssStyle="color:red"></form:errors><br/>
</form:form>

<form:form commandName="user2">
	<form:errors path="*" cssStyle="color:red"></form:errors><br/>
</form:form>


</body>
</html>