package org.hotel.controller.attendance;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

/**
 * 考勤综合查询记录
 * @author xinqch
 *
 */
@Controller
@RequestMapping("attendanceSearch")
public class AttendanceSearchController {

private final String urlStr = "attendanceSearch";
	
//	@Autowired
//	private IAttendanceRecordService attendanceRecordService;
//	@Autowired
//	private IOrgService orgService;
//	@Autowired
//	private IStaffService staffService;
//	@Autowired
//	private IAttendanceTypeService attendanceTypeService;
	
	
	@RequestMapping("index")
	public String index(HttpServletRequest request){
//		List<Org> orgs = orgService.findAll();
//		List<Staff> staffs = staffService.findAll();
//		List<AttendanceType> types = attendanceTypeService.findAll();
//		
//		request.setAttribute("orgs", orgs);
//		request.setAttribute("staffs", staffs);
//		request.setAttribute("types", types);
		request.setAttribute("menuKey", urlStr+"index");
		return "work/attendanceSearch";
	}
	
	@RequestMapping(value="/pageData",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> pageDatas(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
//		List<AttendanceRecord> orgs = attendanceRecordService.findAll();
//		PageEntity<AttendanceRecord> baseInfos = BaseController.pageMethod(orgs, 0, 10, null);
		map.put("list", null);
//		map.put("pageSize", baseInfos.getPageSize());
//		map.put("pageNo", baseInfos.getPageNo());
//		map.put("total", baseInfos.getTotalRecord());
		return map;
	}
//	
//	@RequestMapping(value="addAttendance",method=RequestMethod.POST)
//	public @ResponseBody Map<String,Object> addOrg(HttpServletRequest request,AttendanceRecord attendance){
//		Map<String,Object> map = Maps.newHashMap();
//		dealMsg(attendance);
//		int isSuccess = attendanceRecordService.insert(attendance);
//		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
//		return map;
//	}
//	@RequestMapping(value="modifyAttendance",method=RequestMethod.POST)
//	public  @ResponseBody Map<String,Object> modifyOrg(HttpServletRequest request,AttendanceRecord attendance){
//		Map<String,Object> map = Maps.newHashMap();
//		dealMsg(attendance);
//		int isSuccess = attendanceRecordService.modify(attendance);
//		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
//		return map;
//	}
//	@RequestMapping(value="deleteAttendance",method=RequestMethod.POST)
//	public  @ResponseBody Map<String,Object> deleteOrg(HttpServletRequest request){
//		Map<String,Object> map = Maps.newHashMap();
//		String id = request.getParameter("id");
//		int isSuccess = attendanceRecordService.logicDelete(id);
//		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
//		return map;
//	}
//	private AttendanceRecord dealMsg(AttendanceRecord attendance){
//		String orgId = attendance.getOrgId();
//		if(!StringUtils.isEmpty(orgId)){
//			String[] idAndName = orgId.split("-");
//			attendance.setOrgId(idAndName[0]);
//			attendance.setOrgName(idAndName[1]);
//		}
//		return attendance;
//	}
}
