package com.boco.eoms.commons.statistic.base.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.boco.eoms.commons.statistic.base.config.excel.TableCellFont;
import com.boco.eoms.commons.statistic.base.config.excel.TableCellLink;
import com.boco.eoms.commons.statistic.base.exception.Excel2XmlException;
import com.boco.eoms.commons.statistic.base.reference.StaticMethod;

/**
 * @author lizhenyou
 *
 * 解析Excel工具类
 */
public class ExcelConverterUtil {
	
	/**
	 * 返回指定区域的 Region
	 * @param sheet
	 * @param iRow 行号
	 * @param iCol　列号
	 * @return
	 */
	public static Region getMergedRegion(HSSFSheet sheet,int iRow,int iCol){
		int mergedNum=sheet.getNumMergedRegions(); //得到所有合并的区域
	    Region mergedRegion=null;
	    Region targetRegion=null;
	    for(int i=0;i<mergedNum;i++){
	    	mergedRegion=sheet.getMergedRegionAt(i);
	    	if( iCol>=mergedRegion.getColumnFrom() && iCol<=mergedRegion.getColumnTo() &&
	    		iRow>=mergedRegion.getRowFrom() && iRow<=mergedRegion.getRowTo()  	){
	    		targetRegion=mergedRegion;
	    		break;
	    	}
	    }
	    return targetRegion;
	}
	
	/**
	 * 判断该位置是合并区域的第１个位置
	 * @param region　合并的区域
	 * @param iRow　行号
	 * @param iCol　列号
	 * @return　true: 是　false:否
	 */
	public static boolean isFirstCellInRegion(Region region,int iRow,int iCol){
		boolean b=false;
		if(region.getColumnFrom()==iCol && region.getRowFrom()==iRow){
			b=true;
		}
		return b;
	}	
	
	/**
	 * 设置xml输出的格式
	 */
	public static  XMLWriter outputXMLWriter() throws UnsupportedEncodingException
	{
		OutputFormat outFormat = OutputFormat.createPrettyPrint();
		outFormat.setEncoding("GB2312");
		outFormat.setIndent("    ");
		XMLWriter xmlwriter = new XMLWriter(outFormat);
		
		return  xmlwriter;
	}
	
	/**
	 * 获得Cell中的内容
	 * @param cell
	 * @return
	 */
	public static String getCellText(HSSFCell cell) {
		if (cell == null)
			return null;
		String sReturn = null;
		int style = cell.getCellType();
		switch (style) {
		case HSSFCell.CELL_TYPE_BOOLEAN:
			sReturn = "" + cell.getBooleanCellValue();
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			DecimalFormat df = new DecimalFormat();
			short s = cell.getCellStyle().getDataFormat();
			if (s == 0) {
				s = 1;
			}
			if (HSSFDateUtil.isCellDateFormatted(cell) || s == 176) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date dt = HSSFDateUtil.getJavaDate(cell
						.getNumericCellValue());
				sReturn = sdf.format(dt);
			} else {
				String pattern = HSSFDataFormat.getBuiltinFormat(s);
				if (pattern == null) {
					pattern = HSSFDataFormat.getBuiltinFormat((short) 1);
				}
				df.applyPattern(pattern);
				sReturn = df.format(cell.getNumericCellValue());
			}
			break;
		case HSSFCell.CELL_TYPE_STRING:
			sReturn = cell.getRichStringCellValue().getString();
			break;
		default:
			break;
		}
		
		if(sReturn == null)
		{
			sReturn = " ";
		}
		return sReturn;
	}
	
	/**
	 * 设置单元格的内容
	 * @param cell
	 * @param value
	 */
	public static void setCellText(HSSFCell cell, String value)
	{
		double doubleValue = 0;
		
		try
		{
			doubleValue = Double.parseDouble(value);
			cell.setCellValue(doubleValue);
		}
		catch(Exception e)
		{
			cell.setCellValue(new HSSFRichTextString(value));
		}
	}
	
	/**
	 * 获得Cell的水平对其方式
	 * @param cell
	 * @return
	 */
	public static String getCellAlign(HSSFCell cell) {
		if (cell == null)
			return null;
		String sAlign = null;
		switch (cell.getCellStyle().getAlignment()) {
		case HSSFCellStyle.ALIGN_CENTER:
			sAlign = "center";
			break;
		case HSSFCellStyle.ALIGN_RIGHT:
			sAlign = "right";
			break;
		case HSSFCellStyle.ALIGN_LEFT:
		default:
		}
		return sAlign;
	}
	
	/**
	 * 获得Cell的垂直对其方式
	 * @param cell
	 * @return
	 */
	public static String getCellVAlign(HSSFCell cell) {
		if (cell == null)
			return null;
		String sVAlign = null;
		switch (cell.getCellStyle().getVerticalAlignment()) {
		case HSSFCellStyle.VERTICAL_TOP:
			sVAlign = "top";
			break;
		case HSSFCellStyle.VERTICAL_BOTTOM:
			sVAlign = "bottom";
			break;
		case HSSFCellStyle.VERTICAL_CENTER:
		default:
		}
		return sVAlign;
	}
	
	/**
	 * 获得单元格字体的灰度
	 * @param cell
	 * @return 单元格字体的灰度
	 */
	public static String getCellBold(HSSFCell cell ,HSSFWorkbook wb) {
		if (cell == null)
			return null;
		HSSFCellStyle cs = cell.getCellStyle();
		HSSFFont font = wb.getFontAt(cs.getFontIndex());
		String font_weight = "font-weight:"  + String.valueOf(font.getBoldweight());

		return font_weight;
	}
	
	/**
	 * 获得单元格字体的颜色
	 * @param cell
	 * @return
	 */
	public static String getCellFontHexRGB(HSSFCell cell,HSSFWorkbook wb) {
		if (cell == null)
			return null;
		String sReturn = null;
		HSSFCellStyle cs = cell.getCellStyle();
		HSSFFont font = wb.getFontAt(cs.getFontIndex());
		int iColor = font.getColor();
		if (iColor == HSSFFont.COLOR_NORMAL) {
			sReturn = "000000";
		} else {
			HSSFPalette palette = wb.getCustomPalette();
			short[] rgb = palette.getColor(font.getColor()).getTriplet();
			sReturn = toHexRGB(rgb);
		}
		return sReturn;
	}
	
	public static String toHexRGB(short[] rgb){
		String sReturn=null;
		String sTmp=null;
		sTmp=Integer.toHexString(rgb[0]).toUpperCase();
		sReturn=(sTmp.length()<2)?"0"+sTmp:sTmp;
		sTmp=Integer.toHexString(rgb[1]).toUpperCase();
		sTmp=(sTmp.length()<2)?"0"+sTmp:sTmp;
		sReturn+=sTmp;
		sTmp=Integer.toHexString(rgb[2]).toUpperCase();
		sTmp=(sTmp.length()<2)?"0"+sTmp:sTmp;
		sReturn+=sTmp;
		return sReturn;
	}
	
	/**
	 * 获得单元格的字体
	 * @param cell
	 * @return
	 */
	public static String getCellFontName(HSSFCell cell,HSSFWorkbook wb)
	{
		if(cell == null)
		{
			return null;
		}
		HSSFCellStyle cs = cell.getCellStyle();
		HSSFFont font = wb.getFontAt(cs.getFontIndex());
		return font.getFontName();
	}
	
	/**
	 * 获得单元格的字号
	 * @return
	 */
	public static int getCellFontSize(HSSFCell cell,HSSFWorkbook wb)
	{
		if(cell == null)
		{
			return 0;
		}
		HSSFCellStyle cs = cell.getCellStyle();
		HSSFFont font = wb.getFontAt(cs.getFontIndex());
		
		return font.getFontHeightInPoints();
	}
	
	
	/**
	 * TableCellFont是否有字符串
	 * @return
	 */
	public static boolean isTableCellFontHasString(TableCellFont tableCellFont)
	{
		boolean b = false;
		if(tableCellFont.getShowText() != null)
		{
			b = true;
		}
		return b;
	}
	
	/**
	 * TableCellLink是否有字符串
	 * @return
	 */
	public static boolean isTableCellLinkHasString(TableCellLink tableCellLink)
	{
		if(tableCellLink == null)
		{
			return false;
		}
		boolean b = false;
		if(tableCellLink.getShowText() != null)
		{
			b = true;
		}
		return b;
	}
	
	
	/**
	 * 计算Cell的rowspan=?
	 * @param rsList 结果集
	 * @param str key
	 * @param rsIndex 结果集的位置
	 * @param currentString 当前显示的字符串
	 * @return
	 */
	public static  int nextSpan(List rsList,String str ,int rsIndex ,String currentString)
	{
		int rowspan = 1;
		
		for(int x = rsIndex +1  ; x < rsList.size(); x++)
		{
			try {
				String fieldData = StaticMethod.null2String((String) StatUtil
						.getExpressionResult((Map) (rsList.get(x)), str,"0"), "0");
				
				if (fieldData.equals(currentString)) {
					rowspan++;
				}else break;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return rowspan;
	}
	
	/**
	 * 判断当前的Cell是否需要合并
	 * @param rsList 结果集
	 * @param str key
	 * @param rsIndex 结果集的位置
	 * @param currentString 当前显示的字符串
	 * @return
	 */
	public static  boolean isFistCell(List rsList,String str ,int rsIndex ,String currentString,int currentTableCellAllowRows)
	{
		boolean b = true;
		
		if (rsIndex != 0) {
			try {
				String fieldData = StaticMethod.null2String((String) StatUtil
						.getExpressionResult((Map) (rsList.get(rsIndex - 1)),
								str,"0"), "0");

				if (fieldData.equals(currentString))
				{
					b = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	

		return b;
	}
	
	
	/**
	 * 获得配置信息
	 * @param configRow 配置信息行
	 * @return 配置信息列表
	 * @throws Excel2XmlException
	 */
	public static List getCongfigInfo(String xml) throws Exception
	{
		List configList = new ArrayList();
		//使用dom4j解析
		try {
			Document document = new SAXReader().read(new ByteArrayInputStream(xml.getBytes()));
			Element root = document.getRootElement();
			List rootList = root.elements();
			
			for(int i = 0; i < rootList.size(); i++)
			{
				Element config = ((Element)(rootList.get(i)));
				Map ArttMap = new HashMap();
				if(config.getName().equalsIgnoreCase("table"))
				{				
					String width = config.attributeValue(Constants.CONFIGWIDTH);
					
					String head_start = config.attributeValue(Constants.CONFIGHEAD_START);
					
					String head_end = config.attributeValue(Constants.CONFIGHEAD_END);
					
					String body_start = config.attributeValue(Constants.CONFIGBODY_START);
					
					String body_end = config.attributeValue(Constants.CONFIGBODY_END);
					
					String foot_start = config.attributeValue(Constants.CONFIGFOOT_START);
					
					String foot_end = config.attributeValue(Constants.CONFIGFOOT_END);
					
					//checkconfig信息的合法性
					checkConfigInfo();
					
					ArttMap.put("width", width);
					ArttMap.put("head_start", head_start);
					ArttMap.put("head_end", head_end);
					ArttMap.put("body_start", body_start);
					ArttMap.put("body_end", body_end);
					ArttMap.put("foot_start", foot_start);
					ArttMap.put("foot_end", foot_end);	
					configList.add(ArttMap);
				}
				
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new Exception("配置信息格式定义错误");
		}
		
		return configList;
	}
	
	/**
	 * 获得配置信息 QueryName
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static String getCongfigInfoQueryName(String xml) throws Exception
	{

		try {
			Document document = new SAXReader().read(new ByteArrayInputStream(xml.getBytes()));
			Element root = document.getRootElement();
			List rootList = root.elements();
			
			for(int i = 0; i < rootList.size(); i++)
			{
				Element config = ((Element)(rootList.get(i)));
				if(config.getName().equalsIgnoreCase("queryName"))
				{				
					String queryName = config.getText();
					return queryName;
				}
				
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new Exception("配置信息格式定义错误");
		}
		
		return "";
	}
	
	public static class SumarrayUniteConfig implements Serializable
    {
		
		private static final long serialVersionUID = -7633244439140549602L;
		
		public String[] unitecols;//合并列数组
		public int startCol = 0;//开始合并列号
    	public int uniterows = 0; //1次合并行数
    	public boolean unite = false;
    	
    	
    	public SumarrayUniteConfig(boolean unite)
    	{
    		this.unite = unite;
    	}
    	
    	public SumarrayUniteConfig(String[] unitecols,boolean unite,int startCol)
    	{
    		this.unitecols = unitecols;
    		this.unite = unite;
    		this.startCol = startCol;
    	}
    }
	
	/**
	 * 获得配置信息 Sumarrayunite
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static SumarrayUniteConfig getConfigInfoSumarrayunite(String xml) throws Exception
	{

		try {
			Document document = new SAXReader().read(new ByteArrayInputStream(xml.getBytes()));
			Element root = document.getRootElement();
			List rootList = root.elements();
			
			for(int i = 0; i < rootList.size(); i++)
			{
				Element config = ((Element)(rootList.get(i)));
				if(config.getName().equalsIgnoreCase(Constants.SUMARRAYUNITE))
				{		
					boolean suinte = Boolean.valueOf(config.getText()).booleanValue();
					SumarrayUniteConfig suc = new SumarrayUniteConfig(suinte);
					return suc;
					
//					if(suinte)
//					{
//						String[] unitecols = config.attributeValue("unitecols").split(",");
//						int startCol = Integer.parseInt(config.attributeValue("startcol"));
//						SumarrayUniteConfig suc = new SumarrayUniteConfig(unitecols,suinte,startCol); 
//						return suc;
//					}
//					else
//					{
//						SumarrayUniteConfig suc = new SumarrayUniteConfig(suinte); 
//						return suc;
//					}
					
				}
			}
		} catch (Exception e) {
			throw new Exception("合并配置信息格式定义错误,请查看excel配置文件中sumArrayUnite节点配置是否正确");
		}
		return null;
	}
	
	public static String getConfigInfoQueryfilepath(String xml) throws Exception
	{
		Document document = new SAXReader().read(new ByteArrayInputStream(xml.getBytes()));
		Element root = document.getRootElement();
		
		List rootList = root.elements();
		for(int i = 0; i < rootList.size(); i++)
		{
			Element config = ((Element)(rootList.get(i)));
			if(config.getName().equalsIgnoreCase(Constants.QUARYFILENAME))
			{				
				String queryfilename = config.getText();
				return queryfilename;
			}
			
		}
		
		return "";
	}
	
	public static int getConfigInfoShowMaxRow(String xml) throws DocumentException 
	{
		Document document = new SAXReader().read(new ByteArrayInputStream(xml.getBytes()));
		Element root = document.getRootElement();
		
		List rootList = root.elements();
		for(int i = 0; i < rootList.size(); i++)
		{
			Element config = ((Element)(rootList.get(i)));
			if(config.getName().equalsIgnoreCase(Constants.SHOWMAXROW))
			{				
				String showmaxrow = config.getText();
				return Integer.parseInt(showmaxrow);
			}
		}
		return -1;
	}
	
	/**
	 * 检查配置信息是否合法
	 */
	public static boolean checkConfigInfo()
	{
		return true;
	}
	
	/**
	 * 替换 字符串
	 * @param map
	 * @param str
	 * @return
	 */
	public static String rep(Map map,String str)
	{
		int index1 = 0;
		int len = Constants.$INFO$.length();
		while((index1 =  str.indexOf(Constants.$INFO$)) != -1)
		{
			String tmp = str.substring(index1 + len);
			int index2 = tmp.indexOf(Constants.$INFO$);
			String dateTmp = str.substring(index1 + len, index1 + len + index2);
			String value = String.valueOf(map.get(dateTmp)); 
//			Object mapObj = map.get(dateTmp);
//			String value = StaticMethod.nullObject2String(mapObj!=null?((String[])mapObj)[0]:"");
			
			str = replaceAll(str,Constants.$INFO$ + dateTmp + Constants.$INFO$ ,value);
		}
		
		return str;
	}
	
	/**
	 * 替换 字符串
	 * @param sourceStr
	 * @param matchStr
	 * @param targetStr
	 * @return
	 */
	public static String replaceAll(String sourceStr, String matchStr, String targetStr) 

	{
		StringBuffer s = new StringBuffer(sourceStr);
		int pos = -1;
		while ((pos = s.indexOf(matchStr)) != -1&&"".equals(targetStr)) 
		{
			s.replace(pos, pos + matchStr.length(), targetStr);
		}

		return s.toString(); 
	}
	
	/**
	 * 把Map中的value转换为
	 * @param map
	 * @return
	 */
	public static Map mapValueStringToType(Map map,String type)
	{
		Map reMap = new HashMap();
		
		Iterator iterator =map.keySet().iterator();
		while(iterator.hasNext())
		{
			String key = String.valueOf(iterator.next());
			String value = String.valueOf(map.get(key));
			
			try{
				if("Integer".equalsIgnoreCase(type))
				{
					int initValue = Integer.parseInt(value);
					map.put(key, new Integer(initValue));
				}
				else if("Double".equalsIgnoreCase(type))
				{
					double doubleValue = Double.parseDouble(value);
					map.put(key, new Double(doubleValue));
				}
			}
			catch(Exception e)
			{
				//e.printStackTrace();
				//logger.info("请查看$TOTAL$标签是否正确");
				//System.err.println("请查看$TOTAL$标签是否正确");
			}
		}
		return reMap;
	}
	
	 /**
     * id转name
     * 
     * @param id
     *            一般为表中的主键
     * @param beanId
     *            对应dao(表)的beanId
     * @return 返回主键对应的name(自定义)
     * @throws BusinessException
     */
//	public static String id2Name(String id, String beanId)
//	{
//		ID2NameService id2NameService = ID2NameServiceFactory.getId2nameServiceDB();
//		
//		return id2NameService.id2Name(id, beanId);
//	}
	
	/**
	 * 读取输出流中的内容转换为String
	 * @param ops
	 * @return
	 * @throws IOException 
	 */
	public static String OutputStreamToString(OutputStream ops) throws IOException
	{
		byte[] b = ((ByteArrayOutputStream)ops).toByteArray();		
		BufferedReader br = null;
		StringBuffer str =new StringBuffer();
		ByteArrayInputStream bis = null;
		InputStreamReader isr = null;
		try 
		{
			bis = new ByteArrayInputStream(b);
			isr = new InputStreamReader(bis,"UTF-8");
			br = new BufferedReader(isr);
		 
			String line = br.readLine();
			while(line != null)
			{
				str.append(line);
				line =br.readLine();
			}
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			if(bis != null)
			{
				bis.close();
			}
			
			if(isr != null)
			{
				isr.close();
			}
			
			if(br != null)
			{
				br.close();
			}
			
			if(ops != null)
			{
				ops.close();
			}
		}
		
		return str.toString();
	}
	
	/**
	 * 读取输出流中的内容转换为String
	 * @param ops
	 * @return
	 */
	public static String InputStreamToString(InputStream ops)
	{	
		BufferedReader br = null;
		StringBuffer str =new StringBuffer();
		try 
		{
			br = new BufferedReader(new InputStreamReader(ops,"UTF-8"));
		 
			String line = br.readLine();
			while(line != null)
			{
				str.append(line);
				line =br.readLine();
			}
			ops.close();
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				if(br != null)
				{
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(ops != null)
				{
					ops.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return str.toString();
	}
	
	/**
	 * 写文件
	 * @param fileStream
	 * @param filePath
	 */
	public static File writeFile(ByteArrayOutputStream fileStream,String filePath)
	{
		InputStream fis = new ByteArrayInputStream(fileStream.toByteArray());
		File file = new File(filePath);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			byte[] data = new byte[2048];
			int len = 0;
			while ((len = fis.read(data)) > 0) {

				fos.write(data, 0, len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(fis != null)
				{
					fis.close();
				}
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				if(fos != null)
				{
					fos.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				if(fileStream != null)
				{
					fileStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	
	public static File writeFile(String fileString,String filePath)
	{
		ByteArrayOutputStream hrmlOutStream = new ByteArrayOutputStream();
		try {
			hrmlOutStream.write(fileString.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				if(hrmlOutStream != null)
				{
					hrmlOutStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return writeFile(hrmlOutStream,filePath);
	}
	
}
