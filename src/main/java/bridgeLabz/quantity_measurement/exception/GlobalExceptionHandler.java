package bridgeLabz.quantity_measurement.exception;

import bridgeLabz.quantity_measurement.response.RestResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.util.Map;


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

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntime(RuntimeException ex) {

        return ResponseEntity.badRequest().body(
                Map.of(
                        "success", false,
                        "message", ex.getMessage()
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {

        String error = ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        return ResponseEntity.badRequest().body(
                Map.of(
                        "success", false,
                        "message", error
                )
        );
    }
}
