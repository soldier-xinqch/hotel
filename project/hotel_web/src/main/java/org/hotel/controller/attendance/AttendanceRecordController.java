package org.hotel.controller.attendance;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hotel.common.CommEnum.RESULTFLAG;
import org.hotel.model.AttendanceRecord;
import org.hotel.model.AttendanceType;
import org.hotel.model.Org;
import org.hotel.model.Staff;
import org.hotel.model.User;
import org.hotel.service.IAttendanceRecordService;
import org.hotel.service.IAttendanceTypeService;
import org.hotel.service.IOrgService;
import org.hotel.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 考勤单记录
 * @author xinqch
 *
 */
@Controller
@RequestMapping("attendanceRecord")
public class AttendanceRecordController {

private final String urlStr = "attendanceRecord";
	
	@Autowired
	private IAttendanceRecordService attendanceRecordService;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private IStaffService staffService;
	@Autowired
	private IAttendanceTypeService attendanceTypeService;
	@Autowired
	private CacheManager cacheManager;
	
	private List<Org> orgs;
	
	private User user;
	
	@RequestMapping("index")
	public String index(HttpServletRequest request){
		List<Staff> staffs = staffService.findAll();
		List<AttendanceType> types = attendanceTypeService.findAll();
		Cache cache = cacheManager.getCache("userCache");
		Element element =  cache.get("LoginUserKey");
		User user = (User) element.getValue();
		List<Org> orgs = orgService.findOrgListById(user.getOrgId());
		this.orgs =orgs; 
		this.user =user; 
		request.setAttribute("orgs", orgs);
		request.setAttribute("staffs", staffs);
		request.setAttribute("types", types);
		request.setAttribute("menuKey", urlStr+"index");
		return "work/attendanceRecord";
	}
	
	@RequestMapping(value="/pageData",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> pageDatas(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		String pq_curpage = request.getParameter("pq_curpage");
		String pq_rpp = request.getParameter("pq_rpp");
		PageHelper.startPage(Integer.valueOf(pq_curpage), Integer.valueOf(pq_rpp));
		List<AttendanceRecord> attendanceRecords = attendanceRecordService.findAttendanceRecordByOrgs(orgs);
		PageInfo<AttendanceRecord> baseInfos = new PageInfo<AttendanceRecord>(attendanceRecords);
		map.put("list", baseInfos.getList());
		map.put("pageSize", baseInfos.getPageSize());
		map.put("pageNo", baseInfos.getPageNum());
		map.put("total", baseInfos.getTotal());
		return map;
	}
	
	@RequestMapping(value="addAttendance",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addOrg(HttpServletRequest request,AttendanceRecord attendance){
		Map<String,Object> map = Maps.newHashMap();
		dealMsg(attendance);
		attendance.setCreateUser(user.getId());
		int isSuccess = attendanceRecordService.insert(attendance);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	@RequestMapping(value="modifyAttendance",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> modifyOrg(HttpServletRequest request,AttendanceRecord attendance){
		Map<String,Object> map = Maps.newHashMap();
		dealMsg(attendance);
		attendance.setModifyId(user.getId());
		int isSuccess = attendanceRecordService.modify(attendance);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	@RequestMapping(value="deleteAttendance",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> deleteOrg(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		String id = request.getParameter("id");
		int isSuccess = attendanceRecordService.logicDelete(id);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	private AttendanceRecord dealMsg(AttendanceRecord attendance){
		String orgId = attendance.getOrgId();
		if(!StringUtils.isEmpty(orgId)){
			String[] idAndName = orgId.split("-");
			attendance.setOrgId(idAndName[0]);
			attendance.setOrgName(idAndName[1]);
		}
		String staffId = attendance.getStaffId();
		if(!StringUtils.isEmpty(orgId)){
			String[] idAndName = staffId.split("-");
			attendance.setStaffId(idAndName[0]);
			attendance.setStaffName(idAndName[1]);
		}
		String typeId = attendance.getAttendanceTypeId();
		if(!StringUtils.isEmpty(orgId)){
			String[] idAndName = typeId.split("-");
			attendance.setAttendanceTypeId(idAndName[0]);
			attendance.setAttendanceTypeName(idAndName[1]);
		}
		return attendance;
	}
}
