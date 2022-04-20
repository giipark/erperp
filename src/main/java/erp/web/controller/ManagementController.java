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
import erp.web.domain.MaterialVO;
import erp.web.service.ManagementService;
import lombok.extern.log4j.Log4j;

/**
 * 
* FileName : ManagementController.java
* Comment  : 자재정보관리 탭 
 */

@Controller
@RequestMapping(value="/erp/management/")
@Log4j
public class ManagementController {

	private static final String JSP_URI="erp/management/";
	
	
	@Autowired
	private ManagementService managementService;
	
	
	/**
	 *  자재정보관리 - index
	 */
	@GetMapping("")
	public String index(Model model) throws Exception{		
		return JSP_URI+"management_write";
	}
	
	
	/**
	 * 자재정보관리 - 자재등록 페이지
	 */
	@GetMapping(value="writeForm.do")
	public String writeForm(MaterialVO materialVO, Model model) throws Exception{		
		CommonController.commonMenu("management", "managementWrite", model);
		return JSP_URI+"management_write";
	}
	
	/**
	 * Ajax  자재정보관리 -  자재등록 
	 */
	@PostMapping(value="write.do")
	@ResponseBody
	public String write(MaterialVO materialVO, Model model) throws Exception{								
			try {
				if(!managementService.insertMaterial(materialVO.getList())) return "blank";			
				return "success";
			}catch(Exception e) {
				if(e.getMessage().contains("Duplicate")) return "duplicate";					
		        else return e.getMessage();
			}		
	}
	
	
	/**
	 * 자재정보관리 - 자재목록 조회 페이지
	 */
	@GetMapping(value="listSearch.do")
	public String managementListSearch(MaterialVO materialVO, Model model) throws Exception{	
		CommonController.commonMenu("management", "managementList", model);				
		return JSP_URI+"management_list_search"; 
	}
	
	
	/**
	 * ajax 자재정보관리 - 자재목록 조회 
	 */
	@PostMapping(value="listAjaxSearch.do")
	@ResponseBody
	public Map<String, Object> managementAjaxListSearch (
			MaterialVO materialVO, PageMakerAndSearch  pageMaker , Map<String, Object> map,
			HttpServletRequest request,
			Model model) throws Exception{	
		
		map.put("mcode", materialVO.getMcode());
		map.put("materialName", materialVO.getMaterialName());
		pageMaker.setPerPageNum(300);
		
		Integer totalCount=managementService.selectMaterialSearchTotalCount(map);
		pageMaker.setTotalCount(totalCount);				
		map.put("pageStart", pageMaker.getPageStart());
		map.put("perPageNum", pageMaker.getPerPageNum());
		
		List<MaterialVO> materialList=managementService.selectMaterialSearchList(map);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("page", pageMaker.getPage());
		resultMap.put("totalCount", totalCount);
		resultMap.put("materialList", materialList);
		resultMap.put("tempEndPage", pageMaker.getTempEndPage());
		return resultMap; 
	}
	
	/**
	 * Ajax 자재정보관리 - 수정 처리 
	 */	
	@PostMapping(value="materialUpdate.do")
	@ResponseBody
	public String materialUpdate(@RequestParam Map<String, Object> map) throws Exception{
		try {
			managementService.materialUpdate(map);	
			return "success";
		}catch (Exception e) {
			if(e.getMessage().contains("Duplicate")) return "duplicate";					
	        else return e.getMessage();
		}		
	}
	
	
	/**
	 * Ajax 자재정보관리 - 삭제 처리 
	 */		
	@PostMapping(value="materialDelete.do")
	@ResponseBody
	public String materialDelete(MaterialVO materialVO) throws Exception{	
			managementService.materialDelete(materialVO);				
			return "success";
	}
	
	
	
	/**
	 * Bom 등록 폼 
	 */		
	@GetMapping(value="bomWriteForm.do")	
	public String bomWriteForm(MaterialVO materialVO, Model model) throws Exception{
		CommonController.commonMenu("management", "bomWrite", model);
		return JSP_URI+"management_bom_write"; 
	}
	
	/**
	 * 
     * Ajax	Bom 등록 autocomplete 
	 */
	@PostMapping("getBySearchList.do")
	@ResponseBody
	public Map<String, Object> getBySearchList(MaterialVO materialVO ) throws Exception{		
		List<MaterialVO> materialList=managementService.getBySearchList(materialVO);
		Map<String, Object> resultMap=new HashMap<>();
		resultMap.put("materialList", materialList);		
		return resultMap;
	}
	
	
	@GetMapping("mcodeJson.do")
	@ResponseBody
	public List<String> mcodeJson(MaterialVO materialVO ) throws Exception{		
		List<String> mcodeList=managementService.mcodeJson();		
		return mcodeList;
	}
	
	
	/**
	 * Ajax BOM mcode 정보    
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
	 * Ajax  BOM 등록 
	 */
	@PostMapping(value="bomWrite.do")
	@ResponseBody
	public String bomWrite(BomVO bomVO, Model model) throws Exception{
		
		//1. 널 체크 및 등록된 자재 코드 확인
		if(bomVO.getPcode()==null ||  StringUtils.isEmpty(bomVO.getProductName())) return "blank";
		
		List<BomVO> list=new ArrayList<BomVO>();		
		
		for(BomVO vo : bomVO.getList()) {
			if(vo.getMcode()==null ||  StringUtils.isEmpty(vo.getMcode())) return "blank";
			if(vo.getMaterialName()==null ||  StringUtils.isEmpty(vo.getMaterialName())) return "blank";
			if(vo.getMaterialQuantity()==null ||  StringUtils.isEmpty(vo.getMaterialQuantity())) return "blank";
			
			BomVO bomVoInfo=new BomVO();
			//mid 아이디 값 가져오기
			bomVoInfo=managementService.getByMcodeInfo(vo.getMcode());
			
			if(bomVoInfo==null) return "notcode";	//등록 되지 않은 자재 코드		
			bomVoInfo.setPcode(bomVO.getPcode());
			bomVoInfo.setMaterialName(vo.getMaterialName());
			bomVoInfo.setMaterialQuantity(vo.getMaterialQuantity());
			list.add(bomVoInfo);
		}
				
		return managementService.insertBomWrite(bomVO , list);	
	}
	
	
	/**
	 * 자재정보관리 - BOM 조회  페이지
	 */	
	@GetMapping("bomList.do")
	public String bomList(String msg, Model model) throws Exception{
		if(msg!=null && !StringUtils.isEmpty(msg)) {			
			model.addAttribute("msg", msg);	
		}
		CommonController.commonMenu("management", "bomList", model);				
		return JSP_URI+"management_bom_list"; 
	}
	
	
	/**
	 * ajax 자재정보관리 - BOM 조회 
	 */
	@PostMapping(value="bomListAjaxSearch.do")
	@ResponseBody
	public Map<String, Object> bomListAjaxSearch (BomVO bomVO, PageMakerAndSearch  pageMaker , Map<String, Object> map,
			HttpServletRequest request, 
			Model model) throws Exception{	
		
		map.put("pcode", bomVO.getPcode());
		map.put("productName", bomVO.getProductName());
		pageMaker.setPerPageNum(300);
		
		Integer totalCount=managementService.bomSearchTotalCount(map);
		pageMaker.setTotalCount(totalCount);				
		map.put("pageStart", pageMaker.getPageStart());
		map.put("perPageNum", pageMaker.getPerPageNum());
		
		List<BomVO> bomList=managementService.bomSearchList(map);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("page", pageMaker.getPage());
		resultMap.put("totalCount", totalCount);
		resultMap.put("bomList", bomList);
		resultMap.put("tempEndPage", pageMaker.getTempEndPage());
		return resultMap; 
	}
	
	
	/**
	 * ajax 자재정보관리 - BOM 조회  sub - 자재 목록
	 */
	@PostMapping(value="bomToggleMaterialList.do")
	@ResponseBody
	public Map<String, Object> bomToggleMaterialList(BomVO bomVO, Model model) throws Exception{	
		List<BomVO> bomToggleMaterialList=managementService.bomMaterialList(bomVO);			
		Map<String, Object> resultMap = new HashMap<String, Object>();		
		resultMap.put("bomToggleMaterialList", bomToggleMaterialList);		
		return resultMap; 
	}
	
	
	
	/**
	 * ajax 자재정보관리 - BOM 삭제 처리
	 */
	@PostMapping(value="bomDelete.do")
	@ResponseBody
	public String bomDelete(Integer bomid, Model model) throws Exception{			
		managementService.boomDelete(bomid);		
		return "success";
	}
	
	
	/**
	 * 자재정보관리 - BOM 업데이트 화면 이동
	 */
	@PostMapping(value="bomUpdateForm.do")
	public String bomUpdateForm(BomVO bomVO, Model model) throws Exception{	
		CommonController.commonMenu("management", "bomUpdateForm", model);
		
		bomVO=managementService.getByBomInfo(bomVO.getBomid());
		List<BomVO> bomMaterialList=managementService.bomMaterialList(bomVO);
		Integer bomSize=bomMaterialList.size();
		Integer bomMaterCount= bomSize==0 ?  1 : bomSize;
		model.addAttribute("bomVO",bomVO);
		model.addAttribute("bomMaterialList",bomMaterialList);
		model.addAttribute("bomMaterCount", bomMaterCount);
		return JSP_URI+"management_bom_update"; 
	}
	
	
	/**
	 * 자재정보관리 - BOM 업데이트 
	 */	
	@PostMapping(value="bomUpdate.do")
	@ResponseBody
	public String bomUpdate(BomVO bomVO, Model model) throws Exception{	

		
		//1. 널 체크 및 등록된 자재 코드 확인
		if(bomVO.getPcode()==null ||  StringUtils.isEmpty(bomVO.getProductName())) return "blank";
		
		List<BomVO> list=new ArrayList<BomVO>();		
		
		for(BomVO vo : bomVO.getList()) {
			if(vo.getMcode()==null ||  StringUtils.isEmpty(vo.getMcode())) return "blank";
			if(vo.getMaterialName()==null ||  StringUtils.isEmpty(vo.getMaterialName())) return "blank";
			if(vo.getMaterialQuantity()==null ||  StringUtils.isEmpty(vo.getMaterialQuantity())) return "blank";
			
			BomVO bomVoInfo=new BomVO();
			//mid 아이디 값 가져오기
			bomVoInfo=managementService.getByMcodeInfo(vo.getMcode());
			
			if(bomVoInfo==null) return "notcode";	//등록 되지 않은 자재 코드	
			bomVoInfo.setBomid(bomVO.getBomid());
			bomVoInfo.setPcode(bomVO.getPcode());
			bomVoInfo.setMaterialName(vo.getMaterialName());
			bomVoInfo.setMaterialQuantity(vo.getMaterialQuantity());
			list.add(bomVoInfo);
		}
		
		return managementService.bomUpdate(bomVO , list);	
	}
	
	
	
}
