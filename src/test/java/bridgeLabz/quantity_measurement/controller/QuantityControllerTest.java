package bridgeLabz.quantity_measurement.controller;

import bridgeLabz.quantity_measurement.dto.QuantityDTO;
import bridgeLabz.quantity_measurement.request.AddRequest;
import bridgeLabz.quantity_measurement.service.QuantityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuantityController.class)
public class QuantityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuantityService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddAPI() throws Exception {

        AddRequest request = new AddRequest(
                new QuantityDTO(5, "FEET", "LengthUnit"),
                new QuantityDTO(12, "INCHES", "LengthUnit")
        );

        Mockito.when(service.add(Mockito.any(), Mockito.any()))
                .thenReturn(new QuantityDTO(6, "FEET", "LengthUnit"));

        mockMvc.perform(post("/api/quantities/add")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}