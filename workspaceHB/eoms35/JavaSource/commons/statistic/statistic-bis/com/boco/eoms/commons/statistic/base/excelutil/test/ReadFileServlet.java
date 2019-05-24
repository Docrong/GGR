package com.boco.eoms.commons.statistic.base.excelutil.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boco.eoms.commons.statistic.base.excelutil.mgr.impl.PoiExcelImpl;
import com.boco.eoms.commons.statistic.base.excelutil.sample.SamplePoiWork;

public class ReadFileServlet extends HttpServlet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("ReadFileServlet doGet");
		doPost(req,resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("ReadFileServlet doPost");
		
		PoiExcelImpl pe = new PoiExcelImpl();
		SamplePoiWork spw = new SamplePoiWork();
		//导出Excel模板
		ByteArrayOutputStream baos = null;
		try {
			baos = (ByteArrayOutputStream)pe.exportExcel(spw);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		req.setAttribute("baos", baos);
		req.setAttribute("filename", "filename");
		req.getRequestDispatcher("streamdownload.jsp").forward(req, resp);
	}

}
