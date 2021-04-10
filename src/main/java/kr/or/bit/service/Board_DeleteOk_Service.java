package kr.or.bit.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;

public class Board_DeleteOk_Service implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		

		ActionForward actionForward = new ActionForward();
		String idx = request.getParameter("idx");
		String pwd = request.getParameter("pwd");
		
		BoardDao boardDao;
		try {
			boardDao = new BoardDao();
			
			int result = boardDao.deleteOk(idx, pwd);
			
			String msg="";
			String url="";
			if(result > 0){
				msg="delete success";
				url="/BoardList.board";
			}else{
				msg="delete fail";
				url="/BoardList.board";
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
