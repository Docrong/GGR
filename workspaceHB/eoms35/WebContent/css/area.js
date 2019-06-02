function Dsy() {
	this.Items = {};
	}
Dsy.prototype.add = function(id,iArray) {
	this.Items[id] = iArray;
	}
Dsy.prototype.Exists = function(id) {
	if(typeof(this.Items[id]) == "undefined")
	return false;
        return true;
	};
function change(v){
	var str="0";
	for(i=0;i<v;i++){
		str+=("_"+(document.getElementById(s[i]).selectedIndex-1));
		};
	var ss=document.getElementById(s[v]);
	with(ss){
		length = 0;
		options[0]=new Option(opt0[v],opt0[v]);
		if(v && document.getElementById(s[v-1]).selectedIndex>0 || !v)
		{
			if(dsy.Exists(str)){
				ar = dsy.Items[str];
				for(i=0;i<ar.length;i++)options[length]=new Option(ar[i],ar[i]);
				if(v)options[1].selected = true;
			}
		}
		if(++v<s.length){
                change(v);
		}
	}
}
function change(dic,v,arr,arr1){
	//alert(v);
	var str="0";
	for(i=0;i<v;i++){
		str+=("_"+(document.getElementById(arr[i]).selectedIndex-1));
	}
	
	var ss=document.getElementById(arr[v]);
	with(ss){
		length = 0;
		options[0]=new Option(arr1[v],arr1[v]);
		if(v && document.getElementById(arr[v-1]).selectedIndex>0 || !v)
		{
			if(dic.Exists(str)){
				ar = dic.Items[str];
				for(i=0;i<ar.length;i++)options[length]=new Option(ar[i],ar[i]);
				if(v)options[1].selected = true;
			}
		}
		if(++v<s.length){
                change(dic,v,arr,arr1);
		}
	}
}
