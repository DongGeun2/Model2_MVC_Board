package kr.or.bit.service;

import java.io.IOException;
import java.util.Enumeration;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class Board_ReWriteOk_Service implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		
		int size = 1024*1024*10; //10M 네이버 계산기
		MultipartRequest multi;
		ActionForward actionForward = null;
		
		
		try {
			multi = new MultipartRequest(
					request,
					uploadpath, // 실 저장 경로 (배포된 경로)
					size, // 10M
					"UTF-8",
					new DefaultFileRenamePolicy() // 파일 중복 (upload > 중복된 이름 변경)
					);
			
			
			Enumeration filenames = multi.getFileNames();
			
			String file = (String)filenames.nextElement();
			
			int idx = Integer.parseInt(multi.getParameter("idx"));
			String writer = multi.getParameter("writer");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			String email = multi.getParameter("email");
			String homepage = multi.getParameter("homepage");
			String filename = multi.getFilesystemName(file);
			String pwd = multi.getParameter("pwd"); 
			
			String cpage = multi.getParameter("cp"); //current page
			String pagesize = multi.getParameter("ps"); //pagesize
			
		
			Board board = new Board(idx, writer, pwd, subject, content, null, 0, filename, 0, homepage, email, 0, 0, 0);
			
			actionForward = new ActionForward();
			
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
			    
			    actionForward.setPath("/WEB-INF/views/board/redirect.jsp");
		} catch (IOException | NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		return actionForward;
	}

}
