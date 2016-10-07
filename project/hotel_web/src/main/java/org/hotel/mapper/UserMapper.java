package org.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.hotel.model.Org;
import org.hotel.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

	List<User> findAll();

	List<User> findUserByOrgs(@Param("orgs")  List<Org> orgs);

	User findUserByName(String username);
}