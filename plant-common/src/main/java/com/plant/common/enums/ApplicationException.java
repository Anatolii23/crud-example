package com.plant.common.enums;

/**
 * Identity Exception Enum.
 *
 * @author Anatolii Hamza
 */
public enum ApplicationException {

    PLANT_NOT_FOUND("error_plant_not_found", "Plant not found"),
    PLANT_NAME_EXISTS("error_plant_name_exist", "Plant name is exists");

    private String code;
    private String text;

    ApplicationException(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
