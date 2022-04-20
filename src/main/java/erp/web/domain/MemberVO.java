package erp.web.domain;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class MemberVO {

	private String mid;	
	
	@NotBlank(message = "* 아이디를 입력해 주세요.")
	private String userid;	
	
	@NotBlank(message = "* 패스워드를 입력해 주세요.")
	private String userpw;	
	
	private String username;
	private String email;		
	private Date regdate;
	private Date updatedate;		
	
}



