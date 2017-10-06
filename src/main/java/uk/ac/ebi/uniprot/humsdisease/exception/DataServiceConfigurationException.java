package uk.ac.ebi.uniprot.humsdisease.exception;

public class DataServiceConfigurationException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DataServiceConfigurationException() {
        super();
    }

    public DataServiceConfigurationException(String message) {
        super(message);
    }

    public DataServiceConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataServiceConfigurationException(Throwable cause) {
        super(cause);
    }

}
