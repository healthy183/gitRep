<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="js/jquery-1.4.2.js"></script>


</head>
<body>
<span id="span" style="color:red"></span>
	<form  id="lgnForm" class="s"  method="post" action="main/lgn.html">
			用户名字:<input id="usrName" name="usrname" value="梁健康"></input><br>
			用户密码:<input id="usrPwd" name="usrpwd" value="123"></input><br>
	<input id="button" type="submit" value="登陆"></input>
		</form>
<hr>

validate not null
		<form action="main/validateNotNull.html" method="post">
			usrDname:<input name="usrDname"></input>
			<input type="submit" value="提交"></input>
</form>

<hr>
 double validate not null
		<form action="main/validateDoubleNotNull.html" method="post">
				usrName:<input name="usrName"></input>
			usrDname:<input name="usrDname"></input>
			
			usrGname:<input name="usrGname"></input>
			<input type="submit" value="提交"></input>
</form>

<hr>
StringModelconversion

	<form action="conversion/stringtoModel.html" method="post">
				model:<input name="model" value="020-12345678"></input>&nbsp;&nbsp;
				conversionDate:<input name="conversionDate" value="2014-02-25"></input>&nbsp;&nbsp;
			<input type="submit" value="提交"></input>
</form>

<hr>
formatPrototype
	<form action="format/formatPrototype.html" method="post">
			totalCount:<input name="totalCount" value="100000"/><br>
			discount:<input name="discount" value="0.51"/><br>
			sumMoney:<input name="sumMoney" value="100000.128"/><br>
			phoneNumber:<input name="phoneNumber" value="010-12345678"/><br>
			<input type="submit" value="提交"></input>
</form>
	<hr>
formatFielt
	<form action="format/formatFielt.html" method="post">
			phoneNumber:<input name="phoneNumber" value="010-12342678"/><br>
			<input type="submit" value="提交"></input>
</form>

<hr>
<b>what is new in spring mvc 3.1?</b><br>
 <b>1,</b>return domainModel with  HandlerMethodReturnValueHandler <br>
 		 mvc:return-value-handlers<br>
		<form action="handlerController/testNewUser.html" method="post">
		<input type="submit" value="提交"></input>
</form>
	<hr>
	 <b>2,</b>requestParameter in HandlerMethodReturnValueHandler<br>
	 	mvc:argument-resolvers<br>
	<form action="customerWebArgumentHandler/webArgumentHandler.html" method="post">
		   argumentUsrId:<input name="argumentUsrId" value="1234"/><br>
		
			<input type="submit" value="提交"></input>		
	
	</form> 
	
	<hr>
	  <b>3,</b>if dont accord with controller RequestMapping,it will throw 415;<br>
	  @RequestMapping(value="/consumesAndproduces", //headers="",<br>
			method=RequestMethod.POST, <br>
				consumes="application/x-www-form-urlencoded", <br>
					produces="text/html")<br>
	<form action="newAttributeattributeInThreePointOne/consumesAndproduces.html"  
		enctype="text/plain" accept="application/json"  method="post">
		   <input type="submit" value="提交"></input>	
	</form>	
	<hr>
	<b>4,</b>Model  Automatic set attribute when usring @PathVariable 	<br>
	<a href="newAttributeattributeInThreePointOne/newAttr.html">test!</a><br>	
	<hr>
	
	<b>5,</b>params Automatic set into domain model  when usring @PathVariable 	<br>
	<a href="newAttributeattributeInThreePointOne/newMoble/newAttrName.html">test!</a><br>
<br>
<hr>

<!--  
<form action="customerWebArgumentHandler/testAjaxValid.html" method="post">
	<input id="usrName" name="usrName" value="ajaxName"></input>
	<input id="usrSwd"  name="nullStr" value="ajaxSwd"></input> 
	<input id="ajaxBtn" value="提交"></input> 
</form>	-->
<hr style="font-size: 10px;">
<b>what is new in spring mvc 3.2?</b><br>

	<b>1,</b>test @MatrixVariable<br>
	<!--  
	<a href="newAttributeattributeInThreePointTwo/testMatrixVariable/44/pets/petId;q=33;s=23.html">test!</a><br>
-->
	
	<a href="newAttributeattributeInThreePointTwo/voidfindPet/pets/42.html">test!</a>


<br><hr>
<b>3,</b>test @ControllerAdvice

	<a href="newAttributeattributeInThreePointTwo/testCtrlAdvice.html">test!</a>

<hr style="font-size: 10px;">
<b>what is new in spring mvc 4?</b><br>

<b>1,</b>test @RestController<br>
<script type="text/javascript">
	$(function(){
		
		$("#testButton").click(function(){
				
			$.ajax({
				type:"POST",
				url:"testRestController/testRest.html",
				/* contentType:'application/json',
				mimeType: 'application/json', */
				success:function(abc){
					
						alert(abc);
						alert(abc.usrId);//undefined 
						
						var dataObj=eval("("+abc+")");//转换为json对象 
						
						alert(dataObj.usrId); //123 
						
						var data =(new Function("","return "+abc))();
						
						alert(data.usrId); //123 
						
				},error:function(s){
					
				}
			});
		});
	});
</script>
<input type="button" id="testButton" value="test!"></input>

<b>2,</b>test groupsConvertValidate @ConvertGroup<br>
<form action="groupValidate/groupsConvertValidate.html" method="post">

</form>

</body>
</html>