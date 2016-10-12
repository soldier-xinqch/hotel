package org.hotel.service;

import java.util.List;

import org.hotel.common.BaseService;
import org.hotel.model.AttendanceRecord;
import org.hotel.model.Org;

/**
 *  考勤单数据接口
 * @author xinqch
 *
 */
public interface IAttendanceRecordService extends BaseService<AttendanceRecord> {

	/**
	 *  查询所有考勤信息
	 * @return
	 */
	List<AttendanceRecord> findAll();
	/**
	 *  根据组织查询考勤单
	 * @param orgs
	 * @return
	 */
	List<AttendanceRecord> findAttendanceRecordByOrgs(List<Org> orgs);
	/**
	 *  模糊查询考勤单信息
	 * @param orgs
	 * @param staffId
	 * @return
	 */
	List<AttendanceRecord> findAttendanceRecordByLikes(List<Org> orgs, String[] staffId);
}
