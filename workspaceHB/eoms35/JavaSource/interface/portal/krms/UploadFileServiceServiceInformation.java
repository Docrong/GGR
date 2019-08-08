/**
 * UploadFileServiceServiceInformation.java
 * <p>
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf130745.06 v111407041203
 */

package krms;

public class UploadFileServiceServiceInformation implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

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
        inner0.put("uploadByWebservice", list0);

        com.ibm.ws.webservices.engine.description.OperationDesc uploadByWebservice0Op = _uploadByWebservice0Op();
        list0.add(uploadByWebservice0Op);

        operationDescriptions.put("uploadFileService", inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _uploadByWebservice0Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc uploadByWebservice0Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[] _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[]{
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "fileURL"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "categoryId"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "username"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
        };
        _params0[0].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[0].setOption("partName", "string");
        _params0[1].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[1].setOption("partName", "string");
        _params0[2].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[2].setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.ParameterDesc _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "uploadByWebserviceReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, true, false, false, false, true, false);
        _returnDesc0.setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _returnDesc0.setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.FaultDesc[] _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[]{
        };
        uploadByWebservice0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("uploadByWebservice", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://wservice.externalinterface.subassembly.krm.boco.com", "uploadByWebservice"), _params0, _returnDesc0, _faults0, null);
        uploadByWebservice0Op.setOption("inputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/uploadFileService", "uploadByWebserviceRequest"));
        uploadByWebservice0Op.setOption("ServiceQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/uploadFileService", "UploadFileServiceService"));
        uploadByWebservice0Op.setOption("inoutOrderingReq", "false");
        uploadByWebservice0Op.setOption("inputName", "uploadByWebserviceRequest");
        uploadByWebservice0Op.setOption("inputEncodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
        uploadByWebservice0Op.setOption("buildNum", "cf130745.06");
        uploadByWebservice0Op.setOption("outputName", "uploadByWebserviceResponse");
        uploadByWebservice0Op.setOption("targetNamespace", "http://krms/services/uploadFileService");
        uploadByWebservice0Op.setOption("outputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/uploadFileService", "uploadByWebserviceResponse"));
        uploadByWebservice0Op.setOption("portTypeQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/uploadFileService", "UploadFileService"));
        uploadByWebservice0Op.setOption("outputEncodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
        uploadByWebservice0Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.RPC);
        return uploadByWebservice0Op;

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
