package org.hotel.service;

import java.util.List;
import java.util.Set;

import org.hotel.common.BaseService;
import org.hotel.model.Menu;
import org.hotel.model.Org;

/**
 *  菜单数据接口
 * @author xinqch
 *
 */
public interface IMenuService extends BaseService<Menu> {

	/**
	 *  查询所有菜单
	 * @return
	 */
	List<Menu> findAll();
	/**
	 *  根据菜单Id获取菜单
	 * @param menuIds
	 * @return
	 */
	List<Menu> findMenuByIds(List<String> menuIds);
	/**
	 *  根据父Id和菜单级别获取菜单
	 * @param parentId
	 * @param level
	 * @return
	 */
	List<Menu> findMenuByIdsAndlevel(String parentId, String level);
	/**
	 *  根据父Id和菜单级别以及菜单状态获取菜单
	 * @param parentId
	 * @param level
	 * @param status
	 * @return
	 */
	List<Menu> findMenuByIdsAndlevelAndStatus(String parentId, String level, String status);
	/**
	 *  根据状态开关获取所有级别的菜单
	 * @param isStatus
	 * @return
	 */
	List<Menu> findMenus(boolean isStatus);
	/**
	 *  根据父Id和状态开关获取所有菜单
	 * @param parentId
	 * @param isStatus
	 * @return
	 */
	List<Menu> findMenusByParentId(String parentId, boolean isStatus);
	/**
	 *  根据菜单标识查询菜单
	 * @param activeFlag
	 * @return
	 */
	Menu findMenusByFlag(String activeFlag);
	/**
	 *  根据ID查询菜单
	 * @param id
	 * @return
	 */
	Menu findMenusById(String id);
	
	Set<Menu> findParents(List<String> menuIds);
	
	
	List<Menu> findMenuByOrgs(List<Org> orgs);
	
}
