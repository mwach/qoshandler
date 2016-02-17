/**
 * 
 * File ExpirationParameter.java
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
package itti.com.pl.qoshandler.ws.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import itti.com.pl.qoshandler.ws.QoSHandlerWS;

/**
 * Expiration parameters used by the {@link QoSHandlerWS}:getExpirationParameters method
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExpirationParameter {

    @XmlElement(required=false)
    private Long timeToLive;
    @XmlElement(required=false)
    private Integer hopLimit;
    @XmlElement(required=false)
    private Integer networkLimit;

    /**
     * Default constructor
     */
    public ExpirationParameter(){}

    /**
     * Constructor
     * @param timeToLive time to live
     * @param hopLimit hop limit
     * @param networkLimit network limit
     */
    public ExpirationParameter(Long timeToLive, Integer hopLimit, Integer networkLimit){
        this();
        this.timeToLive = timeToLive;
        this.hopLimit = hopLimit;
        this.networkLimit = networkLimit;
    }

    /**
     * Returns value of the time-to-live parameter
     * @return timeToLive
     */
    public long getTimeToLive() {
        return timeToLive;
    }

    /**
     * Sets value of the time-to-live parameter
     * @param timeToLive time-to-live
     */
    public void setTimeToLive(long timeToLive) {
        this.timeToLive = timeToLive;
    }
    /**
     * Returns value of the hop-limit parameter
     * @return hopLimit
     */
    public int getHopLimit() {
        return hopLimit;
    }

    /**
     * Sets value of the hop-limit parameter
     * @param hopLimit hop limit
     */
    public void setHopLimit(int hopLimit) {
        this.hopLimit = hopLimit;
    }
    /**
     * Returns value of the network-limit parameter
     * @return networkLimit
     */
    public int getNetworkLimit() {
        return networkLimit;
    }

    /**
     * Sets value of the network-limit parameter
     * @param networkLimit network limit 
     */
    public void setNetworkLimit(int networkLimit) {
        this.networkLimit = networkLimit;
    }

    
}
