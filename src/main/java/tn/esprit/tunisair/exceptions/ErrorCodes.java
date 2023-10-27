package tn.esprit.tunisair.exceptions;

public enum ErrorCodes {



    SALLE_NOT_FOUND(2000),
    SALLe_NOT_VALID(2001),
    SALLE_ALREADY_IN_USE(2002);














    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
