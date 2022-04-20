
package erp.web.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class PlanProductVO {

	private Integer planId ;//생산 아이디
	private Integer no ;
	private Integer bomid; //BOM 제품 아이디
	private String  pcode; //BOM 코드
	private Integer maid; //BOM 자재 아이디
	private String 	planProductName;//  제품명
	private Integer planProductQuantity; // 개수
	private String startDate;
	private String endDate;
	private String deadline;// 날짜 기한
	private Date sqlDeadline;
	private String remarks;// 비고
	private List<PlanProductVO> list;
	
	
}
