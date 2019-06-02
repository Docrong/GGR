package com.boco.eoms.commons.statistic.base.excelutil.test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boco.eoms.commons.statistic.base.excelutil.mgr.impl.PoiExcelImpl;
import com.boco.eoms.commons.statistic.base.excelutil.sample.SamplePoiWork;

public class WriteFileServlet extends HttpServlet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("WriteFileServlet doGet");
		doPost(req,resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("WriteFileServlet doPost");
		String filepath = String.valueOf(req.getAttribute("filepath"));//upload\filename.xls
		filepath = req.getRealPath(filepath);
		PoiExcelImpl pe = new PoiExcelImpl();
		SamplePoiWork spw = new SamplePoiWork();
		
		if(filepath.lastIndexOf(".xls") == -1)
		{
			System.out.println("WriteFileServlet doPost 失败 只能解析xls文件");
			return;
		}
		
		//导入Excel为list
		List list = null;
		try {
			list = pe.importExcel(spw,filepath);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
//		for(int i=0;i<list.size();i++)
//		{
//			Object ob = list.get(o)
//		}
		
		System.out.println(list);
	}

}
