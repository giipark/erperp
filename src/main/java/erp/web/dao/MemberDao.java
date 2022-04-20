package erp.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import erp.web.domain.MemberVO;

@Mapper
@Repository
public interface MemberDao {
	
	public MemberVO login(String userid) throws Exception;
	
	public String loginPasswd(String userid) throws Exception;

	public MemberVO loginInfo(String userid) throws Exception;

	public void memberUpdate(MemberVO memberVO) throws Exception;

	public MemberVO loginEmail(String email) throws Exception;
		
}
