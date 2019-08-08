package com.boco.eoms.sheet.nbproducts.dao;

import com.boco.eoms.base.dao.Dao;

public interface INBProductsJdbc extends Dao {

    //select maxcode
    public String getMaxCode();
}
