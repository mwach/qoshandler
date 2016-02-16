package itti.com.pl.qoshandler.ws.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExpirationParameter {

    @XmlElement(required=false)
    Long timeToLive;
    @XmlElement(required=false)
    Integer hopLimit;
    @XmlElement(required=false)
    Integer networkLimit;

    public ExpirationParameter(){}

    public ExpirationParameter(Long timeToLive, Integer hopLimit, Integer networkLimit){
        this.timeToLive = timeToLive;
        this.hopLimit = hopLimit;
        this.networkLimit = networkLimit;
    }

    public long getTimeToLive() {
        return timeToLive;
    }
    public void setTimeToLive(long timeToLive) {
        this.timeToLive = timeToLive;
    }
    public int getHopLimit() {
        return hopLimit;
    }
    public void setHopLimit(int hopLimit) {
        this.hopLimit = hopLimit;
    }
    public int getNetworkLimit() {
        return networkLimit;
    }
    public void setNetworkLimit(int networkLimit) {
        this.networkLimit = networkLimit;
    }

    
}
