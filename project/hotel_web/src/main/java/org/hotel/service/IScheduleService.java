package org.hotel.service;

import java.util.List;

import org.hotel.common.BaseService;
import org.hotel.model.Org;
import org.hotel.model.ScheduleStaff;
import org.hotel.model.ScheduleWithBLOBs;
/**
 *  排班数据接口
 * @author xinqch
 *
 */
public interface IScheduleService extends BaseService<ScheduleWithBLOBs> {

	/**
	 *  查询所有组织
	 * @return
	 */
	List<ScheduleWithBLOBs> findAll();

	List<ScheduleWithBLOBs> findScheduleByOrgs(List<Org> orgs);

	/**
	 *  根据员工编号和刷卡时间查询排班信息
	 * @param staffNo
	 * @param brushTime
	 * @return
	 */
	ScheduleStaff findStaffScheduleByNoAndTime(String staffNo, String brushTime);
}
