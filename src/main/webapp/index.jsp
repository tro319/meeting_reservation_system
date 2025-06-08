<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>面談予約システム</title>
</head>
<body>

<h1>面談予約システム</h1>

	<h2>テーブル作成</h2>
	
	<form action="CreateTable" method="post">
	
		<label for="table_name"><span>テーブル名</span><br>
		
		<input type="text" id="table_name" name="table_name" maxlength="30" required>
		
		</label>
		
		<input type="submit" value="作成">
	
	</form>

	

</body>
</html>