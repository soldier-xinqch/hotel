package org.hotel.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.shiro.util.CollectionUtils;
import org.hotel.common.CommEnum.MENULEVEL;
import org.hotel.common.CommEnum.MENUSTATUS;
import org.hotel.mapper.MenuMapper;
import org.hotel.model.Menu;
import org.hotel.model.Org;
import org.hotel.service.IMenuService;
import org.hotel.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Service("menuService")
@Transactional
public class MenuServiceImpl implements IMenuService{
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public List<Menu> findAll(){
		return menuMapper.findAll();
	}
	@Override
	public List<Menu> findMenuByIds(List<String> menuIds) {
		if(CollectionUtils.isEmpty(menuIds)) return null;
		List<Menu> menus = menuMapper.findMenuByIds(menuIds);
		return findMenus(menus);
	}
	private List<Menu> findMenus(List<Menu> menus){
		List<Menu> firstMenu = findMenusByIdAndLevel(menus,MENULEVEL.FIRST.getValue());
		List<Menu> secondMenu = findMenusByIdAndLevel(menus,MENULEVEL.SECOND.getValue());
		List<Menu> thirdMenu = findMenusByIdAndLevel(menus,MENULEVEL.THIRD.getValue());
		if(CollectionUtils.isEmpty(firstMenu)) return null;
		for (Menu menu : firstMenu) {
			List<Menu> secondList = Lists.newArrayList();
			if(CollectionUtils.isEmpty(secondMenu)) continue;
			for (Menu second : secondMenu) {
				if(menu.getId().equals(second.getMenuParentId())){
					secondList.add(second);
				}
				if(CollectionUtils.isEmpty(thirdMenu)) continue;
				List<Menu> thirdList = Lists.newArrayList();
				for (Menu third : thirdMenu) {
					if(second.getId().equals(third.getMenuParentId())){
						thirdList.add(third);
					}
				}
				second.setMenu(thirdList);
			}
			menu.setMenu(secondList);
		}
		return firstMenu;
	}
	private List<Menu> findMenusByIdAndLevel(List<Menu> menus, String level) {
		return menuMapper.findMenusByIdAndLevel(menus,level);
	}
	@Override
	public List<Menu> findMenuByIdsAndlevel(String parentId,String level){
		return menuMapper.findMenuByIdsAndlevel(parentId,level);
	}
	@Override
	public List<Menu> findMenuByIdsAndlevelAndStatus(String parentId,String level,String status){
		return menuMapper.findMenuByIdsAndlevelAndStatus(parentId,level,status);
	}
	@Override
	public List<Menu> findMenus(boolean isStatus){
		List<Menu> menus = findMenusByParentId(null, isStatus);
		return  menus;
	}
	@Override
	public List<Menu> findMenusByParentId(String parentId,boolean isStatus){
		List<Menu> firstMenus = null;
		if(isStatus){
			firstMenus = findMenuByIdsAndlevel(parentId, MENULEVEL.FIRST.getValue());
			if(CollectionUtils.isEmpty(firstMenus))return null;
			for (Menu menu : firstMenus) {
				List<Menu> secodeMenus = findMenuByIdsAndlevel(menu.getId(), MENULEVEL.SECOND.getValue());
				if(CollectionUtils.isEmpty(secodeMenus))continue;
				menu.setMenu(secodeMenus);
				for (Menu secodeMenu : secodeMenus) {
					List<Menu> thirdMenus = findMenuByIdsAndlevel(secodeMenu.getId(), MENULEVEL.THIRD.getValue());
					if(CollectionUtils.isEmpty(thirdMenus))continue;
					secodeMenu.setMenu(thirdMenus);
				}
			}
		}else{
			firstMenus = findMenuByIdsAndlevelAndStatus(parentId, MENULEVEL.FIRST.getValue(),MENUSTATUS.DOING.getValue());
			if(CollectionUtils.isEmpty(firstMenus))return null;
			for (Menu menu : firstMenus) {
				List<Menu> secodeMenus = findMenuByIdsAndlevelAndStatus(menu.getId(), MENULEVEL.SECOND.getValue(),MENUSTATUS.DOING.getValue());
				if(CollectionUtils.isEmpty(secodeMenus))continue;
				menu.setMenu(secodeMenus);
				for (Menu secodeMenu : secodeMenus) {
					List<Menu> thirdMenus = findMenuByIdsAndlevelAndStatus(secodeMenu.getId(), MENULEVEL.THIRD.getValue(),MENUSTATUS.DOING.getValue());
					if(CollectionUtils.isEmpty(thirdMenus))continue;
					secodeMenu.setMenu(thirdMenus);
				}
			}
		}
		return firstMenus;
	}
	@Override
	public Set<Menu> findParents(List<String> menuIds){
		Set<Menu> results = Sets.newHashSet();
		List<Menu> menus = menuMapper.findMenuByIds(menuIds);
		if(CollectionUtils.isEmpty(menus)) return null;
		results.addAll(menus);
		return findParentMenus(menus, results );
	}
	
	private Set<Menu> findParentMenus(List<Menu> menus,Set<Menu> results){
		List<Menu> parents =menuMapper.findParentMenus(menus);
		if(CollectionUtils.isEmpty(parents)) return results;
		results.addAll(parents);
		return findParentMenus(parents, results);
	}
	
	
	@Override
	public int insert(Menu menu) {
		if(null == menu) return 0;
		menu.setId(CommonUtil.getUUID());
		menu.setCreateTime(new Date());
		menu.setModifyTime(dateInit);
		menu.setDelFlag(deleteFlag_insert);
		return menuMapper.insertSelective(menu);
	}
	@Override
	public int modify(Menu menu) {
		if(null == menu) return 0;
		menu.setModifyTime(new Date());
		return menuMapper.updateByPrimaryKeySelective(menu);
	}
	@Override
	public int logicDelete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	@Transactional
	public int physicsDelete(String menuId) {
		if(StringUtils.isEmpty(menuId)) return 0;
		//TODO 查询角色关系表，删除菜单节点的同时删除关系表中的相关数据
		return menuMapper.deleteByPrimaryKey(menuId);
	}
	@Override
	public Menu findMenusByFlag(String activeFlag) {
		 Menu menu = menuMapper.findMenusByFlag(activeFlag);
		 if(null != menu&&menu.getMenuLevel().equals(MENULEVEL.FIRST.getValue())) return menu;
		 if(null != menu&&!StringUtils.isEmpty(menu.getMenuParentId())) return findParentMenu(menu.getMenuParentId());
		 return menu;
	}
	/**
	 *  递归查询父菜单ID
	 * @param parentId
	 * @return
	 */
	private Menu findParentMenu(String parentId){
		 Menu menu = findMenusById(parentId);
		 if(menu.getMenuLevel().equals(MENULEVEL.FIRST.getValue())){
			return  menu;
		 }
		 return findParentMenu(menu.getMenuParentId());
	}
	
	@Override
	public Menu findMenusById(String id) {
		return menuMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<Menu> findMenuByOrgs(List<Org> orgs) {
		return menuMapper.findMenuByOrgs(orgs);
	}
	
}
