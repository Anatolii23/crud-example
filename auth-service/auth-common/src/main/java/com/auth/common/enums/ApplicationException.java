package com.auth.common.enums;

/**
 * Identity Exception Enum.
 *
 * @author Anatolii Hamza
 */
public enum ApplicationException {

    CONSTRAINTS_VIOLATION("error_constraints_violation", "Constraints violation."),
    INVALID_REQUEST_ARGUMENT("error_invalid_request_argument", "Invalid request argument."),
    JSON_PROCESSING("error_json_processing", "Error during json processing."),
    MESSAGE_NOT_READABLE("error_message_not_readable", "Incorrect request fields or values."),
    METHOD_ARGUMENT_NOT_VALID("error_method_argument_not_valid", "Method arguments is invalid.");

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
