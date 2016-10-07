package org.hotel.service.Impl;

import java.util.List;

import org.hotel.mapper.AttendanceExceptionalMapper;
import org.hotel.model.AttendanceExceptional;
import org.hotel.model.Org;
import org.hotel.service.IAttendanceExceptionalService;
import org.hotel.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("attendanceExceptionalService")
public class AttendanceExceptionalServiceImpl implements IAttendanceExceptionalService{
	
	@Autowired
	private AttendanceExceptionalMapper attendanceExceptionalMapper;

	@Override
	public int insert(AttendanceExceptional attendanceExceptional) {
		attendanceExceptional.setId(CommonUtil.getUUID());
		return attendanceExceptionalMapper.insertSelective(attendanceExceptional);
	}

	@Override
	public int modify(AttendanceExceptional attendanceArrange) {
		return attendanceExceptionalMapper.updateByPrimaryKeySelective(attendanceArrange);
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
	public List<AttendanceExceptional> findAll() {
		return attendanceExceptionalMapper.findAll();
	}

	@Override
	public List<AttendanceExceptional> findAttendanceExceptionalByOrgs(List<Org> orgs) {
		return attendanceExceptionalMapper.findAttendanceExceptionalByOrgs(orgs);
	}
	
}
