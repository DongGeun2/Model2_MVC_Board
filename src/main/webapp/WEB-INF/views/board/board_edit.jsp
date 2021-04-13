<%@page import="kr.or.bit.dao.BoardDao"%>
<%@page import="kr.or.bit.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//수정하기
	String idx = request.getParameter("idx");
	if(idx == null || idx.trim().equals("")){
		response.sendRedirect(request.getContextPath()+"/WEB-INF/views/board/redirect.jsp");
		return;
	}
	BoardDao boardDao = new BoardDao();
	
	
	Board board = boardDao.getEditContent(idx);
	if(board == null){
		out.print("데이터 오류");
		out.print("<hr><a href=" + request.getContextPath() + "/board/redirect.jsp'>목록가가</a>");
		return;
	}
	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="Stylesheet"
	href="<%=request.getContextPath()%>/style/default.css" />
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
	<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.js"></script>
	<script type="text/javascript">
	function editCheck() {

		if (!edit.writer.value) {

			alert("이름을 입력하세요");
			edit.writer.focus();
			return false;
		}
		if (!edit.pwd.value) {
			alert("비밀번호를 입력해야 합니다.");
			edit.pwd.focus();
			return false;
		}

		if (!edit.email.value) {
			alert("이메일을 입력해야합니다.");
			edit.email.focus();
			return false;
		}

		if (!edit.subject.value) {
			alert("제목을 입력하세요");
			edit.subject.focus();
			return false;
		}

		if (!edit.content.value) {
			alert("글 내용을 입력하세요");
			edit.content.focus();
			return false;
		}

		document.edit.submit();

	}
	$(function(){

		$('#summernote').summernote({

		placeholder: '글을 입력 하세요..',

		tabsize: 2,

		height: 300, // set editor height

		minHeight: 300, // set minimum height of editor

		maxHeight: 300, // set maximum height of editor

		focus: true 

		}); 

		});
</script>
</head>
<body>
	<%
		pageContext.include("/include/header.jsp");
	%>
	
	<c:set var="idx" value="${requestScope.idx}" />
	<c:set var="idx" value = "<%=idx%>" />
	<c:set var="board" value="<%=boardDao.getContent(Integer.parseInt(idx))%>" />
	
	<div id="pageContainer">
		<div style="padding-top: 25px; text-align: center">
			<!-- form 시작 -->
			<form name="edit" action=${ path }/BoardEditOk.board method="POST">
				<center>
					<table width="90%" border="1">
						<tr>
							<td width="20%" align="center"><b> 글번호 </b></td>
							<td width="30%">
									${idx} 
									<input type="hidden" name="idx" value="${idx}"></td>
							<td width="20%" align="center"><b>작성일</b></td>
							<td>${board.writedate}</td>
						</tr>
						<tr>
							<td width="20%" align="center"><b>글쓴이</b></td>
							<td width="30%">
								<input type="text" name="writer" value="${board.writer}">
							</td>
							<td width="20%" align="center"><b>홈페이지</b></td>
							<td>
								<input type="text" name="homepage" value="${board.homepage}">
							</td>
						</tr>
						<tr>
							<td width="20%" align="center"><b>비밀번호(기존)</b></td>
							<td>
								<input type="text" name="pwd">
							</td>
							<td width="20%" align="center"><b>이메일</b></td>
							<td>
								<input type="text" name="email" value="${board.email}">
							</td>
						</tr>

						<tr>
							<td width="20%" align="center"><b>제목</b></td>
							<td colspan="3">
								<input type="text" name="subject" value="${board.subject}" size="40">
							</td>
						</tr>
						<tr height="100">
							<td width="20%" align="center"><b>글내용</b></td>
							<td colspan="3">
								<textarea rows="7" cols="50" name="content" id="summernote">${board.content}</textarea>
							</td>
						</tr>

						<tr>
							<td width="20%" align="center"><b>첨부파일</b></td>
							<td colspan="3">${board.filename} (${board.filename}bytes)<br /> 
								<input type="file" name="filename">
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="button" value="수정하기" onclick="editCheck();"> 
								<input type="reset" value="다시쓰기">
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<a href="board_list.jsp">목록</a>
							</td>
						</tr>
					</table>
				</center>
			</form>
		</div>
	</div>
</body>
</html>