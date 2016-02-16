package itti.com.pl.qoshandler.exception;

public class QoSHandlerApplicationException extends RuntimeException {

    public QoSHandlerApplicationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public QoSHandlerApplicationException(String message) {
        super(message);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
