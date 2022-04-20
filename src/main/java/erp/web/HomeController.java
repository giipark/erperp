package erp.web;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,HttpServletRequest request) throws Exception {		
		return "index";
	}
	
	
	@GetMapping(value = "/favicon.ico")
	@ResponseBody
	public String favicon() throws Exception {		
		return "favicon";
	}
	
	@GetMapping(value = "/undefined")
	@ResponseBody
	public String undefined() throws Exception {		
		return "undefined";
	}
	
	@GetMapping(value = "/robots.txt")
	@ResponseBody
	public String robots() throws Exception {		
		return "robots";
	}
	
}
