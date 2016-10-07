package org.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.hotel.model.Menu;
import org.hotel.model.Org;

public interface MenuMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Menu record);

	List<Menu> findAll();

	List<Menu> findMenuByIds(List<String> list);

	List<Menu> findMenuByIdsAndlevel(@Param("parentId")String parentId,@Param("level") String level);

	List<Menu> findMenuByIdsAndlevelAndStatus(@Param("parentId")String parentId,@Param("level") String level,@Param("status") String status);

	Menu findMenusByFlag(String activeFlag);

	List<Menu> findParentMenus(List<Menu> list);

	List<Menu> findMenusByIdAndLevel(@Param("list") List<Menu> list,@Param("level") String level);

	List<Menu> findMenuByOrgs(@Param("orgs") List<Org> orgs); 
}
