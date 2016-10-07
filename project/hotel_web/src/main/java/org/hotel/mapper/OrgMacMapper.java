package org.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.hotel.model.Org;
import org.hotel.model.OrgMac;

public interface OrgMacMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrgMac record);

    int insertSelective(OrgMac record);

    OrgMac selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrgMac record);

    int updateByPrimaryKey(OrgMac record);

	List<OrgMac> findAll();

	List<OrgMac> findOrgMacByOrgs(@Param("orgs") List<Org> orgs);

	OrgMac findOrgMacByMac(String mac);

	OrgMac findOrgMacByMacName(String macName);
}