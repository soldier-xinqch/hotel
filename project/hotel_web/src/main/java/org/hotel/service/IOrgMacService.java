package org.hotel.service;

import java.util.List;

import org.hotel.common.BaseService;
import org.hotel.model.Org;
import org.hotel.model.OrgMac;

/**
 *  设备信息数据接口
 * @author xinqch
 *
 */
public interface IOrgMacService extends BaseService<OrgMac>{

	/**
	 *  查询所有组织下的机器
	 * @return
	 */
	List<OrgMac> findAll();
	/**
	 *  根据组织信息查询人员信息
	 * @param orgs
	 * @return
	 */
	List<OrgMac> findOrgMacByOrgs(List<Org> orgs);
	/**
	 *  根据mac地址查询设备信息
	 * @param mac
	 * @return
	 */
	OrgMac findOrgMacByMac(String mac);
	/**
	 *  根据mac名称查询设备
	 * @param macName
	 * @return
	 */
	OrgMac findOrgMacByMacName(String macName);
}
