//var CTX_PATH='<%=request.getContextPath()%>';
    //选择组织机构节点
    function onClick(event, treeId, treeNode, clickFlag){
    	alert(1);
      /*   alert(getSelectedNode().orgName);
        alert(getSelectedNode().id); 
        var id = (getSelectedNode().id);*/
//        var courtName=(getSelectedNode().orgName);
//        var courtId = (getSelectedNode().id);
        //alert(courtId+"##"+courtName);
       // parent.document.getElementById("court").value = courtName;
//        parent.document.getElementById("orgId").value = courtId;
//        parent.document.getElementById("orgName").value = courtName;
//        window.close();
        /* if(confirm("确认选择该法院么？")) {
             var courtName=(getSelectedNode().orgName);
             var courtId = (getSelectedNode().id);
             parent.document.getElementById("court").value = courtName;
             parent.document.getElementById("courtId").value = courtId;
	         window.close();
             
      		} */
    }
        
    function OnRightClick(){
                
    }
            
    function beforeAsync(){
        //alert('开始异步加载...');
        
    }
    
    function onAsyncSuccess(){
//    	${courtId}
    	
    }
    
    function onAsyncError(){
        alert('加载错误...');
    }
    
    function getZTree(){
        var zTree = $.fn.zTree.getZTreeObj("zTree");
        return zTree;
    }
    
    //获取节点
    function getSelectedNode(){
        var nodes = getZTree().getSelectedNodes();
        if(!nodes){
            return;
        }
        var node=nodes[0];
        return node;
    }
    
    var setting = {
    	 	view: {
    			showLine: false,
    			showIcon: false,
    			selectedMulti: false,
    			dblClickExpand: false,
    			addDiyDom: addDiyDom
    		}, 
            async:{
                enable: true,
                url: _getNodeLoadUrl(),
                autoParam:["id"]
            },
            callback: {
                onClick: onClick,
                onRightClick: OnRightClick,
                beforeAsync: beforeAsync,
                onAsyncSuccess: onAsyncSuccess,
                onAsyncError: onAsyncError
            },
            data:{
                keep:{
                    leaf:false,
                    parent:false
                },
                key:{
                    checked:"checked",
                    children:"children",
                    name:"orgName",
                    title:"",
                    url:"url"
                },
                simpleData:{
                    enable:true,
                    idKey:"id",
                    pIdKey:"parentId",
                    rootPId:null
                }
            }
    };
    
  function addDiyDom(treeId, treeNode) {
		var spaceWidth = 5;
		var switchObj = $("#" + treeNode.tId + "_switch"),
		icoObj = $("#" + treeNode.tId + "_ico");
		switchObj.remove();
		icoObj.before(switchObj);

		if (treeNode.level > 1) {
			var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
			switchObj.before(spaceStr);
		}
	} 
    
    $(document).ready(function(){
    	var treeObj = $("#zTree");
        $.fn.zTree.init(treeObj, setting);
        
    	treeObj.hover(function () {
			if (!treeObj.hasClass("showIcon")) {
				treeObj.addClass("showIcon");
			}
		}, function() {
			treeObj.removeClass("showIcon");
		});
    });
    function _getNodeLoadUrl(){
        var urlTreeLoad=CTX_PATH+'/org/orgTree';
        return urlTreeLoad;
    }
  /*   function check() {
  	    if(confirm("确认选择这条做为回复吗？")) {
  	    	 alert(getSelectedNode().orgName);
             alert(getSelectedNode().id);
             var courtName=(getSelectedNode().orgName);
	                window.opener.document.getElementById("court").value = courtName;
	        window.close();
      }
  } */