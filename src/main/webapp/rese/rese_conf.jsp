<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>面談予約確認 | 面談予約システム</title>
<link rel="stylesheet" href="/meeting_reservation_system/css/body.css">
</head>
<body>

	<h1>面談予約システム</h1>

	<h2>面談予約確認</h2>
	
	
	<form action="EntryRese" method="post">
	
		<input type="hidden" name="slot_id" id="slot_id" value="${ id }" />
		
		<input type="hidden" name="inter_id" id="inter_id" value="${ inter_id }" />
		
		<input type="hidden" name="user_id" id="user_id" value="${ user_id }" />
		
		<input type="hidden" name="week" id="week" value="${ week }" />
		
		<label for="rese_date"><span>予約日: <c:out value="${ rese_date }" /></span>
		
			<input type="hidden" name="rese_date" id="rese_date" value="${ rese_date }" />
		
		</label>
		
		<br>
	
		<label for="time"><span>開始時間: <c:out value="${ time }" />時</span>
		
			<input type="hidden" name="time" id="time" value="${ time }" />
		
		</label>
		
		<br>
		
		<label for="inter_name_conf"><span>面談実施者名: <c:out value="${ inter_name_conf }" /></span>
		
			<input type="hidden" name="inter_name_conf" id="inter_name_conf" value="${ inter_name_conf }"
		
		</label>
		
		<br>
		
		<label for="user_name_conf"><span>予約者: <c:out value="${ user_name_conf }" /></span>
		
			<input type="hidden" name="user_name_conf" id="user_name_conf" value="${ user_name_conf }"
		
		</label>
		
		<br>
		
		<input type="submit" value="予約確定" class="entry_btn" />
	
	</form>

</body>
</html>