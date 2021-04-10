package kr.or.bit.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class Board_ReplyDeleteOk_Service implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		
	
		
		String idx_fk=request.getParameter("idx");//댓글의 원본 게시글 번호
		String no = request.getParameter("no");//댓글의 순번(PK)
		String pwd = request.getParameter("delPwd");//댓글의 암호
		
		BoardDao boardDao;
		ActionForward actionForward = new ActionForward();
		try {
			boardDao = new BoardDao();
			int result = boardDao.replyDelete(no, pwd);
			
			String msg="";
		    String url="";
		    
		    if(result > 0){
		    	msg ="댓글 삭제 성공";
		    	url ="/BoardContent.board?idx="+idx_fk;
		    }else{
		    	msg="댓글 삭제 실패";
		    	url ="/BoardContent.board?idx="+idx_fk;
		    }
		    
		    request.setAttribute("board_msg",msg);
		    request.setAttribute("board_url",url);
			
		    actionForward.setPath("/board/redirect.jsp");
		    
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return actionForward;
	}

}
