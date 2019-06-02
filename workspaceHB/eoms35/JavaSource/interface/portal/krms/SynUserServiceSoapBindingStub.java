/**
 * SynUserServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf130745.06 v111407041203
 */

package krms;

public class SynUserServiceSoapBindingStub extends com.ibm.ws.webservices.engine.client.Stub implements krms.SynchroniseUserService {
    public SynUserServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws com.ibm.ws.webservices.engine.WebServicesFault {
        if (service == null) {
            super.service = new com.ibm.ws.webservices.engine.client.Service();
        }
        else {
            super.service = service;
        }
        super.engine = ((com.ibm.ws.webservices.engine.client.Service) super.service).getEngine();
        super.cachedEndpoint = endpointURL;
        super.connection = ((com.ibm.ws.webservices.engine.client.Service) super.service).getConnection(endpointURL);
        super.messageContexts = new com.ibm.ws.webservices.engine.MessageContext[1];
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _synchroniseUserOperation0 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getsynchroniseUserOperation0() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
          };
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(null, com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://websphere.ibm.com/webservices/", "Void"), void.class, true, false, false, false, true, true); 
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _synchroniseUserOperation0 = new com.ibm.ws.webservices.engine.description.OperationDesc("synchroniseUser", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://wservice.externalinterface.subassembly.krm.boco.com", "synchroniseUser"), _params0, _returnDesc0, _faults0, "");
        _synchroniseUserOperation0.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/synUserService", "synchroniseUserRequest"));
        _synchroniseUserOperation0.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/synUserService", "SynchroniseUserServiceService"));
        _synchroniseUserOperation0.setOption("inputName","synchroniseUserRequest");
        _synchroniseUserOperation0.setOption("inputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        _synchroniseUserOperation0.setOption("buildNum","cf130745.06");
        _synchroniseUserOperation0.setOption("outputName","synchroniseUserResponse");
        _synchroniseUserOperation0.setOption("targetNamespace","http://krms/services/synUserService");
        _synchroniseUserOperation0.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/synUserService", "synchroniseUserResponse"));
        _synchroniseUserOperation0.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/synUserService", "SynchroniseUserService"));
        _synchroniseUserOperation0.setOption("outputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        _synchroniseUserOperation0.setUse(com.ibm.ws.webservices.engine.enumtype.Use.ENCODED);
        _synchroniseUserOperation0.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.RPC);
        return _synchroniseUserOperation0;

    }

    private int _synchroniseUserIndex0 = 0;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getsynchroniseUserInvoke0(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_synchroniseUserIndex0];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(SynUserServiceSoapBindingStub._synchroniseUserOperation0);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            super.primeMessageContext(mc);
            super.messageContexts[_synchroniseUserIndex0] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public void synchroniseUser() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        try {
            _getsynchroniseUserInvoke0(new java.lang.Object[] {}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
    }

    private static void _staticInit() {
        _synchroniseUserOperation0 = _getsynchroniseUserOperation0();
    }

    static {
       _staticInit();
    }
}
