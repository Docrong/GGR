package com.boco.eoms.commons.statistic.base.mgr.impl;

import java.io.Serializable;

//合并汇总字段信息
public class SuarryUnit implements Serializable
{
	private static final long serialVersionUID = -8570858796498855095L;
	
	public int column = 0;
	public int beginrow = 0;
	public int endrow = 0;
	
	public SuarryUnit(int column,int beginrow,int endrow)
	{
		this.column = column;
		this.beginrow = beginrow;
		this.endrow = endrow;
	}
	
	public void  handleSuarryUnit(int n)
	{
		//1次合并多少行计算公式,例如有的数据本身就是站2行，那么合并的时候就需要*2，每次合并都是合并2行，下面是通过
		//寻找规律得出来的计算的公式。
//		公式： n*begin , 
//		      n*(end + 1) - 1
		beginrow = n * beginrow;
		endrow = n*(endrow + 1) - 1;
	}
	
	public String toString()
	{
		return "column is : " + this.column + " , " +  
				"beginrow is : " + this.beginrow + " , " + 
				"endrow is : " + this.endrow;
	}
}
