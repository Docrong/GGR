/**
 * 
 */
package com.boco.eoms.commons.statistic.base.config.excel;

import java.io.IOException;
import java.io.Serializable;

import com.boco.eoms.commons.statistic.base.config.graphic.GraphicConfig;
import com.boco.eoms.commons.statistic.base.util.GraphicsReportUtil;

/**
 * @author lizhenyou
 *
 * Excel中的sheet
 */
public class Sheet  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Table[] tables = null;
	
	private String sheetName = null;
	
	private int sheetIndex = 0;
	
	private String queryName;
	
	private String queryFileName;
	
	private boolean sumArrayUnite;
	
	private int showmaxrow = 0;
	
	/**
	 * 图形报表配置(xml的形式)
	 */
	private String graphicConfig;
	
	private GraphicConfig graphicConfigObj;
	
	public String getQueryFileName() {
		return queryFileName;
	}

	public void setQueryFileName(String queryFileName) {
		this.queryFileName = queryFileName;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public Table[] getTables() {
		return tables;
	}

	public void setTables(Table[] tables) {
		this.tables = tables;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public int getSheetIndex() {
		return sheetIndex;
	}

	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	public String getGraphicConfig() {
		return graphicConfig;
	}
	
	public GraphicConfig getGraphicConfigObj() {
		
		GraphicConfig graphicConfig = null;
		if(graphicConfigObj == null)
		{
			try {
				if(getGraphicConfig() != null && !getGraphicConfig().equalsIgnoreCase(""))
				{
					graphicConfig = (GraphicConfig)GraphicsReportUtil.xml2object(GraphicConfig.class, getGraphicConfig());
				}
				
				return graphicConfig;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		else
		{
			return graphicConfigObj;
		}
		
	}
	
	public void setGraphicConfigObj(GraphicConfig graphicConfig)
	{
		this.graphicConfigObj = graphicConfig;
	}
	
	public void setGraphicConfig(String graphicConfig) {
		this.graphicConfig = graphicConfig; 
	}

	public boolean isSumArrayUnite() {
		return sumArrayUnite;
	}

	public void setSumArrayUnite(boolean sumArrayUnite) {
		this.sumArrayUnite = sumArrayUnite;
	}

	public int getShowmaxrow() {
		return showmaxrow;
	}

	public void setShowmaxrow(int showmaxrow) {
		this.showmaxrow = showmaxrow;
	}
}
