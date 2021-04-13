package kr.or.bit.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;

public class Board_EditOk_Service implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		

		ActionForward actionForward = new ActionForward();
		String idx = request.getParameter("idx");
		
		String msg="";
	    String url="";
		
		if(idx == null || idx.trim().equals("")){
			msg="글번호 입력 오류";
		    url="/BoardList.board";
		  
		}
		
		BoardDao boardDao;
		try {
			boardDao = new BoardDao();
			int result = boardDao.boardEdit(request);
			
		    if(result > 0){
		    	msg = "게시물 수정 성공";
		    	url = "/BoardList.board";
		    }else{
		    	msg="게시물 수정 실패";
		    	url= "/BoardEdit.board?idx=" +idx;
		    }
			
		    request.setAttribute("board_msg",msg);
		    request.setAttribute("board_url", url);
		    
		    actionForward.setPath("/WEB-INF/views/board/redirect.jsp");
		    
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return actionForward;
	}

}
