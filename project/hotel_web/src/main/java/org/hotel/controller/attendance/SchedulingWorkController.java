package org.hotel.controller.attendance;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.hotel.common.BaseController;
import org.hotel.common.CommEnum.RESULTFLAG;
import org.hotel.common.CommEnum.STAFFSTATUS;
import org.hotel.model.Org;
import org.hotel.model.Schedule;
import org.hotel.model.Staff;
import org.hotel.model.User;
import org.hotel.model.WorkOrder;
import org.hotel.service.IOrgService;
import org.hotel.service.IScheduleService;
import org.hotel.service.IStaffService;
import org.hotel.service.IWorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Controller
@RequestMapping("schedulingWork")
public class SchedulingWorkController extends BaseController<Schedule>{

	
	private final String urlStr = "schedulingWork";
	
	@Autowired
	private IOrgService orgService;
	@Autowired
	private IStaffService staffService;
	@Autowired
	private CacheManager cacheManager;
	@Autowired
	private IWorkOrderService workOrderService;
	@Autowired
	private IScheduleService scheduleService;
	
	private List<Org> orgs;
	
	private User user;
	
	
	@RequestMapping("index")
	public String index(HttpServletRequest request){
		Cache cache = cacheManager.getCache("userCache");
		Element element =  cache.get("LoginUserKey");
		if(null  == element){
			Subject currentUser = SecurityUtils.getSubject();       
			currentUser.logout();
			return null;
		} 
		User user = (User) element.getValue();
		List<Org> orgs = orgService.findOrgListById(user.getOrgId());
		this.orgs = orgs;
		this.user = user;
		
		List<Staff> staffs = staffService.findStaffByOrgs(orgs,STAFFSTATUS.WORKING.getValue());
		List<WorkOrder> workOrders = workOrderService.findWorkOrdersByOrgs(orgs);
		request.setAttribute("orgs", orgs);
		request.setAttribute("workOrders", workOrders);
		request.setAttribute("staffs", staffs);
		request.setAttribute("menuKey", urlStr+"index");
		return "arrange/attendanceArrange";
	}
	
	@RequestMapping(value="/pageData",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> pageDatas(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		List<Schedule> schedules = scheduleService.findScheduleByOrgs(orgs);
		map.put("list", schedules);
		map.put("type", schedules.size()>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	
	@RequestMapping(value="addSchedule",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addOrg(HttpServletRequest request,Schedule schedule){
		Map<String,Object> map = Maps.newHashMap();
		dealScheduleMsg(schedule);
		schedule.setCreateUser(user.getId());
		int isSuccess = scheduleService.insert(schedule);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	private Schedule dealScheduleMsg(Schedule schedule){
		String orgId = schedule.getOrgId();
		if(!StringUtils.isEmpty(orgId)){
			String[] idAndName = orgId.split("-");
			schedule.setOrgId(idAndName[0]);
			schedule.setOrgName(idAndName[1]);
		}
		String userId = schedule.getUserId();
		if(!StringUtils.isEmpty(userId)){
			String[] idAndName = userId.split("-");
			schedule.setUserId(idAndName[0]);
			schedule.setUserName(idAndName[1]);
		}
		return schedule;
	}
	
	@RequestMapping(value="modifySchedule",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> modifyOrg(HttpServletRequest request,Schedule schedule){
		Map<String,Object> map = Maps.newHashMap();
		dealScheduleMsg(schedule);
		schedule.setModifyUser(user.getId());
		int isSuccess = scheduleService.modify(schedule);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	
	@RequestMapping(value="deleteSchedule",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> deleteOrg(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		String id = request.getParameter("id");
		int isSuccess = scheduleService.logicDelete(id);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	
	
	
}
