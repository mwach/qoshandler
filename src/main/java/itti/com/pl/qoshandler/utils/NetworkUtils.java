/**
 * 
 * File NetworkUtils.java
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

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import itti.com.pl.qoshandler.exception.QoSHandlerRuntimeException;

/**
 * Utility class containing network-related helper methods
 */
public final class NetworkUtils {

    private static List<String> EXCLUDED_PATTERNS = Arrays.asList("127.0.0.1", "127.0.1.1", ":");

    private NetworkUtils() {
    }

    /**
     * Returns IP-4 network address Filters-out localhost/127.0.x.x addresses
     * 
     * @return IP address of one of the available network interfaces
     * @throws QoSHandlerRuntimeException
     *             could not obtain IP address
     */
    public static String getIpAddress() throws QoSHandlerRuntimeException {
        try {
            String hostIP = InetAddress.getLocalHost().getHostAddress();
            if (!isExcludedAddress(hostIP)) {
                return hostIP;
            }

            Enumeration<NetworkInterface> nInterfaces = NetworkInterface.getNetworkInterfaces();
            while (nInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = nInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    String address = inetAddresses.nextElement().getHostAddress();
                    if (!isExcludedAddress(address)) {
                        return address;
                    }
                }
            }
        } catch (UnknownHostException | SocketException e) {
            throw new QoSHandlerRuntimeException("Could not determine IP address", e);
        }
        throw new QoSHandlerRuntimeException("Could not determine IP address");
    }

    private static boolean isExcludedAddress(String address) {
        for(String excluded : EXCLUDED_PATTERNS){
            if (address.contains(excluded)){
                return true;
            }
        }
        return false;
    }

}
