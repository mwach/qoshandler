package itti.com.pl.qoshandler.ws;

import javax.jws.WebService;

import itti.com.pl.qoshandler.ws.dto.ExpirationParameter;
import itti.com.pl.qoshandler.ws.dto.Priority;

@WebService(endpointInterface = "itti.com.pl.qoshandler.ws.QoSHandlerWS")
public class QosHandler implements QoSHandlerWS{

    @Override
    public boolean getAdmissionDecision(String metadataInformation) {
        return false;
    }

    @Override
    public String[] getTransport(String messageId) {
        return new String[]{"tcp", "udp"};
    }

    @Override
    public String[] getTransportByHop(String messageId, String nextHop) {
        return new String[]{"dccp", "udp"};
    }

    @Override
    public boolean getReliabilityParameters(String messageId) {
        return true;
    }

    @Override
    public int getPriority(String messageId) {
        return Priority.NON_URGENT.getPriority();
    }

    @Override
    public ExpirationParameter getExpirationParameters(String messageId) {
        return new ExpirationParameter(100L, 5, null);
    }

}
