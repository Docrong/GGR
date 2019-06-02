/**
 * 多级联动下拉框控件
 */
eoms.ComboBox = function(){
	var dataUrl = "/dict/tawSystemDictTypes.do?method=xsearch&dictId=";
	var dataStore = new Ext.util.MixedCollection();
	var Options = eoms.form.Options;
	return {
		/**
		 * 执行联动
		 */
		doCombo : function(/*HTML Element*/el,/*String*/subId,/*String*/actionPath){  
			var subEl = document.getElementById(subId);
			if(!el || !subEl){
  				return;
  			}
  					
			if(actionPath){
  	  			this.getData("", subEl, actionPath);
  			}
  			else{
  	  			this.getData(el.value, subEl, el.getAttribute("ds") || dataUrl);
  			}

    		if(subEl.getAttribute("subid")){
      			this.resetsub(document.getElementById(subEl.getAttribute("subid")));    		
    		}			
		},
		/**
		 * 获取数据
		 * @param {String} dataId
		 * @param {String} subEl
		 * @param {String} url
		 */
		getData : function(dataId,subEl,url){
			//先在dataStore中查找
  			if(dataStore.containsKey(dataId)){
    			this.setData(dataStore.get(dataId),subEl,dataId);
    			return;
  			}
  			
  			//发送ajax请求
  			Ext.Ajax.request({
				method:'get',
               	url: eoms.appPath+url+dataId,
               	success: function(req){             		
					if(!req || req.responseText=="")return;
  					var ds = eoms.JSONDecode(req.responseText);
  					dataStore.add(dataId,ds);
  					
  					this.setData(ds,subEl,dataId);	
				},
               failure: this.handleFailure,
               scope: this
           });			
		},
		
		/**
		 * 填充子下拉框
		 * @param {Object} ds
		 * @param {Element} el
		 * @param {String} dictId
		 */
		setData : function(ds,el,dictId){
		  if(!ds.rows)return;
		  
		  if(ds.results>0){  
			  var optionstr = "<option value=\"\" selected=\"selected\">请选择(共"+ds.results+"项)</option>";
		
			  // 当多选模式时，默认没有“请选择”选项
			  if(el.multiple!=false) {
			  	optionstr = "";
			  }
			  
			  for (var i=0;i<ds.results;i++){
			    optionstr += "<option value=\""+ds.rows[i].value+"\">"+ds.rows[i].text+"</option>";
			  }
			  
			  el.disabled = false;
			  
			  if(eoms.isFF){
			  	el.innerHTML = optionstr;
			  }
			  else{
			  	el.length=0;
			  	Options.add(el,"请选择(共"+ds.results+"项)",'');
			  	for (var i=0;i<ds.results;i++){
			  		Options.add(el,ds.rows[i].text,ds.rows[i].value);
			  	}
			  }
		  	
		  }
		  else{
			// 目前vista IE7有bug，当列表中只有1项时点击会导致IE崩溃。
		  	el.length=0;
		  	Options.add(el,'无数据');
		  	// 设置成disabled避免bug
		  	el.disabled = 'disabled';
		  }		
		},
		resetsub : function(el){
		  if(el.disabled==false){
		    el.length = 0;
		    Options.add(el,"等待上级选择");
		    el.disabled = true;
		  }
		  if(el.getAttribute("subid")){
		    this.resetsub(document.getElementById(el.getAttribute("subid")));
		  }
		},
		/**
		 * 使两个select一起联动
		 */
		link : function(handleEl,el){
			var handler = Ext.get(handleEl);
			var linker = Ext.get(el);
			handler.on('change',function(e,el){
				linker.dom.value = el.value;
				this.doCombo(linker.dom,linker.dom.getAttribute('subid'));
			},this);
		},
		handleFailure : function(req){		
		}
		
	}
}();
