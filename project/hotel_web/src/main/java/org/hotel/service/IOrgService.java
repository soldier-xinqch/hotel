package org.hotel.service;

import java.util.List;

import org.hotel.common.BaseService;
import org.hotel.model.Org;

public interface IOrgService extends BaseService<Org> {

	/**
	 *  查询所有组织
	 * @return
	 */
	List<Org> findAll();
	/**
	 *  根据ID查询组织
	 * @param orgId
	 * @return
	 */
	Org findOrgById(String orgId);
	/**
	 *  根据组织ID获取所有子信息
	 * @param orgId
	 * @return
	 */
	List<Org> findOrgListById(String orgId);
	/**
	 *  根据组织ID获取所有子信息 (包括自己)
	 * @param orgId
	 * @param includSelf
	 * @return
	 */
	List<Org> findOrgListById(String orgId,boolean includSelf);
	/**
	 * 根据父Id查询子组织信息
	 * @param parentId
	 * @return
	 */
	List<Org> findOrgsByParentId(String parentId);
}
