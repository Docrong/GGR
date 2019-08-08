package com.boco.eoms.system.mappingstorage.dao.hibernate;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.system.mappingstorage.model.Mapping;

public class Test {

    private static int len = 7;

    //private static List strList = null;//new ArrayList();

    private static String laomeng = "";

    /**
     * @param args
     */
    public static void main(String[] args) {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from Mapping mapping where mapping.app_code=netData";
                Query query = session.createQuery(queryStr);
                //query.setString("id", id);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (Mapping) result.iterator().next();
                } else {
                    System.out.println("0000000");
                    return new Mapping();

                }
            }
        };
        List dictlist = new ArrayList();
        String newStr = "";
        dictlist.add("10117010201");
        dictlist.add("1011701030101");
        dictlist.add("10117010201");
        dictlist.add("1011701030101");
        dictlist.add("1011701030101");
        dictlist.add("10117010201");
        dictlist.add("101010401");
        dictlist.add("101010402");


        List newList = new ArrayList();
        for (int i = 0, j = dictlist.size(); i < j; i++) {

            newStr = getNewStr((String) dictlist.get(i));
            newList.add(newStr.substring(0, newStr.length() - 1));


        }

        for (int i = 0, j = newList.size(); i < j; i++) {
            System.out.println("newStr: " + newList.get(i));
        }
    }

    public static String getNewStr(final String str) {
        String beanid = "ItawSystemDictTypeDao";
        ID2NameService id2nameservice = (ID2NameService) ApplicationContextHolder.
                getInstance().getBean("id2nameService");


        int length = str.length();
        if (length == len) {
            return id2nameservice.id2Name(str, beanid) + "-";
        } else if (length < len + 2) {
            return "";
        } else {
            String newStr = str.substring(0, str.lastIndexOf(str.substring(length - 2)));
            return getNewStr(newStr) + id2nameservice.id2Name(str, beanid) + "-";
        }
    }


}
