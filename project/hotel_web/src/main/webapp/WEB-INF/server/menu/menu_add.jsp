<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>

<div class="row">
	<div class="form-group">
 		<label class="col-sm-3 control-label">菜单序号</label>
       	<div class="col-sm-9">
       		<input type="text" class="form-control input-xlarge" name="menuIndex"  value="">
       	</div>
       </div>
 	<div class="form-group">
 		<label class="col-sm-3 control-label">菜单名称</label>
       	<div class="col-sm-9">
       		<input type="text" class="form-control input-xlarge" name="menuName"  value="">
       		<input type="hidden" class="form-control input-xlarge" name="id"  value="">
       	</div>
       </div>
       <div class="form-group">
       	<label class="col-sm-3 control-label">菜单图标</label>
       	<div class="col-sm-9">
       		<input type="text" class="form-control input-xlarge" name="menuIcon" value="" >
       	</div>
       </div>
       <div class="form-group">
       	<label class="col-sm-3 control-label">菜单级别</label>
       	<div class="col-sm-9">
        	<select class="form-control input-xlarge" name="menuLevel" data-placeholder="请选择菜单级别">
        		<option value=""></option>
        		<option value="0">不变菜单</option>
        		<option value="1">主菜单</option>
        		<option value="2">子菜单</option>
        		<option value="3">三级菜单</option>
        	</select>
      		</div>
      	</div>
      	<div class="form-group">
       	<label class="col-sm-3 control-label">上级菜单</label>
       	<div class="col-sm-9">
        	<select class="form-control input-xlarge"  name="menuParentId" data-placeholder="请选择上级菜单">
        		<option value=""></option>
        		<c:if test="${null != menuList}">
					<c:forEach items="${menuList}" var="menu">
				    	 <option value="${menu.id}-${menu.menuName}">${menu.menuName}</option>
					</c:forEach>
				</c:if>
        	</select>
      		</div>
      	</div>
      	<div class="form-group">
      		<label class="col-sm-3 control-label">菜单状态</label>
       	<div class="col-sm-9">
       		<select class="form-control input-xlarge" name="menuStatus" data-placeholder="请选择菜单状态">
       			<option value=""></option>
       			<option value="0">新功能</option>
       			<option value="1">即将上线</option>
       			<option value="2">立即生效</option>
       		</select>
       	</div>
       </div>
       <div class="form-group">
       	<label class="col-sm-3 control-label">菜单链接</label>
       	<div class="col-sm-9">
       		<input type="text" class="form-control input-xlarge" name="menuUrl"  value="">
       	</div>
       </div>
       <div class="form-group">
       	<label class="col-sm-3 control-label">菜单样式</label>
       	<div class="col-sm-9">
       		<input type="text" class="form-control input-xlarge" name="menuStyle"  value="">
       	</div>
       </div>
       <div class="form-group">
       	<label class="col-sm-3 control-label">菜单描述</label>
       	<div class="col-sm-9">
       		<textarea id="form-field-9" name="menuDesc" class="form-control limited input-xlarge" maxlength="50"></textarea>
       	</div>
       </div>
</div>