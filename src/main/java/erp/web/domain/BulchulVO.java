
package erp.web.domain;

import java.util.List;

import lombok.Data;

@Data
public class BulchulVO {

	private Integer bulchulId;
	private Integer bulchulQuantity;
	private String bulchulCheckbox;
	private String msg;
	private Integer maid;
	private String mcode;
	private Integer no;
	private List<BulchulVO> list;
	
}
