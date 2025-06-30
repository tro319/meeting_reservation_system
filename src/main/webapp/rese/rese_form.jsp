<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>面談予約フォーム</title>
</head>
<body>

	<h1>面談予約システム</h1>

	<h2>面談予約フォーム</h2>
	
	<c:out value="${ id }" />
	
	<c:out value="${ week }" />
	
	<c:out value="${ time }" />

</body>
</html>