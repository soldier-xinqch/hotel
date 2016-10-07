package org.hotel.service.Impl;

import java.util.Date;
import java.util.List;

import org.hotel.mapper.EatTimeMapper;
import org.hotel.model.EatTime;
import org.hotel.service.IEatTimeService;
import org.hotel.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("eatTimeService")
public class EatTimeServiceImpl implements IEatTimeService {

	@Autowired
	private EatTimeMapper eatTimeMapper;
	
	@Override
	public int insert(EatTime eatTime) {
		eatTime.setId(CommonUtil.getUUID());
		eatTime.setCreateTime(new Date());
		eatTime.setDelFlag(deleteFlag_insert);
		eatTime.setModifyTime(dateInit);
		return eatTimeMapper.insertSelective(eatTime);
	}

	@Override
	public int modify(EatTime eatTime) {
		eatTime.setModifyTime(new Date());
		return eatTimeMapper.updateByPrimaryKeySelective(eatTime);
	}

	@Override
	public int logicDelete(String id) {
		EatTime eatTime = new EatTime();
		eatTime.setId(id);
		eatTime.setDelFlag(deleteFlag_del);
		eatTime.setModifyTime(new Date());
		return eatTimeMapper.updateByPrimaryKeySelective(eatTime );
	}

	@Override
	public int physicsDelete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EatTime> findAll() {
		return eatTimeMapper.findAll();
	}

	@Override
	public List<EatTime> findEatTimeByOrgId(String orgId) {
		return eatTimeMapper.findEatTimeByOrgId(orgId);
	}

}
