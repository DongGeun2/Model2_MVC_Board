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


@WebServlet("/ReplyAjax")
public class Reply_Ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public Reply_Ajax() {
        super();
    }
    
    protected void doprecess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    				//데이터 받기
				String writer = request.getParameter("reply_writer");
				String content = request.getParameter("reply_content");
				String pwd = request.getParameter("reply_pwd");
				String idx_fk = request.getParameter("idx");
				String userid = "empty";
				
				PrintWriter out = response.getWriter();
				
				BoardDao boardDao;
				ActionForward actionForward = new ActionForward();
				
				
				int reslut = 0;
				try {
					boardDao = new BoardDao();
					reslut = boardDao.replywrite(Integer.parseInt(idx_fk), writer, userid, content, pwd);
					
					if(reslut > 0) {
						out.print("true");
					}else {
						out.print("false");
					}
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprecess(request, response);
				
				
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprecess(request, response);
	}

}
