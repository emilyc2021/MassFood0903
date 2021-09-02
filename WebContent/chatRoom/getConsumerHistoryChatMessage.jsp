<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ChatRoom.model.*"%>


<%
	ChatRoomJDBCDAO dao = new ChatRoomJDBCDAO();
	List<ChatRoomVO> list = dao.getAll();
	List<ChatRoomVO> conversationList = dao.conversationList();
	Integer senderAccountNumber = Integer.parseInt(request.getParameter("sender"));
	List<ChatRoomVO> findMessageContent = dao.findMessageContent(senderAccountNumber, 4);
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("conversationList", conversationList);
	pageContext.setAttribute("findMessageContent", findMessageContent);
	pageContext.setAttribute("senderAccountNumber",senderAccountNumber);
%>

<c:forEach var="ChatRoomVO" items="${findMessageContent}" varStatus="status">
	<c:if test="${status.index eq 0}" >
		<input type="hidden" type ="text" id="receiverAccountNumber" value="${senderAccountNumber}">
	</c:if>

	<ul>
		<c:if test="${ChatRoomVO.messageType == 41}">
			<li class="clearfix other-message">
				<div class="message other-message float-right">${ChatRoomVO.messageContent}</div>
				<div class="message-data align-right">
					<span class="message-data-time"><fmt:formatDate
							value="${ChatRoomVO.dateTime}" pattern="yyyy-MM-dd HH:mm" /></span>
				</div>

			</li>
		</c:if>
	</ul>

	<ul>
		<c:if test="${ChatRoomVO.messageType == 14}">
			<li class="clearfix you-message">
				<div class="message other-message float-right">${ChatRoomVO.messageContent}</div>
				<div class="message-data align-right">
					<span class="message-data-time"><fmt:formatDate
							value="${ChatRoomVO.dateTime}" pattern="yyyy-MM-dd HH:mm" /></span>
				</div>
			</li>
		</c:if>
	</ul>
</c:forEach>

