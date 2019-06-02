function item(object){
	return;
	for (i=0;i<document.all.openm.length;i++){
		eval("document.all.open"+i+"r").className="b_right";
		eval("document.all.open"+i+"m").className="b_middle";
		eval("document.all.open"+i+"l").className="b_left";
		if (eval("document.all.open"+i+"m")==object){
		eval("document.all.open"+i+"r").className="c_right";
		eval("document.all.open"+i+"m").className="c_middle";
		eval("document.all.open"+i+"l").className="c_left";
		}
	}
	
}

 function changeM(obj){
	if (typeof(obj) != "undefined") {
	 obj.className="b_middle";
	}
 }
 function changeR(obj){
	if (typeof(obj) != "undefined") {
	 obj.className="b_right";
	}
 }
 function changeL(obj){
	if (typeof(obj) != "undefined") {
	 obj.className="b_left";
	}
 }