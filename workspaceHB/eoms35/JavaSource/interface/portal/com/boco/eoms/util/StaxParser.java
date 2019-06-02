package com.boco.eoms.util;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import com.boco.eoms.commons.loging.BocoLog;

public class StaxParser {
	
	private static XMLInputFactory factory = null;
	private static StaxParser _staxParser = null;
	
	public static StaxParser getInstance(){
		if(_staxParser==null){
			factory = XMLInputFactory.newInstance();
			_staxParser = new StaxParser();
		}
		return _staxParser;
	}

	public Map getOpdetail(String opdetail) throws Exception {		
		
		Map map = new HashMap();
		
//		factory.setProperty(XMLInputFactory.IS_COALESCING, Boolean.TRUE);
		
			XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(opdetail));
			String fieldEnName = "";
			while (reader.hasNext())
            {
                switch (reader.getEventType())
                {
                case XMLStreamReader.START_ELEMENT:
                    String name = reader.getLocalName();
                    if("fieldInfo".equals(name)){
                    	System.out.println("fieldInfo:");
                    }
                    else if("fieldChName".equals(name)){
                    	String desc = reader.getElementText();
                    	System.out.println("fieldChName:"+desc);
                    } else if("fieldEnName".equals(name)){
                    	fieldEnName = reader.getElementText();                    	
                    	System.out.println("fieldEnName:"+fieldEnName);
                    } else if("fieldContent".equals(name)){
                    	String desc = reader.getElementText();
                    	System.out.println("fieldContent:"+desc);
                    	if(fieldEnName!=null && fieldEnName.length()>0)
                    		map.put(fieldEnName, desc);
                    }
                    break;
                }
                reader.next();//获取下一个解析事件
            }
			return map;
	}
	public List getOpdetailList(String opdetail,Map sheetMap) throws Exception {		

//		factory.setProperty(XMLInputFactory.IS_COALESCING, Boolean.TRUE);
		
			BocoLog.info(this, "start extra opdetail");
		
			XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(opdetail));
			String fieldEnName = "";
			List list = new ArrayList();
			Map map = new HashMap();
			
			while (reader.hasNext())
            {
                switch (reader.getEventType())
                {
                case XMLStreamReader.START_ELEMENT:
                    String name = reader.getLocalName();
                    if("recordInfo".equals(name)){
                    	if(map!=null && !map.isEmpty()){
                    		Map tmap = new HashMap();
                    		tmap.putAll(sheetMap);
                    		tmap.putAll(map);                    		
                    		list.add(tmap);
                    		map = new HashMap();
                    	}                    	
                    }
                    else if("fieldChName".equals(name)){
//                    	String desc = reader.getElementText();
//                    	BocoLog.info(this, "fieldChName:"+desc);
                    } else if("fieldEnName".equals(name)){
                    	fieldEnName = reader.getElementText();                    	
//                    	BocoLog.info(this, "fieldEnName:"+fieldEnName);
                    } else if("fieldContent".equals(name)){
                    	String desc = reader.getElementText();
//                    	BocoLog.info(this, "fieldContent:"+desc);
                    	if(fieldEnName!=null && fieldEnName.length()>0)
                    		map.put(fieldEnName, desc);
                    }
                    break;
                }
                reader.next();//获取下一个解析事件
            }
			if(map!=null && !map.isEmpty()){
        		Map tmap = new HashMap();
        		tmap.putAll(sheetMap);
        		tmap.putAll(map);        		
        		list.add(tmap);
			}
			
			BocoLog.info(this, "end extra opdetail");
			return list;
	}
	
	
	/**
	 * by lyg
	 * @param opdetail
	 * @return
	 * @throws Exception
	 */
	public List getOpdetailList(String opdetail) throws Exception {
		List list = new ArrayList();
		return list;
	}
	
	public HashMap getOpdetail4HashMap(String opdetail) throws Exception {
		HashMap map = new HashMap();
		return map;
	}
	
	
}
