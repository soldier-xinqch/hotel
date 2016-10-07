package org.hotel.service.Impl;

import java.util.Date;
import java.util.List;

import org.hotel.common.ConfigParams;
import org.hotel.mapper.UserMapper;
import org.hotel.model.Org;
import org.hotel.model.User;
import org.hotel.service.IUserService;
import org.hotel.utils.CommonUtil;
import org.hotel.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public int insert(User user) {
		user.setId(CommonUtil.getUUID());
		user.setCreateTime(new Date());
		user.setDelFlag(deleteFlag_insert);
		user.setModifyTime(dateInit);
		user.setPassword(MD5Util.encodeMD5Hex(ConfigParams.initPassword));
		return userMapper.insertSelective(user);
	}

	@Override
	public int modify(User user) {
		user.setModifyTime(new Date());
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int logicDelete(String id) {
		User  user= new User();
		user.setId(id);
		user.setModifyTime(new Date());
		user.setDelFlag(deleteFlag_del);
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int physicsDelete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}

	@Override
	public List<User> findUserByOrgs(List<Org> orgs) {
		return userMapper.findUserByOrgs(orgs);
	}

	@Override
	public User findUserByName(String username) {
		return userMapper.findUserByName(username);
	}

}
