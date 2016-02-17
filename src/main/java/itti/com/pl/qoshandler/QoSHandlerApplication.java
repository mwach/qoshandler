/**
 * 
 * File QoSHandlerApplication.java
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
package itti.com.pl.qoshandler;

import java.util.Properties;

import javax.xml.ws.Endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import itti.com.pl.qoshandler.exception.QoSHandlerRuntimeException;
import itti.com.pl.qoshandler.utils.NetworkUtils;
import itti.com.pl.qoshandler.utils.PropertyUtils;
import itti.com.pl.qoshandler.ws.QosHandler;

/**
 * Entry point of the application
 */
public class QoSHandlerApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(QoSHandlerApplication.class);

    private Properties properties = new Properties();
    private QosHandler qosHandlerImplementor = null;

    /**
     * Main method
     * @param args a path to the file with custom properties can be provided as an optional argument
     */
    public static void main(String[] args) {
        LOGGER.info("Starting Server");
        QoSHandlerApplication qoSHandlerApplication = new QoSHandlerApplication();
        try {
            LOGGER.info("Loading default properties");
            qoSHandlerApplication.loadProperties();

            if(args.length > 0){
                LOGGER.info("Loading custom propeties from file {}", args[0]);
                qoSHandlerApplication.loadProperties(args[0]);
            }
            qoSHandlerApplication.initService();
        }catch(RuntimeException exc){
            LOGGER.error(exc.getLocalizedMessage(), exc);
        }
    }

    private void loadProperties() {
        String propertyFile = String.format("/%s.properties", QoSHandlerApplication.class.getSimpleName());
        loadProperties(propertyFile);
    }

    private void loadProperties(String propertyFile) {
        properties.putAll(PropertyUtils.loadProperties(getClass().getResourceAsStream(propertyFile)));
    }


    /**
     * Initializes SOAP CXF web service interface
     * @throws QoSHandlerRuntimeException 
     */
    private void initService() throws QoSHandlerRuntimeException {

        String ipAddress = NetworkUtils.getIpAddress();
        LOGGER.info("Obtained IP address of the host {}", ipAddress);

        String mhServiceAddress = getEndpointAddress(Constants.ENDPOINT, ipAddress);
        LOGGER.info("Initialising web service using address {}", mhServiceAddress);
        qosHandlerImplementor = new QosHandler();

        try {
            Endpoint.publish(mhServiceAddress, qosHandlerImplementor);
        } catch (RuntimeException e) {
            throw new QoSHandlerRuntimeException("Could not create a service using given endpoint", e);
        }
    }

    private String getEndpointAddress(String propertyName, String ipAddress) {
        String propertyAddress = properties.getProperty(propertyName);
        if(!propertyAddress.startsWith("http")){
            return "http://" + ipAddress + propertyAddress;
        }
        return propertyAddress;
    }
}
