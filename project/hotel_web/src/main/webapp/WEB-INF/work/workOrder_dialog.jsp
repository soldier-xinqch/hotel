<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>
	<div class="row">
		<div class='col-xs-6'>
			<div class="form-group">
				<label class="col-sm-4 control-label">班次编号</label>
				<div class="col-sm-8">
					<input type="text" class="form-control input-xlarge" name="orderNo"  >
					<input type="hidden" class="form-control input-xlarge" name="id"  >
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">上班起始</label>
				<div class="col-sm-8 clockpicker">
					<input type="text" class="form-control input-xlarge" name="onStart"  readonly="readonly">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">上班时间</label>
				<div class="col-sm-8 clockpicker">
					<input type="text" class="form-control input-xlarge" name="beginWork"  readonly="readonly">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">迟到终止</label>
				<div class="col-sm-8 clockpicker">
					<input type="text" class="form-control input-xlarge" name="onEnd"  readonly="readonly">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">下班时间</label>
				<div class="col-sm-8 clockpicker">
					<input type="text" class="form-control input-xlarge" name="endWork" readonly="readonly" >
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">早退起始</label>
				<div class="col-sm-8 clockpicker">
					<input type="text" class="form-control input-xlarge" name="offStart"  readonly="readonly">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">下班终止</label>
				<div class="col-sm-8 clockpicker">
					<input type="text" class="form-control input-xlarge" name="offEnd" readonly="readonly" >
				</div>
			</div> 
			<div id="offOtherTime" class="form-group ">
				<label class="col-sm-4 control-label">下1时间</label>
				<div class="col-sm-8 clockpicker">
					<input type="text" class="form-control input-xlarge" name="offOtherTime"  readonly="readonly">
				</div>
			</div> 
			<div id="onOtherTime" class="form-group ">
				<label class="col-sm-4 control-label">上2时间</label>
				<div class="col-sm-8 clockpicker">
					<input type="text" class="form-control input-xlarge" name="onOtherTime" readonly="readonly" >
				</div>
			</div> 
		</div>
		<div class='col-xs-6'>
			<div class="form-group">
	       <label class="col-sm-4 control-label">所属部门</label>
	       <div class="col-sm-8">
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
	     <label class="col-sm-4 control-label">班次类别</label>
	       <div class="col-sm-8">
	       	<select class="form-control input-xlarge"  name="workType" data-placeholder="请选择班次类别">
	       		<option value=""></option>
	        		<c:if test="${null != workOrders}">
						<c:forEach items="${workOrders}" var="workOrder">
					    	 <option value="${workOrder.id}-${workOrder.orderName}">${workOrder.orderName}</option>
						</c:forEach>
					</c:if>
	        	</select>
	      		</div>
	      	</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">工时</label>
				<div class="col-sm-8">
					<input type="number" class="form-control input-xlarge" name="workTime"  >
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">当天餐次</label>
				<div class="col-sm-8">
					<input type="checkbox" name="breakfast" value="1" />早餐
					<input type="checkbox" name="lunch" value="1" />午餐
					<input type="checkbox" name="dinner" value="1" />晚餐
					<input type="checkbox" name="nightEating" value="1" />夜餐
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">次日允许餐次</label>
				<div class="col-sm-8">
					<select class="form-control input-xlarge" name="tomorrowEatNum" data-placeholder="请选择次日餐次">
		        		<option value=""></option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
		        	</select>
				</div>
			</div>
		</div>
</div>	

