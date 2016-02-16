package itti.com.pl.qoshandler;

import java.util.Properties;

import javax.xml.ws.Endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import itti.com.pl.qoshandler.exception.QoSHandlerApplicationException;
import itti.com.pl.qoshandler.utils.NetworkUtils;
import itti.com.pl.qoshandler.utils.PropertyUtils;
import itti.com.pl.qoshandler.ws.QosHandler;

public class QoSHandlerApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(QoSHandlerApplication.class);

    private Properties properties = new Properties();
    private QosHandler qosHandlerImplementor = null;

    public static void main(String[] args) {
        LOGGER.info("Starting Server");
        QoSHandlerApplication qoSHandlerApplication = new QoSHandlerApplication();
        try {
            LOGGER.info("Loading default properties");
            qoSHandlerApplication.loadProperties();

            if(args.length > 0){
                LOGGER.info("Loading custom propeties from file {}", args[1]);
                qoSHandlerApplication.loadProperties(args[0]);             
            }
            qoSHandlerApplication.initService();
        }catch(Exception exc){
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
     * @throws QoSHandlerApplicationException 
     */
    private void initService() throws QoSHandlerApplicationException {

        String ipAddress = NetworkUtils.getIpAddress();
        LOGGER.info("Obtained IP address of the host {}", ipAddress);

        String mhServiceAddress = getEndpointAddress(Constants.ENDPOINT, ipAddress);
        LOGGER.info("Initialising web service using address {}", mhServiceAddress);
        qosHandlerImplementor = new QosHandler();

        try {
            Endpoint.publish(mhServiceAddress, qosHandlerImplementor);
        } catch (RuntimeException e) {
            throw new QoSHandlerApplicationException("Could not publish an endpoint", e);
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
