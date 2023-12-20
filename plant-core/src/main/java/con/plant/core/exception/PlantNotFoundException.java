package con.plant.core.exception;

import com.plant.common.model.ServiceException;
import org.springframework.http.HttpStatus;

import static com.plant.common.enums.ApplicationException.PLANT_NOT_FOUND;

/**
 * Plant Not Found Exception.
 *
 * @author Anatolii Hamza
 */
public class PlantNotFoundException extends ServiceException {

    public PlantNotFoundException() {
        super(HttpStatus.NOT_FOUND, PLANT_NOT_FOUND.getCode(), PLANT_NOT_FOUND.getText());
    }
}
