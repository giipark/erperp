package erp.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import erp.common.util.PageMakerAndSearch;
import erp.web.common.CommonController;
import erp.web.domain.BomVO;
import erp.web.domain.PlanProductVO;
import erp.web.service.ManagementService;
import erp.web.service.PlanProductService;
import lombok.extern.log4j.Log4j;
/**
 * 
* FileName : PlanProductController.java
* Comment  : 생산 탭- 계획 생산
 */

@Controller
@RequestMapping(value="/erp/planproduct/")
@Log4j
public class PlanProductController {
	
	private static final String JSP_URI="erp/planproduct/";
	
	@Autowired
	private PlanProductService planProductService;
	
	
	@Autowired
	private ManagementService managementService;
	
	/**
	 *  index
	 */
	@GetMapping("")
	public String index(Model model) throws Exception{		
		return JSP_URI+"planproduct_write";
	}
	
	
	/**
	 * 계획 등록 페이지
	 */
	@GetMapping(value="writeForm.do")
	public String writeForm(Model model) throws Exception{		
		CommonController.commonMenu("planproduct", "planWrite", model);
		return JSP_URI+"planproduct_write";
	}

	
	/**
	 * 
     * Ajax	Bom pcode 목록
	 */
	@PostMapping("bomSearchList.do")
	@ResponseBody
	public Map<String, Object> bomSearchList(String pcode) throws Exception{		
		List<BomVO> bomList=planProductService.bomSearchList(pcode);
		Map<String, Object> resultMap=new HashMap<>();
		resultMap.put("bomList", bomList);		
		return resultMap;
	}

	
	/**
	 * 
     * Ajax	Bom 정보
	 */
	@PostMapping("getByPcodeInfo.do")
	@ResponseBody
	public Map<String, Object> getByPcodeInfo(String pcode) throws Exception{		
		BomVO bomInfo=planProductService.getByPcodeInfo(pcode);
		Map<String, Object> resultMap=new HashMap<>();
		resultMap.put("bomInfo", bomInfo);		
		return resultMap;
	}

	
	/**
	 * 계획 등록
	 */
	@PostMapping(value="write.do")
	@ResponseBody
	public String write(PlanProductVO planProductVO, Model model) throws Exception{	
		try {
			return planProductService.insertPlanProduct(planProductVO.getList());				
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
		
	}
	
	
	/**
	 * 계획 조회, 삭제, 수정 페이지
	 */
	@GetMapping(value="list.do")
	public String list(PlanProductVO planProductVO,  String msg, Model model) throws Exception{					
		CommonController.commonMenu("planproduct", "planList", model);
		model.addAttribute("msg", msg);
		return JSP_URI+"planproduct_list";
	}
	
		

	
	/**
	 * ajax 생산 계획 - 조회
	 */
	@PostMapping(value="planListAjaxSearch.do")
	@ResponseBody
	public Map<String, Object> planListAjaxSearch (PlanProductVO planProductVO, PageMakerAndSearch  pageMaker , Map<String, Object> map,
			HttpServletRequest request, 
			Model model) throws Exception{	
		
		map.put("pcode", planProductVO.getPcode());
		map.put("planProductName", planProductVO.getPlanProductName());
		map.put("startDate", planProductVO.getStartDate());
		map.put("endDate", planProductVO.getEndDate());
		pageMaker.setPerPageNum(300);
		
		Integer totalCount=planProductService.planProductTotalCount(map);
		pageMaker.setTotalCount(totalCount);				
		map.put("pageStart", pageMaker.getPageStart());
		map.put("perPageNum", pageMaker.getPerPageNum());
		
		List<PlanProductVO> planList=planProductService.planProductList(map);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("page", pageMaker.getPage());
		resultMap.put("totalCount", totalCount);
		resultMap.put("planList", planList);
		resultMap.put("tempEndPage", pageMaker.getTempEndPage());
		return resultMap; 
	}
	
	
	/**
	 * ajax 자재정보관리 - BOM 조회  sub - 자재 목록
	 */
	@PostMapping(value="bomToggleMaterialList.do")
	@ResponseBody
	public Map<String, Object> bomToggleMaterialList(BomVO bomVO, String planId, String planProductQuantity,  Model model) throws Exception{	
		List<BomVO> bomToggleMaterialList=managementService.bomMaterialList(bomVO);	
		
		for(BomVO vo : bomToggleMaterialList) {
			vo.setSumQuantity(vo.getMaterialQuantity()*Integer.parseInt(planProductQuantity));
			vo.setPlanProductQuantity(Integer.parseInt(planProductQuantity));
			vo.numFormat();
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("planProductQuantity", planProductQuantity);		
		resultMap.put("bomToggleMaterialList", bomToggleMaterialList);		
		return resultMap; 
	}
	
	/**
	//생산 계획 삭제
	 */
	@PostMapping(value="planDelete.do")
	@ResponseBody
	public String planDelete(Integer planId) throws Exception{					
		planProductService.planDelete(planId);		
		return "success"; 
	}
	
	
	/**
	 *  생산 계획 업데이트
	 */
	@PostMapping(value="planUpdate.do")
	@ResponseBody
	public String planUpdate(PlanProductVO planProductVO) throws Exception{					
		planProductService.planUpdate(planProductVO);		
		return "success"; 
	}
	

	
}
