
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

/** add by mios 070329 >> **/
var	ie=/msie/i.test(navigator.userAgent);
/** add by mios 070329 << **/

//------------------------------------------------------------ PortletWindow.js
 PortletWindow = function (tIndex,serverId,position,title,icon,url,request,requestUrl,closeable,refreshMode,editMode,helpMode,configMode,autoRefreshed,periodTime,allowJS,barBgColor,barFontColor,contentBgColor,parameter) {
   this.layout={	     	    
	    titleRelativeLeft : 10,
	    titleRelativeTop : -8, 
	    buttonRelative : 16,
	    upRelativeRight : 94,
	    downRelativeRight : 78, 
	    minRelativeRight : 62,
	    maxRelativeRight : 46,
	    closeRelativeRight : 30,
	    buttonRelativeTop : -6,
	    rowBetween : 12  
   }
   this.mode = Light._VIEW_MODE;
   this.state = Light._NORMAL_STATE;
   this.tIndex = tIndex;   
   this.serverId = serverId;
   this.position = position;
   this.title = title;
   this.icon = icon;
   this.url = url;
   this.request = request;
   this.requestUrl = requestUrl;
   this.closeable = closeable;
   this.refreshMode = refreshMode;
   this.editMode = editMode;
   this.helpMode = helpMode;
   this.configMode = configMode;
   this.autoRefreshed = autoRefreshed;
   this.periodTime = periodTime;
   this.allowJS = allowJS;
   this.barBgColor = barBgColor;
   this.barFontColor = barFontColor;
   this.contentBgColor = contentBgColor;
   this.parameter = parameter;
   this.index = Light.portal.tabs[this.tIndex].getPortletIndex(this.position);
   var height = 0;
   var maxIndex = 0;
   var nullNum = 0;        
   for(var i=0;i<Light.portal.tabs[this.tIndex].portlets[this.position].length;i++) {        
      if(i == this.index){
         break;
      }
  	  if(Light.portal.tabs[this.tIndex].portlets[this.position][i] != null && !Light.portal.tabs[this.tIndex].portlets[this.position][i].maximized){
         height += Light.portal.tabs[this.tIndex].portlets[this.position][i].container.clientHeight;   
      } 
      if(Light.portal.tabs[this.tIndex].portlets[this.position][i] == null){
         nullNum++;
      }
      if(Light.portal.tabs[this.tIndex].portlets[this.position][i] != null && Light.portal.tabs[this.tIndex].portlets[this.position][i].maximized){
         height = Light.portal.tabs[this.tIndex].portlets[this.position][i].container.clientHeight;     
         maxIndex = i;
         nullNum = 0;
      }              
    }
    this.top  = Light.portal.layout.normalTop + height + this.layout.rowBetween * (i - maxIndex - nullNum);  
    this.width = Light.portal.tabs[this.tIndex].widths[this.position];
    this.left = Light.portal.tabs[this.tIndex].between;
    for(var i=0;i<this.position;i++) { 
        this.left += Light.portal.tabs[this.tIndex].widths[i] + Light.portal.tabs[this.tIndex].between;
    }
    
    Light.portal.tabs[this.tIndex].portlets[this.position][this.index] = this;     
   
   this.container = this.createPortletContainer(this);
   this.header = this.createPortletHeader(this);   
   //this.upButton = this.createPortletUpButton(this);
   //this.downButton = this.createPortletDownButton(this);   
   this.minButton = this.createPortletMinButton(this);
   this.maxButton = this.createPortletMaxButton(this);   
   this.createPortletRestoreMinButton(this);
   this.createPortletRestoreMaxButton(this);
   
   var vdocument = document.getElementById('panel_tab_page'+this.tIndex);
   vdocument.appendChild(this.container);
   vdocument.appendChild(this.header);
   //vdocument.appendChild(this.upButton);
   //vdocument.appendChild(this.downButton);   
   vdocument.appendChild(this.minButton);
   vdocument.appendChild(this.maxButton);   
   
   if(this.closeable){
   	  this.closeButton = this.createPortletCloseButton(this);
   	  vdocument.appendChild(this.closeButton);
   }
   if(this.configMode){
      this.configButton = this.createPortletConfigButton(this);   
      vdocument.appendChild(this.configButton);
      this.createPortletCancelConfigButton(this);
   }   
   if(this.helpMode){
      this.helpButton = this.createPortletHelpButton(this);   
      vdocument.appendChild(this.helpButton);
      this.createPortletCancelHelpButton(this);
   }   
   if(this.editMode){
      this.editButton = this.createPortletEditButton(this);   
      vdocument.appendChild(this.editButton);
      this.createPortletCancelEditButton(this);
   }
   
   if(this.refreshMode){
      this.refreshButton = this.createPortletRefreshButton(this);   
      vdocument.appendChild(this.refreshButton);
   }
   
   this.minimized = false;
   this.maximized = false;   
   this.moveable = false;
   this.buttonIsClicked = false;
   this.mouseDownX = 0;
   this.mouseDownY = 0;
   this.setPosition();
   var loading = "<img src='"+Light.portal.contextPath+"/portal/light/images/spacer.gif'"
		+" height='8' style='border: 0px' width='200' alt='' />"
                +"<span class='portlet'>Loading</span>";
   this.container.innerHTML = loading;
   this.round();
   Light.portal.tabs[this.tIndex].rePositionPortlets(this); 
   if(this.autoRefreshed){
   	  this.firstTimeAutoRefreshed = true;
   	  this.autoRefresh();
   }
 }  
 
 PortletWindow.prototype.round = function(){
   if (document.all){
        var cssRule = document.styleSheets[0].rules;
   }else {
        var cssRule = document.styleSheets[0].cssRules;
   }
   var color=cssRule[0].style.color;
   var borderRule= {border: color};
   Rico.Corner.round($(Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+this.index), borderRule);
 }
 
 PortletWindow.prototype.setFocus = function()
 {
    this.container.style.zIndex= ++Light.maxZIndex;
    this.header.style.zIndex= ++Light.maxZIndex; 
    if(this.refreshMode){
    	this.refreshButton.style.zIndex= Light.maxZIndex;
    }
    if(this.editMode){
    	this.editButton.style.zIndex= Light.maxZIndex;
    }
    if(this.helpMode){
    	this.helpButton.style.zIndex= Light.maxZIndex;
    }
    if(this.configMode){
    	this.configButton.style.zIndex= Light.maxZIndex;
    }
    //this.upButton.style.zIndex= Light.maxZIndex;
    //this.downButton.style.zIndex= Light.maxZIndex;    
    this.minButton.style.zIndex= Light.maxZIndex;
    this.maxButton.style.zIndex= Light.maxZIndex;     
    if(this.closeable){
      this.closeButton.style.zIndex= Light.maxZIndex;   	  
    }   
 }
 
 PortletWindow.prototype.show = function(){
 	this.container.style.visibility = "visible";
 	this.header.style.visibility = "visible";
    if(this.refreshMode){
    	this.refreshButton.style.visibility = "visible";
    }
    if(this.editMode){
    	this.editButton.style.visibility = "visible";
    }
    if(this.helpMode){
    	this.helpButton.style.visibility = "visible";
    }
    if(this.configMode){
    	this.configButton.style.visibility = "visible";
    }
    //this.upButton.style.visibility = "visible";
    //this.downButton.style.visibility = "visible"; 
    this.minButton.style.visibility = "visible";
    this.maxButton.style.visibility = "visible";    
    if(this.closeable){
      this.closeButton.style.visibility = "visible";
    } 
 }
 
 PortletWindow.prototype.hide = function(){
 	this.container.style.visibility = "hidden";
 	this.header.style.visibility = "hidden";
    if(this.refreshMode){
    	this.refreshButton.style.visibility = "hidden";
    }
    if(this.editMode){
    	this.editButton.style.visibility = "hidden";
    }
    if(this.helpMode){
    	this.helpButton.style.visibility = "hidden";
    }
    if(this.configMode){
    	this.configButton.style.visibility = "hidden";
    }
    //this.upButton.style.visibility = "hidden";
    //this.downButton.style.visibility = "hidden"; 
    this.minButton.style.visibility = "hidden";
    this.maxButton.style.visibility = "hidden";
    if(this.closeable){
      this.closeButton.style.visibility = "hidden";    
    } 
 }
  
 PortletWindow.prototype.moveBegin = function(e)
 {  
    var x = 0;
    var y = 0;
    if (window.event) {	
    	x = event.clientX;
        y = event.clientY;
    }else {
        x = e.clientX;
        y = e.clientY;
    }
    this.setFocus();
    this.moveable = true;
    this.mouseDownX = x;
    this.mouseDownY = y; 
    this.moveBeginX = x;
    this.moveBeginY = y;
 }
 
 PortletWindow.prototype.moveEnd = function()
 {    
    if(this.moveable){ 
	var xDifference = this.mouseDownX - this.moveBeginX;
        var yDifference = this.mouseDownY - this.moveBeginY;
        if(Math.abs(xDifference) > Math.abs(yDifference)){
	  if(this.mouseDownX > this.moveBeginX)
             this.moveRight();
          else if(this.mouseDownX < this.moveBeginX)
             this.moveLeft();
        }else{
    	  if(this.mouseDownY > this.moveBeginY)
             this.moveDown();
          else if(this.mouseDownY < this.moveBeginY)
             this.moveUp();
        }           
        this.moveable = false;  
    } 	
 }

 PortletWindow.prototype.move = function(e)
 { 	
    if(this.moveable){
        var x = 0;
    	var y = 0;
    	if (window.event) {	
    	   x = event.clientX;
     	   y = event.clientY;
   		}else {
           x = e.clientX;
           y = e.clientY;
    	}   
     
        this.left += x - this.mouseDownX;
        this.top  += y - this.mouseDownY;
        
        this.setPosition();
        this.mouseDownX = x;
        this.mouseDownY = y; 
        
    }
 }

 PortletWindow.prototype.setPosition = function()
 { 
 //alert("304===portlet=="+this.width);
   this.container.style.width = this.width; 
   if (document.all) {	
           this.container.style.posLeft = this.left;
       	   this.container.style.posTop = this.top;
           this.header.style.posLeft = this.left + this.layout.titleRelativeLeft;
       	   this.header.style.posTop = this.top + this.layout.titleRelativeTop;
       	   var RelativeRight = this.layout.minRelativeRight + this.layout.buttonRelative;
       	   if(this.configMode){
           	  this.configButton.style.posLeft = this.left + this.width - RelativeRight;
           	  this.configButton.style.posTop = this.top + this.layout.buttonRelativeTop;
           	  RelativeRight = RelativeRight + this.layout.buttonRelative;
           }
       	   if(this.helpMode){
           	  this.helpButton.style.posLeft = this.left + this.width - RelativeRight;
           	  this.helpButton.style.posTop = this.top + this.layout.buttonRelativeTop;
           	  RelativeRight = RelativeRight + this.layout.buttonRelative;
           }
       	   if(this.editMode){
           	  this.editButton.style.posLeft = this.left + this.width - RelativeRight;
           	  this.editButton.style.posTop = this.top + this.layout.buttonRelativeTop;
           	  RelativeRight = RelativeRight + this.layout.buttonRelative;
           }
           if(this.refreshMode){
           	  this.refreshButton.style.posLeft = this.left + this.width - RelativeRight;
           	  this.refreshButton.style.posTop = this.top + this.layout.buttonRelativeTop;
           }          
	       this.minButton.style.posLeft = this.left + this.width - this.layout.minRelativeRight;
           this.minButton.style.posTop = this.top + this.layout.buttonRelativeTop;
           this.maxButton.style.posLeft = this.left + this.width - this.layout.maxRelativeRight;
           this.maxButton.style.posTop = this.top + this.layout.buttonRelativeTop;                      
           if(this.closeable){
              this.closeButton.style.posLeft = this.left + this.width - this.layout.closeRelativeRight;
      		  this.closeButton.style.posTop = this.top + this.layout.buttonRelativeTop;
    	   } 
    	}    
    	else {        
           this.container.style.left = this.left;
           this.container.style.top = this.top;
           this.header.style.left = this.left + this.layout.titleRelativeLeft;
       	   this.header.style.top = this.top + this.layout.titleRelativeTop;
       	   var RelativeRight = this.layout.minRelativeRight + this.layout.buttonRelative;
           if(this.configMode){
           	  this.configButton.style.left = this.left + this.width - RelativeRight;
           	  this.configButton.style.top = this.top + this.layout.buttonRelativeTop;
           	  RelativeRight = RelativeRight + this.layout.buttonRelative;
           }
           if(this.helpMode){
           	  this.helpButton.style.left = this.left + this.width - RelativeRight;
           	  this.helpButton.style.top = this.top + this.layout.buttonRelativeTop;
           	  RelativeRight = RelativeRight + this.layout.buttonRelative;
           }
       	   if(this.editMode){
           	  this.editButton.style.left = this.left + this.width - RelativeRight;
           	  this.editButton.style.top = this.top + this.layout.buttonRelativeTop;
           	  RelativeRight = RelativeRight + this.layout.buttonRelative;
           }
           if(this.refreshMode){
           	  this.refreshButton.style.left = this.left + this.width - RelativeRight;
           	  this.refreshButton.style.top = this.top + this.layout.buttonRelativeTop;
           }
           //this.upButton.style.left = this.left + this.width - this.layout.upRelativeRight;
           //this.upButton.style.top = this.top + this.layout.buttonRelativeTop;
           //this.downButton.style.left = this.left + this.width - this.layout.downRelativeRight;
           //this.downButton.style.top = this.top + this.layout.buttonRelativeTop;           
           this.minButton.style.left = this.left + this.width - this.layout.minRelativeRight;
           this.minButton.style.top = this.top + this.layout.buttonRelativeTop;
           this.maxButton.style.left = this.left + this.width - this.layout.maxRelativeRight;
           this.maxButton.style.top = this.top + this.layout.buttonRelativeTop;           
           if(this.closeable){
              this.closeButton.style.left = this.left + this.width - this.layout.closeRelativeRight;
              this.closeButton.style.top = this.top + this.layout.buttonRelativeTop;
    	   } 
    	}
 }

PortletWindow.prototype.autoRefresh = function()
 {
   if(this.autoRefreshed){       
       if(this.firstTimeAutoRefreshed){
       	  this.firstTimeAutoRefreshed = false;
       }else{
       	  this.selfRefresh();
       }
       setTimeout("Light.portal.tabs["+this.tIndex+"].portlets["+this.position+"]["+this.index+"].autoRefresh()", this.periodTime);
    }
 }
 
PortletWindow.prototype.selfRefresh = function()
 {   	
   	Light.portal.tabs[this.tIndex].rePositionPortlets(this);    
   	if(this.allowJS){
      var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId
      		    +"&mode="+this.mode;
      if(this.parameter.length > 0)
      	pars = pars+"&"+ this.parameter;
      	
      	pars = pars+"&width="+this.width; 
	    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
    }else{
      if(this.parameter.length > 0){
            ajaxEngine.sendRequest(this.request
      					, "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
                    	, this.parameter
                    	, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                    	, "portletId="+this.serverId
                    	, "mode="+this.mode
                    	, "width="+this.width
                    	);
      }else{          
      		ajaxEngine.sendRequest(this.request
      					, "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      					, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                   		, "portletId="+this.serverId
                   		, "mode="+this.mode
                   		, "width="+this.width
                    	);
      }
    }      
 }

PortletWindow.prototype.refresh = function()
 {
   	var refreshing = "<img src='"+Light.portal.contextPath+"/portal/light/images/spacer.gif' height='8' style='border: 0px' width='300' alt='' /><span class='portlet'>Refreshing</span>";
   	this.container.innerHTML = refreshing;
   	this.round();
   	Light.portal.tabs[this.tIndex].rePositionPortlets(this);    
   	if(this.allowJS){
      var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId
      		    +"&mode="+this.mode;
      if(this.parameter.length > 0)
      	pars = pars+"&"+ this.parameter;
      	
      	pars = pars+"&width="+this.width; 
	    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
    }else{
      if(this.parameter.length > 0){
            ajaxEngine.sendRequest(this.request
      					, "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
                    	, this.parameter
                    	, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                    	, "portletId="+this.serverId
                    	, "mode="+this.mode
                    	, "width="+this.width
                    	);
      }else{          
      		ajaxEngine.sendRequest(this.request
      					, "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      					, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                   		, "portletId="+this.serverId
                   		, "mode="+this.mode
                   		, "width="+this.width
                    	);
      }
    }      
 }
 
PortletWindow.prototype.changePosition = function()
{
   var params = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId
                    +"&column="+this.position
                    +"&row="+this.index
                    +"&width="+this.width;

   new Ajax.Request(Light.portal.contextPath+Light.changePositionRequest,{parameters:params}); 
    //add by zhoucz start 
   var pars="width="+this.width;
   if(this.parameter.length > 0)
      	pars = pars+"&"+ this.parameter;
   ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
   //zhoucz add end
   Light.portal.tabs[this.tIndex].rePositionPortlets(this);
 }

PortletWindow.prototype.changePosition2 = function()
 {
   	Light.portal.tabs[this.tIndex].rePositionPortlets(this);    
   	if(this.allowJS){
      var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId
      		    +"&mode=changePosition"
                    +"&originalMode="+this.mode
                    +"&column="+this.position
                    +"&row="+this.index;
      if(this.parameter.length > 0)
      	pars = pars+"&"+ this.parameter;
      	
      	pars = pars +"&width="+this.width;
	    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
    }else{
      if(this.parameter.length > 0){
            ajaxEngine.sendRequest(this.request
      					, "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
                    	, this.parameter
                    	, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                    	, "portletId="+this.serverId
                    	, "mode=changePosition"
                        , "originalMode="+this.mode
                        , "column="+this.position
                        , "row="+this.index
                        , "width="+this.width
                    	);
      }else{          
      		ajaxEngine.sendRequest(this.request
      					, "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      					, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                   		, "portletId="+this.serverId
                   		, "mode=changePosition"
                        	, "originalMode="+this.mode
                        	, "column="+this.position
                        	, "row="+this.index
                        	, "width="+this.width
                    		);
      }
    }      
 }

PortletWindow.prototype.edit = function()
 {

   if(this.editMode){ 
        this.mode= Light._EDIT_MODE;
        var id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index;
        if(this.allowJS){
	   var pars = "responseId="+id
	                +"&mode="+this.mode
	      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
	      		    +"&portletId="+this.serverId;
           if(this.parameter.length > 0)
      	      pars = pars+"&"+ this.parameter;	      
	   ajaxEngine.sendRequestAndUpdate(this.request,id,{evalScripts: true, parameters: pars});
	}else{
          if(this.parameter.length > 0){
            ajaxEngine.sendRequest(this.request
      		, "responseId="+id
                    	, this.parameter
                    	, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                    	, "portletId="+this.serverId
                    	, "mode="+this.mode
                    	);
          }else{          
      		ajaxEngine.sendRequest(this.request
      		, "responseId="+id
      					, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                   		, "portletId="+this.serverId
                   		, "mode="+this.mode
                    	);
          }	      
	}        	
        var vdocument = document.getElementById('panel_tab_page'+this.tIndex);
    	vdocument.removeChild(this.editButton);
    	this.editButton = this.createPortletCancelEditButton(this);        
        this.editButton.style.visibility = "hidden";
    	vdocument.appendChild(this.editButton);
    	this.setPosition();
        this.editButton.style.visibility = "visible";
    }
 }

PortletWindow.prototype.cancelEdit = function()
 {

   if(this.editMode){
    this.mode= Light._VIEW_MODE;
    if(this.allowJS){
      var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId;
      if(this.parameter.length > 0)
      	pars = pars+"&"+ this.parameter;
	    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
    }else{
	    if(this.parameter.length > 0){   
			ajaxEngine.sendRequest(this.request
							, "responseId="+Light._PC_PREFIX +this.tIndex+"_"+ this.position + "_" + this.index
		                    ,this.parameter
		                    , "tabId="+Light.portal.tabs[this.tIndex].tabServerId
	                    	, "portletId="+this.serverId);
		}else{
		  	ajaxEngine.sendRequest(this.request
						, "responseId="+Light._PC_PREFIX +this.tIndex+"_"+ this.position + "_" + this.index
						, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
	                    , "portletId="+this.serverId);
		}    
    }      
   	
    var vdocument = document.getElementById('panel_tab_page'+this.tIndex);
    vdocument.removeChild(this.editButton);
    this.editButton = this.createPortletEditButton(this);
    this.editButton.style.visibility = "hidden";
    vdocument.appendChild(this.editButton);
    this.setPosition();
    this.editButton.style.visibility = "visible";
   }
 }
 
PortletWindow.prototype.help = function()
 {

   if(this.helpMode){ 
        this.mode = Light._HELP_MODE;   
        var id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index;
        if(this.allowJS){
	   var pars = "responseId="+id
	                +"&mode="+this.mode
	      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
	      		    +"&portletId="+this.serverId;
           if(this.parameter.length > 0)
      	      pars = pars+"&"+ this.parameter;	      
	   ajaxEngine.sendRequestAndUpdate(this.request,id,{evalScripts: true, parameters: pars});
	}else{
          if(this.parameter.length > 0){
            ajaxEngine.sendRequest(this.request
      		, "responseId="+id
                    	, this.parameter
                    	, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                    	, "portletId="+this.serverId
                    	, "mode="+this.mode
                    	);
          }else{          
      		ajaxEngine.sendRequest(this.request
      		, "responseId="+id
      					, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                   		, "portletId="+this.serverId
                   		, "mode="+this.mode
                    	);
          }	      
	}         
    	var vdocument = document.getElementById('panel_tab_page'+this.tIndex);
    	vdocument.removeChild(this.helpButton);
    	this.helpButton = this.createPortletCancelHelpButton(this);
    	this.helpButton.style.visibility = "hidden";
        vdocument.appendChild(this.helpButton);
    	this.setPosition();
        this.helpButton.style.visibility = "visible";
    }
 }
 
PortletWindow.prototype.cancelHelp = function()
 {

   if(this.helpMode){
    this.mode = Light._VIEW_MODE;
    if(this.allowJS){
      var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId;
      if(this.parameter.length > 0)
      	pars = pars+"&"+ this.parameter;
	    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
    }else{
	    if(this.parameter.length > 0){   
			ajaxEngine.sendRequest(this.request
							, "responseId="+Light._PC_PREFIX +this.tIndex+"_"+ this.position + "_" + this.index
		                    ,this.parameter
		                    , "tabId="+Light.portal.tabs[this.tIndex].tabServerId
	                    	, "portletId="+this.serverId);
		}else{
		  	ajaxEngine.sendRequest(this.request
						, "responseId="+Light._PC_PREFIX +this.tIndex+"_"+ this.position + "_" + this.index
						, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
	                    , "portletId="+this.serverId);
		}    
    }    
    
    var vdocument = document.getElementById('panel_tab_page'+this.tIndex);
    vdocument.removeChild(this.helpButton);
    this.helpButton = this.createPortletHelpButton(this);
    this.helpButton.style.visibility = "hidden";
    vdocument.appendChild(this.helpButton);
    this.setPosition();
    this.helpButton.style.visibility = "visible";
   }
 }

PortletWindow.prototype.config = function()
 {
   if(this.configMode){ 
        this.mode = Light._CONFIG_MODE;   
        var id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index;
        if(this.allowJS){
	   var pars = "responseId="+id
	                +"&mode="+this.mode
	      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
	      		    +"&portletId="+this.serverId;
           if(this.parameter.length > 0)
      	      pars = pars+"&"+ this.parameter;	 
      	        
	   ajaxEngine.sendRequestAndUpdate(this.request,id,{evalScripts: true, parameters: pars});
	}else{
          if(this.parameter.length > 0){
            ajaxEngine.sendRequest(this.request
      		, "responseId="+id
                    	, this.parameter
                    	, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                    	, "portletId="+this.serverId
                    	, "mode="+this.mode
                    	);
          }else{          
      		ajaxEngine.sendRequest(this.request
      		, "responseId="+id
      					, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                   		, "portletId="+this.serverId
                   		, "mode="+this.mode
                    	);
          }	      
	}   
    	var vdocument = document.getElementById('panel_tab_page'+this.tIndex);
    	vdocument.removeChild(this.configButton);
    	this.configButton = this.createPortletCancelConfigButton(this);
        this.configButton.style.visibility = "hidden";
    	vdocument.appendChild(this.configButton);
    	this.setPosition();
        this.configButton.style.visibility = "visible";
    }
 }
 
PortletWindow.prototype.cancelConfig = function()
 {
   if(this.configMode){
    this.mode = Light._VIEW_MODE;
    if(this.allowJS){
      var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId;
      if(this.parameter.length > 0)
      	pars = pars+"&"+ this.parameter;
	    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
    }else{
	    if(this.parameter.length > 0){   
			ajaxEngine.sendRequest(this.request
							, "responseId="+Light._PC_PREFIX +this.tIndex+"_"+ this.position + "_" + this.index
		                    ,this.parameter
		                    , "tabId="+Light.portal.tabs[this.tIndex].tabServerId
	                    	, "portletId="+this.serverId);
		}else{
		  	ajaxEngine.sendRequest(this.request
						, "responseId="+Light._PC_PREFIX +this.tIndex+"_"+ this.position + "_" + this.index
						, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
	                    , "portletId="+this.serverId);
		}    
    }    
    
    var vdocument = document.getElementById('panel_tab_page'+this.tIndex);
    vdocument.removeChild(this.configButton);
    this.configButton = this.createPortletConfigButton(this);
    this.configButton.style.visibility = "hidden";
    vdocument.appendChild(this.configButton);
    this.setPosition();
    this.configButton.style.visibility = "visible";
   }
 }

PortletWindow.prototype.moveUp = function()
 {
   if(this.index > 0){
     var temp = null;
     var upIndex = 0;
     var currentIndex = this.index;
     var started = this.index - 1;                 
        for(var i=started;i>=0;i--){
           if(Light.portal.tabs[this.tIndex].portlets[this.position][i] != null){
             temp = Light.portal.tabs[this.tIndex].portlets[this.position][i];
             upIndex = i;
             break;
           }
        }               
        if(temp != null){
          temp.index = this.index;
          this.index = upIndex;
          temp.container.id = Light._PC_PREFIX+this.tIndex+"_"+temp.position+"_"+temp.index;          
          this.container.id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+this.index;  
          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+temp.position+"_"+temp.index);
          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+ this.position + "_" + this.index);        
          Light.portal.tabs[this.tIndex].portlets[this.position][upIndex] = this;
          Light.portal.tabs[this.tIndex].portlets[this.position][currentIndex] = temp; 
          temp.changePosition();   
          this.left = Light.portal.tabs[this.tIndex].between;
    	  for(var i=0;i<this.position;i++) { 
        	this.left += Light.portal.tabs[this.tIndex].widths[i] + Light.portal.tabs[this.tIndex].between;
    	  }
          this.changePosition();      
          //Light.portal.tabs[this.tIndex].rePositionPortlets(this);
        }     
   }
 }
 
 PortletWindow.prototype.moveDown = function()
 {
    var temp = null;
    var downIndex = 0;
    var currentIndex = this.index;
    var started = this.index + 1;                  
    for(var i=started;i<Light.portal.tabs[this.tIndex].portlets[this.position].length;i++){
       if(Light.portal.tabs[this.tIndex].portlets[this.position][i] != null){
         temp = Light.portal.tabs[this.tIndex].portlets[this.position][i];
         downIndex = i;
         break;
       }
    }               
    if(temp != null){
      temp.index = this.index;
      this.index = downIndex;
      temp.container.id = Light._PC_PREFIX+this.tIndex+"_"+temp.position+"_"+temp.index;          
      this.container.id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+this.index;          
      ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+temp.position+"_"+temp.index);
      ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+ this.position + "_" + this.index);        
      Light.portal.tabs[this.tIndex].portlets[this.position][downIndex] = this;
      Light.portal.tabs[this.tIndex].portlets[this.position][currentIndex] = temp;          
      this.changePosition();
      this.left = Light.portal.tabs[this.tIndex].between;
    	  for(var i=0;i<this.position;i++) { 
        	this.left += Light.portal.tabs[this.tIndex].widths[i] + Light.portal.tabs[this.tIndex].between;
    	  }
      temp.changePosition();             
      //Light.portal.tabs[this.tIndex].rePositionPortlets(temp);
    }    
 }
 
 PortletWindow.prototype.moveLeft = function()
 {
   if(this.position > 0){
     var temp = null;
     var newIndex = 0;
     var currentPosition = this.position;
     var currentIndex = this.index;
     var column = this.position - 1; 
     if(Light.portal.tabs[this.tIndex].portlets[column] == null)
        Light.portal.tabs[this.tIndex].portlets[column] = new Array();
     var len = Light.portal.tabs[this.tIndex].portlets[column].length;   
     for(var i=0;i<len;i++){
       if(Light.portal.tabs[this.tIndex].portlets[column][i] != null 
       && this.top < Light.portal.tabs[this.tIndex].portlets[column][i].top){
            temp = Light.portal.tabs[this.tIndex].portlets[column][i];
            newIndex = temp.index;
            break;
       }
     }                    
                
        if(temp != null){
          for(var i= len - 1;i>=0;i--){
	       if(Light.portal.tabs[this.tIndex].portlets[column][i] != null){
	          var temp2 = Light.portal.tabs[this.tIndex].portlets[column][i];
	          temp2.index++;
	          temp2.container.id = Light._PC_PREFIX+temp2.tIndex+"_"+temp2.position+"_"+temp2.index;  
	          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+temp2.tIndex+"_"+temp2.position+"_"+temp2.index);
	          Light.portal.tabs[this.tIndex].portlets[column][i + 1]= temp2;
	          Light.portal.tabs[this.tIndex].portlets[column][i + 1].changePosition();
	          Light.portal.tabs[this.tIndex].portlets[column][i]= null;
                  if(temp2.serverId == temp.serverId) break;	         
	       }
	      }   
	      this.position = column;
              this.index = newIndex;                            
          this.container.id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+this.index;           
          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+ this.position + "_" + this.index);        
          Light.portal.tabs[this.tIndex].portlets[this.position][this.index] = this;          
          Light.portal.tabs[this.tIndex].portlets[currentPosition][currentIndex] = null; 
          this.left = Light.portal.tabs[this.tIndex].between;
    	  for(var i=0;i<this.position;i++) { 
        	this.left += Light.portal.tabs[this.tIndex].widths[i] + Light.portal.tabs[this.tIndex].between;
    	  }
          this.width = Light.portal.tabs[this.tIndex].widths[this.position];  
          this.changePosition();                     
        }else{
          this.position = column;  
          var isEmpty = true;
          for(var i= len - 1;i>=0;i--){
	       if(Light.portal.tabs[this.tIndex].portlets[column][i] != null){
	          var temp3 = Light.portal.tabs[this.tIndex].portlets[column][i];
                  this.index = temp3.index + 1;
                  isEmpty = false;
	          break;	         
	       }
	  }          
          if(isEmpty) this.index = 0;
          this.container.id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+this.index;  
          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+ this.position + "_" + this.index);        
          Light.portal.tabs[this.tIndex].portlets[this.position][this.index] = this;   
          Light.portal.tabs[this.tIndex].portlets[currentPosition][currentIndex] = null;        
          this.left = Light.portal.tabs[this.tIndex].between;
    	  for(var i=0;i<this.position;i++) { 
        	this.left += Light.portal.tabs[this.tIndex].widths[i] + Light.portal.tabs[this.tIndex].between;
    	  }
	  this.width = Light.portal.tabs[this.tIndex].widths[this.position];
          this.changePosition();        
        }
      var length = Light.portal.tabs[this.tIndex].portlets[currentPosition].length;   
     for(var i=currentIndex+1;i<length;i++){
       if(Light.portal.tabs[this.tIndex].portlets[currentPosition][i] != null){
         var next = Light.portal.tabs[this.tIndex].portlets[currentPosition][i];
         Light.portal.tabs[this.tIndex].rePositionPortlets(next);
         break;
       }
     }          
   }
 }
 
 PortletWindow.prototype.moveRight = function()
 {      
  columns = Light.portal.tabs[this.tIndex].widths.length;
  if(this.position + 1 < columns 
    && Light.portal.tabs[this.tIndex].portlets[this.position + 1] == null)
    Light.portal.tabs[this.tIndex].portlets[this.position + 1] = new Array();
  if(Light.portal.tabs[this.tIndex].portlets[this.position + 1] != null){
     var temp = null;
     var newIndex = 0;
     var currentPosition = this.position;
     var currentIndex = this.index;
     var column = this.position + 1;              
     var len = Light.portal.tabs[this.tIndex].portlets[column].length;   
     for(var i=0;i<len;i++){
       if(Light.portal.tabs[this.tIndex].portlets[column][i] != null
       && this.top < Light.portal.tabs[this.tIndex].portlets[column][i].top){
            temp = Light.portal.tabs[this.tIndex].portlets[column][i];
            newIndex = temp.index;
            break;
       }
     }               
     if(temp != null){                    
          for(var i= len - 1;i>=0;i--){
	       if(Light.portal.tabs[this.tIndex].portlets[column][i] != null){
	          var temp2 = Light.portal.tabs[this.tIndex].portlets[column][i];
	          temp2.index++;
	          temp2.container.id = Light._PC_PREFIX+temp2.tIndex+"_"+temp2.position+"_"+temp2.index;  
	          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+temp2.tIndex+"_"+temp2.position+"_"+temp2.index);
	          Light.portal.tabs[this.tIndex].portlets[column][i + 1]= temp2;
	          Light.portal.tabs[this.tIndex].portlets[column][i + 1].changePosition();
	          Light.portal.tabs[this.tIndex].portlets[column][i]= null;
                  if(temp2.serverId == temp.serverId) break;	         
	       }
	      }   
	      this.position = column;
              this.index = newIndex;                    
          this.container.id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+this.index;  
          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+ this.position + "_" + this.index);                  
          Light.portal.tabs[this.tIndex].portlets[this.position][this.index] = this;          
	  Light.portal.tabs[this.tIndex].portlets[currentPosition][currentIndex] = null; 
          this.left = Light.portal.tabs[this.tIndex].between;
    	  for(var i=0;i<this.position;i++) { 
        	this.left += Light.portal.tabs[this.tIndex].widths[i] + Light.portal.tabs[this.tIndex].between;
    	  }
          this.width = Light.portal.tabs[this.tIndex].widths[this.position]; 
          this.changePosition();    
        }else{
          this.position = column;
          var isEmpty = true;
          for(var i= len - 1;i>=0;i--){
	       if(Light.portal.tabs[this.tIndex].portlets[column][i] != null){
	          var temp3 = Light.portal.tabs[this.tIndex].portlets[column][i];
                  this.index = temp3.index + 1;
                  isEmpty = false;
	          break;	         
	       }
	  }          
          if(isEmpty) this.index = 0;        
          this.container.id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+this.index;  
          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+ this.position + "_" + this.index);        
          Light.portal.tabs[this.tIndex].portlets[this.position][this.index] = this;
          Light.portal.tabs[this.tIndex].portlets[currentPosition][currentIndex] = null; 
          this.left = Light.portal.tabs[this.tIndex].between;
    	  for(var i=0;i<this.position;i++) { 
        	this.left += Light.portal.tabs[this.tIndex].widths[i] + Light.portal.tabs[this.tIndex].between;
    	  }
	      this.width = Light.portal.tabs[this.tIndex].widths[this.position];
          this.changePosition();          
        }    
     var length = Light.portal.tabs[this.tIndex].portlets[currentPosition].length;   
     for(var i=currentIndex+1;i<length;i++){
       if(Light.portal.tabs[this.tIndex].portlets[currentPosition][i] != null){
         var next = Light.portal.tabs[this.tIndex].portlets[currentPosition][i];
         Light.portal.tabs[this.tIndex].rePositionPortlets(next);
         break;
       }
     }      
   }
 }

 PortletWindow.prototype.minimize = function()
 { 
   this.minimized = !this.minimized;
   var vdocument = document.getElementById('panel_tab_page'+this.tIndex);
   if(this.minimized){
    if(this.maximized){
       this.maximize();   
       this.minimized = true;     
    }
    var empty = "<img src='"+Light.portal.contextPath+"/portal/light/images/spacer.gif'"
		+" height='20' style='border: 0px' width='200' alt='' />";
    //this.body = this.container.innerHTML;
    this.container.innerHTML = empty;   
    vdocument.removeChild(this.minButton);
    this.minButton = this.createPortletRestoreMinButton(this);
    vdocument.appendChild(this.minButton); 
    this.round();
       
  }else{   
      var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId;
      if(this.parameter.length > 0)
      	pars = pars+"&"+ this.parameter;
      if(this.mode != Light._VIEW_MODE)
    	pars = pars+"&mode="+this.mode;
    	
    	pars = pars +"&width="+this.width;
      if(this.allowJS){
	    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
    }else{
	    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: false, parameters: pars});
    }    

    vdocument.removeChild(this.minButton);
    this.minButton = this.createPortletMinButton(this);
    vdocument.appendChild(this.minButton);    
    //this.container.innerHTML = this.body;
    
  }
  
  vdocument.removeChild(this.maxButton);
  this.maximized = false;
  this.maxButton = this.createPortletMaxButton(this);
  vdocument.appendChild(this.maxButton); 
  this.setPosition();  
  Light.portal.tabs[this.tIndex].rePositionPortlets(this);
 }
 
 PortletWindow.prototype.maximize = function()
 { 
   var empty = "<img src='"+Light.portal.contextPath+"/portal/light/images/spacer.gif'"
		+" height='20' style='border: 0px' width='200' alt='' />";
   this.container.innerHTML = empty;
   var vdocument = document.getElementById('panel_tab_page'+this.tIndex);
   this.maximized = !this.maximized; 
   if(!this.maximized){   
      Light.portal.tabs[this.tIndex].showOtherPortlets();	
      var height = 0;
      var maxIndex = 0;
      var nullNum = 0;      
      for(var i=0;i<Light.portal.tabs[this.tIndex].portlets[this.position].length;i++) {        
          if(i == this.index){
             break;
          }
 		  if(Light.portal.tabs[this.tIndex].portlets[this.position][i] != null && !Light.portal.tabs[this.tIndex].portlets[this.position][i].maximized){
             height += Light.portal.tabs[this.tIndex].portlets[this.position][i].container.clientHeight;   
          } 
          if(Light.portal.tabs[this.tIndex].portlets[this.position][i] == null){
             nullNum++;
          }
          if(Light.portal.tabs[this.tIndex].portlets[this.position][i] != null && Light.portal.tabs[this.tIndex].portlets[this.position][i].maximized){
             height = Light.portal.tabs[this.tIndex].portlets[this.position][i].container.clientHeight;     
             maxIndex = i;
             nullNum = 0;
          }              
    	}  
    	this.top  = Light.portal.layout.normalTop + height + this.layout.rowBetween * (i - maxIndex - nullNum); 	
    	this.width = Light.portal.tabs[this.tIndex].widths[this.position];
	    this.left = Light.portal.tabs[this.tIndex].between;
	    for(var i=0;i<this.position;i++) { 
	        this.left += Light.portal.tabs[this.tIndex].widths[i] + Light.portal.tabs[this.tIndex].between;
	    }  
     if(!this.minimized){
	    var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
	      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
	      		    +"&portletId="+this.serverId;
	    if(this.parameter.length > 0)
	      	pars = pars+"&"+ this.parameter;
	    if(this.mode != Light._VIEW_MODE)
    		pars = pars+"&mode="+this.mode; 
    		
    		pars = pars +"&width="+this.width;	
		if(this.allowJS){    
		    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
	    }else{
		    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: false, parameters: pars}); 
	    }              
     }  
     vdocument.removeChild(this.maxButton);
     this.maxButton = this.createPortletMaxButton(this);
     vdocument.appendChild(this.maxButton);
     Light.portal.container.style.zIndex= 1;
   }else{        
        Light.portal.tabs[this.tIndex].hideOtherPortlets();        
   	    this.left = Light.portal.layout.maxLeft;
   	    this.top = Light.portal.layout.maxTop;
        this.width = Light.portal.layout.maxWidth;        
	
        vdocument.removeChild(this.maxButton);
    	this.maxButton = this.createPortletRestoreMaxButton(this);
    	vdocument.appendChild(this.maxButton);
        
        Light.portal.container.style.zIndex= ++Light.maxZIndex; 
        Light.portal.body.style.zIndex= ++Light.maxZIndex;
        Light.portal.footer.style.zIndex= ++Light.maxZIndex;
        
      	var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId
      		    +"&state=maximized";
      	if(this.parameter.length > 0)
      		pars = pars+"&"+ this.parameter;
      	if(this.mode != Light._VIEW_MODE)
    		pars = pars+"&mode="+this.mode; 
    		
    		pars = pars +"&width="+this.width;

   		if(this.allowJS){
	  		ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
	    }else{
		    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: false, parameters: pars});
	    }            
   }        
   vdocument.removeChild(this.minButton);
   this.minimized = false;
   this.minButton = this.createPortletMinButton(this);
   vdocument.appendChild(this.minButton);     
   this.setPosition();
   this.setFocus();   
   Light.portal.tabs[this.tIndex].rePositionPortlets(this);
 }

 PortletWindow.prototype.close = function()
 {
   var closePortlet = confirm(LightResourceBundle._COMMAND_CLOSE_PORTLET);
   if (!closePortlet) // user cancelled close closePortlet
	{
		return;
	}
   if(this.maximized)   
     Light.portal.tabs[this.tIndex].showOtherPortlets();    
   var vdocument = document.getElementById('panel_tab_page'+this.tIndex); 
   vdocument.removeChild(this.container);
   vdocument.removeChild(this.header);
   if(this.refreshMode){
      vdocument.removeChild(this.refreshButton);
   }
   if(this.editMode){
      vdocument.removeChild(this.editButton);
   }
   if(this.helpMode){
      vdocument.removeChild(this.helpButton);
   }
   if(this.configMode){
      vdocument.removeChild(this.configButton);
   }
   //vdocument.removeChild(this.upButton);
   //vdocument.removeChild(this.downButton);   
   vdocument.removeChild(this.minButton);
   vdocument.removeChild(this.maxButton);
   vdocument.removeChild(this.closeButton);
   Light.portal.tabs[this.tIndex].portlets[this.position][this.index] = null;   
   Light.portal.tabs[this.tIndex].rePositionPortlets(this);
    
   new Ajax.Request(Light.portal.contextPath+Light.deletePortletRequest, {parameters:'portletId='+this.serverId});    
   
 }
 
 PortletWindow.prototype.refreshWindow= function () { 
   var vdocument = document.getElementById('panel_tab_page'+this.tIndex);
   vdocument.removeChild(this.header);
   vdocument.removeChild(this.container);
   this.container = this.createPortletContainer(this);  
   this.header = this.createPortletHeader(this);    
   vdocument.appendChild(this.header);
   vdocument.appendChild(this.container);
   ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+ this.position + "_" + this.index);
   this.setPosition();
   this.setFocus();  
 }
 
 PortletWindow.prototype.refreshHeader= function () {
 
   var vdocument = document.getElementById('panel_tab_page'+this.tIndex);
   vdocument.removeChild(this.header);
   this.header = this.createPortletHeader(this);
   if (document.all) {	          
      this.header.style.posLeft = this.left + this.layout.titleRelativeLeft;
      this.header.style.posTop = this.top + this.layout.titleRelativeTop;
   }else{
      this.header.style.left = this.left + this.layout.titleRelativeLeft;
      this.header.style.top = this.top + this.layout.titleRelativeTop;
   }
   vdocument.appendChild(this.header);
 }
 
 PortletWindow.prototype.createPortletContainer = function (portlet) {

   var vContainer = document.createElement('div');
   vContainer.id = Light._PC_PREFIX+portlet.tIndex+"_"+portlet.position+"_"+portlet.index;
   vContainer.style.position = "absolute";
   vContainer.className = "portlet"; 
   vContainer.style.width = portlet.width;
   vContainer.style.zIndex= ++Light.maxZIndex;
   if(this.contentBgColor.length > 0)
   	  vContainer.style.backgroundColor = this.contentBgColor;
   return vContainer;
  }
  
  PortletWindow.prototype.createPortletHeader = function (portlet) {
   var vHeader = document.createElement('div');
   //vHeader.id = "portletheader_"+portlet.position+"_"+portlet.index;
   vHeader.style.position = "absolute";
   vHeader.className = "portlet-header";
   
   if(Light.portal.tabs[this.tIndex].tabIsMoveable){
   	vHeader.onmousedown = function(e){ portlet.moveBegin(e);}
   	vHeader.onmousemove = function(e){ portlet.move(e);}
   	vHeader.onmouseup   = function(){ portlet.moveEnd();}
   	vHeader.onmouseout  = function(){ portlet.moveEnd();}
        vHeader.style.cursor= "move";
   }

   var inner = "";
   if(portlet.icon.length > 0){
      if(portlet.icon.substring(0,4) == "http")
      	inner = "<img src='"+portlet.icon+"'/>&nbsp;";
      else
        inner = "<img src='"+Light.portal.contextPath+portlet.icon+"'/>&nbsp;";
   }
   if(portlet.url.length > 0){
      inner = inner+"<a href='"+portlet.url+"' target='_blank'>";
      if(this.barFontColor.length > 0)
         inner = inner+"<font color='"+this.barFontColor+"'>";
      inner = inner+portlet.title;
      if(this.barFontColor.length > 0)
         inner = inner+"</font>";
      inner = inner+"</a>";
   }else
      inner = inner+portlet.title;  
   vHeader.innerHTML = inner;

   vHeader.style.zIndex= ++Light.maxZIndex;  
   if(this.barBgColor.length > 0)
   	  vHeader.style.backgroundColor = this.barBgColor;
   if(this.barFontColor.length > 0)
   	  vHeader.style.color = this.barFontColor;    
   return vHeader;
  }
  
  PortletWindow.prototype.createPortletRefreshButton = function (portlet) { 
   var strIco="<span title='"+LightResourceBundle._REFRESH_PORTLET+"'><img src='"+Light.portal.contextPath+"/portal/light/images/refresh_on.gif' style='border: 0px;'/></span>";   
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('a');
   varA.innerHTML = strIco;
   varA.href = "javascript:void(0)";
   varA.onclick = function(){
     portlet.refresh();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }
  
  PortletWindow.prototype.createPortletEditButton = function (portlet) { 
   var strIco="<span title='"+LightResourceBundle._EDIT_MODE+"'><img src='"+Light.portal.contextPath+"/portal/light/images/edit_on.gif' style='border: 0px;'/></span>";    
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('a');
   varA.innerHTML = strIco;
   varA.href = "javascript:void(0)";
   varA.onclick = function(){
     portlet.edit();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }
  
  PortletWindow.prototype.createPortletCancelEditButton = function (portlet) { 
   var strIco="<span title='"+LightResourceBundle._VIEW_MODE+"'><img src='"+Light.portal.contextPath+"/portal/light/images/leave_edit_on.gif' style='border: 0px;'/></span>";   
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('a');
   varA.innerHTML = strIco;
   varA.href = "javascript:void(0)";
   varA.onclick = function(){
     portlet.cancelEdit();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }

  PortletWindow.prototype.createPortletHelpButton = function (portlet) { 
   var strIco="<span title='"+LightResourceBundle._HELP_MODE+"'><img src='"+Light.portal.contextPath+"/portal/light/images/help_on.gif' style='border: 0px;'/></span>";   
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('a');
   varA.innerHTML = strIco;
   varA.href = "javascript:void(0)";
   varA.onclick = function(){
     portlet.help();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }
  
  PortletWindow.prototype.createPortletCancelHelpButton = function (portlet) { 
   var strIco="<span title='"+LightResourceBundle._VIEW_MODE+"'><img src='"+Light.portal.contextPath+"/portal/light/images/leave_help_on.gif' style='border: 0px;'/></span>";   
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('a');
   varA.innerHTML = strIco;
   varA.href = "javascript:void(0)";
   varA.onclick = function(){
     portlet.cancelHelp();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }

  PortletWindow.prototype.createPortletConfigButton = function (portlet) { 
   var strIco="<span title='"+LightResourceBundle._CONFIG_MODE+"'><img src='"+Light.portal.contextPath+"/portal/light/images/config_on.gif' style='border: 0px;'/></span>";   
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('a');
   varA.innerHTML = strIco;
   varA.href = "javascript:void(0)";
   varA.onclick = function(){
     portlet.config();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }
  
  PortletWindow.prototype.createPortletCancelConfigButton = function (portlet) { 
   var strIco="<span title='"+LightResourceBundle._VIEW_MODE+"'><img src='"+Light.portal.contextPath+"/portal/light/images/leave_config_on.gif' style='border: 0px;'/></span>";   
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('a');
   varA.innerHTML = strIco;
   varA.href = "javascript:void(0)";
   varA.onclick = function(){
     portlet.cancelConfig();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }
  
  PortletWindow.prototype.createPortletUpButton = function (portlet) { 
   var strIco="<span title='"+LightResourceBundle._MOVE_UP+"'><img src='"+Light.portal.contextPath+"/portal/light/images/up_on.gif' style='border: 0px;'/></span>";   
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var minA = document.createElement('a');
   minA.innerHTML = strIco;
   minA.href = "javascript:void(0)";
   minA.onclick = function(){
     portlet.moveUp();
   }
   vButton.appendChild(minA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }
   
  PortletWindow.prototype.createPortletDownButton = function (portlet) { 
   var strIco="<span title='"+LightResourceBundle._MOVE_DOWN+"'><img src='"+Light.portal.contextPath+"/portal/light/images/down_on.gif' style='border: 0px;'/></span>";   
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var minA = document.createElement('a');
   minA.innerHTML = strIco;
   minA.href = "javascript:void(0)";
   minA.onclick = function(){
     portlet.moveDown();
   }
   vButton.appendChild(minA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }

  PortletWindow.prototype.createPortletMinButton = function (portlet) { 
   var strIcoMin="<span title='"+LightResourceBundle._MINIMIZED+"'><img src='"+Light.portal.contextPath+"/portal/light/images/min_on.gif' style='border: 0px;'/></span>";   
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var minA = document.createElement('a');
   minA.innerHTML = strIcoMin;
   minA.href = "javascript:void(0)";
   minA.onclick = function(){
     portlet.minimize();
   }
   vButton.appendChild(minA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }
  
  PortletWindow.prototype.createPortletRestoreMinButton = function (portlet) { 
   var strIcoMin = "<span title='"+LightResourceBundle._RESTORE_MINIMIZED+"'><img src='"+Light.portal.contextPath+"/portal/light/images/restore_on.gif' style='border: 0px'/></span>";   
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var minA = document.createElement('a');
   minA.innerHTML = strIcoMin;
   minA.href = "javascript:void(0)";
   minA.onclick = function(){
     portlet.minimize();
   }
   vButton.appendChild(minA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }

  PortletWindow.prototype.createPortletMaxButton = function (portlet) {   
   var strIcoMax = "<span title='"+LightResourceBundle._MAXIMIZED+"'><img src='"+Light.portal.contextPath+"/portal/light/images/max_on.gif' style='border: 0px'/></span>";         
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var maxA = document.createElement('a');
   maxA.innerHTML = strIcoMax;
   maxA.href = "javascript:void(0)";
   maxA.onclick = function(){
     portlet.maximize();
   }
   vButton.appendChild(maxA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }
  
  PortletWindow.prototype.createPortletRestoreMaxButton = function (portlet) {   
   var strIcoMax = "<span title='"+LightResourceBundle._RESTORE_MAXIMIZED+"'><img src='"+Light.portal.contextPath+"/portal/light/images/restore_on.gif' style='border: 0px'/></span>";         
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var maxA = document.createElement('a');
   maxA.innerHTML = strIcoMax;
   maxA.href = "javascript:void(0)";
   maxA.onclick = function(){
     portlet.maximize();
   }
   vButton.appendChild(maxA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }

  PortletWindow.prototype.createPortletCloseButton = function (portlet)  {      
   var strIcoCls = "<span title='"+LightResourceBundle._CLOSE+"'><img src='"+Light.portal.contextPath+"/portal/light/images/close_on.gif' style='border: 0px'/></span>";          
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var clsA = document.createElement('a');
   clsA.innerHTML = strIcoCls;
   clsA.href = "javascript:void(0)";
   clsA.onclick = function(){
     portlet.close();
   }
   vButton.appendChild(clsA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }

  PortletWindow.prototype.createPortletBody = function (portlet) {   
   var vBody = document.createElement('div');
   vBody.id = 'portletbody_'+portlet.position+"_"+portlet.index;
   vBody.className = "portlet-body"; 
   vBody.onmousedown = function(){
      portlet.setFocus();   
   }
   return vBody;
  }
  

//------------------------------------------------------------ PortletWindow2.js
 PortletWindow2 = function (tIndex,serverId,position,title,icon,url,request,requestUrl,closeable,refreshMode,editMode,helpMode,configMode,autoRefreshed,periodTime,allowJS,barBgColor,barFontColor,contentBgColor,parameter) {
   this.layout={	     	    	    
	    rowBetween : 40  
   }
   this.mode = Light._VIEW_MODE;
   this.state = Light._NORMAL_STATE;
   this.tIndex = tIndex;
   this.serverId = serverId;
   this.position = position;
   this.title = title;
   this.icon = icon;
   this.url = url;
   this.request = request;
   this.requestUrl = requestUrl;
   this.closeable = closeable;
   this.refreshMode = refreshMode;
   this.editMode = editMode;
   this.helpMode = helpMode;
   this.configMode = configMode;
   this.autoRefreshed = autoRefreshed;
   this.periodTime = periodTime;
   this.allowJS = allowJS;
   this.barBgColor = barBgColor;
   this.barFontColor = barFontColor;
   this.contentBgColor = contentBgColor;
   this.parameter = parameter;
   this.index = Light.portal.tabs[this.tIndex].getPortletIndex(this.position);
   var height = 0;
   var maxIndex = 0;
   var nullNum = 0;        
   for(var i=0;i<Light.portal.tabs[this.tIndex].portlets[this.position].length;i++) {        
      if(i == this.index){
         break;
      }
  	  if(Light.portal.tabs[this.tIndex].portlets[this.position][i] != null && !Light.portal.tabs[this.tIndex].portlets[this.position][i].maximized){
         height += Light.portal.tabs[this.tIndex].portlets[this.position][i].container.clientHeight;   
      } 
      if(Light.portal.tabs[this.tIndex].portlets[this.position][i] == null){
         nullNum++;
      }
      if(Light.portal.tabs[this.tIndex].portlets[this.position][i] != null && Light.portal.tabs[this.tIndex].portlets[this.position][i].maximized){
         height = Light.portal.tabs[this.tIndex].portlets[this.position][i].container.clientHeight;     
         maxIndex = i;
         nullNum = 0;
      }              
    }
    this.top  = Light.portal.layout.normalTop + height + this.layout.rowBetween * (i - maxIndex - nullNum);  
    this.width = Light.portal.tabs[this.tIndex].widths[this.position];
    this.left = Light.portal.tabs[this.tIndex].between;
    for(var i=0;i<this.position;i++) { 
        this.left += Light.portal.tabs[this.tIndex].widths[i] + Light.portal.tabs[this.tIndex].between;
    }
    
    Light.portal.tabs[this.tIndex].portlets[this.position][this.index] = this;     
   
   this.window = this.createPortletWindow(this);
   this.container = this.createPortletContainer(this); 
   this.header = this.createPortletHeader(this);     
   
   var vdocument = document.getElementById('panel_tab_page'+this.tIndex);
   this.window.appendChild(this.header); 
   this.window.appendChild(this.container);     
   vdocument.appendChild(this.window);
   
   this.minimized = false;
   this.maximized = false;
   this.moveable = false;
   this.mouseDownX = 0;
   this.mouseDownY = 0;
   this.setPosition();
   var loading = "<img src='"+Light.portal.contextPath+"/portal/light/images/spacer.gif'"
		+" height='8' style='border: 0px' width='200' alt='' />"
                +"<span class='portlet'>Loading</span>";
   this.container.innerHTML = loading;
   this.round();
   Light.portal.tabs[this.tIndex].rePositionPortlets(this); 
   if(this.autoRefreshed){
   	  this.firstTimeAutoRefreshed = true;
   	  this.autoRefresh();
   }
 }  
 
 PortletWindow2.prototype.round = function(){   
 }
 
 PortletWindow2.prototype.setFocus = function()
 {
    this.window.style.zIndex= ++Light.maxZIndex;    
 }
 
 PortletWindow2.prototype.show = function(){
 	this.window.style.visibility = "visible";
 }
 
 PortletWindow2.prototype.hide = function(){
 	this.window.style.visibility = "hidden";  
 }
  
 PortletWindow2.prototype.moveBegin = function(e)
 {  
    var x = 0;
    var y = 0;
    if (window.event) {	
    	x = event.clientX;
        y = event.clientY;
    }else {
        x = e.clientX;
        y = e.clientY;
    }
    this.setFocus();
    this.moveable = true;
    this.mouseDownX = x;
    this.mouseDownY = y; 
    this.moveBeginX = x;
    this.moveBeginY = y;
 }

 PortletWindow2.prototype.moveCancel = function()
 {    
    this.buttonIsClicked = true;
 }

 PortletWindow2.prototype.moveEnd = function()
 {    
    if(this.moveable){ 
	var xDifference = this.mouseDownX - this.moveBeginX;
        var yDifference = this.mouseDownY - this.moveBeginY;
        if(Math.abs(xDifference) > Math.abs(yDifference)){
	  if(this.mouseDownX > this.moveBeginX)
             this.moveRight();
          else if(this.mouseDownX < this.moveBeginX)
             this.moveLeft();
        }else{
    	  if(this.mouseDownY > this.moveBeginY)
             this.moveDown();
          else if(this.mouseDownY < this.moveBeginY)
             this.moveUp();
        }   
        this.moveable = false; 
        Light.portal.tabs[this.tIndex].refresh(); 
    } 	
    this.buttonIsClicked = false;
 }
  
 PortletWindow2.prototype.move = function(e)
 { 	
    if(this.moveable && !this.buttonIsClicked){
        var x = 0;
    	var y = 0;
    	if (window.event) {	
    	   x = event.clientX;
     	   y = event.clientY;
   		}else {
           x = e.clientX;
           y = e.clientY;
    	}   
     
        this.left += x - this.mouseDownX;
        this.top  += y - this.mouseDownY;
        
        this.setPosition();
        this.mouseDownX = x;
        this.mouseDownY = y; 
        
    }
 }


 PortletWindow2.prototype.setPosition = function()
 {
  this.window.style.width = this.width; 
  this.header.style.width = this.width; 
  this.container.style.width = this.width; 
   
   if (document.all) {	
           this.window.style.posLeft = this.left;
       	   this.window.style.posTop = this.top;       	   
    	}    
    	else {        
           this.window.style.left = this.left;
           this.window.style.top = this.top;
    	}
 }

PortletWindow2.prototype.autoRefresh = function()
 {
   if(this.autoRefreshed){       
       if(this.firstTimeAutoRefreshed){
       	  this.firstTimeAutoRefreshed = false;
       }else{
       	  this.selfRefresh();
       }
       setTimeout("Light.portal.tabs["+this.tIndex+"].portlets["+this.position+"]["+this.index+"].autoRefresh()", this.periodTime);
    }
 }
 
PortletWindow2.prototype.selfRefresh = function()
 {   
   	Light.portal.tabs[this.tIndex].rePositionPortlets(this);  
   	if(this.allowJS){
      var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId
      		    +"&mode="+this.mode;
      if(this.parameter.length > 0)
      	pars = pars+"&"+ this.parameter;
      	
      	pars = pars +"&width="+this.width;
	    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
    }else{
      if(this.parameter.length > 0){
            ajaxEngine.sendRequest(this.request
      					, "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
                    	,this.parameter
                    	, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                    	, "portletId="+this.serverId
                    	, "mode="+this.mode
                    	, "width="+this.width
                    	);
      }else{          
      		ajaxEngine.sendRequest(this.request
      					, "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      					, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                   		, "portletId="+this.serverId
                   		, "mode+"+this.mode
                   		, "width="+this.width
                    	);
      }
    }              
 }

PortletWindow2.prototype.refresh = function()
 {
   	this.moveable = false;
        var refreshing = "<img src='"+Light.portal.contextPath+"/portal/light/images/spacer.gif' height='8' style='border: 0px' width='300' alt='' /><span class='portlet'>Refreshing</span>";
   	this.container.innerHTML = refreshing;
   	this.round();
   	Light.portal.tabs[this.tIndex].rePositionPortlets(this);  
   	if(this.allowJS){
      var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId
      		    +"&mode="+this.mode;
      if(this.parameter.length > 0)
      	pars = pars+"&"+ this.parameter;
      	
      	pars = pars +"&width="+this.width;
	    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
    }else{
      if(this.parameter.length > 0){
            ajaxEngine.sendRequest(this.request
      					, "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
                    	,this.parameter
                    	, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                    	, "portletId="+this.serverId
                    	, "mode="+this.mode
                    	, "width="+this.width
                    	);
      }else{          
      		ajaxEngine.sendRequest(this.request
      					, "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      					, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                   		, "portletId="+this.serverId
                   		, "mode+"+this.mode
                   		, "width="+this.width
                    	);
      }
    }              
 }

PortletWindow2.prototype.changePosition = function()
{
   var params = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId
                    +"&column="+this.position
                    +"&row="+this.index
                    +"&width="+this.width;
   new Ajax.Request(Light.portal.contextPath+Light.changePositionRequest,{parameters:params});   
   Light.portal.tabs[this.tIndex].rePositionPortlets(this);    
 }
 
PortletWindow2.prototype.changePosition2 = function()
 {   
   	Light.portal.tabs[this.tIndex].rePositionPortlets(this);    
   	if(this.allowJS){
      var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId
      		    +"&mode=changePosition"
                    +"&originalMode="+this.mode
                    +"&column="+this.position
                    +"&row="+this.index;
      if(this.parameter.length > 0)
      	pars = pars+"&"+ this.parameter;
      	pars = pars +"&width="+this.width;//add by zhoucz 070322
	    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
    }else{
      if(this.parameter.length > 0){
            ajaxEngine.sendRequest(this.request
      					, "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
                    	, this.parameter
                    	, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                    	, "portletId="+this.serverId
                    	, "mode=changePosition"
                        , "originalMode="+this.mode
                        , "column="+this.position
                        , "row="+this.index
                        , "width="+this.width
                    	);
      }else{          
      		ajaxEngine.sendRequest(this.request
      					, "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      					, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                   		, "portletId="+this.serverId
                   		, "mode=changePosition"
                        	, "originalMode="+this.mode
                        	, "column="+this.position
                        	, "row="+this.index
                        	, "width="+this.width
                    		);
      }
    }      
 }

PortletWindow2.prototype.edit = function()
 {
//alert("1793=====");
   this.moveable = false;
   if(this.editMode){      	
        this.mode = Light._EDIT_MODE;
        var id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index;
        if(this.allowJS){
	   var pars = "responseId="+id
	                +"&mode="+this.mode
	      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
	      		    +"&portletId="+this.serverId;
           if(this.parameter.length > 0)
      	      pars = pars+"&"+ this.parameter;	   
      	    pars = pars +"&width="+this.width;//add by zhoucz 070322   
	   ajaxEngine.sendRequestAndUpdate(this.request,id,{evalScripts: true, parameters: pars});
	}else{
          if(this.parameter.length > 0){
            ajaxEngine.sendRequest(this.request
      		, "responseId="+id
                    	, this.parameter
                    	, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                    	, "portletId="+this.serverId
                    	, "mode="+this.mode
                    	, "width="+this.width
                    	);
          }else{          
      		ajaxEngine.sendRequest(this.request
      		, "responseId="+id
      					, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                   		, "portletId="+this.serverId
                   		, "mode="+this.mode
                   		, "width="+this.width
                    	);
          }	      
	}   
    	this.window.removeChild(this.header);
    	this.header = this.createPortletHeader(this);
    	this.window.insertBefore(this.header, this.container);
    	this.setPosition();
    }
 }

PortletWindow2.prototype.cancelEdit = function()
 {
   this.moveable = false;
   if(this.editMode){
   	if(this.allowJS){
      var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId;
      if(this.parameter.length > 0)
      	pars = pars+"&"+ this.parameter;
      	pars = pars +"&width="+this.width;//add by zhoucz 070322
	    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
    }else{     
	    if(this.parameter.length > 0){   
			ajaxEngine.sendRequest(this.request
							, "responseId="+Light._PC_PREFIX +this.tIndex+"_"+ this.position + "_" + this.index
		                    ,this.parameter
		                    , "tabId="+Light.portal.tabs[this.tIndex].tabServerId
	                    	, "portletId="+this.serverId
	                    	, "width="+this.width
	                    	);
		}else{
		  	ajaxEngine.sendRequest(this.request
						, "responseId="+Light._PC_PREFIX +this.tIndex+"_"+ this.position + "_" + this.index
						, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
	                    , "portletId="+this.serverId
	                    , "width="+this.width
	                    );
		}    
    }      
    this.mode = Light._VIEW_MODE;
	this.window.removeChild(this.header);
	this.header = this.createPortletHeader(this);
	this.window.insertBefore(this.header, this.container);
    this.setPosition();
   }
 }
 
PortletWindow2.prototype.help = function()
 {
   this.moveable = false;
   if(this.helpMode){      	
        this.mode = Light._HELP_MODE;            	
        var id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index;
        if(this.allowJS){
	   var pars = "responseId="+id
	                +"&mode="+this.mode
	      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
	      		    +"&portletId="+this.serverId;
           if(this.parameter.length > 0)
      	      pars = pars+"&"+ this.parameter;	
      	 
      	 pars = pars +"&width="+this.width;//add by zhoucz 070322      
	   ajaxEngine.sendRequestAndUpdate(this.request,id,{evalScripts: true, parameters: pars});
	}else{
          if(this.parameter.length > 0){
            ajaxEngine.sendRequest(this.request
      		, "responseId="+id
                    	, this.parameter
                    	, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                    	, "portletId="+this.serverId
                    	, "mode="+this.mode
                    	, "width="+this.width
                    	);
          }else{          
      		ajaxEngine.sendRequest(this.request
      		, "responseId="+id
      					, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                   		, "portletId="+this.serverId
                   		, "mode="+this.mode
                   		, "width="+this.width
                    	);
          }	      
	}   
    	this.window.removeChild(this.header);
		this.header = this.createPortletHeader(this);
		this.window.insertBefore(this.header, this.container);
    	this.setPosition();
    }
 }
 
PortletWindow2.prototype.cancelHelp = function()
 {
   this.moveable = false;
   if(this.helpMode){
    if(this.allowJS){
      var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId;
      if(this.parameter.length > 0)
      	pars = pars+"&"+ this.parameter;
      	
      	pars = pars +"&width="+this.width;//add by zhoucz 070322
	    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
    }else{
	    if(this.parameter.length > 0){   
			ajaxEngine.sendRequest(this.request
							, "responseId="+Light._PC_PREFIX +this.tIndex+"_"+ this.position + "_" + this.index
		                    ,this.parameter
		                    , "tabId="+Light.portal.tabs[this.tIndex].tabServerId
	                    	, "portletId="+this.serverId
	                    	, "width="+this.width
	                    	);
		}else{
		  	ajaxEngine.sendRequest(this.request
						, "responseId="+Light._PC_PREFIX +this.tIndex+"_"+ this.position + "_" + this.index
						, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
	                    , "portletId="+this.serverId
	                    , "width="+this.width
	                    );
		}    
    }    
    this.mode = Light._VIEW_MODE;            	
	this.window.removeChild(this.header);
	this.header = this.createPortletHeader(this);
	this.window.insertBefore(this.header, this.container);
    this.setPosition();
   }
 }
 
 
PortletWindow2.prototype.config = function()
 {
   this.moveable = false;
   if(this.configMode){  
        this.mode = Light._CONFIG_MODE;  
    	var id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index;
        if(this.allowJS){
	   var pars = "responseId="+id
	                +"&mode="+this.mode
	      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
	      		    +"&portletId="+this.serverId;
           if(this.parameter.length > 0)
      	      pars = pars+"&"+ this.parameter;	   
      
      pars = pars +"&width="+this.width;//add by zhoucz 070322   
	   ajaxEngine.sendRequestAndUpdate(this.request,id,{evalScripts: true, parameters: pars});
	}else{
          if(this.parameter.length > 0){
            ajaxEngine.sendRequest(this.request
      		, "responseId="+id
                    	, this.parameter
                    	, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                    	, "portletId="+this.serverId
                    	, "mode="+this.mode
                    	, "width="+this.width
                    	);
          }else{          
      		ajaxEngine.sendRequest(this.request
      		, "responseId="+id
      					, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                   		, "portletId="+this.serverId
                   		, "mode="+this.mode
                   		, "width="+this.width
                    	);
          }	      
	}         	
    	this.window.removeChild(this.header);
		this.header = this.createPortletHeader(this);
		this.window.insertBefore(this.header, this.container);
    	this.setPosition();
    }
 }
 
PortletWindow2.prototype.cancelConfig = function()
 {

   this.moveable = false;
   if(this.configMode){
    if(this.allowJS){
      var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId;
      if(this.parameter.length > 0)
      	pars = pars+"&"+ this.parameter;
      	pars = pars +"&width="+this.width;//add by zhoucz 070322
	    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
    }else{
	    if(this.parameter.length > 0){   
			ajaxEngine.sendRequest(this.request
							, "responseId="+Light._PC_PREFIX +this.tIndex+"_"+ this.position + "_" + this.index
		                    ,this.parameter
		                    , "tabId="+Light.portal.tabs[this.tIndex].tabServerId
	                    	, "portletId="+this.serverId
	                    	, "width="+this.width
	                    	);
		}else{
		  	ajaxEngine.sendRequest(this.request
						, "responseId="+Light._PC_PREFIX +this.tIndex+"_"+ this.position + "_" + this.index
						, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
	                    , "portletId="+this.serverId
	                    , "width = "+this.width
	                    );
		}    
    }    
    this.mode = Light._VIEW_MODE;            	
	this.window.removeChild(this.header);
	this.header = this.createPortletHeader(this);
	this.window.insertBefore(this.header, this.container);
    this.setPosition();
   }
 }
 
PortletWindow2.prototype.moveUp = function()
 {
   if(this.index > 0){
     var temp = null;
     var upIndex = 0;
     var currentIndex = this.index;
     var started = this.index - 1;                 
        for(var i=started;i>=0;i--){
           if(Light.portal.tabs[this.tIndex].portlets[this.position][i] != null){
             temp = Light.portal.tabs[this.tIndex].portlets[this.position][i];
             upIndex = i;
             break;
           }
        }               
        if(temp != null){
          temp.index = this.index;
          this.index = upIndex;
          temp.container.id = Light._PC_PREFIX+this.tIndex+"_"+temp.position+"_"+temp.index;          
          this.container.id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+this.index;  
          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+temp.position+"_"+temp.index);
          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+ this.position + "_" + this.index);        
          Light.portal.tabs[this.tIndex].portlets[this.position][upIndex] = this;
          Light.portal.tabs[this.tIndex].portlets[this.position][currentIndex] = temp; 
          temp.changePosition();   
          this.left = Light.portal.tabs[this.tIndex].between;
    	  for(var i=0;i<this.position;i++) { 
        	this.left += Light.portal.tabs[this.tIndex].widths[i] + Light.portal.tabs[this.tIndex].between;
    	  }
          this.changePosition();      
          //Light.portal.tabs[this.tIndex].rePositionPortlets(this);
        }     
   }
 }
 
 PortletWindow2.prototype.moveDown = function()
 {
    var temp = null;
    var downIndex = 0;
    var currentIndex = this.index;
    var started = this.index + 1;                  
    for(var i=started;i<Light.portal.tabs[this.tIndex].portlets[this.position].length;i++){
       if(Light.portal.tabs[this.tIndex].portlets[this.position][i] != null){
         temp = Light.portal.tabs[this.tIndex].portlets[this.position][i];
         downIndex = i;
         break;
       }
    }               
    if(temp != null){
      temp.index = this.index;
      this.index = downIndex;
      temp.container.id = Light._PC_PREFIX+this.tIndex+"_"+temp.position+"_"+temp.index;          
      this.container.id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+this.index;          
      ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+temp.position+"_"+temp.index);
      ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+ this.position + "_" + this.index);        
      Light.portal.tabs[this.tIndex].portlets[this.position][downIndex] = this;
      Light.portal.tabs[this.tIndex].portlets[this.position][currentIndex] = temp;          
      this.changePosition();
      this.left = Light.portal.tabs[this.tIndex].between;
    	  for(var i=0;i<this.position;i++) { 
        	this.left += Light.portal.tabs[this.tIndex].widths[i] + Light.portal.tabs[this.tIndex].between;
    	  }
      temp.changePosition();             
      //Light.portal.tabs[this.tIndex].rePositionPortlets(temp);
    }    
 }
 
 PortletWindow2.prototype.moveLeft = function()
 {
   if(this.position > 0){
     var temp = null;
     var newIndex = 0;
     var currentPosition = this.position;
     var currentIndex = this.index;
     var column = this.position - 1;
     if(Light.portal.tabs[this.tIndex].portlets[column] == null)
        Light.portal.tabs[this.tIndex].portlets[column] = new Array(); 
     var len = Light.portal.tabs[this.tIndex].portlets[column].length;   
     for(var i=0;i<len;i++){
       if(Light.portal.tabs[this.tIndex].portlets[column][i] != null 
       && this.top < Light.portal.tabs[this.tIndex].portlets[column][i].top){
            temp = Light.portal.tabs[this.tIndex].portlets[column][i];
            newIndex = temp.index;
            break;
       }
     }                    
                
        if(temp != null){
          for(var i= len - 1;i>=0;i--){
	       if(Light.portal.tabs[this.tIndex].portlets[column][i] != null){
	          var temp2 = Light.portal.tabs[this.tIndex].portlets[column][i];
	          temp2.index++;
	          temp2.container.id = Light._PC_PREFIX+temp2.tIndex+"_"+temp2.position+"_"+temp2.index;  
	          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+temp2.tIndex+"_"+temp2.position+"_"+temp2.index);
	          Light.portal.tabs[this.tIndex].portlets[column][i + 1]= temp2;
	          Light.portal.tabs[this.tIndex].portlets[column][i + 1].changePosition();
	          Light.portal.tabs[this.tIndex].portlets[column][i]= null;
                  if(temp2.serverId == temp.serverId) break;	         
	       }
	      }   
	      this.position = column;
              this.index = newIndex;                            
          this.container.id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+this.index;           
          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+ this.position + "_" + this.index);        
          Light.portal.tabs[this.tIndex].portlets[this.position][this.index] = this;          
          Light.portal.tabs[this.tIndex].portlets[currentPosition][currentIndex] = null; 
          this.left = Light.portal.tabs[this.tIndex].between;
    	  for(var i=0;i<this.position;i++) { 
        	this.left += Light.portal.tabs[this.tIndex].widths[i] + Light.portal.tabs[this.tIndex].between;
    	  }
          this.width = Light.portal.tabs[this.tIndex].widths[this.position];  
          this.changePosition();                     
        }else{
          this.position = column;  
          var isEmpty = true;
          for(var i= len - 1;i>=0;i--){
	       if(Light.portal.tabs[this.tIndex].portlets[column][i] != null){
	          var temp3 = Light.portal.tabs[this.tIndex].portlets[column][i];
                  this.index = temp3.index + 1;
                  isEmpty = false;
	          break;	         
	       }
	  }          
          if(isEmpty) this.index = 0;
          this.container.id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+this.index;  
          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+ this.position + "_" + this.index);        
          Light.portal.tabs[this.tIndex].portlets[this.position][this.index] = this;   
          Light.portal.tabs[this.tIndex].portlets[currentPosition][currentIndex] = null;        
          this.left = Light.portal.tabs[this.tIndex].between;
    	  for(var i=0;i<this.position;i++) { 
        	this.left += Light.portal.tabs[this.tIndex].widths[i] + Light.portal.tabs[this.tIndex].between;
    	  }
	  this.width = Light.portal.tabs[this.tIndex].widths[this.position];
          this.changePosition();        
        }
      var length = Light.portal.tabs[this.tIndex].portlets[currentPosition].length;   
     for(var i=currentIndex+1;i<length;i++){
       if(Light.portal.tabs[this.tIndex].portlets[currentPosition][i] != null){
         var next = Light.portal.tabs[this.tIndex].portlets[currentPosition][i];
         Light.portal.tabs[this.tIndex].rePositionPortlets(next);
         break;
       }
     }          
   }
 }
 
 PortletWindow2.prototype.moveRight = function()
 { 
   columns = Light.portal.tabs[this.tIndex].widths.length;
   if(this.position + 1 < columns 
    && Light.portal.tabs[this.tIndex].portlets[this.position + 1] == null)
      Light.portal.tabs[this.tIndex].portlets[this.position + 1] = new Array();     
   if(Light.portal.tabs[this.tIndex].portlets[this.position + 1] != null){
     var temp = null;
     var newIndex = 0;
     var currentPosition = this.position;
     var currentIndex = this.index;
     var column = this.position + 1;              
     var len = Light.portal.tabs[this.tIndex].portlets[column].length;   
     for(var i=0;i<len;i++){
       if(Light.portal.tabs[this.tIndex].portlets[column][i] != null
       && this.top < Light.portal.tabs[this.tIndex].portlets[column][i].top){
            temp = Light.portal.tabs[this.tIndex].portlets[column][i];
            newIndex = temp.index;
            break;
       }
     }               
     if(temp != null){                    
          for(var i= len - 1;i>=0;i--){
	       if(Light.portal.tabs[this.tIndex].portlets[column][i] != null){
	          var temp2 = Light.portal.tabs[this.tIndex].portlets[column][i];
	          temp2.index++;
	          temp2.container.id = Light._PC_PREFIX+temp2.tIndex+"_"+temp2.position+"_"+temp2.index;  
	          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+temp2.tIndex+"_"+temp2.position+"_"+temp2.index);
	          Light.portal.tabs[this.tIndex].portlets[column][i + 1]= temp2;
	          Light.portal.tabs[this.tIndex].portlets[column][i + 1].changePosition();
	          Light.portal.tabs[this.tIndex].portlets[column][i]= null;
                  if(temp2.serverId == temp.serverId) break;	         
	       }
	      }   
	      this.position = column;
              this.index = newIndex;                    
          this.container.id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+this.index;  
          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+ this.position + "_" + this.index);                  
          Light.portal.tabs[this.tIndex].portlets[this.position][this.index] = this;          
	  Light.portal.tabs[this.tIndex].portlets[currentPosition][currentIndex] = null; 
          this.left = Light.portal.tabs[this.tIndex].between;
    	  for(var i=0;i<this.position;i++) { 
        	this.left += Light.portal.tabs[this.tIndex].widths[i] + Light.portal.tabs[this.tIndex].between;
    	  }
          this.width = Light.portal.tabs[this.tIndex].widths[this.position]; 
          this.changePosition();    
        }else{
          this.position = column;
          var isEmpty = true;
          for(var i= len - 1;i>=0;i--){
	       if(Light.portal.tabs[this.tIndex].portlets[column][i] != null){
	          var temp3 = Light.portal.tabs[this.tIndex].portlets[column][i];
                  this.index = temp3.index + 1;
                  isEmpty = false;
	          break;	         
	       }
	  }          
          if(isEmpty) this.index = 0;        
          this.container.id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+this.index;  
          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+ this.position + "_" + this.index);        
          Light.portal.tabs[this.tIndex].portlets[this.position][this.index] = this;
          Light.portal.tabs[this.tIndex].portlets[currentPosition][currentIndex] = null; 
          this.left = Light.portal.tabs[this.tIndex].between;
    	  for(var i=0;i<this.position;i++) { 
        	this.left += Light.portal.tabs[this.tIndex].widths[i] + Light.portal.tabs[this.tIndex].between;
    	  }
	      this.width = Light.portal.tabs[this.tIndex].widths[this.position];
          this.changePosition();          
        }    
     var length = Light.portal.tabs[this.tIndex].portlets[currentPosition].length;   
     for(var i=currentIndex+1;i<length;i++){
       if(Light.portal.tabs[this.tIndex].portlets[currentPosition][i] != null){
         var next = Light.portal.tabs[this.tIndex].portlets[currentPosition][i];
         Light.portal.tabs[this.tIndex].rePositionPortlets(next);
         break;
       }
     }      
   }
 }

 PortletWindow2.prototype.minimize = function()
 { 
   this.minimized = !this.minimized;
   if(this.minimized){
    if(this.maximized){
       this.maximize();   
       this.minimized = true;     
    }
    var empty = "<img src='"+Light.portal.contextPath+"/portal/light/images/spacer.gif'"
		+" height='20' style='border: 0px' width='200' alt='' />";
    this.container.innerHTML = empty;             	       
  }else{
   
      var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId;
      if(this.parameter.length > 0)
      	pars = pars+"&"+ this.parameter;
      if(this.mode != Light._VIEW_MODE)
        pars = pars+"&mode="+this.mode; 
        
        pars = pars+"&width="+this.width; 
    if(this.allowJS){       
	  ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
    }else{
      ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: false, parameters: pars});
    }         	       
  }  
  this.maximized = false;
  this.window.removeChild(this.header);
  this.header = this.createPortletHeader(this);
  this.window.insertBefore(this.header, this.container);  
  this.setPosition();  
  Light.portal.tabs[this.tIndex].rePositionPortlets(this);
 }
 
 PortletWindow2.prototype.maximize = function()
 { 
   var empty = "<img src='"+Light.portal.contextPath+"/portal/light/images/spacer.gif'"
		+" height='20' style='border: 0px' width='200' alt='' />";
   this.container.innerHTML = empty;
   this.maximized = !this.maximized; 
   if(!this.maximized){   
      Light.portal.tabs[this.tIndex].showOtherPortlets();	
      var height = 0;
      var maxIndex = 0;
      var nullNum = 0;      
      for(var i=0;i<Light.portal.tabs[this.tIndex].portlets[this.position].length;i++) {        
          if(i == this.index){
             break;
          }
 		  if(Light.portal.tabs[this.tIndex].portlets[this.position][i] != null && !Light.portal.tabs[this.tIndex].portlets[this.position][i].maximized){
             height += Light.portal.tabs[this.tIndex].portlets[this.position][i].container.clientHeight;   
          } 
          if(Light.portal.tabs[this.tIndex].portlets[this.position][i] == null){
             nullNum++;
          }
          if(Light.portal.tabs[this.tIndex].portlets[this.position][i] != null && Light.portal.tabs[this.tIndex].portlets[this.position][i].maximized){
             height = Light.portal.tabs[this.tIndex].portlets[this.position][i].container.clientHeight;     
             maxIndex = i;
             nullNum = 0;
          }              
    	}  
    	this.top  = Light.portal.layout.normalTop + height + this.layout.rowBetween * (i - maxIndex - nullNum); 	
    	this.width = Light.portal.tabs[this.tIndex].widths[this.position];
	    this.left = Light.portal.tabs[this.tIndex].between;
	    for(var i=0;i<this.position;i++) { 
	        this.left += Light.portal.tabs[this.tIndex].widths[i] + Light.portal.tabs[this.tIndex].between;
	    }   
	   if(! this.minimized){          
	        var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
	      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
	      		    +"&portletId="+this.serverId;
		    if(this.parameter.length > 0)
		      	pars = pars+"&"+ this.parameter;
		    if(this.mode != Light._VIEW_MODE)
	        	pars = pars+"&mode="+this.mode; 
	        	
	        	pars = pars+"&width="+this.width; //add by zhoucz 070322
	        if(this.allowJS){ 
			    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
		    }else{
			    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: false, parameters: pars});  
		    }       
	     }else{
	        var empty = "<img src='"+Light.portal.contextPath+"/portal/light/images/spacer.gif'"
				+" height='20' style='border: 0px' width='200' alt='' />";
		    this.container.innerHTML = empty; 
		 }        
     	Light.portal.container.style.zIndex= 1;
   }else{
        
        Light.portal.tabs[this.tIndex].hideOtherPortlets();        
   	    this.left = Light.portal.layout.maxLeft;
   	    this.top = Light.portal.layout.maxTop;
        this.width = Light.portal.layout.maxWidth;        	
        
        Light.portal.container.style.zIndex= ++Light.maxZIndex; 
        Light.portal.body.style.zIndex= ++Light.maxZIndex;
        Light.portal.footer.style.zIndex= ++Light.maxZIndex;

	    var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
	      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
	      		    +"&portletId="+this.serverId
	      		    +"&state=maximized";
	    if(this.parameter.length > 0)
	      	pars = pars+"&"+ this.parameter;
	    if(this.mode != Light._VIEW_MODE)
        	pars = pars+"&mode="+this.mode; 
        	pars = pars+"&width="+this.width; //add by zhoucz 070322
        if(this.allowJS){ 
		    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
	    }else{
		    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: false, parameters: pars});  
	    }          
   } 
   this.minimized = false;
   this.window.removeChild(this.header);
   this.header = this.createPortletHeader(this);
   this.window.insertBefore(this.header, this.container);         
   this.setPosition();
   this.setFocus();   
   Light.portal.tabs[this.tIndex].rePositionPortlets(this);
 }

 PortletWindow2.prototype.close = function()
 { 
   if(this.maximized)   
     Light.portal.tabs[this.tIndex].showOtherPortlets();    
   var vdocument = document.getElementById('panel_tab_page'+this.tIndex); 
   this.window.removeChild(this.header);
   this.window.removeChild(this.container);
   vdocument.removeChild(this.window);
   
   Light.portal.tabs[this.tIndex].portlets[this.position][this.index] = null;   
   Light.portal.tabs[this.tIndex].rePositionPortlets(this);
   new Ajax.Request(Light.portal.contextPath+Light.deletePortletRequest, {parameters:'portletId='+this.serverId});    
   
 }
 
  PortletWindow2.prototype.refreshWindow= function () {
   this.window.removeChild(this.header);
   this.window.removeChild(this.container);
   this.header = this.createPortletHeader(this);
   this.container = this.createPortletContainer(this);
   this.window.appendChild(this.header); 
   this.window.appendChild(this.container);  
   ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+ this.position + "_" + this.index); 
   this.setPosition();
   
 }
 
 PortletWindow2.prototype.refreshHeader= function () {
   this.window.removeChild(this.header);
   this.header = this.createPortletHeader(this);
   this.window.insertBefore(this.header, this.container);  
 }
 
 PortletWindow2.prototype.createPortletWindow = function (portlet) {
   var vWindow = document.createElement('div');
   vWindow.id = "portlet_"+portlet.tIndex+"_"+portlet.position+"_"+portlet.index;
   vWindow.style.position = "absolute";
   vWindow.className = "portlet2";    
   vWindow.style.zIndex= ++Light.maxZIndex;
   return vWindow;
  }
  
  PortletWindow2.prototype.createPortletHeader= function (portlet) {
  //alert("2436===in portlet");
   var vHeader = document.createElement('div');
   vHeader.className = "portlet2-header";
   if(this.barBgColor.length > 0)
   	  vHeader.style.backgroundColor = this.barBgColor;   
   if(Light.portal.tabs[this.tIndex].tabIsMoveable){
   	vHeader.onmousedown = function(e){ portlet.moveBegin(e);}
   	vHeader.onmousemove = function(e){ portlet.move(e);}
   	vHeader.onmouseup   = function(){ portlet.moveEnd();}
   	vHeader.onmouseout  = function(){ portlet.moveEnd();}
        vHeader.style.cursor= "move";
   }
   
   /** modified by mios 070329 >> **/
   function createIcon(src,title){
     return "<span title='"+title+"'><img id='pngIcoUp'"+
  			"onerror='this.src=this.src'"+
		    " src='"+Light.portal.contextPath+src+"'" +
		    " style='border: 0;"+(ie ? "filter:alpha(opacity=50);":"-moz-opacity:0.5;")+"'" +
		    " onmouseover='"+(ie ? "this.filters.alpha.opacity=100;":"this.style.MozOpacity=1;")+"'" +
		    " onmouseout='"+(ie ? "this.filters.alpha.opacity=50;":"this.style.MozOpacity=0.5;")+"'/></span>";
   }
   var strIcoRefresh = createIcon("/portal/light/images/refresh_on.gif",LightResourceBundle._REFRESH_PORTLET);
   var strIcoEdit = createIcon("/portal/light/images/edit_on.gif",LightResourceBundle._EDIT_MODE);
   var strIcoCancelEdit = createIcon("/portal/light/images/leave_edit_on.gif",LightResourceBundle._VIEW_MODE);
   var strIcoHelp = createIcon("/portal/light/images/help_on.gif",LightResourceBundle._HELP_MODE);
   var strIcoCancelHelp = createIcon("/portal/light/images/leave_help_on.gif","");
   var strIcoConfig = createIcon("/portal/light/images/config_on.gif",LightResourceBundle._CONFIG_MODE);
   var strIcoCancelConfig = createIcon("/portal/light/images/leave_config_on.gif",LightResourceBundle._VIEW_MODE);
   var strIcoUp = createIcon("/portal/light/images/up_on.gif",LightResourceBundle._MOVE_UP);
   var strIcoDown = createIcon("/portal/light/images/down_on.gif",LightResourceBundle._MOVE_DOWN);
   var strIcoMin = createIcon("/portal/light/images/min_on.gif",LightResourceBundle._MINIMIZED);
   var strIcoRestore = createIcon("/portal/light/images/restore_on.gif",LightResourceBundle._RESTORE);
   var strIcoMax = createIcon("/portal/light/images/max_on.gif",LightResourceBundle._MAXIMIZED);
   var strIcoCls = createIcon("/portal/light/images/close_on.gif",LightResourceBundle._CLOSE);  
   /** modified by mios 070329 << **/

   var vTitle = document.createElement('span');
   var inner = "";
   if(portlet.icon.length > 0){
      if(portlet.icon.substring(0,4) == "http")
      	inner = "<img src='"+portlet.icon+"' align='absmiddle'/>&nbsp;";
      else
        inner = "<img src='"+Light.portal.contextPath+portlet.icon+"' align='absmiddle'/>&nbsp;";
   }
   if(portlet.url.length > 0){
      inner = inner+"<a href='"+portlet.url+"' target='_blank'>";
      if(this.barFontColor.length > 0)
         inner = inner+"<font color='"+this.barFontColor+"'>";
      inner = inner+portlet.title;
      if(this.barFontColor.length > 0)
         inner = inner+"</font>";
      inner = inner+"</a>";
   }else
      inner = inner+portlet.title;

   vTitle.innerHTML = inner;
   vTitle.className = "portlet2-header-title";
   if(this.barBgColor.length > 0)
   	  vTitle.style.backgroundColor = this.barBgColor;
   if(this.barFontColor.length > 0)
   	  vTitle.style.color = this.barFontColor;       
   vHeader.appendChild(vTitle);
   
   var vButton = document.createElement('span');
   vButton.className = "portlet2-header-button";
   vHeader.appendChild(vButton);
   
   if(this.refreshMode){
	   var refresh = document.createElement('a');
	   refresh.innerHTML = strIcoRefresh;
	   //alert("2499=="+refresh.innerHTML);
	   refresh.href = "javascript:void(0)";
	   refresh.onclick = function(){
	     portlet.refresh();
	   }   
	     
       refresh.onmousedown = function(){ portlet.moveCancel();}    
	   vButton.appendChild(refresh);
   }
   
   if(this.editMode){
       var edit = document.createElement('a');
       edit.href = "javascript:void(0)";
       if(this.mode == Light._EDIT_MODE){
       	   edit.innerHTML = strIcoCancelEdit;
       	   //alert("2513=="+edit.innerHTML);
       	   edit.onclick = function(){
		     portlet.cancelEdit();
		   }
       }else{	
       	   if(this.title == LightResourceBundle._WEATHER_TITLE){   
		    edit.innerHTML = strIcoCancelEdit;
		   }else{
		   edit.innerHTML = strIcoEdit;
		   }
		   //alert("2523=="+edit.innerHTML);
		   edit.onclick = function(){
		     portlet.edit();
		   }
	   }
	   //this.refresh();
       edit.onmousedown = function(){ portlet.moveCancel();}
	   vButton.appendChild(edit);
   }
   
   if(this.helpMode){
	   var help = document.createElement('a');	   
	   help.href = "javascript:void(0)";
	   if(this.mode == Light._HELP_MODE){
	       help.innerHTML = strIcoCancelHelp;
	       //alert("2538=="+help.innerHTML);
		   help.onclick = function(){
		     portlet.cancelHelp();
		   }
	   }else{
	       help.innerHTML = strIcoHelp;
	       //alert("2544=="+help.innerHTML);
	   	   help.onclick = function(){
		     portlet.help();
		   }	   
	   }
           help.onmousedown = function(){ portlet.moveCancel();}
	   vButton.appendChild(help);
   }
   
   if(this.configMode){
	   var config = document.createElement('a');	   
	   config.href = "javascript:void(0)";
	   if(this.mode == Light._CONFIG_MODE){
	       config.innerHTML = strIcoCancelConfig;
	       //alert("2557=="+config.innerHTML);
		   config.onclick = function(){
		     portlet.cancelConfig();
		   }
	   }else{
	       config.innerHTML = strIcoConfig;
	       //alert("2564=="+config.innerHTML);
	   	   config.onclick = function(){
		     portlet.config();
		   }	   
	   }
           config.onmousedown = function(){ portlet.moveCancel();}
	   vButton.appendChild(config);
   }
 
   
   var minA = document.createElement('a');
   if(this.minimized){
	   minA.innerHTML = strIcoRestore;	   
   }else{
       minA.innerHTML = strIcoMin;	   
   }
   //alert("2580=="+minA.innerHTML);
   minA.href = "javascript:void(0)";
   minA.onclick = function(){
     portlet.minimize();
   }
   minA.onmousedown = function(){ portlet.moveCancel();}
   vButton.appendChild(minA);

   var maxA = document.createElement('a');
   if(this.maximized){
   	  maxA.innerHTML = strIcoRestore;
   }else{
      maxA.innerHTML = strIcoMax;
   }
   //alert("2594=="+minA.innerHTML);
   maxA.href = "javascript:void(0)";
   maxA.onclick = function(){
     portlet.maximize();
   }
   maxA.onmousedown = function(){ portlet.moveCancel();}
   vButton.appendChild(maxA);
   
   if(this.closeable){
	   var clsA = document.createElement('a');
	   clsA.innerHTML = strIcoCls;
	   clsA.href = "javascript:void(0)";
	   clsA.onclick = function(){
	     portlet.close();
	   }
           clsA.onmousedown = function(){ portlet.moveCancel();}
	   vButton.appendChild(clsA);
   }     
   return vHeader;
  }

  PortletWindow2.prototype.createPortletInfo = function (portlet) {   
   var vInfo = document.createElement('div');
   //vInfo.id = 'info_'+portlet.index;
   //vInfo.className = "winfo"; 
   vInfo.onmousedown = function(){
     portlet.setFocus();
   }
   return vInfo;
  }
  
  PortletWindow2.prototype.createPortletContainer = function (portlet) {   
   var vContainer = document.createElement('div');
   vContainer.className = "portlet2-content";
   vContainer.id = Light._PC_PREFIX+portlet.tIndex+"_"+portlet.position+"_"+portlet.index;
   vContainer.onmousedown = function(){
      portlet.setFocus();   
   }
   if(this.contentBgColor.length > 0)
   	  vContainer.style.backgroundColor = this.contentBgColor; 
   return vContainer;
  }
  

//------------------------------------------------------------ PortletPopupWindow.js
 PortletPopupWindow = function (tIndex,serverId,position,left,width,title,icon,url,request,requestUrl,closeable,refreshMode,editMode,helpMode,configMode,autoRefreshed,periodTime,allowJS,barBgColor,barFontColor,contentBgColor,parameter) {
   this.layout={
        popup : 10,
	    titleRelativeLeft : 10,
	    titleRelativeTop : -8, 
	    buttonRelative : 16,
	    upRelativeRight : 94,
	    downRelativeRight : 78, 
	    minRelativeRight : 62,
	    maxRelativeRight : 46,
	    closeRelativeRight : 30,
	    buttonRelativeTop : -6,
	    rowBetween : 20  
   }
   this.mode = Light._VIEW_MODE;
   this.state = Light._NORMAL_STATE;
   this.tIndex = tIndex;   
   this.serverId = serverId;
   this.position = position;
   this.left = left;
   this.width = width;
   this.title = title;
   this.icon = icon;
   this.url = url;
   this.request = request;
   this.requestUrl = requestUrl;
   this.closeable = closeable;
   this.refreshMode = refreshMode;
   this.editMode = editMode;
   this.helpMode = helpMode;
   this.configMode = configMode;
   this.autoRefreshed = autoRefreshed;
   this.periodTime = periodTime;
   this.allowJS = allowJS;
   this.barBgColor = barBgColor;
   this.barFontColor = barFontColor;
   this.contentBgColor ='#F5F2DB';
   if(contentBgColor !='') this.contentBgColor = contentBgColor;
   this.parameter = parameter;   
   this.index = Light.portal.tabs[this.tIndex].getPortletIndex(this.position);
   var height = 0;
   var maxIndex = 0;
   var nullNum = 0;        

   this.originalTop = this.top;
   this.originalWidth = this.width;
   this.originalLeft = this.left;

   Light.portal.tabs[this.tIndex].portlets[this.position][this.index] = this;
   this.container = this.createPortletContainer(this);
   this.header = this.createPortletHeader(this);     
   this.minButton = this.createPortletMinButton(this);
   this.maxButton = this.createPortletMaxButton(this);   
   this.createPortletRestoreMinButton(this);
   this.createPortletRestoreMaxButton(this);
   
   var vdocument = document.getElementById('panel_tab_page'+this.tIndex);
   vdocument.appendChild(this.container);
   vdocument.appendChild(this.header);   
   vdocument.appendChild(this.minButton);
   vdocument.appendChild(this.maxButton);   
   
   if(this.closeable){
   	  this.closeButton = this.createPortletCloseButton(this);
   	  vdocument.appendChild(this.closeButton);
   }
   if(this.configMode){
      this.configButton = this.createPortletConfigButton(this);   
      vdocument.appendChild(this.configButton);
      this.createPortletCancelConfigButton(this);
   }   
   if(this.helpMode){
      this.helpButton = this.createPortletHelpButton(this);   
      vdocument.appendChild(this.helpButton);
      this.createPortletCancelHelpButton(this);
   }   
   if(this.editMode){
      this.editButton = this.createPortletEditButton(this);   
      vdocument.appendChild(this.editButton);
      this.createPortletCancelEditButton(this);
   }
   
   if(this.refreshMode){
      this.refreshButton = this.createPortletRefreshButton(this);   
      vdocument.appendChild(this.refreshButton);
   }
   
   this.minimized = false;
   this.maximized = false;   
   this.moveable = false;
   this.mouseDownX = 0;
   this.mouseDownY = 0;
   this.setPosition();
   var loading = "<img src='"+Light.portal.contextPath+"/portal/light/images/spacer.gif'"
		+" height='8' style='border: 0px' width='200' alt='' />"
                +"<span class='portlet'>Loading</span>";
   this.container.innerHTML = loading;
   this.round();
   this.setFocus();
   if(this.autoRefreshed){
   	  this.firstTimeAutoRefreshed = true;
   	  this.autoRefresh();
   }
 }  
 
 PortletPopupWindow.prototype.round = function(){
   if (document.all){
        var cssRule = document.styleSheets[0].rules;
   }else {
        var cssRule = document.styleSheets[0].cssRules;
   }
   var color=cssRule[0].style.color;
   var borderRule= {border: color};
   var id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+this.index;
   Rico.Corner.round($(id), borderRule);   
   
   if( document.forms['form_'+id]!= null
    && document.forms['form_'+id]['userId'] != null){
	 document.forms['form_'+id]['userId'].focus();    
   }  
 }
 
 PortletPopupWindow.prototype.setFocus = function()
 {
    var zIndex = Light.maxZIndex+100;
    this.container.style.zIndex= ++zIndex;
    this.header.style.zIndex= ++zIndex; 
    if(this.refreshMode){
    	this.refreshButton.style.zIndex= zIndex;
    }
    if(this.editMode){
    	this.editButton.style.zIndex= zIndex;
    }
    if(this.helpMode){
    	this.helpButton.style.zIndex= zIndex;
    }
    if(this.configMode){
    	this.configButton.style.zIndex= zIndex;
    }  
    this.minButton.style.zIndex= zIndex;
    this.maxButton.style.zIndex= zIndex;     
    if(this.closeable){
      this.closeButton.style.zIndex= zIndex;   	  
    } 
 }
 
 PortletPopupWindow.prototype.show = function(){
 	this.container.style.visibility = "visible";
 	this.header.style.visibility = "visible";
    if(this.refreshMode){
    	this.refreshButton.style.visibility = "visible";
    }
    if(this.editMode){
    	this.editButton.style.visibility = "visible";
    }
    if(this.helpMode){
    	this.helpButton.style.visibility = "visible";
    }
    if(this.configMode){
    	this.configButton.style.visibility = "visible";
    }
    this.minButton.style.visibility = "visible";
    this.maxButton.style.visibility = "visible";    
    if(this.closeable){
      this.closeButton.style.visibility = "visible";
    } 
 }
 
 PortletPopupWindow.prototype.hide = function(){
 	this.container.style.visibility = "hidden";
 	this.header.style.visibility = "hidden";
    if(this.refreshMode){
    	this.refreshButton.style.visibility = "hidden";
    }
    if(this.editMode){
    	this.editButton.style.visibility = "hidden";
    }
    if(this.helpMode){
    	this.helpButton.style.visibility = "hidden";
    }
    if(this.configMode){
    	this.configButton.style.visibility = "hidden";
    }
    this.minButton.style.visibility = "hidden";
    this.maxButton.style.visibility = "hidden";
    if(this.closeable){
      this.closeButton.style.visibility = "hidden";    
    } 
 }
  
 PortletPopupWindow.prototype.moveBegin = function(e)
 {  
    var x = 0;
    var y = 0;
    if (window.event) {	
    	x = event.clientX;
        y = event.clientY;
    }else {
        x = e.clientX;
        y = e.clientY;
    }
    this.setFocus();
    this.moveable = true;
    this.mouseDownX = x;
    this.mouseDownY = y; 
    this.moveBeginX = x;
    this.moveBeginY = y;
 }
 
 PortletPopupWindow.prototype.moveEnd = function()
 {    
    if(this.moveable){ 	
        this.moveable = false;  
        this.originalTop = this.top;
        this.originalLeft = this.left;
    } 	
 }

 PortletPopupWindow.prototype.move = function(e)
 { 	
    if(this.moveable){
        var x = 0;
    	var y = 0;
    	if (window.event) {	
    	   x = event.clientX;
     	   y = event.clientY;
   		}else {
           x = e.clientX;
           y = e.clientY;
    	}   
     
        this.left += x - this.mouseDownX;
        this.top  += y - this.mouseDownY;        
        this.setPosition();
        this.mouseDownX = x;
        this.mouseDownY = y; 
        
    }
 }

 PortletPopupWindow.prototype.setPosition = function()
 { 
   this.container.style.width = this.width; 
   if (document.all) {	
           this.container.style.posLeft = this.left;
       	   this.container.style.posTop = this.top - this.layout.popup;
           this.header.style.posLeft = this.left + this.layout.titleRelativeLeft;
       	   this.header.style.posTop = this.top + this.layout.titleRelativeTop - this.layout.popup;
       	   var RelativeRight = this.layout.minRelativeRight + this.layout.buttonRelative;
       	   if(this.configMode){
           	  this.configButton.style.posLeft = this.left + this.width - RelativeRight;
           	  this.configButton.style.posTop = this.top + this.layout.buttonRelativeTop - this.layout.popup;
           	  RelativeRight = RelativeRight + this.layout.buttonRelative;
           }
       	   if(this.helpMode){
           	  this.helpButton.style.posLeft = this.left + this.width - RelativeRight;
           	  this.helpButton.style.posTop = this.top + this.layout.buttonRelativeTop - this.layout.popup;
           	  RelativeRight = RelativeRight + this.layout.buttonRelative;
           }
       	   if(this.editMode){
           	  this.editButton.style.posLeft = this.left + this.width - RelativeRight;
           	  this.editButton.style.posTop = this.top + this.layout.buttonRelativeTop - this.layout.popup;
           	  RelativeRight = RelativeRight + this.layout.buttonRelative;
           }
           if(this.refreshMode){
           	  this.refreshButton.style.posLeft = this.left + this.width - RelativeRight;
           	  this.refreshButton.style.posTop = this.top + this.layout.buttonRelativeTop;
           }          
	       this.minButton.style.posLeft = this.left + this.width - this.layout.minRelativeRight;
           this.minButton.style.posTop = this.top + this.layout.buttonRelativeTop - this.layout.popup;
           this.maxButton.style.posLeft = this.left + this.width - this.layout.maxRelativeRight;
           this.maxButton.style.posTop = this.top + this.layout.buttonRelativeTop - this.layout.popup;                      
           if(this.closeable){
              this.closeButton.style.posLeft = this.left + this.width - this.layout.closeRelativeRight;
      		  this.closeButton.style.posTop = this.top + this.layout.buttonRelativeTop - this.layout.popup;
    	   } 
    	}    
    	else {        
           this.container.style.left = this.left;
           this.container.style.top = this.top - this.layout.popup;
           this.header.style.left = this.left + this.layout.titleRelativeLeft;
       	   this.header.style.top = this.top + this.layout.titleRelativeTop  - this.layout.popup;
       	   var RelativeRight = this.layout.minRelativeRight + this.layout.buttonRelative;
           if(this.configMode){
           	  this.configButton.style.left = this.left + this.width - RelativeRight;
           	  this.configButton.style.top = this.top + this.layout.buttonRelativeTop - this.layout.popup;
           	  RelativeRight = RelativeRight + this.layout.buttonRelative;
           }
           if(this.helpMode){
           	  this.helpButton.style.left = this.left + this.width - RelativeRight;
           	  this.helpButton.style.top = this.top + this.layout.buttonRelativeTop - this.layout.popup;
           	  RelativeRight = RelativeRight + this.layout.buttonRelative;
           }
       	   if(this.editMode){
           	  this.editButton.style.left = this.left + this.width - RelativeRight;
           	  this.editButton.style.top = this.top + this.layout.buttonRelativeTop - this.layout.popup;
           	  RelativeRight = RelativeRight + this.layout.buttonRelative;
           }
           if(this.refreshMode){
           	  this.refreshButton.style.left = this.left + this.width - RelativeRight;
           	  this.refreshButton.style.top = this.top + this.layout.buttonRelativeTop - this.layout.popup;
           }          
           this.minButton.style.left = this.left + this.width - this.layout.minRelativeRight;
           this.minButton.style.top = this.top + this.layout.buttonRelativeTop - this.layout.popup;
           this.maxButton.style.left = this.left + this.width - this.layout.maxRelativeRight;
           this.maxButton.style.top = this.top + this.layout.buttonRelativeTop - this.layout.popup;           
           if(this.closeable){
              this.closeButton.style.left = this.left + this.width - this.layout.closeRelativeRight;
              this.closeButton.style.top = this.top + this.layout.buttonRelativeTop - this.layout.popup;
    	   } 
    	}
        this.setFocus();
 }

PortletPopupWindow.prototype.autoRefresh = function()
 {
   if(this.autoRefreshed){       
       if(this.firstTimeAutoRefreshed){
       	  this.firstTimeAutoRefreshed = false;
       }else{
       	  this.refresh();
       }
       setTimeout("Light.portal.tabs["+this.tIndex+"].portlets["+this.position+"]["+this.index+"].autoRefresh()", this.periodTime);
    }
 }
 
PortletPopupWindow.prototype.refresh = function()
 {  

  if(this.allowJS){
      var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId
      		    +"&mode="+this.mode;
      if(this.parameter.length > 0)
      	pars = pars+"&"+ this.parameter;
      	
      	pars = pars+"&width="+this.width; //add by zhoucz 070322
	    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
    }else{
      if(this.parameter.length > 0){
            ajaxEngine.sendRequest(this.request
      					, "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
                    	, this.parameter
                    	, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                    	, "portletId="+this.serverId
                    	, "mode="+this.mode
                    	, "width="+this.width
                    	);
      }else{          
      		ajaxEngine.sendRequest(this.request
      					, "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      					, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                   		, "portletId="+this.serverId
                   		, "mode="+this.mode
                   		, "width="+this.width
                    	);
      }
    }     
 }

PortletPopupWindow.prototype.changePosition = function()
{
   var params = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId
                +"&column="+this.position
                +"&row="+this.index
                +"&width="+this.width;
                  
   new Ajax.Request(Light.portal.contextPath+Light.changePositionRequest,{parameters:params});   
   Light.portal.tabs[this.tIndex].rePositionPortlets(this);    
 }

PortletPopupWindow.prototype.changePosition2 = function()
 { 

   	Light.portal.tabs[this.tIndex].rePositionPortlets(this);    
   	if(this.allowJS){
      var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId
      		    +"&mode=changePosition"
                    +"&originalMode="+this.mode
                    +"&column="+this.position
                    +"&row="+this.index;
      if(this.parameter.length > 0)
      	pars = pars+"&"+ this.parameter;
      	pars = pars+"&width="+this.width; //add by zhoucz 070322
	    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
    }else{
      if(this.parameter.length > 0){
            ajaxEngine.sendRequest(this.request
      					, "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
                    	, this.parameter
                    	, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                    	, "portletId="+this.serverId
                    	, "mode=changePosition"
                        , "originalMode="+this.mode
                        , "column="+this.position
                        , "row="+this.index
                        , "width="+this.width
                       
                    	);
      }else{          
      		ajaxEngine.sendRequest(this.request
      					, "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      					, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                   		, "portletId="+this.serverId
                   		, "mode=changePosition"
                        	, "originalMode="+this.mode
                        	, "column="+this.position
                        	, "row="+this.index
                        	, "width="+this.width
                    		);
      }
    }      
 }

PortletPopupWindow.prototype.edit = function()
 {

   if(this.editMode){ 
        this.mode= Light._EDIT_MODE;
        var id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index;
        if(this.allowJS){
	   var pars = "responseId="+id
	                +"&mode="+this.mode
	      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
	      		    +"&portletId="+this.serverId;
           if(this.parameter.length > 0)
      	      pars = pars+"&"+ this.parameter;	
      	      
      	      pars = pars+"&width="+this.width; //add by zhoucz 070322      
	   ajaxEngine.sendRequestAndUpdate(this.request,id,{evalScripts: true, parameters: pars});
	}else{
          if(this.parameter.length > 0){
            ajaxEngine.sendRequest(this.request
      		, "responseId="+id
                    	, this.parameter
                    	, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                    	, "portletId="+this.serverId
                    	, "mode="+this.mode
                    	, "width="+this.width
                    	);
          }else{          
      		ajaxEngine.sendRequest(this.request
      		, "responseId="+id
      					, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                   		, "portletId="+this.serverId
                   		, "mode="+this.mode
                   		, "width="+this.width
                    	);
          }	      
	}           	
        var vdocument = document.getElementById('panel_tab_page'+this.tIndex);
    	vdocument.removeChild(this.editButton);
    	this.editButton = this.createPortletCancelEditButton(this);
    	vdocument.appendChild(this.editButton);
    	this.setPosition();
    }
 }

PortletPopupWindow.prototype.cancelEdit = function()
 {
   if(this.editMode){
    this.mode= Light._VIEW_MODE;
    if(this.allowJS){
      var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId;
      if(this.parameter.length > 0)
      	pars = pars+"&"+ this.parameter;
      	pars = pars+"&width="+this.width; //add by zhoucz 070322
	    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
    }else{
	    if(this.parameter.length > 0){   
			ajaxEngine.sendRequest(this.request
							, "responseId="+Light._PC_PREFIX +this.tIndex+"_"+ this.position + "_" + this.index
		                    ,this.parameter
		                    , "tabId="+Light.portal.tabs[this.tIndex].tabServerId
	                    	, "portletId="+this.serverId
	                    	, "width="+this.width
	                    	);
		}else{
		  	ajaxEngine.sendRequest(this.request
						, "responseId="+Light._PC_PREFIX +this.tIndex+"_"+ this.position + "_" + this.index
						, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
	                    , "portletId="+this.serverId
	                    , "width="+this.width
	                    );
		}    
    }      
   	
	var vdocument = document.getElementById('panel_tab_page'+this.tIndex);
    vdocument.removeChild(this.editButton);
    this.editButton = this.createPortletEditButton(this);
    vdocument.appendChild(this.editButton);
    this.setPosition();
   }
 }
 
PortletPopupWindow.prototype.help = function()
 {

   if(this.helpMode){ 
        this.mode = Light._HELP_MODE;   
        var id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index;
        if(this.allowJS){
	   var pars = "responseId="+id
	                +"&mode="+this.mode
	      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
	      		    +"&portletId="+this.serverId;
           if(this.parameter.length > 0)
      	      pars = pars+"&"+ this.parameter;	  
		pars = pars+"&width="+this.width; //add by zhoucz 070322
	   ajaxEngine.sendRequestAndUpdate(this.request,id,{evalScripts: true, parameters: pars});
	}else{
          if(this.parameter.length > 0){
            ajaxEngine.sendRequest(this.request
      		, "responseId="+id
                    	, this.parameter
                    	, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                    	, "portletId="+this.serverId
                    	, "mode="+this.mode
                    	, "width="+this.width
                    	);
          }else{          
      		ajaxEngine.sendRequest(this.request
      		, "responseId="+id
      					, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                   		, "portletId="+this.serverId
                   		, "mode="+this.mode
                   		, "width="+this.width
                    	);
          }	      
	}   
    	var vdocument = document.getElementById('panel_tab_page'+this.tIndex);
    	vdocument.removeChild(this.helpButton);
    	this.helpButton = this.createPortletCancelHelpButton(this);
    	vdocument.appendChild(this.helpButton);
    	this.setPosition();
    }
 }
 
PortletPopupWindow.prototype.cancelHelp = function()
 {

   if(this.helpMode){
    this.mode = Light._VIEW_MODE;
    if(this.allowJS){
      var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId;
      if(this.parameter.length > 0)
      	pars = pars+"&"+ this.parameter;
      	pars = pars+"&width="+this.width; //add by zhoucz 070322
	    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
    }else{
	    if(this.parameter.length > 0){   
			ajaxEngine.sendRequest(this.request
							, "responseId="+Light._PC_PREFIX +this.tIndex+"_"+ this.position + "_" + this.index
		                    ,this.parameter
		                    , "tabId="+Light.portal.tabs[this.tIndex].tabServerId
	                    	, "portletId="+this.serverId
	                    	, "width="+this.width
	                    	);
		}else{
		  	ajaxEngine.sendRequest(this.request
						, "responseId="+Light._PC_PREFIX +this.tIndex+"_"+ this.position + "_" + this.index
						, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
	                    , "portletId="+this.serverId
	                    , "width="+this.width
	                    );
		}    
    }    
    
    var vdocument = document.getElementById('panel_tab_page'+this.tIndex);
    vdocument.removeChild(this.helpButton);
    this.helpButton = this.createPortletHelpButton(this);
    vdocument.appendChild(this.helpButton);
    this.setPosition();
   }
 }

PortletPopupWindow.prototype.config = function()
 {
   if(this.configMode){ 
        this.mode = Light._CONFIG_MODE;   
        var id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index;
        if(this.allowJS){
	   var pars = "responseId="+id
	                +"&mode="+this.mode
	      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
	      		    +"&portletId="+this.serverId;
           if(this.parameter.length > 0)
      	      pars = pars+"&"+ this.parameter;	
      	    pars = pars+"&width="+this.width; //add by zhoucz 070322      
	   ajaxEngine.sendRequestAndUpdate(this.request,id,{evalScripts: true, parameters: pars});
	}else{
          if(this.parameter.length > 0){
            ajaxEngine.sendRequest(this.request
      		, "responseId="+id
                    	, this.parameter
                    	, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                    	, "portletId="+this.serverId
                    	, "mode="+this.mode
                    	, "width="+this.width
                    	);
          }else{          
      		ajaxEngine.sendRequest(this.request
      		, "responseId="+id
      					, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
                   		, "portletId="+this.serverId
                   		, "mode="+this.mode
                   		, "width="+this.width
                    	);
          }	      
	}   
    	var vdocument = document.getElementById('panel_tab_page'+this.tIndex);
    	vdocument.removeChild(this.configButton);
    	this.configButton = this.createPortletCancelConfigButton(this);
    	vdocument.appendChild(this.configButton);
    	this.setPosition();
    }
 }
 
PortletPopupWindow.prototype.cancelConfig = function()
 {
   if(this.configMode){
    this.mode = Light._VIEW_MODE;
    if(this.allowJS){
      var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId;
      if(this.parameter.length > 0)
      	pars = pars+"&"+ this.parameter;
      	pars = pars+"&width="+this.width; //add by zhoucz 070322
	    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
    }else{
	    if(this.parameter.length > 0){   
			ajaxEngine.sendRequest(this.request
							, "responseId="+Light._PC_PREFIX +this.tIndex+"_"+ this.position + "_" + this.index
		                    ,this.parameter
		                    , "tabId="+Light.portal.tabs[this.tIndex].tabServerId
	                    	, "portletId="+this.serverId
	                    	, "width="+this.width
	                    	);
		}else{
		  	ajaxEngine.sendRequest(this.request
						, "responseId="+Light._PC_PREFIX +this.tIndex+"_"+ this.position + "_" + this.index
						, "tabId="+Light.portal.tabs[this.tIndex].tabServerId
	                    , "portletId="+this.serverId
	                    , "width="+this.width
	                    );
		}    
    }    
    
    var vdocument = document.getElementById('panel_tab_page'+this.tIndex);
    vdocument.removeChild(this.configButton);
    this.configButton = this.createPortletConfigButton(this);
    vdocument.appendChild(this.configButton);
    this.setPosition();
   }
 }

PortletPopupWindow.prototype.moveUp = function()
 {

   if(this.index > 0){
     var temp = null;
     var upIndex = 0;
     var currentIndex = this.index;
     var started = this.index - 1;                 
        for(var i=started;i>=0;i--){
           if(Light.portal.tabs[this.tIndex].portlets[this.position][i] != null){
             temp = Light.portal.tabs[this.tIndex].portlets[this.position][i];
             upIndex = i;
             break;
           }
        }               
        if(temp != null){
          temp.index = this.index;
          this.index = upIndex;
          temp.container.id = Light._PC_PREFIX+this.tIndex+"_"+temp.position+"_"+temp.index;          
          this.container.id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+this.index;  
          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+temp.position+"_"+temp.index);
          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+ this.position + "_" + this.index);        
          Light.portal.tabs[this.tIndex].portlets[this.position][upIndex] = this;
          Light.portal.tabs[this.tIndex].portlets[this.position][currentIndex] = temp; 
          temp.changePosition();   
          this.left = Light.portal.tabs[this.tIndex].between;
    	  for(var i=0;i<this.position;i++) { 
        	this.left += Light.portal.tabs[this.tIndex].widths[i] + Light.portal.tabs[this.tIndex].between;
    	  }
          this.changePosition();      
          //Light.portal.tabs[this.tIndex].rePositionPortlets(this);
        }     
   }
 }
 
 PortletPopupWindow.prototype.moveDown = function()
 {

    var temp = null;
    var downIndex = 0;
    var currentIndex = this.index;
    var started = this.index + 1;                  
    for(var i=started;i<Light.portal.tabs[this.tIndex].portlets[this.position].length;i++){
       if(Light.portal.tabs[this.tIndex].portlets[this.position][i] != null){
         temp = Light.portal.tabs[this.tIndex].portlets[this.position][i];
         downIndex = i;
         break;
       }
    }               
    if(temp != null){
      temp.index = this.index;
      this.index = downIndex;
      temp.container.id = Light._PC_PREFIX+this.tIndex+"_"+temp.position+"_"+temp.index;          
      this.container.id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+this.index;          
      ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+temp.position+"_"+temp.index);
      ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+ this.position + "_" + this.index);        
      Light.portal.tabs[this.tIndex].portlets[this.position][downIndex] = this;
      Light.portal.tabs[this.tIndex].portlets[this.position][currentIndex] = temp;          
      this.changePosition();
      this.left = Light.portal.tabs[this.tIndex].between;
    	  for(var i=0;i<this.position;i++) { 
        	this.left += Light.portal.tabs[this.tIndex].widths[i] + Light.portal.tabs[this.tIndex].between;
    	  }
      temp.changePosition();             
      //Light.portal.tabs[this.tIndex].rePositionPortlets(temp);
    }    
 }
 
 PortletPopupWindow.prototype.moveLeft = function()
 {
   if(this.position > 0){
     var temp = null;
     var newIndex = 0;
     var currentPosition = this.position;
     var currentIndex = this.index;
     var column = this.position - 1; 
     if(Light.portal.tabs[this.tIndex].portlets[column] == null)
        Light.portal.tabs[this.tIndex].portlets[column] = new Array();
     var len = Light.portal.tabs[this.tIndex].portlets[column].length;   
     for(var i=0;i<len;i++){
       if(Light.portal.tabs[this.tIndex].portlets[column][i] != null 
       && this.top < Light.portal.tabs[this.tIndex].portlets[column][i].top){
            temp = Light.portal.tabs[this.tIndex].portlets[column][i];
            newIndex = temp.index;
            break;
       }
     }                    
                
        if(temp != null){
          for(var i= len - 1;i>=0;i--){
	       if(Light.portal.tabs[this.tIndex].portlets[column][i] != null){
	          var temp2 = Light.portal.tabs[this.tIndex].portlets[column][i];
	          temp2.index++;
	          temp2.container.id = Light._PC_PREFIX+temp2.tIndex+"_"+temp2.position+"_"+temp2.index;  
	          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+temp2.tIndex+"_"+temp2.position+"_"+temp2.index);
	          Light.portal.tabs[this.tIndex].portlets[column][i + 1]= temp2;
	          Light.portal.tabs[this.tIndex].portlets[column][i + 1].changePosition();
	          Light.portal.tabs[this.tIndex].portlets[column][i]= null;
                  if(temp2.serverId == temp.serverId) break;	         
	       }
	      }   
	      this.position = column;
              this.index = newIndex;                            
          this.container.id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+this.index;           
          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+ this.position + "_" + this.index);        
          Light.portal.tabs[this.tIndex].portlets[this.position][this.index] = this;          
          Light.portal.tabs[this.tIndex].portlets[currentPosition][currentIndex] = null; 
          this.left = Light.portal.tabs[this.tIndex].between;
    	  for(var i=0;i<this.position;i++) { 
        	this.left += Light.portal.tabs[this.tIndex].widths[i] + Light.portal.tabs[this.tIndex].between;
    	  }
          this.width = Light.portal.tabs[this.tIndex].widths[this.position];  
          this.changePosition();                     
        }else{
          this.position = column;  
          var isEmpty = true;
          for(var i= len - 1;i>=0;i--){
	       if(Light.portal.tabs[this.tIndex].portlets[column][i] != null){
	          var temp3 = Light.portal.tabs[this.tIndex].portlets[column][i];
                  this.index = temp3.index + 1;
                  isEmpty = false;
	          break;	         
	       }
	  }          
          if(isEmpty) this.index = 0;
          this.container.id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+this.index;  
          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+ this.position + "_" + this.index);        
          Light.portal.tabs[this.tIndex].portlets[this.position][this.index] = this;   
          Light.portal.tabs[this.tIndex].portlets[currentPosition][currentIndex] = null;        
          this.left = Light.portal.tabs[this.tIndex].between;
    	  for(var i=0;i<this.position;i++) { 
        	this.left += Light.portal.tabs[this.tIndex].widths[i] + Light.portal.tabs[this.tIndex].between;
    	  }
	  this.width = Light.portal.tabs[this.tIndex].widths[this.position];
          this.changePosition();        
        }
      var length = Light.portal.tabs[this.tIndex].portlets[currentPosition].length;   
     for(var i=currentIndex+1;i<length;i++){
       if(Light.portal.tabs[this.tIndex].portlets[currentPosition][i] != null){
         var next = Light.portal.tabs[this.tIndex].portlets[currentPosition][i];
         Light.portal.tabs[this.tIndex].rePositionPortlets(next);
         break;
       }
     }          
   }
 }
 
 PortletPopupWindow.prototype.moveRight = function()
 {      
   columns = Light.portal.tabs[this.tIndex].widths.length;
   if(this.position + 1 < columns 
    && Light.portal.tabs[this.tIndex].portlets[this.position + 1] == null)
      Light.portal.tabs[this.tIndex].portlets[this.position + 1] = new Array();
   if(Light.portal.tabs[this.tIndex].portlets[this.position + 1] != null){
     var temp = null;
     var newIndex = 0;
     var currentPosition = this.position;
     var currentIndex = this.index;
     var column = this.position + 1;              
     var len = Light.portal.tabs[this.tIndex].portlets[column].length;   
     for(var i=0;i<len;i++){
       if(Light.portal.tabs[this.tIndex].portlets[column][i] != null
       && this.top < Light.portal.tabs[this.tIndex].portlets[column][i].top){
            temp = Light.portal.tabs[this.tIndex].portlets[column][i];
            newIndex = temp.index;
            break;
       }
     }               
     if(temp != null){                    
          for(var i= len - 1;i>=0;i--){
	       if(Light.portal.tabs[this.tIndex].portlets[column][i] != null){
	          var temp2 = Light.portal.tabs[this.tIndex].portlets[column][i];
	          temp2.index++;
	          temp2.container.id = Light._PC_PREFIX+temp2.tIndex+"_"+temp2.position+"_"+temp2.index;  
	          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+temp2.tIndex+"_"+temp2.position+"_"+temp2.index);
	          Light.portal.tabs[this.tIndex].portlets[column][i + 1]= temp2;
	          Light.portal.tabs[this.tIndex].portlets[column][i + 1].changePosition();
	          Light.portal.tabs[this.tIndex].portlets[column][i]= null;
                  if(temp2.serverId == temp.serverId) break;	         
	       }
	      }   
	      this.position = column;
              this.index = newIndex;                    
          this.container.id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+this.index;  
          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+ this.position + "_" + this.index);                  
          Light.portal.tabs[this.tIndex].portlets[this.position][this.index] = this;          
	  Light.portal.tabs[this.tIndex].portlets[currentPosition][currentIndex] = null; 
          this.left = Light.portal.tabs[this.tIndex].between;
    	  for(var i=0;i<this.position;i++) { 
        	this.left += Light.portal.tabs[this.tIndex].widths[i] + Light.portal.tabs[this.tIndex].between;
    	  }
          this.width = Light.portal.tabs[this.tIndex].widths[this.position]; 
          this.changePosition();    
        }else{
          this.position = column;
          var isEmpty = true;
          for(var i= len - 1;i>=0;i--){
	       if(Light.portal.tabs[this.tIndex].portlets[column][i] != null){
	          var temp3 = Light.portal.tabs[this.tIndex].portlets[column][i];
                  this.index = temp3.index + 1;
                  isEmpty = false;
	          break;	         
	       }
	  }          
          if(isEmpty) this.index = 0;        
          this.container.id = Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+this.index;  
          ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+ this.position + "_" + this.index);        
          Light.portal.tabs[this.tIndex].portlets[this.position][this.index] = this;
          Light.portal.tabs[this.tIndex].portlets[currentPosition][currentIndex] = null; 
          this.left = Light.portal.tabs[this.tIndex].between;
    	  for(var i=0;i<this.position;i++) { 
        	this.left += Light.portal.tabs[this.tIndex].widths[i] + Light.portal.tabs[this.tIndex].between;
    	  }
	      this.width = Light.portal.tabs[this.tIndex].widths[this.position];
          this.changePosition();          
        }    
     var length = Light.portal.tabs[this.tIndex].portlets[currentPosition].length;   
     for(var i=currentIndex+1;i<length;i++){
       if(Light.portal.tabs[this.tIndex].portlets[currentPosition][i] != null){
         var next = Light.portal.tabs[this.tIndex].portlets[currentPosition][i];
         Light.portal.tabs[this.tIndex].rePositionPortlets(next);
         break;
       }
     }      
   }
 }

 PortletPopupWindow.prototype.minimize = function()
 {
   this.minimized = !this.minimized;
   var vdocument = document.getElementById('panel_tab_page'+this.tIndex);
   if(this.minimized){
    if(this.maximized){
       this.maximize();   
       this.minimized = true;     
    }
    var empty = "<img src='"+Light.portal.contextPath+"/portal/light/images/spacer.gif'"
		+" height='20' style='border: 0px' width='200' alt='' />";
    //this.body = this.container.innerHTML;
    this.container.innerHTML = empty;   
    vdocument.removeChild(this.minButton);
    this.minButton = this.createPortletRestoreMinButton(this);
    vdocument.appendChild(this.minButton); 
    this.round();
       
  }else{   
      var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId;
      if(this.parameter.length > 0)
      	pars = pars+"&"+ this.parameter;
      if(this.mode != Light._VIEW_MODE)
    	pars = pars+"&mode="+this.mode;
    	
    	pars = pars+"&width="+this.width; //add by zhoucz 070322    
      if(this.allowJS){
	    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
    }else{
	    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: false, parameters: pars});
    }    

    vdocument.removeChild(this.minButton);
    this.minButton = this.createPortletMinButton(this);
    vdocument.appendChild(this.minButton);    
    //this.container.innerHTML = this.body;
    
  }
  vdocument.removeChild(this.maxButton);
  this.maximized = false;
  this.maxButton = this.createPortletMaxButton(this);
  vdocument.appendChild(this.maxButton); 
  this.setPosition();  
  Light.portal.tabs[this.tIndex].rePositionPortlets(this);
 }
 
 PortletPopupWindow.prototype.maximize = function()
 { 
   var empty = "<img src='"+Light.portal.contextPath+"/portal/light/images/spacer.gif'"
		+" height='20' style='border: 0px' width='200' alt='' />";
   this.container.innerHTML = empty;
   var vdocument = document.getElementById('panel_tab_page'+this.tIndex);
   this.maximized = !this.maximized; 
   if(!this.maximized){         
    	this.top  = this.originalTop;	
    	this.width = this.originalWidth;
	this.left = this.originalLeft;   
      if(!this.minimized){ 
	    var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
	      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
	      		    +"&portletId="+this.serverId;
	    if(this.parameter.length > 0)
	      	pars = pars+"&"+ this.parameter;
	    if(this.mode != Light._VIEW_MODE)
    		pars = pars+"&mode="+this.mode; 
    	
    	 pars = pars+"&width="+this.width; //add by zhoucz 070322    	
		if(this.allowJS){    
		    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
	    }else{
		    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: false, parameters: pars}); 
	    }              
     }  
     vdocument.removeChild(this.maxButton);
     this.maxButton = this.createPortletMaxButton(this);
     vdocument.appendChild(this.maxButton);
     Light.portal.container.style.zIndex= 1;
   }else{
              
   	    this.left = Light.portal.layout.maxLeft;
   	    //this.top = Light.portal.layout.maxTop;
        this.width = Light.portal.layout.maxWidth;        
	
        vdocument.removeChild(this.maxButton);
    	this.maxButton = this.createPortletRestoreMaxButton(this);
    	vdocument.appendChild(this.maxButton);
        
        Light.portal.container.style.zIndex= ++Light.maxZIndex; 
        Light.portal.body.style.zIndex= ++Light.maxZIndex;
        Light.portal.footer.style.zIndex= ++Light.maxZIndex;
        
      	var pars = "responseId="+Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index
      		    +"&tabId="+Light.portal.tabs[this.tIndex].tabServerId
      		    +"&portletId="+this.serverId
      		    +"&state=maximized";
      	if(this.parameter.length > 0)
      		pars = pars+"&"+ this.parameter;
      	if(this.mode != Light._VIEW_MODE)
    		pars = pars+"&mode="+this.mode; 
    	pars = pars+"&width="+this.width; //add by zhoucz 070322    
   		if(this.allowJS){
	  		ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: true, parameters: pars});
	    }else{
		    ajaxEngine.sendRequestAndUpdate(this.request,Light._PC_PREFIX+this.tIndex+"_"+this.position+"_"+ this.index,{evalScripts: false, parameters: pars});
	    }            
   } 
   vdocument.removeChild(this.minButton);
   this.minimized = false;
   this.minButton = this.createPortletMinButton(this);
   vdocument.appendChild(this.minButton);         
   this.setPosition();
   this.setFocus();   
   Light.portal.tabs[this.tIndex].rePositionPortlets(this);
 }

 PortletPopupWindow.prototype.close = function()
 { 
 	popup = false;
   var closePortlet = confirm(LightResourceBundle._COMMAND_CLOSE_POPUP_PORTLET);
   if (!closePortlet) // user cancelled close closePortlet
	{
		return;
	}
  
   var vdocument = document.getElementById('panel_tab_page'+this.tIndex); 
   vdocument.removeChild(this.container);
   vdocument.removeChild(this.header);
   if(this.refreshMode){
      vdocument.removeChild(this.refreshButton);
   }
   if(this.editMode){
      vdocument.removeChild(this.editButton);
   }
   if(this.helpMode){
      vdocument.removeChild(this.helpButton);
   }
   if(this.configMode){
      vdocument.removeChild(this.configButton);
   } 
   vdocument.removeChild(this.minButton);
   vdocument.removeChild(this.maxButton);
   vdocument.removeChild(this.closeButton);
   Light.portal.tabs[this.tIndex].portlets[this.position][this.index] = null;   
   Light.portal.tabs[this.tIndex].rePositionPortlets(this); 
   
 }
 
 PortletPopupWindow.prototype.refreshWindow= function () { 

   var vdocument = document.getElementById('panel_tab_page'+this.tIndex);
   vdocument.removeChild(this.header);
   vdocument.removeChild(this.container);
   this.container = this.createPortletContainer(this);  
   this.header = this.createPortletHeader(this);    
   vdocument.appendChild(this.header);
   vdocument.appendChild(this.container);
   ajaxEngine.registerAjaxElement(Light._PC_PREFIX+this.tIndex+"_"+ this.position + "_" + this.index);
   this.setPosition();
   this.setFocus();
   
   
 }
 
 PortletPopupWindow.prototype.refreshHeader= function () {
 
   var vdocument = document.getElementById('panel_tab_page'+this.tIndex);
   vdocument.removeChild(this.header);
   this.header = this.createPortletHeader(this);
   
   vdocument.appendChild(this.header);
 }
 
 PortletPopupWindow.prototype.createPortletContainer = function (portlet) {
   var vContainer = document.createElement('div');
   vContainer.id = Light._PC_PREFIX+portlet.tIndex+"_"+portlet.position+"_"+portlet.index;
   vContainer.style.position = "absolute";
   vContainer.className = "portlet";  
   vContainer.style.width = portlet.width;
   vContainer.style.zIndex= ++Light.maxZIndex;
   if(this.contentBgColor != null && this.contentBgColor.length > 0)
   	  vContainer.style.backgroundColor = this.contentBgColor;
   return vContainer;
  }
  
  PortletPopupWindow.prototype.createPortletHeader = function (portlet) {
   var vHeader = document.createElement('div');
   //vHeader.id = "portletheader_"+portlet.position+"_"+portlet.index;
   vHeader.style.position = "absolute";
   vHeader.className = "portlet-header";
   
   if(Light.portal.tabs[this.tIndex].tabIsMoveable){
   	vHeader.onmousedown = function(e){ portlet.moveBegin(e);}
   	vHeader.onmousemove = function(e){ portlet.move(e);}
   	vHeader.onmouseup   = function(){ portlet.moveEnd();}
   	vHeader.onmouseout  = function(){ portlet.moveEnd();}
        vHeader.style.cursor= "move";
   }

   var inner = "";
   if(portlet.icon.length > 0){
      if(portlet.icon.substring(0,4) == "http")
      	inner = "<img onerror=\"this.src=this.src\" src='"+portlet.icon+"'/>&nbsp;";
      else
        inner = "<img onerror=\"this.src=this.src\" src='"+Light.portal.contextPath+portlet.icon+"'/>&nbsp;";
   }
   if(portlet.url.length > 0){
      inner = inner+"<a href='"+portlet.url+"' target='_blank'>";
      if(this.barFontColor.length > 0)
         inner = inner+"<font color='"+this.barFontColor+"'>";
      inner = inner+portlet.title;
      if(this.barFontColor.length > 0)
         inner = inner+"</font>";
      inner = inner+"</a>";
   }else
      inner = inner+portlet.title;   
   vHeader.innerHTML = inner;
   vHeader.style.zIndex= ++Light.maxZIndex;  
   if(this.barBgColor.length > 0)
   	  vHeader.style.backgroundColor = this.barBgColor;
   if(this.barFontColor.length > 0)
   	  vHeader.style.color = this.barFontColor;    
   return vHeader;
  }
  
  PortletPopupWindow.prototype.createPortletRefreshButton = function (portlet) { 
   var strIco="<span title='"+LightResourceBundle._REFRESH_PORTLET+"'><img src='"+Light.portal.contextPath+"/portal/light/images/refresh_on.gif' style='border: 0px;'/></span>";   
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('a');
   varA.innerHTML = strIco;
   varA.href = "javascript:void(0)";
   varA.onclick = function(){
     portlet.refresh();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }
  
  PortletPopupWindow.prototype.createPortletEditButton = function (portlet) { 
   var strIco="<span title='"+LightResourceBundle._EDIT_MODE+"'><img src='"+Light.portal.contextPath+"/portal/light/images/edit_on.gif' style='border: 0px;'/></span>";   
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('a');
   varA.innerHTML = strIco;
   varA.href = "javascript:void(0)";
   varA.onclick = function(){
     portlet.edit();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }
  
  PortletPopupWindow.prototype.createPortletCancelEditButton = function (portlet) { 
   var strIco="<span title='"+LightResourceBundle._VIEW_MODE+"'><img src='"+Light.portal.contextPath+"/portal/light/images/leave_edit_on.gif' style='border: 0px;'/></span>";   
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('a');
   varA.innerHTML = strIco;
   varA.href = "javascript:void(0)";
   varA.onclick = function(){
     portlet.cancelEdit();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }

  PortletPopupWindow.prototype.createPortletHelpButton = function (portlet) { 
   var strIco="<span title='"+LightResourceBundle._HELP_MODE+"'><img src='"+Light.portal.contextPath+"/portal/light/images/help_on.gif' style='border: 0px;'/></span>";   
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('a');
   varA.innerHTML = strIco;
   varA.href = "javascript:void(0)";
   varA.onclick = function(){
     portlet.help();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }
  
  PortletPopupWindow.prototype.createPortletCancelHelpButton = function (portlet) { 
   var strIco="<span title='"+LightResourceBundle._VIEW_MODE+"'><img src='"+Light.portal.contextPath+"/portal/light/images/leave_help_on.gif' style='border: 0px;'/></span>";   
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('a');
   varA.innerHTML = strIco;
   varA.href = "javascript:void(0)";
   varA.onclick = function(){
     portlet.cancelHelp();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }

  PortletPopupWindow.prototype.createPortletConfigButton = function (portlet) { 
   var strIco="<span title='"+LightResourceBundle._CONFIG_MODE+"'><img src='"+Light.portal.contextPath+"/portal/light/images/config_on.gif' style='border: 0px;'/></span>";   
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('a');
   varA.innerHTML = strIco;
   varA.href = "javascript:void(0)";
   varA.onclick = function(){
     portlet.config();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }
  
  PortletPopupWindow.prototype.createPortletCancelConfigButton = function (portlet) { 
   var strIco="<span title='"+LightResourceBundle._VIEW_MODE+"'><img src='"+Light.portal.contextPath+"/portal/light/images/leave_config_on.gif' style='border: 0px;'/></span>";   
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('a');
   varA.innerHTML = strIco;
   varA.href = "javascript:void(0)";
   varA.onclick = function(){
     portlet.cancelConfig();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }
  
  PortletPopupWindow.prototype.createPortletUpButton = function (portlet) { 
   var strIco="<span title='"+LightResourceBundle._MOVE_UP+"'><img src='"+Light.portal.contextPath+"/portal/light/images/up_on.gif' style='border: 0px;'/></span>";   
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var minA = document.createElement('a');
   minA.innerHTML = strIco;
   minA.href = "javascript:void(0)";
   minA.onclick = function(){
     portlet.moveUp();
   }
   vButton.appendChild(minA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }
   
  PortletPopupWindow.prototype.createPortletDownButton = function (portlet) { 
   var strIco="<span title='"+LightResourceBundle._MOVE_DOWN+"'><img src='"+Light.portal.contextPath+"/portal/light/images/down_on.gif' style='border: 0px;'/></span>";   
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var minA = document.createElement('a');
   minA.innerHTML = strIco;
   minA.href = "javascript:void(0)";
   minA.onclick = function(){
     portlet.moveDown();
   }
   vButton.appendChild(minA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }

  PortletPopupWindow.prototype.createPortletMinButton = function (portlet) { 
   var strIcoMin="<span title='"+LightResourceBundle._MINIMIZED+"'><img src='"+Light.portal.contextPath+"/portal/light/images/min_on.gif' style='border: 0px;'/></span>";   
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var minA = document.createElement('a');
   minA.innerHTML = strIcoMin;
   minA.href = "javascript:void(0)";
   minA.onclick = function(){
     portlet.minimize();
   }
   vButton.appendChild(minA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }
  
  PortletPopupWindow.prototype.createPortletRestoreMinButton = function (portlet) { 
   var strIcoMin = "<span title='"+LightResourceBundle._RESTORE_MINIMIZED+"'><img src='"+Light.portal.contextPath+"/portal/light/images/restore_on.gif' style='border: 0px'/></span>";   
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var minA = document.createElement('a');
   minA.innerHTML = strIcoMin;
   minA.href = "javascript:void(0)";
   minA.onclick = function(){
     portlet.minimize();
   }
   vButton.appendChild(minA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }

  PortletPopupWindow.prototype.createPortletMaxButton = function (portlet) {   
   var strIcoMax = "<span title='"+LightResourceBundle._MAXIMIZED+"'><img src='"+Light.portal.contextPath+"/portal/light/images/max_on.gif' style='border: 0px'/></span>";         
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var maxA = document.createElement('a');
   maxA.innerHTML = strIcoMax;
   maxA.href = "javascript:void(0)";
   maxA.onclick = function(){
     portlet.maximize();
   }
   vButton.appendChild(maxA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }
  
  PortletPopupWindow.prototype.createPortletRestoreMaxButton = function (portlet) {   
   var strIcoMax = "<span title='"+LightResourceBundle._RESTORE_MAXIMIZED+"'><img src='"+Light.portal.contextPath+"/portal/light/images/restore_on.gif' style='border: 0px'/></span>";         
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var maxA = document.createElement('a');
   maxA.innerHTML = strIcoMax;
   maxA.href = "javascript:void(0)";
   maxA.onclick = function(){
     portlet.maximize();
   }
   vButton.appendChild(maxA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }

  PortletPopupWindow.prototype.createPortletCloseButton = function (portlet)  {      
   var strIcoCls = "<span title='"+LightResourceBundle._CLOSE+"'><img onerror=\"this.src=this.src\" src='"+Light.portal.contextPath+"/portal/light/images/close_on.gif' style='border: 0px'/></span>";          
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var clsA = document.createElement('a');
   clsA.innerHTML = strIcoCls;
   clsA.href = "javascript:void(0)";
   clsA.onclick = function(){
     portlet.close();
   }
   vButton.appendChild(clsA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }

  PortletPopupWindow.prototype.createPortletBody = function (portlet) {   
   var vBody = document.createElement('div');
   vBody.id = 'portletbody_'+portlet.position+"_"+portlet.index;
   vBody.className = "portlet-body"; 
   vBody.onmousedown = function(){
      portlet.setFocus();   
   }
   return vBody;
  }
