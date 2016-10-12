<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>
	<div class="row">
		<div class="form-group">
	       	<label class="col-sm-4 control-label">所属组织</label>
	       	<div class="col-sm-8">
	        	<select class="form-control input-xlarge" name="orgId" data-placeholder="请选择上级组织">
	        		<option ></option>
	        		<c:if test="${null != orgs}">
						<c:forEach items="${orgs}" var="org">
					    	 <option value="${org.id}-${org.orgName}">${org.orgName}</option>
						</c:forEach>
					</c:if>
	        	</select>
      		</div>
      	</div>
      	<div class="form-group">
	       	<label class="col-sm-4 control-label">员工</label>
	       	<div class="col-sm-8">
	        	<select class="form-control input-xlarge" name="staffId" multiple="multiple" data-placeholder="请选择员工" >
	        		<c:if test="${null != staffs}">
						<c:forEach items="${staffs}" var="staff">
					    	 <option value="${staff.id}">${staff.staffName}</option>
						</c:forEach>
					</c:if>
	        	</select>
      		</div>
      	</div>
      	<div class="form-group">
			<label class="col-sm-4 control-label">考勤时间</label>
			<div class="col-sm-8">
				<input type="text" class="form-control input-xlarge datepicker" data-date-format="yyyy/mm/dd" name="startTime"  >
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label"></label>
			<div class="col-sm-8">
				<input type="text" class="form-control input-xlarge datepicker" data-date-format="yyyy/mm/dd" name="endTime"  >
			</div>
		</div>
		<div class="form-group">
       	<label class="col-sm-4 control-label">选择列头</label>
       	<div class="col-sm-8">
        	<select class="form-control input-xlarge" name="field" multiple="multiple" data-placeholder="请选择列头信息">
	    	 	<option value="员工编号-StaffNo">员工编号</option>
	    	 	<option value="员工姓名-StaffName">员工姓名</option>
	    	 	<option value="所属部门-OrgName">所属部门</option>
	    	 	<option value="考勤日期-AttendanceTime">考勤日志</option>
	    	 	<option value="异常类型-ExceptionType">异常类型</option>
	    	 	<option value="班次-WorkOrderName">班次</option>
	    	 	<option value="班次类型-WorkOrderTypeName">班次类型</option>
	    	 	<option value="刷卡1-Brush1">刷卡1</option>
	    	 	<option value="刷卡2-Brush2">刷卡2</option>
	    	 	<option value="刷卡3-Brush3">刷卡3</option>
	    	 	<option value="刷卡4-Brush4">刷卡4</option>
	    	 	<option value="刷卡5-Brush5">刷卡5</option>
	    	 	<option value="刷卡6-Brush6">刷卡6</option>
	    	 	<option value="刷卡7-Brush7">刷卡7</option>
	    	 	<option value="刷卡8-Brush8">刷卡8</option>
        	</select>
      		</div>
      	</div>
</div>	

