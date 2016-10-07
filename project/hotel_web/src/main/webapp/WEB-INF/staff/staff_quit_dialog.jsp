<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>
	<div class="row">
		<div class="form-group">
			<label class="col-sm-4 control-label">员工编号</label>
			<div class="col-sm-8">
				<input type="text" class="form-control input-xlarge" name="staffNo"  readonly="readonly" >
				<input type="hidden" class="form-control input-xlarge" name="id"  >
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">员工姓名</label>
			<div class="col-sm-8">
				<input type="text" class="form-control input-xlarge" name="staffName"  readonly="readonly" >
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">员工状态</label>
			<div class="col-sm-8">
				<select class="form-control input-xlarge" name="staffStatus" data-placeholder="请选择员工状态">
	        		<option value=""></option>
	        		<option value="QUITED">离职</option>
	        		<option value="0">辞标</option>
	        	</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">办理时间</label>
			<div class="col-sm-8">
				<input type="text" class="form-control input-xlarge datepicker" name="quitTime"  >
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">离职原因</label>
			<div class="col-sm-8">
				<textarea id="form-field-9" name="quitDesc" class="form-control limited input-xlarge" maxlength="50"></textarea>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">离职备注</label>
			<div class="col-sm-8">
				<textarea id="form-field-9" name="quitMemo" class="form-control limited input-xlarge" maxlength="50"></textarea>
			</div>
		</div>
</div>	

