package bridgeLabz.quantity_measurement.exception;

import bridgeLabz.quantity_measurement.dto.response.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoryMismatchException.class)
    public ResponseEntity<RestResponse<?>> handleCategory(CategoryMismatchException ex) {
        return ResponseEntity.badRequest()
                .body(RestResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(InvalidUnitException.class)
    public ResponseEntity<RestResponse<?>> handleUnit(InvalidUnitException ex) {
        return ResponseEntity.badRequest()
                .body(RestResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<RestResponse<?>> handleUnsupported(UnsupportedOperationException ex) {
        return ResponseEntity.badRequest()
                .body(RestResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(QuantityMeasurementException.class)
    public ResponseEntity<RestResponse<?>> handleGeneral(QuantityMeasurementException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(RestResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<RestResponse<?>> handleBadCredentials(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(RestResponse.error("Invalid email or password"));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<RestResponse<?>> handleAuthentication(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(RestResponse.error("Authentication failed: " + ex.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<RestResponse<?>> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(RestResponse.error("Access denied"));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<RestResponse<?>> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest()
                .body(RestResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<RestResponse<?>> handleArithmetic(ArithmeticException ex) {
        return ResponseEntity.badRequest()
                .body(RestResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponse<?>> handleValidation(MethodArgumentNotValidException ex) {
        String error = ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        return ResponseEntity.badRequest()
                .body(RestResponse.error(error));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RestResponse<?>> handleRuntime(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(RestResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestResponse<?>> handleAll(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(RestResponse.error("An unexpected error occurred"));
    }
}
