/**
 * 
 * File PropertyUtils.java
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
package itti.com.pl.qoshandler.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import itti.com.pl.qoshandler.exception.QoSHandlerRuntimeException;

/**
 * Utility class containing property-related helper methods
 */
public final class PropertyUtils {

    private PropertyUtils() {
    }

    /**
     * Load properties from property file
     * 
     * @param propertyFile
     *            path to the property file
     * @throws QoSHandlerRuntimeException exception during properties processing
     * @return properties found in the provided file
     */
    public static Properties loadProperties(String propertyFile) {
        try (InputStream stream = new FileInputStream(propertyFile)) {
            return loadProperties(stream);
        } catch (IOException | RuntimeException e) {
            throw new QoSHandlerRuntimeException(
                    String.format("Could not read data from '%s' property file", propertyFile), e);
        }
    }

    /**
     * Load properties from the provided {@link InputStream}
     * 
     * @param stream
     *            {@link InputStream}
     * @throws QoSHandlerRuntimeException exception during properties processing
     * @return properties found in the provided stream
     */
    public static Properties loadProperties(InputStream stream) {
        Properties properties = new Properties();
        try {
            properties.load(stream);
        } catch (IOException e) {
            throw new QoSHandlerRuntimeException("Could not load property data", e);
        }
        return properties;
    }

}
