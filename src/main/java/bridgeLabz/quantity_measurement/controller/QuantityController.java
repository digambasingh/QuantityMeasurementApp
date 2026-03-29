package bridgeLabz.quantity_measurement.controller;


import bridgeLabz.quantity_measurement.dto.QuantityDTO;
import bridgeLabz.quantity_measurement.request.AddRequest;
import bridgeLabz.quantity_measurement.response.RestResponse;
import bridgeLabz.quantity_measurement.service.QuantityService;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/quantities")
@RequiredArgsConstructor
public class QuantityController {

    private final QuantityService service;

    @PostMapping(value="/add", consumes = "application/json",
            produces = "application/json")
    public RestResponse<QuantityDTO> add(@RequestBody AddRequest request) {
        QuantityDTO result = service.add(request.getFirstQuantity(), request.getSecondQuantity());
        return new RestResponse<>(true, "Addition successful", result);
    }

    @PostMapping("/compare")
    public RestResponse<Boolean> compare(@RequestBody AddRequest request) {
        boolean result = service.compare(request.getFirstQuantity(), request.getSecondQuantity());
        return new RestResponse<>(true, "Comparison successful", result);
    }

    @PostMapping("/convert")
    public RestResponse<QuantityDTO> convert(@RequestBody QuantityDTO q,
                                             @RequestParam String targetUnit) {
        return new RestResponse<>(true, "Conversion successful",
                service.convert(q, targetUnit));
    }
}