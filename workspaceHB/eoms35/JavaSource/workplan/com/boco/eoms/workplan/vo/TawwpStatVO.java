package com.boco.eoms.workplan.vo;

/**
 * <p>Title: 月度作业计划统计数据显示类</p>
 * <p>Description: 提供页面的所需的数据封装</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 * @author eoms
 * @version 1.0
 */

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import com.boco.eoms.common.util.StaticMethod;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.workplan.model.TawwpMonthPlan;
import com.boco.eoms.workplan.model.TawwpNet;

public class TawwpStatVO {
	private TawSystemDept tawDept;

	private TawSystemUser tawSystemUser;

	private TawwpMonthPlan tawWpMonthPlan;

	private TawwpNet tawwpNet;

	private TawSystemCptroom tawSystemCptroom;

	private String cycle;

	private String cycleName;
	
	private String yearFlag;
	
	private String monthFlag;

	// 天
	private int dayCount;

	private int dayInTimeCount;

	private int dayOutTimeCount;
	
	private int dayNormalCount;

	// 周
	private int weekCount;

	private int weekInTimeCount;

	private int weekOutTimeCount;
	
	private int weekNormalCount;

	// 半月
	private int halfmonthCount;

	private int halfmonthInTimeCount;

	private int halfmonthOutTimeCount;
	
	private int halfmonthNormalCount;

	// 月
	private int monthCount;

	private int monthInTimeCount;

	private int monthOutTimeCount;
	
	private int monthNormalCount;

	// 两月
	private int towmonthCount;

	private int towmonthInTimeCount;

	private int towmonthOutTimeCount;
	
	private int towmonthNormalCount;

	// 季度
	private int quarterCount;

	private int quarterInTimeCount;

	private int quarterOutTimeCount;
	
	private int quarterNormalCount;

	// 四月
	private int fourmonthCount;

	private int fourmonthInTimeCount;

	private int fourmonthOutTimeCount;
	
	private int fourmonthNormalCount;

	// 半年
	private int halfyearCount;

	private int halfyearInTimeCount;

	private int halfyearOutTimeCount;
	
	private int halfyearNormalCount;

	// 年
	private int yearCount;

	private int yearInTimeCount;

	private int yearOutTimeCount;

	private int yearNormalCount;
	// 其他
	private int tempCount;
	
	private int tempNormalCount;

	private int tempInTimeCount;

	private int tempOutTimeCount;

	private int allCount;
	
	private int allNormalCount; // 所有正常数

	private int allinTimeCount;

	private int alloutTimeCount;

	private double inTimeRate;

	private double outTimeRate;

	private int constituteCount;
	
	private String result;
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public TawSystemDept getTawDept() {
		return tawDept;
	}

	public void setTawDept(TawSystemDept tawDept) {
		this.tawDept = tawDept;
	}

	public int getAllCount() {
		return allCount;
	}

	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}

	public int getAllinTimeCount() {
		return allinTimeCount;
	}

	public void setAllinTimeCount(int allinTimeCount) {
		this.allinTimeCount = allinTimeCount;
	}

	public int getAlloutTimeCount() {
		return alloutTimeCount;
	}

	public void setAlloutTimeCount(int alloutTimeCount) {
		this.alloutTimeCount = alloutTimeCount;
	}

	public int getConstituteCount() {
		return constituteCount;
	}

	public void setConstituteCount(int constituteCount) {
		this.constituteCount = constituteCount;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getCycleName() {
		return cycleName;
	}

	public void setCycleName(String cycleName) {
		this.cycleName = cycleName;
	}

	public int getDayCount() {
		return dayCount;
	}

	public void setDayCount(int dayCount) {
		this.dayCount = dayCount;
	}

	public int getDayInTimeCount() {
		return dayInTimeCount;
	}

	public void setDayInTimeCount(int dayInTimeCount) {
		this.dayInTimeCount = dayInTimeCount;
	}

	public int getDayOutTimeCount() {
		return dayOutTimeCount;
	}

	public void setDayOutTimeCount(int dayOutTimeCount) {
		this.dayOutTimeCount = dayOutTimeCount;
	}

	public int getFourmonthCount() {
		return fourmonthCount;
	}

	public void setFourmonthCount(int fourmonthCount) {
		this.fourmonthCount = fourmonthCount;
	}

	public int getFourmonthInTimeCount() {
		return fourmonthInTimeCount;
	}

	public void setFourmonthInTimeCount(int fourmonthInTimeCount) {
		this.fourmonthInTimeCount = fourmonthInTimeCount;
	}

	public int getFourmonthOutTimeCount() {
		return fourmonthOutTimeCount;
	}

	public void setFourmonthOutTimeCount(int fourmonthOutTimeCount) {
		this.fourmonthOutTimeCount = fourmonthOutTimeCount;
	}

	public int getHalfmonthCount() {
		return halfmonthCount;
	}

	public void setHalfmonthCount(int halfmonthCount) {
		this.halfmonthCount = halfmonthCount;
	}

	public int getHalfmonthInTimeCount() {
		return halfmonthInTimeCount;
	}

	public void setHalfmonthInTimeCount(int halfmonthInTimeCount) {
		this.halfmonthInTimeCount = halfmonthInTimeCount;
	}

	public int getHalfmonthOutTimeCount() {
		return halfmonthOutTimeCount;
	}

	public void setHalfmonthOutTimeCount(int halfmonthOutTimeCount) {
		this.halfmonthOutTimeCount = halfmonthOutTimeCount;
	}

	public int getHalfyearCount() {
		return halfyearCount;
	}

	public void setHalfyearCount(int halfyearCount) {
		this.halfyearCount = halfyearCount;
	}

	public int getHalfyearInTimeCount() {
		return halfyearInTimeCount;
	}

	public void setHalfyearInTimeCount(int halfyearInTimeCount) {
		this.halfyearInTimeCount = halfyearInTimeCount;
	}

	public int getHalfyearOutTimeCount() {
		return halfyearOutTimeCount;
	}

	public void setHalfyearOutTimeCount(int halfyearOutTimeCount) {
		this.halfyearOutTimeCount = halfyearOutTimeCount;
	}

	public double getInTimeRate() {
		return inTimeRate;
	}

	public void setInTimeRate(double inTimeRate) {
		this.inTimeRate = inTimeRate;
	}

	public int getMonthCount() {
		return monthCount;
	}

	public void setMonthCount(int monthCount) {
		this.monthCount = monthCount;
	}

	public int getMonthInTimeCount() {
		return monthInTimeCount;
	}

	public void setMonthInTimeCount(int monthInTimeCount) {
		this.monthInTimeCount = monthInTimeCount;
	}

	public int getMonthOutTimeCount() {
		return monthOutTimeCount;
	}

	public void setMonthOutTimeCount(int monthOutTimeCount) {
		this.monthOutTimeCount = monthOutTimeCount;
	}

	public double getOutTimeRate() {
		return outTimeRate;
	}

	public void setOutTimeRate(double outTimeRate) {
		this.outTimeRate = outTimeRate;
	}

	public int getQuarterCount() {
		return quarterCount;
	}

	public void setQuarterCount(int quarterCount) {
		this.quarterCount = quarterCount;
	}

	public int getQuarterInTimeCount() {
		return quarterInTimeCount;
	}

	public void setQuarterInTimeCount(int quarterInTimeCount) {
		this.quarterInTimeCount = quarterInTimeCount;
	}

	/**
   * 生成当前日志对象的xml字符串
   * @param url String 连接路径
   * @return String xml字符串
   */
	
  public String writeHTML(String url) {
		StringBuffer str = null;
		str = new StringBuffer();
		str.append("<a href='" + url + "'>");
		str.append("<tr class='tr_show'>");
		str.append("<td nowrap>" + this.getTawDept().getDeptName() + "</td>");
		str.append("<td >" + this.getDayCount() + "</td>");
		str.append("<td >" + this.getDayInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getDayInTimeCount() + this.getDayOutTimeCount())
				+ "</td>");
		str.append("<td >" + this.getWeekCount() + "</td>");
		str.append("<td >" + this.getWeekInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getWeekInTimeCount() + this.getWeekOutTimeCount())
				+ "</td>");
		str.append("<td >" + this.getHalfmonthCount() + "</td>");
		str.append("<td >" + this.getHalfmonthInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getHalfmonthInTimeCount() + this
						.getHalfmonthOutTimeCount()) + "</td>");
		str.append("<td >" + this.getMonthCount() + "</td>");
		str.append("<td >" + this.getMonthInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getMonthInTimeCount() + this.getMonthOutTimeCount())
				+ "</td>");
		str.append("<td >" + this.getQuarterCount() + "</td>");
		str.append("<td >" + this.getQuarterInTimeCount() + "</td>");
		str
				.append("<td >"
						+ (this.getQuarterInTimeCount() + this
								.getQuarterOutTimeCount()) + "</td>");
		str.append("<td >" + this.getHalfyearCount() + "</td>");
		str.append("<td >" + this.getHalfyearInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getHalfyearInTimeCount() + this
						.getHalfyearOutTimeCount()) + "</td>");
		str.append("<td >" + this.getYearCount() + "</td>");
		str.append("<td >" + this.getYearInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getYearInTimeCount() + this.getYearOutTimeCount())
				+ "</td>");
		str.append("<td >" + this.getYearCount() + "</td>");
		str.append("<td >" + this.getTempCount() + "</td>");
		str.append("<td >"
				+ (this.getTempInTimeCount() + this.getTempOutTimeCount())
				+ "</td>");
		str.append("<td >" + this.getAllCount() + "</td>");
		str.append("<td >" + this.getAllinTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getAllinTimeCount() + this.getAlloutTimeCount())
				+ "</td>");
		if (this.getAllCount() > 0) {
			str.append("<td >"
					+ ((((double) this.getAllinTimeCount() / (double) this
							.getAllCount()) * 100) + "00000").subSequence(0, 5)
					+ "%</td>");
			str.append("<td >"
					+ (((((double) this.getAllinTimeCount() + this
							.getAlloutTimeCount()) / (double) this
							.getAllCount()) * 100) + "00000").substring(0, 5)
					+ "%</td>");
		} else {
			str.append("<td >0%</td>");
			str.append("<td >0%</td>");
		}
		str.append("<td >100%</td>");
		String results=this.countPoints(((((double) this.getAllinTimeCount() + this
				.getAlloutTimeCount()) / (double) this
				.getAllCount()) * 100),getAllCount());
		str.append("<td >"+results+"</td>");
		str.append("</tr></a>");

		return str.toString();
	}
  public String writeStatHTML(String yearFlag,String monthFlag, String deptId) {
		StringBuffer str = null;
		str = new StringBuffer();
		String url = "../tawwpstat/statyearall.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId;
		str.append("<tr class='tr_show'>");
		str.append("<td nowrap><a href='" + url + "'>" + this.getTawDept().getDeptName() + "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=1";
		str.append("<td ><a href='" + url + "'>" + this.getDayCount() + "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=1&executeFlag=1";
		str.append("<td ><a href='" + url + "'>" + this.getDayInTimeCount() + "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=1&executeFlag=0";
		str.append("<td ><a href='" + url + "'>"
				+ (this.getDayInTimeCount() + this.getDayOutTimeCount())
				+ "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=2";
		str.append("<td ><a href='" + url + "'>" + this.getWeekCount() + "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=2&executeFlag=1";
		str.append("<td ><a href='" + url + "'>" + this.getWeekInTimeCount() + "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=2&executeFlag=0";
		str.append("<td ><a href='" + url + "'>"
				+ (this.getWeekInTimeCount() + this.getWeekOutTimeCount())
				+ "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=3";
		str.append("<td ><a href='" + url + "'>" + this.getHalfmonthCount() + "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=3&executeFlag=1";
		str.append("<td ><a href='" + url + "'>" + this.getHalfmonthInTimeCount() + "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=3&executeFlag=0";
		str.append("<td ><a href='" + url + "'>"
				+ (this.getHalfmonthInTimeCount() + this
						.getHalfmonthOutTimeCount()) + "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=4";
		str.append("<td ><a href='" + url + "'>" + this.getMonthCount() + "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=4&executeFlag=1";
		str.append("<td ><a href='" + url + "'>" + this.getMonthInTimeCount() + "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=4&executeFlag=0";
		str.append("<td ><a href='" + url + "'>"
				+ (this.getMonthInTimeCount() + this.getMonthOutTimeCount())
				+ "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=5";
		str.append("<td ><a href='" + url + "'>"+ this.getQuarterCount() + "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=5&executeFlag=1";
		str.append("<td ><a href='" + url + "'>" + this.getQuarterInTimeCount() + "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=5&executeFlag=0";
		str
				.append("<td ><a href='" + url + "'>"
						+ (this.getQuarterInTimeCount() + this
								.getQuarterOutTimeCount()) + "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=6";
		str.append("<td ><a href='" + url + "'>" + this.getHalfyearCount() + "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=6&executeFlag=1";
		str.append("<td ><a href='" + url + "'>" + this.getHalfyearInTimeCount() + "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=6&executeFlag=0";
		str.append("<td ><a href='" + url + "'>"
				+ (this.getHalfyearInTimeCount() + this
						.getHalfyearOutTimeCount()) + "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=7";
		str.append("<td ><a href='" + url + "'>" + this.getYearCount() + "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=7&executeFlag=1";
		str.append("<td ><a href='" + url + "'>" + this.getYearInTimeCount() + "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=7&executeFlag=1";
		str.append("<td ><a href='" + url + "'>"
				+ (this.getYearInTimeCount() + this.getYearOutTimeCount())
				+ "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=0";
		str.append("<td ><a href='" + url + "'>" + this.getTempCount() + "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=0&executeFlag=1";
		str.append("<td ><a href='" + url + "'>" + this.getTempInTimeCount() + "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=0&executeFlag=0";
		str.append("<td ><a href='" + url + "'>"
				+ (this.getTempInTimeCount() + this.getTempOutTimeCount())
				+ "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId;
		str.append("<td ><a href='" + url + "'>" + this.getAllCount() + "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&executeFlag=1";
		str.append("<td ><a href='" + url + "'>" + this.getAllinTimeCount() + "</a></td>");
		url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
		+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&executeFlag=0";
		str.append("<td ><a href='" + url + "'>"
				+ (this.getAllinTimeCount() + this.getAlloutTimeCount())
				+ "</a></td>");
		if (this.getAllCount() > 0) {
			str.append("<td >"
					+ ((((double) this.getAllinTimeCount() / (double) this
							.getAllCount()) * 100) + "00000").subSequence(0, 5)
					+ "%</td>");
			str.append("<td >"
					+ (((((double) this.getAllinTimeCount() + this
							.getAlloutTimeCount()) / (double) this
							.getAllCount()) * 100) + "00000").substring(0, 5)
					+ "%</td>");
		} else {
			str.append("<td >0%</td>");
			
			str.append("<td >0%</td>");
		}
		str.append("<td >100%</td>");
		String results=this.countPoints(((((double) this.getAllinTimeCount() + this
				.getAlloutTimeCount()) / (double) this
				.getAllCount()) * 100),getAllCount());
		str.append("<td >"+results+"</td>");
		
		str.append("</tr>");

		return str.toString();
	}
	public void setTowmonthCount(int towmonthCount) {
		this.towmonthCount = towmonthCount;
	}

	public int getTowmonthInTimeCount() {
		return towmonthInTimeCount;
	}

	public void setTowmonthInTimeCount(int towmonthInTimeCount) {
		this.towmonthInTimeCount = towmonthInTimeCount;
	}

	public int getTowmonthOutTimeCount() {
		return towmonthOutTimeCount;
	}

	public void setTowmonthOutTimeCount(int towmonthOutTimeCount) {
		this.towmonthOutTimeCount = towmonthOutTimeCount;
	}

	public int getWeekCount() {
		return weekCount;
	}

	public void setWeekCount(int weekCount) {
		this.weekCount = weekCount;
	}

	public int getWeekInTimeCount() {
		return weekInTimeCount;
	}

	public void setWeekInTimeCount(int weekInTimeCount) {
		this.weekInTimeCount = weekInTimeCount;
	}

	public int getWeekOutTimeCount() {
		return weekOutTimeCount;
	}

	public void setWeekOutTimeCount(int weekOutTimeCount) {
		this.weekOutTimeCount = weekOutTimeCount;
	}

	public int getTempCount() {
		return tempCount;
	}

	public void setTempCount(int tempCount) {
		this.tempCount = tempCount;
	}

	public int getTempInTimeCount() {
		return tempInTimeCount;
	}

	public void setTempInTimeCount(int tempInTimeCount) {
		this.tempInTimeCount = tempInTimeCount;
	}

	public int getTempOutTimeCount() {
		return tempOutTimeCount;
	}

	public void setTempOutTimeCount(int tempOutTimeCount) {
		this.tempOutTimeCount = tempOutTimeCount;
	}

	public int getYearCount() {
		return yearCount;
	}

	public void setYearCount(int yearCount) {
		this.yearCount = yearCount;
	}

	public int getYearInTimeCount() {
		return yearInTimeCount;
	}

	public void setYearInTimeCount(int yearInTimeCount) {
		this.yearInTimeCount = yearInTimeCount;
	}

	public int getYearOutTimeCount() {
		return yearOutTimeCount;
	}

	public void setYearOutTimeCount(int yearOutTimeCount) {
		this.yearOutTimeCount = yearOutTimeCount;
	}

	public void putCycleCount(String _cycle, int _count, int _state) {

		if (_cycle.equals("1")) {
			// 周期为天
			if (_state == 0) { // 天总数
				this.setDayCount(_count);
				this.setAllCount(this.getAllCount() + _count);
			} else if (_state == 1) { // 天按时数
				this.setDayInTimeCount(_count);
				this.setAllinTimeCount(this.getAllinTimeCount() + _count);
			} else if (_state == 2) { // 天超时数
				this.setDayOutTimeCount(_count);
				this.setAlloutTimeCount(this.getAlloutTimeCount() + _count);
			}
		} else if (_cycle.equals("2")) {
			// 周期为周
			if (_state == 0) { // 周总数
				this.setWeekCount(_count);
				this.setAllCount(this.getAllCount() + _count);
			} else if (_state == 1) { // 周按时数
				this.setWeekInTimeCount(_count);
				this.setAllinTimeCount(this.getAllinTimeCount() + _count);
			} else if (_state == 2) { // 周超时数
				this.setWeekOutTimeCount(_count);
				this.setAlloutTimeCount(this.getAlloutTimeCount() + _count);
			}
		} else if (_cycle.equals("3")) {
			// 周期为半月
			if (_state == 0) { // 半月总数
				this.setHalfmonthCount(_count);
				this.setAllCount(this.getAllCount() + _count);
			} else if (_state == 1) { // 半月按时数
				this.setHalfmonthInTimeCount(_count);
				this.setAllinTimeCount(this.getAllinTimeCount() + _count);
			} else if (_state == 2) { // 半月超时数
				this.setHalfmonthOutTimeCount(_count);
				this.setAlloutTimeCount(this.getAlloutTimeCount() + _count);
			}
		} else if (_cycle.equals("4")) {
			// 周期为月
			if (_state == 0) { // 月总数
				this.setMonthCount(_count);
				this.setAllCount(this.getAllCount() + _count);
			} else if (_state == 1) { // 月按时数
				this.setMonthInTimeCount(_count);
				this.setAllinTimeCount(this.getAllinTimeCount() + _count);
			} else if (_state == 2) { // 月超时数
				this.setMonthOutTimeCount(_count);
				this.setAlloutTimeCount(this.getAlloutTimeCount() + _count);
			}
		} else if (_cycle.equals("5")) {
			// 周期为季度
			if (_state == 0) { // 季度总数
				this.setQuarterCount(_count);
				this.setAllCount(this.getAllCount() + _count);
			} else if (_state == 1) { // 季度按时数
				this.setQuarterInTimeCount(_count);
				this.setAllinTimeCount(this.getAllinTimeCount() + _count);
			} else if (_state == 2) { // 季度超时数
				this.setQuarterOutTimeCount(_count);
				this.setAlloutTimeCount(this.getAlloutTimeCount() + _count);
			}
		} else if (_cycle.equals("6")) {
			// 周期为半年
			if (_state == 0) { // 半年总数
				this.setHalfyearCount(_count);
				this.setAllCount(this.getAllCount() + _count);
			} else if (_state == 1) { // 半年按时数
				this.setHalfyearInTimeCount(_count);
				this.setAllinTimeCount(this.getAllinTimeCount() + _count);
			} else if (_state == 2) { // 半年超时数
				this.setHalfyearOutTimeCount(_count);
				this.setAlloutTimeCount(this.getAlloutTimeCount() + _count);
			}
		} else if (_cycle.equals("7")) {
			// 周期为年
			if (_state == 0) { // 年总数
				this.setYearCount(_count);
				this.setAllCount(this.getAllCount() + _count);
			} else if (_state == 1) { // 年按时数
				this.setYearInTimeCount(_count);
				this.setAllinTimeCount(this.getAllinTimeCount() + _count);
			} else if (_state == 2) { // 年超时数
				this.setYearOutTimeCount(_count);
				this.setAlloutTimeCount(this.getAlloutTimeCount() + _count);
			}
		} else if (_cycle.equals("8")) {
			// 周期为两月
			if (_state == 0) { // 两月总数
				this.setTowmonthCount(_count);
				this.setAllCount(this.getAllCount() + _count);
			} else if (_state == 1) { // 两月按时数
				this.setTowmonthInTimeCount(_count);
				this.setAllinTimeCount(this.getAllinTimeCount() + _count);
			} else if (_state == 2) { // 两月超时数
				this.setTowmonthOutTimeCount(_count);
				this.setAlloutTimeCount(this.getAlloutTimeCount() + _count);
			}
		} else if (_cycle.equals("9")) {
			// 周期为四月
			if (_state == 0) { // 四月总数
				this.setFourmonthCount(_count);
				this.setAllCount(this.getAllCount() + _count);
			} else if (_state == 1) { // 四月按时数
				this.setFourmonthInTimeCount(_count);
				this.setAllinTimeCount(this.getAllinTimeCount() + _count);
			} else if (_state == 2) { // 四月超时数
				this.setFourmonthOutTimeCount(_count);
				this.setAlloutTimeCount(this.getAlloutTimeCount() + _count);
			}
		} else if (_cycle.equals("0")) {
			// 周期为其他
			if (_state == 0) { // 其他总数
				this.setTempCount(_count);
				this.setAllCount(this.getAllCount() + _count);
			} else if (_state == 1) { // 其他按时数
				this.setTempInTimeCount(_count);
				this.setAllinTimeCount(this.getAllinTimeCount() + _count);
			} else if (_state == 2) { // 其他超时数
				this.setTempOutTimeCount(_count);
				this.setAlloutTimeCount(this.getAlloutTimeCount() + _count);
			}
		}
	}
	
	
	/**
	 * @param _cycle
	 * @param _count
	 * @param _state
	 * @param _normalFlag
	 */
	public void putCycleCountNext(String _cycle, int _count, int _state,int _normalcount) {

		if (_cycle.equals("1")) {
			// 周期为天
			if (_state == 0) { // 天总数
				this.setDayCount(this.getDayCount()+ _count);
				this.setAllCount(this.getAllCount() + _count);
				
			} else if (_state == 1) { // 天按时数
				this.setDayInTimeCount(this.getDayInTimeCount() + _count);
				this.setAllinTimeCount(this.getAllinTimeCount() + _count);
			} else if (_state == 2) { // 天超时数
				this.setDayOutTimeCount(this.getDayOutTimeCount() + _count);
				this.setAlloutTimeCount(this.getAlloutTimeCount() + _count);
			}
			 
			this.setDayNormalCount(this.getDayNormalCount()+_normalcount);
			 
		} else if (_cycle.equals("2")) {
			// 周期为周
			if (_state == 0) { // 周总数
				this.setWeekCount(this.getWeekCount()+_count);
				this.setAllCount(this.getAllCount() + _count);
			} else if (_state == 1) { // 周按时数
				this.setWeekInTimeCount(this.getWeekInTimeCount()+_count);
				this.setAllinTimeCount(this.getAllinTimeCount() + _count);
			} else if (_state == 2) { // 周超时数
				this.setWeekOutTimeCount(this.getWeekOutTimeCount()+_count);
				this.setAlloutTimeCount(this.getAlloutTimeCount() + _count);
			}
			 
				this.setWeekNormalCount(this.getWeekNormalCount()+_normalcount);
			 
		} else if (_cycle.equals("3")) {
			// 周期为半月
			if (_state == 0) { // 半月总数
				this.setHalfmonthCount(this.getHalfmonthCount()+ _count);
				this.setAllCount(this.getAllCount() + _count);
			} else if (_state == 1) { // 半月按时数
				this.setHalfmonthInTimeCount(this.getHalfmonthInTimeCount()+_count);
				this.setAllinTimeCount(this.getAllinTimeCount() + _count);
			} else if (_state == 2) { // 半月超时数
				this.setHalfmonthOutTimeCount(this.getHalfmonthOutTimeCount()+_count);
				this.setAlloutTimeCount(this.getAlloutTimeCount() + _count);
			}
			 
				this.setHalfmonthNormalCount(this.getHalfmonthNormalCount()+_normalcount);
			 
		} else if (_cycle.equals("4")) {
			// 周期为月
			if (_state == 0) { // 月总数
				this.setMonthCount(this.getMonthCount()+_count);
				this.setAllCount(this.getAllCount() + _count);
			} else if (_state == 1) { // 月按时数
				this.setMonthInTimeCount(this.getMonthInTimeCount()+_count);
				this.setAllinTimeCount(this.getAllinTimeCount() + _count);
			} else if (_state == 2) { // 月超时数
				this.setMonthOutTimeCount(this.getMonthOutTimeCount()+ _count);
				this.setAlloutTimeCount(this.getAlloutTimeCount() + _count);
			}
			 
				this.setMonthNormalCount(this.getMonthNormalCount()+_normalcount);
			 
		} else if (_cycle.equals("5")) {
			// 周期为季度
			if (_state == 0) { // 季度总数
				this.setQuarterCount(this.getQuarterCount()+ _count);
				this.setAllCount(this.getAllCount() + _count);
			} else if (_state == 1) { // 季度按时数
				this.setQuarterInTimeCount(this.getQuarterInTimeCount()+ _count);
				this.setAllinTimeCount(this.getAllinTimeCount() + _count);
			} else if (_state == 2) { // 季度超时数
				this.setQuarterOutTimeCount(this.getQuarterOutTimeCount()+ _count);
				this.setAlloutTimeCount(this.getAlloutTimeCount() + _count);
			}
			 
				this.setQuarterNormalCount(this.getQuarterNormalCount()+_normalcount);
			 
		} else if (_cycle.equals("6")) {
			// 周期为半年
			if (_state == 0) { // 半年总数
				this.setHalfyearCount(this.getHalfyearCount() + _count);
				this.setAllCount(this.getAllCount() + _count);
			} else if (_state == 1) { // 半年按时数
				this.setHalfyearInTimeCount(this.getHalfyearInTimeCount() +_count);
				this.setAllinTimeCount(this.getAllinTimeCount() + _count);
			} else if (_state == 2) { // 半年超时数
				this.setHalfyearOutTimeCount(this.getHalfyearOutTimeCount() + _count);
				this.setAlloutTimeCount(this.getAlloutTimeCount() + _count);
			}
			 
				this.setHalfyearNormalCount(this.getHalfyearNormalCount()+_normalcount);
			 
		} else if (_cycle.equals("7")) {
			// 周期为年
			if (_state == 0) { // 年总数
				this.setYearCount(this.getYearCount() + _count);
				this.setAllCount(this.getAllCount() + _count);
			} else if (_state == 1) { // 年按时数
				this.setYearInTimeCount(this.getYearInTimeCount() + _count);
				this.setAllinTimeCount(this.getAllinTimeCount() + _count);
			} else if (_state == 2) { // 年超时数
				this.setYearOutTimeCount(this.getYearOutTimeCount() + _count);
				this.setAlloutTimeCount(this.getAlloutTimeCount() + _count);
			}
			 
				this.setYearNormalCount(this.getYearNormalCount()+_normalcount);
			 
		} else if (_cycle.equals("8")) {
			// 周期为两月
			if (_state == 0) { // 两月总数
				this.setTowmonthCount(this.getTowmonthCount() + _count);
				this.setAllCount(this.getAllCount() + _count);
			} else if (_state == 1) { // 两月按时数
				this.setTowmonthInTimeCount(this.getTowmonthInTimeCount()+ _count);
				this.setAllinTimeCount(this.getAllinTimeCount() + _count);
			} else if (_state == 2) { // 两月超时数
				this.setTowmonthOutTimeCount(this.getTowmonthOutTimeCount() + _count);
				this.setAlloutTimeCount(this.getAlloutTimeCount() + _count);
			}
			 
				this.setTowmonthNormalCount(this.getTowmonthNormalCount()+_normalcount);
			 
		} else if (_cycle.equals("9")) {
			// 周期为四月
			if (_state == 0) { // 四月总数
				this.setFourmonthCount(this.getFourmonthCount() + _count);
				this.setAllCount(this.getAllCount() + _count);
			} else if (_state == 1) { // 四月按时数
				this.setFourmonthInTimeCount(this.getFourmonthInTimeCount() + _count);
				this.setAllinTimeCount(this.getAllinTimeCount() + _count);
			} else if (_state == 2) { // 四月超时数
				this.setFourmonthOutTimeCount(this.getFourmonthOutTimeCount()+_count);
				this.setAlloutTimeCount(this.getAlloutTimeCount() + _count);
			}
			 
				this.setFourmonthNormalCount(this.getFourmonthNormalCount()+_normalcount);
			 
		} else if (_cycle.equals("0")) {
			// 周期为其他
			if (_state == 0) { // 其他总数
				this.setTempCount(this.getTempCount()+ _count);
				this.setAllCount(this.getAllCount() + _count);
			} else if (_state == 1) { // 其他按时数
				this.setTempInTimeCount(this.getTempInTimeCount() + _count);
				this.setAllinTimeCount(this.getAllinTimeCount() + _count);
			} else if (_state == 2) { // 其他超时数
				this.setTempOutTimeCount(this.getTempOutTimeCount()+ _count);
				this.setAlloutTimeCount(this.getAlloutTimeCount() + _count);
			}
			 
				this.setTempNormalCount(this.getTempNormalCount()+_normalcount);
			 
		}
	}


	/**
	 * 生成当前日志对象的xml字符串
	 * 
	 * @param url
	 *            String 连接路径
	 * @return String xml字符串
	 */
	public String getresultvalue(double a)
	{
	int b=(int)a*100;
	if(a==1.0)
	{
		this.setResult("优");
	}
	else{
		SAXBuilder builder = new SAXBuilder();
		DocumentBuilderFactory xmlfactory = DocumentBuilderFactory.newInstance();
		 try {
			org.jdom.Document doc = builder.build(new File("../webapps/eomsMain/web-inf/classes/config/evaluation-result.xml"));
			Element root = doc.getRootElement();
			 List list = root.getChildren("item");  
			 for(Iterator it=list.iterator();it.hasNext();)
				{
					Element operation = (Element)it.next(); 
					int h=Integer.parseInt(operation.getAttributeValue("high").toString().replaceAll("%", ""));
					int l=Integer.parseInt(operation.getAttributeValue("low").toString().replace("%", ""));
				 if(l==b)
				 {
					 this.setResult(operation.getAttributeValue("value").toString());
				 }
				 if(h>b&&l<b)
				 {
					this.setResult(operation.getAttributeValue("value").toString());
					System.out.println(this.getResult());
				 }
				}
		 }
		 catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		return this.getResult();
	}
	
	public String writeHTMLresult(String url) {
		StringBuffer str = null;
		str = new StringBuffer();

		str.append("<a href='" + url + "'>");
		str.append("<tr class='tr_show'>");
		str.append("<td nowrap>" + this.getTawDept().getDeptName() + "</td>");
		str.append("<td >" + this.getDayCount() + "</td>");
		str.append("<td >" + this.getDayInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getDayInTimeCount() + this.getDayOutTimeCount())
				+ "</td>");
		str.append("<td >" + this.getWeekCount() + "</td>");
		str.append("<td >" + this.getWeekInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getWeekInTimeCount() + this.getWeekOutTimeCount())
				+ "</td>");
		str.append("<td >" + this.getHalfmonthCount() + "</td>");
		str.append("<td >" + this.getHalfmonthInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getHalfmonthInTimeCount() + this
						.getHalfmonthOutTimeCount()) + "</td>");
		str.append("<td >" + this.getMonthCount() + "</td>");
		str.append("<td >" + this.getMonthInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getMonthInTimeCount() + this.getMonthOutTimeCount())
				+ "</td>");
		str.append("<td >" + this.getQuarterCount() + "</td>");
		str.append("<td >" + this.getQuarterInTimeCount() + "</td>");
		str
				.append("<td >"
						+ (this.getQuarterInTimeCount() + this
								.getQuarterOutTimeCount()) + "</td>");
		str.append("<td >" + this.getHalfyearCount() + "</td>");
		str.append("<td >" + this.getHalfyearInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getHalfyearInTimeCount() + this
						.getHalfyearOutTimeCount()) + "</td>");
		str.append("<td >" + this.getYearCount() + "</td>");
		str.append("<td >" + this.getYearInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getYearInTimeCount() + this.getYearOutTimeCount())
				+ "</td>");
		str.append("<td >" + this.getYearCount() + "</td>");
		str.append("<td >" + this.getTempCount() + "</td>");
		str.append("<td >"
				+ (this.getTempInTimeCount() + this.getTempOutTimeCount())
				+ "</td>");
		str.append("<td >" + this.getAllCount() + "</td>");
		str.append("<td >" + this.getAllinTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getAllinTimeCount() + this.getAlloutTimeCount())
				+ "</td>");
		if (this.getAllCount() > 0) {
			str.append("<td >"
					+ ((((double) this.getAllinTimeCount() / (double) this
							.getAllCount()) * 100) + "00000").subSequence(0, 5)
					+ "%</td>");

			str.append("<td >"
					+ (((((double) this.getAllinTimeCount() + this
							.getAlloutTimeCount()) / (double) this
							.getAllCount()) * 100) + "00000").substring(0, 5)+"dd"
					+ "%</td>");
		} else {
			str.append("<td >0%</td>");
			str.append("<td >0%</td>");
		}
		str.append("<td >100%</td>");
		str.append("<td >1</td>");
		str.append("<td>"+this.getresultvalue((this.getAllinTimeCount()+this.getAlloutTimeCount())/(double)this.getAllCount())+"</td>");
		str.append("</tr></a>");

		return str.toString();
	}


	public static String writeHeadHTML(String _yearFlag, String _monthFlag,
			boolean _selectFlag) {
		StringBuffer str = null;

		str = new StringBuffer();
		str.append(" ");
		// str.append("<%@ include file=\"/common/taglibs.jsp\"%>");
		str.append("<%@ include file=\"/workplan/header_eoms_form.jsp\"%>");

		str.append("<script language='javascript'>");
		str.append("function onMonth(){");
		str.append("document.statyear.submit();}");
		str.append("</script>");

		// str.append(
		// "<link rel='stylesheet' href='../css/table_style.css'
		// type='text/css'>");

		if (_selectFlag) {
			str.append("<form name='statyear'>");
			str.append("<table width='700' align=center class=\"listTable\" >");
			str.append("<caption>");
			str.append(_yearFlag + "年" + _monthFlag + "月统计数据");
			str.append("<select size='1' name='yearflag'>");
			str.append("<option value='2006'>2006</option>");
			str.append("<option value='2007'>2007</option>");
			str.append("<option value='2008'>2008</option>");
			str.append("<option value='2009'>2009</option>");
			str.append("<option value='2010'>2010</option>");
			str.append("</select>年");
			str.append("<select size='1' name='monthflag'>");
			str.append("<option value='01'>01</option>");
			str.append("<option value='02'>02</option>");
			str.append("<option value='03'>03</option>");
			str.append("<option value='04'>04</option>");
			str.append("<option value='05'>05</option>");
			str.append("<option value='06'>06</option>");
			str.append("<option value='07'>07</option>");
			str.append("<option value='08'>08</option>");
			str.append("<option value='09'>09</option>");
			str.append("<option value='10'>10</option>");
			str.append("<option value='11'>11</option>");
			str.append("<option value='12'>12</option>");
			str.append("</select>月");
			str
					.append("<input type='button' value='月度选择' name='B1' onclick='onMonth()'  class='button'>");
			str.append(" ");
			str.append("<script language='javascript'>");
			str.append("document.forms[0].yearflag.value = '" + _yearFlag
					+ "';");
			str.append("document.forms[0].monthflag.value = '" + _monthFlag
					+ "';");
			str.append("</script>");
			str.append("</caption>");
			str.append("</table>");
			str.append("</form>");
		}

		str.append("<table class=\"listTable\">");
		str.append("<thead>");
		str.append("<tr > ");
		str.append("<td rowspan='2'>部门</td>");
		str.append("<td colspan='3'>天</td>");
		str.append("<td colspan='3'>周</td>");
		str.append("<td colspan='3'>半月</td>");
		str.append("<td colspan='3'>月</td>");
		str.append("<td colspan='3'>季度</td>");
		str.append("<td colspan='3'>半年</td>");
		str.append("<td colspan='3'>年</td>");
		str.append("<td colspan='3'>其他</td>");
		str.append("<td colspan='3'>总计</td>");
		str.append("<td rowspan='2'>及时率</td>");
		str.append("<td rowspan='2'>完成率</td>");
		str.append("<td rowspan='2'>制定及时率</td>");
		str.append("<td rowspan='2'>得分</td>");
		str.append("</tr>");

		str.append("<tr  >");
		str.append("<td >总数</td>");
		str.append("<td >及时数</td>");
		str.append("<td >完成数</td>");
		str.append("<td >总数</td>");
		str.append("<td >及时数</td>");
		str.append("<td >完成数</td>");
		str.append("<td >总数</td>");
		str.append("<td >及时数</td>");
		str.append("<td >完成数</td>");
		str.append("<td >总数</td>");
		str.append("<td >及时数</td>");
		str.append("<td >完成数</td>");
		str.append("<td >总数</td>");
		str.append("<td >及时数</td>");
		str.append("<td >完成数</td>");
		str.append("<td >总数</td>");
		str.append("<td >及时数</td>");
		str.append("<td >完成数</td>");
		str.append("<td >总数</td>");
		str.append("<td >及时数</td>");
		str.append("<td >完成数</td>");
		str.append("<td >总数</td>");
		str.append("<td >及时数</td>");
		str.append("<td >完成数</td>");
		str.append("<td >总数</td>");
		str.append("<td >及时数</td>");
		str.append("<td >完成数</td>");
		str.append("</tr>");
		str.append("</thead>");
		return str.toString();
	}

	public static String writeFootHTML() {
		return "</table ><br><font color='blue' size=2>注:点击行可以查看详情<font>";
	}

	public static Object writeHeadHTML(String _startTime, String _endTime,
			boolean _selectFlag, boolean _detail) {
		StringBuffer str = null;

		str = new StringBuffer();
		str.append("<%@ page contentType='text/html; charset=GB2312' %>");
		str.append("<%@ taglib uri='/WEB-INF/app.tld' prefix='eoms' %>");
		str.append("<script language='javascript'>");
		str.append("function onMonth(){");
		str.append("document.statyear.submit();}");
		str.append("function changelocation(){");
		str.append("var viewFlag=document.statyear.viewflag.value;");
		str.append("if(viewFlag==1)");
		str
				.append("window.navigate('../tawwpstat/statyearall.do?detail=true');");
		str.append("else ");
		str.append("window.navigate('../tawwpstat/statyearall.do');");
		str.append("}");
		str.append("function GoBack(){");
		str.append("window.history.back();");
		str.append("}");
		str.append("</script>");

		str
				.append("<link rel='stylesheet' href='../css/table_style.css' type='text/css'>");

		if (_selectFlag) {
			str.append("<form name='statyear'>");
			str
					.append("<table width='100%' align=center style='margin:0pt 0pt 2pt 0pt'>");
			str.append("<tr>");
			str.append("<td width='100'  align='left' >");
			str
					.append("<select size=1 name='viewflag' style='width:100' onChange='changelocation()'>");
			str.append("<option value='0' >普通视图</option>");
			str.append("<option value='1'");
			if (_detail)
				str.append(" selected='selected' ");
			str.append(">详细视图</option>");
			str.append("</select>");
			str.append("</td>");
			str.append("<td width='600'  align='right' >");
			str
					.append("<eoms:SelectDate name='startTime' formName='statyear' value='"
							+ _startTime + "'/>");
			str.append("---");
			str
					.append("<eoms:SelectDate name='endTime' formName='statyear' value='"
							+ _endTime + "'/>");
			str
					.append("<input type='button' value='选择' name='B1' onclick='onMonth()'  Class='clsbtn2'>");
			str.append("</td></tr></table>");
			str.append("</form>");
		}

		str
				.append("<table width='700' align=center style='margin:0pt 0pt 2pt 0pt'>");
		str.append("<tr>");
		str
				.append("<td width='700' align='center' valign='middle' class='table_title'>");
		str.append(_startTime + "至" + _endTime + " 统计数据");
		str.append("</td></tr></table>");

		str
				.append("<table border='0'  cellspacing='1' cellpadding='1' class='table_show' align='center' width='100%'>");
		str.append("<tr class='td_label'>");
		str.append("<td rowspan='2'>部门</td>");
		if (_detail) {
			str.append("<td colspan='3'>天</td>");
			str.append("<td colspan='3'>周</td>");
			str.append("<td colspan='3'>半月</td>");
			str.append("<td colspan='3'>月</td>");
			str.append("<td colspan='3'>季度</td>");
			str.append("<td colspan='3'>半年</td>");
			str.append("<td colspan='3'>年</td>");
			str.append("<td colspan='3'>其他</td>");
		}
		str.append("<td colspan='3'>总计</td>");
		str.append("<td rowspan='2'>及时率</td>");
		str.append("<td rowspan='2'>完成率</td>");
		// str.append("<td rowspan='2'>制定及时率</td>");
		str.append("</tr>");
		str.append("<tr class='td_label'>");
		if (_detail) {
			str.append("<td >总数</td>");
			str.append("<td >及时数</td>");
			str.append("<td >未执行</td>");
			str.append("<td >总数</td>");
			str.append("<td >及时数</td>");
			str.append("<td >未执行</td>");
			str.append("<td >总数</td>");
			str.append("<td >及时数</td>");
			str.append("<td >未执行</td>");
			str.append("<td >总数</td>");
			str.append("<td >及时数</td>");
			str.append("<td >未执行</td>");
			str.append("<td >总数</td>");
			str.append("<td >及时数</td>");
			str.append("<td >未执行</td>");
			str.append("<td >总数</td>");
			str.append("<td >及时数</td>");
			str.append("<td >未执行</td>");
			str.append("<td >总数</td>");
			str.append("<td >及时数</td>");
			str.append("<td >未执行</td>");
			str.append("<td >总数</td>");
			str.append("<td >及时数</td>");
			str.append("<td >未执行</td>");
		}
		str.append("<td >总数</td>");
		str.append("<td >及时数</td>");
		str.append("<td >未执行</td>");
		// str.append("<td >得分</td>");
		str.append("</tr>");

		return str.toString();
	}

	public Object writeHTML(String url, boolean _detailFlag) {
		StringBuffer str = null;
		str = new StringBuffer();

		str.append("<a href='" + url + "'>");
		str
				.append("<tr class='tr_show' style=\"cursor:hand\" onmousemove=\"this.style.backgroundColor='#87CEEB'\" onmouseout=\"this.style.backgroundColor=''\">");
		str.append("<td>" + this.getTawDept().getDeptName() + "</td>");
		if (_detailFlag) {
			str.append("<td >" + this.getDayCount() + "</td>");
			str.append("<td >" + this.getDayInTimeCount() + "</td>");
			str.append("<td >"
					+ (this.getDayCount() - this.getDayInTimeCount() - this
							.getDayOutTimeCount()) + "</td>");
			str.append("<td >" + this.getWeekCount() + "</td>");
			str.append("<td >" + this.getWeekInTimeCount() + "</td>");
			str.append("<td >"
					+ (this.getWeekCount() - this.getWeekInTimeCount() - this
							.getWeekOutTimeCount()) + "</td>");
			str.append("<td >" + this.getHalfmonthCount() + "</td>");
			str.append("<td >" + this.getHalfmonthInTimeCount() + "</td>");
			str.append("<td >"
					+ (this.getHalfmonthCount()
							- this.getHalfmonthInTimeCount() - this
							.getHalfmonthOutTimeCount()) + "</td>");
			str.append("<td >" + this.getMonthCount() + "</td>");
			str.append("<td >" + this.getMonthInTimeCount() + "</td>");
			str.append("<td >"
					+ (this.getMonthCount() - this.getMonthInTimeCount() - this
							.getMonthOutTimeCount()) + "</td>");
			str.append("<td >" + this.getQuarterCount() + "</td>");
			str.append("<td >" + this.getQuarterInTimeCount() + "</td>");
			str
					.append("<td >"
							+ (this.getQuarterCount()
									- this.getQuarterInTimeCount() - this
									.getQuarterOutTimeCount()) + "</td>");
			str.append("<td >" + this.getHalfyearCount() + "</td>");
			str.append("<td >" + this.getHalfyearInTimeCount() + "</td>");
			str
					.append("<td >"
							+ (this.getHalfyearCount()
									- this.getHalfyearInTimeCount() - this
									.getHalfyearOutTimeCount()) + "</td>");
			str.append("<td >" + this.getYearCount() + "</td>");
			str.append("<td >" + this.getYearInTimeCount() + "</td>");
			str.append("<td >"
					+ (this.getYearCount() - this.getYearInTimeCount() - this
							.getYearOutTimeCount()) + "</td>");
			str.append("<td >" + this.getTempCount() + "</td>");
			str.append("<td >" + this.getTempInTimeCount() + "</td>");
			str.append("<td >"
					+ (this.getTempCount() - this.getTempInTimeCount() - this
							.getTempOutTimeCount()) + "</td>");
		}

		str.append("<td >" + this.getAllCount() + "</td>");
		str.append("<td >" + this.getAllinTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getAllCount() - this.getAllinTimeCount() - this
						.getAlloutTimeCount()) + "</td>");
		if (this.getAllCount() > 0) {
			str.append("<td >"
					+ ((((double) this.getAllinTimeCount() / (double) this
							.getAllCount()) * 100) + "00000").subSequence(0, 5)
					+ "%</td>");
			str.append("<td >"
					+ (((((double) this.getAllinTimeCount() + this
							.getAlloutTimeCount()) / (double) this
							.getAllCount()) * 100) + "00000").substring(0, 5)
					+ "%</td>");
		} else {
			str.append("<td >0%</td>");
			str.append("<td >0%</td>");
		}
		str.append("</tr></a>");

		return str.toString();
	}

	public TawwpMonthPlan getTawWpMonthPlan() {
		return tawWpMonthPlan;
	}

	public void setTawWpMonthPlan(TawwpMonthPlan tawWpMonthPlan) {
		this.tawWpMonthPlan = tawWpMonthPlan;
	}

	public TawwpNet getTawwpNet() {
		return tawwpNet;
	}

	public void setTawwpNet(TawwpNet tawwpNet) {
		this.tawwpNet = tawwpNet;
	}

	/**
	 * 生成当前日志对象的xml字符串 按照网元统计
	 * 
	 * @param url
	 *            String 连接路径
	 * @return String xml字符串
	 */
	public String writeHTMLNet() {
		StringBuffer str = null;
		str = new StringBuffer();

		str.append("<a href='../tawwpstat/querymonthplanresult.do?monthplanid="
				+ this.getTawWpMonthPlan().getId() + "'>");
		str.append("<tr class='tr_show'>");
		str.append("<td nowrap>" + this.getTawwpNet().getName() + "</td>");
		str
				.append("<td nowrap>" + this.getTawWpMonthPlan().getName()
						+ "</td>");
		str.append("<td >" + this.getDayCount() + "</td>");
		str.append("<td >" + this.getDayInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getDayInTimeCount() + this.getDayOutTimeCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getDayNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getDayCount() - this.getDayNormalCount())
				+ "</td>");
		str.append("<td >" + this.getWeekCount() + "</td>");
		str.append("<td >" + this.getWeekInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getWeekInTimeCount() + this.getWeekOutTimeCount())
				+ "</td>");
		//
		str.append("<td >"
				+ (this.getWeekNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getWeekCount() - this.getWeekNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getHalfmonthCount() + "</td>");
		str.append("<td >" + this.getHalfmonthInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getHalfmonthInTimeCount() + this
						.getHalfmonthOutTimeCount()) + "</td>");
		//
		str.append("<td >"
				+ (this.getHalfmonthNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getHalfmonthCount() - this.getHalfmonthNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getMonthCount() + "</td>");
		str.append("<td >" + this.getMonthInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getMonthInTimeCount() + this.getMonthOutTimeCount())
				+ "</td>");
		//
		str.append("<td >"
				+ (this.getMonthNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getMonthCount() - this.getMonthNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getQuarterCount() + "</td>");
		str.append("<td >" + this.getQuarterInTimeCount() + "</td>");
		str
				.append("<td >"
						+ (this.getQuarterInTimeCount() + this
								.getQuarterOutTimeCount()) + "</td>");
		//
		str.append("<td >"
				+ (this.getQuarterNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getQuarterCount() - this.getQuarterNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getHalfyearCount() + "</td>");
		str.append("<td >" + this.getHalfyearInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getHalfyearInTimeCount() + this
						.getHalfyearOutTimeCount()) + "</td>");
		//
		str.append("<td >"
				+ (this.getHalfyearNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getHalfyearCount() - this.getHalfyearNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getYearCount() + "</td>");
		str.append("<td >" + this.getYearInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getYearInTimeCount() + this.getYearOutTimeCount())
				+ "</td>");
		//
		str.append("<td >"
				+ (this.getYearNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getYearCount() - this.getYearNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getYearCount() + "</td>");
		str.append("<td >" + this.getTempCount() + "</td>");
		str.append("<td >"
				+ (this.getTempInTimeCount() + this.getTempOutTimeCount())
				+ "</td>");
		//
		str.append("<td >"
				+ (this.getTempNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getTempCount() - this.getTempNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getAllCount() + "</td>");
		str.append("<td >" + this.getAllinTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getAllinTimeCount() + this.getAlloutTimeCount())
				+ "</td>");
	 
		if (this.getAllCount() > 0) {
			str.append("<td >"
					+ ((((double) this.getAllinTimeCount() / (double) this
							.getAllCount()) * 100) + "00000").subSequence(0, 5)
					+ "%</td>");

			str.append("<td >"
					+ (((((double) this.getAllinTimeCount() + this
							.getAlloutTimeCount()) / (double) this
							.getAllCount()) * 100) + "00000").substring(0, 5)
					+ "%</td>");
		} else {
			str.append("<td >0%</td>");
			str.append("<td >0%</td>");
		}

		str.append("</tr></a>");

		return str.toString();
	}

	public TawSystemCptroom getTawSystemCptroom() {
		return tawSystemCptroom;
	}

	public void setTawSystemCptroom(TawSystemCptroom tawSystemCptroom) {
		this.tawSystemCptroom = tawSystemCptroom;
	}

	/**
	 * 生成当前日志对象的xml字符串 按照网元统计
	 * 
	 * @param url
	 *            String 连接路径
	 * @return String xml字符串
	 */
	public String writeHTMLRoom() {
		StringBuffer str = null;
		str = new StringBuffer();

		str.append("<a href='../tawwpstat/querymonthplanresult.do?monthplanid="
				+ this.getTawWpMonthPlan().getId() + "'>");
		str.append("<tr class='tr_show'>");
		str.append("<td nowrap>" + this.getTawSystemCptroom().getRoomname()
				+ "</td>");
		str
				.append("<td nowrap>" + this.getTawWpMonthPlan().getName()
						+ "</td>");
		str.append("<td >" + this.getDayCount() + "</td>");
		str.append("<td >" + this.getDayInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getDayInTimeCount() + this.getDayOutTimeCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getDayNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getDayCount() - this.getDayNormalCount())
				+ "</td>");
		str.append("<td >" + this.getWeekCount() + "</td>");
		str.append("<td >" + this.getWeekInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getWeekInTimeCount() + this.getWeekOutTimeCount())
				+ "</td>");
		//
		str.append("<td >"
				+ (this.getWeekNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getWeekCount() - this.getWeekNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getHalfmonthCount() + "</td>");
		str.append("<td >" + this.getHalfmonthInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getHalfmonthInTimeCount() + this
						.getHalfmonthOutTimeCount()) + "</td>");
		//
		str.append("<td >"
				+ (this.getHalfmonthNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getHalfmonthCount() - this.getHalfmonthNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getMonthCount() + "</td>");
		str.append("<td >" + this.getMonthInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getMonthInTimeCount() + this.getMonthOutTimeCount())
				+ "</td>");
		//
		str.append("<td >"
				+ (this.getMonthNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getMonthCount() - this.getMonthNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getQuarterCount() + "</td>");
		str.append("<td >" + this.getQuarterInTimeCount() + "</td>");
		str
				.append("<td >"
						+ (this.getQuarterInTimeCount() + this
								.getQuarterOutTimeCount()) + "</td>");
		//
		str.append("<td >"
				+ (this.getQuarterNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getQuarterCount() - this.getQuarterNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getHalfyearCount() + "</td>");
		str.append("<td >" + this.getHalfyearInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getHalfyearInTimeCount() + this
						.getHalfyearOutTimeCount()) + "</td>");
		//
		str.append("<td >"
				+ (this.getHalfyearNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getHalfyearCount() - this.getHalfyearNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getYearCount() + "</td>");
		str.append("<td >" + this.getYearInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getYearInTimeCount() + this.getYearOutTimeCount())
				+ "</td>");
		//
		str.append("<td >"
				+ (this.getYearNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getYearCount() - this.getYearNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getYearCount() + "</td>");
		str.append("<td >" + this.getTempCount() + "</td>");
		str.append("<td >"
				+ (this.getTempInTimeCount() + this.getTempOutTimeCount())
				+ "</td>");
		//
		str.append("<td >"
				+ (this.getTempNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getTempCount() - this.getTempNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getAllCount() + "</td>");
		str.append("<td >" + this.getAllinTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getAllinTimeCount() + this.getAlloutTimeCount())
				+ "</td>");
	 
		if (this.getAllCount() > 0) {
			str.append("<td >"
					+ ((((double) this.getAllinTimeCount() / (double) this
							.getAllCount()) * 100) + "00000").subSequence(0, 5)
					+ "%</td>");

			str.append("<td >"
					+ (((((double) this.getAllinTimeCount() + this
							.getAlloutTimeCount()) / (double) this
							.getAllCount()) * 100) + "00000").substring(0, 5)
					+ "%</td>");
		} else {
			str.append("<td >0%</td>");
			str.append("<td >0%</td>");
		}

		str.append("</tr></a>");

		return str.toString();
	}

	/**
	 * @return
	 */
	public String writeHTMLUser() {
		StringBuffer str = null;
		str = new StringBuffer();
		str.append("<a href='../tawwpstat/querymonthplanresult.do?monthplanid="
				+ this.getTawWpMonthPlan().getId() + "'>");
		str.append("<tr class='tr_show'>");
		str.append("<td nowrap>" + this.getTawSystemUser().getDeptname()
				+ "</td>");
		str.append("<td nowrap>" + this.getTawSystemUser().getUsername()
				+ "</td>");
		str
				.append("<td nowrap>" + this.getTawWpMonthPlan().getName()
						+ "</td>");
		str.append("<td >" + this.getDayCount() + "</td>");
		str.append("<td >" + this.getDayInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getDayInTimeCount() + this.getDayOutTimeCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getDayNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getDayCount() - this.getDayNormalCount())
				+ "</td>");
		str.append("<td >" + this.getWeekCount() + "</td>");
		str.append("<td >" + this.getWeekInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getWeekInTimeCount() + this.getWeekOutTimeCount())
				+ "</td>");
		//
		str.append("<td >"
				+ (this.getWeekNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getWeekCount() - this.getWeekNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getHalfmonthCount() + "</td>");
		str.append("<td >" + this.getHalfmonthInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getHalfmonthInTimeCount() + this
						.getHalfmonthOutTimeCount()) + "</td>");
		//
		str.append("<td >"
				+ (this.getHalfmonthNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getHalfmonthCount() - this.getHalfmonthNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getMonthCount() + "</td>");
		str.append("<td >" + this.getMonthInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getMonthInTimeCount() + this.getMonthOutTimeCount())
				+ "</td>");
		//
		str.append("<td >"
				+ (this.getMonthNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getMonthCount() - this.getMonthNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getQuarterCount() + "</td>");
		str.append("<td >" + this.getQuarterInTimeCount() + "</td>");
		str
				.append("<td >"
						+ (this.getQuarterInTimeCount() + this
								.getQuarterOutTimeCount()) + "</td>");
		//
		str.append("<td >"
				+ (this.getQuarterNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getQuarterCount() - this.getQuarterNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getHalfyearCount() + "</td>");
		str.append("<td >" + this.getHalfyearInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getHalfyearInTimeCount() + this
						.getHalfyearOutTimeCount()) + "</td>");
		//
		str.append("<td >"
				+ (this.getHalfyearNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getHalfyearCount() - this.getHalfyearNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getYearCount() + "</td>");
		str.append("<td >" + this.getYearInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getYearInTimeCount() + this.getYearOutTimeCount())
				+ "</td>");
		//
		str.append("<td >"
				+ (this.getYearNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getYearCount() - this.getYearNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getYearCount() + "</td>");
		str.append("<td >" + this.getTempCount() + "</td>");
		str.append("<td >"
				+ (this.getTempInTimeCount() + this.getTempOutTimeCount())
				+ "</td>");
		//
		str.append("<td >"
				+ (this.getTempNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getTempCount() - this.getTempNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getAllCount() + "</td>");
		str.append("<td >" + this.getAllinTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getAllinTimeCount() + this.getAlloutTimeCount())
				+ "</td>");
	 
		if (this.getAllCount() > 0) {
			str.append("<td >"
					+ ((((double) this.getAllinTimeCount() / (double) this
							.getAllCount()) * 100) + "00000").subSequence(0, 5)
					+ "%</td>");

			str.append("<td >"
					+ (((((double) this.getAllinTimeCount() + this
							.getAlloutTimeCount()) / (double) this
							.getAllCount()) * 100) + "00000").substring(0, 5)
					+ "%</td>");
		} else {
			str.append("<td >0%</td>");
			str.append("<td >0%</td>");
		}

		str.append("</tr></a>");

		return str.toString();
	}
	
	
	/**
	 * @return
	 */
	public String writeHTMLTime() {
		StringBuffer str = null;
		str = new StringBuffer();
		str.append("<a href='../tawwpstat/querymonthplanresult.do?monthplanid="
				+ this.getTawWpMonthPlan().getId() + "'>");
		str.append("<tr class='tr_show'>");
		str.append("<td nowrap>" + this.getYearFlag()
				+ "</td>");
		str.append("<td nowrap>" + this.getMonthFlag()
				+ "</td>");
		str
				.append("<td nowrap>" + this.getTawWpMonthPlan().getName()
						+ "</td>");
		str.append("<td >" + this.getDayCount() + "</td>");
		str.append("<td >" + this.getDayInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getDayInTimeCount() + this.getDayOutTimeCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getDayNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getDayCount() - this.getDayNormalCount())
				+ "</td>");
		str.append("<td >" + this.getWeekCount() + "</td>");
		str.append("<td >" + this.getWeekInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getWeekInTimeCount() + this.getWeekOutTimeCount())
				+ "</td>");
		//
		str.append("<td >"
				+ (this.getWeekNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getWeekCount() - this.getWeekNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getHalfmonthCount() + "</td>");
		str.append("<td >" + this.getHalfmonthInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getHalfmonthInTimeCount() + this
						.getHalfmonthOutTimeCount()) + "</td>");
		//
		str.append("<td >"
				+ (this.getHalfmonthNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getHalfmonthCount() - this.getHalfmonthNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getMonthCount() + "</td>");
		str.append("<td >" + this.getMonthInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getMonthInTimeCount() + this.getMonthOutTimeCount())
				+ "</td>");
		//
		str.append("<td >"
				+ (this.getMonthNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getMonthCount() - this.getMonthNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getQuarterCount() + "</td>");
		str.append("<td >" + this.getQuarterInTimeCount() + "</td>");
		str
				.append("<td >"
						+ (this.getQuarterInTimeCount() + this
								.getQuarterOutTimeCount()) + "</td>");
		//
		str.append("<td >"
				+ (this.getQuarterNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getQuarterCount() - this.getQuarterNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getHalfyearCount() + "</td>");
		str.append("<td >" + this.getHalfyearInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getHalfyearInTimeCount() + this
						.getHalfyearOutTimeCount()) + "</td>");
		//
		str.append("<td >"
				+ (this.getHalfyearNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getHalfyearCount() - this.getHalfyearNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getYearCount() + "</td>");
		str.append("<td >" + this.getYearInTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getYearInTimeCount() + this.getYearOutTimeCount())
				+ "</td>");
		//
		str.append("<td >"
				+ (this.getYearNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getYearCount() - this.getYearNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getYearCount() + "</td>");
		str.append("<td >" + this.getTempCount() + "</td>");
		str.append("<td >"
				+ (this.getTempInTimeCount() + this.getTempOutTimeCount())
				+ "</td>");
		//
		str.append("<td >"
				+ (this.getTempNormalCount())
				+ "</td>");
		str.append("<td >"
				+ (this.getTempCount() - this.getTempNormalCount())
				+ "</td>");
		//
		str.append("<td >" + this.getAllCount() + "</td>");
		str.append("<td >" + this.getAllinTimeCount() + "</td>");
		str.append("<td >"
				+ (this.getAllinTimeCount() + this.getAlloutTimeCount())
				+ "</td>");
	 
		if (this.getAllCount() > 0) {
			str.append("<td >"
					+ ((((double) this.getAllinTimeCount() / (double) this
							.getAllCount()) * 100) + "00000").subSequence(0, 5)
					+ "%</td>");

			str.append("<td >"
					+ (((((double) this.getAllinTimeCount() + this
							.getAlloutTimeCount()) / (double) this
							.getAllCount()) * 100) + "00000").substring(0, 5)
					+ "%</td>");
		} else {
			str.append("<td >0%</td>");
			str.append("<td >0%</td>");
		}

		str.append("</tr></a>");

		return str.toString();
	}

	public TawSystemUser getTawSystemUser() {
		return tawSystemUser;
	}

	public void setTawSystemUser(TawSystemUser tawSystemUser) {
		this.tawSystemUser = tawSystemUser;
	}

	public String getMonthFlag() {
		return monthFlag;
	}

	public void setMonthFlag(String monthFlag) {
		this.monthFlag = monthFlag;
	}

	public String getYearFlag() {
		return yearFlag;
	}

	public void setYearFlag(String yearFlag) {
		this.yearFlag = yearFlag;
	}

	public int getAllNormalCount() {
		return allNormalCount;
	}

	public void setAllNormalCount(int allNormalCount) {
		this.allNormalCount = allNormalCount;
	}

	public int getDayNormalCount() {
		return dayNormalCount;
	}

	public void setDayNormalCount(int dayNormalCount) {
		this.dayNormalCount = dayNormalCount;
	}

	public int getFourmonthNormalCount() {
		return fourmonthNormalCount;
	}

	public void setFourmonthNormalCount(int fourmonthNormalCount) {
		this.fourmonthNormalCount = fourmonthNormalCount;
	}

	public int getHalfmonthNormalCount() {
		return halfmonthNormalCount;
	}

	public void setHalfmonthNormalCount(int halfmonthNormalCount) {
		this.halfmonthNormalCount = halfmonthNormalCount;
	}

	public int getHalfyearNormalCount() {
		return halfyearNormalCount;
	}

	public void setHalfyearNormalCount(int halfyearNormalCount) {
		this.halfyearNormalCount = halfyearNormalCount;
	}

	public int getMonthNormalCount() {
		return monthNormalCount;
	}

	public void setMonthNormalCount(int monthNormalCount) {
		this.monthNormalCount = monthNormalCount;
	}

	public int getQuarterNormalCount() {
		return quarterNormalCount;
	}

	public void setQuarterNormalCount(int quarterNormalCount) {
		this.quarterNormalCount = quarterNormalCount;
	}

	public int getTowmonthNormalCount() {
		return towmonthNormalCount;
	}

	public void setTowmonthNormalCount(int towmonthNormalCount) {
		this.towmonthNormalCount = towmonthNormalCount;
	}

	public int getWeekNormalCount() {
		return weekNormalCount;
	}

	public void setWeekNormalCount(int weekNormalCount) {
		this.weekNormalCount = weekNormalCount;
	}

	public int getYearNormalCount() {
		return yearNormalCount;
	}

	public void setYearNormalCount(int yearNormalCount) {
		this.yearNormalCount = yearNormalCount;
	}

	public int getTempNormalCount() {
		return tempNormalCount;
	}

	public void setTempNormalCount(int tempNormalCount) {
		this.tempNormalCount = tempNormalCount;
	}

	public int getQuarterOutTimeCount() {
		return quarterOutTimeCount;
	}

	public void setQuarterOutTimeCount(int quarterOutTimeCount) {
		this.quarterOutTimeCount = quarterOutTimeCount;
	}

	public int getTowmonthCount() {
		return towmonthCount;
	}
 
	 public String countPoints(double count, int allcount) {
		 double results;
		 double fullMarks=0.99;
		 double zero=0.95;
		 double bai=count/100;
		
//		 allcount=(int) (allcount*zero);
		 if(!"NaN".equals(String.valueOf(count))&& bai>zero){
		 results=(bai-zero)*2/(fullMarks-zero);
		 }
		 else{
			 results=0; 
		
		 }
		 if(StaticMethod.null2int(String.valueOf(results))>=2){
			 results=2;
		 }
		 
			return String.valueOf(results);
		}
 
	/* public String writeStatHTML(String yearFlag,String monthFlag, String deptId) {
			StringBuffer str = null;
			str = new StringBuffer();
			String url = "../tawwpstat/statyearall.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId;
			str.append("<tr class='tr_show'>");
			str.append("<td nowrap><a href='" + url + "'>" + this.getTawDept().getDeptName() + "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=1";
			str.append("<td ><a href='" + url + "'>" + this.getDayCount() + "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=1&executeFlag=1";
			str.append("<td ><a href='" + url + "'>" + this.getDayInTimeCount() + "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=1&executeFlag=0";
			str.append("<td ><a href='" + url + "'>"
					+ (this.getDayInTimeCount() + this.getDayOutTimeCount())
					+ "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=2";
			str.append("<td ><a href='" + url + "'>" + this.getWeekCount() + "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=2&executeFlag=1";
			str.append("<td ><a href='" + url + "'>" + this.getWeekInTimeCount() + "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=2&executeFlag=0";
			str.append("<td ><a href='" + url + "'>"
					+ (this.getWeekInTimeCount() + this.getWeekOutTimeCount())
					+ "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=3";
			str.append("<td ><a href='" + url + "'>" + this.getHalfmonthCount() + "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=3&executeFlag=1";
			str.append("<td ><a href='" + url + "'>" + this.getHalfmonthInTimeCount() + "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=3&executeFlag=0";
			str.append("<td ><a href='" + url + "'>"
					+ (this.getHalfmonthInTimeCount() + this
							.getHalfmonthOutTimeCount()) + "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=4";
			str.append("<td ><a href='" + url + "'>" + this.getMonthCount() + "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=4&executeFlag=1";
			str.append("<td ><a href='" + url + "'>" + this.getMonthInTimeCount() + "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=4&executeFlag=0";
			str.append("<td ><a href='" + url + "'>"
					+ (this.getMonthInTimeCount() + this.getMonthOutTimeCount())
					+ "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=5";
			str.append("<td ><a href='" + url + "'>"+ this.getQuarterCount() + "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=5&executeFlag=1";
			str.append("<td ><a href='" + url + "'>" + this.getQuarterInTimeCount() + "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=5&executeFlag=0";
			str
					.append("<td ><a href='" + url + "'>"
							+ (this.getQuarterInTimeCount() + this
									.getQuarterOutTimeCount()) + "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=6";
			str.append("<td ><a href='" + url + "'>" + this.getHalfyearCount() + "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=6&executeFlag=1";
			str.append("<td ><a href='" + url + "'>" + this.getHalfyearInTimeCount() + "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=6&executeFlag=0";
			str.append("<td ><a href='" + url + "'>"
					+ (this.getHalfyearInTimeCount() + this
							.getHalfyearOutTimeCount()) + "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=7";
			str.append("<td ><a href='" + url + "'>" + this.getYearCount() + "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=7&executeFlag=1";
			str.append("<td ><a href='" + url + "'>" + this.getYearInTimeCount() + "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=7&executeFlag=1";
			str.append("<td ><a href='" + url + "'>"
					+ (this.getYearInTimeCount() + this.getYearOutTimeCount())
					+ "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=0";
			str.append("<td ><a href='" + url + "'>" + this.getTempCount() + "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=0&executeFlag=1";
			str.append("<td ><a href='" + url + "'>" + this.getTempInTimeCount() + "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&cycle=0&executeFlag=0";
			str.append("<td ><a href='" + url + "'>"
					+ (this.getTempInTimeCount() + this.getTempOutTimeCount())
					+ "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId;
			str.append("<td ><a href='" + url + "'>" + this.getAllCount() + "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&executeFlag=1";
			str.append("<td ><a href='" + url + "'>" + this.getAllinTimeCount() + "</a></td>");
			url = "../tawwpstat/statyeardeptnew.do?yearflag=" + yearFlag
			+ "&monthflag=" + monthFlag + "&deptid="+deptId+"&executeFlag=0";
			str.append("<td ><a href='" + url + "'>"
					+ (this.getAllinTimeCount() + this.getAlloutTimeCount())
					+ "</a></td>");
			if (this.getAllCount() > 0) {
				str.append("<td >"
						+ ((((double) this.getAllinTimeCount() / (double) this
								.getAllCount()) * 100) + "00000").subSequence(0, 5)
						+ "%</td>");
				str.append("<td >"
						+ (((((double) this.getAllinTimeCount() + this
								.getAlloutTimeCount()) / (double) this
								.getAllCount()) * 100) + "00000").substring(0, 5)
						+ "%</td>");
			} else {
				str.append("<td >0%</td>");
				str.append("<td >0%</td>");
			}
			str.append("<td >100%</td>");
			String results=this.countPoints(((((double) this.getAllinTimeCount() + this
					.getAlloutTimeCount()) / (double) this
					.getAllCount()) * 100),getAllCount());
			str.append("<td >"+results+"</td>");
			
			str.append("</tr>");


			return str.toString();
		}
	 
	 public String countPoints(double count, int allcount) {
		 double results;
		 double fullMarks=0.99;
		 double zero=0.95;
		 double bai=count/100;
//		 allcount=(int) (allcount*zero);
		 if(!"NaN".equals(String.valueOf(count))&& bai>zero){
		 results=(bai-zero)*2/(fullMarks-zero);
		 }
		 else{
			 results=0; 
		
		 }
		 
			return String.valueOf(results);
		}*/
}
