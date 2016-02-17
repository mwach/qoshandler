/**
 * 
 * File QoSHandler.java
 *
 * Project: TACTICS
 *
 * Begin: Feb 17, 2016, 8:26:43 PM
 *
 * UNCLASSIFIED
 *
 * Copyright (c) 2016 <FILL IN YOUR ORGANIZATION(S)>
 *
 * This work was performed within the EDA Project TACTICS (Tactical Service
 * Oriented Architecture). The TACTICS project is jointly undertaken by Patria
 * (FI), Thales Communications & Security (FR), Fraunhofer-Institut fuer
 * Kommunikation, Informationsverarbeitung und Ergonomie FKIE (DE), Thales
 * Deutschland (DE), Finmeccanica (IT), Thales Italia (IT), NTNU i Gjøvik (NO),
 * ITTI (PL), Military Communication Institute (PL), and their partners,
 * supported by the respective national Ministries of Defence under EDA Contract
 *  No. B 0980 IAP4 GP.
 *
 * Subject to the terms of the EDA Contract No. B 0980 IAP4 GP, all rights,
 * especially the right for copying and distribution as well as translation,
 * are reserved.
 *
 * No part of the product shall be reproduced or stored, processed, copied or
 * distributed with electronic tools or by paper copy or microfiche or any
 * other process without written authorization of all originators.
 *
 * Liability is excluded to the extent permitted by Belgian law and limited to
 * the extent set out in the terms and conditions of above-mentioned EDA
 * Contract.
 *
 * @author ITTI
 */
package itti.com.pl.qoshandler.ws;

import javax.jws.WebService;

import itti.com.pl.qoshandler.ws.dto.ExpirationParameter;
import itti.com.pl.qoshandler.ws.dto.Priority;

/**
 * Implementation of the web services defined in the {@link QoSHandlerWS}
 */
@WebService(endpointInterface = "itti.com.pl.qoshandler.ws.QoSHandlerWS")
public class QosHandler implements QoSHandlerWS{

    /* (non-Javadoc)
     * @see itti.com.pl.qoshandler.ws.QoSHandlerWS#getAdmissionDecision(java.lang.String)
     */
    @Override
    public boolean getAdmissionDecision(String metadataInformation) {
        return false;
    }

    /* (non-Javadoc)
     * @see itti.com.pl.qoshandler.ws.QoSHandlerWS#getTransport(java.lang.String)
     */
    @Override
    public String[] getTransport(String messageId) {
        return new String[]{"tcp", "udp"};
    }

    /* (non-Javadoc)
     * @see itti.com.pl.qoshandler.ws.QoSHandlerWS#getTransportByHop(java.lang.String, java.lang.String)
     */
    @Override
    public String[] getTransportByHop(String messageId, String nextHop) {
        return new String[]{"dccp", "udp"};
    }

    /* (non-Javadoc)
     * @see itti.com.pl.qoshandler.ws.QoSHandlerWS#getReliabilityParameters(java.lang.String)
     */
    @Override
    public boolean getReliabilityParameters(String messageId) {
        return true;
    }

    /* (non-Javadoc)
     * @see itti.com.pl.qoshandler.ws.QoSHandlerWS#getPriority(java.lang.String)
     */
    @Override
    public int getPriority(String messageId) {
        return Priority.NON_URGENT.getPriority();
    }

    /* (non-Javadoc)
     * @see itti.com.pl.qoshandler.ws.QoSHandlerWS#getExpirationParameters(java.lang.String)
     */
    @Override
    public ExpirationParameter getExpirationParameters(String messageId) {
        return new ExpirationParameter(100L, 5, null);
    }

}
