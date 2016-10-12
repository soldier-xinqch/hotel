package org.hotel.service.Impl;

import java.util.Date;
import java.util.List;

import org.hotel.mapper.StaffMapper;
import org.hotel.model.Org;
import org.hotel.model.Staff;
import org.hotel.service.IStaffService;
import org.hotel.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("staffService")
public class StaffServiceImpl implements IStaffService {

	@Autowired
	private StaffMapper staffMapper;
	
	@Override
	public int insert(Staff staff) {
		staff.setId(CommonUtil.getUUID());
		if(null == staff.getCreateTime())staff.setCreateTime(new Date());
		if(null == staff.getBirthday())staff.setBirthday(dateInit);
		if(null == staff.getModifyTime())staff.setModifyTime(dateInit);
		if(null == staff.getIntoTime())staff.setIntoTime(dateInit);
		if(null == staff.getQuitTime())staff.setQuitTime(dateInit);
		staff.setDelFlag(deleteFlag_insert);
		return staffMapper.insertSelective(staff);
	}

	@Override
	public int modify(Staff staff) {
		staff.setModifyTime(new Date());
		return staffMapper.updateByPrimaryKeySelective(staff);
	}

	@Override
	public int logicDelete(String id) {
		Staff staff = new Staff();
		staff.setId(id);
		staff.setDelFlag(deleteFlag_del);
		staff.setModifyTime(new Date());
		return staffMapper.updateByPrimaryKeySelective(staff );
	}

	@Override
	public int physicsDelete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Staff> findAll() {
		return staffMapper.findAll();
	}

	@Override
	public List<Staff> findStaffsByOrgId(String orgId) {
		return staffMapper.findStaffsByOrgId(orgId);
	}

	@Override
	public List<Staff> findStaffByOrgs(List<Org> orgs,String status) {
		return staffMapper.findStaffByOrgs(orgs,status);
	}

	@Override
	public List<Staff> findStaffAndMac(String status, String mac) {
		return staffMapper.findStaffAndMac(status,mac);
	}

	@Override
	public Staff findStaffAndMacByNoOrName(String no, String name, String mac) {
		return staffMapper.findStaffAndMacByNoOrName(no,name,mac);
	}

	@Override
	public List<Staff> findStaffByLikes(List<Org> orgIds, String[] staffId, String startTime, String endTime,
			String status) {
		return staffMapper.findStaffByLikes(orgIds,staffId,startTime,endTime,status);
	}
}
