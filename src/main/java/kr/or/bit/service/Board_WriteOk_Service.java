package kr.or.bit.service;

import java.io.IOException;
import java.util.Enumeration;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//
//import com.oreilly.servlet.MultipartRequest;
//import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class Board_WriteOk_Service implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		
		System.out.println(uploadpath);
		ActionForward actionForward  = new ActionForward();
		
		try {
			
			int size = 1024*1024*10; //10M 네이버 계산기
			MultipartRequest multi = new MultipartRequest(
					request,
					uploadpath, // 실 저장 경로 (배포된 경로)
					size, // 10M
					"UTF-8",
					new DefaultFileRenamePolicy() // 파일 중복 (upload > 중복된 이름 변경)
					);
			Enumeration filenames = multi.getFileNames();
			
			String file = (String)filenames.nextElement();
			
			String writer = multi.getParameter("writer");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			String email = multi.getParameter("email");
			String homepage = multi.getParameter("homepage");
			String filename = multi.getFilesystemName(file);
			String pwd = multi.getParameter("pwd"); 
			
			
			
			
			System.out.println(filename);
			
			Board board = new Board(0, writer, pwd, subject, content, null, 0, filename, 0, homepage, email, 0, 0, 0);
			
	
			BoardDao boardDao = new BoardDao();
			int result = boardDao.writeok(board);
			
			System.out.println(result);
		
			// 파일 업로드	
//			MultipartRequest multi = new MultipartRequest(
//					request,
//					uploadpath, // 실 저장 경로 (배포된 경로)
//					size, // 10M
//					"UTF-8",
//					new DefaultFileRenamePolicy() // 파일 중복 (upload > 중복된 이름 변경)
//			);
//			
				String msg="";
			    String url="";
			    
			    if(result > 0){
			    	msg = "게시물 등록 성공";
			    	url = "/BoardList.board";
			    }else{
			    	msg="게시물 등록 실패";
			    	url="/BoardWrite.board";
			    }
			
			    request.setAttribute("board_msg",msg);
			    request.setAttribute("board_url", url);
			    
			    actionForward.setPath("/WEB-INF/views/board/redirect.jsp");
			    
		} catch (NamingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return actionForward;
	}

}
