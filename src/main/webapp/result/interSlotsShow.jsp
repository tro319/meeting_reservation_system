<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>面談実施者別 予約枠状況表示結果 | 面談予約システム</title>
<link rel="stylesheet" href="/meeting_reservation_system/css/body.css">
</head>
<body>


<div class="container">

<h1 class="site_title">面談予約システム</h1>

<h2 class="sub_title">面談担当：<c:out value="${ inter_name_conf }" /> 空き状況</h2>

<div class="table_cover">


	<table class="table inter_slot_table">
	
		<thead>
		
			<tr>
			
				<th class="th_white"></th>
			
				<c:forEach items="${result_date_list}" var="x">
				
					<th><c:out value="${x}" /></th>
					
				</c:forEach>

			</tr>
		
				
			<tr>
				
				<th class="th_white"></th>	
				
				
				<c:forEach items="${result_date_week_list}" var="y">
				
					<th><c:out value="${y}" /></th>
					
				</c:forEach>
			
			</tr>
		
		</thead>
		
		
		<tbody>
		
			<c:set var="count" value="0" />
			
			<c:set var="dateCount" value="0" />
	
		  	
			<tr>
				
		  		<c:forEach items="${result_time_list}" var="i">
		  		
		
		  			
		  			<c:set var="count" value="${count + 1}" />
		  		
		  		
		  			<c:if test="${count == 1}">
					
						<th><c:out value="${i.getStartTime()}:00" /></th>	
						
						<c:set var="judgeTime" value="${i.getStartTime()}" />
						
					</c:if>
					
				</c:forEach>
						
	
			
				<c:forEach items="${result_status_list}" var="j">
				
				
				
					<c:if test="${judgeTime == j.getStartTime()}">
					
							
						<c:choose>
						
							<c:when test="${j.getWeekday() == '土'}">
									
								<td>--</td>
										
							</c:when>
							
							<c:when test="${j.getWeekday() == '日'}">
									
								<td>--</td>
										
							</c:when>
							
							<c:when test="${j.getStatus() == 'true'}">
										
								<td><a href="ParamShow?id=${ j.getID() }&week=${ j.getWeekday() }&time=${ j.getStartTime() }&date_count=${ dateCount }">◎</a></td>
										
							</c:when>
									
									
							<c:when test="${j.getStatus() == 'false'}">
									
								<td>×</td>
										
							</c:when>
								
						</c:choose>
						
						<c:set var="dateCount" value="${dateCount + 1}" />
								
					</c:if>
		
				
					
				</c:forEach>
				
			</tr>
			
			
			<c:set var="count" value="0" />
			
			<c:set var="dateCount" value="0" />
				
				
			<tr>
				
		  		<c:forEach items="${result_time_list}" var="i">
		  		
		
		  			
		  			<c:set var="count" value="${count + 1}" />
		  		
		  			<c:if test="${count == 2}">
					
						<th><c:out value="${i.getStartTime()}:00" /></th>	
						
						<c:set var="judgeTime" value="${i.getStartTime()}" />
						
					</c:if>
					
				</c:forEach>
						
	
			
				<c:forEach items="${result_status_list}" var="j">
				
				
				
					<c:if test="${judgeTime == j.getStartTime()}">
					
							
						<c:choose>
						
							<c:when test="${j.getWeekday() == '土'}">
									
								<td>--</td>
										
							</c:when>
							
							<c:when test="${j.getWeekday() == '日'}">
									
								<td>--</td>
										
							</c:when>
							
							<c:when test="${j.getStatus() == 'true'}">
										
								<td><a href="ParamShow?id=${ j.getID() }&week=${ j.getWeekday() }&time=${ j.getStartTime() }&date_count=${ dateCount }">◎</a></td>
										
							</c:when>
									
									
							<c:when test="${j.getStatus() == 'false'}">
									
								<td>×</td>
										
							</c:when>
								
						</c:choose>
						
						<c:set var="dateCount" value="${dateCount + 1}" />
								
					</c:if>
					
					
				
					
				</c:forEach>
				
			</tr>
			
			
			<c:set var="count" value="0" />
			
			<c:set var="dateCount" value="0" />
	
	
			<tr>
				
		  		<c:forEach items="${result_time_list}" var="i">
		  		
		
		  			
		  			<c:set var="count" value="${count + 1}" />
		  		
		  			<c:if test="${count == 3}">
					
						<th><c:out value="${i.getStartTime()}:00" /></th>	
						
						<c:set var="judgeTime" value="${i.getStartTime()}" />
						
					</c:if>
					
				</c:forEach>
						
	
			
				<c:forEach items="${result_status_list}" var="j">
				
				
				
					<c:if test="${judgeTime == j.getStartTime()}">
					
						<c:choose>
						
							<c:when test="${j.getWeekday() == '土'}">
									
								<td>--</td>
										
							</c:when>
							
							<c:when test="${j.getWeekday() == '日'}">
									
								<td>--</td>
										
							</c:when>
							
							<c:when test="${j.getStatus() == 'true'}">
										
								<td><a href="ParamShow?id=${ j.getID() }&week=${ j.getWeekday() }&time=${ j.getStartTime() }&date_count=${ dateCount }">◎</a></td>
										
							</c:when>
									
									
							<c:when test="${j.getStatus() == 'false'}">
									
								<td>×</td>
										
							</c:when>
								
						</c:choose>
						
						<c:set var="dateCount" value="${dateCount + 1}" />
								
					</c:if>
					
				
		
					
				</c:forEach>
				
			</tr>
				
			
		</tbody>
	
	
	</table>
	
	</div>

</div>

</body>
</html>