package kr.or.bit.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class Board_ReWriteOk_Service implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		int idx = Integer.parseInt(request.getParameter("idx"));
		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String email = request.getParameter("email");
		String homepage = request.getParameter("homepage");
		String filename = request.getParameter("filename");
		String pwd = request.getParameter("pwd"); 
		
		String cpage = request.getParameter("cp"); //current page
		String pagesize = request.getParameter("ps"); //pagesize
		
	
		Board board = new Board(idx, writer, pwd, subject, content, null, 0, filename, 0, homepage, email, 0, 0, 0);
		
		ActionForward actionForward = new ActionForward();
		
		try {
			BoardDao boardDao = new BoardDao();
			int result = boardDao.reWriteOk(board);
				
			
				String msg="";
			    String url="";
			    
			    if(result > 0){
			    	msg = "게시물 답글 등록";
			    	url = "/BoardList.board?cp=" + cpage + "&ps=" + pagesize;
			    }else{
			    	msg= "게시물 답글 등록 실패";
			    	url= "/BoardList.board?cp=" + cpage + "&ps=" + pagesize;
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
