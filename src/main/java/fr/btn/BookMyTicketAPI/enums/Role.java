package fr.btn.BookMyTicketAPI.enums;

public enum Role {
    DIRECTOR("D"),
    LEAD_ACTOR("S");

    private String code;

    Role(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
