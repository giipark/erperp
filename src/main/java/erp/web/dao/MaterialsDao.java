
package erp.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import erp.web.domain.BulchulVO;
import erp.web.domain.WarehosingMaterialsApplyVO;

@Mapper
@Repository
public interface MaterialsDao {


	public Integer insertWarehosingMaterials(List<WarehosingMaterialsApplyVO> list) throws Exception;

	public List<WarehosingMaterialsApplyVO> selectWarehosingMaterials(Map<String, Object> map) throws Exception;
		
	public Integer warehosingMaterialsTotalCount() throws Exception;

	public Integer updateWarehosingMaterials(WarehosingMaterialsApplyVO wmaVO)  throws Exception;

	public Integer wmaDelete(Integer applicationCode) throws Exception;

	public Integer wmaAllDelete() throws Exception;

	public WarehosingMaterialsApplyVO getByInfoWarehosingMaterials(String applicationCode) throws Exception;

	public WarehosingMaterialsApplyVO getByInfoWarehosingMaterials2(Integer applicationCode) throws Exception;
	
	public List<WarehosingMaterialsApplyVO> getByselectWMAList() throws Exception;

	public void wmaProcessStatusUpdate(WarehosingMaterialsApplyVO wmavo) throws Exception;

	public void materialInformationInsert(WarehosingMaterialsApplyVO vo) throws Exception;

	public void materialInformationUpdate(WarehosingMaterialsApplyVO vo) throws Exception;

	public void wmaSuccessDelete() throws Exception;

	public Integer stockSearchTotalCount(Map<String, Object> map) throws Exception;

	public List<WarehosingMaterialsApplyVO> selectMaterialSearchList(Map<String, Object> map) throws Exception;

	public void stockUpdate(HashMap<String, Object> map) throws Exception;

	public void insertAutoWMA(WarehosingMaterialsApplyVO wmaVO) throws Exception;

	public void insertBulchulApply(WarehosingMaterialsApplyVO wmaVO) throws Exception;

	public Integer bulchulProcessTotalCount(Map<String, Object> map) throws Exception;

	public List<WarehosingMaterialsApplyVO> bulchulProcessList(Map<String, Object> map) throws Exception;

	public void bulchulStatusSave() throws Exception;

	public WarehosingMaterialsApplyVO getByBulchulProcess(Integer bulchulId) throws Exception;

	public void bulchulCurrentInventoryUpdate(BulchulVO bulchulVO) throws Exception;

	public void bulchulApplicationQuantityUpdate(BulchulVO bulchulVO) throws Exception;

	public void bulchulDelete() throws Exception;



}
