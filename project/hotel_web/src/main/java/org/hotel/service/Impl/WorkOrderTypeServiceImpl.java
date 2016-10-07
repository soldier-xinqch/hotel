package org.hotel.service.Impl;

import java.util.Date;
import java.util.List;

import org.hotel.mapper.WorkOrderTypeMapper;
import org.hotel.model.Org;
import org.hotel.model.WorkOrderType;
import org.hotel.service.IWorkOrderTypeService;
import org.hotel.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service("workOrderTypeService")
public class WorkOrderTypeServiceImpl implements IWorkOrderTypeService{
	
	@Autowired
	private WorkOrderTypeMapper workOrderTypeMapper;
	
	@Override
	public List<WorkOrderType> findAll(){
		return workOrderTypeMapper.findAll();
	}
	@Override
	public List<WorkOrderType> findworkOrderTypeByOrgId(String typeId) {
		return workOrderTypeMapper.findworkOrderTypeByOrgId(typeId);
	}
	
	@Override
	public int insert(WorkOrderType workOrderType) {
		if(null == workOrderType) return 0;
		workOrderType.setId(CommonUtil.getUUID());
		workOrderType.setCreateTime(new Date());
		workOrderType.setModifyTime(dateInit);
		workOrderType.setDelFlag(deleteFlag_insert);
		return workOrderTypeMapper.insertSelective(workOrderType);
	}
	@Override
	public int modify(WorkOrderType workOrderType) {
		if(null == workOrderType) return 0;
		workOrderType.setModifyTime(new Date());
		return workOrderTypeMapper.updateByPrimaryKeySelective(workOrderType);
	}
	@Override
	public int logicDelete(String id) {
		WorkOrderType workOrderType = new WorkOrderType();
		workOrderType.setId(id);
		workOrderType.setDelFlag(deleteFlag_del);
		workOrderType.setModifyTime(new Date());
		return workOrderTypeMapper.updateByPrimaryKeySelective(workOrderType);
	}
	@Override
	@Transactional
	public int physicsDelete(String typeId) {
		if(StringUtils.isEmpty(typeId)) return 0;
		//TODO 查询角色关系表，删除菜单节点的同时删除关系表中的相关数据
		return workOrderTypeMapper.deleteByPrimaryKey(typeId);
	}
	@Override
	public List<WorkOrderType> findWorkOrdersByOrgs(List<Org> orgs) {
		return workOrderTypeMapper.findWorkOrdersByOrgs(orgs);
	}
	
}
