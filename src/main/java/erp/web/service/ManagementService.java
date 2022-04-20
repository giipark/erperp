package erp.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import erp.web.dao.ManagementDao;
import erp.web.domain.BomVO;
import erp.web.domain.MaterialVO;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ManagementService {
	
	@Autowired
	private ManagementDao managementDao;


	/**
	 * 자재  등록
	 */
	public boolean insertMaterial(List<MaterialVO> list) throws Exception {	
		for(MaterialVO vo : list) {
			if(vo.getMcode()==null ||  StringUtils.isEmpty(vo.getMcode())) return false;					
			if(vo.getMaterialName()==null ||  StringUtils.isEmpty(vo.getMaterialName()))return false;					
			if(vo.getProperInventory()==null ||  StringUtils.isEmpty(vo.getProperInventory())) return false;
		}
		managementDao.insertMaterial(list);
		return true;
	}


	/**
	 * 자재 전체 개수
	 */
	public Integer selectMaterialSearchTotalCount(Map<String, Object> map)  throws Exception{		
		return managementDao.selectMaterialSearchTotalCount(map);
	}


	/**
	 * @param map
	 * @return
	 */
	public List<MaterialVO> selectMaterialSearchList(Map<String, Object> map) throws Exception{	
		return managementDao.selectMaterialSearchList(map);
	}


	/**
	 * 자재 등록
	 */
	public void materialUpdate(Map<String, Object> map) throws Exception{	
		 managementDao.materialUpdate(map);
	}


	/**
	 * 자재 삭제
	 */
	@Transactional
	public void materialDelete(MaterialVO materialVO) throws Exception{
		 managementDao.bomMaidMaterialDelete(materialVO.getMaid());
		 managementDao.materialDelete(materialVO);		
	}


	/** 
     * Ajax	Bom 등록 autocomplete 
	 */
	public List<MaterialVO> getBySearchList(MaterialVO materialVO) throws Exception{
		return  managementDao.getBySearchList(materialVO);
	}


	/**
	 * mcode Json
	 */
	public List<String> mcodeJson() throws Exception{
		return  managementDao.mcodeJson();
	}


	public BomVO getByMcodeInfo(String mcode) throws Exception{
		return  managementDao.getByMcodeInfo(mcode);
	}


	/**
	 * Ajax  BOM 등록 
	 */
	@Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.REPEATABLE_READ ,rollbackFor={RuntimeException.class})
	public String insertBomWrite(BomVO bomVO ,List<BomVO>  list) throws Exception{	

		try{			
			//2.BOM 제품 등록  , BOM 제품 중복 코드 확인			
			managementDao.insertBomProduct(bomVO);																
			
			//3.BOM 자재 등록	
			managementDao.insertBomMaterial(list);				
			return "success";				
		}catch (Exception e) {
			if(e.getMessage().contains("Duplicate")) return "duplicate";		
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();			
			throw new RuntimeException();
		}		
	}


	/**
	 * BOM 조회 전체 개수
	 */
	public Integer bomSearchTotalCount(Map<String, Object> map) throws Exception {		
		return managementDao.bomSearchTotalCount(map);
	}


	/**
	 * BOM 조회
	 */
	public List<BomVO> bomSearchList(Map<String, Object> map) throws Exception{
		return managementDao.bomSearchList(map);
	}


	/**
	 * ajax 자재정보관리 - BOM 조회  sub - 자재 목록
	 */
	public List<BomVO> bomMaterialList(BomVO bomVO) throws Exception{
		return managementDao.bomMaterialList(bomVO);
	}


	/**
	 * @param parseInt
	 * @return
	 */
	public BomVO getByBomInfo(int bomid) throws Exception{
		return managementDao.getByBomInfo(bomid);
	}


	/**
	 * 자재정보관리 - BOM 삭제 처리
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ ,
			rollbackFor={RuntimeException.class})
	public void boomDelete(Integer bomid) throws Exception{
		//1. 자재 삭제
		managementDao.bomMaterialDelete(bomid);
		//2. 제품 삭제
		managementDao.bomProductDelete(bomid);		
	}


	
	/**
	 * 자재정보관리 - BOM 업데이트 
	 */	
	@Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.REPEATABLE_READ ,rollbackFor={RuntimeException.class})
	public String bomUpdate(BomVO bomVO, List<BomVO> list) throws Exception{
		try{
			
			//1.BOM 제품 등록  , BOM 제품 중복 코드 확인			
			managementDao.updateBomProduct(bomVO);																
			
			//2. 기존 등록된 자재 삭제
			managementDao.bomMaterialDelete(bomVO.getBomid());
			
			//3.BOM 자재 등록	
			managementDao.updateBomMaterial(list);		
			
			return "success";				
		}catch (Exception e) {
			if(e.getMessage().contains("Duplicate")) return "duplicate";		
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();			
			throw new RuntimeException();
		}		
	}

	
	
	
	
}
