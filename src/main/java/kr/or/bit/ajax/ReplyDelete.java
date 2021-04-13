package kr.or.bit.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;


@WebServlet("/ReplyDelete")
public class ReplyDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ReplyDelete() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idx_fk= request.getParameter("idx");//댓글의 원본 게시글 번호
		String no =   request.getParameter("no");//댓글의 순번(PK)
		String pwd =  request.getParameter("delPwd");//댓글의 암호
		
		BoardDao boardDao;
		ActionForward actionForward = new ActionForward();
		PrintWriter out = response.getWriter();
		try {
			boardDao = new BoardDao();
			int result = boardDao.replyDelete(no, pwd);
		    
		    if(result > 0){
		    	out.print("true");
		    }else{
		    	out.print("false");
		    }
		}catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
