package org.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.hotel.model.Org;
import org.hotel.model.WorkOrder;

public interface WorkOrderMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(WorkOrder record);

    WorkOrder selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WorkOrder record);

	List<WorkOrder> findworkOrderByOrgId(String orgId);

	List<WorkOrder> findAll();

	List<WorkOrder> findWorkOrdersByOrgs(@Param("orgs") List<Org> orgs);
}  
  