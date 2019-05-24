/**
 * UploadFileServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf130745.06 v111407041203
 */

package krms;

public class UploadFileServiceSoapBindingStub extends com.ibm.ws.webservices.engine.client.Stub implements krms.UploadFileService {
    public UploadFileServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws com.ibm.ws.webservices.engine.WebServicesFault {
        if (service == null) {
            super.service = new com.ibm.ws.webservices.engine.client.Service();
        }
        else {
            super.service = service;
        }
        super.engine = ((com.ibm.ws.webservices.engine.client.Service) super.service).getEngine();
        initTypeMapping();
        super.cachedEndpoint = endpointURL;
        super.connection = ((com.ibm.ws.webservices.engine.client.Service) super.service).getConnection(endpointURL);
        super.messageContexts = new com.ibm.ws.webservices.engine.MessageContext[1];
    }

    private void initTypeMapping() {
        javax.xml.rpc.encoding.TypeMapping tm = super.getTypeMapping(com.ibm.ws.webservices.engine.Constants.URI_SOAP11_ENC);
        java.lang.Class javaType = null;
        javax.xml.namespace.QName xmlType = null;
        javax.xml.namespace.QName compQName = null;
        javax.xml.namespace.QName compTypeQName = null;
        com.ibm.ws.webservices.engine.encoding.SerializerFactory sf = null;
        com.ibm.ws.webservices.engine.encoding.DeserializerFactory df = null;
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _uploadByWebserviceOperation0 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getuploadByWebserviceOperation0() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "fileURL"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "categoryId"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "username"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[0].setOption("partName","string");
        _params0[1].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[1].setOption("partName","string");
        _params0[2].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[2].setOption("partName","string");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "uploadByWebserviceReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _returnDesc0.setOption("partName","string");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _uploadByWebserviceOperation0 = new com.ibm.ws.webservices.engine.description.OperationDesc("uploadByWebservice", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://wservice.externalinterface.subassembly.krm.boco.com", "uploadByWebservice"), _params0, _returnDesc0, _faults0, "");
        _uploadByWebserviceOperation0.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/uploadFileService", "uploadByWebserviceRequest"));
        _uploadByWebserviceOperation0.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/uploadFileService", "UploadFileServiceService"));
        _uploadByWebserviceOperation0.setOption("inoutOrderingReq","false");
        _uploadByWebserviceOperation0.setOption("inputName","uploadByWebserviceRequest");
        _uploadByWebserviceOperation0.setOption("inputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        _uploadByWebserviceOperation0.setOption("buildNum","cf130745.06");
        _uploadByWebserviceOperation0.setOption("outputName","uploadByWebserviceResponse");
        _uploadByWebserviceOperation0.setOption("targetNamespace","http://krms/services/uploadFileService");
        _uploadByWebserviceOperation0.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/uploadFileService", "uploadByWebserviceResponse"));
        _uploadByWebserviceOperation0.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/uploadFileService", "UploadFileService"));
        _uploadByWebserviceOperation0.setOption("outputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        _uploadByWebserviceOperation0.setUse(com.ibm.ws.webservices.engine.enumtype.Use.ENCODED);
        _uploadByWebserviceOperation0.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.RPC);
        return _uploadByWebserviceOperation0;

    }

    private int _uploadByWebserviceIndex0 = 0;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getuploadByWebserviceInvoke0(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_uploadByWebserviceIndex0];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(UploadFileServiceSoapBindingStub._uploadByWebserviceOperation0);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            super.primeMessageContext(mc);
            super.messageContexts[_uploadByWebserviceIndex0] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String uploadByWebservice(java.lang.String fileURL, java.lang.String categoryId, java.lang.String username) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getuploadByWebserviceInvoke0(new java.lang.Object[] {fileURL, categoryId, username}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (java.lang.String) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (java.lang.String) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), java.lang.String.class);
        }
    }

    private static void _staticInit() {
        _uploadByWebserviceOperation0 = _getuploadByWebserviceOperation0();
    }

    static {
       _staticInit();
    }
}
