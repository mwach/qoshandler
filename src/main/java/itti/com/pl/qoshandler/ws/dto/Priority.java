package itti.com.pl.qoshandler.ws.dto;

public enum Priority {

    NORMAL(0),
    NON_URGENT(1),
    URGENT(2);
    
    private int priority;
    private Priority(int priority){
        this.priority = priority;
    }
    
    public int getPriority(){
        return priority;
    }
}
