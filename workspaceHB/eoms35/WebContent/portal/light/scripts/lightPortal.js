/**  
  *  Copyright (c) 2006 Jianmin Liu <liujianmin@gmail.com>
  *
  *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
  *  file except in compliance with the License. You may obtain a copy of the License at
  *
  *         http://www.apache.org/licenses/LICENSE-2.0
  *
  *  Unless required by applicable law or agreed to in writing, software distributed under the
  *  License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
  *  either express or implied. See the License for the specific language governing permissions
  *  and limitations under the License.
  **/
  
/** 
 *Light Portal Framework javascript library, version 1.0.0
 **/
 
//------------------------------------------------------------  light.js
/***whole varible :true:lock;false:unlock  add by zhoucz 20070417***/
var popup = false;

var Light = {
  			Version: '1.0.0'
  		   ,ricoVersion: Rico.Version
  		   ,_ON: "LightPortalOn"
  		   ,_DEFAULT_CONTEXT_PATH:"lightPortal"   		   
  		   ,_USER_ID: "LightPortalUserId"
  		   ,_LOGINED_USER_ID: "LightPortalLoginedUserId"
           ,_REMEMBERED_USER_ID: "LightPortalRememberedUserId"
           ,_REMEMBERED_USER_PASSWORD: "LightPortalRememberedUserPassword"
           ,_IS_SIGN_OUT: "LightPortalIsSignOut"
  		   ,_USER_LOCALE: "LightPortalUserLocale" 
  		   ,_CURRENT_TAB: "LightPortalCurrentTab"		   
  		   ,_PC_PREFIX: "portletContent_"
  		   ,_VIEW_MODE:"view"
  		   ,_EDIT_MODE:"edit"
  		   ,_HELP_MODE:"help"
  		   ,_CONFIG_MODE:"config"
  		   ,_NORMAL_STATE:"normal"
  		   ,_MINIMIZED_STATE:"minimized"
  		   ,_MAXIMIZED_STATE:"maximized"
  		   ,maxZIndex: 1
		   ,checkLoginRequest:"checkLogin.lp"
  		   ,getPortalRequest:"getPortal.lp"
  		   ,getPortalTabsByUserRequest:"/getPortalTabsByUser.lp"
  		   ,getPortletsByTabRequest:"/getPortletsByTab.lp"
  		   ,changeTitle:"/changeTitle.lp"
  		   ,changeLocale:"/changeLocale.lp"
  		   ,lookAndFeelRequest:"/lookAndFeel.lp"
  		   ,changeTheme:"/changeTheme.lp"
  		   ,addTabRequest:"/addTab.lp"
  		   ,editTabRequest:"/editTab.lp"
  		   ,addContentRequest:"/addContent.lp"
  		   ,loginRequest:"/login.lp"
  		   ,logoutRequest:"/logout.lp"
  		   ,signUpRequest:"/signUp.lp"
  		   ,profileRequest:"/profile.lp"
  		   ,deletePortletRequest:"/deletePortlet.lp"
  		   ,deleteTabRequest:"/deleteTab.lp"
           ,changePositionRequest:"/changePosition.lp" 
           ,countLightRequest:"/countLight.lp"
           ,getRssDesc:"/getRssDesc.lp" 
           ,uploadOpml:"/uploadOpml.lp"
		   ,getUserDetail:"/getUserDetail.lp"
	       ,chatWithBuddy:"/chatWithBuddy.lp"	
           ,deleteBuddy:"/deleteBuddy.lp"
           ,listenServer:"/listenServer.lp"	   
  		   ,portal: null  		  
           ,serverError: false
		   /** modified by mios 070329 >> **/
		   ,_THEME:"master"
		   /** modified by mios 070329 << **/
}

Light.supportLocale = new Array(
	new Array("English", "en"),
	new Array("Simple Chinese","zh_CN")
);

window.onload = function(){
	Light.refreshPortal();
}

Light.refreshPortal = function() {
   //if(Light.getCookie(Light._ON) == "on"){
   //alert("85");
		Light.switchPortal();
   //}
}

Light.switchPortal = function() {  

   if(Light.portal == null){
	 	Light.portal = new LightPortal(); 
	 	Light.deleteCookie(Light._LOGINED_USER_ID);//20070328 zhoucz    	 	
	 	Light.portal.turnOn();
   }else{
   	if(Light.portal.turnedOn){
       Light.portal.turnOff();
    }else{
       Light.portal.turnOn();
    }
   }
 }   

Light.setCookie = function (name, value, expires, path, domain, secure)
{
    document.cookie= name + "=" + escape(value) +
        ((expires) ? "; expires=" + expires.toGMTString() : "") +
        ((path) ? "; path=" + path : "") +
        ((domain) ? "; domain=" + domain : "") +
        ((secure) ? "; secure" : "");
}

Light.getCookie = function (name)
{
    var dc = document.cookie;
    var prefix = name + "=";
    var begin = dc.indexOf("; " + prefix);
    if (begin == -1)
    {
        begin = dc.indexOf(prefix);
        if (begin != 0) return null;
    }
    else
    {
        begin += 2;
    }
    var end = document.cookie.indexOf(";", begin);
    if (end == -1)
    {
        end = dc.length;
    }
    return unescape(dc.substring(begin + prefix.length, end));
}

Light.deleteCookie = function(name, path, domain)
{
//alert("133 delete");
    if (Light.getCookie(name))
    {
        document.cookie = name + "=" + 
            ((path) ? "; path=" + path : "") +
            ((domain) ? "; domain=" + domain : "") +
            "; expires=Thu, 01-Jan-70 00:00:01 GMT";
    }
}

Light.getPortletById = function(id) {
  var portletIds = id.split("_");
  var tIndex = portletIds[1];      
  var position = portletIds[2];
  var index = portletIds[3];
  var portlet = Light.portal.tabs[tIndex].portlets[position][index];
  return portlet;
}

Light.execute = function(id,pForm,action,actionName,parameter,mode,param) {
  var aparams = new Array();
  var vContainer = document.getElementById(id);
  var forms = vContainer.getElementsByTagName("form");
  if(forms != null && forms.length > 0)
     pForm = forms[0];
  if(pForm != null && pForm.elements != null){
     for(var i = 0; i < pForm.elements.length; i++) {        
		var aName = pForm.elements[i].name;
		var aValue = pForm.elements[i].value;
		if(!aName){
		  var len =  pForm.elements[i].length;
		  for(var j = 0; j < len; j++) {
				if(pForm.elements[i][j].checked) {
				   aName = pForm.elements[i][j].name;
				   aValue = pForm.elements[i][j].value;
				}
			}
		}
		if(aValue != null && aValue.length > 0){
		   if(actionName == null)		   
		      aparams.push("\""+aName+"="+encodeURIComponent(aValue)+"\"");		 
		   else{
		      if(pForm.elements[i].type != "button" && 
                 pForm.elements[i].type != "submit")
                 aparams.push("\""+aName+"="+encodeURIComponent(aValue)+"\"");
              else{
                 if(actionName == aName)
                    aparams.push("\""+aName+"="+encodeURIComponent(aValue)+"\"");		 
              }
          }
	    }
     }
  }
  var portlet = Light.getPortletById(id); 
  var sendRequest = "ajaxEngine.sendRequest(\""+portlet.request+"\""
  				   +", \"responseId="+id+"\""
  				   +", \"tabId="+Light.portal.tabs[portlet.tIndex].tabServerId+"\""
  				   +", \"portletId="+portlet.serverId+"\""
  				   ;
  if(portlet.parameter.length > 0)
      	sendRequest += ", \""+portlet.parameter+"\"";
  var vMode = null;
  if(document.mode != null) 
     vMode = document.mode;  		
  else if(mode != Light._VIEW_MODE)
     vMode = mode;
  else 
     vMode = Light._VIEW_MODE
  sendRequest += ", \"mode="+vMode+"\""; 
  if(action != null  && action.length > 0){
  	    sendRequest += ", \"action="+action+"\"";
            sendRequest += ", \"webwork.portlet.action=/"+vMode+"/"+action+"\"";
  }
  if(parameter != null && parameter.length > 0){
  	    sendRequest += ", \"parameter="+parameter+"\"";
  } 
  for(var i = 0; i < aparams.length; i++) {
  	sendRequest += ", "+aparams[i];
  }	
  var serverParam = param.split(";");		
   for(var i = 0; i < serverParam.length; i++) {
  	if(serverParam[i].length > 0) 
           sendRequest += ", \""+serverParam[i]+"\"";
  }
  sendRequest += ");";
  eval(sendRequest);
}

//------------------------------------------------------------ lightPortal.js
LightPortal= function () {
  this.layout={
	  	normalTop: 60,
	  	containerLeft: 0,
	  	containerTop : 0, 
	  	bodyLeft : 0,
	  	bodyTop : -10,
	  	footerLeft :10,
	    maxLeft  : 10,
	    maxTop   : 60, 
	    //maxWidth : 1024,
	    maxWidth : "100%",//0315 by zhoucz
	    maxHeight : "100%",
	    minHeight : 40 	    
  }
  this.tabs= new Array();  
  this.turnedOn= false;   
  //this.layout.maxHeight = document.body.offsetHeight;  
}

LightPortal.prototype.turnOn = function(){ 
   this.turnOnLoginUser();
}

LightPortal.prototype.turnOnLoginUser = function(){ 
     var userId = Light.getCookie(Light._LOGINED_USER_ID); 
     Light.deleteCookie(Light._LOGINED_USER_ID);//20070328 zhoucz
     var opt = {
    	method: 'post',
    	postBody: 'userId='+userId,
    	// Handle successful response
    	onSuccess: function(t) {
        	Light.portal.responseTurnOnLoginUser(t);
    	},
    	// Handle 404
    	on404: function(t) {
        	alert('Error 404: location "' + t.statusText + '" was not found.');
    	},
    	// Handle other errors
    	onFailure: function(t) {
        	alert('Error ' + t.status + ' -- ' + t.statusText);
    	}
     }
     new Ajax.Request(Light.checkLoginRequest, opt);        
}

LightPortal.prototype.responseTurnOnLoginUser = function(t){
  var userId = t.responseText;
  if(userId != "-1"){
    Light.setCookie(Light._LOGINED_USER_ID,userId);//zhoucz 20070328
    this.turnOnUser(userId);
  }else{//20070325 by zhoucz start
    this.turnOnRememberLoginUser();
    Light.setCookie(Light._LOGINED_USER_ID,null);
  //zhoucz end
  }
}

LightPortal.prototype.turnOnRememberLoginUser = function(){  
   var userId = Light.getCookie(Light._REMEMBERED_USER_ID);
   var password = Light.getCookie(Light._REMEMBERED_USER_PASSWORD);
   if( userId != null && userId != "" && password != null && password != "" 
   && (Light.getCookie(Light._IS_SIGN_OUT) == null || Light.getCookie(Light._IS_SIGN_OUT) == "")){
       var params = "userId="+userId  
  	          +"&password="+password;
       new Ajax.Request(Light.portal.contextPath+Light.loginRequest, {parameters:params,   onSuccess:Light.portal.responseTurnOnRememberLoginUser});   
   }else{
      this.turnOnRememberUser();//20070325 by zhoucz
   }
}

LightPortal.prototype.responseTurnOnRememberLoginUser = function(t){
  var userId = t.responseText;
  if(userId != "-1" && userId != "-2"){
    Light.setCookie(Light._LOGINED_USER_ID,userId);//20070328 zhoucz
    Light.deleteCookie(Light._CURRENT_TAB);     
    var opt = {
    method: 'post',
    postBody: 'userId='+userId,
    // Handle successful response
    onSuccess: function(t) {
        Light.portal.responseTurnOn(t);
    },
    // Handle 404
    on404: function(t) {
        alert('Error 404: location "' + t.statusText + '" was not found.');
    },
    // Handle other errors
    onFailure: function(t) {
        alert('Error ' + t.status + ' -- ' + t.statusText);
    }
   }
   new Ajax.Request(Light.getPortalRequest, opt);   
  }else{
    Light.deleteCookie(Light._REMEMBERED_USER_ID);
    Light.deleteCookie(Light._REMEMBERED_USER_PASSWORD);
    //alert("Error: The User doesn't exist!");
    this.turnOnRememberUser();//20070325 by zhoucz
  }
}

LightPortal.prototype.turnOnRememberUser = function(){  
  //var userId = Light.getCookie(Light._USER_ID);
  var userId = "role_public";//zhoucz 20070328
   if(userIds != null && userIds != 'null'){
  	userId = userIds;
  }
   Light.deleteCookie(Light._LOGINED_USER_ID);
   Light.deleteCookie(Light._CURRENT_TAB);
  this.turnOnUser(userId);
}

LightPortal.prototype.turnOnUser = function(userId){  
//CAS for modify this zhoucz start
    if(userId !='role_public'&& userIds != null && userIds != 'null'){
    	userId = userIds;
    }
//zhoucz end
  var opt = {
    method: 'post',
    postBody: 'userId='+userId,
    // Handle successful response
    onSuccess: function(t) {
    	if(userId !='role_public'&& userIds != null && userIds != 'null'){
    		Light.setCookie(Light._LOGINED_USER_ID,userId); //20070403 zhoucz for CAS
    	}	
        Light.portal.responseTurnOn(t);
    },
    // Handle 404
    on404: function(t) {
        alert('Error 404: location "' + t.statusText + '" was not found.');
    },
    // Handle other errors
    onFailure: function(t) {
        alert('Error ' + t.status + ' -- ' + t.statusText);
    }
   }
   new Ajax.Request(Light.getPortalRequest, opt);   
}

LightPortal.prototype.responseTurnOn = function(t){
      var params = t.responseText.split(",");
      Light.portal.contextPath = params[0]; 
      if(Light.portal.contextPath.length == 0)
         Light.portal.contextPath= Light._DEFAULT_CONTEXT_PATH;
      Light.portal.title=params[1];       
      Light.portal.allowLookAndFeel=true;      
      Light.portal.allowLayout=true;
      Light.portal.allowAddTab=true;
      Light.portal.allowAddContent=true;
      Light.portal.allowSignIn=true;
      Light.portal.allowTurnOff=false;
      Light.portal.allowChangeLocale=false;
      if(params[2] == 0) Light.portal.allowLookAndFeel=false;
      if(params[3] == 0) Light.portal.allowLayout=false;
      if(params[4] == 0) Light.portal.allowAddTab=false;
      if(params[5] == 0) Light.portal.allowAddContent=false;
      if(params[6] == 0) Light.portal.allowSignIn=false;
      if(params[7] == 0) Light.portal.allowTurnOff=false;
      if(params[8] == 0) Light.portal.allowChangeLocale=false;
      Light.locale = params[9];
      Light.portal.bgImage =  params[10];
      Light.portal.newBgImage = "";  
      Light.portal.headerImage =  params[11]; 
      Light.portal.newHeaderImage="";  
      Light.portal.headerHeight =  params[12];  
      //Light.portal.headerHeight =  "60";  //zhoucz test
      Light.portal.fontSize =  params[13];  
      this.container = this.createPortalContainer();
      this.header = this.createPortalHeader(null);  
      this.footer = this.createPortalFooter();
      this.container.appendChild(this.header);
	  this.body = this.createPortalBody();
      this.container.appendChild(this.body);
	  this.container.appendChild(this.footer);
	  document.body.appendChild(this.container);
	  Light.setCookie(Light._ON,"on");
	  this.turnedOn= true;
	  this.getPortalTabsByUser();
}

LightPortal.prototype.getPortalTabsByUser = function(){
   var opt = {
    method: 'post',
    postBody: '',
    // Handle successful response
    onSuccess: function(t) {
        Light.portal.responseGetPortalTabsByUser(t);
    },
    // Handle 404
    on404: function(t) {
        alert('Error 404: location "' + t.statusText + '" was not found.');
    },
    // Handle other errors
    onFailure: function(t) {
        alert('Error ' + t.status + ' -- ' + t.statusText);
    }
   }
   new Ajax.Request(Light.portal.contextPath+Light.getPortalTabsByUserRequest, opt);
}

LightPortal.prototype.responseGetPortalTabsByUser = function(t){  
	  var defaultTab = 0;
      var responseText = t.responseText; 
      var portalTabs = responseText.split(";");
      for(var i=0;i<portalTabs.length;i++){ 
          var vPortalTab = eval(portalTabs[i]);         
          vPortalTab.open(vPortalTab);
          this.tabs[i]=vPortalTab;   
          if(vPortalTab.defaulted)
             defaultTab = i;       
      }      
      if(Light.getCookie(Light._CURRENT_TAB) != null && Light.getCookie(Light._CURRENT_TAB) != "" && Light.getCookie(Light._CURRENT_TAB) < portalTabs.length){
      	  defaultTab = Light.getCookie(Light._CURRENT_TAB);
      }
      this.tabs[defaultTab].focus(); 
      this.tabs[defaultTab].refresh(); 
}


LightPortal.prototype.turnOff = function(){
   this.container.removeChild(this.header);
   this.container.removeChild(this.body);
   this.container.removeChild(this.footer);
   document.body.removeChild(this.container);
   
   Light.deleteCookie(Light._ON);
   Light.deleteCookie(Light._CURRENT_TAB);
   for(var i=0;i<this.tabs.length;i++){
      for(var j=0;j<this.tabs[i].portlets.length;j++){
   	      this.tabs[i].portlets[j]= new Array();
   	  }
   }
   this.tabs = new Array();
   this.turnedOn= false;
}

LightPortal.prototype.GetFocusedTabId = function(){  
	var tabList = document.getElementById('tabList');
	for (i=0; i < tabList.childNodes.length; i++){
		if (tabList.childNodes[i] && tabList.childNodes[i].tagName == "LI" ){
            if (tabList.childNodes[i].getAttribute('tabColor') + "current" == tabList.childNodes[i].className){
                return tabList.childNodes[i].getAttribute('id');
			}
		}
	}
}

/** added by mios for make it top 070403 >> **/
LightPortal.prototype.loadPortlet = function(portlet,pars){
	ajaxEngine.sendRequest(portlet.request, {parameters: pars,onComplete:makeItTop});
	
	function makeItTop(){
     var strTemp = "<iframe id=\"topiframe\" src=\"javascript:false\" style=\"position:absolute; visibility:inherit;";
     strTemp += "top:0px;left:0px;width:expression(parentElement.offsetWidth); height:expression(parentElement.offsetHeight);";
     strTemp += "z-index:-1;filter=\'progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)\';\"></iframe>";
     portlet.container.innerHTML += strTemp;
   }
}
/** added by mios for make it top 070403 << **/

LightPortal.prototype.changeTheme = function(){
 	var currentTabId = this.GetFocusedTabId();
 	var index = currentTabId.substring(8,currentTabId.length);//tab id perfix tab_page
 	if(popup){
		//popup = false;
	 }else{ 
	    popup = true;	
		var vPortlet = new PortletPopupWindow(index,0,11,150,600,LightResourceBundle._MENU_LOOK_AND_FEEL,"","","themePortlet",Light.portal.contextPath+"/themePortlet.lp",true,false,false,false,false,false,0,false,'','','',"");    
	    this.tabs[index].rePositionAll();
	    ajaxEngine.registerRequest(vPortlet.request,Light.portal.contextPath+vPortlet.requestUrl);
	    ajaxEngine.registerAjaxElement(Light._PC_PREFIX+index+"_"+vPortlet.position+"_"+vPortlet.index);
	    
	    var pars = "responseId="+Light._PC_PREFIX+index+"_"+vPortlet.position+"_"+ vPortlet.index+"&"+
	    "tabId="+this.tabs[index].tabServerId;    
	    this.loadPortlet(vPortlet,pars);
    }
}



LightPortal.prototype.editTab = function(){
 	var currentTabId = this.GetFocusedTabId();
 	var index = currentTabId.substring(8,currentTabId.length);//tab id perfix tab_page
 	if(popup){
	//	popup = false;
	  }else{
	  popup = true;
		var vPortlet = new PortletPopupWindow(index,0,11,280,400,LightResourceBundle._MENU_LAYOUT,"","","tabPortlet",Light.portal.contextPath+"/tabPortlet.lp",true,false,false,false,false,false,0,false,'','','',"");    
	    this.tabs[index].rePositionAll();
	    ajaxEngine.registerRequest(vPortlet.request,Light.portal.contextPath+vPortlet.requestUrl);
	    ajaxEngine.registerAjaxElement(Light._PC_PREFIX+index+"_"+vPortlet.position+"_"+vPortlet.index);
	    
	    var pars = "responseId="+Light._PC_PREFIX+index+"_"+vPortlet.position+"_"+ vPortlet.index +
	    		   "&" + "tabId="+this.tabs[index].tabServerId;
	    this.loadPortlet(vPortlet,pars); 
	 }   
}

LightPortal.prototype.addTab = function(){
 	var currentTabId = this.GetFocusedTabId();
 	var index = currentTabId.substring(8,currentTabId.length);//tab id perfix tab_page
 	if(popup){
	//	popup = false;
	 }else{
	 popup = true;
		var vPortlet = new PortletPopupWindow(index,0,11,280,400,LightResourceBundle._MENU_ADD_TAB,"","","tabPortlet",Light.portal.contextPath+"/tabPortlet.lp",true,false,false,false,false,false,0,false,'','','',"");       
	    this.tabs[index].rePositionAll();
	    ajaxEngine.registerRequest(vPortlet.request,Light.portal.contextPath+vPortlet.requestUrl);
	    ajaxEngine.registerAjaxElement(Light._PC_PREFIX+index+"_"+vPortlet.position+"_"+vPortlet.index);
	    
	    var pars = "responseId="+Light._PC_PREFIX+index+"_"+vPortlet.position+"_"+ vPortlet.index;
	    this.loadPortlet(vPortlet,pars);
    }
}

LightPortal.prototype.addContent = function(){
 	var currentTabId = this.GetFocusedTabId();
 	var index = currentTabId.substring(8,currentTabId.length);//tab id perfix tab_page
 	if(popup){
	//	popup = false;
	 }else{
	 popup = true;
		var vPortlet = new PortletPopupWindow(index,0,11,280,300,LightResourceBundle._MENU_ADD_CONTENT,"","","contentPortlet",Light.portal.contextPath+"/contentPortlet.lp",true,false,false,false,false,false,0,false,'','','',"");
	    this.tabs[index].rePositionAll();
	    ajaxEngine.registerRequest(vPortlet.request,Light.portal.contextPath+vPortlet.requestUrl);
	    ajaxEngine.registerAjaxElement(Light._PC_PREFIX+index+"_"+vPortlet.position+"_"+vPortlet.index);
	    
	    var pars = "responseId="+Light._PC_PREFIX+index+"_"+vPortlet.position+"_"+ vPortlet.index + "&" +
	    		   "tabId="+this.tabs[index].tabServerId;
	    this.loadPortlet(vPortlet,pars);
    }
}

LightPortal.prototype.login = function(){
 	var currentTabId = this.GetFocusedTabId();
 	var index = currentTabId.substring(8,currentTabId.length);//tab id perfix tab_page
 	 if(popup){
	//	popup = false;
	  }else{
	  popup = true;
		var vPortlet = new PortletPopupWindow(index,0,11,280,400,LightResourceBundle._MENU_SIGN_IN,"","","userPortlet",Light.portal.contextPath+"/userPortlet.lp",true,false,false,false,false,false,0,false,'','','',"");    
	    this.tabs[index].rePositionAll();
	    ajaxEngine.registerRequest(vPortlet.request,Light.portal.contextPath+vPortlet.requestUrl);
	    ajaxEngine.registerAjaxElement(Light._PC_PREFIX+index+"_"+vPortlet.position+"_"+vPortlet.index);
	    
	    var pars = "responseId="+Light._PC_PREFIX+index+"_"+vPortlet.position+"_"+ vPortlet.index + "&" +
	    		   "tabId="+this.tabs[index].tabServerId;
	    		   
	    this.loadPortlet(vPortlet,pars);
    }
}

LightPortal.prototype.logout = function(){
   Light.deleteCookie(Light._LOGINED_USER_ID);
   Light.deleteCookie(Light._CURRENT_TAB); 
   var date = new Date();      
   date.setFullYear(date.getFullYear()+1);
   Light.setCookie(Light._IS_SIGN_OUT,"true");
    
   var opt = {
    method: 'post',
    postBody: '',
    // Handle successful response
    onSuccess: function(t) {        
    },
    // Handle 404
    on404: function(t) {
        alert('Error 404: location "' + t.statusText + '" was not found.');
    },
    // Handle other errors
    onFailure: function(t) {
        alert('Error ' + t.status + ' -- ' + t.statusText);
    }
   }
   new Ajax.Request(Light.portal.contextPath+Light.logoutRequest, opt); 
   window.location.reload( true );
}

LightPortal.prototype.editProfile = function(){
 	var currentTabId = this.GetFocusedTabId();
 	var index = currentTabId.substring(8,currentTabId.length);//tab id perfix tab_page
  	 if(popup){
	//	popup = false;
	  }else{
	  popup = true;	
		var vPortlet = new PortletPopupWindow(index,0,11,280,400,LightResourceBundle._MENU_MY_PROFILE,"","","userPortlet",Light.portal.contextPath+"/userPortlet.lp",true,false,false,false,false,false,0,false,'','','',"");    
	    vPortlet.mode = Light._CONFIG_MODE;
	    this.tabs[index].rePositionAll();
	    ajaxEngine.registerRequest(vPortlet.request,Light.portal.contextPath+vPortlet.requestUrl);
	    ajaxEngine.registerAjaxElement(Light._PC_PREFIX+index+"_"+vPortlet.position+"_"+vPortlet.index);
	    
	    var pars = "responseId="+Light._PC_PREFIX+index+"_"+vPortlet.position+"_"+ vPortlet.index + "&" +
	    		   "tabId="+this.tabs[index].tabServerId + "&" +
	    		   "mode="+Light._CONFIG_MODE;
	    		   
	    this.loadPortlet(vPortlet,pars);
    }
}

LightPortal.prototype.setPortletContent= function(responseId,inHTML){
   var portletIds = responseId.split("_");   
   var tIndex = portletIds[1];
   var position = portletIds[2];
   var index = portletIds[3];
   var i = inHTML.indexOf("<div>");
   inHTML = inHTML.substring(i);
   i = inHTML.indexOf("</response>");
   inHTML = inHTML.substring(0,i);      
   Light.portal.tabs[tIndex].portlets[position][index].container.innerHTML = inHTML;
   Light.portal.tabs[tIndex].portlets[position][index].round();
   Light.portal.tabs[tIndex].rePositionPortlets(Light.portal.tabs[tIndex].portlets[position][index]);
}
   
LightPortal.prototype.responsePortlet = function(responseId){   
   var portletIds = responseId.split("_");   
   var tIndex = portletIds[1];
   var position = portletIds[2];
   var index = portletIds[3];
   Light.portal.tabs[tIndex].portlets[position][index].round();
   Light.portal.tabs[tIndex].rePositionPortlets(Light.portal.tabs[tIndex].portlets[position][index]);		    
}

LightPortal.prototype.createPortalContainer = function (){ 
   var vPortalContainer= document.createElement('div');   
   vPortalContainer.style.position = "absolute";
   vPortalContainer.className = "portal-container";  
   if(this.bgImage.length > 0){ 
      if(this.bgImage != "no")
         vPortalContainer.style.background= "url('"+this.bgImage+"')";
      else
         vPortalContainer.style.background= "#ffffff";  
   }
   vPortalContainer.style.fontSize=12 + parseInt(this.fontSize);
   vPortalContainer.style.width = Light.portal.layout.maxWidth;
   vPortalContainer.style.height = Light.portal.layout.maxHeight;
   vPortalContainer.style.zIndex= ++Light.maxZIndex;
   if (document.all) {	
       vPortalContainer.style.posLeft = Light.portal.layout.containerLeft;
       vPortalContainer.style.posTop = Light.portal.layout.containerTop;           
   }    
   else {        
       vPortalContainer.style.left = Light.portal.layout.containerLeft;
       vPortalContainer.style.top = Light.portal.layout.containerTop;      
   }    
   return vPortalContainer;
}
//20070422 by zhoucz start
function systemManage(s){

s.href = Light.portal.contextPath+"/portal/portalSysManages.html";
return s;
}
//20070422 by zhoucz end
LightPortal.prototype.createPortalHeader = function (tab){
   var vPortalHeader = document.createElement('div');
   vPortalHeader.className = "portal-header"; 
    //alert("651==="+this.headerImage.length);  
   if(this.headerImage.length > 0){ 
      if(this.headerImage != "no")
         vPortalHeader.style.background= "url('"+this.headerImage+"')";
      else{
         if (document.all){
       	     var cssRule = document.styleSheets[0].rules;
	  	 }else {
	      	    var cssRule = document.styleSheets[0].cssRules;
	  	 }
         var background=cssRule[0].style.background;
         vPortalHeader.style.background= background;
      }
   }
   var height = parseInt(this.headerHeight);
   if( height> 0) vPortalHeader.style.height= 80 + parseInt(height);
   
   var vSpan = document.createElement('span');
   vSpan.className = "portal-header-menu-item";
	//20070422 by zhoucz start for Portal System Manage
   if(Light.getCookie(Light._LOGINED_USER_ID)== 'role_admin'){
 	   var varG = document.createElement('a');
	   varG.innerHTML = LightResourceBundle._MENU_SYSTEM_MANAGE;
	   	varG.href = "javascript:void(0)";
	     varG.onclick = function(){
	  	  systemManage(varG);
	    }

	   vSpan.appendChild(varG);
       vSpan.appendChild(getSeparater());  
	}
	//20070422 by zhoucz end
   if(Light.portal.allowLookAndFeel){
	   var varA = document.createElement('a');
	   varA.innerHTML = LightResourceBundle._MENU_LOOK_AND_FEEL;

	   	varA.href = "javascript:void(0)";
	     varA.onclick = function(){
	  	  Light.portal.changeTheme();
	    }

	   vSpan.appendChild(varA);
       vSpan.appendChild(getSeparater());
   }
   
   //if(Light.portal.allowLayout && ( tab == null || tab.tabIsEditable)){//20070403 zhoucz modify	   
   if(Light.portal.allowLayout){	   
	   var varB = document.createElement('a');
	    varB.innerHTML = LightResourceBundle._MENU_LAYOUT;
	    varB.href = "javascript:void(0)";
	    varB.onclick = function(){
	  	Light.portal.editTab();
	   }
	   vSpan.appendChild(varB);
       vSpan.appendChild(getSeparater());
   }
   
   if(Light.portal.allowAddTab){ 	   
	   var varC = document.createElement('a');
	   varC.innerHTML = LightResourceBundle._MENU_ADD_TAB;
	   varC.href = "javascript:void(0)";
	   varC.onclick = function(){
	  	Light.portal.addTab();
	   }
	   vSpan.appendChild(varC);
       vSpan.appendChild(getSeparater());
   }
   //if(Light.portal.allowAddContent && ( tab == null || tab.allowAddContent)){ //20070403 zhoucz modify	 	
   if(Light.portal.allowAddContent){  	   
	   var varD = document.createElement('a');
	   varD.innerHTML = LightResourceBundle._MENU_ADD_CONTENT;
	   varD.href = "javascript:void(0)";
	   varD.onclick = function(){
	  	Light.portal.addContent();
	   }
	   vSpan.appendChild(varD);
       vSpan.appendChild(getSeparater());
   }
   
   if(Light.portal.allowSignIn){	   
	   var varE = document.createElement('a');
       var u = Light.getCookie(Light._LOGINED_USER_ID);
	   //if(Light.getCookie(Light._LOGINED_USER_ID) != null && Light.getCookie(Light._LOGINED_USER_ID) != ""){
	   if(Light.getCookie(Light._LOGINED_USER_ID) != null&& Light.getCookie(Light._LOGINED_USER_ID) != "null"){
		   varE.innerHTML = LightResourceBundle._MENU_SIGN_OUT;
		   varE.href = "javascript:void(0)";
		   varE.onclick = function(){
		  	Light.portal.logout();
		   }
		//zhoucz start zhoucz
		   if(Light.portal.allowSignIn && Light.getCookie(Light._LOGINED_USER_ID) != null && Light.getCookie(Light._LOGINED_USER_ID) != ""){	   
			   var varF = document.createElement('a');
			   varF.innerHTML = LightResourceBundle._MENU_MY_PROFILE;
			   varF.href = "javascript:void(0)";
			   varF.onclick = function(){
			  	Light.portal.editProfile();
			   }
		   	   vSpan.appendChild(varF);
			   vSpan.appendChild(getSeparater());
		   }
		//zhoucz end
	   }else{
	   		
	   	   varE.innerHTML = LightResourceBundle._MENU_SIGN_IN;
		   varE.href = "javascript:void(0)";
		   varE.onclick = function(){
		  	Light.portal.login();
		   }
	   }
	   vSpan.appendChild(varE);
       vSpan.appendChild(getSeparater());
       /*---------20070409 zhoucz add start--------------*/
       if(userName != null && userName !='null'){
       	var varT = document.createElement('a');
       	varT.innerHTML = LightResourceBundle._LOGIN_USER+userName;
       	vSpan.appendChild(varT);
     }  
     /*--------------20070409 zhoucz add end--------------------*/
   }

   if(Light.portal.allowTurnOff){
	   var varF = document.createElement('a');
	   varF.innerHTML = LightResourceBundle._MENU_TURN_OFF;
	   varF.href = "javascript:void(0)";
	   varF.onclick = function(){
	  	Light.switchPortal();
	   }
	   vSpan.appendChild(varF);
   }
   if(Light.portal.allowChangeLocale){
   	var vSpanG = document.createElement('span');
   	var innerHtml = "<select name='psEngine' size='1' class='portlet-form-select'"
                     +" onChange='javascript:changeLocale(this.value)'>";
 
   	for(i=0; i<Light.supportLocale.length; i++){
		if(Light.locale == Light.supportLocale[i][1]){
	  	 innerHtml +="<option value='"+Light.supportLocale[i][1]+"' SELECTED>"
			+Light.supportLocale[i][0]+"</option>";
        	}else{
          	 innerHtml +="<option value='"+Light.supportLocale[i][1]+"'>"
			+Light.supportLocale[i][0]+"</option>";
        	}
   	}					 	
	innerHtml +="</select>";
   	vSpanG.innerHTML = innerHtml;
   	vSpan.appendChild(vSpanG);
   }
   vPortalHeader.appendChild(vSpan);
   vPortalHeader.style.zIndex= ++Light.maxZIndex;     
   return vPortalHeader;
}
/** add by mios 070330 >> **/
function getSeparater(){	   
	var s = document.createElement('span');
	s.className = "portal-header-menu-item-separater";
	s.innerHTML = "|";
	return s;
}
/** add by mios 070330 << **/

LightPortal.prototype.editTitle = function(title){ 
  title.className= 'portal-header-title-edit';
  title.innerHTML= "<input type='text' name='portalTitle' class='portal-header-title-edit' size='24' value=\""+this.title+"\" onchange=\"javascript:Light.portal.saveTitle();\" onblur=\"javascript:Light.portal.saveTitle();\"/>";
  document.all['portalTitle'].focus();
}

LightPortal.prototype.viewTitle = function(title){
  title.className= 'portal-header-title-view';
  this.container.appendChild(this.header);
}

LightPortal.prototype.saveTitle = function(title){
  var title = document.all['portalTitle'].value;
  this.title=title;
  this.container.removeChild(Light.portal.header);
  this.header = this.createPortalHeader(this.tabs[Light.getCookie(Light._CURRENT_TAB)]);
  this.container.insertBefore(this.header,Light.portal.body);
  var params = "title="+encodeURIComponent(title);
  new Ajax.Request(Light.portal.contextPath+Light.changeTitle, {parameters:params});
}

LightPortal.prototype.refreshPortalHeader = function(tab){
	this.container.removeChild(this.header);
	this.header = this.createPortalHeader(tab);
	this.container.appendChild(this.header);
}
LightPortal.prototype.createPortalBody = function (){
   var vBody = document.createElement('div');   
   vBody.id = "portalBody";
   vBody.className = "portal-body"; 
   vBody.style.position = "absolute";
   
   /** modified by mios for delete hr line 070329 >> **/  
   vBody.innerHTML="<div id='tabs' class='portal-tabs' >" +
   		"<ul id='tabList'></ul>" +
   		"</div>" +
   		"<div id='tabPanels' class='portal-tab-panels' ></div>";  
   /** modified by mios 070329 << **/ 
   
   vBody.style.zIndex= ++Light.maxZIndex;
   if (document.all) {	        
       vBody.style.posLeft = this.layout.bodyLeft;
       vBody.style.posTop = this.layout.bodyTop + parseInt(this.headerHeight);         
   }    
   else {               
       vBody.style.left = this.layout.bodyLeft;
       vBody.style.top = this.layout.bodyTop +parseInt(this.headerHeight);         
   } 
   return vBody;
}

LightPortal.prototype.createPortalFooter = function (){


   var vfooter = document.createElement('div');
   vfooter.id = "portalFooter";
   vfooter.className = "portal-footer";
   var innerHtml ="";
   vfooter.innerHTML= innerHtml;


   if (document.all) {	
       vfooter.style.posLeft = Light.portal.layout.footerLeft;  
       vfooter.style.posTop = Light.portal.layout.normalTop;       
   }    
   else {        
       vfooter.style.left = Light.portal.layout.footerLeft;
       vfooter.style.top = Light.portal.layout.normalTop;         
   } 
  vfooter.style.zIndex= ++Light.maxZIndex; 
   
  return vfooter;
}


//------------------------------------------------------------ lightPortalTab.js
LightPortalTab = function (index,tabId,tabServerId, tabLabel, tabURL, tabIsCloseable, tabIsEditable, tabIsMoveable, allowAddContent, tabColor, defaulted, between, widths, portletWindowName) {
   this.index = index;
   this.tabId=tabId;
   this.tabServerId = tabServerId;
   this.tabLabel = tabLabel;
   this.tabURL=tabURL;
   this.tabIsCloseable = tabIsCloseable;
   this.tabIsEditable = tabIsEditable;
   this.tabIsMoveable  = tabIsMoveable
   this.allowAddContent = allowAddContent;
   this.tabColor = tabColor;
   if(tabColor == "null") this.tabColor = "red";
   this.defaulted = defaulted;
   this.between = between;
   this.widths = widths;
   this.portletWindowName = portletWindowName;
   this.portlets = new Array();   
   this.loaded = false;
}

LightPortalTab.prototype.open = function(portalTab){
   // create the tab
   if(this.tabColor==null||this.tabColor==''||this.tabColor=='null')this.tabColor='red';
	var newLabel = document.createElement('span');
	newLabel.setAttribute("id", "tabSpan" + this.tabId);
	newLabel.className = this.tabColor;
    newLabel.setAttribute("tabColor", this.tabColor);
	if (this.tabIsCloseable){
	   var vButton = document.createElement('div');
	   vButton.className = "portal-tab-handle";
	   vButton.innerHTML = this.tabLabel;
	   var clsA = document.createElement('img');
	   clsA.src = Light.portal.contextPath+'/portal/light/images/closeTab.gif';
       clsA.align = "absmiddle";
       clsA.className =  "portal-tab-button";
	   clsA.onclick = function(){
	     portalTab.close();
	   }
	   newLabel.appendChild(vButton);
	   newLabel.appendChild(clsA);
	}
	else{
		newLabel.innerHTML = "<div class=\"portal-tab-handle\">" + this.tabLabel + "</div>";
	}	
	var newTab = document.createElement('li');
	newTab.className = this.tabColor;
	newTab.setAttribute("id", this.tabId);
	newTab.setAttribute("tabId", this.tabId);
	newTab.setAttribute("tabLabel", this.tabLabel);
	newTab.setAttribute("tabColor", this.tabColor);
	newTab.onclick = function () { 	   
	   Light.portal.tabs[portalTab.index].focus(); 
	   Light.portal.tabs[portalTab.index].refresh(); 
	}
	newTab.setAttribute("tabIsCloseable", "0");	
	if (this.tabIsCloseable){
		newTab.setAttribute("tabIsCloseable", "1");
	}
	newTab.setAttribute('isFocused','true');
	newTab.appendChild(newLabel);
    document.getElementById('tabList').appendChild(newTab);
	// create the panel
	var newPanel = document.createElement('div');
	newPanel.setAttribute('id','panel_' + this.tabId);
	newPanel.setAttribute("panelURL", this.tabURL);
	newPanel.setAttribute("tabColor", this.tabColor);
	newPanel.className = this.tabColor + "Panel";	
	/* newPanel.style.display = "none"; */
	document.getElementById('tabPanels').appendChild(newPanel);
}

LightPortalTab.prototype.focus = function(){
    Light.setCookie(Light._CURRENT_TAB,this.index);   
	var currentFocusedTabId = Light.portal.GetFocusedTabId();		
	var tabList = document.getElementById("tabList");
    for (j=0; j < tabList.childNodes.length; j++){
		if (tabList.childNodes[j] && tabList.childNodes[j].tagName == "LI" ){
			var className = tabList.childNodes[j].getAttribute("tabColor");
            if(className == "null") className = "";
            var currentTabId = tabList.childNodes[j].getAttribute("tabId");
			if (currentTabId == this.tabId){
				tabList.childNodes[j].className = className + "current";
                document.getElementById("tabSpan" + tabList.childNodes[j].getAttribute("id")).className = className + "current";
				document.getElementById("panel_" + currentTabId).style.display = "block";
			}
			else{
				tabList.childNodes[j].className = className;
				document.getElementById("tabSpan" + tabList.childNodes[j].getAttribute("id")).className = className;
				document.getElementById("panel_" + currentTabId).style.display = "none";
			}
		}
	}
	
	if (this.tabId != currentFocusedTabId){
		eval("if (window.tabBlur"+currentFocusedTabId+") { tabBlur"+currentFocusedTabId+"(); }");
		eval("if (window.tabFocus"+this.tabId+") { tabFocus"+this.tabId+"(); }");
	}
}

LightPortalTab.prototype.refresh = function(){
	if(!this.loaded){
	   this.getPortletsByTab(this.index);
	   this.loaded = true;
	}else{
	   this.rePositionAll();
	}
	Light.portal.refreshPortalHeader(this);
}

LightPortalTab.prototype.close = function(){
	var lastTabId = "";
	var somethingHasFocus = false;
	
	var closeTab = confirm(LightResourceBundle._COMMAND_CLOSE_TAB);
	if (!closeTab){ // user cancelled close tab
		return;
	}
	var tabId = this.tabServerId;
	/* Remove the tab */
	var tabList = document.getElementById('tabList');
	for (i=0; i < tabList.childNodes.length; i++){
		if (tabList.childNodes[i] && tabList.childNodes[i].tagName == "LI" ){
			if (tabList.childNodes[i].getAttribute('id') == this.tabId){
				tabList.removeChild(tabList.childNodes[i]);
			}
		}
	}

	/* Remove the panel */
	var panelList = document.getElementById('tabPanels');
	for (i=0; i < panelList.childNodes.length; i++){
		if (panelList.childNodes[i] && panelList.childNodes[i].tagName == "DIV" ){
			if (panelList.childNodes[i].getAttribute('id') == "panel_" + this.tabId){
				panelList.removeChild(panelList.childNodes[i]);
			}
		}
	}

	// If we closed the tab that had focus, focus on another tab.
	for (i=0; i < tabList.childNodes.length; i++){
		if (tabList.childNodes[i] && tabList.childNodes[i].tagName == "LI" ){
			lastTabId = tabList.childNodes[i].getAttribute('id');
			if (tabList.childNodes[i].getAttribute('tabColor') + "current" == tabList.childNodes[i].className){
				somethingHasFocus = true;
			}
		}
	}
	
	if (!somethingHasFocus){
	    //tab id prefix is 'tab_page'
		Light.portal.tabs[lastTabId.substring(8,lastTabId.length)].focus();
		if(!Light.portal.tabs[lastTabId.substring(8,lastTabId.length)].loaded){
			Light.portal.tabs[lastTabId.substring(8,lastTabId.length)].refresh();
	    }
	}
   new Ajax.Request(Light.portal.contextPath+Light.deleteTabRequest, {parameters:'tabId='+tabId});  
}

LightPortalTab.prototype.getPortletsByTab = function(tIndex){
   var tabId = this.tabServerId;
   var currentTab = this;
   var opt = {
    method: 'post',
    postBody: 'tabId='+tabId,
    // Handle successful response
    onSuccess: function(t) {
        currentTab.responseGetPortletsByTab(t);
    },
    // Handle 404
    on404: function(t) {
        alert('Error 404: location "' + t.statusText + '" was not found.');
    },
    // Handle other errors
    onFailure: function(t) {
        alert('Error ' + t.status + ' -- ' + t.statusText);
    }
   }
   new Ajax.Request(Light.portal.contextPath+Light.getPortletsByTabRequest, opt); 
}

LightPortalTab.prototype.responseGetPortletsByTab = function(t){    
      var responseText = t.responseText; 
      if(responseText.length <=0){
         return;
      }
      var portlets = responseText.split(";");
      var portletsCount = portlets.length;
      for(var i=0;i<portletsCount;i++){
     	  var vPortlet = null;
	      var newPortlet = "new "+this.portletWindowName+"("+this.index+","+portlets[i]+")";
          vPortlet = eval(newPortlet);
          ajaxEngine.registerRequest(vPortlet.request,Light.portal.contextPath+vPortlet.requestUrl);
          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.index+"_"+vPortlet.position+"_"+vPortlet.index);
          if(vPortlet.allowJS){
	          var pars = "responseId="+Light._PC_PREFIX+this.index+"_"+vPortlet.position+"_"+ vPortlet.index
	          		    +"&tabId="+this.tabServerId
	          		    +"&portletId="+vPortlet.serverId;
	          if(vPortlet.parameter.length > 0)
	          		pars = pars+"&"+ vPortlet.parameter;
			  ajaxEngine.sendRequestAndUpdate(vPortlet.request,Light._PC_PREFIX+this.index+"_"+vPortlet.position+"_"+ vPortlet.index,{evalScripts: true, parameters: pars});
          }else{
	          if(vPortlet.parameter.length > 0){
	                ajaxEngine.sendRequest(vPortlet.request
	          					, "responseId="+Light._PC_PREFIX+this.index+"_"+vPortlet.position+"_"+ vPortlet.index
	                        	,vPortlet.parameter
	                        	, "tabId="+this.tabServerId
	                            , "portletId="+vPortlet.serverId
	                        	);
	          }else{          
	          		ajaxEngine.sendRequest(vPortlet.request
	          					, "responseId="+Light._PC_PREFIX+this.index+"_"+vPortlet.position+"_"+ vPortlet.index
	          					, "tabId="+this.tabServerId
	                            , "portletId="+vPortlet.serverId
	                        	);
	          }
          }
      }         
}

LightPortalTab.prototype.reLayout = function(){
////**************buju need update********************/////
////////
   for(var i=0;i<this.portlets.length;i++) {
       if(this.portlets[i] != null){     
	       for(var j=0;j<this.portlets[i].length;j++) { 
	       	  if(this.portlets[i][j] != null && !this.portlets[i][j].maximized){   
	          	this.portlets[i][j].width = this.widths[i];
			    this.portlets[i][j].left = this.between;
			    for(var k=0;k<this.portlets[i][j].position;k++) {
			        this.portlets[i][j].left += this.widths[k] + this.between;
			    }
	          }
	       }
	   }
   }   
   this.rePositionAll();
}

LightPortalTab.prototype.rePositionAll = function(){   
   for(var i=0;i<this.portlets.length;i++) {
       if(this.portlets[i] != null){    
	       for(var j=0;j<this.portlets[i].length;j++) {   
	       	  if(this.portlets[i][j] != null && !this.portlets[i][j].maximized){
	          	this.rePositionPortlets(this.portlets[i][j]);
	          	break;
	          }
	       }
	    }
   }   
   this.repostionFooter();
}

LightPortalTab.prototype.rePositionPortlets = function(portlet){
   var position = portlet.position;
   var index = portlet.index;
   var height = 0;
   var maxIndex = 0;
   var nullNum = 0;
    for(var i=0;i<this.portlets[position].length;i++) {        
        if(i >= index && this.portlets[position][i] != null && !this.portlets[position][i].maximized){
          this.portlets[position][i].top = Light.portal.layout.normalTop + height + this.portlets[position][i].layout.rowBetween * (i - maxIndex - nullNum);
          this.portlets[position][i].setPosition();
        }
		if(this.portlets[position][i] != null && !this.portlets[position][i].maximized ){
          height += this.portlets[position][i].container.clientHeight;   
        }    
        if(this.portlets[position][i] == null){
          nullNum++;
        }
        if(this.portlets[position][i] != null && this.portlets[position][i].maximized){
          height = this.portlets[position][i].container.clientHeight;     
          maxIndex = i;
          nullNum = 0;
        } 
    }

    this.repostionFooter();
  }
  
LightPortalTab.prototype.repostionFooter = function(){
   var maxHeight = this.getMaxHeight(); 
    if (document.all) {	
       Light.portal.footer.style.posTop = maxHeight;           
   }else {        
       Light.portal.footer.style.top = maxHeight;           
   } 
   if((maxHeight - Light.portal.layout.maxTop) > Light.portal.layout.maxHeight)
      Light.portal.container.style.height = maxHeight - Light.portal.layout.maxTop; 
 }

LightPortalTab.prototype.getMaxHeight = function(){
   var heights = new Array();   
   var maxHeight = 0;
   
   for(var i=0; i<Light.portal.tabs[this.index].portlets.length;i++) { 
      if(Light.portal.tabs[this.index].portlets[i] != null){
	      for(var j=0;j<Light.portal.tabs[this.index].portlets[i].length;j++) {      
		    if(Light.portal.tabs[this.index].portlets[i][j] != null && Light.portal.tabs[this.index].portlets[i][j].maximized ){ 
		       maxHeight = Light.portal.tabs[this.index].portlets[i][j].top + Light.portal.tabs[this.index].portlets[i][j].container.clientHeight;         
		       break;
		    }      
			if(Light.portal.tabs[this.index].portlets[i][j] != null && !Light.portal.tabs[this.index].portlets[i][j].maximized ){
		      heights[i] = Light.portal.tabs[this.index].portlets[i][j].top + Light.portal.tabs[this.index].portlets[i][j].container.clientHeight;   
		    }
		  }
	  }
	  if(maxHeight > 0)
	  	break;                        
   }    
   if(maxHeight == 0){
       for(var i=0; i<heights.length;i++) {
          if(heights[i] > maxHeight)
             maxHeight = heights[i];
       }	   
   }
   maxHeight = maxHeight + 200;   
   return maxHeight;
}

LightPortalTab.prototype.getPortletIndex = function(position){
   var addAfterLast = true;
   var index = 0;
   if(Light.portal.tabs[this.index].portlets[position] != null){
	   for(var i=0;i<Light.portal.tabs[this.index].portlets[position].length;i++) {
		if(Light.portal.tabs[this.index].portlets[position][i] == null){
	           addAfterLast = false;
	           index = i;
	           break;    
	        }
	   }
	   if(addAfterLast) {
	     index = Light.portal.tabs[this.index].portlets[position].length;  
	   }
	}else {
	   Light.portal.tabs[this.index].portlets[position] = new Array();	   
	}

   return index;
 }

LightPortalTab.prototype.showOtherPortlets = function(){
   for(var i=0;i<this.portlets.length;i++) {
       if(this.portlets[i] != null){    
       for(var j=0;j<this.portlets[i].length;j++) {  
          if(this.portlets[i][j] != null){       
             this.portlets[i][j].show();
          }  
       }
       }
   }   
}

LightPortalTab.prototype.hideOtherPortlets = function(){
   for(var i=0;i<this.portlets.length;i++) {    
       if(this.portlets[i] != null){ 
       for(var j=0;j<this.portlets[i].length;j++) {  
          if(this.portlets[i][j] != null && !this.portlets[i][j].maximized){       
             this.portlets[i][j].hide();
          }  
       }
       }
   }      
}

//----------------------------------------------------  lightPortalConfigPortlet.js 

function changeLocale(locale){
   if(Light.getCookie(Light._USER_LOCALE) != null && Light.getCookie(Light._USER_LOCALE) != ""){
      Light.deleteCookie(Light._USER_LOCALE);
   }
   var date = new Date();    
   date.setFullYear(date.getFullYear()+1);
   Light.setCookie(Light._USER_LOCALE,locale,date);
   var params = "locale="+locale;
   new Ajax.Request(Light.portal.contextPath+Light.changeLocale, {parameters:params, onSuccess:responseChangeLocale});    
}
  
function responseChangeLocale(){
   window.location.reload( true );  
}

function changeTheme(id){
  var len =  document.forms['form_'+id]['ptTheme'].length;
  var theme = null;
  for(var i = 0; i < len; i++) {
		if(document.forms['form_'+id]['ptTheme'][i].checked) {
			theme = document.forms['form_'+id]['ptTheme'][i].value;
		}
	}
  var bgImage = null;
  if(Light.portal.newBgImage.length > 0)
     bgImage = Light.portal.newBgImage;
  len = document.forms['form_'+id]['ptBg'].length;
  for(var i = 0; i < len; i++) {
		if(document.forms['form_'+id]['ptBg'][i].checked &&
                   document.forms['form_'+id]['ptBg'][i].value !="more") {
			bgImage = document.forms['form_'+id]['ptBg'][i].value;
		}
	}
  
  var headerImage = null;
  if(Light.portal.newHeaderImage.length > 0)
     headerImage = Light.portal.newHeaderImage;
  len = document.forms['form_'+id]['ptHeader'].length;
  for(var i = 0; i < len; i++) {
		if(document.forms['form_'+id]['ptHeader'][i].checked &&
                   document.forms['form_'+id]['ptHeader'][i].value !="more") {
			headerImage = document.forms['form_'+id]['ptHeader'][i].value;
		}
	}
  var headerHeight = document.forms['form_'+id]['ptHeaderHeight'].value;
  var fontSize = document.forms['form_'+id]['ptFontSize'].value;
  var params="";
  if(theme != null)
     params = "theme="+theme;
  if(bgImage != null){
     if(params.length > 0)
        params = params+"&bgImage="+bgImage;
     else
        params = "bgImage="+bgImage;
  }
  if(headerImage != null){
     if(params.length > 0)
        params = params+"&headerImage="+headerImage;
     else
        params = "headerImage="+headerImage;
  }
  if(headerHeight != null){
     if(params.length > 0)
        params = params+"&headerHeight="+headerHeight;
     else
        params = "headerHeight="+headerHeight;
  }
  if(fontSize != null){
     if(params.length > 0)
        params = params+"&fontSize="+fontSize;
     else
        params = "fontSize="+fontSize;
  }
  if(params.length > 0)
    new Ajax.Request(Light.portal.contextPath+Light.changeTheme, {parameters:params, onSuccess:responseChangeTheme}); 
   
}
  
function responseChangeTheme(){
   window.location.reload( true );  
}

function changeTabColumns(id){
  var columns = document.forms['form_'+id]['ptColumns'].value;
  var portlet = Light.getPortletById(id);
  ajaxEngine.sendRequest(portlet.request
				         , "responseId="+id				         
				         , "columns="+columns
				         );
}

function changeCurrentTabColumns(id){
  var columns = document.forms['form_'+id]['ptColumns'].value;
  var portlet = Light.getPortletById(id);
  ajaxEngine.sendRequest(portlet.request
				         , "responseId="+id	
				         , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId			         
				         , "columns="+columns
				         );
}

function addTab(id){
  var title = document.forms['form_'+id]['ptTitle'].value;
  var portletWindowType = document.forms['form_'+id]['ptWindow'].value;
  var columns = parseInt(document.forms['form_'+id]['ptColumns'].value);
  var between = document.forms['form_'+id]['ptBetween'].value;
  var widths ="";
  for(var i=0;i<columns;i++){
   widths +="&width"+i+"="+document.forms['form_'+id]['ptWidth'+i].value;
  } 
  var closeable = "0";
  if(document.forms['form_'+id]['ptClose'].checked)
     closeable = "1";
  var defaulted = "0";
  if(document.forms['form_'+id]['ptDefault'].checked)
     defaulted = "1";
  var params = "title="+encodeURIComponent(title)
  			  +"&windowType="+portletWindowType
  			  +"&columns="+columns
  			  +widths
  			  +"&between="+between
  			  +"&closeable="+closeable
  			  +"&defaulted="+defaulted
  				;
  new Ajax.Request(Light.portal.contextPath+Light.addTabRequest, {parameters:params, onSuccess:responseAddTab});
  
}  
function responseAddTab(t){    
      if(Light.getCookie(Light._ON) == "on"){ 
         var portalTab = t.responseText;
    	 var vPortalTab = eval(portalTab);         
         vPortalTab.open(vPortalTab);
         Light.portal.tabs[vPortalTab.index]=vPortalTab;
      }   
  //window.location.reload( true );//zhoucz 20070416  
 }  

function editTab(id){

  var title = document.forms['form_'+id]['ptTitle'].value;
  var portletWindowType = document.forms['form_'+id]['ptWindow'].value;
  var columns = parseInt(document.forms['form_'+id]['ptColumns'].value);
  var between = document.forms['form_'+id]['ptBetween'].value;
  var widths ="";
  for(var i=0;i<columns;i++){
   widths +="&width"+i+"="+document.forms['form_'+id]['ptWidth'+i].value;
  } 
  var closeable = "0";
  if(document.forms['form_'+id]['ptClose'].checked)
     closeable = "1";
  var defaulted = "0";
  if(document.forms['form_'+id]['ptDefault'].checked)
     defaulted = "1"; 
  var portlet = Light.getPortletById(id);
  var params = "title="+encodeURIComponent(title)
              +"&tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
  			  +"&windowType="+portletWindowType
  			  +"&columns="+columns
  			  +widths
  			  +"&between="+between
  			  +"&closeable="+closeable
  			  +"&defaulted="+defaulted
  				;
  new Ajax.Request(Light.portal.contextPath+Light.editTabRequest, {parameters:params, onSuccess:responseEditTab}); 
	
  //window.location.reload( true );//zhoucz 20070416
}  
 function responseEditTab(t){
  if(Light.getCookie(Light._ON) == "on"){   
     var currentTabId = Light.portal.GetFocusedTabId();
 	 var index = currentTabId.substring(8,currentTabId.length);//tab id perfix tab_page
     var portalTab = t.responseText.split("-");
      var tabServerId = portalTab[0];
      var tabLabel = portalTab[1];
      var tabUrl = portalTab[2];
      var tabIsCloseable = portalTab[3];
      if(tabIsCloseable == 0)
         tabIsCloseable = false;
      else
         tabIsCloseable = true;
      var tabColor = portalTab[4];
      var tabDefault = portalTab[5];
      if( tabDefault == 1)
          defaultTab = i;
      var tabBetween = parseInt(portalTab[6]);    
      var widths = portalTab[7].split(",");
      var tabWidths = new Array();
      for(var j=0;j<widths.length;j++){
          tabWidths[j]= parseInt(widths[j]);
      }
      Light.portal.tabs[index].between = tabBetween;
      Light.portal.tabs[index].widths = tabWidths;
      Light.portal.tabs[index].reLayout();
  }
} 

function addContentMouseDown(el){
  if(el.className=='')
     el.className='portlet-header';
  else
     el.className='';
}

function addContent(id,name){

  var column = parseInt(document.forms['form_'+id]['pcColumn'].value);   
  var portlet = Light.getPortletById(id);
  var width = document.forms['form_'+id]['columswidth'].value.split(",");
  
  var params = "portletObjectRefName="+name
              +"&tabId="+Light.portal.tabs[portlet.tIndex].tabServerId  			 
  			  +"&column="+column
  			  +"&width="+width[column-1]			  
  				;
  new Ajax.Request(Light.portal.contextPath+Light.addContentRequest, {parameters:params, onSuccess:responseAddContent});     
} 

function responseAddContent(t){
  if(Light.getCookie(Light._ON) == "on"){    
          var currentTabId = Light.portal.GetFocusedTabId();
 		  var index = currentTabId.substring(8,currentTabId.length);//tab id perfix tab_page
		  var newPortlet = "new "+Light.portal.tabs[index].portletWindowName+"("+index+","+t.responseText+")";
		  var widthPortlet = t.responseText.substring(t.responseText.lastIndexOf(",")+1);
		  var portlet = eval(newPortlet);                 
          ajaxEngine.registerRequest(portlet.request,Light.portal.contextPath+portlet.requestUrl);
          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+index+"_"+portlet.position+"_"+portlet.index);
          if(portlet.allowJS){
	          var pars = "responseId="+Light._PC_PREFIX+index+"_"+portlet.position+"_"+ portlet.index
	          		    +"&tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
	          		    +"&portletId="+portlet.serverId
	          		    +"&"+widthPortlet;
	          if(portlet.parameter.length > 0)
	          		pars = pars+"&"+ portlet.parameter;
	          		
			  ajaxEngine.sendRequestAndUpdate(portlet.request,Light._PC_PREFIX+index+"_"+portlet.position+"_"+ portlet.index,{evalScripts: true, parameters: pars});
          }else{
	          if(portlet.parameter.length > 0){
	                ajaxEngine.sendRequest(portlet.request
	          					, "responseId="+Light._PC_PREFIX+index+"_"+portlet.position+"_"+ portlet.index
	                        	,portlet.parameter
	                        	, "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
	                            , "portletId="+portlet.serverId
	                            ,widthPortlet
	                        	);
	          }else{          
	          		ajaxEngine.sendRequest(portlet.request
	          					, "responseId="+Light._PC_PREFIX+index+"_"+portlet.position+"_"+ portlet.index
	          					, "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
	                            , "portletId="+portlet.serverId
	                            ,widthPortlet
	                        	);
	          }
          }          
      }
  }   

function showAddFeed(e,id){
      var currentTabId = Light.portal.GetFocusedTabId();
      var tabIndex = currentTabId.substring(8,currentTabId.length);//tab id perfix tab_page
      var portlet = Light.getPortletById(id);     
      if(portlet == null) return;      
      var vdocument = document.getElementById('panel_tab_page'+tabIndex);
      var old = document.getElementById('myFeed');
      if(old != null) return;
      var vMyFeed = document.createElement('div');
      vMyFeed.id="myFeed";
      vMyFeed.style.position = "absolute";
      vMyFeed.className = "portlet-popup";  
      vMyFeed.style.width=280;          
      var x = 0;
      var y = 0;
      if (window.event) {	
    	   x = event.clientX + document.body.scrollLeft+50;
     	   y = event.clientY + document.body.scrollTop - 100;
      }else {
           x = e.pageX+50;
           y = e.pageY - 100;
      }   
      if (document.all) {	
           vMyFeed.style.posLeft = x;
       	   vMyFeed.style.posTop = y;
      }else{
      	   vMyFeed.style.left = x;
           vMyFeed.style.top = y;
      }
      vMyFeed.innerHTML = "<span title='"+LightResourceBundle._CLOSE+"'  width='100%' style='clear: both;  display: block; text-align:right;'><a href='javascript:void(0);' onclick='javascript:hideAddFeed();'><img src='"+Light.portal.contextPath+"/portal/light/images/close_on.gif' style='border: 0px;'/></a></span>"
                                   +"<form name='myFeedForm'>"
                                   +"<table border='0' cellpadding='0' cellspacing='0' >"
				   +"<tr>"
                                   +"<td class='portlet-table-td-left' >"
				   +"Add My Feed"
				   +"</td>"
				   +"</tr>"
				   +"<tr>"
				   +"<td class='portlet-table-td-left' >"
			           +"Enter a URL (RSS/ATOM or autodiscovery) :"
				   +"</td>"
				   +"</tr>"
				   +"<tr>"
				   +"<td class='portlet-table-td-left' >"
                                   +"<input type='text' name='pcFeed' value='' size='30' /> "
                                   +"<input name='AddFeed' type='button' value='Add'"
				   +" onclick=\"javascript:addFeed('"+id+"');\" />"
				   +"</td>"
                                   +"</tr>"                                  
                                   +"</table>"
                                   +"</form>"
                                   +"<form name='myFeedFileForm' enctype='multipart/form-data' method='post' "
                                   +"action ='"+Light.portal.contextPath+Light.uploadOpml+"' >"
                                   +"<table border='0' cellpadding='0' cellspacing='0' >"
				   +"<tr>"
                                   +"<td class='portlet-table-td-left' >"
				   +"Or import an OPML file :"
				   +"</td>"
                                   +"</tr>"
				   +"<tr>"
				   +"<td class='portlet-table-td-left' >"
                                   +"<input type='file' name='file' onchange='this.form.submit();' />"
				   +"</td>"
                                   +"</tr>"
				   +"<tr>"
				   +"</table>"
                                   +"</form>"
                                   ;
      vMyFeed.style.zIndex= portlet.container.style.zIndex + 1; 
      vdocument.appendChild(vMyFeed); 
}

function hideAddFeed() {        
      var currentTabId = Light.portal.GetFocusedTabId();
      var tabIndex = currentTabId.substring(8,currentTabId.length);//tab id perfix tab_page
      var vdocument = document.getElementById('panel_tab_page'+tabIndex);
      var old = document.getElementById('myFeed');
      if(old != null) vdocument.removeChild(old);
  }

function addFeed(id){
  var feed = document.forms['myFeedForm']['pcFeed'].value;
  var portlet = Light.getPortletById(id);
  ajaxEngine.sendRequest(portlet.request
				         , "responseId="+id
				         , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
                         , "portletId="+portlet.serverId
				         , "action=addFeed"
				         , "feed="+feed);
  hideAddFeed();
}

function showMyFeed(id){  
  var portlet = Light.getPortletById(id);  
  ajaxEngine.sendRequest(portlet.request
		      , "responseId="+id
		      , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
                      , "portletId="+portlet.serverId
		      , "action=showMyFeed"
			);
}

function hideMyFeed(id){  
  var portlet = Light.getPortletById(id);  
  ajaxEngine.sendRequest(portlet.request
		      , "responseId="+id
		      , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
                      , "portletId="+portlet.serverId
                      , "action=hideMyFeed"
			);
}

function showFeaturedFeed(id){  
  var portlet = Light.getPortletById(id);  
  ajaxEngine.sendRequest(portlet.request
		      , "responseId="+id
		      , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
                      , "portletId="+portlet.serverId
		      , "action=showFeaturedFeed"
			);
}

function hideFeaturedFeed(id){  
  var portlet = Light.getPortletById(id);  
  ajaxEngine.sendRequest(portlet.request
		      , "responseId="+id
		      , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
                      , "portletId="+portlet.serverId
                      , "action=hideFeaturedFeed"
			);
}
//***********zhoucz 20070417 night start******************//
function selectCol(id,colval){
  //var params = "col="+colval;
 var portlet = Light.getPortletById(id);
ajaxEngine.sendRequest(portlet.request
                                , "responseId="+id
	          					, "col="+colval
	                        	);
} 
//************zhoucz 20070417 night end***************//
function showDictionary(id,colvalue){ 

  var portlet = Light.getPortletById(id);  
  ajaxEngine.sendRequest(portlet.request
		      , "responseId="+id
		      , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
                      , "portletId="+portlet.serverId
                      , "col="+colvalue
		      , "action=showDictionary"
			);
}

function hideDictionary(id,colvalue){ 

  var portlet = Light.getPortletById(id);  
  ajaxEngine.sendRequest(portlet.request
		      , "responseId="+id
		      , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
                      , "portletId="+portlet.serverId
                      , "col="+colvalue
		      , "action=hideDictionary"
			);
}

function showDictionaryFeed(id,name,colvalue){  
  var portlet = Light.getPortletById(id);  
  ajaxEngine.sendRequest(portlet.request
		      , "responseId="+id
		      , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
                      , "portletId="+portlet.serverId
		      , "action=showDictionaryFeed"
		      , "col="+colvalue
                      , "name="+encodeURIComponent(name)
			);
}

function hideDictionaryFeed(id,name,clovalue){  
  var portlet = Light.getPortletById(id);  
  ajaxEngine.sendRequest(portlet.request
		      , "responseId="+id
		      , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
                      , "portletId="+portlet.serverId
		      , "action=hideDictionaryFeed"
		      , "col="+clovalue
                      , "name="+encodeURIComponent(name)
			);
}

function configPortlet(id){
  var title = document.forms['form_'+id]['pcTitle'].value;
  var barBgColor = document.forms['form_'+id]['pcBarBgColor'].value;
  var barFontColor = document.forms['form_'+id]['pcBarFontColor'].value;
  var contentBgColor = document.forms['form_'+id]['pcContentBgColor'].value;
  var portlet = Light.getPortletById(id);
  portlet.title = title;
  portlet.barBgColor = barBgColor;
  portlet.barFontColor = barFontColor;
  portlet.contentBgColor = contentBgColor;
  portlet.refreshWindow();
  ajaxEngine.sendRequest(portlet.request
				         , "responseId="+id
				         , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
                         , "portletId="+portlet.serverId
                         , "mode="+portlet.mode
				         , "action=configPortlet"
				         , "title="+encodeURIComponent(title)
				         , "barBgColor="+barBgColor
				         , "barFontColor="+barFontColor
				         , "contentBgColor="+contentBgColor
				         );
}

function loginPortal(id){
  var userId = document.forms['form_'+id]['userId'].value;
  var password = document.forms['form_'+id]['password'].value;  
   if(document.forms['form_'+id]['rememberMe'].checked) {
     var date = new Date(); 
	 var timestamp = Date.parse(date);      
	 date.setFullYear(date.getFullYear()+1);
     Light.setCookie(Light._REMEMBERED_USER_ID,userId,date);
     Light.setCookie(Light._REMEMBERED_USER_PASSWORD,password,date);
  }else{
     Light.deleteCookie(Light._REMEMBERED_USER_ID);
     Light.deleteCookie(Light._REMEMBERED_USER_PASSWORD);
  }
 
  var portlet = Light.getPortletById(id);
  var params = "portletId="+portlet.serverId
              +"&tabId="+Light.portal.tabs[portlet.tIndex].tabServerId  			 
  			  +"&userId="+userId  
  			  +"&password="+password	 			  
  				;
  new Ajax.Request(Light.portal.contextPath+Light.loginRequest, {parameters:params, onSuccess:responseLogin});   
}

function responseLogin(t){
	var userId = t.responseText;
	if(userId == "-1"){
	   alert("This User ID is not exist , please relate the admin.");
           Light.deleteCookie(Light._REMEMBERED_USER_ID);
           Light.deleteCookie(Light._REMEMBERED_USER_PASSWORD);
	}else if(userId == "-2"){
	    alert("you input wrong password , please try again.");
            Light.deleteCookie(Light._REMEMBERED_USER_ID);
            Light.deleteCookie(Light._REMEMBERED_USER_PASSWORD);
	}else{
		Light.setCookie(Light._LOGINED_USER_ID,userId);
		Light.deleteCookie(Light._CURRENT_TAB); 
		Light.deleteCookie(Light._IS_SIGN_OUT);              
		window.location.reload( true );
	}    
}

function showSignUp(id){
  var portlet = Light.getPortletById(id);
  portlet.mode= Light._EDIT_MODE;
  ajaxEngine.sendRequest(portlet.request
				         , "responseId="+id
				         , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
                         , "portletId="+portlet.serverId
                         , "mode="+Light._EDIT_MODE
				         );
}

function signUpPortal(id){
  var userId = document.forms['form_'+id]['plUserId'].value;
  var password = document.forms['form_'+id]['plPassword'].value;
  var cpassword = document.forms['form_'+id]['plConfirmPassword'].value;  
  var firstName = document.forms['form_'+id]['plFirstName'].value;
  //var middleName = document.forms['form_'+id]['plMiddleName'].value;//zhoucz 20070329
  var middleName = "";
  var lastName = document.forms['form_'+id]['plLastName'].value;
  var email = document.forms['form_'+id]['plEmail'].value;
  if(userId == null || userId == "" 
   || password == null || password == "" 
   || cpassword ==null || cpassword == "" 
   || firstName == null || firstName == ""
   || lastName == null || lastName == ""){
  	alert(LightResourceBundle._ERROR_FIELDS_REQUIRED);
  	return;
  }
  if(password != cpassword){
  	alert(LightResourceBundle._ERROR_PASSWORD_NOT_EQUAL);
  	return;
  }
  var channels="";
  /*20070329 zhoucz
  var len =  document.forms['form_'+id]['plChannels'].length; 
  for(var i = 0; i < len; i++) {
		if(document.forms['form_'+id]['plChannels'][i].checked) {
			channels += document.forms['form_'+id]['plChannels'][i].value+",";
		}
	}*/
  var portlet = Light.getPortletById(id);  
  var params = "portletId="+portlet.serverId
              +"&tabId="+Light.portal.tabs[portlet.tIndex].tabServerId  			 
  			  +"&userId="+userId  
  			  +"&password="+password	
  			  +"&firstName="+firstName
  	          +"&middleName="+middleName
  			  +"&lastName="+lastName
  			  +"&email="+email
  			  +"&channels="+channels	 			  
  				;
  if(document.forms['form_'+id]['plShowLocale'].checked) {
    params += +"&showLocale="+document.forms['form_'+id]['plShowLocale'].value;
  }
  new Ajax.Request(Light.portal.contextPath+Light.signUpRequest, {parameters:params, onSuccess:responseSignUpPortal}); 
}

function responseSignUpPortal(t){
	var userId = t.responseText;
	if(userId == "-1"){
	    alert("This User ID have already registered before, please try other User ID.");
	}else if(userId == "-2"){
	    alert("System busy, please try later.");
	}else{
	    Light.setCookie(Light._LOGINED_USER_ID,userId);
		window.location.reload( true );    	
	}   
}

function cancelSignUpPortal(id){
  var portlet = Light.getPortletById(id);
  ajaxEngine.sendRequest(portlet.request
				         , "responseId="+id
				         , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
                         , "portletId="+portlet.serverId
				         );
}

function saveProfile(id){ 
  var password = document.forms['form_'+id]['plPassword'].value;
  var cpassword = document.forms['form_'+id]['plConfirmPassword'].value;  
  var firstName = document.forms['form_'+id]['plFirstName'].value;
  //var middleName = document.forms['form_'+id]['plMiddleName'].value;//20070329 zhoucz
  var middleName = "";
  var lastName = document.forms['form_'+id]['plLastName'].value;
  var email = document.forms['form_'+id]['plEmail'].value;
  if(password == null || password == "" 
   || cpassword ==null || cpassword == "" 
   || firstName == null || firstName == ""
   || lastName == null || lastName == ""){
  	alert(LightResourceBundle._ERROR_FIELDS_REQUIRED);
  	return;
  }
  if(password != cpassword){
  	alert(LightResourceBundle._ERROR_PASSWORD_NOT_EQUAL);
  	return;
  }
  var channels="";
  /*20070329 zhoucz
  var len =  document.forms['form_'+id]['plChannels'].length;  
   for(var i = 0; i < len; i++) {
		if(document.forms['form_'+id]['plChannels'][i].checked) {
			channels += document.forms['form_'+id]['plChannels'][i].value+",";
		}
	}*/
  var portlet = Light.getPortletById(id);
   var params = "portletId="+portlet.serverId
              +"&tabId="+Light.portal.tabs[portlet.tIndex].tabServerId  			 
  			  +"&password="+password	
  			  +"&firstName="+firstName
  	          +"&middleName="+middleName
  			  +"&lastName="+lastName
  			  +"&email="+email
  			  +"&channels="+channels	 			  
  				;
  if(document.forms['form_'+id]['plShowLocale'].checked) {
    params += "&showLocale=1";
  }
  new Ajax.Request(Light.portal.contextPath+Light.profileRequest, {parameters:params, onSuccess:responseSaveProfile}); 
}

function responseSaveProfile(t){
	var userId = t.responseText;
	if(userId == "-1"){
		alert("This User is not exist.");
	}else if(userId == "-2"){
	    alert("System busy, please try later.");
	}else{
		window.location.reload( true );    	
	}    
}

function showMoreBgImage(e, id){
      var currentTabId = Light.portal.GetFocusedTabId();
      var tabIndex = currentTabId.substring(8,currentTabId.length);//tab id perfix tab_page      
      var vdocument = document.getElementById('panel_tab_page'+tabIndex);
      var portlet = Light.getPortletById(id);     
      if(portlet == null) return; 
      var old = document.getElementById('moreBgImage');
      if(old != null) return;
      var vMoreBgImage = document.createElement('div');
      vMoreBgImage.id="moreBgImage";
      vMoreBgImage.style.position = "absolute";      
      vMoreBgImage.className = "portlet-popup";      
      var inner = "<form name='form_moreBgImage'>"
                 +"<table border='0' cellpadding='0' cellspacing='0'>";
      inner=inner+"<tr>"
                 +"<td class='portlet-table-td-left'><span height='20' width='100' style='background:url(\"portal/light/images/portal-bg1.png\");' ><image height='20' width='100' src=\"portal/light/images/spacer.gif\" style='border: 0px' /></span>";
      if(Light.portal.bgImage != "portal/light/images/portal-bg1.png")
  	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg1.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg1.png' checked='true'/></td>";
      
      inner = inner+"<td class='portlet-table-td-left'><span height='20' width='100' style='background:url(\"portal/light/images/portal-bg2.png\");' ><image height='20' width='100' src=\"portal/light/images/spacer.gif\" style='border: 0px' /></span>";
      if(Light.portal.bgImage != "portal/light/images/portal-bg2.png")
  	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg2.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg2.png' checked='true'/></td>";
      
      inner = inner+"<td class='portlet-table-td-left'><span height='20' width='100' style='background:url(\"portal/light/images/portal-bg3.png\");' ><image height='20' width='100' src=\"portal/light/images/spacer.gif\" style='border: 0px' /></span>";
      if(Light.portal.bgImage != "portal/light/images/portal-bg3.png")
  	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg3.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg3.png' checked='true'/></td>";
      
      inner = inner+"<td class='portlet-table-td-left'><span height='20' width='100' style='background:url(\"portal/light/images/portal-bg4.png\");' ><image height='20' width='100' src=\"portal/light/images/portal-bg4.png\" style='border: 0px' /></span>";
      if(Light.portal.bgImage != "portal/light/images/portal-bg4.png")
  	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg4.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg4.png' checked='true'/></td>"; 
      
      inner = inner+"<td class='portlet-table-td-left'><span height='20' width='100' style='background:url(\"portal/light/images/portal-bg5.png\");' ><image height='20' width='100' src=\"portal/light/images/portal-bg5.png\" style='border: 0px' /></span>";
      if(Light.portal.bgImage != "portal/light/images/portal-bg5.png")
  	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg5.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg5.png' checked='true'/></td>"; 
      inner = inner + "</tr>";
      
      inner=inner+"<tr>"
                 +"<td class='portlet-table-td-left'><span height='20' width='100' style='background:url(\"portal/light/images/portal-bg6.png\");' ><image height='20' width='100' src=\"portal/light/images/spacer.gif\" style='border: 0px' /></span>";
      if(Light.portal.bgImage != "portal/light/images/portal-bg6.png")
  	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg6.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg6.png' checked='true'/></td>";
      
      inner = inner+"<td class='portlet-table-td-left'><span height='20' width='100' style='background:url(\"portal/light/images/portal-bg7.png\");' ><image height='20' width='100' src=\"portal/light/images/spacer.gif\" style='border: 0px' /></span>";
      if(Light.portal.bgImage != "portal/light/images/portal-bg7.png")
  	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg7.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg7.png' checked='true'/></td>";
      
      inner = inner+"<td class='portlet-table-td-left'><span height='20' width='100' style='background:url(\"portal/light/images/portal-bg8.png\");' ><image height='20' width='100' src=\"portal/light/images/spacer.gif\" style='border: 0px' /></span>";
      if(Light.portal.bgImage != "portal/light/images/portal-bg8.png")
  	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg8.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg8.png' checked='true'/></td>";
      
      inner = inner+"<td class='portlet-table-td-left'><span height='20' width='100' style='background:url(\"portal/light/images/portal-bg9.png\");' ><image height='20' width='100' src=\"portal/light/images/spacer.gif\" style='border: 0px' /></span>";
      if(Light.portal.bgImage != "portal/light/images/portal-bg9.png")
  	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg9.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg9.png' checked='true'/></td>"; 
      
      inner = inner+"<td class='portlet-table-td-left'><span height='20' width='100' style='background:url(\"portal/light/images/portal-bg10.png\");' ><image height='20' width='100' src=\"portal/light/images/spacer.gif\" style='border: 0px' /></span>";
      if(Light.portal.bgImage != "portal/light/images/portal-bg10.png")
  	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg10.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg10.png' checked='true'/></td>"; 
      inner = inner + "</tr>";

      inner=inner+"<tr>"
                 +"<td class='portlet-table-td-left'><span height='20' width='100' style='background:url(\"portal/light/images/portal-bg11.png\");' ><image height='20' width='100' src=\"portal/light/images/spacer.gif\" style='border: 0px' /></span>";
      if(Light.portal.bgImage != "portal/light/images/portal-bg11.png")
  	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg11.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg11.png' checked='true'/></td>";
      
      inner = inner+"<td class='portlet-table-td-left'><span height='20' width='100' style='background:url(\"portal/light/images/portal-bg12.png\");' ><image height='20' width='100' src=\"portal/light/images/spacer.gif\" style='border: 0px' /></span>";
      if(Light.portal.bgImage != "portal/light/images/portal-bg12.png")
  	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg12.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg12.png' checked='true'/></td>";
      
      inner = inner+"<td class='portlet-table-td-left'><span height='20' width='100' style='background:url(\"portal/light/images/portal-bg13.png\");' ><image height='20' width='100' src=\"portal/light/images/spacer.gif\" style='border: 0px' /></span>";
      if(Light.portal.bgImage != "portal/light/images/portal-bg13.png")
  	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg13.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg13.png' checked='true'/></td>";
      
      inner = inner+"<td class='portlet-table-td-left'><span height='20' width='100' style='background:url(\"portal/light/images/portal-bg14.png\");' ><image height='20' width='100' src=\"portal/light/images/spacer.gif\" style='border: 0px' /></span>";
      if(Light.portal.bgImage != "portal/light/images/portal-bg14.png")
  	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg14.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg4.png' checked='true'/></td>"; 
      
      inner = inner+"<td class='portlet-table-td-left'><span height='20' width='100' style='background:url(\"portal/light/images/portal-bg15.png\");' ><image height='20' width='100' src=\"portal/light/images/spacer.gif\" style='border: 0px' /></span>";
      if(Light.portal.bgImage != "portal/light/images/portal-bg15.png")
  	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg15.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg15.png' checked='true'/></td>"; 
      inner = inner + "</tr>";
      
      inner=inner+"<tr>"
                 +"<td class='portlet-table-td-left'><span height='20' width='100' style='background:url(\"portal/light/images/portal-bg16.png\");' ><image height='20' width='100' src=\"portal/light/images/spacer.gif\" style='border: 0px' /></span>";
      if(Light.portal.bgImage != "portal/light/images/portal-bg16.png")
  	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg16.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg16.png' checked='true'/></td>";
      
      inner = inner+"<td class='portlet-table-td-left'><span height='20' width='100' style='background:url(\"portal/light/images/portal-bg17.png\");' ><image height='20' width='100' src=\"portal/light/images/spacer.gif\" style='border: 0px' /></span>";
      if(Light.portal.bgImage != "portal/light/images/portal-bg17.png")
  	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg17.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg17.png' checked='true'/></td>";
      
      inner = inner+"<td class='portlet-table-td-left'><span height='20' width='100' style='background:url(\"portal/light/images/portal-bg18.png\");' ><image height='20' width='100' src=\"portal/light/images/spacer.gif\" style='border: 0px' /></span>";
      if(Light.portal.bgImage != "portal/light/images/portal-bg18.png")
  	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg18.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg18.png' checked='true'/></td>";
      
      inner = inner+"<td class='portlet-table-td-left'><span height='20' width='100' style='background:url(\"portal/light/images/portal-bg19.png\");' ><image height='20' width='100' src=\"portal/light/images/spacer.gif\" style='border: 0px' /></span>";
      if(Light.portal.bgImage != "portal/light/images/portal-bg19.png")
  	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg19.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg19.png' checked='true'/></td>"; 
      
      inner = inner+"<td class='portlet-table-td-left'><span height='20' width='100' style='background:url(\"portal/light/images/portal-bg20.png\");' ><image height='20' width='100' src=\"portal/light/images/spacer.gif\" style='border: 0px' /></span>";
      if(Light.portal.bgImage != "portal/light/images/portal-bg20.png")
  	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg20.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptBg' value='portal/light/images/portal-bg20.png' checked='true'/></td>"; 
      inner = inner + "</tr>";

      inner = inner + "<tr><td class='portlet-table-td-right'><input type='button' value='"+LightResourceBundle._BUTTON_OK+"' onclick='javascript:saveBgImage(\""+id+"\");' class='portlet-form-button'/><input type='button' value='"+LightResourceBundle._BUTTON_CANCEL+"' onclick='javascript:cancelBgImage();' class='portlet-form-button'/></td></tr>";
      inner =inner + "</table></form>";
      vMoreBgImage.innerHTML = inner;
      var x = 0;
      var y = 0;
      if (window.event) {	
    	   x = event.clientX + document.body.scrollLeft+10;
     	   y = event.clientY + document.body.scrollTop - 100;
      }else {
           x = e.pageX - 200;
           y = e.pageY - 200;
      }   
      if (document.all) {	
           vMoreBgImage.style.posLeft = x;
       	   vMoreBgImage.style.posTop = y;
      }else{
      	   vMoreBgImage.style.left = x;
           vMoreBgImage.style.top = y;
      }
      vMoreBgImage.style.zIndex= portlet.container.style.zIndex + 1; 
      vdocument.appendChild(vMoreBgImage); 

}

function saveBgImage(id){  
  var len = document.forms['form_moreBgImage']['ptBg'].length;
  var bgImage="";
  for(var i = 0; i < len; i++) {
		if(document.forms['form_moreBgImage']['ptBg'][i].checked) {
			bgImage = document.forms['form_moreBgImage']['ptBg'][i].value;
		}
	}
   Light.portal.newBgImage=bgImage;   
   if(bgImage.length > 0){
    var len = document.forms['form_'+id]['ptBg'].length;
    for(var i = 0; i < len; i++) {
		if(document.forms['form_'+id]['ptBg'][i].value="more")
		   document.forms['form_'+id]['ptBg'][i].checked = true;
		else
                   document.forms['form_'+id]['ptBg'][i].checked = false;  
	}
   }
   cancelBgImage();
}

function cancelBgImage(){
   var currentTabId = Light.portal.GetFocusedTabId();
   var tabIndex = currentTabId.substring(8,currentTabId.length);//tab id perfix tab_page
   var vdocument = document.getElementById('panel_tab_page'+tabIndex);
   var old = document.getElementById('moreBgImage');
   if(old != null) vdocument.removeChild(old);
}

function showMoreHeaderImage(e, id){
      var currentTabId = Light.portal.GetFocusedTabId();
      var tabIndex = currentTabId.substring(8,currentTabId.length);//tab id perfix tab_page      
      var vdocument = document.getElementById('panel_tab_page'+tabIndex);
      var portlet = Light.getPortletById(id);     
      if(portlet == null) return; 
      var old = document.getElementById('moreHeaderImage');
      if(old != null) return;
      var vMoreHeaderImage = document.createElement('div');
      vMoreHeaderImage.id="moreHeaderImage";
      vMoreHeaderImage.style.position = "absolute";      
      vMoreHeaderImage.className = "portlet-popup";      
      var inner = "<form name='form_moreHeaderImage'>"
                 +"<table border='0' cellpadding='0' cellspacing='0'>";
      inner=inner+"<tr>"
                 +"<td class='portlet-table-td-left'><image height='40' width='200' src=\"portal/light/images/portal-header1.png\" style='border: 0px' />";
      if(Light.portal.headerImage != "portal/light/images/portal-header1.png")
  	 inner=inner+"<input type='radio' name='ptHeader' value='portal/light/images/portal-header1.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptHeader' value='portal/light/images/portal-header1.png' checked='true'/></td>";
      
      inner = inner+"<td class='portlet-table-td-left'><image height='40' width='200' src=\"portal/light/images/portal-header2.png\" style='border: 0px' />";
      if(Light.portal.headerImage != "portal/light/images/portal-header2.png")
  	 inner=inner+"<input type='radio' name='ptHeader' value='portal/light/images/portal-header2.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptHeader' value='portal/light/images/portal-header2.png' checked='true'/></td>";       
      inner = inner + "</tr>";
      
      inner=inner+"<tr>"
                 +"<td class='portlet-table-td-left'><image height='40' width='200' src=\"portal/light/images/portal-header3.png\" style='border: 0px' />";
      if(Light.portal.headerImage != "light/images/portal-header3.png")
  	 inner=inner+"<input type='radio' name='ptHeader' value='portal/light/images/portal-header3.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptHeader' value='portal/light/images/portal-header3.png' checked='true'/></td>"; 

      inner = inner+"<td class='portlet-table-td-left'><image height='40' width='200' src=\"portal/light/images/portal-header4.png\" style='border: 0px' />";
      if(Light.portal.headerImage != "portal/light/images/portal-header4.png")
  	 inner=inner+"<input type='radio' name='ptHeader' value='portal/light/images/portal-header4.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptHeader' value='portal/light/images/portal-header4.png' checked='true'/></td>"; 
      inner = inner + "</tr>";
      
      inner=inner+"<tr>"
                 +"<td class='portlet-table-td-left'><image height='40' width='200' src=\"portal/light/images/portal-header5.png\" style='border: 0px' />";
      if(Light.portal.headerImage != "portal/light/images/portal-header5.png")
  	 inner=inner+"<input type='radio' name='ptHeader' value='portal/light/images/portal-header5.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptHeader' value='portal/light/images/portal-header5.png' checked='true'/></td>"; 

      inner = inner+"<td class='portlet-table-td-left'><image height='40' width='200' src=\"portal/light/images/portal-header6.png\" style='border: 0px' />";
      if(Light.portal.headerImage != "light/images/portal-header6.png")
  	 inner=inner+"<input type='radio' name='ptHeader' value='portal/light/images/portal-header6.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptHeader' value='portal/light/images/portal-header6.png' checked='true'/></td>"; 
      inner = inner + "</tr>";

      inner=inner+"<tr>"
                 +"<td class='portlet-table-td-left'><image height='40' width='200' src=\"portal/light/images/portal-header7.png\" style='border: 0px' />";
      if(Light.portal.headerImage != "light/images/portal-header7.png")
  	 inner=inner+"<input type='radio' name='ptHeader' value='portal/light/images/portal-header7.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptHeader' value='portal/light/images/portal-header7.png' checked='true'/></td>"; 

      inner = inner+"<td class='portlet-table-td-left'><image height='40' width='200' src=\"portal/light/images/portal-header8.png\" style='border: 0px' />";
      if(Light.portal.headerImage != "portal/light/images/portal-header8.png")
  	 inner=inner+"<input type='radio' name='ptHeader' value='portal/light/images/portal-header8.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptHeader' value='portal/light/images/portal-header8.png' checked='true'/></td>"; 
      inner = inner + "</tr>";      
	
      inner=inner+"<tr>"
                 +"<td class='portlet-table-td-left'><image height='40' width='200' src=\"portal/light/images/portal-header9.png\" style='border: 0px' />";
      if(Light.portal.headerImage != "portal/light/images/portal-header9.png")
  	 inner=inner+"<input type='radio' name='ptHeader' value='portal/light/images/portal-header9.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptHeader' value='portal/light/images/portal-header9.png' checked='true'/></td>"; 

      inner = inner+"<td class='portlet-table-td-left'><image height='40' width='200' src=\"portal/light/images/portal-header10.png\" style='border: 0px' />";
      if(Light.portal.headerImage != "portal/light/images/portal-header10.png")
  	 inner=inner+"<input type='radio' name='ptHeader' value='portal/light/images/portal-header10.png' /></td>";
      else
   	 inner=inner+"<input type='radio' name='ptHeader' value='portal/light/images/portal-header10.png' checked='true'/></td>"; 
      inner = inner + "</tr>";

      inner = inner + "<tr><td class='portlet-table-td-right'><input type='button' value='"+LightResourceBundle._BUTTON_OK+"' onclick='javascript:saveHeaderImage(\""+id+"\");' class='portlet-form-button'/><input type='button' value='"+LightResourceBundle._BUTTON_CANCEL+"' onclick='javascript:cancelHeaderImage();' class='portlet-form-button'/></td></tr>";
      inner =inner + "</table></form>";
      vMoreHeaderImage.innerHTML = inner;
      var x = 0;
      var y = 0;
      if (window.event) {	
    	   x = event.clientX + document.body.scrollLeft+10;
     	   y = event.clientY + document.body.scrollTop - 100;
      }else {
           x = e.pageX - 200;
           y = e.pageY - 200;
      }   
      if (document.all) {	
           vMoreHeaderImage.style.posLeft = x;
       	   vMoreHeaderImage.style.posTop = y;
      }else{
      	   vMoreHeaderImage.style.left = x;
           vMoreHeaderImage.style.top = y;
      }
      vMoreHeaderImage.style.zIndex= portlet.container.style.zIndex + 1; 
      vdocument.appendChild(vMoreHeaderImage); 

}

function saveHeaderImage(id){  
  var len = document.forms['form_moreHeaderImage']['ptHeader'].length;
  var headerImage="";
  for(var i = 0; i < len; i++) {
		if(document.forms['form_moreHeaderImage']['ptHeader'][i].checked) {
			headerImage = document.forms['form_moreHeaderImage']['ptHeader'][i].value;
		}
	}
   Light.portal.newHeaderImage=headerImage;   
   if(headerImage.length > 0){
    var len = document.forms['form_'+id]['ptHeader'].length;
    for(var i = 0; i < len; i++) {
		if(document.forms['form_'+id]['ptHeader'][i].value="more")
		   document.forms['form_'+id]['ptHeader'][i].checked = true;
		else
                   document.forms['form_'+id]['ptHeader'][i].checked = false;  
	}
   }
   cancelHeaderImage();
}

function cancelHeaderImage(){
   var currentTabId = Light.portal.GetFocusedTabId();
   var tabIndex = currentTabId.substring(8,currentTabId.length);//tab id perfix tab_page
   var vdocument = document.getElementById('panel_tab_page'+tabIndex);
   var old = document.getElementById('moreHeaderImage');
   if(old != null) vdocument.removeChild(old);
}

//----------------------------------------------------  lightUtilityPortlet.js  

 function showRssDesc( e, index, id) {   
      if(Light.portal == null) return;           
      var currentTabId = Light.portal.GetFocusedTabId();
      var tabIndex = currentTabId.substring(8,currentTabId.length);//tab id perfix tab_page
      var portlet = Light.getPortletById(id);     
      if(portlet == null) return;      
      var vdocument = document.getElementById('panel_tab_page'+tabIndex);
      var old = document.getElementById('rssDesc');
      if(old != null) vdocument.removeChild(old);
      var vRssDesc = document.createElement('div');
      vRssDesc.id="rssDesc";
      vRssDesc.style.position = "absolute";      
      vRssDesc.onmouseout  = function(){ hideRssDesc();}
      var x = 0;
      var y = 0;
      if (window.event) {	
    	   x = event.clientX + document.body.scrollLeft+10;
     	   y = event.clientY + document.body.scrollTop - 100;
      }else {
           x = e.pageX+10;
           y = e.pageY - 100;
      }   
      if (document.all) {	
           vRssDesc.style.posLeft = x;
       	   vRssDesc.style.posTop = y;
      }else{
      	   vRssDesc.style.left = x;
           vRssDesc.style.top = y;
      }
      vRssDesc.style.zIndex= Light.maxZIndex; 
      vdocument.appendChild(vRssDesc); 
      var params="index="+index
                +"&"+portlet.parameter;
      new Ajax.Request(Light.portal.contextPath+Light.getRssDesc, {parameters:params, onSuccess:responseRssDesc}); 
  } 
  
   hideRssDesc  = function () {        
      var currentTabId = Light.portal.GetFocusedTabId();
      var tabIndex = currentTabId.substring(8,currentTabId.length);//tab id perfix tab_page
      var vdocument = document.getElementById('panel_tab_page'+tabIndex);
      var old = document.getElementById('rssDesc');
      if(old != null) vdocument.removeChild(old);
  }
 
function responseRssDesc(t){
      var desc = t.responseText;             
      var currentTabId = Light.portal.GetFocusedTabId();
      var tabIndex = currentTabId.substring(8,currentTabId.length);//tab id perfix tab_page
      var vdocument = document.getElementById('panel_tab_page'+tabIndex);
      var old = document.getElementById('rssDesc');
      var x= 100;
      var y= 100;
      if(old != null){
        if (document.all) {	
           x = old.style.posLeft;
       	   y = old.style.posTop;
        }else{
      	   x = old.style.left;
           y = old.style.top;
        }
        vdocument.removeChild(old);      
        var vRssDesc = document.createElement('div');
        vRssDesc.id="rssDesc";
        vRssDesc.style.position = "absolute";
        vRssDesc.style.width=300;
        vRssDesc.onmouseout  = function(){ hideRssDesc();}
        vRssDesc.className = "portlet-popup";      
        vRssDesc.innerHTML = desc;      
        if (document.all) {	
           vRssDesc.style.posLeft = x;
       	   vRssDesc.style.posTop = y;
        }else{
      	   vRssDesc.style.left = x;
           vRssDesc.style.top = y;
        }
        vRssDesc.style.zIndex= Light.maxZIndex+10; 
        vdocument.appendChild(vRssDesc); 
     }
  }

function editRssPortlet(id){
   var feed  = document.forms['form_'+id]['prFeed'].value;
   var title  = document.forms['form_'+id]['prTitle'].value;
   var icon  = document.forms['form_'+id]['prIcon'].value;
   var url  = document.forms['form_'+id]['prUrl'].value;
   var autoRefresh  = document.forms['form_'+id]['prAuto'].value;
   var minute  = document.forms['form_'+id]['prMinute'].value;
   var items  = document.forms['form_'+id]['prItems'].value;   
   if(autoRefresh == '1' && minute == '0'){
      alert(LightResourceBundle._ERROR_MINUTE_RATE);
      return;
   }
   var portlet = Light.getPortletById(id);
   portlet.title = title;
   portlet.icon = icon;
   portlet.url = url;
   portlet.parameter = "feed="+feed;  
   portlet.refreshHeader();
   if(autoRefresh== "1")
      portlet.autoRefreshed = true;
   else
      portlet.autoRefreshed = false;
   portlet.periodTime = minute * 60000;
   portlet.autoRefresh();
   ajaxEngine.sendRequest(portlet.request
				         , "responseId="+id
				         , "action=edit"
				         , "mode=EDIT"
				         , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
                         , "portletId="+portlet.serverId
				         , "title="+title
				         , "url="+url
				         , "icon="+icon
                         , "autoRefresh="+autoRefresh
                         , "minute="+minute
				         , "items="+items
				         , "feed="+feed);
}

function keyDownSearchWeather(e,id) {  
  var KeyID;
  if (window.event) {	
	keyID = window.event.keyCode;
  } else {
        keyID = e.which;
  } 
  if ( keyID == 13){   
    searchWeatherLocation(id);    
  }
  return !(keyID == 13);
 }
 
function searchWeatherLocation(id){
   var locName  = document.forms['form_'+id]['pwLocation'].value;  
   var portlet = Light.getPortletById(id);

   ajaxEngine.sendRequest(portlet.request
				         , "responseId="+id
				         , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
                         , "portletId="+portlet.serverId
				         , "locName="+locName
				        );
}

function selectWeatherLocation(id){  
  var locId  =  document.forms['form_'+id]['pwLocId'].value;
  if(!locId){      
      var len =  document.forms['form_'+id]['pwLocId'].length;
	  for(var i = 0; i < len; i++) {
			if(document.forms['form_'+id]['pwLocId'][i].checked) {
				locId = document.forms['form_'+id]['pwLocId'][i].value;
			}
		}
   }
   var unit =  document.forms['form_'+id]['pwUnit'].value;
   var portlet = Light.getPortletById(id);
   ajaxEngine.sendRequest(portlet.request
				         , "responseId="+id
				         , "action=select"
				         , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
                         , "portletId="+portlet.serverId
				         , "locId="+locId
				         , "unit="+unit
				        );
}

function keyDownEditWeather(e,id) {  
  var KeyID;
  if (window.event) {	
	keyID = window.event.keyCode;
  } else {
        keyID = e.which;
  } 
  if ( keyID == 13){   
    editWeatherLocation(id);    
  }
  return !(keyID == 13);
 }
 
function editWeatherLocation(id){
   var locName  = document.forms['form_'+id]['pwLocation'].value;  
   var portlet = Light.getPortletById(id);
   ajaxEngine.sendRequest(portlet.request
				         , "responseId="+id
				         , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
                         , "portletId="+portlet.serverId
                         , "mode=EDIT"
				         , "locName="+locName
				        );
}

function previousImage(id){
  var number = document.forms['form_'+id]['pvNumber'].value;  
  number = parseInt(number) - 1;
  var portlet = Light.getPortletById(id); 
  ajaxEngine.sendRequest(portlet.request
				         , "responseId="+id
				         , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
                         , "portletId="+portlet.serverId
				         , portlet.parameter
				         , "number="+number
				         );
}

function nextImage(id){
  var number = document.forms['form_'+id]['pvNumber'].value;  
  number = parseInt(number) + 1;
  var portlet = Light.getPortletById(id);  
  ajaxEngine.sendRequest(portlet.request
				         , "responseId="+id
				         , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
                         , "portletId="+portlet.serverId
				         , portlet.parameter
				         , "number="+number
				         );
}

function previousVideo(id){
  var number = document.forms['form_'+id]['pvNumber'].value;  
  number = parseInt(number) - 1;
  var portlet = Light.getPortletById(id); 
  ajaxEngine.sendRequest(portlet.request
				         , "responseId="+id
				         , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
                         , "portletId="+portlet.serverId
				         , portlet.parameter
				         , "number="+number
				         );
}

function nextVideo(id){
  var number = document.forms['form_'+id]['pvNumber'].value;  
  number = parseInt(number) + 1;
  var portlet = Light.getPortletById(id);  
  ajaxEngine.sendRequest(portlet.request
				         , "responseId="+id
				         , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
                         , "portletId="+portlet.serverId
				         , portlet.parameter
				         , "number="+number
				         );
}

function editViewerPortlet(id){
   var feed  = document.forms['form_'+id]['prFeed'].value;
   var title  = document.forms['form_'+id]['prTitle'].value;
   var icon  = document.forms['form_'+id]['prIcon'].value;
   var url  = document.forms['form_'+id]['prUrl'].value;
   var portlet = Light.getPortletById(id);
   portlet.title = title;
   portlet.icon = icon;
   portlet.url = url;
   portlet.parameter = "feed="+feed;  
   portlet.refreshHeader();
   ajaxEngine.sendRequest(portlet.request
				         , "responseId="+id
				         , "action=edit"
				         , "mode=EDIT"
				         , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
                         , "portletId="+portlet.serverId
				         , "title="+title
				         , "url="+url
				         , "icon="+icon
				         , "feed="+feed);
}

function countLight(type){
 var params = "type="+type;
 new Ajax.Request(Light.portal.contextPath+Light.countLightRequest,{parameters:params}); 
}
