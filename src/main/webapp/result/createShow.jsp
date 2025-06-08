<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>テーブル作成結果 | 面談予約システム</title>
</head>
<body>

<h1>面談予約システム</h1>

	<h2>テーブル作成結果</h2>
	
		<p>${ requestScope.create_result }</p>
		
		<p><a href="../index.jsp">トップへ</a></p>

</body>
</html>