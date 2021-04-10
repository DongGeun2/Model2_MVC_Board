package kr.or.bit.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class Board_ReplyOk_Service implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		
		

		//데이터 받기
		String writer = request.getParameter("reply_writer");
		String content = request.getParameter("reply_content");
		String pwd = request.getParameter("reply_pwd");
		String idx_fk = request.getParameter("idx");
		String userid = "empty";
		
	
		BoardDao boardDao;
		ActionForward actionForward = new ActionForward();
		int result = 0;
		try {
			boardDao = new BoardDao();
			result = boardDao.replywrite(Integer.parseInt(idx_fk), writer, userid, content, pwd);
			
			//처리하는 코드
		 	String msg="";
		    String url="";
		    
		    if(result > 0){
		    	msg ="댓글 입력 성공";
		    	url ="/BoardContent.board?idx="+idx_fk;
		    }else{
		    	msg="댓글 입력 실패";
		    	url ="/BoardContent.board?idx="+idx_fk;
		    }
		    
		    actionForward.setPath("/board/redirect.jsp");
		    
		    request.setAttribute("board_msg",msg);
		    request.setAttribute("board_url",url);
		    
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return actionForward;
	}

}
