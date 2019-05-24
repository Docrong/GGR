package com.boco.eoms.autosheet.util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.Properties;
import java.util.Vector;
import org.apache.struts.util.LabelValueBean;

import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.duty.model.TawDutySheet;
import com.boco.eoms.autosheet.util.SheetAttribute;

public class Export extends DAO {
	public Export(com.boco.eoms.db.util.ConnectionPool ds) {
		super(ds);
	}

	public Export() {
		super();
	}

	public List getSheetListByWorkSerial(int id) {
		List retVector = new ArrayList();
		com.boco.eoms.db.util.BocoConnection conn = ds.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from taw_sheetattr where sheet_id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				SheetAttribute tawDutySheet = SheetAttribute.getInstance();
				 
				populate(tawDutySheet, rs);
				retVector.add(tawDutySheet);

				tawDutySheet = null;
			}
			close(rs);
			close(pstmt);
		} catch (SQLException e) {
			close(rs);
			close(pstmt);
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return retVector;
	}

	public static void main(String[] args) {
		 
		
		 
		for (int i=1;i<10;i++){
			for(int j=1;j<=i;j++){
			System.out.print(i+"*"+j+"="+i*j);
			System.out.print("  ");
			if(i==j){
			 System.out.println();	
			}
			}
			
		}
		 
	}
}
