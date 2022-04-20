package erp.web.domain;

import java.util.List;

import lombok.Data;

@Data
public class MaterialVO {

	private Integer maid; //자재테이블 아이디
	private Integer no;//번호
	private String mcode; //자재코드
	private String materialName; //자재명
	private Integer properInventory; //적정재고량
	private List<MaterialVO> list;
		
}






