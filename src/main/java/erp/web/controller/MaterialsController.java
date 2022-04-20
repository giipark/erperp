
package erp.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import erp.common.util.PageMakerAndSearch;
import erp.web.common.CommonController;
import erp.web.domain.BomVO;
import erp.web.domain.BulchulVO;
import erp.web.domain.MaterialVO;
import erp.web.domain.WarehosingMaterialsApplyVO;
import erp.web.service.ManagementService;
import erp.web.service.MaterialsService;
import lombok.Data;
import lombok.extern.log4j.Log4j;

/**
* FileName : MaterialsController.java
* Comment  : 자재 탭
*/
@Controller
@RequestMapping(value="/erp/materials/")
@Log4j
public class MaterialsController {

	
	private static final String JSP_URI="erp/materials/";
	
	@Autowired
	private MaterialsService materialsService;
	
	@Autowired
	private ManagementService managementService;
	
	
	/**
	 * 입고 신청 페이지
	 */	
	@GetMapping("warehosingMaterialsApplyForm.do")
	public String warehosingMaterialsForm(Model model) throws Exception{
		CommonController.commonMenu("materials", "warehosingMaterialsApply", model);
		return JSP_URI+"warehosing_materials_write";
	}
	
	
	/**
	 * 
     * Ajax	mcode autocomplete 
	 */
	@PostMapping("getBySearchList.do")
	@ResponseBody
	public Map<String, Object> getBySearchList(MaterialVO materialVO ) throws Exception{		
		List<MaterialVO> materialList=managementService.getBySearchList(materialVO);
		Map<String, Object> resultMap=new HashMap<>();
		resultMap.put("materialList", materialList);		
		return resultMap;
	}
	
	
	/**
	 * Ajax  mcode 정보    
	 */	
	@PostMapping("getByMcodeInfo.do")
	@ResponseBody
	public Map<String, Object> getByMcodeInfo(MaterialVO materialVO ) throws Exception{		
		BomVO materialInfo=managementService.getByMcodeInfo(materialVO.getMcode());	
		Map<String, Object> resultMap=new HashMap<>();
		resultMap.put("materialInfo", materialInfo);	
		return resultMap;
	}
	

	/**
	 * 입고 신청 등록
	 */	
	@PostMapping("warehosingMaterialsApplyWrite.do")
	@ResponseBody
	public String warehosingMaterialsApplyWrite(WarehosingMaterialsApplyVO wmaVO,
			Model model) throws Exception{
		
		//1. 널 체크 및 등록된 자재 코드 확인
		for(WarehosingMaterialsApplyVO vo : wmaVO.getList()) {
			if(vo.getMcode()==null ||  StringUtils.isEmpty(vo.getMcode())) return "blank";
			if(vo.getWarehosingMaterialName()==null ||  StringUtils.isEmpty(vo.getWarehosingMaterialName())) return "blank";
			if(vo.getApplicationQuantity()==null ||  StringUtils.isEmpty(vo.getApplicationQuantity())) return "blank";
			
			//mid 아이디 값 가져오기
			BomVO bomVoInfo=managementService.getByMcodeInfo(vo.getMcode());						
			if(bomVoInfo==null) return "notcode";	//등록 되지 않은 자재 코드					
			vo.setMaid(bomVoInfo.getMaid());			
		}
		
		Integer result=materialsService.insertWarehosingMaterials(wmaVO.getList());		
		return result >0 ? "success" :"error";
	}
	
	
	/**
	 * 입고 신청 목록
	 */	
	
	@PostMapping(value="listWarehosingMaterialsApply.do")
	@ResponseBody
	public Map<String, Object> listWarehosingMaterialsApply (
			WarehosingMaterialsApplyVO wmaVO, PageMakerAndSearch  pageMaker , Map<String, Object> map,
			HttpServletRequest request,
			Model model) throws Exception{	
		
		//pageMaker.setPerPageNum(300);		
		Integer totalCount=materialsService.warehosingMaterialsTotalCount();
		pageMaker.setTotalCount(totalCount);				
		
		
		//map.put("pageStart", pageMaker.getPageStart());
		//map.put("perPageNum", pageMaker.getPerPageNum());
		
		List<WarehosingMaterialsApplyVO> listWarehosingMaterialsApply=materialsService.selectWarehosingMaterials(map);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("page", pageMaker.getPage());
		resultMap.put("totalCount", totalCount);
		resultMap.put("listWarehosingMaterialsApply", listWarehosingMaterialsApply);
		resultMap.put("tempEndPage", pageMaker.getTempEndPage());
		return resultMap; 
	}
	
	
	/**
	 * 입고 신청 수정
	 */
	@PostMapping("wmaUpdate.do")
	@ResponseBody
	public String wmaUpdate(WarehosingMaterialsApplyVO wmaVO,
			Model model) throws Exception{		
		//1. 널 체크 및 등록된 자재 코드 확인
		if(wmaVO.getMcode()==null ||  StringUtils.isEmpty(wmaVO.getMcode())) return "blank";
		if(wmaVO.getWarehosingMaterialName()==null ||  StringUtils.isEmpty(wmaVO.getWarehosingMaterialName())) return "blank";
		if(wmaVO.getApplicationQuantity()==null ||  StringUtils.isEmpty(wmaVO.getApplicationQuantity())) return "blank";
			
			//mid 아이디 값 가져오기
		BomVO bomVoInfo=managementService.getByMcodeInfo(wmaVO.getMcode());						
		if(bomVoInfo==null) return "notcode";	//등록 되지 않은 자재 코드					
		wmaVO.setMaid(bomVoInfo.getMaid());			
		
		Integer result=materialsService.updateWarehosingMaterials(wmaVO);		
		return result >0 ? "success" :"error";
	}
	
		
	
	/**
	 * 입고 신청 삭제
	 */
	@PostMapping("wmaDelete.do")
	@ResponseBody
	public String wmaDelete(WarehosingMaterialsApplyVO wmaVO,
			Model model) throws Exception{						
		Integer result=materialsService.wmaDelete(wmaVO.getApplicationCode());		
		return result==1 ? "success" :"error";
	}
	
	/**
	 * 입고 신청 전체 삭제
	 */
	@PostMapping("wmaAllDelete.do")
	@ResponseBody
	public String wmaAllDelete() throws Exception{						
		try {
			materialsService.wmaAllDelete();
			return "success";			
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	
	
	/**
	 * 입고 신청 완료건만 삭제
	 */
	@PostMapping("wmaSuccessDelete.do")
	@ResponseBody
	public String wmaSuccessDelete() throws Exception{	
		try {
			materialsService.wmaSuccessDelete();		
			return "success";			
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	
	/**
	 * 
	 * 입고 처리 페이지
	 * 
	 */	
	@GetMapping("wmaProcess.do")
	public String wmaProcessPage(String applicationCode, Model model) throws Exception{
		if(applicationCode!=null && !applicationCode.equals("")) {
			WarehosingMaterialsApplyVO wmaVO=materialsService.getByInfoWarehosingMaterials(applicationCode);
			if(wmaVO.getProcessStatus()!='1') {
				model.addAttribute("wmaVO", wmaVO);	
			}			
		}
		List<WarehosingMaterialsApplyVO> wmaAllList=materialsService.getByselectWMAList();
		model.addAttribute("wmaAllList", wmaAllList) ;
		CommonController.commonMenu("materials", "wmaProcess", model);
		return JSP_URI+"warehosing_materials_process";
	}
	
	
	
	
	/**
	 * 
     * 입고 처리 - 신청 등록 목록  mcode autocomplete 
	 */
	@PostMapping("getByselectWMAList.do")
	@ResponseBody
	public Map<String, Object> getByselectWMAList(WarehosingMaterialsApplyVO wmaVO, Model model ) throws Exception{		
		List<WarehosingMaterialsApplyVO> getByselectWMAList=materialsService.getByselectWMAList();
		Map<String, Object> resultMap=new HashMap<>();
		resultMap.put("getByselectWMAList", getByselectWMAList);	
		return resultMap;
	}
	
	/**
	 * 
     * 입고 처리 - 신청 등록 정보  mcode autocomplete 
	 */
	@PostMapping("getByWMAcodeInfo.do")
	@ResponseBody
	public Map<String, Object> getByWMAcodeInfo(String applicationCode, Model model ) throws Exception{		
		WarehosingMaterialsApplyVO getByWMAcodeInfo=materialsService.getByInfoWarehosingMaterials(applicationCode);
		Map<String, Object> resultMap=new HashMap<>();
		resultMap.put("getByWMAcodeInfo", getByWMAcodeInfo);	
		return resultMap;
	}
	
	
	
	/**
	 * 	입고 처리입력
	 */
	@PostMapping("wmaProcessSave.do")
	@ResponseBody
	public String wmaProcessSave(WarehosingMaterialsApplyVO wmaVO, Model model) throws Exception{		
		try {
			materialsService.wmaProcessSave(wmaVO.getList());				
			return "success";			
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	
	/**
	 *  재고관리  페이지 
	 *  //Stock
	 */	
	@GetMapping("stock.do")
	public String stock(Model model) throws Exception{		
		CommonController.commonMenu("materials", "stock", model);
		return JSP_URI+"stock";
	}
	
	
	/**
	 *  Ajax 재고관리  search List
	 *  //Stock
	 */	
	@PostMapping(value="stockAjaxSearchList.do")
	@ResponseBody
	public Map<String, Object> stockAjaxSearchList (
			WarehosingMaterialsApplyVO wmaVO, PageMakerAndSearch  pageMaker , Map<String, Object> map,
			HttpServletRequest request,
			Model model) throws Exception{	
		
		map.put("mcode", wmaVO.getMcode());
		map.put("materialName", wmaVO.getMaterialName());
		pageMaker.setPerPageNum(300);
		
		Integer totalCount=materialsService.stockSearchTotalCount(map);
		pageMaker.setTotalCount(totalCount);				
		map.put("pageStart", pageMaker.getPageStart());
		map.put("perPageNum", pageMaker.getPerPageNum());
		
		List<WarehosingMaterialsApplyVO> stockList=materialsService.selectMaterialSearchList(map);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("page", pageMaker.getPage());
		resultMap.put("totalCount", totalCount);
		resultMap.put("stockList", stockList);
		resultMap.put("tempEndPage", pageMaker.getTempEndPage());
		return resultMap; 
	}


	/**
	 *  Ajax 재고관리  업데이트
	 *  
	 */	
	@PostMapping("stockUpdate.do")
	@ResponseBody
	public String stockUpdate(@RequestParam HashMap<String, Object> map ,Model model) throws Exception{		
		materialsService.stockUpdate(map);
		return "success";
	}
	
	
	
	
	/**
	 * 불출 신청 페이지
	 */
	@GetMapping("bulchulApplyPage.do")
	public String bulchulApplyPage(Model model) throws Exception{
		CommonController.commonMenu("materials", "bulchulApply", model);
		return JSP_URI+"bulchul_apply";		
	}
	
	/**
	 * 불출 신청 등록
	 */
	@PostMapping("bulchulApplyWrite.do")
	@ResponseBody
	public String bulchulApplyWrite(BomVO bomVO ,Model model) throws Exception{			
		for(BomVO vo : bomVO.getList()) {
			BomVO getInfo=managementService.getByMcodeInfo(vo.getMcode());						
			if(getInfo==null) return "notcode";	//등록 되지 않은 자재 코드	
			vo.setMaid(getInfo.getMaid());
			vo.setMcode(getInfo.getMcode());
			vo.setMaterialName(getInfo.getMaterialName());
			vo.setCurrentInventory(getInfo.getCurrentInventory());
			vo.setProperInventory(getInfo.getProperInventory());
		}	

		materialsService.bulchulApplyWrite(bomVO.getList());
		return "success";		
	}
	
	
	
	/**
	 * 불출 처리 페이지
	 */
	@GetMapping("bulchulProcess.do")
	public String bulchulProcess(Model model) throws Exception{
		CommonController.commonMenu("materials", "bulchulProcess", model);
		return JSP_URI+"bulchul_process";		
	}

	
	
	/**
	 *  Ajax 불출 처리 페이지 -처리 상태 0:일반불출처리
	 *  
	 */	
	@PostMapping(value="bulchulProcess.do")
	@ResponseBody
	public Map<String, Object> bulchulProcess (
			WarehosingMaterialsApplyVO wmaVO, PageMakerAndSearch  pageMaker , Map<String, Object> map,
			HttpServletRequest request,
			Model model) throws Exception{	
		
		//pageMaker.setPerPageNum(5);
		map.put("processStatus", wmaVO.getProcessStatus());
		Integer totalCount=materialsService.bulchulProcessTotalCount(map);
		pageMaker.setTotalCount(totalCount);				
		//map.put("pageStart", pageMaker.getPageStart());
		//map.put("perPageNum", pageMaker.getPerPageNum());
		
		List<WarehosingMaterialsApplyVO> bulchulList=materialsService.bulchulProcessList(map);
		
		

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("page", pageMaker.getPage());
		resultMap.put("totalCount", totalCount);
		resultMap.put("bulchulList", bulchulList);
		resultMap.put("tempEndPage", pageMaker.getTempEndPage());
		return resultMap; 
	}


	
	/**
	 * 생산 계획 불출 상태 변경
	 */
	@PostMapping("bulchulStatusSave.do")
	@ResponseBody
	public String bulchulStatusSave(WarehosingMaterialsApplyVO wmaVO, Map<String, Object> map) throws Exception{		
		map.put("processStatus", '1');
		List<WarehosingMaterialsApplyVO> bulchulList=materialsService.bulchulProcessList(map);		
		materialsService.bulchulStatusSave(bulchulList);		
		return "success";		
	}
	
	
	/**
	 * 생산 계획 불출 처리
	 */
	@PostMapping("bulchulProcessSave.do")
	@ResponseBody
	public Map<String, Object> bulchulProcessSave(BulchulVO bulchulVO) throws Exception{	
		
		List<BulchulVO> list=bulchulVO.getList();
		
		Map<String, Object> resultMap=new HashMap<>();
		String message="success";
		List<BulchulVO> errorList=new ArrayList<>();
		Integer count=0;
		if(list!=null) {
			for(int i=0; i<list.size(); i++) {					
					if(	list.get(i).getBulchulCheckbox() !=null && list.get(i).getBulchulCheckbox().equals("on")
						&&  list.get(i).getBulchulQuantity() !=null && list.get(i).getBulchulQuantity()>0 ) {							
							count++;
							
							WarehosingMaterialsApplyVO wmaVO=materialsService.getByBulchulProcess(list.get(i).getBulchulId());
							

							Integer properInventory=wmaVO.getProperInventory(); //적정재고수량
							Integer currentInventory=wmaVO.getCurrentInventory(); //현재 재고수량
							Integer applicationQuantity=wmaVO.getApplicationQuantity(); //신청 재고수량
							Integer bulchulQuantity=list.get(i).getBulchulQuantity();  //불출 신청 수량
							list.get(i).setMaid(wmaVO.getMaid());
							list.get(i).setMcode(wmaVO.getMcode());
							
							if(bulchulQuantity >properInventory){
								list.get(i).setMsg("적정재고수량보다 초과 입력 하셨습니다. 적정재고수량을 확인해 주세요.");
								errorList.add(list.get(i));								
							}else if(bulchulQuantity >currentInventory) {
								list.get(i).setMsg("현재 재고수량보다 초과 입력 하셨습니다. 재고수량을 올려주세요.");
								errorList.add(list.get(i));	
							}else if(bulchulQuantity >applicationQuantity) {
								list.get(i).setMsg("신청 수량보다 초과 입력 하셨습니다.");
								errorList.add(list.get(i));
							}else{								
								//불출 업데이트
								materialsService.bulchulProcessSave(list.get(i));																
							}														
					}
			}
											
			if(count==0) message="empty";
		}else{
			message="empty";
		}
		
		if(errorList.size()!=0) {
			message="error";
			resultMap.put("errorList", errorList);	
		}
		
		resultMap.put("message", message);
		return 	resultMap;	
	}
	
	
	
	
	
	
}
