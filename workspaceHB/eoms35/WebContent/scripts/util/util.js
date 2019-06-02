/**
 * 打开新窗口 默认为屏幕居中
 * @param {String} url 链接地址
 * @param {Object} config 属性设置
 */
eoms.openWindow = function(url, config) {
	var width = 790, height = 480, name;
	var features = "fullscreen=0,toolbar=0,location=0,menubar=0,scrollbars=1,resizable=1,";
	if(config && typeof config == "object"){
		for(p in config){
			var re = new RegExp(p+"=(0|1)","g");
			features = features.replace(re,p+"="+config[p]);
			if(p=="width"){
				width = config[p];
			}
			if(p=="height"){
				height = config[p];
			}
			if(p=="name"){
				name = config.name;
			}
		}
	}
	var top = (window.screen.height / 2) - (height / 2);
	var left = (window.screen.width / 2) - (width / 2);
	features += "width=" + width + ",height=" + height + ",top="+top+",left="+left;
	var newwindow = window.open(url,name,features);
	newwindow.focus();
	return newwindow;
};