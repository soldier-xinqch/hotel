package org.hotel.service;

import java.util.List;

import org.hotel.common.BaseService;
import org.hotel.model.Org;
import org.hotel.model.WorkOrder;

/**
 *  菜单数据接口
 * @author xinqch
 *
 */
public interface IWorkOrderService extends BaseService<WorkOrder> {

	/**
	 *  查询所有菜单
	 * @return
	 */
	List<WorkOrder> findAll();

	List<WorkOrder> findworkOrderByOrgId(String orgId);

	List<WorkOrder> findWorkOrdersByOrgs(List<Org> orgs);
	
	WorkOrder findOrderById(String orderId);

}
