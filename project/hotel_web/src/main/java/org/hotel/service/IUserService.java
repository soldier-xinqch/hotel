package org.hotel.service;

import java.util.List;

import org.hotel.common.BaseService;
import org.hotel.model.Org;
import org.hotel.model.User;

public interface IUserService extends BaseService<User>{

	/**
	 *  查询所有用户
	 * @return
	 */
	List<User> findAll();
	/**
	 *  根据orgID查询用户
	 * @return
	 */
	List<User> findUserByOrgs(List<Org> orgs);
	
	
	User findUserByName(String username);
}
