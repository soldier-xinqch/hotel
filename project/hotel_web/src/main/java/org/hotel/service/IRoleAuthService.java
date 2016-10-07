package org.hotel.service;

import java.util.List;

import org.hotel.common.BaseService;
import org.hotel.model.Org;
import org.hotel.model.Role;
import org.hotel.model.RoleAuth;

public interface IRoleAuthService extends BaseService<RoleAuth>{

	/**
	 *  根据角色查询角色关联信息
	 * @return
	 */
	RoleAuth findRoleAuthByRoleId(String roleId);

	List<RoleAuth> findRoleAuthByRoles(List<Role> roles);
}
