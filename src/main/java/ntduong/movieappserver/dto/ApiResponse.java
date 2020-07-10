package ntduong.movieappserver.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class ApiResponse<T> {

    private HttpStatus success;
    private String message;
    private T result;

    public ApiResponse() {
    }

    public ApiResponse(HttpStatus success, String message) {
        this.success = success;
        this.message = message;
    }
}
