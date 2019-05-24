Ext.state.userData = function(config){  
    Ext.state.userData.superclass.constructor.call(this);  
    try{  
        if(Ext.isIE)  
        {  
            this.storage = this.getIEUserData("eoms");  
        }else if(window.globalStorage)  
        {  
            this.storage =  window.globalStorage[window.location.hostname];  
        }  
    }catch(e){  
    }  
};  
  
Ext.extend(Ext.state.userData, Ext.state.Provider, {  
    get : function(name, defaultValue){  
        if(!this.storage)  
        {  
            return defaultValue;  
        }  
        try{  
            var value = this.storage.getItem("ys-"+name)  
            return value == "undefined" ? defaultValue : this.decodeValue(value);  
        }catch(e){  
            return defaultValue;  
        }  
    },  
    // private  
    set : function(name, value){  
        if(!this.storage)  
        {  
            return;  
        }  
        if(typeof value == "undefined" || value === null){  
            this.clear(name);  
            return;  
        }  
        try{  
            this.storage.setItem("ys-"+name, this.encodeValue(value));  
            Ext.state.userData.superclass.set.call(this, name, value);  
        }catch(e){  
        }  
    },  
  
    // private  
    clear : function(name){  
        if(!this.storage)  
        {  
            return;  
        }  
        try{  
            this.storage.removeItem(name)  
            Ext.state.userData.superclass.clear.call(this, name);  
        }catch(e){  
        }  
    },
    getIEUserData : function(id){
        var doc = document.documentElement;
        doc.addBehavior("#default#userdata");
        doc.load(id);
        return{ 
            setItem:function(key, value){  
                doc.setAttribute(key, value);  
                doc.save(id);  
            },  
            getItem:function(key){  
                return doc.getAttribute(key);  
            },  
            removeItem:function(key){  
                doc.removeAttribute(key);  
                doc.save(id);  
            },  
            deleteSelf:function(){  
                doc.expires = new Date(315532799000).toUTCString();  
                doc.save(id);  
            }            
        }
    } 
});