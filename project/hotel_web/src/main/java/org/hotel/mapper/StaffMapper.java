package org.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.hotel.model.Org;
import org.hotel.model.Staff;

public interface StaffMapper {
    int deleteByPrimaryKey(String id);

    int insert(Staff record);

    int insertSelective(Staff record);

    Staff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Staff record);

    int updateByPrimaryKey(Staff record);

	List<Staff> findAll();

	List<Staff> findStaffsByOrgId(String orgId);

	List<Staff> findStaffByOrgs(@Param("orgs") List<Org> orgs,@Param("status") String status);

	List<Staff> findStaffAndMac(@Param("status") String status,@Param("mac") String mac);

	Staff findStaffAndMacByNoOrName(@Param("no") String no,@Param("name") String name,@Param("mac") String mac);
}