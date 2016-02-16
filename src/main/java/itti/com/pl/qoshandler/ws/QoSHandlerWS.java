package itti.com.pl.qoshandler.ws;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import itti.com.pl.qoshandler.ws.dto.ExpirationParameter;

@WebService
public interface QoSHandlerWS {

    /**
     * This operation is before a message is constructed. 
     * It serves as a “Go/ No-go” decision taken by TACTICS_QoS_Handler_Service. 
     * This decision is based on the message Metadata information. 
     * This important decision aims to prevent congestion on the local networking stack.
     * @param metadata Metadata informations
     * @return IsAdmitted
     */
    @WebResult(name="IsAdmitted") 
    public boolean getAdmissionDecision(@WebParam(name = "metadataInformation") @XmlElement(required=true, nillable=false) String metadataInformation);

    /**
     * This operation can be used to get a decision which transport should be used for the message. 
     * The available transports are contained in a configuration file and need to be loaded by the QoS Handler.
     * @param messageId MessageID
     * @return available transports
     */
    @WebResult(name="transport") 
    public String[] getTransport(@WebParam(name = "messageID") @XmlElement(required=true, nillable=false) String messageId);
    
    /**
     * This operation can be used to get a decision which transport should be used to deliver the message to the next TSI-level hop. 
     * The available transports are contained in a configuration file and need to be loaded by the QoS Handler.
     * @param messageId MessageID
     * @param nextHop TSI node address
     * @return available transports
     */
    @WebResult(name="transport") 
    public String[] getTransportByHop(@WebParam(name = "messageID") @XmlElement(required=true, nillable=false) String messageId, 
            @WebParam(name="nextHop") @XmlElement(required=true, nillable=false) String nextHop);

    /**
     * This operation can be used to learn whether a message demands reliability or not.
     * @param messageId MessageID
     * @return IsReliable
     */
    @WebResult(name="IsReliable") 
    public boolean getReliabilityParameters(@WebParam(name = "messageID") @XmlElement(required=true, nillable=false) String messageId);

    /**
     * This operation can be used in order to get the message priority computed by the TACTICS_QoS_Handler_Service.
     * @param messageId MessageID
     * @return Possible values (as in ACP123(B)): 0 = „NORMAL”, 1 = „NON-URGENT”, 2 = „URGENT”.
     */
    @WebResult(name="priority") 
    public int getPriority(@WebParam(name = "messageID") @XmlElement(required=true, nillable=false) String messageId);

    /**
     * This operation can be used to get the conditions under which the message expires. timeToLive is a timestamp; 
     * the current date and time plus an offset. 
     * hopLimit is the number of nodes the message may traverse. networkLimit is the number of networks the message may cross (as determined by the PacketHandler). 
     * Both limits must be larger than 0.
     * @param messageId MessageID
     * @return expirationParameters: timeToLive: timestamp [optional], hopLimit: Integer [optional], networkLimit: Integer [optional]
     */
    @WebResult(name="expirationParameters") 
    public ExpirationParameter getExpirationParameters(@WebParam(name = "messageID") @XmlElement(required=true, nillable=false) String messageId);

}
