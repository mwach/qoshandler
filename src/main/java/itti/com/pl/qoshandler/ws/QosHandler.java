package itti.com.pl.qoshandler.ws;

import javax.jws.WebService;

@WebService(endpointInterface = "itti.com.pl.qoshandler.ws.QoSHandlerWS")
public class QosHandler implements QoSHandlerWS{

    @Override
    public void dummy(String name) {
    }

}
