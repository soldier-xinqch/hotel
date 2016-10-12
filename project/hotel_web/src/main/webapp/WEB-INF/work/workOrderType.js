    $(function () {
    	//首先定义表格选项
    	var tabId = $("#type_grid");
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
    	                return { curPage: dataJSON.pageNo, totalRecords: dataJSON.total,data: dataJSON.list };
    	            }
    	        };
    	var colModel = [
    	                { title: "班次编号",dataIndx:"createTime", width: '30%', dataType: "Integer", align: "center",
    	                	filter: { type: 'textbox', condition: 'begin', listeners: ['change'] }
    	                },
    	        		{ title: "类别名称",dataIndx:"orderName", width: '30%', dataType: "string", align: "center",
    	        			filter: { type: 'textbox', condition: 'begin', listeners: ['change'] }
    	                },
    	        		{ title: "班次描述",dataIndx:"orderDesc", width: '39%', dataType: "string", align: "center" }
    	        	];
    	var tab_toolbar = {
                items: [
                    { type: 'button', label: '添加', listeners: [{ click: tab_addRow}], cls: 'btn btn-sm btn-success' },
                    { type: 'button', label: '修改', listeners: [{ click:tab_editRow}], cls: 'btn btn-sm btn-primary' },
                    { type: 'button', label: '删除', listeners: [{ click: tab_deleteRow}], cls: 'btn btn-sm btn-danger' },
                    {type: 'separator' },
                    {
                        type: 'button',
                        label: "导出",
                        cls:'btn btn-sm',
                        listeners: [{"click": function (evt) {
                        	 tab_export();
                            }
                        }]
                    }
                ]
            };
        var obj = { 
        	title:"班次类别信息列表",
			height: "100%",
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
            filterModel: { on: true, mode: "AND", header: false, type: 'local' },
            selectionModel: { mode: 'single' },
            selectionModel: { type: 'row' },//选中 row 为整行 cell 为整列 model 为单元格
            pageModel: { type: "remote", rPP: 10, rPPOptions: [10, 20, 50, 100] },//定义表尾分页参数 local为本地数据，remote 为远程数据
            scrollModel: { autoFit: false },
            collapsible: { on: true, collapsed: false },
            complete:function(event, ui){
            	//TODO  
            },
            rowDblClick: function(evt, ui){
            	alert(evt.originalEvent.target);
            }
        };
        obj.columnTemplate = {            
            title: function (ui) {
            	ss();
                return ui.column.Title + " (" + ui.column.width + ")";
            }
        };
        obj.toolbar = tab_toolbar;
        var $grid = tabId.pqGrid(obj);
        $grid.pqGrid("option", $.paramquery.pqGrid.regional['zh']);            
        $grid.find(".pq-pager").pqPager("option", $.paramquery.pqPager.regional['zh']);   
        //load shipregion and shipvia dropdowns in first load event.   
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
                var updateDialogMsg = '<div"><form id="update_tab_from" class="form-horizontal" role="form">'+$("#type_dialog").html()+'</form></div>';
                bootbox.dialog({
                	title: "班次类型修改", 
                	message:updateDialogMsg,
                	animate: true,
        	    	buttons: {
        	             success: {
        	                 label: "保存",
        	                 className: "btn btn-sm btn-success",
        	                 callback: function () {
         	                	 $.ajax({
        	                		   type: "POST",
        	                		   url: "modifyWorkOrderType",
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
                });
                updateMeuns(row);
                
            }
        }
        function updateMeuns(row){
        	$('#update_tab_from input[name=id]').val(row.id);
        	$('#update_tab_from input[name=orderName]').val(row.orderName);
        	$('#update_tab_from select[name=orgId]').find("option[value="+row.orgId+"-"+row.orgName+"]").attr("selected",true);
        	$('#update_tab_from select[name=orgId]').pqSelect({
                  checkbox: false, //adds checkbox to options   
                  width:'270px'
              }).on("change", function(event,ui) {
              }).pqSelect('close');  
        	$('#update_tab_from textarea[name=orderDesc]').val(row.orderDesc);
        }
        //append Row
        function tab_addRow() {
            var $frm = $("form#crud-form");
            $frm.find("input").val("");
            var addMessage = '<div class="dialog-cls"><form id="add_tab_from" class="form-horizontal" role="form">'+$("#type_dialog").html()+'</form></div>';
            bootbox.dialog({ 
            	title: "新增班次类型", 
            	message:addMessage,
            	animate: true,
    	    	buttons: {
    	             success: {
    	                 label: "保存",
    	                 className: "btn btn-sm btn-success",
    	                 callback: function () {
     	                	 $.ajax({
    	                		   type: "POST",
    	                		   url: "addWorkOrderType",
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
        	$('#add_tab_from .row').find(" .form-group div").find("select").pqSelect({ 
        		selectClsType:'',
        		deselect: false,
        		width:'270px'
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
            		  title: "删除班次类别信息",
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
    	                		   url: "deleteWorkOrderType",
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
            		icon: false,
            		height:'300px',
            		title: '点击提示',
            		msg: '请选择一行数据'
            		});
                return null;
            }
        }
        function tab_export() {
          	var delMsg = '<div class="dialog-cls"><form id="export_from" class="form-horizontal" role="form">'+$("#workOrderType_export_dialog").html()+'</form></div>';
          	bootbox.dialog({
          		  message: delMsg,
          		  title: "导出列表",
          		  buttons: {
          		    success: {
          		      label: "导出",
          		      className: "btn btn-sm btn-success",
          		      callback: function() {
          		    	  window.open("exportExcel?"+$("#export_from").serialize());
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
          	$('#export_from .row').find(" .form-group div").find("select").pqSelect({ 
          		selectallText:'全选',
	    		checkbox: true,
	    		width:'270px'
	    	});
          	$('.datepicker').datetimepicker({
    			language: 'zh-CN',
    			format: 'yyyy-mm-dd hh:ii:ss',
    	        autoclose: true,
    	        todayBtn: true,
    	        pickerPosition: "bottom-left"
            });
          }
    });    
    