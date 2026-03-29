package bridgeLabz.quantity_measurement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuantityMeasurementAppTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void testEndToEnd_Add_Length() {

		String url = "/api/quantities/add";

		String requestJson = """
        {
          "firstQuantity": {
            "value": 1,
            "unitName": "FEET",
            "measurementType": "LengthUnit"
          },
          "secondQuantity": {
            "value": 12,
            "unitName": "INCHES",
            "measurementType": "LengthUnit"
          }
        }
        """;

		String response = restTemplate.postForObject(url, requestJson, String.class);

		assertTrue(response.contains("Addition successful"));
	}
}

