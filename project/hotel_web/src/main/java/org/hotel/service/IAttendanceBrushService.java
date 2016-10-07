package org.hotel.service;

import java.util.List;

import org.hotel.common.BaseService;
import org.hotel.model.AttendanceBrush;
import org.hotel.model.Org;

/**
 *  刷卡记录数据接口
 * @author xinqch
 *
 */
public interface IAttendanceBrushService extends BaseService<AttendanceBrush> {

	/**
	 *  查询所有考勤信息
	 * @return
	 */
	List<AttendanceBrush> findAll();

	List<AttendanceBrush> findAttendanceBrushByOrgs(List<Org> orgs);
	/**
	 *  通过人员和设备查询刷卡记录
	 * @param staffNo
	 * @param mac
	 * @param day
	 * @return
	 */
	AttendanceBrush findBrushByStaffAndMac(String staffNo, String mac, String day);
}
