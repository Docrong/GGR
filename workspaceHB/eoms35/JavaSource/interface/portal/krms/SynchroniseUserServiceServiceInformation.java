/**
 * SynchroniseUserServiceServiceInformation.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf130745.06 v111407041203
 */

package krms;

public class SynchroniseUserServiceServiceInformation implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

    private static java.util.Map operationDescriptions;
    private static java.util.Map typeMappings;

    static {
         initOperationDescriptions();
         initTypeMappings();
    }

    private static void initOperationDescriptions() { 
        operationDescriptions = new java.util.HashMap();

        java.util.Map inner0 = new java.util.HashMap();

        java.util.List list0 = new java.util.ArrayList();
        inner0.put("synchroniseUser", list0);

        com.ibm.ws.webservices.engine.description.OperationDesc synchroniseUser0Op = _synchroniseUser0Op();
        list0.add(synchroniseUser0Op);

        operationDescriptions.put("synUserService",inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _synchroniseUser0Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc synchroniseUser0Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
          };
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(null, com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://websphere.ibm.com/webservices/", "Void"), void.class, true, false, false, false, true, true); 
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        synchroniseUser0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("synchroniseUser", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://wservice.externalinterface.subassembly.krm.boco.com", "synchroniseUser"), _params0, _returnDesc0, _faults0, null);
        synchroniseUser0Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/synUserService", "synchroniseUserRequest"));
        synchroniseUser0Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/synUserService", "SynchroniseUserServiceService"));
        synchroniseUser0Op.setOption("inputName","synchroniseUserRequest");
        synchroniseUser0Op.setOption("inputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        synchroniseUser0Op.setOption("buildNum","cf130745.06");
        synchroniseUser0Op.setOption("outputName","synchroniseUserResponse");
        synchroniseUser0Op.setOption("targetNamespace","http://krms/services/synUserService");
        synchroniseUser0Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/synUserService", "synchroniseUserResponse"));
        synchroniseUser0Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/synUserService", "SynchroniseUserService"));
        synchroniseUser0Op.setOption("outputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        synchroniseUser0Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.RPC);
        return synchroniseUser0Op;

    }


    private static void initTypeMappings() {
        typeMappings = new java.util.HashMap();
        typeMappings = java.util.Collections.unmodifiableMap(typeMappings);
    }

    public java.util.Map getTypeMappings() {
        return typeMappings;
    }

    public Class getJavaType(javax.xml.namespace.QName xmlName) {
        return (Class) typeMappings.get(xmlName);
    }

    public java.util.Map getOperationDescriptions(String portName) {
        return (java.util.Map) operationDescriptions.get(portName);
    }

    public java.util.List getOperationDescriptions(String portName, String operationName) {
        java.util.Map map = (java.util.Map) operationDescriptions.get(portName);
        if (map != null) {
            return (java.util.List) map.get(operationName);
        }
        return null;
    }

}
