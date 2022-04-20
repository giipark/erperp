
package erp.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import erp.web.domain.BomVO;
import erp.web.domain.MaterialVO;

@Mapper
@Repository
public interface ManagementDao {


	public void insertMaterial(List<MaterialVO> list) throws Exception;

	public Integer selectMaterialSearchTotalCount(Map<String, Object> map) throws Exception;

	public List<MaterialVO> selectMaterialSearchList(Map<String, Object> map) throws Exception;

	public Integer materialMcodeConfirm(MaterialVO materialVO) throws Exception;

	public void materialUpdate(Map<String, Object> map) throws Exception;

	public void materialDelete(MaterialVO materialVO) throws Exception;

	public List<MaterialVO> getBySearchList(MaterialVO materialVO) throws Exception;

	public List<String> mcodeJson() throws Exception;

	public BomVO getByMcodeInfo(String mcode) throws Exception;

	public Integer insertBomProduct(BomVO bomVO) throws Exception;

	public Integer insertBomMaterial(List<BomVO> bomVO) throws Exception;

	public Integer bomSearchTotalCount(Map<String, Object> map) throws Exception;

	public List<BomVO> bomSearchList(Map<String, Object> map) throws Exception;

	public List<BomVO> bomMaterialList(BomVO bomVO) throws Exception;

	public BomVO getByBomInfo(int bomid) throws Exception;

	public void bomMaterialDelete(Integer bomid) throws Exception;

	public void bomProductDelete(Integer bomid) throws Exception;

	public void updateBomProduct(BomVO bomVO) throws Exception;

	public void updateBomMaterial(List<BomVO> list) throws Exception;

	public void bomMaidMaterialDelete(Integer maid) throws Exception;
	

	
	
}
