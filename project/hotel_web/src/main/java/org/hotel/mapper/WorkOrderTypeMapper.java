package org.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.hotel.model.Org;
import org.hotel.model.WorkOrderType;

public interface WorkOrderTypeMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(WorkOrderType record);

    WorkOrderType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WorkOrderType record);

	List<WorkOrderType> findAll();

	List<WorkOrderType> findworkOrderTypeByOrgId(String orgId);

	List<WorkOrderType> findWorkOrdersByOrgs(@Param("orgs") List<Org> orgs);
}