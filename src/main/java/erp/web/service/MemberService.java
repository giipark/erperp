package erp.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erp.web.dao.MemberDao;
import erp.web.domain.MemberVO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	
	public MemberVO login(String userid) throws Exception {	
		return memberDao.login(userid);
	}
	
	public String loginPasswd(String userid) throws Exception {
		return memberDao.loginPasswd(userid);
	}

	public MemberVO loginInfo(String userid) throws Exception{
		return memberDao.loginInfo(userid);
	}

	public void memberUpdate(MemberVO memberVO)  throws Exception{
		memberDao.memberUpdate(memberVO);		
	}



	public MemberVO loginEmail(String email) throws Exception{		
		return memberDao.loginEmail(email);	
	}

	
	
}
