package ntduong.movieappserver.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiResponse<T> {

    private HttpStatus success;
    private String message;
    private T result;

    public ApiResponse() {
    }

    public ApiResponse(HttpStatus success, String message, T result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public ApiResponse(HttpStatus success, String message) {
        this.success = success;
        this.message = message;
    }
}
