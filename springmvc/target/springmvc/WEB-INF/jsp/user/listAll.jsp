<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>所有数据</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
    所有结果<br/>
    <table border="1">
    	<tr>
    		<td>姓名 user.name</td>
    		<td>年龄 user.age</td>
    		<td>配偶 user.mate.name</td>
    		<td>公司名称 user.company.companyName</td>
            <td>职位名称 user.position.positionName</td>
            <td>孩子 user.childList->child.childName</td>
    	</tr>
    	<c:forEach var="user"  items="${userList}">
    	<tr>
    		<td>${user.name}</td>
    		<td>${user.age}</td>
    		<td>${user.mate.name}</td>
            <td>${user.company.companyName}</td>
            <td>${user.position.positionName}</td>
            <td>
                <c:forEach var="child"  items="${user.childList}">
                    ${child.childName}
                </c:forEach>
            </td>
    	</tr>
    	</c:forEach> 
    </table>
  </body>
</html>
