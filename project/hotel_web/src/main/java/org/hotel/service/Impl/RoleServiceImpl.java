package org.hotel.service.Impl;

import java.util.Date;
import java.util.List;

import org.hotel.mapper.RoleMapper;
import org.hotel.model.Org;
import org.hotel.model.Role;
import org.hotel.service.IRoleService;
import org.hotel.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public int insert(Role role) {
		role.setId(CommonUtil.getUUID());
		role.setCreateTime(new Date());
		role.setDelFlag(deleteFlag_insert);
		role.setModifyTime(dateInit);
		return roleMapper.insertSelective(role);
	}

	@Override
	public int modify(Role role) {
		role.setModifyTime(new Date());
		return roleMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public int logicDelete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int physicsDelete(String id) {
		return roleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Role> findAll() {
		return roleMapper.findAll();
	}

	@Override
	public List<Role> findRoleByOrgId(List<Org> orgs) {
		return roleMapper.findRoleByOrgs(orgs);
	}
	

}
