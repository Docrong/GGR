<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8" src="${app}/scripts/base/eoms.js"></script>
<%@ include file="/common/extlibs.jsp"%>
<style type="text/css">
#calendar{
  margin:1px;
  width:150px
}
</style>
<form id ="form1">
<div id="calendar"></div>
</form>
<script type="text/javascript">
    Ext.onReady(function(){    
    	var calendarCfg = {
			keyFormat : 'Y-m-d',
	  		getKey : function(/*Date*/date){
				var d = date || this.value;
				return d.format(this.keyFormat);
	  		},
	  		handler : function(){
	    		w = window.open("${app}/networkcalendar/networkcalendarmain.do?method=networkcalendarList&date="+this.getKey(),'addRecord');	    		
	    		setTimeout("w.focus();",0);
	  		}
		};
    	c = new Ext.DatePicker(calendarCfg);
    	c.render('calendar');
    });
</script>

