package krms;

public class UploadFileServiceProxy implements krms.UploadFileService {
    private boolean _useJNDI = true;
    private String _endpoint = null;
    private krms.UploadFileService __uploadFileService = null;

    public UploadFileServiceProxy() {
        _initUploadFileServiceProxy();
    }

    private void _initUploadFileServiceProxy() {

        if (_useJNDI) {
            try {
                javax.naming.InitialContext ctx = new javax.naming.InitialContext();
                __uploadFileService = ((krms.UploadFileServiceService) ctx.lookup("java:comp/env/service/UploadFileServiceService")).getUploadFileService();
            } catch (javax.naming.NamingException namingException) {
            } catch (javax.xml.rpc.ServiceException serviceException) {
            }
        }
        if (__uploadFileService == null) {
            try {
                __uploadFileService = (new krms.UploadFileServiceServiceLocator()).getUploadFileService();

            } catch (javax.xml.rpc.ServiceException serviceException) {
            }
        }
        if (__uploadFileService != null) {
            if (_endpoint != null)
                ((javax.xml.rpc.Stub) __uploadFileService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
            else
                _endpoint = (String) ((javax.xml.rpc.Stub) __uploadFileService)._getProperty("javax.xml.rpc.service.endpoint.address");
        }

    }


    public void useJNDI(boolean useJNDI) {
        _useJNDI = useJNDI;
        __uploadFileService = null;

    }

    public String getEndpoint() {
        return _endpoint;
    }

    public void setEndpoint(String endpoint) {
        _endpoint = endpoint;
        if (__uploadFileService != null)
            ((javax.xml.rpc.Stub) __uploadFileService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

    }

    public krms.UploadFileService getUploadFileService() {
        if (__uploadFileService == null)
            _initUploadFileServiceProxy();
        return __uploadFileService;
    }

    public java.lang.String uploadByWebservice(java.lang.String fileURL, java.lang.String categoryId, java.lang.String username) throws java.rmi.RemoteException {
        if (__uploadFileService == null)
            _initUploadFileServiceProxy();
        return __uploadFileService.uploadByWebservice(fileURL, categoryId, username);
    }


}