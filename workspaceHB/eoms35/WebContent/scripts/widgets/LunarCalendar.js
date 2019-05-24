/**
 * 农历日期选择控件
 * <html:form action="/TawWorkdaySetAction.do?method=x_save" method="post" name = "form1"styleId="TawWorkdaySetForm">
 * new eoms.LunarCalendar("calendar");
 */

eoms.LunarCalendar = function(el,config){
	this.el = Ext.get(el);
    eoms.LunarCalendar.superclass.constructor.call(this, config);
    this.render(this.el);
};

Ext.extend(eoms.LunarCalendar, Ext.DatePicker, {	
	//作为记录标识的日期表示格式，例如2008-06-12
	keyFormat : 'Y-m-d',
	
	//得到指定日期或当前日期的key值
	getKey : function(/*Date*/date){
		var d = date || this.value;
		return d.format(this.keyFormat);
	},	
	//是否工作日
	isWorkDay : function(date){
		return (date.getDay()!=0 && date.getDay()!=6);
	},
	//得到当前日期的农历对象
	getLunar : function(d){
		return new Lunar(d);
	},
	//得到当前日期的农历日期
	getLunarDay : function(d){
		return this.getLunar(d).getDay();
	},
	updateCellClass : function(d,cell){
	},
	//用于保存当前修改操作的对象
	saveData : new Ext.util.MixedCollection(),
	
	log : function(text){
	},

	//日期点击时的处理
	handler : function(datePicker,date){
		eoms.log(date);			
	},
	// private
    update : function(date){
    	// date:当前时间； activeDate:当前选择的时间
    	//鼠标点击时间与当前的时间不一致时，先清除当前日期的样式，再将样式加到选中的日期上
        var vd = this.activeDate;
        this.activeDate = date;
        if(vd && this.el){ 
            var t = date.getTime();
            if(vd.getMonth() == date.getMonth() && vd.getFullYear() == date.getFullYear()){ 
                this.cells.removeClass("x-date-selected");
                this.cells.each(function(c){
                   if(c.dom.firstChild.dateValue == t){
                       c.addClass("x-date-selected");
                       setTimeout(function(){//为了解决浏览器问题
                            try{c.dom.firstChild.focus();}catch(e){}
                       }, 50);
                       return false;
                   }
                });
                return;
            }
        }
        //月份首天 (是周几 及其定位)
        var days = date.getDaysInMonth();//每月天数
        var firstOfMonth = date.getFirstDateOfMonth();//每月首天
        var startingPos = firstOfMonth.getDay()-this.startDay;//每月首天的星期
        if(startingPos <= this.startDay){
            startingPos += 7;
        }

        var pm = date.add("mo", -1);//每月的某天是周几
        var prevStart = pm.getDaysInMonth()-startingPos;//当月的天数减去某日的星期数
        var cells = this.cells.elements;
        var textEls = this.textNodes;
        days += startingPos;   //末尾天的的定位
        
        // convert everything to numbers so it's fast
        var day = 86400000;
        var d = (new Date(pm.getFullYear(), pm.getMonth(), prevStart)).clearTime();
        var today = new Date().clearTime().getTime();
        var sel = date.clearTime().getTime();
        var min = this.minDate ? this.minDate.clearTime() : Number.NEGATIVE_INFINITY;
        var max = this.maxDate ? this.maxDate.clearTime() : Number.POSITIVE_INFINITY;
        var ddMatch = this.disabledDatesRE;
        var ddText = this.disabledDatesText;
        var ddays = this.disabledDays ? this.disabledDays.join("") : false;
        var ddaysText = this.disabledDaysText;
        var format = this.format;
        
        //将日历的内容表现出来，根据设置的样式
        var setCellClass = function(cal, cell){
            cell.title = "";
            var t = d.getTime();         
            cell.firstChild.dateValue = t;
            
            cal.updateCellClass(d,cell);
            
            //非工作日样式
            if(!cal.isWorkDay(d)){
              cell.className += " x-date-notworkday";
            }
              
            //当天的样式
            if(t == today ){
                cell.className += " x-date-today";
                cell.title = cal.todayText;
            }
            //选中的样式
            if(t == sel){
                cell.className += " x-date-selected";
                setTimeout(function(){
                    try{cell.firstChild.focus();}catch(e){}
                }, 50);
            }
            // disabling
            if(t < min) {
                cell.className = " x-date-disabled";
                cell.title = cal.minText;
                return;
            }
            if(t > max) {
                cell.className = " x-date-disabled";
                cell.title = cal.maxText;
                return;
            }
            if(ddays){
                if(ddays.indexOf(d.getDay()) != -1){
                    cell.title = ddaysText;
                    cell.className = " x-date-disabled";
                }
            }
            if(ddMatch && format){
                var fvalue = d.dateFormat(format);
                if(ddMatch.test(fvalue)){
                    cell.title = ddText.replace("%0", fvalue);
                    cell.className = " x-date-disabled";
                }
            }
        };

        var i = 0;
        //循环打印出上月的天（为了补充42格），样式为灰色
        for(; i < startingPos; i++) { 
            textEls[i].innerHTML = (++prevStart);
            d.setDate(d.getDate()+1);
            cells[i].className = "x-date-prevday";
            setCellClass(this, cells[i]);
        }
        //循环打印出当前月的所有的天，样式为高亮色
        for(; i < days; i++){
            intDay = i - startingPos + 1;
            d.setDate(d.getDate()+1);    
            
            //添加农历日期
            var lunarDay = this.getLunarDay(new Date(d.getTime()));         
            
            textEls[i].innerHTML = (intDay)+"<br/>"+lunarDay;
            cells[i].className = "x-date-active";
            setCellClass(this, cells[i]);
        }
        var extraDays = 0;
        //循环打印出下月的天（为了补充42格），样式为灰色
        for(; i < 42; i++) {
             textEls[i].innerHTML = (++extraDays);
             d.setDate(d.getDate()+1);
             cells[i].className = "x-date-nextday";
             setCellClass(this, cells[i]);
        }

        this.mbtn.setText(date.getFullYear() + "年 " +this.monthNames[date.getMonth()]);

        if(!this.internalRender){
            var main = this.el.dom.firstChild;
            //修改宽度以适应中文
            //var w = main.offsetWidth;
            var w = 370;
            this.el.setWidth(w + this.el.getBorderWidth("lr"));
            Ext.fly(main).setWidth(w);
            this.internalRender = true;
            // opera does not respect the auto grow header center column
            // then, after it gets a width opera refuses to recalculate
            // without a second pass
            if(Ext.isOpera && !this.secondPass){
                main.rows[0].cells[1].style.width = (w - (main.rows[0].cells[0].offsetWidth+main.rows[0].cells[2].offsetWidth)) + "px";
                this.secondPass = true;
                this.update.defer(10, this, [date]);
			}
		}
	}

});

