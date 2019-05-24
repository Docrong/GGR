/**
 * 
 */
package com.boco.eoms.commons.statistic.base.config.excel;

import java.io.Serializable;
import java.util.List;

import com.boco.eoms.commons.statistic.base.mgr.impl.SuarryUnit;
import com.boco.eoms.commons.statistic.base.util.ExcelConverterUtil.SumarrayUniteConfig;

/**
 * @author lizhenyou
 *
 */
public abstract class TableContent  implements Serializable {
	
	private TableRow[] tableRows = null;
	
	/**
	 * 是否合并汇总字段
	 */
	private SumarrayUniteConfig sumArrayUnite;
	
	private int showmaxrow = 0;

	public int getShowmaxrow() {
		return showmaxrow;
	}

	public void setShowmaxrow(int showmaxrow) {
		this.showmaxrow = showmaxrow;
	}

	public SumarrayUniteConfig getSumArrayUnite() {
		return sumArrayUnite;
	}

	public void setSumArrayUnite(SumarrayUniteConfig sumArrayUnite) {
		this.sumArrayUnite = sumArrayUnite;
	}

	public TableRow[] getTableRows() {
		return tableRows;
	}

	public void setTableRows(TableRow[] tableRows) {
		this.tableRows = tableRows;
	}
	
	public void setTableRows(List tableRowslist)
	{
		TableRow[] tmp = new TableRow[tableRowslist.size()];
		for(int f=0;f<tableRowslist.size();f++)
		{
			tmp[f] = (TableRow)tableRowslist.get(f);
		}
		
		this.tableRows = tmp;
	}
	
	//处理合并单元格
	public void unitCell(SuarryUnit su,int startCol,int n)
	{
		int column = su.column + startCol - 1;
		int beginrow = su.beginrow;
		int endrow = su.endrow;
		
		for(int i = beginrow;i<=endrow;i=i+n)
		{
			TableRow tableRow = tableRows[i];
			if(i == beginrow)
			{
				tableRow.getTableCells()[column].setRowSpan(String.valueOf(endrow-beginrow+1));
			}
			else
			{
				tableRow.getTableCells()[column] = null;
			}
		}
	}
}
