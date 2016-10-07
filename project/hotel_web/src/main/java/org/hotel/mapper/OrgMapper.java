package org.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.hotel.model.Org;

public interface OrgMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(Org record);

    Org selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Org record);

	List<Org> findAll();

	/**
	 *  根据父组织Id获取子组织信息
	 * @param parentId
	 * @return
	 */
	List<Org> findOrgsByParentId(String parentId);
	/**
	 *  根据组织信息查询组织
	 * @param orgs
	 * @return
	 */
	List<Org> findOrgsByIds(@Param("orgs") List<Org> orgs); 
}