package kr.or.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.service.Board_EditOk_Service;
import kr.or.bit.service.Board_ReWriteOk_Service;
import kr.or.bit.service.Board_ReplyDeleteOk_Service;
import kr.or.bit.service.Board_ReplyOk_Service;
import kr.or.bit.service.Board_WriteOk_Service;


@WebServlet("*.board")
public class BoardConroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public BoardConroller() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    protected void doprocess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		
		String requesturl = request.getRequestURI();
		String requestpath = request.getContextPath();
		String URL = requesturl.substring(requestpath.length());
		
		System.out.println(URL);
		
		Action action = null;
		ActionForward actionForward = null;
		
		// 게시판 리스트
		if(URL.equals("/BoardList.board")) {
			//System.out.println("boradlist");
			actionForward = new ActionForward();
			actionForward.setPath("/board/board_list.jsp");
		
		// 게시판 작성	
		}else if(URL.equals("/BoardWrite.board")) {
			actionForward = new ActionForward();
			actionForward.setPath("/board/board_write.jsp");
		
		// 게시판 작성확인
		}else if(URL.equals("/BoardWriteOk.board")) {
			action = new Board_WriteOk_Service();
			actionForward = action.excute(request, response);
		
		// 게시판 상세보기
		}else if(URL.equals("/BoardContent.board")) {
			actionForward = new ActionForward();
			actionForward.setPath("/board/board_content.jsp");
		
		// 게시판 수정
		}else if(URL.equals("/BoardEdit.board")) {
			actionForward = new ActionForward();
			actionForward.setPath("/board/board_edit.jsp");
			
		// 게시판 수정 확인	
		}else if(URL.equals("/BoardEditOk.board")) {
			action = new Board_EditOk_Service();
			actionForward = action.excute(request, response);
		
		// 게시판 댓글	
		}else if(URL.equals("/BoardReplyOk.board")) {
			action = new Board_ReplyOk_Service();
			actionForward = action.excute(request, response);
		
		// 게시판 댓글 삭제
		}else if(URL.equals("/BoardReplyDelete.board")) {
			action = new Board_ReplyDeleteOk_Service();
			actionForward = action.excute(request, response);
			
		// 게시판 삭제	
		}else if(URL.equals("/BoardDelete.board")) {
			actionForward = new ActionForward();
			actionForward.setPath("/board/board_delete.jsp");
			
		// 게시판 삭제 확인	
		}else if(URL.equals("/BoardDeleteOk.board")) {
			action = new Board_ReplyDeleteOk_Service();
			actionForward = action.excute(request, response);
		
		// 답글 달기
		}else if(URL.equals("/BoardReWrite.board")) {
			actionForward = new ActionForward();
			actionForward.setPath("/board/board_rewrite.jsp");
		
		// 답글 달기 확인	
		}else if(URL.equals("/BoardReWriteOk.board")) {
			action = new Board_ReWriteOk_Service();
			actionForward = action.excute(request, response);
		}
		
		
		
		
		System.out.println(actionForward.getPath());
		
		if(actionForward != null) {
			RequestDispatcher dis = request.getRequestDispatcher(actionForward.getPath());
			dis.forward(request, response);
		}
		
		
	}
	
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprocess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprocess(request, response);
		
	}

}
