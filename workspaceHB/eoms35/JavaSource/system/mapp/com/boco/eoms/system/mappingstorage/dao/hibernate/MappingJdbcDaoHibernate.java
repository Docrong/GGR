package com.boco.eoms.system.mappingstorage.dao.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.system.mappingstorage.dao.MappingJdbcDao;

public class MappingJdbcDaoHibernate extends JdbcDaoSupport implements MappingJdbcDao {

    //len是rootid对应的基点长度
    private static int len;
    String str = "";

    public String dictToName(String appcode, String sheetkey) {
        //通过appcode得到表名
        String tablename = "";
        String back = "";
        String temp = "";
        String rootid = "";
        List tablenamelist = new ArrayList();
        List namelist = new ArrayList();
        String sql = "select new_table from mappingstorage where app_code= '" + appcode + "'";
        tablenamelist = (List) getJdbcTemplate().queryForList(sql);
        if (tablenamelist != null && tablenamelist.size() > 0) {

            Iterator _objIterator = tablenamelist.iterator();
            while (_objIterator.hasNext()) {
                Map _objMap = (Map) _objIterator.next();
                tablename = StaticMethod
                        .nullObject2String(_objMap.get("new_table"));
                if (!tablename.equals("")) {
                    break;
                }
            }
            //从得到的表中得到dictid

            String sql1 = "select dictid from " + tablename + " where sheetkey= '" + sheetkey + "'";
            List dictlist = new ArrayList();
            dictlist = (List) getJdbcTemplate().queryForList(sql1);

            if (dictlist != null && dictlist.size() > 0) {
                List userDutydict = new ArrayList();
                Iterator _objIteratordict = dictlist.iterator();
                while (_objIteratordict.hasNext()) {
                    Map _objMap = (Map) _objIteratordict.next();
                    userDutydict.add(StaticMethod
                            .nullObject2String(_objMap.get("dictid")));
                }
                List rootidlist = new ArrayList();
                List userDutyrootid = new ArrayList();
                String sql2 = "select rootid from " + tablename + " where sheetkey= '" + sheetkey + "'";
                rootidlist = (List) getJdbcTemplate().queryForList(sql2);
                if (rootidlist != null && rootidlist.size() > 0) {
                    Iterator _objIteratorrootid = rootidlist.iterator();
                    while (_objIteratorrootid.hasNext()) {
                        Map _objMap = (Map) _objIteratorrootid.next();
                        rootid = StaticMethod
                                .nullObject2String(_objMap.get("rootid"));
                        if (!rootid.equals("")) {
                            break;
                        }
                    }
                    if (rootid != null) {
                        len = rootid.length() + 2;
                    }
                    String newStr = "";
                    for (int i = 0, j = userDutydict.size(); userDutydict != null && i < j; i++) {
                        newStr = dictIdToNamee((String) userDutydict.get(i));
                        temp += newStr.substring(0, newStr.length() - 1) + ",";
                        back = temp.substring(0, temp.length() - 1);

                    }
                }
            }
        }
        //return temp.substring(0,temp.length()-1);
        return back;

    }

    //递归调用id2name得到字符串
    public String dictIdToNamee(String id) {

        String beanid = "ItawSystemDictTypeDao";
        ID2NameService id2nameservice = (ID2NameService) ApplicationContextHolder.
                getInstance().getBean("id2nameService");
        int length = id.length();
        if (length == len) {
            return id2nameservice.id2Name(id, beanid) + "-";
        } else if (length < len + 2) {
            return "";
        } else {
            String newStr = id.substring(0, id.lastIndexOf(id.substring(length - 2)));
            return dictIdToNamee(newStr) + id2nameservice.id2Name(id, beanid) + "-";
        }
    }


    public void genTable(String tablename, String dbType) {
        StringBuffer sql = new StringBuffer();
        StringBuffer sql1 = new StringBuffer();

        sql.append("create table " + tablename);
        sql1.append("create index " + tablename + "_index on " + tablename);
        sql1.append(" (app_code)");
        if (dbType.equals("org.hibernate.dialect.OracleDialect")) {
            sql.append("(app_code varchar2(32)," +
                    "sheetKey varchar2(32),rootId varchar2(32),dictId varchar2(200),context varchar2(200) )");

        } else {

            sql.append("(app_code char(32),sheetkey char(10),rootId(20),dictId(100) )extent size 16 next size 16 lock mode row;");
        }
        getJdbcTemplate().execute(sql.toString());
        getJdbcTemplate().execute(sql1.toString());
    }


    public void insertValue(String appcode, String sheetKey, String rootId,
                            String dict) throws Exception {
        String tablename = "";
        List tablenamelist = new ArrayList();
        List namelist = new ArrayList();
        String sql = "select new_table from mappingstorage where app_code= '" + appcode + "'";
        tablenamelist = (List) getJdbcTemplate().queryForList(sql);
        Iterator _objIterator = tablenamelist.iterator();
        while (_objIterator.hasNext()) {

            Map _objMap = (Map) _objIterator.next();
            namelist.add(StaticMethod
                    .nullObject2String(_objMap.get("new_table")));
        }
        tablename = namelist.get(0).toString();
        String[] strArray = dict.split(",");
        for (int i = 0, j = strArray.length; i < j; i++) {
            System.out.println(tablename);
            StringBuffer sql1 = new StringBuffer();
            sql1.append("insert into " + tablename + "(app_code, sheetKey,rootId,dictId)");
            sql1.append(" values('" + appcode + "', '" + sheetKey + "','" + rootId + "','" + strArray[i] + "')");
            getJdbcTemplate().execute(sql1.toString());
        }

    }

}

