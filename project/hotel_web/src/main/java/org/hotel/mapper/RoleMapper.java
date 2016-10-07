package org.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.hotel.model.Org;
import org.hotel.model.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

	List<Role> findAll();

	List<Role> findRoleByOrgs(@Param("orgs")  List<Org> orgs);
}