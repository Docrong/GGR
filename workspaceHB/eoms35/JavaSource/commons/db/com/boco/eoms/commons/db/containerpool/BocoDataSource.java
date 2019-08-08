/**
 * Title: com.boco.eoms.commons.db.containerpool.BocoDataSource Description:
 * self defined datasource, which implements java.sql.Connection interface.
 * Copyright: Copyright (c) 2007 Company: boco modify records: CONTENT DATE BY
 * WHO
 */
package com.boco.eoms.commons.db.containerpool;

// java standard library

import java.util.Properties;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

// java extend library
import javax.sql.DataSource;
import javax.naming.Context;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class BocoDataSource implements DataSource {

    protected String datasourceKey;

    protected DataSource dataSource;

    protected Properties params;

    public BocoDataSource() {
        datasourceKey = null;
        dataSource = null;
        params = new Properties();
    }

    public void setDatasourceKey(String value) {
        datasourceKey = value;
    }

    public String getDatasourceKey() {
        return datasourceKey;
    }

    public void setDataSource(DataSource objDataSource) {
        dataSource = objDataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("DataSource[");
        sb.append("datasourceKey=");
        sb.append(datasourceKey);
        sb.append(", dataSource=");
        sb.append(dataSource);
        sb.append("], ");
        sb.append(super.toString());
        return sb.toString();
    }

    public int getLoginTimeout() throws SQLException {
        return dataSource.getLoginTimeout();
    }

    public void setLoginTimeout(int arg0) throws SQLException {
        dataSource.setLoginTimeout(arg0);
    }

    public PrintWriter getLogWriter() throws SQLException {
        return dataSource.getLogWriter();
    }

    public void setLogWriter(PrintWriter arg0) throws SQLException {
        dataSource.setLogWriter(arg0);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public Connection getConnection(String username, String password)
            throws SQLException {
        throw new SQLException("Not Support This Interface!");
    }

    public void close() {
        if (dataSource != null) {
            dataSource = null;
        }
    }

    // setter and getter functions for container Context

    public void setAParam(boolean bCover, String strKey, String strValue) {
        if (!params.containsKey(strKey)) { // dont contain strKey
            params.put(strKey, strValue);
        } else {                             // contain strKey
            if (bCover) {   // override
                params.remove(strKey);
                params.put(strKey, strValue);
            } else {          // not override
                // do nothing
            }
        }
    }

    public void setAParam(String strKey, String strValue) {
        setAParam(true, strKey, strValue);
    }

    public String getAParam(String strKey) {
        String _strReturn = null;

        if (params.containsKey(strKey)) {
            _strReturn = (String) params.get(strKey);
        }
        return _strReturn;
    }

    public void setAPPLET(String value) {
        params.put(Context.APPLET, value);
    }

    public String getAPPLET() {
        return (String) params.get(Context.APPLET);
    }

    public void setAUTHORITATIVE(String value) {
        params.put(Context.AUTHORITATIVE, value);
    }

    public String getAUTHORITATIVE() {
        return (String) params.get(Context.AUTHORITATIVE);
    }

    public void setBATCHSIZE(String value) {
        params.put(Context.BATCHSIZE, value);
    }

    public String getBATCHSIZE() {
        return (String) params.get(Context.BATCHSIZE);
    }

    public void setDNS_URL(String value) {
        params.put(Context.DNS_URL, value);
    }

    public String getDNS_URL() {
        return (String) params.get(Context.DNS_URL);
    }

    public void setINITIAL_CONTEXT_FACTORY(String value) {
        params.put(Context.INITIAL_CONTEXT_FACTORY, value);
    }

    public String getINITIAL_CONTEXT_FACTORY() {
        return (String) params.get(Context.INITIAL_CONTEXT_FACTORY);
    }

    public void setLANGUAGE(String value) {
        params.put(Context.LANGUAGE, value);
    }

    public String getLANGUAGE() {
        return (String) params.get(Context.LANGUAGE);
    }

    public void setOBJECT_FACTORIES(String value) {
        params.put(Context.OBJECT_FACTORIES, value);
    }

    public String getOBJECT_FACTORIES() {
        return (String) params.get(Context.OBJECT_FACTORIES);
    }

    public void setPROVIDER_URL(String value) {
        params.put(Context.PROVIDER_URL, value);
    }

    public String getPROVIDER_URL() {
        return (String) params.get(Context.PROVIDER_URL);
    }

    public void setREFERRAL(String value) {
        params.put(Context.REFERRAL, value);
    }

    public String getREFERRAL() {
        return (String) params.get(Context.REFERRAL);
    }

    public void setSECURITY_AUTHENTICATION(String value) {
        params.put(Context.SECURITY_AUTHENTICATION, value);
    }

    public String getSECURITY_AUTHENTICATION() {
        return (String) params.get(Context.SECURITY_AUTHENTICATION);
    }

    public void setSECURITY_CREDENTIALS(String value) {
        params.put(Context.SECURITY_CREDENTIALS, value);
    }

    public String getSECURITY_CREDENTIALS() {
        return (String) params.get(Context.SECURITY_CREDENTIALS);
    }

    public void setSECURITY_PRINCIPAL(String value) {
        params.put(Context.SECURITY_PRINCIPAL, value);
    }

    public String getSECURITY_PRINCIPAL() {
        return (String) params.get(Context.SECURITY_PRINCIPAL);
    }

    public void setSECURITY_PROTOCOL(String value) {
        params.put(Context.SECURITY_PROTOCOL, value);
    }

    public String getSECURITY_PROTOCOL() {
        return (String) params.get(Context.SECURITY_PROTOCOL);
    }

    public void setSTATEF_ACTORIES(String value) {
        params.put(Context.STATE_FACTORIES, value);
    }

    public String getSTATE_FACTORIES() {
        return (String) params.get(Context.STATE_FACTORIES);
    }

    public void setURL_PKG_PREFIXES(String value) {
        params.put(Context.URL_PKG_PREFIXES, value);
    }

    public String getURL_PKG_PREFIXES() {
        return (String) params.get(Context.URL_PKG_PREFIXES);
    }

}
