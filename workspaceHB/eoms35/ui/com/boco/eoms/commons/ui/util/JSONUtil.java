/**
 * 
 */
package com.boco.eoms.commons.ui.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

public class JSONUtil {
	/**
	 * 设定输出内容的类型
	 *下为JSON的标准MIME类型，但不利于调试
	 *"application/json;charset=UTF-8"
	 */
	protected static String cTypeString = "text/xml;charset=UTF-8";
	
	/**
	 * 使用jdom输出xml
	 * @param el
	 */
	public static String getStrElement(Element el) {
		
  		XMLOutputter xo = new XMLOutputter();
  		return xo.outputString(el);
  	}
	/**
	 * 输出JSON格式字符串
	 * @param response
	 * @param s 要输出的字符串
	 * @throws IOException
	 */
	public static void print(HttpServletResponse response, String s) 
		throws IOException {
		
		response.setContentType(JSONUtil.cTypeString);	
		response.getWriter().print(s);
  	}
	
	/**
	 * 将list中的全部字段转换为JSON对象后返回
	 * @param list
	 */
	public static String list2JSON(List list) 
		throws IOException {
		
		JSONObject jsonRoot = new JSONObject();
		jsonRoot.put("results", list.size());
		jsonRoot.put("rows",JSONArray.fromObject(list));
		return jsonRoot.toString();
  	}
	
	/**
	 * 将list中指定字段除去后再转换为JSON对象
	 * @param list
	 * @param exclude 字符串数组对象，如 {"id","userid"} 转换后将不包含这些字段
	 */
	public static String list2JSON(List list,String[] exclude) 
		throws IOException {
		
		JSONObject jsonRoot = new JSONObject();
		jsonRoot.put("results", list.size());
		jsonRoot.put("rows",JSONArray.fromObject(list, exclude));
		return jsonRoot.toString();
  	}
	
	
	/**
	 * 直接返回JSON给页面
	 * @param list
	 * @param response
	 */
	public static void returnJSON(List list,HttpServletResponse response) 
		throws IOException {

		JSONUtil.print(response,list2JSON(list));
  	}
	
	/**
	 * 返回除去指定字段后的JSON给页面
	 * @param list
	 * @param response
	 * @param exclude 字符串数组对象，如 {"id","userid"} 转换后将不包含这些字段
	 */
	public static void returnJSON(List list,HttpServletResponse response, String[] exclude) 
		throws IOException {
		
		JSONUtil.print(response,list2JSON(list, exclude));
  	}
	
	private static String createJSONMsg(Boolean success, String message) 
	throws IOException {
	
		JSONObject jsonRoot = new JSONObject();
		jsonRoot.put("success", success);
		jsonRoot.put("message", message);
	
		return jsonRoot.toString();
	}	
	
	/**
	 * 返回成功信息
	 * @param HttpServletResponse response
	 * @param String message 成功信息
	 */
	public static void success(HttpServletResponse response, String message) 
		throws IOException {
		
		String json = JSONUtil.createJSONMsg(new Boolean (true),message);
		JSONUtil.print(response,json);
	}
	
	/**
	 * 返回失败信息
	 * @param HttpServletResponse response
	 * @param String message 失败信息
	 */
	public static void fail(HttpServletResponse response, String message) 
		throws IOException {

		String json = JSONUtil.createJSONMsg(new Boolean (false),message);
		JSONUtil.print(response,json);
	}	
}
