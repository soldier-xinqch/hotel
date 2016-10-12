﻿    $(function () {
    	//首先定义表格选项
	var tabId = $("#org_grid");
	var dataModel = {
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
	                { title: "组织名称",dataIndx:"orgName", width: '17%', dataType: "string", align: "center" },
	        		{ title: "所属组织",dataIndx:"parentName", width: '17%', dataType: "string", align: "center"},
	        		{ title: "组织编码",dataIndx:"orgNo", width: '15%', dataType: "string", align: "center" },
	        		{ title: "组织描述",dataIndx:"orgDesc", width: '30%', dataType: "string", align: "center" },
	        		{ title: "创建时间",dataIndx:"createTime", width: '20%', dataType: "string", align: "center",
	        			 render: function (ui) {
	         	            var rowData=ui.rowData;
	                     return formatDateTime(rowData.createTime,"yyyy-MM-dd HH:mm:ss");
	        			 }
	        		},
	        	];
		var topicjson={
				   "response": [
				          {
				              "id": "1",
				              "elementName": "Grouping",
				              level:"0", parent:"", isLeaf:false, loaded:true
				          },
				          {
				              "id": "1_1",
				              "elementName": "Simple Grouping",
				              level:"1", parent:"1", isLeaf:false, loaded:true
				          },
				          {
				              "id": "1_2",
				              "elementName": "May be some other grouping",
				              level:"3", parent:"1", isLeaf:false, loaded:true
				          },
				          {
				              "id": "2",
				              "elementName": "CustomFormater",
				              level:"0", parent:"", isLeaf:false, loaded:true
				          },
				          {
				              "id": "2_1",
				              "elementName": "Image Formatter",
				              level:"1", parent:"2", isLeaf:false, loaded:true
				          }
				      ]
				   };
		jQuery('#tree').jqGrid({
			"url":"/org/pageData",
			datatype: "json",
			 autowidth:true,
			"hoverrows":false,
			"viewrecords":false,
			"gridview":true,
			"editurl" : "clientArray",
			"ExpandColumn":"elementName",
			ExpandColClick:true,
			viewrecords : true,
			"height":"auto",
			"sortname":"id",
			"scrollrows":true,
			"treeGrid":true,
			"treedatatype":"json",
			"treeGridModel":"adjacency",
			"treeIcons": {plus:'icon-plus',minus:'icon-minus',leaf:'ui-icon-document-b'},
			"loadonce":true,
			"rowNum":1000, 
			loadui: "disable",
			"treeReader":{
				"parent_id_field":"parentId",
				"level_field":"level",
				"leaf_field":"isLeaf",
				"expanded_field":"expanded",
				"loaded":"loaded",
				"icon_field":"icon"
			},
//			datastr: topicjson,
//			   datatype: "jsonstring",
			   colNames: ["部门名称","部门介绍","111"],
			   colModel: [
			       {name: "elementName", editable:true,width:250, resizable: false},
			       {name: "id",width:200, hidden:false, key:true},
			       {name: "url",width:1, editable:true,hidden:true}
			   ],
//			   jsonReader: {
//			       repeatitems: false,
//			       root: "response"
//			   },
			   loadComplete : function() {
					var table = this;
					setTimeout(function(){
						updatePagerIcons(table);
					}, 0);
				},
				ondblClickRow:function(rowid, iRow, iCol, e){
					/* edit(rowid); */
					alert(1);
				},
			"pager":"#pager"
		});
		// nable add
		jQuery('#tree').jqGrid('navGrid','#pager',
				{
					"edit":false,
					"add":false,
					"del":false,
					"search":false,
					"refresh":true,
					"view":false,
					"excel":false,
					"pdf":false,
					"csv":false,
					"columns":false
				},
				{"drag":true,"resize":true,"closeOnEscape":true,"dataheight":150},
				{"drag":true,"resize":true,"closeOnEscape":true,"dataheight":150}
				);
				jQuery('#tree').jqGrid('bindKeys');
		function updatePagerIcons(table) {
			var replacement = 
			{
				'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
				'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
				'ui-icon-seek-next' : 'icon-angle-right bigger-140',
				'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
			};
			$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
				var icon = $(this);
				var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
				
				if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
			})
		}
    //edit Row
    function tab_editRow() {
//        var rowIndx = getRowIndx();
//        if (rowIndx != null) {
//            var row = $grid.pqGrid('getRowData', {rowIndx: rowIndx});
            var updateDialogMsg = '<div"><form id="update_tab_from" class="form-horizontal" role="form">'+$("#org_dialog").html()+'</form></div>';
            bootbox.dialog({
            	title: "修改组织信息", 
            	message:updateDialogMsg,
            	animate: true,
    	    	buttons: {
    	             success: {
    	                 label: "保存",
    	                 className: "btn btn-sm btn-success",
    	                 callback: function () {
     	                	 $.ajax({
    	                		   type: "POST",
    	                		   url: "modifyOrg",
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
//            updateMeuns(row);
//        }
    }
    function updateMeuns(row){
    	$('#update_tab_from input[name=id]').val(row.id);
    	$('#update_tab_from input[name=orgName]').val(row.orgName);
    	$('#update_tab_from input[name=orgFlag]').val(row.orgFlag);
    	$('#update_tab_from textarea[name=orgDesc]').val(row.orgDesc);
    	$('#update_tab_from select[name=parentId]').find("option[value="+row.parentId+"-"+row.parentName+"]").attr("selected",true);
    	$('#update_tab_from select[name=parentId]').pqSelect({
              checkbox: false, //adds checkbox to options   
              width:'270px'
          }).pqSelect('close');  
    }
    //append Row
    function tab_addRow() {
        var $frm = $("form#crud-form");
        $frm.find("input").val("");
        var addMessage = '<div class="dialog-cls"><form id="add_tab_from" class="form-horizontal" role="form">'+$("#org_dialog").html()+'</form></div>';
        bootbox.dialog({ 
        	title: "新增组织", 
        	message:addMessage,
        	animate: true,
	    	buttons: {
	             success: {
	                 label: "保存",
	                 className: "btn btn-sm btn-success",
	                 callback: function () {
 	                	 $.ajax({
	                		   type: "POST",
	                		   url: "addOrg",
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
        		  title: "删除组织信息",
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
	                		   url: "deleteOrg",
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
});    