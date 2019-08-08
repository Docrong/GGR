
package com.boco.eoms.extra.supplierkpi.dao;

import java.util.ArrayList;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiDict;

public interface TawSupplierkpiDictDao extends Dao {

    public void delDictByDictId(String dictId);

    public TawSupplierkpiDict getDictByDictId(String dictId);

    public ArrayList getDictSonsByDictId(String parentDictId);

    public boolean isHaveSameLevel(String parentdictid);

    public void saveTawSupplierkpiDict(TawSupplierkpiDict tawSupplierkpiDict);

    public void updateParentDictLeaf(String dictid, String leaf);

    public String getMaxDictId(String parentDictId, int length);

    public String saveDictReturnDictId(TawSupplierkpiDict tawSupplierkpiDict);

    public String id2Name(String dictId);
}

