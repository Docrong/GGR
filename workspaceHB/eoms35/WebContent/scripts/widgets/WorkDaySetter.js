/**
 * 工作日设定控件
 */

eoms.WorkDaySetter = function(el, config) {
	this.tplData = config.tplData;
	this.tplId = config.tplId;
	eoms.WorkDaySetter.superclass.constructor.call(this, el, config);
};

Ext.extend(eoms.WorkDaySetter, eoms.LunarCalendar, {
	cfgFieldName : 'dateConfig',// 工作日设置radio的name
	from_prefix : 'from_', // 工作开始时间文本域的id前缀，如 from_0
	to_prefix : 'to_', // 工作结束时间文本域的id前缀，如 to_0
	keyFormat : 'Y-m-d', // 作为记录标识的日期表示格式，例如2008-06-12

	defaultWTime : ["8:00-12:00", "13:00-17:00",""],// 缺省工作时间

	// 是否工作日
	isWorkDay : function(date) {
		var isworkday = false;
		//有做过修改的日期
		if(this.tplData[this.getKey(date)])
		{	
			if(date.getDay() != 0 && date.getDay() != 6)
			{
				if(this.tplData[this.getKey(date)].config!='1')
				{
					isworkday = true;
				}
			}
			else if(date.getDay() == 0 || date.getDay() == 6)
			{
				if(this.tplData[this.getKey(date)].config=='2')
				{
					isworkday = true;
				}
			}			
		}
		//没有做过修改的日期
		else
		{
			if(date.getDay() != 0 && date.getDay() != 6)
			{
				isworkday =true;
			}
		}
		return isworkday;
	},

	updateCellClass : function(d, cell) {
		if (this.tplData[this.getKey(d)]){
			cell.className = " x-date-changed";
		}
	},
	// 点击日期模式设置时的事件
	onEditConfig : function(configValue) {
		configValue = parseInt(configValue);
		this.updateConfig(configValue);		
		this.save(this.getKey(), configValue, this.getTimes(configValue));
	},

	// 编辑工作时间后的事件
	onEditTimes : function() {
		var configValue = 2;
		if(this.getTimes(configValue)!=-1){
			this.save(this.getKey(), configValue, this.getTimes(configValue));
			return true;
		}
		else{
		    return false;
		}
	},
	// 得到其他设定参数
	onEditOptions : function(){
		var configValue = this.getConfig();
		this.save(this.getKey(), configValue, this.getTimes(configValue));
	},
	// 得到当前设定的config值
	getConfig : function() {
		var v;
		Ext.each(document.getElementsByName(this.cfgFieldName), function(r) {
			if (r.checked)
				v = r.value;
		});
		return v;		
	},
	// 得到当前设定的工作时间
	getTimes : function(configValue) {
		if (configValue == 2) {
			var arrTemp = [];
			for (var i = 0; i <= 2; i++) {
				var fromEl = Ext.getDom(this.from_prefix + i);
				var toEl = Ext.getDom(this.to_prefix + i);
				if (fromEl.value != "" && toEl.value != "") {
					var from = fromEl.value.split(":");
					var to = toEl.value.split(":")
					if(parseInt(from[0])>parseInt(to[0])||parseInt(from[0])==parseInt(to[0])&&parseInt(from[1])>parseInt(to[1])){
						return -1;
					}
					arrTemp.push(fromEl.value + "-" + toEl.value);
				}
			}
			return arrTemp;
		} else {
			return [];
		}
	},

	// 保存一个修改记录到this.saveData和this.tplData中
	save : function(key, configValue, time) {
		var overwrite = $("overwrite").checked;
		//alert(overwrite);
		var obj = {
			tplId : this.tplId,
			date : key,
			config : configValue,
			time : time || [],
			overwrite : overwrite
		};
		this.saveData.replace(key, obj);
		this.tplData[key] = obj;
		
		this.log();
		var a = [];
		this.saveData.each(function(item) {
			a.push(Ext.util.JSON.encode(item) + "\n");
		});
		Ext.getDom("data").value = "[" + a.toString() + "]";
		eoms.log("[" + a.toString() + "]");
	},

	log : function(text) {
	},
	// 更新对日期属性的设置和工作时间的设置
	updateConfig : function(value, time, overwrite) {
		//value = parseInt(value);
		
		//update config
		Ext.each(document.getElementsByName(this.cfgFieldName), function(r) {
			r.checked = (r.value == value);
		});
		
		//update times
		for (var i = 0; i <= 2; i++) {
			var fromEl = Ext.getDom(this.from_prefix + i);
			var toEl = Ext.getDom(this.to_prefix + i);
			
			fromEl.value = toEl.value = "";
			

			fromEl.disabled = true;
			toEl.disabled = true;
			switch (value) {
				case 0 :// 默认设置
					if (this.isWorkDay(this.value) && this.defaultWTime[i]) {
						var timedata = this.defaultWTime[i].split('-');
						fromEl.value = timedata[0];
						toEl.value = timedata[1];
					}
					break;
				case 1 :// 非工作日
					break;
				case 2 :// 非默认工作时间
					fromEl.disabled = false;
					toEl.disabled = false;
					var t = time || this.defaultWTime;
					if (t[i]) {
						fromEl.value = t[i].split('-')[0];
						toEl.value = t[i].split('-')[1];
					}
					break;
			}
		}
		
		//update options
		$("overwrite").checked = overwrite ;
	},
	// 日期点击时的处理
	handler : function(cal, date) {
		if (this.tplData[this.getKey()]) {
			var data = this.tplData[this.getKey()];
			this.updateConfig(parseInt(data.config), data.time, data.overwrite);
		} else {
			this.updateConfig(0);
		}
	}
});
