package org.hotel.service.Impl;

import java.util.Date;
import java.util.List;

import org.hotel.mapper.OrgMacMapper;
import org.hotel.model.Org;
import org.hotel.model.OrgMac;
import org.hotel.service.IOrgMacService;
import org.hotel.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orgMacService")
public class OrgMacServiceImpl implements IOrgMacService {

	@Autowired
	private OrgMacMapper orgMacMapper;
	
	@Override
	public int insert(OrgMac orgMac) {
		orgMac.setId(CommonUtil.getUUID());
		orgMac.setCreateTime(new Date());
		orgMac.setDelFlag(deleteFlag_insert);
		orgMac.setModifyTime(dateInit);
		return orgMacMapper.insertSelective(orgMac);
	}

	@Override
	public int modify(OrgMac orgMac) {
		orgMac.setModifyTime(new Date());
		return orgMacMapper.updateByPrimaryKeySelective(orgMac);
	}

	@Override
	public int logicDelete(String id) {
		OrgMac staff = new OrgMac();
		staff.setId(id);
		staff.setDelFlag(deleteFlag_del);
		staff.setModifyTime(new Date());
		return orgMacMapper.updateByPrimaryKeySelective(staff );
	}

	@Override
	public int physicsDelete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrgMac> findAll() {
		return orgMacMapper.findAll();
	}

	@Override
	public List<OrgMac> findOrgMacByOrgs(List<Org> orgs) {
		return orgMacMapper.findOrgMacByOrgs(orgs);
	}

	@Override
	public OrgMac findOrgMacByMac(String mac) {
		return orgMacMapper.findOrgMacByMac(mac);
	}
	
	@Override
	public OrgMac findOrgMacByMacName(String macName) {
		return orgMacMapper.findOrgMacByMacName(macName);
	}
}
