package org.hotel.service.Impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.util.CollectionUtils;
import org.hotel.mapper.OrgMapper;
import org.hotel.model.Org;
import org.hotel.service.IOrgService;
import org.hotel.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service("orgService")
public class OrgServiceImpl implements IOrgService {

	@Autowired
	private OrgMapper orgMapper;

	@Override
	public List<Org> findAll() {
		return orgMapper.findAll();
	}
	
	@Override
	public Org findOrgById(String orgId){
		return orgMapper.selectByPrimaryKey(orgId);
	}
	@Override
	public List<Org> findOrgListById(String orgId){
		List<Org> results = Lists.newArrayList();
		List<Org> parentOrgs = findOrgsByParentId(orgId);
		Org org = findOrgById(orgId);
		results.addAll(parentOrgs);
		results.add(org);
		findSonOrgs(parentOrgs, results);
		return results;
	}
	/**
	 *  递归查询子组织
	 * @param orgs
	 * @param results
	 * @return
	 */
	private List<Org> findSonOrgs(List<Org> orgs,List<Org> results){
		List<Org> sons = orgMapper.findOrgsByIds(orgs);
		if(CollectionUtils.isEmpty(sons)) return results;
		for (Org org : sons) {
			results.add(org);
		}
		return findSonOrgs(sons, results);
	}
	
//	private List<Org> findOrgTree(List<Org> orgs,List<Org> results){
//		List<Org> sons = orgMapper.findOrgsByIds(orgs);
//		if(CollectionUtils.isEmpty(sons)) return results;
//		for (Org org : sons) {
//			results.add(org);
//		}
//		return findSonOrgs(sons, results);
//	}
	
	
	@Override
	public List<Org> findOrgsByParentId(String parentId){
		List<Org> orgs = orgMapper.findOrgsByParentId(parentId);
		return orgs;
	}
	
	@Override
	public List<Org> findOrgListById(String orgId, boolean includSelf) {
		List<Org> orgs = findOrgListById(orgId);
		if(includSelf){
			Org org = findOrgById(orgId);
			orgs.add(org);
		}
		return orgs;
	}

	@Override
	public int insert(Org org) {
		org.setId(CommonUtil.getUUID());
		org.setCreateTime(new Date());
		org.setDelFlag(deleteFlag_insert);
		org.setModifyTime(dateInit);
		return orgMapper.insertSelective(org);
	}

	@Override
	public int modify(Org org) {
		org.setModifyTime(new Date());
		return orgMapper.updateByPrimaryKeySelective(org);
	}

	@Override
	public int logicDelete(String orgId) {
		Org org = new Org();
		org.setId(orgId);
		org.setModifyTime(new Date());
		org.setDelFlag(deleteFlag_del);
		return orgMapper.updateByPrimaryKeySelective(org);
	}

	@Override
	public int physicsDelete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	
	
}
