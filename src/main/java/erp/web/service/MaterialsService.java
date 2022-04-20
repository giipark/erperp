
package erp.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import erp.web.dao.MaterialsDao;
import erp.web.domain.BomVO;
import erp.web.domain.BulchulVO;
import erp.web.domain.WarehosingMaterialsApplyVO;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MaterialsService {

	@Autowired
	private MaterialsDao materialsDao;


	public Integer insertWarehosingMaterials(List<WarehosingMaterialsApplyVO> list) throws Exception {
		return materialsDao.insertWarehosingMaterials(list);
	}

	
	public List<WarehosingMaterialsApplyVO> selectWarehosingMaterials(Map<String, Object> map) throws Exception{
		return materialsDao.selectWarehosingMaterials(map);
	}
	
	public Integer warehosingMaterialsTotalCount() throws Exception{
		return materialsDao.warehosingMaterialsTotalCount();
	}

	/**
     * 입고 신청 수정
	 */
	public Integer updateWarehosingMaterials(WarehosingMaterialsApplyVO wmaVO) throws Exception{
		return materialsDao.updateWarehosingMaterials(wmaVO);
	}


	/**
	 * 입고 신청 삭제
	 */
	public Integer wmaDelete(Integer applicationCode) throws Exception{
		return materialsDao.wmaDelete(applicationCode);
	}


	/**
	  * 입고 신청 전체 삭제
	 */
	public Integer wmaAllDelete() throws Exception{
		return materialsDao.wmaAllDelete();
	}

	public WarehosingMaterialsApplyVO getByInfoWarehosingMaterials(String applicationCode)  throws Exception{
		return materialsDao.getByInfoWarehosingMaterials(applicationCode);
		
	}


	public List<WarehosingMaterialsApplyVO> getByselectWMAList() throws Exception{
		return materialsDao.getByselectWMAList();
	}

	@Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.REPEATABLE_READ ,rollbackFor={RuntimeException.class})
	public void wmaProcessSave(List<WarehosingMaterialsApplyVO> list) throws Exception{
		//1. 널 체크 및 등록된 자재 코드 확인
		for(WarehosingMaterialsApplyVO vo : list) {
			
			vo=materialsDao.getByInfoWarehosingMaterials(String.valueOf(vo.getApplicationCode()));
			vo.possibleInventory();			
			
			if(vo.getApplicationQuantity() >vo.getPossibleInventory()) {
				vo.setProcessStatus('2');				
			}else{				
				if(vo.getCurrentInventoryNullConfirm()==null) {//널  -  insert 					
					materialsDao.materialInformationInsert(vo);					
				}else {					//update
					materialsDao.materialInformationUpdate(vo);					
				}				
				//등록 성공 1
				vo.setProcessStatus('1');				
			}				
			materialsDao.wmaProcessStatusUpdate(vo);		
		}				
	}

	public void wmaSuccessDelete() throws Exception{
		materialsDao.wmaSuccessDelete(); 		
	}


	public Integer stockSearchTotalCount(Map<String, Object> map) throws Exception{
		return materialsDao.stockSearchTotalCount(map); 
	}


	public List<WarehosingMaterialsApplyVO> selectMaterialSearchList(Map<String, Object> map) throws Exception{
		return materialsDao.selectMaterialSearchList(map); 
	}


	public void stockUpdate(HashMap<String, Object> map) throws Exception{
		 materialsDao.stockUpdate(map); 		
	}


	/**
	 * 불출 신청 등록
	 *///처리 상태 0:기본,1:입고성공,2:실패-적정 재고수량 초과신청,3:불출처리    // 처리 상태 0:일반불출처리,1:생산계획불출처리
	@Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.REPEATABLE_READ ,rollbackFor={RuntimeException.class})
	public void bulchulApplyWrite(List<BomVO> list) throws Exception{
		WarehosingMaterialsApplyVO wmaVO =new WarehosingMaterialsApplyVO();
		for(BomVO vo : list) {
			
			wmaVO.setMaid(vo.getMaid());
			wmaVO.setWarehosingMaterialName(vo.getMaterialName());
			wmaVO.setApplicationQuantity(vo.getApplicationQuantity());//신청수량
			wmaVO.setProcessStatus('0');// 0:일반불출처리
			materialsDao.insertBulchulApply(wmaVO);
			
			if(vo.getApplicationQuantity() > vo.getCurrentInventory()) {//신청수량 > 현재 재고
				//입고신청
				Integer wmaQuantity=vo.getApplicationQuantity()-vo.getCurrentInventory();  			    							
				wmaVO.setApplicationQuantity(wmaQuantity);//차이값
				wmaVO.setProcessStatus('3'); //3:불출처리
				materialsDao.insertAutoWMA(wmaVO);
			}
			
		}

	}


	public Integer bulchulProcessTotalCount(Map<String, Object> map) throws Exception{
		return materialsDao.bulchulProcessTotalCount(map);
	}


	public List<WarehosingMaterialsApplyVO> bulchulProcessList(Map<String, Object> map) throws Exception{
		return materialsDao.bulchulProcessList(map);
	}


	/**
	 * 생산 계획 불출 상태 변경 
	 */
	@Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.REPEATABLE_READ ,rollbackFor={RuntimeException.class})
	public void bulchulStatusSave(List<WarehosingMaterialsApplyVO> bulchulList) throws Exception{
		for(WarehosingMaterialsApplyVO wmaVO : bulchulList) {
			if(wmaVO.getApplicationQuantity() > wmaVO.getCurrentInventory()) {//신청수량 > 현재 재고
				//입고신청
				Integer wmaQuantity=wmaVO.getApplicationQuantity()- wmaVO.getCurrentInventory();  
				wmaVO.setWarehosingMaterialName(wmaVO.getMaterialName());
				wmaVO.setApplicationQuantity(wmaQuantity);//차이값
				wmaVO.setProcessStatus('3'); //3:불출처리
				wmaVO.setRemarks("생산계획");
				materialsDao.insertAutoWMA(wmaVO);
			}			
		}		
		materialsDao.bulchulStatusSave();		
	}


	public WarehosingMaterialsApplyVO getByBulchulProcess(Integer bulchulId)  throws Exception{					
		return materialsDao.getByBulchulProcess(bulchulId);
	}


	@Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.REPEATABLE_READ ,rollbackFor={RuntimeException.class})
	public void bulchulProcessSave(BulchulVO bulchulVO) throws Exception{		
		materialsDao.bulchulCurrentInventoryUpdate(bulchulVO);
		materialsDao.bulchulApplicationQuantityUpdate(bulchulVO);
		materialsDao.bulchulDelete();
	}




	

	
	
	
	
}
