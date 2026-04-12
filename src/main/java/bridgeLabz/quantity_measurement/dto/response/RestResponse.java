package bridgeLabz.quantity_measurement.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse<T> {

    private boolean success;
    private String message;
    private T data;

    public static <T> RestResponse<T> success(String message, T data) {
        return new RestResponse<>(true, message, data);
    }

    public static <T> RestResponse<T> error(String message) {
        return new RestResponse<>(false, message, null);
    }
}
