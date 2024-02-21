package fr.btn.BookMyTicketAPI.enums;

public enum Role {
    DIRECTOR("D"),
    LEAD_ACTOR("LA");

    private String code;

    Role(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
