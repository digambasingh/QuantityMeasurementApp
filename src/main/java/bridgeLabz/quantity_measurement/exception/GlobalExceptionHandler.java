package bridgeLabz.quantity_measurement.exception;

import bridgeLabz.quantity_measurement.response.RestResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoryMismatchException.class)
    public ResponseEntity<RestResponse<?>> handleCategory(CategoryMismatchException ex) {
        return ResponseEntity.badRequest()
                .body(new RestResponse<>(false, ex.getMessage(), "CATEGORY_MISMATCH"));
    }

    @ExceptionHandler(InvalidUnitException.class)
    public ResponseEntity<RestResponse<?>> handleUnit(InvalidUnitException ex) {
        return ResponseEntity.badRequest()
                .body(new RestResponse<>(false, ex.getMessage(), "INVALID_UNIT"));
    }

    @ExceptionHandler(QuantityMeasurementException.class)
    public ResponseEntity<RestResponse<?>> handleGeneral(QuantityMeasurementException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new RestResponse<>(false, ex.getMessage(), "GENERAL_ERROR"));
    }
}
