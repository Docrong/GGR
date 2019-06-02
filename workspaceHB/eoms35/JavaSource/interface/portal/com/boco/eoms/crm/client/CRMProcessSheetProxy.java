package com.boco.eoms.crm.client;

public class CRMProcessSheetProxy implements com.boco.eoms.crm.client.CRMProcessSheet {
  private boolean _useJNDI = true;
  private String _endpoint = null;
  private com.boco.eoms.crm.client.CRMProcessSheet __cRMProcessSheet = null;
  
  public CRMProcessSheetProxy() {
    _initCRMProcessSheetProxy();
  }
  
  private void _initCRMProcessSheetProxy() {
  
    if (_useJNDI) {
      try {
        javax.naming.InitialContext ctx = new javax.naming.InitialContext();
        __cRMProcessSheet = ((com.boco.eoms.crm.client.CRMProcessSheetService)ctx.lookup("java:comp/env/service/CRMProcessSheetService")).getCRMProcessSheet();
      }
      catch (javax.naming.NamingException namingException) {}
      catch (javax.xml.rpc.ServiceException serviceException) {}
    }
    if (__cRMProcessSheet == null) {
      try {
        __cRMProcessSheet = (new com.boco.eoms.crm.client.CRMProcessSheetServiceLocator()).getCRMProcessSheet();
        
      }
      catch (javax.xml.rpc.ServiceException serviceException) {}
    }
    if (__cRMProcessSheet != null) {
      if (_endpoint != null)
        ((javax.xml.rpc.Stub)__cRMProcessSheet)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
      else
        _endpoint = (String)((javax.xml.rpc.Stub)__cRMProcessSheet)._getProperty("javax.xml.rpc.service.endpoint.address");
    }
    
  }
  
  
  public void useJNDI(boolean useJNDI) {
    _useJNDI = useJNDI;
    __cRMProcessSheet = null;
    
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (__cRMProcessSheet != null)
      ((javax.xml.rpc.Stub)__cRMProcessSheet)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.boco.eoms.crm.client.CRMProcessSheet getCRMProcessSheet() {
    if (__cRMProcessSheet == null)
      _initCRMProcessSheetProxy();
    return __cRMProcessSheet;
  }
  
  public java.lang.String isAlive(java.lang.String serSupplier, java.lang.String callTime) throws java.rmi.RemoteException{
    if (__cRMProcessSheet == null)
      _initCRMProcessSheetProxy();
    return __cRMProcessSheet.isAlive(serSupplier, callTime);
  }
  
  public java.lang.String confirmWorkSheet(java.lang.Integer sheetType, java.lang.Integer serviceType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String opPerson, java.lang.String opCorp, java.lang.String opDepart, java.lang.String opContact, java.lang.String opTime, java.lang.String opDetail) throws java.rmi.RemoteException{
    if (__cRMProcessSheet == null)
      _initCRMProcessSheetProxy();
    return __cRMProcessSheet.confirmWorkSheet(sheetType, serviceType, serialNo, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
  }
  
  public java.lang.String notifyWorkSheet(java.lang.Integer sheetType, java.lang.Integer serviceType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String opPerson, java.lang.String opCorp, java.lang.String opDepart, java.lang.String opContact, java.lang.String opTime, java.lang.String opDetail) throws java.rmi.RemoteException{
    if (__cRMProcessSheet == null)
      _initCRMProcessSheetProxy();
    return __cRMProcessSheet.notifyWorkSheet(sheetType, serviceType, serialNo, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
  }
  
  public java.lang.String replyWorkSheet(java.lang.Integer sheetType, java.lang.Integer serviceType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String opPerson, java.lang.String opCorp, java.lang.String opDepart, java.lang.String opContact, java.lang.String opTime, java.lang.String opDetail) throws java.rmi.RemoteException{
    if (__cRMProcessSheet == null)
      _initCRMProcessSheetProxy();
    return __cRMProcessSheet.replyWorkSheet(sheetType, serviceType, serialNo, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
  }
  
  public java.lang.String withdrawWorkSheet(java.lang.Integer sheetType, java.lang.Integer serviceType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String opPerson, java.lang.String opCorp, java.lang.String opDepart, java.lang.String opContact, java.lang.String opTime, java.lang.String opDetail) throws java.rmi.RemoteException{
    if (__cRMProcessSheet == null)
      _initCRMProcessSheetProxy();
    return __cRMProcessSheet.withdrawWorkSheet(sheetType, serviceType, serialNo, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
  }
  
  
}