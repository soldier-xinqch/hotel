package org.hotel.service.Impl;

import java.util.List;

import org.hotel.mapper.AttendanceArrangeMapper;
import org.hotel.model.AttendanceArrange;
import org.hotel.model.Org;
import org.hotel.service.IAttendanceArrangeService;
import org.hotel.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("attendanceArrangeService")
public class AttendanceArrangeServiceImpl implements IAttendanceArrangeService{
	
	@Autowired
	private AttendanceArrangeMapper attendanceArrangeMapper;

	@Override
	public int insert(AttendanceArrange attendanceArrange) {
		attendanceArrange.setId(CommonUtil.getUUID());
		return attendanceArrangeMapper.insertSelective(attendanceArrange);
	}

	@Override
	public int modify(AttendanceArrange attendanceArrange) {
		return attendanceArrangeMapper.updateByPrimaryKeySelective(attendanceArrange);
	}

	@Override
	public int logicDelete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int physicsDelete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AttendanceArrange> findAll() {
		return attendanceArrangeMapper.findAll();
	}

	@Override
	public List<AttendanceArrange> findAttendanceArrangeByOrgs(List<Org> orgs) {
		return attendanceArrangeMapper.findAttendanceArrangeByOrgs(orgs);
	}
	
}
