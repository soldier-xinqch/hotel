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
      <!-- 	<div class="form-group">
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
		</div> -->
</div>	

