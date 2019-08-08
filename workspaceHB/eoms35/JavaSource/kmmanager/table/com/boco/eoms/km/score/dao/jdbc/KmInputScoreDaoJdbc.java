package com.boco.eoms.km.score.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;

import com.boco.eoms.base.dao.jdbc.BaseDaoJdbc;
import com.boco.eoms.km.score.dao.KmInputScoreDao;
import com.boco.eoms.km.score.model.KmInputScoreColumn;

public class KmInputScoreDaoJdbc extends BaseDaoJdbc implements KmInputScoreDao {

    /**
     * 根据条件分页查询知识导入积分
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回知识申请的分页列表
     */
    public Map getKmInputScores(final Integer curPage, final Integer pageSize,
                                final String whereStr) {
        ConnectionCallback callback = new ConnectionCallback() {
            public Object doInConnection(Connection conn) throws SQLException, DataAccessException {
                List results = null;

                return results;
            }
        };
        Object obj = this.getJdbcTemplate().execute(callback);
        return (Map) obj;
    }

    /**
     * 根据条件分页查询知识导入积分
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回知识申请的分页列表
     */
    public Map getKmInputScores(final Integer curPage, final Integer pageSize,
                                final String whereStr, final List column) {

        ConnectionCallback callback = new ConnectionCallback() {
            public Object doInConnection(Connection conn) throws SQLException, DataAccessException {
                PreparedStatement pstm = null;
                ResultSet rest = null;
                List scoreList = new ArrayList();
                int total = 0;
                HashMap map = new HashMap();
                String[] scoreColumn = new String[column.size()];
                String scoreSum = "0";
                for (int i = 0; i < column.size(); i++) {
                    KmInputScoreColumn kmInputScoreColumn = (KmInputScoreColumn) column.get(i);
                    scoreColumn[i] = kmInputScoreColumn.getColumnId();
                }
                try {
                    String sql = "select count(user_Id) from KM_INPUT_SCORE " + whereStr + " group by user_id,dept_id";
                    pstm = conn.prepareStatement(sql);
                    rest = pstm.executeQuery();
                    if (rest.next()) {
                        total = rest.getInt(1);
                    }
                    if (total > 0) {
                        StringBuffer scoreSql = new StringBuffer();
                        scoreSql.append("select user_id,dept_id ");
                        for (int i = 0; i < scoreColumn.length; i++) {
                            String sumStr = "sum(" + scoreColumn[i] + ") ";
                            scoreSql.append("," + sumStr + scoreColumn[i]);
                            scoreSum = scoreSum + "+" + sumStr;

                        }
                        if (!"0".equals(scoreSum)) {
                            scoreSql.append("," + scoreSum + " scoreSum");
                        }
                        scoreSql.append(" from KM_INPUT_SCORE ");
                        scoreSql.append(whereStr);
                        scoreSql.append(" group by user_id,dept_id");
//					String scoreSql = "select * from KM_INPUT_SCORE "+whereStr;
                        pstm = conn.prepareStatement(scoreSql.toString());
                        rest = pstm.executeQuery();
                        while (rest.next()) {
                            String[] score = new String[column.size() + 3];
//						score[0] = rest.getString("ID");
//						score[1] = rest.getString("CREATE_TIME");
//						score[2] = rest.getString("INPUT_USER_ID");
                            score[0] = rest.getString("USER_ID");
                            score[1] = rest.getString("DEPT_ID");
                            for (int i = 0; i < column.size(); i++) {
                                KmInputScoreColumn kmInputScoreColumn = (KmInputScoreColumn) column.get(i);
                                score[i + 2] = rest.getString(kmInputScoreColumn.getColumnId());
                            }
                            score[2 + column.size()] = rest.getString("scoreSum");
                            scoreList.add(score);
                        }
                    }
                    map.put("total", new Integer(total));
                    map.put("result", scoreList);
                    rest.close();
                    pstm.close();
                } catch (Exception e) {
                    rest.close();
                    pstm.close();
                } finally {
                    conn.close();
                }

                return map;
            }
        };
        Object obj = this.getJdbcTemplate().execute(callback);
        return (Map) obj;
    }

    /**
     * 根据条件分页查询知识导入积分
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回知识申请的分页列表
     */
    public Map getKmInputScoresHistory(final Integer curPage, final Integer pageSize,
                                       final String whereStr, final List column) {

        ConnectionCallback callback = new ConnectionCallback() {
            public Object doInConnection(Connection conn) throws SQLException, DataAccessException {
                PreparedStatement pstm = null;
                ResultSet rest = null;
                List scoreList = new ArrayList();
                int total = 0;
                HashMap map = new HashMap();
                String[] scoreColumn = new String[column.size()];
                String scoreSum = "0";
                for (int i = 0; i < column.size(); i++) {
                    KmInputScoreColumn kmInputScoreColumn = (KmInputScoreColumn) column.get(i);
                    scoreColumn[i] = kmInputScoreColumn.getColumnId();
                }
                try {
                    String sql = "select count(id) from KM_INPUT_SCORE " + whereStr + " ";
                    pstm = conn.prepareStatement(sql);
                    rest = pstm.executeQuery();
                    if (rest.next()) {
                        total = rest.getInt(1);
                    }
                    if (total > 0) {
                        StringBuffer scoreSql = new StringBuffer();
                        scoreSql.append("select ID,CREATE_TIME,INPUT_USER_ID,USER_ID,DEPT_ID");
                        for (int i = 0; i < scoreColumn.length; i++) {
                            scoreSql.append("," + scoreColumn[i]);

                        }
                        scoreSql.append(" from KM_INPUT_SCORE ");
                        scoreSql.append(whereStr);
//					String scoreSql = "select * from KM_INPUT_SCORE "+whereStr;
                        pstm = conn.prepareStatement(scoreSql.toString());
                        rest = pstm.executeQuery();
                        while (rest.next()) {
                            String[] score = new String[column.size() + 5];
                            score[0] = rest.getString("ID");
                            score[1] = rest.getString("CREATE_TIME");
                            score[2] = rest.getString("INPUT_USER_ID");
                            score[3] = rest.getString("USER_ID");
                            score[4] = rest.getString("DEPT_ID");
                            for (int i = 0; i < column.size(); i++) {
                                KmInputScoreColumn kmInputScoreColumn = (KmInputScoreColumn) column.get(i);
                                score[i + 5] = rest.getString(kmInputScoreColumn.getColumnId());
                            }
                            scoreList.add(score);
                        }
                    }
                    map.put("total", new Integer(total));
                    map.put("result", scoreList);
                    rest.close();
                    pstm.close();
                } catch (Exception e) {
                    rest.close();
                    pstm.close();
                    e.printStackTrace();
                } finally {
                    conn.close();
                }

                return map;
            }
        };
        Object obj = this.getJdbcTemplate().execute(callback);
        return (Map) obj;
    }

    /**
     * 取出导入积分类型集合
     *
     * @return 返回导入积分类型集合
     */
    public List getKmInputScoreColumns() {
        ConnectionCallback callback = new ConnectionCallback() {
            public Object doInConnection(Connection conn) throws SQLException, DataAccessException {
                PreparedStatement pstm = null;
                ResultSet rest = null;
                List columns = new ArrayList();
                String sql = "select * from KM_INPUT_SCORE_COLUMN where SHOW_FLAG='1' order by ORDER_FLAG";
                pstm = conn.prepareStatement(sql);
                rest = pstm.executeQuery();
                while (rest.next()) {
                    KmInputScoreColumn kmInputScoreColumn = new KmInputScoreColumn();
                    kmInputScoreColumn.setId(rest.getString("ID"));
                    kmInputScoreColumn.setColumnId(rest.getString("COLUMN_ID"));
                    kmInputScoreColumn.setColumnName(rest.getString("COLUMN_NAME"));
                    kmInputScoreColumn.setShowFlag(rest.getString("SHOW_FLAG"));
                    kmInputScoreColumn.setOrderFlag(rest.getString("ORDER_FLAG"));
                    columns.add(kmInputScoreColumn);
                }
                return columns;
            }
        };
        Object obj = this.getJdbcTemplate().execute(callback);
        return (List) obj;
    }
}
