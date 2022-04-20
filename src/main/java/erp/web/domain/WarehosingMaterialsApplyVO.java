
package erp.web.domain;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

import lombok.Data;
/**
 * 
* FileName : WarehosingMaterialsApplyVO.java
* Comment  : 입고  신청, 재고, 불출
*/
@Data
public class WarehosingMaterialsApplyVO {

	private Integer no;
	private Integer applicationCode; //신청코드
	private Integer maid; //자재 아이디
	
	private String mcode;
	private String warehosingMaterialName; //자재명
	private Integer applicationQuantity; //신청수량   , 불출 신청수량
	private Integer properInventory; //적정 재고량
	private Integer currentInventory; //현재 재고량
	
	private Integer materialQuantity; //자재수량
	private Integer possibleInventory; //입고 가능한  재고수량
	
	private String applicationQuantityFormat; //적정재고량
	private String properInventoryFormat; //적정재고량
	private String materialQuantityFormat; //자재수량		
	private String sumQuantityFormat;
	private String planProductQuantityFormat;	
	private String currentInventoryFormat;
	private String possibleInventoryFormat;
	
	private String remarks;   // 불출 신청 비고
	
	private char processStatus;  //처리 상태 0:기본,1:입고성공,2:실패-적정 재고수량 초과신청,3:불출처리    // 처리 상태 0:일반불출처리,1:생산계획불출처리
	private Integer currentInventoryNullConfirm;
	private String materialName; //자재명

	
	private Integer bulchulId; //불출 신청테이블 아이디
	private Integer bomid;   //BOM 제품 아이디'
	private Integer planId;  //생산 아이디
	private String  pcode; //BOM 코드
	private String 	planProductName;//  제품명
	private Integer planProductQuantity; // 개수
	
	private Date applicationDate;
	private String applicationDateFormat;
	
	
	private List<WarehosingMaterialsApplyVO> list;
	

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
		if(this.applicationDate!=null && !StringUtils.isEmpty(this.applicationDate)) {
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			this.applicationDateFormat=format.format(applicationDate);
		}
	}
	
	public void setProperInventory(Integer properInventory) {
		this.properInventory = properInventory;
		this.numFormat();
	}

	public void setApplicationQuantity(Integer applicationQuantity) {
		this.applicationQuantity = applicationQuantity;
		if(this.applicationQuantity!=null && !StringUtils.isEmpty(this.applicationQuantity)) {
			this.applicationQuantityFormat=toNumFormat(this.applicationQuantity);
		}
	}

	public void setCurrentInventory(Integer currentInventory) {
		this.currentInventory = currentInventory;
		if(this.currentInventory!=null && !StringUtils.isEmpty(this.currentInventory)) {
			this.currentInventoryFormat=toNumFormat(this.currentInventory);
		}
	}

	public void setMaterialQuantity(Integer materialQuantity) {
		this.materialQuantity = materialQuantity;
		if(this.materialQuantity!=null && !StringUtils.isEmpty(this.materialQuantity)) {
			this.materialQuantityFormat=toNumFormat(this.materialQuantity);
		}
	}


	public void setPossibleInventory(Integer possibleInventory) {
		this.possibleInventory = possibleInventory;
		if(this.properInventory!=null && !StringUtils.isEmpty(this.properInventory)
				&&	this.currentInventory!=null && !StringUtils.isEmpty(this.currentInventory)	
		) {
			possibleInventory=this.properInventory-this.currentInventory;
			this.possibleInventoryFormat=toNumFormat(this.possibleInventory);
		}
	}



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
		
	public void possibleInventory(){
		if(this.properInventory!=null && !StringUtils.isEmpty(this.properInventory)
				&&	this.currentInventory!=null && !StringUtils.isEmpty(this.currentInventory)	
		) {
			possibleInventory=this.properInventory-this.currentInventory;
			this.possibleInventoryFormat=toNumFormat(this.possibleInventory);
		}
	}
	
}
