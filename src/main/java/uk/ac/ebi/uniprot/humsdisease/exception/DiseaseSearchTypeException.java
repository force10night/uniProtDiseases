package uk.ac.ebi.uniprot.humsdisease.exception;

public class DiseaseSearchTypeException extends RuntimeException {

    private static final long serialVersionUID = 3L;

    public DiseaseSearchTypeException() {
        super();
    }

    public DiseaseSearchTypeException(String message) {
        super(message);
    }

    public DiseaseSearchTypeException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public DiseaseSearchTypeException(Throwable thrown) {
        super(thrown);
    }

}
