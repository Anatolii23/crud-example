package com.plant.core.exception;

import com.plant.common.model.ServiceException;
import org.springframework.http.HttpStatus;

import static com.plant.common.enums.ApplicationException.PLANT_NAME_EXISTS;

/**
 * Plant Name Exists Exception.
 *
 * @author Anatolii Hamza
 */
public class PlantNameExistsException extends ServiceException {

    public PlantNameExistsException() {
        super(HttpStatus.NOT_FOUND, PLANT_NAME_EXISTS.getCode(), PLANT_NAME_EXISTS.getText());
    }
}
