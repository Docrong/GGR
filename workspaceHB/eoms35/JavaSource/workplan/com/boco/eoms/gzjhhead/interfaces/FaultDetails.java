/**
 * FaultDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.gzjhhead.interfaces;

public class FaultDetails extends org.apache.axis.AxisFault {
    public java.lang.String parameters;
    public java.lang.String getParameters() {
        return this.parameters;
    }

    public FaultDetails() {
    }

      public FaultDetails(java.lang.String parameters) {
        this.parameters = parameters;
    }

    /**
     * Writes the exception data to the faultDetails
     */
    public void writeDetails(javax.xml.namespace.QName qname, org.apache.axis.encoding.SerializationContext context) throws java.io.IOException {
        context.serialize(qname, null, parameters);
    }
}
