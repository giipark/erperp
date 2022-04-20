
package erp.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import erp.web.dao.ManagementDao;
import erp.web.dao.MaterialsDao;
import erp.web.dao.PlanProductDao;
import erp.web.domain.BomVO;
import erp.web.domain.PlanProductVO;
import erp.web.domain.WarehosingMaterialsApplyVO;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class PlanProductService {

	@Autowired
	private PlanProductDao planProductDao;

	@Autowired
	private ManagementDao managementDao;

	@Autowired
	private MaterialsDao materialsDao;
	
	
	public List<BomVO> bomSearchList(String pcode) throws Exception{
		return planProductDao.bomSearchList(pcode);
	}


	/**
	 * 
     * Ajax	Bom 정보
	 */
	public BomVO getByPcodeInfo(String pcode) throws Exception{
		return planProductDao.getByPcodeInfo(pcode);
	}


	/**
	 * BOM 에 등록된 pcode 인지 확인
	 * 
	 */
	public Integer getByPcodeComfirm(String pcode) throws Exception{
		return planProductDao.getByPcodeComfirm(pcode);
	}


	/**
	 * 계획 등록
	 */
	@Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.REPEATABLE_READ ,rollbackFor={RuntimeException.class})
	public String insertPlanProduct(List<PlanProductVO> list) throws Exception{
		
		for(PlanProductVO vo : list) {
			if(vo.getPcode()==null ||  StringUtils.isEmpty(vo.getPcode())) return "blank";
			if(vo.getPlanProductName()==null ||  StringUtils.isEmpty(vo.getPlanProductName())) return "blank";
			if(vo.getPlanProductQuantity()==null ||  StringUtils.isEmpty(vo.getPlanProductQuantity())) return "blank";
			if(vo.getDeadline()==null ||  StringUtils.isEmpty(vo.getDeadline())) return "blank";
						
			//BOM 에 등록된 pcode 인지 확인
			Integer bomid=planProductDao.getByPcodeComfirm(vo.getPcode());
			
			if(bomid==null) return "notcode";	//등록 되지 않은 BOM  코드					
			//bomid 셋팅
			vo.setBomid(bomid);
		}
		
		//Integer result=planProductDao.insertPlanProduct(list);	

		
		WarehosingMaterialsApplyVO wmaVO =new WarehosingMaterialsApplyVO();
		BomVO bomVO=new BomVO();
		for(PlanProductVO vo : list) {
				//1 등록
				Integer planId =planProductDao.insertPlanProduct2(vo);	
				wmaVO.setPlanId(vo.getPlanId());
				log.info("\n\n\n 결과 반환 값 아이디  : " + vo.getPlanId()) ;
				//bomid 에 해당하는 자재 가져오기
				bomVO.setBomid(vo.getBomid());
				
				List<BomVO> bomList =managementDao.bomMaterialList(bomVO);
				for(BomVO bvo : bomList) {
					  // 처리 상태 0:일반불출처리,1:생산계획불출처리
					
					wmaVO.setMaid(bvo.getMaid());
					wmaVO.setBomid(bvo.getBomid());
					wmaVO.setWarehosingMaterialName(bvo.getMaterialName());
					wmaVO.setApplicationQuantity(bvo.getMaterialQuantity()*vo.getPlanProductQuantity());//신청수량
					wmaVO.setProcessStatus('1');// 1:생산계획불출처리
					wmaVO.setRemarks("생산계획");
					materialsDao.insertBulchulApply(wmaVO);					
				}
		}

		return "success";
				
	}


	/**
	 * 계획 조회  전체 개수
	 */
	public Integer planProductTotalCount(Map<String, Object> map) throws Exception {
		return planProductDao.planProductTotalCount(map);
	}


	/**
	 * planProductList
	 */
	public List<PlanProductVO> planProductList(Map<String, Object> map) throws Exception{
		return planProductDao.planProductList(map);
	}


	@Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.REPEATABLE_READ ,rollbackFor={RuntimeException.class})
	public void planDelete(Integer planId) throws Exception{		
		planProductDao.bulchulPlanDelete(planId);
		planProductDao.planProductDelete(planId);
	}


	/**
	 * 생산 계획 업데이트
	 */
	@Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.REPEATABLE_READ ,rollbackFor={RuntimeException.class})
	public void planUpdate(PlanProductVO planProductVO) throws Exception{	
		//불출 처리 상태 0:일반불출처리,1:생산계획불출처리 확인
		Integer processStatus=planProductDao.getProcessStatus(planProductVO.getPlanId());
		//0 일경우 저장된 상태라 tbl_plan_product 테이블만 변경
		
		if(processStatus==null || processStatus.equals("") || processStatus==0) {
			planProductVO.setPlanProductQuantity(null); //신청 수량 변경 없음
			planProductDao.planUpdate(planProductVO);
			
		}else if(processStatus==1){			
			//1. tbl_plan_product 업데이트
			planProductDao.planUpdate(planProductVO);
			
			//2. tbl_bulchul  삭제
			planProductDao.bulchulPlanDelete(planProductVO.getPlanId());
			
			//2.bomid 에 해당하는 자재 가져오기
			WarehosingMaterialsApplyVO wmaVO =new WarehosingMaterialsApplyVO();
			BomVO bomVO=new BomVO();
			bomVO.setBomid(planProductVO.getBomid());
			List<BomVO> bomList =managementDao.bomMaterialList(bomVO);
			
			
			//3.자재 등록
			for(BomVO bvo : bomList) {
				  // 처리 상태 0:일반불출처리,1:생산계획불출처리				
				wmaVO.setMaid(bvo.getMaid());
				wmaVO.setPlanId(planProductVO.getPlanId());
				wmaVO.setBomid(bvo.getBomid());
				wmaVO.setWarehosingMaterialName(bvo.getMaterialName());
				wmaVO.setApplicationQuantity(bvo.getMaterialQuantity()* planProductVO.getPlanProductQuantity());//신청수량
				wmaVO.setProcessStatus('1');// 1:생산계획불출처리
				wmaVO.setRemarks("생산계획");
				materialsDao.insertBulchulApply(wmaVO);					
			}
			
			
		}
		
		
	}
	
}
