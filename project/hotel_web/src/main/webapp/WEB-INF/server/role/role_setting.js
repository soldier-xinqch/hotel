﻿﻿    $(function () {
    	//首先定义表格选项
	var tabId = $("#role_grid");
	var addBtn = { type: 'button', label: '添加', listeners: [{ click: tab_addRow}], cls: 'btn btn-sm btn-success' };
	var modifyBtn ={ type: 'button', label: '修改', listeners: [{ click:tab_editRow}], cls: 'btn btn-sm btn-primary' };
	var delBtn = { type: 'button', label: '删除', listeners: [{ click: tab_deleteRow}], cls: 'btn btn-sm btn-danger' };
	var item =[];
	 $.ajax({
		   type: "GET",
		   url: "searchRoleAuth",
		   data: {"id":rowData.id},
		   success: function(data){
			   if(null !=dataJSON.authElements){
          		 $.each(dataJSON.authElements, function(key, value){
//      		        alert(key+":"+value);
      		        if(('${menuKey}'+'-ADD')  ==key)item.push(addBtn);
          			if('${menuKey}'+'-MODIFY' ==key)item.push(modifyBtn);
      				if('${menuKey}'+'-DELETE' ==key)item.push(delBtn);
//              		if('${menuKey}'+'-SEARCH' ==key)item.push(searchBtn);
//          			if('${menuKey}'+'-EXPORT' ==key)item.push(exportBtn);
      		     });
          		 alert(item);
          	}
		   }
		});
	var dataModel = {
			 	cache: true,
//    	            recIndx: "customerid",
//    	            sortIndx: "contactname",
//    			 	sortDir: "up",
	            location: "remote",
	            sorting: "local",            
	            dataType: "JSON",
	            method: "GET",
	            url: "pageData",
	            getData: function (dataJSON) {  
	            	var data = dataJSON.list;
	                return { curPage: dataJSON.pageNo, totalRecords: dataJSON.total, data: data };
	            }
	        };
	alert(item);
	var colModel = [
	                { title: "角色名称",dataIndx:"roleName", width: '25%', dataType: "string", align: "center" },
	        		{ title: "角色标识",dataIndx:"roleKey", width: '25%', dataType: "string", align: "center"},
	        		{ title: "所属组织",dataIndx:"orgName", width: '34%', dataType: "string", align: "center" },
	        		{ title: "操作", editable: false, minWidth: '15%', sortable: false,align: "center", render: function (ui) {
	        	            var rowData=ui.rowData,flag=rowData.delFlag ;
	        	            var btnName,btnCls;
	        	            if(1==flag){
	        	            	btnName="激活";
	        	            	btnCls="btn-success";
	        	            }else{
	        	            	btnName="冻结";
	        	            	btnCls='btn-danger';
	        	            }
	        	            var checkState =flag?true:false;
	                    return "<button id="+rowData.id+" type='button' class='btn btn-xs btn-primary auth_btn'>授权</button>";
//	                    		"\TODO 以后时间不紧在加上吧
//	                        <button id="+rowData.id+" type='button' class='btn btn-xs "+btnCls+" delete_btn' >"+btnName+"</button>";
	                }
	                }
	        	];
	var tab_toolbar = {
			  items:item
//            items: [
//                { type: 'button', label: '添加', listeners: [{ click: tab_addRow}], cls: 'btn btn-sm btn-success' },
//                { type: 'button', label: '修改', listeners: [{ click:tab_editRow}], cls: 'btn btn-sm btn-primary' },
//                { type: 'button', label: '删除', listeners: [{ click: tab_deleteRow}], cls: 'btn btn-sm btn-danger' }
//            ]
        };
	
    var obj = { 
    	title:"角色信息",
//			width: '100%',
		height: "100%",
//            minWidth: 500,
//            height: 400,
        flexHeight: true,
        showTitle:false,
		showButton:true,
		wrap:false,
		track: true,
		resizable: true,//调整网格的居中和垂直
		sortIndx: 0,//排序列或多列的dataIndx。它变成多列排序使用阵列时
        colModel:colModel,
        dataModel:dataModel,
        numberCell:{resizable:true, title: "#"},
        selectionModel: { mode: 'single' },
        selectionModel: { type: 'row' },//选中 row 为整行 cell 为整列 model 为单元格
        pageModel: { type: "remote", rPP: 10, rPPOptions: [10, 20, 50, 100] },//定义表尾分页参数 local为本地数据，remote 为远程数据
        scrollModel: { autoFit: false },
        collapsible: { on: true, collapsed: false },
        refresh: function () {
          $(".auth_btn").bind('click',function(){
          	authRole();
          });
          $(".delete_btn").bind('click',function(){
//          	alert($(this).attr("id"));
        	  freezeRole();
          });
        }
    };
    obj.columnTemplate = {            
        title: function (ui) {
            return ui.column.Title + " (" + ui.column.width + ")";
        }
    };
    obj.toolbar = tab_toolbar;
    var $grid = tabId.pqGrid(obj);
    $grid.pqGrid("option", $.paramquery.pqGrid.regional['zh']);            
    $grid.find(".pq-pager").pqPager("option", $.paramquery.pqPager.regional['zh']);   
    //bind width to select list.
    $("#sl_width").change(function (evt) {
        var val = $(this).val();
        $grid.pqGrid('option', 'width', val)
                .pqGrid('refresh');
    });
    $("#sl_margin").change(function (evt) {
        var val = $(this).val();
        $grid.css('margin', val).pqGrid('refresh');
    });
    //edit Row
    function tab_editRow() {
        var rowIndx = getRowIndx();
        if (rowIndx != null) {
            var row = $grid.pqGrid('getRowData', {rowIndx: rowIndx});
            var updateDialogMsg = '<div"><form id="update_tab_from" class="form-horizontal" role="form">'+$("#role_dialog").html()+'</form></div>';
            bootbox.dialog({
            	title: "修改角色", 
            	message:updateDialogMsg,
            	animate: true,
    	    	buttons: {
    	             success: {
    	                 label: "保存",
    	                 className: "btn btn-sm btn-success",
    	                 callback: function () {
     	                	 $.ajax({
    	                		   type: "POST",
    	                		   url: "modifyRole",
    	                		   data: $("#update_tab_from").serialize(),
    	                		   success: function(data){
    	                			   if('success' == data.type){
    	                				   Lobibox.notify("success", {
    	                		        		icon: false,
    	                		        		height:'300px',
    	                		        		title: '修改提示',
    	                		        		msg: '修改成功'
    	                		        	});
    	                				   $grid.pqGrid( "refreshDataAndView" )
    	                			   }else{
    	                				   Lobibox.notify("error", {
    	                		        		icon: false,
    	                		        		height:'300px',
    	                		        		title: '修改提示',
    	                		        		msg: '修改失败，请检查修改的数据'
    	                		        	});
    	                			   }
    	                		   }
    	                		});
    	                 }
    	             },
    	             canle:{
    	            	 label: "取消",
    	                 className: "btn btn-sm btn-grey",
    	                 callback: function () {
    	                	 
    	                 }
    	             }
    	    	},
//        	    	callback:setTimeout(updateMeuns(row),500)
            });
            updateMeuns(row);
        }
    }
    function updateMeuns(row){
    	$('#update_tab_from .row').find(" .form-group div").find("select").pqSelect({ 
    		selectClsType:'',
    		deselect: false,
    		width:'270px'
    	});
    	$('#update_tab_from input[name=id]').val(row.id);
    	$('#update_tab_from input[name=roleName]').val(row.roleName);
    	$('#update_tab_from input[name=roleKey]').val(row.roleKey);
    	$('#update_tab_from select[name=orgId]').val(row.orgId);
    }
    //append Row
    function tab_addRow() {
        var $frm = $("form#crud-form");
        $frm.find("input").val("");
        var addMessage = '<div class="dialog-cls"><form id="add_tab_from" class="form-horizontal" role="form">'+$("#role_dialog").html()+'</form></div>';
        bootbox.dialog({ 
        	title: "新增角色", 
        	message:addMessage,
        	animate: true,
	    	buttons: {
	             success: {
	                 label: "保存",
	                 className: "btn btn-sm btn-success",
	                 callback: function () {
	                	 if(vailDateParam('ADD')){
	                		 return false;
	                	 }
 	                	 $.ajax({
	                		   type: "POST",
	                		   url: "addRole",
	                		   data: $("#add_tab_from").serialize(),
	                		   success: function(data){
	                			   if('success' == data.type){
	                				   Lobibox.notify("success", {
	                		        		icon: false,
	                		        		height:'300px',
	                		        		title: '新增提示',
	                		        		msg: '添加成功'
	                		        	});
	                				   $grid.pqGrid( "refreshDataAndView" )
	                			   }else{
	                				   Lobibox.notify("error", {
	                		        		icon: false,
	                		        		height:'300px',
	                		        		title: '新增提示',
	                		        		msg: '添加失败，请检查添加的数据'
	                		        	});
	                			   }
	                		   }
	                		});
	                 }
	             },
	             canle:{
	            	 label: "取消",
	                 className: "btn btn-sm btn-grey",
	                 callback: function () {
	                	 
	                 }
	             }
	    	},
	    	callback:setTimeout(selectInput,200)
        });
        $("#popup-dialog-crud").dialog("open");
    }
    function selectInput(){
//    	$('#add_tab_from .row').find(" .form-group div").find("select").select2();
    	$('#add_tab_from .row').find(" .form-group div").find("select").pqSelect({ 
    		selectClsType:'',
    		deselect: false
//    		width:'270px'
    	});
    }
    function formatRepoProvince(repo) {
        if (repo.loading) return repo.text;
        var markup = "<div>"+repo.name+"</div>";
        return markup;
    }
    //delete Row.
    function tab_deleteRow() {
        var rowIndx = getRowIndx();
        if (rowIndx != null) {
        	var row = $grid.pqGrid('getRowData', {rowIndx: rowIndx});
        	var delMsg = '<form id="del_tab_from" class="form-horizontal" role="form"><input type="hidden" class="form-control input-xlarge" name="id" value="'+row.id+'"></form>';
        	bootbox.dialog({
        		  message: "您确定要删除这条信息么"+delMsg,
        		  title: "删除角色",
        		  buttons: {
        		    success: {
        		      label: "确定",
        		      className: "btn btn-sm btn-success",
        		      callback: function() {
        		    	 var add_tab_data=$("#del_tab_from input[type=hidden]").map(function(){
   	                		  return ($(this).attr("name")+'='+$(this).val());
   	                		}).get().join("&") ;
        		    	  $.ajax({
	                		   type: "POST",
	                		   url: "deleteRole",
	                		   data: add_tab_data,
	                		   success: function(data){
	                			   if('success' == data.type){
	                				   $grid.pqGrid("deleteRow", { rowIndx: rowIndx });
	                	               $grid.pqGrid("setSelection", { rowIndx: rowIndx });
	                				   Lobibox.notify("success", {
	                		        		icon: false,
	                		        		height:'300px',
	                		        		title: '删除提示',
	                		        		msg: '删除成功'
	                		        	});
	                			   }else{
	                				   Lobibox.notify("error", {
	                		        		icon: false,
	                		        		height:'300px',
	                		        		title: '删除提示',
	                		        		msg: '删除失败，请检查删除的数据'
	                		        	});
	                			   }
	                			   $grid.pqGrid( "refreshDataAndView" )
	                		   }
	                		});
        		      }
        		    },
        		    canle:{
   	            	 label: "取消",
   	                 className: "btn btn-sm btn-grey",
   	                 callback: function () {
   	                 }
        		    }
        		  }
        		});
        }
    }
    function getRowIndx() {
        var arr = $grid.pqGrid("selection", { type: 'row', method: 'getSelection' });
        if (arr && arr.length > 0) {
            return arr[0].rowIndx;                                
        }
        else {
        	Lobibox.notify("info", {
        		//size: 'mini',
        		icon: false,
        		//width:'',
        		height:'300px',
        		title: '点击提示',
        		msg: '请选择一行数据'
        		});
            return null;
        }
    }
    
    
    function authRole() {
    	var rowIndx = getRowIndx();
        var rowData = $grid.pqGrid("getRowData", { rowIndx: rowIndx });
        var addMessage = '<div class="dialog-cls"><form id="auth_from" class="form-horizontal" role="form">'+$("#auth_dialog").html()+'</form></div>';
        var authId='';
        $.ajax({
 		   type: "POST",
 		   url: "searchRoleAuth",
 		   data: {"id":rowData.id},
 		   success: function(data){
 			   //TODO 将得到的数据插入到弹出框中 .elementId
// 			  alert(data.authList);
 		   }
 		});
        bootbox.dialog({ 
	        	title: "角色授权", 
	        	message:addMessage,
	        	animate: true,
		    	buttons: {
		             success: {
		                 label: "保存",
		                 className: "btn btn-sm btn-success",
		                 callback: function () {
		                	 var add_tab_data=$("#auth_from input[type=hidden]").map(function(){
		                		 return ($(this).attr("name")+'='+$(this).val());
   	                			}).get().join("&") ;
//		                	 alert($("#auth_from").serialize()+"&"+add_tab_data);
	 	                	 $.ajax({
		                		   type: "POST",
		                		   url: "modifyAuthRole",
		                		   data: $("#auth_from").serialize()+"&"+add_tab_data,
		                		   success: function(data){
		                			   if('success' == data.type){
		                				   Lobibox.notify("success", {
		                		        		icon: false,
		                		        		height:'300px',
		                		        		title: '新增提示',
		                		        		msg: '添加成功'
		                		        	});
		                				   $grid.pqGrid( "refreshDataAndView" );
		                			   }else{
		                				   Lobibox.notify("error", {
		                		        		icon: false,
		                		        		height:'300px',
		                		        		title: '新增提示',
		                		        		msg: '添加失败，请检查添加的数据'
		                		        	});
		                			   }
		                		   }
		                		});
		                 }
		             },
		             canle:{
		            	 label: "取消",
		                 className: "btn btn-sm btn-grey",
		                 callback: function () {
		                	 
		                 }
		             }
		    	},
	        });
	    	$('#auth_from .row').find(" .form-group div").find("select").pqSelect({ 
	    		selectClsType:'请选择授权的菜单',
	    		checkbox: true,
	    		width:'270px'
	    	});
	    	$('#auth_from input[name=roleName]').val(rowData.roleName);
	    	$('#auth_from input[name=roleId]').val(rowData.id);
	    	$('#auth_from input[name=authId]').val(authId);
//        	$('#auth_from select[name=authElement]').val(row.authElement);
	    	$("#auth_from .list-group-item").find('.menu').bind("click",function(){
	    		if($(this).is(":checked")){
	    			if($(this).hasClass("main")){
	    				$("#auth_from .list-group-item ."+$(this).attr("id")).parents(".list-group-item").find(".lbl").trigger("click");
	    				$("#auth_from .list-group-item").find("."+$(this).attr("id")).attr("checked",true);
	    				$("#auth_from .list-group-item ."+$(this).attr("id")).parents(".list-group-item").find("input[type=checkbox]").attr("checked",true);
	    			}else{
	    				$(this).parents(".list-group-item").find(".lbl").trigger("click");
	    				$(this).parents(".list-group-item").find("input[type=checkbox]").attr("checked",true);
	    			}
	    			$(this).parents(".list-group-item").find("input[type=checkbox]").attr("checked",true);
	    		}else{
	    			$(this).parents(".list-group-item").find("input[type=checkbox]").removeAttr("checked");
	    			$("#auth_from .list-group-item").find("."+$(this).attr("id")).removeAttr("checked");
	    			$("#auth_from .list-group-item ."+$(this).attr("id")).parents(".list-group-item").find("input[type=checkbox]").removeAttr("checked");
	    		}
	    	});
//	    	$("#auth_from .list-group-item .right").find('input[type=checkbox]').bind("click",function(){
//	    		if($(this).is(":checked")){
//	    			$(this).parents(".list-group-item input[name=authElement]").find(".lbl").trigger("click");
//	    			alert($(this).parents(".list-group-item").find("input[name=authElement]").is(":checked"));
//	    			alert($(this).parents(".list-group-item .lbl").html());
//	    			if(!$(this).parents(".list-group-item").find("input[name=authElement]").is(":checked")){
//	    				$(this).parents(".list-group-item").find("input[name=authElement]").attr("checked",true);
//	    			}
//	    		}
//	    	});
	    	
    }
    //delete Row.
    function freezeRole() {
        var rowIndx = getRowIndx();
        if (rowIndx != null) {
        	var row = $grid.pqGrid('getRowData', {rowIndx: rowIndx});
        	var delMsg = '<form id="freeze_from" class="form-horizontal" role="form"><input type="hidden" class="form-control input-xlarge" name="id" value="'+row.id+'"></form>';
        	bootbox.dialog({
        		  message: "您确定要冻结此角色么"+delMsg,
        		  title: "角色操作",
        		  buttons: {
        		    success: {
        		      label: "确定",
        		      className: "btn btn-sm btn-success",
        		      callback: function() {
        		    	 var add_tab_data=$("#freeze_from input[type=hidden]").map(function(){
   	                		  return ($(this).attr("name")+'='+$(this).val());
   	                		}).get().join("&") ;
        		    	  $.ajax({
	                		   type: "POST",
	                		   url: "freezeAndActive",
	                		   data: add_tab_data,
	                		   success: function(data){
	                			   if('success' == data.type){
	                				   $grid.pqGrid("deleteRow", { rowIndx: rowIndx });
	                	               $grid.pqGrid("setSelection", { rowIndx: rowIndx });
	                				   Lobibox.notify("success", {
	                		        		icon: false,
	                		        		height:'300px',
	                		        		title: '操作提示',
	                		        		msg: '操作成功'
	                		        	});
	                			   }else{
	                				   Lobibox.notify("error", {
	                		        		icon: false,
	                		        		height:'300px',
	                		        		title: '操作提示',
	                		        		msg: '操作失败，请检查操作的数据'
	                		        	});
	                			   }
	                			   $grid.pqGrid( "refreshDataAndView" )
	                		   }
	                		});
        		      }
        		    },
        		    canle:{
   	            	 label: "取消",
   	                 className: "btn btn-sm btn-grey",
   	                 callback: function () {
   	                 }
        		    }
        		  }
        		});
        }
    }
    function vailDateParam(status){
    	var isSuccess;
    	var roleName = $('form input[name=roleName]').val();
    	var roleKey = $('form input[name=roleKey]').val();
    	$.ajax({
 		   type: "GET",
 		   url: "validate",
 		   async: false,
 		   data: "roleName="+roleName+"&roleKey="+roleKey+"&status="+status,
 		   success: function(data){
 			   if('success' != data.type){
 				   Lobibox.notify("error", {
 					   	size: 'mini',
 		        		position: 'center top',
 		        		msg: data.message
 		        	});
 				  isSuccess=true;
 			   }
 			   if('success'==data.type){
 				   isSuccess=false;
 			   }
 		   }
 		});
    	return isSuccess;
    }
});    