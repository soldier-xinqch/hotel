package org.hotel.service;

import java.util.List;

import org.hotel.common.BaseService;
import org.hotel.model.AttendanceExceptional;
import org.hotel.model.Org;

/**
 *  考勤异常信息数据接口
 * @author xinqch
 *
 */
public interface IAttendanceExceptionalService extends BaseService<AttendanceExceptional> {

	/**
	 *  查询所有考勤信息
	 * @return
	 */
	List<AttendanceExceptional> findAll();
	/**
	 *  根据组织查询考勤异常信息
	 * @param orgs
	 * @return
	 */
	List<AttendanceExceptional> findAttendanceExceptionalByOrgs(List<Org> orgs);
}
