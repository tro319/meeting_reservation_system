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
	
	<p>今日から数えて、<c:out value="${ date_count }" />日後の予約枠です。</p>
	
	
	<form action="ConfRese" method="post">
	
		<input type="hidden" name="id" id="id" value="${ id }" />
		
		<input type="hidden" name="week" id="week" value="${ week }" />
		
		<br>
	
		<label for="time"><span>開始時間: <c:out value="${ time }" />時</span>
		
			<input type="hidden" name="time" id="time" value="${ time }" />
		
		</label>
		
		<br>
		
		<input type="submit" value="確認" />
	
	</form>

</body>
</html>