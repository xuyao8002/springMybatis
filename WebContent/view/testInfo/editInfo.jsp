<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>我的测试</title>
<script src="${pageContext.request.contextPath}/js/jquery-1.7.1.js"
	type="text/javascript"></script>
<script type="text/javascript">
 $(function(){
	 
 });
 
 
</script>
</head>
<body>
<form action="${pageContext.request.contextPath}/testInfo/saveInfo" method="post">
<input type="hidden" value="${testInfo.id}" name="id"/>
<table border="1">
  <tr>
    <th></th>
    <th></th>
    
  </tr>
  
  <tr>
	<td>年龄</td>
	<td><input type="text" value="${testInfo.age}" name="age"/></td>
  </tr>
  <tr>
	<td>性别</td>
	<td><input type="text" value="${testInfo.gender}" name="gender"/></td>
  </tr>
  <tr>
	<td>爱好</td>
	<td><input type="text" value="${testInfo.hobby}" name="hobby"/></td>
  </tr>
  <tr>
	<td>姓名</td>
	<td><input type="text" value="${testInfo.name}" name="name"/></td>
  </tr>
  <tr >
  	<td colspan="2"><input value="提交" type="submit"></input></td>
  </tr>
</table>
</form>
</body>
</html>
