package itti.com.pl.qoshandler.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import itti.com.pl.qoshandler.exception.QoSHandlerApplicationException;

public final class PropertyUtils {

    private PropertyUtils (){
    }
   

    /**
     * Load optional application properties from external property file
     * @param propertyFile path to the external property file
     * @throws QoSHandlerApplicationException 
     */
    public static Properties loadProperties(String propertyFile) {
        try(InputStream stream = new FileInputStream(propertyFile)){
            return loadProperties(stream);
        }catch (IOException | RuntimeException e) {
            throw new QoSHandlerApplicationException(String.format("Could not read data from '%s' property file", propertyFile), e);
        }
    }

    /**
     * Load properties from the provided {@link InputStream}
     * @param stream {@link InputStream}
     * @throws QoSHandlerApplicationException 
     */
    public static Properties loadProperties(InputStream stream) {
        Properties properties = new Properties();
        try {
            properties.load(stream);
        } catch (IOException e) {
            throw new QoSHandlerApplicationException("Could not load property data", e);
        }
        return properties;
    }

}
