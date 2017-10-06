package uk.ac.ebi.uniprot.humsdisease.exception;

public class DataServiceDatabaseException extends RuntimeException {

    private static final long serialVersionUID = 2L;

    public DataServiceDatabaseException() {
        super();
    }

    public DataServiceDatabaseException(String message) {
        super(message);
    }

    public DataServiceDatabaseException(String message, Throwable thrown) {
        super(message, thrown);
    }

    public DataServiceDatabaseException(Throwable thrown) {
        super(thrown);
    }

}
