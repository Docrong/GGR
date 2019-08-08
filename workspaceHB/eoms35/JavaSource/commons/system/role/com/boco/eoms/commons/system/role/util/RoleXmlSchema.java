/*
 * Created on 2007-9-5
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.system.role.util;

import java.util.Iterator;

import com.boco.eoms.commons.fileconfig.exception.ParseXMLException;
import com.boco.eoms.commons.fileconfig.service.impl.ParseXmlService;

/**
 * @author IBM
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RoleXmlSchema {
    private static SubRoleSchema nodes = null;
    private static RoleXmlSchema roleXmlSchema = null;

    public static RoleXmlSchema getInstance() throws Exception {
        if (roleXmlSchema == null) {
            roleXmlSchema = new RoleXmlSchema();
            try {
                loadXml();
            } catch (Exception e) {
                throw e;
            }
        }
        return roleXmlSchema;
    }

    public RoleXmlSchema() {
    }

    private static void loadXml() throws Exception {
        try {
            nodes = (SubRoleSchema) ParseXmlService
                    .create()
                    .xml2object(SubRoleSchema.class, "classpath:com/boco/eoms/commons/system/role/util/SubRoleMapping.xml",
                            "classpath:config/eomsRoleSchema.xml", false);

        } catch (ParseXMLException e) {
            e.printStackTrace();
            throw new Exception("读取配置文件'config/eomsRoleSchema.xml'出错");
        }
    }

    public String getColumnName(String businessName) {
        if (nodes != null) {
            for (Iterator it = nodes.getSubRoleMapping().iterator(); it.hasNext(); ) {
                SubRoleMapping rm = (SubRoleMapping) it.next();
                if (businessName.equals(rm.getBusinessName()))
                    return rm.getColumnName();
            }
        }
        return null;

    }

    public String getBusinessName(String columnName) {
        if (nodes != null) {
            for (Iterator it = nodes.getSubRoleMapping().iterator(); it.hasNext(); ) {
                SubRoleMapping rm = (SubRoleMapping) it.next();
                if (columnName.equals(rm.getColumnName()))
                    return rm.getBusinessName();
            }
        }
        return null;
    }
}
