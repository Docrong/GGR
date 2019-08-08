package com.boco.eoms.sheet.nbproducts.dao.hibernate;

import com.boco.eoms.base.dao.jdbc.BaseDaoJdbc;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.nbproducts.dao.INBProductsJdbc;

public class NBProductsJdbcImpl extends BaseDaoJdbc implements INBProductsJdbc {

    public String getMaxCode() {
        String sql = " select max(code) as code from NBProducts";

        String maxcode = StaticMethod.nullObject2String(getJdbcTemplate().queryForMap(sql).get("code"));
        if (maxcode.equals("")) {
            maxcode = "0001";
        } else {
            int mcode = Integer.valueOf(maxcode).intValue();
            mcode += 1;
            maxcode = String.valueOf(mcode);
            int alength = maxcode.length();
            String str = "0";
            if (alength != 4) {
                for (int i = 1; i < 4 - alength; i++) {
                    str = str + "0";
                }
                maxcode = str + maxcode;
            }
        }
        return maxcode;
    }

}
