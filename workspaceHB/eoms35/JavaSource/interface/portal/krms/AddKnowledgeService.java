/**
 * AddKnowledgeService.java
 * <p>
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf170819.19 v52708210711
 */

package krms;

public interface AddKnowledgeService extends java.rmi.Remote {
    public java.lang.String getKnowledgeBySheetIds(java.lang.String xmlStr) throws java.rmi.RemoteException;

    public java.lang.String saveXmlValue(java.lang.String xmlStr) throws java.rmi.RemoteException;
}
