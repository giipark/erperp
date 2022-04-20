package erp.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import erp.web.domain.MemberVO;
import erp.web.service.MemberService;
/**

* FileName : MemberController.java
* Comment  : 회원

*/

@Controller
@RequestMapping(value="/member/**")
public class MemberController {

	private static Logger logger =LoggerFactory.getLogger(MemberController.class);
	
	private static final String JSP_URI="erp/member/";
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    
	@GetMapping(value="login.do")
	public String login(MemberVO memberVO, Model model) throws Exception{				
		return JSP_URI+"login";
	}
	
	
	@PostMapping(value="login.do")
	public String login(@Valid MemberVO memberVO, BindingResult bindingResult,  HttpServletRequest request, RedirectAttributes rttr) throws Exception{
		
		if(bindingResult.hasErrors()) return JSP_URI+"login";
		
		//DB에서 가져온 패스워드
		String dbPw=memberService.loginPasswd(memberVO.getUserid());
		if(bCryptPasswordEncoder.matches(memberVO.getUserpw(), dbPw)){			
			HttpSession session =request.getSession();	
			MemberVO dto  =memberService.login(memberVO.getUserid());
		    session.setAttribute("LOGIN", dto);
			return "redirect:/";
		}else{
			rttr.addFlashAttribute("loginError", "아이디 또는 패스워드가 일치하지 않습니다.");					
			return "redirect:login.do";
		}									
	}

	
	@RequestMapping(value="/update.do" , method=RequestMethod.POST)
	@ResponseBody
	public String memebrUpdate(@ModelAttribute MemberVO memberVO, HttpSession session) throws Exception{		
		String userpw =bCryptPasswordEncoder.encode(memberVO.getUserpw());//패스워드 암호화
		memberVO.setUserpw(userpw);		
		memberService.memberUpdate(memberVO);			
	    session.setAttribute("LOGIN", memberVO);		
		return "success";
	}

	
	
	@RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public String logout(HttpSession session, RedirectAttributes rttr) throws Exception{				
		session.invalidate();					
		return "redirect:/";
	}
	
	


	
	
}
