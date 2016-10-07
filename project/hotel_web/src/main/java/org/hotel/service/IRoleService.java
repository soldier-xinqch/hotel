package org.hotel.service;

import java.util.List;

import org.hotel.common.BaseService;
import org.hotel.model.Org;
import org.hotel.model.Role;

public interface IRoleService extends BaseService<Role>{

	/**
	 *  查询所有角色
	 * @return
	 */
	List<Role> findAll();
	/**
	 *  根据orgId查询角色
	 * @return
	 */
	List<Role> findRoleByOrgId(List<Org> orgs);
	
}
