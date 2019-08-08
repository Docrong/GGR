package krms;

public class SynchroniseUserServiceProxy implements krms.SynchroniseUserService {
    private boolean _useJNDI = true;
    private String _endpoint = null;
    private krms.SynchroniseUserService __synchroniseUserService = null;

    public SynchroniseUserServiceProxy() {
        _initSynchroniseUserServiceProxy();
    }

    private void _initSynchroniseUserServiceProxy() {

        if (_useJNDI) {
            try {
                javax.naming.InitialContext ctx = new javax.naming.InitialContext();
                __synchroniseUserService = ((krms.SynchroniseUserServiceService) ctx.lookup("java:comp/env/service/SynchroniseUserServiceService")).getSynUserService();
            } catch (javax.naming.NamingException namingException) {
            } catch (javax.xml.rpc.ServiceException serviceException) {
            }
        }
        if (__synchroniseUserService == null) {
            try {
                __synchroniseUserService = (new krms.SynchroniseUserServiceServiceLocator()).getSynUserService();

            } catch (javax.xml.rpc.ServiceException serviceException) {
            }
        }
        if (__synchroniseUserService != null) {
            if (_endpoint != null)
                ((javax.xml.rpc.Stub) __synchroniseUserService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
            else
                _endpoint = (String) ((javax.xml.rpc.Stub) __synchroniseUserService)._getProperty("javax.xml.rpc.service.endpoint.address");
        }

    }


    public void useJNDI(boolean useJNDI) {
        _useJNDI = useJNDI;
        __synchroniseUserService = null;

    }

    public String getEndpoint() {
        return _endpoint;
    }

    public void setEndpoint(String endpoint) {
        _endpoint = endpoint;
        if (__synchroniseUserService != null)
            ((javax.xml.rpc.Stub) __synchroniseUserService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

    }

    public krms.SynchroniseUserService getSynchroniseUserService() {
        if (__synchroniseUserService == null)
            _initSynchroniseUserServiceProxy();
        return __synchroniseUserService;
    }

    public void synchroniseUser() throws java.rmi.RemoteException {
        if (__synchroniseUserService == null)
            _initSynchroniseUserServiceProxy();
        __synchroniseUserService.synchroniseUser();
    }


}