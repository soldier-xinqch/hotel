package org.hotel.service.Impl;

import java.util.List;

import org.hotel.mapper.AttendanceBrushMapper;
import org.hotel.model.AttendanceBrush;
import org.hotel.model.Org;
import org.hotel.service.IAttendanceBrushService;
import org.hotel.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("attendanceBrushService")
public class AttendanceBrushServiceImpl implements IAttendanceBrushService{
	
	@Autowired
	private AttendanceBrushMapper attendanceBrushMapper;

	@Override
	public int insert(AttendanceBrush attendanceBrush) {
		attendanceBrush.setId(CommonUtil.getUUID());
		return attendanceBrushMapper.insertSelective(attendanceBrush);
	}

	@Override
	public int modify(AttendanceBrush attendanceBrush) {
		return attendanceBrushMapper.updateByPrimaryKeySelective(attendanceBrush);
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
	public List<AttendanceBrush> findAll() {
		return attendanceBrushMapper.findAll();
	}

	@Override
	public List<AttendanceBrush> findAttendanceBrushByOrgs(List<Org> orgs) {
		return attendanceBrushMapper.findAttendanceBrushByOrgs(orgs);
	}

	@Override
	public AttendanceBrush findBrushByStaffAndMac(String staffNo,String mac,String day) {
		return attendanceBrushMapper.findBrushByStaffAndMac(staffNo,mac,day);
	}
	
	
	
}
