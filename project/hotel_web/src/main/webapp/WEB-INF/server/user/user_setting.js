﻿$(function () {
//	$('#update_tab_from .row').find(" .form-group div").find("select").pqSelect({ 
//	selectClsType:'',
//	deselect: false,
//	width:'270px'
//});
    	//首先定义表格选项
var tabId = $("#user_grid");
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
                return { curPage: dataJSON.pageNo, totalRecords: dataJSON.pageSize,data: dataJSON.list };
            }
        };
var colModel = [
                { title: "用户名称",dataIndx:"username", width: '10%', dataType: "string", align: "center" ,
                	filter: { type: 'textbox', condition: 'begin', listeners: ['keyup'] }
                },
        		{ title: "所属组织",dataIndx:"orgName", width: '10%', dataType: "string", align: "center",
        			filter: { type: 'textbox', condition: 'begin', listeners: ['keyup'] }
                },
        		{ title: "用户昵称",dataIndx:"nickName", width: '10%', dataType: "string", align: "center" },
        		{ title: "真实姓名",dataIndx:"realName", width: '10%', dataType: "string", align: "center" ,
        			filter: { type: 'textbox', condition: 'begin', listeners: ['keyup'] }
        		},
        		{ title: "用户邮箱",dataIndx:"email", width:'14%', dataType: "string", align: "center" },
        		{ title: "用户手机",dataIndx:"telphone", width: '10%', dataType: "string", align: "center",
        			filter: { type: 'textbox', condition: 'begin', listeners: ['keyup'] }
        		},
        		{ title: "固定电话",dataIndx:"fixedTelphone", width: '10%', dataType: "string", align: "center" },
        		{ title: "最后登陆",dataIndx:"lastLogin", width: '13%', dataType: "date", align: "center",
        			 filter: { type: 'textbox', condition: "between", init: pqDatePicker, listeners: ['change'] },
        			 render: function (ui) {
         	            var rowData=ui.rowData;
                     return formatDateTime(rowData.lastLogin,"yyyy-MM-dd HH:mm:ss");

        			 }
        		},
        		{ title: "操作", editable: false, minWidth: '12%', sortable: false,align: "center", render: function (ui) {
    	            var rowData=ui.rowData,flag=rowData.delFlag ;
                return "<button id="+rowData.id+" type='button' class='btn btn-xs btn-success auth_btn'>用户授权</button>\
                    <button id="+rowData.id+" type='button' class='btn btn-xs btn-primary update_passwd' >修改密码</button>";
            }
            }
        	];
var tab_toolbar = {
        items: [
            { type: 'button', label: '添加', listeners: [{ click: tab_addRow}], cls: 'btn btn-sm btn-success' },
            { type: 'button', label: '修改', listeners: [{ click:tab_editRow}], cls: 'btn btn-sm btn-primary' },
            { type: 'button', label: '删除', listeners: [{ click: tab_deleteRow}], cls: 'btn btn-sm btn-danger' },
            {type: 'separator' },
            {
				type:'button',
				label: '查询',
				listener: function(){
					this.option('filterModel.header', !this.option('filterModel.header'));
					this.refresh();
				},
				cls: 'btn btn-sm btn-danger'
			},
            {
				type:'button',
				label: '重置',
				listener: function(){
					this.reset({filter: true});							
				},
				cls: 'btn btn-sm btn-danger'
            }
        ]
    };
function pqDatePicker(ui) {
    var $this = $(this);
    $this
    //.css({ zIndex: 3, position: "relative" })
        .datepicker({
            yearRange: "-20:+0", //20 years prior to present.
            changeYear: true,
            changeMonth: true,
            //showButtonPanel: true,
            onClose: function (evt, ui) {
                $(this).focus();
            }
        });
    //default From date
    $this.filter(".pq-from").datepicker("option", "defaultDate", new Date());
    //default To date
    $this.filter(".pq-to").datepicker("option", "defaultDate", new Date());
}

var obj = { 
	title:"用户信息列表",
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
    filterModel: { on: true, mode: "AND", header: false },
    refresh: function () {
        $(".auth_btn").bind('click',function(){
        	authUser();
        });
        $(".update_passwd").bind('click',function(){
        	updatePasswd();
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
        var updateDialogMsg = '<div"><form id="update_tab_from" class="form-horizontal" role="form">'+$("#user_dialog").html()+'</form></div>';
        bootbox.dialog({
        	title: "修改用户信息", 
        	message:updateDialogMsg,
        	animate: true,
	    	buttons: {
	             success: {
	                 label: "保存",
	                 className: "btn btn-sm btn-success",
	                 callback: function () {
 	                	 $.ajax({
	                		   type: "POST",
	                		   url: "modifyUser",
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
	$('#update_tab_from input[name=id]').val(row.id);
	$('#update_tab_from input[name=username]').val(row.username);
	$('#update_tab_from input[name=nickName]').val(row.nickName);
	$('#update_tab_from input[name=realName]').val(row.realName);
	$('#update_tab_from input[name=email]').val(row.email);
	$('#update_tab_from input[name=telphone]').val(row.telphone);
	$('#update_tab_from input[name=fixedTelphone]').val(row.fixedTelphone);
	$('#update_tab_from select[name=orgId]').find("option[value="+row.orgId+"-"+row.orgName+"]").attr("selected",true);
	$('#update_tab_from select[name=orgId]').pqSelect({
          checkbox: false, //adds checkbox to options   
          selectallText:'全选',
          width:'270px'
      }).pqSelect('close');  
}
//append Row
function tab_addRow() {
    var $frm = $("form#crud-form");
    $frm.find("input").val("");
    var addMessage = '<div class="dialog-cls"><form id="add_tab_from" class="form-horizontal" role="form">'+$("#user_dialog").html()+'</form></div>';
    bootbox.dialog({ 
    	title: "新增用户", 
    	message:addMessage,
    	animate: true,
    	buttons: {
             success: {
                 label: "保存",
                 className: "btn btn-sm btn-success",
                 callback: function () {
                	 $.ajax({
                		   type: "POST",
                		   url: "addUser",
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
		selectallText:'全选',
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
    		  title: "删除用户信息",
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
                		   url: "deleteUser",
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
function authUser() {
	var rowIndx = getRowIndx();
    var rowData = $grid.pqGrid("getRowData", { rowIndx: rowIndx });
    var addMessage = '<div class="dialog-cls"><form id="auth_from" class="form-horizontal" role="form">'+$("#user_auth").html()+'</form></div>';
//    $.ajax({
//		   type: "POST",
//		   url: "searchRoleAuth",
//		   data: {"id":rowData.id},
//		   success: function(data){
//			   //TODO 将得到的数据插入到弹出框中 .elementId
//			  alert(data.authList);
//		   }
//		});
    bootbox.dialog({ 
        	title: "角色授权", 
        	message:addMessage,
        	animate: true,
	    	buttons: {
	             success: {
	                 label: "保存",
	                 className: "btn btn-sm btn-success",
	                 callback: function () {
 	                	 $.ajax({
	                		   type: "POST",
	                		   url: "modifyUser",
	                		   data: $("#auth_from").serialize(),
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
	    	}
        });
    	$('#auth_from .row').find(" .form-group div").find("select").pqSelect({ 
    		selectClsType:'请选择授权的菜单',
    		selectallText:'全选',
    		checkbox: true,
    		width:'270px'
    	});
    	$('#auth_from input[name=id]').val(rowData.id);
    	$('#auth_from input[name=userName]').val(rowData.username);
    	$('#auth_from input[name=roleName]').val(rowData.roleName);
//    	$('#auth_from select[name=orgId]').val(row.menuIndex);
//    	$('#update_tab_from input[name=menuName]').val(row.menuName);
//    	$('#update_tab_from input[name=menuIcon]').val(row.menuIcon);
//    	$('#update_tab_from select[name=menuLevel]').val(row.menuLevel);
//    	$('#update_tab_from select[name=menuParentId]').val(row.menuParentId);
}
//delete Row.
function updatePasswd() {
    var rowIndx = getRowIndx();
    if (rowIndx != null) {
    	var row = $grid.pqGrid('getRowData', {rowIndx: rowIndx});
        var updatePasswd = '<div class="dialog-cls"><form id="update_passwd" class="form-horizontal" role="form">'+$("#user_edit_passwd").html()+'</form></div>';
    	bootbox.dialog({
    		  message: updatePasswd,
    		  title: "修改密码",
    		  buttons: {
    		    success: {
    		      label: "确定",
    		      className: "btn btn-sm btn-success",
    		      callback: function() {
    		    	  $.ajax({
                		   type: "POST",
                		   url: "modifyPasswd",
                		   data: $("#update_passwd").serialize(),
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
    	$('#update_passwd input[name=userName]').val(row.username);
    	$('#update_passwd input[name=id]').val(row.id);
    }
}
});    