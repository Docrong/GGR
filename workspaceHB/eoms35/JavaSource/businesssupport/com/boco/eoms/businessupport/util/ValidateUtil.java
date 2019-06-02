package com.boco.eoms.businessupport.util;

import java.io.File;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.boco.eoms.base.util.StaticMethod;

public class ValidateUtil {
	private String filePath = "";
	private Element root = null;
	private static ValidateUtil _validateUtil= null;
	
	public static ValidateUtil getInstance(){
		try{
			if(_validateUtil==null){
				_validateUtil = new ValidateUtil();
				_validateUtil.init();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return _validateUtil;
	}
		
	private void init() throws Exception{
		filePath = StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/businessupport/config/validate-util.xml");
		SAXBuilder dc=new SAXBuilder();
		Document doc = dc.build(new File(filePath));
		
		root = doc.getRootElement();
	}
	
	/**
	 * 通过taskName查找字段名称
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public String[] getColumns(String nodePath,String taskName){

		Element element = root.getChild(nodePath);
		if(element==null){
			System.out.println("dict."+nodePath+" not find");
			return null;
		}
		
		List list = element.getChildren();
		for(int i=0;i<list.size();i++){
			Element node = (Element)list.get(i);
			String code = node.getAttribute("taskName").getValue();
			String columns = node.getAttribute("column").getValue();
			if(code.equals(taskName)&&!columns.equals("")){
				return columns.split(",");
			}
		}
		return null;
	}
	
	public static void main(String[] avg){
		String[] str = ValidateUtil.getInstance().getColumns("validateDict", "UserTask");
		System.out.println(str[0]);
	}
}
