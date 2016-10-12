package org.hotel.service.Impl;

import java.util.Date;
import java.util.List;

import org.hotel.mapper.WorkOrderMapper;
import org.hotel.model.Org;
import org.hotel.model.WorkOrder;
import org.hotel.service.IWorkOrderService;
import org.hotel.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service("workOrderService")
public class WorkOrderServiceImpl implements IWorkOrderService{
	
	@Autowired
	private WorkOrderMapper workOrderMapper;
	
	@Override
	public List<WorkOrder> findAll(){
		return workOrderMapper.findAll();
	}
	@Override
	public List<WorkOrder> findworkOrderByOrgId(String orgId) {
		return workOrderMapper.findworkOrderByOrgId(orgId);
	}
	
	@Override
	public int insert(WorkOrder workOrder) {
		if(null == workOrder) return 0;
		workOrder.setId(CommonUtil.getUUID());
		workOrder.setCreateTime(new Date());
		workOrder.setModifyTime(dateInit);
		workOrder.setDelFlag(deleteFlag_insert);
		return workOrderMapper.insertSelective(workOrder);
	}
	@Override
	public int modify(WorkOrder workOrder) {
		if(null == workOrder) return 0;
		workOrder.setModifyTime(new Date());
		return workOrderMapper.updateByPrimaryKeySelective(workOrder);
	}
	@Override
	public int logicDelete(String id) {
		WorkOrder workOrder = new WorkOrder();
		workOrder.setId(id);
		workOrder.setModifyTime(new Date());
		workOrder.setDelFlag(deleteFlag_del);
		return workOrderMapper.updateByPrimaryKeySelective(workOrder);
	}
	@Override
	@Transactional
	public int physicsDelete(String orderId) {
		if(StringUtils.isEmpty(orderId)) return 0;
		//TODO 查询角色关系表，删除菜单节点的同时删除关系表中的相关数据
		return workOrderMapper.deleteByPrimaryKey(orderId);
	}
	@Override
	public List<WorkOrder> findWorkOrdersByOrgs(List<Org> orgs) {
		return workOrderMapper.findWorkOrdersByOrgs(orgs);
	}
	@Override
	public WorkOrder findOrderById(String orderId) {
		return workOrderMapper.selectByPrimaryKey(orderId);
	}
	
}
