package com.boco.eoms.interfaces.EOMSService.client;

public class IAttempXToEomsProxy implements com.boco.eoms.interfaces.EOMSService.client.IAttempXToEoms {
  private boolean _useJNDI = true;
  private String _endpoint = null;
  private com.boco.eoms.interfaces.EOMSService.client.IAttempXToEoms __iAttempXToEoms = null;
  
  public IAttempXToEomsProxy() {
    _initIAttempXToEomsProxy();
  }
  
  private void _initIAttempXToEomsProxy() {
  
    if (_useJNDI) {
      try {
        javax.naming.InitialContext ctx = new javax.naming.InitialContext();
        __iAttempXToEoms = ((com.boco.eoms.interfaces.EOMSService.client.IRMSService)ctx.lookup("java:comp/env/service/IRMSService")).getIRMSService();
      }
      catch (javax.naming.NamingException namingException) {}
      catch (javax.xml.rpc.ServiceException serviceException) {}
    }
    if (__iAttempXToEoms == null) {
      try {
        __iAttempXToEoms = (new com.boco.eoms.interfaces.EOMSService.client.IRMSServiceLocator()).getIRMSService();
        
      }
      catch (javax.xml.rpc.ServiceException serviceException) {}
    }
    if (__iAttempXToEoms != null) {
      if (_endpoint != null)
        ((javax.xml.rpc.Stub)__iAttempXToEoms)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
      else
        _endpoint = (String)((javax.xml.rpc.Stub)__iAttempXToEoms)._getProperty("javax.xml.rpc.service.endpoint.address");
    }
    
  }
  
  
  public void useJNDI(boolean useJNDI) {
    _useJNDI = useJNDI;
    __iAttempXToEoms = null;
    
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (__iAttempXToEoms != null)
      ((javax.xml.rpc.Stub)__iAttempXToEoms)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.boco.eoms.interfaces.EOMSService.client.IAttempXToEoms getIAttempXToEoms() {
    if (__iAttempXToEoms == null)
      _initIAttempXToEomsProxy();
    return __iAttempXToEoms;
  }
  
  public java.lang.String submitReplySheet(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException{
    if (__iAttempXToEoms == null)
      _initIAttempXToEomsProxy();
    return __iAttempXToEoms.submitReplySheet(serSupplier, serCaller, callerPwd, callTime, opDetail);
  }
  
  public java.lang.String putBusinessData(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException{
    if (__iAttempXToEoms == null)
      _initIAttempXToEomsProxy();
    return __iAttempXToEoms.putBusinessData(serSupplier, serCaller, callerPwd, callTime, opDetail);
  }
  
  public java.lang.String deleteSheet(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException{
    if (__iAttempXToEoms == null)
      _initIAttempXToEomsProxy();
    return __iAttempXToEoms.deleteSheet(serSupplier, serCaller, callerPwd, callTime, opDetail);
  }
  
  public java.lang.String setCheck(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException{
    if (__iAttempXToEoms == null)
      _initIAttempXToEomsProxy();
    return __iAttempXToEoms.setCheck(serSupplier, serCaller, callerPwd, callTime, opDetail);
  }
  
  public java.lang.String getDeptIds(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException{
    if (__iAttempXToEoms == null)
      _initIAttempXToEomsProxy();
    return __iAttempXToEoms.getDeptIds(serSupplier, serCaller, callerPwd, callTime, opDetail);
  }
  
  public java.lang.String getExcelData(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException{
    if (__iAttempXToEoms == null)
      _initIAttempXToEomsProxy();
    return __iAttempXToEoms.getExcelData(serSupplier, serCaller, callerPwd, callTime, opDetail);
  }
  
  
}