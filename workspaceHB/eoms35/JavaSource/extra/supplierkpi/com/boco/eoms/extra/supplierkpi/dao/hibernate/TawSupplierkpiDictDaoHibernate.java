package com.boco.eoms.extra.supplierkpi.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiDictDao;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiDict;
import com.boco.eoms.extra.supplierkpi.util.TawSupplierkpiDictUtil;

public class TawSupplierkpiDictDaoHibernate extends BaseDaoHibernate implements
        TawSupplierkpiDictDao, ID2NameDAO {

    public void delDictByDictId(String dictId) {
        TawSupplierkpiDict tawSupplierkpiDict = getDictByDictId(dictId);
        getHibernateTemplate().delete(tawSupplierkpiDict);
    }

    public TawSupplierkpiDict getDictByDictId(String dictId) {
        String hql = " from TawSupplierkpiDict dict where dict.dictId='"
                + dictId + "'";
        return (TawSupplierkpiDict) getHibernateTemplate().find(hql).get(0);
    }

    public ArrayList getDictSonsByDictId(String parentDictId) {
        String hql = " from TawSupplierkpiDict dict where dict.parentDictId='"
                + parentDictId + "' order by dict.dictId";
        return (ArrayList) getHibernateTemplate().find(hql);
    }

    //生成最小的可用dictId,返回当前最小的未被占用的dictId liqiuye 20080903
    public String getMaxDictId(final String parentDictId, final int length) {
//		String maxDictId = "";
//		HibernateCallback callback = new HibernateCallback() {
//			public Object doInHibernate(Session session)
//					throws HibernateException {
//				String hql = " select max(dict.dictId) from TawSupplierkpiDict dict where dict.dictId like '"
//						+ parentDictId
//						+ "%' and length(dict.dictId)='"
//						+ length + "'";
//				Query query = session.createQuery(hql);
//				List result = query.list();
//				Iterator iter = result.iterator();
//				if (!iter.hasNext()) {
//					return TawSupplierkpiDictUtil.DICTID_DEFULT_VALUE;
//				} else {
//					Object[] obj = (Object[]) iter.next();
//					if (null == obj) {
//						return TawSupplierkpiDictUtil.DICTID_DEFULT_VALUE;
//					} else {
//						return StaticMethod.nullObject2String(obj[0]);
//					}
//				}
//			}
//		};
//		maxDictId = getHibernateTemplate().execute(callback).toString();
//		return maxDictId;
        String minDictId = getUsableMinDictId(parentDictId, length);
        if ((minDictId.equals("") || minDictId.equals("null"))) {
            minDictId = TawSupplierkpiDictUtil.DICTID_DEFULT_VALUE;
        }
        return minDictId;
    }

    public boolean isHaveSameLevel(String parentDictId) {
        boolean flag = false;
        String hql = " from TawSupplierkpiDict dict where dict.parentDictId='"
                + parentDictId + "'";
        List list = getHibernateTemplate().find(hql);
        if (list != null && list.size() > 0) {
            flag = true;
        }
        return flag;
    }

    public void saveTawSupplierkpiDict(TawSupplierkpiDict tawSupplierkpiDict) {
        if (tawSupplierkpiDict.getId() == null
                || "".equals(tawSupplierkpiDict.getId())) {
            getHibernateTemplate().save(tawSupplierkpiDict);
        } else {
            getHibernateTemplate().saveOrUpdate(tawSupplierkpiDict);
        }
    }

    public void updateParentDictLeaf(String dictId, String leaf) {
        TawSupplierkpiDict dict = getDictByDictId(dictId);
        if (dict != null) {
            dict.setLeaf(leaf);
        }
        saveTawSupplierkpiDict(dict);
    }

    public String saveDictReturnDictId(TawSupplierkpiDict tawSupplierkpiDict) {
        if ((tawSupplierkpiDict.getId() == null)
                || (tawSupplierkpiDict.getId().equals("")))
            getHibernateTemplate().save(tawSupplierkpiDict);
        else
            getHibernateTemplate().saveOrUpdate(tawSupplierkpiDict);

        return tawSupplierkpiDict.getDictId();
    }

    public String id2Name(String dictId) {
        String dictName = "";
        TawSupplierkpiDict tawSupplierkpiDict = getDictByDictId(dictId);
        if (null != tawSupplierkpiDict) {
            dictName = tawSupplierkpiDict.getDictName();
        } else {
            dictName = TawSupplierkpiDictUtil.DICTNAME_DEFULT_STRINGVAR;
        }
        return dictName;
    }

    /**
     * 生成最小的可用dictId,返回当前最小的未被占用的dictId liqiuye 20080903
     *
     * @param parentDictId
     * @param len
     */
    public String getUsableMinDictId(final String parentDictId, final int len) {
        String minUsableDictId = "";
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String hql = " select distinct(dictId) from TawSupplierkpiDict where dictId like '"
                        + parentDictId + "%' and length(dictId)='" + len + "'";
                Query query = session.createQuery(hql);
                return query.list();
            }
        };
        List list = (List) getHibernateTemplate().execute(callback);
        HashMap hm = new HashMap();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            String dictId = it.next().toString();
            hm.put(dictId, dictId);
        }
        //防止下面的while出现死循环,如果所有Id全部被占用则返回一个空值
        if (hm.size() >= Integer.parseInt(TawSupplierkpiDictUtil.DICTID_IF_MAXID)) {
            return minUsableDictId;
        }
        long dictIdVar = Long.valueOf(parentDictId + TawSupplierkpiDictUtil.DICTID_NOSON).longValue();
        while (null != hm.get(String.valueOf(dictIdVar))) {
            dictIdVar = dictIdVar + 1;
        }
        return String.valueOf(dictIdVar);
    }
}
