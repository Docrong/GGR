/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.datum.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.util.MyBeanUtils;
import com.boco.eoms.datum.dao.ITawBureaudataBasicDAO;
import com.boco.eoms.datum.model.TawBureaudataBasic;
import com.boco.eoms.datum.model.TawBureaudataHlr;
import com.boco.eoms.datum.service.ITawBureaudataBasicDAOManager;
import com.boco.eoms.datum.vo.TawBureaudataSegmenthlrVO;
import com.boco.eoms.datum.webapp.form.TawBureaudataSHlrForExcel;
import com.ctc.wstx.dtd.StarModel;

/**
 * @author panlong
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TawBureaudataBasicDAOManagerImpl implements
		ITawBureaudataBasicDAOManager {

	private ITawBureaudataBasicDAO bureaudataBasicDAO;

	public ITawBureaudataBasicDAO getbureaudataBasicDAO() {
		return bureaudataBasicDAO;
	}

	public void setbureaudataBasicDAO(ITawBureaudataBasicDAO bureaudataBasicDAO) {
		this.bureaudataBasicDAO = bureaudataBasicDAO;
	}

	public Object getObject(Class clazz, Serializable id)
			throws HibernateException {
		return bureaudataBasicDAO.getObject(clazz, id);
	}
	
	public Set getAllBureaudataBasic() throws HibernateException {
		return bureaudataBasicDAO.getAllBureaudataBasic();
	}
	public List getObjectsByCondtion(Integer curPage, Integer pageSize,
			int[] aTotal, Map condition, String queryNumber)
			throws HibernateException {

		String hql = " from TawBureaudataBasic ";
		String countHql = "select count(*) from TawBureaudataBasic ";
		String where = (String) condition.get("where");
		hql = hql + where;
		countHql = countHql + where;
		return bureaudataBasicDAO.getObjectsByCondtion(curPage, pageSize,
				aTotal, hql, countHql, queryNumber);
	}

	public void removeObject(Class clazz, Serializable id)
			throws HibernateException {
		bureaudataBasicDAO.removeObject(clazz, id);

	}

	public void saveObject(Object o) throws HibernateException {
		bureaudataBasicDAO.saveObject(o);

	}

	public void saveOrUpdateAll(List list) throws HibernateException {
		bureaudataBasicDAO.saveOrUpdateAll(list);
	}

	public List getDepBureaudateInfoSheet(String sheetId)
			throws HibernateException {
		String hql = "select b.segmentid  from TawBureaudatasheetDetail de , TawBureaudataBasic b ,"
				+ " TawBureaudataHlr hlr  "
				+ "where de.segmentid=b.segmentid and hlr.hlrsignalid=de.hlrsignalid and  b.belongflag=1 and de.sheetid='"
				+ sheetId + "' order by de.segmentid,de.hlrsignalid ";
		List list = bureaudataBasicDAO.loadList(hql);
		List listHlr = new ArrayList();
		int beg = 0;
		int end = 0;
		// java.util.Iterator ite = list.iterator();
		int size = list.size();
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				Object obj = list.get(i);
				int tmp = Integer.parseInt(obj.toString());
				if (beg == 0) {
					beg = tmp;
					end = tmp;
				} else {
					if (tmp == end + 1) {
						end = tmp;
					} else {
						listHlr.add(new int[] { beg, end });
						beg = tmp;
						end = tmp;
					}
				}
			}
		}
		if (beg != 0) {
			listHlr.add(new int[] { beg, end });
		}
		return listHlr;
	}

	public List getDepBureaudateInfo(String deptid, int belongflag)
			throws HibernateException {
		String hql = "select b.segmentid from TawBureaudataBasic b ,TawBureaudataCityzone c  "
				+ "  where  b.zonenum = c.zonenum and c.deptid = '"+deptid+"' and b.belongflag = "
				+ belongflag
				+ " order by b.segmentid";
		List list = bureaudataBasicDAO.loadList(hql);
		List listHlr = new ArrayList();
		int beg = 0;
		int end = 0;
		// java.util.Iterator ite = list.iterator();
		int size = list.size();
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				Object obj = list.get(i);
				int tmp = Integer.parseInt(obj.toString());
				if (beg == 0) {
					beg = tmp;
					end = tmp;
				} else {
					if (tmp == end + 1) {
						end = tmp;
					} else {
						listHlr.add(new int[] { beg, end });
						beg = tmp;
						end = tmp;
					}
				}
			}
		}
		if (beg != 0) {
			listHlr.add(new int[] { beg, end });
		}

		return listHlr;

	}

	public List getBelongSegment(String deptid) throws HibernateException {
		List list = new ArrayList();
		String hql = "select b.segmentid, b.hlrsignalid, hlr.hlrname, hlr.hlrid ,b.prehlrsignalid ,hlr.hlrid ,b.prehlrsignalid "+
		" from TawBureaudataBasic b,TawBureaudataHlr hlr,TawBureaudataCityzone  c "+
		" where  hlr.hlrsignalid = b.hlrsignalid and b.zonenum =c.zonenum "+
		"and  b.belongflag = 1 and c.userid='"+deptid+"' order by b.segmentid, b.hlrsignalid";

		list = bureaudataBasicDAO.loadList(hql);

		return this.getBureaudataSegmenthlr(list);
	}

	public List getRelatedBelongsegment(String sheetId,String type)throws HibernateException {
		List list = new ArrayList();
//		String hql = "select b.segmentid, b.hlrsignalid, hlr.hlrname, hlr.hlrid,b.prehlrsignalid ,perhlr.hlrname,perhlr.hlrid from TawBureaudatasheetDetail de , TawBureaudataBasic b ,"
//				+ " TawBureaudataHlr hlr ,TawBureaudataHlr perhlr  "
//				+ "where de.segmentid=b.segmentid and hlr.hlrsignalid=de.hlrsignalid and perhlr.hlrsignalid=b.prehlrsignalid and  b.belongflag=1 and de.sheetid='"
//				+ sheetId + "' order by de.segmentid,de.hlrsignalid ";
		String hql="";
		if("新增".equals(type)){
			hql = "select b.segmentid, b.hlrsignalid, hlr.hlrname, hlr.hlrid,b.prehlrsignalid ,hlr.hlrid,b.prehlrsignalid from TawBureaudatasheetDetail de , TawBureaudataBasic b ,"
				+ " TawBureaudataHlr hlr  "
				+ "where de.segmentid=b.segmentid and de.hlrsignalid = hlr.hlrsignalid  and  b.belongflag=1 and de.sheetid='"
				+ sheetId + "' order by de.segmentid,de.hlrsignalid ";
			
		} else{
			hql = "select b.segmentid, b.hlrsignalid, hlr.hlrname, hlr.hlrid,b.prehlrsignalid ,perhlr.hlrname,perhlr.hlrid from TawBureaudatasheetDetail de , TawBureaudataBasic b ,"
				+ " TawBureaudataHlr hlr ,TawBureaudataHlr perhlr  "
				+ "where de.segmentid=b.segmentid and de.hlrsignalid = hlr.hlrsignalid and b.prehlrsignalid = perhlr.hlrsignalid and  b.belongflag=1 and de.sheetid='"
				+ sheetId + "' order by de.segmentid,de.hlrsignalid ";
			
		}
		list = bureaudataBasicDAO.loadList(hql);

		return this.getBureaudataSegmenthlr(list);
	}
	public void updatenew(String sheetId,String type)throws HibernateException{
		List list  = this.getRelatedBelongsegment(sheetId, type);
		if("新增".equals(type)){
			bureaudataBasicDAO.updateData(list, 0);
		}else{
			bureaudataBasicDAO.updateData(list, 1);
		}
	}
	public List getBureaudataSegmenthlr(List lists) throws HibernateException {
		List list = new ArrayList();
		int beg = 0;
		int end = 0;
		String preHlrId = "";
		String preHlrName = "";
		String preHlrSignalId = "";
		String prePerHlrSignalId = "";
		String prePerHlrName = "";
		String prePerHlrId = "";
		for (int i = 0; i < lists.size(); i++) {
			Object[] obj = (Object[]) lists.get(i);
			int tmp = Integer.parseInt(obj[0].toString());
			String tmpHlrId = StaticMethod.nullObject2String(obj[3],
					"空HLR");
			String tmpHlrName = StaticMethod.nullObject2String(obj[2],
					"空HLR");
			String tmpHlrSignalId = StaticMethod.nullObject2String(obj[1],
					"空HLR");
			String tmpPerHlrSignalId = StaticMethod.nullObject2String(obj[4],
			"空HLR");
			String tmpPerHlrName = StaticMethod.nullObject2String(obj[5],
			"空HLR");
			String tmpPerHlrId = StaticMethod.nullObject2String(obj[6],
			"空HLR");
			
			if (beg == 0) {
				beg = tmp;
				end = tmp;
			} else {
				if (tmp == end + 1 && tmpHlrSignalId.equals(preHlrSignalId)) {
					end = tmp;
				} else {
					if (list.size() > 0) {
						TawBureaudataSegmenthlrVO preSeg = (TawBureaudataSegmenthlrVO) list
								.get(list.size() - 1);
						if (beg == preSeg.getEndSegment() + 1) {
							preSeg.setAfterEven(true);
						}
					}
					TawBureaudataSegmenthlrVO seg = new TawBureaudataSegmenthlrVO();
					seg.setBeginSegment(beg);
					seg.setEndSegment(end);
					seg.setHlrId(preHlrId);
					seg.setHlrName(preHlrName);
					seg.setHlrSignalId(preHlrSignalId);
					seg.setPrehlrSignalId(prePerHlrSignalId);
					seg.setPrehlrName(prePerHlrName);
					seg.setPrehlrId(preHlrId);
					list.add(seg);
					beg = tmp;
					end = tmp;
				}
			}
			preHlrId = tmpHlrId;
			preHlrName = tmpHlrName;
			preHlrSignalId = tmpHlrSignalId;
			prePerHlrSignalId = tmpPerHlrSignalId;
			prePerHlrName = tmpPerHlrName;
			preHlrId = tmpPerHlrId;
		}
		if (beg != 0) {
			if (list.size() > 0) {
				TawBureaudataSegmenthlrVO preSeg = (TawBureaudataSegmenthlrVO) list
						.get(list.size() - 1);
				if (beg == preSeg.getEndSegment() + 1) {
					preSeg.setAfterEven(true);
				}
			}
			TawBureaudataSegmenthlrVO seg = new TawBureaudataSegmenthlrVO();
			seg.setBeginSegment(beg);
			seg.setEndSegment(end);
			seg.setHlrId(preHlrId);
			seg.setHlrName(preHlrName);
			seg.setHlrSignalId(preHlrSignalId);
			seg.setPrehlrSignalId(prePerHlrSignalId);
			seg.setPrehlrName(prePerHlrName);
			seg.setPrehlrId(preHlrId);
			list.add(seg);
		}
		return list;
	}

	public void updateBase(List list, int belongflag) throws HibernateException {
		bureaudataBasicDAO.saveOrUpdate(list, belongflag);
	}
	
	

	public List getBureaudataHlrAreaList(String where)
			throws HibernateException {
		String hql = "select  b.segmentid, hlr.hlrsignalid, hlr.hlrname, hlr.hlrid , c.areaname  from TawBureaudataBasic b ,TawBureaudataHlr hlr , TawSystemArea c where  hlr.hlrsignalid = b.hlrsignalid and b.zonenum = c.zonenum  "
				+ where;
		return getBureaudataSegmenthlrArea(bureaudataBasicDAO.loadList(hql));
	}

	public List getBureaudataSegmenthlrArea(List lists)
			throws HibernateException {
		List list = new ArrayList();
		int beg = 0;
		int end = 0;
		String preHlrId = "";
		String preHlrName = "";
		String preHlrSignalId = "";
		String preAreaname = "";
		for (int i = 0; i < lists.size(); i++) {
			Object[] obj = (Object[]) lists.get(i);
			int tmp = Integer.parseInt(obj[0].toString());
			String tmpHlrId = StaticMethod.null2String(obj[3].toString(),
					"空HLR");
			String tmpHlrName = StaticMethod.null2String(obj[2].toString(),
					"空HLR");
			String tmpHlrSignalId = StaticMethod.null2String(obj[1].toString(),
					"空HLR");
			String tmpAreaname = StaticMethod.null2String(obj[4].toString(),
					"空地市");
			if (beg == 0) {
				beg = tmp;
				end = tmp;
			} else {
				if (tmp == end + 1 && tmpHlrSignalId.equals(preHlrSignalId)) {
					end = tmp;
				} else {
					if (list.size() > 0) {
						TawBureaudataSegmenthlrVO preSeg = (TawBureaudataSegmenthlrVO) list
								.get(list.size() - 1);
						if (beg == preSeg.getEndSegment() + 1) {
							preSeg.setAfterEven(true);
						}
					}
					TawBureaudataSegmenthlrVO seg = new TawBureaudataSegmenthlrVO();
					seg.setBeginSegment(beg);
					seg.setEndSegment(end);
					seg.setHlrId(preHlrId);
					seg.setHlrName(preHlrName);
					seg.setHlrSignalId(preHlrSignalId);
					seg.setCityName(preAreaname);

					list.add(seg);
					beg = tmp;
					end = tmp;
				}
			}
			preHlrId = tmpHlrId;
			preHlrName = tmpHlrName;
			preHlrSignalId = tmpHlrSignalId;
			preAreaname = tmpAreaname;
		}
		if (beg != 0) {
			if (list.size() > 0) {
				TawBureaudataSegmenthlrVO preSeg = (TawBureaudataSegmenthlrVO) list
						.get(list.size() - 1);
				if (beg == preSeg.getEndSegment() + 1) {
					preSeg.setAfterEven(true);
				}
			}
			TawBureaudataSegmenthlrVO seg = new TawBureaudataSegmenthlrVO();
			seg.setBeginSegment(beg);
			seg.setEndSegment(end);
			seg.setHlrId(preHlrId);
			seg.setHlrName(preHlrName);
			seg.setHlrSignalId(preHlrSignalId);
			seg.setCityName(preAreaname);
			list.add(seg);
		}
		return list;
	}

	public List getAllTawBureaudata() throws HibernateException {
		List list = new ArrayList();
		List listbase = bureaudataBasicDAO.getAllTawBureaudata();
		// c.areaname, h.hlrname,h.hlrsignalid,b.segmentid, b.newbureauid,
		// b.adjustbureauid,b.bureadatasheetid
		if (listbase != null) {
			for (int i = 0; i < listbase.size(); i++) {
				TawBureaudataSHlrForExcel obj = new TawBureaudataSHlrForExcel();
				Object[] listob = (Object[]) listbase.get(i);
				obj.setCityName(StaticMethod.nullObject2String(listob[0], ""));
				obj.setHlrName(StaticMethod.nullObject2String(listob[1], ""));
				obj.setHlrSignalId(StaticMethod
						.nullObject2String(listob[2], ""));
				obj.setSegmentId(StaticMethod.nullObject2String(listob[3], ""));
				obj.setNewBureauId(StaticMethod
						.nullObject2String(listob[4], ""));
				obj.setAdjustBureauId(StaticMethod.nullObject2String(listob[5],
						""));
				obj.setBureaudataSheet(StaticMethod.nullObject2String(
						listob[6], ""));
				list.add(obj);
			}
		}
		return list;
	}

	public List getCityAdjustBureauDataForExcel(Map headSegMap, String bureauId)
			throws HibernateException {
		List list = new ArrayList();
		try {
			List bureauList = bureaudataBasicDAO.getCityAdjustBureauData(bureauId);
			Map excelMap = new LinkedHashMap();
			for(int i=0; i<bureauList.size(); i++){
			  TawBureaudataSegmenthlrVO seg = (TawBureaudataSegmenthlrVO)bureauList.get(i);

			  String headStr = String.valueOf(seg.getBeginSegment()).substring(0,4);
			  int index = ((Integer)headSegMap.get(headStr)).intValue();

			  String keyStr = seg.getPrezoneNum() + "|" +  seg.getZoneNum() + "|" + seg.getHlrSignalId();
			  TawBureaudataSHlrForExcel excelPO = (TawBureaudataSHlrForExcel)excelMap.get(keyStr);
			  if(excelPO == null){
			    excelPO = new TawBureaudataSHlrForExcel();
			    MyBeanUtils.copyPropertiesFromDBToPage(excelPO,seg);
			    excelPO.setSegArr(new String[headSegMap.size()]);
			    excelMap.put(keyStr,excelPO);
			  }
			  String[] segArr = excelPO.getSegArr();
			  if(segArr[index] == null){
			    segArr[index] = String.valueOf(seg.getBeginSegment()).substring(4);
			  }else{
			    segArr[index] = segArr[index] + "," + String.valueOf(seg.getBeginSegment()).substring(4);
			  }
			  if(seg.getBeginSegment() != seg.getEndSegment()){
			    segArr[index] = segArr[index] + "-" + String.valueOf(seg.getEndSegment()).substring(4);
			  }
			}
			Set set = excelMap.keySet();
			Iterator iter = set.iterator();
			while(iter.hasNext()){
			  list.add(excelMap.get(iter.next()));
			}
			return list;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private Map getHLRAdjustBureauDataIntoMap(List list)
			throws HibernateException {
		Map bureauMap = new LinkedHashMap();
		for (int i = 0; i < list.size(); i++) {
			TawBureaudataSegmenthlrVO seg = (TawBureaudataSegmenthlrVO) list
					.get(i);
			String bureaudataSheet = seg.getBureaudataSheet();
			List tmpList = (List) bureauMap.get(bureaudataSheet);
			if (tmpList == null) {
				tmpList = new ArrayList();
				bureauMap.put(bureaudataSheet, tmpList);
			}
			tmpList.add(seg);
		}
		return bureauMap;
	}

	public Map getHLRAdjustBureauDataForExcel(Map headSegMap)
			throws HibernateException {
		try {
			List bureauList = bureaudataBasicDAO.getHLRAdjustBureauData();
			Map bureauMap = this.getHLRAdjustBureauDataIntoMap(bureauList);
			Map returnMap = new LinkedHashMap();
			Iterator iter = bureauMap.keySet().iterator();
			while (iter.hasNext()) {
				Object key = iter.next();
				List tmpList = (List) bureauMap.get(key);
				Map excelMap = new LinkedHashMap();
				for (int i = 0; i < tmpList.size(); i++) {
					TawBureaudataSegmenthlrVO seg = (TawBureaudataSegmenthlrVO) tmpList
							.get(i);
					String headStr = String.valueOf(seg.getBeginSegment())
							.substring(0, 4);
					int index = ((Integer) headSegMap.get(headStr)).intValue();

					String keyStr = seg.getPrehlrSignalId() + "|"
							+ seg.getHlrSignalId() + "|" + seg.getZoneNum();
					TawBureaudataSHlrForExcel excelPO = (TawBureaudataSHlrForExcel) excelMap
							.get(keyStr);
					if (excelPO == null) {
						excelPO = new TawBureaudataSHlrForExcel();
						MyBeanUtils.copyPropertiesFromDBToPage(excelPO, seg);
						excelPO.setSegArr(new String[headSegMap.size()]);
						excelMap.put(keyStr, excelPO);
					}
					String[] segArr = excelPO.getSegArr();
					if (segArr[index] == null) {
						segArr[index] = String.valueOf(seg.getBeginSegment())
								.substring(4);
					} else {
						segArr[index] = segArr[index]
								+ ","
								+ String.valueOf(seg.getBeginSegment())
										.substring(4);
					}
					if (seg.getBeginSegment() != seg.getEndSegment()) {
						segArr[index] = segArr[index]
								+ "-"
								+ String.valueOf(seg.getEndSegment())
										.substring(4);
					}
				}
				List list = new ArrayList();
				Iterator tempIter = excelMap.keySet().iterator();
				while (tempIter.hasNext()) {
					list.add(excelMap.get(tempIter.next()));
				}
				returnMap.put(key, list);
			}
			return returnMap;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map getHeadsegMapForCityAdjust(String bureauId)
			throws HibernateException {
		return bureaudataBasicDAO.getHeadsegMapForCityAdjust(bureauId);
	}

	public Map getHeadsegMapForHLRAdjust() throws HibernateException {
		return bureaudataBasicDAO.getHeadsegMapForHLRAdjust();
	}

	public Map getHeadsegMapForNew(String bureauId) throws HibernateException {
		return bureaudataBasicDAO.getHeadsegMapForNew(bureauId);
	}

	public List getNewBureauDataForExcel(Map headSegMap, String bureauId)
			throws HibernateException {
		List list = new ArrayList();
		try {
			List bureauList = bureaudataBasicDAO.getNewBureauData(bureauId);
			Map excelMap = new LinkedHashMap();
			for(int i=0; i<bureauList.size(); i++){
			  TawBureaudataSegmenthlrVO seg = (TawBureaudataSegmenthlrVO)bureauList.get(i);

			  String headStr = String.valueOf(seg.getBeginSegment()).substring(0,4);
			  int index = ((Integer)headSegMap.get(headStr)).intValue();

			  String keyStr = seg.getPrezoneNum() + "|" +  seg.getZoneNum() + "|" + seg.getHlrSignalId();
			  TawBureaudataSHlrForExcel excelPO = (TawBureaudataSHlrForExcel)excelMap.get(keyStr);
			  if(excelPO == null){
			    excelPO = new TawBureaudataSHlrForExcel();
			    MyBeanUtils.copyPropertiesFromDBToPage(excelPO,seg);
			    excelPO.setSegArr(new String[headSegMap.size()]);
			    excelMap.put(keyStr,excelPO);
			  }
			  String[] segArr = excelPO.getSegArr();
			  if(segArr[index] == null){
			    segArr[index] = String.valueOf(seg.getBeginSegment()).substring(4);
			  }else{
			    segArr[index] = segArr[index] + "," + String.valueOf(seg.getBeginSegment()).substring(4);
			  }
			  if(seg.getBeginSegment() != seg.getEndSegment()){
			    segArr[index] = segArr[index] + "-" + String.valueOf(seg.getEndSegment()).substring(4);
			  }
			}
			Set set = excelMap.keySet();
			Iterator iter = set.iterator();
			while(iter.hasNext()){
			  list.add(excelMap.get(iter.next()));
			}
			return list;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
