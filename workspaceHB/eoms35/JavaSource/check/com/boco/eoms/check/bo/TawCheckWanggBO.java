package com.boco.eoms.check.bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.boco.eoms.check.bo.schedulerbo.TawCheckGSMSchedulerBO;
import com.boco.eoms.check.bo.schedulerbo.TawCheckCDMASchedulerBO;
import com.boco.eoms.check.dao.TawCheckDataDAO;
import com.boco.eoms.check.model.TawCheckGSMDATA;
import com.boco.eoms.check.model.TawCheckCDMADATA;
import com.boco.eoms.check.util.TawCheckConn;
import com.boco.eoms.check.util.TawCheckSCOREMethod;
import com.boco.eoms.common.bo.BO;

public class TawCheckWanggBO extends BO {

	String filePathName = "";

	String dateTime = "";

	Date localDate = null;

	String fileName = "";

	String scoreYear;

	String scoreMonth;

	public TawCheckWanggBO() {
		// TODO �Զ���ɹ��캯����
	}

	public void getGsmData(String start_time) {

		TawCheckConn hebconn = new TawCheckConn();
		Statement query = null;
		ResultSet rs = null;
		String compress_date = start_time.substring(0, 10);
		String sqlg = "select ne_id,'"
				+ start_time
				+ "' first_result,'"
				+ compress_date
				+ "' compress_date";
				//+"trunc(avg(g41),2) g41,trunc(avg(g42),2) g42,trunc(avg(g43),2) g43,trunc(avg(g49),2) g49,trunc(avg(g66),2) g66";
				String sqlg1="from eoms_check_gsm"
				+ " where first_result='" + start_time +"'";

		com.boco.eoms.db.util.BocoConnection connn = null;
		connn = ds.getConnection();
		PreparedStatement pstmt = null;

		String sql = "delete from taw_quality_data_gsm where first_result='"+ start_time +"'";
		try {
			pstmt = connn.prepareStatement(sql);
			pstmt.executeUpdate();
			connn.commit();
			System.out.println(sql);
			pstmt.close();
			connn.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			connn.close();
		}
		//by changhongxiang ��ȡ�����ֶ� begin
		List codeList=new ArrayList(0);
		List remarkList=new ArrayList(0);
		String sql1 = "select remark,code from taw_ws_dict where dict_type='17' and dict_id in(select dict_id_rel from taw_ws_dict_rel where dict_type_rel='17' and dict_id=1)";
		ResultSet rs1 = null;
		connn = ds.getConnection();
		try {
			pstmt = connn.prepareStatement(sql1);
			rs1 = pstmt.executeQuery();
			while (rs1.next()) {
				String remark = rs1.getString("remark");
				String code = rs1.getString("code");
				codeList.add(code);
				remarkList.add(remark);
				
			}
			pstmt.close();
			connn.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			connn.close();
		}
		String sqlg2="";
		for(int i=0;i<remarkList.size();i++){
			String remark=(String)remarkList.get(i);
			sqlg2+=",round("+remark+",2) "+remark+" ";
		}
		sqlg=sqlg+sqlg2+sqlg1;
		//by changhongxiang end
		
		
		Connection conn;

		try {
			conn = hebconn.getGsmConn();
			System.out.println("GSM��ݿ�l�ӳɹ���");
			query = conn.createStatement();
			rs = query.executeQuery(sqlg);
			while (rs.next()) {
				TawCheckDataDAO tawcheckdatadao = new TawCheckDataDAO();
				TawCheckGSMDATA tawcheckgsmdata = new TawCheckGSMDATA();
				TawCheckSCOREMethod tawCheckSCOREMethod = new TawCheckSCOREMethod();
				String ZH = tawCheckSCOREMethod.getZhLaber(rs.getInt("ne_id"));
				java.util.Date currentDate = new java.util.Date();

				tawcheckgsmdata.setFirst_result(rs.getDate("first_result"));
				tawcheckgsmdata.setCompress_date(rs.getDate("compress_date"));
				tawcheckgsmdata.setNe_id(rs.getInt("ne_id"));
				tawcheckgsmdata.setNe_type(10003);
				for(int i=0;i<remarkList.size();i++){
					String remark=(String)remarkList.get(i);
					String code=(String)codeList.get(i);
					tawcheckgsmdata
					.setNameValue(code, rs.getDouble(remark));
				}
				// tawcheckgsmdata.setG42(rs.getDouble("g41"));
				// tawcheckgsmdata.setG43(rs.getDouble("g42"));
				// tawcheckgsmdata.setG45(rs.getDouble("g43"));
				// tawcheckgsmdata.setG49(rs.getDouble("g49"));
				// tawcheckgsmdata.setG411(rs.getDouble("g66"));
				tawcheckgsmdata.setNe_zh(ZH);
				tawcheckgsmdata.setProvince_id(1047933115);
				tawcheckgsmdata.setProvince_zh("�ӱ�ʡ");
				tawcheckgsmdata.setRegion_id(rs.getInt("ne_id"));
				tawcheckgsmdata.setRegion_zh(ZH);
				tawcheckgsmdata.setDeleted("0");
				tawcheckgsmdata.setInsert_time(currentDate);

				tawcheckdatadao.save(tawcheckgsmdata);
				// insert(ne_id,ne_type,sys_link,cs_sys_link,an_sys_link,tel_drop,suc_pages,insert_time);

			}
			rs.close();
			query.close();
			conn.close();
			System.out.println("GSM��ݿ�l����ر�");
		} catch (SQLException exce) {
			System.out.println("Caught: " + exce.getErrorCode());
		}
		//���
		 TawCheckGSMSchedulerBO tawCheckGSMSchedulerBO=new TawCheckGSMSchedulerBO();
	      tawCheckGSMSchedulerBO.getMothScore(start_time.substring(0,10));
	}

	public void getCdmaData(String start_time,double ppp) {

		TawCheckConn hebconn = new TawCheckConn();
		Statement query = null;
		ResultSet rs = null;

		String compress_date = start_time.substring(0, 10);
		String sqlc = "select ne_id,'"
			+ start_time
			+ "' first_result,'"
			+ compress_date
			+ "' compress_date";
				//+ "' compress_date,trunc(avg(c41),2) c41,trunc(avg(c42),2) c42,trunc(avg(c43),2) c43,trunc(avg(c49),2) c49,trunc(avg(c410),2) c410,trunc(avg(c65),2) c65,trunc(avg(c66),2) c66 from eoms_check_cdma"
				String sqlg1="from eoms_check_cdma"
					+ " where first_result='" + start_time +"'";
		Connection conn;

		com.boco.eoms.db.util.BocoConnection connn = null;
		connn = ds.getConnection();
		PreparedStatement pstmt = null;

		String sql = "delete from taw_quality_data_cdma where first_result='"+ start_time +"'";
		try {
			pstmt = connn.prepareStatement(sql);
			pstmt.executeUpdate();
			connn.commit();
			System.out.println(sql);
			pstmt.close();
			connn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			connn.close();
		}
		//by changhongxiang ��ȡ�����ֶ� begin
		List codeList=new ArrayList(0);
		List remarkList=new ArrayList(0);
		String sql1 = "select remark,code from taw_ws_dict where dict_type='17' and dict_id in(select dict_id_rel from taw_ws_dict_rel where dict_type_rel='17' and dict_id=2)";
		ResultSet rs1 = null;
		connn = ds.getConnection();
		try {
			pstmt = connn.prepareStatement(sql1);
			rs1 = pstmt.executeQuery();
			while (rs1.next()) {
				String remark = rs1.getString("remark");
				String code = rs1.getString("code");
				codeList.add(code);
				remarkList.add(remark);
				
			}
			pstmt.close();
			connn.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			connn.close();
		}
		String sqlg2="";
		for(int i=0;i<remarkList.size();i++){
			String remark=(String)remarkList.get(i);
			sqlg2+=",round("+remark+",2) "+remark+" ";
		}
		sqlc=sqlc+sqlg2+sqlg1;
		//by changhongxiang end
		try {
			conn = hebconn.getCdmaConn();
			System.out.println("CDMA��ݿ�l�ӳɹ���");
			query = conn.createStatement();
			rs = query.executeQuery(sqlc);
			while (rs.next()) {
				TawCheckDataDAO tawcheckdatadao = new TawCheckDataDAO();

				TawCheckCDMADATA tawcheckcdmadata = new TawCheckCDMADATA();
				TawCheckSCOREMethod tawCheckSCOREMethod = new TawCheckSCOREMethod();
				String ZH = tawCheckSCOREMethod.getZhLaber(rs.getInt("ne_id"));
				java.util.Date currentDate = new java.util.Date();

				tawcheckcdmadata.setFirst_result(rs.getDate("first_result"));
				tawcheckcdmadata.setCompress_date(rs.getDate("compress_date"));
				tawcheckcdmadata.setNe_id(rs.getInt("ne_id"));
				tawcheckcdmadata.setNe_type(10003);
				for(int i=0;i<remarkList.size();i++){
					String remark=(String)remarkList.get(i);
					String code=(String)codeList.get(i);
					if(code.equalsIgnoreCase("VIR_P26")){
						tawcheckcdmadata.setVir_p26(ppp);
					}else{
					tawcheckcdmadata
					.setNameValue(code, rs.getDouble(remark));
					}
				}
				/*tawcheckcdmadata.setC42(rs.getDouble("c41"));
				tawcheckcdmadata.setC43(rs.getDouble("c42"));
				tawcheckcdmadata.setC45(rs.getDouble("c43"));
				tawcheckcdmadata.setC411(rs.getDouble("c49"));
				tawcheckcdmadata.setC66(rs.getDouble("c66"));*/
				tawcheckcdmadata.setNe_zh(ZH);
				tawcheckcdmadata.setProvince_id(1047933115);
				tawcheckcdmadata.setProvince_zh("�ӱ�ʡ");
				tawcheckcdmadata.setRegion_id(rs.getInt("ne_id"));
				tawcheckcdmadata.setRegion_zh(ZH);
				tawcheckcdmadata.setDeleted("0");
				tawcheckcdmadata.setInsert_time(currentDate);

				tawcheckdatadao.save(tawcheckcdmadata);
			}
			rs.close();
			query.close();
			conn.close();
			System.out.println("CDMA��ݿ�l����ر�");
		} catch (SQLException exce) {
			System.out.println("Caught: " + exce.getErrorCode());
		}
		//���
		 TawCheckCDMASchedulerBO tawCheckCDMASchedulerBO=new TawCheckCDMASchedulerBO();
	      tawCheckCDMASchedulerBO.getMothScore(start_time.substring(0,10));
	}
}
