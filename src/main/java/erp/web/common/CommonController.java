package erp.web.common;

import org.springframework.ui.Model;

public class CommonController {
	
	//메뉴 설정
	public static void commonMenu(String depth1, String depth2,  Model model) throws Exception{
		model.addAttribute("depth1",depth1);
		model.addAttribute("depth2", depth2);			
	}
	
	
}
