<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>
	<div class="row">
      	<div class="form-group">
			<label class="col-sm-4 control-label">员工编号</label>
			<div class="col-sm-8">
				<input type="text" class="form-control input-xlarge " name="staffNo"  readonly="readonly" >
				<input type="hidden" class="form-control input-xlarge " name="id"  >
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">员工姓名</label>
			<div class="col-sm-8">
				<input type="text" class="form-control input-xlarge "  name="staffName" readonly="readonly"  >
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">年假天数</label>
			<div class="col-sm-8">
				<input type="text" class="form-control input-xlarge " name="yearRestDay"  >
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">存休天数</label>
			<div class="col-sm-8">
				<input type="text" class="form-control input-xlarge " name="keepRestDay"  >
			</div>
		</div>
</div>	

