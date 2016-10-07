package org.hotel.service;

import java.util.List;

import org.hotel.common.BaseService;
import org.hotel.model.Menu;
import org.hotel.model.Org;
import org.hotel.model.WorkOrderType;

/**
 *  菜单数据接口
 * @author xinqch
 *
 */
public interface IWorkOrderTypeService extends BaseService<WorkOrderType> {

	/**
	 *  查询所有菜单
	 * @return
	 */
	List<WorkOrderType> findAll();

	List<WorkOrderType> findworkOrderTypeByOrgId(String orgId);

	List<WorkOrderType> findWorkOrdersByOrgs(List<Org> orgs);
	
}
