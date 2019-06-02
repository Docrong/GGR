package com.chinamobile.eoms.service;

public class SheetStateSync3SoapProxy implements com.chinamobile.eoms.service.SheetStateSync3Soap {
  private boolean _useJNDI = true;
  private String _endpoint = null;
  private com.chinamobile.eoms.service.SheetStateSync3Soap __sheetStateSync3Soap = null;
  
  public SheetStateSync3SoapProxy() {
    _initSheetStateSync3SoapProxy();
  }
  
  private void _initSheetStateSync3SoapProxy() {
  
    if (_useJNDI) {
      try {
        javax.naming.InitialContext ctx = new javax.naming.InitialContext();
        __sheetStateSync3Soap = ((com.chinamobile.eoms.service.SheetStateSync3)ctx.lookup("java:comp/env/service/SheetStateSync3")).getSheetStateSync3Soap();
      }
      catch (javax.naming.NamingException namingException) {}
      catch (javax.xml.rpc.ServiceException serviceException) {}
    }
    if (__sheetStateSync3Soap == null) {
      try {
        __sheetStateSync3Soap = (new com.chinamobile.eoms.service.SheetStateSync3Locator()).getSheetStateSync3Soap();
        
      }
      catch (javax.xml.rpc.ServiceException serviceException) {}
    }
    if (__sheetStateSync3Soap != null) {
      if (_endpoint != null)
        ((javax.xml.rpc.Stub)__sheetStateSync3Soap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
      else
        _endpoint = (String)((javax.xml.rpc.Stub)__sheetStateSync3Soap)._getProperty("javax.xml.rpc.service.endpoint.address");
    }
    
  }
  
  
  public void useJNDI(boolean useJNDI) {
    _useJNDI = useJNDI;
    __sheetStateSync3Soap = null;
    
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (__sheetStateSync3Soap != null)
      ((javax.xml.rpc.Stub)__sheetStateSync3Soap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.chinamobile.eoms.service.SheetStateSync3Soap getSheetStateSync3Soap() {
    if (__sheetStateSync3Soap == null)
      _initSheetStateSync3SoapProxy();
    return __sheetStateSync3Soap;
  }
  
  public java.lang.String isAlive() throws java.rmi.RemoteException{
    if (__sheetStateSync3Soap == null)
      _initSheetStateSync3SoapProxy();
    return __sheetStateSync3Soap.isAlive();
  }
  
  public java.lang.String syncSheetState(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException{
    if (__sheetStateSync3Soap == null)
      _initSheetStateSync3SoapProxy();
    return __sheetStateSync3Soap.syncSheetState(serSupplier, serCaller, callerPwd, callTime, opDetail);
  }
  
  
}