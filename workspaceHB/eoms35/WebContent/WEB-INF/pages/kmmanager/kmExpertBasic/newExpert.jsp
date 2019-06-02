<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<script type="text/javascript" src="${app}/scripts/widgets/TabPanel.js"></script>

<%request.setAttribute("userId", request.getParameter("userId")); %>

<script type="text/javascript">
    var _tabs;
    Ext.onReady(function(){
        var tabConfig = {
            items:[
                {id:'sheet-info',  text:'基本资料', url:'${app}/kmmanager/kmExpertBasics.do?method=edit&userId=${userId}', isIframe : true},
                {id:'sheet-edu',   text:'教育情况', url:'${app}/kmmanager/kmExpertEdus.do?method=search&userId=${userId}', isIframe : true},
                {id:'sheet-exp',   text:'工作情况', url:'${app}/kmmanager/kmExpertExps.do?method=search&userId=${userId}', isIframe : true},
                {id:'sheet-train', text:'培训管理', url:'${app}/kmmanager/kmExpertTrains.do?method=search&userId=${userId}', isIframe : true},
                {id:'sheet-cet',   text:'证书管理', url:'${app}/kmmanager/kmExpertCets.do?method=search&userId=${userId}', isIframe : true},
                {id:'sheet-com',   text:'技术交流竞赛表彰', url:'${app}/kmmanager/kmExpertComs.do?method=search&userId=${userId}', isIframe : true},
                {id:'sheet-photo', text:'头像照片', url:'${app}/kmmanager/kmExpertPhoto.do?method=uploadPhoto&userId=${userId}', isIframe : true}                
            ]
        };

        _tabs = new eoms.TabPanel('sheet-detail-page', tabConfig);

        init();
    });
    
    function doCheck(id,bCheck){
        var item = _tabs.getActiveTab();
        var selfItem = _tabs.getTab(id);
        if(item==selfItem){
            _tabs.activate("sheet-info");
        }
        	
        if(bCheck){
            _tabs.tabPanel.hideTab(id);
        }
        else{
            _tabs.unhideTab(id);
        }
    }

    function disableTab(id){
        _tabs.tabPanel.disableTab(id);
    }
		
    function enableTab(id){
        _tabs.tabPanel.enableTab(id);
    }
		
    function disableAllTab(){
        _tabs.tabPanel.disableTab("sheet-edu");
        _tabs.tabPanel.disableTab("sheet-exp");
        _tabs.tabPanel.disableTab("sheet-train");
        _tabs.tabPanel.disableTab("sheet-cet");
        _tabs.tabPanel.disableTab("sheet-com");
        _tabs.tabPanel.disableTab("sheet-photo");
    }
		
    function enableAllTab(){
        _tabs.tabPanel.enableTab("sheet-edu");		
		_tabs.tabPanel.enableTab("sheet-exp");
		_tabs.tabPanel.enableTab("sheet-train");
		_tabs.tabPanel.enableTab("sheet-cet");
		_tabs.tabPanel.enableTab("sheet-com");
		_tabs.tabPanel.enableTab("sheet-photo");
    }

    function setUserid(userId){
        window.navigate("${app}/kmmanager/kmExpertBasics.do?method=newExpert&userId="+userId);
    }
		
    function init(){
        var userId = '${userId}';
        if(userId == ''){
            disableAllTab();
        }else{
            enableAllTab();
        }
    }
</script>

<div id="sheet-detail-page"></div>

<br/>

<!--  
<input type="checkBox" id="checkEdu"   onclick="doCheck('sheet-edu',  this.checked)"/> 隐藏教育背景 &nbsp&nbsp
<input type="checkBox" id="checkExp"   onclick="doCheck('sheet-exp',  this.checked)"/> 隐藏工作经验&nbsp&nbsp
<input type="checkBox" id="checkTrain" onclick="doCheck('sheet-train',this.checked)"/> 隐藏培训管理&nbsp&nbsp
<input type="checkBox" id="checkCet"   onclick="doCheck('sheet-cet',  this.checked)"/> 隐藏证书管理&nbsp&nbsp
<input type="checkBox" id="checkCom"   onclick="doCheck('sheet-com',  this.checked)"/> 隐藏技术交流竞赛表彰&nbsp&nbsp
-->
<%@ include file="/common/footer_eoms.jsp"%>
