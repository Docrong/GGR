package krms;

public class AddKnowledgeServiceProxy implements krms.AddKnowledgeService {
  private boolean _useJNDI = true;
  private String _endpoint = null;
  private krms.AddKnowledgeService __addKnowledgeService = null;
  
  public AddKnowledgeServiceProxy() {
    _initAddKnowledgeServiceProxy();
  }
  
  private void _initAddKnowledgeServiceProxy() {
  
    if (_useJNDI) {
      try {
        javax.naming.InitialContext ctx = new javax.naming.InitialContext();
        __addKnowledgeService = ((krms.AddKnowledgeServiceService)ctx.lookup("java:comp/env/service/AddKnowledgeServiceService")).getKnowledgeService();
      }
      catch (javax.naming.NamingException namingException) {}
      catch (javax.xml.rpc.ServiceException serviceException) {}
    }
    if (__addKnowledgeService == null) {
      try {
        __addKnowledgeService = (new krms.AddKnowledgeServiceServiceLocator()).getKnowledgeService();
        
      }
      catch (javax.xml.rpc.ServiceException serviceException) {}
    }
    if (__addKnowledgeService != null) {
      if (_endpoint != null)
        ((javax.xml.rpc.Stub)__addKnowledgeService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
      else
        _endpoint = (String)((javax.xml.rpc.Stub)__addKnowledgeService)._getProperty("javax.xml.rpc.service.endpoint.address");
    }
    
  }
  
  
  public void useJNDI(boolean useJNDI) {
    _useJNDI = useJNDI;
    __addKnowledgeService = null;
    
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (__addKnowledgeService != null)
      ((javax.xml.rpc.Stub)__addKnowledgeService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public krms.AddKnowledgeService getAddKnowledgeService() {
    if (__addKnowledgeService == null)
      _initAddKnowledgeServiceProxy();
    return __addKnowledgeService;
  }
  
  public java.lang.String getKnowledgeBySheetIds(java.lang.String xmlStr) throws java.rmi.RemoteException{
    if (__addKnowledgeService == null)
      _initAddKnowledgeServiceProxy();
    return __addKnowledgeService.getKnowledgeBySheetIds(xmlStr);
  }
  
  public java.lang.String saveXmlValue(java.lang.String xmlStr) throws java.rmi.RemoteException{
    if (__addKnowledgeService == null)
      _initAddKnowledgeServiceProxy();
    return __addKnowledgeService.saveXmlValue(xmlStr);
  }
  
  
}