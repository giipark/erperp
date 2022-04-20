
package erp.web.domain;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.util.StringUtils;

import lombok.Data;

/**
 * 
* FileName : BomVO.java
* Comment  : BOM 제품, BOM 자재
 */
@Data
public class BomVO {

	private Integer bomid;//bom id
	private String pcode;// 제품코드
	private String productName;//제품명	
	private Integer productQuantity;//제품 수량
	
	private Integer bomMaterialId ; //BOM 자재 아이디
	private Integer maid;//자재 아이디
	private String mcode; //자재코드
	private String materialName; //자재명
	
	private Integer properInventory; //적정재고량
	private Integer materialQuantity; //자재수량
	private Integer currentInventory; //현재 재고수량
	private Integer possibleInventory; //입고 가능한  재고수량
	
	private Integer sumQuantity;
	private Integer planProductQuantity;
	

	private String properInventoryFormat; //적정재고량
	private String materialQuantityFormat; //자재수량		
	private String sumQuantityFormat;
	private String planProductQuantityFormat;	
	private String currentInventoryFormat;
	private String possibleInventoryFormat;
	
	
	private Integer bulchulId; //불출 신청테이블 아이디
	private Integer planId;  //생산 아이디
	private Integer applicationQuantity; //신청수량   , 불출 신청수량
	
	private Integer no ;
	private List<BomVO> list;
	
	
	 /**
	  * 숫자에 천단위마다 콤마 넣기
	  * @param int
	  * @return String
	  * */
	public static String toNumFormat(int num) {	
	  DecimalFormat df = new DecimalFormat("#,###");
	  return df.format(num);
	}

	public void numFormat() {
		if(this.properInventory!=null && !StringUtils.isEmpty(this.properInventory)) {
			this.properInventoryFormat=toNumFormat(this.properInventory);
		}
		
		if(this.materialQuantity!=null && !StringUtils.isEmpty(this.materialQuantity)) {
			this.materialQuantityFormat=toNumFormat(this.materialQuantity);
		}
		
		if(this.sumQuantity!=null && !StringUtils.isEmpty(this.sumQuantity)) {
			this.sumQuantityFormat=toNumFormat(this.sumQuantity);
		}
		
		if(this.planProductQuantity!=null && !StringUtils.isEmpty(this.planProductQuantity)) {
			this.planProductQuantityFormat=toNumFormat(this.planProductQuantity);
		}

		if(this.currentInventory!=null && !StringUtils.isEmpty(this.currentInventory)) {
			this.currentInventoryFormat=toNumFormat(this.currentInventory);
		}
		
		if(this.properInventory!=null && !StringUtils.isEmpty(this.properInventory)
				&&	this.currentInventory!=null && !StringUtils.isEmpty(this.currentInventory)	
		) {
			possibleInventory=this.properInventory-this.currentInventory;
			this.possibleInventoryFormat=toNumFormat(this.possibleInventory);
		}
		
	}
	
	
	
	
}
