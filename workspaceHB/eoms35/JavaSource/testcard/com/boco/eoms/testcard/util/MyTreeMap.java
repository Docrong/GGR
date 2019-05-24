package com.boco.eoms.testcard.util;

import java.util.Map;
import java.util.TreeMap;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class MyTreeMap{

    public Map myHashMap=null;

    public MyTreeMap(){
        myHashMap=new TreeMap();
    }

    public void init(String key,String va){
        if(myHashMap.containsKey(key)){
            String temp=myHashMap.get(key).toString();
            myHashMap.remove(key);
            myHashMap.put(key,temp+",'"+va+"'");
        }
        else{
            myHashMap.put(key,"'"+va+"'");
        }
    }
}
