package kr.or.bit.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Reply;


@WebServlet("/Replylist")
public class ReplyList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ReplyList() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html;charset=UTF-8");
		
		String idx = request.getParameter("idx");
		
		BoardDao boardDao;
		try {
			boardDao = new BoardDao();
			List<Reply> replylist = boardDao.replylist(idx); //참조하는 글번호
			
			PrintWriter out = response.getWriter();
			
			String tr = null;
			int check = 0;
			for(Reply list : replylist) {
				tr += "<tr align='left'>";
				tr += "<td width='80%'>["+ list.getWriter() + "] : " + list.getContent() ;
				tr += "<br> 작성일 : " + list.getWritedate().toString();
				tr += "</td><td><form action='/ReplyDelete' method='GET' name='replyDel'>";
				tr += "<input type='hidden' name='no' value='" +  list.getNo()  + "'><input type='hidden' name='idx' value='" + list.getIdx_fk() +"'>password :<input type='password' name='delPwd' size='4'><input type='button' value='삭제' onclick='ReplyDelete(this.form)'>";
				tr += "</form></td></tr>";
			}
			
			if(tr == null) {
				tr = "<tr align='center'><td width='80%'>댓글이 없습니다.</td></tr>";
			}
			
			
			out.print(tr);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
		
		
//		<tr align="left">
//		<td width="80%">[<%=reply.getWriter()%>] : <%=reply.getContent()%>
//			<br> 작성일:<%=reply.getWritedate().toString()%>
//		</td>
//		<td width="20%">
//			<form action="${ path }/BoardReplyDelete.board" method="POST"
//				name="replyDel">
//				<input type="hidden" name="no" value="<%=reply.getNo()%>"> <input
//					type="hidden" name="idx" value="<%=idx%>"> password :<input
//					type="password" name="delPwd" size="4"> <input
//					type="button" value="삭제" onclick="reply_del(this.form)">
//			</form>
//		</td>
//	</tr>
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
