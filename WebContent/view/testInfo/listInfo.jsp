<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>我的测试</title>
<script src="${pageContext.request.contextPath}/js/jquery-1.7.1.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$('.del').click(function() {
			if (confirm('确定要删除信息吗？')) {
				return true;
			} else {
				return false;
			}
		});
		$('#upload').submit(function(){
			if($('input[name="file"]').val() == ''){
				alert('请选择文件!');
				return false;
			}
		})
		//正则替换
		var str = '【name】';
		//str = str.replace('【','').replace('】','');
		str = str.replace(/[】【]/g,'');
		//alert(str);

		var arr = [9,5,[99,88],7,[92,[108,2]]];
		var temp = arr.join(",").split(",");
		//alert(Math.max.apply(this,temp));
		//alert(Math.min.apply(this,temp));

	});
	function fn(arg1, arg2, callback) {
		var num = Math.ceil(Math.random() * (arg1 - arg2) + arg2);
		callback(num);
	}
	/* fn(10, 20, function(num){
	 alert(num);
	}); */

	function al(arg, callback) {
		alert(arg);
		if (callback != null)
			callback();
	}

	/* al('呵呵',function ho(){
		alert('is back');
	}); */
</script>
</head>
<body>

<form action="downloadFile" method="post" >
	<input type="hidden" name="id"/>
	 <input type="submit" value="download" />
</form>
<br>
	<form id="upload" action="uploadFile" method="post" enctype="multipart/form-data">
		<input type="file" name="file" /> <input type="submit" value="Submit" />
	</form>

	你是我的测试，请让我看到你！ ${testInfo}
	<a href="addInfo">添加</a>
	<table border="1">
		<tr>
			<th>年龄</th>
			<th>性别</th>
			<th>爱好</th>
			<th>姓名</th>
			<th>操作</th>
		</tr>
		<c:forEach var="info" items="${testInfos}">
			<tr>
				<td>${info.age}</td>
				<td>${info.gender}</td>
				<td>${info.hobby}</td>
				<td>${info.name}</td>
				<td><a href="editInfo/${info.id}">编辑</a> <a class="del"
					href="deleteInfo/${info.id}">删除</a></td>
			</tr>
		</c:forEach>
	</table>
	<%-- 	<c:forEach var="info" items="${testInfos}">
		
	</c:forEach> --%>
</body>
</html>
