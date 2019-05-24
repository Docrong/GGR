package com.boco.eoms.sparepart.util;

import com.boco.eoms.sparepart.bo.TawRemindBO;
import com.boco.eoms.db.util.ConnectionPool;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class Remind
      extends TawRemindBO{

    public Remind(){
    }

    public Remind(ConnectionPool ds){
        super(ds);
    }

    public Remind(ConnectionPool ds,String str){
        super(ds,str);
    }
}