$(function () {
	//首先定义表格选项
	var tabId = $("#base_info_tab");
	var dataModel = {
//			 	cache: true,
//	            recIndx: "customerid",
//	            sortIndx: "contactname",
//			 	sortDir: "up",
	            location: "remote",
	            sorting: "local",            
	            dataType: "JSON",
	            method: "GET",
	            url: "pageData",
	            getData: function (dataJSON) {  
	                return { data: dataJSON.list };
	            }
	        };
	var colModel = [
		{ title: "基础Key",dataIndx:"baseKey", width: 300, dataType: "string", align: "center"},
		{ title: "基础Value",dataIndx:"baseValue", width: 500, dataType: "string", align: "center" },
		{ title: "详细信息",dataIndx:"baseMessage", width: 550, dataType: "string", align: "center" },
//		{ title: "Price", dataType: "float", width: 70, dataIndx: 3, align:"right", render:function(ui){
//            var rowData=ui.rowData, cV=rowData[3];
//            return "$"+cV;
//        }},	
//		{ title: "有效",dataIndx:"delFlag", width: 150, dataType: "boolean", align: "center",},
		{ title: "有效",dataIndx:"delFlag", width: 150, dataType: "integer", align: "center",
			render:function(ui){
	            var rowData=ui.rowData,flag=rowData.delFlag ;
	            var checkState =flag?true:false;
	            var flagBtn = '<label><input type="checkbox" data-size="mini" data-on-color="primary" data-on-text="有效" data-off-color="danger" data-off-text="无效" data-state="'+checkState+'" name="delFlag" /></label>';
	            return flagBtn;
	    }},
		{ title: "操作", editable: false, minWidth: 100, sortable: false,align: "center", render: function (ui) {
            return "<button type='button' class='btn btn-xs btn-primary edit_btn'>修改</button>\
                <button type='button' class='btn btn-xs btn-danger delete_btn'>删除</button>";
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
                    type: 'button',
                    label: "导出至Excel",
                    cls:'btn btn-sm btn-info',
                    listeners: [{"click": function (evt) {
                    		tabId.pqGrid("exportExcel", { url: "/pro/demos/excel", sheetName: "pqGrid sheet" });
                        }
                    }]
                },
                {
	                type: 'button',
	                label: "导出至CSV",
	                cls:'btn btn-sm btn-info',
	                listeners: [{
	                    "click": function (evt) {
	                    	tabId.pqGrid("exportCsv", { url: "/pro/demos/excel" });
	                    }
                }]
        }
                
            ]
        };
	var tab_obj ={
			 title: "Companies listed on the <b>NASDAQ</b>",
			 width: '100%',
			 height: "100%",
			 flexHeight: true,
			 numberCell:{resizable:true, title: "#"},
			 showTitle:false,
			 showButton:false,
			 sortIndx: 0,//排序列或多列的dataIndx。它变成多列排序使用阵列时
			 pageModel: { type: "local", rPP: 10, rPPOptions: [15, 20, 50, 100] },//定义表尾分页参数 local为本地数据，remote 为远程数据
			 wrap:false,//内容溢出用……显示
			 track: true, //to turn on the track changes.
			 resizable: true,//调整网格的居中和垂直
			 selectionModel: { type: 'row' },//选中 row 为整行 cell 为整列 model 为单元格
		     colModel:colModel,
	         selectionModel: { mode: 'single' },
	         dataModel:dataModel,
//	         editModel: false,
	         editModel: {
	             saveKey: $.ui.keyCode.ENTER
	         },
//	         editor: { type: 'textbox', select: true },//定义单元格修改的表单类型这里是全局设置会覆盖colmodel中的设置，若要自定义最好还是在colmodel中定义
	         validation: {
	             icon: 'ui-icon-info'
	         },
	         //save the cell when cell loses focus.
             quitEditMode: function (evt, ui) {
                 var $grid = $(this);
                 if (evt.keyCode != $.ui.keyCode.ESCAPE) {
                    $grid.pqGrid("saveEditCell");
                 }
             },
             //make rows editable selectively.
             editable: function (ui) {
                 var $grid = $(this);
                 var rowIndx = ui.rowIndx;
                 if ($grid.pqGrid("hasClass", { rowIndx: rowIndx, cls: 'pq-row-edit' }) == true) {
                     return true;
                 }
                 else {
                     return false;
                 }
             },
	        //use refresh event to display jQueryUI buttons and bind events.
	        refresh: function () {
	            //debugger;
	            var $grid = $(this);
	            if (!$grid) {
	            	// 弹出消息组件
	            	alert("表格加载失败");
	                return;
	            }
	            //delete button
	            $grid.find("button.delete_btn").button({})
	            .unbind("click")
	            .bind("click", function (evt) {
	                if (isEditing($grid)) {
	                    return false;
	                }
	                var $tr = $(this).closest("tr"),
	                    rowIndx = $grid.pqGrid("getRowIndx", { $tr: $tr }).rowIndx;
	                deleteRow(rowIndx, $grid);
	            });
	            //edit button
	            $grid.find("button.edit_btn").button({ })
	            .unbind("click")
	            .bind("click", function (evt) {
	                if (isEditing($grid)) {
	                    return false;
	                }
	                var $tr = $(this).closest("tr"),
	                    rowIndx = $grid.pqGrid("getRowIndx", { $tr: $tr }).rowIndx;
	                editRow(rowIndx, $grid);
	                return false;
	            });
	
	            //rows which were in edit mode before refresh, put them in edit mode again.
	            var rows = $grid.pqGrid("getRowsByClass", { cls: 'pq-row-edit' });
	            if (rows.length > 0) {
	                var rowIndx = rows[0].rowIndx;
	                editRow(rowIndx, $grid);
	            }
	            //初始化 滑动按钮
	            $("[name='delFlag']").bootstrapSwitch();
	            $("[name='delFlag']").bind('click',function(){
	            	alert($(this).attr("data-state"));
	            });
	        }
	};
	var pq = {
        totalRecords: 0,
        requestPage: 1,
        pending: true,
        rpp: 100, //records per request.
        data: []
    };
	tab_obj.toolbar = tab_toolbar;
	//如果列中有小图标可以用这种方式为该列添加小图标
	 /*$.extend(newObj.colModel[3], {
         render: function (ui) {
             var rowData = ui.rowData;
             if (rowData[4] < 0) {
                 return "<img src='/Content/images/arrow-us-down.gif'/>&nbsp;" + rowData[3];
             }
             else if (rowData[4] > 0) {
                 return "<img src='/Content/images/arrow-us-up.gif'/>&nbsp;" + rowData[3];
             }
             else {
                 return rowData[3];
             }
         }, width: 100, editable: false
     });*/
	var $grid = tabId.pqGrid(tab_obj);
	 $grid.pqGrid("option", $.paramquery.pqGrid.regional['zh']);            
	 $grid.find(".pq-pager").pqPager("option", $.paramquery.pqPager.regional['zh']);   
	//列上的修改按钮事件方法
	function isEditing($grid) {
         var rows = $grid.pqGrid("getRowsByClass", { cls: 'pq-row-edit' });
         if (rows.length > 0) {
             //focus on editor if any 
             $grid.find(".pq-editor-focus").focus();
             return true;
         }
         return false;
     }
	
	//called by delete button.
    function deleteRow(rowIndx, $grid) {
        $grid.pqGrid("addClass", { rowIndx: rowIndx, cls: 'pq-row-delete' });
        var rowData = $grid.pqGrid("getRowData", { rowIndx: rowIndx });
    	var delMsg = '<form id="del_tab_from" class="form-horizontal" role="form"><input type="hidden" class="form-control input-xlarge" name="baseId" value="'+rowData.baseId+'"></form>';
        bootbox.dialog({
  		  message: "您确定要删除这条信息么"+delMsg,
  		  title: "删除基础信息",
  		  buttons: {
  		    success: {
  		      label: "确定",
  		      className: "btn btn-sm btn-success",
  		      callback: function() {
  		    	 var add_tab_data=add_tab_data+"&"+$("#del_tab_from input[type=hidden]").map(function(){
	                		  return ($(this).attr("name")+'='+$(this).val());
	                		}).get().join("&") ;
  		    	  $.ajax({
              		   type: "POST",
              		   url: "delBaseVal",
              		   data: add_tab_data,
              		   success: function(data){
              			   if('SUCCESS' == data.type){
              				   Lobibox.notify("success", {
              		        		icon: false,
              		        		height:'300px',
              		        		title: '删除提示',
              		        		msg: '删除成功'
              		        	});
              				 $grid.pqGrid("deleteRow", { rowIndx: rowIndx, effect: true });
              			   }else{
              				   Lobibox.notify("error", {
              		        		icon: false,
              		        		height:'300px',
              		        		title: '删除提示',
              		        		msg: '删除失败，请检查删除的数据'
              		        	});
              				   //debugger;
                               $grid.pqGrid("removeClass", { rowData: rowData, cls: 'pq-row-delete' });
                               $grid.pqGrid("rollback");
              			   }
              			   $grid.pqGrid( "refreshDataAndView" );
              		   }
              		});
  		      }
  		    },
  		    canle:{
	            	 label: "取消",
	                 className: "btn btn-sm btn-grey",
	                 callback: function () {
	                	  $grid.pqGrid("removeClass", { rowData: rowData, cls: 'pq-row-delete' });
	                 }
  		    }
  		  }
  		});
    }
    //called by edit button.
    function editRow(rowIndx, $grid) {
        $grid.pqGrid("addClass", { rowIndx: rowIndx, cls: 'pq-row-edit' });
        $grid.pqGrid("editFirstCellInRow", { rowIndx: rowIndx });

        //change edit button to update button and delete to cancel.
        var $tr = $grid.pqGrid("getRow", { rowIndx: rowIndx }),
            $btn = $tr.find("button.edit_btn");
        $btn.button("option", { label: "确认", "icons": { primary: ""} })
            .unbind("click")
            .click(function (evt) {
                evt.preventDefault();
                return update(rowIndx, $grid);
            });
        $btn.next().button("option", { label: "取消", "icons": { primary: ""} })
            .unbind("click")
            .click(function (evt) {
                $grid.pqGrid("quitEditMode");
                $grid.pqGrid("removeClass", { rowIndx: rowIndx, cls: 'pq-row-edit' });
                $grid.pqGrid("refreshRow", { rowIndx: rowIndx });
                $grid.pqGrid("rollback");
            });
    }
    //called by update button.
    function update(rowIndx, $grid) {
        if (!$grid.pqGrid("saveEditCell")) {
            return false;
        }

        var rowData = $grid.pqGrid("getRowData", { rowIndx: rowIndx });
        var isValid = $grid.pqGrid("isValid", { rowData: rowData }).valid;
        if (!isValid) {
            return false;
        }
        var recIndx = $grid.pqGrid("option", "dataModel.recIndx");
        $grid.pqGrid("removeClass", { rowIndx: rowIndx, cls: 'pq-row-edit' });
        $.ajax($.extend({}, ajaxObj, {
            context: $grid,
            type: "POST",
            url: "updateBaseVal",
            data: rowData,
            success: function (response) {
                var recIndx = $grid.pqGrid("option", "dataModel.recIndx");
                if (rowData[recIndx] == null) {
                    rowData[recIndx] = response.recId;
                }
                $grid.pqGrid("removeClass", { rowIndx: rowIndx, cls: 'pq-row-edit' });
                $grid.pqGrid("commit");
            }
        }));
    }
    
    var ajaxObj = {
            dataType: "JSON",
            beforeSend: function () {
                this.pqGrid("showLoading");
            },
            complete: function () {
                this.pqGrid("hideLoading");
            },
            error: function () {
                this.pqGrid("rollback");
            }
        };
    
    
    
    
    
    
    
    //edit Row
    function tab_editRow() {
        var rowIndx = getRowIndx();
        if (rowIndx != null) {

            var row = $grid.pqGrid('getRowData', {rowIndx: rowIndx});
            var check = row.delFlag?'checked':'';
            var updateDialogMsg = '<div class="row"><form id="update_tab_from" class="form-horizontal" role="form"><div class="form-group"><label class="col-sm-3 control-label">基础信息Key</label>'+
    		'<div class="col-sm-9"><input type="hidden" class="form-control input-xlarge" name="baseId" value="'+row.baseId+'"><input type="text" class="form-control input-xlarge" name="baseKey" value="'+row.baseKey+'" >'+
    		'</div></div><div class="form-group"><label class="col-sm-3 control-label">基础信息值</label>'+
    		'<div class="col-sm-9"><input type="text" class="form-control input-xlarge" name="baseValue" value="'+row.baseValue+'"  ></div></div><div class="form-group">	<label class="col-sm-3 control-label">基础信息说明</label>'+
    		'<div class="col-sm-9"><textarea id="form-field-9" name="baseMessage" class="form-control limited input-xlarge" maxlength="50">'+row.baseMessage+'</textarea></div></div><div class="form-group"><div class="col-sm-offset-3 col-sm-6">'+
    		'<div class="checkbox"><label><input name="delflag" type="checkbox" class="ace" '+check+' /><span class="lbl"> 是否生效</span></label></div></div></div></form></div>';


            var $frm = $("form#crud-form");
            bootbox.dialog({ 
            	title: "修改基础信息", 
            	message:updateDialogMsg,
    	    	buttons: {
    	             success: {
    	                 label: "保存",
    	                 className: "btn btn-sm btn-success",
    	                 callback: function () {
    	                	 var add_tab_data=$("#update_tab_from input[type=text]").map(function(){
    	                		  return ($(this).attr("name")+'='+$(this).val());
    	                		}).get().join("&") ;
    	                	 add_tab_data=add_tab_data+"&"+$("#update_tab_from textarea").map(function(){
    	                		  return ($(this).attr("name")+'='+$(this).val());
    	                		}).get().join("&") ;
    	                	 add_tab_data=add_tab_data+"&"+$("#update_tab_from input[type=hidden]").map(function(){
   	                		  return ($(this).attr("name")+'='+$(this).val());
   	                		}).get().join("&") ;
    	                	 add_tab_data =add_tab_data+'&delflag='+$('[name=delflag]').is(':checked');
     	                	 $.ajax({
    	                		   type: "POST",
    	                		   url: "updateBaseVal",
    	                		   data: add_tab_data,
    	                		   success: function(data){
    	                			   if('SUCCESS' == data.type){
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
    	    	}
            });
        }
    }
    //append Row
    function tab_addRow() {
        var $frm = $("form#crud-form");
        $frm.find("input").val("");

        bootbox.dialog({ 
        	title: "新增基础信息", 
        	message:addDialogMsg,
	    	buttons: {
	             success: {
	                 label: "保存",
	                 className: "btn btn-sm btn-success",
	                 callback: function () {
	                	 
	                	 var add_tab_data=$("#add_tab_from input[type=text]").map(function(){
	                		  return ($(this).attr("name")+'='+$(this).val());
	                		}).get().join("&") ;
	                	 add_tab_data=add_tab_data+"&"+$("#add_tab_from textarea").map(function(){
	                		  return ($(this).attr("name")+'='+$(this).val());
	                		}).get().join("&") ;
	                	 add_tab_data =add_tab_data+'&delflag='+$('[name=delflag]').is(':checked');
 	                	 $.ajax({
	                		   type: "POST",
	                		   url: "addBaseVal",
	                		   data: add_tab_data,
	                		   success: function(data){
	                			   if('SUCCESS' == data.type){
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
}
        });
        $("#popup-dialog-crud").dialog("open");
    }
    
    var addDialogMsg = '<div class="row"><form id="add_tab_from" class="form-horizontal" role="form"><div class="form-group"><label class="col-sm-3 control-label">基础信息Key</label>'+
    	'<div class="col-sm-9"><input type="text" class="form-control input-xlarge" name="baseKey" ></div></div><div class="form-group"><label class="col-sm-3 control-label">基础信息值</label>'+
    	'<div class="col-sm-9"><input type="text" class="form-control input-xlarge" name="baseValue"  ></div></div><div class="form-group">	<label class="col-sm-3 control-label">基础信息说明</label>'+
    	'<div class="col-sm-9"><textarea id="form-field-9" name="baseMessage" class="form-control limited input-xlarge" maxlength="50"></textarea></div></div><div class="form-group"><div class="col-sm-offset-3 col-sm-6">'+
    	'<div class="checkbox"><label><input name="delflag" type="checkbox" class="ace" /><span class="lbl"> 是否生效</span></label></div></div></div></form></div>';
    //delete Row.
    function tab_deleteRow() {
        var rowIndx = getRowIndx();
        if (rowIndx != null) {
        	 var row = $grid.pqGrid('getRowData', {rowIndx: rowIndx});
        	var delMsg = '<form id="del_tab_from" class="form-horizontal" role="form"><input type="hidden" class="form-control input-xlarge" name="baseId" value="'+row.baseId+'"></form>';
        	
        	bootbox.dialog({
        		  message: "您确定要删除这条信息么"+delMsg,
        		  title: "删除基础信息",
        		  buttons: {
        		    success: {
        		      label: "确定",
        		      className: "btn btn-sm btn-success",
        		      callback: function() {
        		    	 var add_tab_data=add_tab_data+"&"+$("#del_tab_from input[type=hidden]").map(function(){
   	                		  return ($(this).attr("name")+'='+$(this).val());
   	                		}).get().join("&") ;
        		    	  $.ajax({
	                		   type: "POST",
	                		   url: "delBaseVal",
	                		   data: add_tab_data,
	                		   success: function(data){
	                			   if('SUCCESS' == data.type){
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
            $grid.pqGrid("deleteRow", { rowIndx: rowIndx });
            $grid.pqGrid("setSelection", { rowIndx: rowIndx });
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
});

