<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>

<div class="row">
    <div class="form-group">
       	<label class="col-sm-3 control-label">上级组织</label>
       	<div class="col-sm-9">
        	<select class="form-control input-xlarge" name="orgId" data-placeholder="请选择上级组织">
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
     		<label class="col-sm-3 control-label">选择员工</label>
      		<div class="col-sm-9">
      		<select class="form-control input-xlarge"  name="userId" multiple="multiple" data-placeholder="请选择员工信息">
       		<c:if test="${null != staffs}">
				<c:forEach items="${staffs}" var="staff">
			    	 <option value="${staff.id}-${staff.staffName}">${staff.staffName}</option>
				</c:forEach>
			</c:if>
       	</select>
      		</div>
      </div>
      <div class="form-group">
      		<label class="col-sm-3 control-label">选择排班</label>
       		<div class="col-sm-9">
       		<select class="form-control input-xlarge"  name="scheduleMsg" data-placeholder="请选择排班信息">
        		<option value=""></option>
        		<c:if test="${null != workOrders}">
					<c:forEach items="${workOrders}" var="workOrder">
				    	 <option value="${workOrder.id}">${workOrder.orderNo} 上班时间：${workOrder.beginWork}-下班时间： ${workOrder.endWork}</option>
					</c:forEach>
				</c:if>
        	</select>
       		</div>
       </div> 
       <div class="form-group">
 		<label class="col-sm-3 control-label">开始时间</label>
       	<div class="col-sm-9 ">
       		<input type="text" class="form-control input-xlarge datepicker" name="startTime"  value="" readonly="readonly">
       		<input type="hidden" class="form-control input-xlarge datepicker" name="id"  value="" readonly="readonly">
       	</div>
       </div>
       <div class="form-group">
 		<label class="col-sm-3 control-label">结束时间</label>
       	<div class="col-sm-9 ">
       		<input type="text" class="form-control input-xlarge datepicker" name="endTime"  value="" readonly="readonly">
       	</div>
       </div>
</div>