package org.hotel.service;

import java.util.List;

import org.hotel.common.BaseService;
import org.hotel.model.Org;
import org.hotel.model.Staff;

public interface IStaffService extends BaseService<Staff>{

	/**
	 *  查询所有员工
	 * @return
	 */
	List<Staff> findAll();
	/**
	 *  根据orgID查询员工
	 * @return
	 */
	List<Staff> findStaffsByOrgId(String orgId);
	/**
	 *  根据组织信息及人员状态查询人员信息
	 * @param orgs
	 * @return
	 */
	List<Staff> findStaffByOrgs(List<Org> orgs,String status);
	/**
	 *  根据mac地址和员工状态获取员工信息
	 * @param status
	 * @param mac
	 * @return
	 */
	List<Staff> findStaffAndMac(String status,String mac);
	/**
	 *  根据编号名称以及mac地址获取人员信息
	 * @param no
	 * @param name
	 * @param mac
	 * @return
	 */
	Staff findStaffAndMacByNoOrName(String no, String name, String mac);
}
