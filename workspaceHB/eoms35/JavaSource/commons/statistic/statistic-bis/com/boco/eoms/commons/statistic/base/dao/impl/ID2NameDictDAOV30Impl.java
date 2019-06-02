package com.boco.eoms.commons.statistic.base.dao.impl;

import com.boco.eoms.commons.statistic.base.exception.Id2NameDAOException;

/**
 *
 * @author lizhenyou
 *
 */
public class ID2NameDictDAOV30Impl extends Id2NameBaseV30Impl {
  private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.
       ConnectionPool.getInstance();


        public String idType2Name(String id, String type) throws Id2NameDAOException {
//        com.boco.eoms.wsdict.dao.TawWsDictDAO tawWsDictDAO = new com.boco.eoms.wsdict.dao.TawWsDictDAO(ds);
//        String dictname = null;
//        try {
//          dictname = tawWsDictDAO.getDictName(Integer.parseInt(id),
//                                         Integer.parseInt(type), 0);
//        }
//        catch (NumberFormatException ex) {
//        }
//        catch (SQLException ex) {
//        }
//         return dictname;
        	return "";

        }
}
