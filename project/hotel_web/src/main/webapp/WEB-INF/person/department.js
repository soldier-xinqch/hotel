    $(function () {
    	//首先定义表格选项
    	var tabId = $("#department_grid");
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
    	                return { data: dataJSON.list };
    	            }
    	        };
    	var colModel = [
    	        		{ title: "企业编号",dataIndx:"orgFlag", width: 553, dataType: "string", align: "center"},
    	        		{ title: "企业名称",dataIndx:"companyName", width: 553, dataType: "string", align: "center" },
    	        		{ title: "企业介绍",dataIndx:"companyDesc", width: 553, dataType: "string", align: "center" }
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
                    }
                ]
            };
        var obj = { 
        	title:"企业信息列表",
//			width: '100%',
			height: "100%",
    		title: "企业列表",
//            minWidth: 500,
//            height: 400,
            flexHeight: true,
            showTitle:true,
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
            pageModel: { type: "remote", rPP: 10, rPPOptions: [15, 20, 50, 100] },//定义表尾分页参数 local为本地数据，remote 为远程数据
            scrollModel: { autoFit: false },
            collapsible: { on: true, collapsed: false }
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
                var check = row.delFlag?'checked':'';
                var updateDialogMsg = '<div class="row"><form id="update_tab_from" class="form-horizontal" role="form"><div class="form-group"><label class="col-sm-3 control-label">企业名称</label><input type="hidden" class="form-control input-xlarge" name="id" value="'+row.id+'">'+
        		'<div class="col-sm-9"><input type="text" class="form-control input-xlarge" name="companyName" value="'+row.companyName+'"  ></div></div><div class="form-group">	<label class="col-sm-3 control-label">企业介绍</label>'+
        		'<div class="col-sm-9"><textarea id="form-field-9" name="companyDesc" class="form-control limited input-xlarge" maxlength="50">'+row.companyDesc+'</textarea></div></div><div class="form-group"><div class="col-sm-offset-3 col-sm-6">'+
        		'</div></div></form></div>';


                var $frm = $("form#crud-form");
                bootbox.dialog({ 
                	title: "修改企业信息", 
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
        
        var addDialogMsg = '<div class="row"><form id="add_tab_from" class="form-horizontal" role="form"><div class="form-group"><label class="col-sm-3 control-label">企业名称</label>'+
        	'<div class="col-sm-9"><input type="text" class="form-control input-xlarge" name="companyName"  ></div></div><div class="form-group">	<label class="col-sm-3 control-label">企业介绍</label>'+
        	'<div class="col-sm-9"><textarea id="form-field-9" name="companyDesc" class="form-control limited input-xlarge" maxlength="50"></textarea></div></div><div class="form-group"><div class="col-sm-offset-3 col-sm-6">'+
        	'</div></div></form></div>';
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
    