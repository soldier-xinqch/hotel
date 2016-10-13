package org.hotel.mapper;

import org.hotel.model.RoleAuth;

public interface RoleAuthMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(RoleAuth record);

    RoleAuth selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoleAuth record);

	RoleAuth findRoleAuthByRoleId(String roleId); 
} 