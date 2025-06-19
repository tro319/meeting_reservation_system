<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約枠 status 更新結果 | 面談予約システム</title>
</head>
<body>

<% 

String resultMsg = (String)request.getAttribute("result_msg");

%>

<p><%= resultMsg %></p>

</body>
</html>