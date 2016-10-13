<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>

<div class="row">
	<div class="form-group">
 		<label class="col-sm-3 control-label">角色名称</label>
       	<div class="col-sm-9">
       		<input type="text" class="form-control input-xlarge" name="roleName"  value="" disabled="disabled">
       		<input type="hidden" class="form-control input-xlarge" name="roleId"  value="" disabled="disabled">
       		<input type="hidden" class="form-control input-xlarge" name="authId"  value="" disabled="disabled">
       	</div>
       </div>
       <div>
       		<c:if test="${null != menuList}">
       			<c:forEach items="${menuList}" var="appMenu">
   					<div class="list-group-item" style="height: 50px;">
       					<div class="left col-sm-3">
			 				<div class="checkbox">
								<label>
									<input name="authElement" id="${appMenu.id}" type="checkbox" class="ace menu main" value="${appMenu.id}"/>
									<span class="lbl">  ${appMenu.menuName }</span>
								</label>
							</div>
       					</div>
       					<c:if test="${null ==appMenu.menu}">
				       		<div class="right col-sm-9">
								<div class="checkbox right">
									<label>
										<input name="${appMenu.menuFlag}-EXPORT" id="${appMenu.id}" type="checkbox" class="ace" />
										<span class="lbl">导出</span>
									</label>
								</div>
								<div class="checkbox right">
									<label>
										<input name="${appMenu.menuFlag}-SEARCH" id="${appMenu.id}" type="checkbox" class="ace" />
										<span class="lbl">查询</span>
									</label>
								</div>
								<div class="checkbox right">
									<label>
										<input name="${appMenu.menuFlag}-DELETE" id="${appMenu.id}" type="checkbox" class="ace" />
										<span class="lbl">删除</span>
									</label>
								</div>
								<div class="checkbox right">
									<label>
										<input name="${appMenu.menuFlag}-MODIFY" id="${appMenu.id}" type="checkbox" class="ace" />
										<span class="lbl"> 修改</span>
									</label>
								</div>
				       			<div class="checkbox right">
									<label>
										<input name="${appMenu.menuFlag}-ADD" id="${appMenu.id}" type="checkbox" class="ace" />
										<span class="lbl"> 增加</span>
									</label>
								</div>
				       		</div>
			       		</c:if>
		        	</div>
       				<c:if test="${null !=appMenu.menu}">
   						<c:forEach items="${appMenu.menu}" var="sonMenu">
		       				<div class="list-group-item" style="height: 50px;margin-left: 30px;">
					 			<div class="left col-sm-3">
				 					<div class="checkbox">
										<label>
											<input name="authElement" id="${sonMenu.id}" type="checkbox" class="ace menu ${appMenu.id}" value="${sonMenu.id}"/>
											<span class="lbl">  ${sonMenu.menuName }</span>
										</label>
									</div>
		       					</div>
		       					<c:if test="${null ==sonMenu.menu}">
						       		<div class="right col-sm-9">
						       			<div class="checkbox right">
											<label>
												<input name="${sonMenu.menuFlag}-EXPORT" id="${appMenu.id}" type="checkbox" class="ace" />
												<span class="lbl">导出</span>
											</label>
										</div>
										<div class="checkbox right">
											<label>
												<input name="${sonMenu.menuFlag}-SEARCH" id="${appMenu.id}" type="checkbox" class="ace" />
												<span class="lbl">查询</span>
											</label>
										</div>
										<div class="checkbox right">
											<label>
												<input name="${sonMenu.menuFlag}-DELETE" id="${appMenu.id}" type="checkbox" class="ace" />
												<span class="lbl">删除</span>
											</label>
										</div>
										<div class="checkbox right">
											<label>
												<input name="${sonMenu.menuFlag}-MODIFY" id="${appMenu.id}" type="checkbox" class="ace" />
												<span class="lbl"> 修改</span>
											</label>
										</div>
						       			<div class="checkbox right">
											<label>
												<input name="${sonMenu.menuFlag}-ADD" id="${appMenu.id}" type="checkbox" class="ace" />
												<span class="lbl"> 增加</span>
											</label>
										</div>
						       		</div>
					       		</c:if>
					        </div>
		       					<c:if test="${null !=sonMenu.menu}">
		       						<c:forEach items="${sonMenu.menu}" var="thirdMenu">
			       						<div class="list-group-item" style="height: 50px;margin-left: 30px;">
								 			<div class="left col-sm-4">
					       						<input type="checkbox" id="${thirdMenu.id}" name="" value="" />
								 				<label class="control-label"> ${thirdMenu.menuName }</label>
					       					</div>
								       		<div class="right col-sm-8">
								       			<input type="checkbox" id="" value="ADD" />
									 			<label class="control-label"> 增加</label>
									 			<input type="checkbox" id="" value="MODIFY" />
									 			<label class="control-label"> 修改</label>
									 			<input type="checkbox" id="" value="DELETE" />
									 			<label class="control-label"> 删除</label>
									 			<input type="checkbox" id="" value="SEARCH" />
									 			<label class="control-label"> 查询</label>
									 			<input type="checkbox" id="" value="EXPORT" />
									 			<label class="control-label"> 导出</label>
								       		</div>
								        </div>
									</c:forEach>
								</c:if>
       					</c:forEach>
					</c:if>
       			</c:forEach>
       		</c:if>
       </div>
</div>
