package org.hotel.controller.staff;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.hotel.common.BaseController;
import org.hotel.common.CommEnum.RESULTFLAG;
import org.hotel.common.CommEnum.STAFFSTATUS;
import org.hotel.model.Org;
import org.hotel.model.Staff;
import org.hotel.model.User;
import org.hotel.service.IOrgService;
import org.hotel.service.IStaffService;
import org.hotel.utils.ExcelEntity;
import org.hotel.utils.ExportExcelUtils;
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

@Controller
@RequestMapping("staff")
public class StaffController extends BaseController<Staff>{

	private final String urlStr ="staff";
	@Autowired
	private IOrgService orgService;
	@Autowired
	private IStaffService staffService;
	@Autowired
	private CacheManager cacheManager;
	
	private List<Org> orgs;
	
	private User user;
	
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String index(HttpServletRequest request){
//		List<Org> orgs = orgService.findAll();
		Cache cache = cacheManager.getCache("userCache");
		Element element =  cache.get("LoginUserKey");
		User user = (User) element.getValue();
		List<Org> orgs = orgService.findOrgListById(user.getOrgId());
		this.orgs =orgs; 
		this.user =user; 
		request.setAttribute("orgs", orgs);
		request.setAttribute("menuKey", urlStr+"index");
		return "staff/staff";
	}
	
	@RequestMapping(value="exportExcel1")
	public @ResponseBody void exportExcels(HttpServletRequest request,HttpServletResponse response){
		try{
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"); 
	        //response.setContentType("octets/stream");
	        response.setHeader("Content-Disposition", "attachment;filename="+ new String(("staff" + ".xlsx").getBytes(), "utf-8"));
	        
	        
	        List<Org> winds = orgService.findAll();
	        String[] columnNames = { "id"};
	        String[] methodNames = { "getId"};
//	      String fileName = "d:/temp/excel1.xlsx";
	        String fileName = "d:/excel1.xlsx";
	        // 生成ExcelEntity实体，包含4个必备参数
	        ExcelEntity<Org> excelEntity = new ExcelEntity<Org>(fileName, columnNames, methodNames, winds);
	        //excelEntity.setHeader("题头");
	        //excelEntity.setFooter("脚注");
	        Workbook excel = ExportExcelUtils.export2Excel(excelEntity);
	        //ExcelExporter.export2Excel("题头","脚注", "sheet1", columnNames, methodNames, winds);//也可以这样调用,无需新建ExcelEntity对象
	        //将Workbook存为文件
//	        ExportExcelUtils.saveWorkBook2007(excel, excelEntity.getFileName());
	         
//	        System.out.println("导出完成！");
	        
	        
	        ServletOutputStream outputStream = response.getOutputStream();
	        ExportExcelUtils.saveWorkBook2007(excel, excelEntity.getFileName(), outputStream);
//	        ExportExcelUtils.exportExcelX("123123213",headMap,ja,null,0,outputStream);
	        // 清除缓存
	        outputStream.flush();
	        outputStream.close();
	    }catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@RequestMapping(value="/pageData",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> pageDatas(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		String pq_curpage = request.getParameter("pq_curpage");
		String pq_rpp = request.getParameter("pq_rpp");
		PageHelper.startPage(Integer.valueOf(pq_curpage), Integer.valueOf(pq_rpp));
		List<Staff> staffs = staffService.findStaffByOrgs(orgs,STAFFSTATUS.WORKING.getValue());
		PageInfo<Staff> baseInfos = new PageInfo<Staff>(staffs);
		map.put("list", baseInfos.getList());
		map.put("pageSize", baseInfos.getPageSize());
		map.put("pageNo", baseInfos.getPageNum());
		map.put("total", baseInfos.getTotal());
		return map;
	}
	
	@RequestMapping(value="addStaff",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addOrg(HttpServletRequest request,Staff staff){
		Map<String,Object> map = Maps.newHashMap();
		dealStaffMsg(staff);
		staff.setCreateUser(user.getId());
		staff.setStaffStatus(STAFFSTATUS.WORKING.getValue());
		int isSuccess = staffService.insert(staff);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	private Staff dealStaffMsg(Staff staff){
		String orgId = staff.getOrgId();
		if(!StringUtils.isEmpty(orgId)){
			String[] idAndName = orgId.split("-");
			staff.setOrgId(idAndName[0]);
			staff.setOrgName(idAndName[1]);
		}
		return staff;
	}
	
	@RequestMapping(value="modifyStaff",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> modifyOrg(HttpServletRequest request,Staff staff){
		Map<String,Object> map = Maps.newHashMap();
		dealStaffMsg(staff);
		staff.setModifyUser(user.getId());
		if(!StringUtils.isEmpty(staff.getStaffStatus())&&STAFFSTATUS.QUITED.getValue().equals(staff.getStaffStatus())){
			staff.setQuitCheckId(user.getId());
			staff.setQuitCheckName(user.getRealName());
		}
		int isSuccess = staffService.modify(staff);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	
//	@RequestMapping(value="deleteOrg",method=RequestMethod.POST)
//	public  @ResponseBody Map<String,Object> deleteOrg(HttpServletRequest request){
//		Map<String,Object> map = Maps.newHashMap();
//		String id = request.getParameter("id");
//		int isSuccess = orgService.logicDelete(id);
//		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
//		return map;
//	}
}
