package com.boco.eoms.portal.webservices.bo;

public class PortalServicePortTypeProxy implements com.boco.eoms.portal.webservices.bo.PortalServicePortType {
  private String _endpoint = null;
  private com.boco.eoms.portal.webservices.bo.PortalServicePortType portalServicePortType = null;
  
  public PortalServicePortTypeProxy() {
    _initPortalServicePortTypeProxy();
  }
  
  public PortalServicePortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initPortalServicePortTypeProxy();
  }
  
  private void _initPortalServicePortTypeProxy() {
    try {
      portalServicePortType = (new com.boco.eoms.portal.webservices.bo.PortalServiceLocator()).getPortalServiceHttpPort();
      if (portalServicePortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)portalServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)portalServicePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (portalServicePortType != null)
      ((javax.xml.rpc.Stub)portalServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.boco.eoms.portal.webservices.bo.PortalServicePortType getPortalServicePortType() {
    if (portalServicePortType == null)
      _initPortalServicePortTypeProxy();
    return portalServicePortType;
  }
  
  public java.lang.String[] getPortalRoleList() throws java.rmi.RemoteException{
    if (portalServicePortType == null)
      _initPortalServicePortTypeProxy();
    return portalServicePortType.getPortalRoleList();
  }
  
  public boolean saveOrUpdatePortalUser(org.light.portal.user.entity.PortalUser in0, java.lang.String in1) throws java.rmi.RemoteException{
    if (portalServicePortType == null)
      _initPortalServicePortTypeProxy();
    return portalServicePortType.saveOrUpdatePortalUser(in0, in1);
  }
  
  public boolean delPortalUser(java.lang.String in0) throws java.rmi.RemoteException{
    if (portalServicePortType == null)
      _initPortalServicePortTypeProxy();
    return portalServicePortType.delPortalUser(in0);
  }
  
  
}