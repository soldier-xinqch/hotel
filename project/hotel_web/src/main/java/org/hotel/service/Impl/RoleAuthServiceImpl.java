package org.hotel.service.Impl;

import java.util.Date;
import java.util.List;

import org.hotel.mapper.RoleAuthMapper;
import org.hotel.model.Role;
import org.hotel.model.RoleAuth;
import org.hotel.service.IRoleAuthService;
import org.hotel.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleAuthService")
public class RoleAuthServiceImpl implements IRoleAuthService {

	@Autowired
	private RoleAuthMapper roleAuthMapper;
	
	@Override
	public int insert(RoleAuth roleAuth) {
		roleAuth.setId(CommonUtil.getUUID());
		roleAuth.setCreateTime(new Date());
		roleAuth.setModifyTime(dateInit);
		roleAuth.setDelFlag(deleteFlag_insert);
		return roleAuthMapper.insertSelective(roleAuth);
	}

	@Override
	public int modify(RoleAuth roleAuth) {
		roleAuth.setModifyTime(new Date());
		return roleAuthMapper.updateByPrimaryKeySelective(roleAuth);
	}

	@Override
	public int logicDelete(String id) {
		RoleAuth roleAuth = new RoleAuth();
		roleAuth.setId(id);
		roleAuth.setDelFlag(deleteFlag_del);
		roleAuth.setModifyTime(new Date());
		return roleAuthMapper.updateByPrimaryKeySelective(roleAuth);
	}

	@Override
	public int physicsDelete(String id) {
		return roleAuthMapper.deleteByPrimaryKey(id);
	}

	@Override
	public RoleAuth findRoleAuthByRoleId(String roleId) {
		return roleAuthMapper.findRoleAuthByRoleId(roleId);
	}

	@Override
	public List<RoleAuth> findRoleAuthByRoles(List<Role> roles) {
		// TODO Auto-generated method stub
		return null;
	}


}
