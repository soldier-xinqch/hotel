<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>

<div class="row">
      	<div class="form-group">
      		<label class="col-sm-3 control-label">选择员工</label>
       		<div class="col-sm-9">
       		<select class="form-control input-xlarge"  name="staffId" data-placeholder="请选择员工信息">
        		<option value=""></option>
        		<c:if test="${null != staffs}">
					<c:forEach items="${staffs}" var="staff">
				    	 <option value="${staff.id}-${staff.staffName}">${staff.staffName}</option>
					</c:forEach>
				</c:if>
        	</select>
       		</div>
       </div>
       <div class="form-group">
       <label class="col-sm-3 control-label">所属部门</label>
       <div class="col-sm-9">
       	<select class="form-control input-xlarge"  name="orgId" data-placeholder="请选择员工所属部门">
       		<option value=""></option>
        		<c:if test="${null != orgs}">
					<c:forEach items="${orgs}" var="org">
				    	 <option value="${org.id}-${org.orgName}">${org.orgName}</option>
					</c:forEach>
				</c:if>
        	</select>
      		</div>
      	</div>
      	 <div class="form-group">
       <label class="col-sm-3 control-label">考勤类型</label>
       <div class="col-sm-9">
       	<select class="form-control input-xlarge"  name="attendanceTypeId" data-placeholder="请选择考勤类型">
       		<option value=""></option>
        		<c:if test="${null != types}">
					<c:forEach items="${types}" var="type">
				    	 <option value="${type.id}-${type.typeName}">${type.typeName}</option>
					</c:forEach>
				</c:if>
        	</select>
      		</div>
      	</div>
      	<div class="form-group">
 		<label class="col-sm-3 control-label">考勤时间</label>
       	<div class="col-sm-9 ">
       		<input type="text" class="form-control input-xlarge datepicker" name="workTime"  value="" readonly="readonly">
       	</div>
       </div>
       <div class="form-group">
 		<label class="col-sm-3 control-label">休息时间</label>
       	<div class="col-sm-9 ">
       		<input type="text" class="form-control input-xlarge datepicker" name="restTime"  value="" readonly="readonly">
       	</div>
       </div>
      	<div class="form-group">
 		<label class="col-sm-3 control-label">计时</label>
       	<div class="col-sm-9">
       		<input type="number" class="form-control input-xlarge" name="num"  value="">
       		<input type="hidden" class="form-control input-xlarge" name="id"  value="">
       	</div>
       </div>
</div>