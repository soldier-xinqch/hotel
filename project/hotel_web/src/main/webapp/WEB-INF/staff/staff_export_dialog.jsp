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
					    	 <option value="${org.id}">${org.orgName}</option>
						</c:forEach>
					</c:if>
	        	</select>
      		</div>
      	</div>
      	<div class="form-group">
	       	<label class="col-sm-4 control-label">员工</label>
	       	<div class="col-sm-8">
	       		<select class="form-control input-xlarge" name="staffId" multiple="multiple" data-placeholder="请选择员工">
	        		<c:if test="${null != staffs}">
						<c:forEach items="${staffs}" var="staff">
					    	 <option value="${staff.id}">${staff.staffName}</option>
						</c:forEach>
					</c:if>
	        	</select>
      		</div>
      	</div>
      	<div class="form-group">
			<label class="col-sm-4 control-label">入职时间</label>
			<div class="col-sm-8">
				<input type="text" class="form-control input-xlarge datepicker" data-date-format="yyyy/mm/dd" name="staffNo"  >
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label"></label>
			<div class="col-sm-8">
				<input type="text" class="form-control input-xlarge datepicker" data-date-format="yyyy/mm/dd" name="staffCardNo"  >
			</div>
		</div>
		<div class="form-group">
       	<label class="col-sm-4 control-label">选择列头</label>
       	<div class="col-sm-8">
        	<select class="form-control input-xlarge" name="staffField" multiple="multiple" data-placeholder="请选择列头信息">
        		<option value="员工编号-StaffNo">员工编号</option>
	    	 	<option value="员工姓名-StaffName">员工姓名</option>
	    	 	<option value="所属部门-OrgName">所属部门</option>
	    	 	<option value="性别-Sex">性别</option>
	    	 	<option value="身份证-CardId">身份证</option>
	    	 	<option value="员工卡号-StaffCardNo">员工卡号</option>
	    	 	<option value="入职时间-IntoTime">入职时间</option>
	    	 	<option value="联系电话-Telphone">联系电话</option>
	    	 	<option value="联系地址-StaffAddress">联系地址</option>
	    	 	<option value="联系邮箱-Email">联系邮箱</option>
	    	 	<option value="员工生日-Birthday">员工生日</option>
	    	 	<option value="年假-YearRestDay">年假</option>
	    	 	<option value="存休-KeepRestDay">存休</option>
        	</select>
      		</div>
      	</div>
</div>	

