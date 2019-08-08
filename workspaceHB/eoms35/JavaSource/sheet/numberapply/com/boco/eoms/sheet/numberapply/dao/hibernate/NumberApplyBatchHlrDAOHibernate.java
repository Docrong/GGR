package com.boco.eoms.sheet.numberapply.dao.hibernate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.numberapply.dao.INumberApplyBatchHlrDAO;

public class NumberApplyBatchHlrDAOHibernate implements INumberApplyBatchHlrDAO {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List batchInsert(final String mainid, List columnValue) {

        List result = new ArrayList();

        for (int i = 0; i < columnValue.size(); i++) {
            final List row = (List) columnValue.get(i);

            try {
                jdbcTemplate.update("INSERT INTO NumberApply_HLRID (id,mainid,netName,netId,netProp,buildAddress,supplier,hardwareFlatRoof,softwareVersion,capability,commondLink,portCount,coverArea,areaNumber,city,deviceName,attachArea,fileNumber,isVaild) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        new PreparedStatementSetter() {

                            public void setValues(PreparedStatement stmt) throws SQLException {
                                try {
                                    stmt.setString(1, UUIDHexGenerator.getInstance().getID());

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                stmt.setString(2, mainid);
                                stmt.setString(3, (String) row.get(0));
                                stmt.setString(4, (String) row.get(1));
                                stmt.setString(5, (String) row.get(2));
                                stmt.setString(6, (String) row.get(3));
                                stmt.setString(7, (String) row.get(5));
                                stmt.setString(8, (String) row.get(6));
                                stmt.setString(9, (String) row.get(7));
                                stmt.setString(10, (String) row.get(8));
                                stmt.setString(11, (String) row.get(9));
                                stmt.setString(12, (String) row.get(10));
                                stmt.setString(13, (String) row.get(11));
                                stmt.setString(14, (String) row.get(12));
                                stmt.setString(15, (String) row.get(13));
                                stmt.setString(16, (String) row.get(14));
                                stmt.setString(17, (String) row.get(15));
                                stmt.setString(18, (String) row.get(16));
                                stmt.setString(19, "0");
                            }

                        });
            } catch (DataAccessException e) {
                e.printStackTrace();
                result.add(new Integer(i));
            }
        }
        return result;
    }

    public void batchPreUpdate(final String mainid) throws Exception {
        try {
//				jdbcTemplate.update("UPDATE  NumberApply_HLRID SET  isVaild = ? WHERE mainid = ? ", 
//						new PreparedStatementSetter() {
//							public void setValues(PreparedStatement stmt) throws SQLException {
//									stmt.setString(1,"1");
//									stmt.setString(2, mainid);
//							}
//				
//				});
            jdbcTemplate.update("UPDATE  NumberApply_HLRID SET  isVaild = 1 WHERE mainid = '" + mainid + "'");

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public void batchUpdate(List sql) throws Exception {
        for (int i = 0; i < sql.size(); i++) {
            try {
                jdbcTemplate.update((String) sql.get(i));

            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }
    }


}
