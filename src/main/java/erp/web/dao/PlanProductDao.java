
package erp.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import erp.web.domain.BomVO;
import erp.web.domain.PlanProductVO;

@Mapper
@Repository
public interface PlanProductDao {

	public List<BomVO> bomSearchList(String pcode) throws Exception;

	public BomVO getByPcodeInfo(String pcode) throws Exception;

	public Integer getByPcodeComfirm(String pcode) throws Exception;

	public Integer insertPlanProduct(List<PlanProductVO> list) throws Exception;

	public Integer planProductTotalCount(Map<String, Object> map) throws Exception;
	
	public List<PlanProductVO> planProductList(Map<String, Object> map) throws Exception;

	public Integer insertPlanProduct2(PlanProductVO vo) throws Exception;

	public void bulchulPlanDelete(Integer planId) throws Exception;

	public void planProductDelete(Integer planId) throws Exception;

	public void planUpdate(PlanProductVO planProductVO) throws Exception;

	public Integer getProcessStatus(Integer planId) throws Exception; 

	
	
	
}
