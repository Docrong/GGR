package com.boco.eoms.filemanager.topic;


import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: gaoc
 * Date: 2003-9-3
 * Time: 11:21:11
 * To change this template use Options | File Templates.
 */
public class LbSelectInfoTypeDetail extends LBDbLogic {

    public LbSelectInfoTypeDetail() {
        super();
    }

    public LbSelectInfoTypeDetail(Connection con) {
        super(con);
    }

    //设置sql文
    public void setSql() {
        m_strSql = "SELECT topic_id,par_topic_id,topic_name,topic_path,topic_memo,topic_order,class_id,topic_type,topic_type_name FROM taw_file_mgr_topic where topic_id= ? ";
    }

    //设置返回HashMap的key植
    public void setReturnParam() {
        String[] strArray = {
            "topic_id", "par_topic_id", "topic_name", "topic_path", "topic_memo", "topic_order", "class_id","topic_type","topic_type_name"
        };
        m_strArrayReturn = strArray;
    }

    public int executeProcess(String[] strParamArray) throws SQLException, Exception {
        return super.executeProcess(strParamArray);
    }
}
