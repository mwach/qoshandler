package itti.com.pl.qoshandler.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface QoSHandlerWS {

    public void dummy(@WebParam(name = "name")String name );
}
