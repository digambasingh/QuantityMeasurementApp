package bridgeLabz.quantity_measurement.controller;

import bridgeLabz.quantity_measurement.dto.QuantityDTO;
import bridgeLabz.quantity_measurement.dto.request.ConvertRequestDTO;
import bridgeLabz.quantity_measurement.dto.request.OperationRequestDTO;
import bridgeLabz.quantity_measurement.dto.response.RestResponse;
import bridgeLabz.quantity_measurement.entity.UserEntity;
import bridgeLabz.quantity_measurement.repository.UserRepository;
import bridgeLabz.quantity_measurement.service.QuantityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quantities")
@RequiredArgsConstructor
public class QuantityController {

    private final QuantityService service;
    private final UserRepository userRepository;

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public RestResponse<QuantityDTO> add(@Valid @RequestBody OperationRequestDTO request,
                                         Authentication authentication) {
        UserEntity user = resolveUser(authentication);
        QuantityDTO result = service.add(
                request.getFirstQuantity(), request.getSecondQuantity(), user);
        return RestResponse.success("Addition successful", result);
    }

    @PostMapping("/subtract")
    public RestResponse<QuantityDTO> subtract(@Valid @RequestBody OperationRequestDTO request,
                                               Authentication authentication) {
        UserEntity user = resolveUser(authentication);
        return RestResponse.success("Subtraction successful",
                service.subtract(request.getFirstQuantity(), request.getSecondQuantity(), user));
    }

    @PostMapping("/multiply")
    public RestResponse<QuantityDTO> multiply(@Valid @RequestBody OperationRequestDTO request,
                                               Authentication authentication) {
        UserEntity user = resolveUser(authentication);
        return RestResponse.success("Multiplication successful",
                service.multiply(request.getFirstQuantity(), request.getSecondQuantity(), user));
    }

    @PostMapping("/divide")
    public RestResponse<QuantityDTO> divide(@Valid @RequestBody OperationRequestDTO request,
                                             Authentication authentication) {
        UserEntity user = resolveUser(authentication);
        return RestResponse.success("Division successful",
                service.divide(request.getFirstQuantity(), request.getSecondQuantity(), user));
    }

    @PostMapping("/compare")
    public RestResponse<Boolean> compare(@Valid @RequestBody OperationRequestDTO request,
                                          Authentication authentication) {
        UserEntity user = resolveUser(authentication);
        boolean result = service.compare(
                request.getFirstQuantity(), request.getSecondQuantity(), user);
        return RestResponse.success("Comparison successful", result);
    }

    @PostMapping("/convert")
    public RestResponse<QuantityDTO> convert(@Valid @RequestBody ConvertRequestDTO request,
                                              Authentication authentication) {
        UserEntity user = resolveUser(authentication);
        QuantityDTO source = new QuantityDTO(
                request.getValue(), request.getUnitName(), request.getMeasurementType());
        return RestResponse.success("Conversion successful",
                service.convert(source, request.getTargetUnit(), user));
    }

    @GetMapping("/history")
    public RestResponse<?> getUserHistory(Authentication authentication) {
        String email = authentication.getName();
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return RestResponse.success("Fetched history successfully", service.getByUser(user));
    }

    /**
     * Resolves the current user from the Authentication object.
     * Returns null if the user is not authenticated (anonymous access).
     */
    private UserEntity resolveUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return userRepository.findByEmail(authentication.getName()).orElse(null);
    }
}