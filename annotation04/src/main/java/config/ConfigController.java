package config;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import model1.BoardDAO;
import model1.BoardTO;

@Controller
public class ConfigController {

	@RequestMapping("/list.do")
	public String handleRequest1(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardTO> lists = dao.boardList();
		
		model.addAttribute("lists",lists);
		
		return "board_list1";
	}

	@RequestMapping("/view.do")
	public String handleRequest2(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		BoardTO to = new BoardTO();
		to.setSeq( request.getParameter( "seq" ) );

		BoardDAO dao = new BoardDAO();
		to = dao.boardView( to );
		
		model.addAttribute("to",to);
		
		return "board_view1";
	}

	@RequestMapping("/write.do")
	public String handleRequest3(HttpServletRequest request, HttpServletResponse response, Model model) {
						
		return "board_write1";
	}

	@RequestMapping("/write_ok.do")
	public String handleRequest4(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		BoardTO to = new BoardTO();

		to.setSubject( request.getParameter( "subject" ) );
		to.setWriter( request.getParameter( "writer" ) );
		to.setMail( "" );
		if( !request.getParameter( "mail1" ).equals("") && !request.getParameter( "mail2" ).equals("") ) {
			to.setMail( request.getParameter( "mail1" ) + "@" + request.getParameter( "mail2" ) );
		}
		to.setPassword( request.getParameter( "password" ) );
		to.setContent( request.getParameter( "content" ) );
		
		to.setWip( request.getRemoteAddr() );
		
		BoardDAO dao = new BoardDAO();
		int flag = dao.boardWriteOk( to );
		
		model.addAttribute("flag",flag);
		
		return "board_write1_ok";
	}
	
	@RequestMapping("/modify.do")
	public String handleRequest5(HttpServletRequest request, HttpServletResponse response, Model model) {
					
		BoardTO to = new BoardTO();
		to.setSeq( request.getParameter( "seq" ) );
		
		BoardDAO dao = new BoardDAO();
		to = dao.boardModify( to );
		
		model.addAttribute("to",to);
		
		return "board_modify1";
	}

	@RequestMapping("/modify_ok.do")
	public String handleRequest6(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		BoardTO to = new BoardTO();
		to.setSeq( request.getParameter( "seq" ) );
		to.setSubject( request.getParameter( "subject" ) );
		to.setMail( "" );
		if(!request.getParameter( "mail1" ).equals( "" ) && !request.getParameter( "mail2" ).equals( "" )) {
			to.setMail( request.getParameter( "mail1" ) + "@" + request.getParameter( "mail2" ) );
		}
		to.setPassword( request.getParameter( "password" ) );
		to.setContent( request.getParameter( "content" ) );
		
		BoardDAO dao = new BoardDAO();
		int flag = dao.boardModifyOk( to );
		
		model.addAttribute("flag",flag);
		
		return "board_modify1_ok";
	}
	
	@RequestMapping("/delete.do")
	public String handleRequest7(HttpServletRequest request, HttpServletResponse response, Model model) {
						
		BoardTO to = new BoardTO();
		to.setSeq( request.getParameter( "seq" ) );
		
		BoardDAO dao = new BoardDAO();
		to = dao.boardDelete( to );
		
		model.addAttribute("to",to);
		
		return "board_delete1";
	}

	@RequestMapping("/delete_ok.do")
	public String handleRequest8(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		BoardTO to = new BoardTO();
		to.setSeq( request.getParameter( "seq" ) );
		to.setPassword( request.getParameter( "password" ) );
		
		BoardDAO dao = new BoardDAO();
		int flag = dao.boardDeleteOk( to );
		
		model.addAttribute("flag",flag);
		
		return "board_delete1";
	}

}
