<%@page import="kr.or.bit.dto.Reply"%>
<%@page import="kr.or.bit.dao.BoardDao"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
	request.setCharacterEncoding("UTF-8");
	String idx= request.getParameter("idx"); 

%>

<c:set var="path" value="<%=request.getContextPath()%>" />

<!-- 유효성 체크	 -->
<script type="text/javascript">
	function reply_check() {
		var frm = document.reply;
		if (frm.reply_writer.value == "" || frm.reply_content.value == ""
				|| frm.reply_pwd.value == "") {
			alert("리플 내용, 작성자, 비밀번호를 모두 입력해야합니다.");
			return false;
		}
		frm.submit();
	}
	function reply_del(frm) {
		//alert("del");
		//var frm = document.replyDel;
		//alert(frm);
		if (frm.delPwd.value == "") {
			alert("비밀번호를 입력하세요");
			frm.delPwd.focus();
			return false;
		}
		frm.submit();
	}
</script>
<br>
<!-- 꼬리글 목록 테이블 -->
<%
//덧글 목록 보여주기
BoardDao boardDao = new BoardDao();
List<Reply> replylist = boardDao.replylist(idx); //참조하는 글번호
if (replylist != null && replylist.size() > 0) {
%>
<table width="80%" border="1">
	<tr>
		<th colspan="2">REPLY LIST</th>
	</tr>
	<%
	for (Reply reply : replylist) {
	%>
	<tr align="left">
		<td width="80%">[<%=reply.getWriter()%>] : <%=reply.getContent()%>
			<br> 작성일:<%=reply.getWritedate().toString()%>
		</td>
		<td width="20%">
			<form action="${ path }/BoardReplyDelete.board" method="POST"
				name="replyDel">
				<input type="hidden" name="no" value="<%=reply.getNo()%>"> <input
					type="hidden" name="idx" value="<%=idx%>"> password :<input
					type="password" name="delPwd" size="4"> <input
					type="button" value="삭제" onclick="reply_del(this.form)">
			</form>
		</td>
	</tr>
	<%
	}
	%>
</table>
<%
}
%>