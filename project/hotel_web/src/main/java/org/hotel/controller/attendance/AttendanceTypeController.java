package org.hotel.controller.attendance;
//package org.hotel.controller;
//
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.hotel.common.BaseController;
//import org.hotel.common.PageEntity;
//import org.hotel.common.CommEnum.RESULTFLAG;
//import org.hotel.model.Attendance;
//import org.hotel.model.Org;
//import org.hotel.service.IAttendanceService;
//import org.hotel.service.IOrgService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.google.common.collect.Maps;
//
///**
// * 考勤表
// * @author xinqch
// *
// */
//@Controller
//@RequestMapping("attendanceRecord")
//public class AttendanceTypeController {
//
//private final String urlStr = "attendanceRecord";
//	
//	@Autowired
//	private IAttendanceService attendanceService;
//	@Autowired
//	private IOrgService orgService;
//	
//	
//	@RequestMapping("index")
//	public String index(HttpServletRequest request){
//		List<Org> orgs = orgService.findAll();
//		request.setAttribute("orgs", orgs);
//		request.setAttribute("menuKey", urlStr+"index");
//		return "work/arrangeTime";
//	}
//	
//	@RequestMapping(value="/pageData",method=RequestMethod.GET)
//	public @ResponseBody Map<String, Object> pageDatas(HttpServletRequest request){
//		Map<String,Object> map = Maps.newHashMap();
//		List<Attendance> orgs = attendanceService.findAll();
//		PageEntity<Attendance> baseInfos = BaseController.pageMethod(orgs, 0, 10, null);
//		map.put("list", baseInfos.getPageDatas());
//		map.put("pageSize", baseInfos.getPageSize());
//		map.put("pageNo", baseInfos.getPageNo());
//		map.put("total", baseInfos.getTotalRecord());
//		return map;
//	}
//	
//	@RequestMapping(value="addAttendance",method=RequestMethod.POST)
//	public @ResponseBody Map<String,Object> addOrg(HttpServletRequest request,Attendance attendance){
//		Map<String,Object> map = Maps.newHashMap();
//		dealMsg(attendance);
//		int isSuccess = attendanceService.insert(attendance);
//		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
//		return map;
//	}
//	@RequestMapping(value="modifyAttendance",method=RequestMethod.POST)
//	public  @ResponseBody Map<String,Object> modifyOrg(HttpServletRequest request,Attendance attendance){
//		Map<String,Object> map = Maps.newHashMap();
//		dealMsg(attendance);
//		int isSuccess = attendanceService.modify(attendance);
//		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
//		return map;
//	}
//	@RequestMapping(value="deleteAttendance",method=RequestMethod.POST)
//	public  @ResponseBody Map<String,Object> deleteOrg(HttpServletRequest request){
//		Map<String,Object> map = Maps.newHashMap();
//		String id = request.getParameter("id");
//		int isSuccess = attendanceService.logicDelete(id);
//		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
//		return map;
//	}
//	private Attendance dealMsg(Attendance attendance){
//		String orgId = attendance.getOrgId();
//		if(!StringUtils.isEmpty(orgId)){
//			String[] idAndName = orgId.split("-");
//			attendance.setOrgId(idAndName[0]);
//			attendance.setOrgName(idAndName[1]);
//		}
//		return attendance;
//	}
//}
