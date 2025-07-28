<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>面談予約空き状況取得 | 面談予約システム</title>
</head>
<body>

<h1>面談予約システム</h1>

	<h2>面談予約空き状況取得</h2>

		
		<form action="InterSlotsResult" method="get">
		
			<p>面談実施者名を、選択してください。</p>
		
			<select name="inter_name">
			
				<option value="実施者1" selected>実施者1</option>
			
			</select>
			
			<input type="submit" value="取得" class="entry_btn" />
		
		
		</form>

	

</body>
</html>