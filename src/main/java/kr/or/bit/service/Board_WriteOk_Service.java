package kr.or.bit.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class Board_WriteOk_Service implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String email = request.getParameter("email");
		String homepage = request.getParameter("homepage");
		String filename = request.getParameter("filename");
		String pwd = request.getParameter("pwd"); 
		

		Board board = new Board(0, writer, pwd, subject, content, null, 0, filename, 0, homepage, email, 0, 0, 0);
		
		ActionForward actionForward = new ActionForward();
		
		try {
			BoardDao boardDao = new BoardDao();
			int result = boardDao.writeok(board);
			
			
				String msg="";
			    String url="";
			    
			    if(result > 0){
			    	msg = "insert success";
			    	url = "/BoardList.board";
			    }else{
			    	msg="insert fail";
			    	url="/BoardWrite.board";
			    }
			
			    request.setAttribute("board_msg",msg);
			    request.setAttribute("board_url", url);
			    
			    actionForward.setPath("/board/redirect.jsp");
			    
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return actionForward;
	}

}
